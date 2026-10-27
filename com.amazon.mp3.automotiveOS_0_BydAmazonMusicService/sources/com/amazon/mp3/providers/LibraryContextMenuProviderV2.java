package com.amazon.mp3.providers;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.fragment.contextmenu.ContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.FollowedArtistContextMenuProvider;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryContextMenuProviderV2.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 /2\u00020\u0001:\u0001/B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u001c\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u001c\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J \u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u0016H\u0016J\b\u0010,\u001a\u00020 H\u0016J\u0010\u0010-\u001a\u00020 2\u0006\u0010*\u001a\u00020\u001aH\u0016J\u0010\u0010.\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u001aH\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u0004\u0018\u00010\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/amazon/mp3/providers/LibraryContextMenuProviderV2;", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "fragment", "Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "artistContextMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider$FollowedArtistContextMenuListener;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider$FollowedArtistContextMenuListener;)V", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "artistMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider;", "getArtistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider;", "artistMenuProvider$delegate", "Lkotlin/Lazy;", "contextMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuProvider;", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "item", "", "selectedMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "selectedView", "Landroid/view/View;", "clearSelectedData", "", "onContextMenuItemSelected", "", "menuItem", "Landroid/view/MenuItem;", "sourceId", "", "onCreateContextMenu", "menu", "Landroid/view/ContextMenu;", "openContextMenu", "view", "metadata", "interaction", "shouldBindContextMenuOnFreeTier", "shouldDisplayContextMenu", "updateStateProvider", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class LibraryContextMenuProviderV2 implements MetadataContextMenuHandler {
    private static final String TAG;
    private final ActionType actionType;
    private final FragmentActivity activity;
    private final FollowedArtistContextMenuProvider.FollowedArtistContextMenuListener artistContextMenuListener;

    /* JADX INFO: renamed from: artistMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy artistMenuProvider;
    private ContextMenuProvider<?> contextMenuProvider;
    private final ContextMenuSupportingFragment fragment;
    private InteractionType interactionType;
    private Object item;
    private final PageType pageType;
    private ContentMetadata selectedMetadata;
    private View selectedView;

    @Override // com.amazon.music.views.library.providers.MetadataContextMenuProvider
    public void updateStateProvider(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
    }

    public LibraryContextMenuProviderV2(FragmentActivity activity, ContextMenuSupportingFragment fragment, PageType pageType, FollowedArtistContextMenuProvider.FollowedArtistContextMenuListener followedArtistContextMenuListener) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.activity = activity;
        this.fragment = fragment;
        this.pageType = pageType;
        this.artistContextMenuListener = followedArtistContextMenuListener;
        this.actionType = ActionType.VIEW_CONTEXT_MENU;
        this.artistMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<FollowedArtistContextMenuProvider>() { // from class: com.amazon.mp3.providers.LibraryContextMenuProviderV2$artistMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FollowedArtistContextMenuProvider invoke() {
                return new FollowedArtistContextMenuProvider(this.this$0.activity, this.this$0.artistContextMenuListener);
            }
        });
    }

    static {
        String simpleName = LibraryContextMenuProviderV2.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "LibraryContextMenuProvid…V2::class.java.simpleName");
        TAG = simpleName;
    }

    private final FollowedArtistContextMenuProvider getArtistMenuProvider() {
        return (FollowedArtistContextMenuProvider) this.artistMenuProvider.getValue();
    }

    @Override // com.amazon.music.views.library.providers.MetadataContextMenuProvider
    public boolean shouldDisplayContextMenu(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return metadata instanceof ArtistMetadata;
    }

    @Override // com.amazon.music.views.library.providers.MetadataContextMenuProvider
    public void openContextMenu(View view, ContentMetadata metadata, InteractionType interaction) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(interaction, "interaction");
        this.selectedMetadata = metadata;
        this.selectedView = view;
        this.interactionType = interaction;
        this.fragment.openContextMenuForView(this, view);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public void onCreateContextMenu(ContextMenu menu, String sourceId) {
        ContentMetadata contentMetadata = this.selectedMetadata;
        if (contentMetadata != null && (contentMetadata instanceof ArtistMetadata)) {
            if (contentMetadata == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.ArtistMetadata");
            }
            this.contextMenuProvider = getArtistMenuProvider();
            this.item = (ArtistMetadata) contentMetadata;
        }
        ContextMenuProvider<?> contextMenuProvider = this.contextMenuProvider;
        if (contextMenuProvider != null) {
            ContentMetadata contentMetadata2 = this.selectedMetadata;
            contextMenuProvider.setBlockRef(contentMetadata2 == null ? null : contentMetadata2.getViewId());
        }
        ContextMenuProvider<?> contextMenuProvider2 = this.contextMenuProvider;
        if (contextMenuProvider2 == null) {
            return;
        }
        contextMenuProvider2.onCreateContextMenu(this.activity, menu, this.item, 0, sourceId, this.actionType, this.pageType, this.interactionType, null, null);
    }

    @Override // com.amazon.music.views.library.providers.MetadataContextMenuProvider
    public boolean shouldBindContextMenuOnFreeTier() {
        return AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public boolean onContextMenuItemSelected(MenuItem menuItem, String sourceId) {
        long artistId;
        if (this.contextMenuProvider == null) {
            Log.error(TAG, "Could not handle context menu selection; ContextMenuProvider is null");
            return false;
        }
        ContentMetadata contentMetadata = this.selectedMetadata;
        if (!(contentMetadata instanceof ArtistMetadata)) {
            artistId = 0;
        } else {
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

    private final void clearSelectedData() {
        this.selectedMetadata = null;
        this.selectedView = null;
        this.interactionType = null;
        this.contextMenuProvider = null;
        this.item = null;
    }
}
