package com.amazon.mp3.providers;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.playback.handler.StationPlaybackHandler;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.providers.GenreClickListenerProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenreClickHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/providers/GenreClickHandler;", "Lcom/amazon/music/views/library/providers/GenreClickListenerProvider;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "onStationActionIconClick", "", "context", "Landroid/content/Context;", "stationMetadata", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GenreClickHandler implements GenreClickListenerProvider {
    private final FragmentActivity activity;

    public GenreClickHandler(FragmentActivity fragmentActivity) {
        this.activity = fragmentActivity;
    }

    @Override // com.amazon.music.views.library.providers.GenreClickListenerProvider
    public void onStationActionIconClick(Context context, StationMetadata stationMetadata) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(stationMetadata, "stationMetadata");
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        new StationPlaybackHandler(this.activity, PlaybackPageType.UNKNOWN).checkCanStartPlayback(PrimeItemsTransformationUtil.convertToStation(stationMetadata), PrimeCollectionTask.Task.PLAYBACK);
    }
}
