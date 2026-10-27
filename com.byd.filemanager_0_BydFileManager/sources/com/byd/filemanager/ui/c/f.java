package com.byd.filemanager.ui.c;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.filemanager.FileApplication;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.k.g;
import com.byd.filemanager.ui.c.a;
import java.io.File;
import java.util.regex.Pattern;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: InputDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends com.byd.filemanager.ui.c.a<String> {
    private static final int y0 = FileApplication.a().getResources().getDimensionPixelSize(R.dimen.dp_280);
    private boolean A0;
    private TextView B0;
    private EditText C0;
    private ImageView D0;
    private TextView E0;
    private int F0;
    private FileItem G0;
    private com.byd.filemanager.ui.c.c H0;
    private int I0 = 73;
    private Handler J0 = new a(Looper.getMainLooper());
    private TextWatcher K0 = new e();
    public final a.c<Object> L0 = new C0091f();
    private final com.byd.filemanager.ui.c.e M0 = new g();
    private int z0;

    /* JADX INFO: compiled from: InputDialog.java */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                if (f.this.C0 != null) {
                    com.byd.filemanager.k.g.g(f.this.C0);
                }
            } else if (i == 1) {
                f.this.y2();
            }
        }
    }

    /* JADX INFO: compiled from: InputDialog.java */
    class b implements View.OnFocusChangeListener {
        b() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                String string = f.this.C0.getText().toString();
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                int iLastIndexOf = string.lastIndexOf(".");
                if (f.this.G0 == null || !f.this.G0.N() || iLastIndexOf <= 0 || iLastIndexOf == string.length() - 1) {
                    Selection.selectAll(f.this.C0.getText());
                } else {
                    Selection.setSelection(f.this.C0.getText(), 0, iLastIndexOf);
                }
            }
        }
    }

    /* JADX INFO: compiled from: InputDialog.java */
    class c implements ActionMode.Callback {
        c() {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            int itemId;
            if (menu == null || menu.size() <= 0) {
                return true;
            }
            for (int i = 0; i < menu.size(); i++) {
                if (menu.getItem(i) != null && (itemId = menu.getItem(i).getItemId()) == 16908341) {
                    menu.removeItem(itemId);
                    return true;
                }
            }
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }
    }

    /* JADX INFO: compiled from: InputDialog.java */
    class d implements g.b {
        d() {
        }

        @Override // com.byd.filemanager.k.g.b
        public void a(int i) {
            com.byd.filemanager.k.b.b("keyboardOffset = " + f.this.z0 + " isResumed = " + f.this.b0() + " height:" + i);
            f.this.A0 = i > 0;
            if (com.byd.filemanager.k.c.d(f.this.j1())) {
                if (f.this.A0) {
                    if (!f.this.b0()) {
                        return;
                    }
                    if (f.this.z0 == 0) {
                        f.this.C2(f.y0);
                    }
                } else if (f.this.z0 > 0) {
                    f.this.C2(-f.y0);
                }
                f fVar = f.this;
                fVar.z0 = fVar.A0 ? f.y0 : 0;
            } else {
                if (f.this.z0 > 0) {
                    f.this.C2(-f.y0);
                }
                f.this.z0 = 0;
            }
            com.byd.filemanager.k.b.b("end dialogBottomOffset = " + f.this.z0);
        }
    }

    /* JADX INFO: compiled from: InputDialog.java */
    class e implements TextWatcher {
        e() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [T, java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v5, types: [T, java.lang.String] */
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            f.this.l0 = editable.toString();
            int length = editable.length();
            if (length > f.this.I0) {
                f.this.C0.setText(editable.subSequence(0, f.this.I0));
                Selection.setSelection(f.this.C0.getText(), f.this.I0);
            } else if (length >= 73) {
                f.this.I0 = length;
            }
            f fVar = f.this;
            fVar.l0 = fVar.D2((String) fVar.l0);
            if (TextUtils.isEmpty((CharSequence) f.this.l0)) {
                f.this.E0.setEnabled(false);
                if (length > 0) {
                    f.this.D0.setVisibility(0);
                } else {
                    f.this.D0.setVisibility(8);
                }
            } else {
                f.this.D0.setVisibility(0);
                f fVar2 = f.this;
                if (fVar2.w2((String) fVar2.l0)) {
                    f.this.B0.setText(R.string.error_name_for_length);
                    f.this.B0.setVisibility(0);
                    f.this.E0.setEnabled(false);
                    return;
                }
            }
            f.this.J0.removeMessages(1);
            f.this.J0.sendEmptyMessageDelayed(1, 100L);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* JADX INFO: renamed from: com.byd.filemanager.ui.c.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: InputDialog.java */
    class C0091f implements a.c<Object> {
        C0091f() {
        }

        @Override // com.byd.filemanager.ui.c.a.c
        public void a(int i, Object obj) {
            if (i == 0) {
                org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(f.this.F0, f.this.l0));
                f.this.z1();
            } else if (i == 1) {
                f.this.a2(true);
            }
        }
    }

    /* JADX INFO: compiled from: InputDialog.java */
    class g implements com.byd.filemanager.ui.c.e {
        g() {
        }
    }

    public static f A2(FileItem fileItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("file", fileItem);
        f fVar = new f();
        fVar.q1(bundle);
        return fVar;
    }

    private void B2() {
        com.byd.filemanager.k.g.f(i1(), new d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C2(int i) {
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

    private boolean u2(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("[%#\\\\/*?\\|:<>\"]").matcher(str).find();
    }

    private boolean v2(String str) {
        FileItem fileItem;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String strE = com.byd.filemanager.f.h().e();
        if (TextUtils.isEmpty(strE) && (fileItem = this.G0) != null) {
            strE = fileItem.D();
        }
        if (strE == null) {
            return false;
        }
        File file = new File(strE, str);
        if (!file.exists()) {
            return false;
        }
        FileItem fileItem2 = this.G0;
        if (fileItem2 != null && fileItem2.C().equals(str)) {
            return false;
        }
        Object[] objArr = new Object[2];
        FileItem fileItem3 = this.G0;
        objArr[0] = fileItem3 != null ? fileItem3.C() : "NULL";
        objArr[1] = file.getAbsolutePath();
        com.byd.filemanager.k.b.f("name = %s, newFile = %s", objArr);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean w2(String str) {
        return !TextUtils.isEmpty(str) && D2(str).length() > 72;
    }

    private boolean x2(FileItem fileItem, String str) {
        String strC;
        int iLastIndexOf;
        if (fileItem != null && fileItem.u() && !TextUtils.isEmpty(str) && fileItem.N() && (iLastIndexOf = (strC = fileItem.C()).lastIndexOf(".")) >= 1 && iLastIndexOf != strC.length() - 1) {
            String strSubstring = strC.contains(".") ? strC.substring(strC.lastIndexOf(".")) : null;
            String strSubstring2 = str.contains(".") ? str.substring(str.lastIndexOf(".")) : null;
            if (strSubstring != null || strSubstring2 != null) {
                if (strSubstring == null || strSubstring2 == null) {
                    com.byd.filemanager.k.b.b("modify file suffix");
                    return true;
                }
                if (!strSubstring.equals(strSubstring2)) {
                    com.byd.filemanager.k.b.b("modify file suffix");
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y2() {
        boolean zZ2;
        String string = this.C0.getText().toString();
        if (string != null) {
            string = D2(string);
        }
        if (TextUtils.isEmpty(string)) {
            this.B0.setText("");
            zZ2 = false;
        } else {
            zZ2 = z2(string);
        }
        if (zZ2) {
            this.B0.setText("");
        }
        this.B0.setVisibility(zZ2 ? 4 : 0);
        this.E0.setEnabled(zZ2);
    }

    private boolean z2(String str) {
        if (u2(str)) {
            com.byd.filemanager.k.b.b("the entered text is spelled legally");
            this.B0.setText(Q(R.string.error_name_for_word) + "%#\\/*?|:<>\"");
            return false;
        }
        if (w2(str)) {
            com.byd.filemanager.k.b.b("the entered text length is valid");
            this.B0.setText(R.string.error_name_for_length);
            return false;
        }
        if (!v2(str)) {
            return true;
        }
        com.byd.filemanager.k.b.b("the entered text already exists");
        this.B0.setText(R.string.error_name_for_duplicate);
        return false;
    }

    public String D2(String str) {
        return !TextUtils.isEmpty(str) ? str.trim() : "";
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int N1() {
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v32, types: [T, java.lang.String] */
    @Override // com.byd.filemanager.ui.c.a
    protected void O1(View view) {
        B2();
        this.B0 = (TextView) view.findViewById(R.id.tv_tip);
        this.C0 = (EditText) view.findViewById(R.id.edit);
        if (this.G0 != null) {
            this.p0.setText(R.string.rename);
            this.C0.setFilters(new InputFilter[]{new InputFilter.LengthFilter(K().getInteger(R.integer.rename_text_max_length))});
            this.F0 = 2;
            this.l0 = this.G0.C();
        } else {
            this.p0.setText(R.string.create_folder);
            this.C0.setFilters(new InputFilter[]{new InputFilter.LengthFilter(K().getInteger(R.integer.rename_text_max_length))});
            this.F0 = 8;
            String strE = com.byd.filemanager.f.h().e();
            if (strE != null) {
                this.l0 = com.byd.filemanager.k.d.t(strE, Q(R.string.create_folder));
            }
        }
        this.D0 = (ImageView) view.findViewById(R.id.iv_delete);
        TextView textView = (TextView) view.findViewById(R.id.btn_ok);
        this.E0 = textView;
        textView.setEnabled(!TextUtils.isEmpty((CharSequence) this.l0));
        this.D0.setOnClickListener(this);
        if (!TextUtils.isEmpty((CharSequence) this.l0)) {
            this.C0.setText((CharSequence) this.l0);
            if (((String) this.l0).length() > 72) {
                this.I0 = ((String) this.l0).length();
            }
        }
        this.C0.addTextChangedListener(this.K0);
        this.C0.setOnFocusChangeListener(new b());
        this.C0.requestFocus();
        this.J0.sendEmptyMessageDelayed(0, 200L);
        this.C0.setCustomSelectionActionModeCallback(new c());
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void P1(Bundle bundle) {
    }

    @Override // com.byd.filemanager.ui.c.a
    protected String R1() {
        return this.F0 == 8 ? this.m0.getString(R.string.create_folder) : this.m0.getString(R.string.rename);
    }

    @Override // com.byd.filemanager.ui.c.a
    public void V1() {
        com.byd.filemanager.k.g.e(this.C0);
        super.V1();
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int X1() {
        return com.byd.filemanager.f.h().v() ? R.layout.dialog_input : R.layout.dialog_input_denza;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void b2(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_312;
    }

    @Override // com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        Bundle bundleV = v();
        org.greenrobot.eventbus.c.c().o(this);
        if (bundleV != null) {
            this.G0 = (FileItem) bundleV.getParcelable("file");
        }
        com.byd.filemanager.ui.c.c cVar = (com.byd.filemanager.ui.c.c) w().d(com.byd.filemanager.ui.c.c.class.getSimpleName());
        this.H0 = cVar;
        if (cVar != null) {
            cVar.Y1(this.L0);
        }
    }

    /* JADX WARN: Type inference failed for: r4v8, types: [T, java.lang.String] */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.byd.filemanager.ui.c.a, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btn_ok) {
            if (id == R.id.btn_cancel) {
                com.byd.filemanager.k.g.e(this.C0);
                z1();
                return;
            } else {
                if (id == R.id.iv_delete) {
                    this.C0.setText("");
                    this.D0.setVisibility(8);
                    return;
                }
                return;
            }
        }
        com.byd.filemanager.k.g.e(this.C0);
        ?? D2 = D2((String) this.l0);
        this.l0 = D2;
        if (TextUtils.isEmpty((CharSequence) D2)) {
            z1();
            return;
        }
        if (z2((String) this.l0)) {
            if (!(!x2(this.G0, (String) this.l0))) {
                com.byd.filemanager.ui.c.c cVarD2 = com.byd.filemanager.ui.c.c.d2(37);
                this.H0 = cVarD2;
                cVarD2.c2(w());
                this.H0.Y1(this.L0);
                return;
            }
            this.B0.setVisibility(4);
            a.c<T> cVar = this.k0;
            if (cVar != 0) {
                cVar.a(0, (T) ((String) this.l0));
            }
            org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(this.F0, this.l0));
            z1();
        }
    }

    @Override // com.byd.filemanager.ui.c.a, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        com.byd.filemanager.k.b.b("isLandscape = " + com.byd.filemanager.k.c.d(j1()));
        com.byd.filemanager.k.b.b("keyboardOffset = " + this.z0);
        if (!com.byd.filemanager.k.c.d(j1())) {
            if (this.z0 > 0) {
                C2(-y0);
            }
            this.z0 = 0;
        } else if (this.A0) {
            int i = y0;
            this.z0 = i;
            C2(i);
        }
        com.byd.filemanager.k.b.b("end dialogBottomOffset = " + this.z0);
    }

    @org.greenrobot.eventbus.l(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.music.f.b bVar) {
        if (bVar.b() == com.byd.filemanager.f.h().k()) {
            z1();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void p0() {
        super.p0();
        com.byd.filemanager.k.b.b("onDestroy");
        this.J0.removeCallbacksAndMessages(null);
        org.greenrobot.eventbus.c.c().q(this);
        com.byd.filemanager.k.g.h(i1());
    }
}
