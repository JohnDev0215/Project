package org.xwalk.core;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkNavigationItem {
    private Object bridge;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;
    private ReflectMethod getUrlMethod = new ReflectMethod((Class<?>) null, "getUrl", (Class<?>[]) new Class[0]);
    private ReflectMethod getOriginalUrlMethod = new ReflectMethod((Class<?>) null, "getOriginalUrl", (Class<?>[]) new Class[0]);
    private ReflectMethod getTitleMethod = new ReflectMethod((Class<?>) null, "getTitle", (Class<?>[]) new Class[0]);

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkNavigationItem(Object obj) {
        this.bridge = obj;
        reflectionInit();
    }

    public String getUrl() {
        try {
            return (String) this.getUrlMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public String getOriginalUrl() {
        try {
            return (String) this.getOriginalUrlMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public String getTitle() {
        try {
            return (String) this.getTitleMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    void reflectionInit() {
        XWalkCoreWrapper.initEmbeddedMode();
        XWalkCoreWrapper xWalkCoreWrapper = XWalkCoreWrapper.getInstance();
        this.coreWrapper = xWalkCoreWrapper;
        if (xWalkCoreWrapper == null) {
            XWalkCoreWrapper.reserveReflectObject(this);
            return;
        }
        this.getUrlMethod.init(this.bridge, null, "getUrlSuper", new Class[0]);
        this.getOriginalUrlMethod.init(this.bridge, null, "getOriginalUrlSuper", new Class[0]);
        this.getTitleMethod.init(this.bridge, null, "getTitleSuper", new Class[0]);
    }
}
