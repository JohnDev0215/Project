package p;

import kotlinx.coroutines.channels.BufferOverflow;

/* JADX INFO: loaded from: classes.dex */
public abstract class zq extends kotlinx.coroutines.flow.internal.a {
    public final fz0 l;

    public zq(fz0 fz0Var, y10 y10Var, int i, BufferOverflow bufferOverflow) {
        super(y10Var, i, bufferOverflow);
        this.l = fz0Var;
    }

    @Override // kotlinx.coroutines.flow.internal.a
    public final String toString() {
        return "block[" + this.l + "] -> " + super.toString();
    }
}
