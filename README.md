# Villager Essentials
This plugin shares trading discounts with all players and eliminates the need to change game difficulty for villager zombification. Tested on Spigot, not tested on Paper.
 
## Features
### Villagers always transform to zombie villagers
- Villagers will transform when killed by any zombie variant (i.e. a zombie, zombie villager, husk, or drowned). 
- This is true regardless of game difficulty. 
- This prevents villagers from being forever lost when killed by a zombie. 
- Inspired by MarioFinale's VillagerSaver plugin (https://github.com/MarioFinale/VillagerSaver).
### All players receive trading discounts
- Players will receive the largest discount that exists within the villager's Gossips data.
- For example, if Steve cures a villager and receives a zombie discount, all players will be able to use that single-cure discount. If another player then zombifies and cures that villager again, that discount will add to Steve's and provide all players with a double-cure discount.
- Inspired by RevengeOfLordMagic's Global Trades datapack (https://www.planetminecraft.com/data-pack/global-trades/). However, this plugin should have improved performance; ReventOfLordMagic's datapack updates villagers' data every minute, while Villager Essentials updates data on a need-basis.
### All zombie variants have a 50% chance of being able to pick up items
- Zombies that pick up items do not despawn, thus eliminating the need for rare nametags.
- This is useful for holding a zombie in a villager trading hall to transform villagers for curing.
- This is true regardless of difficulty.
- A good example of using a zombie in a trading hall is in Gecko's trading hall tutorial (https://www.youtube.com/watch?v=Btd27JpomJk).

Relies heavily on BananaPuncher714's NBTEditor library to modify entity data (https://github.com/BananaPuncher714/NBTEditor/blob/master/src/main/java/io/github/bananapuncher714/nbteditor/NBTEditor.java).
