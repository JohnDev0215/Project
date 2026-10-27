package com.amazon.mp3.sharing.providers;

import android.content.Context;
import com.amazon.music.share.menu.providers.SocialMetadataProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShareSocialMetadataProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/sharing/providers/ShareSocialMetadataProvider;", "Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "facebookAppId", "", "getFacebookAppId", "()Ljava/lang/String;", "instagramAppId", "getInstagramAppId", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ShareSocialMetadataProvider implements SocialMetadataProvider {
    @Override // com.amazon.music.share.menu.providers.SocialMetadataProvider
    public String getFacebookAppId() {
        return "996123557121337";
    }

    @Override // com.amazon.music.share.menu.providers.SocialMetadataProvider
    public String getInstagramAppId() {
        return "164159468181390";
    }

    public ShareSocialMetadataProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
    }
}
