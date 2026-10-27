package com.amazon.mp3.auto.signin;

import android.graphics.Bitmap;
import androidx.core.view.ViewCompat;
import com.amazon.mp3.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.EnumMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LinkCodeWrapper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 #2\u00020\u0001:\u0001#B\u000f\u0012\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\"\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0006\u0010\u0004\u001a\u00020\u0002J\u001e\u0010\n\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\u0006\u0010\u000b\u001a\u00020\u0005R\u0017\u0010\f\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u0010\u0010\u000f\"\u0004\b\u0011\u0010\u0012R\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\t\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0013\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\"\u0010\u001b\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\r¨\u0006$"}, d2 = {"Lcom/amazon/mp3/auto/signin/LinkCodeWrapper;", "", "", "generateQRCode", "resetTTLAndPolling", "", "linkCode", "", "timeToLive", "pollingInterval", "setLinkCodeResponse", "buildCodeEntryURL", "loginDomain", "Ljava/lang/String;", "getLoginDomain", "()Ljava/lang/String;", "getLinkCode", "setLinkCode", "(Ljava/lang/String;)V", "J", "getTimeToLive", "()J", "setTimeToLive", "(J)V", "getPollingInterval", "setPollingInterval", "Landroid/graphics/Bitmap;", "qrCode", "Landroid/graphics/Bitmap;", "getQrCode", "()Landroid/graphics/Bitmap;", "setQrCode", "(Landroid/graphics/Bitmap;)V", "codeEntryURL", "<init>", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LinkCodeWrapper {
    private static final String TAG;
    private final String codeEntryURL;
    private String linkCode;
    private final String loginDomain;
    private long pollingInterval;
    private Bitmap qrCode;
    private long timeToLive;

    public LinkCodeWrapper(String loginDomain) {
        Intrinsics.checkNotNullParameter(loginDomain, "loginDomain");
        this.loginDomain = loginDomain;
        this.linkCode = "";
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.RGB_565);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(dimension, …n, Bitmap.Config.RGB_565)");
        this.qrCode = bitmapCreateBitmap;
        this.codeEntryURL = Intrinsics.stringPlus(loginDomain, "/code");
    }

    public final String getLoginDomain() {
        return this.loginDomain;
    }

    public final String getLinkCode() {
        return this.linkCode;
    }

    public final long getTimeToLive() {
        return this.timeToLive;
    }

    public final long getPollingInterval() {
        return this.pollingInterval;
    }

    public final Bitmap getQrCode() {
        return this.qrCode;
    }

    static {
        String simpleName = LinkCodeWrapper.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "LinkCodeWrapper::class.java.simpleName");
        TAG = simpleName;
    }

    public final void resetTTLAndPolling() {
        this.timeToLive = 0L;
        this.pollingInterval = 0L;
    }

    public final void setLinkCodeResponse(String linkCode, long timeToLive, long pollingInterval) throws WriterException {
        Intrinsics.checkNotNullParameter(linkCode, "linkCode");
        this.linkCode = linkCode;
        this.timeToLive = timeToLive;
        this.pollingInterval = pollingInterval;
        generateQRCode();
    }

    public final String buildCodeEntryURL() {
        if (Intrinsics.areEqual(this.linkCode, "")) {
            return this.codeEntryURL;
        }
        return this.codeEntryURL + "?cbl-code=" + this.linkCode;
    }

    private final void generateQRCode() throws WriterException {
        String strBuildCodeEntryURL = buildCodeEntryURL();
        EnumMap enumMap = new EnumMap(EncodeHintType.class);
        enumMap.put(EncodeHintType.CHARACTER_SET, ParserUtil.UTF_8);
        enumMap.put(EncodeHintType.MARGIN, 2);
        BitMatrix bitMatrixEncode = new MultiFormatWriter().encode(strBuildCodeEntryURL, BarcodeFormat.QR_CODE, 256, 256, enumMap);
        int width = bitMatrixEncode.getWidth();
        int height = bitMatrixEncode.getHeight();
        if (width == 0 || height == 0) {
            Log.error(TAG, "Resulting QR code has invalid dimensions!");
            return;
        }
        int[] iArr = new int[width * height];
        int i = 0;
        while (i < height) {
            int i2 = i + 1;
            int i3 = i * width;
            int i4 = 0;
            while (i4 < width) {
                int i5 = i4 + 1;
                iArr[i3 + i4] = bitMatrixEncode.get(i4, i) ? ViewCompat.MEASURED_STATE_MASK : -1;
                i4 = i5;
            }
            i = i2;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(width, heig… Bitmap.Config.ARGB_8888)");
        this.qrCode = bitmapCreateBitmap;
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
    }
}
