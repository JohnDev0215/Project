package defpackage;

import android.content.Context;
import android.util.SparseIntArray;

/* JADX INFO: renamed from: Df, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0059Df {
    public final SparseIntArray a = new SparseIntArray();
    public AbstractC1278oc b;

    public C0059Df(AbstractC1278oc abstractC1278oc) {
        Cif.f(abstractC1278oc);
        this.b = abstractC1278oc;
    }

    public final int a(Context context, InterfaceC1882zc interfaceC1882zc) {
        Cif.f(context);
        Cif.f(interfaceC1882zc);
        int minApkVersion = interfaceC1882zc.getMinApkVersion();
        int iD = this.a.get(minApkVersion, -1);
        if (iD != -1) {
            return iD;
        }
        int i = 0;
        while (true) {
            if (i < this.a.size()) {
                int iKeyAt = this.a.keyAt(i);
                if (iKeyAt > minApkVersion && this.a.get(iKeyAt) == 0) {
                    iD = 0;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        if (iD == -1) {
            iD = this.b.d(context, minApkVersion);
        }
        this.a.put(minApkVersion, iD);
        return iD;
    }
}
