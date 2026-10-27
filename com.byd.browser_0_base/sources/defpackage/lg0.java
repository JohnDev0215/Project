package defpackage;

import android.annotation.SuppressLint;
import android.text.Editable;

/* JADX INFO: compiled from: EmojiEditableFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class lg0 extends Editable.Factory {
    public static final Object a = new Object();
    public static volatile lg0 b;
    public static Class<?> c;

    @SuppressLint({"PrivateApi"})
    public lg0() {
        try {
            c = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, lg0.class.getClassLoader());
        } catch (Throwable unused) {
        }
    }

    @Override // android.text.Editable.Factory
    public final Editable newEditable(CharSequence charSequence) {
        Class<?> cls = c;
        return cls != null ? new kq2(cls, charSequence) : super.newEditable(charSequence);
    }
}
