package m3;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.net.Uri;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: NfcKeyListener.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6365a = "NfcKeyListener";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f6366b;

    public b(Context context) {
        this.f6366b = context;
    }

    public void a(Context context, int i8) {
        BydLogHelper.logD("startNfcActivity, pair State :" + i8);
        if (BYDAutoBodyworkDevice.getInstance(context).getPowerLevel() == 0) {
            BydLogHelper.logD("it is in off gear mode , NfcActivity not start. ");
            return;
        }
        String str = "byd_pad://nfc:1080/digitalKeyActivateNewActivity?pair_state=" + i8;
        if (context == null) {
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setFlags(268435456);
        if (packageManager.queryIntentActivities(intent, 0).isEmpty()) {
            return;
        }
        context.startActivity(intent);
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter, b.d
    public void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onNfcOperationCommand(int i8) {
        boolean z7 = BYDAutoStudy.getInstance(this.f6366b).getDigtalKeySupport() != 0;
        BydLogHelper.logD("onNfcOperationCommand:" + i8 + ",isSupportNfc:" + z7);
        if (z7 && i8 == 15) {
            a(this.f6366b, 1);
        }
    }

    @Override // android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onPairingProcessCallBack(int i8, int i9) {
        boolean z7 = BYDAutoStudy.getInstance(this.f6366b).getDigtalKeySupport() != 0;
        BydLogHelper.logD("onPairingProcessCallBack:" + i8 + ",isSupportNfc:" + z7);
        if (i9 == 1 && z7 && i8 == 2) {
            a(this.f6366b, 3);
        }
    }

    @Override // m3.a, android.hardware.bydauto.digital.IDigitalKeyLisenter
    public void onResponseFeedbackInform(int i8) {
        super.onResponseFeedbackInform(i8);
        if (i8 != 2 || l3.a.c(this.f6366b).f()) {
            return;
        }
        o3.a.c(this.f6366b, "pre_is_new_card", 1);
    }
}
