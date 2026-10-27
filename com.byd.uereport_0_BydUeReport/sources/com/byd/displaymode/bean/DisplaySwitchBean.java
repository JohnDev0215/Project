package com.byd.displaymode.bean;

/* JADX INFO: loaded from: classes.dex */
public class DisplaySwitchBean {
    private final byte[] SYNC;
    private volatile int acAutoMode;
    private volatile int acMaxState;
    private volatile int acState;
    private volatile int rearMirrorFlip;
    private volatile int rearMirrorFollowState;
    private volatile SeatState[] seatArray;
    private volatile int seatWelcome;
    private volatile int smartRotate;
    private volatile int smartWelcomeLight;
    private SoundEffectBean soundEffectBean;
    private volatile int steerWelcome;
    private volatile int wirelessCharge;

    public DisplaySwitchBean() {
        byte[] bArr = new byte[0];
        this.SYNC = bArr;
        synchronized (bArr) {
            this.soundEffectBean = new SoundEffectBean();
        }
    }

    public int getAcAutoMode() {
        return this.acAutoMode;
    }

    public int getAcMaxState() {
        return this.acMaxState;
    }

    public int getAcState() {
        return this.acState;
    }

    public int getRearMirrorFlip() {
        return this.rearMirrorFlip;
    }

    public int getRearMirrorFollowState() {
        return this.rearMirrorFollowState;
    }

    public SeatState[] getSeatArray() {
        return this.seatArray;
    }

    public int getSeatWelcome() {
        return this.seatWelcome;
    }

    public int getSmartRotate() {
        return this.smartRotate;
    }

    public int getSmartWelcomeLight() {
        return this.smartWelcomeLight;
    }

    public SoundEffectBean getSoundEffectBean() {
        return this.soundEffectBean;
    }

    public int getSteerWelcome() {
        return this.steerWelcome;
    }

    public int getWirelessCharge() {
        return this.wirelessCharge;
    }

    public boolean isValidStatus(int i8) {
        return isValidStatus(i8, 0);
    }

    public boolean isValidStatus(int i8, int i9) {
        return (i8 ^ i9) != 0;
    }

    public void resetAcAssociate() {
        this.acState = 0;
        this.acMaxState = 0;
        this.acAutoMode = 0;
    }

    public void resetVehicle() {
        this.seatWelcome = 0;
        this.steerWelcome = 0;
        this.smartWelcomeLight = 0;
        this.rearMirrorFlip = 0;
        this.rearMirrorFollowState = 0;
        this.wirelessCharge = 0;
        this.smartRotate = 0;
    }

    public void setAcAssociateState(int i8, int i9, int i10) {
        if (i8 > 0 && i8 != this.acState) {
            this.acState = i8;
        }
        if (i9 > 0 && i9 != this.acMaxState) {
            this.acMaxState = i9;
        }
        if (i10 <= 0 || i10 == this.acAutoMode) {
            return;
        }
        this.acAutoMode = i10;
    }

    public void setRearMirrorFlip(int i8) {
        this.rearMirrorFlip = i8;
    }

    public void setRearMirrorFollowState(int i8) {
        this.rearMirrorFollowState = i8;
    }

    public void setSeatArray(SeatState[] seatStateArr) {
        this.seatArray = seatStateArr;
    }

    public void setSeatWelcome(int i8) {
        this.seatWelcome = i8;
    }

    public void setSmartRotate(int i8) {
        this.smartRotate = i8;
    }

    public void setSmartWelcomeLight(int i8) {
        this.smartWelcomeLight = i8;
    }

    public void setSoundEffectBean(SoundEffectBean soundEffectBean) {
        this.soundEffectBean = soundEffectBean;
    }

    public void setSteerWelcome(int i8) {
        this.steerWelcome = i8;
    }

    public void setWirelessCharge(int i8) {
        this.wirelessCharge = i8;
    }

    public void updateVehicle(int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        if (i8 > 0 && this.seatWelcome != i8) {
            this.seatWelcome = i8;
        }
        if (i9 > 0 && this.steerWelcome != i9) {
            this.steerWelcome = i9;
        }
        if (i10 > 0 && this.smartWelcomeLight != i10) {
            this.smartWelcomeLight = i10;
        }
        if (i11 > 0 && this.rearMirrorFlip != i11) {
            this.rearMirrorFlip = i11;
        }
        if (i12 > 0 && this.rearMirrorFollowState != i12) {
            this.rearMirrorFollowState = i12;
        }
        if (i13 > 0 && this.wirelessCharge != i13) {
            this.wirelessCharge = i13;
        }
        if (i14 <= 0 || this.smartRotate == i14) {
            return;
        }
        this.smartRotate = i14;
    }
}
