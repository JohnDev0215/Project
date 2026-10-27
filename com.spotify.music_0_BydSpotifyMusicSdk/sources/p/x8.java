package p;

import android.content.res.TypedArray;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public final class x8 {
    public final EditText a;
    private final oc0 b;

    public x8(EditText editText) {
        this.a = editText;
        this.b = new oc0(editText);
    }

    public final KeyListener a(KeyListener keyListener) {
        return (keyListener instanceof NumberKeyListener) ^ true ? this.b.a.B(keyListener) : keyListener;
    }

    public final void b(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, yz1.i, i, 0);
        try {
            boolean z = typedArrayObtainStyledAttributes.hasValue(14) ? typedArrayObtainStyledAttributes.getBoolean(14, true) : true;
            typedArrayObtainStyledAttributes.recycle();
            d(z);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final InputConnection c(InputConnection inputConnection, EditorInfo editorInfo) {
        oc0 oc0Var = this.b;
        if (inputConnection != null) {
            return oc0Var.a.C(inputConnection, editorInfo);
        }
        oc0Var.getClass();
        return null;
    }

    public final void d(boolean z) {
        this.b.a.D(z);
    }
}
