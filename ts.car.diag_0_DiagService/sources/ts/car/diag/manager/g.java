package ts.car.diag.manager;

import android.content.Context;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static g f1603a;

    public g(Context context) {
    }

    public static synchronized g a(Context context) {
        if (f1603a == null) {
            f1603a = new g(context);
        }
        return f1603a;
    }

    public byte b(byte b2) {
        q.a("CarMediaEffectManager", "setDisableAudioEffect.in data : " + ((int) b2));
        q.a("CarMediaEffectManager", "setDisableAudioEffect.out result : 1");
        return (byte) 1;
    }
}
