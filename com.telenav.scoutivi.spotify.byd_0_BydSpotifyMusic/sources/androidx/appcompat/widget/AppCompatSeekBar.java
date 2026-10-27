package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatSeekBar extends SeekBar {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final t f842e;

    public AppCompatSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, e.a.seekBarStyle);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.f842e.h();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.f842e.i();
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f842e.g(canvas);
    }

    public AppCompatSeekBar(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        n0.a(this, getContext());
        t tVar = new t(this);
        this.f842e = tVar;
        tVar.c(attributeSet, i7);
    }
}
