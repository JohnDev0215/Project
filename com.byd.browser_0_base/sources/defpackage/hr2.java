package defpackage;

import J.N;
import android.util.Log;
import defpackage.zy2;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.site_engagement.SiteEngagementService;

/* JADX INFO: compiled from: StaleTabSuggestionProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hr2 implements x33 {
    @Override // defpackage.x33
    public final List<v33> a(zy2 zy2Var) {
        double size;
        double dDoubleValue;
        List<zy2.b> list = zy2Var.a;
        if (list == null) {
            return null;
        }
        int size2 = list.size();
        sk skVar = bq.a;
        if (size2 < N.M37SqSAy("CloseTabSuggestions", "close_tab_suggestions_stale_time_ms", 3)) {
            return null;
        }
        if (!N.M6bsIDpc("CloseTabSuggestions", "close_tab_features_time_last_used_enabled", false) && !N.M6bsIDpc("CloseTabSuggestions", "close_tab_features_site_engagement_enabled", false)) {
            return null;
        }
        ArrayList<zy2.b> arrayList = new ArrayList();
        arrayList.addAll(list);
        if (N.M6bsIDpc("CloseTabSuggestions", "close_tab_features_time_last_used_enabled", false)) {
            String strMMltG$kc = N.MMltG$kc("CloseTabSuggestions", "close_tab_features_time_last_used_transform");
            if (strMMltG$kc == null) {
                Log.e("cr_AdvStaleTabSuggest", "Time last used enabled but no transform string provided");
                return null;
            }
            char c = !strMMltG$kc.equals("MEAN_VARIANCE") ? !strMMltG$kc.equals("DAY_NORMALIZATION") ? (char) 2 : (char) 0 : (char) 1;
            HashMap map = new HashMap();
            if (c == 0) {
                Iterator<zy2.b> it = list.iterator();
                while (it.hasNext()) {
                    map.put(it.next(), Double.valueOf((System.currentTimeMillis() - r4.f) / TimeUnit.DAYS.toMillis(1L)));
                }
            } else if (c == 1) {
                if (list.isEmpty()) {
                    size = 0.0d;
                } else {
                    double d = 0.0d;
                    while (list.iterator().hasNext()) {
                        d += r4.next().f;
                    }
                    size = d / ((double) list.size());
                }
                Iterator<zy2.b> it2 = list.iterator();
                while (it2.hasNext()) {
                    map.put(it2.next(), Double.valueOf(r12.f - size));
                }
                Collection<Double> collectionValues = map.values();
                if (collectionValues == null || collectionValues.size() <= 1) {
                    dDoubleValue = 1.0d;
                } else {
                    dDoubleValue = 0.0d;
                    for (Double d2 : collectionValues) {
                        dDoubleValue += d2.doubleValue() * d2.doubleValue();
                    }
                }
                for (zy2.b bVar : list) {
                    map.put(bVar, Double.valueOf(((Double) map.get(bVar)).doubleValue() / Math.sqrt(dDoubleValue)));
                }
            }
            sk skVar2 = bq.a;
            double dMQdjXFDB = N.MQdjXFDB("CloseTabSuggestions", "close_tab_features_time_last_used_threshold", 0.5d);
            arrayList = new ArrayList();
            for (Map.Entry entry : map.entrySet()) {
                if (c == 1) {
                    if (((Double) entry.getValue()).doubleValue() < dMQdjXFDB) {
                        arrayList.add((zy2.b) entry.getKey());
                    }
                } else if (c == 0 && ((Double) entry.getValue()).doubleValue() > dMQdjXFDB) {
                    arrayList.add((zy2.b) entry.getKey());
                }
            }
        }
        sk skVar3 = bq.a;
        AbstractList abstractList = arrayList;
        if (N.M6bsIDpc("CloseTabSuggestions", "close_tab_features_site_engagement_enabled", false)) {
            double dMQdjXFDB2 = N.MQdjXFDB("CloseTabSuggestions", "close_tab_features_site_engagement_threshold", 100.0d);
            LinkedList linkedList = new LinkedList();
            for (zy2.b bVar2 : arrayList) {
                bVar2.getClass();
                SiteEngagementService siteEngagementService = (SiteEngagementService) N.Ml4trBg9(Profile.d());
                long j = siteEngagementService.a;
                if ((j == 0 ? 0.0d : N.MVTED07I(j, siteEngagementService, bVar2.h)) < dMQdjXFDB2) {
                    linkedList.add(bVar2);
                }
            }
            abstractList = linkedList;
        }
        return Arrays.asList(new v33(1, abstractList));
    }
}
