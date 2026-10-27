package a5;

import android.content.Context;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextInputLayout f11170a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f11171b;
    public final CheckableImageButton c;

    public p(TextInputLayout textInputLayout) {
        this.f11170a = textInputLayout;
        this.f11171b = textInputLayout.getContext();
        this.c = textInputLayout.getEndIconView();
    }

    public abstract void a();

    public boolean b(int i7) {
        return true;
    }

    public void c(boolean z6) {
    }
}
