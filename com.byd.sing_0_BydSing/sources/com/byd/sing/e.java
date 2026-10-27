package com.byd.sing;

import com.android.volley.BuildConfig;
import com.byd.sing.view.a.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: FrameManager.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f3242a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<String, com.byd.sing.view.a.g> f3243b = new HashMap();

    /* JADX INFO: compiled from: FrameManager.java */
    class a implements g.a {
        a() {
        }

        @Override // com.byd.sing.view.a.g.a
        public void a(int i) {
            new com.byd.sing.view.a.j().p();
        }
    }

    public static void h() {
        if (!f3242a) {
            com.byd.sing.view.a.i iVar = new com.byd.sing.view.a.i(1);
            iVar.o(new a());
            iVar.p();
        }
        f3242a = true;
    }

    public static void i() {
        if (!f3242a) {
            new com.byd.sing.view.a.h().V();
        }
        f3242a = true;
    }

    public void a(String str, com.byd.sing.view.a.g gVar) {
        if (f(str)) {
            return;
        }
        this.f3243b.put(str, gVar);
    }

    public void b() {
        c(null);
    }

    public void c(String str) {
        if (this.f3243b.isEmpty()) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList(this.f3243b.keySet());
        com.byd.sing.a.a("size = " + arrayList.size());
        for (String str2 : arrayList) {
            if (str2 != null && !str2.equals(str)) {
                com.byd.sing.a.a(BuildConfig.FLAVOR);
                com.byd.sing.view.a.g gVarRemove = this.f3243b.remove(str2);
                if (gVarRemove != null) {
                    gVarRemove.a();
                }
            }
        }
    }

    public Map<String, com.byd.sing.view.a.g> d() {
        return this.f3243b;
    }

    public com.byd.sing.view.a.g e(String str) {
        return this.f3243b.get(str);
    }

    public boolean f(String str) {
        com.byd.sing.view.a.g gVar = this.f3243b.get(str);
        return gVar != null && gVar.k();
    }

    public void g(String str) {
        this.f3243b.remove(str);
    }
}
