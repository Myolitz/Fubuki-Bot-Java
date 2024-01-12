package org.myolitz.fubukibot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jetbrains.annotations.NotNull;
import org.myolitz.fubukibot.utilities.Utils;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class EventListener extends ListenerAdapter {
    Utils utils = new Utils();

    // Pseudo-Commands that do not require slash or prefix
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        if (event.getAuthor().isBot()) {
            event.getChannel().sendMessage("I don't talk to bots");
        } else {
            switch (message.toLowerCase()) {
                case "fubuki" -> {
                    event.getChannel().sendMessage("Yo~ dayo~").queue();
                }
                case "sbeve" -> {
                    event.getChannel().sendMessage("She Believed :(").queue();
                }
                case "sbren" -> {
                    event.getChannel().sendMessage("She's Broken Because She Believed").queue();
                }
                case "ping" -> {
                    event.getChannel().sendMessage("Pong!").queue();
                }
                case "your mother", "your mother." -> {
                    event.getChannel().sendMessage("Ya daddy").queue();
                }
                case "cat" -> {
                    boolean x = utils.catChance();
                    boolean y = utils.uniChance();
                    if (event.getGuild().getName().equalsIgnoreCase("Sbeve")) {
                        if (event.getChannel().getName().equalsIgnoreCase("cat-spam")) {
                            if (event.getAuthor().getName().equalsIgnoreCase("ffubuki")) {
                                event.getChannel().sendFiles(FileUpload.fromData(utils.getCat("uni"))).queue();
                            }
                            if (y) {
                                event.getChannel().sendFiles(FileUpload.fromData(utils.getCat("uni"))).queue();
                            }
                            if (x) {
                                event.getChannel().sendFiles(FileUpload.fromData(utils.getCat("angry"))).queue();
                            }
                        }
                    } else if (event.getGuild().getName().equalsIgnoreCase("Myo's Crack Den")) {
                        if (event.getChannel().getName().equalsIgnoreCase("cat-test")) {
                            //utils.increaseCat();
                            event.getChannel().sendFiles(FileUpload.fromData(utils.getCat("angry"))).queue();
                        }
                    }
                }
            }
        }
    }
    /**
     * Requires "Guild Members" gateway intent
     * Activates on member join
     */
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        //Grabs the newly joined user and
        User user = event.getUser();
        String avatarUrl = event.getUser().getAvatarUrl();

        String guildName = event.getGuild().getName();

        //#Bot-Test
        //TextChannel chan = event.getGuild().getTextChannelById("1194742650591510589");
        //Welcome-Test
        TextChannel chan = event.getGuild()
                .getTextChannelById("1194813804060610610");

        //Create an embed + Message that tags user upon join
        String message = "Welcome to the Fleet "
                + user.getAsMention()
                + "!";
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(user.getName())
                .setThumbnail(avatarUrl)
                .setDescription("Profile Created:\n" + user.getTimeCreated().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)))
                .setFooter("Created by Myo :D");

        chan.sendMessage(message).setEmbeds(embed.build()).queue();
    }

    /*
       Will automatically log any user reaction in a designated channel
       Maybe poke around with CustomEmoji implementation to revert to "x reacted with y in [link]"
    */
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {

        //Grabs user who reacted
        User user = event.getUser();
        //Grabs emote name
        String emote = event.getEmoji().getName();

        //Gets the channel as a name + obj as well as a jump link
        Channel channel = event.getChannel();
        String channelMentioned = channel.getAsMention();
        String jumpLink = event.getJumpUrl();

        //Desired output
        String message = user.getName() + " reacted to a message in " + jumpLink;

        //Will differentiate between Sbeve and Test (as those 2 will be the only servers it'll ever be active in)
        if (event.getGuild().getName().equalsIgnoreCase("Sbeve")) {
            TextChannel txtChanSbeve =
                    event.getGuild().getTextChannelById("1194808461641396234");
            txtChanSbeve.sendMessage(message).queue();
        }
        else if (event.getGuild().getName().equalsIgnoreCase("Myo's Crack Den")) {
            TextChannel txtChanTest =
                    event.getGuild().getTextChannelById("1194742650591510589");
            txtChanTest.sendMessage(message).queue();
        }

    }


}
