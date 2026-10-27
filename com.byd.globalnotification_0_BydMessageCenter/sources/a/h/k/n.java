package a.h.k;

import a.h.k.q;
import android.text.TextUtils;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class n extends q.a<CharSequence> {
    public n(int i, Class cls, int i2, int i3) {
        super(i, cls, i2, i3);
    }

    @Override // a.h.k.q.a
    public CharSequence b(View view) {
        return view.getAccessibilityPaneTitle();
    }

    @Override // a.h.k.q.a
    public void c(View view, CharSequence charSequence) {
        view.setAccessibilityPaneTitle(charSequence);
    }

    @Override // a.h.k.q.a
    public boolean e(CharSequence charSequence, CharSequence charSequence2) {
        return !TextUtils.equals(charSequence, charSequence2);
    }
}
