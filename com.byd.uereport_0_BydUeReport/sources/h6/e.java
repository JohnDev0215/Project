package h6;

import android.content.Context;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: compiled from: EndIconDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextInputLayout f5407a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f5408b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public CheckableImageButton f5409c;

    public e(TextInputLayout textInputLayout) {
        this.f5407a = textInputLayout;
        this.f5408b = textInputLayout.getContext();
        this.f5409c = textInputLayout.getEndIconView();
    }

    public abstract void a();

    public boolean b(int i8) {
        return true;
    }

    public void c(boolean z7) {
    }

    public boolean d() {
        return false;
    }
}
