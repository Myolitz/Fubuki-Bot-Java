package org.myolitz.fubukibot;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.myolitz.fubukibot.commands.CommandManager;
import org.myolitz.fubukibot.listeners.EventListener;


/**
 * JDA Bot (5.0.0-beta.19) (JDK 17)
 *
 * @author FFubuki on Discord
 * @author TechnoVision (Tutorial Guy)
 */

public class FubukiBot {

    private final Dotenv config;
    private final ShardManager shardManager;

    /**
     * Loads Env Vars & Builds The Bot Shard Manager
     * @throws InvalidTokenException when token is invalid
     */
    public FubukiBot() throws InvalidTokenException {
        config = Dotenv.configure().load();
        String x = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(x);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.enableIntents(
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.DIRECT_MESSAGES
                );
        builder.setActivity(Activity.watching("greatness unfold"));
        shardManager = builder.build();

        //Registers Listeners
        shardManager.addEventListener(new EventListener());
        shardManager.addEventListener(new CommandManager());

    }

    /**
     * Retrieves the shard manager
     * @return ShardManager for the bot (maybe could do a this.)
     */
    public ShardManager getShardManager() {
        return shardManager;
    }

    /**
     * Retrieves bot config
     * @return config for the bot
     */
    public Dotenv getConfig() {
        return config;
    }

    public static void main(String[] args) {
        try {
            FubukiBot bot = new FubukiBot();
            System.out.println("Yoohoo~ yours truly Fubuki class has arrived, so you’re the Commander? I'm looking forward to your guidance~");
        }
        catch (InvalidTokenException a) {
            System.out.println("Invalid Token! Check Token Validity");
        }

    }
}