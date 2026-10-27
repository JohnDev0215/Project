package org.xwalk.core;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkCookieManager {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Object bridge;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;
    private ReflectMethod setAcceptCookiebooleanMethod = new ReflectMethod((Class<?>) null, "setAcceptCookie", (Class<?>[]) new Class[0]);
    private ReflectMethod acceptCookieMethod = new ReflectMethod((Class<?>) null, "acceptCookie", (Class<?>[]) new Class[0]);
    private ReflectMethod setCookieStringStringMethod = new ReflectMethod((Class<?>) null, "setCookie", (Class<?>[]) new Class[0]);
    private ReflectMethod getCookieStringMethod = new ReflectMethod((Class<?>) null, "getCookie", (Class<?>[]) new Class[0]);
    private ReflectMethod removeSessionCookieMethod = new ReflectMethod((Class<?>) null, "removeSessionCookie", (Class<?>[]) new Class[0]);
    private ReflectMethod removeAllCookieMethod = new ReflectMethod((Class<?>) null, "removeAllCookie", (Class<?>[]) new Class[0]);
    private ReflectMethod hasCookiesMethod = new ReflectMethod((Class<?>) null, "hasCookies", (Class<?>[]) new Class[0]);
    private ReflectMethod removeExpiredCookieMethod = new ReflectMethod((Class<?>) null, "removeExpiredCookie", (Class<?>[]) new Class[0]);
    private ReflectMethod flushCookieStoreMethod = new ReflectMethod((Class<?>) null, "flushCookieStore", (Class<?>[]) new Class[0]);
    private ReflectMethod allowFileSchemeCookiesMethod = new ReflectMethod((Class<?>) null, "allowFileSchemeCookies", (Class<?>[]) new Class[0]);
    private ReflectMethod setAcceptFileSchemeCookiesbooleanMethod = new ReflectMethod((Class<?>) null, "setAcceptFileSchemeCookies", (Class<?>[]) new Class[0]);
    private ArrayList<Object> constructorTypes = new ArrayList<>();
    private ArrayList<Object> constructorParams = new ArrayList<>();

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkCookieManager() {
        reflectionInit();
    }

    public void setAcceptCookie(boolean z) {
        try {
            this.setAcceptCookiebooleanMethod.invoke(Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean acceptCookie() {
        try {
            return ((Boolean) this.acceptCookieMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setCookie(String str, String str2) {
        try {
            this.setCookieStringStringMethod.invoke(str, str2);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public String getCookie(String str) {
        try {
            return (String) this.getCookieStringMethod.invoke(str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void removeSessionCookie() {
        try {
            this.removeSessionCookieMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void removeAllCookie() {
        try {
            this.removeAllCookieMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean hasCookies() {
        try {
            return ((Boolean) this.hasCookiesMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void removeExpiredCookie() {
        try {
            this.removeExpiredCookieMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void flushCookieStore() {
        try {
            this.flushCookieStoreMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean allowFileSchemeCookies() {
        try {
            return ((Boolean) this.allowFileSchemeCookiesMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void setAcceptFileSchemeCookies(boolean z) {
        try {
            this.setAcceptFileSchemeCookiesbooleanMethod.invoke(Boolean.valueOf(z));
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
        int size = this.constructorTypes.size();
        Class[] clsArr = new Class[size + 1];
        for (int i = 0; i < size; i++) {
            Object obj = this.constructorTypes.get(i);
            if (obj instanceof String) {
                clsArr[i] = this.coreWrapper.getBridgeClass((String) obj);
                ArrayList<Object> arrayList = this.constructorParams;
                arrayList.set(i, this.coreWrapper.getBridgeObject(arrayList.get(i)));
            } else if (obj instanceof Class) {
                clsArr[i] = (Class) obj;
            }
        }
        clsArr[size] = Object.class;
        this.constructorParams.add(this);
        try {
            this.bridge = new ReflectConstructor(this.coreWrapper.getBridgeClass("XWalkCookieManagerBridge"), clsArr).newInstance(this.constructorParams.toArray());
            ReflectMethod reflectMethod = this.postWrapperMethod;
            if (reflectMethod != null) {
                reflectMethod.invoke(new Object[0]);
            }
            this.setAcceptCookiebooleanMethod.init(this.bridge, null, "setAcceptCookieSuper", Boolean.TYPE);
            this.acceptCookieMethod.init(this.bridge, null, "acceptCookieSuper", new Class[0]);
            this.setCookieStringStringMethod.init(this.bridge, null, "setCookieSuper", String.class, String.class);
            this.getCookieStringMethod.init(this.bridge, null, "getCookieSuper", String.class);
            this.removeSessionCookieMethod.init(this.bridge, null, "removeSessionCookieSuper", new Class[0]);
            this.removeAllCookieMethod.init(this.bridge, null, "removeAllCookieSuper", new Class[0]);
            this.hasCookiesMethod.init(this.bridge, null, "hasCookiesSuper", new Class[0]);
            this.removeExpiredCookieMethod.init(this.bridge, null, "removeExpiredCookieSuper", new Class[0]);
            this.flushCookieStoreMethod.init(this.bridge, null, "flushCookieStoreSuper", new Class[0]);
            this.allowFileSchemeCookiesMethod.init(this.bridge, null, "allowFileSchemeCookiesSuper", new Class[0]);
            this.setAcceptFileSchemeCookiesbooleanMethod.init(this.bridge, null, "setAcceptFileSchemeCookiesSuper", Boolean.TYPE);
        } catch (UnsupportedOperationException unused) {
        }
    }
}
