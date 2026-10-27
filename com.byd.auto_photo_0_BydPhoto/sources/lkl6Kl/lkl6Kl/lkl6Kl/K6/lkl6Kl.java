package lkl6Kl.lkl6Kl.lkl6Kl.K6;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkBYDAuthListener;
import com.byd.dilink.app.IDiLinkBYDAuthManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkBYDAuthManager.class, singleton = true)
public class lkl6Kl implements IDiLinkBYDAuthManager {
    @SuppressLint({"WrongConstant"})
    public lkl6Kl(Context context) {
    }

    @Override // com.byd.dilink.app.IDiLinkBYDAuthManager
    public void requestAuth(int i, @NonNull IDiLinkBYDAuthListener iDiLinkBYDAuthListener) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 requestAuth方法");
    }
}
