package org.chromium.components.signin.identitymanager;

import android.accounts.Account;
import defpackage.s62;
import defpackage.ul2;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.b;
import org.chromium.components.signin.d;
import org.chromium.components.signin.identitymanager.ProfileOAuth2TokenServiceDelegate;
import org.chromium.components.signin.identitymanager.c;

/* JADX INFO: loaded from: classes3.dex */
public final class ProfileOAuth2TokenServiceDelegate {
    public final long a;
    public final AccountTrackerService b;
    public final AccountManagerFacade c = AccountManagerFacadeProvider.getInstance();

    public interface a {
    }

    @CalledByNative
    public ProfileOAuth2TokenServiceDelegate(long j, AccountTrackerService accountTrackerService) {
        this.a = j;
        this.b = accountTrackerService;
    }

    @CalledByNative
    public final void getAccessTokenFromNative(final String str, final String str2, final long j) {
        this.c.b().g(new Callback() { // from class: j62
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                ProfileOAuth2TokenServiceDelegate profileOAuth2TokenServiceDelegate = this.e;
                profileOAuth2TokenServiceDelegate.getClass();
                Account accountD = b.d(str, (List) obj);
                long j2 = j;
                if (accountD == null) {
                    ThreadUtils.e(new uq0(2, j2));
                    return;
                }
                d dVar = new d(new org.chromium.components.signin.identitymanager.d(profileOAuth2TokenServiceDelegate, accountD, "oauth2:" + str2, new c(j2)));
                Object obj2 = ThreadUtils.a;
                dVar.g.set(false);
                new org.chromium.components.signin.c(dVar).c(i9.e);
            }
        });
    }

    @CalledByNative
    public boolean hasOAuth2RefreshToken(String str) {
        s62<List<Account>> s62VarB = this.c.b();
        return s62VarB.d() && org.chromium.components.signin.b.d(str, s62VarB.b) != null;
    }

    @CalledByNative
    public void invalidateAccessToken(String str) {
        this.c.g(str);
    }

    @CalledByNative
    public final void invalidateAccountsSeedingStatus() {
        AccountTrackerService accountTrackerService = this.b;
        if (accountTrackerService.g == 1) {
            accountTrackerService.j = true;
        } else {
            accountTrackerService.g = 0;
        }
    }

    @CalledByNative
    public void seedAndReloadAccountsWithPrimaryAccount(String str) {
        Object obj = ThreadUtils.a;
        this.b.c(new ul2(this, 3, str));
    }
}
