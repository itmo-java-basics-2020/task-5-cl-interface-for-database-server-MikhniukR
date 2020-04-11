package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    Optional<String> getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static DatabaseCommandResult success(String result) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.SUCCESS, result, null);
    }

    static DatabaseCommandResult error(String errorMessage) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.FAILED, null, errorMessage);
    }


    class DatabaseCommandResultImpl implements DatabaseCommandResult {

        private final DatabaseCommandStatus status;
        private final String result;
        private final String errorMessage;

        private DatabaseCommandResultImpl(DatabaseCommandStatus status, String result, String errorMessage) {
            this.status = status;
            this.result = result;
            this.errorMessage = errorMessage;
        }

        @Override
        public Optional<String> getResult() {
            if (isSuccess()) {
                return Optional.of(result);
            }

            return Optional.empty();
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return status.equals(DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public Optional<String> getErrorMessage() {
            if (!isSuccess()) {
                return Optional.of(errorMessage);
            }

            return Optional.empty();
        }
    }
}