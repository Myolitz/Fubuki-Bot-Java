package org.myolitz.fubukibot.commands;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.utils.FileUpload;
import org.myolitz.fubukibot.utilities.Utils;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    Utils utils = new Utils();
    List<CommandData> guildCmds;

    /*
        Global Cmd: 100 Limit <-- Production (up to an hour to update)
        Guild Cmd: 100 Limit <-- Local (instantly updated)
     */

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        String cmd = event.getName();

        //Will run a '/welcome' cmd
        switch (cmd.toLowerCase()) {
            case "greet" -> {
                String userTag = event.getUser().getName();
                event.reply("Hello **" + userTag + "**").queue();
            }
            case "quote" -> {
                String resp = utils.GenerateQuote();
                event.reply(resp).queue();
            }
            case "help" -> {
                String resp = "";
                String intro = "## My current guild commands are: \n";
                for (CommandData a : guildCmds) {
                    resp += a.getName().replace(a.getName().charAt(0), a.getName().toUpperCase().charAt(0)) + "\n";
                }
                event.reply(intro + resp).queue();
            }
            case "picture" -> {
                event.replyFiles(FileUpload.fromData(utils.GenImage())).queue();
            }
            case "bucky-secrets" -> {
                String resp = "";
                String intro = "## My secret keywords are: \n";
                String[] words = utils.getSecrets();
                for (String s : words) {
                    resp += s.replace(s.charAt(0), s.toUpperCase().charAt(0)) + "\n";
                }
                event.reply(intro + resp).queue();
            }
//            case "cat-count" -> {
//                event.reply("There have been " + utils.getCatCount() + " cats generated today").queue();
//            }
        }
    }

    // Guild Commands (Local)
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        guildCmds = new ArrayList<>();

        guildCmds.add(Commands.slash("greet", "Get greeted"));
        guildCmds.add(Commands.slash("quote", "Get a Fubuki quote"));
        guildCmds.add(Commands.slash("picture", "Get a picture of Fubuki"));
        guildCmds.add(Commands.slash("bucky-secrets", "All the secret keywords"));
        guildCmds.add(Commands.slash("help", "List the current commands"));


        event.getGuild().updateCommands().addCommands(guildCmds).queue();

    }


    // Global Cmds
    @Override
    public void onReady(ReadyEvent event) {
        TextChannel intro = event.getJDA().getTextChannelById("1194808461641396234");
        intro.sendMessage("Yoohoo~ yours truly Fubuki class has arrived, so you’re the Commander? I'm looking forward to your guidance~").queue();

        List<CommandData> globalCmds = new ArrayList<>();
        globalCmds.add(Commands.slash("welcome", "Get Welcomed"));
        event.getJDA().updateCommands().addCommands(globalCmds).queue();
    }
}
