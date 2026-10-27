package p;

import com.google.common.collect.ImmutableList;
import com.spotify.player.esperanto.proto.EsProvidedTrack$ProvidedTrack;
import com.spotify.player.esperanto.proto.EsQueue$Queue;
import com.spotify.player.model.PlayerQueue;
import java.util.ArrayList;
import java.util.Iterator;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class uh0 implements io.reactivex.rxjava3.functions.o, h00 {
    public static final uh0 c = new uh0();
    public static final uh0 e = new uh0();

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        EsQueue$Queue esQueue$Queue = (EsQueue$Queue) obj;
        v03.j(esQueue$Queue, "p0");
        PlayerQueue.Builder builderA = PlayerQueue.a();
        builderA.d(String.valueOf(esQueue$Queue.j()));
        if (esQueue$Queue.l()) {
            EsProvidedTrack$ProvidedTrack esProvidedTrack$ProvidedTrackK = esQueue$Queue.k();
            v03.i(esProvidedTrack$ProvidedTrackK, "protoQueue.track");
            builderA.e(b00.a(esProvidedTrack$ProvidedTrackK));
        }
        if (esQueue$Queue.f() > 0) {
            v51 v51VarG = esQueue$Queue.g();
            v03.i(v51VarG, "protoQueue.nextTracksList");
            ArrayList arrayList = new ArrayList(lu.W(v51VarG));
            Iterator<E> it = v51VarG.iterator();
            while (it.hasNext()) {
                arrayList.add(b00.a((EsProvidedTrack$ProvidedTrack) it.next()));
            }
            builderA.b(ImmutableList.o(arrayList));
        }
        if (esQueue$Queue.h() > 0) {
            v51 v51VarI = esQueue$Queue.i();
            v03.i(v51VarI, "protoQueue.prevTracksList");
            ArrayList arrayList2 = new ArrayList(lu.W(v51VarI));
            Iterator<E> it2 = v51VarI.iterator();
            while (it2.hasNext()) {
                arrayList2.add(b00.a((EsProvidedTrack$ProvidedTrack) it2.next()));
            }
            builderA.c(ImmutableList.o(arrayList2));
        }
        PlayerQueue playerQueueA = builderA.a();
        v03.i(playerQueueA, "builder().apply {\n      …)\n        }\n    }.build()");
        return playerQueueA;
    }

    @Override // p.h00
    public Object convert(Object obj) {
        return ((ResponseBody) obj).q();
    }
}
