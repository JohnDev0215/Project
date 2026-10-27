package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collection;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.components.browser_ui.site_settings.AllSiteSettings;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* JADX INFO: compiled from: TrustedWebActivitySettingsLauncher.java */
/* JADX INFO: loaded from: classes.dex */
public final class fg3 {
    public static void a(Context context, Collection<String> collection, Collection<String> collection2) {
        if (collection.size() == 1) {
            b(context, collection.iterator().next());
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("category", yn2.m(0));
        bundle.putString("title", context.getString(R$string.twa_clear_data_site_selection_title));
        bundle.putStringArrayList("selected_domains", new ArrayList<>(collection2));
        bundle.putInt("org.chromium.chrome.preferences.navigation_source", 1);
        String name = AllSiteSettings.class.getName();
        Intent intent = new Intent();
        intent.setClass(context, SettingsActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
            intent.addFlags(67108864);
        }
        intent.putExtra("show_fragment", name);
        intent.putExtra("show_fragment_args", bundle);
        ComponentName componentName = q11.a;
        try {
            context.startActivity(intent, null);
        } catch (ActivityNotFoundException unused) {
        }
    }

    public static void b(Context context, String str) {
        Bundle bundleM0 = SingleWebsiteSettings.M0(str);
        bundleM0.putInt("org.chromium.chrome.preferences.navigation_source", 1);
        String name = SingleWebsiteSettings.class.getName();
        Intent intent = new Intent();
        intent.setClass(context, SettingsActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
            intent.addFlags(67108864);
        }
        intent.putExtra("show_fragment", name);
        intent.putExtra("show_fragment_args", bundleM0);
        context.startActivity(intent);
    }
}
