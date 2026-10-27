package org.chromium.chrome.browser.bookmarks;

import J.N;
import android.content.Context;
import defpackage.mg;
import defpackage.tp2;
import defpackage.u80;
import defpackage.wp2;
import java.util.Locale;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: compiled from: BookmarkUndoController.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends mg implements wp2.b, BookmarkModel.a {
    public final BookmarkModel e;
    public final wp2 f;
    public final Context g;
    public final u80 h;
    public final boolean i;

    public a(Context context, BookmarkModel bookmarkModel, wp2 wp2Var) {
        this.e = bookmarkModel;
        bookmarkModel.e.a(this);
        this.f = wp2Var;
        this.g = context;
        this.h = new u80();
        this.i = false;
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookmarkModel.a
    public final void a(String[] strArr) {
        tp2 tp2VarA;
        int length = strArr.length;
        Context context = this.g;
        if (length == 1) {
            tp2VarA = tp2.a(strArr[0], this, 0, 1);
            tp2VarA.c = context.getString(R$string.delete_message);
        } else {
            tp2VarA = tp2.a(String.format(Locale.getDefault(), "%d", Integer.valueOf(strArr.length)), this, 0, 1);
            tp2VarA.c = context.getString(R$string.undo_bar_multiple_delete_message);
        }
        tp2VarA.j = 3000;
        this.f.c(tp2VarA);
    }

    @Override // wp2.b
    public final void d(Object obj) {
        u80 u80Var = this.h;
        u80Var.getClass();
        if (!this.i || u80Var.e) {
            return;
        }
        n();
    }

    @Override // defpackage.mg
    public final void f() {
        this.f.a(this);
    }

    @Override // wp2.b
    public final void j(Object obj) {
        this.h.getClass();
        BookmarkModel bookmarkModel = this.e;
        bookmarkModel.getClass();
        Object obj2 = ThreadUtils.a;
        long j = bookmarkModel.a;
        if (j != 0) {
            N.Mv0zkYXw(j, bookmarkModel);
        }
        this.f.a(this);
        if (this.i) {
            n();
        }
    }

    public final void n() {
        u80 u80Var = this.h;
        u80Var.getClass();
        this.e.e.c(this);
        this.f.a(this);
        u80Var.e = true;
    }

    @Override // defpackage.mg
    public final void h() {
    }

    @Override // defpackage.mg
    public final void i(BookmarkItem bookmarkItem) {
    }
}
