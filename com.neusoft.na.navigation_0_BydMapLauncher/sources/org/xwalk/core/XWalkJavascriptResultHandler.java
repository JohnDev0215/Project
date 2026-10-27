package org.xwalk.core;

import cerence.ark.assistant.controller.domain.cc.CCRequestsEnum;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkJavascriptResultHandler implements XWalkJavascriptResult {
    private Object bridge;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;
    private ReflectMethod confirmMethod = new ReflectMethod((Class<?>) null, "confirm", (Class<?>[]) new Class[0]);
    private ReflectMethod confirmWithResultStringMethod = new ReflectMethod((Class<?>) null, "confirmWithResult", (Class<?>[]) new Class[0]);
    private ReflectMethod cancelMethod = new ReflectMethod((Class<?>) null, CCRequestsEnum.CANCEL, (Class<?>[]) new Class[0]);

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkJavascriptResultHandler(Object obj) {
        this.bridge = obj;
        reflectionInit();
    }

    @Override // org.xwalk.core.XWalkJavascriptResult
    public void confirm() {
        try {
            this.confirmMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    @Override // org.xwalk.core.XWalkJavascriptResult
    public void confirmWithResult(String str) {
        try {
            this.confirmWithResultStringMethod.invoke(str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    @Override // org.xwalk.core.XWalkJavascriptResult
    public void cancel() {
        try {
            this.cancelMethod.invoke(new Object[0]);
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
            return;
        }
        this.confirmMethod.init(this.bridge, null, "confirmSuper", new Class[0]);
        this.confirmWithResultStringMethod.init(this.bridge, null, "confirmWithResultSuper", String.class);
        this.cancelMethod.init(this.bridge, null, "cancelSuper", new Class[0]);
    }
}
