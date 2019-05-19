package org.sct.legendgang.command;

import org.bukkit.command.CommandSender;

/**
 * @author SCT_Alchemy
 * @date 2019-05-19 19:48
 */

public interface SubCommand {

    /**
     * 指令调用接口
     *
     * @param sender
     * @param args
     * @return
     */
    boolean execute(CommandSender sender, String[] args);

}
