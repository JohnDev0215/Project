package defpackage;

import J.N;
import android.content.Context;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import defpackage.iq2;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.browser_ui.styles.R$attr;

/* JADX INFO: compiled from: ChromeContextMenuItem.java */
/* JADX INFO: loaded from: classes.dex */
public final class zp {
    public static final int[] a = {R$id.contextmenu_open_in_new_chrome_tab, R$id.contextmenu_open_in_chrome_incognito_tab, R$id.contextmenu_open_in_browser_id, R$id.contextmenu_open_in_new_tab, R$id.contextmenu_open_in_new_tab_in_group, R$id.contextmenu_open_in_incognito_tab, R$id.contextmenu_open_in_other_window, R$id.contextmenu_open_in_new_window, R$id.contextmenu_open_in_ephemeral_tab, R$id.contextmenu_copy_link_address, R$id.contextmenu_copy_link_text, R$id.contextmenu_save_link_as, R$id.contextmenu_share_link, R$id.contextmenu_direct_share_link, R$id.contextmenu_read_later, R$id.contextmenu_load_original_image, R$id.contextmenu_save_image, R$id.contextmenu_open_image, R$id.contextmenu_open_image_in_new_tab, R$id.contextmenu_open_image_in_ephemeral_tab, R$id.contextmenu_copy_image, R$id.contextmenu_search_by_image, R$id.contextmenu_search_with_google_lens, R$id.contextmenu_shop_image_with_google_lens, R$id.contextmenu_share_image, R$id.contextmenu_direct_share_image, R$id.contextmenu_call, R$id.contextmenu_send_message, R$id.contextmenu_add_to_contacts, R$id.contextmenu_copy, R$id.contextmenu_save_video, R$id.contextmenu_open_in_chrome, R$id.contextmenu_share_highlight, R$id.contextmenu_remove_highlight, R$id.contextmenu_learn_more};
    public static final int[] b;

    static {
        int[] iArr = new int[35];
        iArr[0] = R$string.contextmenu_open_in_new_chrome_tab;
        iArr[1] = R$string.contextmenu_open_in_chrome_incognito_tab;
        iArr[2] = 0;
        iArr[3] = R$string.contextmenu_open_in_new_tab;
        iArr[4] = R$string.contextmenu_open_in_new_tab_group;
        iArr[5] = R$string.contextmenu_open_in_incognito_tab;
        iArr[6] = R$string.contextmenu_open_in_other_window;
        iArr[7] = R$string.contextmenu_open_in_new_window;
        iArr[8] = R$string.contextmenu_open_in_ephemeral_tab;
        iArr[9] = R$string.contextmenu_copy_link_address;
        iArr[10] = R$string.contextmenu_copy_link_text;
        iArr[11] = R$string.contextmenu_save_link;
        iArr[12] = R$string.contextmenu_share_link;
        iArr[13] = 0;
        iArr[14] = R$string.contextmenu_read_later;
        iArr[15] = R$string.contextmenu_load_original_image;
        iArr[16] = R$string.contextmenu_save_image;
        iArr[17] = R$string.contextmenu_open_image;
        iArr[18] = R$string.contextmenu_open_image_in_new_tab;
        iArr[19] = R$string.contextmenu_open_image_in_ephemeral_tab;
        iArr[20] = R$string.contextmenu_copy_image;
        iArr[21] = R$string.contextmenu_search_web_for_image;
        sk skVar = bq.a;
        iArr[22] = N.M6bsIDpc("ContextMenuGoogleLensSearchOptimizations", "useLensContextMenuAlternateText1", false) ? R$string.contextmenu_search_image_with_google_lens_alt_text_1 : N.M6bsIDpc("ContextMenuGoogleLensSearchOptimizations", "useLensContextMenuAlternateText2", false) ? R$string.contextmenu_search_image_with_google_lens_alt_text_2 : R$string.contextmenu_search_image_with_google_lens;
        iArr[23] = R$string.contextmenu_shop_image_with_google_lens;
        iArr[24] = R$string.contextmenu_share_image;
        iArr[25] = 0;
        iArr[26] = R$string.contextmenu_call;
        iArr[27] = R$string.contextmenu_send_message;
        iArr[28] = R$string.contextmenu_add_to_contacts;
        iArr[29] = R$string.contextmenu_copy;
        iArr[30] = R$string.contextmenu_save_video;
        iArr[31] = R$string.menu_open_in_chrome;
        iArr[32] = R$string.contextmenu_share_highlight;
        iArr[33] = R$string.contextmenu_remove_highlight;
        iArr[34] = R$string.contextmenu_learn_more;
        b = iArr;
    }

    public static CharSequence a(Context context, int i, String str, boolean z) {
        String string = context.getString(b(context, i));
        return (!z || (str != null && SharedPreferencesManager.getInstance().readBoolean(str, false))) ? iq2.b(string, new iq2.a(new Object[0])) : iq2.a(string, new iq2.a(new SuperscriptSpan(), new RelativeSizeSpan(0.75f), new ForegroundColorSpan(ii2.g(context, R$attr.colorPrimary))));
    }

    public static int b(Context context, int i) {
        if (oj2.D(context)) {
            if (CachedFeatureFlags.b(ar.d.b("TabGridLayoutAndroid:enable_tab_group_auto_creation"), true) && i == 3) {
                return R$string.contextmenu_open_in_new_tab_group;
            }
        }
        return b[i];
    }
}
