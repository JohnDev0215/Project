package com.amazon.mp3.voice.onboarding.provider;

import android.content.Context;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.music.platform.aom.onboarding.provider.MicPermissionProvider;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaMicPermissionProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/provider/AlexaMicPermissionProvider;", "Lcom/amazon/music/platform/aom/onboarding/provider/MicPermissionProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isMicPermissionGranted", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaMicPermissionProvider implements MicPermissionProvider {
    private final Context context;

    public AlexaMicPermissionProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.platform.aom.onboarding.provider.MicPermissionProvider
    public boolean isMicPermissionGranted() {
        Context context = this.context;
        Object[] array = CollectionsKt__CollectionsJVMKt.listOf("android.permission.RECORD_AUDIO").toArray(new String[0]);
        if (array != null) {
            return GrantPermissionActivity.areAllGranted(context, (String[]) array);
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }
}
