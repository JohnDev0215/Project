package lkl6Kl.lkl6Kl.lkl6Kl.K6;

import a.a.a;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkUiStyleConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkUiStyleConfig.class, singleton = true)
public class U66LkU6Uk implements IDiLinkUiStyleConfig {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f3101a;

    public U66LkU6Uk(Context context) {
        this.f3101a = a.a(context);
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public int getUiStyle() {
        return this.f3101a.b();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isDenza() {
        return this.f3101a.c();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isDynasty() {
        return this.f3101a.d();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isF() {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 isF 这个函数！");
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isOcean() {
        return this.f3101a.e();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isR4ui() {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 isR4ui 这个函数！");
    }
}
