package GGW.GGW.GGW.gG;

import android.content.Context;
import android.safekeyservice.OutDataBean;
import android.safekeyservice.SafeKeyManager;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.safekeyservice.ExecResult;
import com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = false, versions = {"RUI"})
@ServiceImpl(service = IDiLinkSafeKeyManager.class, singleton = true)
public class GGW implements IDiLinkSafeKeyManager {
    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public ExecResult asymmetricDe(Context context, int i, String str, int i2) {
        ExecResult execResult = new ExecResult();
        OutDataBean outDataBean = new OutDataBean();
        int iAsymmetricDe = new SafeKeyManager(context).asymmetricDe(i, str, i2, outDataBean);
        if (iAsymmetricDe == 0) {
            execResult.mData = outDataBean.getData();
            execResult.mDataLen = outDataBean.getDataLen();
            execResult.retCode = 0;
        } else {
            execResult.mData = "";
            execResult.mDataLen = 0;
            execResult.retCode = iAsymmetricDe;
        }
        return execResult;
    }

    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public ExecResult asymmetricSign(Context context, int i, String str, int i2) {
        ExecResult execResult = new ExecResult();
        OutDataBean outDataBean = new OutDataBean();
        int iAsymmetricSign = new SafeKeyManager(context).asymmetricSign(i, 1, str, i2, outDataBean);
        if (iAsymmetricSign == 0) {
            execResult.mData = outDataBean.getData();
            execResult.mDataLen = outDataBean.getDataLen();
            execResult.retCode = 0;
        } else {
            execResult.mData = "";
            execResult.mDataLen = 0;
            execResult.retCode = iAsymmetricSign;
        }
        return execResult;
    }

    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public int asymmetricVerify(Context context, int i, String str, int i2, String str2) {
        return new SafeKeyManager(context).asymmetricVerify(i, 1, str, i2, str2);
    }

    @Override // com.byd.dilink.safekeyservice.IDiLinkSafeKeyManager
    public ExecResult getDataFromChip(Context context, int i, int i2) {
        ExecResult execResult = new ExecResult();
        OutDataBean outDataBean = new OutDataBean();
        int dataFromChip = new SafeKeyManager(context).getDataFromChip(i, i2, outDataBean);
        if (dataFromChip == 0) {
            execResult.mData = outDataBean.getData();
            execResult.mDataLen = outDataBean.getDataLen();
            execResult.retCode = 0;
        } else {
            execResult.mData = "";
            execResult.mDataLen = 0;
            execResult.retCode = dataFromChip;
        }
        return execResult;
    }
}
