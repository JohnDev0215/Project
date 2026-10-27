package com.amazon.mp3.view.stage.overlay;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: OverlaySelectionType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType;", "", "()V", "Automatic", "Lyrics", "None", "Xray", "Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType$Automatic;", "Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType$Lyrics;", "Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType$Xray;", "Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType$None;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class OverlaySelectionType {
    public /* synthetic */ OverlaySelectionType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private OverlaySelectionType() {
    }

    /* JADX INFO: compiled from: OverlaySelectionType.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType$Automatic;", "Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Automatic extends OverlaySelectionType {
        public static final Automatic INSTANCE = new Automatic();

        private Automatic() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: OverlaySelectionType.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType$Lyrics;", "Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Lyrics extends OverlaySelectionType {
        public static final Lyrics INSTANCE = new Lyrics();

        private Lyrics() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: OverlaySelectionType.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType$Xray;", "Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Xray extends OverlaySelectionType {
        public static final Xray INSTANCE = new Xray();

        private Xray() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: OverlaySelectionType.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType$None;", "Lcom/amazon/mp3/view/stage/overlay/OverlaySelectionType;", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class None extends OverlaySelectionType {
        public static final None INSTANCE = new None();

        private None() {
            super(null);
        }
    }
}
