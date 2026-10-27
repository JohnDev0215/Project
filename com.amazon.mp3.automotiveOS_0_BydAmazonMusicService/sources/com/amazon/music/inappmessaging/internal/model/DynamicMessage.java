package com.amazon.music.inappmessaging.internal.model;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessage {
    public CachePolicyType cachePolicy;
    public long expirationTime;
    public String messageRef;
    public Boolean shouldRecordEmptyControl;
    public Map<String, String> templateAttributes;
    public String templateId;
    public List<Weblab> weblabsToTrigger;
}
