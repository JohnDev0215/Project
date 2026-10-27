package com.amazon.podcast.external.car;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

/* JADX INFO: loaded from: classes5.dex */
public interface CarModeUserPresetHandler {
    LiveData<Boolean> isPartOfPresets(@NonNull final Context context, @NonNull final String showId);

    void onAddPodcastShowToPresetsClicked(@NonNull final Context context, @NonNull final String showId, @NonNull final String pageType);

    void onRemovePodcastShowFromPresetsClicked(@NonNull final Context context, @NonNull final String showId, @NonNull final String pageType);
}
