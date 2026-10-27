package com.android.bluetooth.mapclient;

import android.net.INetd;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
final class RequestGetMessagesListing extends Request {
    private static final String TYPE = "x-bt/MAP-msg-listing";
    private MessagesListing mResponse = null;
    private boolean mNewMessage = false;
    private Date mServerTime = null;

    RequestGetMessagesListing(String folderName, int parameters, MessagesFilter filter, int subjectLength, int maxListCount, int listStartOffset) {
        if (subjectLength < 0 || subjectLength > 255) {
            throw new IllegalArgumentException("subjectLength should be [0..255]");
        }
        if (maxListCount < 0 || maxListCount > 65535) {
            throw new IllegalArgumentException("maxListCount should be [0..65535]");
        }
        if (listStartOffset < 0 || listStartOffset > 65535) {
            throw new IllegalArgumentException("listStartOffset should be [0..65535]");
        }
        this.mHeaderSet.setHeader(66, TYPE);
        if (folderName == null) {
            this.mHeaderSet.setHeader(1, INetd.NEXTHOP_NONE);
        } else {
            this.mHeaderSet.setHeader(1, folderName);
        }
        ObexAppParameters oap = new ObexAppParameters();
        if (filter != null) {
            if (filter.messageType != 0) {
                oap.add((byte) 3, filter.messageType);
            }
            if (filter.periodBegin != null) {
                oap.add((byte) 4, filter.periodBegin);
            }
            if (filter.periodEnd != null) {
                oap.add((byte) 5, filter.periodEnd);
            }
            if (filter.readStatus != 0) {
                oap.add((byte) 6, filter.readStatus);
            }
            if (filter.recipient != null) {
                oap.add((byte) 7, filter.recipient);
            }
            if (filter.originator != null) {
                oap.add((byte) 8, filter.originator);
            }
            if (filter.priority != 0) {
                oap.add((byte) 9, filter.priority);
            }
        }
        if (subjectLength != 0) {
            oap.add((byte) 19, (byte) subjectLength);
        }
        if (parameters > 0) {
            oap.add(Ascii.DLE, parameters);
        }
        if (maxListCount >= 0) {
            oap.add((byte) 1, (short) maxListCount);
        }
        if (listStartOffset != 0) {
            oap.add((byte) 2, (short) listStartOffset);
        }
        oap.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.android.bluetooth.mapclient.Request
    protected void readResponse(InputStream stream) {
        this.mResponse = new MessagesListing(stream);
    }

    @Override // com.android.bluetooth.mapclient.Request
    protected void readResponseHeaders(HeaderSet headerset) {
        String mseTime;
        ObexAppParameters oap = ObexAppParameters.fromHeaderSet(headerset);
        this.mNewMessage = (oap.getByte(Ascii.CR) & 1) == 1;
        if (oap.exists(Ascii.EM) && (mseTime = oap.getString(Ascii.EM)) != null) {
            this.mServerTime = new ObexTime(mseTime).getTime();
        }
    }

    public ArrayList<Message> getList() {
        MessagesListing messagesListing = this.mResponse;
        if (messagesListing == null) {
            return null;
        }
        return messagesListing.getList();
    }

    public boolean getNewMessageStatus() {
        return this.mNewMessage;
    }

    public Date getMseTime() {
        return this.mServerTime;
    }

    @Override // com.android.bluetooth.mapclient.Request
    public void execute(ClientSession session) throws IOException {
        executeGet(session);
    }
}
