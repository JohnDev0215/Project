package F0;

import android.os.SystemClock;
import android.view.MotionEvent;
import androidx.compose.ui.platform.AndroidComposeView;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: F0.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0236x extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2294m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ AndroidComposeView f2295n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0236x(AndroidComposeView androidComposeView, int i7) {
        super(0);
        this.f2294m = i7;
        this.f2295n = androidComposeView;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        int actionMasked;
        switch (this.f2294m) {
            case 0:
                AndroidComposeView androidComposeView = this.f2295n;
                MotionEvent motionEvent = androidComposeView.f11684y0;
                if (motionEvent != null && ((actionMasked = motionEvent.getActionMasked()) == 7 || actionMasked == 9)) {
                    androidComposeView.f11686z0 = SystemClock.uptimeMillis();
                    androidComposeView.post(androidComposeView.f11621C0);
                }
                return m7.q.f19267a;
            default:
                return this.f2295n.get_viewTreeOwners();
        }
    }
}
