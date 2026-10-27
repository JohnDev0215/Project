package org.chromium.chrome.browser.autofill;

import J.N;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.C0175b;
import defpackage.bi1;
import defpackage.bq;
import defpackage.di1;
import defpackage.i9;
import defpackage.in3;
import defpackage.om3;
import defpackage.sk;
import java.util.Calendar;
import java.util.WeakHashMap;
import org.chromium.chrome.R$id;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: CardUnmaskPrompt.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements TextWatcher, View.OnClickListener, di1.a {
    public bi1 A;
    public Activity B;
    public boolean C;
    public boolean D;
    public boolean E;
    public final b e;
    public PropertyModel f;
    public boolean g;
    public final View h;
    public final TextView i;
    public final TextView j;
    public final EditText k;
    public final EditText l;
    public final EditText m;
    public final View n;
    public final TextView o;
    public final TextView p;
    public final CheckBox q;
    public final ViewGroup r;
    public final View s;
    public final ProgressBar t;
    public final TextView u;
    public final long v;
    public final int w;
    public final boolean x;
    public int y;
    public int z;

    /* JADX INFO: compiled from: CardUnmaskPrompt.java */
    public class a extends i9<Calendar> {
        public a() {
        }

        @Override // defpackage.i9
        public final Calendar b() {
            return Calendar.getInstance();
        }

        @Override // defpackage.i9
        public final void l(Calendar calendar) {
            Calendar calendar2 = calendar;
            int i = calendar2.get(1);
            e eVar = e.this;
            eVar.y = i;
            eVar.z = calendar2.get(2) + 1;
        }
    }

    /* JADX INFO: compiled from: CardUnmaskPrompt.java */
    public interface b {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0071  */
    /* JADX WARN: Type inference failed for: r11v2, types: [T, android.view.View] */
    /* JADX WARN: Type inference failed for: r1v10, types: [T, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public e(android.app.Activity r15, org.chromium.chrome.browser.autofill.e.b r16, java.lang.String r17, java.lang.String r18, int r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, org.chromium.url.GURL r23, java.lang.String r24, int r25, java.lang.String r26, int r27, boolean r28, boolean r29, boolean r30, boolean r31, long r32) {
        /*
            Method dump skipped, instruction units count: 533
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.autofill.e.<init>(android.app.Activity, org.chromium.chrome.browser.autofill.e$b, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, org.chromium.url.GURL, java.lang.String, int, java.lang.String, int, boolean, boolean, boolean, boolean, long):void");
    }

    public final void a() {
        TextView textView = this.p;
        textView.setText((CharSequence) null);
        textView.setVisibility(8);
        d.h(7, this.B, this.l, this.m, this.k);
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        h();
    }

    @Override // di1.a
    public final void b(int i) {
        CardUnmaskBridge cardUnmaskBridge = (CardUnmaskBridge) this.e;
        N.Mek0Fv7c(cardUnmaskBridge.a, cardUnmaskBridge);
        this.f = null;
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i != 0) {
            if (i == 1) {
                this.A.b(2, propertyModel);
            }
        } else {
            String string = this.k.getText().toString();
            String string2 = this.l.getText().toString();
            String string3 = Integer.toString(d.d(this.m));
            boolean zIsChecked = this.q.isChecked();
            CardUnmaskBridge cardUnmaskBridge = (CardUnmaskBridge) this.e;
            N.McBOMUil(cardUnmaskBridge.a, cardUnmaskBridge, string, string2, string3, zIsChecked);
        }
    }

    public final void d() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.B.getSystemService("input_method");
        EditText editText = this.g ? this.l : this.k;
        editText.requestFocus();
        inputMethodManager.showSoftInput(editText, 1);
        editText.sendAccessibilityEvent(8);
    }

    public final void e(int i) {
        View view = this.s;
        view.setVisibility(i);
        ViewGroup viewGroup = this.r;
        viewGroup.setAlpha(1.0f);
        boolean z = i == 8;
        if (!z) {
            view.setAlpha(0.0f);
            long j = 250;
            view.animate().alpha(1.0f).setDuration(j);
            viewGroup.animate().alpha(0.0f).setDuration(j);
        }
        int i2 = z ? 0 : 4;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.p(viewGroup, i2);
        viewGroup.setDescendantFocusability(z ? 131072 : 393216);
    }

    public final void f() {
        if (this.g) {
            View view = this.n;
            if (view.getVisibility() == 0) {
                return;
            }
            view.setVisibility(0);
            this.k.setEms(3);
            this.l.addTextChangedListener(this);
            this.m.addTextChangedListener(this);
        }
    }

    public final void g(Activity activity, String str) {
        TextView textView = (TextView) this.h.findViewById(R$id.title);
        sk skVar = bq.a;
        if (N.M09VlOh_("AutofillTouchToFillForCreditCardsAndroid")) {
            textView.setText(str);
            return;
        }
        Drawable drawableA = AppCompatResources.a(activity, this.w);
        SpannableString spannableString = new SpannableString(C0175b.j("   ", str));
        float textSize = textView.getTextSize() / drawableA.getIntrinsicHeight();
        drawableA.setBounds(0, 0, (int) (drawableA.getIntrinsicWidth() * textSize), (int) (textSize * drawableA.getIntrinsicHeight()));
        spannableString.setSpan(new ImageSpan(drawableA, 2), 0, 1, 17);
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
    }

    public final void h() {
        boolean z = this.g;
        EditText editText = this.m;
        EditText editText2 = this.l;
        int iC = z ? d.c(editText2, editText, this.C, this.D) : 7;
        EditText editText3 = this.k;
        String string = editText3.getText().toString();
        CardUnmaskBridge cardUnmaskBridge = (CardUnmaskBridge) this.e;
        if (!N.MRcUBmjo(cardUnmaskBridge.a, cardUnmaskBridge, string)) {
            if (this.E && !editText3.isFocused()) {
                iC = (iC == 7 || iC == 6) ? 4 : 5;
            } else if (iC == 7) {
                iC = 6;
            }
        }
        this.f.l(di1.l, iC != 7);
        d.g(iC, this.B, this.p);
        d.h(iC, this.B, editText2, editText, editText3);
        if (iC == 6) {
            if (!editText2.isFocused() || editText2.getText().length() != 2) {
                if (editText.isFocused() && editText.getText().length() == 2) {
                    editText3.requestFocus();
                    this.E = true;
                    return;
                }
                return;
            }
            if (editText.getText().length() == 2) {
                editText3.requestFocus();
                this.E = true;
            } else {
                editText.requestFocus();
                this.D = true;
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        CardUnmaskBridge cardUnmaskBridge = (CardUnmaskBridge) this.e;
        N.Mxa$aTDN(cardUnmaskBridge.a, cardUnmaskBridge);
        this.o.setVisibility(8);
        this.k.setText((CharSequence) null);
        a();
        this.l.requestFocus();
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
