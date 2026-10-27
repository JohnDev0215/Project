package okhttp3;

import java.io.Closeable;
import okio.ByteString;
import p.de2;
import p.fo;
import p.qo;
import p.tl2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class MultipartReader implements Closeable {
    public boolean c;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public static final class Part implements Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            throw null;
        }
    }

    public final class PartSource implements de2 {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            throw null;
        }

        @Override // p.de2
        public final tl2 e() {
            return null;
        }

        @Override // p.de2
        public final long t(fo foVar, long j) {
            v03.j(foVar, "sink");
            if (j >= 0) {
                throw null;
            }
            throw new IllegalArgumentException(u4.g("byteCount < 0: ", j).toString());
        }
    }

    static {
        new Companion(0);
        ByteString byteString = ByteString.k;
        qo.r(qo.p("\r\n"), qo.p("--"), qo.p(" "), qo.p("\t"));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.c) {
            return;
        }
        this.c = true;
        throw null;
    }
}
