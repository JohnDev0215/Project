package org.chromium.chrome.browser.app.bookmarks;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import defpackage.mg;
import defpackage.n3;
import defpackage.of;
import defpackage.vh3;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.SynchronousInitializationActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkAddEditFolderActivity extends SynchronousInitializationActivity implements View.OnClickListener {
    public static final /* synthetic */ int Q = 0;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public BookmarkId f56J;
    public BookmarkModel K;
    public ArrayList L;
    public BookmarkId M;
    public EditText N;
    public View O;
    public final a P = new a();

    public class a extends mg {
        public a() {
        }

        @Override // defpackage.mg
        public final void f() {
            BookmarkAddEditFolderActivity bookmarkAddEditFolderActivity = BookmarkAddEditFolderActivity.this;
            if (bookmarkAddEditFolderActivity.I) {
                if (bookmarkAddEditFolderActivity.K.f(bookmarkAddEditFolderActivity.f56J)) {
                    bookmarkAddEditFolderActivity.f56J = bookmarkAddEditFolderActivity.f56J;
                    return;
                } else {
                    bookmarkAddEditFolderActivity.f56J = bookmarkAddEditFolderActivity.K.k();
                    return;
                }
            }
            if (bookmarkAddEditFolderActivity.K.f(bookmarkAddEditFolderActivity.M)) {
                bookmarkAddEditFolderActivity.f56J = bookmarkAddEditFolderActivity.K.h(bookmarkAddEditFolderActivity.M).e;
            } else {
                bookmarkAddEditFolderActivity.finish();
            }
        }

        @Override // defpackage.mg
        public final void l(BookmarkItem bookmarkItem, BookmarkItem bookmarkItem2, int i) {
            if (bookmarkItem.c.equals(bookmarkItem2.c)) {
                return;
            }
            BookmarkAddEditFolderActivity bookmarkAddEditFolderActivity = BookmarkAddEditFolderActivity.this;
            BookmarkModel bookmarkModel = bookmarkAddEditFolderActivity.K;
            bookmarkModel.getClass();
            Object obj = ThreadUtils.a;
            long j = bookmarkModel.a;
            BookmarkId bookmarkId = bookmarkItem2.c;
            if ((j == 0 ? null : (BookmarkId) N.MvzSBRAY(j, bookmarkModel, bookmarkId.getId(), bookmarkId.getType(), i)).equals(bookmarkAddEditFolderActivity.M)) {
                bookmarkAddEditFolderActivity.f56J = bookmarkId;
            }
        }

        @Override // defpackage.mg
        public final void m(BookmarkItem bookmarkItem, BookmarkItem bookmarkItem2, boolean z) {
            BookmarkId bookmarkId = bookmarkItem2.c;
            BookmarkAddEditFolderActivity bookmarkAddEditFolderActivity = BookmarkAddEditFolderActivity.this;
            if (bookmarkId.equals(bookmarkAddEditFolderActivity.M)) {
                bookmarkAddEditFolderActivity.finish();
            }
        }
    }

    public static void N1(Activity activity, ArrayList arrayList) {
        Intent intent = new Intent(activity, (Class<?>) BookmarkAddEditFolderActivity.class);
        intent.putExtra("BookmarkAddEditFolderActivity.isAddMode", true);
        ArrayList<String> arrayList2 = new ArrayList<>(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((BookmarkId) it.next()).toString());
        }
        intent.putStringArrayListExtra("BookmarkFolderSelectActivity.bookmarksToMove", arrayList2);
        activity.startActivityForResult(intent, 13);
    }

    public static void O1(Context context, BookmarkId bookmarkId) {
        n3.X0("MobileBookmarkManagerEditFolder");
        Intent intent = new Intent(context, (Class<?>) BookmarkAddEditFolderActivity.class);
        intent.putExtra("BookmarkAddEditFolderActivity.isAddMode", false);
        intent.putExtra("BookmarkAddEditFolderActivity.BookmarkId", bookmarkId.toString());
        context.startActivity(intent);
    }

    public final boolean K1(Button button) {
        boolean zIsEmpty = TextUtils.isEmpty(L1());
        button.setEnabled(!zIsEmpty);
        this.O.setVisibility(zIsEmpty ? 8 : 0);
        return zIsEmpty;
    }

    public final String L1() {
        return this.N.getText().toString().trim();
    }

    public final void M1(boolean z) {
        int i = R$drawable.icon_dark_public_green_cherk;
        int i2 = z ? R$string.card_edit_sucess : R$string.save_failed;
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
        if (z) {
            return;
        }
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10 && i2 == -1) {
            this.f56J = BookmarkId.a(intent.getStringExtra("BookmarkFolderSelectActivity.selectedFolder"));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!this.I) {
            startActivity(BookmarkFolderSelectActivity.K1(this, false, this.M));
            return;
        }
        ArrayList arrayList = this.L;
        BookmarkId[] bookmarkIdArr = new BookmarkId[arrayList.size()];
        arrayList.toArray(bookmarkIdArr);
        startActivityForResult(BookmarkFolderSelectActivity.K1(this, true, bookmarkIdArr), 10);
    }

    @Override // org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BookmarkModel bookmarkModelV = BookmarkModel.v(Profile.d());
        this.K = bookmarkModelV;
        bookmarkModelV.c(this.P);
        boolean booleanExtra = getIntent().getBooleanExtra("BookmarkAddEditFolderActivity.isAddMode", false);
        this.I = booleanExtra;
        if (booleanExtra) {
            ArrayList<String> stringArrayListExtra = getIntent().getStringArrayListExtra("BookmarkFolderSelectActivity.bookmarksToMove");
            this.L = new ArrayList(stringArrayListExtra.size());
            Iterator<String> it = stringArrayListExtra.iterator();
            while (it.hasNext()) {
                this.L.add(BookmarkId.a(it.next()));
            }
        } else {
            this.M = BookmarkId.a(getIntent().getStringExtra("BookmarkAddEditFolderActivity.BookmarkId"));
        }
        setContentView(R$layout.activity_folder_edit_add);
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.ll_sure_cancel);
        if (n3.Q0()) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        vh3.e(312, this, true);
        this.N = (EditText) findViewById(R$id.et_bookmark_folder);
        this.O = findViewById(R$id.et_bookmark_folder_cancel);
        TextView textView = (TextView) findViewById(R$id.tv_title);
        if (this.I) {
            textView.setText(textView.getText().toString().replace("…", ""));
            this.f56J = this.K.k();
        } else {
            textView.setText(getString(R$string.edit_folder));
            BookmarkItem bookmarkItemH = this.K.h(this.M);
            this.f56J = bookmarkItemH.e;
            this.N.setText(bookmarkItemH.a);
        }
        Button button = (Button) findViewById(R$id.btn_cancel);
        Button button2 = (Button) findViewById(R$id.btn_sure);
        View viewFindViewById = findViewById(R$id.folder_edit);
        of ofVar = new of(this, button, button2, viewFindViewById, 0);
        button.setOnClickListener(ofVar);
        button2.setOnClickListener(ofVar);
        this.O.setOnClickListener(ofVar);
        K1(button2);
        this.N.addTextChangedListener(new b(viewFindViewById, button2));
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        this.K.r(this.P);
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStop() {
        super.onStop();
    }

    public class b implements TextWatcher {
        public final /* synthetic */ Button e;

        public b(View view, Button button) {
            this.e = button;
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            int i4 = BookmarkAddEditFolderActivity.Q;
            BookmarkAddEditFolderActivity.this.K1(this.e);
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }
}
