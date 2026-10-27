package org.chromium.chrome.browser.tasks.tab_management;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import defpackage.ac2;
import defpackage.r91;
import defpackage.wp2;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: TabSelectionEditorBookmarkAction.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x0 extends v0 {
    public final Activity g;
    public final a h;

    /* JADX INFO: compiled from: TabSelectionEditorBookmarkAction.java */
    public interface a {
    }

    /* JADX INFO: compiled from: TabSelectionEditorBookmarkAction.java */
    public static class b implements a {
    }

    public x0(Activity activity, Drawable drawable, b bVar) {
        super(R$id.tab_selection_editor_bookmark_menu_item, 0, R$plurals.tab_selection_editor_bookmark_tabs_action_button, Integer.valueOf(R$plurals.accessibility_tab_selection_editor_bookmark_tabs_action_button), drawable);
        this.g = activity;
        this.h = bVar;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final void c(List<Integer> list) {
        e(this.f.booleanValue() ? v0.a(list, this.c) : list.size(), !list.isEmpty());
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final void d(ArrayList arrayList) {
        wp2 wp2Var = ((a1) this.d).u;
        if (this.h != null) {
            BookmarkModel bookmarkModelV = BookmarkModel.v(Profile.d());
            bookmarkModelV.g(new r91(this.g, bookmarkModelV, arrayList, wp2Var));
        }
        ac2.c(0);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final boolean f() {
        return false;
    }
}
