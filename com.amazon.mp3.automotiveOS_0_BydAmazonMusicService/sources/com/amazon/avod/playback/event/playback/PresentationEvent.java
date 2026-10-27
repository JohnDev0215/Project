package com.amazon.avod.playback.event.playback;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PresentationEvent {
    private final PresentationType mPresentationType;

    public enum PresentationType {
        DAG("DAG"),
        PLSM("PLSM");

        final String presentationType;

        PresentationType(String str) {
            this.presentationType = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.presentationType;
        }
    }

    public PresentationEvent(@Nonnull PresentationType presentationType) {
        this.mPresentationType = presentationType;
    }

    public PresentationType getPresentationType() {
        return this.mPresentationType;
    }
}
