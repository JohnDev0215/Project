package com.amazon.mp3.curate.provider;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.curate.provider.EndpointSettingsProvider;
import com.amazon.music.curate.skyfire.CurateEndpoints;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CurateEndpointSettingsProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0011\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\t¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateEndpointSettingsProvider;", "Lcom/amazon/music/curate/provider/EndpointSettingsProvider;", "()V", "getCurrentOverrideId", "", "getMarketPlaceId", "context", "Landroid/content/Context;", "overrideEntries", "", "()[Ljava/lang/String;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CurateEndpointSettingsProvider implements EndpointSettingsProvider {
    private static final String DEFAULT_OVERRIDE_ID = CurateEndpoints.Stage.PROD.name();

    @Override // com.amazon.music.curate.provider.EndpointSettingsProvider
    public String getCurrentOverrideId() {
        Context context = AmazonApplication.getContext();
        if (SettingsUtil.isCustomEndpointEnabled(context)) {
            String environment = SettingsUtil.EnvironmentPref.getEnvironment(context, R.string.setting_key_dev_curate_service_endpoint_override, DEFAULT_OVERRIDE_ID);
            Intrinsics.checkNotNullExpressionValue(environment, "getEnvironment(\n        …     DEFAULT_OVERRIDE_ID)");
            return environment;
        }
        return DEFAULT_OVERRIDE_ID;
    }

    @Override // com.amazon.music.curate.provider.EndpointSettingsProvider
    public String getMarketPlaceId(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String homeMarketPlaceId = AccountDetailUtil.get(context).getHomeMarketPlaceId();
        Intrinsics.checkNotNullExpressionValue(homeMarketPlaceId, "get(context).getHomeMarketPlaceId()");
        return homeMarketPlaceId;
    }

    public final String[] overrideEntries() {
        CurateEndpoints.Stage[] stageArrValues = CurateEndpoints.Stage.values();
        ArrayList arrayList = new ArrayList(stageArrValues.length);
        int length = stageArrValues.length;
        int i = 0;
        while (i < length) {
            CurateEndpoints.Stage stage = stageArrValues[i];
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
