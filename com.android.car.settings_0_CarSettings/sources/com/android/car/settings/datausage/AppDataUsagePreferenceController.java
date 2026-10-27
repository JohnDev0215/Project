package com.android.car.settings.datausage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.net.NetworkStats;
import android.os.UserHandle;
import android.util.SparseArray;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.common.ProgressBarPreference;
import com.android.car.settings.datausage.AppsNetworkStatsManager;
import com.android.car.settings.users.UserHelper;
import com.android.settingslib.AppItem;
import com.android.settingslib.net.UidDetail;
import com.android.settingslib.net.UidDetailProvider;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class AppDataUsagePreferenceController extends PreferenceController<PreferenceGroup> implements AppsNetworkStatsManager.Callback {
    private final UidDetailProvider mUidDetailProvider;

    public AppDataUsagePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mUidDetailProvider = new UidDetailProvider(getContext());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.datausage.AppsNetworkStatsManager.Callback
    public void onDataLoaded(@Nullable NetworkStats stats, @Nullable int[] restrictedUids) {
        List<AppItem> items = new ArrayList<>();
        long largest = 0;
        List<UserInfo> profiles = UserHelper.getInstance(getContext()).getAllUsers();
        SparseArray<AppItem> knownItems = new SparseArray<>();
        NetworkStats.Entry entry = null;
        if (stats != null) {
            for (int i = 0; i < stats.size(); i++) {
                entry = stats.getValues(i, entry);
                long size = aggregateDataUsage(knownItems, items, entry, profiles);
                largest = Math.max(size, largest);
            }
        }
        updateRestrictedState(restrictedUids, knownItems, items, profiles);
        sortAndAddPreferences(items, largest);
    }

    private long aggregateDataUsage(SparseArray<AppItem> knownItems, List<AppItem> items, NetworkStats.Entry entry, List<UserInfo> profiles) {
        int collapseKey;
        int category;
        int currentUserId = UserHandle.myUserId();
        int uid = entry.uid;
        final int userId = UserHandle.getUserId(uid);
        if (isUidValid(uid)) {
            return accumulate(uid, knownItems, entry, 2, items);
        }
        if (!UserHandle.isApp(uid)) {
            return accumulate(1000, knownItems, entry, 2, items);
        }
        if (profileContainsUserId(profiles, userId) && userId == currentUserId) {
            return accumulate(uid, knownItems, entry, 2, items);
        }
        if (profileContainsUserId(profiles, userId) && userId != currentUserId) {
            int managedKey = UidDetailProvider.buildKeyForUser(userId);
            long usersLargest = accumulate(managedKey, knownItems, entry, 0, items);
            long appLargest = accumulate(uid, knownItems, entry, 2, items);
            return Math.max(usersLargest, appLargest);
        }
        Optional<UserInfo> info = profiles.stream().filter(new Predicate() { // from class: com.android.car.settings.datausage.-$$Lambda$AppDataUsagePreferenceController$mFlho4hxCUcvLDCmZo4zCtMHrPQ
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AppDataUsagePreferenceController.lambda$aggregateDataUsage$0(userId, (UserInfo) obj);
            }
        }).findFirst();
        if (!info.isPresent()) {
            collapseKey = -4;
            category = 2;
        } else {
            collapseKey = UidDetailProvider.buildKeyForUser(userId);
            category = 0;
        }
        return accumulate(collapseKey, knownItems, entry, category, items);
    }

    static /* synthetic */ boolean lambda$aggregateDataUsage$0(int userId, UserInfo userInfo) {
        return userInfo.id == userId;
    }

    private boolean isUidValid(int uid) {
        return !UserHandle.isApp(uid) && (uid == -4 || uid == -5);
    }

    static /* synthetic */ boolean lambda$profileContainsUserId$1(int userId, UserInfo userInfo) {
        return userInfo.id == userId;
    }

    private boolean profileContainsUserId(List<UserInfo> profiles, final int userId) {
        return profiles.stream().anyMatch(new Predicate() { // from class: com.android.car.settings.datausage.-$$Lambda$AppDataUsagePreferenceController$EWTnlvsB_sxvOgSsnQi6dGkDNgo
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AppDataUsagePreferenceController.lambda$profileContainsUserId$1(userId, (UserInfo) obj);
            }
        });
    }

    private void updateRestrictedState(@Nullable int[] restrictedUids, SparseArray<AppItem> knownItems, List<AppItem> items, List<UserInfo> profiles) {
        if (restrictedUids == null) {
            return;
        }
        for (int uid : restrictedUids) {
            if (profileContainsUserId(profiles, uid)) {
                AppItem item = knownItems.get(uid);
                if (item == null) {
                    item = new AppItem(uid);
                    item.total = -1L;
                    items.add(item);
                    knownItems.put(item.key, item);
                }
                item.restricted = true;
            }
        }
    }

    private void sortAndAddPreferences(List<AppItem> items, long largest) {
        Collections.sort(items);
        for (int i = 0; i < items.size(); i++) {
            int percentTotal = largest != 0 ? (int) ((items.get(i).total * 100) / largest) : 0;
            AppDataUsagePreference preference = new AppDataUsagePreference(getContext(), items.get(i), percentTotal, this.mUidDetailProvider);
            getPreference().addPreference(preference);
        }
    }

    private static long accumulate(int collapseKey, SparseArray<AppItem> knownItems, NetworkStats.Entry entry, int itemCategory, List<AppItem> items) {
        int uid = entry.uid;
        AppItem item = knownItems.get(collapseKey);
        if (item == null) {
            item = new AppItem(collapseKey);
            item.category = itemCategory;
            items.add(item);
            knownItems.put(item.key, item);
        }
        item.addUid(uid);
        item.total += entry.rxBytes + entry.txBytes;
        return item.total;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class AppDataUsagePreference extends ProgressBarPreference {
        private UidDetail mDetail;
        private final AppItem mItem;
        private final int mPercent;

        AppDataUsagePreference(Context context, AppItem item, int percent, final UidDetailProvider provider) {
            super(context);
            this.mItem = item;
            this.mPercent = percent;
            setLayoutResource(R.layout.progress_bar_preference);
            setKey(String.valueOf(item.key));
            if (item.restricted && item.total <= 0) {
                setSummary(R.string.data_usage_app_restricted);
            } else {
                CharSequence s = DataUsageUtils.bytesToIecUnits(context, item.total);
                setSummary(s);
            }
            UidDetail uidDetail = provider.getUidDetail(item.key, false);
            this.mDetail = uidDetail;
            if (uidDetail != null) {
                lambda$new$0$AppDataUsagePreferenceController$AppDataUsagePreference();
            } else {
                ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.settings.datausage.-$$Lambda$AppDataUsagePreferenceController$AppDataUsagePreference$Kdfb3Axg18tj30GyX1C-p4rmLgc
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$new$1$AppDataUsagePreferenceController$AppDataUsagePreference(provider);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$new$1$AppDataUsagePreferenceController$AppDataUsagePreference(UidDetailProvider provider) {
            this.mDetail = provider.getUidDetail(this.mItem.key, true);
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.settings.datausage.-$$Lambda$AppDataUsagePreferenceController$AppDataUsagePreference$Q_nChWoXz1DmQyy2JQmtyrqGXJc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$new$0$AppDataUsagePreferenceController$AppDataUsagePreference();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: setAppInfo, reason: merged with bridge method [inline-methods] */
        public void lambda$new$0$AppDataUsagePreferenceController$AppDataUsagePreference() {
            UidDetail uidDetail = this.mDetail;
            if (uidDetail != null) {
                setIcon(uidDetail.icon);
                setTitle(this.mDetail.label);
                setProgress(this.mPercent);
            } else {
                setIcon((Drawable) null);
                setTitle((CharSequence) null);
            }
        }
    }
}
