package org.itzvyral.discordbot;

import org.itzvyral.discordbot.utils.ConfigManager;
import org.itzvyral.discordbot.utils.commands.MessageManager;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.io.File;

public class Main {

    private static DiscordApi api;
    private static ConfigManager configManager;

    public static void main() {

        configManager = new ConfigManager(new File(System.getProperty("user.dir"), "config.toml"));

        api = new DiscordApiBuilder()
                .setToken(configManager.getToml().getString("bot.token"))
                .setAllIntents()
                .login().join();

        api.addMessageCreateListener(MessageManager::create);

    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }
}
