package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class AttemptAccountCreationResponse implements Comparable<AttemptAccountCreationResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.AttemptAccountCreationResponse");
    private Account account;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AttemptAccountCreationResponse attemptAccountCreationResponse) {
        Account account;
        Account account2;
        if (attemptAccountCreationResponse == null) {
            return -1;
        }
        if (attemptAccountCreationResponse != this && (account = getAccount()) != (account2 = attemptAccountCreationResponse.getAccount())) {
            if (account == null) {
                return -1;
            }
            if (account2 == null) {
                return 1;
            }
            int iCompareTo = account.compareTo(account2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AttemptAccountCreationResponse) {
            return internalEqualityCheck(getAccount(), ((AttemptAccountCreationResponse) obj).getAccount());
        }
        return false;
    }

    public Account getAccount() {
        return this.account;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAccount());
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
