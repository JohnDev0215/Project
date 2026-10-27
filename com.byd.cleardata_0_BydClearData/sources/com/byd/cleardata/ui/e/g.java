package com.byd.cleardata.ui.e;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.cleardata.FileApplication;
import com.byd.cleardata.R;
import com.byd.cleardata.i.h;
import com.byd.cleardata.ui.e.a;
import java.io.File;
import java.util.regex.Pattern;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class g extends com.byd.cleardata.ui.e.a<String> {
    private static final int y0 = FileApplication.a().getResources().getDimensionPixelSize(R.dimen.dp_230);
    private boolean A0;
    private TextView B0;
    private EditText C0;
    private ImageView D0;
    private TextView E0;
    private int F0;
    private com.byd.cleardata.d.d G0;
    private com.byd.cleardata.ui.e.d H0;
    private Handler I0 = new a(Looper.getMainLooper());
    private TextWatcher J0 = new d();
    public final a.c<Object> K0 = new e();
    private final com.byd.cleardata.ui.e.f L0 = new f();
    private int z0;

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                ((InputMethodManager) g.this.j1().getSystemService("input_method")).toggleSoftInput(0, 2);
            } else if (i == 1) {
                g.this.p2();
            }
        }
    }

    class b implements View.OnFocusChangeListener {
        b() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                String string = g.this.C0.getText().toString();
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                int iLastIndexOf = string.lastIndexOf(".");
                if (g.this.G0 == null || !g.this.G0.N() || iLastIndexOf <= 0 || iLastIndexOf == string.length() - 1) {
                    Selection.selectAll(g.this.C0.getText());
                } else {
                    Selection.setSelection(g.this.C0.getText(), 0, iLastIndexOf);
                }
            }
        }
    }

    class c implements h.b {
        c() {
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0062  */
        @Override // com.byd.cleardata.i.h.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a(int r3) {
            /*
                r2 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "keyboardOffset = "
                r0.append(r1)
                com.byd.cleardata.ui.e.g r1 = com.byd.cleardata.ui.e.g.this
                int r1 = com.byd.cleardata.ui.e.g.c2(r1)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                com.byd.cleardata.i.c.b(r0)
                com.byd.cleardata.ui.e.g r0 = com.byd.cleardata.ui.e.g.this
                r1 = 0
                if (r3 <= 0) goto L21
                r3 = 1
                goto L22
            L21:
                r3 = r1
            L22:
                com.byd.cleardata.ui.e.g.f2(r0, r3)
                com.byd.cleardata.ui.e.g r3 = com.byd.cleardata.ui.e.g.this
                android.content.Context r3 = r3.j1()
                boolean r3 = com.byd.cleardata.i.d.d(r3)
                if (r3 == 0) goto L67
                com.byd.cleardata.ui.e.g r3 = com.byd.cleardata.ui.e.g.this
                boolean r3 = com.byd.cleardata.ui.e.g.e2(r3)
                if (r3 == 0) goto L48
                com.byd.cleardata.ui.e.g r3 = com.byd.cleardata.ui.e.g.this
                int r3 = com.byd.cleardata.ui.e.g.c2(r3)
                if (r3 != 0) goto L5a
                com.byd.cleardata.ui.e.g r3 = com.byd.cleardata.ui.e.g.this
                int r0 = com.byd.cleardata.ui.e.g.g2()
                goto L57
            L48:
                com.byd.cleardata.ui.e.g r3 = com.byd.cleardata.ui.e.g.this
                int r3 = com.byd.cleardata.ui.e.g.c2(r3)
                if (r3 <= 0) goto L5a
                com.byd.cleardata.ui.e.g r3 = com.byd.cleardata.ui.e.g.this
                int r0 = com.byd.cleardata.ui.e.g.g2()
                int r0 = -r0
            L57:
                com.byd.cleardata.ui.e.g.h2(r3, r0)
            L5a:
                com.byd.cleardata.ui.e.g r3 = com.byd.cleardata.ui.e.g.this
                boolean r0 = com.byd.cleardata.ui.e.g.e2(r3)
                if (r0 == 0) goto L7b
                int r1 = com.byd.cleardata.ui.e.g.g2()
                goto L7b
            L67:
                com.byd.cleardata.ui.e.g r3 = com.byd.cleardata.ui.e.g.this
                int r3 = com.byd.cleardata.ui.e.g.c2(r3)
                if (r3 <= 0) goto L79
                com.byd.cleardata.ui.e.g r3 = com.byd.cleardata.ui.e.g.this
                int r0 = com.byd.cleardata.ui.e.g.g2()
                int r0 = -r0
                com.byd.cleardata.ui.e.g.h2(r3, r0)
            L79:
                com.byd.cleardata.ui.e.g r3 = com.byd.cleardata.ui.e.g.this
            L7b:
                com.byd.cleardata.ui.e.g.d2(r3, r1)
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r0 = "end dialogBottomOffset = "
                r3.append(r0)
                com.byd.cleardata.ui.e.g r0 = com.byd.cleardata.ui.e.g.this
                int r0 = com.byd.cleardata.ui.e.g.c2(r0)
                r3.append(r0)
                java.lang.String r3 = r3.toString()
                com.byd.cleardata.i.c.b(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.cleardata.ui.e.g.c.a(int):void");
        }
    }

    class d implements TextWatcher {
        d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.String] */
        /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.String] */
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            g.this.l0 = editable.toString();
            g gVar = g.this;
            gVar.l0 = gVar.u2((String) gVar.l0);
            if (TextUtils.isEmpty((CharSequence) g.this.l0)) {
                g.this.E0.setEnabled(false);
                g.this.D0.setVisibility(4);
            } else {
                g.this.E0.setEnabled(true);
                g.this.D0.setVisibility(0);
                g gVar2 = g.this;
                if (gVar2.n2((String) gVar2.l0)) {
                    g.this.B0.setText(R.string.error_name_for_length);
                    g.this.B0.setVisibility(0);
                    g.this.E0.setEnabled(false);
                    return;
                }
                g.this.E0.setEnabled(true);
                g.this.B0.setVisibility(4);
            }
            g.this.I0.removeMessages(1);
            g.this.I0.sendEmptyMessageDelayed(1, 100L);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    class e implements a.c<Object> {
        e() {
        }

        @Override // com.byd.cleardata.ui.e.a.c
        public void a(int i, Object obj) {
            if (i == 0) {
                org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.f(g.this.F0, g.this.l0));
                g.this.z1();
            } else if (i == 1) {
                g.this.T1(true);
            }
        }
    }

    class f implements com.byd.cleardata.ui.e.f {
        f() {
        }
    }

    private boolean l2(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("[%#/*?？\\|]").matcher(str).find();
    }

    private boolean m2(String str) {
        String strE;
        if (TextUtils.isEmpty(str) || (strE = com.byd.cleardata.b.h().e()) == null) {
            return false;
        }
        File file = new File(strE, str);
        if (!file.exists()) {
            return false;
        }
        com.byd.cleardata.d.d dVar = this.G0;
        if (dVar != null && dVar.C().equals(str)) {
            return false;
        }
        Object[] objArr = new Object[2];
        com.byd.cleardata.d.d dVar2 = this.G0;
        objArr[0] = dVar2 != null ? dVar2.C() : "NULL";
        objArr[1] = file.getAbsolutePath();
        com.byd.cleardata.i.c.f("name = %s, newFile = %s", objArr);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n2(String str) {
        return !TextUtils.isEmpty(str) && u2(str).length() > 72;
    }

    private boolean o2(com.byd.cleardata.d.d dVar, String str) {
        String strC;
        int iLastIndexOf;
        if (dVar != null && !TextUtils.isEmpty(str) && dVar.N() && (iLastIndexOf = (strC = dVar.C()).lastIndexOf(".")) >= 1 && iLastIndexOf != strC.length() - 1) {
            String strSubstring = strC.contains(".") ? strC.substring(strC.lastIndexOf(".")) : null;
            String strSubstring2 = str.contains(".") ? str.substring(str.lastIndexOf(".")) : null;
            if (strSubstring != null || strSubstring2 != null) {
                if (strSubstring == null || strSubstring2 == null) {
                    com.byd.cleardata.i.c.b("modify file suffix");
                    return true;
                }
                if (!strSubstring.equals(strSubstring2)) {
                    com.byd.cleardata.i.c.b("modify file suffix");
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p2() {
        boolean zQ2;
        String string = this.C0.getText().toString();
        if (string != null) {
            string = u2(string);
        }
        if (TextUtils.isEmpty(string)) {
            this.B0.setText("");
            zQ2 = false;
        } else {
            zQ2 = q2(string);
        }
        if (zQ2) {
            this.B0.setText("");
        }
        this.B0.setVisibility(zQ2 ? 4 : 0);
        this.E0.setEnabled(zQ2);
    }

    private boolean q2(String str) {
        TextView textView;
        int i;
        if (l2(str)) {
            com.byd.cleardata.i.c.b("the entered text is spelled legally");
            textView = this.B0;
            i = R.string.error_name_for_word;
        } else if (n2(str)) {
            com.byd.cleardata.i.c.b("the entered text length is valid");
            textView = this.B0;
            i = R.string.error_name_for_length;
        } else {
            if (!m2(str)) {
                return true;
            }
            com.byd.cleardata.i.c.b("the entered text already exists");
            textView = this.B0;
            i = R.string.error_name_for_duplicate;
        }
        textView.setText(i);
        return false;
    }

    public static g r2(com.byd.cleardata.d.d dVar) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("file", dVar);
        g gVar = new g();
        gVar.q1(bundle);
        return gVar;
    }

    private void s2() {
        com.byd.cleardata.i.h.f(i1(), new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t2(int i) {
        if (this.u0) {
            ConstraintLayout.b bVar = (ConstraintLayout.b) this.s0.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) bVar).bottomMargin += i;
            this.s0.setLayoutParams(bVar);
        } else {
            Window window = this.t0.getWindow();
            if (window != null) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.y += i;
                window.setAttributes(attributes);
            }
        }
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int H1() {
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x00a3  */
    @Override // com.byd.cleardata.ui.e.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void I1(android.view.View r9) {
        /*
            r8 = this;
            r8.s2()
            r0 = 2131296703(0x7f0901bf, float:1.821133E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r8.B0 = r0
            r0 = 2131296408(0x7f090098, float:1.8210732E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.EditText r0 = (android.widget.EditText) r0
            r8.C0 = r0
            com.byd.cleardata.d.d r0 = r8.G0
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L33
            android.widget.TextView r0 = r8.p0
            r3 = 2131755162(0x7f10009a, float:1.9141195E38)
            r0.setText(r3)
            r0 = 2
            r8.F0 = r0
            com.byd.cleardata.d.d r0 = r8.G0
            java.lang.String r0 = r0.C()
        L30:
            r8.l0 = r0
            goto L6b
        L33:
            android.widget.TextView r0 = r8.p0
            r3 = 2131755060(0x7f100034, float:1.9140989E38)
            r0.setText(r3)
            android.widget.EditText r0 = r8.C0
            android.text.InputFilter[] r4 = new android.text.InputFilter[r2]
            android.text.InputFilter$LengthFilter r5 = new android.text.InputFilter$LengthFilter
            android.content.res.Resources r6 = r8.L()
            r7 = 2131361815(0x7f0a0017, float:1.8343393E38)
            int r6 = r6.getInteger(r7)
            r5.<init>(r6)
            r4[r1] = r5
            r0.setFilters(r4)
            r0 = 8
            r8.F0 = r0
            com.byd.cleardata.b r0 = com.byd.cleardata.b.h()
            java.lang.String r0 = r0.e()
            if (r0 == 0) goto L6b
            java.lang.String r3 = r8.R(r3)
            java.lang.String r0 = com.byd.cleardata.i.e.t(r0, r3)
            goto L30
        L6b:
            r0 = 2131296464(0x7f0900d0, float:1.8210845E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            r8.D0 = r0
            r0 = 2131296346(0x7f09005a, float:1.8210606E38)
            android.view.View r9 = r9.findViewById(r0)
            android.widget.TextView r9 = (android.widget.TextView) r9
            r8.E0 = r9
            T r0 = r8.l0
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r0 = r0 ^ r2
            r9.setEnabled(r0)
            android.widget.ImageView r9 = r8.D0
            r9.setOnClickListener(r8)
            android.widget.EditText r9 = r8.C0
            android.text.TextWatcher r0 = r8.J0
            r9.addTextChangedListener(r0)
            T r9 = r8.l0
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 != 0) goto Lac
            android.widget.EditText r9 = r8.C0
            T r0 = r8.l0
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r9.setText(r0)
        Lac:
            android.widget.EditText r9 = r8.C0
            com.byd.cleardata.ui.e.g$b r0 = new com.byd.cleardata.ui.e.g$b
            r0.<init>()
            r9.setOnFocusChangeListener(r0)
            android.widget.EditText r9 = r8.C0
            r9.requestFocus()
            android.os.Handler r9 = r8.I0
            r2 = 200(0xc8, double:9.9E-322)
            r9.sendEmptyMessageDelayed(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.cleardata.ui.e.g.I1(android.view.View):void");
    }

    @Override // com.byd.cleardata.ui.e.a
    protected String L1() {
        Context context;
        int i;
        if (this.F0 == 8) {
            context = this.m0;
            i = R.string.create_file;
        } else {
            context = this.m0;
            i = R.string.rename;
        }
        return context.getString(i);
    }

    @Override // com.byd.cleardata.ui.e.a
    public void O1() {
        com.byd.cleardata.i.h.e(this.C0);
        super.O1();
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int Q1() {
        return R.layout.dialog_input;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void U1(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_312;
    }

    @Override // com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        Bundle bundleW = w();
        org.greenrobot.eventbus.c.c().o(this);
        if (bundleW != null) {
            this.G0 = (com.byd.cleardata.d.d) bundleW.getParcelable("file");
        }
        com.byd.cleardata.ui.e.d dVar = (com.byd.cleardata.ui.e.d) x().c(com.byd.cleardata.ui.e.d.class.getSimpleName());
        this.H0 = dVar;
        if (dVar != null) {
            dVar.R1(this.K0);
        }
    }

    /* JADX WARN: Type inference failed for: r4v7, types: [T, java.lang.String] */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.byd.cleardata.ui.e.a, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_ok) {
            com.byd.cleardata.i.h.e(this.C0);
            ?? U2 = u2((String) this.l0);
            this.l0 = U2;
            if (!TextUtils.isEmpty((CharSequence) U2)) {
                if (!q2((String) this.l0)) {
                    return;
                }
                if (!(!o2(this.G0, (String) this.l0))) {
                    T1(false);
                    com.byd.cleardata.ui.e.d dVarW1 = com.byd.cleardata.ui.e.d.W1(37);
                    this.H0 = dVarW1;
                    dVarW1.V1(x());
                    this.H0.R1(this.K0);
                    return;
                }
                this.B0.setVisibility(4);
                a.c<T> cVar = this.k0;
                if (cVar != 0) {
                    cVar.a(0, (T) ((String) this.l0));
                }
                org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.f(this.F0, this.l0));
            }
        } else {
            if (id != R.id.btn_cancel) {
                if (id == R.id.iv_delete) {
                    this.C0.setText("");
                    return;
                }
                return;
            }
            com.byd.cleardata.i.h.e(this.C0);
        }
        z1();
    }

    @Override // com.byd.cleardata.ui.e.a, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        com.byd.cleardata.i.c.b("isLandscape = " + com.byd.cleardata.i.d.d(j1()));
        com.byd.cleardata.i.c.b("keyboardOffset = " + this.z0);
        if (!com.byd.cleardata.i.d.d(j1())) {
            if (this.z0 > 0) {
                t2(-y0);
            }
            this.z0 = 0;
        } else if (this.A0) {
            int i = y0;
            this.z0 = i;
            t2(i);
        }
        com.byd.cleardata.i.c.b("end dialogBottomOffset = " + this.z0);
    }

    @org.greenrobot.eventbus.m(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.cleardata.e.b bVar) {
        if (bVar.b() == com.byd.cleardata.b.h().k()) {
            z1();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void p0() {
        super.p0();
        com.byd.cleardata.i.c.b("onDestroy");
        org.greenrobot.eventbus.c.c().q(this);
        com.byd.cleardata.i.h.g(i1());
    }

    public String u2(String str) {
        int length = str.length();
        int i = 0;
        while (i < length && str.charAt(i) <= ' ') {
            i++;
        }
        return i > 0 ? str.substring(i, length) : str;
    }
}
