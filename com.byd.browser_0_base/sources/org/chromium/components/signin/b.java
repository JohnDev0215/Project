package org.chromium.components.signin;

import android.accounts.Account;
import defpackage.s62;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.chromium.components.signin.AccountManagerFacade;

/* JADX INFO: compiled from: AccountUtils.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b {
    public static final Pattern a = Pattern.compile("@");

    public static String a(String str) {
        String[] strArrSplit = a.split(str);
        if (strArrSplit.length != 2) {
            return str;
        }
        if ("googlemail.com".equalsIgnoreCase(strArrSplit[1])) {
            strArrSplit[1] = "gmail.com";
        }
        if ("gmail.com".equalsIgnoreCase(strArrSplit[1])) {
            strArrSplit[0] = strArrSplit[0].replace(".", "");
        }
        return (strArrSplit[0] + "@" + strArrSplit[1]).toLowerCase(Locale.US);
    }

    public static void b(AccountManagerFacade accountManagerFacade, List<Account> list, AccountManagerFacade.a aVar) {
        if (list.size() >= 1) {
            accountManagerFacade.h(list.get(0), aVar);
        } else {
            aVar.k(false);
        }
    }

    public static Account c(String str) {
        return new Account(str, "com.google");
    }

    public static Account d(String str, List list) {
        String strA = a(str);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Account account = (Account) it.next();
            if (a(account.name).equals(strA)) {
                return account;
            }
        }
        return null;
    }

    public static List<Account> e(s62<List<Account>> s62Var) {
        return s62Var.d() ? s62Var.b : Collections.emptyList();
    }

    public static ArrayList f(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Account) it.next()).name);
        }
        return arrayList;
    }
}
