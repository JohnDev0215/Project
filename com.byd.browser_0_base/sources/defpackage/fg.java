package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityManager;
import defpackage.gg;
import java.util.ArrayList;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.browser_ui.widget.selectable_list.a;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class fg implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ gg f;

    public /* synthetic */ fg(gg ggVar, int i) {
        this.e = i;
        this.f = ggVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        gg ggVar = this.f;
        switch (i) {
            case 0:
                ggVar.D = null;
                break;
            default:
                final gg.g gVar = ggVar.h;
                gVar.a = ggVar;
                a<BookmarkId> aVar = ggVar.m;
                gVar.b = aVar;
                AccessibilityManager accessibilityManager = (AccessibilityManager) gg.this.l.getContext().getSystemService("accessibility");
                gVar.c = false;
                AccessibilityManager.AccessibilityStateChangeListener accessibilityStateChangeListener = new AccessibilityManager.AccessibilityStateChangeListener() { // from class: hg
                    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
                    public final void onAccessibilityStateChanged(boolean z) {
                        gVar.c = z;
                    }
                };
                gVar.getClass();
                accessibilityManager.addAccessibilityStateChangeListener(accessibilityStateChangeListener);
                ggVar.k(ggVar.e);
                BookmarkModel bookmarkModel = ggVar.j;
                bookmarkModel.c(ggVar.d);
                aVar.a(ggVar.f);
                ggVar.w();
                xf xfVar = ggVar.o;
                xfVar.getClass();
                xfVar.j = new ArrayList();
                xfVar.s = ggVar;
                xfVar.n = ggVar.h;
                xfVar.o();
                if (!TextUtils.isEmpty(ggVar.x)) {
                    String str = ggVar.x;
                    ggVar.A("chrome-native://bookmarks/folder//shopping".equals(str) ? new gh(2, "chrome-native://bookmarks/folder//shopping", BookmarkId.c) : gh.b(Uri.parse(str), bookmarkModel));
                }
                break;
        }
    }
}
