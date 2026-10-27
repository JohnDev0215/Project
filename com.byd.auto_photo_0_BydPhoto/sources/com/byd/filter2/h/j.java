package com.byd.filter2.h;

import android.content.Context;
import android.graphics.Rect;
import com.byd.util.w;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile j f2852a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f2853b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final LinkedList<a> f2854c = new LinkedList<>();
    private com.byd.filter2.g.d d;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2855a = 2;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2856b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2857c;
        int d;
        int e;
        int f;
        com.byd.filter2.e.b g;
        Rect h;

        public a(int i, int i2, int i3, a aVar) {
            this.d = i;
            this.e = i2;
            this.f = i3;
            if (aVar != null) {
                this.g = aVar.g;
                this.h = aVar.h;
                this.f2856b = aVar.f2856b;
                this.f2857c = aVar.f2857c;
            }
        }

        public a(int i, int i2, a aVar) {
            this.f2856b = i;
            this.f2857c = i2;
            if (aVar != null) {
                this.g = aVar.g;
                this.h = aVar.h;
                this.e = aVar.e;
                this.d = aVar.d;
                this.f = aVar.f;
            }
        }

        public a(Rect rect, a aVar) {
            this.h = new Rect(rect);
            if (aVar != null) {
                this.f2856b = aVar.f2856b;
                this.f2857c = aVar.f2857c;
                this.d = aVar.d;
                this.e = aVar.e;
                this.f = aVar.f;
                this.g = aVar.g;
            }
        }

        public a(com.byd.filter2.e.b bVar, a aVar) {
            this.g = bVar;
            if (aVar != null) {
                this.h = aVar.h;
                this.f2856b = aVar.f2856b;
                this.f2857c = aVar.f2857c;
                this.d = aVar.d;
                this.e = aVar.e;
                this.f = aVar.f;
            }
        }

        public int a() {
            return this.e;
        }

        public int b() {
            return this.d;
        }

        public Rect c() {
            return this.h;
        }

        public int d() {
            return this.f2857c;
        }

        public com.byd.filter2.e.b e() {
            return this.g;
        }

        public int f() {
            return this.f;
        }

        public int g() {
            return this.f2856b;
        }

        public String toString() {
            return "EditInfo{editType=" + this.f2855a + ", startPos=" + this.f2856b + ", endPos=" + this.f2857c + ", contrast=" + this.d + ", brightness=" + this.e + ", saturation=" + this.f + ", filter=" + this.g + ", crop=" + this.h + '}';
        }
    }

    private j(Context context) {
        this.f2853b = context.getApplicationContext();
    }

    private void f() {
        if (this.f2854c.size() > 10) {
            w.c("reach max record , remove : " + this.f2854c.removeLast());
        }
    }

    public static j i(Context context) {
        if (f2852a == null) {
            synchronized (j.class) {
                if (f2852a == null) {
                    f2852a = new j(context);
                }
            }
        }
        return f2852a;
    }

    public void a(int i, int i2, int i3) {
        a aVarH = h();
        if (aVarH == null && i == 0 && i2 == 0 && i3 == 0) {
            return;
        }
        if (aVarH != null && aVarH.d == i && aVarH.e == i2 && aVarH.f == i3) {
            return;
        }
        a aVar = new a(i, i2, i3, aVarH);
        this.f2854c.addFirst(aVar);
        w.c("add adjust : " + aVar);
        f();
    }

    public boolean b(h hVar, long j, long j2) {
        if (this.d != null) {
            return false;
        }
        com.byd.filter2.g.d dVar = new com.byd.filter2.g.d(this.f2853b.getResources());
        this.d = dVar;
        dVar.x(j, j2);
        hVar.g(this.d);
        return true;
    }

    public void c(Rect rect) {
        a aVarH = h();
        if (aVarH != null) {
            Rect rect2 = aVarH.h;
            if (rect2 == null && rect == null) {
                return;
            }
            if (rect != null && rect.equals(rect2)) {
                w.c("crop same with pre one");
                return;
            }
        }
        a aVar = new a(rect, aVarH);
        this.f2854c.addFirst(aVar);
        w.c("add crop : " + aVar);
        f();
    }

    public void d(com.byd.filter2.e.b bVar) {
        a aVarH = h();
        if (aVarH == null && bVar == null) {
            return;
        }
        if (aVarH != null) {
            com.byd.filter2.e.b bVar2 = aVarH.g;
            if (bVar2 == null && bVar == null) {
                return;
            }
            if (bVar != null && bVar.equals(bVar2)) {
                return;
            }
        }
        a aVar = new a(bVar, aVarH);
        this.f2854c.addFirst(aVar);
        w.c("add filter : " + aVar);
        f();
    }

    public void e(int i, int i2) {
        a aVarH = h();
        if (aVarH != null && aVarH.f2856b == i && aVarH.f2857c == i2) {
            return;
        }
        a aVar = new a(i, i2, aVarH);
        this.f2854c.addFirst(aVar);
        w.c("add time clip : " + aVar);
        f();
    }

    public com.byd.filter2.g.d g() {
        return this.d;
    }

    public a h() {
        if (this.f2854c.isEmpty()) {
            return null;
        }
        return this.f2854c.getFirst();
    }

    public void j() {
        this.d = null;
        f2852a = null;
    }
}
