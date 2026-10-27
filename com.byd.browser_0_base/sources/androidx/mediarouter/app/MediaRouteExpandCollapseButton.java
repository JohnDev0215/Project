package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.mediarouter.R$drawable;
import androidx.mediarouter.R$string;
import defpackage.bz;

/* JADX INFO: loaded from: classes.dex */
class MediaRouteExpandCollapseButton extends AppCompatImageButton {
    public final AnimationDrawable h;
    public final AnimationDrawable i;
    public final String j;
    public final String k;
    public boolean l;
    public View.OnClickListener m;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = MediaRouteExpandCollapseButton.this;
            boolean z = !mediaRouteExpandCollapseButton.l;
            mediaRouteExpandCollapseButton.l = z;
            if (z) {
                mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.h);
                mediaRouteExpandCollapseButton.h.start();
                mediaRouteExpandCollapseButton.setContentDescription(mediaRouteExpandCollapseButton.k);
            } else {
                mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.i);
                mediaRouteExpandCollapseButton.i.start();
                mediaRouteExpandCollapseButton.setContentDescription(mediaRouteExpandCollapseButton.j);
            }
            View.OnClickListener onClickListener = mediaRouteExpandCollapseButton.m;
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
        this.m = onClickListener;
    }

    public MediaRouteExpandCollapseButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MediaRouteExpandCollapseButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2 = R$drawable.mr_group_expand;
        Object obj = bz.a;
        AnimationDrawable animationDrawable = (AnimationDrawable) context.getDrawable(i2);
        this.h = animationDrawable;
        AnimationDrawable animationDrawable2 = (AnimationDrawable) context.getDrawable(R$drawable.mr_group_collapse);
        this.i = animationDrawable2;
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(f.c(context, i), PorterDuff.Mode.SRC_IN);
        animationDrawable.setColorFilter(porterDuffColorFilter);
        animationDrawable2.setColorFilter(porterDuffColorFilter);
        String string = context.getString(R$string.mr_controller_expand_group);
        this.j = string;
        this.k = context.getString(R$string.mr_controller_collapse_group);
        setImageDrawable(animationDrawable.getFrame(0));
        setContentDescription(string);
        super.setOnClickListener(new a());
    }
}
