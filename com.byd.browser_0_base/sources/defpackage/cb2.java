package defpackage;

import org.chromium.base.Callback;

/* JADX INFO: compiled from: RequestCoordinatorBridge.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cb2 implements Callback<Integer> {
    public final /* synthetic */ Callback e = null;

    @Override // org.chromium.base.Callback
    public final void onResult(Integer num) {
        Integer num2 = num;
        Callback callback = this.e;
        if (callback != null) {
            callback.onResult(num2);
        }
    }
}
