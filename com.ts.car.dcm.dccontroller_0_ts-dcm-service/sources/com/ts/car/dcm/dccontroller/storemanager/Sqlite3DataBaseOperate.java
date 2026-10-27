package com.ts.car.dcm.dccontroller.storemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import com.ts.car.dcm.common.data.DcmUtils;
import com.ts.car.dcm.common.data.LogEncrypt;
import com.ts.car.dcm.dccontroller.R;
import com.ts.car.dcm.dccontroller.configdata.IConfiguration;
import com.ts.car.dcm.dccontroller.storemanager.Sqlite3DataBaseOperate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class Sqlite3DataBaseOperate extends SQLiteOpenHelper implements IDataBaseOperate {
    private static final String TAG = "Sqlite3DataBaseOperate";
    private final List<ColumnInfo> mColumnInfos;
    private boolean mIsDatabaseUpdate;
    private SQLiteDatabase mSqLiteDatabase;

    public Sqlite3DataBaseOperate(Context context) {
        super(context, IDataBaseOperate.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, Integer.parseInt(context.getString(R.string.DatabaseVersion)));
        this.mColumnInfos = new ArrayList(Arrays.asList(new ColumnInfo(IDataBaseOperate.COLUMN_DEVICE_UUID, IDataBaseOperate.COLUMN_TYPE_TEXT, true, "not null"), new ColumnInfo(IDataBaseOperate.COLUMN_DEVICE_FRIENDLY_NAME, IDataBaseOperate.COLUMN_TYPE_TEXT, false, "IVI"), new ColumnInfo(IDataBaseOperate.COLUMN_USB_SERIAL_NUMBER, IDataBaseOperate.COLUMN_TYPE_TEXT, false, "null"), new ColumnInfo(IDataBaseOperate.COLUMN_BTADDR, IDataBaseOperate.COLUMN_TYPE_TEXT, false, "null"), new ColumnInfo(IDataBaseOperate.COLUMN_WIFI_MAC_ADDR, IDataBaseOperate.COLUMN_TYPE_TEXT, false, "null"), new ColumnInfo(IDataBaseOperate.COLUMN_IS_LAST_PROJECTION_DEV, IDataBaseOperate.COLUMN_TYPE_TINYINT, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_FAVORTE, IDataBaseOperate.COLUMN_TYPE_TINYINT, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_LAST_ONLINE_TIME, IDataBaseOperate.COLUMN_TYPE_TEXT, false, "(strftime('%s','now','localtime'))"), new ColumnInfo(IDataBaseOperate.COLUMN_LAST_SERVICE, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_ALWAYS_CAPABILITY, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_BLUETOOTH_PAIRED, IDataBaseOperate.COLUMN_TYPE_TINYINT, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_DEVICE_TYPE, IDataBaseOperate.COLUMN_TYPE_TINYINT, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_DEVICE_CAPABILITY, IDataBaseOperate.COLUMN_TYPE_TINYINT, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_DEVICE_IS_CARPLAY_FIRST, IDataBaseOperate.COLUMN_TYPE_TINYINT, false, "1"), new ColumnInfo(IDataBaseOperate.COLUMN_DEVICE_IS_CARLIFE_FIRST, IDataBaseOperate.COLUMN_TYPE_TINYINT, false, "1"), new ColumnInfo(IDataBaseOperate.COLUMN_DEVICE_IS_ANDROIDAUTO_FIRST, IDataBaseOperate.COLUMN_TYPE_TINYINT, false, "1"), new ColumnInfo(IDataBaseOperate.COLUMN_DEVICE_IS_WIRELESS_AA_REQUESTED, IDataBaseOperate.COLUMN_TYPE_TINYINT, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_CPWL_OVER_IAP_DETECTED, IDataBaseOperate.COLUMN_TYPE_TINYINT, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_CPWL_OVER_IAP_DETECTED_NOTIFIED, IDataBaseOperate.COLUMN_TYPE_TINYINT, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_BT_DEVICE_PAIRED_TIME, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_WIRELESS_CARPLAY_CONNECTED_TIME, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_USB_CARPLAY_CONNECTED_TIME, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_WIRELESS_ANDROID_AUTO_CONNECTED_TIME, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_USB_ANDROID_AUTO_CONNECTED_TIME, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, IConfiguration.DEFAULT_JAR_VERSION), new ColumnInfo(IDataBaseOperate.COLUMN_INDEX_OF_SAME_DEVICE_NAME, IDataBaseOperate.COLUMN_TYPE_INTERGER, false, "-1"), new ColumnInfo(IDataBaseOperate.COLUMN_MOBILE_DEVICE_ID, IDataBaseOperate.COLUMN_TYPE_TEXT, false, "null")));
        this.mIsDatabaseUpdate = false;
        this.mSqLiteDatabase = null;
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
                sql.append(" default ");
                sql.append(ci.mDefaultValue);
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
        Log.d(TAG, "onUpgrade, oldVersion:" + oldVersion + ", newVersion:" + newVersion);
        this.mIsDatabaseUpdate = true;
        try {
            Cursor cursor = db.rawQuery("select * from TB_Device", null);
            try {
                String[] oldColumnNames = cursor.getColumnNames();
                db.execSQL("alter table TB_Device rename to old_TB_Device");
                onCreate(db);
                List<String> newColNames = (List) this.mColumnInfos.stream().map(new Function() { // from class: com.ts.car.dcm.dccontroller.storemanager.-$$Lambda$Sqlite3DataBaseOperate$db3iLbmdIZ8njNq90Kdki-QuwgA
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
                db.execSQL("INSERT INTO " + IDataBaseOperate.TABLE_NAME + "(" + copy_columns + ") SELECT " + copy_columns + " FROM old_TB_Device");
                StringBuilder sb = new StringBuilder();
                sb.append("DROP TABLE ");
                sb.append("old_TB_Device");
                db.execSQL(sb.toString());
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

    @Override // com.ts.car.dcm.dccontroller.storemanager.IDataBaseOperate
    public boolean insertDevice(Device dev) {
        if (this.mSqLiteDatabase != null) {
            ContentValues contentValues = getDeviceData(dev);
            if (contentValues == null) {
                return false;
            }
            try {
                long count = this.mSqLiteDatabase.insert(IDataBaseOperate.TABLE_NAME, null, contentValues);
                if (count > 0) {
                    return true;
                }
                Log.e(TAG, "insertDevice, count is 0.");
                return false;
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                return false;
            } catch (RuntimeException runtime) {
                runtime.printStackTrace();
                return false;
            }
        }
        Log.e(TAG, "insertDevice, mSqLiteDatabase is null.");
        return false;
    }

    @Override // com.ts.car.dcm.dccontroller.storemanager.IDataBaseOperate
    public boolean insertAllDevice(Map<String, Device> DeviceMap) {
        if (this.mSqLiteDatabase == null) {
            Log.e(TAG, "insertAllDevice, mSQLiteDatabase is null.");
            return false;
        }
        for (Device s : DeviceMap.values()) {
            if (!insertDevice(s)) {
                Log.e(TAG, "insertAllDevice, insert failed.");
                return false;
            }
        }
        return true;
    }

    @Override // com.ts.car.dcm.dccontroller.storemanager.IDataBaseOperate
    public Device selectDevice(String value, String column) {
        Device device = null;
        StringBuffer sql = new StringBuffer();
        if (this.mSqLiteDatabase != null) {
            sql.append("select * from ");
            sql.append(IDataBaseOperate.TABLE_NAME);
            sql.append(" where ");
            sql.append(column);
            sql.append(" = ");
            sql.append("'" + value + "'");
            try {
                Cursor cursor = this.mSqLiteDatabase.rawQuery(sql.toString(), null);
                if (cursor != null && cursor.getCount() > 0 && cursor.moveToNext()) {
                    device = toDevice(cursor);
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (RuntimeException runtime) {
                runtime.printStackTrace();
            }
        } else {
            Log.e(TAG, "selectDevice, mSqLiteDatabase is null.");
        }
        return device;
    }

    @Override // com.ts.car.dcm.dccontroller.storemanager.IDataBaseOperate
    public List<Device> selectDeviceByOrderAscLimit(String order, String asc, int number) {
        List<Device> list = null;
        if (this.mSqLiteDatabase != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select * from ");
            sql.append(IDataBaseOperate.TABLE_NAME);
            sql.append(" ");
            sql.append(order);
            sql.append(" ");
            sql.append(asc);
            sql.append(" limit " + number);
            try {
                Cursor cursor = this.mSqLiteDatabase.rawQuery(sql.toString(), null);
                if (cursor != null && cursor.getCount() > 0) {
                    list = new ArrayList<>();
                    while (cursor.moveToNext()) {
                        Device dev = toDevice(cursor);
                        if (dev != null) {
                            list.add(dev);
                        }
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
        } else {
            Log.e(TAG, "selectDeviceByOrderAscLimit, mSqLiteDatabase is null.");
        }
        return list;
    }

    @Override // com.ts.car.dcm.dccontroller.storemanager.IDataBaseOperate
    public Map<String, Device> selectAllDevice() {
        Map<String, Device> deviceMap = new ConcurrentHashMap<>();
        StringBuffer sql = new StringBuffer();
        if (this.mSqLiteDatabase != null) {
            sql.append("select * from ");
            sql.append(IDataBaseOperate.TABLE_NAME);
            try {
                Cursor cursor = this.mSqLiteDatabase.rawQuery(sql.toString(), null);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        Device dev = toDevice(cursor);
                        if (dev != null) {
                            deviceMap.put(dev.getId(), dev);
                        }
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
        } else {
            Log.e(TAG, "selectAllDevice, mSqLiteDatabase is null.");
        }
        return deviceMap;
    }

    @Override // com.ts.car.dcm.dccontroller.storemanager.IDataBaseOperate
    public boolean deleteDevice(String uuid) {
        if (this.mSqLiteDatabase != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("delete from ");
            sql.append(IDataBaseOperate.TABLE_NAME);
            sql.append(" where ");
            sql.append(IDataBaseOperate.COLUMN_DEVICE_UUID);
            sql.append(" = ");
            sql.append("'" + uuid + "'");
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
        Log.e(TAG, "deleteDevice, mSqLiteDatabase is null.");
        return false;
    }

    @Override // com.ts.car.dcm.dccontroller.storemanager.IDataBaseOperate
    public boolean deleteDeviceByOrderAscLimit(String order, String asc, int number) {
        if (this.mSqLiteDatabase != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("delete from ");
            sql.append(IDataBaseOperate.TABLE_NAME);
            sql.append(" ");
            sql.append("where ");
            sql.append(IDataBaseOperate.COLUMN_DEVICE_UUID);
            sql.append(" in ");
            sql.append("(");
            sql.append("select DEVICE_UUID from ");
            sql.append(IDataBaseOperate.TABLE_NAME);
            sql.append(" ");
            sql.append(order);
            sql.append(" ");
            sql.append(asc);
            sql.append(" limit " + number);
            sql.append(")");
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
        Log.e(TAG, "deleteDeviceByOrderAscLimit, mSqLiteDatabase is null.");
        return false;
    }

    @Override // com.ts.car.dcm.dccontroller.storemanager.IDataBaseOperate
    public boolean deleteAllDevice() {
        if (this.mSqLiteDatabase != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("delete from ");
            sql.append(IDataBaseOperate.TABLE_NAME);
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
        Log.e(TAG, "deleteAllDevice, mSqLiteDatabase is null.");
        return false;
    }

    @Override // com.ts.car.dcm.dccontroller.storemanager.IDataBaseOperate
    public boolean updateDevice(Device dev) {
        if (this.mSqLiteDatabase != null) {
            ContentValues contentValues = getDeviceData(dev);
            try {
                int count = this.mSqLiteDatabase.update(IDataBaseOperate.TABLE_NAME, contentValues, "DEVICE_UUID = ?", new String[]{dev.getId()});
                if (count <= 0) {
                    return false;
                }
                Log.d(TAG, "updateDevice, devId:" + LogEncrypt.IpAddressEncrypt(dev.getId()) + ", count:" + count);
                return true;
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                return false;
            } catch (RuntimeException runtime) {
                runtime.printStackTrace();
                return false;
            }
        }
        Log.e(TAG, "updateDevice, mSqLiteDatabase is null.");
        return false;
    }

    @Override // com.ts.car.dcm.dccontroller.storemanager.IDataBaseOperate
    public int getDataCount() {
        Cursor cursor = null;
        int count = 0;
        SQLiteDatabase sQLiteDatabase = this.mSqLiteDatabase;
        if (sQLiteDatabase != null) {
            try {
                cursor = sQLiteDatabase.rawQuery("select * from TB_Device", null);
                if (cursor != null) {
                    Log.d(TAG, "getDataCount, count:" + cursor.getCount());
                    count = cursor.getCount();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (RuntimeException runtime) {
                runtime.printStackTrace();
            }
        } else {
            Log.e(TAG, "getDataCount, mSqLiteDatabase is null.");
        }
        if (cursor != null) {
            cursor.close();
        }
        return count;
    }

    @Override // com.ts.car.dcm.dccontroller.storemanager.IDataBaseOperate
    public void beginTransaction() {
        this.mSqLiteDatabase.beginTransaction();
    }

    @Override // com.ts.car.dcm.dccontroller.storemanager.IDataBaseOperate
    public void setTransactionSuccessful() {
        this.mSqLiteDatabase.setTransactionSuccessful();
    }

    @Override // com.ts.car.dcm.dccontroller.storemanager.IDataBaseOperate
    public void endTransaction() {
        this.mSqLiteDatabase.endTransaction();
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

    private boolean getBooleanDataFromDatabase(Cursor cursor, String colum) {
        int index = cursor.getColumnIndex(colum);
        if (index != -1) {
            return cursor.getInt(index) == 1;
        }
        addDataBaseColumn(colum, IDataBaseOperate.COLUMN_TYPE_TINYINT);
        return false;
    }

    private long getLongDataFromDatabase(Cursor cursor, String colum) {
        int index = cursor.getColumnIndex(colum);
        if (index != -1) {
            return cursor.getLong(index);
        }
        addDataBaseColumn(colum, IDataBaseOperate.COLUMN_TYPE_INTERGER);
        return 0L;
    }

    private void addDataBaseColumn(String column, String columnType) {
        String sql = "alter table TB_Device ADD COLUMN " + column + " " + columnType;
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

    private Device toDevice(Cursor cursor) {
        Device device = null;
        if (cursor != null) {
            device = new Device(getStringDataFromDatabase(cursor, IDataBaseOperate.COLUMN_DEVICE_UUID));
            device.setFriendlyName(getStringDataFromDatabase(cursor, IDataBaseOperate.COLUMN_DEVICE_FRIENDLY_NAME));
            device.setUsbSerialNumber(getStringDataFromDatabase(cursor, IDataBaseOperate.COLUMN_USB_SERIAL_NUMBER));
            device.setBtAddr(getStringDataFromDatabase(cursor, IDataBaseOperate.COLUMN_BTADDR));
            device.setWifiAddr(getStringDataFromDatabase(cursor, IDataBaseOperate.COLUMN_WIFI_MAC_ADDR));
            device.setLast(getBooleanDataFromDatabase(cursor, IDataBaseOperate.COLUMN_IS_LAST_PROJECTION_DEV));
            device.setFavorite(getBooleanDataFromDatabase(cursor, IDataBaseOperate.COLUMN_FAVORTE));
            device.setLastService(getIntDataFromDatabase(cursor, IDataBaseOperate.COLUMN_LAST_SERVICE));
            String time = getStringDataFromDatabase(cursor, IDataBaseOperate.COLUMN_LAST_ONLINE_TIME);
            if (time == null || time.equals("")) {
                device.setLastUptime(IConfiguration.DEFAULT_JAR_VERSION);
            } else {
                device.setLastUptime(time);
            }
            device.setAlwaysCapability(getIntDataFromDatabase(cursor, IDataBaseOperate.COLUMN_ALWAYS_CAPABILITY));
            device.setIsPaired(getBooleanDataFromDatabase(cursor, IDataBaseOperate.COLUMN_BLUETOOTH_PAIRED));
            if (device.isPaired()) {
                device.addConnectedTypeState(2, 3);
            }
            device.setDeviceType(getIntDataFromDatabase(cursor, IDataBaseOperate.COLUMN_DEVICE_TYPE));
            device.setCapability(getIntDataFromDatabase(cursor, IDataBaseOperate.COLUMN_DEVICE_CAPABILITY));
            device.setIsCarplayFirstConn(getBooleanDataFromDatabase(cursor, IDataBaseOperate.COLUMN_DEVICE_IS_CARPLAY_FIRST));
            device.setIsCarlifeFirstConn(getBooleanDataFromDatabase(cursor, IDataBaseOperate.COLUMN_DEVICE_IS_CARLIFE_FIRST));
            device.setIsAndroidAutoFirstConn(getBooleanDataFromDatabase(cursor, IDataBaseOperate.COLUMN_DEVICE_IS_ANDROIDAUTO_FIRST));
            device.setIsWirelessAaRequested(getBooleanDataFromDatabase(cursor, IDataBaseOperate.COLUMN_DEVICE_IS_WIRELESS_AA_REQUESTED));
            device.setCpwlOverIapDetected(getBooleanDataFromDatabase(cursor, IDataBaseOperate.COLUMN_CPWL_OVER_IAP_DETECTED));
            device.setCpwlOverIapDetectedNotified(getBooleanDataFromDatabase(cursor, IDataBaseOperate.COLUMN_CPWL_OVER_IAP_DETECTED_NOTIFIED));
            device.setBtDevicePairedTime(getLongDataFromDatabase(cursor, IDataBaseOperate.COLUMN_BT_DEVICE_PAIRED_TIME));
            device.setWirelessCarplayConnectedTime(getLongDataFromDatabase(cursor, IDataBaseOperate.COLUMN_WIRELESS_CARPLAY_CONNECTED_TIME));
            device.setUsbCarplayConnectedTime(getLongDataFromDatabase(cursor, IDataBaseOperate.COLUMN_USB_CARPLAY_CONNECTED_TIME));
            device.setWirelessAndroidAutoConnectedTime(getLongDataFromDatabase(cursor, IDataBaseOperate.COLUMN_WIRELESS_ANDROID_AUTO_CONNECTED_TIME));
            device.setUsbAndroidAutoConnectedTime(getLongDataFromDatabase(cursor, IDataBaseOperate.COLUMN_USB_ANDROID_AUTO_CONNECTED_TIME));
            device.setIndexOfSameDeviceName(getIntDataFromDatabase(cursor, IDataBaseOperate.COLUMN_INDEX_OF_SAME_DEVICE_NAME));
            device.setMobileDeviceId(getStringDataFromDatabase(cursor, IDataBaseOperate.COLUMN_MOBILE_DEVICE_ID));
            device.setAccepted(1);
            device.setDeviceConnectedState(0);
            device.setIsNeedRecoryBt(false);
            device.setAvailableCapability(0);
            device.setLastAvailableCapability(0);
            device.setActiveCapability(0);
            device.setLastActivateCapability(0);
            device.setInUsbDeviceMode(false);
            device.setUsbNcmNode(null);
            device.setUsbHidNode(null);
            device.setIapNode(null);
        }
        return device;
    }

    public ContentValues getDeviceData(Device dev) {
        if (dev == null) {
            return null;
        }
        ContentValues values = new ContentValues();
        values.put(IDataBaseOperate.COLUMN_DEVICE_UUID, dev.getId());
        values.put(IDataBaseOperate.COLUMN_DEVICE_FRIENDLY_NAME, dev.getFriendlyName());
        values.put(IDataBaseOperate.COLUMN_USB_SERIAL_NUMBER, dev.getUsbSerialNumber());
        values.put(IDataBaseOperate.COLUMN_BTADDR, dev.getBtAddr());
        values.put(IDataBaseOperate.COLUMN_WIFI_MAC_ADDR, dev.getWifiAddr());
        values.put(IDataBaseOperate.COLUMN_IS_LAST_PROJECTION_DEV, Integer.valueOf(DcmUtils.boolToInt(dev.isLast())));
        values.put(IDataBaseOperate.COLUMN_FAVORTE, Integer.valueOf(DcmUtils.boolToInt(dev.isFavorite())));
        values.put(IDataBaseOperate.COLUMN_LAST_SERVICE, Integer.valueOf(dev.getLastService()));
        values.put(IDataBaseOperate.COLUMN_LAST_ONLINE_TIME, dev.getLastUptime());
        if (dev.isSaveAlways()) {
            values.put(IDataBaseOperate.COLUMN_ALWAYS_CAPABILITY, Integer.valueOf(dev.getAlwaysCapability()));
        }
        values.put(IDataBaseOperate.COLUMN_BLUETOOTH_PAIRED, Integer.valueOf(DcmUtils.boolToInt(dev.isPaired())));
        values.put(IDataBaseOperate.COLUMN_DEVICE_TYPE, Integer.valueOf(dev.getDeviceType()));
        values.put(IDataBaseOperate.COLUMN_DEVICE_CAPABILITY, Integer.valueOf(dev.getCapability()));
        values.put(IDataBaseOperate.COLUMN_DEVICE_IS_CARPLAY_FIRST, Boolean.valueOf(dev.isCarplayFirstConn()));
        values.put(IDataBaseOperate.COLUMN_DEVICE_IS_CARLIFE_FIRST, Boolean.valueOf(dev.isIsCarlifeFirstConn()));
        values.put(IDataBaseOperate.COLUMN_DEVICE_IS_ANDROIDAUTO_FIRST, Boolean.valueOf(dev.isAndroidAutoFirstConn()));
        values.put(IDataBaseOperate.COLUMN_DEVICE_IS_WIRELESS_AA_REQUESTED, Integer.valueOf(DcmUtils.boolToInt(dev.isWirelessAaRequested())));
        values.put(IDataBaseOperate.COLUMN_CPWL_OVER_IAP_DETECTED, Boolean.valueOf(dev.isCpwlOverIapDetected()));
        values.put(IDataBaseOperate.COLUMN_CPWL_OVER_IAP_DETECTED_NOTIFIED, Boolean.valueOf(dev.isCpwlOverIapDetectedNotified()));
        values.put(IDataBaseOperate.COLUMN_BT_DEVICE_PAIRED_TIME, Long.valueOf(dev.getBtDevicePairedTime()));
        values.put(IDataBaseOperate.COLUMN_WIRELESS_CARPLAY_CONNECTED_TIME, Long.valueOf(dev.getWirelessCarplayConnectedTime()));
        values.put(IDataBaseOperate.COLUMN_USB_CARPLAY_CONNECTED_TIME, Long.valueOf(dev.getUsbCarplayConnectedTime()));
        values.put(IDataBaseOperate.COLUMN_WIRELESS_ANDROID_AUTO_CONNECTED_TIME, Long.valueOf(dev.getWirelessAndroidAutoConnectedTime()));
        values.put(IDataBaseOperate.COLUMN_USB_ANDROID_AUTO_CONNECTED_TIME, Long.valueOf(dev.getUsbAndroidAutoConnectedTime()));
        values.put(IDataBaseOperate.COLUMN_INDEX_OF_SAME_DEVICE_NAME, Integer.valueOf(dev.getIndexOfSameDeviceName()));
        values.put(IDataBaseOperate.COLUMN_MOBILE_DEVICE_ID, dev.getMobileDeviceId());
        return values;
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
