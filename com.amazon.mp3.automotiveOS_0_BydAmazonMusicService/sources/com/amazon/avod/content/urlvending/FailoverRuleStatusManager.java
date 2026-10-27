package com.amazon.avod.content.urlvending;

import androidx.core.util.Pair;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class FailoverRuleStatusManager {
    private final List<FailoverRule> mFailoverRuleList;
    private final Map<Integer, Integer> mFailoverStatusMap;
    private boolean mIsStatusMapEmpty;
    private final Object mStatusMapMutex = new Object();

    public FailoverRuleStatusManager(@Nonnull FailoverMap failoverMap) {
        Preconditions.checkNotNull(failoverMap, "failoverMap");
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        this.mFailoverRuleList = arrayListNewArrayList;
        arrayListNewArrayList.addAll(failoverMap.getFailoverRuleList());
        Collections.sort(arrayListNewArrayList);
        this.mFailoverStatusMap = Maps.newHashMap();
        Iterator it = arrayListNewArrayList.iterator();
        while (it.hasNext()) {
            this.mFailoverStatusMap.put(Integer.valueOf(((FailoverRule) it.next()).getRuleId()), 0);
        }
        this.mIsStatusMapEmpty = true;
    }

    public void clearStatusMap(@Nullable String str) {
        synchronized (this.mStatusMapMutex) {
            if (!this.mIsStatusMapEmpty) {
                DLog.devf("FailoverRuleStatusManager clearing error map reason:%s", str);
                Iterator<Integer> it = this.mFailoverStatusMap.keySet().iterator();
                while (it.hasNext()) {
                    this.mFailoverStatusMap.put(Integer.valueOf(it.next().intValue()), 0);
                }
                this.mIsStatusMapEmpty = true;
            }
        }
    }

    @Nonnull
    Pair<FailoverType, String> updateStatusAndGetFailoverType(@Nonnull String str, int i, @Nonnull DownloadType downloadType) {
        synchronized (this.mStatusMapMutex) {
            DLog.logf("FailoverRuleStatusManager registering failure: %s-%s-%s", str, Integer.valueOf(i), downloadType.toString());
            for (FailoverRule failoverRule : this.mFailoverRuleList) {
                DLog.devf("FailoverRuleStatusManager RuleId:%s Count:%s", Integer.valueOf(failoverRule.getRuleId()), Integer.valueOf(this.mFailoverStatusMap.get(Integer.valueOf(failoverRule.getRuleId())).intValue()));
            }
            Iterator<FailoverRule> it = this.mFailoverRuleList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                FailoverRule next = it.next();
                if (next.matches(str, i, downloadType)) {
                    int iIntValue = this.mFailoverStatusMap.get(Integer.valueOf(next.getRuleId())).intValue() + 1;
                    if (iIntValue >= next.getRetryCount()) {
                        clearStatusMap(next.getFailoverType().toString());
                        DLog.logf("FailoverRuleStatusManager returning %s for %s-%s", next.getFailoverType().toString(), str, Integer.valueOf(i));
                        return new Pair<>(next.getFailoverType(), Integer.toString(next.getRuleId()));
                    }
                    this.mFailoverStatusMap.put(Integer.valueOf(next.getRuleId()), Integer.valueOf(iIntValue));
                    this.mIsStatusMapEmpty = false;
                }
            }
            FailoverType failoverType = FailoverType.NO_ACTION;
            DLog.logf("FailoverRuleStatusManager returning %s for %s-%s", failoverType.toString(), str, Integer.valueOf(i));
            return new Pair<>(failoverType, "");
        }
    }

    @Nonnull
    public Pair<FailoverType, String> getFailoverType(@Nonnull ContentException contentException, @Nonnull DownloadType downloadType) {
        Preconditions.checkNotNull(contentException, "contentException");
        return updateStatusAndGetFailoverType(Strings.isNullOrEmpty(contentException.getFailoverSource()) ? "" : contentException.getFailoverSource().trim().toLowerCase(Locale.US), contentException.getStatusCode(), downloadType);
    }
}
