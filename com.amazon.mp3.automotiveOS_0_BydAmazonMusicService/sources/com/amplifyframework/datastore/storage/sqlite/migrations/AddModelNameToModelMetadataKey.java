package com.amplifyframework.datastore.storage.sqlite.migrations;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.datastore.model.SystemModelsProviderFactory;
import com.amplifyframework.logging.Logger;
import com.amplifyframework.util.Wrap;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class AddModelNameToModelMetadataKey implements ModelMigration {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private final SQLiteDatabase database;
    private final ModelProvider modelProvider;

    public AddModelNameToModelMetadataKey(SQLiteDatabase sQLiteDatabase, ModelProvider modelProvider) {
        this.database = sQLiteDatabase;
        this.modelProvider = modelProvider;
    }

    @Override // com.amplifyframework.datastore.storage.sqlite.migrations.ModelMigration
    public void apply() {
        if (!needsMigration()) {
            LOG.debug("No ModelMetadata migration needed.");
            return;
        }
        try {
            Cursor cursorDuplicateIds = duplicateIds(this.modelProvider.modelNames());
            try {
                this.database.beginTransaction();
                if (cursorDuplicateIds.moveToNext()) {
                    LOG.debug("There are duplicate IDs. Clearing ModelMetadata to force base sync.");
                    this.database.execSQL("DELETE FROM ModelMetadata;", new String[0]);
                    this.database.execSQL("DELETE FROM LastSyncMetadata;", new String[0]);
                } else {
                    LOG.debug("No duplicate IDs found. Modifying and backfilling ModelMetadata.");
                    this.database.execSQL("DROP TABLE IF EXISTS ModelMetadataCopy;", new String[0]);
                    this.database.execSQL(createModelMetadataTable("ModelMetadataCopy"), new String[0]);
                    this.database.execSQL(backfillModelMetadataQuery(), new String[0]);
                    this.database.execSQL("DROP TABLE ModelMetadata;", new String[0]);
                    this.database.execSQL("ALTER TABLE ModelMetadataCopy RENAME TO ModelMetadata;", new String[0]);
                }
                this.database.setTransactionSuccessful();
                cursorDuplicateIds.close();
            } finally {
            }
        } finally {
            if (this.database.inTransaction()) {
                this.database.endTransaction();
            }
        }
    }

    private Cursor duplicateIds(Set<String> set) {
        Set<String> setModelNames = SystemModelsProviderFactory.create().modelNames();
        StringBuilder sb = new StringBuilder("");
        for (String str : set) {
            if (!setModelNames.contains(str)) {
                if (!"".equals(sb.toString())) {
                    sb.append(" UNION ALL ");
                }
                sb.append("SELECT id,");
                sb.append(Wrap.inSingleQuotes(str));
                sb.append(" as tableName ");
                sb.append("FROM ");
                sb.append(str);
            }
        }
        sb.insert(0, "SELECT id, tableName, count(id) as count FROM (");
        sb.append(") GROUP BY id HAVING count > 1");
        LOG.debug("Check for duplicate IDs:" + sb.toString());
        return this.database.rawQuery(sb.toString(), new String[0]);
    }

    private String backfillModelMetadataQuery() {
        Set<String> setModelNames = SystemModelsProviderFactory.create().modelNames();
        StringBuilder sb = new StringBuilder("");
        for (String str : this.modelProvider.modelNames()) {
            if (!setModelNames.contains(str)) {
                if (!"".equals(sb.toString())) {
                    sb.append(" UNION ALL ");
                }
                sb.append("SELECT id,");
                sb.append(Wrap.inSingleQuotes(str));
                sb.append(" as tableName ");
                sb.append("FROM ");
                sb.append(str);
            }
        }
        sb.insert(0, "select models.tableName || '|' || mm.id,mm._deleted, mm._lastChangedAt,mm._version from ModelMetadata mm INNER JOIN (");
        sb.append(") as models on mm.id=models.id;");
        sb.insert(0, "INSERT INTO ModelMetadataCopy(id,_deleted,_lastChangedAt,_version) ");
        LOG.debug("Backfill query: " + sb.toString());
        return sb.toString();
    }

    private String createModelMetadataTable(String str) {
        return "create table " + str + " (id text NOT NULL, _deleted integer, _lastChangedAt integer,_version integer, PRIMARY KEY (id))";
    }

    private boolean needsMigration() {
        Cursor cursorRawQuery = this.database.rawQuery("select (select count(1) as count from ModelMetadata) as allRecords, (select count(1) as count from ModelMetadata where id like '%|%') as newKeys", new String[0]);
        try {
            if (cursorRawQuery.moveToNext()) {
                int i = cursorRawQuery.getInt(0);
                int i2 = cursorRawQuery.getInt(1);
                if (i == 0 || i2 > 0) {
                    cursorRawQuery.close();
                    return false;
                }
            }
            cursorRawQuery.close();
            return true;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cursorRawQuery != null) {
                    try {
                        cursorRawQuery.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
}
