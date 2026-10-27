package So.So.So.PPO.PopS8Sp;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils;
import com.byd.foundation.annotation.ServiceImpl;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkGraphicUtils.class, singleton = true)
public class PoOSp implements IDiLinkGraphicUtils {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    public void ARGBToNv21(byte[] bArr, int[] iArr, int i, int i2) {
        throw new UnsupportedTargetPlatformException("FSE 没有 GraphicUtils 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    @NonNull
    public byte[] BitmapToNv21(int i, int i2, @NonNull Bitmap bitmap) {
        throw new UnsupportedTargetPlatformException("FSE 没有 GraphicUtils 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    @NonNull
    public Bitmap createBitmap(int i, int i2, float f, int i3) {
        throw new UnsupportedTargetPlatformException("FSE 没有 GraphicUtils 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    public boolean freeBitmap(@NonNull Bitmap bitmap) {
        throw new UnsupportedTargetPlatformException("FSE 没有 GraphicUtils 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    public boolean saveData(@NonNull byte[] bArr, @NonNull String str, @NonNull String str2, int i) {
        throw new UnsupportedTargetPlatformException("FSE 没有 GraphicUtils 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    public boolean savePngFile(@NonNull Bitmap bitmap, @NonNull String str, @NonNull String str2) {
        throw new UnsupportedTargetPlatformException("FSE 没有 GraphicUtils 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkGraphicUtils
    public boolean saveYUVFile(@NonNull Bitmap bitmap, int i, int i2, @NonNull File file) {
        throw new UnsupportedTargetPlatformException("FSE 没有 GraphicUtils 类");
    }
}
