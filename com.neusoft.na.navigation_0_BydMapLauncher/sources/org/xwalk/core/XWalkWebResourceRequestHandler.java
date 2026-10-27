package org.xwalk.core;

import android.net.Uri;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkWebResourceRequestHandler implements XWalkWebResourceRequest {
    private Object bridge;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;
    private ReflectMethod getUrlMethod = new ReflectMethod((Class<?>) null, "getUrl", (Class<?>[]) new Class[0]);
    private ReflectMethod isForMainFrameMethod = new ReflectMethod((Class<?>) null, "isForMainFrame", (Class<?>[]) new Class[0]);
    private ReflectMethod hasGestureMethod = new ReflectMethod((Class<?>) null, "hasGesture", (Class<?>[]) new Class[0]);
    private ReflectMethod getMethodMethod = new ReflectMethod((Class<?>) null, "getMethod", (Class<?>[]) new Class[0]);
    private ReflectMethod getRequestHeadersMethod = new ReflectMethod((Class<?>) null, "getRequestHeaders", (Class<?>[]) new Class[0]);

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkWebResourceRequestHandler(Object obj) {
        this.bridge = obj;
        reflectionInit();
    }

    @Override // org.xwalk.core.XWalkWebResourceRequest
    public Uri getUrl() {
        try {
            return (Uri) this.getUrlMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    @Override // org.xwalk.core.XWalkWebResourceRequest
    public boolean isForMainFrame() {
        try {
            return ((Boolean) this.isForMainFrameMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    @Override // org.xwalk.core.XWalkWebResourceRequest
    public boolean hasGesture() {
        try {
            return ((Boolean) this.hasGestureMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    @Override // org.xwalk.core.XWalkWebResourceRequest
    public String getMethod() {
        try {
            return (String) this.getMethodMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    @Override // org.xwalk.core.XWalkWebResourceRequest
    public Map<String, String> getRequestHeaders() {
        try {
            return (Map) this.getRequestHeadersMethod.invoke(new Object[0]);
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
        this.isForMainFrameMethod.init(this.bridge, null, "isForMainFrameSuper", new Class[0]);
        this.hasGestureMethod.init(this.bridge, null, "hasGestureSuper", new Class[0]);
        this.getMethodMethod.init(this.bridge, null, "getMethodSuper", new Class[0]);
        this.getRequestHeadersMethod.init(this.bridge, null, "getRequestHeadersSuper", new Class[0]);
    }
}
