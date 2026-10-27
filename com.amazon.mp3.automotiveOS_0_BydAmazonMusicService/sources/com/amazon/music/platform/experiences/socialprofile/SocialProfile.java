package com.amazon.music.platform.experiences.socialprofile;

import com.amazon.music.platform.graphql.generated.SocialProfileQuery;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SocialProfileService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\t\u0010\u000bR\u0017\u0010\f\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\f\u0010\n\u001a\u0004\b\f\u0010\u000bR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/experiences/socialprofile/SocialProfile;", "", "", "toString", "", "hashCode", "other", "", "equals", "isFollowing", "Z", "()Z", "isActiveProfile", "Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Other;", "user", "Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Other;", "getUser", "()Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Other;", "<init>", "(ZZLcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Other;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class SocialProfile {
    private final boolean isActiveProfile;
    private final boolean isFollowing;
    private final SocialProfileQuery.Other user;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SocialProfile)) {
            return false;
        }
        SocialProfile socialProfile = (SocialProfile) other;
        return this.isFollowing == socialProfile.isFollowing && this.isActiveProfile == socialProfile.isActiveProfile && Intrinsics.areEqual(this.user, socialProfile.user);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    public int hashCode() {
        boolean z = this.isFollowing;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.isActiveProfile;
        int i2 = (i + (z2 ? 1 : z2)) * 31;
        SocialProfileQuery.Other other = this.user;
        return i2 + (other == null ? 0 : other.hashCode());
    }

    public String toString() {
        return "SocialProfile(isFollowing=" + this.isFollowing + ", isActiveProfile=" + this.isActiveProfile + ", user=" + this.user + ')';
    }

    public SocialProfile(boolean z, boolean z2, SocialProfileQuery.Other other) {
        this.isFollowing = z;
        this.isActiveProfile = z2;
        this.user = other;
    }

    public final SocialProfileQuery.Other getUser() {
        return this.user;
    }

    /* JADX INFO: renamed from: isFollowing, reason: from getter */
    public final boolean getIsFollowing() {
        return this.isFollowing;
    }
}
