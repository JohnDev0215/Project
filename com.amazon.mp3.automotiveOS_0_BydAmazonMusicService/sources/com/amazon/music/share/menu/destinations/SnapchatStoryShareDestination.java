package com.amazon.music.share.menu.destinations;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.share.R$drawable;
import com.amazon.music.share.R$string;
import com.amazon.music.share.featuregaing.ShareFeatureGating;
import com.amazon.music.share.menu.KtExtensionsKt;
import com.amazon.music.share.menu.ShareableContent;
import com.amazon.music.share.menu.StoryAssetGenerator;
import com.amazon.music.share.menu.StoryAssets;
import com.amazon.music.share.menu.providers.EnvironmentProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.visualon.OSMPUtils.voOSType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SnapchatStoryShareDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 12\u00020\u00012\u00020\u0002:\u00011B\u001f\u0012\u0006\u0010.\u001a\u00020-\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b/\u00100J\u0014\u0010\u0007\u001a\u00020\u00062\n\u0010\u0005\u001a\u00060\u0003j\u0002`\u0004H\u0002J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0003J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0016R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0018\u0010\"\u001a\u0004\u0018\u00010!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010'\u001a\u00020$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0014\u0010*\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0014\u0010,\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010)¨\u00062"}, d2 = {"Lcom/amazon/music/share/menu/destinations/SnapchatStoryShareDestination;", "Lcom/amazon/music/share/menu/destinations/ShareDestination;", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "", "failureCallback", "Lcom/amazon/music/share/menu/ShareableContent;", "content", "Lcom/amazon/music/share/menu/StoryAssets;", "assets", "completeSharing", "Landroid/net/Uri;", "stickerUri", "Lorg/json/JSONObject;", "getJsonStickerData", "Landroid/content/Intent;", "baseIntent", "", "apiLess31", "apiPlus31", "", "share", "isAvailable", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "environmentProvider", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "generatorLock", "Ljava/lang/Object;", "Lcom/amazon/music/share/menu/StoryAssetGenerator;", "generator", "Lcom/amazon/music/share/menu/StoryAssetGenerator;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getActionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "getNameResId", "()I", "nameResId", "getIconResId", "iconResId", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/share/menu/providers/EnvironmentProvider;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "Companion", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final class SnapchatStoryShareDestination extends ShareDestination {
    private static final String TAG = SnapchatStoryShareDestination.class.getSimpleName();
    private final EnvironmentProvider environmentProvider;
    private StoryAssetGenerator generator;
    private final Object generatorLock;
    private final StyleSheet styleSheet;

    private final int apiLess31() {
        return 1073741824;
    }

    @TargetApi(31)
    private final int apiPlus31() {
        return voOSType.VOOSMP_SRC_FFAUDIO_WMA;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnapchatStoryShareDestination(Context context, EnvironmentProvider environmentProvider, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(environmentProvider, "environmentProvider");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.environmentProvider = environmentProvider;
        this.styleSheet = styleSheet;
        this.generatorLock = new Object();
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public ActionType getActionType() {
        return ActionType.SHARE_ON_SNAPCHAT_STORIES;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public int getNameResId() {
        if (ShareFeatureGating.IS_CUSTOM_SHARE_SHEET_V2_ENABLED.isEnabled()) {
            return R$string.stories;
        }
        return R$string.dmusic_sharing_destination_snapchat_stories;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public int getIconResId() {
        return R$drawable.ic_share_snap;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public boolean share(ShareableContent content) {
        StoryAssetGenerator storyAssetGenerator;
        Intrinsics.checkNotNullParameter(content, "content");
        if (KtExtensionsKt.urlWithRefMarker(content) == null) {
            return false;
        }
        synchronized (this.generatorLock) {
            StoryAssetGenerator storyAssetGenerator2 = this.generator;
            if (storyAssetGenerator2 != null) {
                storyAssetGenerator2.cancel();
            }
            storyAssetGenerator = new StoryAssetGenerator(getContext(), this.styleSheet, this.environmentProvider, new SnapchatStoryShareDestination$share$1$1(this), new SnapchatStoryShareDestination$share$1$2(this), content);
            this.generator = storyAssetGenerator;
            Unit unit = Unit.INSTANCE;
        }
        storyAssetGenerator.generateAssets();
        emitMetrics(content);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void failureCallback(Exception exception) {
        Toast.makeText(getContext(), R$string.dmusic_sharing_error_sharing, 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void completeSharing(ShareableContent content, StoryAssets assets) {
        Intent intentBaseIntent = baseIntent();
        intentBaseIntent.setDataAndType(Uri.parse("snapchat://creativekit/preview"), "image/*");
        intentBaseIntent.putExtra("android.intent.extra.STREAM", assets.getBackgroundUri());
        intentBaseIntent.putExtra("sticker", getJsonStickerData(assets.getStickerUri()).toString());
        getContext().grantUriPermission("com.snapchat.android", assets.getStickerUri(), 1);
        intentBaseIntent.putExtra("attachmentUrl", KtExtensionsKt.urlWithRefMarker(content));
        ContextCompat.startActivity(getContext(), intentBaseIntent, null);
    }

    private final JSONObject getJsonStickerData(Uri stickerUri) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uri", stickerUri);
            jSONObject.put("widthDp", 300);
            jSONObject.put("heightDp", 300);
        } catch (JSONException e) {
            Log.w(TAG, "Unable to create sticker json", e);
        }
        return jSONObject;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public boolean isAvailable() {
        return isPackageAvailable("com.snapchat.android");
    }

    private final Intent baseIntent() {
        int iApiLess31;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setPackage("com.snapchat.android");
        intent.putExtra("CLIENT_ID", "185cff52-b123-42ed-9dbe-b4badb220744");
        intent.setFlags(335544320);
        Context context = getContext();
        Intent intent2 = new Intent();
        if (Build.VERSION.SDK_INT >= 31) {
            iApiLess31 = apiPlus31();
        } else {
            iApiLess31 = apiLess31();
        }
        intent.putExtra("RESULT_INTENT", PendingIntent.getActivity(context, 1234, intent2, iApiLess31));
        return intent;
    }
}
