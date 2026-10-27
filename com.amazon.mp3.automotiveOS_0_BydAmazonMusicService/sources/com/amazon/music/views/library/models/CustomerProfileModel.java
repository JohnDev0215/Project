package com.amazon.music.views.library.models;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b!\u0018\u00002\u00020\u00012\u00020\u0002BÓ\u0001\u0012\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 \u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010 \u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010 \u0012\b\b\u0002\u0010.\u001a\u00020-\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010;\u001a\u00020-\u0012\b\b\u0002\u0010=\u001a\u00020\u0007\u0012\b\b\u0002\u0010@\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010E\u001a\u00020\u0007\u0012\b\b\u0002\u0010H\u001a\u00020\u0007¢\u0006\u0004\bL\u0010MJ\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003J&\u0010\f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0003R$\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R$\u0010\u0016\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u000e\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R\"\u0010\u001b\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u001c\u0010\u0010\"\u0004\b\u001d\u0010\u0012R\"\u0010\t\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u000e\u001a\u0004\b\u001e\u0010\u0010\"\u0004\b\u001f\u0010\u0012R$\u0010!\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R$\u0010'\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010\"\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&R$\u0010*\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010\"\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R\"\u0010.\u001a\u00020-8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\"\u0010\n\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000e\u001a\u0004\b4\u0010\u0010\"\u0004\b5\u0010\u0012R\"\u0010\u000b\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\"\u0010;\u001a\u00020-8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b;\u0010/\u001a\u0004\b;\u00101\"\u0004\b<\u00103R\"\u0010=\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b=\u0010\u000e\u001a\u0004\b>\u0010\u0010\"\u0004\b?\u0010\u0012R\"\u0010@\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b@\u0010\u000e\u001a\u0004\bA\u0010\u0010\"\u0004\bB\u0010\u0012R\"\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u00106\u001a\u0004\bC\u00108\"\u0004\bD\u0010:R\"\u0010E\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bE\u0010\u000e\u001a\u0004\bF\u0010\u0010\"\u0004\bG\u0010\u0012R\"\u0010H\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bH\u0010\u000e\u001a\u0004\bI\u0010\u0010\"\u0004\bJ\u0010\u0012¨\u0006N"}, d2 = {"Lcom/amazon/music/views/library/models/CustomerProfileModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Ljava/io/Serializable;", "", "followState", "", "updateFollowState", "", "profileName", "profileStatus", "profileAvatarUrl", "profileAvatarRotation", "updateProfileState", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "subTitle", "getSubTitle", "setSubTitle", ContextMappingConstants.TERTIARY_TEXT, "getTertiaryText", "setTertiaryText", "getProfileName", "setProfileName", "profileHandle", "getProfileHandle", "setProfileHandle", "getProfileStatus", "setProfileStatus", "", "followingCount", "Ljava/lang/Long;", "getFollowingCount", "()Ljava/lang/Long;", "setFollowingCount", "(Ljava/lang/Long;)V", "followerCount", "getFollowerCount", "setFollowerCount", "playlistCount", "getPlaylistCount", "setPlaylistCount", "", "hideProfileStats", "Z", "getHideProfileStats", "()Z", "setHideProfileStats", "(Z)V", "getProfileAvatarUrl", "setProfileAvatarUrl", "I", "getProfileAvatarRotation", "()I", "setProfileAvatarRotation", "(I)V", "isSelfView", "setSelfView", "profileId", "getProfileId", "setProfileId", "customerId", "getCustomerId", "setCustomerId", "getFollowState", "setFollowState", "shareListeningHistory", "getShareListeningHistory", "setShareListeningHistory", "profileUrl", "getProfileUrl", "setProfileUrl", "viewId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;ZLjava/lang/String;IZLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CustomerProfileModel extends BaseViewModel implements Serializable {
    private String customerId;
    private int followState;
    private Long followerCount;
    private Long followingCount;
    private boolean hideProfileStats;
    private boolean isSelfView;
    private Long playlistCount;
    private int profileAvatarRotation;
    private String profileAvatarUrl;
    private String profileHandle;
    private String profileId;
    private String profileName;
    private String profileStatus;
    private String profileUrl;
    private String shareListeningHistory;
    private String subTitle;
    private String tertiaryText;
    private String title;

    public CustomerProfileModel() {
        this(null, null, null, null, null, null, null, null, null, null, false, null, 0, false, null, null, 0, null, null, 524287, null);
    }

    public final String getProfileName() {
        return this.profileName;
    }

    public final void setProfileName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.profileName = str;
    }

    public final String getProfileHandle() {
        return this.profileHandle;
    }

    public final void setProfileHandle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.profileHandle = str;
    }

    public final String getProfileStatus() {
        return this.profileStatus;
    }

    public final void setProfileStatus(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.profileStatus = str;
    }

    public /* synthetic */ CustomerProfileModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, Long l, Long l2, Long l3, boolean z, String str8, int i, boolean z2, String str9, String str10, int i2, String str11, String str12, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? null : str3, (i3 & 8) == 0 ? str4 : null, (i3 & 16) != 0 ? "" : str5, (i3 & 32) != 0 ? "" : str6, (i3 & 64) != 0 ? "" : str7, (i3 & 128) != 0 ? 0L : l, (i3 & 256) != 0 ? 0L : l2, (i3 & 512) != 0 ? 0L : l3, (i3 & 1024) != 0 ? false : z, (i3 & 2048) != 0 ? "" : str8, (i3 & 4096) != 0 ? 0 : i, (i3 & 8192) != 0 ? false : z2, (i3 & 16384) != 0 ? "" : str9, (i3 & 32768) != 0 ? "" : str10, (i3 & 65536) != 0 ? 0 : i2, (i3 & 131072) != 0 ? "" : str11, (i3 & 262144) != 0 ? "" : str12);
    }

    public final Long getFollowingCount() {
        return this.followingCount;
    }

    public final Long getFollowerCount() {
        return this.followerCount;
    }

    public final void setFollowerCount(Long l) {
        this.followerCount = l;
    }

    public final boolean getHideProfileStats() {
        return this.hideProfileStats;
    }

    public final String getProfileAvatarUrl() {
        return this.profileAvatarUrl;
    }

    public final void setProfileAvatarUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.profileAvatarUrl = str;
    }

    public final int getProfileAvatarRotation() {
        return this.profileAvatarRotation;
    }

    public final void setProfileAvatarRotation(int i) {
        this.profileAvatarRotation = i;
    }

    /* JADX INFO: renamed from: isSelfView, reason: from getter */
    public final boolean getIsSelfView() {
        return this.isSelfView;
    }

    public final String getProfileId() {
        return this.profileId;
    }

    public final int getFollowState() {
        return this.followState;
    }

    public final void setFollowState(int i) {
        this.followState = i;
    }

    public final String getShareListeningHistory() {
        return this.shareListeningHistory;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomerProfileModel(String str, String str2, String str3, String str4, String profileName, String profileHandle, String profileStatus, Long l, Long l2, Long l3, boolean z, String profileAvatarUrl, int i, boolean z2, String profileId, String customerId, int i2, String shareListeningHistory, String profileUrl) {
        super(str, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(profileName, "profileName");
        Intrinsics.checkNotNullParameter(profileHandle, "profileHandle");
        Intrinsics.checkNotNullParameter(profileStatus, "profileStatus");
        Intrinsics.checkNotNullParameter(profileAvatarUrl, "profileAvatarUrl");
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(shareListeningHistory, "shareListeningHistory");
        Intrinsics.checkNotNullParameter(profileUrl, "profileUrl");
        this.title = str2;
        this.subTitle = str3;
        this.tertiaryText = str4;
        this.profileName = profileName;
        this.profileHandle = profileHandle;
        this.profileStatus = profileStatus;
        this.followingCount = l;
        this.followerCount = l2;
        this.playlistCount = l3;
        this.hideProfileStats = z;
        this.profileAvatarUrl = profileAvatarUrl;
        this.profileAvatarRotation = i;
        this.isSelfView = z2;
        this.profileId = profileId;
        this.customerId = customerId;
        this.followState = i2;
        this.shareListeningHistory = shareListeningHistory;
        this.profileUrl = profileUrl;
    }

    public final String getProfileUrl() {
        return this.profileUrl;
    }

    public final void updateFollowState(int followState) {
        this.followState = followState;
        Long l = this.followerCount;
        if (l != null) {
            long jLongValue = l.longValue();
            if (followState == 0) {
                setFollowerCount(Long.valueOf(jLongValue - 1));
            } else if (followState == 1) {
                setFollowerCount(Long.valueOf(jLongValue + 1));
            }
        }
        notifyChanged(2);
    }

    public final void updateProfileState(String profileName, String profileStatus, String profileAvatarUrl, int profileAvatarRotation) {
        Intrinsics.checkNotNullParameter(profileName, "profileName");
        Intrinsics.checkNotNullParameter(profileStatus, "profileStatus");
        Intrinsics.checkNotNullParameter(profileAvatarUrl, "profileAvatarUrl");
        this.profileName = profileName;
        this.profileStatus = profileStatus;
        this.profileAvatarUrl = profileAvatarUrl;
        this.profileAvatarRotation = profileAvatarRotation;
        notifyChanged(3);
    }
}
