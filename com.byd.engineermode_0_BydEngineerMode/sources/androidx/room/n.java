package androidx.room;

import b.m.a.c;
import java.io.File;

/* JADX INFO: compiled from: SQLiteCopyOpenHelperFactory.java */
/* JADX INFO: loaded from: classes.dex */
class n implements c.InterfaceC0049c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f1364a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final File f1365b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.InterfaceC0049c f1366c;

    n(String str, File file, c.InterfaceC0049c interfaceC0049c) {
        this.f1364a = str;
        this.f1365b = file;
        this.f1366c = interfaceC0049c;
    }

    @Override // b.m.a.c.InterfaceC0049c
    public b.m.a.c a(c.b bVar) {
        return new m(bVar.f1904a, this.f1364a, this.f1365b, bVar.f1906c.f1903a, this.f1366c.a(bVar));
    }
}
