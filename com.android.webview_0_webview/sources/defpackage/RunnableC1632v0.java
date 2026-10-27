package defpackage;

import androidx.appcompat.widget.SearchView;
import java.util.Objects;

/* JADX INFO: renamed from: v0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1632v0 implements Runnable {
    public final /* synthetic */ SearchView s;

    public RunnableC1632v0(SearchView searchView) {
        this.s = searchView;
    }

    @Override // java.lang.Runnable
    public void run() {
        Objects.requireNonNull(this.s);
    }
}
