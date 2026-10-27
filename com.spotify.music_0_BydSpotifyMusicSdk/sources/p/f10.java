package p;

import com.spotify.player.model.PlayerError;
import com.spotify.prefs.esperanto.proto.EsPrefs$Value;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class f10 implements io.reactivex.rxjava3.functions.o {
    public static final f10 e = new f10(0);
    public static final f10 k = new f10(1);
    public final /* synthetic */ int c;

    public /* synthetic */ f10(int i) {
        this.c = i;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        switch (this.c) {
            case 0:
                return he1.C(new Pair("error", ((PlayerError) obj).c()));
            default:
                return Integer.valueOf((int) ((EsPrefs$Value) obj).g());
        }
    }
}
