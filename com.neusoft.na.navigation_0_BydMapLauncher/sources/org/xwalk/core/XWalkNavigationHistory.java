package org.xwalk.core;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkNavigationHistory {
    private Object bridge;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;
    private ReflectMethod enumDirectionClassValueOfMethod = new ReflectMethod();
    private ReflectMethod sizeMethod = new ReflectMethod((Class<?>) null, "size", (Class<?>[]) new Class[0]);
    private ReflectMethod hasItemAtintMethod = new ReflectMethod((Class<?>) null, "hasItemAt", (Class<?>[]) new Class[0]);
    private ReflectMethod getItemAtintMethod = new ReflectMethod((Class<?>) null, "getItemAt", (Class<?>[]) new Class[0]);
    private ReflectMethod getCurrentItemMethod = new ReflectMethod((Class<?>) null, "getCurrentItem", (Class<?>[]) new Class[0]);
    private ReflectMethod canGoBackMethod = new ReflectMethod((Class<?>) null, "canGoBack", (Class<?>[]) new Class[0]);
    private ReflectMethod canGoForwardMethod = new ReflectMethod((Class<?>) null, "canGoForward", (Class<?>[]) new Class[0]);
    private ReflectMethod navigateDirectionInternalintMethod = new ReflectMethod((Class<?>) null, "navigate", (Class<?>[]) new Class[0]);
    private ReflectMethod getCurrentIndexMethod = new ReflectMethod((Class<?>) null, "getCurrentIndex", (Class<?>[]) new Class[0]);
    private ReflectMethod clearMethod = new ReflectMethod((Class<?>) null, "clear", (Class<?>[]) new Class[0]);

    public enum Direction {
        BACKWARD,
        FORWARD
    }

    private Object ConvertDirection(Direction direction) {
        return this.enumDirectionClassValueOfMethod.invoke(direction.toString());
    }

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkNavigationHistory(Object obj) {
        this.bridge = obj;
        reflectionInit();
    }

    public int size() {
        try {
            return ((Integer) this.sizeMethod.invoke(new Object[0])).intValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return 0;
        }
    }

    public boolean hasItemAt(int i) {
        try {
            return ((Boolean) this.hasItemAtintMethod.invoke(Integer.valueOf(i))).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public XWalkNavigationItem getItemAt(int i) {
        try {
            return (XWalkNavigationItem) this.coreWrapper.getWrapperObject(this.getItemAtintMethod.invoke(Integer.valueOf(i)));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public XWalkNavigationItem getCurrentItem() {
        try {
            return (XWalkNavigationItem) this.coreWrapper.getWrapperObject(this.getCurrentItemMethod.invoke(new Object[0]));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public boolean canGoBack() {
        try {
            return ((Boolean) this.canGoBackMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public boolean canGoForward() {
        try {
            return ((Boolean) this.canGoForwardMethod.invoke(new Object[0])).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void navigate(Direction direction, int i) {
        try {
            this.navigateDirectionInternalintMethod.invoke(ConvertDirection(direction), Integer.valueOf(i));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public int getCurrentIndex() {
        try {
            return ((Integer) this.getCurrentIndexMethod.invoke(new Object[0])).intValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return 0;
        }
    }

    public void clear() {
        try {
            this.clearMethod.invoke(new Object[0]);
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
        this.enumDirectionClassValueOfMethod.init(null, xWalkCoreWrapper.getBridgeClass("XWalkNavigationHistoryInternal$DirectionInternal"), "valueOf", String.class);
        this.sizeMethod.init(this.bridge, null, "sizeSuper", new Class[0]);
        this.hasItemAtintMethod.init(this.bridge, null, "hasItemAtSuper", Integer.TYPE);
        this.getItemAtintMethod.init(this.bridge, null, "getItemAtSuper", Integer.TYPE);
        this.getCurrentItemMethod.init(this.bridge, null, "getCurrentItemSuper", new Class[0]);
        this.canGoBackMethod.init(this.bridge, null, "canGoBackSuper", new Class[0]);
        this.canGoForwardMethod.init(this.bridge, null, "canGoForwardSuper", new Class[0]);
        this.navigateDirectionInternalintMethod.init(this.bridge, null, "navigateSuper", this.coreWrapper.getBridgeClass("XWalkNavigationHistoryInternal$DirectionInternal"), Integer.TYPE);
        this.getCurrentIndexMethod.init(this.bridge, null, "getCurrentIndexSuper", new Class[0]);
        this.clearMethod.init(this.bridge, null, "clearSuper", new Class[0]);
    }
}
