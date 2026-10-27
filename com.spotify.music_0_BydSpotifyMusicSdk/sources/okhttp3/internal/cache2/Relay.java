package okhttp3.internal.cache2;

import okio.ByteString;
import p.de2;
import p.fo;
import p.qo;
import p.tl2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Relay {

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public final class RelaySource implements de2 {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
        }

        @Override // p.de2
        public final tl2 e() {
            return null;
        }

        @Override // p.de2
        public final long t(fo foVar, long j) {
            v03.j(foVar, "sink");
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    static {
        new Companion(0);
        ByteString byteString = ByteString.k;
        qo.p("OkHttp cache v1\n");
        qo.p("OkHttp DIRTY :(\n");
    }
}
