package defpackage;

import J.N;
import android.media.Image;
import android.media.ImageReader;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SH implements ImageReader.OnImageAvailableListener {
    public final /* synthetic */ C0542bI a;

    public SH(C0542bI c0542bI, PH ph) {
        this.a = c0542bI;
    }

    @Override // android.media.ImageReader.OnImageAvailableListener
    public void onImageAvailable(ImageReader imageReader) {
        try {
            Image imageAcquireLatestImage = imageReader.acquireLatestImage();
            try {
                if (imageAcquireLatestImage == null) {
                    N.M651cEC1(this.a.e, this.a, 9);
                    if (imageAcquireLatestImage != null) {
                        imageAcquireLatestImage.close();
                        return;
                    }
                    return;
                }
                if (imageAcquireLatestImage.getFormat() != 35 || imageAcquireLatestImage.getPlanes().length != 3) {
                    N.MhmwjISE(this.a.e, this.a, 71, "Unexpected image format: " + imageAcquireLatestImage.getFormat() + " or #planes: " + imageAcquireLatestImage.getPlanes().length);
                    throw new IllegalStateException();
                }
                if (imageReader.getWidth() == imageAcquireLatestImage.getWidth() && imageReader.getHeight() == imageAcquireLatestImage.getHeight()) {
                    N.MlTacwJQ(this.a.e, this.a, imageAcquireLatestImage.getPlanes()[0].getBuffer(), imageAcquireLatestImage.getPlanes()[0].getRowStride(), imageAcquireLatestImage.getPlanes()[1].getBuffer(), imageAcquireLatestImage.getPlanes()[2].getBuffer(), imageAcquireLatestImage.getPlanes()[1].getRowStride(), imageAcquireLatestImage.getPlanes()[1].getPixelStride(), imageAcquireLatestImage.getWidth(), imageAcquireLatestImage.getHeight(), this.a.a(), imageAcquireLatestImage.getTimestamp());
                    imageAcquireLatestImage.close();
                    return;
                }
                N.MhmwjISE(this.a.e, this.a, 72, "ImageReader size (" + imageReader.getWidth() + "x" + imageReader.getHeight() + ") did not match Image size (" + imageAcquireLatestImage.getWidth() + "x" + imageAcquireLatestImage.getHeight() + ")");
                throw new IllegalStateException();
            } finally {
            }
        } catch (IllegalStateException e) {
            Ts.a("VideoCapture", "acquireLatestImage():", e);
        }
    }
}
