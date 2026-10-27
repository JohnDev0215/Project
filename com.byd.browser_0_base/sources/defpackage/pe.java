package defpackage;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: BigBufferUtil.java */
/* JADX INFO: loaded from: classes3.dex */
public final class pe implements AutoCloseable {
    public final jl2 e;
    public final ByteBuffer f;

    public pe(jl2 jl2Var, ByteBuffer byteBuffer) {
        this.e = jl2Var;
        this.f = byteBuffer;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        jl2 jl2Var = this.e;
        if (jl2Var != null) {
            jl2Var.D(this.f);
        }
    }
}
