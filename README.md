Table-Formatter-Tool
====================

Description
-----------

A tool to format LUA table so that values are sorted.

How to use
----------

```console
java -jar table-formatter-tool-1.0.0.jar C:\input.lua C:\output.lua
```

The input file is expecting content like this:

```lua
ROB_Exports = {
	["defaultRotation/rogue/subtlety"] = {
		["version"] = 9,
		["SortedActions"] = {
			"defaultRotation/rogue/kick", -- [1]
			"defaultRotation/rogue/crimson_vial", -- [2]
			"defaultRotation/rogue/marked_for_death_anticipation", -- [3]
			"defaultRotation/rogue/marked_for_death_deeper_stratagem", -- [4]
			"defaultRotation/rogue/marked_for_death", -- [5]
			"defaultRotation/rogue/nightblade_deeper_stratagem", -- [6]
			"defaultRotation/rogue/nightblade", -- [7]
			"defaultRotation/rogue/death_from_above_deeper_stratagem", -- [8]
			"defaultRotation/rogue/death_from_above", -- [9]
			"defaultRotation/rogue/eviscerate_deeper_stratagem", -- [10]
			"defaultRotation/rogue/eviscerate", -- [11]
			"defaultRotation/rogue/shadow_blades", -- [12]
			"defaultRotation/rogue/symbols_of_death", -- [13]
			"defaultRotation/rogue/shadowstrikes", -- [14]
			"defaultRotation/rogue/shadow_dance_symbols_of_death", -- [15]
			"defaultRotation/rogue/shadow_dance", -- [16]
			"defaultRotation/rogue/goremaw_s_bite", -- [17]
			"defaultRotation/rogue/backstab_anticipation", -- [18]
			"defaultRotation/rogue/backstab_deeper_stratagem", -- [19]
			"defaultRotation/rogue/backstab", -- [20]
		},
		["specID"] = 3,
		["ActionList"] = {
			["defaultRotation/rogue/death_from_above"] = {
				["b_gspellcost"] = true,
				["v_togglename"] = "Toggle 1",
				["v_durationstartedtime"] = 0,
				["v_keybind"] = "<keybind>",
				["v_gspellcosttype"] = "3",
				["v_p_knownotspell"] = "193531",
				["v_p_unitpowertype"] = "4",
				["v_spellname"] = "152150",
				["v_gspellcost"] = "25",
				["b_p_knownotspell"] = true,
				["v_p_unitpower"] = ">=5",
				["b_p_unitpower"] = true,
			},
			["defaultRotation/rogue/crimson_vial"] = {
				["b_gspellcost"] = true,
				["b_p_hp"] = true,
				["v_p_hp"] = "<60%",
				["v_durationstartedtime"] = 0,
				["v_spellname"] = "185311",
				["v_gspellcosttype"] = "3",
				["v_keybind"] = "<keybind>",
				["v_togglename"] = "Toggle 1",
				["v_gspellcost"] = "30",
			},
			["defaultRotation/rogue/kick"] = {
				["v_togglename"] = "Toggle 1",
				["v_durationstartedtime"] = 0,
				["b_t_interrupt"] = true,
				["v_keybind"] = "<keybind>",
				["v_spellname"] = "1766",
			},
		},
		["bindindex"] = 0,
	},
}
```