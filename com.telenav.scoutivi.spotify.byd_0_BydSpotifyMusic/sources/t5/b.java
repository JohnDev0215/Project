package t5;

import java.util.Random;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: PlatformRandom.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b extends t5.a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final a f9559h = new a();

    /* JADX INFO: compiled from: PlatformRandom.kt */
    public static final class a extends ThreadLocal<Random> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Random initialValue() {
            return new Random();
        }
    }

    @Override // t5.a
    public Random c() {
        Random random = this.f9559h.get();
        l.d(random, "implStorage.get()");
        return random;
    }
}
