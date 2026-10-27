package org.chromium.chrome.browser.signin;

import J.N;
import android.accounts.Account;
import android.util.Log;
import defpackage.c6;
import defpackage.d73;
import defpackage.dr;
import defpackage.f52;
import defpackage.fa1;
import defpackage.im2;
import defpackage.n3;
import defpackage.nm2;
import defpackage.oj2;
import defpackage.om2;
import defpackage.pm2;
import defpackage.rh3;
import defpackage.s62;
import defpackage.x10;
import defpackage.zi0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.browsing_data.BrowsingDataBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.components.signin.base.AccountInfo;
import org.chromium.components.signin.base.CoreAccountId;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.components.signin.identitymanager.AccountTrackerService;
import org.chromium.components.signin.identitymanager.IdentityManager;
import org.chromium.components.signin.identitymanager.IdentityMutator;

/* JADX INFO: loaded from: classes2.dex */
public class SigninManagerImpl implements IdentityManager.a, SigninManager {
    public static final int[] o = {0, 1, 2, 3, 4};
    public long e;
    public final AccountTrackerService f;
    public final IdentityManager g;
    public final IdentityMutator h;
    public final org.chromium.base.b<SigninManager.b> i = new org.chromium.base.b<>();
    public final ArrayList j = new ArrayList();
    public boolean k;
    public b l;
    public c m;
    public boolean n;

    public class a implements Runnable {
        public final /* synthetic */ BookmarkModel e;
        public final /* synthetic */ Runnable f;

        /* JADX INFO: renamed from: org.chromium.chrome.browser.signin.SigninManagerImpl$a$a, reason: collision with other inner class name */
        public class C0113a implements BrowsingDataBridge.a {
            public C0113a() {
            }

            @Override // org.chromium.chrome.browser.browsing_data.BrowsingDataBridge.a
            public final void s() {
                a aVar = a.this;
                SigninManagerImpl.this.n = false;
                aVar.f.run();
                SigninManagerImpl.this.s();
            }
        }

        public a(BookmarkModel bookmarkModel, Runnable runnable) {
            this.e = bookmarkModel;
            this.f = runnable;
        }

        @Override // java.lang.Runnable
        public final void run() {
            BookmarkModel bookmarkModel = this.e;
            bookmarkModel.getClass();
            Object obj = ThreadUtils.a;
            long j = bookmarkModel.a;
            if (j != 0) {
                N.M70Imm05(j, bookmarkModel);
            }
            BrowsingDataBridge.b().a(new C0113a(), SigninManagerImpl.o, 4);
        }
    }

    public static class b {
        public final Integer a;
        public final SigninManager.a b;
        public final Account c;
        public AccountInfo d;

        public b(Integer num, Account account, SigninManager.a aVar) {
            this.a = num;
            this.c = account;
            this.b = aVar;
        }
    }

    public static class c {
        public final SigninManager.c a;
        public final int b;

        public c(im2 im2Var, int i) {
            this.a = im2Var;
            this.b = i;
        }
    }

    public SigninManagerImpl(long j, AccountTrackerService accountTrackerService, IdentityManager identityManager, IdentityMutator identityMutator) {
        Object obj = ThreadUtils.a;
        this.e = j;
        this.f = accountTrackerService;
        this.g = identityManager;
        this.h = identityMutator;
        this.k = N.Mo0prJ3k(j);
    }

    @CalledByNative
    public static SigninManager create(long j, AccountTrackerService accountTrackerService, IdentityManager identityManager, IdentityMutator identityMutator) {
        SigninManagerImpl signinManagerImpl = new SigninManagerImpl(j, accountTrackerService, identityManager, identityMutator);
        identityManager.b.a(signinManagerImpl);
        s62 s62Var = n3.E;
        if (s62Var == null || !s62Var.d()) {
            org.chromium.components.signin.identitymanager.b bVar = new org.chromium.components.signin.identitymanager.b(identityManager, accountTrackerService);
            s62 s62Var2 = n3.E;
            if (s62Var2 == null) {
                n3.E = s62.c(bVar);
            } else {
                s62Var2.b(bVar);
            }
        }
        CoreAccountInfo coreAccountInfoA = identityManager.a(0);
        N.McMy7mwQ(signinManagerImpl.h.a, coreAccountInfoA == null ? null : coreAccountInfoA.getId());
        return signinManagerImpl;
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final void a(String str, x10 x10Var) {
        N.M7ZP5quR(this.e, (AccountInfo) N.MAwvRw4K(this.g.a, str), x10Var);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final boolean b() {
        if (this.m != null || this.l != null || this.g.a(0) == null) {
            return false;
        }
        Profile profileD = Profile.d();
        return !N.MBL3czGJ(profileD.b, profileD);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final void c(Account account, SigninManager.a aVar) {
        t(new b(null, account, aVar));
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final void d(int i, Account account, SigninManager.a aVar) {
        t(new b(Integer.valueOf(i), account, aVar));
    }

    @CalledByNative
    public void destroy() {
        n3.S().destroy();
        this.g.c(this);
        this.e = 0L;
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final boolean e() {
        return !this.k;
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final String f() {
        return N.MM6ImjTk(this.e);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final void g(Runnable runnable) {
        this.n = true;
        BookmarkModel bookmarkModelV = BookmarkModel.v(Profile.d());
        bookmarkModelV.g(new a(bookmarkModelV, runnable));
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final void h(int i, im2 im2Var) {
        String strF = f();
        this.m = new c(im2Var, strF != null ? 2 : 0);
        fa1.g("SigninManager", "Signing out, dataWipeAction: %d", Integer.valueOf(strF != null ? 2 : 0));
        N.Mw3X2cb0(this.h.a, i, 2);
        PostTask.c(rh3.a, new nm2(this, 1));
        om2 om2Var = new om2(this, 0);
        fa1.g("SigninManager", "Native signout complete, wiping data (user callback: %s)", Integer.valueOf(this.m.b));
        pm2.b.a.o("google.services.username", null);
        c cVar = this.m;
        SigninManager.c cVar2 = cVar.a;
        int i2 = cVar.b;
        if (i2 == 0) {
            N.M3tTsu$h(this.e, om2Var);
        } else if (i2 == 1) {
            g(om2Var);
        } else if (i2 == 2) {
            N.MyfLWqOr(this.e, om2Var);
        }
        AccountTrackerService accountTrackerService = this.f;
        Objects.requireNonNull(accountTrackerService);
        ThreadUtils.e(new f52(8, accountTrackerService));
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final void i(SigninManager.b bVar) {
        this.i.a(bVar);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final void j() {
        if (this.l == null && this.k && this.g.a(1) == null) {
            o();
        }
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final IdentityManager l() {
        return this.g;
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final void n(SigninManager.b bVar) {
        this.i.c(bVar);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final void o() {
        if (c6.e()) {
            return;
        }
        zi0.b.getClass();
    }

    @CalledByNative
    public final void onSigninAllowedByPolicyChanged(boolean z) {
        this.k = z;
        PostTask.c(rh3.a, new nm2(this, 0));
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final String p(String str) {
        return N.MiQjxiSl(str);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public final void q(Runnable runnable) {
        Object obj = ThreadUtils.a;
        if ((this.l == null && this.m == null && !this.n) ? false : true) {
            this.j.add(runnable);
        } else {
            PostTask.c(rh3.a, runnable);
        }
    }

    public final void r() {
        CoreAccountId id = this.l.d.getId();
        IdentityMutator identityMutator = this.h;
        N.McMy7mwQ(identityMutator.a, id);
        b bVar = this.l;
        int iMASdubqY = N.MASdubqY(identityMutator.a, bVar.d.getId(), bVar.a != null ? 1 : 0);
        if (iMASdubqY != 0) {
            fa1.k("SigninManager", "SetPrimaryAccountError in IdentityManager: %d, aborting signin", Integer.valueOf(iMASdubqY));
            b bVar2 = this.l;
            this.l = null;
            s();
            oj2.U(bVar2.a.intValue(), 46, "Signin.SigninAbortedAccessPoint");
            SigninManager.a aVar = bVar2.b;
            if (aVar != null) {
                aVar.b();
            }
            N.MREkQQeM(this.e);
            PostTask.c(rh3.a, new nm2(this, 0));
            return;
        }
        b bVar3 = this.l;
        if (bVar3.a != null) {
            pm2.b.a.o("google.services.username", bVar3.d.getEmail());
            n3.X0("Signin_Signin_Succeed");
            oj2.U(this.l.a.intValue(), 46, "Signin.SigninCompletedAccessPoint");
            oj2.U(0, 7, "Signin.SigninReason");
        }
        SigninManager.a aVar2 = this.l.b;
        if (aVar2 != null) {
            aVar2.a();
        }
        Log.i("cr_SigninManager", "Signin completed.");
        this.l = null;
        s();
        d73 d73Var = rh3.a;
        PostTask.c(d73Var, new nm2(this, 0));
        PostTask.c(d73Var, new nm2(this, 1));
        Iterator<SigninManager.b> it = this.i.iterator();
        while (true) {
            b.a aVar3 = (b.a) it;
            if (!aVar3.hasNext()) {
                return;
            } else {
                ((SigninManager.b) aVar3.next()).c();
            }
        }
    }

    public final void s() {
        Object obj = ThreadUtils.a;
        while (true) {
            ArrayList arrayList = this.j;
            if (arrayList.isEmpty()) {
                return;
            }
            Object obj2 = ThreadUtils.a;
            if ((this.l == null && this.m == null && !this.n) ? false : true) {
                return;
            } else {
                PostTask.c(rh3.a, (Runnable) arrayList.remove(0));
            }
        }
    }

    public final void t(b bVar) {
        this.l = bVar;
        fa1.g("SigninManager", "Signin starts (enabling sync: %b).", Boolean.valueOf(bVar.a != null));
        n3.S().a(this.l.c.name).g(new dr(1, this));
    }
}
