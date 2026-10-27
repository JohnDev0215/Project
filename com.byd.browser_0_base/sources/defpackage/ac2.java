package defpackage;

import android.app.Activity;
import android.content.Context;
import android.text.format.Formatter;
import org.chromium.components.browser_ui.site_settings.R$plurals;
import org.chromium.components.browser_ui.site_settings.R$string;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: RevenueStats.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ac2 {
    public static ac2 a;
    public static Long g;
    public static final int[] b = {0, 4, 9, 8, 5, 2, 3, 25, 21, 12, 15, 30, 13, 52, 50, 43, 55, 56, 38, 74, 32, 72, 73};
    public static final int[] c = {19, 51};
    public static final int[] d = new int[0];
    public static final int[] e = {0};
    public static final hi1 f = new hi1("tab_management", k13.class, "l13");
    public static final hi1 h = new hi1("test_dummy", r73.class, "org.chromium.chrome.modules.test_dummy.TestDummyProviderImpl");
    public static final ac2 i = new ac2();

    public static String a(Context context, long j, int i2) {
        String str = j > 0 ? String.format(context.getString(R$string.origin_settings_storage_usage_brief), Formatter.formatShortFileSize(context, j)) : "";
        if (i2 <= 0) {
            return str;
        }
        String quantityString = context.getResources().getQuantityString(R$plurals.cookies_count, i2, Integer.valueOf(i2));
        return str.isEmpty() ? quantityString : String.format(context.getString(R$string.summary_with_one_bullet), str, quantityString);
    }

    public static boolean b(GURL gurl) {
        return gurl.b && !gurl.i().isEmpty() && ("https".equals(gurl.i()) || "http".equals(gurl.i()));
    }

    public static void c(int i2) {
        switch (i2) {
            case 0:
                n3.X0("TabMultiSelectV2.BookmarkTabs");
                break;
            case 1:
                n3.X0("TabMultiSelectV2.CloseTabs");
                break;
            case 2:
                n3.X0("TabMultiSelectV2.GroupTabs");
                n3.X0("TabGroup.Created.TabMultiSelect");
                break;
            case 3:
                n3.X0("TabMultiSelectV2.SelectAll");
                break;
            case 4:
                n3.X0("TabMultiSelectV2.DeselectAll");
                break;
            case 5:
                n3.X0("TabMultiSelectV2.SharedTabAsTextList");
                break;
            case 6:
                n3.X0("TabMultiSelectV2.SharedTabsListAsTextList");
                break;
            case 7:
                n3.X0("TabMultiSelectV2.UngroupTabs");
                n3.X0("TabGridDialog.RemoveFromGroup.TabMultiSelect");
                break;
            case 8:
                n3.X0("TabMultiSelect.Done");
                n3.X0("TabGroup.Created.TabMultiSelect");
                break;
            case 9:
                n3.X0("TabGridDialog.RemoveFromGroup.TabMultiSelect");
                break;
            case 10:
                n3.X0("TabMultiSelect.TabUnselected");
                break;
            case 11:
                n3.X0("TabMultiSelect.TabSelected");
                break;
        }
    }

    public static void d(Context context, int i2) {
        if (!oj2.E(context)) {
            if (i2 == 0 || i2 == 1 || i2 != 2) {
                return;
            }
            n3.X0("TabMultiSelect.Cancelled");
            return;
        }
        if (i2 == 0) {
            n3.X0("TabMultiSelectV2.Closed");
        } else if (i2 == 1) {
            n3.X0("TabMultiSelectV2.ClosedAutomatically");
        } else {
            if (i2 != 2) {
                return;
            }
            n3.X0("TabMultiSelectV2.ClosedByUser");
        }
    }

    public static void e(Activity activity, int i2) {
        if (!oj2.E(activity)) {
            if (i2 != 1) {
                return;
            }
            n3.X0("TabMultiSelect.OpenFromDialog");
        } else if (i2 == 0) {
            n3.X0("TabMultiSelectV2.OpenFromGrid");
        } else {
            if (i2 != 1) {
                return;
            }
            n3.X0("TabMultiSelectV2.OpenFromDialog");
        }
    }
}
