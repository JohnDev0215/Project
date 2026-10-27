package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class Capability implements Comparable<Capability> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.Capability");
    private String action;
    private AbstractActionStrategy actionStrategy;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Capability capability) {
        if (capability == null) {
            return -1;
        }
        if (capability == this) {
            return 0;
        }
        String action = getAction();
        String action2 = capability.getAction();
        if (action != action2) {
            if (action == null) {
                return -1;
            }
            if (action2 == null) {
                return 1;
            }
            int iCompareTo = action.compareTo(action2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        AbstractActionStrategy actionStrategy = getActionStrategy();
        AbstractActionStrategy actionStrategy2 = capability.getActionStrategy();
        if (actionStrategy != actionStrategy2) {
            if (actionStrategy == null) {
                return -1;
            }
            if (actionStrategy2 == null) {
                return 1;
            }
            int iCompareTo2 = actionStrategy.compareTo(actionStrategy2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Capability)) {
            return false;
        }
        Capability capability = (Capability) obj;
        return internalEqualityCheck(getAction(), capability.getAction()) && internalEqualityCheck(getActionStrategy(), capability.getActionStrategy());
    }

    public String getAction() {
        return this.action;
    }

    public AbstractActionStrategy getActionStrategy() {
        return this.actionStrategy;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAction(), getActionStrategy());
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setActionStrategy(AbstractActionStrategy abstractActionStrategy) {
        this.actionStrategy = abstractActionStrategy;
    }
}
