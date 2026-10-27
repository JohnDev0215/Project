package com.amazon.mp3.providers;

import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.fragment.contextmenu.AbstractPlaylistContextMenuListener;
import com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener;
import com.amazon.mp3.fragment.contextmenu.PrimePlaylistTrackContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.TrackMetadata;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PrimePlaylistContextMenuProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0013\u001a\u0004\u0018\u00010\u00148TX\u0094\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/providers/PrimePlaylistContextMenuProvider;", "Lcom/amazon/mp3/providers/LibraryContextMenuProvider;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/mp3/activity/BaseActivity;", "fragment", "Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "startFromBrowsePrime", "", "trackContextMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/AbstractTrackContextMenuListener;", "playlistContextMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/AbstractPlaylistContextMenuListener;", "headerCount", "", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "(Lcom/amazon/mp3/activity/BaseActivity;Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;ZLcom/amazon/mp3/fragment/contextmenu/AbstractTrackContextMenuListener;Lcom/amazon/mp3/fragment/contextmenu/AbstractPlaylistContextMenuListener;ILkotlinx/coroutines/CoroutineScope;)V", "trackMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/TrackContextMenuProvider;", "getTrackMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/TrackContextMenuProvider;", "trackMenuProvider$delegate", "Lkotlin/Lazy;", "getMusicTrack", "Lcom/amazon/mp3/library/item/MusicTrack;", "trackMetadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PrimePlaylistContextMenuProvider extends LibraryContextMenuProvider {
    private final BaseActivity activity;

    /* JADX INFO: renamed from: trackMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy trackMenuProvider;

    public /* synthetic */ PrimePlaylistContextMenuProvider(BaseActivity baseActivity, ContextMenuSupportingFragment contextMenuSupportingFragment, PageType pageType, boolean z, AbstractTrackContextMenuListener abstractTrackContextMenuListener, AbstractPlaylistContextMenuListener abstractPlaylistContextMenuListener, int i, CoroutineScope coroutineScope, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(baseActivity, contextMenuSupportingFragment, pageType, z, abstractTrackContextMenuListener, abstractPlaylistContextMenuListener, (i2 & 64) != 0 ? 0 : i, coroutineScope);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrimePlaylistContextMenuProvider(BaseActivity activity, ContextMenuSupportingFragment fragment, PageType pageType, final boolean z, final AbstractTrackContextMenuListener abstractTrackContextMenuListener, AbstractPlaylistContextMenuListener abstractPlaylistContextMenuListener, final int i, CoroutineScope coroutineScope) {
        super(activity, fragment, pageType, abstractTrackContextMenuListener, abstractPlaylistContextMenuListener, null, null, coroutineScope);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        this.activity = activity;
        this.trackMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<PrimePlaylistTrackContextMenuProvider>() { // from class: com.amazon.mp3.providers.PrimePlaylistContextMenuProvider$trackMenuProvider$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimePlaylistTrackContextMenuProvider invoke() {
                return new PrimePlaylistTrackContextMenuProvider(this.this$0.activity, i, z, abstractTrackContextMenuListener);
            }
        });
    }

    @Override // com.amazon.mp3.providers.LibraryContextMenuProvider
    protected TrackContextMenuProvider getTrackMenuProvider() {
        return (TrackContextMenuProvider) this.trackMenuProvider.getValue();
    }

    @Override // com.amazon.mp3.providers.LibraryContextMenuProvider
    protected MusicTrack getMusicTrack(TrackMetadata trackMetadata) {
        Intrinsics.checkNotNullParameter(trackMetadata, "trackMetadata");
        MusicTrack trackIncludingPrimePlaylistTrack = AmazonApplication.getLibraryItemFactory().getTrackIncludingPrimePlaylistTrack(trackMetadata.getUri());
        if (trackIncludingPrimePlaylistTrack != null) {
            trackIncludingPrimePlaylistTrack.setParentEligibilities(trackMetadata.getParentEligibilities());
        }
        return trackIncludingPrimePlaylistTrack;
    }
}
