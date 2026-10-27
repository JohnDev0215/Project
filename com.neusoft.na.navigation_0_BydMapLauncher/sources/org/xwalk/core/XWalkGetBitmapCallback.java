package org.xwalk.core;

import android.graphics.Bitmap;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public abstract class XWalkGetBitmapCallback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Object bridge;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;
    private ArrayList<Object> constructorTypes = new ArrayList<>();
    private ArrayList<Object> constructorParams = new ArrayList<>();

    public abstract void onFinishGetBitmap(Bitmap bitmap, int i);

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkGetBitmapCallback() {
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
            this.bridge = new ReflectConstructor(this.coreWrapper.getBridgeClass("XWalkGetBitmapCallbackBridge"), clsArr).newInstance(this.constructorParams.toArray());
            ReflectMethod reflectMethod = this.postWrapperMethod;
            if (reflectMethod != null) {
                reflectMethod.invoke(new Object[0]);
            }
        } catch (UnsupportedOperationException unused) {
        }
    }
}
