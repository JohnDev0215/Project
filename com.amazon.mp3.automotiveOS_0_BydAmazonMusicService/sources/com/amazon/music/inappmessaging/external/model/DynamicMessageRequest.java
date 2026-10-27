package com.amazon.music.inappmessaging.external.model;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageRequest {
    private static final Gson gson = new Gson();
    private final String campaignId;
    private final Channel channel;
    private final DynamicMessageClientCapabilities clientCapabilities;
    private final Map<String, List<String>> eligibleContextualParams;
    private final String mtr;
    private final List<DynamicMessageTemplate> templates;
    private final List<String> triggers;

    public DynamicMessageRequest(final Channel channel, final List<DynamicMessageEvent> events, final List<DynamicMessageTemplate> templates, final String campaignId, final String mtr, final Map<String, List<String>> eligibleContextualParams, final DynamicMessageClientCapabilities clientCapabilities) {
        validateArguments(channel, events, templates, mtr);
        ArrayList arrayList = new ArrayList();
        Iterator<DynamicMessageEvent> it = events.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getEvent());
        }
        this.channel = channel;
        this.triggers = arrayList;
        this.templates = templates;
        this.campaignId = campaignId;
        this.mtr = mtr;
        this.eligibleContextualParams = eligibleContextualParams;
        this.clientCapabilities = clientCapabilities;
    }

    public String toJson() {
        return gson.toJson(this);
    }

    private void validateArguments(final Channel channel, final List<DynamicMessageEvent> events, final List<DynamicMessageTemplate> templates, final String mtr) {
        if (channel == null) {
            throw new IllegalArgumentException("Channel cannot be null");
        }
        if (events == null || events.size() == 0) {
            throw new IllegalArgumentException("Trigger list cannot be null or empty");
        }
        if (templates == null || templates.size() == 0) {
            throw new IllegalArgumentException("Template list cannot be null or empty");
        }
        if (mtr == null) {
            throw new IllegalArgumentException("MTR cannot be null");
        }
    }

    public List<String> getTriggers() {
        return this.triggers;
    }
}
