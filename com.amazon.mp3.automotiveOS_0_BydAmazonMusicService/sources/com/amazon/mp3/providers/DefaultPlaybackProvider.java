package com.amazon.mp3.providers;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.prime.browse.PrimeArtistPlaybackHandler;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.PlaybackProvider;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultPlaybackProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001f\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/providers/DefaultPlaybackProvider;", "Lcom/amazon/music/views/library/providers/PlaybackProvider;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;)V", "artistPlaybackHandler", "Lcom/amazon/mp3/prime/browse/PrimeArtistPlaybackHandler;", "getArtistPlaybackHandler", "()Lcom/amazon/mp3/prime/browse/PrimeArtistPlaybackHandler;", "artistPlaybackHandler$delegate", "Lkotlin/Lazy;", "shouldEnableStationPlayback", "", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "startPlayback", "", "withShuffle", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/Boolean;)V", "startStationPlayback", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultPlaybackProvider implements PlaybackProvider {
    private final FragmentActivity activity;

    /* JADX INFO: renamed from: artistPlaybackHandler$delegate, reason: from kotlin metadata */
    private final Lazy artistPlaybackHandler;
    private final ContentEnabilityProvider contentEnabilityProvider;

    public DefaultPlaybackProvider(FragmentActivity activity, ContentEnabilityProvider contentEnabilityProvider) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(contentEnabilityProvider, "contentEnabilityProvider");
        this.activity = activity;
        this.contentEnabilityProvider = contentEnabilityProvider;
        this.artistPlaybackHandler = LazyKt__LazyJVMKt.lazy(new Function0<PrimeArtistPlaybackHandler>() { // from class: com.amazon.mp3.providers.DefaultPlaybackProvider$artistPlaybackHandler$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeArtistPlaybackHandler invoke() {
                return new PrimeArtistPlaybackHandler(this.this$0.activity);
            }
        });
    }

    private final PrimeArtistPlaybackHandler getArtistPlaybackHandler() {
        return (PrimeArtistPlaybackHandler) this.artistPlaybackHandler.getValue();
    }

    @Override // com.amazon.music.views.library.providers.PlaybackProvider
    public void startPlayback(ContentMetadata metadata, Boolean withShuffle) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (metadata instanceof ArtistMetadata) {
            if (UserSubscriptionUtil.isNightwingOnly()) {
                StationUtils.startSFA(this.activity, ((ArtistMetadata) metadata).getAsin(), "ARTIST_SEED", PlaybackPageType.ARTIST_DETAIL, ControlSource.APP_UI);
            } else {
                getArtistPlaybackHandler().startPlayback(PrimeItemsTransformationUtil.convertToPrimeArtist((ArtistMetadata) metadata), Intrinsics.areEqual(withShuffle, Boolean.TRUE) ? PrimeCollectionTask.Task.PLAYBACK_SHUFFLE : PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN);
            }
            UIEventHandler.INSTANCE.emitUiClickMetric(ActionType.PLAY_ARTIST, metadata);
        }
    }

    @Override // com.amazon.music.views.library.providers.PlaybackProvider
    public boolean shouldEnableStationPlayback(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return this.contentEnabilityProvider.shouldEnableContent(metadata);
    }

    @Override // com.amazon.music.views.library.providers.PlaybackProvider
    public void startStationPlayback(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (metadata instanceof ArtistMetadata) {
            ArtistMetadata artistMetadata = (ArtistMetadata) metadata;
            StationUtils.startSFA((Context) this.activity, artistMetadata.getAsin(), "ARTIST_SEED", PlaybackPageType.ARTIST_DETAIL, ControlSource.APP_UI, false);
            StationUtils.sendStartSFAUiClickMetrics(artistMetadata.getAsin(), EntityIdType.SFA_ARTIST_SEED);
        }
    }
}
