package com.amazon.mp3.auto.carmode;

import android.app.Activity;
import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.auto.storage.PresetEntityType;
import com.amazon.mp3.auto.storage.PresetIdentifier;
import com.amazon.mp3.auto.storage.PresetIdentifierHelper;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.podcast.external.car.CarModeUserPresetHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeUserPresetHandlerImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0016J \u0010\r\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/auto/carmode/CarModeUserPresetHandlerImpl;", "Lcom/amazon/podcast/external/car/CarModeUserPresetHandler;", "()V", "isPartOfPresets", "Landroidx/lifecycle/LiveData;", "", "context", "Landroid/content/Context;", "showId", "", "onAddPodcastShowToPresetsClicked", "", "pageType", "onRemovePodcastShowFromPresetsClicked", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeUserPresetHandlerImpl implements CarModeUserPresetHandler {
    @Override // com.amazon.podcast.external.car.CarModeUserPresetHandler
    public void onAddPodcastShowToPresetsClicked(Context context, String showId, String pageType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(showId, "showId");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        CarModePresetsUtility.INSTANCE.sendMetrics(ActionType.CAR_MODE_OVERFLOW_ADD_TO_PRESETS, pageType, EntityType.PODCAST_SHOW);
        if (CarModePresetsUtility.isPresetsFull(context)) {
            Activity currentActivity = AndroidUtils.getCurrentActivity();
            Intrinsics.checkNotNullExpressionValue(currentActivity, "getCurrentActivity()");
            new BauhausDialogBuilder(currentActivity).setTitle(context.getResources().getString(R.string.alert_title_presets_full)).setNeutralButton(R.string.dmusic_button_ok, null).setMessage(context.getResources().getString(R.string.alert_presets_full)).create().show();
        } else {
            PresetIdentifierHelper.add(context, new PresetIdentifier(showId, PresetEntityType.PodcastShow));
            BauhausToastUtils.showTextToast(context, R.string.dmusic_added_to_presets, 0);
        }
    }

    @Override // com.amazon.podcast.external.car.CarModeUserPresetHandler
    public void onRemovePodcastShowFromPresetsClicked(Context context, String showId, String pageType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(showId, "showId");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        CarModePresetsUtility.INSTANCE.sendMetrics(ActionType.CAR_MODE_OVERFLOW_REMOVE_FROM_PRESETS, pageType, EntityType.PODCAST_SHOW);
        PresetIdentifierHelper.delete$default(context, new PresetIdentifier(showId, PresetEntityType.PodcastShow), false, 4, null);
        BauhausToastUtils.showTextToast(context, R.string.dmusic_removed_from_presets, 0);
    }

    @Override // com.amazon.podcast.external.car.CarModeUserPresetHandler
    public LiveData<Boolean> isPartOfPresets(Context context, String showId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(showId, "showId");
        MutableLiveData mutableLiveData = new MutableLiveData();
        mutableLiveData.postValue(Boolean.valueOf(PresetIdentifierHelper.check(context, showId)));
        return mutableLiveData;
    }
}
