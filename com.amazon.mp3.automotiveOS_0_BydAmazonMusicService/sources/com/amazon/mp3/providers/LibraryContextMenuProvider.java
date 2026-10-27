package com.amazon.mp3.providers;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.detailpages.album.AlbumDetailFragment;
import com.amazon.mp3.fragment.contextmenu.AbstractPlaylistContextMenuListener;
import com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener;
import com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.ArtistContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.ContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.FollowedArtistContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider;
import com.amazon.mp3.library.fragment.PlaylistDetailFragment;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: compiled from: LibraryContextMenuProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 Z2\u00020\u00012\u00020\u0002:\u0001ZBY\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\b\u0010<\u001a\u00020=H\u0002J\b\u0010>\u001a\u00020=H\u0002J\u0012\u0010?\u001a\u0004\u0018\u00010@2\u0006\u0010A\u001a\u000204H\u0014J\u0012\u0010B\u001a\u0004\u0018\u00010C2\u0006\u0010A\u001a\u000204H\u0014J\u0012\u0010D\u001a\u0004\u0018\u00010E2\u0006\u0010F\u001a\u00020GH\u0014J\u001c\u0010H\u001a\u00020I2\b\u0010J\u001a\u0004\u0018\u00010K2\b\u0010L\u001a\u0004\u0018\u00010@H\u0016J\u001c\u0010M\u001a\u00020=2\b\u0010N\u001a\u0004\u0018\u00010O2\b\u0010L\u001a\u0004\u0018\u00010@H\u0016J\u0010\u0010P\u001a\u00020=2\u0006\u0010Q\u001a\u00020RH\u0016J \u0010S\u001a\u00020=2\u0006\u0010T\u001a\u0002062\u0006\u0010U\u001a\u0002042\u0006\u0010V\u001a\u00020+H\u0016J\b\u0010W\u001a\u00020IH\u0016J\u0010\u0010X\u001a\u00020I2\u0006\u0010U\u001a\u000204H\u0016J\u0010\u0010Y\u001a\u00020=2\u0006\u0010U\u001a\u000204H\u0016R\u0014\u0010\u0014\u001a\u00020\u0015X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u0004\u0018\u00010\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\u001e\u001a\u0004\u0018\u00010\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u001d\u001a\u0004\b \u0010!R\u0014\u0010#\u001a\b\u0012\u0002\b\u0003\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010%\u001a\u0004\u0018\u00010&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u001d\u001a\u0004\b'\u0010(R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010.\u001a\u0004\u0018\u00010/8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u0010\u001d\u001a\u0004\b0\u00101R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u00107\u001a\u0004\u0018\u0001088TX\u0094\u0084\u0002¢\u0006\f\n\u0004\b;\u0010\u001d\u001a\u0004\b9\u0010:¨\u0006["}, d2 = {"Lcom/amazon/mp3/providers/LibraryContextMenuProvider;", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "Landroidx/lifecycle/DefaultLifecycleObserver;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "fragment", "Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "trackMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/AbstractTrackContextMenuListener;", "playlistMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/AbstractPlaylistContextMenuListener;", "albumMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/AlbumContextMenuProvider$AlbumContextMenuProviderListener;", "followedArtistContextMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider$FollowedArtistContextMenuListener;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/mp3/fragment/contextmenu/AbstractTrackContextMenuListener;Lcom/amazon/mp3/fragment/contextmenu/AbstractPlaylistContextMenuListener;Lcom/amazon/mp3/fragment/contextmenu/AlbumContextMenuProvider$AlbumContextMenuProviderListener;Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider$FollowedArtistContextMenuListener;Lkotlinx/coroutines/CoroutineScope;)V", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getActionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "albumMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/AlbumContextMenuProvider;", "getAlbumMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/AlbumContextMenuProvider;", "albumMenuProvider$delegate", "Lkotlin/Lazy;", "artistMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/ArtistContextMenuProvider;", "getArtistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/ArtistContextMenuProvider;", "artistMenuProvider$delegate", "contextMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuProvider;", "followedArtistMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider;", "getFollowedArtistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider;", "followedArtistMenuProvider$delegate", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "item", "", "playlistMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/PlaylistContextMenuProvider;", "getPlaylistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/PlaylistContextMenuProvider;", "playlistMenuProvider$delegate", "selectedMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "selectedView", "Landroid/view/View;", "trackMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/TrackContextMenuProvider;", "getTrackMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/TrackContextMenuProvider;", "trackMenuProvider$delegate", "clearSelectedData", "", "fetchItemFromDB", "getMetadataId", "", "contentMetadata", "getMetadataIdType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", "getMusicTrack", "Lcom/amazon/mp3/library/item/MusicTrack;", "trackMetadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "onContextMenuItemSelected", "", "menuItem", "Landroid/view/MenuItem;", "sourceId", "onCreateContextMenu", "menu", "Landroid/view/ContextMenu;", "onDestroy", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "Landroidx/lifecycle/LifecycleOwner;", "openContextMenu", "view", "metadata", "interaction", "shouldBindContextMenuOnFreeTier", "shouldDisplayContextMenu", "updateStateProvider", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class LibraryContextMenuProvider implements MetadataContextMenuHandler, DefaultLifecycleObserver {
    private static final String TAG;
    private final ActionType actionType;
    private FragmentActivity activity;
    private AlbumContextMenuProvider.AlbumContextMenuProviderListener albumMenuListener;

    /* JADX INFO: renamed from: albumMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy albumMenuProvider;

    /* JADX INFO: renamed from: artistMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy artistMenuProvider;
    private ContextMenuProvider<?> contextMenuProvider;
    private final CoroutineScope coroutineScope;
    private FollowedArtistContextMenuProvider.FollowedArtistContextMenuListener followedArtistContextMenuListener;

    /* JADX INFO: renamed from: followedArtistMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy followedArtistMenuProvider;
    private ContextMenuSupportingFragment fragment;
    private InteractionType interactionType;
    private Object item;
    private final PageType pageType;
    private AbstractPlaylistContextMenuListener playlistMenuListener;

    /* JADX INFO: renamed from: playlistMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy playlistMenuProvider;
    private ContentMetadata selectedMetadata;
    private View selectedView;
    private AbstractTrackContextMenuListener trackMenuListener;

    /* JADX INFO: renamed from: trackMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy trackMenuProvider;

    protected String getMetadataId(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        return null;
    }

    protected EntityIdType getMetadataIdType(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        return null;
    }

    @Override // com.amazon.music.views.library.providers.MetadataContextMenuProvider
    public void updateStateProvider(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
    }

    public LibraryContextMenuProvider(FragmentActivity fragmentActivity, ContextMenuSupportingFragment contextMenuSupportingFragment, PageType pageType, AbstractTrackContextMenuListener abstractTrackContextMenuListener, AbstractPlaylistContextMenuListener abstractPlaylistContextMenuListener, AlbumContextMenuProvider.AlbumContextMenuProviderListener albumContextMenuProviderListener, FollowedArtistContextMenuProvider.FollowedArtistContextMenuListener followedArtistContextMenuListener, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        this.activity = fragmentActivity;
        this.fragment = contextMenuSupportingFragment;
        this.pageType = pageType;
        this.trackMenuListener = abstractTrackContextMenuListener;
        this.playlistMenuListener = abstractPlaylistContextMenuListener;
        this.albumMenuListener = albumContextMenuProviderListener;
        this.followedArtistContextMenuListener = followedArtistContextMenuListener;
        this.coroutineScope = coroutineScope;
        this.trackMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<TrackContextMenuProvider>() { // from class: com.amazon.mp3.providers.LibraryContextMenuProvider$trackMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TrackContextMenuProvider invoke() {
                AbstractTrackContextMenuListener abstractTrackContextMenuListener2 = this.this$0.trackMenuListener;
                if (abstractTrackContextMenuListener2 == null) {
                    return null;
                }
                return new TrackContextMenuProvider(this.this$0.activity, 0, abstractTrackContextMenuListener2);
            }
        });
        this.actionType = ActionType.VIEW_CONTEXT_MENU;
        this.albumMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<AlbumContextMenuProvider>() { // from class: com.amazon.mp3.providers.LibraryContextMenuProvider$albumMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AlbumContextMenuProvider invoke() {
                return new AlbumContextMenuProvider(this.this$0.activity, this.this$0.albumMenuListener);
            }
        });
        this.artistMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<ArtistContextMenuProvider>() { // from class: com.amazon.mp3.providers.LibraryContextMenuProvider$artistMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ArtistContextMenuProvider invoke() {
                return new ArtistContextMenuProvider(this.this$0.activity, true);
            }
        });
        this.followedArtistMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<FollowedArtistContextMenuProvider>() { // from class: com.amazon.mp3.providers.LibraryContextMenuProvider$followedArtistMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FollowedArtistContextMenuProvider invoke() {
                FragmentActivity fragmentActivity2 = this.this$0.activity;
                if (fragmentActivity2 == null) {
                    return null;
                }
                return new FollowedArtistContextMenuProvider(fragmentActivity2, this.this$0.followedArtistContextMenuListener);
            }
        });
        this.playlistMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<PlaylistContextMenuProvider>() { // from class: com.amazon.mp3.providers.LibraryContextMenuProvider$playlistMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlaylistContextMenuProvider invoke() {
                boolean z = UserSubscriptionUtil.isNightwingOnly() || !(this.this$0.fragment instanceof PlaylistDetailFragment);
                FragmentActivity fragmentActivity2 = this.this$0.activity;
                if (fragmentActivity2 != null) {
                    return new PlaylistContextMenuProvider((BaseActivity) fragmentActivity2, 0, this.this$0.playlistMenuListener, z);
                }
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.BaseActivity");
            }
        });
    }

    public /* synthetic */ LibraryContextMenuProvider(FragmentActivity fragmentActivity, ContextMenuSupportingFragment contextMenuSupportingFragment, PageType pageType, AbstractTrackContextMenuListener abstractTrackContextMenuListener, AbstractPlaylistContextMenuListener abstractPlaylistContextMenuListener, AlbumContextMenuProvider.AlbumContextMenuProviderListener albumContextMenuProviderListener, FollowedArtistContextMenuProvider.FollowedArtistContextMenuListener followedArtistContextMenuListener, CoroutineScope coroutineScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragmentActivity, contextMenuSupportingFragment, pageType, (i & 8) != 0 ? null : abstractTrackContextMenuListener, (i & 16) != 0 ? null : abstractPlaylistContextMenuListener, (i & 32) != 0 ? null : albumContextMenuProviderListener, (i & 64) != 0 ? null : followedArtistContextMenuListener, coroutineScope);
    }

    static {
        String simpleName = LibraryContextMenuProvider.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "LibraryContextMenuProvider::class.java.simpleName");
        TAG = simpleName;
    }

    protected TrackContextMenuProvider getTrackMenuProvider() {
        return (TrackContextMenuProvider) this.trackMenuProvider.getValue();
    }

    protected ActionType getActionType() {
        return this.actionType;
    }

    private final AlbumContextMenuProvider getAlbumMenuProvider() {
        return (AlbumContextMenuProvider) this.albumMenuProvider.getValue();
    }

    private final ArtistContextMenuProvider getArtistMenuProvider() {
        return (ArtistContextMenuProvider) this.artistMenuProvider.getValue();
    }

    private final FollowedArtistContextMenuProvider getFollowedArtistMenuProvider() {
        return (FollowedArtistContextMenuProvider) this.followedArtistMenuProvider.getValue();
    }

    private final PlaylistContextMenuProvider getPlaylistMenuProvider() {
        return (PlaylistContextMenuProvider) this.playlistMenuProvider.getValue();
    }

    @Override // com.amazon.music.views.library.providers.MetadataContextMenuProvider
    public boolean shouldDisplayContextMenu(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if ((metadata instanceof PlaylistMetadata) || (metadata instanceof TrackMetadata) || (metadata instanceof AlbumMetadata) || (metadata instanceof ArtistMetadata)) {
            return true;
        }
        boolean z = metadata instanceof UserPlaylistMetadata;
        return false;
    }

    @Override // com.amazon.music.views.library.providers.MetadataContextMenuProvider
    public void openContextMenu(View view, ContentMetadata metadata, InteractionType interaction) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(interaction, "interaction");
        this.selectedMetadata = metadata;
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, Dispatchers.getIO(), null, new AnonymousClass1(view, interaction, null), 2, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.providers.LibraryContextMenuProvider$openContextMenu$1, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryContextMenuProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.providers.LibraryContextMenuProvider$openContextMenu$1", f = "LibraryContextMenuProvider.kt", i = {}, l = {104}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ InteractionType $interaction;
        final /* synthetic */ View $view;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(View view, InteractionType interactionType, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$view = view;
            this.$interaction = interactionType;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LibraryContextMenuProvider.this.new AnonymousClass1(this.$view, this.$interaction, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LibraryContextMenuProvider.this.fetchItemFromDB();
                MainCoroutineDispatcher main2 = Dispatchers.getMain();
                C01001 c01001 = new C01001(LibraryContextMenuProvider.this, this.$view, this.$interaction, null);
                this.label = 1;
                if (BuildersKt.withContext(main2, c01001, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.amazon.mp3.providers.LibraryContextMenuProvider$openContextMenu$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: LibraryContextMenuProvider.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.providers.LibraryContextMenuProvider$openContextMenu$1$1", f = "LibraryContextMenuProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ InteractionType $interaction;
            final /* synthetic */ View $view;
            int label;
            final /* synthetic */ LibraryContextMenuProvider this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01001(LibraryContextMenuProvider libraryContextMenuProvider, View view, InteractionType interactionType, Continuation<? super C01001> continuation) {
                super(2, continuation);
                this.this$0 = libraryContextMenuProvider;
                this.$view = view;
                this.$interaction = interactionType;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01001(this.this$0, this.$view, this.$interaction, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.selectedView = this.$view;
                this.this$0.interactionType = this.$interaction;
                ContextMenuSupportingFragment contextMenuSupportingFragment = this.this$0.fragment;
                if (contextMenuSupportingFragment != null) {
                    contextMenuSupportingFragment.openContextMenuForView(this.this$0, this.$view);
                }
                return Unit.INSTANCE;
            }
        }
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public void onCreateContextMenu(ContextMenu menu, String sourceId) {
        ContextMenuProvider<?> artistMenuProvider;
        ContentMetadata contentMetadata = this.selectedMetadata;
        if (contentMetadata == null) {
            return;
        }
        String metadataId = getMetadataId(contentMetadata);
        EntityIdType metadataIdType = getMetadataIdType(contentMetadata);
        ContentMetadata contentMetadata2 = this.selectedMetadata;
        if (contentMetadata2 instanceof TrackMetadata) {
            this.contextMenuProvider = getTrackMenuProvider();
        } else if (contentMetadata2 instanceof AlbumMetadata) {
            this.contextMenuProvider = getAlbumMenuProvider();
        } else if (contentMetadata2 instanceof PlaylistMetadata) {
            this.contextMenuProvider = getPlaylistMenuProvider();
        } else if (contentMetadata2 instanceof ArtistMetadata) {
            if (AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled()) {
                artistMenuProvider = getFollowedArtistMenuProvider();
            } else {
                artistMenuProvider = getArtistMenuProvider();
            }
            this.contextMenuProvider = artistMenuProvider;
        }
        if (this.selectedMetadata instanceof PlaylistMetadata) {
            ContextMenuProvider<?> contextMenuProvider = this.contextMenuProvider;
            if (contextMenuProvider != null) {
                contextMenuProvider.onCreateContextMenu(this.activity, menu, this.item, 1, sourceId);
            }
            ContextMenuProvider<?> contextMenuProvider2 = this.contextMenuProvider;
            if (contextMenuProvider2 == null) {
                return;
            }
            contextMenuProvider2.setPageType(this.pageType);
            return;
        }
        ContextMenuProvider<?> contextMenuProvider3 = this.contextMenuProvider;
        if (contextMenuProvider3 != null) {
            contextMenuProvider3.onCreateContextMenu(this.activity, menu, this.item, 0, sourceId, getActionType(), this.pageType, this.interactionType, metadataId, metadataIdType);
        }
        ContextMenuProvider<?> contextMenuProvider4 = this.contextMenuProvider;
        if (contextMenuProvider4 == null) {
            return;
        }
        ContentMetadata contentMetadata3 = this.selectedMetadata;
        contextMenuProvider4.setBlockRef(contentMetadata3 == null ? null : contentMetadata3.getViewId());
    }

    protected MusicTrack getMusicTrack(TrackMetadata trackMetadata) {
        Intrinsics.checkNotNullParameter(trackMetadata, "trackMetadata");
        FragmentActivity fragmentActivity = this.activity;
        MusicTrack musicTrack = null;
        if (fragmentActivity != null) {
            AbstractItem item = new CirrusSourceLibraryItemFactory(fragmentActivity).getItem(trackMetadata.getUri());
            if (item instanceof MusicTrack) {
                musicTrack = (MusicTrack) item;
            }
        }
        if (musicTrack != null) {
            musicTrack.setParentEligibilities(trackMetadata.getParentEligibilities());
        }
        return musicTrack;
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public boolean onContextMenuItemSelected(MenuItem menuItem, String sourceId) {
        if (this.contextMenuProvider == null) {
            Log.error(TAG, "Could not handle context menu selection; ContextMenuProvider is null");
            return false;
        }
        ContentMetadata contentMetadata = this.selectedMetadata;
        long artistId = 0;
        if (contentMetadata instanceof AlbumMetadata) {
            if (contentMetadata == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.AlbumMetadata");
            }
            artistId = MediaProvider.Albums.getAlbumId(((AlbumMetadata) contentMetadata).getUri());
        } else if (contentMetadata instanceof PlaylistMetadata) {
            if (!(this.item instanceof CatalogPlaylist)) {
                if (contentMetadata == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.PlaylistMetadata");
                }
                artistId = PlaylistUtil.getPlaylistId(((PlaylistMetadata) contentMetadata).getUri());
            }
        } else if (contentMetadata instanceof ArtistMetadata) {
            if (contentMetadata == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.ArtistMetadata");
            }
            artistId = MediaProvider.Artists.getArtistId(((ArtistMetadata) contentMetadata).getUri());
        }
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = new AdapterView.AdapterContextMenuInfo(this.selectedView, 0, artistId);
        adapterContextMenuInfo.targetView.setTag(this.item);
        ContextMenuProvider<?> contextMenuProvider = this.contextMenuProvider;
        if (contextMenuProvider != null) {
            return contextMenuProvider.onContextMenuItemSelected(this.activity, menuItem, adapterContextMenuInfo, sourceId);
        }
        clearSelectedData();
        return false;
    }

    @Override // com.amazon.music.views.library.providers.MetadataContextMenuProvider
    public boolean shouldBindContextMenuOnFreeTier() {
        return AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fetchItemFromDB() {
        ContextMenuProvider<?> artistMenuProvider;
        TrackContextMenuProvider trackMenuProvider;
        ContentMetadata contentMetadata = this.selectedMetadata;
        if (contentMetadata instanceof TrackMetadata) {
            if (contentMetadata != null) {
                TrackMetadata trackMetadata = (TrackMetadata) contentMetadata;
                this.item = getMusicTrack(trackMetadata);
                Integer rowNumber = trackMetadata.getRowNumber();
                if (rowNumber != null) {
                    int iIntValue = rowNumber.intValue();
                    Object obj = this.item;
                    MusicTrack musicTrack = obj instanceof MusicTrack ? (MusicTrack) obj : null;
                    if (musicTrack != null) {
                        musicTrack.setTrackNum(iIntValue);
                    }
                }
                TrackContextMenuProvider trackMenuProvider2 = getTrackMenuProvider();
                if (trackMenuProvider2 != null) {
                    trackMenuProvider2.canAddToLibrary = Intrinsics.areEqual(trackMetadata.getIsInLibrary(), Boolean.FALSE);
                }
                if (!(this.fragment instanceof AlbumDetailFragment) || (trackMenuProvider = getTrackMenuProvider()) == null) {
                    return;
                }
                ContextMenuSupportingFragment contextMenuSupportingFragment = this.fragment;
                if (contextMenuSupportingFragment != null) {
                    trackMenuProvider.canRemoveFromDevice = ((AlbumDetailFragment) contextMenuSupportingFragment).isSourceLocal();
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.detailpages.album.AlbumDetailFragment");
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.TrackMetadata");
        }
        if (contentMetadata instanceof AlbumMetadata) {
            if (contentMetadata != null) {
                AlbumMetadata albumMetadata = (AlbumMetadata) contentMetadata;
                if (this.activity == null) {
                    return;
                }
                this.item = new CirrusSourceLibraryItemFactory(this.activity).getItem(albumMetadata.getUri());
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.AlbumMetadata");
        }
        if (contentMetadata instanceof PlaylistMetadata) {
            if (contentMetadata != null) {
                AbstractItem item = new CirrusSourceLibraryItemFactory(this.activity).getItem(((PlaylistMetadata) contentMetadata).getUri());
                if (item instanceof CatalogPlaylist) {
                    ((CatalogPlaylist) item).getTracks();
                }
                this.item = item;
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.PlaylistMetadata");
        }
        if (contentMetadata instanceof ArtistMetadata) {
            if (contentMetadata != null) {
                ArtistMetadata artistMetadata = (ArtistMetadata) contentMetadata;
                if (AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled()) {
                    artistMenuProvider = getFollowedArtistMenuProvider();
                } else {
                    artistMenuProvider = getArtistMenuProvider();
                }
                this.contextMenuProvider = artistMenuProvider;
                if (this.activity == null) {
                    return;
                }
                this.item = new CirrusSourceLibraryItemFactory(this.activity).getItem(artistMetadata.getUri());
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.ArtistMetadata");
        }
    }

    private final void clearSelectedData() {
        this.selectedMetadata = null;
        this.selectedView = null;
        this.interactionType = null;
        this.contextMenuProvider = null;
        this.item = null;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onDestroy(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        super.onDestroy(owner);
        this.activity = null;
        this.fragment = null;
        this.trackMenuListener = null;
        this.playlistMenuListener = null;
        this.albumMenuListener = null;
        this.followedArtistContextMenuListener = null;
    }
}
