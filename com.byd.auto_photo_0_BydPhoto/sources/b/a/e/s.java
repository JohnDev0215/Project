package b.a.e;

import android.content.Context;
import android.os.Handler;
import android.widget.FrameLayout;
import com.byd.auto_photo.R;
import com.byd.bean.FileBean;
import com.byd.view.CancelableMotionEvent;
import com.byd.view.XRoundCornerImageView;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class s<T> extends t<T> {
    private int q;

    public s(Context context, int i, int i2) {
        super(context, i, i2);
        this.q = context.getResources().getDimensionPixelSize(R.dimen.dialog_ui3_bg_radius);
        this.m = 8;
    }

    @Override // b.a.e.t
    protected void m(t<T>.b bVar) {
        bVar.itemView.setOnTouchListener(new CancelableMotionEvent(bVar));
        int dimensionPixelSize = this.f441b.getResources().getDimensionPixelSize(R.dimen.dp_5);
        ((XRoundCornerImageView) bVar.f444a).setRadius(this.q);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) bVar.f446c.getLayoutParams();
        layoutParams.leftMargin = 0;
        layoutParams.rightMargin = 0;
        layoutParams.topMargin = 0;
        layoutParams.gravity = 5;
        int dimensionPixelSize2 = this.f441b.getResources().getDimensionPixelSize(R.dimen.dp_18);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) bVar.g.getLayoutParams();
        layoutParams2.leftMargin = dimensionPixelSize;
        layoutParams2.bottomMargin = dimensionPixelSize;
        layoutParams2.width = dimensionPixelSize2;
        layoutParams2.height = dimensionPixelSize2;
        bVar.d.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_burst_tips, 0, 0, 0);
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) bVar.d.getLayoutParams();
        layoutParams3.rightMargin = dimensionPixelSize;
        layoutParams3.bottomMargin = dimensionPixelSize;
        layoutParams3.gravity = 85;
        layoutParams3.width = dimensionPixelSize2;
        layoutParams3.height = dimensionPixelSize2;
        int dimensionPixelSize3 = this.f441b.getResources().getDimensionPixelSize(R.dimen.dp_4);
        bVar.h.setPadding(dimensionPixelSize3, dimensionPixelSize3, dimensionPixelSize3, dimensionPixelSize3);
        FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) bVar.e.getLayoutParams();
        layoutParams4.gravity = 80;
        layoutParams4.leftMargin = dimensionPixelSize;
        layoutParams4.bottomMargin = dimensionPixelSize;
    }

    @Override // b.a.e.t
    protected void o(t<T>.b bVar, FileBean fileBean) {
        if (this.i) {
            bVar.f446c.setVisibility(0);
            if (fileBean.isSelect()) {
                bVar.f446c.setImageDrawable(com.byd.util.e.p(this.f441b, R.attr.checkbox_selected));
            } else {
                bVar.f446c.setImageResource(R.drawable.icon_no_selected);
            }
        } else {
            bVar.f446c.setVisibility(8);
        }
        if (!fileBean.isCollected() && fileBean.getSourceType() != 3) {
            bVar.g.setVisibility(8);
        } else {
            bVar.g.setVisibility(0);
            bVar.g.setImageResource(R.drawable.icon_star);
        }
    }

    public void z() {
        if (CancelableMotionEvent.handlers.size() == 0) {
            return;
        }
        while (true) {
            List<Handler> list = CancelableMotionEvent.handlers;
            if (list.isEmpty()) {
                com.byd.util.w.c("clear all item motion");
                return;
            }
            list.remove(0).removeCallbacksAndMessages(null);
        }
    }
}
