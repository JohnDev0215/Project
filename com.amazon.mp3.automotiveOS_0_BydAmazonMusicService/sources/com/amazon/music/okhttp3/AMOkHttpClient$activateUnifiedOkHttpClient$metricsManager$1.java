package com.amazon.music.okhttp3;

import com.amazon.music.platform.providers.MetricsProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: AMOkHttpClient.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class AMOkHttpClient$activateUnifiedOkHttpClient$metricsManager$1 extends FunctionReferenceImpl implements Function0<MetricsProvider> {
    public static final AMOkHttpClient$activateUnifiedOkHttpClient$metricsManager$1 INSTANCE = new AMOkHttpClient$activateUnifiedOkHttpClient$metricsManager$1();

    AMOkHttpClient$activateUnifiedOkHttpClient$metricsManager$1() {
        super(0, AMOkHttpClient.class, "metricsProvider", "metricsProvider()Lcom/amazon/music/platform/providers/MetricsProvider;", 1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final MetricsProvider invoke() {
        return AMOkHttpClient.metricsProvider();
    }
}
