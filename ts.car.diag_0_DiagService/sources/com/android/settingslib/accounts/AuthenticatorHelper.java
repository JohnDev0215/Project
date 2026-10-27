package com.android.settingslib.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SyncAdapterType;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public final class AuthenticatorHelper extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<String, AuthenticatorDescription> f1315a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ArrayList<String> f1316b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<String, Drawable> f1317c;
    private final HashMap<String, ArrayList<String>> d;
    private final UserHandle e;
    private final Context f;
    private final a g;
    private boolean h;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        void a(UserHandle userHandle);
    }

    private void a() {
        this.d.clear();
        for (SyncAdapterType syncAdapterType : ContentResolver.getSyncAdapterTypesAsUser(this.e.getIdentifier())) {
            ArrayList<String> arrayList = this.d.get(syncAdapterType.accountType);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.d.put(syncAdapterType.accountType, arrayList);
            }
            if (Log.isLoggable("AuthenticatorHelper", 2)) {
                Log.v("AuthenticatorHelper", "Added authority " + syncAdapterType.authority + " to accountType " + syncAdapterType.accountType);
            }
            arrayList.add(syncAdapterType.authority);
        }
    }

    void b(Account[] accountArr) {
        c(this.f);
        if (accountArr == null) {
            accountArr = AccountManager.get(this.f).getAccountsAsUser(this.e.getIdentifier());
        }
        this.f1316b.clear();
        this.f1317c.clear();
        for (Account account : accountArr) {
            if (!this.f1316b.contains(account.type)) {
                this.f1316b.add(account.type);
            }
        }
        a();
        if (this.h) {
            this.g.a(this.e);
        }
    }

    public void c(Context context) {
        AuthenticatorDescription[] authenticatorTypesAsUser = AccountManager.get(context).getAuthenticatorTypesAsUser(this.e.getIdentifier());
        for (int i = 0; i < authenticatorTypesAsUser.length; i++) {
            this.f1315a.put(authenticatorTypesAsUser[i].type, authenticatorTypesAsUser[i]);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        b(AccountManager.get(this.f).getAccountsAsUser(this.e.getIdentifier()));
    }
}
