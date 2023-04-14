# Extended Drawers

A mod inspired by storage drawers that aims to allow for easy creation of powerful yet not
overpowered storage using simple components.

## Features
### In-game Guide Book
Crafted by combining a book with and upgrade frame, the guide book will explain in more detail how the mod works.

### Drawers
Drawers are blocks that can store a lot of a few items. They can be interacted with in world or using hopper or pipes from other mods.

### Access Points
Access points are blocks that can interact with multiple drawers that are connected to them. 
They only support insertion in world, but can be extracted from using pipes
and hoppers.

### Shadow Drawers
Shadow drawers show the amount of a resource that is available in the whole network and allows
for insertion and expansion of that resource.

### Upgrades
Upgrades are items that extend the capacity of a drawer slot. 

### Locking
You can use a lock on drawers to make them keep their selected item even when empty.

### Voiding mode
If sneak clicked with a lava bucket, drawers will start voiding excess items

### Hidden mode
Hide icons on drawers to reduce lag or hide your valuables. Remember, anyone can change it.

### Multiplier
Multiplier can be adjusted individually for each T[1,2,3,4] upgrade.

## Configuration

The standard capacity has been increased and can be changed in the config.

The parameters have been added:
`config\extended_drawers\common.properties`

`# How many items drawers are able to hold
defaultCapacity = 2048
# Multiplier for the T1 upgrade, Default: 2*2
CapacityMultiplierT1 = 4
# Multiplier for the T2 upgrade, Default: 4*4
CapacityMultiplierT2 = 16
# Multiplier for the T3 upgrade, Default: 8*8
CapacityMultiplierT3 = 64
# Multiplier for the T4 upgrade, Default: 16*16
CapacityMultiplierT4 = 256`

Please note: Large capacity multipliers can lead to crashes, lags or broken world(s).

## Credits
Source: Extended Drawers
Fork from: https://github.com/MattiDragon/ExtendedDrawers || https://www.curseforge.com/minecraft/mc-mods/extended-drawers
