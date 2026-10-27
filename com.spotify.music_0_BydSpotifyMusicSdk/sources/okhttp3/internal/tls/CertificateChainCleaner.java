package okhttp3.internal.tls;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class CertificateChainCleaner {
    public static final Companion a = new Companion(0);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public abstract List a(String str, List list);
}
