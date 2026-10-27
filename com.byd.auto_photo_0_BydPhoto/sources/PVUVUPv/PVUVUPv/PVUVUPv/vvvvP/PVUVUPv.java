package PVUVUPv.PVUVUPv.PVUVUPv.vvvvP;

import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.safekeyservice.ExecResult;
import com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkSafeKeyManager.class, singleton = true)
public class PVUVUPv implements IDiLinkSafeKeyManager {
    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public ExecResult asymmetricDe(Context context, int i, String str, int i2) {
        throw new UnsupportedTargetPlatformException("RSE 没有 SafeKeyManager 类");
    }

    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public ExecResult asymmetricSign(Context context, int i, String str, int i2) {
        throw new UnsupportedTargetPlatformException("RSE 没有 SafeKeyManager 类");
    }

    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public int asymmetricVerify(Context context, int i, String str, int i2, String str2) {
        throw new UnsupportedTargetPlatformException("RSE 没有 SafeKeyManager 类");
    }

    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public ExecResult getDataFromChip(Context context, int i, int i2) {
        throw new UnsupportedTargetPlatformException("RSE 没有 SafeKeyManager 类");
    }
}
