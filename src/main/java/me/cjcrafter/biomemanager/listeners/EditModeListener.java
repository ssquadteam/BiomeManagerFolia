package me.cjcrafter.biomemanager.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.Set;

public class EditModeListener implements Listener {

    private final Set<Player> injectedPlayers;

    public EditModeListener() {
        injectedPlayers = new HashSet<>();
    }

    public boolean isEnabled(Player player) {
        return injectedPlayers.contains(player);
    }

    public void toggle(Player player, boolean enabled) {
        if (enabled)
            injectedPlayers.add(player);
        else
            injectedPlayers.remove(player);
    }

//    @EventHandler
//    public void onMove(PlayerMoveEvent event) {
//
//        // Only run a check if the player has moved a considerable distance
//        if (event.getFrom().getBlockX() == event.getTo().getBlockX()
//            && event.getFrom().getBlockY() == event.getTo().getBlockY()
//            && event.getFrom().getBlockZ() == event.getTo().getBlockZ())
//            return;
//
//        if (!injectedPlayers.contains(event.getPlayer()))
//            return;
//
//        Player player = event.getPlayer();
//        Audience audience = MechanicsCore.getPlugin().adventure.sender(player);
//        Block previous = event.getFrom().getBlock();
//        Block current = event.getTo().getBlock();
//        Block head = player.getEyeLocation().getBlock();
//
//        // First, we should tell the player what type of block their head is
//        // in. This is important for CAVE_AIR for the cave sound settings.
//        TextComponent.Builder air = text();
//        air.append(text("Currently in ").color(NamedTextColor.GRAY));
//        air.append(text(StringUtil.snakeToReadable(head.getType().name())).color(NamedTextColor.GREEN).decorate(TextDecoration.UNDERLINED));
//        audience.sendActionBar(air);
//
//        // After we switch biomes, we should tell the player information about
//        // the biome they have entered. Basically, we should run `/biome menu`
//        // for the biome they entered. Let's compare keys to prepare for an
//        // upcoming spigot enum change.
//        BiomeWrapper oldBiome = BiomeCompatibilityAPI.getBiomeCompatibility().getBiomeAt(previous);
//        BiomeWrapper newBiome = BiomeCompatibilityAPI.getBiomeCompatibility().getBiomeAt(current);
//        if (!Objects.equals(oldBiome, newBiome)) {
//            Command.menu(player, new BiomeHolder(newBiome.getBukkitBiome(), newBiome.getKey()));
//        }
//    }
}
