package com.byd.carsettings.model.dipilot;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class CockpitPerceptionAssistModel implements Serializable {
    public static final int ASSIST_STATE_OFF = 0;
    public static final int ASSIST_STATE_ON = 1;
    private String assistDes;
    private String assistDialogContent;
    private String assistDialogNotice;
    private String assistMode;
    private String assistPicUrl;
    private int assistState;
    private String assistTitle;
    private String switchDialogContent;
    private String switchDialogNotice;

    public String getAssistDes() {
        return this.assistDes;
    }

    public String getAssistDialogContent() {
        return this.assistDialogContent;
    }

    public String getAssistDialogNotice() {
        return this.assistDialogNotice;
    }

    public String getAssistMode() {
        return this.assistMode;
    }

    public String getAssistPicUrl() {
        return this.assistPicUrl;
    }

    public int getAssistState() {
        return this.assistState;
    }

    public String getAssistTitle() {
        return this.assistTitle;
    }

    public String getSwitchDialogContent() {
        return this.switchDialogContent;
    }

    public String getSwitchDialogNotice() {
        return this.switchDialogNotice;
    }

    public void setAssistDes(String str) {
        this.assistDes = str;
    }

    public void setAssistDialogContent(String str) {
        this.assistDialogContent = str;
    }

    public void setAssistDialogNotice(String str) {
        this.assistDialogNotice = str;
    }

    public void setAssistMode(String str) {
        this.assistMode = str;
    }

    public void setAssistPicUrl(String str) {
        this.assistPicUrl = str;
    }

    public void setAssistState(int i) {
        this.assistState = i;
    }

    public void setAssistTitle(String str) {
        this.assistTitle = str;
    }

    public void setSwitchDialogContent(String str) {
        this.switchDialogContent = str;
    }

    public void setSwitchDialogNotice(String str) {
        this.switchDialogNotice = str;
    }

    public String toString() {
        return "CockpitPerceptionAssistModel{assistTitle='" + this.assistTitle + "', assistDes='" + this.assistDes + "', assistDialogContent='" + this.assistDialogContent + "', assistDialogNotice='" + this.assistDialogNotice + "', assistPicUrl='" + this.assistPicUrl + "', assistState=" + this.assistState + '}';
    }
}
