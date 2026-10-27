package mNTT77n7n.mNTT77n7n.mNTT77n7n.MTmnNTT.nmMNN77m;

import android.content.Context;
import android.hardware.watermark.FontUtils;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkFontUtils;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkFontUtils.class, singleton = true)
public class mNTT77n7n implements IDiLinkFontUtils {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkFontUtils
    @NonNull
    public String convertColorToString(int i) {
        return FontUtils.convertColorToString(i);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkFontUtils
    @NonNull
    public String createPNGName(@NonNull String str, int i, int i2) {
        return FontUtils.createPNGName(str, i, i2);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkFontUtils
    @NonNull
    public String createRGBAName(@NonNull String str, int i, int i2) {
        return FontUtils.createRGBAName(str, i, i2);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkFontUtils
    public boolean createTxtBmp(@NonNull Context context, @NonNull String str, int i, int i2, int i3, int i4, int i5) {
        return FontUtils.createTxtBmp(context, str, i, i2, i3, i4, i5);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkFontUtils
    public boolean createTxtRGBA(@NonNull Context context, @NonNull String str, @NonNull String str2, int i, int i2, int i3, int i4) {
        return FontUtils.createTxtRGBA(context, str, str2, i, i2, i3, i4);
    }

    @Override // com.byd.dilink.hardware.watermark.IDiLinkFontUtils
    public boolean createTxtRGBA(@NonNull Context context, @NonNull String str, @NonNull String str2, int i, int i2, int i3, int i4, int i5) {
        return FontUtils.createTxtRGBA(context, str, str2, i, i2, i3, i4, i5);
    }
}
