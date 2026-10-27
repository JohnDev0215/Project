package a5;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class l extends u {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ o f11157e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(o oVar, TextInputLayout textInputLayout) {
        super(textInputLayout);
        this.f11157e = oVar;
    }

    @Override // a5.u, F1.C0243b
    public final void d(View view, G1.e eVar) {
        super.d(view, eVar);
        if (!o.f(this.f11157e.f11170a.getEditText())) {
            eVar.h("android.widget.Spinner");
        }
        AccessibilityNodeInfo accessibilityNodeInfo = eVar.f2646a;
        if (accessibilityNodeInfo.isShowingHintText()) {
            accessibilityNodeInfo.setHintText(null);
        }
    }

    @Override // F1.C0243b
    public final void e(View view, AccessibilityEvent accessibilityEvent) {
        super.e(view, accessibilityEvent);
        o oVar = this.f11157e;
        EditText editText = oVar.f11170a.getEditText();
        if (!(editText instanceof AutoCompleteTextView)) {
            throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
        }
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText;
        if (accessibilityEvent.getEventType() == 1 && oVar.f11168n.isTouchExplorationEnabled() && !o.f(oVar.f11170a.getEditText())) {
            o.d(oVar, autoCompleteTextView);
        }
    }
}
