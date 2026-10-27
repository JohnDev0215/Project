package com.amazon.mp3.feed;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.marketplace.Marketplace;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeedEndpointFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0005J\u0006\u0010\u0011\u001a\u00020\tJ\u0011\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\u0010\u0014R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\u0004j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/feed/FeedEndpointFactory;", "", "()V", "ACTIVITY_FEED_MARKETPLACE_MAP", "Ljava/util/HashMap;", "Lcom/amazon/music/marketplace/Marketplace;", "Lcom/amazon/mp3/feed/FeedEndpointFactory$SERVICE_ENDPOINT_TYPE;", "Lkotlin/collections/HashMap;", "ACTIVITY_FEED_SERVICE_BETA_ENDPOINT", "", "ACTIVITY_FEED_SERVICE_EU_ENDPOINT", "ACTIVITY_FEED_SERVICE_FE_ENDPOINT", "ACTIVITY_FEED_SERVICE_MAP", "ACTIVITY_FEED_SERVICE_NA_ENDPOINT", "DEFAULT_ENDPOINT", "getCurrentEndpoint", "homeMarketplace", "getCurrentOverrideId", "overrideEntries", "", "()[Ljava/lang/String;", "SERVICE_ENDPOINT_TYPE", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeedEndpointFactory {
    private final HashMap<Marketplace, SERVICE_ENDPOINT_TYPE> ACTIVITY_FEED_MARKETPLACE_MAP;
    private final String ACTIVITY_FEED_SERVICE_BETA_ENDPOINT;
    private final String ACTIVITY_FEED_SERVICE_EU_ENDPOINT;
    private final String ACTIVITY_FEED_SERVICE_FE_ENDPOINT;
    private final HashMap<SERVICE_ENDPOINT_TYPE, String> ACTIVITY_FEED_SERVICE_MAP;
    private final String ACTIVITY_FEED_SERVICE_NA_ENDPOINT;
    private final String DEFAULT_ENDPOINT;

    /* JADX INFO: compiled from: FeedEndpointFactory.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/feed/FeedEndpointFactory$SERVICE_ENDPOINT_TYPE;", "", "(Ljava/lang/String;I)V", "DEFAULT", "BETA", "NA", "EU", "FE", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum SERVICE_ENDPOINT_TYPE {
        DEFAULT,
        BETA,
        NA,
        EU,
        FE
    }

    public FeedEndpointFactory() {
        HashMap<SERVICE_ENDPOINT_TYPE, String> map = new HashMap<>();
        this.ACTIVITY_FEED_SERVICE_MAP = map;
        HashMap<Marketplace, SERVICE_ENDPOINT_TYPE> map2 = new HashMap<>();
        this.ACTIVITY_FEED_MARKETPLACE_MAP = map2;
        this.ACTIVITY_FEED_SERVICE_BETA_ENDPOINT = "https://5oow8tptt7.execute-api.us-east-1.amazonaws.com/beta/feed/item";
        this.ACTIVITY_FEED_SERVICE_NA_ENDPOINT = "https://ro7pgwukkj.execute-api.us-east-1.amazonaws.com/beta/feed/item";
        this.ACTIVITY_FEED_SERVICE_EU_ENDPOINT = "https://ieuumktv06.execute-api.eu-west-1.amazonaws.com/prod/feed/item";
        this.ACTIVITY_FEED_SERVICE_FE_ENDPOINT = "https://tybgdfkgme.execute-api.us-west-2.amazonaws.com/prod/feed/item";
        this.DEFAULT_ENDPOINT = "https://ro7pgwukkj.execute-api.us-east-1.amazonaws.com/beta/feed/item";
        map.put(SERVICE_ENDPOINT_TYPE.BETA, "https://5oow8tptt7.execute-api.us-east-1.amazonaws.com/beta/feed/item");
        SERVICE_ENDPOINT_TYPE service_endpoint_type = SERVICE_ENDPOINT_TYPE.NA;
        map.put(service_endpoint_type, "https://ro7pgwukkj.execute-api.us-east-1.amazonaws.com/beta/feed/item");
        SERVICE_ENDPOINT_TYPE service_endpoint_type2 = SERVICE_ENDPOINT_TYPE.EU;
        map.put(service_endpoint_type2, "https://ieuumktv06.execute-api.eu-west-1.amazonaws.com/prod/feed/item");
        SERVICE_ENDPOINT_TYPE service_endpoint_type3 = SERVICE_ENDPOINT_TYPE.FE;
        map.put(service_endpoint_type3, "https://tybgdfkgme.execute-api.us-west-2.amazonaws.com/prod/feed/item");
        map2.put(Marketplace.USA, service_endpoint_type);
        map2.put(Marketplace.UK, service_endpoint_type2);
        map2.put(Marketplace.GERMANY, service_endpoint_type2);
        map2.put(Marketplace.FRANCE, service_endpoint_type2);
        map2.put(Marketplace.ITALY, service_endpoint_type2);
        map2.put(Marketplace.SPAIN, service_endpoint_type2);
        map2.put(Marketplace.JAPAN, service_endpoint_type3);
        map2.put(Marketplace.ROW_NA, service_endpoint_type);
        map2.put(Marketplace.ROE_EU, service_endpoint_type2);
        map2.put(Marketplace.ROW_FE, service_endpoint_type3);
    }

    public final String getCurrentOverrideId() {
        String environment = SettingsUtil.EnvironmentPref.getEnvironment(AmazonApplication.getContext(), R.string.setting_key_dev_activity_feed_service_endpoint_override, SERVICE_ENDPOINT_TYPE.DEFAULT.name());
        Intrinsics.checkNotNullExpressionValue(environment, "getEnvironment(\n        …DPOINT_TYPE.DEFAULT.name)");
        return environment;
    }

    public final String getCurrentEndpoint(Marketplace homeMarketplace) {
        Intrinsics.checkNotNullParameter(homeMarketplace, "homeMarketplace");
        String str = this.ACTIVITY_FEED_SERVICE_MAP.get(SERVICE_ENDPOINT_TYPE.valueOf(getCurrentOverrideId()));
        if (str != null) {
            return str;
        }
        String str2 = this.ACTIVITY_FEED_SERVICE_MAP.get(this.ACTIVITY_FEED_MARKETPLACE_MAP.get(homeMarketplace));
        return str2 == null ? this.DEFAULT_ENDPOINT : str2;
    }

    public final String[] overrideEntries() {
        SERVICE_ENDPOINT_TYPE[] service_endpoint_typeArrValues = SERVICE_ENDPOINT_TYPE.values();
        ArrayList arrayList = new ArrayList(service_endpoint_typeArrValues.length);
        int length = service_endpoint_typeArrValues.length;
        int i = 0;
        while (i < length) {
            SERVICE_ENDPOINT_TYPE service_endpoint_type = service_endpoint_typeArrValues[i];
            i++;
            arrayList.add(service_endpoint_type.name());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }
}
