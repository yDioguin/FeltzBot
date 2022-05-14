package pt.dioguin.feltzbot.commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import pt.dioguin.feltzbot.lavaplayer.GuildMusicManager;
import pt.dioguin.feltzbot.lavaplayer.PlayerManager;

public class Parar extends SlashCommand {

    public Parar(){
        this.name = "parar";
        this.help = "Mande o bot parar de tocar música imediatamente.";
    }

    @Override
    protected void execute(SlashCommandEvent event) {

        User user = event.getUser();
        Member member = event.getGuild().getMember(user);
        TextChannel channel = event.getTextChannel();

        if (!member.getRoles().contains(event.getGuild().getRoleById("970283581157044254")) && !member.getRoles().contains(event.getGuild().getRoleById("970284514481934346"))){
            event.reply("Você não possui permissão.").setEphemeral(true).queue();
            return;
        }

        if (!event.getMember().getVoiceState().inVoiceChannel()){
            event.reply("Você precisa estar num canal de voz para que isto funcione.").setEphemeral(true).queue();
            return;
        }

        AudioManager audioManager = event.getGuild().getAudioManager();

        if (!audioManager.isConnected()){
            event.reply("Eu já não estou a tocar música!").setEphemeral(true).queue();
            return;
        }

        if (audioManager.getConnectedChannel() != event.getMember().getVoiceState().getChannel()){
            event.reply("Eu não estou no mesmo canal de você!").setEphemeral(true).queue();
            return;
        }

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());

        musicManager.scheduler.player.stopTrack();
        musicManager.scheduler.queue.clear();
        audioManager.closeAudioConnection();
        event.reply("Você parou de ouvir música!").setEphemeral(true).queue();

    }

}
