package kotlinx.coroutines;

import k5.g;

/* JADX INFO: compiled from: CoroutineExceptionHandler.kt */
/* JADX INFO: loaded from: classes.dex */
public interface CoroutineExceptionHandler extends g.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f7512c = a.f7513a;

    /* JADX INFO: compiled from: CoroutineExceptionHandler.kt */
    public static final class a implements g.c<CoroutineExceptionHandler> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ a f7513a = new a();

        private a() {
        }
    }

    void handleException(k5.g gVar, Throwable th);
}
