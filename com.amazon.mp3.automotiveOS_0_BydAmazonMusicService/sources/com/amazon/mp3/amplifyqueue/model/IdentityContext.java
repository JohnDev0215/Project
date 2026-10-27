package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class IdentityContext {
    private final String musicRequestIdentityContextToken;
    private final String recognizedSpeakerId;
    private final String recognizedSpeakerLinkedId;
    private final String serializedMusicRequestIdentityContext;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public String getSerializedMusicRequestIdentityContext() {
        return this.serializedMusicRequestIdentityContext;
    }

    public String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }

    public String getRecognizedSpeakerId() {
        return this.recognizedSpeakerId;
    }

    public String getRecognizedSpeakerLinkedId() {
        return this.recognizedSpeakerLinkedId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || IdentityContext.class != obj.getClass()) {
            return false;
        }
        IdentityContext identityContext = (IdentityContext) obj;
        return ObjectsCompat.equals(getSerializedMusicRequestIdentityContext(), identityContext.getSerializedMusicRequestIdentityContext()) && ObjectsCompat.equals(getMusicRequestIdentityContextToken(), identityContext.getMusicRequestIdentityContextToken()) && ObjectsCompat.equals(getRecognizedSpeakerId(), identityContext.getRecognizedSpeakerId()) && ObjectsCompat.equals(getRecognizedSpeakerLinkedId(), identityContext.getRecognizedSpeakerLinkedId());
    }

    public int hashCode() {
        return (getSerializedMusicRequestIdentityContext() + getMusicRequestIdentityContextToken() + getRecognizedSpeakerId() + getRecognizedSpeakerLinkedId()).hashCode();
    }
}
