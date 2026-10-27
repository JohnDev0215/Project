package org.chromium.components.offline_items_collection.bridges;

import android.net.Uri;
import android.text.TextUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.offline_items_collection.OfflineItemShareInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class OfflineItemShareInfoBridge {
    @CalledByNative
    public static OfflineItemShareInfo createOfflineItemShareInfo(String str) {
        OfflineItemShareInfo offlineItemShareInfo = new OfflineItemShareInfo();
        if (!TextUtils.isEmpty(str)) {
            offlineItemShareInfo.a = Uri.parse(str);
        }
        return offlineItemShareInfo;
    }
}
