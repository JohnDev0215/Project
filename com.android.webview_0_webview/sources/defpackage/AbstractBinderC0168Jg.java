package defpackage;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;

/* JADX INFO: renamed from: Jg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0168Jg extends AbstractBinderC0401Wg implements InterfaceC0132Hg {
    public AbstractBinderC0168Jg() {
        super("com.google.android.gms.auth.api.phone.internal.ISmsRetrieverResultCallback");
    }

    @Override // defpackage.AbstractBinderC0401Wg
    public boolean u(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        p((Status) AbstractC0383Vg.b(parcel, Status.CREATOR));
        return true;
    }
}
