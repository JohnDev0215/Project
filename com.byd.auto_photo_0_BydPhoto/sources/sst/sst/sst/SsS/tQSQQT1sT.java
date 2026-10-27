package sst.sst.sst.SsS;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CarStatusSetting;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.provider.IDiLinkDiCareRecordOfCarStatusSetting;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkDiCareRecordOfCarStatusSetting.class, singleton = true)
public class tQSQQT1sT implements IDiLinkDiCareRecordOfCarStatusSetting {
    @Override // com.byd.dilink.provider.IDiLinkDiCareRecordOfCarStatusSetting
    public Cursor getDiCareRecord(ContentResolver contentResolver) {
        return CarStatusSetting.DiCareRecord.getDiCareRecord(contentResolver);
    }

    @Override // com.byd.dilink.provider.IDiLinkDiCareRecordOfCarStatusSetting
    public boolean insertDiCareRecord(ContentResolver contentResolver, long j, int i, int i2, int i3, String str) {
        return CarStatusSetting.DiCareRecord.insertDiCareRecord(contentResolver, j, i, i2, i3, str);
    }
}
