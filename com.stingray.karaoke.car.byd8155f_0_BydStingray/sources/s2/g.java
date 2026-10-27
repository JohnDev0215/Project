package s2;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import h2.AbstractC1346c;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class g extends m2.g {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f22090u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f22091v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f22092w;

    @Override // m2.g
    public final void f() {
        super.f();
        this.f22091v = 0;
    }

    public final boolean l(m2.g gVar) {
        ByteBuffer byteBuffer;
        AbstractC1346c.c(!gVar.e(TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        AbstractC1346c.c(!gVar.e(268435456));
        AbstractC1346c.c(!gVar.e(4));
        if (m()) {
            if (this.f22091v >= this.f22092w) {
                return false;
            }
            ByteBuffer byteBuffer2 = gVar.f18955p;
            if (byteBuffer2 != null && (byteBuffer = this.f18955p) != null) {
                if (byteBuffer2.remaining() + byteBuffer.position() > 3072000) {
                    return false;
                }
            }
        }
        int i7 = this.f22091v;
        this.f22091v = i7 + 1;
        if (i7 == 0) {
            this.f18957r = gVar.f18957r;
            if (gVar.e(1)) {
                this.f15673m = 1;
            }
        }
        ByteBuffer byteBuffer3 = gVar.f18955p;
        if (byteBuffer3 != null) {
            j(byteBuffer3.remaining());
            this.f18955p.put(byteBuffer3);
        }
        this.f22090u = gVar.f18957r;
        return true;
    }

    public final boolean m() {
        return this.f22091v > 0;
    }
}
