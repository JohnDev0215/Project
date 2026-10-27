package com.vivid.spotify.rootui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.media.MediaBrowserCompat;
import android.text.TextUtils;
import java.util.Objects;

/* JADX INFO: compiled from: RootMenuItem.java */
/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final MediaBrowserCompat.MediaItem f5515a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f5516b;

    public x(MediaBrowserCompat.MediaItem mediaItem, int i7) {
        this.f5515a = mediaItem;
        this.f5516b = i7;
    }

    private boolean d(MediaBrowserCompat.MediaItem mediaItem, MediaBrowserCompat.MediaItem mediaItem2) {
        if (mediaItem == null && mediaItem2 == null) {
            return true;
        }
        if (mediaItem == null || mediaItem2 == null) {
            return false;
        }
        return TextUtils.equals(mediaItem.j(), mediaItem2.j());
    }

    public Drawable a(Context context) {
        int i7;
        MediaBrowserCompat.MediaItem mediaItem = this.f5515a;
        if (mediaItem != null && context != null) {
            String strC = i3.c.c(mediaItem);
            strC.hashCode();
            i7 = 0;
            switch (strC) {
                case "spotify:browse":
                    i7 = com.vivid.spotify.s.root_menu_icon_browse;
                    break;
                case "spotify:library":
                    i7 = com.vivid.spotify.s.root_menu_icon_library;
                    break;
                case "spotify:home":
                    i7 = com.vivid.spotify.s.root_menu_icon_home;
                    break;
                case "spotify:recently-played":
                    i7 = com.vivid.spotify.s.root_menu_icon_recently_played;
                    break;
                case "vivid:search":
                    i7 = com.vivid.spotify.s.root_menu_icon_search;
                    break;
            }
            if (i7 != 0) {
                return y.b.b(context.getResources(), i7, context.getTheme());
            }
        }
        return null;
    }

    public MediaBrowserCompat.MediaItem b() {
        return this.f5515a;
    }

    public int c() {
        return this.f5516b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        x xVar = (x) obj;
        return this.f5516b == xVar.f5516b && d(this.f5515a, xVar.f5515a);
    }

    public int hashCode() {
        return Objects.hash(this.f5515a, Integer.valueOf(this.f5516b));
    }

    public x(MediaBrowserCompat.MediaItem mediaItem) {
        this(mediaItem, 0);
    }
}
