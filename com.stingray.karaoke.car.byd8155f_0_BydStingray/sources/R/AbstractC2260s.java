package r;

import F1.C0245d;
import F1.C0247f;
import F1.InterfaceC0244c;
import F1.InterfaceC0256o;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import com.stingray.drivemodedetector.R;
import l8.C1789g;

/* JADX INFO: renamed from: r.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2260s extends EditText implements InterfaceC0256o {
    public final i2.q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2215C f21555m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final I1.i f21556n;
    public final C1789g o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C2259r f21557p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC2260s(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.editTextStyle);
        r0.a(context);
        q0.a(this, getContext());
        i2.q qVar = new i2.q(this);
        this.l = qVar;
        qVar.f(attributeSet, R.attr.editTextStyle);
        C2215C c2215c = new C2215C(this);
        this.f21555m = c2215c;
        c2215c.d(attributeSet, R.attr.editTextStyle);
        c2215c.b();
        this.f21556n = new I1.i();
        C1789g c1789g = new C1789g(this);
        this.o = c1789g;
        c1789g.C(attributeSet, R.attr.editTextStyle);
        KeyListener keyListener = getKeyListener();
        if (keyListener instanceof NumberKeyListener) {
            return;
        }
        boolean zIsFocusable = super.isFocusable();
        boolean zIsClickable = super.isClickable();
        boolean zIsLongClickable = super.isLongClickable();
        int inputType = super.getInputType();
        KeyListener keyListenerY = c1789g.y(keyListener);
        if (keyListenerY == keyListener) {
            return;
        }
        super.setKeyListener(keyListenerY);
        super.setRawInputType(inputType);
        super.setFocusable(zIsFocusable);
        super.setClickable(zIsClickable);
        super.setLongClickable(zIsLongClickable);
    }

    private C2259r getSuperCaller() {
        if (this.f21557p == null) {
            this.f21557p = new C2259r(this);
        }
        return this.f21557p;
    }

    @Override // F1.InterfaceC0256o
    public final C0247f a(C0247f c0247f) {
        this.f21556n.getClass();
        return I1.i.a(this, c0247f);
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.b();
        }
        C2215C c2215c = this.f21555m;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return super.getCustomSelectionActionModeCallback();
    }

    public ColorStateList getSupportBackgroundTintList() {
        i2.q qVar = this.l;
        if (qVar != null) {
            return qVar.d();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        i2.q qVar = this.l;
        if (qVar != null) {
            return qVar.e();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        c9.m mVar = this.f21555m.f21423h;
        if (mVar != null) {
            return (ColorStateList) mVar.c;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        c9.m mVar = this.f21555m.f21423h;
        if (mVar != null) {
            return (PorterDuff.Mode) mVar.d;
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        return super.getTextClassifier();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        String[] strArrB;
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f21555m.getClass();
        int i7 = Build.VERSION.SDK_INT;
        if (i7 < 30 && inputConnectionOnCreateInputConnection != null) {
            H7.H.P(editorInfo, getText());
        }
        n7.z.t(inputConnectionOnCreateInputConnection, editorInfo, this);
        if (inputConnectionOnCreateInputConnection != null && i7 <= 30 && (strArrB = F1.H.b(this)) != null) {
            editorInfo.contentMimeTypes = strArrB;
            inputConnectionOnCreateInputConnection = new H1.b(inputConnectionOnCreateInputConnection, new G2.a(this, 1));
        }
        return this.o.D(inputConnectionOnCreateInputConnection, editorInfo);
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        int i7 = Build.VERSION.SDK_INT;
        if (i7 < 30 || i7 >= 33) {
            return;
        }
        ((InputMethodManager) getContext().getSystemService("input_method")).isActive(this);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onDragEvent(DragEvent dragEvent) {
        Activity activity;
        boolean zA = false;
        if (Build.VERSION.SDK_INT < 31 && dragEvent.getLocalState() == null && F1.H.b(this) != null) {
            Context context = getContext();
            while (true) {
                if (!(context instanceof ContextWrapper)) {
                    activity = null;
                    break;
                }
                if (context instanceof Activity) {
                    activity = (Activity) context;
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            if (activity == null) {
                Log.i("ReceiveContent", "Can't handle drop: no activity: view=" + this);
            } else if (dragEvent.getAction() != 1 && dragEvent.getAction() == 3) {
                zA = AbstractC2265x.a(dragEvent, this, activity);
            }
        }
        if (zA) {
            return true;
        }
        return super.onDragEvent(dragEvent);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public final boolean onTextContextMenuItem(int i7) {
        InterfaceC0244c o;
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 31 || F1.H.b(this) == null || !(i7 == 16908322 || i7 == 16908337)) {
            return super.onTextContextMenuItem(i7);
        }
        ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
        ClipData primaryClip = clipboardManager == null ? null : clipboardManager.getPrimaryClip();
        if (primaryClip != null && primaryClip.getItemCount() > 0) {
            if (i10 >= 31) {
                o = new B2.O(primaryClip, 1);
            } else {
                C0245d c0245d = new C0245d();
                c0245d.f2326m = primaryClip;
                c0245d.f2327n = 1;
                o = c0245d;
            }
            o.R(i7 == 16908322 ? 0 : 1);
            F1.H.d(this, o.a());
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.g();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.h(i7);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        C2215C c2215c = this.f21555m;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        C2215C c2215c = this.f21555m;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(callback);
    }

    public void setEmojiCompatEnabled(boolean z6) {
        this.o.E(z6);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.o.y(keyListener));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.j(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.k(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        C2215C c2215c = this.f21555m;
        c2215c.f(colorStateList);
        c2215c.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        C2215C c2215c = this.f21555m;
        c2215c.g(mode);
        c2215c.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        C2215C c2215c = this.f21555m;
        if (c2215c != null) {
            c2215c.e(context, i7);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        super.setTextClassifier(textClassifier);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return super.getText();
    }
}
