package com.amazon.music.platform.data.eligibility.factory;

import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.ContentEligibilityInput;
import com.amazon.music.platform.data.eligibility.ContentEligibilityQualifier;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: ContentEligibilityFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0014\u0010\u0007\u001a\u00020\u0006*\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0013\u0010\f\u001a\u00020\u0006*\u00020\u0003H\u0000¢\u0006\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/factory/ContentEligibilityFactory;", "", "Lcom/amazon/music/platform/data/eligibility/ContentEligibility;", "Lcom/amazon/music/platform/data/eligibility/factory/EligibilityContext;", "Lcom/amazon/music/platform/data/eligibility/factory/EligibilityInput;", "eligibilityInput", "", "has", "eligibilityContext", "createFrom", "isUnavailable$MusicPlatformCore_release", "(Lcom/amazon/music/platform/data/eligibility/factory/EligibilityContext;)Z", "isUnavailable", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class ContentEligibilityFactory {
    public static final ContentEligibilityFactory INSTANCE = new ContentEligibilityFactory();

    private ContentEligibilityFactory() {
    }

    public ContentEligibility createFrom(EligibilityContext eligibilityContext) {
        Intrinsics.checkNotNullParameter(eligibilityContext, "eligibilityContext");
        EligibilityInput eligibilityInput = eligibilityContext.getInputs().get(Reflection.getOrCreateKotlinClass(EntityItemIdentifier.class));
        EntityItemIdentifier entityItemIdentifier = eligibilityInput instanceof EntityItemIdentifier ? (EntityItemIdentifier) eligibilityInput : null;
        if (entityItemIdentifier == null) {
            throw new IllegalStateException("");
        }
        ArrayList arrayList = new ArrayList();
        ContentEligibilityFactory contentEligibilityFactory = INSTANCE;
        if (!contentEligibilityFactory.has(eligibilityContext, ContentEligibilityInput.IsAvailable.INSTANCE) && contentEligibilityFactory.isUnavailable$MusicPlatformCore_release(eligibilityContext)) {
            arrayList.add(ContentEligibilityQualifier.IsUnavailable.INSTANCE);
        }
        if (contentEligibilityFactory.has(eligibilityContext, ContentEligibilityInput.IsOwned.INSTANCE)) {
            arrayList.add(ContentEligibilityQualifier.IsOwned.INSTANCE);
        }
        if (contentEligibilityFactory.has(eligibilityContext, ContentEligibilityInput.IsFree.INSTANCE) && eligibilityContext.get$MusicPlatformCore_release(Reflection.getOrCreateKotlinClass(ContentEligibilityInput.IsFreeOnDemandPlayable.class)) != null) {
            arrayList.add(ContentEligibilityQualifier.IsFreeOnDemandPlayable.INSTANCE);
        }
        if (contentEligibilityFactory.has(eligibilityContext, ContentEligibilityInput.IsSonicRush.INSTANCE) && eligibilityContext.get$MusicPlatformCore_release(Reflection.getOrCreateKotlinClass(ContentEligibilityInput.IsSonicRushOnDemandPlayable.class)) != null) {
            arrayList.add(ContentEligibilityQualifier.IsSonicRushOnDemandPlayable.INSTANCE);
        }
        if (contentEligibilityFactory.has(eligibilityContext, ContentEligibilityInput.IsDownloaded.INSTANCE)) {
            arrayList.add(ContentEligibilityQualifier.IsDownloaded.INSTANCE);
        }
        if (contentEligibilityFactory.has(eligibilityContext, ContentEligibilityInput.IsOnDemandByService.INSTANCE)) {
            arrayList.add(ContentEligibilityQualifier.IsOnDemandByService.INSTANCE);
        }
        if (contentEligibilityFactory.has(eligibilityContext, ContentEligibilityInput.IsFollowable.INSTANCE)) {
            arrayList.add(ContentEligibilityQualifier.IsFollowable.INSTANCE);
        }
        return new ContentEligibility(entityItemIdentifier, arrayList);
    }

    public final boolean isUnavailable$MusicPlatformCore_release(EligibilityContext eligibilityContext) {
        Intrinsics.checkNotNullParameter(eligibilityContext, "<this>");
        return !(has(eligibilityContext, ContentEligibilityInput.IsOwned.INSTANCE) || has(eligibilityContext, ContentEligibilityInput.IsSonicRush.INSTANCE) || has(eligibilityContext, ContentEligibilityInput.IsFree.INSTANCE) || has(eligibilityContext, ContentEligibilityInput.IsPrime.INSTANCE) || has(eligibilityContext, ContentEligibilityInput.IsMusicUnlimited.INSTANCE));
    }

    private final boolean has(EligibilityContext eligibilityContext, EligibilityInput eligibilityInput) {
        return eligibilityContext.get$MusicPlatformCore_release(Reflection.getOrCreateKotlinClass(eligibilityInput.getClass())) != null;
    }
}
