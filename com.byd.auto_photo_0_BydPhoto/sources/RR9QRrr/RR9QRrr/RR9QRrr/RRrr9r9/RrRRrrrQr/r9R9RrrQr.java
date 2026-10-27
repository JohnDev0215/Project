package RR9QRrr.RR9QRrr.RR9QRrr.RRrr9r9.RrRRrrrQr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkTextToImage;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkTextToImage.class, singleton = true)
public class r9R9RrrQr implements IDiLinkTextToImage {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkTextToImage
    public boolean textTo12File(@NonNull Context context, @NonNull Bitmap bitmap, @NonNull String str) {
        throw new UnsupportedTargetPlatformException("rcs. 没有 TextToImage 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkTextToImage
    public boolean textToBmpFile(@NonNull Context context, @NonNull String str, @NonNull String str2, int i, int i2, int i3, int i4, int i5, @NonNull String str3) {
        throw new UnsupportedTargetPlatformException("rcs. 没有 TextToImage 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkTextToImage
    public boolean textToNv21File(@NonNull Context context, @NonNull Bitmap bitmap, @NonNull String str) {
        throw new UnsupportedTargetPlatformException("rcs. 没有 TextToImage 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkTextToImage
    public boolean textToRgbFile(@NonNull Context context, @NonNull Bitmap bitmap, @NonNull String str) {
        throw new UnsupportedTargetPlatformException("rcs. 没有 TextToImage 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkTextToImage
    public boolean textToRgbFile(@NonNull Context context, @NonNull String str, @NonNull String str2, int i, int i2, int i3, int i4, int i5, @NonNull String str3) {
        throw new UnsupportedTargetPlatformException("rcs. 没有 TextToImage 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkTextToImage
    @NonNull
    public Bitmap txtToBitmap(@NonNull Context context, @NonNull Typeface typeface, @NonNull String str, int i, int i2, int i3, int i4, int i5) {
        throw new UnsupportedTargetPlatformException("rcs. 没有 TextToImage 类");
    }
}
