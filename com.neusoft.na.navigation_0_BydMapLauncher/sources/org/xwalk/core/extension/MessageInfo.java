package org.xwalk.core.extension;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class MessageInfo {
    private String TAG;
    private JSONArray mArgs;
    private ByteBuffer mBinaryArgs;
    private String mCallbackId;
    private String mCmd;
    private XWalkExternalExtension mExtension;
    private int mInstanceId;
    private String mJsName;
    private String mObjectId;

    private int AlignedWith4Bytes(int i) {
        return i + (4 - (i % 4));
    }

    public MessageInfo(MessageInfo messageInfo) {
        this.TAG = "MessageInfo";
        this.mExtension = messageInfo.mExtension;
        this.mInstanceId = messageInfo.mInstanceId;
        this.mJsName = messageInfo.mJsName;
        this.mCallbackId = messageInfo.mCallbackId;
        this.mObjectId = messageInfo.mObjectId;
        this.mArgs = messageInfo.mArgs;
        this.mCmd = messageInfo.mCmd;
    }

    public MessageInfo(XWalkExternalExtension xWalkExternalExtension, int i, String str) {
        this.TAG = "MessageInfo";
        this.mExtension = xWalkExternalExtension;
        this.mInstanceId = i;
        if (str.trim().charAt(0) == '[') {
            try {
                JSONArray jSONArray = new JSONArray(str);
                this.mArgs = jSONArray;
                this.mCmd = "invokeNative";
                this.mJsName = jSONArray.getString(0);
                this.mCallbackId = this.mArgs.getString(1);
                this.mObjectId = this.mArgs.getString(2);
                this.mArgs.remove(0);
                this.mArgs.remove(0);
                this.mArgs.remove(0);
                return;
            } catch (JSONException e) {
                Log.e(this.TAG, e.toString());
                return;
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("cmd");
            int i2 = jSONObject.getInt("objectId");
            this.mCmd = string;
            this.mObjectId = Integer.toString(i2);
            this.mCallbackId = Integer.toString(0);
            String string2 = jSONObject.getString("type");
            this.mArgs = new JSONArray();
            if (string2.equals(JsStubGenerator.MSG_TO_EXTENSION)) {
                this.mArgs = jSONObject.getJSONArray("args");
                this.mJsName = jSONObject.getString("name");
                if (this.mCmd.equals("newInstance")) {
                    this.mObjectId = this.mArgs.getString(0);
                    this.mArgs = this.mArgs.getJSONArray(1);
                }
            } else {
                this.mJsName = string2;
                this.mArgs.put(0, jSONObject.getString("name"));
                this.mArgs.put(1, jSONObject.getJSONArray("args"));
            }
        } catch (JSONException e2) {
            Log.e(this.TAG, e2.toString());
        }
    }

    public MessageInfo(XWalkExternalExtension xWalkExternalExtension, int i, byte[] bArr) {
        this.TAG = "MessageInfo";
        this.mExtension = xWalkExternalExtension;
        this.mInstanceId = i;
        this.mCmd = "invokeNative";
        try {
            this.mArgs = null;
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            if (byteBufferWrap.order() != ByteOrder.LITTLE_ENDIAN) {
                byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
            }
            int iPosition = byteBufferWrap.position();
            int i2 = byteBufferWrap.getInt(iPosition);
            int iAlignedWith4Bytes = AlignedWith4Bytes(i2);
            int i3 = iPosition + 4;
            this.mJsName = new String(bArr, i3, i2);
            int i4 = i3 + iAlignedWith4Bytes;
            this.mCallbackId = Integer.toString(byteBufferWrap.getInt(i4));
            int i5 = i4 + 4;
            int i6 = byteBufferWrap.getInt(i5);
            int iAlignedWith4Bytes2 = AlignedWith4Bytes(i6);
            int i7 = i5 + 4;
            this.mObjectId = new String(bArr, i7, i6);
            int i8 = i7 + iAlignedWith4Bytes2;
            this.mBinaryArgs = ByteBuffer.wrap(bArr, i8, bArr.length - i8);
        } catch (IndexOutOfBoundsException e) {
            Log.e(this.TAG, e.toString());
        } catch (NullPointerException e2) {
            Log.e(this.TAG, e2.toString());
        }
    }

    public String getJsName() {
        return this.mJsName;
    }

    public void setJsName(String str) {
        this.mJsName = str;
    }

    public JSONArray getArgs() {
        return this.mArgs;
    }

    public void setArgs(JSONArray jSONArray) {
        this.mArgs = jSONArray;
    }

    public ByteBuffer getBinaryArgs() {
        return this.mBinaryArgs;
    }

    public void setBinaryArgs(ByteBuffer byteBuffer) {
        this.mBinaryArgs = byteBuffer;
    }

    public String getObjectId() {
        return this.mObjectId;
    }

    public void setObjectId(String str) {
        this.mObjectId = str;
    }

    public String getCallbackId() {
        return this.mCallbackId;
    }

    public void setCallbackId(String str) {
        this.mCallbackId = str;
    }

    public String getCmd() {
        return this.mCmd;
    }

    public void postResult(JSONArray jSONArray) {
        try {
            JSONArray jSONArray2 = new JSONArray();
            int i = 0;
            jSONArray2.put(0, this.mCallbackId);
            while (i < jSONArray.length()) {
                int i2 = i + 1;
                jSONArray2.put(i2, jSONArray.get(i));
                i = i2;
            }
            Log.w(this.TAG, "postResult: " + jSONArray2.toString());
            this.mExtension.postMessage(this.mInstanceId, jSONArray2.toString());
        } catch (JSONException e) {
            Log.e(this.TAG, e.toString());
        }
    }

    public XWalkExternalExtension getExtension() {
        return this.mExtension;
    }

    public int getInstanceId() {
        return this.mInstanceId;
    }

    public ExtensionInstanceHelper getInstanceHelper() {
        return this.mExtension.getInstanceHelper(this.mInstanceId);
    }

    public void postResult(byte[] bArr) {
        this.mExtension.postBinaryMessage(this.mInstanceId, bArr);
    }
}
