package org.chromium.components.offline_items_collection.bridges;

import android.graphics.Bitmap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.offline_items_collection.OfflineItemVisuals;

/* JADX INFO: loaded from: classes3.dex */
public final class OfflineItemVisualsBridge {
    @CalledByNative
    public static OfflineItemVisuals createOfflineItemVisuals(Bitmap bitmap) {
        OfflineItemVisuals offlineItemVisuals = new OfflineItemVisuals();
        offlineItemVisuals.a = bitmap;
        return offlineItemVisuals;
    }
}
