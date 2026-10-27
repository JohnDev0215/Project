package org.chromium.chrome.browser.ntp;

import android.content.ContentValues;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import defpackage.bk3;
import defpackage.c30;
import defpackage.im;
import defpackage.n3;
import defpackage.vh3;
import java.util.ArrayList;
import org.chromium.base.LocaleUtils;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.SynchronousInitializationActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.ntp.sql.CardDBHelper;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: loaded from: classes2.dex */
public class CardEditActivity extends SynchronousInitializationActivity {
    public static final /* synthetic */ int U = 0;
    public int I = 0;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public ArrayList f69J;
    public int K;
    public BookmarkId L;
    public BookmarkModel M;
    public TextView N;
    public EditText O;
    public EditText P;
    public View Q;
    public View R;
    public View S;
    public View T;

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final boolean H1() {
        return true;
    }

    public final void K1() {
        if (O1()) {
            im imVar = new im();
            imVar.c = N1(this.P);
            imVar.d = N1(this.O);
            CardDBHelper.q(this).t(0, imVar, true);
            finish();
        }
    }

    public final void L1() {
        boolean zIsEmpty = TextUtils.isEmpty(N1(this.O));
        boolean zIsEmpty2 = TextUtils.isEmpty(N1(this.P));
        int i = 8;
        this.Q.setVisibility(zIsEmpty ? 8 : 0);
        View view = this.R;
        if (!zIsEmpty2 && this.M == null) {
            i = 0;
        }
        view.setVisibility(i);
        this.T.setEnabled(!(zIsEmpty || zIsEmpty2));
    }

    public final void M1() {
        if (O1()) {
            im imVar = (im) this.f69J.get(this.K);
            imVar.c = N1(this.P);
            imVar.d = N1(this.O);
            CardDBHelper cardDBHelperQ = CardDBHelper.q(this);
            String str = imVar.e;
            ((NewTabPageLayout) cardDBHelperQ.g).g();
            String[] strArr = {str};
            ContentValues contentValues = new ContentValues();
            contentValues.put("tittle", imVar.d);
            contentValues.put("url", imVar.c);
            contentValues.put("back", imVar.d);
            cardDBHelperQ.e.update("card", contentValues, "id=?", strArr);
            int i = R$drawable.icon_dark_public_green_cherk;
            int i2 = R$string.card_edit_sucess;
            Toast toast = new Toast(this);
            View viewInflate = LayoutInflater.from(this).inflate(R$layout.layout_toast_clear_success, (ViewGroup) null, false);
            ImageView imageView = (ImageView) viewInflate.findViewById(R$id.iv_toast);
            TextView textView = (TextView) viewInflate.findViewById(R$id.tv_toast);
            toast.setView(viewInflate);
            if (imageView != null) {
                imageView.setImageResource(i);
            }
            if (textView != null) {
                textView.setText(i2);
            }
            toast.show();
            finish();
        }
    }

    public final String N1(EditText editText) {
        return editText.getText().toString().trim();
    }

    public final boolean O1() {
        im imVar;
        boolean z = false;
        if (TextUtils.isEmpty(N1(this.O))) {
            P1(this.O, getString(R$string.bookmark_missing_title), null);
            this.O.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(N1(this.P))) {
            P1(this.P, getString(R$string.bookmark_missing_url), getString(R$string.url_input_msg));
            this.P.requestFocus();
            return false;
        }
        if (!bk3.o(N1(this.P))) {
            Toast.makeText(this, getString(R$string.url_input_msg), 0).show();
            return false;
        }
        ArrayList arrayList = this.f69J;
        if (arrayList != null && !arrayList.isEmpty()) {
            int i = 0;
            while (true) {
                if (i >= this.f69J.size()) {
                    break;
                }
                if ((this.I != 1 || i != this.K) && (imVar = (im) this.f69J.get(i)) != null && !TextUtils.isEmpty(imVar.d) && !TextUtils.isEmpty(N1(this.O)) && imVar.d.equals(N1(this.O))) {
                    Toast.makeText(this, getString(R$string.rename_failure_name_conflict), 0).show();
                    z = true;
                    break;
                }
                i++;
            }
        }
        return !z;
    }

    public final void P1(EditText editText, String str, String str2) {
        if (TextUtils.isEmpty(N1(editText))) {
            Toast.makeText(this, str, 0).show();
        } else {
            if (bk3.o(N1(editText)) || str2 == null) {
                return;
            }
            Toast.makeText(this, str2, 0).show();
        }
    }

    @Override // org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("BookmarkEditActivity.BookmarkId");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.L = BookmarkId.a(stringExtra);
            this.M = BookmarkModel.v(Profile.d());
        }
        setContentView(R$layout.speed_card_add_edit);
        vh3.e(344, this, true);
        this.N = (TextView) findViewById(R$id.tv_title);
        this.O = (EditText) findViewById(R$id.edit_title);
        this.P = (EditText) findViewById(R$id.edit_url);
        this.Q = findViewById(R$id.edit_cancel_title);
        this.R = findViewById(R$id.edit_cancel_url);
        this.S = findViewById(R$id.iv_bookmark);
        Button button = (Button) findViewById(R$id.btn_cancel);
        this.T = findViewById(R$id.btn_sure);
        View viewFindViewById = findViewById(R$id.view_title);
        if (!LocaleUtils.b()) {
            viewFindViewById.setTextDirection(3);
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.ll_sure_cancel);
        if (n3.Q0()) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        this.P.setText("https://");
        this.f69J = CardDBHelper.q(this).C();
        int intExtra = getIntent().getIntExtra("CardType", 0);
        this.I = intExtra;
        if (intExtra == 0) {
            this.N.setText(getString(R$string.card_add_msg));
        } else {
            if (intExtra == 1) {
                this.K = getIntent().getIntExtra("CARD_POS", 0);
                this.N.setText(getString(R$string.card_edit_msg));
            } else {
                this.N.setText(getString(R$string.card_add_msg));
                this.S.setVisibility(8);
            }
            BookmarkModel bookmarkModel = this.M;
            if (bookmarkModel != null) {
                BookmarkItem bookmarkItemH = bookmarkModel.h(this.L);
                if (bookmarkItemH != null) {
                    this.O.setText(bookmarkItemH.a);
                    this.P.setText(bookmarkItemH.b.j());
                    this.P.setEnabled(false);
                    EditText editText = this.P;
                    editText.setTextColor(editText.getCurrentHintTextColor());
                }
            } else {
                im imVar = (im) this.f69J.get(this.K);
                EditText editText2 = this.O;
                if (editText2 != null) {
                    editText2.setText(imVar.d);
                }
                EditText editText3 = this.P;
                if (editText3 != null) {
                    editText3.setText(imVar.c);
                }
            }
        }
        c30 c30Var = new c30(this, 3, button);
        button.setOnClickListener(c30Var);
        this.T.setOnClickListener(c30Var);
        this.S.setOnClickListener(c30Var);
        this.Q.setOnClickListener(c30Var);
        this.R.setOnClickListener(c30Var);
        L1();
        this.P.addTextChangedListener(new a());
        this.O.addTextChangedListener(new b());
        EditText editText4 = this.P;
        editText4.setSelection(editText4.getText().length());
    }

    @Override // android.app.Activity
    public final boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R$id.save_menu_tab) {
            int i = this.I;
            if (i != 0 && i != 2) {
                M1();
            } else if (this.f69J.size() >= 12) {
                Toast.makeText(this, getString(R$string.card_full_msg), 0).show();
            } else {
                K1();
            }
            return true;
        }
        if (menuItem.getItemId() == R$id.bookmark_menu_tab) {
            n3.l1(null, false);
            finish();
            return true;
        }
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            int i4 = CardEditActivity.U;
            CardEditActivity.this.L1();
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public class b implements TextWatcher {
        public b() {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            int i4 = CardEditActivity.U;
            CardEditActivity.this.L1();
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }
}
