package de.miinoo.ultimateticket.manager;

import de.miinoo.ultimateticket.commands.SetupCommand;
import de.miinoo.ultimateticket.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

    public Map<String, ServerCommand> commands;

    public CommandManager() {
        this.commands = new ConcurrentHashMap<>();

        this.commands.put("setup", new SetupCommand());
    }

    public boolean perform(String command, Member member, TextChannel channel, Message message) {
        ServerCommand cmd;
        if((cmd = this.commands.get(command.toLowerCase())) != null) {
            cmd.performCommand(member, channel, message);
            return true;
        }
        return false;
    }

    public Map<String, ServerCommand> getCommands() {
        return commands;
    }
}
