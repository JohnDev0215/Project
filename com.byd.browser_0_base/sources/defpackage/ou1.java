package defpackage;

import android.content.Context;
import android.view.View;
import org.chromium.components.browser_ui.widget.FadingEdgeScrollView;

/* JADX INFO: compiled from: PageInfoDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ou1 extends FadingEdgeScrollView {
    public final /* synthetic */ View j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ou1(Context context, View view) {
        super(context, null);
        this.j = view;
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        View view = this.j;
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(view != null ? (view.getHeight() * 90) / 100 : 0, Integer.MIN_VALUE));
    }
}
