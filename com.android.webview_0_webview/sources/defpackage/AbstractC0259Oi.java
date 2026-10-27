package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.Feature;

/* JADX INFO: renamed from: Oi, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0259Oi extends AbstractC0399We {
    public final String C;
    public final InterfaceC0241Ni D;

    public AbstractC0259Oi(Context context, Looper looper, InterfaceC0110Gc interfaceC0110Gc, InterfaceC0128Hc interfaceC0128Hc, String str, C0381Ve c0381Ve) {
        super(context, looper, 23, c0381Ve, interfaceC0110Gc, interfaceC0128Hc);
        this.D = new C0277Pi(this);
        this.C = str;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public /* synthetic */ IInterface d(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return iInterfaceQueryLocalInterface instanceof InterfaceC0080Ei ? (InterfaceC0080Ei) iInterfaceQueryLocalInterface : new C0062Di(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public Feature[] e() {
        return AbstractC0562bj.c;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public Bundle g() {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.C);
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, defpackage.InterfaceC1882zc
    public int getMinApkVersion() {
        return 11925000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public String j() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public String k() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }
}
