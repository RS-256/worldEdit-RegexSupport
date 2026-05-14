package com.rs256.wers.command;

import com.rs256.wers.WERS;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.*;
import net.minecraft.network.chat.*;

public class WERSCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext) {
        dispatcher.register(
                Commands.literal(WERS.MOD_ID)
                        .then(Commands.literal("reload")
                                .executes(commandContext -> executeReload())
                        )
        );
    }

    private static int executeReload() {
        return 1;
    }
}
