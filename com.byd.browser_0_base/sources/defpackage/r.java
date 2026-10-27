package defpackage;

import java.util.Random;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: compiled from: PlatformRandom.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class r extends x82 {
    public final int a() {
        Random random = ((wj0) this).g.get();
        i21.d(random, "implStorage.get()");
        return random.nextInt();
    }
}
