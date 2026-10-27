package defpackage;

import android.view.View;
import org.chromium.chrome.browser.content_creation.internal.R;
import org.chromium.chrome.browser.content_creation.notes.a;
import org.chromium.chrome.browser.content_creation.notes.top_bar.TopBarView;

/* JADX INFO: compiled from: TopBarCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class ld3 {
    public ld3(View view, a aVar) {
        TopBarView topBarView = (TopBarView) view.findViewById(R.id.top_bar);
        topBarView.setOnCloseListener(new androidx.appcompat.widget.a(22, aVar));
        topBarView.setOnNextListener(new wv(29, aVar));
    }
}
