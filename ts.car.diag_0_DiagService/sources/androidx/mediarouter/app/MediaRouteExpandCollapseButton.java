package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.C0066l;
import androidx.mediarouter.R$drawable;
import androidx.mediarouter.R$string;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
class MediaRouteExpandCollapseButton extends C0066l {
    final AnimationDrawable e;
    final AnimationDrawable f;
    final String g;
    final String h;
    boolean i;
    View.OnClickListener j;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = MediaRouteExpandCollapseButton.this;
            boolean z = !mediaRouteExpandCollapseButton.i;
            mediaRouteExpandCollapseButton.i = z;
            if (z) {
                mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.e);
                MediaRouteExpandCollapseButton.this.e.start();
                MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton2 = MediaRouteExpandCollapseButton.this;
                mediaRouteExpandCollapseButton2.setContentDescription(mediaRouteExpandCollapseButton2.h);
            } else {
                mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.f);
                MediaRouteExpandCollapseButton.this.f.start();
                MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton3 = MediaRouteExpandCollapseButton.this;
                mediaRouteExpandCollapseButton3.setContentDescription(mediaRouteExpandCollapseButton3.g);
            }
            View.OnClickListener onClickListener = MediaRouteExpandCollapseButton.this.j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public MediaRouteExpandCollapseButton(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.j = onClickListener;
    }

    public MediaRouteExpandCollapseButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MediaRouteExpandCollapseButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = (AnimationDrawable) androidx.core.content.a.d(context, R$drawable.mr_group_expand);
        this.f = (AnimationDrawable) androidx.core.content.a.d(context, R$drawable.mr_group_collapse);
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(j.f(context, i), PorterDuff.Mode.SRC_IN);
        this.e.setColorFilter(porterDuffColorFilter);
        this.f.setColorFilter(porterDuffColorFilter);
        this.g = context.getString(R$string.mr_controller_expand_group);
        this.h = context.getString(R$string.mr_controller_collapse_group);
        setImageDrawable(this.e.getFrame(0));
        setContentDescription(this.g);
        super.setOnClickListener(new a());
    }
}
