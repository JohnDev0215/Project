package com.amazon.music.find.utils;

import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.find.common.ContentTier;
import com.amazon.music.platform.find.common.ParentalControlItem;
import com.amazon.music.platform.find.common.ParentalControlsPerContentTier;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ParentalControlsPerContentTierUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/find/utils/ParentalControlsPerContentTierUtils;", "", "()V", "convertContentAccessTypeExplicitMap", "Lcom/amazon/music/platform/find/common/ParentalControlsPerContentTier;", "explicitMap", "", "Lcom/amazon/music/ContentAccessType;", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ParentalControlsPerContentTierUtils {
    public static final ParentalControlsPerContentTierUtils INSTANCE = new ParentalControlsPerContentTierUtils();

    private ParentalControlsPerContentTierUtils() {
    }

    public final ParentalControlsPerContentTier convertContentAccessTypeExplicitMap(Map<ContentAccessType, Boolean> explicitMap) {
        Intrinsics.checkNotNullParameter(explicitMap, "explicitMap");
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<ContentAccessType, Boolean> entry : explicitMap.entrySet()) {
            ContentTier contentTierConvertFromContentAccessType = ContentTierUtils.INSTANCE.convertFromContentAccessType(entry.getKey());
            ParentalControlItem parentalControlItem = contentTierConvertFromContentAccessType == null ? null : new ParentalControlItem(contentTierConvertFromContentAccessType, entry.getValue().booleanValue());
            if (parentalControlItem != null) {
                arrayList.add(parentalControlItem);
            }
        }
        return new ParentalControlsPerContentTier(arrayList);
    }
}
