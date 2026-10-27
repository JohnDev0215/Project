package p;

import com.google.common.collect.ImmutableList;
import com.spotify.connectivity.productstateesperanto.RxProductStateImpl;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.PlayerQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ga1 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ String e;

    public /* synthetic */ ga1(String str, int i) {
        this.c = i;
        this.e = str;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        int i = this.c;
        String str = this.e;
        switch (i) {
            case 0:
                PlayerQueue playerQueue = (PlayerQueue) obj;
                ArrayList arrayListD = he1.D(playerQueue.b());
                Iterator it = arrayListD.iterator();
                int i2 = 0;
                while (true) {
                    if (it.hasNext()) {
                        ContextTrack contextTrack = (ContextTrack) it.next();
                        if (contextTrack == null || !str.equals(contextTrack.f())) {
                            i2++;
                        }
                    } else {
                        i2 = -1;
                    }
                }
                if (-1 != i2) {
                    arrayListD.remove(i2);
                }
                return playerQueue.e().b(ImmutableList.o(arrayListD)).a();
            case 1:
                return RxProductStateImpl.lambda$productStateKeyV2$1(str, (Map) obj);
            default:
                return RxProductStateImpl.lambda$productStateKey$0(str, (Map) obj);
        }
    }
}
