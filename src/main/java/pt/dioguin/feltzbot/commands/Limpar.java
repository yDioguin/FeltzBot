package pt.dioguin.feltzbot.commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import pw.chew.jdachewtils.command.OptionHelper;

import java.util.Collections;
import java.util.List;

public class Limpar extends SlashCommand {

    public Limpar(){
        this.name = "limpar";
        this.help = "limpe uma quantia de mensagens do chat.";

        this.options = Collections.singletonList(new OptionData(OptionType.INTEGER, "quantia", "Quantia de mensagens para limpar").setRequired(true));
    }

    @Override
    protected void execute(SlashCommandEvent event) {

        double quantity = OptionHelper.optDouble(event, "quantia");
        User user = event.getUser();
        Member member = event.getGuild().getMember(user);

        if (!member.getRoles().contains(event.getGuild().getRoleById("970283581157044254")) && !member.getRoles().contains(event.getGuild().getRoleById("970284514481934346"))){
            event.reply("Você não possui permissão.").setEphemeral(true).queue();
            return;
        }

        if (quantity < 1){
            event.reply("Quantia de mensagens inválida!").setEphemeral(true).queue();
            return;
        }

        List<Message> messages = event.getChannel().getHistory().retrievePast((int) quantity).complete();
        messages.forEach(message -> event.getChannel().deleteMessageById(message.getId()).queue());
        event.getChannel().sendMessage("Foram apagadas " + (int) quantity + " mensagens com sucesso").queue();

    }

}
