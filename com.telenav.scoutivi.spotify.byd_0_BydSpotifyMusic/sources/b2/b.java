package b2;

import android.graphics.Bitmap;
import l1.a;

/* JADX INFO: compiled from: GifBitmapProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements a.InterfaceC0114a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final r1.d f3482a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final r1.b f3483b;

    public b(r1.d dVar, r1.b bVar) {
        this.f3482a = dVar;
        this.f3483b = bVar;
    }

    @Override // l1.a.InterfaceC0114a
    public int[] a(int i7) {
        r1.b bVar = this.f3483b;
        return bVar == null ? new int[i7] : (int[]) bVar.e(i7, int[].class);
    }

    @Override // l1.a.InterfaceC0114a
    public Bitmap b(int i7, int i8, Bitmap.Config config) {
        return this.f3482a.e(i7, i8, config);
    }

    @Override // l1.a.InterfaceC0114a
    public void c(byte[] bArr) {
        r1.b bVar = this.f3483b;
        if (bVar == null) {
            return;
        }
        bVar.d(bArr);
    }

    @Override // l1.a.InterfaceC0114a
    public void d(Bitmap bitmap) {
        this.f3482a.d(bitmap);
    }

    @Override // l1.a.InterfaceC0114a
    public byte[] e(int i7) {
        r1.b bVar = this.f3483b;
        return bVar == null ? new byte[i7] : (byte[]) bVar.e(i7, byte[].class);
    }

    @Override // l1.a.InterfaceC0114a
    public void f(int[] iArr) {
        r1.b bVar = this.f3483b;
        if (bVar == null) {
            return;
        }
        bVar.d(iArr);
    }
}
