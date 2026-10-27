package com.amazon.music.casting.session.things;

/* JADX INFO: loaded from: classes4.dex */
public class RejectedThingShadowDocument {
    private String clientToken;
    private String code;
    private String message;
    private Long timestamp;

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public String getClientToken() {
        return this.clientToken;
    }

    public String toString() {
        return "RejectedThingShadowDocument{code=" + this.code + ", message=" + this.message + ", timestamp=" + this.timestamp + ", clientToken=" + this.clientToken + '}';
    }
}
