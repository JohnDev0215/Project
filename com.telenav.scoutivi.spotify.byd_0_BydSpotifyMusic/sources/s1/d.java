package s1;

import java.io.File;
import s1.a;

/* JADX INFO: compiled from: DiskLruCacheFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements a.InterfaceC0144a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final long f9469a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f9470b;

    /* JADX INFO: compiled from: DiskLruCacheFactory.java */
    public interface a {
        File a();
    }

    public d(a aVar, long j7) {
        this.f9469a = j7;
        this.f9470b = aVar;
    }

    @Override // s1.a.InterfaceC0144a
    public s1.a a() {
        File fileA = this.f9470b.a();
        if (fileA == null) {
            return null;
        }
        if (fileA.mkdirs() || (fileA.exists() && fileA.isDirectory())) {
            return e.c(fileA, this.f9469a);
        }
        return null;
    }
}
