package defpackage;

import J.N;
import android.util.Pair;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wb0 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ long f;

    public /* synthetic */ wb0(int i, long j) {
        this.e = i;
        this.f = j;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        long j = this.f;
        switch (i) {
            case 0:
                Pair pair = (Pair) obj;
                N.MLbF8aR_(j, ((Boolean) pair.first).booleanValue(), (String) pair.second);
                break;
            default:
                if (!((Boolean) obj).booleanValue()) {
                    N.MN7OOxaa(j);
                }
                break;
        }
    }
}
