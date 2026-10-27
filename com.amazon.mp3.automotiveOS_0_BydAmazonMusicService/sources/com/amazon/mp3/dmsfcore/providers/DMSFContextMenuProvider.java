package com.amazon.mp3.dmsfcore.providers;

import ExternalActionInterface.v1_0.Album;
import ExternalActionInterface.v1_0.Artist;
import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.Playlist;
import ExternalActionInterface.v1_0.Station;
import ExternalActionInterface.v1_0.Track;
import ExternalActionInterface.v1_0.TriggerExternalContextMenuMethod;
import android.content.Context;
import android.view.ContextMenu;
import android.view.MenuItem;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.catalog.fragment.contextmenu.PrimeAlbumContextMenuListener;
import com.amazon.mp3.catalog.fragment.contextmenu.PrimePlaylistContextMenuListener;
import com.amazon.mp3.catalog.fragment.contextmenu.PrimeTrackContextMenuListener;
import com.amazon.mp3.explore.MusicEntityConverters;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.PrimeArtistContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.StationContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.VideoContextMenuProvider;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.prime.browse.PrimeArtistPlaybackHandler;
import com.amazon.music.skyfire.ui.providers.ContextMenuProvider;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DMSFContextMenuProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFContextMenuProvider;", "Lcom/amazon/music/skyfire/ui/providers/ContextMenuProvider;", "()V", "item", "", "menuProvider", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuProvider;", "onMenuItemSelected", "", "menuItem", "Landroid/view/MenuItem;", "openMenu", "", "context", "Landroid/content/Context;", "menu", "Landroid/view/ContextMenu;", "method", "LExternalActionInterface/v1_0/TriggerExternalContextMenuMethod;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DMSFContextMenuProvider implements ContextMenuProvider {
    private static FragmentActivity mActivity;
    private Object item;
    private com.amazon.mp3.fragment.contextmenu.ContextMenuProvider<?> menuProvider;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<PrimeAlbumContextMenuProvider> albumMenuProvider$delegate = LazyKt__LazyJVMKt.lazy(new Function0<PrimeAlbumContextMenuProvider>() { // from class: com.amazon.mp3.dmsfcore.providers.DMSFContextMenuProvider$Companion$albumMenuProvider$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PrimeAlbumContextMenuProvider invoke() {
            return new PrimeAlbumContextMenuProvider(DMSFContextMenuProvider.mActivity, new PrimeAlbumContextMenuListener(DMSFContextMenuProvider.mActivity));
        }
    });
    private static final Lazy<PrimeArtistContextMenuProvider> artistMenuProvider$delegate = LazyKt__LazyJVMKt.lazy(new Function0<PrimeArtistContextMenuProvider>() { // from class: com.amazon.mp3.dmsfcore.providers.DMSFContextMenuProvider$Companion$artistMenuProvider$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PrimeArtistContextMenuProvider invoke() {
            return new PrimeArtistContextMenuProvider(DMSFContextMenuProvider.mActivity, new PrimeArtistPlaybackHandler(DMSFContextMenuProvider.mActivity));
        }
    });
    private static final Lazy<PrimePlaylistContextMenuProvider> playlistMenuProvider$delegate = LazyKt__LazyJVMKt.lazy(new Function0<PrimePlaylistContextMenuProvider>() { // from class: com.amazon.mp3.dmsfcore.providers.DMSFContextMenuProvider$Companion$playlistMenuProvider$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PrimePlaylistContextMenuProvider invoke() {
            return new PrimePlaylistContextMenuProvider(DMSFContextMenuProvider.mActivity, new PrimePlaylistContextMenuListener(DMSFContextMenuProvider.mActivity), ContextMenuPageType.LONG_PRESS);
        }
    });
    private static final Lazy<PrimeTrackContextMenuProvider> trackMenuProvider$delegate = LazyKt__LazyJVMKt.lazy(new Function0<PrimeTrackContextMenuProvider>() { // from class: com.amazon.mp3.dmsfcore.providers.DMSFContextMenuProvider$Companion$trackMenuProvider$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PrimeTrackContextMenuProvider invoke() {
            FragmentActivity fragmentActivity = DMSFContextMenuProvider.mActivity;
            FragmentActivity fragmentActivity2 = DMSFContextMenuProvider.mActivity;
            FragmentActivity fragmentActivity3 = DMSFContextMenuProvider.mActivity;
            Intrinsics.checkNotNull(fragmentActivity3);
            return new PrimeTrackContextMenuProvider(fragmentActivity, new PrimeTrackContextMenuListener(fragmentActivity2, new LibraryStateProvider(fragmentActivity3)));
        }
    });
    private static final Lazy<StationContextMenuProvider> stationMenuProvider$delegate = LazyKt__LazyJVMKt.lazy(new Function0<StationContextMenuProvider>() { // from class: com.amazon.mp3.dmsfcore.providers.DMSFContextMenuProvider$Companion$stationMenuProvider$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final StationContextMenuProvider invoke() {
            FragmentActivity fragmentActivity = DMSFContextMenuProvider.mActivity;
            if (fragmentActivity != null) {
                return new StationContextMenuProvider((BaseActivity) fragmentActivity);
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.BaseActivity");
        }
    });
    private static final Lazy<VideoContextMenuProvider> videoMenuProvider$delegate = LazyKt__LazyJVMKt.lazy(new Function0<VideoContextMenuProvider>() { // from class: com.amazon.mp3.dmsfcore.providers.DMSFContextMenuProvider$Companion$videoMenuProvider$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final VideoContextMenuProvider invoke() {
            FragmentActivity fragmentActivity = DMSFContextMenuProvider.mActivity;
            Intrinsics.checkNotNull(fragmentActivity);
            return new VideoContextMenuProvider(fragmentActivity, new VideoContextMenuProvider.VideoContextMenuProviderListener());
        }
    });

    @Override // com.amazon.music.skyfire.ui.providers.ContextMenuProvider
    public void openMenu(Context context, ContextMenu menu, TriggerExternalContextMenuMethod method) {
        com.amazon.mp3.fragment.contextmenu.ContextMenuProvider<?> videoMenuProvider;
        com.amazon.mp3.fragment.contextmenu.ContextMenuProvider<?> contextMenuProvider;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(method, "method");
        FragmentActivity fragmentActivity = context instanceof FragmentActivity ? (FragmentActivity) context : null;
        if (fragmentActivity == null) {
            fragmentActivity = mActivity;
        }
        mActivity = fragmentActivity;
        PlayableContent playableContent = method.playableContent();
        if (!(playableContent instanceof Album)) {
            if (!(playableContent instanceof Artist)) {
                if (!(playableContent instanceof Playlist)) {
                    if (!(playableContent instanceof Station)) {
                        if (!(playableContent instanceof Track)) {
                            return;
                        } else {
                            videoMenuProvider = ((Track) playableContent).isVideo() ? INSTANCE.getVideoMenuProvider() : INSTANCE.getTrackMenuProvider();
                        }
                    } else {
                        videoMenuProvider = INSTANCE.getStationMenuProvider();
                    }
                } else {
                    videoMenuProvider = INSTANCE.getPlaylistMenuProvider();
                }
            } else {
                videoMenuProvider = INSTANCE.getArtistMenuProvider();
            }
        } else {
            videoMenuProvider = INSTANCE.getAlbumMenuProvider();
        }
        this.menuProvider = videoMenuProvider;
        MusicEntityConverters.Companion companion = MusicEntityConverters.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(playableContent, "this");
        Object objConvertToMusicEntity = companion.convertToMusicEntity(playableContent);
        Intrinsics.checkNotNull(objConvertToMusicEntity);
        this.item = objConvertToMusicEntity;
        com.amazon.mp3.fragment.contextmenu.ContextMenuProvider<?> contextMenuProvider2 = this.menuProvider;
        if (contextMenuProvider2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuProvider");
            contextMenuProvider = null;
        } else {
            contextMenuProvider = contextMenuProvider2;
        }
        FragmentActivity fragmentActivity2 = mActivity;
        Object obj = this.item;
        if (obj == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
            obj = Unit.INSTANCE;
        }
        contextMenuProvider.onCreateContextMenu(fragmentActivity2, menu, obj, 0, "cirrus");
    }

    @Override // com.amazon.music.skyfire.ui.providers.ContextMenuProvider
    public boolean onMenuItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        com.amazon.mp3.fragment.contextmenu.ContextMenuProvider<?> contextMenuProvider = this.menuProvider;
        if (contextMenuProvider == null) {
            return false;
        }
        if (contextMenuProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("menuProvider");
            contextMenuProvider = null;
        }
        FragmentActivity fragmentActivity = mActivity;
        Object obj = this.item;
        if (obj == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
            obj = Unit.INSTANCE;
        }
        return contextMenuProvider.onContextMenuItemSelected(fragmentActivity, menuItem, obj, "cirrus");
    }

    /* JADX INFO: compiled from: DMSFContextMenuProvider.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\f\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\f\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\f\u001a\u0004\b \u0010!R\u001b\u0010#\u001a\u00020$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\f\u001a\u0004\b%\u0010&¨\u0006("}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFContextMenuProvider$Companion;", "", "()V", "POSITION", "", "SOURCE_ID", "", "albumMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/PrimeAlbumContextMenuProvider;", "getAlbumMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/PrimeAlbumContextMenuProvider;", "albumMenuProvider$delegate", "Lkotlin/Lazy;", "artistMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/PrimeArtistContextMenuProvider;", "getArtistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/PrimeArtistContextMenuProvider;", "artistMenuProvider$delegate", "mActivity", "Landroidx/fragment/app/FragmentActivity;", "playlistMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/PrimePlaylistContextMenuProvider;", "getPlaylistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/PrimePlaylistContextMenuProvider;", "playlistMenuProvider$delegate", "stationMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/StationContextMenuProvider;", "getStationMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/StationContextMenuProvider;", "stationMenuProvider$delegate", "trackMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/PrimeTrackContextMenuProvider;", "getTrackMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/PrimeTrackContextMenuProvider;", "trackMenuProvider$delegate", "videoMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider;", "getVideoMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider;", "videoMenuProvider$delegate", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final PrimeAlbumContextMenuProvider getAlbumMenuProvider() {
            return (PrimeAlbumContextMenuProvider) DMSFContextMenuProvider.albumMenuProvider$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final PrimeArtistContextMenuProvider getArtistMenuProvider() {
            return (PrimeArtistContextMenuProvider) DMSFContextMenuProvider.artistMenuProvider$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final PrimePlaylistContextMenuProvider getPlaylistMenuProvider() {
            return (PrimePlaylistContextMenuProvider) DMSFContextMenuProvider.playlistMenuProvider$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final PrimeTrackContextMenuProvider getTrackMenuProvider() {
            return (PrimeTrackContextMenuProvider) DMSFContextMenuProvider.trackMenuProvider$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final StationContextMenuProvider getStationMenuProvider() {
            return (StationContextMenuProvider) DMSFContextMenuProvider.stationMenuProvider$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final VideoContextMenuProvider getVideoMenuProvider() {
            return (VideoContextMenuProvider) DMSFContextMenuProvider.videoMenuProvider$delegate.getValue();
        }
    }
}
