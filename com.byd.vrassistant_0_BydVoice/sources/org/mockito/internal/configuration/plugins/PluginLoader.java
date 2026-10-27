package org.mockito.internal.configuration.plugins;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.Enumeration;
import org.mockito.internal.util.collections.Iterables;
import org.mockito.plugins.PluginSwitch;

/* JADX INFO: loaded from: classes4.dex */
class PluginLoader {
    private String alias;
    private final PluginSwitch pluginSwitch;
    private final DefaultMockitoPlugins plugins = new DefaultMockitoPlugins();

    public PluginLoader(PluginSwitch pluginSwitch) {
        this.pluginSwitch = pluginSwitch;
    }

    @Deprecated
    PluginLoader withAlias(String str) {
        this.alias = str;
        return this;
    }

    <T> T loadPlugin(final Class<T> cls) {
        try {
            T t = (T) loadImpl(cls);
            return t != null ? t : (T) this.plugins.getDefaultPlugin(cls);
        } catch (Throwable th) {
            return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: org.mockito.internal.configuration.plugins.PluginLoader.1
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    throw new IllegalStateException("Could not initialize plugin: " + cls, th);
                }
            });
        }
    }

    private <T> T loadImpl(Class<T> cls) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader == null) {
            contextClassLoader = ClassLoader.getSystemClassLoader();
        }
        try {
            Enumeration<URL> resources = contextClassLoader.getResources("mockito-extensions/" + cls.getName());
            try {
                String strFindPluginClass = new PluginFinder(this.pluginSwitch).findPluginClass(Iterables.toIterable(resources));
                if (strFindPluginClass == null) {
                    return null;
                }
                if (strFindPluginClass.equals(this.alias)) {
                    strFindPluginClass = this.plugins.getDefaultPluginClass(this.alias);
                }
                return cls.cast(contextClassLoader.loadClass(strFindPluginClass).newInstance());
            } catch (Exception e) {
                throw new IllegalStateException("Failed to load " + cls + " implementation declared in " + resources, e);
            }
        } catch (IOException e2) {
            throw new IllegalStateException("Failed to load " + cls, e2);
        }
    }
}
