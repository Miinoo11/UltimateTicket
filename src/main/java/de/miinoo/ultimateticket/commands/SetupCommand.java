package de.miinoo.ultimateticket.commands;

import de.miinoo.ultimateticket.UltimateTicket;
import de.miinoo.ultimateticket.commands.types.ServerCommand;
import de.miinoo.ultimateticket.util.MessageUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

public class SetupCommand implements ServerCommand {

    //UfChannel: 793097967971991552

    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {
        Guild guild = channel.getGuild();
        if(member.isOwner()) {
            EmbedBuilder em = new EmbedBuilder();
            em.setDescription("<:UF:793097817244696576> _Click the reaction below to create a ticket!\n" +
                    "If that doesn't work, run `" + UltimateTicket.getPrefix() + "new` in another channel._");
            em.setColor(0xd01940);

            if(guild.getCategoriesByName("tickets", true).size() == 0) {
                guild.createCategory("tickets").complete();
            }

            if(guild.getRolesByName("UltimateSupport", true).size() == 0) {
                guild.createRole()
                        .setColor(0xA2D781)
                        .setName("UltimateSupport")
                        .setMentionable(true)
                        .complete();
            }

            channel.sendMessage(em.build()).complete();
            channel.addReactionById(MessageUtil.getLastMessageOfUser(channel, UltimateTicket.getJda().getSelfUser()).getId(), ":UT:793130837734326292").complete();
        }
    }


}
