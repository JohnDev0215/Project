package com.amazon.mp3.sharing.dagger;

import android.content.Context;
import com.amazon.mp3.sharing.ShareHelperImpl;
import com.amazon.mp3.sharing.providers.ShareEnvironmentProvider;
import com.amazon.mp3.sharing.providers.ShareSocialMetadataProvider;
import com.amazon.mp3.sharing.providers.ShareStyleSheetProvider;
import com.amazon.music.share.ShareHelper;
import com.amazon.music.share.menu.providers.EnvironmentProvider;
import com.amazon.music.share.menu.providers.SocialMetadataProvider;
import com.amazon.music.share.menu.providers.StyleSheetProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShareModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u000b\u001a\u00020\fH\u0007¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/sharing/dagger/ShareModule;", "", "()V", "provideEnvironmentProvider", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "context", "Landroid/content/Context;", "provideShareHelper", "Lcom/amazon/music/share/ShareHelper;", "provideSocialMetadataProvider", "Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "provideStyleSheetProvider", "Lcom/amazon/music/share/menu/providers/StyleSheetProvider;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ShareModule {
    public final StyleSheetProvider provideStyleSheetProvider() {
        return new ShareStyleSheetProvider();
    }

    public final SocialMetadataProvider provideSocialMetadataProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ShareSocialMetadataProvider(context);
    }

    public final EnvironmentProvider provideEnvironmentProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ShareEnvironmentProvider(context);
    }

    public final ShareHelper provideShareHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ShareHelperImpl();
    }
}
