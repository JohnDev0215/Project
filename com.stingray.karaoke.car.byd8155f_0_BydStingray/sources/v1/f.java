package V1;

import a.AbstractC0904a;
import android.text.InputFilter;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class f extends AbstractC0904a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f9322e;

    public f(TextView textView) {
        this.f9322e = new e(textView);
    }

    @Override // a.AbstractC0904a
    public final InputFilter[] E(InputFilter[] inputFilterArr) {
        return !T1.i.c() ? inputFilterArr : this.f9322e.E(inputFilterArr);
    }

    @Override // a.AbstractC0904a
    public final void Q(boolean z6) {
        if (T1.i.c()) {
            this.f9322e.Q(z6);
        }
    }

    @Override // a.AbstractC0904a
    public final void R(boolean z6) {
        boolean zC = T1.i.c();
        e eVar = this.f9322e;
        if (zC) {
            eVar.R(z6);
        } else {
            eVar.f9321g = z6;
        }
    }
}
