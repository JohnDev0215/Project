package com.amazon.music.identity.profiles.datasource.mockprofile;

import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u0018\u0012\u0006\u0010\u001b\u001a\u00020\u0018\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\t\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u001c\u0010\u001dR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\n\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\"\u0010\u0012\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/identity/profiles/datasource/mockprofile/ProfileModel;", "Lcom/amazon/music/identity/profiles/datasource/mockprofile/ProfileBaseModel;", "", "profileId", "I", "getProfileId", "()I", "setProfileId", "(I)V", "", "isActivated", "Z", "()Z", "setActivated", "(Z)V", "isAvailable", "setAvailable", "Ljava/util/Date;", "enrollmentDate", "Ljava/util/Date;", "getEnrollmentDate", "()Ljava/util/Date;", "setEnrollmentDate", "(Ljava/util/Date;)V", "", "fullName", "handle", "avatarUrl", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ZZLjava/util/Date;)V", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0})
public final class ProfileModel extends ProfileBaseModel {
    private Date enrollmentDate;
    private boolean isActivated;
    private boolean isAvailable;
    private int profileId;

    public final int getProfileId() {
        return this.profileId;
    }

    public final void setProfileId(int i) {
        this.profileId = i;
    }

    /* JADX INFO: renamed from: isActivated, reason: from getter */
    public final boolean getIsActivated() {
        return this.isActivated;
    }

    /* JADX INFO: renamed from: isAvailable, reason: from getter */
    public final boolean getIsAvailable() {
        return this.isAvailable;
    }

    public /* synthetic */ ProfileModel(String str, int i, String str2, String str3, boolean z, boolean z2, Date date, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, str3, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? true : z2, (i2 & 64) != 0 ? new Date() : date);
    }

    public final Date getEnrollmentDate() {
        return this.enrollmentDate;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileModel(String fullName, int i, String handle, String avatarUrl, boolean z, boolean z2, Date enrollmentDate) {
        super(fullName, handle, avatarUrl, false, null, 24, null);
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(handle, "handle");
        Intrinsics.checkNotNullParameter(avatarUrl, "avatarUrl");
        Intrinsics.checkNotNullParameter(enrollmentDate, "enrollmentDate");
        this.profileId = i;
        this.isActivated = z;
        this.isAvailable = z2;
        this.enrollmentDate = enrollmentDate;
    }
}
