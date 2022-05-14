package pt.dioguin.feltzbot.commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import pt.dioguin.feltzbot.Main;

import java.util.EnumSet;

public class FecharTicket extends SlashCommand {

    public FecharTicket(){
        this.name = "fecharticket";
        this.help = "Feche e elimine o ticket.";
    }

    @Override
    protected void execute(SlashCommandEvent event) {

        User user = event.getUser();
        Member member = event.getGuild().getMember(user);
        TextChannel channel = event.getTextChannel();

        if (channel.getName().contains("compras-") || channel.getName().contains("geral-") || channel.getName().contains("revisão-")){

            if (!member.getRoles().contains(event.getGuild().getRoleById("970283581157044254")) && !member.getRoles().contains(event.getGuild().getRoleById("970284514481934346")) && !member.getRoles().contains(event.getGuild().getRoleById("970287161347821628")) && !member.getRoles().contains(event.getGuild().getRoleById("970286479521755186")) && !member.getRoles().contains(event.getGuild().getRoleById("970286950479167538"))){
                event.reply("Você não possui permissão.").setEphemeral(true).queue();
                return;
            }

            for (Member us : channel.getMembers()){
                if (us.getUser() == Main.getJda().getSelfUser()) continue;
                EmbedBuilder eb = new EmbedBuilder()
                        .setTitle("Suporte")
                        .setDescription("O ticket denominado de " + channel.getName() + " foi finalizado.\nA nossa equipa espera que todas as suas dúvidas tenham sido esclarecidas!");
                us.getUser().openPrivateChannel().queue(ch -> ch.sendMessage(eb.build()).queue());
            }

            channel.delete().queue();

        }else{
            event.reply("Este não é um canal de suporte!").setEphemeral(true).queue();
        }

    }

}
