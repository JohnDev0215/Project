package org.chromium.chrome.browser.findinpage;

import J.N;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.Settings;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.aq1;
import defpackage.e23;
import defpackage.ii2;
import defpackage.nh0;
import defpackage.pc;
import defpackage.t13;
import defpackage.tm0;
import defpackage.vb;
import defpackage.vm0;
import defpackage.y13;
import defpackage.yj;
import defpackage.ym0;
import defpackage.zb;
import defpackage.zp1;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.components.browser_ui.styles.R$attr;
import org.chromium.components.browser_ui.widget.text.VerticallyFixedEditText;
import org.chromium.components.find_in_page.FindMatchRectsDetails;
import org.chromium.components.find_in_page.FindNotificationDetails;
import org.chromium.components.find_in_page.FindResultBar;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class FindToolbar extends LinearLayout implements vb {
    public static final /* synthetic */ int D = 0;
    public boolean A;
    public final aq1<Boolean> B;
    public boolean C;
    public TextView e;
    public FindQuery f;
    public ImageButton g;
    public ImageButton h;
    public ImageButton i;
    public View j;
    public FindResultBar k;
    public y13 l;
    public final c m;
    public final d n;
    public Tab o;
    public final b p;
    public WindowAndroid q;
    public tm0 r;
    public ym0 s;
    public String t;
    public boolean u;
    public boolean v;
    public int w;
    public int x;
    public final Handler y;
    public org.chromium.chrome.browser.findinpage.a z;

    @SuppressLint({"Instantiatable"})
    public static class FindQuery extends VerticallyFixedEditText implements View.OnKeyListener {
        public FindToolbar k;

        public FindQuery(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            if (zb.b() || yj.a()) {
                return;
            }
            setOnKeyListener(this);
        }

        @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
        public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.k.r()) {
                editorInfo.imeOptions |= 16777216;
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View.OnKeyListener
        @SuppressLint({"GestureBackNavigation"})
        public final boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 4) {
                return false;
            }
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                getKeyDispatcherState().startTracking(keyEvent, this);
                return true;
            }
            if (keyEvent.getAction() != 1) {
                return false;
            }
            getKeyDispatcherState().handleUpEvent(keyEvent);
            if (!keyEvent.isTracking() || keyEvent.isCanceled()) {
                return false;
            }
            zb.c(13);
            this.k.h(true);
            return true;
        }

        @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
        public final boolean onKeyDown(int i, KeyEvent keyEvent) {
            if (i != 66 && i != 133 && (i != 35 || !keyEvent.isCtrlPressed())) {
                return super.onKeyDown(i, keyEvent);
            }
            FindToolbar.d(this.k, !keyEvent.isShiftPressed());
            return true;
        }

        @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.EditText, android.widget.TextView
        public final boolean onTextContextMenuItem(int i) {
            ClipData primaryClip;
            if (i != 16908322 || (primaryClip = ((ClipboardManager) getContext().getSystemService("clipboard")).getPrimaryClip()) == null) {
                return super.onTextContextMenuItem(i);
            }
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            for (int i3 = 0; i3 < primaryClip.getItemCount(); i3++) {
                sb.append(primaryClip.getItemAt(i3).coerceToText(getContext()));
            }
            int length = getText().length();
            if (isFocused()) {
                int selectionStart = getSelectionStart();
                int selectionEnd = getSelectionEnd();
                int iMax = Math.max(0, Math.min(selectionStart, selectionEnd));
                length = Math.max(0, Math.max(selectionStart, selectionEnd));
                i2 = iMax;
            }
            Selection.setSelection(getText(), length);
            getText().replace(i2, length, sb.toString());
            return true;
        }
    }

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = FindToolbar.D;
            FindToolbar findToolbar = FindToolbar.this;
            findToolbar.u();
            findToolbar.f.sendAccessibilityEvent(128);
            findToolbar.f.sendAccessibilityEvent(32768);
        }
    }

    public class b extends nh0 {
        public b() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void D0(Tab tab, boolean z) {
            if (z) {
                FindToolbar.this.h(true);
            }
        }

        @Override // defpackage.n23
        public final void P0(Tab tab, WindowAndroid windowAndroid) {
            if (windowAndroid == null) {
                FindToolbar findToolbar = FindToolbar.this;
                if (findToolbar.getVisibility() == 0) {
                    findToolbar.h(true);
                }
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void g1(Tab tab, GURL gurl) {
            FindToolbar.this.h(true);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void j0(FindMatchRectsDetails findMatchRectsDetails) {
            FindToolbar findToolbar = FindToolbar.this;
            if (findToolbar.k == null) {
                return;
            }
            if (findToolbar.f.getText().length() > 0) {
                findToolbar.k.c(findMatchRectsDetails.a, findMatchRectsDetails.b, findMatchRectsDetails.c);
            } else {
                findToolbar.k.c(-1, new RectF[0], null);
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void j1(FindNotificationDetails findNotificationDetails) {
            FindToolbar findToolbar = FindToolbar.this;
            FindResultBar findResultBar = findToolbar.k;
            if (findResultBar != null) {
                findResultBar.C = false;
            }
            int i = findNotificationDetails.c;
            boolean z = findNotificationDetails.d;
            int i2 = findNotificationDetails.a;
            if ((i == -1 || i2 == 1) && !z) {
                return;
            }
            if (z) {
                if (i2 > 0) {
                    tm0 tm0Var = findToolbar.r;
                    N.M4m8QCn$(tm0Var.b, tm0Var, findResultBar != null ? findResultBar.getRectsVersion() : -1);
                } else {
                    findToolbar.f();
                }
                findToolbar.j(findNotificationDetails.b);
            }
            Context context = findToolbar.getContext();
            Resources resources = context.getResources();
            int i3 = R$string.find_in_page_count;
            int i4 = findNotificationDetails.c;
            findToolbar.t(resources.getString(i3, Integer.valueOf(Math.max(i4, 0)), Integer.valueOf(i2)), i2 == 0);
            findToolbar.setPrevNextEnabled(i2 > 0);
            int iMax = Math.max(i4, 0);
            Context context2 = findToolbar.getContext();
            String string = i2 > 0 ? context2.getResources().getString(R$string.accessible_find_in_page_count, Integer.valueOf(iMax), Integer.valueOf(i2)) : context2.getResources().getString(R$string.accessible_find_in_page_no_results);
            findToolbar.e.setContentDescription(string);
            if (!findToolbar.A) {
                org.chromium.chrome.browser.findinpage.a aVar = findToolbar.z;
                Handler handler = findToolbar.y;
                if (aVar != null) {
                    handler.removeCallbacks(aVar);
                }
                org.chromium.chrome.browser.findinpage.a aVar2 = new org.chromium.chrome.browser.findinpage.a(findToolbar, string);
                findToolbar.z = aVar2;
                handler.postDelayed(aVar2, 500L);
            }
            if (i2 == 0 && z) {
                tm0 tm0Var2 = findToolbar.r;
                if (N.M3t_h9OB(tm0Var2.b, tm0Var2).startsWith(findToolbar.f.getText().toString())) {
                    return;
                }
                if (Settings.System.getInt(context.getContentResolver(), "haptic_feedback_enabled", 1) == 1) {
                    ((Vibrator) context.getSystemService("vibrator")).vibrate(50L);
                }
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void u(Tab tab) {
            FindToolbar.this.h(true);
        }
    }

    public class c implements e23 {
        public c() {
        }

        @Override // defpackage.e23
        public final void k(TabModel tabModel) {
            FindToolbar findToolbar = FindToolbar.this;
            findToolbar.h(true);
            findToolbar.v(findToolbar.r());
        }
    }

    public class d implements t13 {
        public d() {
        }

        @Override // defpackage.t13
        public final void B0(int i, int i2, Tab tab) {
            FindToolbar.this.h(true);
        }

        @Override // defpackage.t13
        public final void n0(Tab tab) {
            FindToolbar findToolbar = FindToolbar.this;
            if (tab != findToolbar.o) {
                return;
            }
            findToolbar.h(true);
        }
    }

    public class e implements View.OnFocusChangeListener {
        public e() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public final void onFocusChange(View view, boolean z) {
            FindToolbar findToolbar = FindToolbar.this;
            findToolbar.A = false;
            if (z) {
                return;
            }
            if (findToolbar.f.getText().length() > 0) {
                findToolbar.v = true;
            }
            findToolbar.q.m().e(findToolbar.f);
        }
    }

    public class g implements TextView.OnEditorActionListener {
        public g() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (keyEvent != null && keyEvent.getAction() == 1) {
                return false;
            }
            FindToolbar findToolbar = FindToolbar.this;
            if (findToolbar.r == null) {
                return false;
            }
            if (findToolbar.v) {
                findToolbar.v = false;
                FindToolbar.d(findToolbar, true);
            } else {
                findToolbar.q.m().e(findToolbar.f);
                tm0 tm0Var = findToolbar.r;
                N.MNC06_Rq(tm0Var.b, tm0Var);
                findToolbar.A = true;
            }
            return true;
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            FindToolbar.d(FindToolbar.this, false);
        }
    }

    public class i implements View.OnClickListener {
        public i() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            FindToolbar.d(FindToolbar.this, true);
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            FindToolbar.this.h(true);
        }
    }

    public FindToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.t = "";
        this.w = 2;
        this.x = 2;
        this.y = new Handler();
        this.B = new aq1<>();
        this.p = new b();
        this.m = new c();
        this.n = new d();
    }

    public static void d(FindToolbar findToolbar, boolean z) {
        if (findToolbar.r == null) {
            return;
        }
        String string = findToolbar.f.getText().toString();
        if (string.length() == 0) {
            return;
        }
        findToolbar.q.m().e(findToolbar.f);
        tm0 tm0Var = findToolbar.r;
        N.MiKuFRTN(tm0Var.b, tm0Var, string, z, false);
        tm0 tm0Var2 = findToolbar.r;
        N.MNC06_Rq(tm0Var2.b, tm0Var2);
        findToolbar.A = true;
    }

    private void setCurrentState(int i2) {
        this.w = i2;
        this.B.r(Boolean.valueOf(i2 == 0));
        ym0 ym0Var = this.s;
        if (ym0Var != null) {
            int i3 = this.w;
            if (i3 == 2) {
                ym0Var.d();
            } else if (i3 == 0) {
                ym0Var.c();
            }
        }
        int i4 = this.w;
        if (i4 == 2 && this.x == 0) {
            e();
        } else if (i4 == 0 && this.x == 2) {
            h(true);
        }
    }

    private void setResultsBarVisibility(boolean z) {
        FindResultBar findResultBar;
        Tab tab;
        if (z && this.k == null && (tab = this.o) != null && tab.c() != null) {
            this.k = new FindResultBar(getContext(), this.o.d(), this.q, this.r);
            return;
        }
        if (z || (findResultBar = this.k) == null) {
            return;
        }
        findResultBar.z = true;
        findResultBar.r = null;
        ObjectAnimator objectAnimator = findResultBar.y;
        if (objectAnimator != null && objectAnimator.isRunning()) {
            findResultBar.y.cancel();
        }
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[1];
        boolean zIsLayoutRtl = LocalizationUtils.isLayoutRtl();
        int i2 = findResultBar.k;
        if (zIsLayoutRtl) {
            i2 = -i2;
        }
        fArr[0] = i2;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(findResultBar, (Property<FindResultBar, Float>) property, fArr);
        findResultBar.y = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(200L);
        findResultBar.y.setInterpolator(pc.d);
        findResultBar.s.v(findResultBar.y);
        findResultBar.y.addListener(new vm0(findResultBar));
        this.k = null;
    }

    public final void e() {
        ThreadUtils.b();
        Tab tabI = this.l.i();
        if ((tabI == null || tabI.c() == null || tabI.isNativePage()) ? false : true) {
            int i2 = this.w;
            if (i2 == 0) {
                this.f.requestFocus();
                u();
                return;
            }
            this.x = 0;
            if (i2 != 2) {
                return;
            }
            setCurrentState(1);
            l();
        }
    }

    public void f() {
        t("", false);
        FindResultBar findResultBar = this.k;
        if (findResultBar != null) {
            findResultBar.c(-1, new RectF[0], null);
        }
    }

    public FindResultBar getFindResultBar() {
        return this.k;
    }

    @Override // defpackage.vb
    public zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.B;
    }

    public final void h(boolean z) {
        ThreadUtils.b();
        this.x = 2;
        if (this.w != 0) {
            return;
        }
        setCurrentState(3);
        q(z);
    }

    public int k(boolean z, boolean z2) {
        return z ? getContext().getColor(R$color.find_in_page_failed_results_status_color) : ii2.g(getContext(), R$attr.colorOnSurfaceVariant);
    }

    public void l() {
        this.l.m(this.m);
        Iterator it = this.l.y().iterator();
        while (it.hasNext()) {
            ((TabModel) it.next()).j(this.n);
        }
        Tab tabI = this.l.i();
        this.o = tabI;
        tabI.x(this.p);
        tm0 tm0Var = new tm0(this.o.c());
        this.r = tm0Var;
        this.u = true;
        String strM3t_h9OB = N.M3t_h9OB(tm0Var.b, tm0Var);
        if (strM3t_h9OB.isEmpty() && !r()) {
            strM3t_h9OB = this.t;
        }
        this.v = true;
        this.f.setText(strM3t_h9OB);
        this.u = false;
        this.f.requestFocus();
        u();
        setResultsBarVisibility(true);
        v(r());
        setCurrentState(0);
    }

    @Override // defpackage.vb
    public final int o() {
        int i2 = (this.w == 0 ? 1 : 0) ^ 1;
        h(true);
        return i2;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setOrientation(0);
        setGravity(16);
        FindQuery findQuery = (FindQuery) findViewById(R$id.find_query);
        this.f = findQuery;
        findQuery.k = this;
        findQuery.setInputType(177);
        this.f.setSelectAllOnFocus(true);
        this.f.setOnFocusChangeListener(new e());
        this.f.addTextChangedListener(new f());
        this.f.setOnEditorActionListener(new g());
        this.e = (TextView) findViewById(R$id.find_status);
        t("", false);
        ImageButton imageButton = (ImageButton) findViewById(R$id.find_prev_button);
        this.h = imageButton;
        imageButton.setOnClickListener(new h());
        ImageButton imageButton2 = (ImageButton) findViewById(R$id.find_next_button);
        this.i = imageButton2;
        imageButton2.setOnClickListener(new i());
        setPrevNextEnabled(false);
        ImageButton imageButton3 = (ImageButton) findViewById(R$id.close_find_button);
        this.g = imageButton3;
        imageButton3.setOnClickListener(new j());
        this.j = findViewById(R$id.find_separator);
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.C) {
            this.C = false;
            this.y.postDelayed(new a(), 0L);
        }
    }

    public void q(boolean z) {
        setResultsBarVisibility(false);
        this.l.l(this.m);
        Iterator it = this.l.y().iterator();
        while (it.hasNext()) {
            ((TabModel) it.next()).l(this.n);
        }
        this.o.t(this.p);
        this.q.m().e(this.f);
        if (this.f.getText().length() > 0) {
            f();
            tm0 tm0Var = this.r;
            N.MWOuMqhA(tm0Var.b, tm0Var, z);
        }
        tm0 tm0Var2 = this.r;
        N.MlPioXlo(tm0Var2.b, tm0Var2);
        tm0Var2.b = 0L;
        this.r = null;
        this.o = null;
        setCurrentState(2);
    }

    public final boolean r() {
        y13 y13Var = this.l;
        return y13Var != null && y13Var.r();
    }

    public final void s() {
        setResultsBarVisibility(false);
    }

    public void setActionModeCallbackForTextEdit(ActionMode.Callback callback) {
        this.f.setCustomSelectionActionModeCallback(callback);
    }

    public void setFindQuery(String str) {
        this.f.setText(str);
    }

    public void setObserver(ym0 ym0Var) {
        this.s = ym0Var;
    }

    public void setPrevNextEnabled(boolean z) {
        this.h.setEnabled(z);
        this.i.setEnabled(z);
    }

    public void setTabModelSelector(y13 y13Var) {
        this.l = y13Var;
        v(r());
    }

    public void setWindowAndroid(WindowAndroid windowAndroid) {
        this.q = windowAndroid;
    }

    public final void t(String str, boolean z) {
        this.e.setText(str);
        this.e.setContentDescription(null);
        this.e.setTextColor(k(z, r()));
        this.e.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
    }

    public final void u() {
        if (this.f.hasWindowFocus()) {
            this.q.m().i(this.f);
        } else {
            this.C = true;
        }
    }

    public class f implements TextWatcher {
        public f() {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            FindToolbar findToolbar = FindToolbar.this;
            if (findToolbar.r == null) {
                return;
            }
            findToolbar.A = false;
            if (findToolbar.u || findToolbar.o.c() == null) {
                return;
            }
            if (charSequence.length() > 0) {
                findToolbar.v = false;
                tm0 tm0Var = findToolbar.r;
                N.MiKuFRTN(tm0Var.b, tm0Var, charSequence.toString(), true, false);
            } else {
                findToolbar.f();
                tm0 tm0Var2 = findToolbar.r;
                N.MWOuMqhA(tm0Var2.b, tm0Var2, true);
                findToolbar.setPrevNextEnabled(false);
            }
            if (findToolbar.r()) {
                return;
            }
            findToolbar.t = charSequence.toString();
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public void j(Rect rect) {
    }

    public void v(boolean z) {
    }
}
