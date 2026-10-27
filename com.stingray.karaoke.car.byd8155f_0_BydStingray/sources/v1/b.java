package V1;

import android.text.Editable;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public final class b extends InputConnectionWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final EditText f9313a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Y4.e f9314b;

    public b(EditText editText, InputConnection inputConnection, EditorInfo editorInfo) {
        Y4.e eVar = new Y4.e(11);
        super(inputConnection, false);
        this.f9313a = editText;
        this.f9314b = eVar;
        if (T1.i.c()) {
            T1.i.a().h(editorInfo);
        }
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i7, int i10) {
        Editable editableText = this.f9313a.getEditableText();
        this.f9314b.getClass();
        return Y4.e.E(this, editableText, i7, i10, false) || super.deleteSurroundingText(i7, i10);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i7, int i10) {
        Editable editableText = this.f9313a.getEditableText();
        this.f9314b.getClass();
        return Y4.e.E(this, editableText, i7, i10, true) || super.deleteSurroundingTextInCodePoints(i7, i10);
    }
}
