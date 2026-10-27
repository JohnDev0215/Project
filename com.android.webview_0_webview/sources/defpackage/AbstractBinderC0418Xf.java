package defpackage;

import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: Xf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0418Xf extends AbstractBinderC0382Vf {
    public static final WeakReference c = new WeakReference(null);
    public WeakReference b;

    public AbstractBinderC0418Xf(byte[] bArr) {
        super(bArr);
        this.b = c;
    }

    @Override // defpackage.AbstractBinderC0382Vf
    public final byte[] v() {
        byte[] bArrX;
        synchronized (this) {
            bArrX = (byte[]) this.b.get();
            if (bArrX == null) {
                bArrX = x();
                this.b = new WeakReference(bArrX);
            }
        }
        return bArrX;
    }

    public abstract byte[] x();
}
