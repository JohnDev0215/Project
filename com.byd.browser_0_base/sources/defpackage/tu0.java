package defpackage;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: HistoricalTabSaverImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tu0 implements su0 {
    public static final ArrayList b = new ArrayList(Arrays.asList("chrome", "chrome-native", "about"));
    public final TabModel a;

    public tu0(TabModel tabModel) {
        this.a = tabModel;
    }

    public static on2 a(Tab tab) {
        on2 on2Var;
        if (on2.c == null) {
            on2 on2Var2 = new on2(ByteBuffer.wrap(new byte[0]));
            on2.c = on2Var2;
            on2Var2.a = -1;
        }
        on2 on2Var3 = on2.c;
        return (tab.c() == null && (on2Var = CriticalPersistedTabData.m(tab).s) != null) ? on2Var : on2Var3;
    }

    public static boolean b(Tab tab) {
        GURL url;
        if (tab.isIncognito()) {
            return false;
        }
        if (tab.c() != null) {
            url = tab.c().g();
        } else {
            if (CriticalPersistedTabData.m(tab).s == null) {
                return false;
            }
            url = tab.getUrl();
        }
        return (url == null || !url.b || url.k() || b.contains(url.i())) ? false : true;
    }
}
