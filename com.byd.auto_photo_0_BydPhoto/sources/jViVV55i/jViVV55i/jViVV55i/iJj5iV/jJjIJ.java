package jViVV55i.jViVV55i.jViVV55i.iJj5iV;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.CarStatusSetting;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.provider.IDiLinkTravelInfoDataOfCarStatusSetting;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkTravelInfoDataOfCarStatusSetting.class, singleton = true)
public class jJjIJ implements IDiLinkTravelInfoDataOfCarStatusSetting {
    @Override // com.byd.dilink.provider.IDiLinkTravelInfoDataOfCarStatusSetting
    public int getInt(ContentResolver contentResolver, String str, int i) {
        return CarStatusSetting.TravelInfoData.getInt(contentResolver, str, i);
    }

    @Override // com.byd.dilink.provider.IDiLinkTravelInfoDataOfCarStatusSetting
    public String getString(ContentResolver contentResolver, String str) {
        return CarStatusSetting.TravelInfoData.getString(contentResolver, str);
    }

    @Override // com.byd.dilink.provider.IDiLinkTravelInfoDataOfCarStatusSetting
    public Uri getUriFor(String str) {
        return CarStatusSetting.TravelInfoData.getUriFor(str);
    }

    @Override // com.byd.dilink.provider.IDiLinkTravelInfoDataOfCarStatusSetting
    public boolean putInt(ContentResolver contentResolver, String str, int i) {
        return CarStatusSetting.TravelInfoData.putInt(contentResolver, str, i);
    }

    @Override // com.byd.dilink.provider.IDiLinkTravelInfoDataOfCarStatusSetting
    public boolean putString(ContentResolver contentResolver, String str, String str2) {
        return CarStatusSetting.TravelInfoData.putString(contentResolver, str, str2);
    }
}
