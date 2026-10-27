package com.spotify.connectivity;

import java.util.ArrayList;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.c;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public enum ConnectionType {
    UNKNOWN(-1),
    NONE(0),
    MOBILE_GPRS(1),
    MOBILE_EDGE(2),
    MOBILE_3G(3),
    MOBILE_4G(4),
    WLAN(5),
    ETHERNET(6);

    private static final Map<Integer, ConnectionType> BY_VALUE;
    public static final Companion Companion = new Companion(null);
    private final int value;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ConnectionType connectionTypeOfValue(int i) {
            return (ConnectionType) ConnectionType.BY_VALUE.get(Integer.valueOf(i));
        }
    }

    static {
        ConnectionType[] connectionTypeArrValues = values();
        ArrayList arrayList = new ArrayList(connectionTypeArrValues.length);
        for (ConnectionType connectionType : connectionTypeArrValues) {
            arrayList.add(new Pair(Integer.valueOf(connectionType.value), connectionType));
        }
        BY_VALUE = c.k0(arrayList);
    }

    ConnectionType(int i) {
        this.value = i;
    }

    public static final ConnectionType connectionTypeOfValue(int i) {
        return Companion.connectionTypeOfValue(i);
    }

    public final int getValue() {
        return this.value;
    }
}
