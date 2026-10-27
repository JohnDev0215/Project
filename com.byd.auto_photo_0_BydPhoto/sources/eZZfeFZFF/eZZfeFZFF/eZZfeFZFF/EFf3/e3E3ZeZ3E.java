package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.EFf3;

import android.content.ContentResolver;
import android.provider.CarSettings;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.provider.IDiLinkCarSettingsConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkCarSettingsConfig.class, singleton = true)
public class e3E3ZeZ3E implements IDiLinkCarSettingsConfig {
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
