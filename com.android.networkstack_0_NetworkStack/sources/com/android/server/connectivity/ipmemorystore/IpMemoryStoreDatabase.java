package com.android.server.connectivity.ipmemorystore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.net.ipmemorystore.NetworkAttributes;
import android.util.Log;
import com.android.networkstack.util.Inet4AddressUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

/* JADX INFO: loaded from: classes.dex */
public class IpMemoryStoreDatabase {
    private static final String TAG = "IpMemoryStoreDatabase";
    private static final String[] EXPIRY_COLUMN = {"expiryDate"};
    private static final String[] DATA_COLUMN = {"data"};

    public static class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, "IpMemoryStore.db", (SQLiteDatabase.CursorFactory) null, 4);
            setIdleConnectionTimeout(30000L);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS NetworkAttributes (l2Key TEXT NOT NULL PRIMARY KEY NOT NULL, expiryDate BIGINT, assignedV4Address INTEGER, assignedV4AddressExpiry BIGINT, groupHint TEXT, dnsAddresses BLOB, mtu INTEGER DEFAULT -1)");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS PrivateData (l2Key TEXT NOT NULL, client TEXT NOT NULL, dataName TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (l2Key, client, dataName))");
            createTrigger(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i < 3) {
                try {
                    sQLiteDatabase.execSQL("alter table NetworkAttributes ADD assignedV4AddressExpiry BIGINT");
                } catch (SQLiteException e) {
                    Log.e(IpMemoryStoreDatabase.TAG, "Could not upgrade to the new version", e);
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS NetworkAttributes");
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS PrivateData");
                    onCreate(sQLiteDatabase);
                    return;
                }
            }
            if (i < 4) {
                createTrigger(sQLiteDatabase);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS NetworkAttributes");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS PrivateData");
            sQLiteDatabase.execSQL("DROP TRIGGER delete_cascade_to_private");
            onCreate(sQLiteDatabase);
        }

        private void createTrigger(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TRIGGER delete_cascade_to_private DELETE ON NetworkAttributes BEGIN DELETE FROM PrivateData WHERE OLD.l2Key=l2Key; END;");
        }
    }

    private static byte[] encodeAddressList(List<InetAddress> list) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Iterator<InetAddress> it = list.iterator();
        while (it.hasNext()) {
            byte[] address = it.next().getAddress();
            byteArrayOutputStream.write(address.length);
            byteArrayOutputStream.write(address, 0, address.length);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static ArrayList<InetAddress> decodeAddressList(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ArrayList<InetAddress> arrayList = new ArrayList<>();
        while (true) {
            int i = byteArrayInputStream.read();
            if (i == -1) {
                return arrayList;
            }
            byte[] bArr2 = new byte[i];
            byteArrayInputStream.read(bArr2, 0, i);
            try {
                arrayList.add(InetAddress.getByAddress(bArr2));
            } catch (UnknownHostException unused) {
            }
        }
    }

    private static ContentValues toContentValues(NetworkAttributes networkAttributes) {
        ContentValues contentValues = new ContentValues();
        if (networkAttributes == null) {
            return contentValues;
        }
        Inet4Address inet4Address = networkAttributes.assignedV4Address;
        if (inet4Address != null) {
            contentValues.put("assignedV4Address", Integer.valueOf(Inet4AddressUtils.inet4AddressToIntHTH(inet4Address)));
        }
        Long l = networkAttributes.assignedV4AddressExpiry;
        if (l != null) {
            contentValues.put("assignedV4AddressExpiry", l);
        }
        String str = networkAttributes.cluster;
        if (str != null) {
            contentValues.put("groupHint", str);
        }
        List<InetAddress> list = networkAttributes.dnsAddresses;
        if (list != null) {
            contentValues.put("dnsAddresses", encodeAddressList(list));
        }
        Integer num = networkAttributes.mtu;
        if (num != null) {
            contentValues.put("mtu", num);
        }
        return contentValues;
    }

    private static ContentValues toContentValues(String str, NetworkAttributes networkAttributes, long j) {
        ContentValues contentValues = toContentValues(networkAttributes);
        contentValues.put("l2Key", str);
        contentValues.put("expiryDate", Long.valueOf(j));
        return contentValues;
    }

    private static ContentValues toContentValues(String str, String str2, String str3, byte[] bArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("l2Key", str);
        contentValues.put("client", str2);
        contentValues.put("dataName", str3);
        contentValues.put("data", bArr);
        return contentValues;
    }

    private static NetworkAttributes readNetworkAttributesLine(Cursor cursor) {
        if (getLong(cursor, "expiryDate", -1L) < System.currentTimeMillis()) {
            return null;
        }
        NetworkAttributes.Builder builder = new NetworkAttributes.Builder();
        int i = getInt(cursor, "assignedV4Address", 0);
        long j = getLong(cursor, "assignedV4AddressExpiry", 0L);
        String string = getString(cursor, "groupHint");
        byte[] blob = getBlob(cursor, "dnsAddresses");
        int i2 = getInt(cursor, "mtu", -1);
        if (i != 0) {
            builder.setAssignedV4Address(Inet4AddressUtils.intToInet4AddressHTH(i));
        }
        if (0 != j) {
            builder.setAssignedV4AddressExpiry(Long.valueOf(j));
        }
        builder.setCluster(string);
        if (blob != null) {
            builder.setDnsAddresses(decodeAddressList(blob));
        }
        if (i2 >= 0) {
            builder.setMtu(Integer.valueOf(i2));
        }
        return builder.build();
    }

    static long getExpiry(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursorQuery = sQLiteDatabase.query("NetworkAttributes", EXPIRY_COLUMN, "l2Key = ?", new String[]{str}, null, null, null);
        try {
            if (cursorQuery.getCount() != 1) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return -1L;
            }
            cursorQuery.moveToFirst();
            long j = cursorQuery.getLong(0);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return j;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    static int storeNetworkAttributes(SQLiteDatabase sQLiteDatabase, String str, long j, NetworkAttributes networkAttributes) {
        ContentValues contentValues = toContentValues(str, networkAttributes, j);
        sQLiteDatabase.beginTransaction();
        try {
            try {
                if (sQLiteDatabase.insertWithOnConflict("NetworkAttributes", null, contentValues, 4) < 0) {
                    sQLiteDatabase.update("NetworkAttributes", contentValues, "l2Key = ?", new String[]{str});
                }
                sQLiteDatabase.setTransactionSuccessful();
                return 0;
            } catch (SQLiteException e) {
                Log.e(TAG, "Could not write to the memory store", e);
                sQLiteDatabase.endTransaction();
                return -4;
            }
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    static int storeBlob(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, byte[] bArr) {
        return sQLiteDatabase.insertWithOnConflict("PrivateData", null, toContentValues(str, str2, str3, bArr), 5) == -1 ? -4 : 0;
    }

    static NetworkAttributes retrieveNetworkAttributes(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursorQuery = sQLiteDatabase.query("NetworkAttributes", null, "l2Key = ?", new String[]{str}, null, null, null);
        try {
            if (cursorQuery.getCount() != 1) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return null;
            }
            cursorQuery.moveToFirst();
            NetworkAttributes networkAttributesLine = readNetworkAttributesLine(cursorQuery);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return networkAttributesLine;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    static byte[] retrieveBlob(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        Cursor cursorQuery = sQLiteDatabase.query("PrivateData", DATA_COLUMN, "l2Key = ? AND client = ? AND dataName = ?", new String[]{str, str2, str3}, null, null, null);
        try {
            if (cursorQuery.getCount() != 1) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return null;
            }
            cursorQuery.moveToFirst();
            byte[] blob = cursorQuery.getBlob(0);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return blob;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    static void wipeDataUponNetworkReset(SQLiteDatabase sQLiteDatabase) {
        for (int i = 3; i > 0; i--) {
            sQLiteDatabase.beginTransaction();
            try {
                try {
                    sQLiteDatabase.delete("NetworkAttributes", null, null);
                    sQLiteDatabase.delete("PrivateData", null, null);
                    Cursor cursorQuery = sQLiteDatabase.query("NetworkAttributes", new String[]{"l2Key"}, null, null, null, null, null, "1");
                    try {
                        if (cursorQuery.getCount() == 0) {
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            cursorQuery = sQLiteDatabase.query("PrivateData", new String[]{"l2Key"}, null, null, null, null, null, "1");
                            try {
                                if (cursorQuery.getCount() == 0) {
                                    if (cursorQuery != null) {
                                        cursorQuery.close();
                                    }
                                    sQLiteDatabase.setTransactionSuccessful();
                                } else if (cursorQuery != null) {
                                    cursorQuery.close();
                                }
                            } finally {
                            }
                        } else if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    } finally {
                    }
                } catch (Throwable th) {
                    sQLiteDatabase.endTransaction();
                    throw th;
                }
            } catch (SQLiteException e) {
                Log.e(TAG, "Could not wipe the data in database", e);
            }
            sQLiteDatabase.endTransaction();
        }
    }

    private static class CustomCursorFactory implements SQLiteDatabase.CursorFactory {
        private final ArrayList<Object> mArgs;

        CustomCursorFactory(ArrayList<Object> arrayList) {
            this.mArgs = arrayList;
        }

        @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
        public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
            int i;
            int i2 = 1;
            for (Object obj : this.mArgs) {
                if (obj instanceof String) {
                    i = i2 + 1;
                    sQLiteQuery.bindString(i2, (String) obj);
                } else if (obj instanceof Long) {
                    i = i2 + 1;
                    sQLiteQuery.bindLong(i2, ((Long) obj).longValue());
                } else if (obj instanceof Integer) {
                    i = i2 + 1;
                    sQLiteQuery.bindLong(i2, Long.valueOf(((Integer) obj).intValue()).longValue());
                } else if (obj instanceof byte[]) {
                    i = i2 + 1;
                    sQLiteQuery.bindBlob(i2, (byte[]) obj);
                } else {
                    throw new IllegalStateException("Unsupported type CustomCursorFactory " + obj.getClass().toString());
                }
                i2 = i;
            }
            return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
        }
    }

    static String findClosestAttributes(SQLiteDatabase sQLiteDatabase, NetworkAttributes networkAttributes) {
        String string = null;
        if (networkAttributes.isEmpty()) {
            return null;
        }
        ContentValues contentValues = toContentValues(networkAttributes);
        StringJoiner stringJoiner = new StringJoiner(" OR ");
        ArrayList arrayList = new ArrayList();
        arrayList.add(Long.valueOf(System.currentTimeMillis()));
        for (String str : contentValues.keySet()) {
            stringJoiner.add(str + " = ?");
            arrayList.add(contentValues.get(str));
        }
        Cursor cursorQueryWithFactory = sQLiteDatabase.queryWithFactory(new CustomCursorFactory(arrayList), false, "NetworkAttributes", null, "expiryDate > ? AND (" + stringJoiner.toString() + ")", null, null, null, null, null);
        try {
            if (cursorQueryWithFactory.getCount() <= 0) {
                if (cursorQueryWithFactory != null) {
                    cursorQueryWithFactory.close();
                }
                return null;
            }
            cursorQueryWithFactory.moveToFirst();
            float f = 0.5f;
            while (!cursorQueryWithFactory.isAfterLast()) {
                float networkGroupSamenessConfidence = readNetworkAttributesLine(cursorQueryWithFactory).getNetworkGroupSamenessConfidence(networkAttributes);
                if (networkGroupSamenessConfidence > f) {
                    string = getString(cursorQueryWithFactory, "l2Key");
                    f = networkGroupSamenessConfidence;
                }
                cursorQueryWithFactory.moveToNext();
            }
            if (cursorQueryWithFactory != null) {
                cursorQueryWithFactory.close();
            }
            return string;
        } finally {
        }
    }

    static StatusAndCount delete(SQLiteDatabase sQLiteDatabase, String str, boolean z) {
        return deleteEntriesWithColumn(sQLiteDatabase, "l2Key", str, z);
    }

    static StatusAndCount deleteCluster(SQLiteDatabase sQLiteDatabase, String str, boolean z) {
        return deleteEntriesWithColumn(sQLiteDatabase, "groupHint", str, z);
    }

    private static StatusAndCount deleteEntriesWithColumn(SQLiteDatabase sQLiteDatabase, String str, String str2, boolean z) {
        int iVacuum;
        sQLiteDatabase.beginTransaction();
        try {
            int iDelete = sQLiteDatabase.delete("NetworkAttributes", str + "= ?", new String[]{str2});
            sQLiteDatabase.setTransactionSuccessful();
            if (z && (iVacuum = vacuum(sQLiteDatabase)) != 0) {
                return new StatusAndCount(iVacuum, iDelete);
            }
            return new StatusAndCount(0, iDelete);
        } catch (SQLiteException e) {
            Log.e(TAG, "Could not delete from the memory store", e);
            return new StatusAndCount(-5, 0);
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    static int dropAllExpiredRecords(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.delete("NetworkAttributes", "expiryDate < ?", new String[]{Long.toString(System.currentTimeMillis())});
            sQLiteDatabase.setTransactionSuccessful();
            try {
                sQLiteDatabase.execSQL("VACUUM");
            } catch (SQLiteException unused) {
            }
            return 0;
        } catch (SQLiteException e) {
            Log.e(TAG, "Could not delete data from memory store", e);
            return -4;
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    static int dropNumberOfRecords(SQLiteDatabase sQLiteDatabase, int i) {
        if (i <= 0) {
            return -2;
        }
        Cursor cursorQuery = sQLiteDatabase.query("NetworkAttributes", new String[]{"expiryDate"}, null, null, null, null, "expiryDate", Integer.toString(i));
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() > 0) {
                    cursorQuery.moveToLast();
                    long j = getLong(cursorQuery, "expiryDate", 0L);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    sQLiteDatabase.beginTransaction();
                    try {
                        sQLiteDatabase.delete("NetworkAttributes", "expiryDate <= ?", new String[]{Long.toString(j)});
                        sQLiteDatabase.setTransactionSuccessful();
                        try {
                            sQLiteDatabase.execSQL("VACUUM");
                        } catch (SQLiteException unused) {
                        }
                        return 0;
                    } catch (SQLiteException e) {
                        Log.e(TAG, "Could not delete data from memory store", e);
                        return -4;
                    } finally {
                        sQLiteDatabase.endTransaction();
                    }
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return -1;
    }

    static int getTotalRecordNumber(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("NetworkAttributes", new String[]{"COUNT(*)"}, null, null, null, null, null);
        try {
            cursorQuery.moveToFirst();
            int i = 0;
            if (cursorQuery != null) {
                i = cursorQuery.getInt(0);
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return i;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private static String getString(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return cursor.getString(columnIndex);
        }
        return null;
    }

    private static byte[] getBlob(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return cursor.getBlob(columnIndex);
        }
        return null;
    }

    private static int getInt(Cursor cursor, String str, int i) {
        int columnIndex = cursor.getColumnIndex(str);
        return columnIndex >= 0 ? cursor.getInt(columnIndex) : i;
    }

    private static long getLong(Cursor cursor, String str, long j) {
        int columnIndex = cursor.getColumnIndex(str);
        return columnIndex >= 0 ? cursor.getLong(columnIndex) : j;
    }

    private static int vacuum(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("VACUUM");
            return 0;
        } catch (SQLiteException unused) {
            return -4;
        }
    }
}
