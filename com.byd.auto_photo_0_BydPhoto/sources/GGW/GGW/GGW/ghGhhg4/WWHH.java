package GGW.GGW.GGW.ghGhhg4;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CarStatusSetting;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.provider.IDiLinkDiCareRecordOfCarStatusSetting;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = false, versions = {"RUI"})
@ServiceImpl(service = IDiLinkDiCareRecordOfCarStatusSetting.class, singleton = true)
public class WWHH implements IDiLinkDiCareRecordOfCarStatusSetting {
    @Override // com.byd.dilink.provider.IDiLinkDiCareRecordOfCarStatusSetting
    public Cursor getDiCareRecord(ContentResolver contentResolver) {
        return CarStatusSetting.DiCareRecord.getDiCareRecord(contentResolver);
    }

    @Override // com.byd.dilink.provider.IDiLinkDiCareRecordOfCarStatusSetting
    public boolean insertDiCareRecord(ContentResolver contentResolver, long j, int i, int i2, int i3, String str) {
        return CarStatusSetting.DiCareRecord.insertDiCareRecord(contentResolver, j, i, i2, i3, str);
    }
}
