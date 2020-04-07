package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.commands.CreateDatabase;
import ru.andrey.kvstorage.commands.CreateTable;
import ru.andrey.kvstorage.commands.ReadKey;
import ru.andrey.kvstorage.commands.UpdateKey;

public enum DatabaseCommands {
    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new CreateDatabase(env, args[DATABASE_NAME]);
        }
    },
    CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new CreateTable(env, args[DATABASE_NAME], args[TABLE_NAME]);
        }
    },
    UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new UpdateKey(env, args[DATABASE_NAME], args[TABLE_NAME], args[KEY], args[VALUE]);
        }
    },
    READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
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
