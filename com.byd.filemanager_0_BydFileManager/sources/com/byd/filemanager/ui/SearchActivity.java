package com.byd.filemanager.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.g;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileItem;
import com.byd.filemanager.ui.c.h;
import com.byd.filemanager.ui.fragment.SearchHistoryFragment;
import com.byd.filemanager.ui.fragment.SearchResultFragment;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

/* JADX INFO: loaded from: classes.dex */
public class SearchActivity extends BaseActivity<com.byd.filemanager.i.c.c> implements com.byd.filemanager.i.a.f, View.OnClickListener {
    ImageView A;
    ImageView B;
    ImageView C;
    private SearchResultFragment D;
    private String E;
    private g x;
    EditText z;
    private final Handler y = new a(Looper.getMainLooper());
    private int F = 0;
    private TextWatcher G = new d();

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SearchActivity searchActivity;
            EditText editText;
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                SearchActivity.this.Q();
                return;
            }
            if (i == 2) {
                String string = SearchActivity.this.z.getText().toString();
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                SearchActivity.this.c0(string);
                return;
            }
            if (i == 0) {
                EditText editText2 = SearchActivity.this.z;
                if (editText2 != null) {
                    com.byd.filemanager.k.g.g(editText2);
                    return;
                }
                return;
            }
            if (i != 3 || (editText = (searchActivity = SearchActivity.this).z) == null) {
                return;
            }
            searchActivity.d0(editText.getText().toString());
        }
    }

    class b implements View.OnFocusChangeListener {
        b() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (com.byd.filemanager.f.h().v()) {
                if (z) {
                    SearchActivity.this.z.setBackgroundResource(R.drawable.bg_edit_search);
                } else {
                    SearchActivity.this.z.setBackgroundResource(R.drawable.bg_edit_search_normal);
                }
            }
        }
    }

    class c implements TextView.OnEditorActionListener {
        c() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            com.byd.filemanager.k.b.b("actionId:" + i);
            if (i == 5) {
                return true;
            }
            if (i != 3) {
                return false;
            }
            SearchActivity.this.y.removeMessages(2);
            SearchActivity.this.y.removeMessages(3);
            SearchActivity.this.y.sendEmptyMessage(3);
            return true;
        }
    }

    class d implements TextWatcher {
        d() {
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

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchActivity.this.R();
            if (SearchActivity.this.D != null) {
                SearchActivity.this.D.Q1(SearchActivity.this.E);
            }
        }
    }

    class f implements com.byd.filemanager.ui.a<String> {
        f() {
        }

        @Override // com.byd.filemanager.ui.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(String str, int i) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            SearchActivity searchActivity = SearchActivity.this;
            searchActivity.z.removeTextChangedListener(searchActivity.G);
            SearchActivity.this.z.setText(str);
            SearchActivity.this.B.setVisibility(0);
            SearchActivity searchActivity2 = SearchActivity.this;
            searchActivity2.z.addTextChangedListener(searchActivity2.G);
            SearchActivity.this.d0(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c0(String str) {
        com.byd.filemanager.k.b.b("search text = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.E = str;
        f0();
        this.D.R1();
        ((com.byd.filemanager.i.c.c) this.u).h(str);
        S();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0(String str) {
        this.z.clearFocus();
        a0();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c0(str);
    }

    private void e0() {
        SearchHistoryFragment searchHistoryFragment = new SearchHistoryFragment();
        searchHistoryFragment.F1(new f());
        this.x.a().k(R.id.fragment_container, searchHistoryFragment).g();
        this.D = null;
    }

    private void f0() {
        this.D = new SearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("key_search_tab_position", this.F);
        this.D.q1(bundle);
        this.x.a().k(R.id.fragment_container, this.D).g();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.filemanager.ui.BaseActivity
    /* JADX INFO: renamed from: Z, reason: merged with bridge method [inline-methods] */
    public com.byd.filemanager.i.c.c P() {
        return new com.byd.filemanager.i.c.c(this);
    }

    protected void a0() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        View viewPeekDecorView = getWindow().peekDecorView();
        if (viewPeekDecorView != null) {
            inputMethodManager.hideSoftInputFromWindow(viewPeekDecorView.getWindowToken(), 0);
        }
    }

    public void b0() {
        EditText editText = this.z;
        if (editText == null || !editText.isFocused()) {
            return;
        }
        com.byd.filemanager.k.b.g("hideSoftInput:");
        this.z.clearFocus();
        a0();
    }

    @Override // com.byd.filemanager.i.a.f
    public void e() {
        com.byd.filemanager.k.b.b("onFinish");
        this.y.post(new e());
    }

    @Override // com.byd.filemanager.i.a.f
    public void f(FileItem fileItem) {
        SearchResultFragment searchResultFragment = this.D;
        if (searchResultFragment != null) {
            searchResultFragment.P1(fileItem);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
            return;
        }
        if (id != R.id.iv_delete) {
            if (id == R.id.iv_search) {
                d0(this.z.getText().toString());
            }
        } else {
            this.E = "";
            this.z.setText("");
            this.z.requestFocus();
            e0();
        }
    }

    @Override // com.byd.filemanager.ui.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (com.byd.filemanager.f.h().v()) {
            setContentView(R.layout.activity_search);
        } else {
            setContentView(R.layout.activity_search_denza);
        }
        this.w = (h) x().d(h.class.getSimpleName());
        org.greenrobot.eventbus.c.c().o(this);
        this.z = (EditText) findViewById(R.id.et_search);
        this.A = (ImageView) findViewById(R.id.iv_search);
        this.B = (ImageView) findViewById(R.id.iv_delete);
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        this.C = imageView;
        imageView.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.B.setOnClickListener(this);
        if (com.byd.filemanager.k.c.f()) {
            this.C.setRotation(180.0f);
        }
        this.x = x();
        e0();
        this.z.addTextChangedListener(this.G);
        this.z.setOnFocusChangeListener(new b());
        this.z.setOnEditorActionListener(new c());
        this.z.requestFocus();
        this.y.sendEmptyMessageDelayed(0, 200L);
        if (bundle != null) {
            String string = bundle.getString("lastKey");
            this.F = bundle.getInt("key_search_tab_position", 0);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            c0(string);
        }
    }

    @Override // com.byd.filemanager.ui.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        EditText editText = this.z;
        if (editText != null) {
            editText.addTextChangedListener(null);
        }
        super.onDestroy();
        org.greenrobot.eventbus.c.c().q(this);
        this.y.removeCallbacksAndMessages(null);
        Q();
    }

    @l(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.music.f.b bVar) {
        if (!com.byd.filemanager.e.f().s(bVar.b())) {
            ((com.byd.filemanager.i.c.c) this.u).f();
        } else {
            this.y.removeMessages(2);
            c0(this.E);
        }
    }

    @l
    public void onOperateEvent(com.byd.music.f.f fVar) {
        int iB = fVar.b();
        com.byd.filemanager.k.b.b("operate = " + iB);
        if (iB == 20) {
            ((com.byd.filemanager.i.c.c) this.u).f();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.z.clearFocus();
        a0();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.y.sendEmptyMessageDelayed(0, 200L);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("lastKey", this.E);
        SearchResultFragment searchResultFragment = this.D;
        if (searchResultFragment != null) {
            bundle.putInt("key_search_tab_position", searchResultFragment.I1());
        }
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        com.byd.filemanager.k.b.b("onTouchEvent action = " + motionEvent.getAction());
        if (1 == motionEvent.getAction()) {
            this.z.clearFocus();
            a0();
        }
        return super.onTouchEvent(motionEvent);
    }
}
