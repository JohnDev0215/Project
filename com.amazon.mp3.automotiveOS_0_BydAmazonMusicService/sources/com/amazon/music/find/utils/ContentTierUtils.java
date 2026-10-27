package com.amazon.music.find.utils;

import UpsellInterface.v1_0.UpsellType;
import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.find.common.ContentTier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentTierUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\tJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\b¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/find/utils/ContentTierUtils;", "", "()V", "convertFromContentAccessType", "Lcom/amazon/music/platform/find/common/ContentTier;", "contentAccessTypes", "Lcom/amazon/music/ContentAccessType;", "convertFromContentAccessTypeSet", "", "", "convertFromSearchContentTier", "contentTier", "", "convertFromSearchContentTierList", "contentTiers", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContentTierUtils {
    public static final ContentTierUtils INSTANCE = new ContentTierUtils();

    /* JADX INFO: compiled from: ContentTierUtils.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ContentAccessType.values().length];
            iArr[ContentAccessType.NIGHTWING.ordinal()] = 1;
            iArr[ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE.ordinal()] = 2;
            iArr[ContentAccessType.HAWKFIRE.ordinal()] = 3;
            iArr[ContentAccessType.PRIME.ordinal()] = 4;
            iArr[ContentAccessType.SONIC_RUSH.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ContentTierUtils() {
    }

    public final ContentTier convertFromContentAccessType(ContentAccessType contentAccessTypes) {
        Intrinsics.checkNotNullParameter(contentAccessTypes, "contentAccessTypes");
        int i = WhenMappings.$EnumSwitchMapping$0[contentAccessTypes.ordinal()];
        if (i == 1) {
            return ContentTier.FREE;
        }
        if (i == 2) {
            return ContentTier.FREE_ON_DEMAND_PLAYABLE;
        }
        if (i == 3) {
            return ContentTier.MUSIC_SUBSCRIPTION;
        }
        if (i == 4) {
            return ContentTier.PRIME;
        }
        if (i != 5) {
            return null;
        }
        return ContentTier.SONIC_RUSH;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final ContentTier convertFromSearchContentTier(String contentTier) {
        if (contentTier != null) {
            switch (contentTier.hashCode()) {
                case -1288105721:
                    if (contentTier.equals("NIGHTWING_ONDEMAND_PLAYABLE")) {
                        return ContentTier.FREE_ON_DEMAND_PLAYABLE;
                    }
                    break;
                case -141600765:
                    if (contentTier.equals("NIGHTWING")) {
                        return ContentTier.FREE;
                    }
                    break;
                case 76397151:
                    if (contentTier.equals(UpsellType.PRIME)) {
                        return ContentTier.PRIME;
                    }
                    break;
                case 379545793:
                    if (contentTier.equals(UpsellType.UNLIMITED)) {
                        return ContentTier.MUSIC_SUBSCRIPTION;
                    }
                    break;
                case 686193195:
                    if (contentTier.equals("SONIC_RUSH")) {
                        return ContentTier.SONIC_RUSH;
                    }
                    break;
            }
        }
        return null;
    }

    public final List<ContentTier> convertFromContentAccessTypeSet(Set<? extends ContentAccessType> contentAccessTypes) {
        Intrinsics.checkNotNullParameter(contentAccessTypes, "contentAccessTypes");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = contentAccessTypes.iterator();
        while (it.hasNext()) {
            ContentTier contentTierConvertFromContentAccessType = INSTANCE.convertFromContentAccessType((ContentAccessType) it.next());
            if (contentTierConvertFromContentAccessType != null) {
                arrayList.add(contentTierConvertFromContentAccessType);
            }
        }
        return arrayList;
    }

    public final List<ContentTier> convertFromSearchContentTierList(List<String> contentTiers) {
        Intrinsics.checkNotNullParameter(contentTiers, "contentTiers");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = contentTiers.iterator();
        while (it.hasNext()) {
            ContentTier contentTierConvertFromSearchContentTier = INSTANCE.convertFromSearchContentTier((String) it.next());
            if (contentTierConvertFromSearchContentTier != null) {
                arrayList.add(contentTierConvertFromSearchContentTier);
            }
        }
        return arrayList;
    }
}
