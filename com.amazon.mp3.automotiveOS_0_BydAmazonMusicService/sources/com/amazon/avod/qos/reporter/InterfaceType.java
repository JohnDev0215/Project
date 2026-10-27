package com.amazon.avod.qos.reporter;

/* JADX INFO: loaded from: classes2.dex */
public enum InterfaceType {
    NextUpCard,
    SkipCard,
    Background,
    Spinner,
    TrickPlay,
    Notification,
    MaturityRating;

    @Override // java.lang.Enum
    public String toString() {
        return name();
    }
}
