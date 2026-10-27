package org.mockito.internal.configuration.plugins;

import org.mockito.plugins.AnnotationEngine;
import org.mockito.plugins.InstantiatorProvider;
import org.mockito.plugins.MockMaker;
import org.mockito.plugins.PluginSwitch;
import org.mockito.plugins.StackTraceCleanerProvider;

/* JADX INFO: loaded from: classes4.dex */
class PluginRegistry {
    private AnnotationEngine annotationEngine;
    private final InstantiatorProvider instantiatorProvider;
    private final MockMaker mockMaker;
    private final PluginSwitch pluginSwitch;
    private final StackTraceCleanerProvider stackTraceCleanerProvider;

    PluginRegistry() {
        PluginSwitch pluginSwitch = (PluginSwitch) new PluginLoader(new DefaultPluginSwitch()).loadPlugin(PluginSwitch.class);
        this.pluginSwitch = pluginSwitch;
        this.mockMaker = (MockMaker) new PluginLoader(pluginSwitch).withAlias("mock-maker-inline").loadPlugin(MockMaker.class);
        this.stackTraceCleanerProvider = (StackTraceCleanerProvider) new PluginLoader(pluginSwitch).loadPlugin(StackTraceCleanerProvider.class);
        this.instantiatorProvider = (InstantiatorProvider) new PluginLoader(pluginSwitch).loadPlugin(InstantiatorProvider.class);
        this.annotationEngine = (AnnotationEngine) new PluginLoader(pluginSwitch).loadPlugin(AnnotationEngine.class);
    }

    StackTraceCleanerProvider getStackTraceCleanerProvider() {
        return this.stackTraceCleanerProvider;
    }

    MockMaker getMockMaker() {
        return this.mockMaker;
    }

    InstantiatorProvider getInstantiatorProvider() {
        return this.instantiatorProvider;
    }

    AnnotationEngine getAnnotationEngine() {
        return this.annotationEngine;
    }
}
