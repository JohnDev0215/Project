package p;

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
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public class w8 extends EditText implements cq1 {
    private final v7 c;
    private final ca e;
    private final v9 k;
    public final gk2 l;
    private final x8 m;
    public v8 n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w8(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.editTextStyle);
        wl2.a(context);
        ik2.a(this, getContext());
        v7 v7Var = new v7(this);
        this.c = v7Var;
        v7Var.d(attributeSet, R.attr.editTextStyle);
        ca caVar = new ca(this);
        this.e = caVar;
        caVar.m(attributeSet, R.attr.editTextStyle);
        caVar.b();
        v9 v9Var = new v9();
        v9Var.a = this;
        this.k = v9Var;
        this.l = new gk2();
        x8 x8Var = new x8(this);
        this.m = x8Var;
        x8Var.b(attributeSet, R.attr.editTextStyle);
        KeyListener keyListener = getKeyListener();
        if (!(keyListener instanceof NumberKeyListener)) {
            boolean zIsFocusable = super.isFocusable();
            boolean zIsClickable = super.isClickable();
            boolean zIsLongClickable = super.isLongClickable();
            int inputType = super.getInputType();
            KeyListener keyListenerA = x8Var.a(keyListener);
            if (keyListenerA == keyListener) {
                return;
            }
            super.setKeyListener(keyListenerA);
            super.setRawInputType(inputType);
            super.setFocusable(zIsFocusable);
            super.setClickable(zIsClickable);
            super.setLongClickable(zIsLongClickable);
        }
    }

    private v8 getSuperCaller() {
        if (this.n == null) {
            this.n = new v8(this);
        }
        return this.n;
    }

    @Override // p.cq1
    public final jz a(jz jzVar) {
        return this.l.a(this, jzVar);
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.a();
        }
        ca caVar = this.e;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        ActionMode.Callback customSelectionActionModeCallback = super.getCustomSelectionActionModeCallback();
        return customSelectionActionModeCallback instanceof fk2 ? ((fk2) customSelectionActionModeCallback).a : customSelectionActionModeCallback;
    }

    public ColorStateList getSupportBackgroundTintList() {
        v7 v7Var = this.c;
        if (v7Var != null) {
            return v7Var.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        v7 v7Var = this.c;
        if (v7Var != null) {
            return v7Var.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.e.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.e.k();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        v9 v9Var;
        if (Build.VERSION.SDK_INT >= 28 || (v9Var = this.k) == null) {
            return super.getTextClassifier();
        }
        TextClassifier textClassifier = v9Var.b;
        return textClassifier == null ? u9.a(v9Var.a) : textClassifier;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        String[] strArrE;
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.e.getClass();
        ca.o(this, inputConnectionOnCreateInputConnection, editorInfo);
        ln1.t(this, editorInfo, inputConnectionOnCreateInputConnection);
        if (inputConnectionOnCreateInputConnection != null && Build.VERSION.SDK_INT <= 30 && (strArrE = zt2.e(this)) != null) {
            editorInfo.contentMimeTypes = strArrE;
            inputConnectionOnCreateInputConnection = new q41(inputConnectionOnCreateInputConnection, new p41(this));
        }
        return this.m.c(inputConnectionOnCreateInputConnection, editorInfo);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onDragEvent(DragEvent dragEvent) {
        Activity activity;
        if (Build.VERSION.SDK_INT < 31 && dragEvent.getLocalState() == null && zt2.e(this) != null) {
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
                toString();
            } else if (dragEvent.getAction() != 1 && dragEvent.getAction() == 3 && h9.a(dragEvent, this, activity)) {
                return true;
            }
        }
        return super.onDragEvent(dragEvent);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public final boolean onTextContextMenuItem(int i) {
        if (Build.VERSION.SDK_INT >= 31 || zt2.e(this) == null || !(i == 16908322 || i == 16908337)) {
            return super.onTextContextMenuItem(i);
        }
        ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
        ClipData primaryClip = clipboardManager == null ? null : clipboardManager.getPrimaryClip();
        if (primaryClip != null && primaryClip.getItemCount() > 0) {
            oh1 oh1Var = new oh1(primaryClip, 1);
            ((gz) oh1Var.e).c(i == 16908322 ? 0 : 1);
            zt2.g(this, ((gz) oh1Var.e).build());
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.f(i);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        ca caVar = this.e;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        ca caVar = this.e;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(f60.T(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z) {
        this.m.d(z);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.m.a(keyListener));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.i(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.e.s(colorStateList);
        this.e.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.e.t(mode);
        this.e.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        ca caVar = this.e;
        if (caVar != null) {
            caVar.n(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        v9 v9Var;
        if (Build.VERSION.SDK_INT >= 28 || (v9Var = this.k) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            v9Var.b = textClassifier;
        }
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }
}
