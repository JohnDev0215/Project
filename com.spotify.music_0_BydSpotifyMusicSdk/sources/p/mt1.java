package p;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* JADX INFO: loaded from: classes.dex */
public final class mt1 extends u1 {
    @Override // p.u1
    public final Random b() {
        ThreadLocalRandom threadLocalRandomCurrent = ThreadLocalRandom.current();
        v03.i(threadLocalRandomCurrent, "current()");
        return threadLocalRandomCurrent;
    }
}
