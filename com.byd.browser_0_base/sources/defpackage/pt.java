package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment;

/* JADX INFO: compiled from: ClearBrowsingDataFragment.java */
/* JADX INFO: loaded from: classes.dex */
public final class pt extends Handler {
    public final /* synthetic */ ClearBrowsingDataFragment a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public pt(ClearBrowsingDataFragment clearBrowsingDataFragment, Looper looper) {
        super(looper);
        this.a = clearBrowsingDataFragment;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what != 1) {
            return;
        }
        this.a.s();
    }
}
