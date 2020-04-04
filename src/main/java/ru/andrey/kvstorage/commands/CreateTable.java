package ru.andrey.kvstorage.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CreateTable implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;

    public CreateTable(ExecutionEnvironment environment, String databaseName, String tableName) {
        this.environment = environment;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> databaseOptional = environment.getDatabase(databaseName);
        if (databaseOptional.isEmpty()) {
            return DatabaseCommandResult.error("Database " + databaseName + " is not exist");
        }

        try {
            databaseOptional.get().createTableIfNotExists(tableName);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error("Some error in database");
        }

        return DatabaseCommandResult.success("Table " + tableName + " was created");
    }
}
