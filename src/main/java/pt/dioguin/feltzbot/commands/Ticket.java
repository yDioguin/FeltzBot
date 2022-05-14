package pt.dioguin.feltzbot.commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class Ticket extends SlashCommand {

    public Ticket(){
        this.name = "ticket";
        this.help = "Implementa a mensagem de ticket.";
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
                .setTitle("Ticket")
                .setDescription("Reaga abaixo para abrir um canal de suporte.\n\n🛒 -> Compras \n🔨 -> Revisão \n📘 -> Geral");

        channel.sendMessage(eb.build()).queue(message -> {
            message.addReaction("🛒").queue();
            message.addReaction("🔨").queue();
            message.addReaction("📘").queue();
        });

    }

}
