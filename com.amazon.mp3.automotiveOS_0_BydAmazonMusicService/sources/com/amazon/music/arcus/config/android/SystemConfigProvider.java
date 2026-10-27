package com.amazon.music.arcus.config.android;

import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public interface SystemConfigProvider {
    String getLogLevel() throws Exception;

    Map getLogLevelTagMap() throws Exception;

    Date getUploadLogRequestDate(String str) throws Exception;
}
