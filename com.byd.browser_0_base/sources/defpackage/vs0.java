package defpackage;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.transition.R$id;
import java.util.ArrayList;

/* JADX INFO: compiled from: GhostViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
public final class vs0 extends FrameLayout {
    public static final /* synthetic */ int g = 0;
    public final ViewGroup e;
    public boolean f;

    public vs0(ViewGroup viewGroup) {
        super(viewGroup.getContext());
        setClipChildren(false);
        this.e = viewGroup;
        viewGroup.setTag(R$id.ghost_view_holder, this);
        viewGroup.getOverlay().add(this);
        this.f = true;
    }

    public static void a(View view, ArrayList<View> arrayList) {
        Object parent = view.getParent();
        if (parent instanceof ViewGroup) {
            a((View) parent, arrayList);
        }
        arrayList.add(view);
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        if (!this.f) {
            throw new IllegalStateException("This GhostViewHolder is detached!");
        }
        super.onViewAdded(view);
    }

    @Override // android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if ((getChildCount() == 1 && getChildAt(0) == view) || getChildCount() == 0) {
            int i = R$id.ghost_view_holder;
            ViewGroup viewGroup = this.e;
            viewGroup.setTag(i, null);
            viewGroup.getOverlay().remove(this);
            this.f = false;
        }
    }
}
