package pt.dioguin.feltzbot.commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class Verificacao extends SlashCommand {

    public Verificacao(){
        this.name = "verificacao";
        this.help = "Implementa a mensagem de verificação.";
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

        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Verificação")
                .setDescription("Reaga abaixo para ter acesso aos canais.\nAo reagir você está de acordo com todas as regras do servidor e caso quebre alguma, aceita as punições que o servidor implicar.");

        channel.sendMessage(eb.build()).queue(message -> message.addReaction("✅").queue());

    }

}
