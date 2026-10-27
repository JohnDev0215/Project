package kotlinx.coroutines.flow.internal;

import java.util.ArrayList;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.channels.BufferOverflow;
import p.c00;
import p.fv0;
import p.hf2;
import p.lq2;
import p.o72;
import p.ou;
import p.q30;
import p.rz0;
import p.sx1;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements rz0 {
    public final y10 c;
    public final int e;
    public final BufferOverflow k;

    public a(y10 y10Var, int i, BufferOverflow bufferOverflow) {
        this.c = y10Var;
        this.e = i;
        this.k = bufferOverflow;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0015  */
    @Override // p.rz0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.dv0 a(p.y10 r5, int r6, kotlinx.coroutines.channels.BufferOverflow r7) {
        /*
            r4 = this;
            p.y10 r0 = r4.c
            p.y10 r5 = r5.o0(r0)
            kotlinx.coroutines.channels.BufferOverflow r1 = kotlinx.coroutines.channels.BufferOverflow.c
            kotlinx.coroutines.channels.BufferOverflow r2 = r4.k
            int r3 = r4.e
            if (r7 == r1) goto Lf
            goto L26
        Lf:
            r7 = -3
            if (r3 != r7) goto L13
            goto L25
        L13:
            if (r6 != r7) goto L17
        L15:
            r6 = r3
            goto L25
        L17:
            r7 = -2
            if (r3 != r7) goto L1b
            goto L25
        L1b:
            if (r6 != r7) goto L1e
            goto L15
        L1e:
            int r6 = r6 + r3
            if (r6 < 0) goto L22
            goto L25
        L22:
            r6 = 2147483647(0x7fffffff, float:NaN)
        L25:
            r7 = r2
        L26:
            boolean r0 = p.v03.b(r5, r0)
            if (r0 == 0) goto L31
            if (r6 != r3) goto L31
            if (r7 != r2) goto L31
            return r4
        L31:
            kotlinx.coroutines.flow.internal.a r5 = r4.d(r5, r6, r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.a.a(p.y10, int, kotlinx.coroutines.channels.BufferOverflow):p.dv0");
    }

    public abstract Object b(sx1 sx1Var, c00 c00Var);

    @Override // p.dv0
    public Object c(fv0 fv0Var, c00 c00Var) throws Throwable {
        ChannelFlow$collect$2 channelFlow$collect$2 = new ChannelFlow$collect$2(null, fv0Var, this);
        o72 o72Var = new o72(c00Var, c00Var.getContext());
        Object objV = q30.v(o72Var, o72Var, channelFlow$collect$2);
        return objV == CoroutineSingletons.c ? objV : lq2.a;
    }

    public abstract a d(y10 y10Var, int i, BufferOverflow bufferOverflow);

    public String toString() {
        ArrayList arrayList = new ArrayList(4);
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.c;
        y10 y10Var = this.c;
        if (y10Var != emptyCoroutineContext) {
            arrayList.add("context=" + y10Var);
        }
        int i = this.e;
        if (i != -3) {
            arrayList.add("capacity=" + i);
        }
        BufferOverflow bufferOverflow = BufferOverflow.c;
        BufferOverflow bufferOverflow2 = this.k;
        if (bufferOverflow2 != bufferOverflow) {
            arrayList.add("onBufferOverflow=" + bufferOverflow2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append('[');
        return hf2.d(sb, ou.h0(arrayList, ", ", null, null, null, 62), ']');
    }
}
