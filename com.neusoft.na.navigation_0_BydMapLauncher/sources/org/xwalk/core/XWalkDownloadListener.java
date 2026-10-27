package org.xwalk.core;

import android.content.Context;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public abstract class XWalkDownloadListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Object bridge;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;

    public abstract void onDownloadStart(String str, String str2, String str3, String str4, long j);

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkDownloadListener(Context context) {
        ArrayList<Object> arrayList = new ArrayList<>();
        this.constructorTypes = arrayList;
        arrayList.add(Context.class);
        ArrayList<Object> arrayList2 = new ArrayList<>();
        this.constructorParams = arrayList2;
        arrayList2.add(context);
        reflectionInit();
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
            this.bridge = new ReflectConstructor(this.coreWrapper.getBridgeClass("XWalkDownloadListenerBridge"), clsArr).newInstance(this.constructorParams.toArray());
            ReflectMethod reflectMethod = this.postWrapperMethod;
            if (reflectMethod != null) {
                reflectMethod.invoke(new Object[0]);
            }
        } catch (UnsupportedOperationException unused) {
        }
    }
}
