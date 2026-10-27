package com.ts.car.core.provider;

/* JADX INFO: loaded from: classes.dex */
public class ServiceProviderFactory {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static IServiceProvider gServiceProvider;

    public static synchronized IServiceProvider getServiceProvider() {
        if (gServiceProvider != null) {
            return gServiceProvider;
        }
        ClassLoader classLoader = ServiceProviderFactory.class.getClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass("com.ts.car.core.provider.BuildServiceProvider");
            IServiceProvider iServiceProvider = (IServiceProvider) clazz.newInstance();
            gServiceProvider = iServiceProvider;
            return iServiceProvider;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            ex.printStackTrace();
            AnnotationServiceProvider annotationServiceProvider = new AnnotationServiceProvider();
            gServiceProvider = annotationServiceProvider;
            return annotationServiceProvider;
        }
    }
}
