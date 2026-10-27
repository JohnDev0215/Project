package defpackage;

import android.util.Patterns;
import defpackage.xf0;

/* JADX INFO: compiled from: ContactEditor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dy implements xf0.b {
    @Override // xf0.b
    public final boolean isValid(CharSequence charSequence) {
        return charSequence != null && Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }

    @Override // xf0.b
    public final void a() {
    }
}
