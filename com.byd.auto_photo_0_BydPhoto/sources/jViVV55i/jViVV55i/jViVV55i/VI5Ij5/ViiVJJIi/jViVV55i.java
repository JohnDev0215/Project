package jViVV55i.jViVV55i.jViVV55i.VI5Ij5.ViiVJJIi;

import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkFontUtils;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkFontUtils.class, singleton = true)
public class jViVV55i implements IDiLinkFontUtils {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkFontUtils
    @NonNull
    public String convertColorToString(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0. 没有 FontUtils 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkFontUtils
    @NonNull
    public String createPNGName(@NonNull String str, int i, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0. 没有 FontUtils 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkFontUtils
    @NonNull
    public String createRGBAName(@NonNull String str, int i, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0. 没有 FontUtils 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkFontUtils
    public boolean createTxtBmp(@NonNull Context context, @NonNull String str, int i, int i2, int i3, int i4, int i5) {
        throw new UnsupportedTargetPlatformException("Di3.0. 没有 FontUtils 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkFontUtils
    public boolean createTxtRGBA(@NonNull Context context, @NonNull String str, @NonNull String str2, int i, int i2, int i3, int i4) {
        throw new UnsupportedTargetPlatformException("Di3.0. 没有 FontUtils 类");
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkFontUtils
    public boolean createTxtRGBA(@NonNull Context context, @NonNull String str, @NonNull String str2, int i, int i2, int i3, int i4, int i5) {
        throw new UnsupportedTargetPlatformException("Di3.0. 没有 FontUtils 类");
    }
}
