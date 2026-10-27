package com.amazon.music.castingviews;

import com.amazon.music.casting.Casting;
import com.amazon.music.casting.Configuration;
import com.amazon.music.uicasting.UiCasting;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class CastingFactory {
    public static UiCasting createUiCasting(Configuration configuration) {
        Validate.notNull(configuration, "configuration cannot be null", new Object[0]);
        return new UiCasting(createCasting(configuration));
    }

    public static Casting createCasting(Configuration configuration) {
        Validate.notNull(configuration, "configuration cannot be null", new Object[0]);
        com.amazon.music.casting.Configuration configurationBuild = new Configuration.Builder().withRequestInterceptor(configuration.getRequestInterceptor()).withMarketplace(configuration.getMarketplace()).withCastingSessionConfig(configuration.getCastingSessionConfig()).build();
        configurationBuild.setIsDebugMode(Configuration.isDebugMode());
        return Casting.builder(configuration.getActivityContext(), configurationBuild).build();
    }
}
