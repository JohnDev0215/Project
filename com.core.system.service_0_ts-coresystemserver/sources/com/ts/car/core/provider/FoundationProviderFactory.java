package com.ts.car.core.provider;

/* JADX INFO: loaded from: classes.dex */
public class FoundationProviderFactory {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static IFoundationProvider gFoundationProvider;

    public static synchronized IFoundationProvider getFoundationProvider() {
        if (gFoundationProvider != null) {
            return gFoundationProvider;
        }
        ClassLoader classLoader = FoundationProviderFactory.class.getClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass("com.ts.car.core.provider.BuildFoundationProvider");
            IFoundationProvider iFoundationProvider = (IFoundationProvider) clazz.newInstance();
            gFoundationProvider = iFoundationProvider;
            return iFoundationProvider;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            ex.printStackTrace();
            AnnotationFoundationProvider annotationFoundationProvider = new AnnotationFoundationProvider();
            gFoundationProvider = annotationFoundationProvider;
            return annotationFoundationProvider;
        }
    }
}
