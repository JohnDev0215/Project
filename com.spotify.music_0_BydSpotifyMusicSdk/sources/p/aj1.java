package p;

import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class aj1 extends FrameLayout implements hu {
    public final CollapsibleActionView c;

    /* JADX WARN: Multi-variable type inference failed */
    public aj1(View view) {
        super(view.getContext());
        this.c = (CollapsibleActionView) view;
        addView(view);
    }

    @Override // p.hu
    public final void a() {
        this.c.onActionViewExpanded();
    }

    @Override // p.hu
    public final void e() {
        this.c.onActionViewCollapsed();
    }
}
