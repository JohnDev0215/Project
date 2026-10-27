package com.android.bluetooth.pbapclient;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import javax.obex.ClientOperation;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
abstract class BluetoothPbapRequest {
    private static final boolean DBG = false;
    protected static final byte OAP_TAGID_FILTER = 6;
    protected static final byte OAP_TAGID_FORMAT = 7;
    protected static final byte OAP_TAGID_LIST_START_OFFSET = 5;
    protected static final byte OAP_TAGID_MAX_LIST_COUNT = 4;
    protected static final byte OAP_TAGID_NEW_MISSED_CALLS = 9;
    protected static final byte OAP_TAGID_ORDER = 1;
    protected static final byte OAP_TAGID_PBAP_SUPPORTED_FEATURES = 16;
    protected static final byte OAP_TAGID_PHONEBOOK_SIZE = 8;
    protected static final byte OAP_TAGID_SEARCH_ATTRIBUTE = 3;
    protected static final byte OAP_TAGID_SEARCH_VALUE = 2;
    private static final String TAG = "BluetoothPbapRequest";
    protected int mResponseCode;
    private boolean mAborted = false;
    private ClientOperation mOp = null;
    protected HeaderSet mHeaderSet = new HeaderSet();

    BluetoothPbapRequest() {
    }

    public final boolean isSuccess() {
        return this.mResponseCode == 160;
    }

    public void execute(ClientSession session) throws IOException {
        if (this.mAborted) {
            this.mResponseCode = 208;
            return;
        }
        try {
            ClientOperation clientOperation = session.get(this.mHeaderSet);
            this.mOp = clientOperation;
            clientOperation.setGetFinalFlag(true);
            this.mOp.continueOperation(true, false);
            readResponseHeaders(this.mOp.getReceivedHeader());
            InputStream is = this.mOp.openInputStream();
            readResponse(is);
            is.close();
            this.mOp.close();
            int responseCode = this.mOp.getResponseCode();
            this.mResponseCode = responseCode;
            checkResponseCode(responseCode);
        } catch (IOException e) {
            Log.e(TAG, "IOException occured when processing request", e);
            this.mResponseCode = 208;
            throw e;
        }
    }

    public void abort() {
        this.mAborted = true;
        ClientOperation clientOperation = this.mOp;
        if (clientOperation != null) {
            try {
                clientOperation.abort();
            } catch (IOException e) {
                Log.e(TAG, "Exception occured when trying to abort", e);
            }
        }
    }

    protected void readResponse(InputStream stream) throws IOException {
    }

    protected void readResponseHeaders(HeaderSet headerset) {
    }

    protected void checkResponseCode(int responseCode) throws IOException {
    }
}
