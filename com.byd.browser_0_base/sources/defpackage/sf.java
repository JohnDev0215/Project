package defpackage;

import android.view.View;
import org.chromium.chrome.browser.app.bookmarks.BookmarkEditActivity;
import org.chromium.chrome.browser.autofill.e;
import org.chromium.chrome.browser.autofill.settings.AutofillLocalCardEditor;
import org.chromium.chrome.browser.tasks.tab_management.m;
import org.chromium.chrome.browser.tasks.tab_management.q;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class sf implements View.OnFocusChangeListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ sf(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                BookmarkEditActivity bookmarkEditActivity = (BookmarkEditActivity) obj;
                bookmarkEditActivity.K1(bookmarkEditActivity.K);
                bookmarkEditActivity.K1(bookmarkEditActivity.L);
                break;
            case 1:
                e eVar = (e) obj;
                eVar.D = true;
                eVar.h();
                break;
            case 2:
                ((AutofillLocalCardEditor) obj).i0.setCounterEnabled(z);
                break;
            default:
                m mVar = (m) obj;
                mVar.w = z;
                if (oj2.C(mVar.e)) {
                    PropertyModel.n nVar = q.x;
                    PropertyModel propertyModel = mVar.f;
                    propertyModel.l(nVar, z);
                    propertyModel.l(q.w, z);
                    break;
                }
                break;
        }
    }
}
