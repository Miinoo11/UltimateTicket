package de.miinoo.ultimateticket.events;

import de.miinoo.ultimateticket.UltimateTicket;
import de.miinoo.ultimateticket.util.MessageUtil;
import de.miinoo.ultimateticket.util.StringUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.EnumSet;

public class ReactionListener extends ListenerAdapter {

    Category tickets = null;

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        Guild guild = event.getGuild();
        if (event.getChannel().getId().equals("793097967971991552")) {
            if (event.getMember().getUser() != UltimateTicket.getJda().getSelfUser()) {
                event.getChannel().removeReactionById(event.getReaction().getMessageId(), event.getReactionEmote().getEmote(), event.getUser()).complete();

                if(guild.getCategoriesByName("tickets", true).size() == 0) {
                    guild.createCategory("tickets").complete();
                    tickets = guild.getCategoriesByName("tickets", true).get(0);

                    tickets.createPermissionOverride(
                            guild.getRolesByName("UltimateSupport", true).get(0))
                            .setAllow(Permission.VIEW_CHANNEL).complete();
                    tickets.createPermissionOverride(
                            guild.getRoleById(793078045674045451L))
                            .setDeny(Permission.VIEW_CHANNEL).complete();
                    tickets.createPermissionOverride(event.getMember())
                            .setAllow(Permission.VIEW_CHANNEL)
                            .complete();
                }

                String ticketName = "UltimateTicket-" + StringUtil.randomString(10);
                guild.createTextChannel(ticketName, guild.getCategoriesByName("tickets", true).get(0)).complete();

                TextChannel ticketChannel = guild.getTextChannelsByName(ticketName, true).get(0);

                ticketChannel.getManager().setParent(guild.getCategoriesByName("tickets", true).get(0)).queue();
                ticketChannel.createPermissionOverride(event.getMember()).setAllow(Permission.VIEW_CHANNEL).queue();

                EmbedBuilder ticketInfo = new EmbedBuilder();
                ticketInfo.setColor(0xd01940);
                ticketInfo.setTitle("<:UT:793130837734326292> - Created by: <@" + event.getMember().getId() + ">");
                ticketInfo.setDescription("Reactions:");
                ticketInfo.addField("Close Ticket", "\uD83D\uDDD1", true);
                ticketChannel.sendMessage(ticketInfo.build()).complete();

                ticketChannel.addReactionById(MessageUtil.getLastMessageOfUser(ticketChannel, event.getJDA().getSelfUser()).getId(), "\uD83D\uDDD1").complete();
            }
        }

        if(event.getChannel().getName().contains("UltimateTicket-".toLowerCase())) {
            TextChannel ticketChannel = event.getChannel();
            if(!event.getUser().equals(event.getJDA().getSelfUser())) {
                if(event.getReactionEmote().equals(MessageReaction.ReactionEmote.fromUnicode("\uD83D\uDDD1", event.getJDA()))) {
                    ticketChannel.delete().queue();
                }
            }
        }
    }
}
