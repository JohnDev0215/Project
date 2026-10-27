package p;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class rf1<S> extends ws1 {
    public int c0;
    public ip d0;

    @Override // p.ww0
    public final void r(Bundle bundle) {
        super.r(bundle);
        if (bundle == null) {
            bundle = this.n;
        }
        this.c0 = bundle.getInt("THEME_RES_ID_KEY");
        u4.t(bundle.getParcelable("DATE_SELECTOR_KEY"));
        this.d0 = (ip) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }

    @Override // p.ww0
    public final View s(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        layoutInflater.cloneInContext(new ContextThemeWrapper(e(), this.c0));
        throw null;
    }

    @Override // p.ww0
    public final void w(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.c0);
        bundle.putParcelable("DATE_SELECTOR_KEY", null);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.d0);
    }
}
