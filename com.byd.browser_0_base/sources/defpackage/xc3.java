package defpackage;

import java.util.function.BooleanSupplier;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* JADX INFO: compiled from: ToolbarPhone.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xc3 implements BooleanSupplier {
    public final /* synthetic */ ToolbarPhone a;

    public xc3(ToolbarPhone toolbarPhone) {
        this.a = toolbarPhone;
    }

    @Override // java.util.function.BooleanSupplier
    public final boolean getAsBoolean() {
        ToolbarPhone toolbarPhone = this.a;
        return (toolbarPhone.H != 0 || toolbarPhone.O || toolbarPhone.o || toolbarPhone.getToolbarDataProvider().g().e() || toolbarPhone.getToolbarDataProvider().e()) ? false : true;
    }
}
