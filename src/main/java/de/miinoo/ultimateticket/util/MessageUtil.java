package de.miinoo.ultimateticket.util;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class MessageUtil {

    public static Message getLastMessageOfUser(MessageChannel channel, User user) {
        try {
            return getChannelMessages(channel, user).get().get(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CompletableFuture<List<Message>> getChannelMessages(MessageChannel channel, User user) {
        return channel.getIterableHistory()
                .takeAsync(10) // Collect 10 messages
                .thenApply(list ->
                        list.stream()
                                .filter(m -> m.getAuthor().equals(user)) // Filter messages by author
                                .collect(Collectors.toList())
                );
    }

}
