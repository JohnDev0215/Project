package org.xwalk.core;

import cerence.ark.assistant.controller.domain.cc.CCRequestsEnum;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ClientCertRequestHandler implements ClientCertRequest {
    private Object bridge;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;
    private ReflectMethod proceedPrivateKeyListMethod = new ReflectMethod((Class<?>) null, "proceed", (Class<?>[]) new Class[0]);
    private ReflectMethod ignoreMethod = new ReflectMethod((Class<?>) null, "ignore", (Class<?>[]) new Class[0]);
    private ReflectMethod cancelMethod = new ReflectMethod((Class<?>) null, CCRequestsEnum.CANCEL, (Class<?>[]) new Class[0]);
    private ReflectMethod getHostMethod = new ReflectMethod((Class<?>) null, "getHost", (Class<?>[]) new Class[0]);
    private ReflectMethod getPortMethod = new ReflectMethod((Class<?>) null, "getPort", (Class<?>[]) new Class[0]);
    private ReflectMethod getKeyTypesMethod = new ReflectMethod((Class<?>) null, "getKeyTypes", (Class<?>[]) new Class[0]);
    private ReflectMethod getPrincipalsMethod = new ReflectMethod((Class<?>) null, "getPrincipals", (Class<?>[]) new Class[0]);

    protected Object getBridge() {
        return this.bridge;
    }

    public ClientCertRequestHandler(Object obj) {
        this.bridge = obj;
        reflectionInit();
    }

    @Override // org.xwalk.core.ClientCertRequest
    public void proceed(PrivateKey privateKey, List<X509Certificate> list) {
        try {
            this.proceedPrivateKeyListMethod.invoke(privateKey, list);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    @Override // org.xwalk.core.ClientCertRequest
    public void ignore() {
        try {
            this.ignoreMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    @Override // org.xwalk.core.ClientCertRequest
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

    @Override // org.xwalk.core.ClientCertRequest
    public String getHost() {
        try {
            return (String) this.getHostMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    @Override // org.xwalk.core.ClientCertRequest
    public int getPort() {
        try {
            return ((Integer) this.getPortMethod.invoke(new Object[0])).intValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return 0;
        }
    }

    @Override // org.xwalk.core.ClientCertRequest
    public String[] getKeyTypes() {
        try {
            return (String[]) this.getKeyTypesMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    @Override // org.xwalk.core.ClientCertRequest
    public Principal[] getPrincipals() {
        try {
            return (Principal[]) this.getPrincipalsMethod.invoke(new Object[0]);
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
        this.proceedPrivateKeyListMethod.init(this.bridge, null, "proceedSuper", PrivateKey.class, List.class);
        this.ignoreMethod.init(this.bridge, null, "ignoreSuper", new Class[0]);
        this.cancelMethod.init(this.bridge, null, "cancelSuper", new Class[0]);
        this.getHostMethod.init(this.bridge, null, "getHostSuper", new Class[0]);
        this.getPortMethod.init(this.bridge, null, "getPortSuper", new Class[0]);
        this.getKeyTypesMethod.init(this.bridge, null, "getKeyTypesSuper", new Class[0]);
        this.getPrincipalsMethod.init(this.bridge, null, "getPrincipalsSuper", new Class[0]);
    }
}
