package defpackage;

import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: renamed from: Md, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0218Md {
    public final int a;

    public AbstractC0218Md(int i) {
        this.a = i;
    }

    public static Status d(RemoteException remoteException) {
        StringBuilder sb = new StringBuilder();
        if (remoteException instanceof TransactionTooLargeException) {
            sb.append("TransactionTooLargeException: ");
        }
        sb.append(remoteException.getLocalizedMessage());
        return new Status(8, sb.toString());
    }

    public abstract void a(C0415Xc c0415Xc);

    public abstract void b(C1389qd c1389qd, boolean z);

    public abstract void c(Exception exc);

    public abstract void e(Status status);
}
