package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class a1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f166a;
    public final AmazonAccountManager b;
    public final u7 c;

    public a1(Context context) {
        t5 t5VarA = t5.a(context);
        this.f166a = t5VarA;
        this.b = (AmazonAccountManager) t5VarA.getSystemService("dcp_amazon_account_man");
        this.c = ((v7) t5VarA.getSystemService("dcp_data_storage_factory")).a();
    }

    public static boolean a(Bundle bundle) {
        return bundle != null && (bundle.getBoolean("DeregisteringDevice") || (bundle.getBoolean("DeregisteringDefaultPrimary") && TextUtils.isEmpty(bundle.getString("NewDefaultPrimary"))));
    }

    public boolean a(String str) {
        String strD = this.c.d(str, AccountConstants.KEY_DEVICE_ACCOUNT_ROLE);
        return !TextUtils.isEmpty(strD) && RegisterDeviceRequest.DeviceAccountRole.PRIMARY.name().equals(strD);
    }
}
