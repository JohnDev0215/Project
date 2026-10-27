package defpackage;

import android.os.Build;
import android.os.SystemClock;
import android.view.InputDevice;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class KE {
    public int a;
    public int b;
    public String h;
    public int[] i;
    public WE j;
    public final float[] d = new float[4];
    public final float[] e = new float[17];
    public final float[] f = new float[256];
    public final float[] g = new float[256];
    public long c = SystemClock.uptimeMillis();

    public KE(int i, InputDevice inputDevice) {
        WE ue;
        this.b = i;
        this.a = inputDevice.getId();
        this.h = inputDevice.getName();
        List<InputDevice.MotionRange> motionRanges = inputDevice.getMotionRanges();
        this.i = new int[motionRanges.size()];
        int i2 = 0;
        for (InputDevice.MotionRange motionRange : motionRanges) {
            if ((motionRange.getSource() & 16) != 0) {
                this.i[i2] = motionRange.getAxis();
                i2++;
            }
        }
        int[] iArr = this.i;
        int i3 = Build.VERSION.SDK_INT;
        int productId = inputDevice.getProductId();
        int vendorId = inputDevice.getVendorId();
        WE we = null;
        WE ue2 = (vendorId == 1356 && (productId == 1476 || productId == 2508 || productId == 2976)) ? i3 >= 28 ? new UE(null) : new RE() : (vendorId == 1118 && productId == 736) ? new VE(null) : null;
        if (ue2 == null) {
            String name = inputDevice.getName();
            if (name.startsWith("NVIDIA Corporation NVIDIA Controller") || name.equals("Microsoft X-Box 360 pad")) {
                ue = new UE(null);
            } else if (name.equals("Sony PLAYSTATION(R)3 Controller")) {
                ue = i3 >= 28 ? new PE(null) : new QE(null);
            } else if (name.equals("Samsung Game Pad EI-GP20")) {
                ue = new SE(null);
            } else {
                if (name.equals("Amazon Fire Game Controller")) {
                    ue = new OE(null);
                }
                ue2 = we;
            }
            we = ue;
            ue2 = we;
        }
        this.j = ue2 == null ? new TE(iArr) : ue2;
    }
}
