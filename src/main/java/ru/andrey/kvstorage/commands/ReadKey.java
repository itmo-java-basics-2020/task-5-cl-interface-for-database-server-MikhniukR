package ru.andrey.kvstorage.commands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKey implements DatabaseCommand {

    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;
    private final String key;

    public ReadKey(ExecutionEnvironment environment, String databaseName, String tableName, String key) {
        this.environment = environment;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> databaseOptional = environment.getDatabase(databaseName);
        if (databaseOptional.isEmpty()) {
            return DatabaseCommandResult.error("Database " + databaseName + " is not exist");
        }

        try {
            return DatabaseCommandResult.success(databaseOptional.get().read(tableName, key));
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error("Some error in database");
        }
    }
}
