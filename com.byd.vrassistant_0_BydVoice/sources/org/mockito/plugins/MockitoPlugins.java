package org.mockito.plugins;

/* JADX INFO: loaded from: classes4.dex */
public interface MockitoPlugins {
    <T> T getDefaultPlugin(Class<T> cls);

    MockMaker getInlineMockMaker();
}
