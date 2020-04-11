package ru.andrey.kvstorage.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKey implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;
    private final String key;
    private final String value;

    public UpdateKey(ExecutionEnvironment environment, String databaseName,
                     String tableName, String key, String value) {
        this.environment = environment;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
        this.value = value;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> databaseOptional = environment.getDatabase(databaseName);
        if (databaseOptional.isEmpty()) {
            return DatabaseCommandResult.error("Database " + databaseName + " is not exist");
        }

        try {
            databaseOptional.get().write(tableName, key, value);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }

        return DatabaseCommandResult.success(value);
    }
}
