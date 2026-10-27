package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.Feature;

/* JADX INFO: renamed from: Kg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0186Kg extends AbstractC0399We {
    public C0186Kg(Context context, Looper looper, C0381Ve c0381Ve, InterfaceC0397Wc interfaceC0397Wc, InterfaceC0947id interfaceC0947id) {
        super(context, looper, 126, c0381Ve, interfaceC0397Wc, interfaceC0947id);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface d(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.phone.internal.ISmsRetrieverApiService");
        return iInterfaceQueryLocalInterface instanceof InterfaceC0114Gg ? (InterfaceC0114Gg) iInterfaceQueryLocalInterface : new C0150Ig(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] e() {
        return AbstractC0311Rg.c;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, defpackage.InterfaceC1882zc
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String j() {
        return "com.google.android.gms.auth.api.phone.internal.ISmsRetrieverApiService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String k() {
        return "com.google.android.gms.auth.api.phone.service.SmsRetrieverApiService.START";
    }
}
