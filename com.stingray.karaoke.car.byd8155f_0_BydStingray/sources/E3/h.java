package E3;

import android.webkit.MimeTypeMap;
import java.io.File;
import n0.C1886o;
import q7.InterfaceC2189c;
import r9.o;
import r9.z;

/* JADX INFO: loaded from: classes.dex */
public final class h implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final File f1728a;

    public h(File file) {
        this.f1728a = file;
    }

    @Override // E3.g
    public final Object a(InterfaceC2189c interfaceC2189c) {
        String str = z.f21951m;
        File file = this.f1728a;
        B3.l lVar = new B3.l(C1886o.j(file), o.f21939a, null, null);
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        String name = file.getName();
        A7.m.e(name, "getName(...)");
        return new m(lVar, singleton.getMimeTypeFromExtension(Q8.h.a1('.', name, "")), B3.f.f625n);
    }
}
