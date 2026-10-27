package org.xwalk.core;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkHitTestResult {
    private Object bridge;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;
    private ReflectMethod enumtypeClassValueOfMethod = new ReflectMethod();
    private ReflectMethod getTypeMethod = new ReflectMethod((Class<?>) null, "getType", (Class<?>[]) new Class[0]);
    private ReflectMethod getExtraMethod = new ReflectMethod((Class<?>) null, "getExtra", (Class<?>[]) new Class[0]);

    public enum type {
        UNKNOWN_TYPE,
        ANCHOR_TYPE,
        PHONE_TYPE,
        GEO_TYPE,
        EMAIL_TYPE,
        IMAGE_TYPE,
        IMAGE_ANCHOR_TYPE,
        SRC_ANCHOR_TYPE,
        SRC_IMAGE_ANCHOR_TYPE,
        EDIT_TEXT_TYPE
    }

    private Object Converttype(type typeVar) {
        return this.enumtypeClassValueOfMethod.invoke(typeVar.toString());
    }

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkHitTestResult(Object obj) {
        this.bridge = obj;
        reflectionInit();
    }

    public type getType() {
        try {
            return type.valueOf(this.getTypeMethod.invoke(new Object[0]).toString());
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public String getExtra() {
        try {
            return (String) this.getExtraMethod.invoke(new Object[0]);
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
        this.enumtypeClassValueOfMethod.init(null, xWalkCoreWrapper.getBridgeClass("XWalkHitTestResultInternal$type"), "valueOf", String.class);
        this.getTypeMethod.init(this.bridge, null, "getTypeSuper", new Class[0]);
        this.getExtraMethod.init(this.bridge, null, "getExtraSuper", new Class[0]);
    }
}
