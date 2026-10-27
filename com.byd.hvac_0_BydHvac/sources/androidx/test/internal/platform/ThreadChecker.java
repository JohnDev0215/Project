package androidx.test.internal.platform;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface ThreadChecker {
    void checkMainThread();

    void checkNotMainThread();
}
