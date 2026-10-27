package p;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import com.spotify.automotive.playerapi.BitrateSetting;

/* JADX INFO: loaded from: classes.dex */
public final class jc2 {
    public final Context a;
    public final x00 b;
    public final fh0 c;
    public final m d;

    public jc2(Context context, x00 x00Var, fh0 fh0Var, m mVar) {
        v03.j(context, "context");
        v03.j(x00Var, "player");
        v03.j(fh0Var, "esperantoApi");
        v03.j(mVar, "automotiveProperties");
        this.a = context;
        this.b = x00Var;
        this.c = fh0Var;
        this.d = mVar;
    }

    public final BitrateSetting a() {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("com.spotify.auto.SETTINGS", 0);
            v03.i(sharedPreferences, "context.getSharedPrefere…LE, Context.MODE_PRIVATE)");
            int i = sharedPreferences.getInt("com.spotify.auto.STREAMING_BITRATE", BitrateSetting.AUTO.a());
            for (BitrateSetting bitrateSetting : BitrateSetting.c) {
                if (i == bitrateSetting.a()) {
                    return bitrateSetting;
                }
            }
            return BitrateSetting.AUTO;
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }
}
