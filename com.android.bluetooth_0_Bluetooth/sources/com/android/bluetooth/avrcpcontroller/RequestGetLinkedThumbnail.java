package com.android.bluetooth.avrcpcontroller;

import java.io.IOException;
import java.io.InputStream;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
public class RequestGetLinkedThumbnail extends BipRequest {
    private static final String TYPE = "x-bt/img-thm";
    private BipImage mImage = null;
    private final String mImageHandle;

    @Override // com.android.bluetooth.avrcpcontroller.BipRequest
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public RequestGetLinkedThumbnail(String imageHandle) {
        this.mHeaderSet = new HeaderSet();
        this.mResponseCode = -1;
        this.mImageHandle = imageHandle;
        debug("GetLinkedThumbnail - handle: " + this.mImageHandle);
        this.mHeaderSet.setHeader(66, TYPE);
        this.mHeaderSet.setHeader(48, this.mImageHandle);
    }

    @Override // com.android.bluetooth.avrcpcontroller.BipRequest
    public int getType() {
        return 2;
    }

    @Override // com.android.bluetooth.avrcpcontroller.BipRequest
    public void execute(ClientSession session) throws IOException {
        executeGet(session);
    }

    @Override // com.android.bluetooth.avrcpcontroller.BipRequest
    protected void readResponse(InputStream stream) throws IOException {
        this.mImage = new BipImage(this.mImageHandle, stream);
        debug("Response GetLinkedThumbnail - handle:" + this.mImageHandle + ", image: " + this.mImage);
    }

    public String getImageHandle() {
        return this.mImageHandle;
    }

    public BipImage getImage() {
        return this.mImage;
    }
}
