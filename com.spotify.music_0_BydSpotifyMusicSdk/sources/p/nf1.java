package p;

import android.R;
import android.content.res.ColorStateList;

/* JADX INFO: loaded from: classes.dex */
public final class nf1 extends f9 {
    public static final int[][] o = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public ColorStateList m;
    public boolean n;

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.m == null) {
            int iQ = f60.q(this, com.spotify.music.R.attr.colorControlActivated);
            int iQ2 = f60.q(this, com.spotify.music.R.attr.colorOnSurface);
            int iQ3 = f60.q(this, com.spotify.music.R.attr.colorSurface);
            this.m = new ColorStateList(o, new int[]{f60.x(iQ3, iQ, 1.0f), f60.x(iQ3, iQ2, 0.54f), f60.x(iQ3, iQ2, 0.38f), f60.x(iQ3, iQ2, 0.38f)});
        }
        return this.m;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.n && iw.a(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.n = z;
        if (z) {
            iw.c(this, getMaterialThemeColorsTintList());
        } else {
            iw.c(this, null);
        }
    }
}
