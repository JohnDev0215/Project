package c.a.i.a;

import android.app.Dialog;
import android.content.Context;
import c.a.i.a.b;
import c.a.i.a.g;
import c.a.i.a.h;

/* JADX INFO: compiled from: DialogBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static Dialog a(Context context) {
        return i(context, c.a.e.e.f2647e);
    }

    public static Dialog b(Context context) {
        return i(context, c.a.e.e.f2648f);
    }

    public static Dialog c(Context context, h.c cVar) {
        h hVar = new h(context);
        hVar.e(c.a.e.e.f2646d);
        hVar.d(c.a.e.e.f2645c);
        hVar.c(c.a.e.e.f2643a);
        hVar.b(cVar);
        return hVar;
    }

    public static Dialog d(Context context, b.c cVar) {
        b bVar = new b(context);
        bVar.b(cVar);
        return bVar;
    }

    public static Dialog e(Context context) {
        d dVar = new d(context);
        dVar.b(c.a.e.e.k);
        dVar.a(c.a.e.e.f2650h);
        return dVar;
    }

    public static Dialog f(Context context) {
        e eVar = new e(context);
        eVar.c(c.a.e.e.k);
        eVar.a(c.a.e.e.f2649g);
        return eVar;
    }

    public static Dialog g(Context context) {
        e eVar = new e(context);
        eVar.c(c.a.e.e.k);
        eVar.a(c.a.e.e.j);
        return eVar;
    }

    public static Dialog h(Context context) {
        e eVar = new e(context);
        eVar.c(c.a.e.e.k);
        eVar.a(c.a.e.e.p);
        return eVar;
    }

    public static Dialog i(Context context, int i2) {
        e eVar = new e(context);
        eVar.a(i2);
        return eVar;
    }

    public static Dialog j(Context context) {
        return i(context, c.a.e.e.n);
    }

    public static Dialog k(Context context, g.c cVar, int i2) {
        g gVar = new g(context);
        gVar.b(cVar);
        gVar.c(i2);
        return gVar;
    }

    public static Dialog l(Context context) {
        d dVar = new d(context);
        dVar.b(c.a.e.e.k);
        dVar.a(c.a.e.e.u);
        return dVar;
    }

    public static Dialog m(Context context) {
        return i(context, c.a.e.e.q);
    }

    public static Dialog n(Context context) {
        e eVar = new e(context);
        eVar.c(c.a.e.e.k);
        eVar.a(c.a.e.e.s);
        return eVar;
    }

    public static Dialog o(Context context) {
        d dVar = new d(context);
        dVar.b(c.a.e.e.k);
        dVar.a(c.a.e.e.t);
        return dVar;
    }

    public static Dialog p(Context context, String str) {
        e eVar = new e(context);
        eVar.c(c.a.e.e.v);
        eVar.b(String.format(context.getResources().getString(c.a.e.e.w), str));
        return eVar;
    }

    public static Dialog q(Context context) {
        d dVar = new d(context);
        dVar.b(c.a.e.e.k);
        dVar.a(c.a.e.e.x);
        return dVar;
    }
}
