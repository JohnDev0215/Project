package defpackage;

import java.io.OutputStream;

/* JADX INFO: renamed from: Rk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0315Rk implements InterfaceC0509am {
    public int memoizedHashCode = 0;

    public int a(InterfaceC1178mm interfaceC1178mm) {
        AbstractC0065Dl abstractC0065Dl = (AbstractC0065Dl) this;
        int i = abstractC0065Dl.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iE = interfaceC1178mm.e(this);
        abstractC0065Dl.memoizedSerializedSize = iE;
        return iE;
    }

    public void b(OutputStream outputStream) {
        AbstractC0065Dl abstractC0065Dl = (AbstractC0065Dl) this;
        C1287ol c1287ol = new C1287ol(outputStream, AbstractC1342pl.o(abstractC0065Dl.f()));
        abstractC0065Dl.j(c1287ol);
        if (c1287ol.f > 0) {
            c1287ol.Z();
        }
    }
}
