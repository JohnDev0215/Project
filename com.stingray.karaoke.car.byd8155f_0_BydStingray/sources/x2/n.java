package x2;

import h2.AbstractC1343A;
import i3.C1406e;
import java.io.IOException;
import java.util.concurrent.Executors;
import u2.I;
import y2.ExecutorC2739a;

/* JADX INFO: loaded from: classes.dex */
public final class n {
    public static final C1406e d = new C1406e(-9223372036854775807L, 2);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C1406e f23756e = new C1406e(-9223372036854775807L, 3);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ExecutorC2739a f23757a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public k f23758b;
    public IOException c;

    /* JADX WARN: Illegal instructions before constructor call */
    public n(String str) {
        String strConcat = "ExoPlayer:Loader:".concat(str);
        int i7 = AbstractC1343A.f15195a;
        this(new ExecutorC2739a(Executors.newSingleThreadExecutor(new T1.a(strConcat, 1)), new I(8)));
    }

    public final boolean a() {
        return this.f23758b != null;
    }

    public n(ExecutorC2739a executorC2739a) {
        this.f23757a = executorC2739a;
    }
}
