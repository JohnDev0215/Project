package defpackage;

import android.net.Uri;
import java.util.Arrays;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.RenderFrameHost;

/* JADX INFO: compiled from: LinkToTextHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class y71 {

    /* JADX INFO: compiled from: LinkToTextHelper.java */
    public interface a {
    }

    public static void a(final List<String> list, final List<RenderFrameHost> list2, final Callback<String> callback, final int i) {
        if (i >= list2.size()) {
            callback.onResult(String.join("&text=", list));
            return;
        }
        final f83 f83Var = (f83) list2.get(i).e(g83.a);
        if (f83Var == null) {
            a(list, list2, callback, i + 1);
        } else {
            f83Var.k(new w71(new Callback() { // from class: t71
                @Override // org.chromium.base.Callback
                public final void onResult(Object obj) {
                    String[] strArr = (String[]) obj;
                    int length = strArr.length;
                    List list3 = list;
                    if (length > 0) {
                        list3.addAll(Arrays.asList(strArr));
                    }
                    y71.a(list3, list2, callback, i + 1);
                    f83Var.close();
                }
            }));
        }
    }

    public static String b(String str, String str2) {
        return !str2.isEmpty() ? Uri.parse(str).buildUpon().encodedFragment(":~:text=".concat(str2)).toString() : str;
    }

    public static void c(Callback callback, Tab tab) {
        if ((tab.c() == null || tab.c().y() == null || tab.getUrl().k() || tab.m() || hd2.z1(tab)) ? false : true) {
            tab.c().y().h(new v71(callback));
        } else {
            callback.onResult("");
        }
    }
}
