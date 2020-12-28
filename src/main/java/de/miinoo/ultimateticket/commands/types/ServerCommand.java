package de.miinoo.ultimateticket.commands.types;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public interface ServerCommand {

    void performCommand(Member member, TextChannel channel, Message message);


}
