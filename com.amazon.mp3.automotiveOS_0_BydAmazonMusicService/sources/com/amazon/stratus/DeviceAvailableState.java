package com.amazon.stratus;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class DeviceAvailableState implements Comparable<DeviceAvailableState> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.DeviceAvailableState");
    private Date availableDate;
    private String nextState;
    private Date stateStartDate;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated DeviceAvailableState deviceAvailableState) {
        if (deviceAvailableState == null) {
            return -1;
        }
        if (deviceAvailableState == this) {
            return 0;
        }
        Date availableDate = getAvailableDate();
        Date availableDate2 = deviceAvailableState.getAvailableDate();
        if (availableDate != availableDate2) {
            if (availableDate == null) {
                return -1;
            }
            if (availableDate2 == null) {
                return 1;
            }
            int iCompareTo = availableDate.compareTo(availableDate2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Date stateStartDate = getStateStartDate();
        Date stateStartDate2 = deviceAvailableState.getStateStartDate();
        if (stateStartDate != stateStartDate2) {
            if (stateStartDate == null) {
                return -1;
            }
            if (stateStartDate2 == null) {
                return 1;
            }
            int iCompareTo2 = stateStartDate.compareTo(stateStartDate2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String nextState = getNextState();
        String nextState2 = deviceAvailableState.getNextState();
        if (nextState != nextState2) {
            if (nextState == null) {
                return -1;
            }
            if (nextState2 == null) {
                return 1;
            }
            int iCompareTo3 = nextState.compareTo(nextState2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceAvailableState)) {
            return false;
        }
        DeviceAvailableState deviceAvailableState = (DeviceAvailableState) obj;
        return internalEqualityCheck(getAvailableDate(), deviceAvailableState.getAvailableDate()) && internalEqualityCheck(getStateStartDate(), deviceAvailableState.getStateStartDate()) && internalEqualityCheck(getNextState(), deviceAvailableState.getNextState());
    }

    public Date getAvailableDate() {
        return this.availableDate;
    }

    public String getNextState() {
        return this.nextState;
    }

    public Date getStateStartDate() {
        return this.stateStartDate;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAvailableDate(), getStateStartDate(), getNextState());
    }

    public void setAvailableDate(Date date) {
        this.availableDate = date;
    }

    public void setNextState(String str) {
        this.nextState = str;
    }

    public void setStateStartDate(Date date) {
        this.stateStartDate = date;
    }
}
