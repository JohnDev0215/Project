package okhttp3.internal.http2;

/* JADX INFO: loaded from: classes.dex */
public interface PushObserver {
    public static final PushObserver a;

    public static final class Companion {
        public static final /* synthetic */ int a = 0;

        public static final class PushObserverCancel implements PushObserver {
        }

        static {
            new Companion();
        }

        private Companion() {
        }
    }

    static {
        int i = Companion.a;
        a = new Companion.PushObserverCancel();
    }
}
