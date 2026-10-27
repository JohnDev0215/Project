package com.amazon.podcast;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PodcastEndpointOverrideFactory.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\u0018\u0000 \b2\u00020\u0001:\u0002\b\tB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0011\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/podcast/PodcastEndpointOverrideFactory;", "", "()V", "getCurrentOverrideId", "", "overrideEntries", "", "()[Ljava/lang/String;", "Companion", "STAGE", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PodcastEndpointOverrideFactory {
    private static final String DEFAULT_OVERRIDE_ID = STAGE.PROD.name();

    /* JADX INFO: compiled from: PodcastEndpointOverrideFactory.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/podcast/PodcastEndpointOverrideFactory$STAGE;", "", "(Ljava/lang/String;I)V", "PROD", "GAMMA", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum STAGE {
        PROD,
        GAMMA
    }

    public final String getCurrentOverrideId() {
        String environment = SettingsUtil.EnvironmentPref.getEnvironment(AmazonApplication.getContext(), R.string.setting_key_podcast_endpoint_override, DEFAULT_OVERRIDE_ID);
        Intrinsics.checkNotNullExpressionValue(environment, "getEnvironment(\n        …     DEFAULT_OVERRIDE_ID)");
        return environment;
    }

    public final String[] overrideEntries() {
        STAGE[] stageArrValues = STAGE.values();
        ArrayList arrayList = new ArrayList(stageArrValues.length);
        int length = stageArrValues.length;
        int i = 0;
        while (i < length) {
            STAGE stage = stageArrValues[i];
            i++;
            arrayList.add(stage.name());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }
}
