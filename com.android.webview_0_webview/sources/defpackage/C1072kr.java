package defpackage;

import android.database.DataSetObserver;
import android.widget.TextView;
import java.util.Locale;

/* JADX INFO: renamed from: kr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1072kr extends DataSetObserver {
    public final /* synthetic */ TextView a;
    public final /* synthetic */ C1183mr b;

    public C1072kr(C1183mr c1183mr, C1238nr c1238nr, TextView textView) {
        this.b = c1183mr;
        this.a = textView;
    }

    @Override // android.database.DataSetObserver
    public void onChanged() {
        this.a.setText(String.format(Locale.US, "Crashes (%d)", Integer.valueOf(this.b.a.size())));
    }
}
