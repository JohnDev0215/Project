package com.amazon.music.share;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ShareProvider;
import com.amazon.music.share.featuregaing.ShareFeatureGating;

/* JADX INFO: loaded from: classes4.dex */
public class ProfileShareProvider extends ShareProvider {
    private final String mProfileImage;
    private final String mProfileKey;
    private final String mProfileName;
    private final String mProfileUrl;

    public ProfileShareProvider(Context context, String str, String str2, String str3, String str4, String str5) {
        super(context, str, R$string.dmusic_context_share_profile);
        this.mProfileKey = str2;
        this.mProfileName = str3;
        this.mProfileImage = str4;
        this.mProfileUrl = str5;
        this.mCustomShareSupported = ShareFeatureGating.PROFILE_SHARING.isEnabled();
    }

    @Override // com.amazon.music.share.ShareProvider
    protected Uri getLink() {
        return appendRefMarker(!TextUtils.isEmpty(this.mProfileUrl) ? Uri.parse(this.mProfileUrl) : getBaseUrl().buildUpon().appendPath("profiles").appendPath(this.mProfileKey).build());
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getMessage() {
        return getLink().toString();
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubject() {
        return this.mProfileName;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getTitle() {
        return this.mProfileName;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getImageUrl() {
        return this.mProfileImage;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected EntityIdType getEntityIdType() {
        return EntityIdType.ASIN;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityId() {
        return this.mProfileKey;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected ShareProvider.ShareProviderType getShareProviderType() {
        return ShareProvider.ShareProviderType.CUSTOMER_PROFILE;
    }
}
