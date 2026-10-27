package defpackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: renamed from: tJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1541tJ extends C0989jJ {
    public final C1045kJ d;
    public C0989jJ e;

    public C1541tJ(C0989jJ c0989jJ) {
        C1045kJ c1045kJ = new C1045kJ(c0989jJ);
        super(c0989jJ.a, c0989jJ.b);
        this.d = c1045kJ;
    }

    @Override // defpackage.C0989jJ
    public C1541tJ a() {
        return this;
    }

    public C0989jJ b() {
        if (this.e == null) {
            ByteBuffer byteBufferSlice = ((ByteBuffer) this.a.position(this.d.a.a)).slice();
            byteBufferSlice.order(ByteOrder.LITTLE_ENDIAN);
            this.e = new C0989jJ(byteBufferSlice, this.b);
        }
        return this.e;
    }

    public C1541tJ(C0989jJ c0989jJ, C1045kJ c1045kJ) {
        super(c0989jJ.a, c0989jJ.b);
        this.d = c1045kJ;
    }
}
