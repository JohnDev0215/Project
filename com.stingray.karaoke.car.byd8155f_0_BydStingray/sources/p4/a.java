package P4;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Runnable {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5331m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ View f5332n;
    public final /* synthetic */ Object o;

    public a(TextView textView, Typeface typeface, int i7) {
        this.f5332n = textView;
        this.o = typeface;
        this.f5331m = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                ((BottomSheetBehavior) this.o).A(this.f5332n, this.f5331m);
                break;
            default:
                ((TextView) this.f5332n).setTypeface((Typeface) this.o, this.f5331m);
                break;
        }
    }

    public a(BottomSheetBehavior bottomSheetBehavior, View view, int i7) {
        this.o = bottomSheetBehavior;
        this.f5332n = view;
        this.f5331m = i7;
    }
}
