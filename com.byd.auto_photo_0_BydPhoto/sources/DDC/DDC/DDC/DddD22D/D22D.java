package DDC.DDC.DDC.DddD22D;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.CarStatusSetting;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.provider.IDiLinkTravelInfoDataOfCarStatusSetting;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkTravelInfoDataOfCarStatusSetting.class, singleton = true)
public class D22D implements IDiLinkTravelInfoDataOfCarStatusSetting {
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
