package com.android.bluetooth.avrcpcontroller;

import android.util.Log;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.obex.ClientOperation;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
abstract class BipRequest {
    protected static final byte HEADER_ID_IMG_DESCRIPTOR = 113;
    protected static final byte HEADER_ID_IMG_HANDLE = 48;
    public static final int TYPE_GET_IMAGE = 1;
    public static final int TYPE_GET_IMAGE_PROPERTIES = 0;
    public static final int TYPE_GET_LINKED_THUMBNAIL = 2;
    private static final String TAG = "avrcpcontroller.BipRequest";
    private static final boolean DBG = Log.isLoggable(TAG, 3);
    protected ClientOperation mOperation = null;
    protected HeaderSet mHeaderSet = new HeaderSet();
    protected int mResponseCode = -1;

    public abstract void execute(ClientSession clientSession) throws IOException;

    public abstract int getType();

    BipRequest() {
    }

    protected void executeGet(ClientSession session) throws IOException {
        debug("Exeucting GET");
        setOperation(null);
        try {
            ClientOperation operation = (ClientOperation) session.get(this.mHeaderSet);
            setOperation(operation);
            operation.setGetFinalFlag(true);
            operation.continueOperation(true, false);
            readResponseHeaders(operation.getReceivedHeader());
            InputStream inputStream = operation.openInputStream();
            readResponse(inputStream);
            inputStream.close();
            operation.close();
            this.mResponseCode = operation.getResponseCode();
            debug("GET final response code is '" + this.mResponseCode + "'");
        } catch (IOException e) {
            this.mResponseCode = 208;
            error("GET threw an exeception: " + e);
            throw e;
        }
    }

    protected void executePut(ClientSession session, byte[] body) throws IOException {
        debug("Exeucting PUT");
        setOperation(null);
        this.mHeaderSet.setHeader(195, Long.valueOf(body.length));
        try {
            ClientOperation operation = (ClientOperation) session.put(this.mHeaderSet);
            setOperation(operation);
            DataOutputStream outputStream = this.mOperation.openDataOutputStream();
            outputStream.write(body);
            outputStream.close();
            readResponseHeaders(operation.getReceivedHeader());
            operation.close();
            this.mResponseCode = operation.getResponseCode();
            debug("PUT final response code is '" + this.mResponseCode + "'");
        } catch (IOException e) {
            this.mResponseCode = 208;
            error("PUT threw an exeception: " + e);
            throw e;
        }
    }

    public final boolean isSuccess() {
        return this.mResponseCode == 160;
    }

    public final int getResponseCode() {
        return this.mResponseCode;
    }

    protected void readResponseHeaders(HeaderSet headerset) {
    }

    protected void readResponse(InputStream stream) throws IOException {
    }

    private synchronized ClientOperation getOperation() {
        return this.mOperation;
    }

    private synchronized void setOperation(ClientOperation operation) {
        this.mOperation = operation;
    }

    public String toString() {
        return "avrcpcontroller.BipRequest (type: " + getType() + ", mResponseCode: " + this.mResponseCode + ")";
    }

    protected void debug(String msg) {
        if (DBG) {
            Log.d(TAG, msg);
        }
    }

    protected void warn(String msg) {
        Log.w(TAG, msg);
    }

    protected void error(String msg) {
        Log.e(TAG, msg);
    }
}
