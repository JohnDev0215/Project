package com.amazon.mp3.explore;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.skyfire.ui.skyfire.ServiceStage;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExploreEndpointOverrideFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0011\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/explore/ExploreEndpointOverrideFactory;", "", "()V", "getCurrentOverrideId", "", "overrideEntries", "", "()[Ljava/lang/String;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExploreEndpointOverrideFactory {
    private static final String DEFAULT_OVERRIDE_ID = ServiceStage.PROD.name();

    public final String getCurrentOverrideId() {
        String environment = SettingsUtil.EnvironmentPref.getEnvironment(AmazonApplication.getContext(), R.string.setting_key_dev_explore_service_endpoint_override, DEFAULT_OVERRIDE_ID);
        Intrinsics.checkNotNullExpressionValue(environment, "getEnvironment(\n        …     DEFAULT_OVERRIDE_ID)");
        return environment;
    }

    public final String[] overrideEntries() {
        ServiceStage[] serviceStageArrValues = ServiceStage.values();
        ArrayList arrayList = new ArrayList(serviceStageArrValues.length);
        int length = serviceStageArrValues.length;
        int i = 0;
        while (i < length) {
            ServiceStage serviceStage = serviceStageArrValues[i];
            i++;
            arrayList.add(serviceStage.name());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }
}
