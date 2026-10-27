package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.browser_ui.widget.selectable_list.a;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: BookmarkToolbarProperties.java */
/* JADX INFO: loaded from: classes.dex */
public final class eh {
    public static final PropertyModel.r<BookmarkModel> a;
    public static final PropertyModel.r<ng> b;
    public static final PropertyModel.r<a> c;
    public static final PropertyModel.r<Integer> d;
    public static final PropertyModel.r<Boolean> e;
    public static final PropertyModel.n f;
    public static final PropertyModel.n g;
    public static final PropertyModel.r<BookmarkId> h;
    public static final PropertyModel.r<Runnable> i;
    public static final PropertyModel.r<Callback<BookmarkId>> j;
    public static final v62[] k;

    static {
        PropertyModel.r<BookmarkModel> rVar = new PropertyModel.r<>();
        a = rVar;
        PropertyModel.r<ng> rVar2 = new PropertyModel.r<>();
        b = rVar2;
        PropertyModel.r<a> rVar3 = new PropertyModel.r<>();
        c = rVar3;
        PropertyModel.r<Integer> rVar4 = new PropertyModel.r<>(null, true);
        d = rVar4;
        PropertyModel.r<Boolean> rVar5 = new PropertyModel.r<>(null, true);
        e = rVar5;
        PropertyModel.n nVar = new PropertyModel.n();
        f = nVar;
        PropertyModel.n nVar2 = new PropertyModel.n();
        g = nVar2;
        PropertyModel.r<BookmarkId> rVar6 = new PropertyModel.r<>(null, true);
        h = rVar6;
        PropertyModel.r<Runnable> rVar7 = new PropertyModel.r<>();
        i = rVar7;
        PropertyModel.r<Callback<BookmarkId>> rVar8 = new PropertyModel.r<>();
        j = rVar8;
        k = new v62[]{rVar, rVar2, rVar3, rVar4, rVar5, nVar, nVar2, rVar6, rVar7, rVar8};
    }
}
