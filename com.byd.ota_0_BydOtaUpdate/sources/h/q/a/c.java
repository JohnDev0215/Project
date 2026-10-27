package h.q.a;

import c.b.a.q;
import e.b0;
import h.e;
import java.io.IOException;

/* JADX INFO: compiled from: GsonResponseBodyConverter.java */
/* JADX INFO: loaded from: classes.dex */
public final class c<T> implements e<b0, T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.b.a.e f5884a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q<T> f5885b;

    public c(c.b.a.e eVar, q<T> qVar) {
        this.f5884a = eVar;
        this.f5885b = qVar;
    }

    @Override // h.e
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public T a(b0 b0Var) throws IOException {
        try {
            return this.f5885b.b(this.f5884a.q(b0Var.d()));
        } finally {
            b0Var.close();
        }
    }
}
