package com.byd.cleardata.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.fragment.app.i;
import com.byd.cleardata.R;
import com.byd.cleardata.e.f;
import com.byd.cleardata.f.a.h;
import com.byd.cleardata.ui.f.o;
import com.byd.cleardata.ui.f.p;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.m;

/* JADX INFO: loaded from: classes.dex */
public class SearchActivity extends com.byd.cleardata.ui.b<com.byd.cleardata.f.c.d> implements h, View.OnClickListener {
    ImageView A;
    ImageView B;
    ImageView C;
    private p D;
    private String E;
    private i x;
    EditText z;
    private final Handler y = new a(Looper.getMainLooper());
    private TextWatcher F = new c();

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                SearchActivity.this.R();
            } else if (i == 2) {
                String string = SearchActivity.this.z.getText().toString();
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                SearchActivity.this.b0(string);
            }
        }
    }

    class b implements View.OnFocusChangeListener {
        b() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            SearchActivity.this.z.setBackgroundResource(z ? R.drawable.bg_edit_search : R.drawable.bg_edit_search_normal);
        }
    }

    class c implements TextWatcher {
        c() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            SearchActivity.this.B.setVisibility(TextUtils.isEmpty(editable.toString()) ? 4 : 0);
            SearchActivity.this.y.removeMessages(2);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchActivity.this.R();
            if (SearchActivity.this.D != null) {
                SearchActivity.this.D.P1(SearchActivity.this.E);
            }
        }
    }

    class e implements com.byd.cleardata.ui.c<String> {
        e() {
        }

        @Override // com.byd.cleardata.ui.c
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(String str, int i) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            SearchActivity searchActivity = SearchActivity.this;
            searchActivity.z.removeTextChangedListener(searchActivity.F);
            SearchActivity.this.z.setText(str);
            SearchActivity.this.B.setVisibility(0);
            SearchActivity searchActivity2 = SearchActivity.this;
            searchActivity2.z.addTextChangedListener(searchActivity2.F);
            SearchActivity.this.c0(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b0(String str) {
        com.byd.cleardata.i.c.b("search text = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.E = str;
        e0();
        this.D.Q1();
        ((com.byd.cleardata.f.c.d) this.u).h(str);
        S();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c0(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.z.clearFocus();
        a0();
        b0(str);
    }

    private void d0() {
        o oVar = new o();
        oVar.F1(new e());
        this.x.a().h(R.id.fragment_container, oVar).e();
        this.D = null;
    }

    private void e0() {
        this.D = new p();
        this.x.a().h(R.id.fragment_container, this.D).e();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.cleardata.ui.b
    /* JADX INFO: renamed from: Z, reason: merged with bridge method [inline-methods] */
    public com.byd.cleardata.f.c.d Q() {
        return new com.byd.cleardata.f.c.d(this);
    }

    protected void a0() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        View viewPeekDecorView = getWindow().peekDecorView();
        if (viewPeekDecorView != null) {
            inputMethodManager.hideSoftInputFromWindow(viewPeekDecorView.getWindowToken(), 0);
        }
    }

    @Override // androidx.appcompat.app.c, androidx.core.app.d, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        com.byd.cleardata.i.c.b("onKeyDown keyCode = " + keyEvent.getKeyCode());
        if (1 == keyEvent.getAction() && (keyEvent.getKeyCode() == 66 || keyEvent.getKeyCode() == 84)) {
            this.y.removeMessages(2);
            c0(this.z.getText().toString());
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // com.byd.cleardata.f.a.h
    public void e() {
        com.byd.cleardata.i.c.b("onFinish");
        this.y.post(new d());
    }

    @Override // com.byd.cleardata.f.a.h
    public void n(com.byd.cleardata.d.d dVar) {
        p pVar = this.D;
        if (pVar != null) {
            pVar.O1(dVar);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
            return;
        }
        if (id == R.id.iv_delete) {
            this.z.setText("");
            this.z.requestFocus();
            d0();
        } else if (id == R.id.iv_search) {
            c0(this.z.getText().toString());
        }
    }

    @Override // com.byd.cleardata.ui.b, androidx.appcompat.app.c, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search);
        this.w = (com.byd.cleardata.ui.e.i) y().c(com.byd.cleardata.ui.e.i.class.getSimpleName());
        org.greenrobot.eventbus.c.c().o(this);
        this.z = (EditText) findViewById(R.id.et_search);
        this.A = (ImageView) findViewById(R.id.iv_search);
        this.B = (ImageView) findViewById(R.id.iv_delete);
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        this.C = imageView;
        imageView.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.B.setOnClickListener(this);
        if (com.byd.cleardata.i.d.f()) {
            this.C.setRotation(180.0f);
        }
        this.x = y();
        d0();
        this.z.addTextChangedListener(this.F);
        this.z.setOnFocusChangeListener(new b());
        if (bundle != null) {
            String string = bundle.getString("lastKey");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            b0(string);
        }
    }

    @Override // com.byd.cleardata.ui.b, androidx.appcompat.app.c, androidx.fragment.app.d, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        org.greenrobot.eventbus.c.c().q(this);
        this.y.removeCallbacksAndMessages(null);
        R();
    }

    @m(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.cleardata.e.b bVar) {
        if (!com.byd.cleardata.a.f().p(bVar.b())) {
            ((com.byd.cleardata.f.c.d) this.u).f();
        } else {
            this.y.removeMessages(2);
            b0(this.E);
        }
    }

    @m
    public void onOperateEvent(f fVar) {
        int iB = fVar.b();
        com.byd.cleardata.i.c.b("operate = " + iB);
        if (iB == 20) {
            ((com.byd.cleardata.f.c.d) this.u).f();
        }
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onPause() {
        super.onPause();
        a0();
    }

    @Override // androidx.appcompat.app.c, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("lastKey", this.E);
    }
}
