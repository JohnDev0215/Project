package defpackage;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import org.chromium.weblayer_private.ContentViewRenderView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ZP extends FrameLayout {
    public final /* synthetic */ ContentViewRenderView s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ZP(ContentViewRenderView contentViewRenderView, Context context) {
        super(context);
        this.s = contentViewRenderView;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int measuredHeight = getMeasuredHeight();
        if (getMeasuredWidth() == View.MeasureSpec.getSize(i) && measuredHeight > View.MeasureSpec.getSize(i2)) {
            ContentViewRenderView contentViewRenderView = this.s;
            VP vp = contentViewRenderView.u;
            boolean zIsActive = false;
            if (vp != null && vp.a == 0) {
                if (contentViewRenderView.isAttachedToWindow() && contentViewRenderView.getWidth() == contentViewRenderView.getRootView().getWidth()) {
                    zIsActive = ((InputMethodManager) contentViewRenderView.getContext().getSystemService("input_method")).isActive();
                }
            }
            if (zIsActive) {
                i2 = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        ContentViewRenderView contentViewRenderView = this.s;
        contentViewRenderView.z = i;
        contentViewRenderView.A = i2;
    }
}
