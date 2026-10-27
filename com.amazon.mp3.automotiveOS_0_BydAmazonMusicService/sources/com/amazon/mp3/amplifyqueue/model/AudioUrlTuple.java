package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class AudioUrlTuple {
    private final String contentType;
    private final String url;

    public interface BuildStep {
    }

    public static class Builder implements ContentTypeStep, UrlStep, BuildStep {
    }

    public interface ContentTypeStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface UrlStep {
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AudioUrlTuple.class != obj.getClass()) {
            return false;
        }
        AudioUrlTuple audioUrlTuple = (AudioUrlTuple) obj;
        return ObjectsCompat.equals(getContentType(), audioUrlTuple.getContentType()) && ObjectsCompat.equals(getUrl(), audioUrlTuple.getUrl());
    }

    public int hashCode() {
        return (getContentType() + getUrl()).hashCode();
    }
}
