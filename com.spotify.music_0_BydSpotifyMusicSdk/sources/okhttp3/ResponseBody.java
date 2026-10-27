package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import okhttp3.internal.Util;
import p.cs;
import p.ko;
import p.lq2;
import p.ux2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class ResponseBody implements Closeable {
    public static final Companion e = new Companion(0);
    public BomAwareReader c;

    public static final class BomAwareReader extends Reader {
        public final ko c;
        public final Charset e;
        public boolean k;
        public InputStreamReader l;

        public BomAwareReader(ko koVar, Charset charset) {
            v03.j(koVar, "source");
            v03.j(charset, "charset");
            this.c = koVar;
            this.e = charset;
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
            lq2 lq2Var;
            this.k = true;
            InputStreamReader inputStreamReader = this.l;
            if (inputStreamReader != null) {
                inputStreamReader.close();
                lq2Var = lq2.a;
            } else {
                lq2Var = null;
            }
            if (lq2Var == null) {
                this.c.close();
            }
        }

        @Override // java.io.Reader
        public final int read(char[] cArr, int i, int i2) throws IOException {
            v03.j(cArr, "cbuf");
            if (this.k) {
                throw new IOException("Stream closed");
            }
            InputStreamReader inputStreamReader = this.l;
            if (inputStreamReader == null) {
                ko koVar = this.c;
                inputStreamReader = new InputStreamReader(koVar.C0(), Util.s(koVar, this.e));
                this.l = inputStreamReader;
            }
            return inputStreamReader.read(cArr, i, i2);
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public abstract long b();

    public abstract MediaType c();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Util.c(i());
    }

    public abstract ko i();

    public final String q() {
        Charset charsetA;
        ko koVarI = i();
        try {
            MediaType mediaTypeC = c();
            if (mediaTypeC == null || (charsetA = mediaTypeC.a(cs.a)) == null) {
                charsetA = cs.a;
            }
            String strA0 = koVarI.A0(Util.s(koVarI, charsetA));
            ux2.e(koVarI, null);
            return strA0;
        } finally {
        }
    }
}
