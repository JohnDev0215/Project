package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class SkipLimitIotActionStrategy extends AbstractActionStrategy {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.SkipLimitIotActionStrategy");
    private AbstractActionStrategy noSkipsRemainingActionStrategy;
    private AbstractActionStrategy skipsRemainingActionStrategy;
    private String thingShadowName;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.v2.track.AbstractActionStrategy, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractActionStrategy abstractActionStrategy) {
        if (abstractActionStrategy == null) {
            return -1;
        }
        if (abstractActionStrategy == this) {
            return 0;
        }
        if (!(abstractActionStrategy instanceof SkipLimitIotActionStrategy)) {
            return 1;
        }
        SkipLimitIotActionStrategy skipLimitIotActionStrategy = (SkipLimitIotActionStrategy) abstractActionStrategy;
        AbstractActionStrategy skipsRemainingActionStrategy = getSkipsRemainingActionStrategy();
        AbstractActionStrategy skipsRemainingActionStrategy2 = skipLimitIotActionStrategy.getSkipsRemainingActionStrategy();
        if (skipsRemainingActionStrategy != skipsRemainingActionStrategy2) {
            if (skipsRemainingActionStrategy == null) {
                return -1;
            }
            if (skipsRemainingActionStrategy2 == null) {
                return 1;
            }
            int iCompareTo = skipsRemainingActionStrategy.compareTo(skipsRemainingActionStrategy2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String thingShadowName = getThingShadowName();
        String thingShadowName2 = skipLimitIotActionStrategy.getThingShadowName();
        if (thingShadowName != thingShadowName2) {
            if (thingShadowName == null) {
                return -1;
            }
            if (thingShadowName2 == null) {
                return 1;
            }
            int iCompareTo2 = thingShadowName.compareTo(thingShadowName2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        AbstractActionStrategy noSkipsRemainingActionStrategy = getNoSkipsRemainingActionStrategy();
        AbstractActionStrategy noSkipsRemainingActionStrategy2 = skipLimitIotActionStrategy.getNoSkipsRemainingActionStrategy();
        if (noSkipsRemainingActionStrategy != noSkipsRemainingActionStrategy2) {
            if (noSkipsRemainingActionStrategy == null) {
                return -1;
            }
            if (noSkipsRemainingActionStrategy2 == null) {
                return 1;
            }
            int iCompareTo3 = noSkipsRemainingActionStrategy.compareTo(noSkipsRemainingActionStrategy2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(abstractActionStrategy);
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.AbstractActionStrategy
    public boolean equals(Object obj) {
        if (!(obj instanceof SkipLimitIotActionStrategy)) {
            return false;
        }
        SkipLimitIotActionStrategy skipLimitIotActionStrategy = (SkipLimitIotActionStrategy) obj;
        return super.equals(obj) && internalEqualityCheck(getSkipsRemainingActionStrategy(), skipLimitIotActionStrategy.getSkipsRemainingActionStrategy()) && internalEqualityCheck(getThingShadowName(), skipLimitIotActionStrategy.getThingShadowName()) && internalEqualityCheck(getNoSkipsRemainingActionStrategy(), skipLimitIotActionStrategy.getNoSkipsRemainingActionStrategy());
    }

    public AbstractActionStrategy getNoSkipsRemainingActionStrategy() {
        return this.noSkipsRemainingActionStrategy;
    }

    public AbstractActionStrategy getSkipsRemainingActionStrategy() {
        return this.skipsRemainingActionStrategy;
    }

    public String getThingShadowName() {
        return this.thingShadowName;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.AbstractActionStrategy
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSkipsRemainingActionStrategy(), getThingShadowName(), getNoSkipsRemainingActionStrategy());
    }

    public void setNoSkipsRemainingActionStrategy(AbstractActionStrategy abstractActionStrategy) {
        this.noSkipsRemainingActionStrategy = abstractActionStrategy;
    }

    public void setSkipsRemainingActionStrategy(AbstractActionStrategy abstractActionStrategy) {
        this.skipsRemainingActionStrategy = abstractActionStrategy;
    }

    public void setThingShadowName(String str) {
        this.thingShadowName = str;
    }
}
