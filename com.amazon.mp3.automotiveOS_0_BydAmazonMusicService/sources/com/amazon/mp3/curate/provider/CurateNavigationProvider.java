package com.amazon.mp3.curate.provider;

import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.provider.NavigationProvider;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CurateNavigationProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated(message = "No longer used", replaceWith = @ReplaceWith(expression = "CurateLibraryNavigationProvider", imports = {}))
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0016J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\n\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0004H\u0016J\b\u0010\r\u001a\u00020\u0007H\u0016J\u0018\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0004H\u0016J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateNavigationProvider;", "Lcom/amazon/music/curate/provider/NavigationProvider;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "goToAlbum", "", "asin", "goToArtist", "goToBrowseHome", "goToEditPlaylist", "id", "goToMyMusic", "goToPlaylist", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "goToProfile", "profileId", "goToSimilarMusic", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CurateNavigationProvider implements NavigationProvider {
    private final String TAG = CurateNavigationProvider.class.getSimpleName();

    @Override // com.amazon.music.curate.provider.NavigationProvider
    public void goToAlbum(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
    }

    @Override // com.amazon.music.curate.provider.NavigationProvider
    public void goToArtist(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
    }

    @Override // com.amazon.music.curate.provider.NavigationProvider
    public void goToBrowseHome() {
    }

    @Override // com.amazon.music.curate.provider.NavigationProvider
    public void goToEditPlaylist(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
    }

    @Override // com.amazon.music.curate.provider.NavigationProvider
    public void goToMyMusic() {
    }

    @Override // com.amazon.music.curate.provider.NavigationProvider
    public void goToProfile(String profileId) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
    }

    @Override // com.amazon.music.curate.provider.NavigationProvider
    public void goToSimilarMusic(String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0030  */
    @Override // com.amazon.music.curate.provider.NavigationProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void goToPlaylist(com.amazon.music.curate.model.Playlist r6, androidx.fragment.app.FragmentActivity r7) {
        /*
            r5 = this;
            java.lang.String r0 = "playlist"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = r6.getId()
            java.lang.Boolean r2 = r6.getIsPersonalized()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            boolean r2 = r2.booleanValue()
            r3 = 1
            if (r2 == 0) goto L30
            java.lang.Boolean r6 = r6.getIsFollowed()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto L30
            r6 = r3
            goto L31
        L30:
            r6 = 0
        L31:
            com.amazon.mp3.activity.settings.LastViewedScreenUtil$LastViewedSource r2 = com.amazon.mp3.library.adapter.LibraryPagerAdapter.DEFAULT_LIBRARY_SOURCE
            com.amazon.mp3.activity.settings.LastViewedScreenUtil$LastViewedSource r2 = com.amazon.mp3.activity.settings.LastViewedScreenUtil.getLastViewedSource(r7, r2)
            java.lang.String r2 = r2.toCirrusMediaSource()
            if (r6 == 0) goto L5a
            android.content.Context r6 = r7.getApplicationContext()
            android.net.Uri r6 = com.amazon.mp3.library.provider.MediaProvider.UdoPlaylists.getContentUriFromLuid(r6, r2, r1)
            if (r6 != 0) goto L4f
            java.lang.String r6 = r5.TAG
            java.lang.String r7 = "Playlist sync isn't complete. Failed to open playlist detail page."
            com.amazon.mp3.util.Log.error(r6, r7)
            return
        L4f:
            android.content.Intent r6 = com.amazon.mp3.library.activity.LibraryActivityFactory.getIntentForContentUri(r7, r6)
            r0 = 4
            r1 = 0
            androidx.fragment.app.Fragment r6 = com.amazon.mp3.detailpages.playlist.PlaylistDetailFactory.createFragment$default(r6, r3, r1, r0, r1)
            goto L7a
        L5a:
            java.lang.String r6 = "cirrus"
            android.net.Uri r2 = com.amazon.mp3.library.provider.MediaProvider.PrimePlaylists.getContentUri(r6, r1)
            java.lang.String r4 = "com.amazon.mp3.library.EXTRA_CONTENT_URI"
            r0.putParcelable(r4, r2)
            java.lang.String r2 = "com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT"
            r0.putBoolean(r2, r3)
            java.lang.String r2 = "com.amazon.mp3.library.EXTRA_SHOW_HEADER"
            r0.putBoolean(r2, r3)
            java.lang.String r2 = "com.amazon.mp3.library.EXTRA_SOURCE_ID"
            r0.putString(r2, r6)
            java.lang.String r6 = ""
            androidx.fragment.app.Fragment r6 = com.amazon.mp3.detailpages.playlist.PlaylistDetailFactory.createFragment(r1, r6, r3, r0)
        L7a:
            androidx.fragment.app.FragmentManager r7 = r7.getSupportFragmentManager()
            androidx.fragment.app.FragmentTransaction r7 = r7.beginTransaction()
            r0 = 2130772059(0x7f01005b, float:1.7147226E38)
            r1 = 2130772015(0x7f01002f, float:1.7147136E38)
            androidx.fragment.app.FragmentTransaction r7 = r7.setCustomAnimations(r0, r1)
            r0 = 2131429250(0x7f0b0782, float:1.8480167E38)
            androidx.fragment.app.FragmentTransaction r6 = r7.replace(r0, r6)
            java.lang.Class<com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment> r7 = com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment.class
            java.lang.String r7 = r7.getSimpleName()
            androidx.fragment.app.FragmentTransaction r6 = r6.addToBackStack(r7)
            r6.commitAllowingStateLoss()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.curate.provider.CurateNavigationProvider.goToPlaylist(com.amazon.music.curate.model.Playlist, androidx.fragment.app.FragmentActivity):void");
    }
}
