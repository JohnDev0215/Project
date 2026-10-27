package defpackage;

import android.os.Parcel;
import com.google.android.gms.signin.internal.zzm;

/* JADX INFO: renamed from: ij, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0952ij extends AbstractBinderC0401Wg implements InterfaceC0785fj {
    public AbstractBinderC0952ij() {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }

    @Override // defpackage.AbstractBinderC0401Wg
    public boolean u(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 3:
                break;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                break;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
            default:
                return false;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                break;
            case 7:
                break;
            case 8:
                a((zzm) AbstractC0383Vg.b(parcel, zzm.CREATOR));
                break;
            case 9:
                break;
        }
        parcel2.writeNoException();
        return true;
    }
}
