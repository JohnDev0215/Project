package org.chromium.chrome.browser.app.bookmarks;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import defpackage.bz;
import defpackage.in3;
import defpackage.kp2;
import defpackage.lh;
import defpackage.m92;
import defpackage.mg;
import defpackage.n3;
import defpackage.om3;
import defpackage.q11;
import defpackage.re3;
import defpackage.uf;
import defpackage.uh3;
import defpackage.vh3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.SynchronousInitializationActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkFolderSelectActivity extends SynchronousInitializationActivity implements AdapterView.OnItemClickListener {
    public static final /* synthetic */ int Q = 0;
    public BookmarkModel I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f58J;
    public ArrayList K;
    public BookmarkId L;
    public b M;
    public ListView N;
    public int O;
    public final a P = new a();

    public class a extends mg {
        public a() {
        }

        @Override // defpackage.mg
        public final void f() {
            int i = BookmarkFolderSelectActivity.Q;
            BookmarkFolderSelectActivity.this.N1();
        }

        @Override // defpackage.mg
        public final void m(BookmarkItem bookmarkItem, BookmarkItem bookmarkItem2, boolean z) {
            BookmarkFolderSelectActivity bookmarkFolderSelectActivity = BookmarkFolderSelectActivity.this;
            if (!bookmarkFolderSelectActivity.K.contains(bookmarkItem2.c)) {
                if (bookmarkItem2.d) {
                    bookmarkFolderSelectActivity.N1();
                }
            } else {
                bookmarkFolderSelectActivity.K.remove(bookmarkItem2.c);
                if (bookmarkFolderSelectActivity.K.isEmpty()) {
                    bookmarkFolderSelectActivity.L1(bookmarkFolderSelectActivity.K);
                }
            }
        }
    }

    public static class b extends BaseAdapter {
        public final int e;
        public final int f;
        public List<c> g = new ArrayList();

        public b(Context context) {
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.bookmark_folder_item_left);
            this.e = dimensionPixelSize;
            this.f = dimensionPixelSize * 2;
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            return this.g.size();
        }

        @Override // android.widget.Adapter
        public final Object getItem(int i) {
            return this.g.get(i);
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public final int getItemViewType(int i) {
            return this.g.get(i).e;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            Drawable drawableA;
            c cVar = this.g.get(i);
            if (view != null && cVar.e != 1) {
                return view;
            }
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.modern_list_item_view_divider, viewGroup, false);
                view.findViewById(R$id.divider).setBackgroundResource(R$color.color_divider_select);
            }
            ((TextView) view.findViewById(R$id.title)).setText(cVar.b);
            view.findViewById(R$id.description).setVisibility(8);
            ImageView imageView = (ImageView) view.findViewById(R$id.start_icon);
            if (cVar.e == 1) {
                Context context = view.getContext();
                int i2 = R$drawable.folder_to_bookmark;
                cVar.a.getType();
                int i3 = lh.a;
                drawableA = uh3.e(context, i2, kp2.a());
            } else {
                drawableA = re3.a(view.getResources(), R$drawable.ic_add, view.getContext().getTheme());
                drawableA.setTintList(bz.b(view.getContext(), R$color.default_icon_color_tint_list));
            }
            imageView.setImageDrawable(drawableA);
            ImageView imageView2 = (ImageView) view.findViewById(R$id.end_button);
            imageView2.setImageResource(R$drawable.bookmark_delete_check_yes);
            imageView2.setVisibility(cVar.d ? 0 : 8);
            int iMin = Math.min(cVar.c, 5) * this.f;
            int i4 = this.e;
            int paddingTop = view.getPaddingTop();
            int paddingBottom = view.getPaddingBottom();
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.e.i(view, iMin + i4, paddingTop, i4, paddingBottom);
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public final int getViewTypeCount() {
            return 2;
        }
    }

    public static class c {
        public final BookmarkId a;
        public String b;
        public int c;
        public boolean d;
        public final int e = 1;

        public c(BookmarkId bookmarkId, int i, String str, boolean z) {
            this.c = i;
            this.a = bookmarkId;
            this.b = str;
            this.d = z;
        }
    }

    public static Intent K1(Context context, boolean z, BookmarkId... bookmarkIdArr) {
        Intent intent = new Intent(context, (Class<?>) BookmarkFolderSelectActivity.class);
        intent.putExtra("BookmarkFolderSelectActivity.isCreatingFolder", z);
        ArrayList<String> arrayList = new ArrayList<>(bookmarkIdArr.length);
        for (BookmarkId bookmarkId : bookmarkIdArr) {
            arrayList.add(bookmarkId.toString());
        }
        intent.putStringArrayListExtra("BookmarkFolderSelectActivity.bookmarksToMove", arrayList);
        return intent;
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final boolean H1() {
        return true;
    }

    public final void L1(ArrayList arrayList) {
        if (getCallingActivity() != null) {
            if (arrayList.size() == 1) {
                Intent intent = new Intent();
                intent.putExtra("BookmarkFolderSelectActivity.bookmarkMoveResult", ((BookmarkId) arrayList.get(0)).toString());
                setResult(-1, intent);
            } else {
                setResult(0);
            }
        }
        finish();
    }

    public final void M1(BookmarkId bookmarkId) {
        ArrayList arrayList = new ArrayList();
        m92.a(this.I, this.K, arrayList, bookmarkId);
        this.I.w(this.K, bookmarkId);
        arrayList.addAll(this.K);
        int i = lh.a;
        SharedPreferencesManager.getInstance().o("enhanced_bookmark_last_used_parent_folder", bookmarkId.toString());
        L1(arrayList);
    }

    public final void N1() {
        ArrayList arrayList = new ArrayList();
        BookmarkId bookmarkIdM = this.I.m();
        arrayList.add(bookmarkIdM);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(0);
        BookmarkModel bookmarkModel = this.I;
        ArrayList arrayList3 = this.K;
        long j = bookmarkModel.a;
        if (j != 0) {
            Object obj = ThreadUtils.a;
            N.MEqyLeo9(j, bookmarkModel, arrayList, arrayList2);
            if (arrayList3 != null && arrayList3.size() != 0) {
                int i = 0;
                boolean z = false;
                int i2 = -1;
                while (i < arrayList.size()) {
                    int iIntValue = ((Integer) arrayList2.get(i)).intValue();
                    if (z) {
                        if (iIntValue <= i2) {
                            z = false;
                            i2 = -1;
                        } else {
                            arrayList.remove(i);
                            arrayList2.remove(i);
                            i--;
                        }
                    }
                    if (!z && arrayList3.contains((BookmarkId) arrayList.get(i))) {
                        arrayList.remove(i);
                        arrayList2.remove(i);
                        i--;
                        z = true;
                        i2 = iIntValue;
                    }
                    i++;
                }
            }
        }
        ArrayList arrayList4 = new ArrayList(arrayList.size() + 3);
        c cVar = null;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            BookmarkId bookmarkId = (BookmarkId) arrayList.get(i3);
            BookmarkModel bookmarkModel2 = this.I;
            bookmarkModel2.getClass();
            Object obj2 = ThreadUtils.a;
            if (bookmarkModel2.a == 0 ? false : bookmarkId.getType() == 2 ? true : N.MCNIYDWB(bookmarkModel2.a, bookmarkModel2, bookmarkId.getId(), bookmarkId.getType())) {
                c cVar2 = new c(bookmarkId, ((Integer) arrayList2.get(i3)).intValue(), this.I.h(bookmarkId).a, bookmarkId.equals(this.L));
                arrayList4.add(cVar2);
                if (!this.f58J && this.L.equals(bookmarkId)) {
                    cVar = cVar2;
                }
            }
        }
        BookmarkId bookmarkIdK = this.I.k();
        if (arrayList4.size() > 1 && ((c) arrayList4.get(0)).a.equals(bookmarkIdM) && ((c) arrayList4.get(1)).a.equals(bookmarkIdK)) {
            c cVar3 = (c) arrayList4.get(0);
            cVar3.c++;
            arrayList4.remove(0);
            arrayList4.add(1, cVar3);
            c cVar4 = (c) arrayList4.get(0);
            cVar4.b = bookmarkIdK.equals(cVar4.a) ? getString(R$string.bookmark_main) : cVar4.b;
        }
        b bVar = this.M;
        bVar.g = arrayList4;
        bVar.notifyDataSetChanged();
        if (cVar != null) {
            int iIndexOf = this.M.g.indexOf(cVar);
            this.O = iIndexOf;
            this.N.smoothScrollToPosition(iIndexOf);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 13 && i2 == -1) {
            M1(BookmarkId.a(intent.getStringExtra("BookmarkAddEditFolderActivity.createdBookmark")));
        }
    }

    @Override // org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.I = BookmarkModel.v(Profile.d());
        ArrayList<String> arrayListQ = q11.q(getIntent(), "BookmarkFolderSelectActivity.bookmarksToMove");
        this.K = new ArrayList(arrayListQ.size());
        BookmarkModel bookmarkModel = this.I;
        if (!bookmarkModel.c) {
            finish();
            return;
        }
        bookmarkModel.c(this.P);
        Iterator<String> it = arrayListQ.iterator();
        while (it.hasNext()) {
            BookmarkId bookmarkIdA = BookmarkId.a(it.next());
            if (this.I.f(bookmarkIdA)) {
                this.K.add(bookmarkIdA);
            }
        }
        if (this.K.isEmpty()) {
            finish();
            return;
        }
        int i = 0;
        boolean booleanExtra = getIntent().getBooleanExtra("BookmarkFolderSelectActivity.isCreatingFolder", false);
        this.f58J = booleanExtra;
        if (booleanExtra) {
            this.L = this.I.k();
        } else {
            this.L = this.I.h((BookmarkId) this.K.get(0)).e;
        }
        if (this.L.equals(this.I.n())) {
            this.L = this.I.k();
        }
        setContentView(R$layout.activity_bookmark_select);
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.ll_sure_cancel);
        if (n3.Q0()) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        vh3.e(500, this, false);
        ListView listView = (ListView) findViewById(R$id.bookmark_folder_list);
        this.N = listView;
        listView.setOnItemClickListener(this);
        this.I.m();
        this.I.k();
        b bVar = new b(this);
        this.M = bVar;
        this.N.setAdapter((ListAdapter) bVar);
        N1();
        Button button = (Button) findViewById(R$id.btn_cancel);
        Button button2 = (Button) findViewById(R$id.btn_sure);
        uf ufVar = new uf(this, i, button, button2);
        button.setOnClickListener(ufVar);
        button2.setOnClickListener(ufVar);
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        this.I.r(this.P);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ((c) adapterView.getItemAtPosition(this.O)).d = false;
        ((c) adapterView.getItemAtPosition(i)).d = true;
        this.M.notifyDataSetChanged();
        this.O = i;
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }
}
