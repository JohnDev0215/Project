package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.util.Map;
import org.chromium.components.policy.a;

/* JADX INFO: compiled from: PolicyCacheProvider.java */
/* JADX INFO: loaded from: classes3.dex */
public final class m22 extends p22 {
    public Bundle c;

    @Override // defpackage.p22
    public final void b() {
        SharedPreferences sharedPreferences;
        ku2 ku2VarT;
        Bundle bundle = this.c;
        if (bundle != null) {
            a(bundle);
            return;
        }
        Bundle bundle2 = new Bundle();
        if (a.c == null) {
            a.c = new a();
        }
        a aVar = a.c;
        aVar.b.getClass();
        Map<String, ?> all = null;
        if (aVar.a != null) {
            sharedPreferences = aVar.a;
        } else {
            Context context = xz.a;
            if (context == null) {
                sharedPreferences = null;
            } else {
                ku2VarT = ku2.t();
                try {
                    aVar.a = context.getSharedPreferences("Components.Policy", 0);
                    ku2VarT.close();
                    sharedPreferences = aVar.a;
                } finally {
                }
            }
        }
        if (sharedPreferences != null) {
            ku2VarT = ku2.t();
            try {
                all = sharedPreferences.getAll();
            } finally {
                try {
                    ku2VarT.close();
                } catch (Throwable unused) {
                }
            }
        }
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Integer) {
                bundle2.putInt(key, ((Integer) value).intValue());
            } else if (value instanceof Boolean) {
                bundle2.putBoolean(key, ((Boolean) value).booleanValue());
            } else if (value instanceof String) {
                bundle2.putString(key, (String) value);
            }
        }
        this.c = bundle2;
        a(bundle2);
    }
}
