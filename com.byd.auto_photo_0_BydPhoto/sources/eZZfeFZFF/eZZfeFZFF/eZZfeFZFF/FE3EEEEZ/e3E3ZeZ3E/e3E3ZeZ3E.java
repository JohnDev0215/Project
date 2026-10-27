package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FE3EEEEZ.e3E3ZeZ3E;

import android.graphics.Bitmap;
import android.hardware.watermark.GraphicUtils;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils;
import com.byd.foundation.annotation.ServiceImpl;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkGraphicUtils.class, singleton = true)
public class e3E3ZeZ3E implements IDiLinkGraphicUtils {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    public void ARGBToNv21(byte[] bArr, int[] iArr, int i, int i2) {
        GraphicUtils.ARGBToNv21(bArr, iArr, i, i2);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    @NonNull
    public byte[] BitmapToNv21(int i, int i2, @NonNull Bitmap bitmap) {
        return GraphicUtils.BitmapToNv21(i, i2, bitmap);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    @NonNull
    public Bitmap createBitmap(int i, int i2, float f, int i3) {
        return GraphicUtils.createBitmap(i, i2, f, i3);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    public boolean freeBitmap(@NonNull Bitmap bitmap) {
        return GraphicUtils.freeBitmap(bitmap);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    public boolean saveData(@NonNull byte[] bArr, @NonNull String str, @NonNull String str2, int i) {
        return GraphicUtils.saveData(bArr, str, str2, i);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    public boolean savePngFile(@NonNull Bitmap bitmap, @NonNull String str, @NonNull String str2) {
        return GraphicUtils.savePngFile(bitmap, str, str2);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    public boolean saveYUVFile(@NonNull Bitmap bitmap, int i, int i2, @NonNull File file) {
        return GraphicUtils.saveYUVFile(bitmap, i, i2, file);
    }
}
