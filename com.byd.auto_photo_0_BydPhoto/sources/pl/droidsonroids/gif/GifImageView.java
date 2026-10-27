package pl.droidsonroids.gif;

import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import pl.droidsonroids.gif.f;

/* JADX INFO: loaded from: classes2.dex */
public class GifImageView extends ImageView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f3174a;

    public GifImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(f.c(this, attributeSet, 0, 0));
    }

    private void a(f.a aVar) {
        this.f3174a = aVar.f3198a;
        int i = aVar.f3197c;
        if (i > 0) {
            super.setImageResource(i);
        }
        int i2 = aVar.d;
        if (i2 > 0) {
            super.setBackgroundResource(i2);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof GifViewSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        GifViewSavedState gifViewSavedState = (GifViewSavedState) parcelable;
        super.onRestoreInstanceState(gifViewSavedState.getSuperState());
        gifViewSavedState.a(getDrawable(), 0);
        gifViewSavedState.a(getBackground(), 1);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        return new GifViewSavedState(super.onSaveInstanceState(), this.f3174a ? getDrawable() : null, this.f3174a ? getBackground() : null);
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        if (f.e(this, false, i)) {
            return;
        }
        super.setBackgroundResource(i);
    }

    public void setFreezesAnimation(boolean z) {
        this.f3174a = z;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        if (f.e(this, true, i)) {
            return;
        }
        super.setImageResource(i);
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        if (f.d(this, uri)) {
            return;
        }
        super.setImageURI(uri);
    }
}
