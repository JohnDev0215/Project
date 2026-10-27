package com.amazon.mp3.library.db;

import android.database.Cursor;
import android.database.CursorWrapper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class ColumnMappedCursor extends CursorWrapper {
    private String[] mAllMappedColumnNames;
    private final HashMap<String, String> mColumnMap;
    private final Cursor mCursor;
    private HashMap<String, Integer> mIndexTable;

    public ColumnMappedCursor(Cursor cursor, HashMap<String, String> columnMap) {
        super(cursor);
        this.mIndexTable = new HashMap<>();
        this.mCursor = cursor;
        this.mColumnMap = columnMap;
        buildReversedMap();
        buildMappedColumnNames();
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getColumnIndex(String columnName) {
        if (this.mColumnMap.get(columnName) != null && this.mIndexTable.containsKey(columnName)) {
            return this.mIndexTable.get(columnName).intValue();
        }
        return -1;
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
        int iIntValue = this.mIndexTable.containsKey(columnName) ? this.mIndexTable.get(columnName).intValue() : -1;
        if (iIntValue != -1) {
            return iIntValue;
        }
        throw new IllegalArgumentException("Unknown column requested from cursor: " + columnName);
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public String getColumnName(int columnIndex) {
        String str = columnIndex < getColumnCount() ? this.mAllMappedColumnNames[columnIndex] : null;
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("Couldn't find column name: " + columnIndex + " for " + this.mCursor.getColumnName(columnIndex));
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public String[] getColumnNames() {
        return this.mAllMappedColumnNames;
    }

    private HashMap<String, HashSet<String>> buildReversedMap() {
        HashMap<String, HashSet<String>> map = new HashMap<>();
        for (Map.Entry<String, String> entry : this.mColumnMap.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();
            HashSet<String> hashSet = map.get(value);
            if (hashSet == null) {
                hashSet = new HashSet<>();
                map.put(value, hashSet);
            }
            hashSet.add(key);
        }
        return map;
    }

    private void buildMappedColumnNames() {
        HashMap<String, HashSet<String>> mapBuildReversedMap = buildReversedMap();
        String[] columnNames = this.mCursor.getColumnNames();
        String[] strArr = new String[columnNames.length];
        int length = columnNames.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str = columnNames[i];
            HashSet<String> hashSet = mapBuildReversedMap.get(str);
            String next = (hashSet == null || !hashSet.iterator().hasNext()) ? null : hashSet.iterator().next();
            if (next != null) {
                if (hashSet != null) {
                    for (String str2 : hashSet) {
                        if (!str2.equals(next)) {
                            this.mIndexTable.put(str2, Integer.valueOf(i2));
                        }
                    }
                }
                str = next;
            }
            this.mIndexTable.put(str, Integer.valueOf(i2));
            strArr[i2] = str;
            i++;
            i2++;
        }
        this.mAllMappedColumnNames = strArr;
    }
}
