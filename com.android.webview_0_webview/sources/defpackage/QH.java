package defpackage;

import J.N;
import android.media.Image;
import android.media.ImageReader;
import java.nio.ByteBuffer;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class QH implements ImageReader.OnImageAvailableListener {
    public final long a;
    public final /* synthetic */ C0542bI b;

    public QH(C0542bI c0542bI, long j) {
        this.b = c0542bI;
        this.a = j;
    }

    @Override // android.media.ImageReader.OnImageAvailableListener
    public void onImageAvailable(ImageReader imageReader) {
        TraceEvent.I("VideoCaptureCamera2.java", "CrPhotoReaderListener.onImageAvailable");
        try {
            Image imageAcquireLatestImage = imageReader.acquireLatestImage();
            try {
                if (imageAcquireLatestImage == null) {
                    throw new IllegalStateException();
                }
                if (imageAcquireLatestImage.getFormat() != 256) {
                    Ts.a("VideoCapture", "Unexpected image format: %d", Integer.valueOf(imageAcquireLatestImage.getFormat()));
                    throw new IllegalStateException();
                }
                byte[] bArrArray = null;
                try {
                    try {
                        bArrArray = imageAcquireLatestImage.getPlanes()[0].getBuffer().array();
                    } catch (Throwable unused) {
                    }
                } catch (UnsupportedOperationException unused2) {
                    ByteBuffer buffer = imageAcquireLatestImage.getPlanes()[0].getBuffer();
                    bArrArray = new byte[buffer.remaining()];
                    buffer.get(bArrArray);
                }
                N.MdZBZ$ST(this.b.e, this.b, this.a, bArrArray);
                imageAcquireLatestImage.close();
                C0542bI.e(this.b, 73);
            } finally {
            }
        } catch (IllegalStateException unused3) {
            this.b.d(this.a);
        }
    }
}
