package defpackage;

import android.os.Bundle;
import android.widget.directwriting.IDirectWritingService;
import defpackage.fa0;

/* JADX INFO: compiled from: DirectWritingTrigger.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ja0 implements fa0.b {
    public final /* synthetic */ ka0 a;

    public ja0(ka0 ka0Var) {
        this.a = ka0Var;
    }

    public final void a(Bundle bundle) {
        ha0 ha0Var = this.a.f;
        ha0Var.getClass();
        ha0Var.a = bundle.getLong(IDirectWritingService.KEY_BUNDLE_CONFIG_HIDE_DELAY, 1000L);
        ha0Var.b = bundle.getLong(IDirectWritingService.KEY_BUNDLE_CONFIG_KEEP_WRITING_DELAY, 1000L);
        bundle.getInt(IDirectWritingService.KEY_BUNDLE_CONFIG_TRIGGER_VERTICAL_SPACE, 56);
        bundle.getInt(IDirectWritingService.KEY_BUNDLE_CONFIG_TRIGGER_HORIZONTAL_SPACE_DEFAULT, 56);
    }
}
