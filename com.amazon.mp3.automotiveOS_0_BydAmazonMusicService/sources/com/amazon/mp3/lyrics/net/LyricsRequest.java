package com.amazon.mp3.lyrics.net;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public enum LyricsRequest implements Request {
    GetLyricsByTrackAsins("com.amazon.musicxray.MusicXrayService", "getLyricsByTrackAsinBatch"),
    GetLyricsStatusChangesByDate("com.amazon.musicxray.MusicXrayService", "getLyricStatusChangesByDate");

    private final String mRequestName;
    private final String mServiceName;

    LyricsRequest(String serviceName, String requestName) {
        this.mRequestName = requestName;
        this.mServiceName = serviceName;
    }

    @Override // com.amazon.mp3.lyrics.net.Request
    public String getServiceName() {
        return this.mServiceName;
    }

    @Override // com.amazon.mp3.lyrics.net.Request
    public String getRequestName() {
        return this.mRequestName;
    }

    public JSONObject execute(JSONObject request) throws Exception {
        return LyricsRequestDispatcher.dispatch(this, request);
    }
}
