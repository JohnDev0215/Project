package com.amazon.mp3.web;

/* JADX INFO: loaded from: classes4.dex */
public class BasicWebEvent implements WebEvent {
    private String mId;

    public BasicWebEvent(final String id) {
        this.mId = id;
    }

    @Override // com.amazon.mp3.web.WebEvent
    public String id() {
        return this.mId;
    }
}
