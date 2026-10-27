package org.chromium.chrome.browser.app.bookmarks;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import defpackage.mg;
import defpackage.n3;
import defpackage.q11;
import defpackage.rf;
import defpackage.sf;
import defpackage.vh3;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.SynchronousInitializationActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkEditActivity extends SynchronousInitializationActivity {
    public static final /* synthetic */ int Q = 0;
    public BookmarkModel I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public BookmarkId f57J;
    public EditText K;
    public EditText L;
    public Button M;
    public View N;
    public View O;
    public final a P = new a();

    public class a extends mg {
        public a() {
        }

        @Override // defpackage.mg
        public final void f() {
            BookmarkEditActivity bookmarkEditActivity = BookmarkEditActivity.this;
            if (bookmarkEditActivity.I.f(bookmarkEditActivity.f57J)) {
                bookmarkEditActivity.M1(true);
            } else {
                bookmarkEditActivity.getClass();
                bookmarkEditActivity.finish();
            }
        }
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final boolean H1() {
        return true;
    }

    public final boolean K1(EditText editText) {
        TextUtils.isEmpty(L1(editText));
        editText.hasFocus();
        boolean z = TextUtils.isEmpty(L1(this.L)) || TextUtils.isEmpty(L1(this.K));
        this.M.setEnabled(!z);
        return z;
    }

    public final String L1(EditText editText) {
        return editText.getText().toString().trim();
    }

    public final void M1(boolean z) {
        BookmarkItem bookmarkItemH = this.I.h(this.f57J);
        if (!z) {
            this.K.setText(bookmarkItemH.a);
            this.L.setText(bookmarkItemH.b.j());
        }
        boolean z2 = false;
        this.N.setVisibility(TextUtils.isEmpty(L1(this.K)) ? 8 : 0);
        this.O.setVisibility(TextUtils.isEmpty(L1(this.L)) ? 8 : 0);
        this.K.setEnabled(bookmarkItemH.a());
        EditText editText = this.L;
        if (bookmarkItemH.a() && bookmarkItemH.c.getType() == 0) {
            z2 = true;
        }
        editText.setEnabled(z2);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 15 && i2 == -1) {
            this.f57J = BookmarkId.a(q11.r(intent, "BookmarkFolderSelectActivity.bookmarkMoveResult"));
            M1(true);
        }
    }

    @Override // org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.I = BookmarkModel.v(Profile.d());
        this.f57J = BookmarkId.a(getIntent().getStringExtra("BookmarkEditActivity.BookmarkId"));
        this.I.c(this.P);
        BookmarkItem bookmarkItemH = this.I.h(this.f57J);
        if (!this.I.f(this.f57J) || bookmarkItemH == null) {
            finish();
            return;
        }
        setContentView(R$layout.activity_bookamrk_edit);
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.ll_sure_cancel);
        int i = 0;
        if (n3.Q0()) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        vh3.e(344, this, true);
        this.K = (EditText) findViewById(R$id.edit_title);
        this.L = (EditText) findViewById(R$id.edit_url);
        this.N = findViewById(R$id.edit_cancel_title);
        this.O = findViewById(R$id.edit_cancel_url);
        M1(false);
        Button button = (Button) findViewById(R$id.btn_cancel);
        this.M = (Button) findViewById(R$id.btn_sure);
        findViewById(R$id.view_title);
        findViewById(R$id.view_url);
        rf rfVar = new rf(this, 0, button);
        button.setOnClickListener(rfVar);
        this.M.setOnClickListener(rfVar);
        this.N.setOnClickListener(rfVar);
        this.O.setOnClickListener(rfVar);
        sf sfVar = new sf(i, this);
        this.K.setOnFocusChangeListener(sfVar);
        this.L.setOnFocusChangeListener(sfVar);
        this.L.addTextChangedListener(new b());
        this.K.addTextChangedListener(new c());
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        this.I.r(this.P);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem != null) {
            if (menuItem.getItemId() != 16908332) {
                return super.onOptionsItemSelected(menuItem);
            }
            finish();
            return true;
        }
        Log.i("cr_BookmarkEdit", "Delete button pressed by user! isFinishing() == " + isFinishing());
        this.I.e(this.f57J);
        finish();
        return true;
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStop() {
        super.onStop();
    }

    public class b implements TextWatcher {
        public b() {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            BookmarkEditActivity bookmarkEditActivity = BookmarkEditActivity.this;
            bookmarkEditActivity.K1(bookmarkEditActivity.L);
            bookmarkEditActivity.O.setVisibility(TextUtils.isEmpty(bookmarkEditActivity.L1(bookmarkEditActivity.L)) ? 8 : 0);
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class c implements TextWatcher {
        public c() {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            BookmarkEditActivity bookmarkEditActivity = BookmarkEditActivity.this;
            bookmarkEditActivity.K1(bookmarkEditActivity.K);
            bookmarkEditActivity.N.setVisibility(TextUtils.isEmpty(bookmarkEditActivity.L1(bookmarkEditActivity.K)) ? 8 : 0);
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }
}
