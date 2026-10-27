package com.google.android.play.core.assetpacks;

import java.util.concurrent.atomic.AtomicBoolean;
import p.f13;
import p.tz2;

/* JADX INFO: loaded from: classes.dex */
public final class h {
    public static final tz2 k = new tz2("ExtractorLooper", 0);
    public final j a;
    public final g b;
    public final r c;
    public final l d;
    public final m e;
    public final n f;
    public final o g;
    public final f13 h;
    public final k i;
    public final AtomicBoolean j = new AtomicBoolean(false);

    public h(j jVar, f13 f13Var, g gVar, r rVar, l lVar, m mVar, n nVar, o oVar, k kVar) {
        this.a = jVar;
        this.h = f13Var;
        this.b = gVar;
        this.c = rVar;
        this.d = lVar;
        this.e = mVar;
        this.f = nVar;
        this.g = oVar;
        this.i = kVar;
    }

    public final void a(int i, Exception exc) {
        j jVar = this.a;
        try {
            jVar.getClass();
            jVar.c(new i(jVar, i, 0));
            jVar.c(new i(jVar, i));
        } catch (zzck unused) {
            k.c("Error during error handling: %s", exc.getMessage());
        }
    }
}
