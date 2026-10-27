package com.amazon.music.media.auto.provider;

import android.content.Context;
import android.net.Uri;
import com.amazon.music.media.auto.R$drawable;
import com.amazon.music.media.auto.util.LocalResourceUriProviderUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultImageUriProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001c\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b \u0010!J\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0004\u001a\u00020\u0002J\u0006\u0010\u0005\u001a\u00020\u0002J\u0006\u0010\u0006\u001a\u00020\u0002R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\rR\u0017\u0010\u0011\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\r\u001a\u0004\b\u0015\u0010\u0013R\u0017\u0010\u0016\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010\r\u001a\u0004\b\u0017\u0010\u0013R\u0017\u0010\u0018\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\r\u001a\u0004\b\u0019\u0010\u0013R\u0017\u0010\u001a\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\r\u001a\u0004\b\u001b\u0010\u0013R\u0017\u0010\u001c\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\r\u001a\u0004\b\u001d\u0010\u0013R\u0017\u0010\u001e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001e\u0010\r\u001a\u0004\b\u001f\u0010\u0013¨\u0006#"}, d2 = {"Lcom/amazon/music/media/auto/provider/DefaultImageUriProvider;", "", "Landroid/net/Uri;", "getSeeMoreIconUri", "getMediaItemIconUri", "getHomeTabIconUri", "getLibraryTabIconUri", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "defaultMediaIconLocalUri", "Landroid/net/Uri;", "seeMoreIconLocalUri", "homeTabIconLocalUri", "libraryTabIconLocalUri", "recentIconUri", "getRecentIconUri", "()Landroid/net/Uri;", "albumIconUri", "getAlbumIconUri", "artistIconUri", "getArtistIconUri", "fireIconUri", "getFireIconUri", "playlistIconUri", "getPlaylistIconUri", "songIconUri", "getSongIconUri", "presetsIconUri", "getPresetsIconUri", "<init>", "(Landroid/content/Context;)V", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class DefaultImageUriProvider {
    private final Uri albumIconUri;
    private final Uri artistIconUri;
    private final Context context;
    private final Uri defaultMediaIconLocalUri;
    private final Uri fireIconUri;
    private final Uri homeTabIconLocalUri;
    private final Uri libraryTabIconLocalUri;
    private final Uri playlistIconUri;
    private final Uri presetsIconUri;
    private final Uri recentIconUri;
    private final Uri seeMoreIconLocalUri;
    private final Uri songIconUri;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int DEFAULT_MEDIA_ID = R$drawable.ic_auto_media;
    private static final int SEE_MORE_ICON_ID = R$drawable.ic_see_more_tile;
    private static final int HOME_TAB_ICON_ID = R$drawable.ic_navigation_home;
    private static final int LIBRARY_TAB_ICON_ID = R$drawable.ic_navigation_mymusic;
    private static final int RECENT_ICON = R$drawable.ic_other_time_media_browser;
    private static final int ALBUM_ICON = R$drawable.ic_other_album_media_browser;
    private static final int ARTIST_ICON = R$drawable.ic_other_artist_media_browser;
    private static final int FIRE_ICON = R$drawable.ic_other_fire_media_browser;
    private static final int PLAYLIST_ICON = R$drawable.ic_other_playlist_media_browser;
    private static final int SONG_ICON = R$drawable.ic_other_song_media_browser;
    private static final int PRESETS_ICON = R$drawable.ic_by_car;

    public DefaultImageUriProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        LocalResourceUriProviderUtil localResourceUriProviderUtil = LocalResourceUriProviderUtil.INSTANCE;
        int i = DEFAULT_MEDIA_ID;
        String packageName = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
        this.defaultMediaIconLocalUri = localResourceUriProviderUtil.createLocalAndroidResourceUri(i, context, packageName);
        int i2 = SEE_MORE_ICON_ID;
        String packageName2 = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName2, "context.packageName");
        this.seeMoreIconLocalUri = localResourceUriProviderUtil.createLocalAndroidResourceUri(i2, context, packageName2);
        int i3 = HOME_TAB_ICON_ID;
        String packageName3 = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName3, "context.packageName");
        this.homeTabIconLocalUri = localResourceUriProviderUtil.createLocalAndroidResourceUri(i3, context, packageName3);
        int i4 = LIBRARY_TAB_ICON_ID;
        String packageName4 = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName4, "context.packageName");
        this.libraryTabIconLocalUri = localResourceUriProviderUtil.createLocalAndroidResourceUri(i4, context, packageName4);
        int i5 = RECENT_ICON;
        String packageName5 = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName5, "context.packageName");
        this.recentIconUri = localResourceUriProviderUtil.createLocalAndroidResourceUri(i5, context, packageName5);
        int i6 = ALBUM_ICON;
        String packageName6 = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName6, "context.packageName");
        this.albumIconUri = localResourceUriProviderUtil.createLocalAndroidResourceUri(i6, context, packageName6);
        int i7 = ARTIST_ICON;
        String packageName7 = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName7, "context.packageName");
        this.artistIconUri = localResourceUriProviderUtil.createLocalAndroidResourceUri(i7, context, packageName7);
        int i8 = FIRE_ICON;
        String packageName8 = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName8, "context.packageName");
        this.fireIconUri = localResourceUriProviderUtil.createLocalAndroidResourceUri(i8, context, packageName8);
        int i9 = PLAYLIST_ICON;
        String packageName9 = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName9, "context.packageName");
        this.playlistIconUri = localResourceUriProviderUtil.createLocalAndroidResourceUri(i9, context, packageName9);
        int i10 = SONG_ICON;
        String packageName10 = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName10, "context.packageName");
        this.songIconUri = localResourceUriProviderUtil.createLocalAndroidResourceUri(i10, context, packageName10);
        int i11 = PRESETS_ICON;
        String packageName11 = context.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName11, "context.packageName");
        this.presetsIconUri = localResourceUriProviderUtil.createLocalAndroidResourceUri(i11, context, packageName11);
    }

    /* JADX INFO: compiled from: DefaultImageUriProvider.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0017\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/media/auto/provider/DefaultImageUriProvider$Companion;", "", "()V", "ALBUM_ICON", "", "getALBUM_ICON", "()I", "ARTIST_ICON", "getARTIST_ICON", "DEFAULT_MEDIA_ID", "getDEFAULT_MEDIA_ID", "FIRE_ICON", "getFIRE_ICON", "HOME_TAB_ICON_ID", "getHOME_TAB_ICON_ID", "LIBRARY_TAB_ICON_ID", "getLIBRARY_TAB_ICON_ID", "PLAYLIST_ICON", "getPLAYLIST_ICON", "PRESETS_ICON", "getPRESETS_ICON", "RECENT_ICON", "getRECENT_ICON", "SEE_MORE_ICON_ID", "getSEE_MORE_ICON_ID", "SONG_ICON", "getSONG_ICON", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getDEFAULT_MEDIA_ID() {
            return DefaultImageUriProvider.DEFAULT_MEDIA_ID;
        }

        public final int getSEE_MORE_ICON_ID() {
            return DefaultImageUriProvider.SEE_MORE_ICON_ID;
        }

        public final int getHOME_TAB_ICON_ID() {
            return DefaultImageUriProvider.HOME_TAB_ICON_ID;
        }

        public final int getLIBRARY_TAB_ICON_ID() {
            return DefaultImageUriProvider.LIBRARY_TAB_ICON_ID;
        }

        public final int getRECENT_ICON() {
            return DefaultImageUriProvider.RECENT_ICON;
        }

        public final int getALBUM_ICON() {
            return DefaultImageUriProvider.ALBUM_ICON;
        }

        public final int getARTIST_ICON() {
            return DefaultImageUriProvider.ARTIST_ICON;
        }

        public final int getFIRE_ICON() {
            return DefaultImageUriProvider.FIRE_ICON;
        }

        public final int getPLAYLIST_ICON() {
            return DefaultImageUriProvider.PLAYLIST_ICON;
        }

        public final int getSONG_ICON() {
            return DefaultImageUriProvider.SONG_ICON;
        }

        public final int getPRESETS_ICON() {
            return DefaultImageUriProvider.PRESETS_ICON;
        }
    }

    public final Uri getRecentIconUri() {
        return this.recentIconUri;
    }

    public final Uri getAlbumIconUri() {
        return this.albumIconUri;
    }

    public final Uri getArtistIconUri() {
        return this.artistIconUri;
    }

    public final Uri getFireIconUri() {
        return this.fireIconUri;
    }

    public final Uri getPlaylistIconUri() {
        return this.playlistIconUri;
    }

    public final Uri getSongIconUri() {
        return this.songIconUri;
    }

    public final Uri getPresetsIconUri() {
        return this.presetsIconUri;
    }

    /* JADX INFO: renamed from: getSeeMoreIconUri, reason: from getter */
    public final Uri getSeeMoreIconLocalUri() {
        return this.seeMoreIconLocalUri;
    }

    /* JADX INFO: renamed from: getMediaItemIconUri, reason: from getter */
    public final Uri getDefaultMediaIconLocalUri() {
        return this.defaultMediaIconLocalUri;
    }

    /* JADX INFO: renamed from: getHomeTabIconUri, reason: from getter */
    public final Uri getHomeTabIconLocalUri() {
        return this.homeTabIconLocalUri;
    }

    /* JADX INFO: renamed from: getLibraryTabIconUri, reason: from getter */
    public final Uri getLibraryTabIconLocalUri() {
        return this.libraryTabIconLocalUri;
    }
}
