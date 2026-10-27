package p;

import com.spotify.common.uri.SpotifyUri$Kind;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class xb1 {
    public final Set a;

    public xb1(Set set) {
        v03.j(set, "loaderProviders");
        this.a = set;
    }

    public final x81 a(Object obj) {
        Object next;
        boolean zB;
        Iterator it = this.a.iterator();
        while (true) {
            if (it.hasNext()) {
                next = it.next();
                switch (((v5) ((x81) next)).a) {
                    case 0:
                        v03.j(obj, "key");
                        if (obj != SpotifyUri$Kind.ALBUM) {
                            continue;
                        }
                        break;
                    case 1:
                        v03.j(obj, "key");
                        if (obj != SpotifyUri$Kind.ARTIST) {
                            continue;
                        }
                        break;
                    case 2:
                        v03.j(obj, "key");
                        zB = v03.b(obj, bv2.d);
                        break;
                    case 3:
                        v03.j(obj, "key");
                        if (obj != SpotifyUri$Kind.GENRE) {
                            continue;
                        }
                        break;
                    case 4:
                        v03.j(obj, "key");
                        zB = v03.b(obj, bv2.b);
                        break;
                    case 5:
                        v03.j(obj, "key");
                        if (obj != SpotifyUri$Kind.COLLECTION_YOUR_EPISODES) {
                            continue;
                        }
                        break;
                    case 6:
                        v03.j(obj, "key");
                        if (obj != SpotifyUri$Kind.COLLECTION) {
                            continue;
                        }
                        break;
                    case 7:
                        v03.j(obj, "key");
                        zB = ou.Z(v03.C(SpotifyUri$Kind.PLAYLIST, SpotifyUri$Kind.PLAYLIST_V2), obj);
                        break;
                    case 8:
                        v03.j(obj, "key");
                        zB = v03.b(obj, bv2.c);
                        break;
                    case 9:
                        v03.j(obj, "key");
                        zB = v03.b(obj, bv2.a);
                        break;
                    case 10:
                        v03.j(obj, "key");
                        if (obj != SpotifyUri$Kind.SEARCH) {
                            continue;
                        }
                        break;
                    case 11:
                        v03.j(obj, "key");
                        if (obj != SpotifyUri$Kind.SHOW) {
                            continue;
                        }
                        break;
                    case 12:
                        v03.j(obj, "key");
                        zB = v03.b(obj, bv2.h);
                        break;
                    case 13:
                        v03.j(obj, "key");
                        zB = v03.b(obj, bv2.g);
                        break;
                    case 14:
                        v03.j(obj, "key");
                        zB = v03.b(obj, bv2.j);
                        break;
                    case 15:
                        v03.j(obj, "key");
                        zB = v03.b(obj, bv2.e);
                        break;
                    case 16:
                        v03.j(obj, "key");
                        zB = v03.b(obj, bv2.f);
                        break;
                    default:
                        v03.j(obj, "key");
                        zB = v03.b(obj, bv2.i);
                        break;
                }
                if (zB) {
                }
            } else {
                next = null;
            }
        }
        return (x81) next;
    }

    public final tb1 b(com.spotify.navigation.identifier.a aVar) {
        v03.j(aVar, "viewUri");
        x81 x81VarA = a(aVar);
        return x81VarA != null ? ((v5) x81VarA).a() : new vd0();
    }

    public final tb1 c(if2 if2Var) {
        v03.j(if2Var, "spotifyUri");
        SpotifyUri$Kind spotifyUri$Kind = if2Var.e;
        v03.i(spotifyUri$Kind, "spotifyUri.kind");
        x81 x81VarA = a(spotifyUri$Kind);
        return x81VarA != null ? ((v5) x81VarA).a() : new vd0();
    }
}
