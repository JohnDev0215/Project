package com.android.launcher3.allapps.search;

import android.os.Handler;
import com.android.launcher3.AppInfo;
import com.android.launcher3.allapps.search.AllAppsSearchBarController;
import com.android.launcher3.util.ComponentKey;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultAppSearchAlgorithm implements SearchAlgorithm {
    private final List<AppInfo> mApps;
    protected final Handler mResultHandler = new Handler();

    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    private static boolean isBreak(int r2, int r3, int r4) {
        /*
            r0 = 1
            if (r3 == 0) goto L38
            switch(r3) {
                case 12: goto L38;
                case 13: goto L38;
                case 14: goto L38;
                default: goto L6;
            }
        L6:
            r1 = 0
            if (r2 == r0) goto L31
            r4 = 2
            if (r2 == r4) goto L29
            r4 = 3
            if (r2 == r4) goto L34
            r4 = 20
            if (r2 == r4) goto L28
            switch(r2) {
                case 9: goto L1a;
                case 10: goto L1a;
                case 11: goto L1a;
                default: goto L16;
            }
        L16:
            switch(r2) {
                case 24: goto L28;
                case 25: goto L28;
                case 26: goto L28;
                default: goto L19;
            }
        L19:
            return r1
        L1a:
            r2 = 9
            if (r3 == r2) goto L27
            r2 = 10
            if (r3 == r2) goto L27
            r2 = 11
            if (r3 == r2) goto L27
            goto L28
        L27:
            r0 = r1
        L28:
            return r0
        L29:
            r2 = 5
            if (r3 > r2) goto L30
            if (r3 > 0) goto L2f
            goto L30
        L2f:
            r0 = r1
        L30:
            return r0
        L31:
            if (r4 != r0) goto L34
            return r0
        L34:
            if (r3 == r0) goto L37
            goto L38
        L37:
            r0 = r1
        L38:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.allapps.search.DefaultAppSearchAlgorithm.isBreak(int, int, int):boolean");
    }

    public DefaultAppSearchAlgorithm(List<AppInfo> apps) {
        this.mApps = apps;
    }

    @Override // com.android.launcher3.allapps.search.SearchAlgorithm
    public void cancel(boolean interruptActiveRequests) {
        if (interruptActiveRequests) {
            this.mResultHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.android.launcher3.allapps.search.SearchAlgorithm
    public void doSearch(final String query, final AllAppsSearchBarController.Callbacks callback) {
        final ArrayList<ComponentKey> titleMatchResult = getTitleMatchResult(query);
        this.mResultHandler.post(new Runnable() { // from class: com.android.launcher3.allapps.search.DefaultAppSearchAlgorithm.1
            @Override // java.lang.Runnable
            public void run() {
                callback.onSearchResult(query, titleMatchResult);
            }
        });
    }

    private ArrayList<ComponentKey> getTitleMatchResult(String query) {
        String lowerCase = query.toLowerCase();
        ArrayList<ComponentKey> arrayList = new ArrayList<>();
        StringMatcher stringMatcher = StringMatcher.getInstance();
        for (AppInfo appInfo : this.mApps) {
            if (matches(appInfo, lowerCase, stringMatcher)) {
                arrayList.add(appInfo.toComponentKey());
            }
        }
        return arrayList;
    }

    public static boolean matches(AppInfo info, String query, StringMatcher matcher) {
        int length = query.length();
        String string = info.title.toString();
        int length2 = string.length();
        if (length2 >= length && length > 0) {
            int type = Character.getType(string.codePointAt(0));
            int i = length2 - length;
            int i2 = 0;
            int i3 = 0;
            while (i2 <= i) {
                int type2 = i2 < length2 + (-1) ? Character.getType(string.codePointAt(i2 + 1)) : 0;
                if (isBreak(type, i3, type2) && matcher.matches(query, string.substring(i2, i2 + length))) {
                    return true;
                }
                i2++;
                i3 = type;
                type = type2;
            }
        }
        return false;
    }

    public static class StringMatcher {
        private static final char MAX_UNICODE = 65535;
        private final Collator mCollator;

        StringMatcher() {
            Collator collator = Collator.getInstance();
            this.mCollator = collator;
            collator.setStrength(0);
            collator.setDecomposition(1);
        }

        public boolean matches(String query, String target) {
            int iCompare = this.mCollator.compare(query, target);
            return iCompare != -1 ? iCompare == 0 : this.mCollator.compare(new StringBuilder().append(query).append(MAX_UNICODE).toString(), target) > -1;
        }

        public static StringMatcher getInstance() {
            return new StringMatcher();
        }
    }
}
