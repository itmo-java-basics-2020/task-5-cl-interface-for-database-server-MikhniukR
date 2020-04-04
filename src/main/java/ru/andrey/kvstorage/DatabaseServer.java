package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DataBaseCommands;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

import java.util.Arrays;

public class DatabaseServer {

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
            return DataBaseCommands.valueOf(args[0])
                    .getCommand(env, Arrays.copyOfRange(args, 1, args.length))
                    .execute();
        }
        catch (IllegalArgumentException e) {
            return DatabaseCommandResult.error("No such command");
        }
    }
}
