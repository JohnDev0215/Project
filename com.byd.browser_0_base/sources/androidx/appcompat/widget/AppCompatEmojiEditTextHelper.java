package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.appcompat.R;
import androidx.emoji2.text.d;
import defpackage.kg0;
import defpackage.mg0;
import defpackage.n3;
import defpackage.og0;
import defpackage.rg0;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
class AppCompatEmojiEditTextHelper {
    public final EditText a;
    public final kg0 b;

    public AppCompatEmojiEditTextHelper(EditText editText) {
        this.a = editText;
        this.b = new kg0(editText);
    }

    public final KeyListener a(KeyListener keyListener) {
        if (!(!(keyListener instanceof NumberKeyListener))) {
            return keyListener;
        }
        this.b.a.getClass();
        if (keyListener instanceof og0) {
            return keyListener;
        }
        if (keyListener == null) {
            return null;
        }
        return keyListener instanceof NumberKeyListener ? keyListener : new og0(keyListener);
    }

    public final void b(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, i, 0);
        try {
            int i2 = R.styleable.AppCompatTextView_emojiCompatEnabled;
            boolean z = typedArrayObtainStyledAttributes.hasValue(i2) ? typedArrayObtainStyledAttributes.getBoolean(i2, true) : true;
            typedArrayObtainStyledAttributes.recycle();
            d(z);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final InputConnection c(InputConnection inputConnection, EditorInfo editorInfo) {
        kg0 kg0Var = this.b;
        if (inputConnection == null) {
            kg0Var.getClass();
            return null;
        }
        kg0.a aVar = kg0Var.a;
        aVar.getClass();
        if (!(inputConnection instanceof mg0)) {
            inputConnection = new mg0(aVar.a, inputConnection, editorInfo);
        }
        return inputConnection;
    }

    public final void d(boolean z) {
        rg0 rg0Var = this.b.a.b;
        if (rg0Var.h != z) {
            if (rg0Var.g != null) {
                d dVarA = d.a();
                rg0.a aVar = rg0Var.g;
                dVarA.getClass();
                n3.v(aVar, "initCallback cannot be null");
                ReentrantReadWriteLock reentrantReadWriteLock = dVarA.a;
                reentrantReadWriteLock.writeLock().lock();
                try {
                    dVarA.b.remove(aVar);
                } finally {
                    reentrantReadWriteLock.writeLock().unlock();
                }
            }
            rg0Var.h = z;
            if (z) {
                rg0.a(rg0Var.e, d.a().b());
            }
        }
    }
}
