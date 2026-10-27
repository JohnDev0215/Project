package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionException;
import java.util.function.Function;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.notifications.NotificationPlatformBridge;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n91 implements Function {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ n91(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        String host;
        int i = this.a;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                try {
                    return ((qk) obj2).a(obj);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new CompletionException(e);
                } catch (RuntimeException e2) {
                    throw e2;
                } catch (Exception e3) {
                    throw new CompletionException(e3);
                }
            case 1:
                ((ChromeTabbedActivity) obj2).getClass();
                return Boolean.valueOf(ChromeTabbedActivity.h3((Tab) obj));
            case 2:
                lp1 lp1Var = (lp1) obj2;
                List list = (List) obj;
                String strC = NotificationPlatformBridge.c((String) lp1Var.b.c);
                if (TextUtils.isEmpty(strC) || (host = Uri.parse(strC).getHost()) == null) {
                    host = "";
                }
                if (!list.contains(host)) {
                    return Boolean.FALSE;
                }
                fk3.b().c.a(Collections.singletonList(lp1Var));
                return Boolean.TRUE;
            default:
                String str = (String) obj2;
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    if (((String) entry.getValue()).equals(str)) {
                        return (String) entry.getKey();
                    }
                }
                return null;
        }
    }
}
