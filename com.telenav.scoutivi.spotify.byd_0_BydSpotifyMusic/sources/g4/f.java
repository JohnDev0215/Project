package g4;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.view.View;
import com.vivid.spotify.nowplaying.n;
import com.vivid.spotify.s;
import f4.c;
import f4.o;
import h3.i;
import i3.m;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: ControlActionFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final Context f6539a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final m f6540b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final f4.c f6541c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final y3.b f6542d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Map<String, g4.a> f6543e = new HashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final CopyOnWriteArraySet<c> f6544f = new CopyOnWriteArraySet<>();

    /* JADX INFO: compiled from: ControlActionFactory.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f6545a;

        static {
            int[] iArr = new int[b.values().length];
            f6545a = iArr;
            try {
                iArr[b.Play.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6545a[b.Pause.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6545a[b.SkipToPrevious.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f6545a[b.SkipToNext.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f6545a[b.RepeatNone.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f6545a[b.RepeatOne.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f6545a[b.RepeatAll.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: ControlActionFactory.java */
    public enum b {
        Play,
        Pause,
        SkipToPrevious,
        SkipToNext,
        RepeatNone,
        RepeatOne,
        RepeatAll
    }

    /* JADX INFO: compiled from: ControlActionFactory.java */
    public interface c {
        void a(View view, PlaybackStateCompat.CustomAction customAction);
    }

    public f(Application application, m mVar, y3.b bVar, f4.c cVar) {
        this.f6539a = application;
        this.f6540b = mVar;
        this.f6542d = bVar;
        this.f6541c = cVar;
        s();
    }

    private void f(int i7) {
        int i8 = (i7 + 2) % 3;
        List<Integer> listZ = ((o) this.f6540b).Z();
        Iterator<Integer> it = listZ.iterator();
        String str = "(";
        while (it.hasNext()) {
            str = str + it.next() + ",";
        }
        i.b("ControlActionFactory").g("current = " + i7 + " available " + str + ")");
        if (listZ.isEmpty()) {
            return;
        }
        while (!listZ.contains(Integer.valueOf(i8))) {
            i8 = (i8 + 2) % 3;
        }
        this.f6540b.Q(i8);
    }

    private void g(PlaybackStateCompat.CustomAction customAction) {
        if (com.vivid.spotify.nowplaying.m.e(customAction)) {
            this.f6541c.e(new c.b(this.f6540b.n(), customAction));
        } else {
            this.f6541c.e(null);
        }
    }

    private b h(int i7) {
        i.b("ControlActionFactory").i("1:%s", Integer.valueOf(i7));
        if (i7 == 0) {
            return b.RepeatNone;
        }
        if (i7 == 1) {
            return b.RepeatOne;
        }
        if (i7 == 2 || i7 == 3) {
            return b.RepeatAll;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(b bVar, View view) {
        switch (a.f6545a[bVar.ordinal()]) {
            case 1:
                this.f6540b.I();
                break;
            case 2:
                this.f6540b.H();
                break;
            case 3:
                this.f6540b.T();
                break;
            case 4:
                this.f6540b.S();
                break;
            case 5:
                f(0);
                break;
            case 6:
                f(1);
                break;
            case 7:
                f(2);
                break;
            default:
                i.b("ControlActionFactory").d("getClickListenerByAction unknown action:%s", bVar.name());
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u(PlaybackStateCompat.CustomAction customAction, View view) {
        Iterator<c> it = this.f6544f.iterator();
        while (it.hasNext()) {
            it.next().a(view, customAction);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v(PlaybackStateCompat.CustomAction customAction, View view) {
        g(customAction);
        this.f6540b.P(customAction.c(), new Bundle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f6542d.c(str);
    }

    public void e(c cVar) {
        this.f6544f.add(cVar);
    }

    public View.OnClickListener i(final b bVar) {
        return new View.OnClickListener() { // from class: g4.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f6536e.t(bVar, view);
            }
        };
    }

    public View.OnClickListener j(final PlaybackStateCompat.CustomAction customAction) {
        return customAction.c().startsWith(n.PlaybackSpeed.getAction()) ? new View.OnClickListener() { // from class: g4.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f6534e.u(customAction, view);
            }
        } : new View.OnClickListener() { // from class: g4.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f6532e.v(customAction, view);
            }
        };
    }

    public g4.a k(PlaybackStateCompat.CustomAction customAction) {
        g4.a aVar = this.f6543e.get(customAction.c());
        int iN = n(customAction);
        if (aVar == null) {
            i.b("ControlActionFactory").g("customAction:" + ((Object) customAction.j()) + ",icon:" + iN);
            aVar = iN == 0 ? new g4.a(this.f6540b.m(), customAction.h(), 1, (String) customAction.j(), j(customAction), true) : new g4.a(this.f6539a.getResources(), iN, o(customAction), (String) customAction.j(), j(customAction), true);
            this.f6543e.put(customAction.c(), aVar);
        } else {
            if (iN == 0) {
                aVar.g(1);
            } else {
                aVar.g(o(customAction));
            }
            i.b("ControlActionFactory").g("customAction:" + aVar.d() + " use cache");
        }
        return aVar;
    }

    public g4.a l(b bVar, boolean z6) {
        g4.a aVar = this.f6543e.get(bVar.name());
        if (aVar != null) {
            i.b("ControlActionFactory").g("action:" + bVar.name() + " use cache");
            aVar.h(z6);
            aVar.g(p(bVar));
            return aVar;
        }
        int iM = m(bVar);
        i.b("ControlActionFactory").g("action:" + bVar.name() + ",icon:" + iM);
        g4.a aVar2 = new g4.a(this.f6539a.getResources(), iM, p(bVar), bVar.name(), i(bVar), z6);
        this.f6543e.put(bVar.name(), aVar2);
        return aVar2;
    }

    public int m(b bVar) {
        switch (a.f6545a[bVar.ordinal()]) {
            case 1:
                return s.play;
            case 2:
                return s.pause;
            case 3:
                return s.skip_to_previous;
            case 4:
                return s.skip_to_next;
            case 5:
                return s.repeat_none;
            case 6:
                return s.repeat_one;
            case 7:
                return s.repeat_all;
            default:
                i.b("ControlActionFactory").d("getIconByAction unknown action:%s", bVar.name());
                return 0;
        }
    }

    public int n(PlaybackStateCompat.CustomAction customAction) {
        if (TextUtils.equals(customAction.c(), n.Shuffle.getAction())) {
            return s.spotify_shuffle;
        }
        if (TextUtils.equals(customAction.c(), n.DisableShuffle.getAction())) {
            return s.spotify_disable_shuffle;
        }
        if (TextUtils.equals(customAction.c(), n.LikeTrack.getAction())) {
            return s.spotify_like_track;
        }
        if (TextUtils.equals(customAction.c(), n.UnlikeTrack.getAction())) {
            return s.spotify_unlike_track;
        }
        if (TextUtils.equals(customAction.c(), n.LikeEpisode.getAction())) {
            return s.spotify_like_episode;
        }
        if (TextUtils.equals(customAction.c(), n.UnlikeEpisode.getAction())) {
            return s.spotify_unlike_episode;
        }
        if (TextUtils.equals(customAction.c(), n.SeekBackward.getAction())) {
            return s.spotify_seek_backward;
        }
        if (TextUtils.equals(customAction.c(), n.SeekForward.getAction())) {
            return s.spotify_seek_forward;
        }
        return 0;
    }

    public int o(PlaybackStateCompat.CustomAction customAction) {
        return (TextUtils.equals(customAction.c(), n.DisableShuffle.getAction()) || TextUtils.equals(customAction.c(), n.UnlikeTrack.getAction()) || TextUtils.equals(customAction.c(), n.UnlikeEpisode.getAction())) ? 2 : 1;
    }

    public int p(b bVar) {
        return (bVar == b.RepeatAll || bVar == b.RepeatOne) ? 2 : 1;
    }

    public g4.a q() {
        b bVarH = h(this.f6540b.s());
        if (bVarH == null) {
            return null;
        }
        return l(bVarH, true);
    }

    public int r(String str) {
        str.hashCode();
        switch (str) {
            case "0.8":
                return s.icon_speed_0_8;
            case "1.0":
                return s.icon_speed_1;
            case "1.2":
                return s.icon_speed_1_2;
            case "1.5":
                return s.icon_speed_1_5;
            case "2.0":
                return s.icon_speed_2;
            default:
                i.b("ControlActionFactory").d("getSpeedDrawable unknown name:%s", str);
                return 0;
        }
    }

    protected void s() {
        this.f6541c.b(new m.f() { // from class: g4.e
            @Override // i3.m.f
            public final void a(String str) {
                this.f6538a.w(str);
            }
        });
    }

    public void x(c cVar) {
        this.f6544f.remove(cVar);
    }
}
