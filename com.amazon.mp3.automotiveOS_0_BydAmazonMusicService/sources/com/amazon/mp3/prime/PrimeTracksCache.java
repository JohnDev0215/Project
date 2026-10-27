package com.amazon.mp3.prime;

import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.library.db.ColumnType;
import com.amazon.mp3.util.extensions.SQLiteDatabaseExtensions;

/* JADX INFO: loaded from: classes4.dex */
public class PrimeTracksCache {

    public enum PrimeUIState {
        HIDE(0),
        SHOW(1);

        private final int mId;

        PrimeUIState(int id) {
            this.mId = id;
        }

        public int getValue() {
            return this.mId;
        }

        public static PrimeUIState fromInt(int value) {
            return value == 1 ? SHOW : HIDE;
        }
    }

    public static void addPrimeColumn(SQLiteDatabase db, String tableName) {
        ColumnType.IntegerType integerType = ColumnType.IntegerType.INSTANCE;
        SQLiteDatabaseExtensions.addColumn(db, tableName, "prime_status", integerType, Integer.valueOf(ContentCatalogStatus.NON_CATALOG.getValue()));
        SQLiteDatabaseExtensions.addColumn(db, tableName, "ownership_status", integerType, Integer.valueOf(ContentOwnershipStatus.OWNED.getValue()));
    }

    public static String getCreatePrimeColumnStatement() {
        return "prime_status INTEGER DEFAULT " + ContentCatalogStatus.NON_CATALOG.getValue();
    }

    public static String getCreateOwnershipColumnStatement() {
        return "ownership_status INTEGER DEFAULT " + ContentOwnershipStatus.OWNED.getValue();
    }
}
