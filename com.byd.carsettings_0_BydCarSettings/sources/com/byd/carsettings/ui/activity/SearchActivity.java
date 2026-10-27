package com.byd.carsettings.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.d.p;
import b.b.g.j.b;
import b.b.g.p.d;
import b.b.g.q.b;
import com.byd.base.hilt.Router;
import com.byd.base.hilt.impl.StudyConfigImpl;
import com.byd.base.hilt.inter.IStudyConfig;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.FunctionItemAdapter;
import com.byd.carsettings.adapter.SearchKeywordAdapter;
import com.byd.carsettings.db.model.FunctionItem;
import com.byd.carsettings.db.model.SearchKeyword;
import com.byd.carsettings.ui.dialog.SimpleDialog;
import com.byd.mvp.mvp.XActivity;
import com.byd.mvp.ui.widget.RecyclerSpace;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class SearchActivity extends XActivity<b.b.g.n.e> {
    public static boolean p = true;
    public FunctionItemAdapter A;
    public SearchKeywordAdapter B;
    public volatile String D;
    public ImageView E;
    public View F;
    public String G = "";
    public View q;
    public View r;
    public EditText s;
    public View t;
    public RecyclerView u;
    public RecyclerView v;
    public FrameLayout w;
    public List<SearchKeyword> x;
    public List<FunctionItem> y;
    public ImageView z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.C0048b.a().E(SearchActivity.this.getApplicationContext());
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SearchActivity.this.s == null || SearchActivity.this.z == null) {
                return;
            }
            String strTrim = SearchActivity.this.s.getText().toString().trim();
            b.b.m.c.a.a("SearchActivity", "checkEmptyInputAgain", "do check empty input again. input:" + strTrim);
            if (SearchActivity.this.k0(strTrim)) {
                SearchActivity.this.p0();
                SearchActivity.this.z.setVisibility(8);
            }
        }
    }

    public class c implements b.b.h.c {
        public c() {
        }

        @Override // b.b.h.c
        public void a(int i) {
            b.b.m.c.a.a("SearchActivity", "---deleteKeywordHistory--onFailed---code = " + i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.h.c
        public void b() {
            b.b.m.c.a.a("SearchActivity", "---deleteKeywordHistory--onSuccessful----");
            ((b.b.g.n.e) SearchActivity.this.mo7getP()).l();
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SearchActivity.this.y.isEmpty()) {
                SearchActivity.this.t.setVisibility(0);
                SearchActivity.this.r.setVisibility(8);
                SearchActivity.this.v.setVisibility(8);
                if (SearchActivity.this.w != null) {
                    SearchActivity.this.w.setVisibility(8);
                }
            } else {
                SearchActivity.this.v.scrollToPosition(0);
                SearchActivity.this.r.setVisibility(8);
                SearchActivity.this.t.setVisibility(8);
                SearchActivity.this.v.setVisibility(0);
                if (SearchActivity.this.w != null) {
                    SearchActivity.this.w.setVisibility(0);
                }
            }
            SearchActivity.this.A.notifyDataSetChanged();
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchActivity.this.r.setVisibility(0);
            SearchActivity.this.v.setVisibility(8);
            SearchActivity.this.B.notifyDataSetChanged();
        }
    }

    public class f extends b.b.a.j.d.a {
        public f() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            b.b.m.g.g.a(SearchActivity.this.s);
            SearchActivity.this.g0();
        }
    }

    public class g extends b.b.g.q.h.a {
        public g(EditText editText, String str) {
            super(editText, str);
        }

        @Override // b.b.g.q.h.a, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            int length = SearchActivity.this.s.getText().length();
            if (SearchActivity.this.s.getSelectionStart() >= length) {
                SearchActivity.this.s.setSelection(length);
            }
            String strTrim = editable.toString().trim();
            b.b.m.c.a.a("SearchActivity", "---afterTextChanged--text:" + editable.toString() + ", input:" + strTrim);
            if (TextUtils.isEmpty(strTrim) && !TextUtils.isEmpty(editable.toString())) {
                SearchActivity.this.s.getText().delete(0, editable.toString().length());
                return;
            }
            String strTrim2 = SearchActivity.this.s.getText().toString().trim();
            b.b.m.c.a.a("SearchActivity", "---afterTextChanged--input:" + strTrim2);
            if (strTrim2.equals(SearchActivity.this.G)) {
                return;
            }
            SearchActivity.this.G = strTrim2;
            if (TextUtils.isEmpty(strTrim2)) {
                SearchActivity.this.r0(new ArrayList());
            }
            SearchActivity.this.h0(strTrim2);
        }
    }

    public class h implements TextView.OnEditorActionListener {
        public h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            b.b.m.g.g.b(SearchActivity.this.s, i);
            return false;
        }
    }

    public class i implements View.OnFocusChangeListener {
        public i() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            SearchActivity.this.q.setBackgroundResource(z ? R.drawable.bg_et_frame_gray_focus : R.drawable.bg_et_frame_gray_normal);
            if (z) {
                SearchActivity.this.v.setScrollBarDefaultDelayBeforeFade(750);
            }
            boolean unused = SearchActivity.p = z;
            if (z) {
                return;
            }
            SearchActivity searchActivity = SearchActivity.this;
            searchActivity.b(searchActivity.s);
        }
    }

    public class j implements b.b.m.f.c.a.e {
        public j() {
        }

        @Override // b.b.m.f.c.a.e
        public void onItemClicked(int i) {
            String keyword = ((SearchKeyword) SearchActivity.this.x.get(i)).getKeyword();
            if (keyword == null) {
                return;
            }
            b.b.m.c.a.a("SearchActivity", "---onItemClicked--0--text.length: " + keyword.length());
            if (keyword.length() > 50) {
                keyword = keyword.substring(0, 50);
            }
            b.b.m.c.a.a("SearchActivity", "---onItemClicked--1--text.length: " + keyword.length());
            b.b.m.c.a.a("SearchActivity", "---onItemClicked--Keyword: " + keyword);
            SearchActivity.this.s.setText(keyword);
            SearchActivity.this.s.setSelection(keyword.length());
        }
    }

    public class k implements b.b.m.f.c.a.e {
        public k() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.m.f.c.a.e
        public void onItemClicked(int i) {
            String name = ((FunctionItem) SearchActivity.this.y.get(i)).getName();
            if (SearchActivity.this.x.size() >= 20) {
                ((b.b.g.n.e) SearchActivity.this.mo7getP()).h((SearchKeyword) SearchActivity.this.x.get(SearchActivity.this.x.size() - 1), name);
            } else {
                ((b.b.g.n.e) SearchActivity.this.mo7getP()).m(name);
            }
            SearchActivity searchActivity = SearchActivity.this;
            searchActivity.i0((FunctionItem) searchActivity.y.get(i));
        }
    }

    public class l implements View.OnTouchListener {
        public l() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            b.b.m.c.a.a("SearchActivity", "---onTouch--etSearch.hasFocus: " + SearchActivity.this.s.hasFocus());
            if (SearchActivity.this.s.hasFocus()) {
                SearchActivity.this.s.clearFocus();
            }
            SearchActivity.this.v.setScrollBarDefaultDelayBeforeFade(3000);
            return false;
        }
    }

    public class m implements View.OnTouchListener {
        public m() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            b.b.m.c.a.a("SearchActivity", "---onTouch--history--etSearch.hasFocus: " + SearchActivity.this.s.hasFocus());
            if (SearchActivity.this.s.hasFocus()) {
                SearchActivity.this.s.clearFocus();
            }
            return false;
        }
    }

    public class n implements View.OnClickListener {
        public n() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b.b.m.c.a.a("SearchActivity", "---llSearch--onClick--etSearch.hasFocus:" + SearchActivity.this.s.hasFocus());
            if (SearchActivity.this.s.hasFocus()) {
                return;
            }
            SearchActivity.this.s.requestFocus();
            b.b.m.g.g.f(SearchActivity.this.s);
        }
    }

    public static void s0(boolean z) {
        p = z;
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void D(boolean z) {
        I(this.u, true);
    }

    public final void d0() {
        IStudyConfig iStudyConfigF = Router.b().f();
        if (iStudyConfigF.a()) {
            b.b.m.c.a.a("SearchActivity", "checkADASStudy already ok!");
            b.b.a.f.h.a().b(new Runnable() { // from class: b.b.g.q.c.a
                @Override // java.lang.Runnable
                public final void run() {
                    b.C0048b.a().A((StudyConfigImpl) Router.b().f());
                }
            });
        } else {
            BydLogHelper.logW("SearchActivity", "checkADASStudy initAdasConfig now!");
            iStudyConfigF.b();
        }
    }

    public final void e0() {
        b.b.m.c.a.a("SearchActivity", "checkEmptyInputAgain", "delay to check empty input.");
        b.b.a.f.h.a().g(new b(), 20L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void f0() {
        b.b.m.c.a.a("SearchActivity", "---deleteKeywordHistory-----");
        ((b.b.g.n.e) mo7getP()).g(new c());
    }

    public final void g0() {
        SimpleDialog.z(getString(R.string.dialog_title_hint), getString(R.string.msg_dialog_delete_keyword_history), getString(R.string.dialog_btn_ok), getString(R.string.dialog_btn_cancel), 65810).show(getSupportFragmentManager(), "SimpleDialog");
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void h0(String str) {
        b.b.m.c.a.a("SearchActivity", "---doOnTextChanged--text = " + str);
        this.D = str;
        this.A.notifyDataSetChanged();
        if (k0(str)) {
            e0();
            return;
        }
        this.z.setVisibility(0);
        this.A.i(str);
        ((b.b.g.n.e) mo7getP()).j(str);
    }

    public final void i0(FunctionItem functionItem) {
        b.b.m.c.a.a("SearchActivity", "---findAndJumpToFunctionItem---item = " + functionItem);
        b.C0065b.a().a(this, functionItem);
    }

    public final boolean j0(FunctionItem functionItem) {
        int index0 = functionItem.getIndex0();
        int index1 = functionItem.getIndex1();
        int index2 = functionItem.getIndex2();
        int index3 = functionItem.getIndex3();
        if (b.b.g.j.a.o(index0, index1)) {
            return index3 != -1 ? !b.b.g.j.a.m(index0, index1, index2, index3) : !b.b.g.j.a.l(index0, index1, index2);
        }
        return true;
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void k(Context context) {
        int dimension;
        int i2;
        ArrayList arrayList = new ArrayList();
        this.x = arrayList;
        SearchKeywordAdapter searchKeywordAdapter = new SearchKeywordAdapter(arrayList);
        this.B = searchKeywordAdapter;
        this.u.setAdapter(searchKeywordAdapter);
        int dimension2 = (int) getResources().getDimension(R.dimen.dp_16);
        if (getResources().getConfiguration().orientation == 1) {
            i2 = 4;
            dimension = (int) getResources().getDimension(R.dimen.dp_8);
        } else {
            dimension = dimension2;
            i2 = 6;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, i2);
        gridLayoutManager.setOrientation(1);
        this.u.setLayoutManager(gridLayoutManager);
        this.u.setItemAnimator(new DefaultItemAnimator());
        this.u.addItemDecoration(new RecyclerSpace(dimension, dimension, dimension, dimension));
        this.B.setOnItemClickedListener(new j());
        ArrayList arrayList2 = new ArrayList();
        this.y = arrayList2;
        FunctionItemAdapter functionItemAdapter = new FunctionItemAdapter(arrayList2);
        this.A = functionItemAdapter;
        this.v.setAdapter(functionItemAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.v.setLayoutManager(linearLayoutManager);
        b.b.a.h.m.b(this.v);
        this.v.setItemAnimator(new DefaultItemAnimator());
        this.A.setOnItemClickedListener(new k());
        this.v.setOnTouchListener(new l());
        this.u.setOnTouchListener(new m());
        this.q.setOnClickListener(new n());
        p0();
        b.b.m.c.a.a("SearchActivity", "---initData--needToRequestFocus: " + p);
        if (p) {
            this.s.requestFocus();
        }
        EventBus.getDefault().register(this);
        b.b.a.f.h.a().b(new a());
        b.b.g.g.b.d(this);
        d0();
    }

    public final boolean k0(String str) {
        return str == null || str.isEmpty() || b.b.g.q.h.a.REGEX_SEARCH.contains(str);
    }

    public final void m0(FunctionItem functionItem) {
        String name;
        if (functionItem != null && (name = functionItem.getName()) != null && name.contains(";") && name.split(";").length >= 2) {
            b.b.m.c.a.a("SearchActivity", "---multiNameDiplayStrategy--multi name item, name:" + functionItem);
            FunctionItem settingItem = FunctionItem.getSettingItem(0, 0, 0);
            if (functionItem.equals(settingItem)) {
                n0(functionItem, new b.b.g.d.a().b(), settingItem);
            }
            FunctionItem settingItem2 = FunctionItem.getSettingItem(8, 1);
            if (functionItem.equalsToIndex2(settingItem2)) {
                n0(functionItem, new p().h(), settingItem2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XActivity
    public void n(Context context) {
        ((b.b.g.n.e) mo7getP()).i();
        findViewById(R.id.bg_function_options_search).setOnClickListener(this);
        this.q = findViewById(R.id.ll_search_key_words_search);
        findViewById(R.id.iv_back_search).setOnClickListener(this);
        this.F = findViewById(R.id.rl_sh);
        ImageView imageView = (ImageView) findViewById(R.id.iv_close_search);
        this.z = imageView;
        imageView.setOnClickListener(this);
        this.w = (FrameLayout) findViewById(R.id.result_fl);
        ImageView imageView2 = (ImageView) findViewById(R.id.iv_delete_history_search);
        this.E = imageView2;
        imageView2.setOnClickListener(new f());
        this.s = (EditText) findViewById(R.id.et_search_key_words_search);
        this.t = findViewById(R.id.rl_no_data_search);
        this.u = (RecyclerView) findViewById(R.id.rcv_search_history);
        this.v = (RecyclerView) findViewById(R.id.rcv_search_result);
        this.r = findViewById(R.id.ll_search_history);
        this.t.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.s.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        this.s.setLongClickable(true);
        EditText editText = this.s;
        editText.addTextChangedListener(new g(editText, ""));
        this.s.setOnEditorActionListener(new h());
        this.s.setOnFocusChangeListener(new i());
        this.q.setBackgroundResource(R.drawable.bg_et_frame_gray_normal);
        this.v.setScrollBarDefaultDelayBeforeFade(750);
        this.v.setScrollBarFadeDuration(2000);
        this.v.setScrollbarFadingEnabled(true);
        b.b.a.h.m.a(this, true);
    }

    public final void n0(FunctionItem functionItem, int i2, FunctionItem functionItem2) {
        if (functionItem == null || functionItem2 == null) {
            return;
        }
        if (functionItem2.getIndex3() == -1) {
            functionItem2.setIndex3(functionItem.getIndex3());
        }
        if (functionItem.equals(functionItem2)) {
            String name = functionItem.getName();
            b.b.m.c.a.a("SearchActivity", "---multiNameStrategy--before--name: " + name);
            if (name.contains(";")) {
                String[] strArrSplit = name.split(";");
                if (i2 >= 0 && i2 < strArrSplit.length) {
                    name = strArrSplit[i2];
                }
            }
            b.b.m.c.a.a("SearchActivity", "---multiNameStrategy--after--name: " + name);
            functionItem.setName(name);
        }
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.e newP() {
        return new b.b.g.n.e();
    }

    @Override // com.byd.mvp.mvp.XActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.bg_function_options_search /* 2131296440 */:
            case R.id.ll_search_history /* 2131297557 */:
            case R.id.rl_no_data_search /* 2131297973 */:
                b.b.m.c.a.a("SearchActivity", "---onClick---bg_function_options_search.");
                EditText editText = this.s;
                if (editText != null) {
                    editText.clearFocus();
                }
                break;
            case R.id.iv_back_search /* 2131297202 */:
                i0(null);
                break;
            case R.id.iv_close_search /* 2131297221 */:
                this.s.setText("");
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        b.b.m.c.a.a("SearchActivity", "---onDestroy---");
        super.onDestroy();
        b.b.m.f.d.d.c(getClass().getName());
        EventBus.getDefault().unregister(this);
        b.b.a.h.m.a(this, false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        b.b.m.c.a.a("SearchActivity", "---onGetMessage---message: " + aVar);
        if (aVar.h() == 65810) {
            f0();
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(StudyConfigImpl.InitCompleteMsg initCompleteMsg) {
        BydLogHelper.logW("SearchActivity", "onMessageEvent ADAS study complete!");
        b.C0048b.a().A((StudyConfigImpl) Router.b().f());
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.byd.mvp.mvp.XActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        p = this.s.hasFocus();
        b.b.m.c.a.a("SearchActivity", "---onSaveInstanceState---needToRequestFocus: " + p);
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Activity
    public void overridePendingTransition(int i2, int i3) {
        super.overridePendingTransition(0, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void p0() {
        b.b.m.c.a.a("SearchActivity", "---onEditTextFocus--");
        this.x.clear();
        ((b.b.g.n.e) mo7getP()).l();
        this.r.setVisibility(0);
        this.t.setVisibility(8);
        this.v.setVisibility(8);
        this.z.setVisibility(8);
    }

    public void q0(List<FunctionItem> list) {
        if (list == null) {
            return;
        }
        this.y.clear();
        b.b.m.c.a.a("SearchActivity", "---onGetFunctionItemList--isChinese: " + d.g.a().c0(this));
        b.b.m.c.a.a("SearchActivity", "---onGetFunctionItemList--list.size: " + list.size());
        if (!list.isEmpty()) {
            for (FunctionItem functionItem : list) {
                if (j0(functionItem)) {
                    b.b.m.c.a.a("SearchActivity", "---onGetFunctionItemList--Item Unsupported, item:" + functionItem);
                } else {
                    m0(functionItem);
                    this.y.add(functionItem);
                }
            }
        }
        b.b.m.c.a.a("SearchActivity", "---onGetFunctionItemList--mItemList.size: " + this.y.size());
        runOnUiThread(new d());
    }

    public void r0(List<SearchKeyword> list) {
        this.x.clear();
        if (list == null || list.isEmpty()) {
            this.E.setEnabled(false);
            this.F.setVisibility(8);
        } else {
            b.b.m.c.a.a("SearchActivity", "---onGetSearchKeywordList--list.size = " + list.size());
            this.x.addAll(list);
            this.E.setEnabled(true);
            this.F.setVisibility(0);
        }
        Collections.sort(this.x);
        runOnUiThread(new e());
    }
}
