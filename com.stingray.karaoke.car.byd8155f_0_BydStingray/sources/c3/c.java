package C3;

import A7.A;
import A7.m;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import p1.C2078f;
import r9.H;
import r9.J;
import r9.o;
import r9.u;
import r9.v;
import r9.z;

/* JADX INFO: loaded from: classes.dex */
public final class c extends o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final v f978b;

    public c(v vVar) {
        m.f(vVar, "delegate");
        this.f978b = vVar;
    }

    @Override // r9.o
    public final void b(z zVar) throws IOException {
        this.f978b.b(zVar);
    }

    @Override // r9.o
    public final void c(z zVar) throws IOException {
        m.f(zVar, "path");
        this.f978b.c(zVar);
    }

    @Override // r9.o
    public final List f(z zVar) throws IOException {
        m.f(zVar, "dir");
        List listF = this.f978b.f(zVar);
        ArrayList arrayList = new ArrayList();
        for (z zVar2 : (ArrayList) listF) {
            m.f(zVar2, "path");
            arrayList.add(zVar2);
        }
        if (arrayList.size() > 1) {
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    @Override // r9.o
    public final C2078f h(z zVar) {
        m.f(zVar, "path");
        C2078f c2078fH = this.f978b.h(zVar);
        if (c2078fH == null) {
            return null;
        }
        z zVar2 = (z) c2078fH.d;
        if (zVar2 == null) {
            return c2078fH;
        }
        Map map = (Map) c2078fH.f20659i;
        m.f(map, "extras");
        return new C2078f(c2078fH.f20654b, c2078fH.c, zVar2, (Long) c2078fH.f20655e, (Long) c2078fH.f20656f, (Long) c2078fH.f20657g, (Long) c2078fH.f20658h, map);
    }

    @Override // r9.o
    public final u i(z zVar) {
        return this.f978b.i(zVar);
    }

    @Override // r9.o
    public final H j(z zVar) {
        z zVarC = zVar.c();
        if (zVarC != null) {
            a(zVarC);
        }
        return this.f978b.j(zVar);
    }

    @Override // r9.o
    public final J k(z zVar) {
        m.f(zVar, "file");
        return this.f978b.k(zVar);
    }

    public final void l(z zVar, z zVar2) throws IOException {
        m.f(zVar, BYDAutoMultimediaDevice.PARAM_SOURCE);
        m.f(zVar2, "target");
        this.f978b.l(zVar, zVar2);
    }

    public final String toString() {
        return A.f443a.b(c.class).b() + '(' + this.f978b + ')';
    }
}
