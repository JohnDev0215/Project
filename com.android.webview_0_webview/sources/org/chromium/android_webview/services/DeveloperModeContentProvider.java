package org.chromium.android_webview.services;

import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class DeveloperModeContentProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        HashMap map;
        if (!"/flag-overrides".equals(uri.getPath())) {
            return null;
        }
        synchronized (DeveloperUiService.u) {
            map = new HashMap(DeveloperUiService.v);
        }
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"flagName", "flagState"}, map.size());
        for (Map.Entry entry : map.entrySet()) {
            matrixCursor.addRow(new Object[]{(String) entry.getKey(), Integer.valueOf(((Boolean) entry.getValue()).booleanValue() ? 1 : 0)});
        }
        if (map.isEmpty()) {
            getContext().getPackageManager().setComponentEnabledSetting(new ComponentName(getContext(), "org.chromium.android_webview.devui.DeveloperModeState"), 0, 1);
            getContext().stopService(new Intent(getContext(), (Class<?>) DeveloperUiService.class));
        }
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }
}
