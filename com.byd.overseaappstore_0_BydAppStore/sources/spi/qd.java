package spi;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class qd implements rd<Object> {
    public volatile Object a;
    public final /* synthetic */ rd b;

    public qd(rd rdVar) {
        this.b = rdVar;
    }

    @Override // spi.rd
    public final Object get() {
        if (this.a == null) {
            synchronized (this) {
                if (this.a == null) {
                    Object obj = this.b.get();
                    Objects.requireNonNull(obj, "Argument must not be null");
                    this.a = obj;
                }
            }
        }
        return this.a;
    }
}
