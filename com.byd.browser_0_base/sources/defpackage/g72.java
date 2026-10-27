package defpackage;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: PseudoTab.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g72 {
    public static final LinkedHashMap c = new LinkedHashMap();
    public static final Object d = new Object();
    public static boolean e;
    public static ArrayList f;
    public static g72 g;
    public final Integer a;
    public final WeakReference<Tab> b;

    /* JADX INFO: compiled from: PseudoTab.java */
    public interface a {
        String e(Context context, g72 g72Var);
    }

    public g72(int i) {
        this.a = Integer.valueOf(i);
        this.b = null;
        c.put(Integer.valueOf(c()), this);
    }

    public static g72 a(Tab tab) {
        synchronized (d) {
            g72 g72Var = (g72) c.get(Integer.valueOf(tab.getId()));
            if (g72Var != null) {
                if (g72Var.g() != null) {
                    if (g72Var.g() == tab) {
                        return g72Var;
                    }
                    return new g72(tab);
                }
            }
            return new g72(tab);
        }
    }

    public static g72 b(int i) {
        synchronized (d) {
            g72 g72Var = (g72) c.get(Integer.valueOf(i));
            if (g72Var != null) {
                return g72Var;
            }
            return new g72(i);
        }
    }

    public static ArrayList d(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a((Tab) it.next()));
        }
        return arrayList;
    }

    public static ArrayList e(Context context, g72 g72Var, y13 y13Var) {
        List<Tab> listB;
        synchronized (d) {
            int iC = g72Var.c();
            if (y13Var.t()) {
                q13 q13VarU = y13Var.u();
                List<Tab> listB2 = q13VarU.d(false).B(iC);
                listB = listB2.size() > 0 ? listB2 : q13VarU.d(true).B(iC);
            } else {
                listB = null;
            }
            if (listB != null) {
                return d(listB);
            }
            ArrayList arrayList = new ArrayList();
            int iF = g72Var.f();
            if (iF != -1 && oj2.D(context)) {
                Iterator it = c.keySet().iterator();
                while (it.hasNext()) {
                    g72 g72Var2 = (g72) c.get((Integer) it.next());
                    if (g72Var2.f() != -1 && g72Var2.f() == iF) {
                        arrayList.add(g72Var2);
                    }
                }
                return arrayList;
            }
            arrayList.add(g72Var);
            return arrayList;
        }
    }

    public static void l(Context context) throws Throwable {
        FileInputStream fileInputStream;
        if (e) {
            return;
        }
        e = true;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        File file = new File(s33.a(), v53.q(0));
        if (!file.exists()) {
            Log.i("cr_PseudoTab", "State file does not exist.");
            return;
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            byte[] bArr = new byte[(int) file.length()];
            fileInputStream.read(bArr);
            a8.s(fileInputStream);
            Log.i("cr_PseudoTab", "Finished fetching tab list.");
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            HashSet hashSet = new HashSet();
            f = new ArrayList();
            try {
                z23.m(dataInputStream, new i40(context, hashSet), null);
                Objects.toString(f);
                fa1.g("PseudoTab", "readAllPseudoTabsFromStateFile() took %dms", Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime));
            } catch (IOException e3) {
                Log.e("cr_PseudoTab", "Could not read state file.", e3);
            }
        } catch (IOException e4) {
            e = e4;
            fileInputStream2 = fileInputStream;
            Log.e("cr_PseudoTab", "Could not read state file.", e);
            a8.s(fileInputStream2);
        } catch (Throwable th2) {
            th = th2;
            a8.s(fileInputStream);
            throw th;
        }
    }

    public final int c() {
        return this.a.intValue();
    }

    public final int f() {
        WeakReference<Tab> weakReference = this.b;
        if (weakReference != null && weakReference.get() != null && weakReference.get().isInitialized()) {
            return CriticalPersistedTabData.m(weakReference.get()).q;
        }
        return ky2.c().getInt(ky2.b(this.a.intValue()), -1);
    }

    @Deprecated
    public final Tab g() {
        WeakReference<Tab> weakReference = this.b;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public final long h() {
        WeakReference<Tab> weakReference = this.b;
        if (weakReference != null && weakReference.get() != null && weakReference.get().isInitialized()) {
            return CriticalPersistedTabData.m(weakReference.get()).r;
        }
        return ky2.c().getLong(ky2.d(this.a.intValue()), -1L);
    }

    public final String i() {
        WeakReference<Tab> weakReference = this.b;
        if (weakReference != null && weakReference.get() != null && weakReference.get().isInitialized()) {
            return weakReference.get().getTitle();
        }
        return ky2.c().getString(ky2.e(this.a.intValue()), "");
    }

    public final GURL j() {
        WeakReference<Tab> weakReference = this.b;
        if (weakReference != null && weakReference.get() != null && weakReference.get().isInitialized()) {
            return weakReference.get().getUrl();
        }
        int iIntValue = this.a.intValue();
        String string = ky2.c().getString(ky2.f(iIntValue), "");
        if (!string.isEmpty()) {
            return GURL.a(string);
        }
        return new GURL(ky2.c().getString(iIntValue + "_url", ""));
    }

    public final boolean k() {
        WeakReference<Tab> weakReference = this.b;
        if (weakReference == null || weakReference.get() == null) {
            return false;
        }
        return weakReference.get().isIncognito();
    }

    public final String toString() {
        return "Tab " + this.a;
    }

    public g72(Tab tab) {
        this.a = Integer.valueOf(tab.getId());
        this.b = new WeakReference<>(tab);
        c.put(Integer.valueOf(c()), this);
    }
}
