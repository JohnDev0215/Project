package org.xwalk.core;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkViewDatabase {
    private static XWalkCoreWrapper coreWrapper;
    private static ReflectMethod hasFormDataMethod = new ReflectMethod((Class<?>) null, "hasFormData", (Class<?>[]) new Class[0]);
    private static ReflectMethod clearFormDataMethod = new ReflectMethod((Class<?>) null, "clearFormData", (Class<?>[]) new Class[0]);

    public static boolean hasFormData() {
        reflectionInit();
        try {
            return ((Boolean) hasFormDataMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public static void clearFormData() {
        reflectionInit();
        try {
            clearFormDataMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (coreWrapper == null) {
                clearFormDataMethod.setArguments(new Object[0]);
                XWalkCoreWrapper.reserveReflectMethod(clearFormDataMethod);
            } else {
                XWalkCoreWrapper.handleRuntimeError(e);
            }
        }
    }

    static void reflectionInit() {
        if (coreWrapper != null) {
            return;
        }
        XWalkCoreWrapper.initEmbeddedMode();
        XWalkCoreWrapper xWalkCoreWrapper = XWalkCoreWrapper.getInstance();
        coreWrapper = xWalkCoreWrapper;
        if (xWalkCoreWrapper == null) {
            XWalkCoreWrapper.reserveReflectClass(XWalkViewDatabase.class);
            return;
        }
        Class<?> bridgeClass = xWalkCoreWrapper.getBridgeClass("XWalkViewDatabaseBridge");
        hasFormDataMethod.init(null, bridgeClass, "hasFormData", new Class[0]);
        clearFormDataMethod.init(null, bridgeClass, "clearFormData", new Class[0]);
    }
}
