package com.bumptech.glide;

import android.content.Context;
import android.content.ContextWrapper;
import java.util.List;
import u.C2465e;
import y4.C2742b;

/* JADX INFO: loaded from: classes.dex */
public final class e extends ContextWrapper {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final a f12675k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F.l f12676a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final A4.i f12677b;
    public final m6.b c;
    public final C8.d d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f12678e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C2465e f12679f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final g4.l f12680g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final S6.e f12681h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12682i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public w4.f f12683j;

    static {
        a aVar = new a();
        aVar.l = C2742b.f24119a;
        f12675k = aVar;
    }

    public e(Context context, F.l lVar, t4.e eVar, m6.b bVar, C8.d dVar, C2465e c2465e, List list, g4.l lVar2, S6.e eVar2) {
        super(context.getApplicationContext());
        this.f12676a = lVar;
        this.c = bVar;
        this.d = dVar;
        this.f12678e = list;
        this.f12679f = c2465e;
        this.f12680g = lVar2;
        this.f12681h = eVar2;
        this.f12682i = 4;
        this.f12677b = new A4.i(eVar);
    }

    public final h a() {
        return (h) this.f12677b.get();
    }
}
