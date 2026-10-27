package defpackage;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.components.page_info.PageInfoContainer;
import org.chromium.components.page_info.R$id;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class qt3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ View f;

    public /* synthetic */ qt3(View view, int i) {
        this.e = i;
        this.f = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LinearLayout linearLayout;
        int i = this.e;
        View view = this.f;
        switch (i) {
            case 0:
                ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
                break;
            case 1:
                int i2 = NewTabPageLayout.L;
                new Handler().postDelayed(new zc0(19, view), 200L);
                break;
            default:
                int i3 = PageInfoContainer.l;
                ViewGroup viewGroup = (ViewGroup) view.findViewById(R$id.page_info_row_wrapper);
                if (viewGroup != null) {
                    View view2 = null;
                    int i4 = 0;
                    for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
                        View childAt = viewGroup.getChildAt(i5);
                        if (childAt.getVisibility() == 0) {
                            i4++;
                            view2 = childAt;
                        }
                    }
                    if (i4 == 1 && (linearLayout = (LinearLayout) view2.findViewById(R$id.view_title)) != null) {
                        view2.findViewById(R$id.page_info_row_icon).setVisibility(8);
                        linearLayout.setGravity(17);
                        break;
                    }
                }
                break;
        }
    }
}
