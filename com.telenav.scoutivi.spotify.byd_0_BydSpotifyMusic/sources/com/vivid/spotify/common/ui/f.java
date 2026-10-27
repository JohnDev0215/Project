package com.vivid.spotify.common.ui;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.view.View;
import androidx.navigation.q;
import com.vivid.spotify.t;

/* JADX INFO: compiled from: Route.java */
/* JADX INFO: loaded from: classes.dex */
public class f {
    public static void a(MediaBrowserCompat.MediaItem mediaItem, MediaBrowserCompat.MediaItem mediaItem2, View view) {
        Bundle bundleC;
        MediaDescriptionCompat mediaDescriptionCompatH = mediaItem2.h();
        if (mediaDescriptionCompatH == null || (bundleC = mediaDescriptionCompatH.c()) == null) {
            return;
        }
        int i7 = bundleC.getInt("android.media.browse.CONTENT_STYLE_PLAYABLE_HINT", -1);
        if (i7 == -1) {
            i7 = bundleC.getInt("android.media.browse.CONTENT_STYLE_BROWSABLE_HINT", -1);
        }
        if (i7 != 2) {
            c(mediaItem, mediaItem2, view);
        } else {
            b(mediaItem2, view);
        }
    }

    private static void b(MediaBrowserCompat.MediaItem mediaItem, View view) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("media_item", mediaItem);
        bundle.putBoolean("show_back", true);
        q.a(view).o(t.action_global_gridFragment, bundle);
    }

    private static void c(MediaBrowserCompat.MediaItem mediaItem, MediaBrowserCompat.MediaItem mediaItem2, View view) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("media_item", mediaItem2);
        bundle.putParcelable("parent_media_item", mediaItem);
        q.a(view).o(t.action_global_detailFragment, bundle);
    }
}
