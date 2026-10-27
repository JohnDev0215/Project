package p;

import java.io.Closeable;
import kotlin.text.Regex;

/* JADX INFO: loaded from: classes.dex */
public final class ca0 implements Closeable {
    public final ba0 c;
    public boolean e;
    public final /* synthetic */ coil.disk.a k;

    public ca0(coil.disk.a aVar, ba0 ba0Var) {
        this.k = aVar;
        this.c = ba0Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.e) {
            return;
        }
        this.e = true;
        coil.disk.a aVar = this.k;
        synchronized (aVar) {
            ba0 ba0Var = this.c;
            int i = ba0Var.h - 1;
            ba0Var.h = i;
            if (i == 0 && ba0Var.f) {
                Regex regex = coil.disk.a.y;
                aVar.H0(ba0Var);
            }
        }
    }
}
