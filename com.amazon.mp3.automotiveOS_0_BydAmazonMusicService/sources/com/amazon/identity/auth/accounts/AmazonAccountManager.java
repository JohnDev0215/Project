package com.amazon.identity.auth.accounts;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.u7;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.yd;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class AmazonAccountManager {
    public static final String b = "com.amazon.identity.auth.accounts.AmazonAccountManager";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u7 f144a;

    /* JADX INFO: compiled from: DCP */
    public enum AccountRegistrationStatus {
        Registered("Registered"),
        Deregistering("Deregistering"),
        NotFound("NotFound");

        private final String mValue;

        AccountRegistrationStatus(String str) {
            this.mValue = str;
        }

        public static AccountRegistrationStatus fromValue(String str) {
            AccountRegistrationStatus[] accountRegistrationStatusArrValues = values();
            for (int i = 0; i < 3; i++) {
                AccountRegistrationStatus accountRegistrationStatus = accountRegistrationStatusArrValues[i];
                if (accountRegistrationStatus.getValue().equals(str)) {
                    return accountRegistrationStatus;
                }
            }
            return null;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public AmazonAccountManager(Context context) {
        this(t5.a(context).a());
    }

    public Set<String> a() {
        String str = b;
        this.f144a.b().size();
        ga.a(str);
        return this.f144a.b();
    }

    public String b() {
        HashSet<String> hashSet = (HashSet) c();
        if (hashSet.size() <= 0) {
            return null;
        }
        for (String str : hashSet) {
            if (!e(str)) {
                return str;
            }
        }
        return null;
    }

    public Set<String> c() {
        Set<String> setA = a();
        HashSet hashSet = new HashSet();
        for (String str : setA) {
            if (!c(str)) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    public Set<String> d() {
        HashSet hashSet = new HashSet();
        for (String str : a()) {
            if (f(str)) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    public boolean e() {
        return b() != null;
    }

    public boolean f(String str) {
        return a(str, AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT) != null;
    }

    public AmazonAccountManager(u7 u7Var) {
        this.f144a = u7Var;
    }

    public boolean e(String str) {
        return a(str, AccountConstants.KEY_SECONDARY_AMAZON_ACCOUNT) != null;
    }

    public boolean a(String str) {
        Iterator<String> it = a().iterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public String a(String str, String str2) {
        return this.f144a.d(str, str2);
    }

    public boolean c(String str) {
        AccountRegistrationStatus accountRegistrationStatusFromValue = AccountRegistrationStatus.fromValue(this.f144a.d(str, AccountConstants.KEY_ACCOUNT_STATUS));
        if (accountRegistrationStatusFromValue == null) {
            if (a(str)) {
                accountRegistrationStatusFromValue = AccountRegistrationStatus.Registered;
            } else {
                accountRegistrationStatusFromValue = AccountRegistrationStatus.NotFound;
            }
        }
        return accountRegistrationStatusFromValue == AccountRegistrationStatus.NotFound || accountRegistrationStatusFromValue == AccountRegistrationStatus.Deregistering;
    }

    public boolean d(String str) {
        if (a(str)) {
            if (!(a(str, AccountConstants.KEY_SECONDARY_AMAZON_ACCOUNT) != null)) {
                return true;
            }
        }
        return false;
    }

    public boolean b(String str) {
        Iterator<String> it = a().iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                return false;
            }
            if (it.next().equals(str)) {
                if (AccountRegistrationStatus.fromValue(this.f144a.d(str, AccountConstants.KEY_ACCOUNT_STATUS)) == AccountRegistrationStatus.Deregistering) {
                    yd.a("AccountRemovedBecauseDeregisteringState", new String[0]);
                    Log.i(ga.a(b), "Removing account from database since database is stuck in bad state. Account status is Deregistering and registerAccount API is called");
                    this.f144a.e(str);
                    z = true;
                }
                if (!z) {
                    return true;
                }
            }
        }
    }
}
