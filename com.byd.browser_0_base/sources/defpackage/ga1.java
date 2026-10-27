package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.helper.widget.LogJson;
import org.chromium.chrome.browser.creator.CreatorToolbarView;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ga1 implements View.OnLayoutChangeListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ ga1(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = this.e;
        Object obj = this.f;
        switch (i9) {
            case 0:
                LogJson logJson = (LogJson) obj;
                if (logJson.o == 3) {
                    logJson.v();
                }
                break;
            case 1:
                CreatorToolbarView creatorToolbarView = (CreatorToolbarView) obj;
                if (creatorToolbarView.g.getVisibility() == 0) {
                    creatorToolbarView.a(creatorToolbarView.g);
                } else if (creatorToolbarView.h.getVisibility() == 0) {
                    creatorToolbarView.a(creatorToolbarView.h);
                }
                break;
            default:
                tc3 tc3Var = (tc3) obj;
                g gVar = tc3Var.f;
                if (gVar.l.getBackground() == null) {
                    int iA = gVar.X.a();
                    ToolbarControlContainer toolbarControlContainer = gVar.l;
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) toolbarControlContainer.getLayoutParams();
                    if (marginLayoutParams.topMargin != iA) {
                        marginLayoutParams.topMargin = iA;
                        toolbarControlContainer.setLayoutParams(marginLayoutParams);
                    }
                    toolbarControlContainer.removeOnLayoutChangeListener(tc3Var.e);
                    tc3Var.e = null;
                }
                break;
        }
    }
}
