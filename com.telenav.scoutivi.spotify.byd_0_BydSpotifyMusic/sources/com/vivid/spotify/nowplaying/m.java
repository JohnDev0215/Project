package com.vivid.spotify.nowplaying;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import com.vivid.spotify.x;
import g4.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: SpotifyButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: compiled from: SpotifyButtonHelper.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5352a;

        static {
            int[] iArr = new int[n.values().length];
            f5352a = iArr;
            try {
                iArr[n.LikeTrack.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5352a[n.LikeEpisode.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5352a[n.UnlikeTrack.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5352a[n.UnlikeEpisode.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static String a(Context context, PlaybackStateCompat.CustomAction customAction, PlaybackStateCompat playbackStateCompat) {
        n spotifyCustomAction = n.getSpotifyCustomAction(customAction.c());
        if (spotifyCustomAction == null) {
            return null;
        }
        int i7 = a.f5352a[spotifyCustomAction.ordinal()];
        int i8 = 0;
        if (i7 == 1) {
            PlaybackStateCompat.CustomAction customActionC = c(playbackStateCompat, n.UnlikeTrack);
            PlaybackStateCompat.CustomAction customActionC2 = c(playbackStateCompat, n.LikeTrack);
            if (customActionC == null || customActionC2 != null) {
                h3.i.b("SpotifyButtonHelper").d("2", new Object[0]);
            } else {
                i8 = x.added_to_your_liked_songs;
                h3.i.b("SpotifyButtonHelper").g("a");
            }
        } else if (i7 == 2) {
            PlaybackStateCompat.CustomAction customActionC3 = c(playbackStateCompat, n.UnlikeEpisode);
            PlaybackStateCompat.CustomAction customActionC4 = c(playbackStateCompat, n.LikeEpisode);
            if (customActionC3 == null || customActionC4 != null) {
                h3.i.b("SpotifyButtonHelper").d("4", new Object[0]);
            } else {
                i8 = x.added_to_your_episodes;
                h3.i.b("SpotifyButtonHelper").g("3");
            }
        } else if (i7 == 3) {
            PlaybackStateCompat.CustomAction customActionC5 = c(playbackStateCompat, n.LikeTrack);
            PlaybackStateCompat.CustomAction customActionC6 = c(playbackStateCompat, n.UnlikeTrack);
            if (customActionC5 == null || customActionC6 != null) {
                h3.i.b("SpotifyButtonHelper").d("6", new Object[0]);
            } else {
                i8 = x.removed_from_your_liked_songs;
                h3.i.b("SpotifyButtonHelper").g("5");
            }
        } else if (i7 == 4) {
            PlaybackStateCompat.CustomAction customActionC7 = c(playbackStateCompat, n.LikeEpisode);
            PlaybackStateCompat.CustomAction customActionC8 = c(playbackStateCompat, n.UnlikeEpisode);
            if (customActionC7 == null || customActionC8 != null) {
                h3.i.b("SpotifyButtonHelper").d("8", new Object[0]);
            } else {
                i8 = x.removed_from_your_episodes;
                h3.i.b("SpotifyButtonHelper").g("7");
            }
        }
        if (i8 != 0) {
            return context.getString(i8);
        }
        return null;
    }

    public static PlaybackStateCompat.CustomAction b(PlaybackStateCompat playbackStateCompat) {
        Iterator<n> it = n.getCollectCustomAction().values().iterator();
        while (it.hasNext()) {
            PlaybackStateCompat.CustomAction customActionC = c(playbackStateCompat, it.next());
            if (customActionC != null) {
                return customActionC;
            }
        }
        return null;
    }

    public static PlaybackStateCompat.CustomAction c(PlaybackStateCompat playbackStateCompat, n nVar) {
        List<PlaybackStateCompat.CustomAction> listJ = playbackStateCompat.j();
        if (listJ == null || listJ.isEmpty()) {
            return null;
        }
        Iterator<PlaybackStateCompat.CustomAction> it = listJ.iterator();
        while (it.hasNext()) {
            PlaybackStateCompat.CustomAction next = it.next();
            if ((nVar == n.PlaybackSpeed && next.c().startsWith(nVar.getAction())) || nVar.getAction().equals(next.c())) {
                return next;
            }
        }
        return null;
    }

    private static g4.a d(g4.f fVar, PlaybackStateCompat playbackStateCompat) {
        if ((playbackStateCompat.c() & 262144) != 0) {
            return fVar.q();
        }
        return null;
    }

    public static boolean e(PlaybackStateCompat.CustomAction customAction) {
        return n.getCollectCustomAction().get(customAction.c()) != null;
    }

    public static List<g4.a> f(g4.f fVar, PlaybackStateCompat playbackStateCompat) {
        ArrayList arrayList = new ArrayList();
        long jC = playbackStateCompat.c();
        if (com.vivid.spotify.common.base.i.a()) {
            arrayList.add(fVar.l(f.b.SkipToNext, (32 & jC) != 0));
            arrayList.add(fVar.l(f.b.SkipToPrevious, (jC & 16) != 0));
            PlaybackStateCompat.CustomAction customActionC = c(playbackStateCompat, n.SeekBackward);
            if (customActionC != null) {
                arrayList.add(0, fVar.k(customActionC));
            }
            PlaybackStateCompat.CustomAction customActionC2 = c(playbackStateCompat, n.SeekForward);
            if (customActionC2 != null) {
                arrayList.add(0, fVar.k(customActionC2));
            }
        } else {
            arrayList.add(fVar.l(f.b.SkipToPrevious, (16 & jC) != 0));
            arrayList.add(fVar.l(f.b.SkipToNext, (jC & 32) != 0));
            PlaybackStateCompat.CustomAction customActionC3 = c(playbackStateCompat, n.SeekForward);
            if (customActionC3 != null) {
                arrayList.add(0, fVar.k(customActionC3));
            }
            PlaybackStateCompat.CustomAction customActionC4 = c(playbackStateCompat, n.SeekBackward);
            if (customActionC4 != null) {
                arrayList.add(0, fVar.k(customActionC4));
            }
        }
        return arrayList;
    }

    public static List<g4.a> g(g4.f fVar, PlaybackStateCompat playbackStateCompat) {
        ArrayList arrayList = new ArrayList();
        long jC = playbackStateCompat.c();
        if (com.vivid.spotify.common.base.i.a()) {
            arrayList.add(0, fVar.l(f.b.SkipToNext, (32 & jC) != 0));
            arrayList.add(1, fVar.l(f.b.SkipToPrevious, (jC & 16) != 0));
            PlaybackStateCompat.CustomAction customActionC = c(playbackStateCompat, n.SeekForward);
            if (customActionC != null) {
                arrayList.add(fVar.k(customActionC));
            }
            PlaybackStateCompat.CustomAction customActionC2 = c(playbackStateCompat, n.SeekBackward);
            if (customActionC2 != null) {
                arrayList.add(fVar.k(customActionC2));
            }
        } else {
            arrayList.add(0, fVar.l(f.b.SkipToPrevious, (16 & jC) != 0));
            arrayList.add(1, fVar.l(f.b.SkipToNext, (jC & 32) != 0));
            PlaybackStateCompat.CustomAction customActionC3 = c(playbackStateCompat, n.SeekBackward);
            if (customActionC3 != null) {
                arrayList.add(fVar.k(customActionC3));
            }
            PlaybackStateCompat.CustomAction customActionC4 = c(playbackStateCompat, n.SeekForward);
            if (customActionC4 != null) {
                arrayList.add(fVar.k(customActionC4));
            }
        }
        g4.a aVarD = d(fVar, playbackStateCompat);
        if (aVarD != null) {
            arrayList.add(aVarD);
        }
        boolean z6 = playbackStateCompat.p() != null ? playbackStateCompat.p().getBoolean("com.spotify.music.extra.SHUFFLE_FORCE_ENABLED", false) : false;
        h3.i.b("SpotifyButtonHelper").g("shuffleForcedOn = " + z6);
        PlaybackStateCompat.CustomAction customActionC5 = c(playbackStateCompat, n.Shuffle);
        if (customActionC5 != null) {
            g4.a aVarK = fVar.k(customActionC5);
            aVarK.h(!z6);
            arrayList.add(aVarK);
        }
        PlaybackStateCompat.CustomAction customActionC6 = c(playbackStateCompat, n.DisableShuffle);
        if (customActionC6 != null) {
            g4.a aVarK2 = fVar.k(customActionC6);
            aVarK2.h(!z6);
            arrayList.add(aVarK2);
        }
        return arrayList;
    }
}
