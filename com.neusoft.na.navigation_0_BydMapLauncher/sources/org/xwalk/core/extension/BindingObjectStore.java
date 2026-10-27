package org.xwalk.core.extension;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes4.dex */
public class BindingObjectStore {
    private String TAG = "BindingObjectStore";
    private Map<String, BindingObject> mBindingObjects = new HashMap();
    private ExtensionInstanceHelper mInstance;

    public BindingObjectStore(MessageHandler messageHandler, ExtensionInstanceHelper extensionInstanceHelper) {
        this.mInstance = extensionInstanceHelper;
        messageHandler.register("JSObjectCollected", "onJSObjectCollected", this);
        messageHandler.register(JsStubGenerator.MSG_TO_OBJECT, "onPostMessageToObject", this);
        messageHandler.register(JsStubGenerator.MSG_TO_CLASS, "onPostMessageToClass", this);
    }

    public boolean addBindingObject(String str, BindingObject bindingObject) {
        if (this.mBindingObjects.containsKey(str)) {
            Log.w(this.TAG, "Existing binding object:\n" + str);
            return false;
        }
        bindingObject.initBindingInfo(str, this.mInstance);
        this.mBindingObjects.put(str, bindingObject);
        bindingObject.onJsBound();
        return true;
    }

    public BindingObject getBindingObject(String str) {
        return this.mBindingObjects.get(str);
    }

    public BindingObject removeBindingObject(String str) {
        BindingObject bindingObjectRemove = this.mBindingObjects.remove(str);
        if (bindingObjectRemove != null) {
            bindingObjectRemove.onJsDestroyed();
        }
        return bindingObjectRemove;
    }

    public void onJSObjectCollected(MessageInfo messageInfo) {
        removeBindingObject(messageInfo.getObjectId());
    }

    public Object onPostMessageToClass(MessageInfo messageInfo) {
        JSONArray args = messageInfo.getArgs();
        try {
            MessageInfo messageInfo2 = new MessageInfo(messageInfo);
            String string = args.getString(0);
            JSONArray jSONArray = args.getJSONArray(1);
            String string2 = jSONArray.getString(0);
            JSONArray jSONArray2 = jSONArray.getJSONArray(1);
            messageInfo2.setJsName(string);
            messageInfo2.setArgs(jSONArray2);
            return messageInfo.getExtension().getTargetReflect(string2).handleMessage(messageInfo2, null);
        } catch (JSONException e) {
            Log.e(this.TAG, e.toString());
            return null;
        } catch (Exception e2) {
            Log.e(this.TAG, e2.toString());
            return null;
        }
    }

    public Object onPostMessageToObject(MessageInfo messageInfo) {
        try {
            BindingObject bindingObject = getBindingObject(messageInfo.getObjectId());
            MessageInfo messageInfo2 = new MessageInfo(messageInfo);
            if (messageInfo.getArgs() != null) {
                JSONArray args = messageInfo.getArgs();
                String string = args.getString(0);
                JSONArray jSONArray = args.getJSONArray(1);
                messageInfo2.setJsName(string);
                messageInfo2.setArgs(jSONArray);
            } else {
                ByteBuffer binaryArgs = messageInfo.getBinaryArgs();
                binaryArgs.order(ByteOrder.LITTLE_ENDIAN);
                int iPosition = binaryArgs.position();
                int i = binaryArgs.getInt(iPosition);
                int i2 = iPosition + 4;
                String str = new String(binaryArgs.array(), i2, i);
                int i3 = i2 + (4 - (i % 4)) + i;
                ByteBuffer byteBufferWrap = ByteBuffer.wrap(binaryArgs.array(), i3, binaryArgs.array().length - i3);
                messageInfo2.setJsName(str);
                messageInfo2.setBinaryArgs(byteBufferWrap);
            }
            if (bindingObject != null) {
                return bindingObject.handleMessage(messageInfo2);
            }
            return null;
        } catch (IndexOutOfBoundsException | NullPointerException | JSONException e) {
            Log.e(this.TAG, e.toString());
            return null;
        }
    }

    public void onStart() {
        Iterator<Map.Entry<String, BindingObject>> it = this.mBindingObjects.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().onStart();
        }
    }

    public void onResume() {
        Iterator<Map.Entry<String, BindingObject>> it = this.mBindingObjects.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().onResume();
        }
    }

    public void onPause() {
        Iterator<Map.Entry<String, BindingObject>> it = this.mBindingObjects.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().onPause();
        }
    }

    public void onStop() {
        Iterator<Map.Entry<String, BindingObject>> it = this.mBindingObjects.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().onStop();
        }
    }

    public void onDestroy() {
        Iterator<Map.Entry<String, BindingObject>> it = this.mBindingObjects.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().onDestroy();
        }
    }
}
