package com.android.launcher3.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.util.Log;
import android.util.SparseArray;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherSettings;
import com.android.launcher3.Utilities;
import com.android.launcher3.compat.PackageInstallerCompat;
import com.android.launcher3.provider.LauncherDbUtils;
import com.android.launcher3.util.GridOccupancy;
import com.android.launcher3.util.IntArray;
import com.android.launcher3.util.IntSparseArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class GridSizeMigrationTask {
    private static final boolean DEBUG = true;
    private static final String KEY_MIGRATION_SRC_HOTSEAT_COUNT = "migration_src_hotseat_count";
    private static final String KEY_MIGRATION_SRC_WORKSPACE_SIZE = "migration_src_workspace_size";
    private static final String TAG = "GridSizeMigrationTask";
    private static final float WT_APPLICATION = 0.8f;
    private static final float WT_FOLDER_FACTOR = 0.5f;
    private static final float WT_SHORTCUT = 1.0f;
    private static final float WT_WIDGET_FACTOR = 0.6f;
    private static final float WT_WIDGET_MIN = 2.0f;
    protected final ArrayList<DbEntry> mCarryOver;
    protected final Context mContext;
    protected final SQLiteDatabase mDb;
    private final int mDestHotseatSize;
    protected final IntArray mEntryToRemove;
    private final boolean mShouldRemoveX;
    private final boolean mShouldRemoveY;
    private final int mSrcHotseatSize;
    private final int mSrcX;
    private final int mSrcY;
    private final int mTrgX;
    private final int mTrgY;
    private final SparseArray<ContentValues> mUpdateOperations;
    private final HashSet<String> mValidPackages;

    protected GridSizeMigrationTask(Context context, SQLiteDatabase db, HashSet<String> validPackages, Point sourceSize, Point targetSize) {
        this.mEntryToRemove = new IntArray();
        this.mCarryOver = new ArrayList<>();
        this.mUpdateOperations = new SparseArray<>();
        this.mContext = context;
        this.mDb = db;
        this.mValidPackages = validPackages;
        int i = sourceSize.x;
        this.mSrcX = i;
        int i2 = sourceSize.y;
        this.mSrcY = i2;
        int i3 = targetSize.x;
        this.mTrgX = i3;
        int i4 = targetSize.y;
        this.mTrgY = i4;
        this.mShouldRemoveX = i3 < i;
        this.mShouldRemoveY = i4 < i2;
        this.mDestHotseatSize = -1;
        this.mSrcHotseatSize = -1;
    }

    protected GridSizeMigrationTask(Context context, SQLiteDatabase db, HashSet<String> validPackages, int srcHotseatSize, int destHotseatSize) {
        this.mEntryToRemove = new IntArray();
        this.mCarryOver = new ArrayList<>();
        this.mUpdateOperations = new SparseArray<>();
        this.mContext = context;
        this.mDb = db;
        this.mValidPackages = validPackages;
        this.mSrcHotseatSize = srcHotseatSize;
        this.mDestHotseatSize = destHotseatSize;
        this.mTrgY = -1;
        this.mTrgX = -1;
        this.mSrcY = -1;
        this.mSrcX = -1;
        this.mShouldRemoveY = false;
        this.mShouldRemoveX = false;
    }

    private boolean applyOperations() throws Exception {
        int size = this.mUpdateOperations.size();
        for (int i = 0; i < size; i++) {
            this.mDb.update("favorites", this.mUpdateOperations.valueAt(i), "_id=" + this.mUpdateOperations.keyAt(i), null);
        }
        if (!this.mEntryToRemove.isEmpty()) {
            Log.d(TAG, "Removing items: " + this.mEntryToRemove.toConcatString());
            this.mDb.delete("favorites", Utilities.createDbSelectionQuery("_id", this.mEntryToRemove), null);
        }
        return size > 0 || !this.mEntryToRemove.isEmpty();
    }

    protected boolean migrateHotseat() throws Exception {
        ArrayList<DbEntry> arrayListLoadHotseatEntries = loadHotseatEntries();
        while (arrayListLoadHotseatEntries.size() > this.mDestHotseatSize) {
            DbEntry dbEntry = arrayListLoadHotseatEntries.get(arrayListLoadHotseatEntries.size() / 2);
            for (DbEntry dbEntry2 : arrayListLoadHotseatEntries) {
                if (dbEntry2.weight < dbEntry.weight) {
                    dbEntry = dbEntry2;
                }
            }
            this.mEntryToRemove.add(dbEntry.id);
            arrayListLoadHotseatEntries.remove(dbEntry);
        }
        int i = 0;
        for (DbEntry dbEntry3 : arrayListLoadHotseatEntries) {
            if (dbEntry3.screenId != i) {
                dbEntry3.screenId = i;
                dbEntry3.cellX = i;
                dbEntry3.cellY = 0;
                update(dbEntry3);
            }
            i++;
        }
        return applyOperations();
    }

    static IntArray getWorkspaceScreenIds(SQLiteDatabase db) {
        return LauncherDbUtils.queryIntArray(db, "favorites", LauncherSettings.Favorites.SCREEN, "container = -100", LauncherSettings.Favorites.SCREEN, LauncherSettings.Favorites.SCREEN);
    }

    protected boolean migrateWorkspace() throws Exception {
        IntArray workspaceScreenIds = getWorkspaceScreenIds(this.mDb);
        if (workspaceScreenIds.isEmpty()) {
            throw new Exception("Unable to get workspace screens");
        }
        for (int i = 0; i < workspaceScreenIds.size(); i++) {
            int i2 = workspaceScreenIds.get(i);
            Log.d(TAG, "Migrating " + i2);
            migrateScreen(i2);
        }
        if (!this.mCarryOver.isEmpty()) {
            IntSparseArrayMap intSparseArrayMap = new IntSparseArrayMap();
            for (DbEntry dbEntry : this.mCarryOver) {
                intSparseArrayMap.put(dbEntry.id, dbEntry);
            }
            do {
                OptimalPlacementSolution optimalPlacementSolution = new OptimalPlacementSolution(new GridOccupancy(this.mTrgX, this.mTrgY), deepCopy(this.mCarryOver), 0, true);
                optimalPlacementSolution.find();
                if (optimalPlacementSolution.finalPlacedItems.size() > 0) {
                    int i3 = LauncherSettings.Settings.call(this.mContext.getContentResolver(), LauncherSettings.Settings.METHOD_NEW_SCREEN_ID).getInt("value");
                    for (DbEntry dbEntry2 : optimalPlacementSolution.finalPlacedItems) {
                        if (!this.mCarryOver.remove(intSparseArrayMap.get(dbEntry2.id))) {
                            throw new Exception("Unable to find matching items");
                        }
                        dbEntry2.screenId = i3;
                        update(dbEntry2);
                    }
                } else {
                    throw new Exception("None of the items can be placed on an empty screen");
                }
            } while (!this.mCarryOver.isEmpty());
        }
        return applyOperations();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void migrateScreen(int screenId) {
        ArrayList<DbEntry> arrayListLoadWorkspaceEntries = loadWorkspaceEntries(screenId);
        float[] fArr = new float[2];
        float f = Float.MAX_VALUE;
        int i = Integer.MAX_VALUE;
        ArrayList<DbEntry> arrayList = null;
        float f2 = Float.MAX_VALUE;
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < this.mSrcX; i3++) {
            float f3 = f;
            float f4 = f2;
            int i4 = i2;
            int i5 = i;
            ArrayList<DbEntry> arrayList2 = arrayList;
            for (int i6 = this.mSrcY - 1; i6 >= 0; i6--) {
                ArrayList<DbEntry> arrayListTryRemove = tryRemove(i3, i6, 0, deepCopy(arrayListLoadWorkspaceEntries), fArr);
                if (fArr[0] < f3 || (fArr[0] == f3 && fArr[1] < f4)) {
                    float f5 = fArr[0];
                    float f6 = fArr[1];
                    if (this.mShouldRemoveX) {
                        i4 = i3;
                    }
                    if (this.mShouldRemoveY) {
                        i5 = i6;
                    }
                    arrayList2 = arrayListTryRemove;
                    f3 = f5;
                    f4 = f6;
                }
                if (!this.mShouldRemoveY) {
                    break;
                }
            }
            f = f3;
            f2 = f4;
            i2 = i4;
            i = i5;
            arrayList = arrayList2;
            if (!this.mShouldRemoveX) {
                break;
            }
        }
        Log.d(TAG, String.format("Removing row %d, column %d on screen %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(screenId)));
        IntSparseArrayMap intSparseArrayMap = new IntSparseArrayMap();
        for (DbEntry dbEntry : deepCopy(arrayListLoadWorkspaceEntries)) {
            intSparseArrayMap.put(dbEntry.id, dbEntry);
        }
        for (DbEntry dbEntry2 : arrayList) {
            DbEntry dbEntry3 = (DbEntry) intSparseArrayMap.get(dbEntry2.id);
            intSparseArrayMap.remove(dbEntry2.id);
            if (!dbEntry2.columnsSame(dbEntry3)) {
                update(dbEntry2);
            }
        }
        Iterator it = intSparseArrayMap.iterator();
        while (it.hasNext()) {
            this.mCarryOver.add((DbEntry) it.next());
        }
        if (this.mCarryOver.isEmpty() || f != 0.0f) {
            return;
        }
        GridOccupancy gridOccupancy = new GridOccupancy(this.mTrgX, this.mTrgY);
        gridOccupancy.markCells(0, 0, this.mTrgX, 0, true);
        Iterator<DbEntry> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            gridOccupancy.markCells((ItemInfo) it2.next(), true);
        }
        OptimalPlacementSolution optimalPlacementSolution = new OptimalPlacementSolution(gridOccupancy, deepCopy(this.mCarryOver), 0, true);
        optimalPlacementSolution.find();
        if (optimalPlacementSolution.lowestWeightLoss == 0.0f) {
            for (DbEntry dbEntry4 : optimalPlacementSolution.finalPlacedItems) {
                dbEntry4.screenId = screenId;
                update(dbEntry4);
            }
            this.mCarryOver.clear();
        }
    }

    protected void update(DbEntry item) {
        ContentValues contentValues = new ContentValues();
        item.addToContentValues(contentValues);
        this.mUpdateOperations.put(item.id, contentValues);
    }

    private ArrayList<DbEntry> tryRemove(int col, int row, int startY, ArrayList<DbEntry> items, float[] outLoss) {
        GridOccupancy gridOccupancy = new GridOccupancy(this.mTrgX, this.mTrgY);
        gridOccupancy.markCells(0, 0, this.mTrgX, startY, true);
        if (!this.mShouldRemoveX) {
            col = Integer.MAX_VALUE;
        }
        if (!this.mShouldRemoveY) {
            row = Integer.MAX_VALUE;
        }
        ArrayList<DbEntry> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        for (DbEntry dbEntry : items) {
            if ((dbEntry.cellX <= col && dbEntry.spanX + dbEntry.cellX > col) || (dbEntry.cellY <= row && dbEntry.spanY + dbEntry.cellY > row)) {
                arrayList2.add(dbEntry);
                if (dbEntry.cellX >= col) {
                    dbEntry.cellX--;
                }
                if (dbEntry.cellY >= row) {
                    dbEntry.cellY--;
                }
            } else {
                if (dbEntry.cellX > col) {
                    dbEntry.cellX--;
                }
                if (dbEntry.cellY > row) {
                    dbEntry.cellY--;
                }
                arrayList.add(dbEntry);
                gridOccupancy.markCells((ItemInfo) dbEntry, true);
            }
        }
        OptimalPlacementSolution optimalPlacementSolution = new OptimalPlacementSolution(this, gridOccupancy, arrayList2, startY);
        optimalPlacementSolution.find();
        arrayList.addAll(optimalPlacementSolution.finalPlacedItems);
        outLoss[0] = optimalPlacementSolution.lowestWeightLoss;
        outLoss[1] = optimalPlacementSolution.lowestMoveCost;
        return arrayList;
    }

    private class OptimalPlacementSolution {
        ArrayList<DbEntry> finalPlacedItems;
        private final boolean ignoreMove;
        private final ArrayList<DbEntry> itemsToPlace;
        float lowestMoveCost;
        float lowestWeightLoss;
        private final GridOccupancy occupied;
        private final int startY;

        public OptimalPlacementSolution(final GridSizeMigrationTask this$0, GridOccupancy occupied, ArrayList<DbEntry> itemsToPlace, int startY) {
            this(occupied, itemsToPlace, startY, false);
        }

        public OptimalPlacementSolution(GridOccupancy occupied, ArrayList<DbEntry> itemsToPlace, int startY, boolean ignoreMove) {
            this.lowestWeightLoss = Float.MAX_VALUE;
            this.lowestMoveCost = Float.MAX_VALUE;
            this.occupied = occupied;
            this.itemsToPlace = itemsToPlace;
            this.ignoreMove = ignoreMove;
            this.startY = startY;
            Collections.sort(itemsToPlace);
        }

        public void find() {
            find(0, 0.0f, 0.0f, new ArrayList<>());
        }

        public void find(int index, float weightLoss, float moveCost, ArrayList<DbEntry> itemsPlaced) {
            float f;
            float f2;
            int i;
            float f3;
            float f4;
            int i2;
            int i3;
            float f5 = weightLoss;
            float f6 = this.lowestWeightLoss;
            if (f5 < f6) {
                if (f5 != f6 || moveCost < this.lowestMoveCost) {
                    if (index >= this.itemsToPlace.size()) {
                        this.lowestWeightLoss = f5;
                        this.lowestMoveCost = moveCost;
                        this.finalPlacedItems = GridSizeMigrationTask.deepCopy(itemsPlaced);
                        return;
                    }
                    DbEntry dbEntry = this.itemsToPlace.get(index);
                    int i4 = dbEntry.cellX;
                    int i5 = dbEntry.cellY;
                    ArrayList<DbEntry> arrayList = new ArrayList<>(itemsPlaced.size() + 1);
                    arrayList.addAll(itemsPlaced);
                    arrayList.add(dbEntry);
                    if (dbEntry.spanX > 1 || dbEntry.spanY > 1) {
                        int i6 = dbEntry.spanX;
                        int i7 = dbEntry.spanY;
                        for (int i8 = this.startY; i8 < GridSizeMigrationTask.this.mTrgY; i8++) {
                            int i9 = 0;
                            while (i9 < GridSizeMigrationTask.this.mTrgX) {
                                if (i9 != i4) {
                                    dbEntry.cellX = i9;
                                    f = 1.0f;
                                    f2 = moveCost + 1.0f;
                                } else {
                                    f = 1.0f;
                                    f2 = moveCost;
                                }
                                if (i8 != i5) {
                                    dbEntry.cellY = i8;
                                    f2 += f;
                                }
                                if (this.ignoreMove) {
                                    f2 = moveCost;
                                }
                                if (this.occupied.isRegionVacant(i9, i8, i6, i7)) {
                                    this.occupied.markCells((ItemInfo) dbEntry, true);
                                    find(index + 1, f5, f2, arrayList);
                                    this.occupied.markCells((ItemInfo) dbEntry, false);
                                }
                                if (i6 > dbEntry.minSpanX && this.occupied.isRegionVacant(i9, i8, i6 - 1, i7)) {
                                    dbEntry.spanX--;
                                    this.occupied.markCells((ItemInfo) dbEntry, true);
                                    find(index + 1, f5, f2 + 1.0f, arrayList);
                                    this.occupied.markCells((ItemInfo) dbEntry, false);
                                    dbEntry.spanX++;
                                }
                                if (i7 > dbEntry.minSpanY && this.occupied.isRegionVacant(i9, i8, i6, i7 - 1)) {
                                    dbEntry.spanY--;
                                    this.occupied.markCells((ItemInfo) dbEntry, true);
                                    find(index + 1, f5, f2 + 1.0f, arrayList);
                                    this.occupied.markCells((ItemInfo) dbEntry, false);
                                    dbEntry.spanY++;
                                }
                                if (i7 <= dbEntry.minSpanY || i6 <= dbEntry.minSpanX) {
                                    i = i6;
                                } else {
                                    i = i6;
                                    if (this.occupied.isRegionVacant(i9, i8, i6 - 1, i7 - 1)) {
                                        dbEntry.spanX--;
                                        dbEntry.spanY--;
                                        this.occupied.markCells((ItemInfo) dbEntry, true);
                                        find(index + 1, f5, f2 + GridSizeMigrationTask.WT_WIDGET_MIN, arrayList);
                                        this.occupied.markCells((ItemInfo) dbEntry, false);
                                        dbEntry.spanX++;
                                        dbEntry.spanY++;
                                    }
                                    dbEntry.cellX = i4;
                                    dbEntry.cellY = i5;
                                    i9++;
                                    i6 = i;
                                }
                                dbEntry.cellX = i4;
                                dbEntry.cellY = i5;
                                i9++;
                                i6 = i;
                            }
                        }
                        find(index + 1, f5 + dbEntry.weight, moveCost, itemsPlaced);
                        return;
                    }
                    int i10 = Integer.MAX_VALUE;
                    int i11 = Integer.MAX_VALUE;
                    int i12 = Integer.MAX_VALUE;
                    for (int i13 = this.startY; i13 < GridSizeMigrationTask.this.mTrgY; i13++) {
                        for (int i14 = 0; i14 < GridSizeMigrationTask.this.mTrgX; i14++) {
                            if (this.occupied.cells[i14][i13]) {
                                i2 = i10;
                            } else {
                                if (this.ignoreMove) {
                                    i2 = i10;
                                    i3 = 0;
                                } else {
                                    i2 = i10;
                                    i3 = ((dbEntry.cellX - i14) * (dbEntry.cellX - i14)) + ((dbEntry.cellY - i13) * (dbEntry.cellY - i13));
                                }
                                if (i3 < i12) {
                                    i11 = i13;
                                    i12 = i3;
                                    i10 = i14;
                                }
                            }
                            i10 = i2;
                        }
                    }
                    if (i10 < GridSizeMigrationTask.this.mTrgX && i11 < GridSizeMigrationTask.this.mTrgY) {
                        if (i10 != i4) {
                            dbEntry.cellX = i10;
                            f3 = 1.0f;
                            f4 = moveCost + 1.0f;
                        } else {
                            f3 = 1.0f;
                            f4 = moveCost;
                        }
                        if (i11 != i5) {
                            dbEntry.cellY = i11;
                            f4 += f3;
                        }
                        if (this.ignoreMove) {
                            f4 = moveCost;
                        }
                        this.occupied.markCells((ItemInfo) dbEntry, true);
                        int i15 = index + 1;
                        find(i15, f5, f4, arrayList);
                        this.occupied.markCells((ItemInfo) dbEntry, false);
                        dbEntry.cellX = i4;
                        dbEntry.cellY = i5;
                        if (i15 >= this.itemsToPlace.size() || this.itemsToPlace.get(i15).weight < dbEntry.weight || this.ignoreMove) {
                            return;
                        }
                        find(i15, f5 + dbEntry.weight, moveCost, itemsPlaced);
                        return;
                    }
                    for (int i16 = index + 1; i16 < this.itemsToPlace.size(); i16++) {
                        f5 += this.itemsToPlace.get(i16).weight;
                    }
                    find(this.itemsToPlace.size(), f5 + dbEntry.weight, moveCost, itemsPlaced);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.ArrayList<com.android.launcher3.model.GridSizeMigrationTask.DbEntry> loadHotseatEntries() {
        /*
            r10 = this;
            java.lang.String r0 = "_id"
            java.lang.String r1 = "itemType"
            java.lang.String r2 = "intent"
            java.lang.String r3 = "screen"
            java.lang.String[] r4 = new java.lang.String[]{r0, r1, r2, r3}
            java.lang.String r5 = "container = -101"
            android.database.Cursor r4 = r10.queryWorkspace(r4, r5)
            int r0 = r4.getColumnIndexOrThrow(r0)
            int r1 = r4.getColumnIndexOrThrow(r1)
            int r2 = r4.getColumnIndexOrThrow(r2)
            int r3 = r4.getColumnIndexOrThrow(r3)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
        L28:
            boolean r6 = r4.moveToNext()
            if (r6 == 0) goto Lbb
            com.android.launcher3.model.GridSizeMigrationTask$DbEntry r6 = new com.android.launcher3.model.GridSizeMigrationTask$DbEntry
            r6.<init>()
            int r7 = r4.getInt(r0)
            r6.id = r7
            int r7 = r4.getInt(r1)
            r6.itemType = r7
            int r7 = r4.getInt(r3)
            r6.screenId = r7
            int r7 = r6.screenId
            int r8 = r10.mSrcHotseatSize
            if (r7 < r8) goto L53
            com.android.launcher3.util.IntArray r7 = r10.mEntryToRemove
            int r6 = r6.id
            r7.add(r6)
            goto L28
        L53:
            int r7 = r6.itemType     // Catch: java.lang.Exception -> L97
            if (r7 == 0) goto L80
            r8 = 1
            if (r7 == r8) goto L80
            r8 = 2
            if (r7 == r8) goto L69
            r8 = 6
            if (r7 != r8) goto L61
            goto L80
        L61:
            java.lang.Exception r7 = new java.lang.Exception     // Catch: java.lang.Exception -> L97
            java.lang.String r8 = "Invalid item type"
            r7.<init>(r8)     // Catch: java.lang.Exception -> L97
            throw r7     // Catch: java.lang.Exception -> L97
        L69:
            int r7 = r6.id     // Catch: java.lang.Exception -> L97
            int r7 = r10.getFolderItemsCount(r7)     // Catch: java.lang.Exception -> L97
            if (r7 == 0) goto L78
            r8 = 1056964608(0x3f000000, float:0.5)
            float r7 = (float) r7     // Catch: java.lang.Exception -> L97
            float r7 = r7 * r8
            r6.weight = r7     // Catch: java.lang.Exception -> L97
            goto L93
        L78:
            java.lang.Exception r7 = new java.lang.Exception     // Catch: java.lang.Exception -> L97
            java.lang.String r8 = "Folder is empty"
            r7.<init>(r8)     // Catch: java.lang.Exception -> L97
            throw r7     // Catch: java.lang.Exception -> L97
        L80:
            java.lang.String r7 = r4.getString(r2)     // Catch: java.lang.Exception -> L97
            r10.verifyIntent(r7)     // Catch: java.lang.Exception -> L97
            int r7 = r6.itemType     // Catch: java.lang.Exception -> L97
            if (r7 != 0) goto L8f
            r7 = 1061997773(0x3f4ccccd, float:0.8)
            goto L91
        L8f:
            r7 = 1065353216(0x3f800000, float:1.0)
        L91:
            r6.weight = r7     // Catch: java.lang.Exception -> L97
        L93:
            r5.add(r6)
            goto L28
        L97:
            r7 = move-exception
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Removing item "
            java.lang.StringBuilder r8 = r8.append(r9)
            int r9 = r6.id
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.lang.String r9 = "GridSizeMigrationTask"
            android.util.Log.d(r9, r8, r7)
            com.android.launcher3.util.IntArray r7 = r10.mEntryToRemove
            int r6 = r6.id
            r7.add(r6)
            goto L28
        Lbb:
            r4.close()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.model.GridSizeMigrationTask.loadHotseatEntries():java.util.ArrayList");
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x014b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.util.ArrayList<com.android.launcher3.model.GridSizeMigrationTask.DbEntry> loadWorkspaceEntries(int r17) {
        /*
            Method dump skipped, instruction units count: 382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.model.GridSizeMigrationTask.loadWorkspaceEntries(int):java.util.ArrayList");
    }

    private int getFolderItemsCount(int folderId) {
        Cursor cursorQueryWorkspace = queryWorkspace(new String[]{"_id", "intent"}, "container = " + folderId);
        int i = 0;
        while (cursorQueryWorkspace.moveToNext()) {
            try {
                verifyIntent(cursorQueryWorkspace.getString(1));
                i++;
            } catch (Exception unused) {
                this.mEntryToRemove.add(cursorQueryWorkspace.getInt(0));
            }
        }
        cursorQueryWorkspace.close();
        return i;
    }

    protected Cursor queryWorkspace(String[] columns, String where) {
        return this.mDb.query("favorites", columns, where, null, null, null, null);
    }

    private void verifyIntent(String intentStr) throws Exception {
        Intent uri = Intent.parseUri(intentStr, 0);
        if (uri.getComponent() != null) {
            verifyPackage(uri.getComponent().getPackageName());
        } else if (uri.getPackage() != null) {
            verifyPackage(uri.getPackage());
        }
    }

    private void verifyPackage(String packageName) throws Exception {
        if (!this.mValidPackages.contains(packageName)) {
            throw new Exception("Package not available");
        }
    }

    protected static class DbEntry extends ItemInfo implements Comparable<DbEntry> {
        public float weight;

        public DbEntry copy() {
            DbEntry dbEntry = new DbEntry();
            dbEntry.copyFrom(this);
            dbEntry.weight = this.weight;
            dbEntry.minSpanX = this.minSpanX;
            dbEntry.minSpanY = this.minSpanY;
            return dbEntry;
        }

        @Override // java.lang.Comparable
        public int compareTo(DbEntry another) {
            if (this.itemType == 4) {
                if (another.itemType == 4) {
                    return (another.spanY * another.spanX) - (this.spanX * this.spanY);
                }
                return -1;
            }
            if (another.itemType == 4) {
                return 1;
            }
            return Float.compare(another.weight, this.weight);
        }

        public boolean columnsSame(DbEntry org2) {
            return org2.cellX == this.cellX && org2.cellY == this.cellY && org2.spanX == this.spanX && org2.spanY == this.spanY && org2.screenId == this.screenId;
        }

        public void addToContentValues(ContentValues values) {
            values.put(LauncherSettings.Favorites.SCREEN, Integer.valueOf(this.screenId));
            values.put(LauncherSettings.Favorites.CELLX, Integer.valueOf(this.cellX));
            values.put(LauncherSettings.Favorites.CELLY, Integer.valueOf(this.cellY));
            values.put(LauncherSettings.Favorites.SPANX, Integer.valueOf(this.spanX));
            values.put(LauncherSettings.Favorites.SPANY, Integer.valueOf(this.spanY));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<DbEntry> deepCopy(ArrayList<DbEntry> src) {
        ArrayList<DbEntry> arrayList = new ArrayList<>(src.size());
        Iterator<DbEntry> it = src.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().copy());
        }
        return arrayList;
    }

    public static void markForMigration(Context context, int gridX, int gridY, int hotseatSize) {
        Utilities.getPrefs(context).edit().putString(KEY_MIGRATION_SRC_WORKSPACE_SIZE, Utilities.getPointString(gridX, gridY)).putInt(KEY_MIGRATION_SRC_HOTSEAT_COUNT, hotseatSize).apply();
    }

    public static boolean migrateGridIfNeeded(Context context) {
        boolean zMigrateHotseat;
        HashSet<String> hashSet;
        SharedPreferences prefs = Utilities.getPrefs(context);
        InvariantDeviceProfile idp = LauncherAppState.getIDP(context);
        String pointString = Utilities.getPointString(idp.numColumns, idp.numRows);
        if (pointString.equals(prefs.getString(KEY_MIGRATION_SRC_WORKSPACE_SIZE, "")) && idp.numHotseatIcons == prefs.getInt(KEY_MIGRATION_SRC_HOTSEAT_COUNT, idp.numHotseatIcons)) {
            return true;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            try {
                LauncherDbUtils.SQLiteTransaction sQLiteTransaction = (LauncherDbUtils.SQLiteTransaction) LauncherSettings.Settings.call(context.getContentResolver(), LauncherSettings.Settings.METHOD_NEW_TRANSACTION).getBinder("value");
                try {
                    int restoreHotseatAndGridSize = prefs.getInt(KEY_MIGRATION_SRC_HOTSEAT_COUNT, idp.numHotseatIcons);
                    Point point = Utilities.parsePoint(prefs.getString(KEY_MIGRATION_SRC_WORKSPACE_SIZE, pointString));
                    GridBackupTable gridBackupTable = new GridBackupTable(context, sQLiteTransaction.getDb(), restoreHotseatAndGridSize, point.x, point.y);
                    if (gridBackupTable.backupOrRestoreAsNeeded()) {
                        restoreHotseatAndGridSize = gridBackupTable.getRestoreHotseatAndGridSize(point);
                        zMigrateHotseat = true;
                    } else {
                        zMigrateHotseat = false;
                    }
                    HashSet<String> validPackages = getValidPackages(context);
                    if (restoreHotseatAndGridSize != idp.numHotseatIcons) {
                        hashSet = validPackages;
                        zMigrateHotseat = new GridSizeMigrationTask(context, sQLiteTransaction.getDb(), hashSet, restoreHotseatAndGridSize, idp.numHotseatIcons).migrateHotseat();
                    } else {
                        hashSet = validPackages;
                    }
                    if (new MultiStepMigrationTask(hashSet, context, sQLiteTransaction.getDb()).migrate(point, new Point(idp.numColumns, idp.numRows)) ? true : zMigrateHotseat) {
                        Cursor cursorQuery = context.getContentResolver().query(LauncherSettings.Favorites.CONTENT_URI, null, null, null, null);
                        boolean zMoveToNext = cursorQuery.moveToNext();
                        cursorQuery.close();
                        if (!zMoveToNext) {
                            throw new Exception("Removed every thing during grid resize");
                        }
                    }
                    sQLiteTransaction.commit();
                    LauncherSettings.Settings.call(context.getContentResolver(), LauncherSettings.Settings.METHOD_REFRESH_BACKUP_TABLE);
                    if (sQLiteTransaction != null) {
                        sQLiteTransaction.close();
                    }
                    Log.v(TAG, "Workspace migration completed in " + (System.currentTimeMillis() - jCurrentTimeMillis));
                    prefs.edit().putString(KEY_MIGRATION_SRC_WORKSPACE_SIZE, pointString).putInt(KEY_MIGRATION_SRC_HOTSEAT_COUNT, idp.numHotseatIcons).apply();
                    return true;
                } finally {
                }
            } catch (Exception e) {
                Log.e(TAG, "Error during grid migration", e);
                Log.v(TAG, "Workspace migration completed in " + (System.currentTimeMillis() - jCurrentTimeMillis));
                prefs.edit().putString(KEY_MIGRATION_SRC_WORKSPACE_SIZE, pointString).putInt(KEY_MIGRATION_SRC_HOTSEAT_COUNT, idp.numHotseatIcons).apply();
                return false;
            }
        } catch (Throwable th) {
            Log.v(TAG, "Workspace migration completed in " + (System.currentTimeMillis() - jCurrentTimeMillis));
            prefs.edit().putString(KEY_MIGRATION_SRC_WORKSPACE_SIZE, pointString).putInt(KEY_MIGRATION_SRC_HOTSEAT_COUNT, idp.numHotseatIcons).apply();
            throw th;
        }
    }

    protected static HashSet<String> getValidPackages(Context context) {
        HashSet<String> hashSet = new HashSet<>();
        Iterator<PackageInfo> it = context.getPackageManager().getInstalledPackages(8192).iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().packageName);
        }
        hashSet.addAll(PackageInstallerCompat.getInstance(context).updateAndGetActiveSessionCache().keySet());
        return hashSet;
    }

    public static IntSparseArrayMap<Object> removeBrokenHotseatItems(Context context) throws Exception {
        LauncherDbUtils.SQLiteTransaction sQLiteTransaction = (LauncherDbUtils.SQLiteTransaction) LauncherSettings.Settings.call(context.getContentResolver(), LauncherSettings.Settings.METHOD_NEW_TRANSACTION).getBinder("value");
        try {
            GridSizeMigrationTask gridSizeMigrationTask = new GridSizeMigrationTask(context, sQLiteTransaction.getDb(), getValidPackages(context), Integer.MAX_VALUE, Integer.MAX_VALUE);
            ArrayList<DbEntry> arrayListLoadHotseatEntries = gridSizeMigrationTask.loadHotseatEntries();
            gridSizeMigrationTask.applyOperations();
            IntSparseArrayMap<Object> intSparseArrayMap = new IntSparseArrayMap<>();
            for (DbEntry dbEntry : arrayListLoadHotseatEntries) {
                intSparseArrayMap.put(dbEntry.screenId, dbEntry);
            }
            sQLiteTransaction.commit();
            if (sQLiteTransaction != null) {
                sQLiteTransaction.close();
            }
            return intSparseArrayMap;
        } catch (Throwable th) {
            if (sQLiteTransaction != null) {
                try {
                    sQLiteTransaction.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    protected static class MultiStepMigrationTask {
        private final Context mContext;
        private final SQLiteDatabase mDb;
        private final HashSet<String> mValidPackages;

        public MultiStepMigrationTask(HashSet<String> validPackages, Context context, SQLiteDatabase db) {
            this.mValidPackages = validPackages;
            this.mContext = context;
            this.mDb = db;
        }

        public boolean migrate(Point sourceSize, Point targetSize) throws Exception {
            boolean z = false;
            if (!targetSize.equals(sourceSize)) {
                if (sourceSize.x < targetSize.x) {
                    sourceSize.x = targetSize.x;
                }
                if (sourceSize.y < targetSize.y) {
                    sourceSize.y = targetSize.y;
                }
                while (!targetSize.equals(sourceSize)) {
                    Point point = new Point(sourceSize);
                    if (targetSize.x < point.x) {
                        point.x--;
                    }
                    if (targetSize.y < point.y) {
                        point.y--;
                    }
                    if (runStepTask(sourceSize, point)) {
                        z = true;
                    }
                    sourceSize.set(point.x, point.y);
                }
            }
            return z;
        }

        protected boolean runStepTask(Point sourceSize, Point nextSize) throws Exception {
            return new GridSizeMigrationTask(this.mContext, this.mDb, this.mValidPackages, sourceSize, nextSize).migrateWorkspace();
        }
    }
}
