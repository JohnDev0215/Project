package com.amazon.mp3.explore.providers;

import android.content.Context;
import com.amazon.mp3.voice.wakeword.WakeWordHelper;
import com.amazon.music.explore.providers.WakeWordProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExploreWakeWordProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/explore/providers/ExploreWakeWordProvider;", "Lcom/amazon/music/explore/providers/WakeWordProvider;", "", "pauseWakeWordDetection", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ExploreWakeWordProvider implements WakeWordProvider {
    private final Context context;

    public ExploreWakeWordProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.explore.providers.WakeWordProvider
    public void pauseWakeWordDetection() {
        WakeWordHelper.stopWakeWordService(this.context);
    }
}
