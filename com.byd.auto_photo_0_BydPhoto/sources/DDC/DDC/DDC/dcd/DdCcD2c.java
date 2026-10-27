package DDC.DDC.DDC.dcd;

import a.a.a;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkUiStyleConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkUiStyleConfig.class, singleton = true)
public class DdCcD2c implements IDiLinkUiStyleConfig {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f42a;

    public DdCcD2c(Context context) {
        this.f42a = a.a(context);
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public int getUiStyle() {
        return this.f42a.b();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isDenza() {
        return this.f42a.c();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isDynasty() {
        return this.f42a.d();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isF() {
        return this.f42a.isF();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isOcean() {
        return this.f42a.e();
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleConfig
    public boolean isR4ui() {
        return this.f42a.isR4ui();
    }
}
