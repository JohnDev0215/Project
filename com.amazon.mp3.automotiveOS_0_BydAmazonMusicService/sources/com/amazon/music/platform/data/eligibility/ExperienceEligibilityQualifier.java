package com.amazon.music.platform.data.eligibility;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ExperienceEligibilityQualifier.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\tB\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0001\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ExperienceEligibilityQualifier;", "", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "IsInterludeSupportRequired", "Lcom/amazon/music/platform/data/eligibility/ExperienceEligibilityQualifier$IsInterludeSupportRequired;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public abstract class ExperienceEligibilityQualifier {
    private final String name;

    public /* synthetic */ ExperienceEligibilityQualifier(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    private ExperienceEligibilityQualifier(String str) {
        this.name = str;
    }

    /* JADX INFO: compiled from: ExperienceEligibilityQualifier.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/data/eligibility/ExperienceEligibilityQualifier$IsInterludeSupportRequired;", "Lcom/amazon/music/platform/data/eligibility/ExperienceEligibilityQualifier;", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class IsInterludeSupportRequired extends ExperienceEligibilityQualifier {
        public static final IsInterludeSupportRequired INSTANCE = new IsInterludeSupportRequired();

        private IsInterludeSupportRequired() {
            super("isInterludeSupportRequired", null);
        }
    }
}
