package pt.dioguin.feltzbot.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.EnumSet;

public class messageReactionAdd extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event){

        User user = event.getUser();

        if (user == event.getJDA().getSelfUser()) return;

        assert user != null;
        Member member = event.getGuild().getMember(user);

        if (event.getTextChannel().getId().equals("970298550858698824")) {
            assert member != null;
            event.getGuild().addRoleToMember(member, event.getGuild().getRoleById("970284166769959023")).queue();
            return;
        }

        if (event.getTextChannel().getId().equals("972127996372717589")){

            if (event.getReactionEmote().getEmoji().equals("🛒")){

                event.getReaction().removeReaction(user).queue();

                boolean compras = false;

                for (TextChannel channel : event.getGuild().getTextChannels()){
                    if (channel.getName().equalsIgnoreCase("compras-" + user.getId())){
                        compras = true;
                        break;
                    }
                }

                if (!compras){
                    TextChannel channel = event.getGuild().createTextChannel("compras-" + user.getId(), event.getGuild().getCategoryById("972127937446965348")).complete();

                    channel.createPermissionOverride(event.getGuild().getPublicRole())
                            .setDeny(Permission.VIEW_CHANNEL)
                            .queue();

                    channel.createPermissionOverride(event.getGuild().getRoleById("972144617392844871"))
                            .setAllow(Permission.VIEW_CHANNEL)
                            .queue();

                    channel.createPermissionOverride(event.getMember())
                            .setAllow(Permission.VIEW_CHANNEL)
                            .queue();

                    EmbedBuilder eb = new EmbedBuilder()
                            .setTitle("Compras")
                            .setDescription("Você criou um ticket na categoria 'compras'.\nAguarde por um membro do nosso suporte, alguém irá auxiliar-lhe o mais rápido possível. Por favor não marque ninguém.");
                    channel.sendMessage(eb.build()).queue();
                    return;
                }

                EmbedBuilder eb = new EmbedBuilder()
                        .setTitle("Suporte")
                        .setDescription("Você já possui um ticket aberto na categoria 'compras'. Por favor obtenha o suporte a partir do mesmo.");
                user.openPrivateChannel().queue(channel -> channel.sendMessage(eb.build()).queue());

            }

            if (event.getReactionEmote().getEmoji().equals("🔨")){

                event.getReaction().removeReaction(user).queue();

                boolean revisao = false;

                for (TextChannel channel : event.getGuild().getTextChannels()){
                    if (channel.getName().equalsIgnoreCase("revisão-" + user.getId())){
                        revisao = true;
                        break;
                    }
                }

                if (!revisao){
                    TextChannel channel = event.getGuild().createTextChannel("revisão-" + user.getId(), event.getGuild().getCategoryById("972127937446965348")).complete();

                    channel.createPermissionOverride(event.getGuild().getPublicRole())
                            .setDeny(Permission.VIEW_CHANNEL)
                            .queue();

                    channel.createPermissionOverride(event.getGuild().getRoleById("972144617392844871"))
                            .setAllow(Permission.VIEW_CHANNEL)
                            .queue();

                    channel.createPermissionOverride(event.getMember())
                            .setAllow(Permission.VIEW_CHANNEL)
                            .queue();

                    EmbedBuilder eb = new EmbedBuilder()
                            .setTitle("Revisão")
                            .setDescription("Você criou um ticket na categoria 'revisão'.\nAguarde por um membro do nosso suporte, alguém irá auxiliar-lhe o mais rápido possível. Por favor não marque ninguém.");
                    channel.sendMessage(eb.build()).queue();
                    return;
                }

                EmbedBuilder eb = new EmbedBuilder()
                        .setTitle("Suporte")
                        .setDescription("Você já possui um ticket aberto na categoria 'revisão'. Por favor obtenha o suporte a partir do mesmo.");
                user.openPrivateChannel().queue(channel -> channel.sendMessage(eb.build()).queue());

            }

            if (event.getReactionEmote().getEmoji().equals("📘")){

                event.getReaction().removeReaction(user).queue();

                boolean geral = false;

                for (TextChannel channel : event.getGuild().getTextChannels()){
                    if (channel.getName().equalsIgnoreCase("geral-" + user.getId())){
                        geral = true;
                        break;
                    }
                }

                if (!geral){
                    TextChannel channel = event.getGuild().createTextChannel("geral-" + user.getId(), event.getGuild().getCategoryById("972127937446965348")).complete();

                    channel.createPermissionOverride(event.getGuild().getPublicRole())
                            .setDeny(Permission.VIEW_CHANNEL)
                            .queue();

                    channel.createPermissionOverride(event.getGuild().getRoleById("972144617392844871"))
                            .setAllow(Permission.VIEW_CHANNEL)
                            .queue();

                    channel.createPermissionOverride(event.getMember())
                            .setAllow(Permission.VIEW_CHANNEL)
                            .queue();

                    EmbedBuilder eb = new EmbedBuilder()
                            .setTitle("Geral")
                            .setDescription("Você criou um ticket na categoria 'geral'.\nAguarde por um membro do nosso suporte, alguém irá auxiliar-lhe o mais rápido possível. Por favor não marque ninguém.");
                    channel.sendMessage(eb.build()).queue();
                    return;
                }

                EmbedBuilder eb = new EmbedBuilder()
                        .setTitle("Suporte")
                        .setDescription("Você já possui um ticket aberto na categoria 'geral'. Por favor obtenha o suporte a partir do mesmo.");
                user.openPrivateChannel().queue(channel -> channel.sendMessage(eb.build()).queue());

            }

        }

    }

}
