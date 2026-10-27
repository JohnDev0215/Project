package defpackage;

import android.os.Parcel;
import com.google.android.gms.location.internal.zzae;

/* JADX INFO: renamed from: Ci, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0044Ci extends AbstractBinderC0401Wg implements InterfaceC0008Ai {
    public AbstractBinderC0044Ci() {
        super("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }

    @Override // defpackage.AbstractBinderC0401Wg
    public boolean u(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        zzae zzaeVar = (zzae) AbstractC0383Vg.b(parcel, zzae.CREATOR);
        ((AbstractC1613ui) ((BinderC1833yi) this).a).l(zzaeVar.s);
        return true;
    }
}
