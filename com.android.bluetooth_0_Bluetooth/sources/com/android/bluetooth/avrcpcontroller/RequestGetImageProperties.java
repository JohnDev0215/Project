package com.android.bluetooth.avrcpcontroller;

import java.io.IOException;
import java.io.InputStream;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
public class RequestGetImageProperties extends BipRequest {
    private static final String TYPE = "x-bt/img-properties";
    private String mImageHandle;
    private BipImageProperties mImageProperties = null;

    @Override // com.android.bluetooth.avrcpcontroller.BipRequest
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public RequestGetImageProperties(String imageHandle) {
        this.mImageHandle = null;
        this.mHeaderSet = new HeaderSet();
        this.mResponseCode = -1;
        this.mImageHandle = imageHandle;
        debug("GetImageProperties - handle: " + this.mImageHandle);
        this.mHeaderSet.setHeader(66, TYPE);
        this.mHeaderSet.setHeader(48, this.mImageHandle);
    }

    @Override // com.android.bluetooth.avrcpcontroller.BipRequest
    public int getType() {
        return 0;
    }

    @Override // com.android.bluetooth.avrcpcontroller.BipRequest
    public void execute(ClientSession session) throws IOException {
        executeGet(session);
    }

    @Override // com.android.bluetooth.avrcpcontroller.BipRequest
    protected void readResponse(InputStream stream) throws IOException {
        try {
            this.mImageProperties = new BipImageProperties(stream);
            debug("Response GetImageProperties - handle: " + this.mImageHandle + ", properties: " + this.mImageProperties.toString());
        } catch (ParseException e) {
            error("Failed to parse incoming properties object");
            this.mImageProperties = null;
        }
    }

    public String getImageHandle() {
        return this.mImageHandle;
    }

    public BipImageProperties getImageProperties() {
        return this.mImageProperties;
    }
}
