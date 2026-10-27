package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: TabImportanceManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u03 {
    public static final ArrayList a = new ArrayList(2);

    public static void a(Tab tab) {
        Object obj = ThreadUtils.a;
        TabImpl tabImpl = (TabImpl) tab;
        if (tabImpl.u != 1) {
            tabImpl.u = 1;
            WebContents webContents = tabImpl.g;
            if (webContents != null) {
                webContents.v(1);
            }
        }
        ArrayList arrayList = a;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            TabImpl tabImpl2 = (TabImpl) it.next();
            if (tabImpl2.isHidden()) {
                if (tabImpl2.u != 0) {
                    tabImpl2.u = 0;
                    WebContents webContents2 = tabImpl2.g;
                    if (webContents2 != null) {
                        webContents2.v(0);
                    }
                }
                it.remove();
            }
        }
        if (arrayList.contains(tab)) {
            return;
        }
        arrayList.add(tab);
    }
}
