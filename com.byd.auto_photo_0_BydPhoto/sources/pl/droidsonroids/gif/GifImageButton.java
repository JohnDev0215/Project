package pl.droidsonroids.gif;

import android.net.Uri;
import android.os.Parcelable;
import android.widget.ImageButton;

/* JADX INFO: loaded from: classes2.dex */
public class GifImageButton extends ImageButton {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f3173a;

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
        return new GifViewSavedState(super.onSaveInstanceState(), this.f3173a ? getDrawable() : null, this.f3173a ? getBackground() : null);
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        if (f.e(this, false, i)) {
            return;
        }
        super.setBackgroundResource(i);
    }

    public void setFreezesAnimation(boolean z) {
        this.f3173a = z;
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
