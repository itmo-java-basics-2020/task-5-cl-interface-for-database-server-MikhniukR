package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommands;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

import java.util.Arrays;

public class DatabaseServer {

    private static final int COMMAND_TYPE = 0;

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {

    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null) {
            return DatabaseCommandResult.error("Command should be not null");
        }

        String[] args = commandText.split(" ");

        try {
            return DatabaseCommands.valueOf(args[COMMAND_TYPE])
                    .getCommand(env, Arrays.copyOfRange(args, 1, args.length))
                    .execute();
        }
        catch (Exception e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}
