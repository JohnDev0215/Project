package e;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
import javax.annotation.Nullable;
import okio.ByteString;

/* JADX INFO: compiled from: RequestBody.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: compiled from: RequestBody.java */
    public class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ u f3681a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ByteString f3682b;

        public a(u uVar, ByteString byteString) {
            this.f3681a = uVar;
            this.f3682b = byteString;
        }

        @Override // e.z
        public long a() throws IOException {
            return this.f3682b.size();
        }

        @Override // e.z
        @Nullable
        public u b() {
            return this.f3681a;
        }

        @Override // e.z
        public void h(f.d dVar) throws IOException {
            dVar.i(this.f3682b);
        }
    }

    /* JADX INFO: compiled from: RequestBody.java */
    public class b extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ u f3683a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f3684b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ byte[] f3685c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f3686d;

        public b(u uVar, int i2, byte[] bArr, int i3) {
            this.f3683a = uVar;
            this.f3684b = i2;
            this.f3685c = bArr;
            this.f3686d = i3;
        }

        @Override // e.z
        public long a() {
            return this.f3684b;
        }

        @Override // e.z
        @Nullable
        public u b() {
            return this.f3683a;
        }

        @Override // e.z
        public void h(f.d dVar) throws IOException {
            dVar.g(this.f3685c, this.f3686d, this.f3684b);
        }
    }

    /* JADX INFO: compiled from: RequestBody.java */
    public class c extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ u f3687a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ File f3688b;

        public c(u uVar, File file) {
            this.f3687a = uVar;
            this.f3688b = file;
        }

        @Override // e.z
        public long a() {
            return this.f3688b.length();
        }

        @Override // e.z
        @Nullable
        public u b() {
            return this.f3687a;
        }

        @Override // e.z
        public void h(f.d dVar) throws IOException {
            f.q qVarF = null;
            try {
                qVarF = f.k.f(this.f3688b);
                dVar.k(qVarF);
            } finally {
                e.d0.c.c(qVarF);
            }
        }
    }

    public static z c(@Nullable u uVar, File file) {
        Objects.requireNonNull(file, "content == null");
        return new c(uVar, file);
    }

    public static z d(@Nullable u uVar, String str) {
        Charset charset = e.d0.c.j;
        if (uVar != null) {
            Charset charsetA = uVar.a();
            if (charsetA == null) {
                uVar = u.c(uVar + "; charset=utf-8");
            } else {
                charset = charsetA;
            }
        }
        return f(uVar, str.getBytes(charset));
    }

    public static z e(@Nullable u uVar, ByteString byteString) {
        return new a(uVar, byteString);
    }

    public static z f(@Nullable u uVar, byte[] bArr) {
        return g(uVar, bArr, 0, bArr.length);
    }

    public static z g(@Nullable u uVar, byte[] bArr, int i2, int i3) {
        Objects.requireNonNull(bArr, "content == null");
        e.d0.c.b(bArr.length, i2, i3);
        return new b(uVar, i3, bArr, i2);
    }

    public long a() throws IOException {
        return -1L;
    }

    @Nullable
    public abstract u b();

    public abstract void h(f.d dVar) throws IOException;
}
