package jViVV55i.jViVV55i.jViVV55i.VIJjVj;

import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.safekeyservice.ExecResult;
import com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkSafeKeyManager.class, singleton = true)
public class jViVV55i implements IDiLinkSafeKeyManager {
    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public ExecResult asymmetricDe(Context context, int i, String str, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 SafeKeyManager 类！");
    }

    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public ExecResult asymmetricSign(Context context, int i, String str, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 SafeKeyManager 类！");
    }

    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public int asymmetricVerify(Context context, int i, String str, int i2, String str2) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 SafeKeyManager 类！");
    }

    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public ExecResult getDataFromChip(Context context, int i, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 SafeKeyManager 类！");
    }
}
