package com.loostone.libtuning.inf.extern;

/* JADX INFO: loaded from: classes.dex */
public interface IMicControl {
    int cancelPair(int i);

    int confirmPair(int i);

    int enterPair(int i);

    int getAudioEffect();

    int getBaseStatus(int i);

    int getDongleSoundEffect();

    int getDongleVolume();

    int getEffectVolume();

    byte[] getFactoryMicStatus();

    String getFirmwareVersion(byte b2);

    String getHardwareVersion(byte b2);

    String getInteractEffect();

    double getMicBattery(int i);

    int getMicBatteryPercentage(int i);

    int getMicEQ1();

    int getMicEQ2();

    int getMicEQ3();

    int getMicEQ4();

    int getMicEQ5();

    int getMicEQ6();

    int getMicEQ7();

    int getMicEQ8();

    int getMicEQ9();

    String getMicName();

    int getMicShiftFrequency();

    int getMicVolumeHW(int i);

    boolean getPremiumSpeakerState();

    int getSystemVolume();

    boolean isHardEffect();

    int rebootDongle();

    int rejectPair(int i);

    int resetDongle();

    int resetPair(int i);

    int setAudioEffect(int i);

    int setDongleSoundEffect(int i);

    int setDongleVolume(int i);

    int setEffectVolume(int i);

    int setMicEQ1(int i);

    int setMicEQ2(int i);

    int setMicEQ3(int i);

    int setMicEQ4(int i);

    int setMicEQ5(int i);

    int setMicEQ6(int i);

    int setMicEQ7(int i);

    int setMicEQ8(int i);

    int setMicEQ9(int i);

    void setMicPower();

    int setMicShiftFrequency(int i);

    int setMicSwitch(int i);

    int setRejectPairTime(int i);

    int setSystemVolume(int i);
}
