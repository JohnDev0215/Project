package android.ext.services.storage;

import android.app.usage.CacheQuotaHint;
import android.app.usage.CacheQuotaService;
import android.ext.services.storage.CacheQuotaServiceImpl;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.TextUtils;
import android.util.ArrayMap;
import androidx.core.util.Preconditions;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class CacheQuotaServiceImpl extends CacheQuotaService {
    private static Comparator<CacheQuotaHintExtend> sCacheQuotaRequestComparator = new Comparator<CacheQuotaHintExtend>() { // from class: android.ext.services.storage.CacheQuotaServiceImpl.1
        @Override // java.util.Comparator
        public int compare(CacheQuotaHintExtend cacheQuotaHintExtend, CacheQuotaHintExtend cacheQuotaHintExtend2) {
            long j = cacheQuotaHintExtend2.mTotalTimeInForeground;
            long j2 = cacheQuotaHintExtend.mTotalTimeInForeground;
            if (j < j2) {
                return -1;
            }
            return j == j2 ? 0 : 1;
        }
    };

    public List<CacheQuotaHint> onComputeCacheQuotaHints(List<CacheQuotaHint> list) {
        ArrayMap arrayMap = new ArrayMap();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            CacheQuotaHint cacheQuotaHint = list.get(i);
            String volumeUuid = cacheQuotaHint.getVolumeUuid();
            List arrayList = (List) arrayMap.get(volumeUuid);
            if (arrayList == null) {
                arrayList = new ArrayList();
                arrayMap.put(volumeUuid, arrayList);
            }
            arrayList.add(convertToCacheQuotaHintExtend(cacheQuotaHint));
        }
        final ArrayList arrayList2 = new ArrayList();
        arrayMap.entrySet().forEach(new Consumer() { // from class: android.ext.services.storage.-$$Lambda$CacheQuotaServiceImpl$Zxx38GzcxY8KWXA1odhi6pQPchE
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onComputeCacheQuotaHints$3$CacheQuotaServiceImpl(arrayList2, (Map.Entry) obj);
            }
        });
        return (List) arrayList2.stream().filter(new Predicate() { // from class: android.ext.services.storage.-$$Lambda$CacheQuotaServiceImpl$X5ewsvyLf0etDNhMTVVLBtFoeP8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CacheQuotaServiceImpl.lambda$onComputeCacheQuotaHints$4((CacheQuotaHint) obj);
            }
        }).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onComputeCacheQuotaHints$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onComputeCacheQuotaHints$3$CacheQuotaServiceImpl(List list, Map.Entry entry) {
        Map map = (Map) ((List) entry.getValue()).stream().collect(Collectors.groupingBy(new Function() { // from class: android.ext.services.storage.-$$Lambda$_dl3pH8ocJCMCXmYox_PUiQ_Fws
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((CacheQuotaServiceImpl.CacheQuotaHintExtend) obj).getUid());
            }
        }));
        map.values().forEach(new Consumer() { // from class: android.ext.services.storage.-$$Lambda$CacheQuotaServiceImpl$8RFS8BpAu6NdfRkBp-RGotLH5Dc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CacheQuotaServiceImpl.lambda$onComputeCacheQuotaHints$0((List) obj);
            }
        });
        List list2 = (List) map.values().stream().map(new Function() { // from class: android.ext.services.storage.-$$Lambda$CacheQuotaServiceImpl$lkv04kCM9107A34Mxv-bWrsA2lg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CacheQuotaServiceImpl.lambda$onComputeCacheQuotaHints$1((List) obj);
            }
        }).filter(new Predicate() { // from class: android.ext.services.storage.-$$Lambda$CacheQuotaServiceImpl$FkrXysDbcH8zKIb_53vqKvaRcXg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CacheQuotaServiceImpl.lambda$onComputeCacheQuotaHints$2((CacheQuotaServiceImpl.CacheQuotaHintExtend) obj);
            }
        }).sorted(sCacheQuotaRequestComparator).collect(Collectors.toList());
        double sumOfFairShares = getSumOfFairShares(list2.size());
        long reservedCacheSize = getReservedCacheSize((String) entry.getKey());
        for (int i = 0; i < list2.size(); i++) {
            double fairShareForPosition = getFairShareForPosition(i) / sumOfFairShares;
            CacheQuotaHint.Builder builder = new CacheQuotaHint.Builder(((CacheQuotaHintExtend) list2.get(i)).mCacheQuotaHint);
            builder.setQuota(Math.round(fairShareForPosition * reservedCacheSize));
            list.add(builder.build());
        }
    }

    static /* synthetic */ void lambda$onComputeCacheQuotaHints$0(List list) {
        int size = list.size();
        if (size < 2) {
            return;
        }
        CacheQuotaHintExtend cacheQuotaHintExtend = (CacheQuotaHintExtend) list.get(0);
        for (int i = 1; i < size; i++) {
            cacheQuotaHintExtend.mTotalTimeInForeground += ((CacheQuotaHintExtend) list.get(i)).mTotalTimeInForeground;
        }
    }

    static /* synthetic */ CacheQuotaHintExtend lambda$onComputeCacheQuotaHints$1(List list) {
        return (CacheQuotaHintExtend) list.get(0);
    }

    static /* synthetic */ boolean lambda$onComputeCacheQuotaHints$2(CacheQuotaHintExtend cacheQuotaHintExtend) {
        return cacheQuotaHintExtend.mTotalTimeInForeground != 0;
    }

    static /* synthetic */ boolean lambda$onComputeCacheQuotaHints$4(CacheQuotaHint cacheQuotaHint) {
        return cacheQuotaHint.getQuota() > 0;
    }

    private double getFairShareForPosition(int i) {
        double dLog = (1.0d / Math.log(i + 3)) - 0.285d;
        if (dLog > 0.01d) {
            return dLog;
        }
        return 0.01d;
    }

    private double getSumOfFairShares(int i) {
        double fairShareForPosition = 0.0d;
        for (int i2 = 0; i2 < i; i2++) {
            fairShareForPosition += getFairShareForPosition(i2);
        }
        return fairShareForPosition;
    }

    private long getReservedCacheSize(String str) {
        long usableSpace = 0;
        if (TextUtils.isEmpty(str)) {
            usableSpace = Environment.getDataDirectory().getUsableSpace();
        } else {
            List<StorageVolume> storageVolumes = ((StorageManager) getSystemService(StorageManager.class)).getStorageVolumes();
            int size = storageVolumes.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                StorageVolume storageVolume = storageVolumes.get(i);
                if (TextUtils.equals(storageVolume.getUuid(), str)) {
                    File directory = storageVolume.getDirectory();
                    if (directory != null) {
                        usableSpace = directory.getUsableSpace();
                    }
                } else {
                    i++;
                }
            }
        }
        return Math.round(usableSpace * 0.15d);
    }

    private CacheQuotaHintExtend convertToCacheQuotaHintExtend(CacheQuotaHint cacheQuotaHint) {
        Preconditions.checkNotNull(cacheQuotaHint);
        return new CacheQuotaHintExtend(this, cacheQuotaHint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class CacheQuotaHintExtend {
        public final CacheQuotaHint mCacheQuotaHint;
        public long mTotalTimeInForeground;

        public CacheQuotaHintExtend(CacheQuotaServiceImpl cacheQuotaServiceImpl, CacheQuotaHint cacheQuotaHint) {
            this.mCacheQuotaHint = cacheQuotaHint;
            this.mTotalTimeInForeground = cacheQuotaHint.getUsageStats() != null ? cacheQuotaHint.getUsageStats().getTotalTimeInForeground() : 0L;
        }

        public int getUid() {
            return this.mCacheQuotaHint.getUid();
        }
    }
}
