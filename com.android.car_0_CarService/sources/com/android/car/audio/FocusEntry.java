package com.android.car.audio;

import android.content.pm.PackageManager;
import android.media.AudioFocusInfo;
import android.os.Bundle;
import com.android.car.Manifest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
final class FocusEntry {
    private final int mAudioContext;
    private final AudioFocusInfo mAudioFocusInfo;
    private final List<FocusEntry> mBlockers;
    private boolean mIsDucked;
    private final PackageManager mPackageManager;

    FocusEntry(AudioFocusInfo audioFocusInfo, int context, PackageManager packageManager) {
        Objects.requireNonNull(audioFocusInfo, "AudioFocusInfo cannot be null");
        Objects.requireNonNull(packageManager, "PackageManager cannot be null");
        this.mAudioFocusInfo = audioFocusInfo;
        this.mAudioContext = context;
        this.mBlockers = new ArrayList();
        this.mPackageManager = packageManager;
    }

    int getAudioContext() {
        return this.mAudioContext;
    }

    AudioFocusInfo getAudioFocusInfo() {
        return this.mAudioFocusInfo;
    }

    boolean isUnblocked() {
        return this.mBlockers.isEmpty();
    }

    void addBlocker(FocusEntry blocker) {
        this.mBlockers.add(blocker);
    }

    void removeBlocker(FocusEntry blocker) {
        this.mBlockers.remove(blocker);
    }

    String getClientId() {
        return this.mAudioFocusInfo.getClientId();
    }

    boolean isDucked() {
        return this.mIsDucked;
    }

    void setDucked(boolean ducked) {
        this.mIsDucked = ducked;
    }

    boolean wantsPauseInsteadOfDucking() {
        return (this.mAudioFocusInfo.getFlags() & 2) != 0;
    }

    boolean receivesDuckEvents() {
        Bundle bundle = this.mAudioFocusInfo.getAttributes().getBundle();
        return bundle != null && bundle.getBoolean("android.car.media.AUDIOFOCUS_EXTRA_RECEIVE_DUCKING_EVENTS") && this.mPackageManager.checkPermission(Manifest.permission.RECEIVE_CAR_AUDIO_DUCKING_EVENTS, this.mAudioFocusInfo.getPackageName()) == 0;
    }

    String getUsageName() {
        return this.mAudioFocusInfo.getAttributes().usageToString();
    }

    public void dump(String indent, PrintWriter writer) {
        writer.printf("%s%s - %s\n", indent, getClientId(), getUsageName());
        writer.printf("%s\tReceives Duck Events: %b, ", indent, Boolean.valueOf(receivesDuckEvents()));
        writer.printf("Wants Pause Instead of Ducking: %b, ", Boolean.valueOf(wantsPauseInsteadOfDucking()));
        writer.printf("Is Ducked: %b\n", Boolean.valueOf(isDucked()));
    }
}
