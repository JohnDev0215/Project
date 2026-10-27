package defpackage;

import J.N;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.view.ViewConfiguration;
import org.chromium.ui.gfx.ViewConfigurationHelper;

/* JADX INFO: compiled from: ViewConfigurationHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class pm3 implements ComponentCallbacks {
    public final /* synthetic */ ViewConfigurationHelper e;

    public pm3(ViewConfigurationHelper viewConfigurationHelper) {
        this.e = viewConfigurationHelper;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        ViewConfigurationHelper viewConfigurationHelper = this.e;
        viewConfigurationHelper.getClass();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(xz.a);
        if (viewConfigurationHelper.a == viewConfiguration) {
            return;
        }
        viewConfigurationHelper.a = viewConfiguration;
        viewConfigurationHelper.b = xz.a.getResources().getDisplayMetrics().density;
        N.MtrEpb2R(viewConfigurationHelper, viewConfigurationHelper.getMaximumFlingVelocity(), viewConfigurationHelper.getMinimumFlingVelocity(), viewConfigurationHelper.getTouchSlop(), viewConfigurationHelper.getDoubleTapSlop(), viewConfigurationHelper.getMinScalingSpan());
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}
