package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f652a = "x";

    public static void a(Context context, boolean z, String str, Account account, String str2, Set<Integer> set, Bundle bundle) {
        c7.a(context, new MAPAccountManager(context).getAccount());
        Intent intentA = a(str, null, "com.amazon.identity.auth.account.removed.on.device", null, false, bundle);
        intentA.putIntegerArrayListExtra(MAPAccountManager.KEY_EXTRA_PROFILES_ACCOUNT_ADDED_OR_REMOVED_BELONG_TO, new ArrayList<>(set));
        y0.a(context).b(str, intentA, AccountConstants.PERMISSION_AMAZON_MULTIPLE_PROFILE_AWARE);
        if (z) {
            y0.a(context).a(str, set, a(str, account, "com.amazon.dcp.sso.action.account.removed", str2, false, bundle), AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED);
        } else {
            y0.a(context).a(str, set, a(str, account, "com.amazon.dcp.sso.action.secondary.account.removed", str2, false, bundle), AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED);
        }
    }

    public static Intent a(String str, Account account, String str2, String str3, boolean z, Bundle bundle) {
        Intent intentA = e.a(str2);
        if (str3 != null) {
            intentA.setPackage(str3);
        }
        if (account != null) {
            intentA.putExtra(AccountConstants.EXTRA_AMAZON_ACCOUNT_CHANGED_NAME, account.name);
            intentA.putExtra(AccountConstants.EXTRA_AMAZON_ACCOUNT_CHANGED_TYPE, account.type);
        }
        intentA.putExtra(MAPAccountManager.KEY_EXTRA_DIRECTED_ID, str);
        if (z) {
            intentA.putExtra(MAPAccountManager.KEY_IS_NEW_ACCOUNT, z);
        }
        if (bundle != null) {
            intentA.putExtra(MAPAccountManager.KEY_CLIENT_EVENT_CONTEXT, bundle);
        }
        return intentA;
    }
}
