package defpackage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.util.SparseBooleanArray;
import defpackage.s33;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.customtabs.BaseCustomTabActivity;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: CustomTabTabPersistencePolicy.java */
/* JADX INFO: loaded from: classes.dex */
public final class h40 implements q23 {
    public static final Object f = new Object();
    public static i9<Void> g;
    public final int a;
    public final boolean b;
    public f40 c;
    public oi2 d;
    public boolean e;

    /* JADX INFO: compiled from: CustomTabTabPersistencePolicy.java */
    public class a extends i9<Void> {
        public final Callback<List<String>> h;
        public HashSet i;
        public ArrayList j;
        public HashMap k;

        public a(a33 a33Var) {
            this.h = a33Var;
        }

        @Override // defpackage.i9
        public final Void b() throws Throwable {
            Exception e;
            DataInputStream dataInputStream;
            h40 h40Var = h40.this;
            DataInputStream dataInputStream2 = null;
            if (!h40Var.e) {
                this.k = new HashMap();
                this.i = new HashSet();
                File[] fileArrListFiles = h40Var.n().listFiles();
                if (fileArrListFiles != null) {
                    HashSet hashSet = new HashSet();
                    HashSet hashSet2 = new HashSet();
                    ArrayList arrayList = new ArrayList();
                    for (File file : fileArrListFiles) {
                        String name = file.getName();
                        Object obj = z23.A;
                        if (name.startsWith("tab_state") && !name.endsWith(".new")) {
                            arrayList.add(file);
                            SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
                            this.k.put(file, sparseBooleanArray);
                            try {
                                dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
                            } catch (Exception e2) {
                                e = e2;
                                dataInputStream = null;
                            } catch (Throwable th) {
                                th = th;
                                dataInputStream = dataInputStream2;
                                a8.s(dataInputStream);
                                throw th;
                            }
                            try {
                                z23.m(dataInputStream, null, sparseBooleanArray);
                            } catch (Exception e3) {
                                e = e3;
                                try {
                                    Log.e("cr_tabmodel", "Unable to read state for " + file.getName() + ": " + e);
                                } catch (Throwable th2) {
                                    th = th2;
                                    dataInputStream2 = dataInputStream;
                                    dataInputStream = dataInputStream2;
                                    a8.s(dataInputStream);
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                a8.s(dataInputStream);
                                throw th;
                            }
                            a8.s(dataInputStream);
                            for (int i = 0; i < sparseBooleanArray.size(); i++) {
                                hashSet2.add(Integer.valueOf(sparseBooleanArray.keyAt(i)));
                            }
                        } else {
                            Pair<Integer, Boolean> pairB = t33.b(file.getName());
                            if (pairB != null) {
                                hashSet.add((Integer) pairB.first);
                            }
                        }
                    }
                    this.i.addAll(hashSet);
                    this.i.removeAll(hashSet2);
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    Collections.sort(arrayList, new g40());
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        File file2 = (File) arrayList.get(i2);
                        long jLastModified = jCurrentTimeMillis - file2.lastModified();
                        if (i2 >= 30 || jLastModified >= 2592000000L) {
                            arrayList2.add(file2);
                        }
                    }
                    this.j = arrayList2;
                }
            }
            return null;
        }

        @Override // defpackage.i9
        public final void k(Void r1) {
            synchronized (h40.f) {
                h40.g = null;
            }
        }

        @Override // defpackage.i9
        public final void l(Void r11) {
            d23 d23VarG2;
            ArrayList arrayList = new ArrayList();
            if (h40.this.e) {
                this.h.onResult(arrayList);
                return;
            }
            if (this.i.isEmpty() && this.j.isEmpty()) {
                this.h.onResult(arrayList);
                return;
            }
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            Object obj = ThreadUtils.a;
            for (Activity activity : ApplicationStatus.b()) {
                if (activity instanceof BaseCustomTabActivity) {
                    BaseCustomTabActivity baseCustomTabActivity = (BaseCustomTabActivity) activity;
                    if (baseCustomTabActivity != null && (d23VarG2 = baseCustomTabActivity.G2()) != null) {
                        ArrayList arrayList2 = d23VarG2.a;
                        for (int i = 0; i < arrayList2.size(); i++) {
                            TabModel tabModel = (TabModel) arrayList2.get(i);
                            for (int i2 = 0; i2 < tabModel.getCount(); i2++) {
                                hashSet.add(Integer.valueOf(tabModel.getTabAt(i2).getId()));
                            }
                        }
                    }
                    hashSet2.add(Integer.valueOf(activity.getTaskId()));
                }
            }
            for (Integer num : this.i) {
                if (!hashSet.contains(num)) {
                    arrayList.add(t33.a(num.intValue(), false));
                }
            }
            for (int i3 = 0; i3 < this.j.size(); i3++) {
                File file = (File) this.j.get(i3);
                String name = file.getName();
                Object obj2 = z23.A;
                try {
                    if (!hashSet2.contains(Integer.valueOf(Integer.parseInt(name.substring(9))))) {
                        arrayList.add(file.getName());
                        SparseBooleanArray sparseBooleanArray = (SparseBooleanArray) this.k.get(file);
                        if (sparseBooleanArray != null) {
                            for (int i4 = 0; i4 < sparseBooleanArray.size(); i4++) {
                                arrayList.add(t33.a(sparseBooleanArray.keyAt(i4), false));
                            }
                        }
                    }
                } catch (NumberFormatException unused) {
                }
            }
            this.h.onResult(arrayList);
            synchronized (h40.f) {
                h40.g = null;
            }
        }
    }

    public h40(Activity activity, nw2<Bundle> nw2Var) {
        this.a = activity.getTaskId();
        this.b = nw2Var.get() != null;
    }

    @Override // defpackage.q23
    public final void a(a33 a33Var) {
        synchronized (f) {
            i9<Void> i9Var = g;
            if (i9Var != null) {
                i9Var.a(true);
            }
            a aVar = new a(a33Var);
            g = aVar;
            aVar.c(i9.e);
        }
    }

    @Override // defpackage.q23
    public final String d() {
        String string = Integer.toString(this.a);
        Object obj = z23.A;
        return C0175b.j("tab_state", string);
    }

    @Override // defpackage.q23
    public final void destroy() {
        this.e = true;
    }

    @Override // defpackage.q23
    public final void e() {
        f40 f40Var = this.c;
        if (f40Var == null) {
            return;
        }
        try {
            f40Var.g();
        } catch (InterruptedException | ExecutionException unused) {
        }
    }

    @Override // defpackage.q23
    public final boolean f() {
        return false;
    }

    @Override // defpackage.q23
    public final boolean g(oi2 oi2Var) {
        f40 f40Var = new f40(this);
        f40Var.d(oi2Var);
        this.c = f40Var;
        return true;
    }

    @Override // defpackage.q23
    public final List<String> j() {
        return null;
    }

    @Override // defpackage.q23
    public final void k(oi2 oi2Var) {
        this.d = oi2Var;
    }

    @Override // defpackage.q23
    public final boolean l() {
        return false;
    }

    @Override // defpackage.q23
    public final File n() {
        synchronized (s33.b) {
            if (s33.d == null) {
                s33.d = new File(s33.a.a, "custom_tabs");
                ku2 ku2VarC = ku2.C();
                try {
                    if (!s33.d.exists() && !s33.d.mkdirs()) {
                        Log.e("cr_tabpersistence", "Failed to create state folder: " + s33.d);
                    }
                    ku2VarC.close();
                } catch (Throwable th) {
                    try {
                        ku2VarC.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            }
        }
        return s33.d;
    }

    @Override // defpackage.q23
    public final void o() {
        synchronized (f) {
            i9<Void> i9Var = g;
            if (i9Var != null) {
                i9Var.a(true);
            }
        }
    }

    @Override // defpackage.q23
    public final void b(TabContentManager tabContentManager) {
    }

    @Override // defpackage.q23
    public final void c(boolean z) {
    }

    @Override // defpackage.q23
    public final void h(int i) {
    }
}
