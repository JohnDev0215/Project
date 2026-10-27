package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class AudioSource {
    private final String url;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public String getUrl() {
        return this.url;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AudioSource.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(getUrl(), ((AudioSource) obj).getUrl());
    }

    public int hashCode() {
        return (getUrl()).hashCode();
    }
}
