package com.amazon.identity.auth.accounts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.c7;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.i5;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.va;
import com.amazon.identity.auth.device.x;
import com.amazon.identity.auth.device.y0;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class SessionUserChangedToAccountForPackageChangedAdpater extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f150a = SessionUserChangedToAccountForPackageChangedAdpater.class.getName();

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f151a;

        public a(SessionUserChangedToAccountForPackageChangedAdpater sessionUserChangedToAccountForPackageChangedAdpater, Context context) {
            this.f151a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = SessionUserChangedToAccountForPackageChangedAdpater.f150a;
            ga.a(str);
            t5 t5VarA = t5.a(this.f151a);
            if (!qe.k(((i5) t5VarA.getSystemService("sso_platform")).f382a)) {
                ga.a(str);
                return;
            }
            String str2 = x.f652a;
            String.format("%s sends broadcast for account for package changed", t5VarA.getPackageName());
            ga.a(str2);
            c7.a(t5VarA, new MAPAccountManager(t5VarA).getAccount());
            y0.a(t5VarA).a((String) null, new Intent(MAPAccountManager.ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION), AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ga.a(f150a);
        va.f637a.execute(new a(this, context));
    }
}
