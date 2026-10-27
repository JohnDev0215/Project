package org.chromium.ui.base;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.SparseArray;
import defpackage.xz;
import java.util.HashMap;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.base.c;

/* JADX INFO: compiled from: IntentRequestTrackerImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d implements c {
    public int b;
    public final c.a c;
    public final SparseArray<WindowAndroid.c> a = new SparseArray<>();
    public HashMap<Integer, String> d = new HashMap<>();

    public d(a aVar) {
        this.c = aVar;
    }

    public final int a(Intent intent, WindowAndroid.c cVar, Integer num) {
        int i = this.b;
        int i2 = i + 1000;
        boolean z = true;
        this.b = (i + 1) % 100;
        Activity activity = ((a) this.c).a.get();
        if (activity == null) {
            z = false;
        } else {
            try {
                activity.startActivityForResult(intent, i2);
            } catch (ActivityNotFoundException unused) {
                z = false;
            }
        }
        if (!z) {
            return -1;
        }
        this.a.put(i2, cVar);
        this.d.put(Integer.valueOf(i2), num == null ? null : xz.a.getString(num.intValue()));
        return i2;
    }
}
