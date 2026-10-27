package com.amazon.music.views.library.metadata;

import com.amazon.music.ContentAccessType;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.views.library.metadata.UpdatableContentMetadata;
import com.amazon.music.views.library.models.base.ContentEligibilityData;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00078&@&X¦\u000e¢\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u0004\u0018\u00010\u00078&@&X¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR$\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000e8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/views/library/metadata/EligibilityMetadata;", "Lcom/amazon/music/views/library/metadata/UpdatableContentMetadata;", "Lcom/amazon/music/views/library/models/base/ContentEligibilityData;", "eligibilityData", "", "updateEligibilityData", "(Lcom/amazon/music/views/library/models/base/ContentEligibilityData;)Ljava/lang/Integer;", "", "isOwned", "()Ljava/lang/Boolean;", "setOwned", "(Ljava/lang/Boolean;)V", "isDownloaded", "setDownloaded", "", "Lcom/amazon/music/ContentAccessType;", "getContentTiers", "()Ljava/util/Set;", "setContentTiers", "(Ljava/util/Set;)V", "contentTiers", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "getOnDemandTiers", "setOnDemandTiers", "onDemandTiers", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public interface EligibilityMetadata extends UpdatableContentMetadata {
    Set<ContentAccessType> getContentTiers();

    Set<SubscriptionType> getOnDemandTiers();

    Boolean isDownloaded();

    Boolean isOwned();

    void setContentTiers(Set<? extends ContentAccessType> set);

    void setDownloaded(Boolean bool);

    void setOwned(Boolean bool);

    Integer updateEligibilityData(ContentEligibilityData eligibilityData);

    /* JADX INFO: compiled from: ContentMetadata.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static boolean areEqual(EligibilityMetadata eligibilityMetadata, Object obj) {
            Intrinsics.checkNotNullParameter(eligibilityMetadata, "this");
            return UpdatableContentMetadata.DefaultImpls.areEqual(eligibilityMetadata, obj);
        }

        public static boolean areItemsTheSame(EligibilityMetadata eligibilityMetadata, Object obj) {
            Intrinsics.checkNotNullParameter(eligibilityMetadata, "this");
            return UpdatableContentMetadata.DefaultImpls.areItemsTheSame(eligibilityMetadata, obj);
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x007f  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x008a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static java.lang.Integer updateEligibilityData(com.amazon.music.views.library.metadata.EligibilityMetadata r6, com.amazon.music.views.library.models.base.ContentEligibilityData r7) {
            /*
                java.lang.String r0 = "this"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                java.lang.String r0 = "eligibilityData"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                java.lang.Boolean r0 = r7.getIsOwned()
                r1 = 5
                java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                r2 = 0
                if (r0 != 0) goto L18
                goto L31
            L18:
                r0.booleanValue()
                java.lang.Boolean r0 = r7.getIsOwned()
                java.lang.Boolean r3 = r6.isOwned()
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
                if (r0 != 0) goto L31
                java.lang.Boolean r0 = r7.getIsOwned()
                r6.setOwned(r0)
                r2 = r1
            L31:
                java.util.Set r0 = r7.getSupportedTiers()
                if (r0 != 0) goto L38
                goto L8c
            L38:
                com.amazon.music.views.library.utils.EligibilityUtil r0 = com.amazon.music.views.library.utils.EligibilityUtil.INSTANCE
                java.util.Set r3 = r7.getSupportedTiers()
                java.util.Set r3 = r0.toSubscriptionType(r3)
                java.util.Set r4 = r7.getSupportedTiers()
                java.util.Set r4 = r0.getOnDemandTiers(r4)
                java.util.Set r5 = r6.getContentTiers()
                java.util.Set r0 = r0.toSubscriptionType(r5)
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
                if (r0 != 0) goto L60
                java.util.Set r0 = r7.getSupportedTiers()
                r6.setContentTiers(r0)
                r2 = r1
            L60:
                java.util.Set r0 = r6.getOnDemandTiers()
                r3 = 0
                r5 = 1
                if (r0 == 0) goto L71
                boolean r0 = r0.isEmpty()
                if (r0 == 0) goto L6f
                goto L71
            L6f:
                r0 = r3
                goto L72
            L71:
                r0 = r5
            L72:
                if (r0 == 0) goto L7f
                if (r4 == 0) goto L7c
                boolean r0 = r4.isEmpty()
                if (r0 == 0) goto L7d
            L7c:
                r3 = r5
            L7d:
                if (r3 != 0) goto L8a
            L7f:
                java.util.Set r0 = r6.getOnDemandTiers()
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r4)
                if (r0 != 0) goto L8a
                goto L8b
            L8a:
                r1 = r2
            L8b:
                r2 = r1
            L8c:
                java.lang.Boolean r0 = r7.getIsDownloaded()
                if (r0 != 0) goto L93
                goto Lb4
            L93:
                r0.booleanValue()
                java.lang.Boolean r0 = r7.getIsDownloaded()
                java.lang.Boolean r1 = r6.isDownloaded()
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 != 0) goto Lb4
                java.lang.Boolean r7 = r7.getIsDownloaded()
                r6.setDownloaded(r7)
                if (r2 != 0) goto Lb4
                r6 = 12
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                r2 = r6
            Lb4:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.views.library.metadata.EligibilityMetadata.DefaultImpls.updateEligibilityData(com.amazon.music.views.library.metadata.EligibilityMetadata, com.amazon.music.views.library.models.base.ContentEligibilityData):java.lang.Integer");
        }
    }
}
