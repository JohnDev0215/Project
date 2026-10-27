package p;

import android.content.SharedPreferences;
import com.spotify.automotive.playerapi.BitrateSetting;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ic2 implements io.reactivex.rxjava3.functions.a {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;

    public /* synthetic */ ic2(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    @Override // io.reactivex.rxjava3.functions.a
    public final void run() {
        int i = this.c;
        Object obj = this.k;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                SharedPreferences sharedPreferences = ((jc2) obj2).a.getSharedPreferences("com.spotify.auto.SETTINGS", 0);
                v03.i(sharedPreferences, "context.getSharedPrefere…LE, Context.MODE_PRIVATE)");
                sharedPreferences.edit().putInt("com.spotify.auto.STREAMING_BITRATE", ((BitrateSetting) obj).a()).apply();
                return;
            case 1:
                ((ry0) obj2).invoke((cj0) obj);
                return;
            default:
                f02 f02Var = ((k02) obj2).a;
                Map map = (Map) obj;
                i52 i52Var = f02Var.a;
                i52Var.c();
                try {
                    f02.a(f02Var, map);
                    i52Var.o();
                    return;
                } finally {
                    i52Var.k();
                }
        }
    }
}
