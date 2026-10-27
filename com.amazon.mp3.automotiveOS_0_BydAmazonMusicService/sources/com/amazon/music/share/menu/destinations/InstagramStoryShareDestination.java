package com.amazon.music.share.menu.destinations;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.share.R$drawable;
import com.amazon.music.share.R$string;
import com.amazon.music.share.featuregaing.ShareFeatureGating;
import com.amazon.music.share.menu.KtExtensionsKt;
import com.amazon.music.share.menu.ShareableContent;
import com.amazon.music.share.menu.StoryAssetGenerator;
import com.amazon.music.share.menu.StoryAssets;
import com.amazon.music.share.menu.providers.EnvironmentProvider;
import com.amazon.music.share.menu.providers.SocialMetadataProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: InstagramStoryShareDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 22\u00020\u00012\u00020\u0002:\u00012B'\u0012\u0006\u0010/\u001a\u00020.\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b0\u00101J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0014\u0010\f\u001a\u00020\u00072\n\u0010\u000b\u001a\u00060\tj\u0002`\nH\u0002J\b\u0010\r\u001a\u00020\u0007H\u0002J\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0003H\u0016R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0018\u0010\"\u001a\u0004\u0018\u00010!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010'\u001a\u00020$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0014\u0010+\u001a\u00020(8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010-\u001a\u00020(8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010*¨\u00063"}, d2 = {"Lcom/amazon/music/share/menu/destinations/InstagramStoryShareDestination;", "Lcom/amazon/music/share/menu/destinations/ShareDestination;", "", "Lcom/amazon/music/share/menu/ShareableContent;", "content", "Lcom/amazon/music/share/menu/StoryAssets;", "assets", "", "completeSharing", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "failureCallback", "dispose", "Landroid/content/Intent;", "createShareIntent", "", "isAvailable", "share", "Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "socialMetadataProvider", "Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "environmentProvider", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lorg/slf4j/Logger;", "logger", "Lorg/slf4j/Logger;", "generatorLock", "Ljava/lang/Object;", "Lcom/amazon/music/share/menu/StoryAssetGenerator;", "generator", "Lcom/amazon/music/share/menu/StoryAssetGenerator;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getActionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "", "getIconResId", "()I", "iconResId", "getNameResId", "nameResId", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;Lcom/amazon/music/share/menu/providers/EnvironmentProvider;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "Companion", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final class InstagramStoryShareDestination extends ShareDestination {
    private final EnvironmentProvider environmentProvider;
    private StoryAssetGenerator generator;
    private final Object generatorLock;
    private final Logger logger;
    private final SocialMetadataProvider socialMetadataProvider;
    private final StyleSheet styleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InstagramStoryShareDestination(Context context, SocialMetadataProvider socialMetadataProvider, EnvironmentProvider environmentProvider, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(socialMetadataProvider, "socialMetadataProvider");
        Intrinsics.checkNotNullParameter(environmentProvider, "environmentProvider");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.socialMetadataProvider = socialMetadataProvider;
        this.environmentProvider = environmentProvider;
        this.styleSheet = styleSheet;
        Logger logger = LoggerFactory.getLogger(InstagramStoryShareDestination.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(javaClass.simpleName)");
        this.logger = logger;
        this.generatorLock = new Object();
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public ActionType getActionType() {
        return ActionType.SHARE_ON_INSTAGRAM_STORIES;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public int getIconResId() {
        return R$drawable.ic_share_instagram;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public int getNameResId() {
        if (ShareFeatureGating.IS_CUSTOM_SHARE_SHEET_V2_ENABLED.isEnabled()) {
            return R$string.stories;
        }
        return R$string.dmusic_sharing_destination_instagram_stories;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public boolean isAvailable() {
        return isPackageAvailable("com.instagram.android");
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public boolean share(ShareableContent content) {
        StoryAssetGenerator storyAssetGenerator;
        Intrinsics.checkNotNullParameter(content, "content");
        if (KtExtensionsKt.urlWithRefMarker(content) == null) {
            return false;
        }
        synchronized (this.generatorLock) {
            storyAssetGenerator = new StoryAssetGenerator(getContext(), this.styleSheet, this.environmentProvider, new InstagramStoryShareDestination$share$1$1(this), new InstagramStoryShareDestination$share$1$2(this), content);
            this.generator = storyAssetGenerator;
            Unit unit = Unit.INSTANCE;
        }
        storyAssetGenerator.generateAssets();
        emitMetrics(content);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void completeSharing(ShareableContent content, StoryAssets assets) {
        dispose();
        getContext().grantUriPermission("com.instagram.android", assets.getStickerUri(), 1);
        getContext().grantUriPermission("com.instagram.android", assets.getBackgroundUri(), 1);
        getContext().startActivity(createShareIntent(content, assets));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void failureCallback(Exception exception) {
        dispose();
        this.logger.error("Error while attempting to share", (Throwable) exception);
        Toast.makeText(getContext(), R$string.dmusic_sharing_error_sharing, 1).show();
    }

    private final void dispose() {
        synchronized (this.generatorLock) {
            StoryAssetGenerator storyAssetGenerator = this.generator;
            if (storyAssetGenerator != null) {
                storyAssetGenerator.cancel();
            }
            this.generator = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    private final Intent createShareIntent(ShareableContent content, StoryAssets assets) {
        Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
        intent.setDataAndType(assets.getBackgroundUri(), assets.getMimeType());
        intent.putExtra("source_application", this.socialMetadataProvider.getInstagramAppId());
        intent.putExtra("interactive_asset_uri", assets.getStickerUri());
        intent.putExtra("content_url", KtExtensionsKt.urlWithRefMarker(content));
        return intent;
    }
}
