package com.byd.profile.request.cell;

import com.byd.profile.request.cell.AirRequest;
import com.byd.profile.request.cell.AtmosphereRequest;
import com.byd.profile.request.cell.ChildLockRequest;
import com.byd.profile.request.cell.DoorRequest;
import com.byd.profile.request.cell.FragranceRequest;
import com.byd.profile.request.cell.HeadlightsRequest;
import com.byd.profile.request.cell.InstrumentRequest;
import com.byd.profile.request.cell.MediaRequest;
import com.byd.profile.request.cell.MusicRequest;
import com.byd.profile.request.cell.ScreenRequest;
import com.byd.profile.request.cell.SeatRequest;
import com.byd.profile.request.cell.WindowRequest;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class RequestValue extends BaseValue {
    private AirRequest.AirValue mAirValue;
    private AtmosphereRequest.AtmosphereValue mAtmosphereValue;
    private ChildLockRequest.ChildLockValue mChildLockValue;
    private DoorRequest.DoorValue mDoorValue;
    private FragranceRequest.FragranceValue mFragranceValue;
    private HeadlightsRequest.HeadlightsValue mHeadlightsValue;
    private InstrumentRequest.InstrumentValue mInstrumentValue;
    private MediaRequest.MediaValue mMediaValue;
    private MusicRequest.MusicValue mMusicValue;
    private ScreenRequest.ScreenValue mScreenValue;
    private List<SeatRequest.SeatValue> mSeatList;
    private WindowRequest.WindowValue mWindowValue;

    public MediaRequest.MediaValue getMediaValue() {
        return this.mMediaValue;
    }

    public void setMediaValue(MediaRequest.MediaValue mediaValue) {
        this.mMediaValue = mediaValue;
    }

    public AirRequest.AirValue getAirValue() {
        return this.mAirValue;
    }

    public void setAirValue(AirRequest.AirValue airValue) {
        this.mAirValue = airValue;
    }

    public List<SeatRequest.SeatValue> getSeatValue() {
        return this.mSeatList;
    }

    public void setSeatValue(SeatRequest.SeatValue seatValue) {
        if (this.mSeatList == null) {
            this.mSeatList = new ArrayList();
        }
        this.mSeatList.add(seatValue);
    }

    public DoorRequest.DoorValue getDoorValue() {
        return this.mDoorValue;
    }

    public void setDoorValue(DoorRequest.DoorValue doorValue) {
        this.mDoorValue = doorValue;
    }

    public WindowRequest.WindowValue getWindowValue() {
        return this.mWindowValue;
    }

    public void setWindowValue(WindowRequest.WindowValue windowValue) {
        this.mWindowValue = windowValue;
    }

    public ChildLockRequest.ChildLockValue getChildLockValue() {
        return this.mChildLockValue;
    }

    public void setChildLockValue(ChildLockRequest.ChildLockValue childLockValue) {
        this.mChildLockValue = childLockValue;
    }

    public FragranceRequest.FragranceValue getFragranceValue() {
        return this.mFragranceValue;
    }

    public void setFragranceValue(FragranceRequest.FragranceValue fragranceValue) {
        this.mFragranceValue = fragranceValue;
    }

    public InstrumentRequest.InstrumentValue getInstrumentValue() {
        return this.mInstrumentValue;
    }

    public void setInstrumentValue(InstrumentRequest.InstrumentValue instrumentValue) {
        this.mInstrumentValue = instrumentValue;
    }

    public AtmosphereRequest.AtmosphereValue getAtmosphereValue() {
        return this.mAtmosphereValue;
    }

    public void setAtmosphereValue(AtmosphereRequest.AtmosphereValue atmosphereValue) {
        this.mAtmosphereValue = atmosphereValue;
    }

    public HeadlightsRequest.HeadlightsValue getHeadlightsValue() {
        return this.mHeadlightsValue;
    }

    public void setHeadlightsValue(HeadlightsRequest.HeadlightsValue headlightsValue) {
        this.mHeadlightsValue = headlightsValue;
    }

    public ScreenRequest.ScreenValue getScreenValue() {
        return this.mScreenValue;
    }

    public void setScreenValue(ScreenRequest.ScreenValue screenValue) {
        this.mScreenValue = screenValue;
    }

    public MusicRequest.MusicValue getMusicValue() {
        return this.mMusicValue;
    }

    public void setMusicValue(MusicRequest.MusicValue musicValue) {
        this.mMusicValue = musicValue;
    }
}
