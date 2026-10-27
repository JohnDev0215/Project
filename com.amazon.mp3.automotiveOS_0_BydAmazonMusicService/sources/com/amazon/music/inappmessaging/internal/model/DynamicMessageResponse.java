package com.amazon.music.inappmessaging.internal.model;

import com.amazon.music.inappmessaging.external.model.Trigger;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageResponse {
    public Map<Trigger, List<DynamicMessage>> dynamicMessagesByTrigger;
    public long epochTimeToLive;
}
