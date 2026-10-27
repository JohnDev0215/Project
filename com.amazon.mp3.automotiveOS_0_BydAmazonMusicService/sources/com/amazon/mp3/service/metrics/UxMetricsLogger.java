package com.amazon.mp3.service.metrics;

import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class UxMetricsLogger {
    private static final Map<UserAction, Long> sUserActionTimes = new HashMap(UserAction.values().length);
    private static final Map<ApplicationAction, Long> sAppliactionActionTimes = new HashMap(ApplicationAction.values().length);
    private static final Set<Metric> sMetricLogEnabled = new HashSet(Metric.values().length);

    public enum UserAction {
        LAUNCH_HOME,
        LAUNCHER_STORE_SEARCH,
        LAUNCHER_LIBRARY_SEARCH,
        STORE_TO_LIBRARY,
        STORE_SEARCH,
        LIBRARY_TO_STORE_SEARCH,
        ALBUM_DETAIL,
        STORE_SAMPLE,
        STORE_PURCHASE,
        LIBRARY_TO_STORE;

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public Iterable<Metric> getAssociatedMetrics() {
            HashSet hashSet = new HashSet();
            switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$UserAction[ordinal()]) {
                case 1:
                    hashSet.add(Metric.LAUNCH_TO_HOME);
                    return hashSet;
                case 2:
                    hashSet.add(Metric.STORE_SEARCH);
                    return hashSet;
                case 3:
                    hashSet.add(Metric.LIBRARY_TO_STORE_SEARCH);
                    return hashSet;
                case 4:
                    hashSet.add(Metric.LAUNCH_TO_STORE_ALBUM_DETAIL);
                    hashSet.add(Metric.LAUNCH_TO_STORE_TRACK_SEARCH);
                    hashSet.add(Metric.LAUNCH_TO_STORE_ALBUM_SEARCH);
                    return hashSet;
                case 5:
                    hashSet.add(Metric.LAUNCH_TO_LIBRARY_ALBUM_DETAIL);
                    return hashSet;
                case 6:
                    hashSet.add(Metric.STORE_TO_LIBRARY);
                    return hashSet;
                case 7:
                    hashSet.add(Metric.LIBRARY_TO_STORE);
                    return hashSet;
                case 8:
                    hashSet.add(Metric.ALBUM_DETAIL);
                    return hashSet;
                case 9:
                    hashSet.add(Metric.STORE_SAMPLE);
                    return hashSet;
                case 10:
                    hashSet.add(Metric.STORE_PURCHASE);
                    return hashSet;
                default:
                    return hashSet;
            }
        }
    }

    public enum ApplicationAction {
        LIBRARY_ACTIVITY_COMPLETE,
        LIBRARY_ALBUM_DETAIL_COMPLETE,
        STORE_ACTIVITY_COMPLETE,
        STORE_PROMO_FRAGMENT_COMPLETE,
        STORE_ALBUM_DETAIL_COMPLETE,
        STORE_ALBUM_LIST_FRAGMENT_COMPLETE,
        STORE_SAMPLE_PLAYBACK_STARTED,
        STORE_PURCHASE_COMPLETE,
        STORE_TRACK_LIST_FRAGMENT_COMPLETE;

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public Iterable<Metric> getAssociatedMetrics() {
            HashSet hashSet = new HashSet();
            switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$ApplicationAction[ordinal()]) {
                case 1:
                    hashSet.add(Metric.LIBRARY_TO_STORE);
                    hashSet.add(Metric.LAUNCH_TO_HOME);
                    return hashSet;
                case 2:
                    hashSet.add(Metric.LAUNCH_TO_HOME);
                    hashSet.add(Metric.LIBRARY_TO_STORE);
                    return hashSet;
                case 3:
                    hashSet.add(Metric.LAUNCH_TO_HOME);
                    hashSet.add(Metric.LIBRARY_TO_STORE);
                    hashSet.add(Metric.LAUNCH_TO_STORE_ALBUM_SEARCH);
                    hashSet.add(Metric.LIBRARY_TO_STORE_SEARCH);
                    hashSet.add(Metric.STORE_SEARCH);
                    return hashSet;
                case 4:
                    hashSet.add(Metric.LAUNCH_TO_STORE_ALBUM_DETAIL);
                    hashSet.add(Metric.LIBRARY_TO_STORE_SEARCH);
                    hashSet.add(Metric.STORE_SEARCH);
                    hashSet.add(Metric.ALBUM_DETAIL);
                    return hashSet;
                case 5:
                    hashSet.add(Metric.LAUNCH_TO_STORE_TRACK_SEARCH);
                    hashSet.add(Metric.LIBRARY_TO_STORE_SEARCH);
                    hashSet.add(Metric.STORE_SEARCH);
                    return hashSet;
                case 6:
                    hashSet.add(Metric.STORE_TO_LIBRARY);
                    return hashSet;
                case 7:
                    hashSet.add(Metric.LAUNCH_TO_LIBRARY_ALBUM_DETAIL);
                    return hashSet;
                case 8:
                    hashSet.add(Metric.STORE_SAMPLE);
                    return hashSet;
                case 9:
                    hashSet.add(Metric.STORE_PURCHASE);
                    return hashSet;
                default:
                    return hashSet;
            }
        }
    }

    enum Metric {
        LAUNCH_TO_HOME,
        LAUNCH_TO_STORE_ALBUM_DETAIL,
        LAUNCH_TO_STORE_ALBUM_SEARCH,
        LAUNCH_TO_STORE_TRACK_SEARCH,
        LAUNCH_TO_LIBRARY_ALBUM_DETAIL,
        FIRST_CLOUD_SYNC,
        LIBRARY_TO_STORE,
        LIBRARY_TO_STORE_SEARCH,
        STORE_SEARCH,
        ALBUM_DETAIL,
        STORE_SAMPLE,
        STORE_PURCHASE,
        STORE_TO_LIBRARY;

        /* JADX INFO: Access modifiers changed from: private */
        public UserAction getAssociatedUserAction() {
            switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[ordinal()]) {
                case 1:
                    return UserAction.LAUNCH_HOME;
                case 2:
                case 3:
                case 4:
                    return UserAction.LAUNCHER_STORE_SEARCH;
                case 5:
                    return UserAction.LAUNCHER_LIBRARY_SEARCH;
                case 6:
                default:
                    return null;
                case 7:
                    return UserAction.LIBRARY_TO_STORE;
                case 8:
                    return UserAction.STORE_TO_LIBRARY;
                case 9:
                    return UserAction.LIBRARY_TO_STORE_SEARCH;
                case 10:
                    return UserAction.STORE_SEARCH;
                case 11:
                    return UserAction.ALBUM_DETAIL;
                case 12:
                    return UserAction.STORE_SAMPLE;
                case 13:
                    return UserAction.STORE_PURCHASE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public Iterable<ApplicationAction> getAssociatedAppActions() {
            HashSet hashSet = new HashSet();
            switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[ordinal()]) {
                case 1:
                case 7:
                    hashSet.add(ApplicationAction.STORE_PROMO_FRAGMENT_COMPLETE);
                    hashSet.add(ApplicationAction.STORE_ALBUM_LIST_FRAGMENT_COMPLETE);
                    return hashSet;
                case 2:
                case 11:
                    hashSet.add(ApplicationAction.STORE_ALBUM_DETAIL_COMPLETE);
                    return hashSet;
                case 3:
                    hashSet.add(ApplicationAction.STORE_ALBUM_LIST_FRAGMENT_COMPLETE);
                    return hashSet;
                case 4:
                    hashSet.add(ApplicationAction.STORE_TRACK_LIST_FRAGMENT_COMPLETE);
                    return hashSet;
                case 5:
                    hashSet.add(ApplicationAction.LIBRARY_ALBUM_DETAIL_COMPLETE);
                    return hashSet;
                case 6:
                default:
                    return hashSet;
                case 8:
                    hashSet.add(ApplicationAction.LIBRARY_ACTIVITY_COMPLETE);
                    return hashSet;
                case 9:
                case 10:
                    hashSet.add(ApplicationAction.STORE_TRACK_LIST_FRAGMENT_COMPLETE);
                    hashSet.add(ApplicationAction.STORE_ALBUM_LIST_FRAGMENT_COMPLETE);
                    hashSet.add(ApplicationAction.STORE_ALBUM_DETAIL_COMPLETE);
                    return hashSet;
                case 12:
                    hashSet.add(ApplicationAction.STORE_SAMPLE_PLAYBACK_STARTED);
                    return hashSet;
                case 13:
                    hashSet.add(ApplicationAction.STORE_PURCHASE_COMPLETE);
                    return hashSet;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Iterable<Metric> getAssociatedMetrics() {
            HashSet hashSet = new HashSet();
            int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[ordinal()];
            if (i == 2) {
                hashSet.add(LAUNCH_TO_STORE_TRACK_SEARCH);
                hashSet.add(LAUNCH_TO_STORE_ALBUM_SEARCH);
            } else if (i == 3) {
                hashSet.add(LAUNCH_TO_STORE_ALBUM_DETAIL);
                hashSet.add(LAUNCH_TO_STORE_TRACK_SEARCH);
            } else if (i == 4) {
                hashSet.add(LAUNCH_TO_STORE_ALBUM_DETAIL);
                hashSet.add(LAUNCH_TO_STORE_ALBUM_SEARCH);
            }
            return hashSet;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.service.metrics.UxMetricsLogger$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$ApplicationAction;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$UserAction;

        static {
            int[] iArr = new int[Metric.values().length];
            $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric = iArr;
            try {
                iArr[Metric.LAUNCH_TO_HOME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[Metric.LAUNCH_TO_STORE_ALBUM_DETAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[Metric.LAUNCH_TO_STORE_ALBUM_SEARCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[Metric.LAUNCH_TO_STORE_TRACK_SEARCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[Metric.LAUNCH_TO_LIBRARY_ALBUM_DETAIL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[Metric.FIRST_CLOUD_SYNC.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[Metric.LIBRARY_TO_STORE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[Metric.STORE_TO_LIBRARY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[Metric.LIBRARY_TO_STORE_SEARCH.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[Metric.STORE_SEARCH.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[Metric.ALBUM_DETAIL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[Metric.STORE_SAMPLE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric[Metric.STORE_PURCHASE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            int[] iArr2 = new int[ApplicationAction.values().length];
            $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$ApplicationAction = iArr2;
            try {
                iArr2[ApplicationAction.STORE_ACTIVITY_COMPLETE.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$ApplicationAction[ApplicationAction.STORE_PROMO_FRAGMENT_COMPLETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$ApplicationAction[ApplicationAction.STORE_ALBUM_LIST_FRAGMENT_COMPLETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$ApplicationAction[ApplicationAction.STORE_ALBUM_DETAIL_COMPLETE.ordinal()] = 4;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$ApplicationAction[ApplicationAction.STORE_TRACK_LIST_FRAGMENT_COMPLETE.ordinal()] = 5;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$ApplicationAction[ApplicationAction.LIBRARY_ACTIVITY_COMPLETE.ordinal()] = 6;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$ApplicationAction[ApplicationAction.LIBRARY_ALBUM_DETAIL_COMPLETE.ordinal()] = 7;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$ApplicationAction[ApplicationAction.STORE_SAMPLE_PLAYBACK_STARTED.ordinal()] = 8;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$ApplicationAction[ApplicationAction.STORE_PURCHASE_COMPLETE.ordinal()] = 9;
            } catch (NoSuchFieldError unused22) {
            }
            int[] iArr3 = new int[UserAction.values().length];
            $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$UserAction = iArr3;
            try {
                iArr3[UserAction.LAUNCH_HOME.ordinal()] = 1;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$UserAction[UserAction.STORE_SEARCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$UserAction[UserAction.LIBRARY_TO_STORE_SEARCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$UserAction[UserAction.LAUNCHER_STORE_SEARCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$UserAction[UserAction.LAUNCHER_LIBRARY_SEARCH.ordinal()] = 5;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$UserAction[UserAction.STORE_TO_LIBRARY.ordinal()] = 6;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$UserAction[UserAction.LIBRARY_TO_STORE.ordinal()] = 7;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$UserAction[UserAction.ALBUM_DETAIL.ordinal()] = 8;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$UserAction[UserAction.STORE_SAMPLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$UserAction[UserAction.STORE_PURCHASE.ordinal()] = 10;
            } catch (NoSuchFieldError unused32) {
            }
        }
    }

    public static void registerUserAction(UserAction action) {
        long jNanoTime = System.nanoTime();
        Log.debug("UxMetricsLogger", "Registering user action %s with time %d", action, Long.valueOf(jNanoTime));
        sUserActionTimes.put(action, Long.valueOf(jNanoTime));
        for (Metric metric : action.getAssociatedMetrics()) {
            Log.debug("UxMetricsLogger", "Adding metric to watchlist: %s", metric);
            sMetricLogEnabled.add(metric);
        }
    }

    public static void registerApplicationAction(ApplicationAction action) {
        long jNanoTime = System.nanoTime();
        Log.debug("UxMetricsLogger", "Registering application action %s with time %d", action, Long.valueOf(jNanoTime));
        sAppliactionActionTimes.put(action, Long.valueOf(jNanoTime));
        for (String str : getUxMetricLogStrings(action)) {
            if (str != null) {
                Log.debug("UxMetricsLogger", str);
            }
        }
    }

    private static Iterable<String> getUxMetricLogStrings(ApplicationAction action) {
        ArrayList arrayList = new ArrayList();
        Iterator it = action.getAssociatedMetrics().iterator();
        while (it.hasNext()) {
            arrayList.add(processMetric((Metric) it.next()));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String processMetric(com.amazon.mp3.service.metrics.UxMetricsLogger.Metric r12) {
        /*
            java.util.Map<com.amazon.mp3.service.metrics.UxMetricsLogger$UserAction, java.lang.Long> r0 = com.amazon.mp3.service.metrics.UxMetricsLogger.sUserActionTimes
            com.amazon.mp3.service.metrics.UxMetricsLogger$UserAction r1 = com.amazon.mp3.service.metrics.UxMetricsLogger.Metric.access$200(r12)
            java.lang.Object r0 = r0.get(r1)
            java.lang.Long r0 = (java.lang.Long) r0
            r1 = 0
            if (r0 != 0) goto L10
            return r1
        L10:
            java.lang.Iterable r2 = com.amazon.mp3.service.metrics.UxMetricsLogger.Metric.access$300(r12)
            java.util.Iterator r2 = r2.iterator()
            r3 = -1
            r5 = r3
        L1b:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L47
            java.lang.Object r7 = r2.next()
            com.amazon.mp3.service.metrics.UxMetricsLogger$ApplicationAction r7 = (com.amazon.mp3.service.metrics.UxMetricsLogger.ApplicationAction) r7
            java.util.Map<com.amazon.mp3.service.metrics.UxMetricsLogger$ApplicationAction, java.lang.Long> r8 = com.amazon.mp3.service.metrics.UxMetricsLogger.sAppliactionActionTimes
            java.lang.Object r7 = r8.get(r7)
            java.lang.Long r7 = (java.lang.Long) r7
            if (r7 == 0) goto L1b
            long r8 = r7.longValue()
            long r10 = r0.longValue()
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 >= 0) goto L3e
            goto L1b
        L3e:
            long r7 = r7.longValue()
            long r5 = java.lang.Math.max(r5, r7)
            goto L1b
        L47:
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 != 0) goto L4c
            return r1
        L4c:
            java.util.Set<com.amazon.mp3.service.metrics.UxMetricsLogger$Metric> r2 = com.amazon.mp3.service.metrics.UxMetricsLogger.sMetricLogEnabled
            boolean r2 = r2.contains(r12)
            if (r2 != 0) goto L55
            return r1
        L55:
            long r2 = r0.longValue()
            long r5 = r5 - r2
            r2 = 0
            int r0 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r0 < 0) goto Lb0
            clearAssociatedMetrics(r12)
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r0 = r0.toMillis(r5)
            int r0 = (int) r0
            int[] r1 = com.amazon.mp3.service.metrics.UxMetricsLogger.AnonymousClass1.$SwitchMap$com$amazon$mp3$service$metrics$UxMetricsLogger$Metric
            int r2 = r12.ordinal()
            r1 = r1[r2]
            r2 = 2
            if (r1 == r2) goto L98
            r3 = 3
            if (r1 == r3) goto L94
            r3 = 4
            if (r1 == r3) goto L94
            r3 = 7
            if (r1 == r3) goto L90
            r3 = 13
            if (r1 == r3) goto L8b
            r3 = 9
            if (r1 == r3) goto L94
            r3 = 10
            if (r1 == r3) goto L94
            goto L9b
        L8b:
            long r3 = (long) r0
            com.amazon.mp3.service.metrics.MetricsLogger.purchaseTime(r3)
            goto L9b
        L90:
            com.amazon.mp3.service.metrics.MetricsLogger.storeHome(r0)
            goto L9b
        L94:
            com.amazon.mp3.service.metrics.MetricsLogger.storeSearch(r0)
            goto L9b
        L98:
            com.amazon.mp3.service.metrics.MetricsLogger.storeAlbumDetail(r0)
        L9b:
            java.util.Locale r1 = java.util.Locale.US
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r12
            r12 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2[r12] = r0
            java.lang.String r12 = "%s : %d"
            java.lang.String r12 = java.lang.String.format(r1, r12, r2)
            return r12
        Lb0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.service.metrics.UxMetricsLogger.processMetric(com.amazon.mp3.service.metrics.UxMetricsLogger$Metric):java.lang.String");
    }

    private static void clearAssociatedMetrics(Metric metric) {
        Log.debug("UxMetricsLogger", "Removing metric from watchlist: %s", metric);
        sMetricLogEnabled.remove(metric);
        for (Metric metric2 : metric.getAssociatedMetrics()) {
            Log.debug("UxMetricsLogger", "Removing metric from watchlist: %s", metric2);
            sMetricLogEnabled.remove(metric2);
        }
    }

    static void logMetric(Metric metric, long nanoSeconds) {
        Log.debug("UxMetricsLogger", "%s : %d", metric, Long.valueOf(nanoSeconds));
    }
}
