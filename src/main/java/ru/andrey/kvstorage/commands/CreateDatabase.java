package ru.andrey.kvstorage.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

public class CreateDatabase implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final String databaseName;

    public CreateDatabase(ExecutionEnvironment environment, String databaseName) {
        this.environment = environment;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() {
        //TODO change null for smth
        environment.addDatabase(null);

        return DatabaseCommandResult.success("Database " + databaseName + " was created");
    }
}
