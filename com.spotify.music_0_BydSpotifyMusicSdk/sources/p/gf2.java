package p;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.text.TextUtils;
import com.spotify.automotive.accountmanager.model.AccountManagerModel;
import com.spotify.base.java.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class gf2 implements ef2 {
    public final AccountManager a;

    public gf2(AccountManager accountManager) {
        this.a = accountManager;
    }

    @Override // p.ef2
    public final void a() {
        AccountManager accountManager = this.a;
        Account[] accountsByType = accountManager.getAccountsByType("com.spotify");
        if (accountsByType.length == 0) {
            Logger.b("No accounts to remove", new Object[0]);
            return;
        }
        for (Account account : accountsByType) {
            accountManager.removeAccountExplicitly(account);
        }
    }

    @Override // p.ef2
    public final Account[] b() {
        return this.a.getAccountsByType("com.spotify");
    }

    @Override // p.ef2
    public final void c(AccountManagerModel accountManagerModel) {
        String str = accountManagerModel.c;
        if (!TextUtils.isEmpty(str)) {
            String str2 = accountManagerModel.b;
            if (!TextUtils.isEmpty(str2)) {
                Account account = new Account(str2, "com.spotify");
                AccountManager accountManager = this.a;
                accountManager.addAccountExplicitly(account, null, null);
                accountManager.setAuthToken(account, accountManagerModel.d, str);
                return;
            }
        }
        Logger.b("Attempt to addAccount with empty credentials", new Object[0]);
    }
}
