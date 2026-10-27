package b.d.a.a.z;

import android.annotation.SuppressLint;
import android.widget.ImageButton;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class p extends ImageButton {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2196a;

    public final void b(int i, boolean z) {
        super.setVisibility(i);
        if (z) {
            this.f2196a = i;
        }
    }

    public final int getUserSetVisibility() {
        return this.f2196a;
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        this.f2196a = i;
    }
}
