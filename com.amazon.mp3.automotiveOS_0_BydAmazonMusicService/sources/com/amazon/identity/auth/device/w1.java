package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.utils.AccountConstants;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class w1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f642a = "com.amazon.identity.auth.device.w1";

    public static Bundle a(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            ga.a(f642a);
        }
        ga.a(f642a, "Device Email has been set to ", str2);
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.dcp.sso.property.deviceemail", str2);
        a(context, str, bundle);
        return bundle;
    }

    public static Bundle b(Context context, String str, String str2) {
        new AmazonAccountManager(context).f144a.d(str, "com.amazon.dcp.sso.property.devicename", str2);
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.dcp.sso.property.devicename", str2);
        a(context, str, bundle);
        return bundle;
    }

    public static void a(Context context, String str, Bundle bundle) {
        String str2 = f642a;
        ga.a(str2);
        if (str != null && !str.equals(y0.a(context).a())) {
            Log.i(ga.a(str2), "Not notifying that the given accounts property has changed because it is not currently visibile");
            return;
        }
        Intent intentA = e.a("com.amazon.dcp.sso.action.AmazonAccountPropertyService.property.changed");
        for (String str3 : bundle.keySet()) {
            String str4 = f642a;
            String.format("Key = %s, Value = %s", str3, bundle.getString(str3));
            ga.a(str4);
            intentA.putExtra(str3, bundle.getString(str3));
        }
        Account accountA = r9.a(context, str);
        if (accountA != null) {
            intentA.putExtra("account.property.changed", accountA);
        }
        intentA.putExtra(CustomerAttributeStore.KEY_DIRECTED_ID, str);
        y0.a(context).c(str, intentA, AccountConstants.PERMISSION_AMAZON_ACCOUNT_PROPERTY_CHANGED);
        ga.a(f642a);
    }
}
