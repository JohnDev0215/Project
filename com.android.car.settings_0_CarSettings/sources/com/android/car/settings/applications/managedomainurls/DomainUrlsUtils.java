package com.android.car.settings.applications.managedomainurls;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.IntentFilterVerificationInfo;
import android.content.pm.PackageManager;
import android.util.ArraySet;
import com.android.car.settings.R;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class DomainUrlsUtils {
    private DomainUrlsUtils() {
    }

    public static CharSequence getDomainsSummary(Context context, String packageName, int userId, ArraySet<String> domains) {
        PackageManager pm = context.getPackageManager();
        int domainStatus = pm.getIntentVerificationStatusAsUser(packageName, userId);
        if (domainStatus == 3) {
            return context.getText(R.string.domain_urls_summary_none);
        }
        if (domains.isEmpty()) {
            return context.getText(R.string.domain_urls_summary_none);
        }
        return domains.size() == 1 ? context.getString(R.string.domain_urls_summary_one, domains.valueAt(0)) : context.getString(R.string.domain_urls_summary_some, domains.valueAt(0));
    }

    public static ArraySet<String> getHandledDomains(PackageManager pm, String packageName) {
        List<IntentFilterVerificationInfo> iviList = pm.getIntentFilterVerifications(packageName);
        List<IntentFilter> filters = pm.getAllIntentFilters(packageName);
        ArraySet<String> result = new ArraySet<>();
        if (iviList != null && iviList.size() > 0) {
            for (IntentFilterVerificationInfo ivi : iviList) {
                for (String host : ivi.getDomains()) {
                    result.add(host);
                }
            }
        }
        if (filters != null && filters.size() > 0) {
            for (IntentFilter filter : filters) {
                if (filter.hasCategory("android.intent.category.BROWSABLE") && (filter.hasDataScheme("http") || filter.hasDataScheme("https"))) {
                    result.addAll(filter.getHostsList());
                }
            }
        }
        return result;
    }
}
