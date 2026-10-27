package p;

import android.text.method.KeyListener;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class mb0 extends sj2 {
    public final /* synthetic */ pb0 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public mb0(pb0 pb0Var, TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.e = pb0Var;
    }

    @Override // p.sj2, p.o2
    public final void d(View view, c3 c3Var) {
        super.d(view, c3Var);
        KeyListener keyListener = this.e.a.getEditText().getKeyListener();
        AccessibilityNodeInfo accessibilityNodeInfo = c3Var.a;
        if (keyListener == null) {
            accessibilityNodeInfo.setClassName(Spinner.class.getName());
        }
        if (accessibilityNodeInfo.isShowingHintText()) {
            accessibilityNodeInfo.setHintText(null);
        }
    }

    @Override // p.o2
    public final void e(View view, AccessibilityEvent accessibilityEvent) {
        super.e(view, accessibilityEvent);
        pb0 pb0Var = this.e;
        EditText editText = pb0Var.a.getEditText();
        if (!(editText instanceof AutoCompleteTextView)) {
            throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
        }
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText;
        if (accessibilityEvent.getEventType() == 1 && pb0Var.o.isTouchExplorationEnabled() && pb0Var.a.getEditText().getKeyListener() == null) {
            pb0.d(pb0Var, autoCompleteTextView);
        }
    }
}
