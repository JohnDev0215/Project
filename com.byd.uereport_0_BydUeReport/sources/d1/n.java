package d1;

import h1.c;
import java.io.File;

/* JADX INFO: compiled from: SQLiteCopyOpenHelperFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements c.InterfaceC0050c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4421a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final File f4422b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.InterfaceC0050c f4423c;

    public n(String str, File file, c.InterfaceC0050c interfaceC0050c) {
        this.f4421a = str;
        this.f4422b = file;
        this.f4423c = interfaceC0050c;
    }

    @Override // h1.c.InterfaceC0050c
    public h1.c a(c.b bVar) {
        return new m(bVar.f5123a, this.f4421a, this.f4422b, bVar.f5125c.f5122a, this.f4423c.a(bVar));
    }
}
