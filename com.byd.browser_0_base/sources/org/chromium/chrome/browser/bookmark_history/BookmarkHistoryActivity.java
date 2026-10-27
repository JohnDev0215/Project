package org.chromium.chrome.browser.bookmark_history;

import android.R;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import defpackage.bq;
import defpackage.eg;
import defpackage.gv0;
import defpackage.gx;
import defpackage.js1;
import defpackage.ke0;
import defpackage.kv1;
import defpackage.q11;
import defpackage.vf;
import defpackage.wp2;
import defpackage.xb;
import defpackage.xf;
import defpackage.zb;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ChromeBaseAppCompatActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkSelectableListLayout;
import org.chromium.chrome.browser.history.BrowsingHistoryBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkHistoryActivity extends ChromeBaseAppCompatActivity implements wp2.c {
    public static final /* synthetic */ int O = 0;
    public ViewPager I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public eg f62J;
    public gv0 K;
    public wp2 L;
    public boolean M = false;
    public TabLayout N;

    public class a implements js1 {
        public SpannableStringBuilder a;
        public ForegroundColorSpan b;
        public final /* synthetic */ TextView c;

        public a(TextView textView) {
            this.c = textView;
        }

        public final void a(ArrayList arrayList, boolean z) {
            BookmarkHistoryActivity bookmarkHistoryActivity = BookmarkHistoryActivity.this;
            eg egVar = bookmarkHistoryActivity.f62J;
            ViewGroup viewGroup = egVar.p;
            if (viewGroup != null) {
                viewGroup.setVisibility(z ? 0 : 8);
            }
            boolean z2 = !z;
            BookmarkSelectableListLayout<BookmarkId> bookmarkSelectableListLayout = egVar.g;
            bookmarkSelectableListLayout.g.setVisibility(z2 ? 0 : 8);
            bookmarkSelectableListLayout.o.setVisibility(z2 ? 0 : 8);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) bookmarkSelectableListLayout.h.getLayoutParams();
            marginLayoutParams.topMargin = z2 ? (int) bookmarkSelectableListLayout.getResources().getDimension(R$dimen.selectable_list_toolbar_height) : 0;
            bookmarkSelectableListLayout.h.setLayoutParams(marginLayoutParams);
            int i = z ? 0 : 8;
            TextView textView = this.c;
            textView.setVisibility(i);
            bookmarkHistoryActivity.N.setVisibility(z ? 8 : 0);
            if (this.a == null) {
                this.a = new SpannableStringBuilder();
            }
            if (this.b == null) {
                this.b = new ForegroundColorSpan(bookmarkHistoryActivity.getColor(R$color.color_7fffffff));
            }
            String string = bookmarkHistoryActivity.getString(R$string.bookmark_select_num, Integer.valueOf(arrayList.size()));
            this.a.clear();
            this.a.append((CharSequence) string);
            int iIndexOf = string.indexOf("(");
            if (iIndexOf < 0) {
                iIndexOf = string.indexOf("（");
            }
            if (iIndexOf >= 0 && iIndexOf < this.a.length()) {
                SpannableStringBuilder spannableStringBuilder = this.a;
                spannableStringBuilder.setSpan(this.b, iIndexOf, spannableStringBuilder.length(), 17);
            }
            textView.setText(this.a);
        }
    }

    public static class b extends kv1 {
        public final List<View> b;
        public final List<String> c;

        public b(ArrayList arrayList, ArrayList arrayList2) {
            this.b = arrayList;
            this.c = arrayList2;
        }

        @Override // defpackage.kv1
        public final void a(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(this.b.get(i));
        }

        @Override // defpackage.kv1
        public final int b() {
            return this.b.size();
        }

        @Override // defpackage.kv1
        public final CharSequence c(int i) {
            return this.c.get(i);
        }

        @Override // defpackage.kv1
        public final Object d(int i, ViewGroup viewGroup) {
            List<View> list = this.b;
            viewGroup.addView(list.get(i));
            return list.get(i);
        }

        @Override // defpackage.kv1
        public final boolean e(View view, Object obj) {
            return view == obj;
        }
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final void J1() {
        super.J1();
    }

    public final void K1(TextView textView, boolean z) {
        textView.setSelected(z);
        textView.setTextColor(getColor(z ? R$color.color_tabLayout_text_select_ok : R$color.default_text_color_secondary_baseline));
        textView.setTextSize(getResources().getDimension(z ? R$dimen.custom_tabs_title_text_size : R$dimen.custom_tabs_url_text_size));
        textView.getPaint().setFakeBoldText(z);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 14 && i2 == -1) {
            this.f62J.i.d(BookmarkId.a(intent.getStringExtra("BookmarkEditActivity.VisitBookmarkId")));
        } else if (i == 13 && i2 == -1) {
            eg egVar = this.f62J;
            ViewGroup viewGroup = egVar.p;
            if (viewGroup != null) {
                viewGroup.setVisibility(8);
            }
            egVar.o.b();
        }
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        eg egVar = this.f62J;
        egVar.e((LayoutInflater) egVar.f.getContext().getSystemService("layout_inflater"), egVar.p.getVisibility());
        xf xfVar = egVar.i.o;
        if (xfVar.j != null) {
            xfVar.o();
        }
        this.K.l.o.o();
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.bookmark_history_activity);
        this.M = q11.i(getIntent(), "org.chromium.chrome.browser.incognito_mode", false);
        C1((Toolbar) findViewById(R$id.action_bar));
        ActionBar actionBarB1 = B1();
        Objects.requireNonNull(actionBarB1);
        actionBarB1.t(true);
        B1().u(R$string.back);
        this.L = new wp2(this, (ViewGroup) findViewById(R.id.content), null);
        this.f62J = new eg(this, (ComponentName) q11.n(getIntent(), "org.chromium.chrome.browser.parent_component"), true, this.M, this.L, Profile.d());
        String dataString = getIntent().getDataString();
        if (TextUtils.isEmpty(dataString)) {
            dataString = "chrome-native://bookmarks/";
        }
        this.f62J.h(dataString);
        this.K = new gv0(this, true, this.L, this.M, null, q11.i(getIntent(), "org.chromium.chrome.browser.history_clusters.show", false), q11.r(getIntent(), "org.chromium.chrome.browser.history_clusters.query"), new BrowsingHistoryBridge(Profile.d()));
        this.N = (TabLayout) findViewById(R$id.bookmark_history_tabLayout);
        this.I = (ViewPager) findViewById(R$id.bookmark_history_viewpager);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.f62J.f);
        arrayList.add(this.K.i);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(getString(R$string.menu_bookmarks));
        arrayList2.add(getString(R$string.menu_history));
        this.I.setAdapter(new b(arrayList, arrayList2));
        this.N.setupWithViewPager(this.I);
        int i = 0;
        while (i < arrayList2.size()) {
            TabLayout.g gVarK = this.N.k(i);
            if (gVarK != null) {
                gVarK.c(R$layout.bookmark_history_tab_layout);
                View view = gVarK.f;
                if (view != null) {
                    TextView textView = (TextView) view.findViewById(R$id.tab_item_name);
                    textView.setText((CharSequence) arrayList2.get(i));
                    K1(textView, i == 0);
                }
            }
            i++;
        }
        this.N.addOnTabSelectedListener((TabLayout.d) new vf(this));
        this.f62J.g.setSelect(true);
        SparseIntArray sparseIntArray = zb.k;
        boolean zA = bq.b.a();
        OnBackPressedDispatcher onBackPressedDispatcher = this.k;
        if (zA) {
            xb.a(this, onBackPressedDispatcher, this.f62J);
            xb.a(this, onBackPressedDispatcher, this.K);
        } else {
            eg egVar = this.f62J;
            Objects.requireNonNull(egVar);
            xb.b(this, onBackPressedDispatcher, new ke0(4, egVar));
            gv0 gv0Var = this.K;
            Objects.requireNonNull(gv0Var);
            xb.b(this, onBackPressedDispatcher, new gx(6, gv0Var));
        }
        a aVar = new a((TextView) findViewById(R$id.tv_check_num));
        this.f62J.setOnSelectListener(aVar);
        this.K.setOnSelectListener(aVar);
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        this.K.E();
        this.K = null;
        super.onDestroy();
        this.f62J.f();
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    @Override // wp2.c
    public final wp2 q0() {
        return this.L;
    }
}
