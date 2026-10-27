package defpackage;

import J.N;
import android.media.Image;
import android.media.ImageReader;
import org.chromium.media.ScreenCapture;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HH implements ImageReader.OnImageAvailableListener {
    public final /* synthetic */ ScreenCapture a;

    public HH(ScreenCapture screenCapture, GH gh) {
        this.a = screenCapture;
    }

    @Override // android.media.ImageReader.OnImageAvailableListener
    public void onImageAvailable(ImageReader imageReader) {
        synchronized (this.a.t) {
            if (this.a.u != 2) {
                Ts.a("ScreenCapture", "Get captured frame in unexpected state.", new Object[0]);
                return;
            }
            if (this.a.e()) {
                this.a.c();
                this.a.d();
                return;
            }
            try {
                Image imageAcquireLatestImage = imageReader.acquireLatestImage();
                if (imageAcquireLatestImage == null) {
                    if (imageAcquireLatestImage != null) {
                        imageAcquireLatestImage.close();
                        return;
                    }
                    return;
                }
                try {
                    if (imageReader.getWidth() != imageAcquireLatestImage.getWidth() || imageReader.getHeight() != imageAcquireLatestImage.getHeight()) {
                        Ts.a("ScreenCapture", "ImageReader size (" + imageReader.getWidth() + "x" + imageReader.getHeight() + ") did not match Image size (" + imageAcquireLatestImage.getWidth() + "x" + imageAcquireLatestImage.getHeight() + ")", new Object[0]);
                        throw new IllegalStateException();
                    }
                    int format = imageAcquireLatestImage.getFormat();
                    if (format != 1) {
                        if (format != 35) {
                            Ts.a("ScreenCapture", "Unexpected image format: " + imageAcquireLatestImage.getFormat(), new Object[0]);
                            throw new IllegalStateException();
                        }
                        if (imageAcquireLatestImage.getPlanes().length != 3) {
                            Ts.a("ScreenCapture", "Unexpected image planes for YUV_420_888 format: " + imageAcquireLatestImage.getPlanes().length, new Object[0]);
                            throw new IllegalStateException();
                        }
                        N.MgLL$kUF(this.a.s, this.a, imageAcquireLatestImage.getPlanes()[0].getBuffer(), imageAcquireLatestImage.getPlanes()[0].getRowStride(), imageAcquireLatestImage.getPlanes()[1].getBuffer(), imageAcquireLatestImage.getPlanes()[2].getBuffer(), imageAcquireLatestImage.getPlanes()[1].getRowStride(), imageAcquireLatestImage.getPlanes()[1].getPixelStride(), imageAcquireLatestImage.getCropRect().left, imageAcquireLatestImage.getCropRect().top, imageAcquireLatestImage.getCropRect().width(), imageAcquireLatestImage.getCropRect().height(), imageAcquireLatestImage.getTimestamp());
                    } else {
                        if (imageAcquireLatestImage.getPlanes().length != 1) {
                            Ts.a("ScreenCapture", "Unexpected image planes for RGBA_8888 format: " + imageAcquireLatestImage.getPlanes().length, new Object[0]);
                            throw new IllegalStateException();
                        }
                        N.MjDBp6op(this.a.s, this.a, imageAcquireLatestImage.getPlanes()[0].getBuffer(), imageAcquireLatestImage.getPlanes()[0].getRowStride(), imageAcquireLatestImage.getCropRect().left, imageAcquireLatestImage.getCropRect().top, imageAcquireLatestImage.getCropRect().width(), imageAcquireLatestImage.getCropRect().height(), imageAcquireLatestImage.getTimestamp());
                    }
                    imageAcquireLatestImage.close();
                } finally {
                }
            } catch (IllegalStateException e) {
                Ts.a("ScreenCapture", "acquireLatestImage():" + e, new Object[0]);
            } catch (UnsupportedOperationException e2) {
                Ts.d("ScreenCapture", "acquireLatestImage():" + e2, new Object[0]);
                ScreenCapture screenCapture = this.a;
                if (screenCapture.I == 35) {
                    screenCapture.I = 1;
                    screenCapture.c();
                    this.a.d();
                }
            }
        }
    }
}
