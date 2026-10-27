package com.amazon.music.inappmessaging.external.model;

import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.google.gson.Gson;

/* JADX INFO: loaded from: classes4.dex */
public class HandleActionRequest {
    private static final Gson gson = new Gson();
    private final String action;

    public HandleActionRequest(final DynamicMessageEvent event) throws IllegalArgumentException {
        validateArguments(event);
        this.action = event.getEvent();
    }

    public String toJson() {
        return gson.toJson(this);
    }

    private void validateArguments(final DynamicMessageEvent event) throws IllegalArgumentException {
        if (event == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }
        if (event.getEventType() != DynamicMessageEvent.DynamicMessageEventType.ACTION) {
            throw new IllegalArgumentException("DynamicMessageEvent has to be action type");
        }
    }
}
