package com.android.bluetooth.pbapclient;

import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
final class BluetoothPbapRequestPullPhoneBookSize extends BluetoothPbapRequest {
    private static final String TAG = "BtPbapReqPullPhoneBookSize";
    private static final String TYPE = "x-bt/phonebook";
    private static final boolean VDBG = false;
    private int mSize;

    BluetoothPbapRequestPullPhoneBookSize(String pbName, long filter) {
        this.mHeaderSet.setHeader(1, pbName);
        this.mHeaderSet.setHeader(66, TYPE);
        ObexAppParameters oap = new ObexAppParameters();
        oap.add((byte) 4, (short) 0);
        if (filter != 0) {
            oap.add((byte) 6, filter);
        }
        oap.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.android.bluetooth.pbapclient.BluetoothPbapRequest
    protected void readResponseHeaders(HeaderSet headerset) {
        ObexAppParameters oap = ObexAppParameters.fromHeaderSet(headerset);
        if (oap.exists((byte) 8)) {
            this.mSize = oap.getShort((byte) 8);
        }
    }

    public int getSize() {
        return this.mSize;
    }
}
