package jViVV55i.jViVV55i.jViVV55i.iJj5iV;

import android.content.ContentResolver;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.provider.IDiLinkCarSettingsConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkCarSettingsConfig.class, singleton = true)
public class jVJIV5i implements IDiLinkCarSettingsConfig {
    @Override // com.byd.dilink.provider.IDiLinkCarSettingsConfig
    public boolean getBoolean(ContentResolver contentResolver, String str) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 CarSettings.Config 内部类");
    }

    @Override // com.byd.dilink.provider.IDiLinkCarSettingsConfig
    public int getInt(ContentResolver contentResolver, String str) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 CarSettings.Config 内部类");
    }

    @Override // com.byd.dilink.provider.IDiLinkCarSettingsConfig
    public boolean putBoolean(ContentResolver contentResolver, String str, boolean z) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 CarSettings.Config 内部类");
    }

    @Override // com.byd.dilink.provider.IDiLinkCarSettingsConfig
    public boolean putInt(ContentResolver contentResolver, String str, int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 CarSettings.Config 内部类");
    }
}
