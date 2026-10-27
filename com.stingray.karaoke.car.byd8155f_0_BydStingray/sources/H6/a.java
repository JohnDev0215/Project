package H6;

import com.stingray.shared.qrcode.QRCodeGenerator$QrShape;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f2838a;

    static {
        int[] iArr = new int[QRCodeGenerator$QrShape.values().length];
        try {
            iArr[QRCodeGenerator$QrShape.Default.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[QRCodeGenerator$QrShape.Rounded.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f2838a = iArr;
    }
}
