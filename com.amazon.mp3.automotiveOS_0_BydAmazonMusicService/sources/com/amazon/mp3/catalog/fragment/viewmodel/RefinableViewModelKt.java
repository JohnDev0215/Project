package com.amazon.mp3.catalog.fragment.viewmodel;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.views.library.models.FilterItemModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: RefinableViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "Lcom/amazon/music/views/library/models/FilterItemModel;", "getType", "(Lcom/amazon/music/views/library/models/FilterItemModel;)Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class RefinableViewModelKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final RefinementFilterType getType(FilterItemModel filterItemModel) {
        return RefinementFilterType.INSTANCE.getRefinementTypeFromDisplayName(filterItemModel.getTitle());
    }
}
