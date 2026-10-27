package f;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.annotation.Nullable;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;

/* JADX INFO: compiled from: RequestBody.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f0 {

    /* JADX INFO: compiled from: RequestBody.java */
    class a extends f0 {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ z f5066a;

        /* JADX INFO: renamed from: b */
        final /* synthetic */ ByteString f5067b;

        a(z zVar, ByteString byteString) {
            this.f5066a = zVar;
            this.f5067b = byteString;
        }

        @Override // f.f0
        public long a() {
            return this.f5067b.s();
        }

        @Override // f.f0
        @Nullable
        public z b() {
            return this.f5066a;
        }

        @Override // f.f0
        public void j(BufferedSink bufferedSink) {
            bufferedSink.i(this.f5067b);
        }
    }

    /* JADX INFO: compiled from: RequestBody.java */
    class b extends f0 {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ z f5068a;

        /* JADX INFO: renamed from: b */
        final /* synthetic */ int f5069b;

        /* JADX INFO: renamed from: c */
        final /* synthetic */ byte[] f5070c;

        /* JADX INFO: renamed from: d */
        final /* synthetic */ int f5071d;

        b(z zVar, int i2, byte[] bArr, int i3) {
            this.f5068a = zVar;
            this.f5069b = i2;
            this.f5070c = bArr;
            this.f5071d = i3;
        }

        @Override // f.f0
        public long a() {
            return this.f5069b;
        }

        @Override // f.f0
        @Nullable
        public z b() {
            return this.f5068a;
        }

        @Override // f.f0
        public void j(BufferedSink bufferedSink) {
            bufferedSink.g(this.f5070c, this.f5071d, this.f5069b);
        }
    }

    /* JADX INFO: compiled from: RequestBody.java */
    class c extends f0 {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ z f5072a;

        /* JADX INFO: renamed from: b */
        final /* synthetic */ File f5073b;

        c(z zVar, File file) {
            this.f5072a = zVar;
            this.f5073b = file;
        }

        @Override // f.f0
        public long a() {
            return this.f5073b.length();
        }

        @Override // f.f0
        @Nullable
        public z b() {
            return this.f5072a;
        }

        @Override // f.f0
        public void j(BufferedSink bufferedSink) {
            Source sourceI = Okio.i(this.f5073b);
            try {
                bufferedSink.m(sourceI);
                if (sourceI != null) {
                    sourceI.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (sourceI != null) {
                        try {
                            sourceI.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
    }

    public static f0 c(@Nullable z zVar, File file) {
        Objects.requireNonNull(file, "file == null");
        return new c(zVar, file);
    }

    public static f0 d(@Nullable z zVar, String str) {
        Charset charsetA = StandardCharsets.UTF_8;
        if (zVar != null && (charsetA = zVar.a()) == null) {
            charsetA = StandardCharsets.UTF_8;
            zVar = z.d(zVar + "; charset=utf-8");
        }
        return f(zVar, str.getBytes(charsetA));
    }

    public static f0 e(@Nullable z zVar, ByteString byteString) {
        return new a(zVar, byteString);
    }

    public static f0 f(@Nullable z zVar, byte[] bArr) {
        return g(zVar, bArr, 0, bArr.length);
    }

    public static f0 g(@Nullable z zVar, byte[] bArr, int i2, int i3) {
        Objects.requireNonNull(bArr, "content == null");
        f.k0.e.e(bArr.length, i2, i3);
        return new b(zVar, i3, bArr, i2);
    }

    public long a() {
        return -1L;
    }

    @Nullable
    public abstract z b();

    public boolean h() {
        return false;
    }

    public boolean i() {
        return false;
    }

    public abstract void j(BufferedSink bufferedSink);
}
