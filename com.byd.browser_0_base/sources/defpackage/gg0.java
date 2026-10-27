package defpackage;

import android.view.View;
import android.widget.AutoCompleteTextView;
import defpackage.om3;
import java.util.WeakHashMap;
import org.chromium.components.autofill.prefeditor.EditorTextField;

/* JADX INFO: compiled from: EditorTextField.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gg0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ EditorTextField e;

    public gg0(EditorTextField editorTextField) {
        this.e = editorTextField;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        EditorTextField editorTextField = this.e;
        AutoCompleteTextView autoCompleteTextView = editorTextField.g;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(autoCompleteTextView, om3.e.e(autoCompleteTextView), editorTextField.g.getPaddingTop(), editorTextField.h.getWidth(), editorTextField.g.getPaddingBottom());
    }
}
