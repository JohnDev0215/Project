package defpackage;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: renamed from: x0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewOnLayoutChangeListenerC1742x0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ SearchView a;

    public ViewOnLayoutChangeListenerC1742x0(SearchView searchView) {
        this.a = searchView;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        SearchView searchView = this.a;
        if (searchView.P.getWidth() > 1) {
            Resources resources = searchView.getContext().getResources();
            int paddingLeft = searchView.f15J.getPaddingLeft();
            Rect rect = new Rect();
            boolean zA = X0.a(searchView);
            int dimensionPixelSize = searchView.d0 ? resources.getDimensionPixelSize(AbstractC0299Qm.Q) + resources.getDimensionPixelSize(AbstractC0299Qm.P) : 0;
            searchView.H.getDropDownBackground().getPadding(rect);
            searchView.H.setDropDownHorizontalOffset(zA ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
            searchView.H.setDropDownWidth((((searchView.P.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }
}
