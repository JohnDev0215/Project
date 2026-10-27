package defpackage;

import android.view.View;
import org.chromium.chrome.browser.about_settings.LongClickCopySummaryPreference;
import org.chromium.ui.base.Clipboard;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class la1 implements View.OnLongClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ la1(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                LongClickCopySummaryPreference longClickCopySummaryPreference = (LongClickCopySummaryPreference) obj;
                longClickCopySummaryPreference.getClass();
                Clipboard.getInstance().i(longClickCopySummaryPreference.p().toString());
                break;
            default:
                ((Runnable) obj).run();
                break;
        }
        return true;
    }
}
