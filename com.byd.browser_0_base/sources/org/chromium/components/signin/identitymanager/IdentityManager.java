package org.chromium.components.signin.identitymanager;

import J.N;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.components.signin.base.AccountInfo;
import org.chromium.components.signin.base.CoreAccountInfo;

/* JADX INFO: loaded from: classes3.dex */
public class IdentityManager {
    public long a;
    public final org.chromium.base.b<a> b = new org.chromium.base.b<>();

    public IdentityManager(long j) {
        this.a = j;
    }

    @CalledByNative
    public static IdentityManager create(long j, ProfileOAuth2TokenServiceDelegate profileOAuth2TokenServiceDelegate) {
        return new IdentityManager(j);
    }

    public final CoreAccountInfo a(int i) {
        return (CoreAccountInfo) N.MwJ3GEOr(this.a, i);
    }

    public final boolean b(int i) {
        return a(i) != null;
    }

    public final void c(a aVar) {
        this.b.c(aVar);
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
    }

    @CalledByNative
    public void onAccountsCookieDeletedByUserAction() {
        Iterator<a> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).getClass();
            }
        }
    }

    @CalledByNative
    public void onExtendedAccountInfoUpdated(AccountInfo accountInfo) {
        Iterator<a> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).k(accountInfo);
            }
        }
    }

    @CalledByNative
    public void onPrimaryAccountChanged(PrimaryAccountChangeEvent primaryAccountChangeEvent) {
        Iterator<a> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).m(primaryAccountChangeEvent);
            }
        }
    }

    public interface a {
        default void k(AccountInfo accountInfo) {
        }

        default void m(PrimaryAccountChangeEvent primaryAccountChangeEvent) {
        }
    }

    @CalledByNative
    public final void onRefreshTokenUpdatedForAccount(CoreAccountInfo coreAccountInfo) {
    }
}
