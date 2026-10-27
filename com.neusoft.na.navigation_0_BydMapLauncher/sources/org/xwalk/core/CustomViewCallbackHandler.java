package org.xwalk.core;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class CustomViewCallbackHandler implements CustomViewCallback {
    private Object bridge;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod onCustomViewHiddenMethod = new ReflectMethod((Class<?>) null, "onCustomViewHidden", (Class<?>[]) new Class[0]);
    private ReflectMethod postWrapperMethod;

    protected Object getBridge() {
        return this.bridge;
    }

    public CustomViewCallbackHandler(Object obj) {
        this.bridge = obj;
        reflectionInit();
    }

    @Override // org.xwalk.core.CustomViewCallback
    public void onCustomViewHidden() {
        try {
            this.onCustomViewHiddenMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    void reflectionInit() {
        XWalkCoreWrapper.initEmbeddedMode();
        XWalkCoreWrapper xWalkCoreWrapper = XWalkCoreWrapper.getInstance();
        this.coreWrapper = xWalkCoreWrapper;
        if (xWalkCoreWrapper == null) {
            XWalkCoreWrapper.reserveReflectObject(this);
        } else {
            this.onCustomViewHiddenMethod.init(this.bridge, null, "onCustomViewHiddenSuper", new Class[0]);
        }
    }
}
