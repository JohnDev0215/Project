package com.amazon.music.views.library.providers;

import com.amazon.music.views.library.models.enums.TargetType;
import kotlin.Metadata;

/* JADX INFO: compiled from: BrushUriClickListenerProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH&¨\u0006\t"}, d2 = {"Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "", "onClick", "", "target", "", "viewId", "targetType", "Lcom/amazon/music/views/library/models/enums/TargetType;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface BrushUriClickListenerProvider {
    void onClick(String target, String viewId, TargetType targetType);

    /* JADX INFO: compiled from: BrushUriClickListenerProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void onClick$default(BrushUriClickListenerProvider brushUriClickListenerProvider, String str, String str2, TargetType targetType, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onClick");
            }
            if ((i & 4) != 0) {
                targetType = null;
            }
            brushUriClickListenerProvider.onClick(str, str2, targetType);
        }
    }
}
