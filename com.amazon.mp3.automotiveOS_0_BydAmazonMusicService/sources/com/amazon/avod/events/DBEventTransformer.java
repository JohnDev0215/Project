package com.amazon.avod.events;

import android.content.ContentValues;
import com.amazon.avod.http.internal.TokenKey;

/* JADX INFO: loaded from: classes2.dex */
public class DBEventTransformer {
    private final CompositeEventModelFactory mEventModelFactory;

    public DBEventTransformer(CompositeEventModelFactory compositeEventModelFactory) {
        this.mEventModelFactory = compositeEventModelFactory;
    }

    public ContentValues toContentValues(EventData eventData) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Type", eventData.getType().getName());
        contentValues.put("Name", eventData.getName());
        contentValues.put("SessionId", eventData.getSessionId());
        contentValues.put("Priority", Integer.valueOf(eventData.getPriority().ordinal()));
        contentValues.put("Timestamp", Long.valueOf(eventData.getTimestampMillis()));
        contentValues.put("ASIN", eventData.getTag());
        contentValues.put("Body", eventData.getBody());
        contentValues.put("RetryCount", Integer.valueOf(eventData.getRetryCount()));
        contentValues.put("Processed", Integer.valueOf(eventData.isProcessed() ? 1 : 0));
        TokenKey tokenKey = eventData.getTokenKey();
        if (tokenKey == null) {
            contentValues.put("AccountId", (String) null);
            contentValues.put("ProfileId", (String) null);
        } else {
            contentValues.put("AccountId", tokenKey.getAccountDirectedId());
            contentValues.put("ProfileId", tokenKey.getProfileDirectedId());
        }
        return contentValues;
    }
}
