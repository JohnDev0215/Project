package c.b.a.a.y;

import android.content.Context;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: compiled from: EndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextInputLayout f1931a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f1932b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public CheckableImageButton f1933c;

    public e(TextInputLayout textInputLayout) {
        this.f1931a = textInputLayout;
        this.f1932b = textInputLayout.getContext();
        this.f1933c = textInputLayout.getEndIconView();
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
