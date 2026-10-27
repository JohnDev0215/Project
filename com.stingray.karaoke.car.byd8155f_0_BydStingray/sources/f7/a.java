package F7;

import A7.m;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* JADX INFO: loaded from: classes.dex */
public final class a extends E7.a {
    @Override // E7.a
    public final Random a() {
        ThreadLocalRandom threadLocalRandomCurrent = ThreadLocalRandom.current();
        m.e(threadLocalRandomCurrent, "current(...)");
        return threadLocalRandomCurrent;
    }
}
