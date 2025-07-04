import net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.STARTUP

group = "me.cjcrafter"
version = "3.7.3"

plugins {
    `java-library`
    id("io.github.goooler.shadow") version "8.1.7"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("xyz.jpenilla.run-paper")
//    id("io.papermc.paperweight.userdev") version "2.0.0-beta.16"
}

//paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION


// See https://github.com/Minecrell/plugin-yml
bukkit {
    main = "me.cjcrafter.biomemanager.BiomeManager"
    name = "BiomeManager"
    apiVersion = "1.21.4"
    load = STARTUP // required to register biomes before world load
    foliaSupported = true

    authors = listOf("AlexDev_", "CJCrafter")
    depend = listOf("ProtocolLib")
    softDepend = listOf("TerraformGenerator")  // softdepend on plugins that register custom biomes so we can modify them
    loadBefore = listOf("WorldEdit")
}

repositories {
    mavenCentral() // shade bStats
}

dependencies {
//    paperweight.paperDevBundle("1.21.5-R0.1-SNAPSHOT")
    implementation(project(":"))
//    implementation(project(":Biome_1_21_R1", "reobf"))
    implementation(project(":Biome_1_21_4"))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.jar {
    manifest {
        attributes["paperweight-mappings-namespace"] = "mojang"
    }
}

tasks.shadowJar {
    destinationDirectory.set(file("../build"))
    archiveFileName.set("BiomeManager-${project.version}.jar")

    dependencies {
        include(project(":"))
//        include(project(":Biome_1_21_R1"))
        include(project(":Biome_1_21_4"))

        relocate("org.bstats", "me.cjcrafter.biomemanager.lib.bstats") {
            include(dependency("org.bstats:"))
        }
    }

    // This doesn't actually include any dependencies, this relocates all references
    // to the mechanics core lib.
//    relocate("net.kyori", "me.deecaad.core.lib")
}

tasks.runServer {
    // Configure the Minecraft version for our task.
    // This is the only required configuration besides applying the plugin.
    // Your plugin's jar (or shadowJar if present) will be used automatically.
    minecraftVersion("1.21.4")
}