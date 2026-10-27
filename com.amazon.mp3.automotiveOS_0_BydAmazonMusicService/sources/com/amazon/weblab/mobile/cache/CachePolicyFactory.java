package com.amazon.weblab.mobile.cache;

import com.amazon.weblab.mobile.settings.Interval;
import com.amazon.weblab.mobile.settings.MobileWeblabCachePolicyType;

/* JADX INFO: loaded from: classes5.dex */
public class CachePolicyFactory {

    /* JADX INFO: renamed from: com.amazon.weblab.mobile.cache.CachePolicyFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$weblab$mobile$settings$MobileWeblabCachePolicyType;

        static {
            int[] iArr = new int[MobileWeblabCachePolicyType.values().length];
            $SwitchMap$com$amazon$weblab$mobile$settings$MobileWeblabCachePolicyType = iArr;
            try {
                iArr[MobileWeblabCachePolicyType.DISABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$weblab$mobile$settings$MobileWeblabCachePolicyType[MobileWeblabCachePolicyType.ON_FIRST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$weblab$mobile$settings$MobileWeblabCachePolicyType[MobileWeblabCachePolicyType.AUTO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static ICachePolicy createCachePolicy(MobileWeblabCachePolicyType mobileWeblabCachePolicyType, Interval interval) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$weblab$mobile$settings$MobileWeblabCachePolicyType[mobileWeblabCachePolicyType.ordinal()];
        if (i == 1) {
            return new DisabledPolicy();
        }
        if (i == 2) {
            return new OnFirstPolicy(interval);
        }
        if (i == 3) {
            return new AutoPolicy();
        }
        throw new UnsupportedOperationException(mobileWeblabCachePolicyType + " is not supported");
    }
}
