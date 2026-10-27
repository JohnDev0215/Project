package GGW.GGW.GGW.ghGhhg4;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.CarSettings;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.provider.IDiLinkCarSettingsGlobal;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = false, versions = {"RUI"})
@ServiceImpl(service = IDiLinkCarSettingsGlobal.class, singleton = true)
public class W4H implements IDiLinkCarSettingsGlobal {
    @Override // com.byd.dilink.provider.IDiLinkCarSettingsGlobal
    public boolean getBoolean(ContentResolver contentResolver, String str) {
        return CarSettings.Global.getBoolean(contentResolver, str);
    }

    @Override // com.byd.dilink.provider.IDiLinkCarSettingsGlobal
    public int getInt(ContentResolver contentResolver, String str) {
        return CarSettings.Global.getInt(contentResolver, str);
    }

    @Override // com.byd.dilink.provider.IDiLinkCarSettingsGlobal
    public Uri getUriFor(Uri uri, String str) {
        return CarSettings.getUriFor(uri, str);
    }

    @Override // com.byd.dilink.provider.IDiLinkCarSettingsGlobal
    public boolean putBoolean(ContentResolver contentResolver, String str, boolean z) {
        return CarSettings.Global.putBoolean(contentResolver, str, z);
    }

    @Override // com.byd.dilink.provider.IDiLinkCarSettingsGlobal
    public boolean putInt(ContentResolver contentResolver, String str, int i) {
        return CarSettings.Global.putInt(contentResolver, str, i);
    }
}
