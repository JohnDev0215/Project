package com.amazon.device.crashmanager;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class CrashDetailsAggregator implements CrashDetailsCollectable {
    private static final String TAG = "com.amazon.device.crashmanager.CrashDetailsAggregator";
    private List<CrashDetailsCollectable> collectors = new ArrayList();

    public void appendCollector(CrashDetailsCollectable crashDetailsCollectable) {
        this.collectors.add(crashDetailsCollectable);
    }

    @Override // com.amazon.device.crashmanager.CrashDetailsCollectable
    public Map<String, String> collect(Throwable th) {
        HashMap map = new HashMap();
        for (CrashDetailsCollectable crashDetailsCollectable : this.collectors) {
            try {
                map.putAll(crashDetailsCollectable.collect(th));
            } catch (Exception e) {
                Log.e(TAG, "crash data collector: '" + crashDetailsCollectable.toString() + "' threw exception: ", e);
            }
        }
        return map;
    }
}
