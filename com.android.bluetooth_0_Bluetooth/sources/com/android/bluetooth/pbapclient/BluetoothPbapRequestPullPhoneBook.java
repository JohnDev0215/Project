package com.android.bluetooth.pbapclient;

import android.accounts.Account;
import com.android.vcard.VCardEntry;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
final class BluetoothPbapRequestPullPhoneBook extends BluetoothPbapRequest {
    private static final String TAG = "BtPbapReqPullPhoneBook";
    private static final String TYPE = "x-bt/phonebook";
    private static final boolean VDBG = false;
    private Account mAccount;
    private final byte mFormat;
    private int mNewMissedCalls = -1;
    private BluetoothPbapVcardList mResponse;

    BluetoothPbapRequestPullPhoneBook(String pbName, Account account, long filter, byte format, int maxListCount, int listStartOffset) {
        this.mAccount = account;
        if (maxListCount < 0 || maxListCount > 65535) {
            throw new IllegalArgumentException("maxListCount should be [0..65535]");
        }
        if (listStartOffset < 0 || listStartOffset > 65535) {
            throw new IllegalArgumentException("listStartOffset should be [0..65535]");
        }
        this.mHeaderSet.setHeader(1, pbName);
        this.mHeaderSet.setHeader(66, TYPE);
        ObexAppParameters oap = new ObexAppParameters();
        if (format != 0 && format != 1) {
            format = 0;
        }
        if (filter != 0) {
            oap.add((byte) 6, filter);
        }
        oap.add((byte) 7, format);
        if (maxListCount > 0) {
            oap.add((byte) 4, (short) maxListCount);
        } else {
            oap.add((byte) 4, (short) -1);
        }
        if (listStartOffset > 0) {
            oap.add((byte) 5, (short) listStartOffset);
        }
        oap.addToHeaderSet(this.mHeaderSet);
        this.mFormat = format;
    }

    @Override // com.android.bluetooth.pbapclient.BluetoothPbapRequest
    protected void readResponse(InputStream stream) throws IOException {
        this.mResponse = new BluetoothPbapVcardList(this.mAccount, stream, this.mFormat);
    }

    @Override // com.android.bluetooth.pbapclient.BluetoothPbapRequest
    protected void readResponseHeaders(HeaderSet headerset) {
        ObexAppParameters oap = ObexAppParameters.fromHeaderSet(headerset);
        if (oap.exists((byte) 9)) {
            this.mNewMissedCalls = oap.getByte((byte) 9);
        }
    }

    public ArrayList<VCardEntry> getList() {
        return this.mResponse.getList();
    }

    public int getNewMissedCalls() {
        return this.mNewMissedCalls;
    }
}
