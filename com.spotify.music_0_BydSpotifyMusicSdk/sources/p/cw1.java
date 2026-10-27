package p;

import com.spotify.automotive.pathfinder.type.PodcastMediaType;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class cw1 implements y4 {
    public static final List c = v03.C("coverArt", "mediaType", "name", "publisher", "uri");

    public static aw1 b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        PodcastMediaType podcastMediaType;
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        yv1 yv1Var = null;
        PodcastMediaType podcastMediaType2 = null;
        String str = null;
        zv1 zv1Var = null;
        String str2 = null;
        while (true) {
            int iW = u71Var.W(c);
            if (iW != 0) {
                int i = 0;
                if (iW == 1) {
                    String strU = u71Var.u();
                    v03.h(strU);
                    PodcastMediaType[] podcastMediaTypeArrValues = PodcastMediaType.values();
                    int length = podcastMediaTypeArrValues.length;
                    while (true) {
                        if (i >= length) {
                            podcastMediaType = null;
                            break;
                        }
                        podcastMediaType = podcastMediaTypeArrValues[i];
                        if (v03.b(podcastMediaType.a(), strU)) {
                            break;
                        }
                        i++;
                    }
                    podcastMediaType2 = podcastMediaType == null ? PodcastMediaType.UNKNOWN__ : podcastMediaType;
                } else if (iW == 2) {
                    str = (String) c5.a.a(u71Var, cVar);
                } else if (iW == 3) {
                    zv1Var = (zv1) c5.a(new wo1(dw1.c, false)).a(u71Var, cVar);
                } else {
                    if (iW != 4) {
                        v03.h(podcastMediaType2);
                        v03.h(str);
                        v03.h(str2);
                        return new aw1(yv1Var, podcastMediaType2, str, zv1Var, str2);
                    }
                    str2 = (String) c5.a.a(u71Var, cVar);
                }
            } else {
                yv1Var = (yv1) c5.a(new wo1(bw1.c, true)).a(u71Var, cVar);
            }
        }
    }
}
