package com.loostone.libtuning.channel.byd.old;

/* JADX INFO: loaded from: classes.dex */
public interface InsideDongle {
    int cancelPair(int i);

    int confirmPair(int i);

    int enterPair(int i);

    int getPairState(int i);

    String getVersion(int i);

    boolean isInsideMic();

    boolean isMicConnected(int i);

    boolean isPremiumSpeakerConnected();

    int rebootDongle();

    int rejectPair(int i);

    int resetDongle();

    int resetPair(int i);

    int setRejectPairTime(int i);
}
