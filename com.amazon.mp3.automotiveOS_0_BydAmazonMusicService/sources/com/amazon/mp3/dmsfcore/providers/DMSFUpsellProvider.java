package com.amazon.mp3.dmsfcore.providers;

import ExternalActionInterface.v1_0.PlayableContent;
import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.explore.MusicEntityConverters;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.music.skyfire.ui.providers.UpsellProvider;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DMSFUpsellProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFUpsellProvider;", "Lcom/amazon/music/skyfire/ui/providers/UpsellProvider;", "()V", "showUpsell", "", "context", "Landroid/content/Context;", "playableContent", "LExternalActionInterface/v1_0/PlayableContent;", "name", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DMSFUpsellProvider implements UpsellProvider {
    @Override // com.amazon.music.skyfire.ui.providers.UpsellProvider
    public void showUpsell(Context context, PlayableContent playableContent, String name) {
        ContentAccessUtil.ContentAccessOperation contentAccessOperation;
        Intrinsics.checkNotNullParameter(playableContent, "playableContent");
        Intrinsics.checkNotNullParameter(name, "name");
        if (context == null) {
            return;
        }
        if (Intrinsics.areEqual(name, Contexts.Upsell.add)) {
            contentAccessOperation = ContentAccessUtil.ContentAccessOperation.INTERACT;
        } else {
            contentAccessOperation = Intrinsics.areEqual(name, Contexts.Upsell.playback) ? ContentAccessUtil.ContentAccessOperation.STREAM : ContentAccessUtil.ContentAccessOperation.BROWSE;
        }
        ContentAccessUtil.canContinueCatalogAction((FragmentActivity) context, MusicEntityConverters.INSTANCE.convertToMusicEntity(playableContent), ContentUnavailableReason.CUSTOMER_HAS_NO_HAWKFIRE_SUBSCRIPTION, contentAccessOperation);
    }
}
