package p;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.spotify.automotive.baseui.login.AAMLoginScannableActivity;
import com.spotify.base.java.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class hf implements gf {
    public final AccountManager a;
    public final k b;

    public hf(AccountManager accountManager, k kVar) {
        this.a = accountManager;
        this.b = kVar;
    }

    @Override // p.gf
    public final Bundle a(AccountAuthenticatorResponse accountAuthenticatorResponse, String str) {
        if (!"com.spotify".equals(str)) {
            Logger.b("Not a valid account type %s", str);
            return null;
        }
        Bundle bundle = new Bundle();
        if (this.a.getAccountsByType("com.spotify").length > 0) {
            bundle.putInt("errorCode", 6);
            return bundle;
        }
        k kVar = this.b;
        kVar.getClass();
        Intent intent = new Intent(kVar.a, (Class<?>) AAMLoginScannableActivity.class);
        intent.putExtra("accountAuthenticatorResponse", accountAuthenticatorResponse);
        intent.putExtra("accountType", str);
        bundle.putParcelable("intent", intent);
        return bundle;
    }

    @Override // p.gf
    public final Bundle b(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str) {
        String str2 = account.type;
        if (!"com.spotify".equals(str2)) {
            Logger.b("Not a valid account type %s", str2);
            return null;
        }
        String strPeekAuthToken = this.a.peekAuthToken(account, str);
        if (!TextUtils.isEmpty(strPeekAuthToken)) {
            Bundle bundle = new Bundle();
            bundle.putString("authAccount", account.name);
            bundle.putString("accountType", str2);
            bundle.putString("authtoken", strPeekAuthToken);
            return bundle;
        }
        k kVar = this.b;
        kVar.getClass();
        Intent intent = new Intent(kVar.a, (Class<?>) AAMLoginScannableActivity.class);
        intent.putExtra("accountAuthenticatorResponse", accountAuthenticatorResponse);
        intent.putExtra("accountType", str2);
        intent.putExtra("authAccount", account.name);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("intent", intent);
        return bundle2;
    }
}
