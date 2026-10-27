package com.google.firebase.perf.plugin.instrumentation;

import com.android.build.api.instrumentation.ClassContext;
import com.google.common.collect.ImmutableList;
import com.google.firebase.perf.plugin.instrumentation.annotation.AnnotatedMethodInstrumentationConfig;
import com.google.firebase.perf.plugin.instrumentation.annotation.FirebaseTimerAnnotationConfig;
import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentationConfig;
import com.google.firebase.perf.plugin.instrumentation.network.config.HttpClientExecuteHostRequestContextIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.HttpClientExecuteHostRequestHandlerContextIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.HttpClientExecuteHostRequestHandlerIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.HttpClientExecuteHostRequestIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.HttpClientExecuteRequestContextIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.HttpClientExecuteRequestHandlerContextIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.HttpClientExecuteRequestHandlerIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.HttpClientExecuteRequestIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.OkHttpClientCallEnqueueIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.OkHttpClientCallExecuteIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.UrlConnectionGetContentClassIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.UrlConnectionGetContentIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.UrlConnectionOpenConnectionIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.UrlConnectionOpenConnectionProxyIC;
import com.google.firebase.perf.plugin.instrumentation.network.config.UrlConnectionOpenStreamIC;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class InstrumentationConfigFactory {
    private final List<AnnotatedMethodInstrumentationConfig> annotatedMethodConfigs = ImmutableList.of(new FirebaseTimerAnnotationConfig());
    private final List<NetworkObjectInstrumentationConfig> networkObjectConfigs = ImmutableList.of(new UrlConnectionOpenConnectionIC(), new UrlConnectionOpenConnectionProxyIC(), new UrlConnectionOpenStreamIC(), new UrlConnectionGetContentIC(), new UrlConnectionGetContentClassIC(), new HttpClientExecuteRequestIC(), new HttpClientExecuteRequestContextIC(), new HttpClientExecuteRequestHandlerIC(), new HttpClientExecuteRequestHandlerContextIC(), new HttpClientExecuteHostRequestIC(), new HttpClientExecuteHostRequestContextIC(), new HttpClientExecuteHostRequestHandlerIC(), new HttpClientExecuteHostRequestHandlerContextIC(), new OkHttpClientCallExecuteIC(), new OkHttpClientCallEnqueueIC());

    public ClassLoaderBasedInstrumentationConfig newClassLoaderBasedInstrumentationConfig(ClassLoader classLoader) {
        return new ClassLoaderBasedInstrumentationConfig(classLoader, this.annotatedMethodConfigs, this.networkObjectConfigs);
    }

    public ClassDataBasedInstrumentationConfig newClassDataBasedInstrumentationConfig(ClassContext classContext) {
        return new ClassDataBasedInstrumentationConfig(classContext, this.annotatedMethodConfigs, this.networkObjectConfigs);
    }
}
