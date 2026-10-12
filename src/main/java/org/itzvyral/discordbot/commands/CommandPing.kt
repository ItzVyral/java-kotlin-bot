package org.itzvyral.discordbot.commands

import org.itzvyral.discordbot.utils.commands.Command
import org.itzvyral.discordbot.utils.commands.CommandExecutor
import org.javacord.api.event.message.MessageCreateEvent

import org.itzvyral.discordbot.utils.ConfigManager

class CommandPing : CommandExecutor {
    override fun run(event: MessageCreateEvent, command: Command, args: Array<String>) {
        println("Command "+command.id+" used by "+event.messageAuthor.name)
        val gateway = event.api.latestGatewayLatency.toMillis()
        event.messageAuthor.message.reply("Ping: `"+gateway+"ms`")
    }
}