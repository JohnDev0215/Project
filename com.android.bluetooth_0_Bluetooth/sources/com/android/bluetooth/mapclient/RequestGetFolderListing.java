package com.android.bluetooth.mapclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.obex.ClientSession;

/* JADX INFO: loaded from: classes.dex */
final class RequestGetFolderListing extends Request {
    private static final String TYPE = "x-obex/folder-listing";
    private FolderListing mResponse = null;

    RequestGetFolderListing(int maxListCount, int listStartOffset) {
        if (maxListCount < 0 || maxListCount > 65535) {
            throw new IllegalArgumentException("maxListCount should be [0..65535]");
        }
        if (listStartOffset < 0 || listStartOffset > 65535) {
            throw new IllegalArgumentException("listStartOffset should be [0..65535]");
        }
        this.mHeaderSet.setHeader(66, TYPE);
        ObexAppParameters oap = new ObexAppParameters();
        if (maxListCount >= 0) {
            oap.add((byte) 1, (short) maxListCount);
        }
        if (listStartOffset > 0) {
            oap.add((byte) 2, (short) listStartOffset);
        }
        oap.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.android.bluetooth.mapclient.Request
    protected void readResponse(InputStream stream) {
        this.mResponse = new FolderListing(stream);
    }

    public ArrayList<String> getList() {
        FolderListing folderListing = this.mResponse;
        if (folderListing == null) {
            return null;
        }
        return folderListing.getList();
    }

    @Override // com.android.bluetooth.mapclient.Request
    public void execute(ClientSession session) throws IOException {
        executeGet(session);
    }
}
