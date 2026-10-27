package defpackage;

import android.widget.EditText;

/* JADX INFO: compiled from: EmojiEditTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class kg0 {
    public final a a;

    /* JADX INFO: compiled from: EmojiEditTextHelper.java */
    public static class a extends b {
        public final EditText a;
        public final rg0 b;

        public a(EditText editText) {
            this.a = editText;
            rg0 rg0Var = new rg0(editText);
            this.b = rg0Var;
            editText.addTextChangedListener(rg0Var);
            if (lg0.b == null) {
                synchronized (lg0.a) {
                    if (lg0.b == null) {
                        lg0.b = new lg0();
                    }
                }
            }
            editText.setEditableFactory(lg0.b);
        }
    }

    /* JADX INFO: compiled from: EmojiEditTextHelper.java */
    public static class b {
    }

    public kg0(EditText editText) {
        if (editText == null) {
            throw new NullPointerException("editText cannot be null");
        }
        this.a = new a(editText);
    }
}
