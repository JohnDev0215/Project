package defpackage;

import J.N;
import android.hardware.Camera;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class OH implements Camera.PictureCallback {
    public final /* synthetic */ C0598cI a;

    public OH(C0598cI c0598cI, LH lh) {
        this.a = c0598cI;
    }

    @Override // android.hardware.Camera.PictureCallback
    public void onPictureTaken(byte[] bArr, Camera camera) {
        try {
            this.a.l.flatten();
            camera.setParameters(this.a.l);
        } catch (RuntimeException e) {
            Ts.a("VideoCapture", "onPictureTaken, setParameters() " + e, new Object[0]);
        }
        try {
            camera.startPreview();
        } catch (RuntimeException e2) {
            Ts.a("VideoCapture", "onPictureTaken, startPreview() " + e2, new Object[0]);
        }
        synchronized (this.a.g) {
            if (this.a.h != 0) {
                N.MdZBZ$ST(this.a.e, this.a, this.a.h, bArr);
            }
            this.a.h = 0L;
        }
    }
}
