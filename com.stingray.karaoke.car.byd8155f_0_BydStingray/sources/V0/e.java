package V0;

import N0.AbstractC0418m;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class e extends ClickableSpan {
    public final AbstractC0418m l;

    public e(AbstractC0418m abstractC0418m) {
        this.l = abstractC0418m;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        this.l.getClass();
    }
}
