package org.mockito.internal.configuration.plugins;

import org.mockito.plugins.AnnotationEngine;
import org.mockito.plugins.InstantiatorProvider;
import org.mockito.plugins.MockMaker;
import org.mockito.plugins.MockitoPlugins;
import org.mockito.plugins.StackTraceCleanerProvider;

/* JADX INFO: loaded from: classes4.dex */
public class Plugins {
    private static final PluginRegistry registry = new PluginRegistry();

    public static StackTraceCleanerProvider getStackTraceCleanerProvider() {
        return registry.getStackTraceCleanerProvider();
    }

    public static MockMaker getMockMaker() {
        return registry.getMockMaker();
    }

    public static InstantiatorProvider getInstantiatorProvider() {
        return registry.getInstantiatorProvider();
    }

    public static AnnotationEngine getAnnotationEngine() {
        return registry.getAnnotationEngine();
    }

    public static MockitoPlugins getPlugins() {
        return new DefaultMockitoPlugins();
    }
}
