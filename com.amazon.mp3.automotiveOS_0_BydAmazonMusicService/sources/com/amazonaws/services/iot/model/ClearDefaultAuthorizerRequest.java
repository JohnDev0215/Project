package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ClearDefaultAuthorizerRequest extends AmazonWebServiceRequest implements Serializable {
    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "{}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ClearDefaultAuthorizerRequest)) {
            return false;
        }
        return true;
    }
}
