package xxWX.xxWX.xxWX.wXWw;

import android.content.ContentResolver;
import android.database.Cursor;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.provider.IDiLinkDiCareRecordOfCarStatusSetting;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkDiCareRecordOfCarStatusSetting.class, singleton = true)
public class xxWX implements IDiLinkDiCareRecordOfCarStatusSetting {
    @Override // com.byd.dilink.provider.IDiLinkDiCareRecordOfCarStatusSetting
    public Cursor getDiCareRecord(ContentResolver contentResolver) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有这个接口!");
    }

    @Override // com.byd.dilink.provider.IDiLinkDiCareRecordOfCarStatusSetting
    public boolean insertDiCareRecord(ContentResolver contentResolver, long j, int i, int i2, int i3, String str) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有这个接口!");
    }
}
