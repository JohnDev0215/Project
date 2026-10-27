package com.android.launcher3.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.Utilities;
import com.android.launcher3.model.GridSizeMigrationTask;
import com.android.launcher3.util.IntSparseArrayMap;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class LossyScreenMigrationTask extends GridSizeMigrationTask {
    private final IntSparseArrayMap<GridSizeMigrationTask.DbEntry> mOriginalItems;
    private final IntSparseArrayMap<GridSizeMigrationTask.DbEntry> mUpdates;

    protected LossyScreenMigrationTask(Context context, InvariantDeviceProfile idp, SQLiteDatabase db) {
        super(context, db, getValidPackages(context), new Point(idp.numColumns, idp.numRows + 1), new Point(idp.numColumns, idp.numRows));
        this.mOriginalItems = new IntSparseArrayMap<>();
        this.mUpdates = new IntSparseArrayMap<>();
    }

    @Override // com.android.launcher3.model.GridSizeMigrationTask
    protected void update(GridSizeMigrationTask.DbEntry item) {
        this.mUpdates.put(item.id, item.copy());
    }

    @Override // com.android.launcher3.model.GridSizeMigrationTask
    protected ArrayList<GridSizeMigrationTask.DbEntry> loadWorkspaceEntries(int screen) {
        ArrayList<GridSizeMigrationTask.DbEntry> arrayListLoadWorkspaceEntries = super.loadWorkspaceEntries(screen);
        for (GridSizeMigrationTask.DbEntry dbEntry : arrayListLoadWorkspaceEntries) {
            this.mOriginalItems.put(dbEntry.id, dbEntry.copy());
            dbEntry.cellY++;
            this.mUpdates.put(dbEntry.id, dbEntry.copy());
        }
        return arrayListLoadWorkspaceEntries;
    }

    public void migrateScreen0() {
        migrateScreen(0);
        ContentValues contentValues = new ContentValues();
        for (GridSizeMigrationTask.DbEntry dbEntry : this.mUpdates) {
            GridSizeMigrationTask.DbEntry dbEntry2 = this.mOriginalItems.get(dbEntry.id);
            if (dbEntry2.cellX != dbEntry.cellX || dbEntry2.cellY != dbEntry.cellY || dbEntry2.spanX != dbEntry.spanX || dbEntry2.spanY != dbEntry.spanY) {
                contentValues.clear();
                dbEntry.addToContentValues(contentValues);
                this.mDb.update("favorites", contentValues, "_id = ?", new String[]{Integer.toString(dbEntry.id)});
            }
        }
        Iterator<GridSizeMigrationTask.DbEntry> it = this.mCarryOver.iterator();
        while (it.hasNext()) {
            this.mEntryToRemove.add(it.next().id);
        }
        if (this.mEntryToRemove.isEmpty()) {
            return;
        }
        this.mDb.delete("favorites", Utilities.createDbSelectionQuery("_id", this.mEntryToRemove), null);
    }
}
