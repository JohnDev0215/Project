package u9;

import n3.z;
import t9.d;
import w9.e;
import w9.t;
import w9.u;

/* JADX INFO: loaded from: classes.dex */
public final class a implements z9.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final char f22937a;

    public a(char c) {
        this.f22937a = c;
    }

    @Override // z9.a
    public final char a() {
        return this.f22937a;
    }

    @Override // z9.a
    public final void b(u uVar, u uVar2, int i7) {
        String.valueOf(this.f22937a);
        z eVar = i7 == 1 ? new e() : new t();
        z zVar = (z) uVar.f19923f;
        while (zVar != null && zVar != uVar2) {
            z zVar2 = (z) zVar.f19923f;
            eVar.b(zVar);
            zVar = zVar2;
        }
        eVar.h();
        z zVar3 = (z) uVar.f19923f;
        eVar.f19923f = zVar3;
        if (zVar3 != null) {
            zVar3.f19922e = eVar;
        }
        eVar.f19922e = uVar;
        uVar.f19923f = eVar;
        z zVar4 = (z) uVar.f19921b;
        eVar.f19921b = zVar4;
        if (((z) eVar.f19923f) == null) {
            zVar4.d = eVar;
        }
    }

    @Override // z9.a
    public final int c() {
        return 1;
    }

    @Override // z9.a
    public final int d(d dVar, d dVar2) {
        if (dVar.d || dVar2.c) {
            int i7 = dVar2.f22521h;
            if (i7 % 3 != 0 && (dVar.f22521h + i7) % 3 == 0) {
                return 0;
            }
        }
        return (dVar.f22520g < 2 || dVar2.f22520g < 2) ? 1 : 2;
    }

    @Override // z9.a
    public final char e() {
        return this.f22937a;
    }
}
