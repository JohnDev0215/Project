package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.f81;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: CustomViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class n50 extends i81 {
    public n50(RecyclerView recyclerView) {
        FrameLayout frameLayout = new FrameLayout(recyclerView.getContext());
        super(frameLayout);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    }

    @Override // defpackage.i81
    public final void u(PropertyModel propertyModel, f81 f81Var) {
        f81.i iVar = (f81.i) f81Var;
        ViewGroup viewGroup = (ViewGroup) this.e;
        if (viewGroup.getChildCount() <= 0 || viewGroup.getChildAt(0) != iVar.d) {
            ViewParent parent = iVar.d.getParent();
            boolean z = parent instanceof ViewGroup;
            View view = iVar.d;
            if (z) {
                ((ViewGroup) parent).removeView(view);
            }
            viewGroup.removeAllViews();
            viewGroup.addView(view, new ViewGroup.LayoutParams(-1, -2));
        }
    }
}
