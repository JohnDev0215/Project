package com.amazon.mp3.explore.providers;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.skyfire.ui.ktx.SharedPreferencesKt;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import com.amazon.music.skyfire.ui.skyfire.ServiceStage;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: ExploreSkyFireConfigurationProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0016R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u000f\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0013\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/explore/providers/ExploreSkyFireConfigurationProvider;", "Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;", "", "getRegionSpecificEndpoint", ImagesContract.URL, "", "setServiceURL", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Landroid/content/SharedPreferences;", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "sharedPreferences", "Lcom/amazon/music/skyfire/ui/skyfire/ServiceStage;", "getServiceStage", "()Lcom/amazon/music/skyfire/ui/skyfire/ServiceStage;", "serviceStage", "", "isStageOverridden", "()Z", "getServiceURL", "()Ljava/lang/String;", "serviceURL", "<init>", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ExploreSkyFireConfigurationProvider implements SkyFireConfigurationProvider {
    private static final Map<ServiceStage, String> ENDPOINT_MAP = MapsKt__MapsKt.mapOf(TuplesKt.to(ServiceStage.LOCAL, "http://10.0.2.2:8080/api"), TuplesKt.to(ServiceStage.DEV, "https://dev-%s.mobilemesk.skill.music.a2z.com/api"), TuplesKt.to(ServiceStage.QA, "https://qa-%s.mobilemesk.skill.music.a2z.com/api"), TuplesKt.to(ServiceStage.PROD, "https://%s.mobilemesk.skill.music.a2z.com/api"));
    private final Context context;

    public ExploreSkyFireConfigurationProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider
    public ServiceStage getServiceStage() {
        if (isStageOverridden()) {
            return ServiceStage.valueOf(Environment.EXPLORE_SERVICE.getCurrentOverrideId());
        }
        return ServiceStage.PROD;
    }

    @Override // com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider
    public boolean isStageOverridden() {
        return SettingsUtil.isCustomEndpointEnabled(this.context);
    }

    @Override // com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider
    public String getServiceURL() {
        String string;
        if (isStageOverridden()) {
            string = getRegionSpecificEndpoint();
        } else {
            string = getSharedPreferences().getString("HTTP_SKILL_URL", null);
        }
        return string == null ? getRegionSpecificEndpoint() : string;
    }

    @Override // com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider
    public void setServiceURL(final String url) {
        SharedPreferencesKt.edit(getSharedPreferences(), new Function1<SharedPreferences.Editor, Unit>() { // from class: com.amazon.mp3.explore.providers.ExploreSkyFireConfigurationProvider.setServiceURL.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SharedPreferences.Editor editor) {
                invoke2(editor);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SharedPreferences.Editor edit) {
                Intrinsics.checkNotNullParameter(edit, "$this$edit");
                edit.putString("HTTP_SKILL_URL", url);
            }
        });
    }

    private final SharedPreferences getSharedPreferences() {
        SharedPreferences prefs = SettingsUtil.getPrefs(this.context);
        Intrinsics.checkNotNullExpressionValue(prefs, "getPrefs(context)");
        return prefs;
    }

    private final String getRegionSpecificEndpoint() {
        Marketplace homeMarketPlace;
        Marketplace marketplace = Marketplace.USA;
        AccountDetailUtil accountDetailUtil = AccountDetailUtil.get(this.context);
        if (accountDetailUtil == null || (homeMarketPlace = accountDetailUtil.getHomeMarketPlace()) == null) {
            homeMarketPlace = marketplace;
        }
        String str = ENDPOINT_MAP.get(getServiceStage());
        if (str == null) {
            str = "https://%s.mobilemesk.skill.music.a2z.com/api";
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = new Object[1];
        String region = homeMarketPlace.getRegion();
        if (region == null) {
            region = marketplace.getRegion();
        }
        objArr[0] = region;
        String str2 = String.format(str, Arrays.copyOf(objArr, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
        return str2;
    }
}
