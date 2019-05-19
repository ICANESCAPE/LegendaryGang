package org.sct.legendgang.command;

import com.google.common.collect.Maps;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

import org.sct.legendgang.Gang;
import org.sct.legendgang.command.sub.Add;
import org.sct.legendgang.command.sub.Create;
import org.sct.legendgang.command.sub.Reload;

import java.util.Map;

/**
 * @author SCT_Alchemy
 * @date 2018/12/21 12:46 PM
 */

public class CommandHandle implements CommandExecutor  {

    protected static final String LG = "lg";
    private Map<String, SubCommand> subCommandMap = Maps.newHashMap();

    public CommandHandle() {
        registerSubCommand("reload", new Reload());
        registerSubCommand("add", new Add());
        registerSubCommand("create", new Create());
    }

    public void registerSubCommand(String commandName, SubCommand command) {
        if (subCommandMap.containsKey(commandName)) {
            Gang.getInstance().getLogger().warning("发现重复子命令注册!");
        }
        subCommandMap.put(commandName, command);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(LG.equalsIgnoreCase(cmd.getName())) {
            if(args.length == 0) {
                if(!(sender instanceof Player)) {
                    subCommandMap.get("admin").execute(sender, args);
                    return true;
                }
                return true;
            }

            SubCommand subCommand = subCommandMap.get(args[0]);
            if (subCommand == null) {
                sender.sendMessage("§c未知指令!");
                return true;
            }
            subCommand.execute(sender, args);
        }
        return false;
    }

}


