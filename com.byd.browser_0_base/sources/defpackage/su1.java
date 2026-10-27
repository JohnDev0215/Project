package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;

/* JADX INFO: compiled from: PageInfoIPHController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class su1 implements ContextMenuPopulatorFactory {
    public final Object a;
    public final Object b;

    public /* synthetic */ su1(Object obj, Object obj2) {
        this.a = obj;
        this.b = obj2;
    }

    @Override // org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory
    public final vz a(Context context, ContextMenuParams contextMenuParams, uz uzVar) {
        return new bz2(((ContextMenuPopulatorFactory) this.a).a(context, contextMenuParams, uzVar), (Tab) this.b);
    }

    @Override // org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory
    public final void onDestroy() {
        Object obj = this.a;
        if (((ContextMenuPopulatorFactory) obj) != null) {
            ((ContextMenuPopulatorFactory) obj).onDestroy();
        }
    }

    public /* synthetic */ su1(Activity activity, View view) {
        this.a = new pk3(activity, new Handler(Looper.getMainLooper()));
        this.b = view;
    }
}
