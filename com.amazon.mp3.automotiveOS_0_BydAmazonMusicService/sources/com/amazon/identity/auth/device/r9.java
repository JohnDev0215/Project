package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class r9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f530a = "com.amazon.identity.auth.device.r9";

    public static String a(p9 p9Var, Account account) {
        if (account == null || !p9Var.a(account)) {
            return null;
        }
        String strB = p9Var.b(account, "com.amazon.dcp.sso.property.account.acctId");
        if (strB != null) {
            return strB;
        }
        String string = UUID.randomUUID().toString();
        p9Var.a(account, "com.amazon.dcp.sso.property.account.acctId", string);
        return string;
    }

    public static String b(Context context, String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        return a((p9) t5.a(context).getSystemService("dcp_account_manager"), new Account(str2, str));
    }

    public static boolean b(String str) {
        if (a(str)) {
            return false;
        }
        return AccountConstants.TOKEN_TYPE_COOKIE_XMAIN_TOKEN.equals(str) || "com.amazon.dcp.sso.token.cookie.xmainAndXabcCookies".equals(str) || str.startsWith(AccountConstants.DEVICE_TOKEN_TYPE_PREFIX) || str.startsWith("com.amazon.dcp.sso.property.account.extratokens") || "com.amazon.identity.cookies.xfsn".equals(str);
    }

    public static Account a(Context context, String str) {
        return t5.a(context).a().b(str);
    }

    public static Bundle a(String str, String str2, Bundle bundle) {
        if (str != null) {
            bundle.putString(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME, str);
        }
        bundle.putString(AccountConstants.SUB_AUTHENTICATOR_ACCOUNT_TYPE_ATTRIBUTE, AccountConstants.AMAZON_ACCOUNT_TYPE);
        bundle.putString("com.amazon.dcp.sso.property.account.acctId", str2);
        return bundle;
    }

    public static String a(u7 u7Var, String str) {
        Set<String> setA = u7Var.a();
        String str2 = str;
        int i = 1;
        while (setA.contains(str2)) {
            i++;
            str2 = String.format(Locale.US, "%s %d", str, Integer.valueOf(i));
        }
        return str2;
    }

    public static String a(Context context, Account account) {
        return a((p9) t5.a(context).getSystemService("dcp_account_manager"), account);
    }

    public static void a(Context context, String str, String str2) {
        y8 y8VarA;
        Account accountA = a(context, str);
        if (accountA == null || (y8VarA = ((n8) t5.a(context).getSystemService("dcp_token_cache_holder")).a(accountA)) == null) {
            return;
        }
        Log.i(ga.a(y8.g), y8VarA.f + ": invalidateAuthToken");
        y8VarA.a(y8VarA.c, y8VarA.d.b(str2));
    }

    public static boolean a(String str) {
        return AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN.equals(str) || AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY.equals(str) || AccountConstants.KEY_DEVICE_CREDENTIALS.equals(str);
    }
}
