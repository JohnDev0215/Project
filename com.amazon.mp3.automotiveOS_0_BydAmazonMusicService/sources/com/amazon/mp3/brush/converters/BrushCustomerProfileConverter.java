package com.amazon.mp3.brush.converters;

import android.content.Context;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.customerprofile.CustomerProfileDetailManager;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.CustomerProfileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.CustomerProfile;
import com.amazon.musicensembleservice.brush.CustomerProfileStats;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Page;
import com.amazon.musicrelationshipservice.model.Edge;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushCustomerProfileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\tR\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00108\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0013\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushCustomerProfileConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/CustomerProfile;", "item", "Lcom/amazon/music/views/library/models/CustomerProfileModel;", "convertCustomerProfile", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "Lcom/amazon/musicensembleservice/brush/Page;", "convertCustomerProfileFromPage", "Landroid/content/Context;", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "", "PUBLIC_STATUS", "Ljava/lang/String;", "FOLLOW_STATUS", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushCustomerProfileConverter implements SingleBaseModelConverter<CustomerProfile> {
    private final String FOLLOW_STATUS;
    private final String PUBLIC_STATUS;
    private final Context mContext;

    public BrushCustomerProfileConverter(Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
        this.PUBLIC_STATUS = "PUBLIC";
        this.FOLLOW_STATUS = "FOLLOWS";
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(CustomerProfile data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return convertCustomerProfile(data);
    }

    private final CustomerProfileModel convertCustomerProfile(CustomerProfile item) {
        if (item.getProfileDetails() == null || item.getProfileDetails().getProfileId() == null || item.getProfileDetails().getAvatarLink() == null || item.getProfileDetails().getProfileStatus() == null) {
            return null;
        }
        String blockRef = item.getBlockRef();
        String publicName = item.getProfileDetails().getPublicName();
        if (publicName == null) {
            publicName = AccountCredentialStorage.get(this.mContext).getUsername();
        }
        String str = publicName;
        String profileStatus = item.getProfileDetails().getProfileStatus();
        CustomerProfileStats customerProfileStats = item.getCustomerProfileStats();
        Long followingCount = customerProfileStats == null ? null : customerProfileStats.getFollowingCount();
        CustomerProfileStats customerProfileStats2 = item.getCustomerProfileStats();
        Long followerCount = customerProfileStats2 == null ? null : customerProfileStats2.getFollowerCount();
        CustomerProfileStats customerProfileStats3 = item.getCustomerProfileStats();
        Long publicPlaylistCount = customerProfileStats3 == null ? null : customerProfileStats3.getPublicPlaylistCount();
        boolean z = !Intrinsics.areEqual(item.getProfileDetails().getProfileStatus(), this.PUBLIC_STATUS) && item.getProfileDetails().getCustomerId() == null;
        String avatarLink = item.getProfileDetails().getAvatarLink();
        boolean z2 = item.getProfileDetails().getCustomerId() != null;
        String profileId = item.getProfileDetails().getProfileId();
        String customerId = item.getProfileDetails().getCustomerId() == null ? "" : item.getProfileDetails().getCustomerId();
        Edge relationship = item.getRelationship();
        boolean zAreEqual = Intrinsics.areEqual(relationship != null ? relationship.getType() : null, this.FOLLOW_STATUS);
        String shareListeningHistory = item.getProfileDetails().getShareListeningHistory() == null ? "" : item.getProfileDetails().getShareListeningHistory();
        Intrinsics.checkNotNullExpressionValue(str, "(item.profileDetails.pub…t(mContext).getUsername()");
        Intrinsics.checkNotNullExpressionValue(profileStatus, "profileStatus");
        Intrinsics.checkNotNullExpressionValue(avatarLink, "avatarLink");
        Intrinsics.checkNotNullExpressionValue(profileId, "profileId");
        Intrinsics.checkNotNullExpressionValue(customerId, "if (item.profileDetails.…profileDetails.customerId");
        Intrinsics.checkNotNullExpressionValue(shareListeningHistory, "if (item.profileDetails.…ils.shareListeningHistory");
        CustomerProfileModel customerProfileModel = new CustomerProfileModel(blockRef, "", "", "", str, null, profileStatus, followingCount, followerCount, publicPlaylistCount, z, avatarLink, 0, z2, profileId, customerId, zAreEqual ? 1 : 0, shareListeningHistory, null, 266272, null);
        if (!customerProfileModel.getIsSelfView()) {
            return customerProfileModel;
        }
        CustomerProfileDetailManager.getInstance().setCustomerProfileModel(customerProfileModel);
        return customerProfileModel;
    }

    public final CustomerProfileModel convertCustomerProfileFromPage(Page data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Entity entity = data.getEntity();
        if (entity == null) {
            return null;
        }
        Intrinsics.checkNotNullExpressionValue(entity, "entity");
        if (entity instanceof CustomerProfile) {
            return convertCustomerProfile((CustomerProfile) entity);
        }
        return null;
    }
}
