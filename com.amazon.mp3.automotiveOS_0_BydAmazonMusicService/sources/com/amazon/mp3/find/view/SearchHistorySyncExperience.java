package com.amazon.mp3.find.view;

import android.content.Context;
import com.amazon.mp3.find.dagger.SearchFeaturesProviderDefault;
import com.amazon.mp3.util.OneTimeExperience;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchHistorySyncExperience.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/find/view/SearchHistorySyncExperience;", "Lcom/amazon/mp3/util/OneTimeExperience;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "searchFeaturesProvider", "Lcom/amazon/mp3/find/dagger/SearchFeaturesProviderDefault;", "canPerformSync", "", "disableSyncExperience", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchHistorySyncExperience extends OneTimeExperience {
    private final SearchFeaturesProviderDefault searchFeaturesProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchHistorySyncExperience(Context context) {
        super("sync_default_profile_search_history_experience", context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.searchFeaturesProvider = new SearchFeaturesProviderDefault(context);
    }

    public final boolean canPerformSync() {
        if (Intrinsics.areEqual(this.searchFeaturesProvider.isDefaultProfile(), Boolean.TRUE)) {
            return super.canPresentExperience();
        }
        return false;
    }

    public final void disableSyncExperience() {
        super.disableExperiencePresentation();
    }
}
