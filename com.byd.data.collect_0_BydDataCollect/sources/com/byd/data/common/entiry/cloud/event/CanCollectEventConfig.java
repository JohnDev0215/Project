package com.byd.data.common.entiry.cloud.event;

import com.byd.data.common.entiry.cloud.config.CommonConfig;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CanCollectEventConfig extends CommonConfig<EventCanId> {
    public List<EventCanId> eventCanIdInfoList;
    public List<EventRule> eventTriggerRulesList;
    protected byte timeScope;

    public byte a() {
        return this.timeScope;
    }

    public void b(byte b2) {
        this.timeScope = b2;
    }
}
