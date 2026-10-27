package p;

import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped$Reason;

/* JADX INFO: loaded from: classes.dex */
public final class hs {
    public final /* synthetic */ int a;
    public long b;
    public Object c;

    public hs(int i) {
        this.a = i;
        if (i != 1) {
            this.b = 0L;
        } else {
            this.b = 0L;
            this.c = LogEventDropped$Reason.REASON_UNKNOWN;
        }
    }

    public final void a(int i) {
        if (i < 64) {
            this.b &= ~(1 << i);
            return;
        }
        Object obj = this.c;
        if (((hs) obj) != null) {
            ((hs) obj).a(i - 64);
        }
    }

    public final int b(int i) {
        Object obj = this.c;
        if (((hs) obj) == null) {
            return i >= 64 ? Long.bitCount(this.b) : Long.bitCount(this.b & ((1 << i) - 1));
        }
        if (i < 64) {
            return Long.bitCount(this.b & ((1 << i) - 1));
        }
        return Long.bitCount(this.b) + ((hs) obj).b(i - 64);
    }

    public final void c() {
        if (((hs) this.c) == null) {
            this.c = new hs(0);
        }
    }

    public final boolean d(int i) {
        if (i < 64) {
            return (this.b & (1 << i)) != 0;
        }
        c();
        return ((hs) this.c).d(i - 64);
    }

    public final void e(int i, boolean z) {
        if (i >= 64) {
            c();
            ((hs) this.c).e(i - 64, z);
            return;
        }
        long j = this.b;
        boolean z2 = (Long.MIN_VALUE & j) != 0;
        long j2 = (1 << i) - 1;
        this.b = ((j & (~j2)) << 1) | (j & j2);
        if (z) {
            h(i);
        } else {
            a(i);
        }
        if (z2 || ((hs) this.c) != null) {
            c();
            ((hs) this.c).e(0, z2);
        }
    }

    public final boolean f(int i) {
        if (i >= 64) {
            c();
            return ((hs) this.c).f(i - 64);
        }
        long j = 1 << i;
        long j2 = this.b;
        boolean z = (j2 & j) != 0;
        long j3 = j2 & (~j);
        this.b = j3;
        long j4 = j - 1;
        this.b = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
        Object obj = this.c;
        if (((hs) obj) != null) {
            if (((hs) obj).d(0)) {
                h(63);
            }
            ((hs) this.c).f(0);
        }
        return z;
    }

    public final void g() {
        this.b = 0L;
        Object obj = this.c;
        if (((hs) obj) != null) {
            ((hs) obj).g();
        }
    }

    public final void h(int i) {
        if (i < 64) {
            this.b |= 1 << i;
        } else {
            c();
            ((hs) this.c).h(i - 64);
        }
    }

    public final String toString() {
        switch (this.a) {
            case 0:
                if (((hs) this.c) == null) {
                    return Long.toBinaryString(this.b);
                }
                return ((hs) this.c).toString() + "xx" + Long.toBinaryString(this.b);
            default:
                return super.toString();
        }
    }
}
