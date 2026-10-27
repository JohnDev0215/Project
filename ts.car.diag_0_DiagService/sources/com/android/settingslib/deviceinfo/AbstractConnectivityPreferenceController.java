package com.android.settingslib.deviceinfo;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.android.settingslib.core.lifecycle.b.i;
import com.android.settingslib.core.lifecycle.b.j;
import com.android.settingslib.d.a;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractConnectivityPreferenceController extends a implements com.android.settingslib.core.lifecycle.a, i, j {
    private final BroadcastReceiver d;

    @Override // com.android.settingslib.core.lifecycle.b.i
    public void c() {
        IntentFilter intentFilter = new IntentFilter();
        for (String str : n()) {
            intentFilter.addAction(str);
        }
        this.f1346c.registerReceiver(this.d, intentFilter, "android.permission.CHANGE_NETWORK_STATE", null);
    }

    @Override // com.android.settingslib.core.lifecycle.b.j
    public void g() {
        this.f1346c.unregisterReceiver(this.d);
    }

    protected abstract String[] n();
}
