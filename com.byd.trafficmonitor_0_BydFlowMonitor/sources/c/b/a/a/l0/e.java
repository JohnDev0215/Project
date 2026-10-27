package c.b.a.a.l0;

import android.content.Context;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: compiled from: EndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextInputLayout f3984a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f3985b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public CheckableImageButton f3986c;

    public e(TextInputLayout textInputLayout) {
        this.f3984a = textInputLayout;
        this.f3985b = textInputLayout.getContext();
        this.f3986c = textInputLayout.getEndIconView();
    }

    public abstract void a();

    public boolean b(int i2) {
        return true;
    }

    public void c(boolean z) {
    }

    public boolean d() {
        return false;
    }
}
