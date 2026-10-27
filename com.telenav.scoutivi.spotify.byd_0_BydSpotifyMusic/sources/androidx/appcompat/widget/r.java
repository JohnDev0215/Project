package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;

/* JADX INFO: compiled from: AppCompatRatingBar.java */
/* JADX INFO: loaded from: classes.dex */
public class r extends RatingBar {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final p f1180e;

    public r(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, e.a.ratingBarStyle);
    }

    @Override // android.widget.RatingBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        Bitmap bitmapB = this.f1180e.b();
        if (bitmapB != null) {
            setMeasuredDimension(View.resolveSizeAndState(bitmapB.getWidth() * getNumStars(), i7, 0), getMeasuredHeight());
        }
    }

    public r(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        n0.a(this, getContext());
        p pVar = new p(this);
        this.f1180e = pVar;
        pVar.c(attributeSet, i7);
    }
}
