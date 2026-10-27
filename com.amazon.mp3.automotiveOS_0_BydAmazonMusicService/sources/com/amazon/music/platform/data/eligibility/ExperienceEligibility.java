package com.amazon.music.platform.data.eligibility;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExperienceEligibility.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\t\u0010\t\u001a\u00020\bHÖ\u0001J\u0013\u0010\f\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\nHÖ\u0003R\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ExperienceEligibility;", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "Lcom/amazon/music/platform/data/eligibility/ExperienceEligibilityQualifier;", "qualifier", "", "has", "", "toString", "", "hashCode", "", "other", "equals", "", "qualifiers", "Ljava/util/Set;", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ExperienceEligibility implements Eligibility {
    private Set<? extends ExperienceEligibilityQualifier> qualifiers;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ExperienceEligibility) && Intrinsics.areEqual(this.qualifiers, ((ExperienceEligibility) other).qualifiers);
    }

    public int hashCode() {
        return this.qualifiers.hashCode();
    }

    public String toString() {
        return "ExperienceEligibility(qualifiers=" + this.qualifiers + ')';
    }

    public final boolean has(ExperienceEligibilityQualifier qualifier) {
        Intrinsics.checkNotNullParameter(qualifier, "qualifier");
        return this.qualifiers.contains(qualifier);
    }
}
