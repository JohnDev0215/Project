package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.accounts.SessionUserChangedToAccountForPackageChangedAdpater;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.bootstrapSSO.BootstrapSSOService;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.storage.DatabaseCleaner;
import com.amazon.identity.auth.device.storage.DirtyDataSyncingService;
import com.amazon.identity.auth.device.storage.LambortishClock;
import com.amazon.identity.auth.device.userdictionary.UserDictionaryHelper;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class c5 {
    public static final String c = "com.amazon.identity.auth.device.c5";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f260a;
    public final i5 b;

    public c5(Context context) {
        this.f260a = t5.a(context.getApplicationContext());
        this.b = new i5(context);
    }

    public final void a(Class<?> cls, int i) {
        String str = c;
        cls.getSimpleName();
        ga.a(str);
        try {
            this.f260a.getPackageManager().setComponentEnabledSetting(new ComponentName(this.f260a, cls), i, 1);
        } catch (IllegalArgumentException unused) {
            ga.a(c, "Component Class %s not found in manifest", cls.getSimpleName());
        }
    }

    public void a() {
        if (xa.a()) {
            Log.e(ga.a(c), "Not migrating because we are running unit tests");
            return;
        }
        if (!va.a()) {
            v7 v7Var = (v7) this.f260a.getSystemService("dcp_data_storage_factory");
            String str = c;
            Log.i(ga.a(str), "Get DataStorage instance for initialization");
            u7 u7VarA = v7Var.a();
            Log.i(ga.a(str), "Initialize DataStorage instance");
            u7VarA.d();
            Log.i(ga.a(str), "Setup DataStorage instance");
            u7VarA.e();
            a(this.f260a, 3);
            IsolatedModeSwitcher.switchAppToSSOModeIfNecessary(this.f260a);
            z0.a(this.f260a).a();
            t5 t5Var = this.f260a;
            synchronized (j2.class) {
                boolean z = new MAPAccountManager(t5Var).getAccount() != null;
                if (IsolatedModeSwitcher.isAppInStaticIsolatedMode(t5Var) && !z && l2.a(t5Var)) {
                    j2.a(t5Var);
                }
            }
            ga.a(str);
            t5 t5Var2 = this.f260a;
            int i = LambortishClock.ChangeTimestampsBroadcastReceiver.f554a;
            if (((v7) t5Var2.getSystemService("dcp_data_storage_factory")).b()) {
                a(LambortishClock.ChangeTimestampsBroadcastReceiver.class, 1);
            }
            ga.a(str);
            ga.a(str);
            t5 t5Var3 = this.f260a;
            String str2 = DirtyDataSyncingService.c;
            if (!((v7) t5Var3.getSystemService("dcp_data_storage_factory")).b()) {
                a(DirtyDataSyncingService.class, 2);
            }
            t5 t5Var4 = this.f260a;
            int i2 = DatabaseCleaner.DatabaseCleaningService.c;
            if (!((v7) t5Var4.getSystemService("dcp_data_storage_factory")).b()) {
                a(DatabaseCleaner.DatabaseCleaningService.class, 2);
            }
            i5 i5Var = this.b;
            String str3 = SessionUserChangedToAccountForPackageChangedAdpater.f150a;
            if (!qe.k(i5Var.f382a)) {
                a(SessionUserChangedToAccountForPackageChangedAdpater.class, 2);
            }
            ga.a(str);
            ga.a(str);
            t5 t5Var5 = this.f260a;
            String str4 = BootstrapSSOService.b;
            if (!IsolatedModeSwitcher.isAppInStaticIsolatedMode(t5Var5)) {
                a(BootstrapSSOService.class, 1);
            }
            ga.a(str);
            a(this.f260a, 4);
            a(this.f260a, 5);
            UserDictionaryHelper.a(this.f260a);
            u7 u7VarA2 = t5.a(this.f260a).a();
            try {
                Log.i(ga.a(str), "Start update legacy authportal domain in database if needed");
                if (u7VarA2 instanceof y7) {
                    for (String str5 : u7VarA2.b()) {
                        String str6 = c;
                        ga.a(str6);
                        String strD = u7VarA2.d(str5, "authDomain");
                        String strA = q9.a(strD);
                        if (!TextUtils.equals(strD, strA)) {
                            ga.a(str6);
                            u7VarA2.d(str5, "authDomain", strA);
                            yd.b("DetectFixLegacyAuthPortalLWADomain", new String[0]);
                        }
                    }
                    Log.i(ga.a(c), "Legacy authportal domain in database is up to database");
                    return;
                }
                return;
            } catch (Exception e) {
                Log.e(ga.a(c), "Cannot fix legacy authportal domain in database", e);
                return;
            }
        }
        Log.e(ga.a(c), "Cannot do MAP init tasks on the main thread!");
        throw new IllegalStateException("Cannot do MAP init tasks on the main thread!");
    }

    public static void a(Context context, int i) {
        if (!qe.a() || xa.a()) {
            new f8(context, "SSOInfo.config", 0).a("SSOInfo.config", i);
        }
    }
}
