package defpackage;

import android.os.Parcel;

/* JADX INFO: renamed from: Hf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0131Hf extends AbstractBinderC0401Wg implements InterfaceC0113Gf {
    public AbstractBinderC0131Hf() {
        super("com.google.android.gms.common.internal.ICertData");
    }

    @Override // defpackage.AbstractBinderC0401Wg
    public boolean u(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            BinderC0615cg binderC0615cg = new BinderC0615cg(((AbstractBinderC0382Vf) this).v());
            parcel2.writeNoException();
            AbstractC0383Vg.c(parcel2, binderC0615cg);
        } else {
            if (i != 2) {
                return false;
            }
            int i3 = ((AbstractBinderC0382Vf) this).a;
            parcel2.writeNoException();
            parcel2.writeInt(i3);
        }
        return true;
    }
}
