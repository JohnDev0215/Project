package b.d.a.a.h0;

import android.content.Context;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextInputLayout f1927a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f1928b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public CheckableImageButton f1929c;

    public m(TextInputLayout textInputLayout) {
        this.f1927a = textInputLayout;
        this.f1928b = textInputLayout.getContext();
        this.f1929c = textInputLayout.getEndIconView();
    }

    public abstract void a();

    public boolean b(int i) {
        return true;
    }

    public void c(boolean z) {
    }

    public boolean d() {
        return false;
    }
}
