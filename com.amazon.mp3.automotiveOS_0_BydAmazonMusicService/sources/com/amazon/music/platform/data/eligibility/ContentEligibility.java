package com.amazon.music.platform.data.eligibility;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: ContentEligibility.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\t\b\u0086\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u001d\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002J\t\u0010\t\u001a\u00020\bHÖ\u0001J\t\u0010\u000b\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\fHÖ\u0003R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\"\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006 "}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ContentEligibility;", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "Lcom/amazon/music/platform/data/eligibility/ContentEligibilityQualifier;", "eligibilityQualifier", "getEligibilityQualifier", "qualifier", "", "has", "", "toString", "", "hashCode", "", "other", "equals", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "contentEligibilityIdentifier", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getContentEligibilityIdentifier", "()Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "", "qualifiers", "Ljava/util/Collection;", "", "_eligibilityQualifiers", "Ljava/util/Map;", "getTYPE", "()Ljava/lang/String;", "TYPE", "<init>", "(Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;Ljava/util/Collection;)V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ContentEligibility implements Eligibility {
    private final Map<String, ContentEligibilityQualifier> _eligibilityQualifiers;
    private final EntityItemIdentifier contentEligibilityIdentifier;
    private final Collection<ContentEligibilityQualifier> qualifiers;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContentEligibility)) {
            return false;
        }
        ContentEligibility contentEligibility = (ContentEligibility) other;
        return Intrinsics.areEqual(this.contentEligibilityIdentifier, contentEligibility.contentEligibilityIdentifier) && Intrinsics.areEqual(this.qualifiers, contentEligibility.qualifiers);
    }

    public String getTYPE() {
        return "CONTENT";
    }

    public int hashCode() {
        return (this.contentEligibilityIdentifier.hashCode() * 31) + this.qualifiers.hashCode();
    }

    public String toString() {
        return "ContentEligibility(contentEligibilityIdentifier=" + this.contentEligibilityIdentifier + ", qualifiers=" + this.qualifiers + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ContentEligibility(EntityItemIdentifier contentEligibilityIdentifier, Collection<? extends ContentEligibilityQualifier> qualifiers) {
        Intrinsics.checkNotNullParameter(contentEligibilityIdentifier, "contentEligibilityIdentifier");
        Intrinsics.checkNotNullParameter(qualifiers, "qualifiers");
        this.contentEligibilityIdentifier = contentEligibilityIdentifier;
        this.qualifiers = qualifiers;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(qualifiers, 10)), 16));
        for (Object obj : qualifiers) {
            linkedHashMap.put(((ContentEligibilityQualifier) obj).getName(), obj);
        }
        this._eligibilityQualifiers = linkedHashMap;
        PlatformProviderUtilKt.freeze(this);
    }

    public final EntityItemIdentifier getContentEligibilityIdentifier() {
        return this.contentEligibilityIdentifier;
    }

    public final ContentEligibilityQualifier getEligibilityQualifier(ContentEligibilityQualifier eligibilityQualifier) {
        Intrinsics.checkNotNullParameter(eligibilityQualifier, "eligibilityQualifier");
        return this._eligibilityQualifiers.get(eligibilityQualifier.getName());
    }

    public final boolean has(ContentEligibilityQualifier qualifier) {
        Intrinsics.checkNotNullParameter(qualifier, "qualifier");
        return getEligibilityQualifier(qualifier) != null;
    }
}
