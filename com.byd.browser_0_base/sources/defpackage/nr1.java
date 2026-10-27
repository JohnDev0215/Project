package defpackage;

import J.N;
import android.util.Log;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class nr1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ long f;

    public /* synthetic */ nr1(int i, long j) {
        this.e = i;
        this.f = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        long j = this.f;
        switch (i) {
            case 0:
                if (!or1.c(j)) {
                    Log.e("cr_omaha", "Failed to schedule job");
                } else {
                    Log.i("cr_omaha", "Scheduled using JobService");
                }
                break;
            case 1:
                N.MN7OOxaa(j);
                break;
            default:
                oj2.b0(j, "PushMessaging.TimeToReadPersistedMessages");
                break;
        }
    }
}
