package de.miinoo.ultimateticket;

import de.miinoo.ultimateticket.events.CommandListener;
import de.miinoo.ultimateticket.events.ReactionListener;
import de.miinoo.ultimateticket.manager.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class UltimateTicket {

    private static JDA jda;
    private static String serverID = "793078045674045451";
    private static String prefix = "ut!";

    private static CommandManager commandManager;

    public static void main(String[] args) throws LoginException {
        jda = JDABuilder.createLight("NzkzMDgwOTA4MTcyMzYxNzI5.X-nEJw.aIS_4nOvn8N2Gyk7GqX4RyiKIRU",
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.GUILD_WEBHOOKS)
                .addEventListeners(new CommandListener(), new ReactionListener())
                .build();

        jda.getPresence().setActivity(Activity.watching("at support channels"));

        commandManager = new CommandManager();
    }

    public static JDA getJda() {
        return jda;
    }

    public static String getServerID() {
        return serverID;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }
}
