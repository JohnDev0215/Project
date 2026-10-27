package io.objectbox;

import java.io.Closeable;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class BoxStore implements Closeable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Set<String> f2599c = new HashSet();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2600a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2601b;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            boolean z = this.f2600a;
            if (this.f2600a) {
                if (z) {
                    return;
                }
                synchronized (f2599c) {
                    f2599c.remove(null);
                    f2599c.notifyAll();
                }
                return;
            }
            if (this.f2601b != 0) {
                try {
                    synchronized (this) {
                        if (this.f2601b == 0) {
                            throw new IllegalStateException("ObjectBrowser has not been started before");
                        }
                        this.f2601b = 0;
                        nativeStopObjectBrowser(0L);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            this.f2600a = true;
            throw null;
        }
    }

    public void finalize() throws Throwable {
        close();
        super.finalize();
    }

    public final native boolean nativeStopObjectBrowser(long j);
}
