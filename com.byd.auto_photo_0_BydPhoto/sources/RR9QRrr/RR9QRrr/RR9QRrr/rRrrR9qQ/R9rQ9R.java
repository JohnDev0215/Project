package RR9QRrr.RR9QRrr.RR9QRrr.rRrrR9qQ;

import android.content.ContentResolver;
import android.provider.CarSettings;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.provider.IDiLinkCarSettingsConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkCarSettingsConfig.class, singleton = true)
public class R9rQ9R implements IDiLinkCarSettingsConfig {
    @Override // com.byd.dilink.provider.IDiLinkCarSettingsConfig
    public boolean getBoolean(ContentResolver contentResolver, String str) {
        return CarSettings.Config.getBoolean(contentResolver, str);
    }

    @Override // com.byd.dilink.provider.IDiLinkCarSettingsConfig
    public int getInt(ContentResolver contentResolver, String str) {
        return CarSettings.Config.getInt(contentResolver, str);
    }

    @Override // com.byd.dilink.provider.IDiLinkCarSettingsConfig
    public boolean putBoolean(ContentResolver contentResolver, String str, boolean z) {
        return CarSettings.Config.putBoolean(contentResolver, str, z);
    }

    @Override // com.byd.dilink.provider.IDiLinkCarSettingsConfig
    public boolean putInt(ContentResolver contentResolver, String str, int i) {
        return CarSettings.Config.putInt(contentResolver, str, i);
    }
}
