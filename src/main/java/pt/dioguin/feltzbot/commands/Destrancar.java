package pt.dioguin.feltzbot.commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.EnumSet;

public class Destrancar extends SlashCommand {

    public Destrancar(){
        this.name = "destrancar";
        this.help = "Destranque o chat para que todos tenham permissão de falar.";
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

        channel.getManager().putPermissionOverride(event.getGuild().getRoleById("970283209436848188"), EnumSet.of(Permission.MESSAGE_WRITE), null).queue();
        event.reply("Canal destrancado com sucesso!").setEphemeral(true).queue();

    }
}
