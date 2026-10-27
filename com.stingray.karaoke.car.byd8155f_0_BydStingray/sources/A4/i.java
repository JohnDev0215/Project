package A4;

import i4.InterfaceC1408a;
import io.sentry.C1489n1;
import java.io.File;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
public final class i implements j {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile Object f421m;

    public /* synthetic */ i(Object obj) {
        this.l = obj;
    }

    public InterfaceC1408a a() {
        if (((InterfaceC1408a) this.f421m) == null) {
            synchronized (this) {
                try {
                    if (((InterfaceC1408a) this.f421m) == null) {
                        File cacheDir = ((P6.a) ((e7.f) this.l).f14480m).f5412m.getCacheDir();
                        i4.d dVar = null;
                        File file = cacheDir == null ? null : new File(cacheDir, "image_manager_disk_cache");
                        if (file != null && (file.isDirectory() || file.mkdirs())) {
                            dVar = new i4.d();
                            dVar.o = new C1489n1(22);
                            dVar.f15751n = file;
                            dVar.l = 262144000L;
                            dVar.f15750m = new io.sentry.internal.debugmeta.c(22);
                        }
                        this.f421m = dVar;
                    }
                    if (((InterfaceC1408a) this.f421m) == null) {
                        this.f421m = new C8.d(23);
                    }
                } finally {
                }
            }
        }
        return (InterfaceC1408a) this.f421m;
    }

    @Override // A4.j
    public Object get() {
        if (this.f421m == null) {
            synchronized (this) {
                try {
                    if (this.f421m == null) {
                        Object obj = ((j) this.l).get();
                        h.c(obj, "Argument must not be null");
                        this.f421m = obj;
                    }
                } finally {
                }
            }
        }
        return this.f421m;
    }

    public i() {
        this.l = new CopyOnWriteArraySet();
    }
}
