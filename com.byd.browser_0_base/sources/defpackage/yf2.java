package defpackage;

import android.view.View;
import org.chromium.chrome.R$anim;
import org.chromium.chrome.browser.searchwidget.SearchActivity;

/* JADX INFO: compiled from: SearchActivity.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yf2 implements View.OnClickListener {
    public final /* synthetic */ SearchActivity e;

    public yf2(SearchActivity searchActivity) {
        this.e = searchActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Object obj = SearchActivity.q0;
        SearchActivity searchActivity = this.e;
        searchActivity.finish();
        searchActivity.overridePendingTransition(0, R$anim.activity_close_exit);
    }
}
