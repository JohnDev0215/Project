package com.byd.systemsettings.system.callback;

import android.database.ContentObserver;
import android.net.Uri;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SettingsCallback extends ContentObserver {
    private static final String TAG = "SettingsCallback";
    public String mKey;

    public SettingsCallback(String str) {
        super(null);
        this.mKey = str;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z7, Uri uri) {
        super.onChange(z7);
        String lastPathSegment = uri.getLastPathSegment();
        a.a(TAG, "---onSettingsValueChanged---key:" + lastPathSegment);
        String str = this.mKey;
        if (str == null || !str.equals(lastPathSegment)) {
            return;
        }
        onValueChanged();
    }

    public abstract void onValueChanged();
}
