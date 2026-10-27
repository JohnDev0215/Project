package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.EEeZFfE;

import a.a.a;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkUiStyleConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkUiStyleConfig.class, singleton = true)
public class FE3EEEEZ implements IDiLinkUiStyleConfig {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f3026a;

    public FE3EEEEZ(Context context) {
        this.f3026a = a.a(context);
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public int getUiStyle() {
        return this.f3026a.b();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isDenza() {
        return this.f3026a.c();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isDynasty() {
        return this.f3026a.d();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isF() {
        return this.f3026a.isF();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isOcean() {
        return this.f3026a.e();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isR4ui() {
        return this.f3026a.isR4ui();
    }
}
