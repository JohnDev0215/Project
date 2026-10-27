package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.android.amazonprofile.AmazonProfileManager;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.accounts.SubAuthenticatorConnection;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.g;
import com.amazon.identity.auth.device.metrics.SSOMetrics;
import com.amazon.identity.auth.device.p;
import com.amazon.identity.auth.device.storage.KeystoreProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class q0 {
    public static final String f = "com.amazon.identity.auth.device.q0";
    public static final ExecutorService g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f500a;
    public final z0 b;
    public final u c;
    public final a1 d;
    public final b e = new a();

    /* JADX INFO: compiled from: DCP */
    public interface b {
        SubAuthenticatorConnection a(j1 j1Var);

        p a();
    }

    /* JADX INFO: compiled from: DCP */
    public static class c implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f502a;

        public c(Context context) {
            this.f502a = context;
        }

        @Override // com.amazon.identity.auth.device.q0.b
        public SubAuthenticatorConnection a(j1 j1Var) {
            return new SubAuthenticatorConnection(j1Var, this.f502a);
        }

        @Override // com.amazon.identity.auth.device.q0.b
        public p a() {
            return new p(this.f502a);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public interface e {
    }

    /* JADX INFO: compiled from: DCP */
    public interface f {
    }

    /* JADX INFO: compiled from: DCP */
    public static class h extends r2 implements SubAuthenticatorConnection.d {
        public final SubAuthenticatorConnection d;
        public AtomicBoolean e = new AtomicBoolean(false);

        public h(SubAuthenticatorConnection subAuthenticatorConnection) {
            this.d = subAuthenticatorConnection;
        }

        @Override // com.amazon.identity.auth.device.r2
        public void startAsyncOperation() {
            if (this.d.a(this)) {
                return;
            }
            Log.e(ga.a(q0.f), "Error binding to service");
            this.e.set(false);
            asyncOperationComplete();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class i extends r2 implements SubAuthenticatorConnection.c {
        public final Object[] d = new Object[0];
        public AtomicBoolean e = new AtomicBoolean(false);
        public final SubAuthenticatorConnection f;
        public final Account g;

        /* JADX INFO: compiled from: DCP */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SubAuthenticatorConnection.CurrentState currentState;
                i iVar = i.this;
                SubAuthenticatorConnection subAuthenticatorConnection = iVar.f;
                Account account = iVar.g;
                synchronized (subAuthenticatorConnection.h) {
                    currentState = subAuthenticatorConnection.d;
                }
                if (currentState != SubAuthenticatorConnection.CurrentState.Bound) {
                    Log.e(ga.a(SubAuthenticatorConnection.i), "Cannot deregister the Sub Authenticator until the connection has been opened");
                    iVar.a(subAuthenticatorConnection, 8, "In bad state. Cannot deregister");
                    return;
                }
                i1 i1Var = new i1(subAuthenticatorConnection, iVar);
                try {
                    String str = SubAuthenticatorConnection.i;
                    String str2 = subAuthenticatorConnection.f153a.f390a;
                    ga.a(str);
                    subAuthenticatorConnection.f.getAccountRemovalAllowed(i1Var, account.type, account.name);
                } catch (RemoteException unused) {
                    subAuthenticatorConnection.a(iVar);
                } catch (RuntimeException e) {
                    Log.e(ga.a(SubAuthenticatorConnection.i), String.format("SubAuthenticator package caused run time exception in it's getAccountRemovalAllowed implementation. Error Message: %s", e.getMessage()));
                    subAuthenticatorConnection.a(iVar);
                }
            }
        }

        public i(SubAuthenticatorConnection subAuthenticatorConnection, Account account) {
            this.f = subAuthenticatorConnection;
            this.g = account;
        }

        public void a(SubAuthenticatorConnection subAuthenticatorConnection, int i, String str) {
            synchronized (this.d) {
                Log.e(ga.a(q0.f), String.format(Locale.ENGLISH, "SubAuth Deregister Error: errorCode=%d, msg=%s", Integer.valueOf(i), str));
                this.e.set(false);
                asyncOperationComplete();
            }
        }

        @Override // com.amazon.identity.auth.device.r2
        public void onTimeout() {
            synchronized (this.d) {
                String str = q0.f;
                Log.e(ga.a(str), String.format("SubAuth Deregister Timeout", new Object[0]));
                this.e.set(false);
                asyncOperationComplete();
            }
        }

        @Override // com.amazon.identity.auth.device.r2
        public void startAsyncOperation() {
            q0.g.execute(new a());
        }
    }

    static {
        Executor executor = va.f637a;
        g = Executors.newFixedThreadPool(10, new ka("MAP-DeregisterThreadPool"));
    }

    public q0(Context context) {
        t5 t5VarA = t5.a(context);
        this.f500a = t5VarA;
        this.c = new u(t5VarA);
        this.b = z0.a(t5VarA);
        this.d = new a1(t5VarA);
    }

    /* JADX INFO: compiled from: DCP */
    public static class g extends r2 implements p.b {
        public AtomicBoolean d = new AtomicBoolean(false);
        public final String e;
        public final boolean f;
        public final p g;
        public final u h;
        public final d3 i;
        public final Context j;
        public final y5 k;
        public final Bundle l;

        public g(Context context, String str, boolean z, p pVar, u uVar, d3 d3Var, y5 y5Var, Bundle bundle) {
            this.j = context;
            this.e = str;
            this.f = z;
            this.g = pVar;
            this.h = uVar;
            this.i = d3Var;
            this.k = y5Var;
            this.l = bundle;
        }

        @Override // com.amazon.identity.auth.device.p.b
        public void a(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError, String str2, Bundle bundle) {
            ga.a(q0.f);
            SSOMetrics.a(registrationError);
            this.d.set(false);
            asyncOperationComplete();
        }

        @Override // com.amazon.identity.auth.device.r2
        public void startAsyncOperation() {
            this.g.a(this, this.j.getPackageName(), this.e, this.i, this.f, this.h, this.k, this.l);
        }

        @Override // com.amazon.identity.auth.device.p.b
        public void a(String str, String str2, Bundle bundle) {
            ga.a(q0.f);
            this.d.set(true);
            asyncOperationComplete();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f503a;
        public final i5 b;
        public final MAPAccountManager c;
        public final AmazonAccountManager d;
        public final u7 e;
        public final String f;
        public final ArrayList<j1> g;
        public final y5 h;
        public final d3 i;
        public final Bundle j;
        public final u k;
        public final boolean l;
        public b m;
        public f n;
        public final a1 o;

        public d(Context context, String str, Collection<j1> collection, u uVar, d3 d3Var, y5 y5Var, Bundle bundle) {
            this.f503a = context;
            this.b = (i5) context.getSystemService("sso_platform");
            this.c = new MAPAccountManager(context);
            this.o = new a1(context);
            this.d = (AmazonAccountManager) context.getSystemService("dcp_amazon_account_man");
            this.e = ((v7) context.getSystemService("dcp_data_storage_factory")).a();
            this.g = new ArrayList<>(collection);
            this.f = str;
            this.k = uVar;
            this.l = a1.a(bundle);
            this.i = d3Var;
            this.h = y5Var;
            this.j = bundle;
        }

        public boolean a(Account account, SubAuthenticatorConnection subAuthenticatorConnection) {
            String str = q0.f;
            String str2 = subAuthenticatorConnection.f153a.f390a;
            ga.a(str);
            i iVar = new i(subAuthenticatorConnection, account);
            String str3 = subAuthenticatorConnection.f153a.f390a;
            SSOMetrics.a();
            de deVarA = de.a(SSOMetrics.c, "DeregistrationSubAuthTime", str3);
            deVarA.b();
            iVar.run(7L, TimeUnit.SECONDS, "NotifySubAuthAccountRemoval");
            deVarA.c();
            return iVar.e.get();
        }

        public synchronized b b() {
            if (this.m == null) {
                this.m = new c(this.f503a);
            }
            return this.m;
        }

        public final boolean c() {
            if (this.d.f144a.d(this.f, "has.notified.server.of.deregister") != null) {
                ga.a(q0.f, "Have already notified server of deregister of %s", this.f);
                return true;
            }
            g gVar = new g(this.f503a, this.f, this.l, b().a(), this.k, this.i, this.h, this.j);
            gVar.run();
            boolean z = gVar.d.get();
            if (z) {
                Iterator<String> it = (this.l ? this.c.getAccounts() : com.amazon.identity.auth.device.e.a(this.f)).iterator();
                while (it.hasNext()) {
                    this.d.f144a.d(it.next(), "has.notified.server.of.deregister", "true");
                }
            } else {
                Log.e(ga.a(q0.f), "Deregister was not successful. Not marking accounts that they were deregistered on the server");
            }
            return z;
        }

        public final boolean d() {
            SubAuthenticatorConnection subAuthenticatorConnection;
            Account accountA = r9.a(this.f503a, this.f);
            if (accountA == null) {
                Log.e(ga.a(q0.f), "Sub authenticators are not supported on 3rd party devices yet");
                return true;
            }
            boolean z = true;
            for (j1 j1Var : this.g) {
                h hVar = new h(b().a(j1Var));
                hVar.run();
                if (hVar.e.get()) {
                    subAuthenticatorConnection = hVar.d;
                } else {
                    Log.e(ga.a(q0.f), "Failed to establish SubAuthenticator Connection");
                    subAuthenticatorConnection = null;
                }
                if (subAuthenticatorConnection == null) {
                    String str = j1Var.f390a;
                    SSOMetrics.a();
                    SSOMetrics.a("DeregistrationFailure", str);
                    z = false;
                } else {
                    try {
                        if (!a(accountA, subAuthenticatorConnection)) {
                            String str2 = subAuthenticatorConnection.f153a.f390a;
                            SSOMetrics.a();
                            SSOMetrics.a("DeregistrationFailure", str2);
                            z = false;
                        }
                    } finally {
                        subAuthenticatorConnection.a();
                    }
                }
            }
            return z;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            f fVar;
            if (this.d.a(this.f)) {
                SSOMetrics.a();
                de deVarA = de.a(SSOMetrics.c, "DeregistrationTime", "TotalDeregistrationTime");
                deVarA.b();
                boolean zA = a();
                deVarA.c();
                z = zA;
            } else {
                SSOMetrics.a(MAPAccountManager.RegistrationError.ALREADY_DEREGISTERED);
                z = false;
            }
            synchronized (this) {
                fVar = this.n;
            }
            if (fVar != null) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("booleanResult", z);
                g.a aVar = (g.a) ((r0) fVar).f520a;
                aVar.getClass();
                boolean z2 = bundle.getBoolean("booleanResult");
                if (z2) {
                    Log.i(ga.a(com.amazon.identity.auth.device.g.r), "Device deregistration success");
                } else {
                    Log.w(ga.a(com.amazon.identity.auth.device.g.r), "Device deregistration failed");
                }
                t5 t5Var = com.amazon.identity.auth.device.g.this.f335a;
                boolean z3 = aVar.f336a;
                String str = aVar.b;
                Account account = aVar.c;
                Set set = aVar.d;
                Bundle bundle2 = aVar.e;
                x.a(t5Var, z3, str, account, null, set, bundle2 != null ? bundle2.getBundle(MAPAccountManager.KEY_CLIENT_EVENT_CONTEXT) : null);
                IsolatedModeSwitcher.switchAppToSSOModeIfNecessary(com.amazon.identity.auth.device.g.this.f335a);
                com.amazon.identity.auth.device.g.this.a(aVar.f, z2);
            }
        }

        public final boolean a() {
            boolean zBooleanValue;
            HashSet<String> hashSet;
            boolean zD = d();
            if (!c()) {
                zD = false;
            }
            if (qe.m(this.b.f382a)) {
                String str = this.f;
                u7 u7Var = this.e;
                synchronized (com.amazon.identity.auth.device.e.class) {
                    String strC = u7Var.c(str, "overriding_dsn_child_device_types_key");
                    if (!TextUtils.isEmpty(strC)) {
                        hashSet = new HashSet(Arrays.asList(TextUtils.isEmpty(strC) ? new String[0] : TextUtils.split(strC, ";")));
                    } else {
                        hashSet = new HashSet();
                    }
                }
                if (!hashSet.isEmpty()) {
                    for (String str2 : hashSet) {
                        p pVarA = b().a();
                        s0 s0Var = new s0(this);
                        q0.g.execute(new t0(this, pVarA, s0Var, str2, pVarA.a(this.f, str2, s0Var)));
                    }
                }
            }
            String str3 = q0.f;
            Log.i(ga.a(str3), "Deregister dependent accounts");
            boolean z = true;
            if (!this.l && !this.j.getBoolean("DeregisteringDefaultPrimary")) {
                LinkedList<String> linkedList = new LinkedList();
                Set<String> setA = this.d.a();
                if (setA != null) {
                    for (String str4 : setA) {
                        if (!str4.equals(this.f) && this.f.equals(this.e.d(str4, "com.amazon.dcp.sso.property.account.delegateeaccount"))) {
                            linkedList.add(str4);
                        }
                    }
                    for (String str5 : linkedList) {
                        this.c.deregisterAccount(str5, new u0(this, str5));
                    }
                }
            } else {
                Log.i(ga.a(str3), "Deregister the secondary accounts");
                AmazonAccountManager amazonAccountManager = this.d;
                Set<String> setA2 = amazonAccountManager.a();
                HashSet<String> hashSet2 = new HashSet();
                for (String str6 : setA2) {
                    if (amazonAccountManager.e(str6)) {
                        hashSet2.add(str6);
                    }
                }
                for (String str7 : hashSet2) {
                    if (!this.l) {
                        if (this.o.a(str7)) {
                            String str8 = q0.f;
                            String.format("keeping the secondary primary account %s", str7);
                        } else {
                            String strD = this.e.d(str7, "com.amazon.dcp.sso.property.account.delegateeaccount");
                            if (!TextUtils.isEmpty(strD) && !strD.equals(this.f) && this.o.a(strD)) {
                                String str9 = q0.f;
                                String.format("keeping the delegated account %s", str7);
                            }
                        }
                    }
                    String str10 = q0.f;
                    String.format("Deregister the secondary account %s", str7);
                    this.c.deregisterAccount(str7, new v0(this, str7));
                }
            }
            Context context = this.f503a;
            String str11 = this.f;
            boolean zD2 = this.o.b.d(str11);
            if (qe.n(context)) {
                Boolean bool = qe.l;
                if (bool != null) {
                    zBooleanValue = bool.booleanValue();
                } else {
                    Boolean boolValueOf = Boolean.FALSE;
                    PackageManager packageManager = context.getPackageManager();
                    if (packageManager != null && packageManager.hasSystemFeature("com.fireos.sdk.amazon_profile_service", 11)) {
                        boolValueOf = Boolean.valueOf(packageManager.checkPermission("com.amazon.permission.SET_PROFILE", context.getPackageName()) == 0);
                    }
                    qe.l = boolValueOf;
                    zBooleanValue = boolValueOf.booleanValue();
                }
            } else {
                zBooleanValue = false;
            }
            if (!zBooleanValue) {
                Log.w(ga.a("AmazonProfileManagerSafeConsumer"), "Not removing profiles for the account as either APS is not on this device or APS version is older than 11 or required permission not granted.");
            } else {
                AmazonProfileManager amazonProfileManager = AmazonProfileManager.getAmazonProfileManager(context);
                int iUnregisterAccount = amazonProfileManager.unregisterAccount(amazonProfileManager.getDefaultProgramIdOnDevice(), str11, zD2);
                String str12 = TextUtils.isEmpty(str11) ? "No directedId" : "***" + str11.substring(str11.length() - Math.min(2, str11.length()));
                if (iUnregisterAccount == AmazonProfileManager.SUCCESS) {
                    Log.i(ga.a("AmazonProfileManagerSafeConsumer"), String.format("Removing all profiles from APS for current deregistered account %s success! Primary account: %b", str12, Boolean.valueOf(zD2)));
                } else {
                    Log.e(ga.a("AmazonProfileManagerSafeConsumer"), String.format("Removing all profiles from APS for current deregistered account %s is not success. Primary account: %b", str12, Boolean.valueOf(zD2)));
                }
            }
            this.e.e(this.f);
            try {
                KeystoreProvider keystoreProvider = new KeystoreProvider(String.format("%s_%s", "mobile_auth_storage", this.f));
                if (keystoreProvider.a() != null) {
                    try {
                        keystoreProvider.f552a.deleteEntry(keystoreProvider.b);
                    } catch (Exception e) {
                        throw new KeystoreProvider.KeystoreProviderException(MAPError.CommonError.INTERNAL_ERROR, e.getMessage(), e);
                    }
                }
                f8.a(this.f503a, "mobile_auth_storage").a();
            } catch (KeystoreProvider.KeystoreProviderException unused) {
                z = false;
            }
            boolean z2 = z ? zD : false;
            f8.a(this.f503a, "actor_info_storage_" + this.f).a();
            try {
                f8.a(this.f503a, "DMS_ATS").a();
            } catch (Exception e2) {
                Log.e(ga.a(q0.f), "Something went wrong when clearing account transfer info", e2);
            }
            return z2;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class a implements b {
        public a() {
        }

        @Override // com.amazon.identity.auth.device.q0.b
        public SubAuthenticatorConnection a(j1 j1Var) {
            q0 q0Var = q0.this;
            q0Var.getClass();
            return new SubAuthenticatorConnection(j1Var, q0Var.f500a);
        }

        @Override // com.amazon.identity.auth.device.q0.b
        public p a() {
            q0 q0Var = q0.this;
            q0Var.getClass();
            return new p(q0Var.f500a);
        }
    }
}
