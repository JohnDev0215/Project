package com.byd.vrassistant.bean.jsonparse;

import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import com.byd.vrassistant.bean.VoiceBarType;
import com.byd.vrassistant.bridge.BridgeHelper;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class VrUiState {
    boolean isNetAvailable;
    boolean isVrStart;
    String reason;
    VoiceBarType voiceBarType;
    String zone;

    public boolean isNetAvailable() {
        return this.isNetAvailable;
    }

    public void setNetAvailable(boolean z) {
        this.isNetAvailable = z;
    }

    public boolean isVrStart() {
        return this.isVrStart;
    }

    public void setVrStart(boolean z) {
        this.isVrStart = z;
    }

    public void setZone(String str) {
        this.zone = str;
    }

    public String getZone() {
        return this.zone;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public VoiceBarType getVoiceBarType() {
        return this.voiceBarType;
    }

    public void setVoiceBarType(VoiceBarType voiceBarType) {
        this.voiceBarType = voiceBarType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VrUiState vrUiState = (VrUiState) obj;
        return this.isVrStart == vrUiState.isVrStart && this.isNetAvailable == vrUiState.isNetAvailable && Objects.equals(this.zone, vrUiState.zone) && this.voiceBarType == vrUiState.voiceBarType;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.isVrStart), this.zone, this.voiceBarType);
    }

    public static VrUiState emptyInit() {
        return new VrUiState();
    }

    public static VrUiState initPTT() {
        String str;
        if (BridgeHelper.getVrAreaStatus().isDriverOnly()) {
            str = BridgeHelper.getVrAreaStatus().isRightDriver() ? VehicleAreaEnum.SEAT_ROW_1_RIGHT : VehicleAreaEnum.SEAT_ROW_1_LEFT;
        } else {
            str = VehicleAreaEnum.SEAT_ROW_FRONT;
        }
        VrUiState vrUiState = new VrUiState();
        vrUiState.setNetAvailable(true);
        vrUiState.setReason("PTT");
        vrUiState.setVrStart(true);
        vrUiState.setZone(str);
        vrUiState.setVoiceBarType(VoiceBarType.NORMAL);
        return vrUiState;
    }

    public static VrUiState initStarting(String str) {
        VrUiState vrUiState = new VrUiState();
        vrUiState.setNetAvailable(true);
        vrUiState.setReason("PTT");
        vrUiState.setVrStart(false);
        vrUiState.setZone(str);
        vrUiState.setVoiceBarType(VoiceBarType.NORMAL);
        return vrUiState;
    }

    public String toString() {
        return "VrUiState{isVrStart=" + this.isVrStart + ", isNetAvailable=" + this.isNetAvailable + ", reason='" + this.reason + "', zone='" + this.zone + "', voiceBarType=" + this.voiceBarType + '}';
    }
}
