package org.xwalk.core;

import cerence.ark.assistant.controller.domain.cc.CCRequestsEnum;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkHttpAuthHandler implements XWalkHttpAuth {
    private Object bridge;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;
    private ReflectMethod proceedStringStringMethod = new ReflectMethod((Class<?>) null, "proceed", (Class<?>[]) new Class[0]);
    private ReflectMethod cancelMethod = new ReflectMethod((Class<?>) null, CCRequestsEnum.CANCEL, (Class<?>[]) new Class[0]);
    private ReflectMethod isFirstAttemptMethod = new ReflectMethod((Class<?>) null, "isFirstAttempt", (Class<?>[]) new Class[0]);

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkHttpAuthHandler(Object obj) {
        this.bridge = obj;
        reflectionInit();
    }

    @Override // org.xwalk.core.XWalkHttpAuth
    public void proceed(String str, String str2) {
        try {
            this.proceedStringStringMethod.invoke(str, str2);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    @Override // org.xwalk.core.XWalkHttpAuth
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

    @Override // org.xwalk.core.XWalkHttpAuth
    public boolean isFirstAttempt() {
        try {
            return ((Boolean) this.isFirstAttemptMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
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
        this.proceedStringStringMethod.init(this.bridge, null, "proceedSuper", String.class, String.class);
        this.cancelMethod.init(this.bridge, null, "cancelSuper", new Class[0]);
        this.isFirstAttemptMethod.init(this.bridge, null, "isFirstAttemptSuper", new Class[0]);
    }
}
