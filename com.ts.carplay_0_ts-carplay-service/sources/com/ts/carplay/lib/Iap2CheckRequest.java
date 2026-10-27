package com.ts.carplay.lib;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class Iap2CheckRequest {
    String mac;
    int reason;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Iap2CheckRequest that = (Iap2CheckRequest) o;
        return this.mac.equals(that.mac);
    }

    public int hashCode() {
        return Objects.hash(this.mac);
    }
}
