package com.spotify.connectivity;

import java.util.ArrayList;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.c;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public enum ConnectivityPolicy {
    DISALLOW_ALL(0),
    ALLOW_ALL(1),
    ALLOW_NON_PERSISTENT_CONNECTIONS(2);

    private static final Map<Integer, ConnectivityPolicy> BY_VALUE;
    public static final Companion Companion = new Companion(null);
    private final int value;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ConnectivityPolicy connectivityPolicyOfValue(int i) {
            return (ConnectivityPolicy) ConnectivityPolicy.BY_VALUE.get(Integer.valueOf(i));
        }
    }

    static {
        ConnectivityPolicy[] connectivityPolicyArrValues = values();
        ArrayList arrayList = new ArrayList(connectivityPolicyArrValues.length);
        for (ConnectivityPolicy connectivityPolicy : connectivityPolicyArrValues) {
            arrayList.add(new Pair(Integer.valueOf(connectivityPolicy.value), connectivityPolicy));
        }
        BY_VALUE = c.k0(arrayList);
    }

    ConnectivityPolicy(int i) {
        this.value = i;
    }

    public static final ConnectivityPolicy connectivityPolicyOfValue(int i) {
        return Companion.connectivityPolicyOfValue(i);
    }

    public final int getValue() {
        return this.value;
    }
}
