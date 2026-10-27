package com.android.bluetooth.map;

import com.android.bluetooth.SignedLongLong;

/* JADX INFO: loaded from: classes.dex */
public class MapContact {
    private final long mId;
    private final String mName;

    private MapContact(long id, String name) {
        this.mId = id;
        this.mName = name;
    }

    public static MapContact create(long id, String name) {
        return new MapContact(id, name);
    }

    public String getName() {
        return this.mName;
    }

    public long getId() {
        return this.mId;
    }

    public String getXBtUidString() {
        long j = this.mId;
        if (j > 0) {
            return BluetoothMapUtils.getLongLongAsString(j, 0L);
        }
        return null;
    }

    public SignedLongLong getXBtUid() {
        long j = this.mId;
        if (j > 0) {
            return new SignedLongLong(j, 0L);
        }
        return null;
    }

    public String toString() {
        return this.mName;
    }
}
