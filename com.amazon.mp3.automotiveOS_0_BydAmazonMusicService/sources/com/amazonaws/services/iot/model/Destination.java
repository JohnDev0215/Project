package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class Destination implements Serializable {
    private S3Destination s3Destination;

    public S3Destination getS3Destination() {
        return this.s3Destination;
    }

    public void setS3Destination(S3Destination s3Destination) {
        this.s3Destination = s3Destination;
    }

    public Destination withS3Destination(S3Destination s3Destination) {
        this.s3Destination = s3Destination;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getS3Destination() != null) {
            sb.append("s3Destination: " + getS3Destination());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getS3Destination() == null ? 0 : getS3Destination().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Destination)) {
            return false;
        }
        Destination destination = (Destination) obj;
        if ((destination.getS3Destination() == null) ^ (getS3Destination() == null)) {
            return false;
        }
        return destination.getS3Destination() == null || destination.getS3Destination().equals(getS3Destination());
    }
}
