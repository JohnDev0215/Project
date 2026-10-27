package com.android.inputmethod.latin.personalization;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.util.Patterns;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class AccountUtils {
    private AccountUtils() {
    }

    private static Account[] getAccounts(final Context context) {
        return AccountManager.get(context).getAccounts();
    }

    public static List<String> getDeviceAccountsEmailAddresses(final Context context) {
        ArrayList arrayList = new ArrayList();
        for (Account account : getAccounts(context)) {
            String str = account.name;
            if (Patterns.EMAIL_ADDRESS.matcher(str).matches()) {
                arrayList.add(str);
                arrayList.add(str.split("@")[0]);
            }
        }
        return arrayList;
    }

    public static List<String> getDeviceAccountsWithDomain(final Context context, final String domain) {
        ArrayList arrayList = new ArrayList();
        String str = "@" + domain.toLowerCase(Locale.ROOT);
        for (Account account : getAccounts(context)) {
            if (account.name.toLowerCase(Locale.ROOT).endsWith(str)) {
                arrayList.add(account.name);
            }
        }
        return arrayList;
    }
}
