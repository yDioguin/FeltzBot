package pt.dioguin.feltzbot;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import pt.dioguin.feltzbot.commands.*;
import pt.dioguin.feltzbot.events.messageReactionAdd;

import javax.security.auth.login.LoginException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    private static JDA jda;

    public static void main(String[] args) throws LoginException, InterruptedException {

        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setOwnerId("261565675372281856");
        builder.addSlashCommand(new Trancar());
        builder.addSlashCommand(new Destrancar());
        builder.addSlashCommand(new Verificacao());
        builder.addSlashCommand(new Ticket());
        builder.addSlashCommand(new Tocar());
        builder.addSlashCommand(new Parar());
        builder.addSlashCommand(new Pular());
        builder.addSlashCommand(new Limpar());
        builder.addSlashCommand(new FecharTicket());
        CommandClient commandClient = builder.build();

        jda = JDABuilder.createDefault("OTIzNjE5NDg3MDA4MzA5Mjc5.YcSprQ.MCPoNv4rZbbwe7c5F9jBWATgRfM")
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(commandClient)
                .addEventListeners(new messageReactionAdd())
                .build();

        jda.awaitReady();
        Guild guild = jda.getGuildById("970283209436848188");
        System.out.println("O bot foi inicializado, existem " + guild.getMemberCount() + " usuários neste servidor.");
        updateStatus(guild);

    }

    private static void updateStatus(Guild guild){

        String[] status = {"em redefeltz.com", "factions na Rede Feltz", "com " + guild.getMemberCount() + " usuários"};

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                jda.getPresence().setActivity(Activity.playing(status[new Random().nextInt(status.length)]));
            }
        }, 0, 1000 * 60);

    }

    public static JDA getJda() {
        return jda;
    }
}
