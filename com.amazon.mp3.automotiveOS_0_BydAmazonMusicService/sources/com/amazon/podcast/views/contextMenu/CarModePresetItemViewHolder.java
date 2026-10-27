package com.amazon.podcast.views.contextMenu;

import Podcast.EpisodeOptionsInterface.v1_0.CarPresetElement;
import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.EpisodeOptionsInterface.v1_0.PodcastShowCarPresetElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$string;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
final class CarModePresetItemViewHolder extends ContextMenuItemViewHolder {
    private final Context context;
    private boolean isCarModePreset;
    private final Resources resources;

    public CarModePresetItemViewHolder(@NonNull final View itemView, final Resources resources, final Context context) {
        super(itemView);
        this.resources = resources;
        this.context = context;
    }

    public void bind(final EpisodeOptionsElement episodeOptionsElement, final LifecycleOwner lifecycleOwner, final EmberTextView dismiss, final boolean isPopUp, final UiMetricAttributes$PageType pageType) {
        if (episodeOptionsElement.getCarPreset() == null) {
            return;
        }
        CarPresetElement carPreset = episodeOptionsElement.getCarPreset();
        if (carPreset instanceof PodcastShowCarPresetElement) {
            final PodcastShowCarPresetElement podcastShowCarPresetElement = (PodcastShowCarPresetElement) carPreset;
            Podcast.getCarModeUserPresetHandler().isPartOfPresets(this.context, podcastShowCarPresetElement.getId()).observe(lifecycleOwner, new Observer<Boolean>() { // from class: com.amazon.podcast.views.contextMenu.CarModePresetItemViewHolder.1
                @Override // androidx.lifecycle.Observer
                public void onChanged(Boolean value) {
                    CarModePresetItemViewHolder.this.isCarModePreset = value.booleanValue();
                    CarModePresetItemViewHolder.this.bindCarModePresetIcon(isPopUp);
                }
            });
            super.bindClickerListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.contextMenu.CarModePresetItemViewHolder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    String id = podcastShowCarPresetElement.getId();
                    UiMetricAttributes$PageType uiMetricAttributes$PageType = pageType;
                    String string = uiMetricAttributes$PageType == null ? "" : uiMetricAttributes$PageType.toString();
                    if (CarModePresetItemViewHolder.this.isCarModePreset) {
                        Podcast.getCarModeUserPresetHandler().onRemovePodcastShowFromPresetsClicked(CarModePresetItemViewHolder.this.context, id, string);
                    } else {
                        Podcast.getCarModeUserPresetHandler().onAddPodcastShowToPresetsClicked(CarModePresetItemViewHolder.this.context, id, string);
                    }
                    EmberTextView emberTextView = dismiss;
                    if (emberTextView == null) {
                        return;
                    }
                    emberTextView.performClick();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindCarModePresetIcon(final boolean isPopUp) {
        if (this.isCarModePreset) {
            super.bindImage(R$drawable.ic_car_mode_close_button);
            String string = this.resources.getString(R$string.podcast_remove_from_preset_label);
            super.bindText(string, isPopUp);
            this.itemView.setContentDescription(string);
            this.itemView.setId(R$id.context_menu_remove_from_preset_element);
            return;
        }
        super.bindImage(R$drawable.ic_icon_car_white);
        String string2 = this.resources.getString(R$string.podcast_add_to_preset_label);
        super.bindText(string2, isPopUp);
        this.itemView.setContentDescription(string2);
        this.itemView.setId(R$id.context_menu_add_to_preset_element);
    }
}
