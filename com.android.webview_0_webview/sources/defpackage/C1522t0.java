package defpackage;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: renamed from: t0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1522t0 implements TextWatcher {
    public final /* synthetic */ SearchView s;

    public C1522t0(SearchView searchView) {
        this.s = searchView;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        SearchView searchView = this.s;
        Editable text = searchView.H.getText();
        searchView.j0 = text;
        TextUtils.isEmpty(text);
        searchView.M.setVisibility(8);
        searchView.O.setVisibility(8);
        searchView.r();
        searchView.K.setVisibility(8);
        searchView.i0 = charSequence.toString();
    }
}
