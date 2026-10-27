package So.So.So.PoSP8;

import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.safekeyservice.ExecResult;
import com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkSafeKeyManager.class, singleton = true)
public class So implements IDiLinkSafeKeyManager {
    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public ExecResult asymmetricDe(Context context, int i, String str, int i2) {
        throw new UnsupportedTargetPlatformException("FSE 没有 SafeKeyManager 类");
    }

    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public ExecResult asymmetricSign(Context context, int i, String str, int i2) {
        throw new UnsupportedTargetPlatformException("FSE 没有 SafeKeyManager 类");
    }

    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public int asymmetricVerify(Context context, int i, String str, int i2, String str2) {
        throw new UnsupportedTargetPlatformException("FSE 没有 SafeKeyManager 类");
    }

    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public ExecResult getDataFromChip(Context context, int i, int i2) {
        throw new UnsupportedTargetPlatformException("FSE 没有 SafeKeyManager 类");
    }
}
