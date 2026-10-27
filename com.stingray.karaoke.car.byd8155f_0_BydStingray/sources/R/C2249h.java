package r;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.stingray.drivemodedetector.R;
import q.ViewOnTouchListenerC2144a;

/* JADX INFO: renamed from: r.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2249h extends C2263v implements InterfaceC2251j {
    public final /* synthetic */ C2250i o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2249h(C2250i c2250i, Context context) {
        super(context, R.attr.actionOverflowButtonStyle);
        this.o = c2250i;
        setClickable(true);
        setFocusable(true);
        setVisibility(0);
        setEnabled(true);
        B0.a(this, getContentDescription());
        setOnTouchListener(new ViewOnTouchListenerC2144a(this, this));
    }

    @Override // r.InterfaceC2251j
    public final boolean a() {
        return false;
    }

    @Override // r.InterfaceC2251j
    public final boolean c() {
        return false;
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        playSoundEffect(0);
        this.o.j();
        return true;
    }

    @Override // android.widget.ImageView
    public final boolean setFrame(int i7, int i10, int i11, int i12) {
        boolean frame = super.setFrame(i7, i10, i11, i12);
        Drawable drawable = getDrawable();
        Drawable background = getBackground();
        if (drawable != null && background != null) {
            int width = getWidth();
            int height = getHeight();
            int iMax = Math.max(width, height) / 2;
            int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
            int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
            background.setHotspotBounds(paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
        }
        return frame;
    }
}
