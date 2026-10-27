package defpackage;

import android.util.Log;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: PersistedTabData.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class l02 implements lk3 {
    public static final HashMap l = new HashMap();
    public static final HashSet m = new HashSet();
    public final Tab e;
    public final q02 f;
    public final String g;
    public long h = 0;
    public aq1<Boolean> i;
    public jb1 j;
    public boolean k;

    public l02(Tab tab, q02 q02Var, String str) {
        this.e = tab;
        this.f = q02Var;
        this.g = str;
    }

    public static <T extends l02> T d(Tab tab, Class<T> cls) {
        return (T) tab.B().b(cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [j02] */
    public static <T extends l02> void e(final Tab tab, final n02<T> n02Var, final Callback<Callback<T>> callback, final Class<T> cls, Callback<T> callback2) {
        Object obj = ThreadUtils.a;
        T tCast = cls.cast(tab.B().b(cls));
        if (tCast != null) {
            if (tCast.j()) {
                callback.onResult(new mc(tab, 3, callback2, cls));
                return;
            } else {
                PostTask.c(rh3.a, new z8(callback2, 24, tCast));
                return;
            }
        }
        final String str = String.format(Locale.ENGLISH, "%d-%s", Integer.valueOf(tab.getId()), cls.toString());
        HashMap map = l;
        if (!map.containsKey(str)) {
            map.put(str, new LinkedList());
        }
        ((List) map.get(str)).add(callback2);
        if (((List) map.get(str)).size() > 1) {
            return;
        }
        final m02 m02VarA = m02.a(cls, tab.isIncognito());
        m02VarA.f().a(tab.getId(), m02VarA.e, new Callback() { // from class: j02
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj2) {
                Callback callback3 = callback;
                Tab tab2 = tab;
                Class cls2 = cls;
                String str2 = str;
                ByteBuffer byteBuffer = (ByteBuffer) obj2;
                if (byteBuffer == null) {
                    callback3.onResult(new ct3(tab2, 1, cls2, str2));
                    return;
                }
                m02VarA.f();
                n02Var.p(byteBuffer, new i10(callback3, tab2, cls2, str2, 2));
            }
        });
    }

    public static void k(Class cls, String str, Tab tab, l02 l02Var) {
        if (tab.isDestroyed()) {
            l02Var = null;
        }
        if (l02Var != null) {
        }
        HashMap map = l;
        Iterator it = ((List) map.get(str)).iterator();
        while (it.hasNext()) {
            PostTask.c(rh3.a, new a9((Callback) it.next(), 22, l02Var));
        }
        map.remove(str);
    }

    public void a() {
        this.f.b(this.e.getId(), this.g);
    }

    public abstract boolean b(ByteBuffer byteBuffer);

    public final void c(ByteBuffer byteBuffer) {
        TraceEvent traceEventH0 = TraceEvent.H0("PersistedTabData.Deserialize", null);
        try {
            boolean zB = b(byteBuffer);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            oj2.O("Tabs.PersistedTabData.Deserialize." + i(), zB);
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.lk3
    public void destroy() {
        aq1<Boolean> aq1Var = this.i;
        if (aq1Var != null) {
            aq1Var.d(this.j);
            this.j = null;
        }
    }

    public final k02 f() {
        vi2<ByteBuffer> vi2VarG;
        try {
            vi2VarG = g();
        } catch (OutOfMemoryError e) {
            Log.e("cr_PTD", "Out of memory error when attempting to save PersistedTabData " + e.getMessage());
            vi2VarG = null;
        }
        return new k02(this, vi2VarG);
    }

    public abstract vi2<ByteBuffer> g();

    public long h() {
        return Long.MAX_VALUE;
    }

    public abstract String i();

    public boolean j() {
        if (h() == Long.MAX_VALUE) {
            return false;
        }
        long j = this.h;
        return j == 0 || h() + j < System.currentTimeMillis();
    }

    public void l() {
        aq1<Boolean> aq1Var = this.i;
        if (aq1Var == null || !aq1Var.f.booleanValue()) {
            return;
        }
        this.f.f(this.e.getId(), this.g, f());
    }
}
