package f;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
import okio.ByteString;

/* JADX INFO: compiled from: RequestBody.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class x {

    /* JADX INFO: compiled from: RequestBody.java */
    public static class a extends x {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ s f5430a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ByteString f5431b;

        public a(s sVar, ByteString byteString) {
            this.f5430a = sVar;
            this.f5431b = byteString;
        }

        @Override // f.x
        public long contentLength() throws IOException {
            return this.f5431b.size();
        }

        @Override // f.x
        public s contentType() {
            return this.f5430a;
        }

        @Override // f.x
        public void writeTo(g.d dVar) throws IOException {
            dVar.h(this.f5431b);
        }
    }

    /* JADX INFO: compiled from: RequestBody.java */
    public static class b extends x {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ s f5432a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f5433b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ byte[] f5434c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f5435d;

        public b(s sVar, int i2, byte[] bArr, int i3) {
            this.f5432a = sVar;
            this.f5433b = i2;
            this.f5434c = bArr;
            this.f5435d = i3;
        }

        @Override // f.x
        public long contentLength() {
            return this.f5433b;
        }

        @Override // f.x
        public s contentType() {
            return this.f5432a;
        }

        @Override // f.x
        public void writeTo(g.d dVar) throws IOException {
            dVar.d(this.f5434c, this.f5435d, this.f5433b);
        }
    }

    /* JADX INFO: compiled from: RequestBody.java */
    public static class c extends x {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ s f5436a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ File f5437b;

        public c(s sVar, File file) {
            this.f5436a = sVar;
            this.f5437b = file;
        }

        @Override // f.x
        public long contentLength() {
            return this.f5437b.length();
        }

        @Override // f.x
        public s contentType() {
            return this.f5436a;
        }

        @Override // f.x
        public void writeTo(g.d dVar) throws IOException {
            g.p pVarF = null;
            try {
                pVarF = g.j.f(this.f5437b);
                dVar.j(pVarF);
            } finally {
                f.b0.c.c(pVarF);
            }
        }
    }

    public static x create(s sVar, String str) {
        Charset charset = f.b0.c.j;
        if (sVar != null) {
            Charset charsetA = sVar.a();
            if (charsetA == null) {
                sVar = s.c(sVar + "; charset=utf-8");
            } else {
                charset = charsetA;
            }
        }
        return create(sVar, str.getBytes(charset));
    }

    public long contentLength() throws IOException {
        return -1L;
    }

    public abstract s contentType();

    public abstract void writeTo(g.d dVar) throws IOException;

    public static x create(s sVar, ByteString byteString) {
        return new a(sVar, byteString);
    }

    public static x create(s sVar, byte[] bArr) {
        return create(sVar, bArr, 0, bArr.length);
    }

    public static x create(s sVar, byte[] bArr, int i2, int i3) {
        Objects.requireNonNull(bArr, "content == null");
        f.b0.c.b(bArr.length, i2, i3);
        return new b(sVar, i3, bArr, i2);
    }

    public static x create(s sVar, File file) {
        Objects.requireNonNull(file, "content == null");
        return new c(sVar, file);
    }
}
