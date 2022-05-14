package pt.dioguin.feltzbot.commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.managers.AudioManager;
import pt.dioguin.feltzbot.lavaplayer.PlayerManager;
import pw.chew.jdachewtils.command.OptionHelper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

public class Tocar extends SlashCommand {

    public Tocar(){
        this.name = "tocar";
        this.help = "Começe a tocar algo do YouTube.";
        this.options = Collections.singletonList(new OptionData(OptionType.STRING, "link", "Link do vídeo.").setRequired(true));
    }

    @Override
    protected void execute(SlashCommandEvent event) {

        String message = OptionHelper.optString(event, "link");
        User user = event.getUser();
        Member member = event.getGuild().getMember(user);

        if (!member.getRoles().contains(event.getGuild().getRoleById("970283581157044254")) && !member.getRoles().contains(event.getGuild().getRoleById("970284514481934346"))){
            event.reply("Você não possui permissão.").setEphemeral(true).queue();
            return;
        }

        if (message == null) {
            event.reply("Link inválido!").setEphemeral(true).queue();
            return;
        }

        if (!member.getRoles().contains(event.getGuild().getRoleById("970283581157044254")) && !member.getRoles().contains(event.getGuild().getRoleById("970284514481934346"))){
            event.reply("Você não possui permissão.").setEphemeral(true).queue();
            return;
        }

        if (message == null){
            event.reply("Link inválido.").setEphemeral(true).queue();
            return;
        }

        if (!event.getMember().getVoiceState().inVoiceChannel()){
            event.reply("Você precisa estar num canal de voz para que funcione.").setEphemeral(true).queue();
            return;
        }

        if (!event.getMember().getVoiceState().getChannel().getId().equals("970298087513919558")){
            event.reply("Você precisar estar no canal de música.").setEphemeral(true).queue();
            return;
        }

        AudioManager audioManager = event.getGuild().getAudioManager();
        audioManager.openAudioConnection(event.getMember().getVoiceState().getChannel());
        String link = message;

        if (!isUrl(link)){
            event.reply("Link inválido!").setEphemeral(true);
            return;
        }

        event.reply("Música adicionada!").setEphemeral(true).queue();
        PlayerManager.getInstance().loadAndPlay(event.getTextChannel(), link);

    }

    private boolean isUrl(String url){
        try{
            new URI(url);
            return true;
        }catch (URISyntaxException e){
            return false;
        }
    }

}
