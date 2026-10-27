package a5;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public final class b implements View.OnFocusChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f11137a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ p f11138b;

    public /* synthetic */ b(p pVar, int i7) {
        this.f11137a = i7;
        this.f11138b = pVar;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z6) {
        switch (this.f11137a) {
            case 0:
                ((h) this.f11138b).d(!TextUtils.isEmpty(((EditText) view).getText()) && z6);
                break;
            default:
                o oVar = (o) this.f11138b;
                oVar.f11170a.setEndIconActivated(z6);
                if (!z6) {
                    oVar.g(false);
                    oVar.f11164i = false;
                }
                break;
        }
    }
}
