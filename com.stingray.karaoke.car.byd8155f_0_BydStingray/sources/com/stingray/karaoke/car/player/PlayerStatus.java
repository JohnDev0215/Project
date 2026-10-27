package com.stingray.karaoke.car.player;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B9\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017¨\u0006\u0018"}, d2 = {"Lcom/stingray/karaoke/car/player/PlayerStatus;", "", "forceShowControls", "", "forceHideControls", "isActive", "isLoading", "blockCompanionActions", "prepareOnPlay", "<init>", "(Ljava/lang/String;IZZZZZZ)V", "getForceShowControls", "()Z", "getForceHideControls", "getBlockCompanionActions", "getPrepareOnPlay", "STOPPED", "PLAYING", "PAUSED", "BUFFERING", "READY", "COUNTDOWN", "COUNTDOWN_ENDED", "ERROR", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PlayerStatus {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ PlayerStatus[] $VALUES;
    private final boolean blockCompanionActions;
    private final boolean forceHideControls;
    private final boolean forceShowControls;
    private final boolean isActive;
    private final boolean isLoading;
    private final boolean prepareOnPlay;
    public static final PlayerStatus STOPPED = new PlayerStatus("STOPPED", 0, true, false, false, false, false, true);
    public static final PlayerStatus PLAYING = new PlayerStatus("PLAYING", 1, false, false, true, false, false, false);
    public static final PlayerStatus PAUSED = new PlayerStatus("PAUSED", 2, true, false, false, false, false, false);
    public static final PlayerStatus BUFFERING = new PlayerStatus("BUFFERING", 3, true, false, true, true, false, false);
    public static final PlayerStatus READY = new PlayerStatus("READY", 4, true, false, false, false, false, false);
    public static final PlayerStatus COUNTDOWN = new PlayerStatus("COUNTDOWN", 5, false, true, true, true, true, false);
    public static final PlayerStatus COUNTDOWN_ENDED = new PlayerStatus("COUNTDOWN_ENDED", 6, false, true, true, true, true, false);
    public static final PlayerStatus ERROR = new PlayerStatus("ERROR", 7, true, false, false, false, false, true);

    private static final /* synthetic */ PlayerStatus[] $values() {
        return new PlayerStatus[]{STOPPED, PLAYING, PAUSED, BUFFERING, READY, COUNTDOWN, COUNTDOWN_ENDED, ERROR};
    }

    static {
        PlayerStatus[] playerStatusArr$values = $values();
        $VALUES = playerStatusArr$values;
        $ENTRIES = r.e(playerStatusArr$values);
    }

    private PlayerStatus(String str, int i7, boolean z6, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14) {
        this.forceShowControls = z6;
        this.forceHideControls = z10;
        this.isActive = z11;
        this.isLoading = z12;
        this.blockCompanionActions = z13;
        this.prepareOnPlay = z14;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static PlayerStatus valueOf(String str) {
        return (PlayerStatus) Enum.valueOf(PlayerStatus.class, str);
    }

    public static PlayerStatus[] values() {
        return (PlayerStatus[]) $VALUES.clone();
    }

    public final boolean getBlockCompanionActions() {
        return this.blockCompanionActions;
    }

    public final boolean getForceHideControls() {
        return this.forceHideControls;
    }

    public final boolean getForceShowControls() {
        return this.forceShowControls;
    }

    public final boolean getPrepareOnPlay() {
        return this.prepareOnPlay;
    }

    /* JADX INFO: renamed from: isActive, reason: from getter */
    public final boolean getIsActive() {
        return this.isActive;
    }

    /* JADX INFO: renamed from: isLoading, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }
}
