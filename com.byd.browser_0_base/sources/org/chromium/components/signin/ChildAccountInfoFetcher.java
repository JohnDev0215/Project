package org.chromium.components.signin;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import defpackage.ke0;
import defpackage.xz;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.signin.base.CoreAccountInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class ChildAccountInfoFetcher {
    public final long a;
    public final CoreAccountInfo b;
    public final a c;

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            Object obj = ThreadUtils.a;
            Account account = (Account) intent.getParcelableExtra("account");
            String str = account.name;
            ChildAccountInfoFetcher childAccountInfoFetcher = ChildAccountInfoFetcher.this;
            if (childAccountInfoFetcher.b.getEmail().equals(account.name)) {
                CoreAccountInfo coreAccountInfo = childAccountInfoFetcher.b;
                coreAccountInfo.getEmail();
                AccountManagerFacadeProvider.getInstance().h(CoreAccountInfo.a(coreAccountInfo), new ke0(3, childAccountInfoFetcher));
            }
        }
    }

    public ChildAccountInfoFetcher(long j, CoreAccountInfo coreAccountInfo) {
        this.a = j;
        this.b = coreAccountInfo;
        a aVar = new a();
        this.c = aVar;
        xz.a.registerReceiver(aVar, new IntentFilter("com.google.android.gms.auth.ACCOUNT_SERVICES_CHANGED"), "com.google.android.gms.auth.permission.GOOGLE_ACCOUNT_CHANGE", null, 2);
        coreAccountInfo.getEmail();
        AccountManagerFacadeProvider.getInstance().h(CoreAccountInfo.a(coreAccountInfo), new ke0(3, this));
    }

    @CalledByNative
    public static ChildAccountInfoFetcher create(long j, CoreAccountInfo coreAccountInfo) {
        return new ChildAccountInfoFetcher(j, coreAccountInfo);
    }

    @CalledByNative
    public final void destroy() {
        xz.a.unregisterReceiver(this.c);
    }
}
