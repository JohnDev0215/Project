package com.amplifyframework.datastore.storage.sqlite;

import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
final class CreateSqlCommands {
    private final Set<SqlCommand> createIndexCommands;
    private final Set<SqlCommand> createTableCommands;

    CreateSqlCommands(Set<SqlCommand> set, Set<SqlCommand> set2) {
        this.createTableCommands = set;
        this.createIndexCommands = set2;
    }

    Set<SqlCommand> getCreateTableCommands() {
        return this.createTableCommands;
    }

    Set<SqlCommand> getCreateIndexCommands() {
        return this.createIndexCommands;
    }
}
