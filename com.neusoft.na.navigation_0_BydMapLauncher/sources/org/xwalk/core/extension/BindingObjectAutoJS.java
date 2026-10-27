package org.xwalk.core.extension;

import android.util.Log;
import com.neusoft.na.common.FileUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class BindingObjectAutoJS extends BindingObject {
    @Override // org.xwalk.core.extension.BindingObject
    public Object handleMessage(MessageInfo messageInfo) {
        try {
            return this.mInstanceHelper.getExtension().getReflection().getReflectionByBindingClass(getClass().getName()).handleMessage(messageInfo, this);
        } catch (Exception e) {
            Log.e("BindingObjectAutoJs", e.toString());
            return null;
        }
    }

    public JsContextInfo getJsContextInfo() {
        return new JsContextInfo(this.mInstanceHelper.getId(), this.mInstanceHelper.getExtension(), getClass(), this.mObjectId);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void invokeJsCallback(org.xwalk.core.extension.JsContextInfo r3, java.lang.String r4, java.lang.Object... r5) {
        /*
            r0 = r5
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            int r1 = r0.length
            r2 = 1
            if (r1 != r2) goto L14
            r1 = 0
            r0 = r0[r1]
            boolean r1 = r0 instanceof org.json.JSONArray
            if (r1 == 0) goto L14
            org.json.JSONArray r0 = (org.json.JSONArray) r0
            r5 = r0
            org.json.JSONArray r5 = (org.json.JSONArray) r5
            goto L1e
        L14:
            java.lang.Object r5 = org.xwalk.core.extension.ReflectionHelper.toSerializableObject(r5)
            r0 = r5
            org.json.JSONArray r0 = (org.json.JSONArray) r0
            r5 = r0
            org.json.JSONArray r5 = (org.json.JSONArray) r5
        L1e:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Exception -> L38
            r5.<init>()     // Catch: java.lang.Exception -> L38
            java.lang.String r1 = "cmd"
            java.lang.String r2 = "invokeCallback"
            r5.put(r1, r2)     // Catch: java.lang.Exception -> L38
            java.lang.String r1 = "callbackId"
            r5.put(r1, r4)     // Catch: java.lang.Exception -> L38
            java.lang.String r4 = "args"
            r5.put(r4, r0)     // Catch: java.lang.Exception -> L38
            r3.postMessage(r5)     // Catch: java.lang.Exception -> L38
            goto L3c
        L38:
            r3 = move-exception
            r3.printStackTrace()
        L3c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.extension.BindingObjectAutoJS.invokeJsCallback(org.xwalk.core.extension.JsContextInfo, java.lang.String, java.lang.Object[]):void");
    }

    public void invokeJsCallback(String str, Object... objArr) {
        invokeJsCallback(getJsContextInfo(), str, objArr);
    }

    public static void invokeJsCallback(JsContextInfo jsContextInfo, byte[] bArr) {
        jsContextInfo.postMessage(bArr);
    }

    public void invokeJsCallback(byte[] bArr) {
        getJsContextInfo().postMessage(bArr);
    }

    public static void dispatchEvent(JsContextInfo jsContextInfo, String str, Object obj) {
        if (!jsContextInfo.getTargetReflect().isEventSupported(str)) {
            Log.w(jsContextInfo.getTag(), "Unsupport event in extension: " + str);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cmd", "dispatchEvent");
            jSONObject.put("constructorName", jsContextInfo.getConstructorName());
            jSONObject.put("objectId", jsContextInfo.getObjectId());
            jSONObject.put("type", str);
            jSONObject.put("event", ReflectionHelper.toSerializableObject(obj));
            jsContextInfo.postMessage(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dispatchEvent(String str, Object obj) {
        dispatchEvent(getJsContextInfo(), str, obj);
    }

    public static void sendEvent(JsContextInfo jsContextInfo, String str, Object obj) {
        jsContextInfo.getExtensionClient().sendEvent(str, obj);
    }

    public void sendEvent(String str, Object obj) {
        sendEvent(getJsContextInfo(), str, obj);
    }

    public static void updateProperty(JsContextInfo jsContextInfo, String str) {
        ReflectionHelper targetReflect = jsContextInfo.getTargetReflect();
        if (!targetReflect.hasProperty(str).booleanValue()) {
            Log.w(jsContextInfo.getTag(), "Unexposed property in extension: " + str);
            return;
        }
        boolean z = targetReflect.getMemberInfo(str).isStatic;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cmd", "updateProperty");
            jSONObject.put("objectId", z ? FileUtils.DEFAULT_VERSION : jsContextInfo.getObjectId());
            jSONObject.put("constructorName", jsContextInfo.getConstructorName());
            jSONObject.put("name", str);
            jsContextInfo.postMessage(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProperty(String str) {
        updateProperty(getJsContextInfo(), str);
    }
}
