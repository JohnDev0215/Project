package defpackage;

import android.content.SharedPreferences;
import android.os.StrictMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: Ch, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0043Ch implements InterfaceC1173mh {
    public static final Map f = new C0971j1();
    public final SharedPreferences a;
    public volatile Map d;
    public final SharedPreferences.OnSharedPreferenceChangeListener b = new SharedPreferences.OnSharedPreferenceChangeListener(this) { // from class: Bh
        public final C0043Ch a;

        {
            this.a = this;
        }

        @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
        public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            C0043Ch c0043Ch = this.a;
            synchronized (c0043Ch.c) {
                c0043Ch.d = null;
                AbstractC1667vh.i.incrementAndGet();
            }
            synchronized (c0043Ch) {
                Iterator it = c0043Ch.e.iterator();
                while (it.hasNext()) {
                    ((InterfaceC1228nh) it.next()).a();
                }
            }
        }
    };
    public final Object c = new Object();
    public final List e = new ArrayList();

    public C0043Ch(SharedPreferences sharedPreferences) {
        this.a = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(this.b);
    }

    public static synchronized void b() {
        Object obj = f;
        synchronized (C0043Ch.class) {
            for (C0043Ch c0043Ch : (C1523t1) ((C0971j1) obj).values()) {
                c0043Ch.a.unregisterOnSharedPreferenceChangeListener(c0043Ch.b);
            }
            ((C1633v1) obj).clear();
        }
    }

    @Override // defpackage.InterfaceC1173mh
    public final Object a(String str) {
        Map<String, ?> map = this.d;
        if (map == null) {
            synchronized (this.c) {
                map = this.d;
                if (map == null) {
                    StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        Map<String, ?> all = this.a.getAll();
                        this.d = all;
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                        map = all;
                    } catch (Throwable th) {
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                        throw th;
                    }
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }
}
