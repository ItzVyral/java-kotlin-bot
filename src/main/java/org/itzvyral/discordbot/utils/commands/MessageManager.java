package org.itzvyral.discordbot.utils.commands;

import org.itzvyral.discordbot.Main;
import org.itzvyral.discordbot.commands.CommandPing;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Arrays;

public class MessageManager {

    private static CommandsRegistry registry = new CommandsRegistry();

    static {
        registry.addCommand(new Command(
                "ping",
                "Pings the bot",
                new CommandPing(),
                "ping", "p?"
        ));
    }

    private static final String PREFIX = Main.getConfigManager().getToml().getString("bot.prefix");

    public static void create(MessageCreateEvent event) {

        if (event.getMessageContent().startsWith(PREFIX)) {
            String[] args = event.getMessageContent().split(" ");
            String commandName = args[0].substring(PREFIX.length());
            args = args.length == 1 ? new String[0] : Arrays.copyOfRange(args, 1, args.length - 1);

            String[] finalArgs = args;
            registry.getByAlias(commandName).ifPresent((cmd) -> {
                cmd.getExecutor().run(event, cmd, finalArgs);
            });
        }

    }

}
