# Waifu Mod

An anime-themed Fabric companion mod for Minecraft 1.21.11. Summon your own waifu — she follows you, fights for you, calls you "senpai," chats back when you type, and grows stronger as your bond deepens.

Each waifu spawns with a random anime name (Sakura, Rem, Zero Two, Mikasa, Nezuko, Frieren…) and a random skin. Built on the same companion engine as Girlfriend Mod, but with anime-specific mechanics:

## Features

- **Dere personalities** — every waifu is one of five types with its own dialogue, reactions, and behavior:
  - `tsundere` — prickly but secretly affectionate ("B-baka!")
  - `yandere` — obsessive and possessive; gets jealous of other players near you
  - `kuudere` — cold, calm, and blunt
  - `dandere` — shy and soft-spoken, but gains affection fast
  - `genki` — hyper and cheerful, chats the most
- **Magical-girl transformation** — power up with a sakura-petal burst. While transformed she glows, gains Strength/Speed/Resistance/Regeneration, and shares buffs with you. Needs relationship 25+ and has a cooldown.
- **Headpats** — sneak + right-click with an empty hand to pat her head. She blushes, hearts and music notes pop, and affection rises.
- **Bond aura** — as relationship grows you gain passive buffs: Regeneration at 50, Resistance + Regeneration at 75, Absorption at 100, with cherry-blossom particles.
- **Anime food & gifts** — Onigiri, Pocky, Ramen, Bento, Taiyaki, and Mochi are craftable foods. Feeding them is the fastest way to win her over (Bento is best).
- **Cosplay / outfits** — dress her in `school`, `maid`, `kimono`, `magical`, `nurse`, or `catgirl` outfits.

## How to Use

- **Summon**: Use the Waifu Summoner item (Ingredients creative tab or `/waifu give`) or run `/waifu summon`. She gets a random anime name, skin, and personality.
- **Stats**: Name tag (two lines), HUD overlay when you look at her, action bar on right-click, `/waifu stats`, `/waifu list`, and the summoner tooltip. Stats show `[Personality] Lv, ♥Mood, Aff, Hunger, HP`.
- **Interact**: Right-click to toggle follow/wait. Sneak + right-click to headpat. Feed her food, give flowers/valuables, or rename with a name tag. Use a **Nether Star** on her to trigger her magical-girl transformation.
- **Chat**: Type near her; she replies in her personality's style. Chat replies have a short typing delay.

## Commands

All commands affect **the waifu closest to you**. You must be a player.

- `/waifu summon` – Spawn a waifu (random name, skin, personality).
- `/waifu give` – Give yourself a Waifu Summoner.
- `/waifu list` – Count and list nearby waifus with stats.
- `/waifu stats` – Show the closest waifu's stats.
- `/waifu personality <tsundere|yandere|kuudere|dandere|genki>` – Set her personality.
- `/waifu outfit <default|school|maid|kimono|magical|nurse|catgirl>` – Set her outfit.
- `/waifu transform` – Trigger the magical-girl transformation.
- `/waifu relationship <0–100>` / `/waifu mood <0–100>` – Set relationship / mood.
- `/waifu texture <default|alt|1–20>` – Set skin texture variant.
- `/waifu skin <username>` – Use a player's skin.

## Notes

The outfit and transform textures are **generated placeholders** (recolored from the base skin) — replace `assets/waifu-mod/textures/entity/waifu_outfit_*.png` and `waifu_transform.png` with real art. Food icons in `textures/item/` are also placeholders.

Requires Fabric Loader and Fabric API for Minecraft 1.21.11. Build with `./gradlew build`.
