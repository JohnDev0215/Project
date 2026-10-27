package defpackage;

import android.accounts.Account;
import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

/* JADX INFO: renamed from: He, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0130He extends AbstractBinderC0453Ze {
    public static Account v(InterfaceC0502af interfaceC0502af) {
        if (interfaceC0502af != null) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                C0095Ff c0095Ff = (C0095Ff) interfaceC0502af;
                Parcel parcelC = c0095Ff.c(2, c0095Ff.f());
                Account account = (Account) AbstractC0383Vg.b(parcelC, Account.CREATOR);
                parcelC.recycle();
                return account;
            } catch (RemoteException unused) {
                Log.w("AccountAccessor", "Remote account accessor probably died");
                return null;
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
        return null;
    }
}
