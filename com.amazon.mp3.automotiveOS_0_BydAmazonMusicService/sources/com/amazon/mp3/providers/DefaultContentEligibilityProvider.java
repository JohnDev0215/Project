package com.amazon.mp3.providers;

import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.TableRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.ContentEligibilityProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultContentEligibilityProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\tH\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0007H\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/providers/DefaultContentEligibilityProvider;", "Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "()V", "shouldBadgeStationArtwork", "", "model", "Lcom/amazon/music/views/library/models/HorizontalTileModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "shouldDisplayHawkfireUpsell", "Lcom/amazon/music/views/library/models/TableRowItemModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultContentEligibilityProvider implements ContentEligibilityProvider {
    @Override // com.amazon.music.views.library.providers.ContentEligibilityProvider
    public boolean shouldDisplayHawkfireUpsell(BaseViewModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        if (model instanceof HorizontalTileModel) {
            return shouldDisplayHawkfireUpsell((HorizontalTileModel) model);
        }
        if (model instanceof TableRowItemModel) {
            return shouldDisplayHawkfireUpsell((TableRowItemModel) model);
        }
        return false;
    }

    @Override // com.amazon.music.views.library.providers.ContentEligibilityProvider
    public boolean shouldBadgeStationArtwork(BaseViewModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        if (model instanceof HorizontalTileModel) {
            return shouldBadgeStationArtwork((HorizontalTileModel) model);
        }
        return false;
    }

    private final boolean shouldDisplayHawkfireUpsell(HorizontalTileModel model) {
        return SearchItemUtils.userIsPrimeOnly() && SearchItemUtils.isContentHawkfireOnly(model.getMetadata()) && Feature.unlimited.isEnabled();
    }

    private final boolean shouldDisplayHawkfireUpsell(TableRowItemModel model) {
        return SearchItemUtils.userIsPrimeOnly() && SearchItemUtils.isContentHawkfireOnly(model.getMetadata()) && Feature.unlimited.isEnabled();
    }

    private final boolean shouldBadgeStationArtwork(HorizontalTileModel model) {
        boolean zUserIsNightwingOnly = SearchItemUtils.userIsNightwingOnly();
        boolean z = model.getMetadata() instanceof TrackMetadata;
        Boolean isOwned = z ? ((TrackMetadata) model.getMetadata()).getIsOwned() : null;
        return zUserIsNightwingOnly && z && !(isOwned == null ? false : isOwned.booleanValue());
    }
}
