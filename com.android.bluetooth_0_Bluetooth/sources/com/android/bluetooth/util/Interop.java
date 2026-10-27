package com.android.bluetooth.util;

import com.android.bluetooth.DeviceWorkArounds;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Interop {
    public static final int INTEROP_MAP_ASCIIONLY = 1;
    private static List<Entry> sEntries = null;

    private static class Entry {
        public String address;
        public int workaround_id;

        Entry(int workaroundId, String address) {
            this.workaround_id = workaroundId;
            this.address = address;
        }
    }

    private static void lazyInitInteropDatabase() {
        if (sEntries != null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        sEntries = arrayList;
        arrayList.add(new Entry(1, DeviceWorkArounds.MERCEDES_BENZ_CARKIT));
    }

    public static boolean matchByAddress(int workaroundId, String address) {
        if (address == null || address.isEmpty()) {
            return false;
        }
        lazyInitInteropDatabase();
        for (Entry entry : sEntries) {
            if (entry.workaround_id == workaroundId && entry.address.startsWith(address.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
