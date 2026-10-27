package com.byd.cleardata;

import com.byd.cleardata.d.d;
import com.byd.cleardata.d.e;
import com.byd.cleardata.i.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile b f2571a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private e f2572b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<d> f2573c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<d> f2574d;
    private int e;
    private int f;
    private int g;
    private int h;
    private String i;
    private com.byd.cleardata.f.c.b[] j = new com.byd.cleardata.f.c.b[1];

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.e = l.a(FileApplication.a(), "sort_index", 0);
        }
    }

    private b() {
    }

    public static b h() {
        if (f2571a == null) {
            synchronized (b.class) {
                if (f2571a == null) {
                    f2571a = new b();
                }
            }
        }
        return f2571a;
    }

    public void A(int i) {
        this.h = i;
        w(i == 2 ? com.byd.cleardata.a.f().j(this.g) : i == 6 ? "/" : null);
    }

    public void B() {
        if (this.f2572b == e.CHOOSING) {
            y(e.NORMAL);
            d();
        }
    }

    public void C() {
        y(e.NORMAL);
        d();
    }

    public void D(int i) {
        this.g = i;
        if (this.h == 2) {
            this.i = com.byd.cleardata.a.f().k(this.g);
        }
    }

    public void E(int i) {
        this.e = i;
    }

    public void F() {
        this.f = this.f == 0 ? 1 : 0;
    }

    public void b(List<d> list) {
        this.f2574d.clear();
        if (list != null) {
            this.f2574d.addAll(list);
        }
    }

    public void c(d dVar) {
        if (!dVar.J()) {
            this.f2574d.remove(dVar);
        } else {
            if (this.f2574d.contains(dVar)) {
                return;
            }
            this.f2574d.add(dVar);
        }
    }

    public void d() {
        Iterator<d> it = this.f2574d.iterator();
        while (it.hasNext()) {
            it.next().P(false);
        }
        this.f2574d.clear();
    }

    public String e() {
        return this.i;
    }

    public List<d> f() {
        return this.f2573c;
    }

    public e g() {
        return this.f2572b;
    }

    public int i() {
        return this.f;
    }

    public int j() {
        return this.h;
    }

    public int k() {
        return this.g;
    }

    public List<d> l() {
        return this.f2574d;
    }

    public int m() {
        return this.f2574d.size();
    }

    public d n() {
        if (this.f2574d.size() == 1) {
            return this.f2574d.get(0);
        }
        return null;
    }

    public List<String> o() {
        ArrayList arrayList = new ArrayList();
        Iterator<d> it = this.f2574d.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().E());
        }
        return arrayList;
    }

    public int p() {
        return this.e;
    }

    public void q() {
        this.h = 1;
        this.g = 0;
        this.f2572b = e.NORMAL;
        this.f2573c = new ArrayList();
        this.f2574d = new ArrayList();
        this.f = 1;
        com.byd.cleardata.a.f().o();
        w(com.byd.cleardata.a.f().k(this.g));
        ThreadManager.h().j(new a());
    }

    public boolean r() {
        return this.f2572b == e.CHOOSING;
    }

    public boolean s() {
        return this.h == 6;
    }

    public boolean t() {
        return this.f2572b == e.NORMAL;
    }

    public String toString() {
        return "FileContext{mFileState=" + this.f2572b + ", mSelectedFiles=" + this.f2574d + ", mRootIndex=" + this.g + ", mMenuIndex=" + this.h + ", mCurrPath='" + this.i + "'}";
    }

    public com.byd.cleardata.f.c.b u() {
        com.byd.cleardata.f.c.b bVar = this.j[0];
        StringBuilder sb = new StringBuilder();
        sb.append("restorePresenter ");
        sb.append(bVar != null);
        com.byd.cleardata.i.c.b(sb.toString());
        this.j[0] = null;
        return bVar;
    }

    public void v(com.byd.cleardata.f.c.b bVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("savePresenter ");
        sb.append(bVar != null);
        com.byd.cleardata.i.c.b(sb.toString());
        this.j[0] = bVar;
    }

    public void w(String str) {
        this.i = str;
    }

    public void x(List<d> list) {
        this.f2573c = list;
    }

    public void y(e eVar) {
        com.byd.cleardata.i.c.b("fileState = " + eVar);
        this.f2572b = eVar;
        org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.d());
    }

    public void z(e eVar) {
        this.f2572b = eVar;
    }
}
