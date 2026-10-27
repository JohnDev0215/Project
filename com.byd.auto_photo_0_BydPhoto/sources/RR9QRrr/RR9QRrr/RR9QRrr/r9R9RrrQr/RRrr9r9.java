package RR9QRrr.RR9QRrr.RR9QRrr.r9R9RrrQr;

import a.a.a;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkUiStyleConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkUiStyleConfig.class, singleton = true)
public class RRrr9r9 implements IDiLinkUiStyleConfig {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f131a;

    public RRrr9r9(Context context) {
        this.f131a = a.a(context);
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public int getUiStyle() {
        return this.f131a.b();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isDenza() {
        return this.f131a.c();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isDynasty() {
        return this.f131a.d();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isF() {
        return this.f131a.isF();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isOcean() {
        return this.f131a.e();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isR4ui() {
        return this.f131a.isR4ui();
    }
}
