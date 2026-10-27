package GGW.GGW.GGW.hGHHh4WW.Gg4HHGg44;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.hardware.watermark.TextToImage;
import androidx.annotation.NonNull;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkTextToImage;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkTextToImage.class, singleton = true)
public class W4H implements IDiLinkTextToImage {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkTextToImage
    public boolean textTo12File(@NonNull Context context, @NonNull Bitmap bitmap, @NonNull String str) {
        return TextToImage.textTo12File(context, bitmap, str);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkTextToImage
    public boolean textToBmpFile(@NonNull Context context, @NonNull String str, @NonNull String str2, int i, int i2, int i3, int i4, int i5, @NonNull String str3) {
        return TextToImage.textToBmpFile(context, str, str2, i, i2, i3, i4, i5, str3);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkTextToImage
    public boolean textToNv21File(@NonNull Context context, @NonNull Bitmap bitmap, @NonNull String str) {
        return TextToImage.textToNv21File(context, bitmap, str);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkTextToImage
    public boolean textToRgbFile(@NonNull Context context, @NonNull Bitmap bitmap, @NonNull String str) {
        return TextToImage.textToRgbFile(context, bitmap, str);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkTextToImage
    public boolean textToRgbFile(@NonNull Context context, @NonNull String str, @NonNull String str2, int i, int i2, int i3, int i4, int i5, @NonNull String str3) {
        return TextToImage.textToRgbFile(context, str, str2, i, i2, i3, i4, i5, str3);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkTextToImage
    @NonNull
    public Bitmap txtToBitmap(@NonNull Context context, @NonNull Typeface typeface, @NonNull String str, int i, int i2, int i3, int i4, int i5) {
        return TextToImage.txtToBitmap(context, typeface, str, i, i2, i3, i4, i5);
    }
}
