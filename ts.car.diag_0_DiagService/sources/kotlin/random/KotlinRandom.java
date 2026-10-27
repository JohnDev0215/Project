package kotlin.random;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.q;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010$\u001a\u00020\u001d¢\u0006\u0004\b%\u0010&J\u0017\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0005J\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0019\u0010\u001e\u001a\u00020\u001d8\u0006@\u0000¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0016\u0010\"\u001a\u00020\t8\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006'"}, d2 = {"Lkotlin/random/KotlinRandom;", "Ljava/util/Random;", "Lkotlin/Int;", "bits", "next", "(I)I", "nextInt", "()I", "bound", "Lkotlin/Boolean;", "nextBoolean", "()Z", "Lkotlin/Long;", "nextLong", "()J", "Lkotlin/Float;", "nextFloat", "()F", "Lkotlin/Double;", "nextDouble", "()D", "Lkotlin/ByteArray;", "bytes", "Lkotlin/Unit;", "nextBytes", "([B)V", "seed", "setSeed", "(J)V", "Lkotlin/random/a;", "impl", "Lkotlin/random/a;", "getImpl", "()Lkotlin/random/a;", "seedInitialized", "Z", "p0", "<init>", "(Lkotlin/random/Random;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
final class KotlinRandom extends Random {

    @NotNull
    private final a impl;
    private boolean seedInitialized;

    public KotlinRandom(@NotNull a aVar) {
        q.c(aVar, "impl");
        this.impl = aVar;
    }

    @NotNull
    public final a getImpl() {
        return this.impl;
    }

    @Override // java.util.Random
    protected int next(int bits) {
        return this.impl.a(bits);
    }

    @Override // java.util.Random
    public boolean nextBoolean() {
        return this.impl.b();
    }

    @Override // java.util.Random
    public void nextBytes(@NotNull byte[] bytes) {
        q.c(bytes, "bytes");
        this.impl.c(bytes);
    }

    @Override // java.util.Random
    public double nextDouble() {
        return this.impl.d();
    }

    @Override // java.util.Random
    public float nextFloat() {
        return this.impl.e();
    }

    @Override // java.util.Random
    public int nextInt() {
        return this.impl.f();
    }

    @Override // java.util.Random
    public long nextLong() {
        return this.impl.h();
    }

    @Override // java.util.Random
    public void setSeed(long seed) {
        if (this.seedInitialized) {
            throw new UnsupportedOperationException("Setting seed is not supported.");
        }
        this.seedInitialized = true;
    }

    @Override // java.util.Random
    public int nextInt(int bound) {
        return this.impl.g(bound);
    }
}
