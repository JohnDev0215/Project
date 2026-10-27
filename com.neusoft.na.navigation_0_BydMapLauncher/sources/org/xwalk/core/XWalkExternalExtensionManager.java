package org.xwalk.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public abstract class XWalkExternalExtensionManager {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Object bridge;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;
    private ReflectMethod getViewActivityMethod = new ReflectMethod((Class<?>) null, "getViewActivity", (Class<?>[]) new Class[0]);
    private ReflectMethod getViewContextMethod = new ReflectMethod((Class<?>) null, "getViewContext", (Class<?>[]) new Class[0]);
    private ReflectMethod loadExtensionStringMethod = new ReflectMethod((Class<?>) null, "loadExtension", (Class<?>[]) new Class[0]);
    private ReflectMethod onStartMethod = new ReflectMethod((Class<?>) null, "onStart", (Class<?>[]) new Class[0]);
    private ReflectMethod onResumeMethod = new ReflectMethod((Class<?>) null, "onResume", (Class<?>[]) new Class[0]);
    private ReflectMethod onPauseMethod = new ReflectMethod((Class<?>) null, "onPause", (Class<?>[]) new Class[0]);
    private ReflectMethod onStopMethod = new ReflectMethod((Class<?>) null, "onStop", (Class<?>[]) new Class[0]);
    private ReflectMethod onDestroyMethod = new ReflectMethod((Class<?>) null, "onDestroy", (Class<?>[]) new Class[0]);
    private ReflectMethod onActivityResultintintIntentMethod = new ReflectMethod((Class<?>) null, "onActivityResult", (Class<?>[]) new Class[0]);

    public abstract void onNewIntent(Intent intent);

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkExternalExtensionManager(XWalkView xWalkView) {
        ArrayList<Object> arrayList = new ArrayList<>();
        this.constructorTypes = arrayList;
        arrayList.add("XWalkViewBridge");
        ArrayList<Object> arrayList2 = new ArrayList<>();
        this.constructorParams = arrayList2;
        arrayList2.add(xWalkView);
        reflectionInit();
    }

    @Deprecated
    public Activity getViewActivity() {
        try {
            return (Activity) this.getViewActivityMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public Context getViewContext() {
        try {
            return (Context) this.getViewContextMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void loadExtension(String str) {
        try {
            this.loadExtensionStringMethod.invoke(str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onStart() {
        try {
            this.onStartMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onResume() {
        try {
            this.onResumeMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onPause() {
        try {
            this.onPauseMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onStop() {
        try {
            this.onStopMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onDestroy() {
        try {
            this.onDestroyMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    @Deprecated
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            this.onActivityResultintintIntentMethod.invoke(Integer.valueOf(i), Integer.valueOf(i2), intent);
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
            this.bridge = new ReflectConstructor(this.coreWrapper.getBridgeClass("XWalkExternalExtensionManagerBridge"), clsArr).newInstance(this.constructorParams.toArray());
            ReflectMethod reflectMethod = this.postWrapperMethod;
            if (reflectMethod != null) {
                reflectMethod.invoke(new Object[0]);
            }
            this.getViewActivityMethod.init(this.bridge, null, "getViewActivitySuper", new Class[0]);
            this.getViewContextMethod.init(this.bridge, null, "getViewContextSuper", new Class[0]);
            this.loadExtensionStringMethod.init(this.bridge, null, "loadExtensionSuper", String.class);
            this.onStartMethod.init(this.bridge, null, "onStartSuper", new Class[0]);
            this.onResumeMethod.init(this.bridge, null, "onResumeSuper", new Class[0]);
            this.onPauseMethod.init(this.bridge, null, "onPauseSuper", new Class[0]);
            this.onStopMethod.init(this.bridge, null, "onStopSuper", new Class[0]);
            this.onDestroyMethod.init(this.bridge, null, "onDestroySuper", new Class[0]);
            this.onActivityResultintintIntentMethod.init(this.bridge, null, "onActivityResultSuper", Integer.TYPE, Integer.TYPE, Intent.class);
        } catch (UnsupportedOperationException unused) {
        }
    }
}
