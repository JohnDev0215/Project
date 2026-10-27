package com.android.bluetooth.avrcpcontroller;

import java.io.IOException;
import java.io.InputStream;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;

/* JADX INFO: loaded from: classes.dex */
public class RequestGetImage extends BipRequest {
    private static final String TYPE = "x-bt/img-img";
    private BipImage mImage = null;
    private final BipImageDescriptor mImageDescriptor;
    private final String mImageHandle;

    @Override // com.android.bluetooth.avrcpcontroller.BipRequest
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public RequestGetImage(String imageHandle, BipImageDescriptor descriptor) {
        this.mHeaderSet = new HeaderSet();
        this.mResponseCode = -1;
        this.mImageHandle = imageHandle;
        this.mImageDescriptor = descriptor;
        debug("GetImage - handle: " + this.mImageHandle + ", descriptor: " + this.mImageDescriptor);
        this.mHeaderSet.setHeader(66, TYPE);
        this.mHeaderSet.setHeader(48, this.mImageHandle);
        if (this.mImageDescriptor == null) {
            this.mHeaderSet.setHeader(113, (Object) null);
        } else {
            this.mHeaderSet.setHeader(113, this.mImageDescriptor.serialize());
        }
    }

    @Override // com.android.bluetooth.avrcpcontroller.BipRequest
    public int getType() {
        return 1;
    }

    @Override // com.android.bluetooth.avrcpcontroller.BipRequest
    public void execute(ClientSession session) throws IOException {
        executeGet(session);
    }

    @Override // com.android.bluetooth.avrcpcontroller.BipRequest
    protected void readResponse(InputStream stream) throws IOException {
        this.mImage = new BipImage(this.mImageHandle, stream);
        debug("Response GetImage - handle:" + this.mImageHandle + ", image: " + this.mImage);
    }

    public String getImageHandle() {
        return this.mImageHandle;
    }

    public BipImage getImage() {
        return this.mImage;
    }
}
