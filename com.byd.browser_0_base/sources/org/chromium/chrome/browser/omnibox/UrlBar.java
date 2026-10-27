package org.chromium.chrome.browser.omnibox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.provider.Settings;
import android.text.Editable;
import android.text.Layout;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.autofill.AutofillManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import defpackage.b83;
import defpackage.it3;
import defpackage.me;
import defpackage.n3;
import defpackage.qm0;
import defpackage.r41;
import defpackage.t41;
import defpackage.zc0;
import defpackage.zm0;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.components.browser_ui.share.ShareHelper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class UrlBar extends AutocompleteEditText {
    public final r41 A;
    public boolean B;
    public boolean C;
    public MotionEvent D;
    public boolean E;
    public boolean F;
    public int G;
    public int H;
    public String I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f71J;
    public int K;
    public float L;
    public boolean M;
    public CharSequence N;
    public final int[] O;
    public float P;
    public int Q;
    public int R;
    public CharSequence S;
    public boolean T;
    public int t;
    public c u;
    public e v;
    public TextWatcher w;
    public d x;
    public Callback<Integer> y;
    public final GestureDetector z;

    public class a extends GestureDetector.SimpleOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final void onLongPress(MotionEvent motionEvent) {
            UrlBar urlBar = UrlBar.this;
            c cVar = urlBar.u;
            if (cVar == null) {
                return;
            }
            cVar.v(true);
            urlBar.performLongClick();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onSingleTapUp(MotionEvent motionEvent) {
            UrlBar urlBar = UrlBar.this;
            if (urlBar.u == null) {
                return true;
            }
            urlBar.requestFocus();
            urlBar.u.v(false);
            return true;
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            t41.f.i(UrlBar.this);
        }
    }

    public interface c {
        void P();

        View l();

        boolean q();

        void v(boolean z);
    }

    public interface d {
        String a();

        String c(int i, int i2, String str);
    }

    public interface e {
        void b(String str, String str2);
    }

    public UrlBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = true;
        this.O = new int[2];
        this.t = 3;
        setFocusable(false);
        setFocusableInTouchMode(false);
        new zm0(this, new qm0(15, this)).a(false);
        setInputType(524305);
        String string = Settings.Secure.getString(getContext().getContentResolver(), "default_input_method");
        if (string != null && string.contains("com.htc.android.htcime")) {
            setInputType(getInputType() | 176);
        }
        GestureDetector gestureDetector = new GestureDetector(getContext(), new a(), ThreadUtils.c());
        this.z = gestureDetector;
        gestureDetector.setOnDoubleTapListener(null);
        this.A = new r41(this, new zc0(22, this));
        setTextClassifier(TextClassifier.NO_OP);
    }

    private int getVisibleMeasuredViewportWidth() {
        return getMeasuredWidth() - (getPaddingRight() + getPaddingLeft());
    }

    @Override // org.chromium.chrome.browser.omnibox.AutocompleteEditText, defpackage.ia
    public final void b() {
        if (this.v == null) {
            return;
        }
        Log.w("cr_UrlBar", "Text change observed, triggering autocomplete.");
        this.v.b(getTextWithoutAutocomplete(), getTextWithAutocomplete());
    }

    @Override // org.chromium.chrome.browser.omnibox.AutocompleteEditText, org.chromium.components.browser_ui.widget.text.VerticallyFixedEditText, android.widget.TextView
    public final boolean bringPointIntoView(int i) {
        if (this.B) {
            return super.bringPointIntoView(i);
        }
        return false;
    }

    @Override // android.view.View
    public final View focusSearch(int i) {
        c cVar = this.u;
        return (cVar == null || i != 1 || cVar.l() == null) ? super.focusSearch(i) : this.u.l();
    }

    public final void g() {
        if (getContext() instanceof ChromeTabbedActivity) {
            setTextAlignment(5);
            return;
        }
        if (this.B || length() == 0) {
            setTextDirection(0);
        } else {
            setTextDirection(3);
        }
        setTextAlignment(2);
    }

    @Override // android.widget.EditText, android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return isEnabled() ? super.getAccessibilityClassName() : TextView.class.getName();
    }

    public int getUrlDirection() {
        return this.t;
    }

    public CharSequence getVisibleTextPrefixHint() {
        if (getVisibleMeasuredViewportWidth() != this.f71J) {
            return null;
        }
        return this.N;
    }

    public final void h(int i) {
        int iMax;
        float fMax;
        this.F = false;
        if (this.B) {
            return;
        }
        Editable text = getText();
        int i2 = TextUtils.isEmpty(text) ? 2 : i;
        setSelection(0);
        float textSize = getTextSize();
        boolean z = getLayoutDirection() == 1;
        int visibleMeasuredViewportWidth = getVisibleMeasuredViewportWidth();
        if (i2 == this.H && TextUtils.equals(text, this.I) && visibleMeasuredViewportWidth == this.f71J && textSize == this.L && z == this.M) {
            scrollTo(this.K, getScrollY());
            return;
        }
        float fMax2 = 0.0f;
        if (i2 == 1) {
            Editable text2 = getText();
            int visibleMeasuredViewportWidth2 = getVisibleMeasuredViewportWidth();
            int length = text2.length();
            Layout layout = getLayout();
            int iMin = Math.min(this.Q, length);
            float primaryHorizontal = layout.getPrimaryHorizontal(iMin);
            if ((length == 1 ? 0.0f : layout.getPrimaryHorizontal(Math.max(0, iMin - 1))) < primaryHorizontal) {
                float f = visibleMeasuredViewportWidth2;
                fMax = Math.max(0.0f, primaryHorizontal - f);
                if (primaryHorizontal > f) {
                    this.N = text2.subSequence(0, Math.min(iMin + 1, length));
                } else {
                    int offsetForHorizontal = layout.getOffsetForHorizontal(0, f);
                    if (offsetForHorizontal != length || layout.getPrimaryHorizontal(offsetForHorizontal) > f) {
                        this.N = text2.subSequence(0, Math.min(offsetForHorizontal + 1, length));
                    } else {
                        this.N = null;
                    }
                }
            } else {
                this.N = null;
                int i3 = iMin - 1;
                int i4 = iMin - 2;
                while (true) {
                    int i5 = i4;
                    iMax = i3;
                    i3 = i5;
                    if (i3 >= 0) {
                        if (layout.getPrimaryHorizontal(i3) <= primaryHorizontal) {
                            iMax = Math.max(0, iMax - 1);
                            break;
                        }
                        i4 = i3 - 1;
                    } else {
                        break;
                    }
                }
                float fMeasureText = layout.getPaint().measureText(text2.subSequence(iMax, iMin).toString());
                float f2 = visibleMeasuredViewportWidth2;
                fMax = fMeasureText < f2 ? Math.max(0.0f, (primaryHorizontal + fMeasureText) - f2) : primaryHorizontal + f2;
            }
            scrollTo((int) fMax, getScrollY());
        } else {
            if (i2 != 2) {
                return;
            }
            this.N = null;
            Editable text3 = getText();
            if (!TextUtils.isEmpty(text3)) {
                me meVarC = me.c();
                meVarC.getClass();
                if (((b83.c) meVarC.c).b(text3, text3.length())) {
                    fMax2 = Math.max(0.0f, (getLayout().getPrimaryHorizontal(text3.length()) - getMeasuredWidth()) + getLayout().getPaint().measureText(text3.toString()));
                }
            } else if (getLayoutDirection() == 1) {
                me meVarC2 = me.c();
                CharSequence hint = getHint();
                meVarC2.getClass();
                if (((b83.c) meVarC2.c).b(hint, hint.length())) {
                    fMax2 = ((int) getLayout().getPrimaryHorizontal(0)) - getMeasuredWidth();
                }
            }
            scrollTo((int) fMax2, getScrollY());
        }
        this.H = i2;
        this.I = text.toString();
        this.f71J = visibleMeasuredViewportWidth;
        this.L = textSize;
        this.K = getScrollX();
        this.M = z;
    }

    @Override // org.chromium.chrome.browser.omnibox.AutocompleteEditText, androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        c cVar = this.u;
        if (cVar == null || !cVar.q()) {
            editorInfo.imeOptions |= 16777216;
        }
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getLayout() == null || 3 == this.t) {
            return;
        }
        this.t = 3;
        Callback<Integer> callback = this.y;
        if (callback != null) {
            callback.onResult(3);
        }
        if (isLayoutRequested()) {
            this.F = this.R != 0;
        } else {
            h(this.R);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setPrivateImeOptions("restrictDirectWritingArea=true");
    }

    @Override // org.chromium.chrome.browser.omnibox.AutocompleteEditText, android.widget.TextView, android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        this.B = z;
        super.onFocusChanged(z, i, rect);
        if (z) {
            this.F = false;
        }
        g();
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (4 == i && keyEvent.getAction() == 1) {
            r41 r41Var = this.A;
            r41Var.b();
            View view = r41Var.e;
            if (view.getResources().getConfiguration().keyboard != 2) {
                view.getViewTreeObserver().addOnGlobalLayoutListener(r41Var);
                r41Var.j = true;
                r41Var.k = r41Var.a();
                view.postDelayed(r41Var.g, 1000L);
            }
        }
        return super.onKeyPreIme(i, keyEvent);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.F || this.G != i3 - i) {
            h(this.R);
        }
        this.G = i3 - i;
    }

    @Override // org.chromium.chrome.browser.omnibox.AutocompleteEditText, android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        AutofillManager autofillManager = (AutofillManager) getContext().getSystemService(AutofillManager.class);
        if (autofillManager != null) {
            autofillManager.notifyValueChanged(this);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.EditText, android.widget.TextView
    public final boolean onTextContextMenuItem(int i) {
        d dVar = this.x;
        if (dVar == null) {
            return super.onTextContextMenuItem(i);
        }
        int i2 = 0;
        if (i == 16908322) {
            CharSequence charSequenceA = dVar.a();
            if (charSequenceA != null) {
                int length = getText().length();
                if (isFocused()) {
                    int selectionStart = getSelectionStart();
                    int selectionEnd = getSelectionEnd();
                    int iMax = Math.max(0, Math.min(selectionStart, selectionEnd));
                    length = Math.max(0, Math.max(selectionStart, selectionEnd));
                    i2 = iMax;
                }
                Selection.setSelection(getText(), length);
                getText().replace(i2, length, charSequenceA);
                this.n = true;
            }
            return true;
        }
        if (i != 16908320 && i != 16908321) {
            if (i == 16908341) {
                n3.X0("Omnibox.LongPress.Share");
                ShareHelper.c(0);
            }
            return super.onTextContextMenuItem(i);
        }
        if (i == 16908320) {
            n3.X0("Omnibox.LongPress.Cut");
        } else {
            n3.X0("Omnibox.LongPress.Copy");
        }
        String string = getText().toString();
        String strC = this.x.c(getSelectionStart(), getSelectionEnd(), string);
        if (strC == null) {
            return super.onTextContextMenuItem(i);
        }
        setIgnoreTextChangesForAutocomplete(true);
        setText(strC);
        setSelection(0, strC.length());
        setIgnoreTextChangesForAutocomplete(false);
        boolean zOnTextContextMenuItem = super.onTextContextMenuItem(i);
        if (TextUtils.equals(getText(), strC)) {
            setIgnoreTextChangesForAutocomplete(true);
            setText(string);
            setSelection(getText().length());
            setIgnoreTextChangesForAutocomplete(false);
        }
        return zOnTextContextMenuItem;
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            getLocationInWindow(this.O);
            this.P = r0[1];
            this.C = !this.B;
        }
        if (!this.B) {
            if (motionEvent.getActionMasked() == 0) {
                this.D = MotionEvent.obtain(motionEvent);
            }
            this.z.onTouchEvent(motionEvent);
            return true;
        }
        if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
            this.D = null;
        }
        if (this.C && motionEvent.getActionMasked() == 2) {
            return true;
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (IndexOutOfBoundsException e2) {
            Log.w("cr_UrlBar", "Ignoring IndexOutOfBoundsException in UrlBar#onTouchEvent.", e2);
            return true;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && isFocused()) {
            post(new b());
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean performLongClick() {
        int[] iArr = this.O;
        getLocationInWindow(iArr);
        if (!(this.P == ((float) iArr[1]))) {
            return false;
        }
        MotionEvent motionEvent = this.D;
        if (motionEvent != null) {
            super.onTouchEvent(motionEvent);
            this.D = null;
        }
        return super.performLongClick();
    }

    public void setAllowFocus(boolean z) {
        this.E = z;
        setFocusable(z);
        setFocusableInTouchMode(z);
    }

    public void setDelegate(c cVar) {
        this.u = cVar;
    }

    @Override // org.chromium.chrome.browser.omnibox.AutocompleteEditText
    public void setIgnoreTextChangesForAutocomplete(boolean z) {
        super.setIgnoreTextChangesForAutocomplete(z);
    }

    @Override // org.chromium.chrome.browser.omnibox.AutocompleteEditText, android.widget.EditText, android.widget.TextView
    public final void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        g();
        CharSequence charSequence2 = this.N;
        if (charSequence2 != null) {
            if (charSequence == null || TextUtils.indexOf(charSequence, charSequence2) != 0) {
                this.N = null;
            }
        }
    }

    public void setTextChangedListener(TextWatcher textWatcher) {
        if (Objects.equals(this.w, textWatcher)) {
            return;
        }
        TextWatcher textWatcher2 = this.w;
        if (textWatcher2 != null) {
            removeTextChangedListener(textWatcher2);
        }
        this.w = textWatcher;
        addTextChangedListener(textWatcher);
    }

    public void setTextContextMenuDelegate(d dVar) {
        this.x = dVar;
    }

    public void setTextForAutofillServices(CharSequence charSequence) {
        this.S = charSequence;
    }

    public void setUrlDirectionListener(Callback<Integer> callback) {
        this.y = callback;
        if (callback != null) {
            callback.onResult(Integer.valueOf(this.t));
        }
    }

    public void setUrlTextChangeListener(e eVar) {
        this.v = eVar;
    }

    public void setWindowDelegate(it3 it3Var) {
        this.A.i = it3Var;
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.EditText, android.widget.TextView
    public Editable getText() {
        if (!this.T) {
            return super.getText();
        }
        CharSequence charSequence = this.S;
        if (charSequence == null) {
            charSequence = "";
        }
        return new SpannableStringBuilder(charSequence);
    }
}
