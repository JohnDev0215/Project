package com.android.bluetooth.map;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class SmsMmsContacts {
    private static final String[] ADDRESS_PROJECTION;
    private static final Uri ADDRESS_URI = Telephony.MmsSms.CONTENT_URI.buildUpon().appendPath("canonical-addresses").build();
    private static final int COL_ADDR_ADDR;
    private static final int COL_ADDR_ID;
    private static final int COL_CONTACT_ID;
    private static final int COL_CONTACT_NAME;
    private static final String[] CONTACT_PROJECTION;
    private static final String CONTACT_SEL_VISIBLE = "in_visible_group=1";
    private static final String TAG = "SmsMmsContacts";
    private HashMap<Long, String> mPhoneNumbers = null;
    private final HashMap<String, MapContact> mNames = new HashMap<>(10);

    static {
        String[] strArr = {"_id", "address"};
        ADDRESS_PROJECTION = strArr;
        COL_ADDR_ID = Arrays.asList(strArr).indexOf("_id");
        COL_ADDR_ADDR = Arrays.asList(ADDRESS_PROJECTION).indexOf("address");
        String[] strArr2 = {"_id", "display_name"};
        CONTACT_PROJECTION = strArr2;
        COL_CONTACT_ID = Arrays.asList(strArr2).indexOf("_id");
        COL_CONTACT_NAME = Arrays.asList(CONTACT_PROJECTION).indexOf("display_name");
    }

    public String getPhoneNumber(ContentResolver resolver, long id) {
        String number;
        HashMap<Long, String> map = this.mPhoneNumbers;
        if (map != null && (number = map.get(Long.valueOf(id))) != null) {
            return number;
        }
        fillPhoneCache(resolver);
        return this.mPhoneNumbers.get(Long.valueOf(id));
    }

    public static String getPhoneNumberUncached(ContentResolver resolver, long id) {
        String where = "_id = " + id;
        Cursor c = resolver.query(ADDRESS_URI, ADDRESS_PROJECTION, where, null, null);
        if (c != null) {
            try {
                if (c.moveToPosition(0)) {
                    return c.getString(COL_ADDR_ADDR);
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }
        Log.e(TAG, "query failed");
        if (c != null) {
            c.close();
            return null;
        }
        return null;
    }

    public void clearCache() {
        HashMap<Long, String> map = this.mPhoneNumbers;
        if (map != null) {
            map.clear();
        }
        HashMap<String, MapContact> map2 = this.mNames;
        if (map2 != null) {
            map2.clear();
        }
    }

    private void fillPhoneCache(ContentResolver resolver) {
        Cursor c = resolver.query(ADDRESS_URI, ADDRESS_PROJECTION, null, null, null);
        HashMap<Long, String> map = this.mPhoneNumbers;
        if (map == null) {
            int size = 0;
            if (c != null) {
                size = c.getCount();
            }
            this.mPhoneNumbers = new HashMap<>(size);
        } else {
            map.clear();
        }
        try {
            if (c != null) {
                c.moveToPosition(-1);
                while (c.moveToNext()) {
                    long id = c.getLong(COL_ADDR_ID);
                    String addr = c.getString(COL_ADDR_ADDR);
                    this.mPhoneNumbers.put(Long.valueOf(id), addr);
                }
            } else {
                Log.e(TAG, "query failed");
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public MapContact getContactNameFromPhone(String phone, ContentResolver resolver) {
        return getContactNameFromPhone(phone, resolver, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00bc A[Catch: all -> 0x00cf, TRY_LEAVE, TryCatch #0 {all -> 0x00cf, blocks: (B:19:0x009b, B:21:0x00a1, B:22:0x00bc), top: B:31:0x009b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.android.bluetooth.map.MapContact getContactNameFromPhone(java.lang.String r13, android.content.ContentResolver r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.SmsMmsContacts.getContactNameFromPhone(java.lang.String, android.content.ContentResolver, java.lang.String):com.android.bluetooth.map.MapContact");
    }
}
