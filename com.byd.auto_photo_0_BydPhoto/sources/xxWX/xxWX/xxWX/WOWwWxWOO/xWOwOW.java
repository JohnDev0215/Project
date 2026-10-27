package xxWX.xxWX.xxWX.WOWwWxWOO;

import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkUiStyleConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkUiStyleConfig.class, singleton = true)
public class xWOwOW implements IDiLinkUiStyleConfig {
    public xWOwOW(Context context) {
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public int getUiStyle() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有UiStyleConfig类!");
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isDenza() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有UiStyleConfig类!");
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isDynasty() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有UiStyleConfig类!");
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isF() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有UiStyleConfig类!");
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isOcean() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有UiStyleConfig类!");
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isR4ui() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 isR4ui 这个函数！");
    }
}
