package g4;

import android.app.Application;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.vivid.spotify.nowplaying.n;
import com.vivid.spotify.s;
import g4.f;
import h3.i;
import i3.m;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: NowPlayingBarControlActionFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends f {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Map<String, g4.a> f6547g;

    /* JADX INFO: compiled from: NowPlayingBarControlActionFactory.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f6548a;

        static {
            int[] iArr = new int[f.b.values().length];
            f6548a = iArr;
            try {
                iArr[f.b.Play.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6548a[f.b.Pause.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6548a[f.b.SkipToPrevious.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f6548a[f.b.SkipToNext.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public g(Application application, m mVar, y3.b bVar, f4.c cVar) {
        super(application, mVar, bVar, cVar);
        this.f6547g = new HashMap();
    }

    @Override // g4.f
    public g4.a k(PlaybackStateCompat.CustomAction customAction) {
        g4.a aVar = this.f6547g.get(customAction.c());
        int iN = n(customAction);
        if (aVar == null) {
            aVar = iN == 0 ? new g4.a(this.f6540b.m(), customAction.h(), 0, (String) customAction.j(), j(customAction), true) : new g4.a(this.f6539a.getResources(), iN, o(customAction), (String) customAction.j(), j(customAction), true);
            this.f6547g.put(customAction.c(), aVar);
        } else {
            if (iN == 0) {
                aVar.g(0);
            } else {
                aVar.g(o(customAction));
            }
            i.b("NowPlayingBarControlActionFactory").g("customAction:" + aVar.d() + " use cache");
        }
        return aVar;
    }

    @Override // g4.f
    public g4.a l(f.b bVar, boolean z6) {
        g4.a aVar = this.f6547g.get(bVar.name());
        if (aVar != null) {
            aVar.h(z6);
            return aVar;
        }
        g4.a aVar2 = new g4.a(this.f6539a.getResources(), m(bVar), 3, bVar.name(), i(bVar), z6);
        this.f6547g.put(bVar.name(), aVar2);
        return aVar2;
    }

    @Override // g4.f
    public int m(f.b bVar) {
        int i7 = a.f6548a[bVar.ordinal()];
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? super.m(bVar) : s.bar_skip_to_next : s.bar_skip_to_previous : s.bar_pause : s.bar_play;
    }

    @Override // g4.f
    public int n(PlaybackStateCompat.CustomAction customAction) {
        return TextUtils.equals(customAction.c(), n.LikeTrack.getAction()) ? s.bar_spotify_like_track : TextUtils.equals(customAction.c(), n.LikeEpisode.getAction()) ? s.bar_spotify_like_episode : TextUtils.equals(customAction.c(), n.SeekForward.getAction()) ? s.bar_spotify_seek_forward : TextUtils.equals(customAction.c(), n.SeekBackward.getAction()) ? s.bar_spotify_seek_backward : super.n(customAction);
    }

    @Override // g4.f
    public int o(PlaybackStateCompat.CustomAction customAction) {
        if (TextUtils.equals(customAction.c(), n.LikeTrack.getAction()) || TextUtils.equals(customAction.c(), n.LikeEpisode.getAction()) || TextUtils.equals(customAction.c(), n.SeekForward.getAction()) || TextUtils.equals(customAction.c(), n.SeekBackward.getAction())) {
            return 3;
        }
        return super.o(customAction);
    }

    @Override // g4.f
    protected void s() {
    }
}
