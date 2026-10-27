package com.neusoft.na.sens;

import com.neusoft.na.sens.jni.OneCoreSenJni;

/* JADX INFO: loaded from: classes2.dex */
public class NaviVisionLaneLine {
    public void setNaviVisionLaneLine(VisionLaneLineInfo visionLaneLineInfo, long j) {
        OneCoreSenJni.getInstance().notifyChangeVisionLaneLine(visionLaneLineInfo, j);
    }
}
