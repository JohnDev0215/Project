package com.android.settingslib.drawer;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public abstract class SwitchesProvider extends ContentProvider {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f1351c;
    private final Map<String, f> d = new LinkedHashMap();
    private final List<Bundle> e = new ArrayList();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c(f fVar) {
        String strD = fVar.d();
        if (TextUtils.isEmpty(strD)) {
            throw new NullPointerException("Switch key cannot be null: " + fVar.getClass().getSimpleName());
        }
        if (!this.d.containsKey(strD)) {
            fVar.i(this.f1351c);
            this.d.put(strD, fVar);
            this.e.add(fVar.a());
        } else {
            throw new IllegalArgumentException("Switch key " + strD + " is duplicated by: " + fVar.getClass().getSimpleName());
        }
    }

    private Bundle d(boolean z, f fVar) {
        boolean zH = fVar.h(z);
        Bundle bundle = new Bundle();
        bundle.putBoolean("set_checked_error", !zH);
        if (!zH) {
            bundle.putString("set_checked_error_message", fVar.b(z));
        } else if (fVar instanceof c) {
            fVar.g(getContext());
        }
        return bundle;
    }

    protected abstract List<f> a();

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        String str = providerInfo.authority;
        this.f1351c = str;
        Log.i("SwitchesProvider", str);
        super.attachInfo(context, providerInfo);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        String string = bundle != null ? bundle.getString("com.android.settings.keyhint") : null;
        if (TextUtils.isEmpty(string)) {
            if (!"getSwitchData".equals(str)) {
                return null;
            }
            bundle2.putParcelableList("switch_data", this.e);
            return bundle2;
        }
        f fVar = this.d.get(string);
        if (fVar == 0) {
            return null;
        }
        byte b2 = -1;
        switch (str.hashCode()) {
            case -2097433649:
                if (str.equals("getDynamicTitle")) {
                    b2 = 2;
                }
                break;
            case -1844463779:
                if (str.equals("getDynamicSummary")) {
                    b2 = 3;
                }
                break;
            case -952092468:
                if (str.equals("onCheckedChanged")) {
                    b2 = 5;
                }
                break;
            case -645523212:
                if (str.equals("getSwitchData")) {
                    b2 = 0;
                }
                break;
            case 162535197:
                if (str.equals("isChecked")) {
                    b2 = 4;
                }
                break;
            case 1207101568:
                if (str.equals("getProviderIcon")) {
                    b2 = 1;
                }
                break;
        }
        if (b2 == 0) {
            return fVar.a();
        }
        if (b2 != 1) {
            if (b2 != 2) {
                if (b2 != 3) {
                    if (b2 == 4) {
                        bundle2.putBoolean("checked_state", fVar.e());
                        return bundle2;
                    }
                    if (b2 == 5) {
                        return d(bundle.getBoolean("checked_state"), fVar);
                    }
                } else if (fVar instanceof c) {
                    bundle2.putString("com.android.settings.summary", ((c) fVar).a());
                    return bundle2;
                }
            } else if (fVar instanceof d) {
                bundle2.putString("com.android.settings.title", ((d) fVar).a());
                return bundle2;
            }
        } else if (fVar instanceof e) {
            return ((e) fVar).a();
        }
        return null;
    }

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
        List<f> listA = a();
        if (listA == null || listA.isEmpty()) {
            throw new IllegalArgumentException();
        }
        listA.forEach(new Consumer() { // from class: com.android.settingslib.drawer.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f1353c.c((f) obj);
            }
        });
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }
}
