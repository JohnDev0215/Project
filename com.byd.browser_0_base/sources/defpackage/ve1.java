package defpackage;

import org.chromium.base.MemoryPressureListener;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: MemoryPressureMonitor.java */
/* JADX INFO: loaded from: classes.dex */
public final class ve1 {
    public static final ve1 i = new ve1();
    public Integer c;
    public boolean d;
    public boolean e;
    public int b = 0;
    public final se1 f = new se1(0);
    public final te1 g = new qe1() { // from class: te1
        @Override // defpackage.qe1
        public final void a(int i2) {
            MemoryPressureListener.a(i2);
        }
    };
    public final wq0 h = new wq0(8, this);
    public final int a = 60000;

    public final void a(int i2) {
        Object obj = ThreadUtils.a;
        if (this.d) {
            this.c = Integer.valueOf(i2);
        } else {
            b(i2);
        }
    }

    public final void b(int i2) {
        ThreadUtils.c().postDelayed(this.h, this.a);
        this.d = true;
        this.b = i2;
        getClass();
        MemoryPressureListener.a(i2);
    }
}
