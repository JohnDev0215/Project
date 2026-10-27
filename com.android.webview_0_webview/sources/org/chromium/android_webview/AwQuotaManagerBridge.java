package org.chromium.android_webview;

import J.N;
import android.util.SparseArray;
import defpackage.C0409Wo;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwQuotaManagerBridge {
    public long a;
    public int b;
    public SparseArray c = new SparseArray();
    public SparseArray d = new SparseArray();
    public SparseArray e = new SparseArray();

    public AwQuotaManagerBridge(long j) {
        this.a = j;
        N.MIrzsg_q(this.a, this);
    }

    private void onGetOriginsCallback(int i, String[] strArr, long[] jArr, long[] jArr2) {
        ((Callback) this.c.get(i)).a(new C0409Wo(strArr, jArr, jArr2));
        this.c.remove(i);
    }

    private void onGetUsageAndQuotaForOriginCallback(int i, boolean z, long j, long j2) {
        if (z) {
            ((Callback) this.d.get(i)).a(Long.valueOf(j2));
            this.d.remove(i);
        } else {
            ((Callback) this.e.get(i)).a(Long.valueOf(j));
            this.e.remove(i);
        }
    }

    public final int a() {
        ThreadUtils.b();
        int i = this.b + 1;
        this.b = i;
        return i;
    }
}
