package a.b.p;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes.dex */
public class t extends SeekBar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f361a;

    /* JADX WARN: Illegal instructions before constructor call */
    public t(Context context, AttributeSet attributeSet) {
        int i = a.b.a.seekBarStyle;
        super(context, attributeSet, i);
        s0.a(this, getContext());
        u uVar = new u(this);
        this.f361a = uVar;
        uVar.a(attributeSet, i);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        u uVar = this.f361a;
        Drawable drawable = uVar.f363e;
        if (drawable != null && drawable.isStateful() && drawable.setState(uVar.f362d.getDrawableState())) {
            uVar.f362d.invalidateDrawable(drawable);
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f361a.f363e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f361a.d(canvas);
    }
}
