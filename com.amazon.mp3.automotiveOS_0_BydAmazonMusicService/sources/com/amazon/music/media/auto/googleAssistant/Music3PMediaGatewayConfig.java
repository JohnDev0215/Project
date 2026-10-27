package com.amazon.music.media.auto.googleAssistant;

import com.amazon.music.featuregating.configuration.Configuration;
import kotlin.Metadata;

/* JADX INFO: compiled from: Music3PMediaGatewayConfig.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¨\u0006\b"}, d2 = {"Lcom/amazon/music/media/auto/googleAssistant/Music3PMediaGatewayConfig;", "", "Lcom/amazon/music/featuregating/configuration/Configuration;", "configuration", "", "getGraphQLEndpoint", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class Music3PMediaGatewayConfig {
    public static final Music3PMediaGatewayConfig INSTANCE = new Music3PMediaGatewayConfig();

    private Music3PMediaGatewayConfig() {
    }

    public final String getGraphQLEndpoint(Configuration configuration) {
        String strString;
        return (configuration == null || (strString = configuration.string("google_assistant_3p_voice_gateway_uri", "https://us.api.prod.gateway.music.amazon.dev/graphql")) == null) ? "https://us.api.prod.gateway.music.amazon.dev/graphql" : strString;
    }
}
