package f4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.text.TextUtils;
import com.vivid.spotify.s;
import com.vivid.spotify.x;

/* JADX INFO: compiled from: SpotifyMediaItemWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends i3.c {
    public static double k(MediaBrowserCompat.MediaItem mediaItem) {
        Bundle bundleC = mediaItem.h().c();
        if (bundleC == null) {
            return -1.0d;
        }
        return bundleC.getDouble("androidx.media.MediaItem.Extras.COMPLETION_PERCENTAGE", -1.0d);
    }

    public static MediaBrowserCompat.MediaItem l(String str, Context context) {
        MediaDescriptionCompat.b bVar = new MediaDescriptionCompat.b();
        bVar.f("spotify:user");
        if (TextUtils.equals(str, "spotify:collection:playlists")) {
            bVar.i(context.getString(x.collect_song_title));
            bVar.h(String.format("%d %s", 0, context.getString(x.multi_song_count)));
            bVar.g(Uri.parse("android.resource://com.spotify.music/collection"));
            bVar.e(Uri.parse("android.resource://com.spotify.music/drawable/liked_songs_cover"));
        } else {
            bVar.i(context.getString(x.collect_episode_title));
            bVar.h(String.format("%d %s", 0, context.getString(x.multi_episode_count)));
            bVar.g(Uri.parse("android.resource://com.spotify.music/your-episodes"));
            bVar.e(Uri.parse("android.resource://com.spotify.music/drawable/your_episodes_cover"));
        }
        Bundle bundle = new Bundle();
        bundle.putInt("android.media.browse.CONTENT_STYLE_PLAYABLE_HINT", 1);
        bVar.c(bundle);
        return new MediaBrowserCompat.MediaItem(bVar.a(), 1);
    }

    public static int m(String str) {
        return TextUtils.isEmpty(str) ? s.ic_playlist_placeholder : str.startsWith("spotify:playlist") ? s.ic_playlist_placeholder : str.startsWith("spotify:track") ? s.ic_track_placeholder : str.startsWith("spotify:artist") ? s.ic_artist_placeholder : str.startsWith("spotify:album") ? s.ic_album_placeholder : (str.startsWith("spotify:show") || str.startsWith("spotify:episode")) ? s.ic_podcast_placeholder : s.ic_playlist_placeholder;
    }

    public static boolean n(MediaBrowserCompat.MediaItem mediaItem) {
        Bundle bundleC = mediaItem.h().c();
        return bundleC != null && bundleC.getLong("com.spotify.music.extra.IS_19_PLUS", 0L) == 1;
    }

    public static boolean o(MediaBrowserCompat.MediaItem mediaItem) {
        String strJ = mediaItem.j();
        return strJ != null && (TextUtils.equals("spotify:collection:playlists", strJ) || TextUtils.equals("spotify:collection:shows", strJ));
    }

    public static boolean p(MediaBrowserCompat.MediaItem mediaItem) {
        String strJ = mediaItem.j();
        String strD = i3.c.d(mediaItem);
        return strJ != null && strJ.startsWith("spotify:user") && (strD.endsWith("collection") || strD.endsWith("your-episodes"));
    }

    public static boolean q(MediaBrowserCompat.MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.h() == null) {
            return false;
        }
        return TextUtils.equals(mediaItem.h().c().getString("com.spotify.music.extra.COLLECTION_ICON_HINT", "HEART"), "PLUS");
    }
}
