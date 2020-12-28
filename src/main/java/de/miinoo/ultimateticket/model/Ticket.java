package de.miinoo.ultimateticket.model;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    Member member;
    TextChannel channel;
    List<Message> messages;

    public Ticket(Member member, TextChannel channel) {
        this.member = member;
        this.channel = channel;

        messages = new ArrayList<>();
    }

    public Member getMember() {
        return member;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
