package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.commands.CreateDatabase;
import ru.andrey.kvstorage.commands.CreateTable;
import ru.andrey.kvstorage.commands.ReadKey;
import ru.andrey.kvstorage.commands.UpdateKey;

public enum DataBaseCommands {
    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != 1) {
                return () -> DatabaseCommandResult.error("For CREATE_DATABASE should be 1 args: database name");
            }

            return new CreateDatabase(env, args[DATABASE_NAME]);
        }
    },
    CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != 2) {
                return () -> DatabaseCommandResult.error("For CREATE_TABLE should be 2 args:" +
                        " database name, table name");
            }

            return new CreateTable(env, args[DATABASE_NAME], args[TABLE_NAME]);
        }
    },
    UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != 4) {
                return () -> DatabaseCommandResult.error("For UPDATE_KEY should be 4 args:" +
                        " database name, table name, key, value");
            }

            return new UpdateKey(env, args[DATABASE_NAME], args[TABLE_NAME], args[KEY], args[VALUE]);
        }
    },
    READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            if (args.length != 3) {
                return () -> DatabaseCommandResult.error("For READ_KEY should be 3 args:" +
                        " database name, table name, key");
            }

            return new ReadKey(env, args[DATABASE_NAME], args[TABLE_NAME], args[KEY]);
        }
    };

    //Indexes for args
    private final static int DATABASE_NAME = 0;
    private final static int TABLE_NAME = 1;
    private final static int KEY = 2;
    private final static int VALUE = 3;

    public abstract DatabaseCommand getCommand(ExecutionEnvironment env, String... args);

}
