package defpackage;

import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class F0 implements Runnable {
    public final /* synthetic */ SearchView.SearchAutoComplete s;

    public F0(SearchView.SearchAutoComplete searchAutoComplete) {
        this.s = searchAutoComplete;
    }

    @Override // java.lang.Runnable
    public void run() {
        SearchView.SearchAutoComplete searchAutoComplete = this.s;
        if (searchAutoComplete.x) {
            ((InputMethodManager) searchAutoComplete.getContext().getSystemService("input_method")).showSoftInput(searchAutoComplete, 0);
            searchAutoComplete.x = false;
        }
    }
}
