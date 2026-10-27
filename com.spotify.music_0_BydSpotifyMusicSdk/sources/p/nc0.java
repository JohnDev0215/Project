package p;

import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
public final class nc0 extends zb0 {
    public final EditText s;
    public final bd0 t;

    public nc0(EditText editText) {
        this.s = editText;
        bd0 bd0Var = new bd0(editText);
        this.t = bd0Var;
        editText.addTextChangedListener(bd0Var);
        if (pc0.b == null) {
            synchronized (pc0.a) {
                try {
                    if (pc0.b == null) {
                        pc0 pc0Var = new pc0();
                        try {
                            pc0.c = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, pc0.class.getClassLoader());
                        } catch (Throwable unused) {
                        }
                        pc0.b = pc0Var;
                    }
                } finally {
                }
            }
        }
        editText.setEditableFactory(pc0.b);
    }

    public final KeyListener B(KeyListener keyListener) {
        if (keyListener instanceof tc0) {
            return keyListener;
        }
        if (keyListener == null) {
            return null;
        }
        return keyListener instanceof NumberKeyListener ? keyListener : new tc0(keyListener);
    }

    public final InputConnection C(InputConnection inputConnection, EditorInfo editorInfo) {
        return inputConnection instanceof qc0 ? inputConnection : new qc0(this.s, inputConnection, editorInfo);
    }

    public final void D(boolean z) {
        bd0 bd0Var = this.t;
        if (bd0Var.l != z) {
            if (bd0Var.k != null) {
                jc0 jc0VarA = jc0.a();
                ad0 ad0Var = bd0Var.k;
                jc0VarA.getClass();
                p02.e(ad0Var, "initCallback cannot be null");
                ReentrantReadWriteLock reentrantReadWriteLock = jc0VarA.a;
                reentrantReadWriteLock.writeLock().lock();
                try {
                    jc0VarA.b.remove(ad0Var);
                } finally {
                    reentrantReadWriteLock.writeLock().unlock();
                }
            }
            bd0Var.l = z;
            if (z) {
                bd0.a(bd0Var.c, jc0.a().b());
            }
        }
    }
}
