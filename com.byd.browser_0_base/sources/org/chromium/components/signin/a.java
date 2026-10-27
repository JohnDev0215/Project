package org.chromium.components.signin;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import defpackage.AbstractC0200n0;
import defpackage.C0213u0;
import defpackage.InterfaceC0207r0;
import defpackage.aq3;
import defpackage.ay2;
import defpackage.by2;
import defpackage.c6;
import defpackage.gy1;
import defpackage.i40;
import defpackage.i9;
import defpackage.k1;
import defpackage.m62;
import defpackage.n3;
import defpackage.oj2;
import defpackage.s62;
import defpackage.se3;
import defpackage.xz;
import defpackage.z1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.a;
import org.chromium.components.signin.base.AccountCapabilities;
import org.chromium.components.signin.base.CoreAccountInfo;

/* JADX INFO: compiled from: AccountManagerFacadeImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a implements AccountManagerFacade {
    public final InterfaceC0207r0 a;
    public final org.chromium.base.b<z1> b = new org.chromium.base.b<>();
    public final AtomicReference<List<Account>> c = new AtomicReference<>();
    public final AtomicReference<List<gy1>> d = new AtomicReference<>();
    public s62<List<Account>> e = new s62<>();
    public s62<List<CoreAccountInfo>> f = new s62<>();

    /* JADX INFO: renamed from: org.chromium.components.signin.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccountManagerFacadeImpl.java */
    public class C0142a extends i9<Boolean> {
        public final /* synthetic */ Account h;
        public final /* synthetic */ AccountManagerFacade.a i;

        public C0142a(Account account, AccountManagerFacade.a aVar) {
            this.h = account;
            this.i = aVar;
        }

        @Override // defpackage.i9
        public final Boolean b() {
            InterfaceC0207r0 interfaceC0207r0 = a.this.a;
            Account account = this.h;
            by2 by2Var = (by2) interfaceC0207r0;
            by2Var.getClass();
            String[] strArr = {"service_usm"};
            boolean zBooleanValue = false;
            if (c6.a(Process.myPid(), Process.myUid(), xz.a, "android.permission.GET_ACCOUNTS") == 0) {
                try {
                    zBooleanValue = by2Var.a.hasFeatures(account, strArr, null, null).getResult().booleanValue();
                } catch (AuthenticatorException | OperationCanceledException | IOException e) {
                    Log.e("cr_Auth", "Error while checking features: ", e);
                }
            }
            return Boolean.valueOf(zBooleanValue);
        }

        @Override // defpackage.i9
        public final void l(Boolean bool) {
            Boolean bool2 = bool;
            boolean zBooleanValue = bool2.booleanValue();
            bool2.booleanValue();
            this.i.k(zBooleanValue);
        }
    }

    /* JADX INFO: compiled from: AccountManagerFacadeImpl.java */
    public class b extends i9<AccountCapabilities> {
        public final /* synthetic */ s62 h;

        public b(Account account, s62 s62Var) {
            this.h = s62Var;
        }

        @Override // defpackage.i9
        public final AccountCapabilities b() {
            HashMap map = new HashMap();
            for (String str : AbstractC0200n0.a) {
                InterfaceC0207r0 interfaceC0207r0 = a.this.a;
                if (str.startsWith("accountcapabilities/")) {
                    str.substring(20);
                }
                ((by2) interfaceC0207r0).getClass();
                oj2.U(15, 22, "Signin.AccountCapabilities.GetFromSystemLibraryResult");
                map.put(str, 0);
            }
            HashMap map2 = new HashMap();
            for (String str2 : AbstractC0200n0.a) {
                int iIntValue = ((Integer) map.get(str2)).intValue();
                if (iIntValue != 0) {
                    map2.put(str2, Boolean.valueOf(iIntValue == 1));
                }
            }
            return new AccountCapabilities(map2);
        }

        @Override // defpackage.i9
        public final void l(AccountCapabilities accountCapabilities) {
            this.h.b(accountCapabilities);
        }
    }

    public a(by2 by2Var) {
        Object obj = ThreadUtils.a;
        this.a = by2Var;
        by2Var.b = new z1() { // from class: s0
            @Override // defpackage.z1
            public final void F() {
                a aVar = this.e;
                aVar.getClass();
                Object obj2 = ThreadUtils.a;
                new C0213u0(aVar).c(i9.f);
            }
        };
        Context context = xz.a;
        ay2 ay2Var = new ay2(by2Var);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.accounts.LOGIN_ACCOUNTS_CHANGED");
        xz.g(context, ay2Var, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter2.addDataScheme("package");
        intentFilter2.addDataPath("com.google.android.gms", 1);
        xz.g(context, ay2Var, intentFilter2);
        int i = 9;
        new k1(new aq3(i, this));
        this.e.g(new m62(i));
        new C0213u0(this).c(i9.f);
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public final void a(z1 z1Var) {
        Object obj = ThreadUtils.a;
        this.b.a(z1Var);
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public final s62<List<Account>> b() {
        Object obj = ThreadUtils.a;
        return this.e;
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public final void c() {
        this.a.getClass();
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public final s62<AccountCapabilities> d(Account account) {
        Object obj = ThreadUtils.a;
        s62<AccountCapabilities> s62Var = new s62<>();
        new b(account, s62Var).c(i9.e);
        return s62Var;
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public final void e() {
        this.a.getClass();
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public final void f(Account account, Activity activity, final Callback<Boolean> callback) {
        by2 by2Var = (by2) this.a;
        by2Var.getClass();
        Object obj = ThreadUtils.a;
        AccountManagerCallback<Bundle> accountManagerCallback = new AccountManagerCallback() { // from class: zx2
            @Override // android.accounts.AccountManagerCallback
            public final void run(AccountManagerFuture accountManagerFuture) {
                Bundle bundle;
                try {
                    bundle = (Bundle) accountManagerFuture.getResult();
                } catch (AuthenticatorException | IOException e) {
                    Log.e("cr_Auth", "Error while update credentials: ", e);
                    bundle = null;
                } catch (OperationCanceledException unused) {
                    Log.w("cr_Auth", "Updating credentials was cancelled.");
                    bundle = null;
                }
                boolean z = (bundle == null || bundle.getString("accountType") == null) ? false : true;
                Callback callback2 = callback;
                if (callback2 != null) {
                    callback2.onResult(Boolean.valueOf(z));
                }
            }
        };
        by2Var.a.updateCredentials(account, "android", new Bundle(), activity, accountManagerCallback, null);
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public final void g(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        d dVar = new d(new i40(this, str));
        Object obj = ThreadUtils.a;
        dVar.g.set(false);
        new c(dVar).c(i9.e);
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public final void h(Account account, AccountManagerFacade.a aVar) {
        Object obj = ThreadUtils.a;
        new C0142a(account, aVar).c(i9.e);
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public final s62<List<CoreAccountInfo>> i() {
        Object obj = ThreadUtils.a;
        return this.f;
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public final void j(z1 z1Var) {
        Object obj = ThreadUtils.a;
        this.b.c(z1Var);
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public final void k() {
        n3.X0("Signin_AddAccountToDevice");
        this.a.getClass();
    }

    public final void l() {
        List<Account> listUnmodifiableList;
        AtomicReference<List<Account>> atomicReference = this.c;
        if (atomicReference.get() == null) {
            return;
        }
        AtomicReference<List<gy1>> atomicReference2 = this.d;
        if (atomicReference2.get() == null) {
            return;
        }
        if (atomicReference2.get().isEmpty()) {
            listUnmodifiableList = atomicReference.get();
        } else {
            ArrayList arrayList = new ArrayList();
            for (Account account : atomicReference.get()) {
                Iterator<gy1> it = atomicReference2.get().iterator();
                while (true) {
                    if (it.hasNext()) {
                        gy1 next = it.next();
                        String str = account.name;
                        ArrayList arrayList2 = next.a;
                        boolean zEquals = false;
                        if (arrayList2.size() == 1) {
                            zEquals = str.equals(arrayList2.get(0));
                        } else {
                            String str2 = (String) arrayList2.get(0);
                            if (str.startsWith(str2)) {
                                String str3 = (String) arrayList2.get(arrayList2.size() - 1);
                                if (str.endsWith(str3)) {
                                    int length = str2.length();
                                    Iterator it2 = arrayList2.subList(1, arrayList2.size() - 1).iterator();
                                    while (true) {
                                        if (it2.hasNext()) {
                                            String str4 = (String) it2.next();
                                            int iIndexOf = str.indexOf(str4, length);
                                            if (iIndexOf == -1) {
                                                break;
                                            } else {
                                                length = iIndexOf + str4.length();
                                            }
                                        } else if (str3.length() + length <= str.length()) {
                                            zEquals = true;
                                        }
                                    }
                                }
                            }
                        }
                        if (zEquals) {
                            arrayList.add(account);
                            break;
                        }
                    }
                }
            }
            listUnmodifiableList = Collections.unmodifiableList(arrayList);
        }
        if (this.e.d()) {
            this.e = s62.c(listUnmodifiableList);
        } else {
            this.e.b(listUnmodifiableList);
        }
        Iterator<z1> it3 = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it3;
            if (!aVar.hasNext()) {
                Object obj = ThreadUtils.a;
                this.e.g(new se3(20, this));
                return;
            }
            ((z1) aVar.next()).F();
        }
    }
}
