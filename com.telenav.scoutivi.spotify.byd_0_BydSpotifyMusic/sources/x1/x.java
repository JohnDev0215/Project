package x1;

import android.graphics.Bitmap;
import java.io.IOException;
import java.io.InputStream;
import x1.m;

/* JADX INFO: compiled from: StreamBitmapDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class x implements n1.k<InputStream, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final m f9812a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final r1.b f9813b;

    /* JADX INFO: compiled from: StreamBitmapDecoder.java */
    static class a implements m.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final u f9814a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final k2.d f9815b;

        a(u uVar, k2.d dVar) {
            this.f9814a = uVar;
            this.f9815b = dVar;
        }

        @Override // x1.m.b
        public void a(r1.d dVar, Bitmap bitmap) throws IOException {
            IOException iOExceptionM = this.f9815b.m();
            if (iOExceptionM != null) {
                if (bitmap == null) {
                    throw iOExceptionM;
                }
                dVar.d(bitmap);
                throw iOExceptionM;
            }
        }

        @Override // x1.m.b
        public void b() {
            this.f9814a.z();
        }
    }

    public x(m mVar, r1.b bVar) {
        this.f9812a = mVar;
        this.f9813b = bVar;
    }

    @Override // n1.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public q1.v<Bitmap> b(InputStream inputStream, int i7, int i8, n1.i iVar) {
        boolean z6;
        u uVar;
        if (inputStream instanceof u) {
            uVar = (u) inputStream;
            z6 = false;
        } else {
            z6 = true;
            uVar = new u(inputStream, this.f9813b);
        }
        k2.d dVarZ = k2.d.z(uVar);
        try {
            return this.f9812a.e(new k2.h(dVarZ), i7, i8, iVar, new a(uVar, dVarZ));
        } finally {
            dVarZ.A();
            if (z6) {
                uVar.A();
            }
        }
    }

    @Override // n1.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(InputStream inputStream, n1.i iVar) {
        return this.f9812a.m(inputStream);
    }
}
