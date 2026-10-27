package com.amazon.mp3.providers;

import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.fragment.contextmenu.AbstractPlaylistContextMenuListener;
import com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PlaylistTrackContextMenuProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0015\u001a\u00020\u0016H\u0014R\u0014\u0010\u000f\u001a\u00020\u0010X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/providers/PlaylistTrackContextMenuProvider;", "Lcom/amazon/mp3/providers/LibraryContextMenuProvider;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/mp3/activity/BaseActivity;", "fragment", "Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "trackContextMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/AbstractTrackContextMenuListener;", "playlistContextMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/AbstractPlaylistContextMenuListener;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "(Lcom/amazon/mp3/activity/BaseActivity;Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/mp3/fragment/contextmenu/AbstractTrackContextMenuListener;Lcom/amazon/mp3/fragment/contextmenu/AbstractPlaylistContextMenuListener;Lkotlinx/coroutines/CoroutineScope;)V", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getActionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getMetadataId", "", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getMetadataIdType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistTrackContextMenuProvider extends LibraryContextMenuProvider {
    private final ActionType actionType;
    private final AbstractTrackContextMenuListener trackContextMenuListener;

    @Override // com.amazon.mp3.providers.LibraryContextMenuProvider
    protected String getMetadataId(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        return null;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistTrackContextMenuProvider(BaseActivity activity, ContextMenuSupportingFragment fragment, PageType pageType, AbstractTrackContextMenuListener abstractTrackContextMenuListener, AbstractPlaylistContextMenuListener abstractPlaylistContextMenuListener, CoroutineScope coroutineScope) {
        super(activity, fragment, pageType, abstractTrackContextMenuListener, abstractPlaylistContextMenuListener, null, null, coroutineScope);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        this.trackContextMenuListener = abstractTrackContextMenuListener;
        this.actionType = ActionType.SHOW_OVERFLOW;
    }

    @Override // com.amazon.mp3.providers.LibraryContextMenuProvider
    protected ActionType getActionType() {
        return this.actionType;
    }

    @Override // com.amazon.mp3.providers.LibraryContextMenuProvider
    protected EntityIdType getMetadataIdType(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        return EntityIdType.CD_OBJECT_ID;
    }
}
