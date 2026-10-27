package com.amazon.music.share;

import android.content.Context;
import android.net.Uri;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ShareProvider;

/* JADX INFO: loaded from: classes4.dex */
public class InviteShareProvider extends ShareProvider {
    private final String mInviteMessage;
    private final String mInviteUrl;
    private final String mTitle;

    public InviteShareProvider(Context context, String str, String str2, String str3, String str4, String str5) {
        super(context, str, R$string.dmusic_context_share_invite, str5, null);
        this.mTitle = str2;
        this.mInviteMessage = str3;
        this.mInviteUrl = str4;
        this.mCustomShareSupported = true;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected Uri getLink() {
        return Uri.parse(this.mInviteUrl);
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getMessage() {
        return this.mInviteMessage;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubject() {
        return this.mInviteMessage;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected EntityIdType getEntityIdType() {
        return EntityIdType.URL;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityId() {
        return this.mInviteUrl;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected ShareProvider.ShareProviderType getShareProviderType() {
        return ShareProvider.ShareProviderType.INVITE;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getSubtitle() {
        return this.mTitle;
    }

    @Override // com.amazon.music.share.ShareProvider
    protected String getEntityTypeString() {
        return getString(R$string.dmusic_context_share_content_type_invite);
    }
}
