package de.miinoo.ultimateticket.events;

import de.miinoo.ultimateticket.UltimateTicket;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Member member = event.getMember();
        String message = event.getMessage().getContentRaw();
        if (message.startsWith(UltimateTicket.getPrefix())) {
            String[] args = message.split(UltimateTicket.getPrefix());
            if(!UltimateTicket.getCommandManager().perform(args[1], member, event.getChannel(), event.getMessage())) {
                event.getChannel().sendMessage("Command not found!").queue();
            }
        }
    }
}
