package com.ts.dm.lib.storemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.lib.data.DeviceRecord;
import com.ts.dm.lib.storemanager.Sqlite3DataBaseOperate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class Sqlite3DataBaseOperate extends SQLiteOpenHelper implements IDataBaseOperate {
    private static final String TAG = "Sqlite3DataBaseOperate";
    private final List<ColumnInfo> mColumnInfos;
    private boolean mIsDatabaseUpdate;
    private SQLiteDatabase mSqLiteDatabase;

    public Sqlite3DataBaseOperate(Context context, int version) {
        super(context, IDataBaseOperate.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, version);
        this.mIsDatabaseUpdate = false;
        this.mSqLiteDatabase = null;
        this.mColumnInfos = new ArrayList(Arrays.asList(new ColumnInfo(IDataBaseOperate.MAC_ADDRESS, IDataBaseOperate.COLUMN_TYPE_TEXT, true, "not null"), new ColumnInfo(IDataBaseOperate.FRIENDLY_NAME, IDataBaseOperate.COLUMN_TYPE_TEXT, false, ""), new ColumnInfo(IDataBaseOperate.LAST_PROFILE, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, "0"), new ColumnInfo(IDataBaseOperate.LAST_PROFILE_TIME, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, "0"), new ColumnInfo(IDataBaseOperate.ANDROID_AUTO_PROJECTION_STATE, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, "0"), new ColumnInfo(IDataBaseOperate.CARPLAY_PROJECTION_STATE, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, "0"), new ColumnInfo(IDataBaseOperate.CARLIFE_PROJECTION_STATE, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, "0"), new ColumnInfo(IDataBaseOperate.HICAR_PROJECTION_STATE, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, "0"), new ColumnInfo(IDataBaseOperate.IS_PAIRD, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, "0"), new ColumnInfo(IDataBaseOperate.INDEX_OF_SAME_DEVICE_NAME, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, "-1")));
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            this.mSqLiteDatabase = readableDatabase;
            if (this.mIsDatabaseUpdate && readableDatabase.isOpen()) {
                this.mSqLiteDatabase.close();
                this.mSqLiteDatabase = getReadableDatabase();
                this.mIsDatabaseUpdate = false;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (RuntimeException runtime) {
            runtime.printStackTrace();
        }
        resetProjectionStates();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("create table if not exists ");
        sql.append(IDataBaseOperate.TABLE_NAME);
        sql.append(" ( ");
        for (ColumnInfo ci : this.mColumnInfos) {
            if (!ci.mIsKey) {
                sql.append(ci.mName);
                sql.append(" ");
                sql.append(ci.mType);
                if (!TextUtils.isEmpty(ci.mDefaultValue)) {
                    sql.append(" default ");
                    sql.append(ci.mDefaultValue);
                }
                sql.append(" ,");
            } else {
                sql.append(ci.mName);
                sql.append(" ");
                sql.append(ci.mType);
                sql.append(" ");
                sql.append(ci.mDefaultValue);
                sql.append(" primary key,");
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" ) ");
        try {
            db.execSQL(sql.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Cursor cursor;
        this.mIsDatabaseUpdate = true;
        this.mIsDatabaseUpdate = true;
        String[] oldColumnNames = null;
        try {
            cursor = db.rawQuery("select * from DM_Device", null);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (RuntimeException runtime) {
            runtime.printStackTrace();
        }
        try {
            oldColumnNames = cursor.getColumnNames();
            db.execSQL("alter table DM_Device rename to old_DM_Device");
            if (cursor != null) {
                cursor.close();
            }
            try {
                onCreate(db);
            } catch (SQLException sqle2) {
                sqle2.printStackTrace();
            } catch (RuntimeException runtime2) {
                runtime2.printStackTrace();
            }
            if (oldColumnNames == null) {
                try {
                    cursor = db.rawQuery("select * from old_DM_Device", null);
                    try {
                        oldColumnNames = cursor.getColumnNames();
                        if (cursor != null) {
                            cursor.close();
                        }
                    } finally {
                    }
                } catch (SQLException sqle3) {
                    sqle3.printStackTrace();
                } catch (RuntimeException runtime3) {
                    runtime3.printStackTrace();
                }
            }
            try {
                List<String> newColNames = (List) this.mColumnInfos.stream().map(new Function() { // from class: com.ts.dm.lib.storemanager.-$$Lambda$Sqlite3DataBaseOperate$V_TUrslqMnJQJ7_Xgf8Vvw_-U98
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((Sqlite3DataBaseOperate.ColumnInfo) obj).mName;
                    }
                }).collect(Collectors.toList());
                List<String> columns = new ArrayList<>();
                for (String cn : oldColumnNames) {
                    if (newColNames.contains(cn)) {
                        columns.add(cn);
                    }
                }
                String copy_columns = TextUtils.join(",", columns);
                db.execSQL("INSERT INTO " + IDataBaseOperate.TABLE_NAME + "(" + copy_columns + ") SELECT " + copy_columns + " FROM old_DM_Device");
                StringBuilder sb = new StringBuilder();
                sb.append("DROP TABLE ");
                sb.append("old_DM_Device");
                db.execSQL(sb.toString());
            } catch (SQLException sqle4) {
                sqle4.printStackTrace();
            } catch (RuntimeException runtime4) {
                runtime4.printStackTrace();
            }
        } finally {
        }
    }

    @Override // com.ts.dm.lib.storemanager.IDataBaseOperate
    public List<DeviceRecord> queryAllDevices() {
        if (this.mSqLiteDatabase != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select ");
            sql.append(IDataBaseOperate.MAC_ADDRESS);
            sql.append(",");
            sql.append(IDataBaseOperate.FRIENDLY_NAME);
            sql.append(",");
            sql.append(IDataBaseOperate.LAST_PROFILE);
            sql.append(",");
            sql.append(IDataBaseOperate.LAST_PROFILE_TIME);
            sql.append(",");
            sql.append(IDataBaseOperate.ANDROID_AUTO_PROJECTION_STATE);
            sql.append(",");
            sql.append(IDataBaseOperate.CARPLAY_PROJECTION_STATE);
            sql.append(",");
            sql.append(IDataBaseOperate.CARLIFE_PROJECTION_STATE);
            sql.append(",");
            sql.append(IDataBaseOperate.HICAR_PROJECTION_STATE);
            sql.append(",");
            sql.append(IDataBaseOperate.IS_PAIRD);
            sql.append(",");
            sql.append(IDataBaseOperate.INDEX_OF_SAME_DEVICE_NAME);
            sql.append(" from ");
            sql.append(IDataBaseOperate.TABLE_NAME);
            try {
                Cursor cursor = this.mSqLiteDatabase.rawQuery(sql.toString(), null);
                if (cursor != null) {
                    try {
                        if (cursor.getCount() > 0) {
                            ArrayList list = new ArrayList(cursor.getCount());
                            while (cursor.moveToNext()) {
                                list.add(cursorToDeviceRecord(cursor));
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            return list;
                        }
                    } catch (Throwable th) {
                        if (cursor != null) {
                            try {
                                cursor.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (RuntimeException runtime) {
                runtime.printStackTrace();
            }
        }
        return null;
    }

    @Override // com.ts.dm.lib.storemanager.IDataBaseOperate
    public DeviceRecord queryDevice(String mac) {
        if (!TextUtils.isEmpty(mac) && this.mSqLiteDatabase != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select * from ");
            sql.append(IDataBaseOperate.TABLE_NAME);
            sql.append(" where ");
            sql.append(IDataBaseOperate.MAC_ADDRESS);
            sql.append(" = ");
            sql.append("'" + mac + "'");
            try {
                Cursor cursor = this.mSqLiteDatabase.rawQuery(sql.toString(), null);
                try {
                    if (cursor.getCount() > 0 && cursor.moveToNext()) {
                        DeviceRecord deviceRecordCursorToDeviceRecord = cursorToDeviceRecord(cursor);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return deviceRecordCursorToDeviceRecord;
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        try {
                            cursor.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (RuntimeException runtime) {
                runtime.printStackTrace();
            }
        }
        return null;
    }

    private DeviceRecord cursorToDeviceRecord(Cursor cursor) {
        DeviceRecord deviceRecord = new DeviceRecord();
        deviceRecord.setMacAddress(getStringDataFromDatabase(cursor, IDataBaseOperate.MAC_ADDRESS));
        deviceRecord.setFriendlyName(getStringDataFromDatabase(cursor, IDataBaseOperate.FRIENDLY_NAME));
        deviceRecord.setLastProfile(getIntDataFromDatabase(cursor, IDataBaseOperate.LAST_PROFILE));
        deviceRecord.setLastProfileTimeMillis(getIntDataFromDatabase(cursor, IDataBaseOperate.LAST_PROFILE_TIME));
        deviceRecord.setCarPlayProjectionState(getIntDataFromDatabase(cursor, IDataBaseOperate.CARPLAY_PROJECTION_STATE));
        deviceRecord.setCarlifeProjectionState(getIntDataFromDatabase(cursor, IDataBaseOperate.CARLIFE_PROJECTION_STATE));
        deviceRecord.setAndroidAutoProjectionState(getIntDataFromDatabase(cursor, IDataBaseOperate.ANDROID_AUTO_PROJECTION_STATE));
        deviceRecord.setHicarProjectionState(getIntDataFromDatabase(cursor, IDataBaseOperate.HICAR_PROJECTION_STATE));
        deviceRecord.setPaired(getIntDataFromDatabase(cursor, IDataBaseOperate.IS_PAIRD) == 1);
        deviceRecord.setIndexOfSameDeviceName(getIntDataFromDatabase(cursor, IDataBaseOperate.INDEX_OF_SAME_DEVICE_NAME));
        return deviceRecord;
    }

    public ContentValues getDeviceData(DmDevice dmDevice) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDataBaseOperate.MAC_ADDRESS, dmDevice.getMacAddress());
        contentValues.put(IDataBaseOperate.FRIENDLY_NAME, dmDevice.getFriendlyName());
        contentValues.put(IDataBaseOperate.LAST_PROFILE, Integer.valueOf(dmDevice.getLastProfile()));
        contentValues.put(IDataBaseOperate.LAST_PROFILE_TIME, Long.valueOf(dmDevice.getLastProfileTimeMillis()));
        contentValues.put(IDataBaseOperate.ANDROID_AUTO_PROJECTION_STATE, Integer.valueOf(dmDevice.getAndroidAutoProjectionState()));
        contentValues.put(IDataBaseOperate.CARPLAY_PROJECTION_STATE, Integer.valueOf(dmDevice.getCarPlayProjectionState()));
        contentValues.put(IDataBaseOperate.CARLIFE_PROJECTION_STATE, Integer.valueOf(dmDevice.getCarlifeProjectionState()));
        contentValues.put(IDataBaseOperate.HICAR_PROJECTION_STATE, Integer.valueOf(dmDevice.getHicarProjectionState()));
        contentValues.put(IDataBaseOperate.IS_PAIRD, Integer.valueOf(dmDevice.isPaired() ? 1 : 0));
        contentValues.put(IDataBaseOperate.INDEX_OF_SAME_DEVICE_NAME, Integer.valueOf(dmDevice.getIndexOfSameDeviceName()));
        return contentValues;
    }

    @Override // com.ts.dm.lib.storemanager.IDataBaseOperate
    public boolean saveOrUpdateDeviceRecord(DmDevice device) {
        if (TextUtils.isEmpty(device.getMacAddress())) {
            return false;
        }
        boolean ret = false;
        if (this.mSqLiteDatabase != null) {
            try {
                DeviceRecord deviceRecord = queryDevice(device.getMacAddress());
                if (deviceRecord == null) {
                    ret = saveDeviceRecord(device);
                } else {
                    ret = updateDeviceRecord(device);
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (RuntimeException runtime) {
                runtime.printStackTrace();
            }
        }
        return ret;
    }

    private boolean saveDeviceRecord(DmDevice device) throws SQLException {
        ContentValues contentValues;
        if (TextUtils.isEmpty(device.getMacAddress()) || this.mSqLiteDatabase == null || (contentValues = getDeviceData(device)) == null) {
            return false;
        }
        long count = this.mSqLiteDatabase.insert(IDataBaseOperate.TABLE_NAME, null, contentValues);
        if (count <= 0) {
            return false;
        }
        return true;
    }

    private boolean updateDeviceRecord(DmDevice device) throws SQLException {
        if (TextUtils.isEmpty(device.getMacAddress()) || this.mSqLiteDatabase == null) {
            return false;
        }
        ContentValues contentValues = getDeviceData(device);
        int count = this.mSqLiteDatabase.update(IDataBaseOperate.TABLE_NAME, contentValues, "MAC_ADDRESS = ?", new String[]{device.getMacAddress()});
        if (count <= 0) {
            return false;
        }
        return true;
    }

    @Override // com.ts.dm.lib.storemanager.IDataBaseOperate
    public boolean deleteDevice(String mac) {
        if (TextUtils.isEmpty(mac) || this.mSqLiteDatabase == null) {
            return false;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("delete from ");
        sql.append(IDataBaseOperate.TABLE_NAME);
        sql.append(" where ");
        sql.append(IDataBaseOperate.MAC_ADDRESS);
        sql.append(" = ");
        sql.append("'" + mac + "'");
        try {
            this.mSqLiteDatabase.execSQL(sql.toString());
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        } catch (RuntimeException runtime) {
            runtime.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.dm.lib.storemanager.IDataBaseOperate
    public void resetProjectionStates() {
        if (this.mSqLiteDatabase != null) {
            resetProjectionStates(IDataBaseOperate.ANDROID_AUTO_PROJECTION_STATE);
            resetProjectionStates(IDataBaseOperate.CARPLAY_PROJECTION_STATE);
            resetProjectionStates(IDataBaseOperate.CARLIFE_PROJECTION_STATE);
            resetProjectionStates(IDataBaseOperate.HICAR_PROJECTION_STATE);
        }
    }

    private void resetProjectionStates(String projectionState) {
        ContentValues values = new ContentValues();
        values.put(projectionState, (Integer) 2);
        try {
            this.mSqLiteDatabase.update(IDataBaseOperate.TABLE_NAME, values, projectionState + " = ? or " + projectionState + " = ? or " + projectionState + " = ?", new String[]{String.valueOf(3), String.valueOf(4), String.valueOf(5)});
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    private String getStringDataFromDatabase(Cursor cursor, String column) {
        int index = cursor.getColumnIndex(column);
        if (index != -1) {
            return cursor.getString(index);
        }
        addDataBaseColumn(column, IDataBaseOperate.COLUMN_TYPE_TEXT);
        return "";
    }

    private int getIntDataFromDatabase(Cursor cursor, String colum) {
        int index = cursor.getColumnIndex(colum);
        if (index != -1) {
            return cursor.getInt(index);
        }
        addDataBaseColumn(colum, IDataBaseOperate.COLUMN_TYPE_INTERGER);
        return 0;
    }

    private void addDataBaseColumn(String column, String columnType) {
        String sql = "alter table DM_Device ADD COLUMN " + column + " " + columnType;
        try {
            this.mSqLiteDatabase.execSQL(sql);
            if (this.mSqLiteDatabase.isOpen()) {
                this.mSqLiteDatabase.close();
            }
            this.mSqLiteDatabase = getReadableDatabase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ColumnInfo {
        String mDefaultValue;
        boolean mIsKey;
        String mName;
        String mType;

        ColumnInfo(String name, String type, boolean key, String defaultValue) {
            this.mName = name;
            this.mType = type;
            this.mIsKey = key;
            this.mDefaultValue = defaultValue;
        }
    }
}
