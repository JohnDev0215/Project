package androidx.transition;

import android.view.ViewGroup;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
class ViewGroupUtils {
    static ViewGroupOverlayImpl getOverlay(@NonNull ViewGroup viewGroup) {
        return new ViewGroupOverlayApi18(viewGroup);
    }

    static void suppressLayout(@NonNull ViewGroup viewGroup, boolean z) {
        ViewGroupUtilsApi18.suppressLayout(viewGroup, z);
    }

    private ViewGroupUtils() {
    }
}
