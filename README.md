<p align="center">
  <a href="https://github.com/tantaihaha4487/QDropConfirm/actions/workflows/build-plugin.yml" style="text-decoration:none;"><img src="https://github.com/tantaihaha4487/QDropConfirm/actions/workflows/build-plugin.yml/badge.svg" alt="Build Status"></a>
  <a href="https://github.com/tantaihaha4487/QDropConfirm/releases/latest" style="text-decoration:none;"><img src="https://img.shields.io/github/v/release/tantaihaha4487/QDropConfirm?style=flat-square" alt="Latest Release"></a>
  <!-- <a href="https://modrinth.com/plugin/qdropconfirm" style="text-decoration:none;"><img src="https://img.shields.io/modrinth/dt/YOUR-SLUG?color=00AF5C&label=Modrinth&style=flat-square" alt="Modrinth Downloads"></a> -->
</p>

# QDropConfirm

Think twice before you dropped your important item!

## Features

*   Requires a second key press to confirm dropping an item.
*   Configurable delay for drop confirmation.
*   Whitelist items that require drop confirmation.
*   Customizable confirmation message with MiniMessage support.

## Configuration

<details>
<summary>config.yml</summary>

```yaml
# Confirmation drop message (MiniMessage Support!)
# https://webui.advntr.dev/
confirm-message: '<b><gold>(<yellow>i</yellow>)</gold></b> <gradient:#6BF965:#64F9F5>Tap <b>Q</b> Again to drop!</gradient>'

# Delay(in ticks, 20 ticks = 1 second) after drop item to reset drop confirmation notify
delay: 20 # 1secs

# Whitelist item add confirm to drop
# https://jd.papermc.io/paper/1.21.10/org/bukkit/Material.html
whitelist:
  - DIAMOND_SWORD
  - DIAMOND_PICKAXE
  - DIAMOND_AXE
  - DIAMOND_SHOVEL
  - DIAMOND_HOE
  - DIAMOND_HELMET
  - DIAMOND_CHESTPLATE
  - DIAMOND_LEGGINGS
  - DIAMOND_BOOTS
  - NETHERITE_SWORD
  - NETHERITE_PICKAXE
  - NETHERITE_AXE
  - NETHERITE_SHOVEL
  - NETHERITE_HOE
  - NETHERITE_HELMET
  - NETHERITE_CHESTPLATE
  - NETHERITE_LEGGINGS
  - NETHERITE_BOOTS
```
</details>
