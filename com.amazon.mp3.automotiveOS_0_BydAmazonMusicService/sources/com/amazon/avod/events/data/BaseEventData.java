package com.amazon.avod.events.data;

import com.amazon.avod.events.EventData;
import com.amazon.avod.events.EventPriority;
import com.amazon.avod.events.EventType;
import com.amazon.avod.http.internal.TokenKey;
import com.amazon.avod.util.DLog;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class BaseEventData implements EventData {
    private String mBody;
    private long mId;
    private boolean mIsProcessed;
    private final String mName;
    private final EventPriority mPriority;
    private int mRetryCount;
    private final String mSessionId;
    private String mTag;
    private final long mTimestampMillis;
    private final TokenKey mTokenKey;
    private final EventType mType;

    public BaseEventData(EventType eventType, String str, String str2, EventPriority eventPriority, String str3, String str4, TokenKey tokenKey) {
        this(-1L, eventType, str, str2, eventPriority, System.currentTimeMillis(), str3, str4, 0, false, tokenKey);
    }

    public BaseEventData(long j, EventType eventType, String str, String str2, EventPriority eventPriority, long j2, String str3, String str4, int i, boolean z, TokenKey tokenKey) {
        this.mId = j;
        this.mType = eventType;
        this.mName = str;
        this.mSessionId = str2;
        this.mPriority = eventPriority;
        this.mTimestampMillis = j2;
        this.mTag = str3;
        this.mBody = str4;
        this.mRetryCount = i;
        this.mIsProcessed = z;
        this.mTokenKey = tokenKey;
    }

    @Override // com.amazon.avod.events.EventData
    public long getId() {
        return this.mId;
    }

    @Override // com.amazon.avod.events.EventData
    public EventType getType() {
        return this.mType;
    }

    @Override // com.amazon.avod.events.EventData
    public String getName() {
        return this.mName;
    }

    @Override // com.amazon.avod.events.EventData
    public String getSessionId() {
        return this.mSessionId;
    }

    @Override // com.amazon.avod.events.EventData
    public EventPriority getPriority() {
        return this.mPriority;
    }

    @Override // com.amazon.avod.events.EventData
    public long getTimestampMillis() {
        return this.mTimestampMillis;
    }

    public long getAgeMillis() {
        return System.currentTimeMillis() - this.mTimestampMillis;
    }

    @Override // com.amazon.avod.events.EventData
    public String getTag() {
        return this.mTag;
    }

    @Override // com.amazon.avod.events.EventData
    public String getBody() {
        return this.mBody;
    }

    @Override // com.amazon.avod.events.EventData
    public int getRetryCount() {
        return this.mRetryCount;
    }

    @Override // com.amazon.avod.events.EventData
    public TokenKey getTokenKey() {
        return this.mTokenKey;
    }

    @Override // com.amazon.avod.events.EventData
    public boolean isProcessed() {
        return this.mIsProcessed;
    }

    @Override // com.amazon.avod.events.EventData
    public void setId(long j) {
        this.mId = j;
    }

    public String toString() {
        Locale locale = Locale.US;
        return DLog.sanitize_avod_message(String.format(locale, "Event=[%d: %s/%s]; Session=[%s]; Time=[%s]; Age=[%dmins]; Tag=[%s]; Body=[%s]", Long.valueOf(getId()), getType(), getName(), getSessionId(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", locale).format(new Date(getTimestampMillis())), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(getAgeMillis())), getTag(), getBody()));
    }
}
