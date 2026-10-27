package defpackage;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.LinearInterpolator;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$drawable;
import com.google.android.material.R$string;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.a;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class pe0 extends sh0 {
    public AutoCompleteTextView e;
    public final ie0 f;
    public final je0 g;
    public final ke0 h;
    public boolean i;
    public boolean j;
    public boolean k;
    public long l;
    public AccessibilityManager m;
    public ValueAnimator n;
    public ValueAnimator o;

    public pe0(a aVar) {
        super(aVar);
        int i = 0;
        this.f = new ie0(i, this);
        this.g = new je0(i, this);
        this.h = new ke0(i, this);
        this.l = Long.MAX_VALUE;
    }

    @Override // defpackage.sh0
    public final void a() {
        if (this.m.isTouchExplorationEnabled()) {
            if ((this.e.getInputType() != 0) && !this.d.hasFocus()) {
                this.e.dismissDropDown();
            }
        }
        this.e.post(new aw(2, this));
    }

    @Override // defpackage.sh0
    public final int c() {
        return R$string.exposed_dropdown_menu_content_description;
    }

    @Override // defpackage.sh0
    public final int d() {
        return R$drawable.mtrl_dropdown_arrow;
    }

    @Override // defpackage.sh0
    public final View.OnFocusChangeListener e() {
        return this.g;
    }

    @Override // defpackage.sh0
    public final View.OnClickListener f() {
        return this.f;
    }

    @Override // defpackage.sh0
    public final InterfaceC0220y h() {
        return this.h;
    }

    @Override // defpackage.sh0
    public final boolean i(int i) {
        return i != 0;
    }

    @Override // defpackage.sh0
    public final boolean j() {
        return this.i;
    }

    @Override // defpackage.sh0
    public final boolean l() {
        return this.k;
    }

    @Override // defpackage.sh0
    public final void m(EditText editText) {
        if (!(editText instanceof AutoCompleteTextView)) {
            throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
        }
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText;
        this.e = autoCompleteTextView;
        autoCompleteTextView.setOnTouchListener(new me0(0, this));
        this.e.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() { // from class: ne0
            @Override // android.widget.AutoCompleteTextView.OnDismissListener
            public final void onDismiss() {
                pe0 pe0Var = this.a;
                pe0Var.j = true;
                pe0Var.l = System.currentTimeMillis();
                pe0Var.t(false);
            }
        });
        this.e.setThreshold(0);
        TextInputLayout textInputLayout = this.a;
        textInputLayout.setErrorIconDrawable((Drawable) null);
        if ((editText.getInputType() != 0 ? 1 : 0) == 0 && this.m.isTouchExplorationEnabled()) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.p(this.d, 2);
        }
        textInputLayout.setEndIconVisible(true);
    }

    @Override // defpackage.sh0
    public final void n(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (!(this.e.getInputType() != 0)) {
            accessibilityNodeInfoCompat.n(Spinner.class.getName());
        }
        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
        if (accessibilityNodeInfo.isShowingHintText()) {
            accessibilityNodeInfo.setHintText(null);
        }
    }

    @Override // defpackage.sh0
    public final void o(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 1 && this.m.isEnabled()) {
            if (this.e.getInputType() != 0) {
                return;
            }
            u();
            this.j = true;
            this.l = System.currentTimeMillis();
        }
    }

    @Override // defpackage.sh0
    public final void r() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        LinearInterpolator linearInterpolator = u5.a;
        valueAnimatorOfFloat.setInterpolator(linearInterpolator);
        valueAnimatorOfFloat.setDuration(67);
        int i = 0;
        valueAnimatorOfFloat.addUpdateListener(new le0(i, this));
        this.o = valueAnimatorOfFloat;
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat2.setInterpolator(linearInterpolator);
        valueAnimatorOfFloat2.setDuration(50);
        valueAnimatorOfFloat2.addUpdateListener(new le0(i, this));
        this.n = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.addListener(new oe0(this));
        this.m = (AccessibilityManager) this.c.getSystemService("accessibility");
    }

    @Override // defpackage.sh0
    @SuppressLint({"ClickableViewAccessibility"})
    public final void s() {
        AutoCompleteTextView autoCompleteTextView = this.e;
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setOnTouchListener(null);
            this.e.setOnDismissListener(null);
        }
    }

    public final void t(boolean z) {
        if (this.k != z) {
            this.k = z;
            this.o.cancel();
            this.n.start();
        }
    }

    public final void u() {
        if (this.e == null) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - this.l;
        if (jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300) {
            this.j = false;
        }
        if (this.j) {
            this.j = false;
            return;
        }
        t(!this.k);
        if (!this.k) {
            this.e.dismissDropDown();
        } else {
            this.e.requestFocus();
            this.e.showDropDown();
        }
    }
}
