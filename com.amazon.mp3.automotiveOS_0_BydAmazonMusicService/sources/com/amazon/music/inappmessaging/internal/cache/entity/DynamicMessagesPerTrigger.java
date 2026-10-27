package com.amazon.music.inappmessaging.internal.cache.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
@Entity(tableName = "dynamicMessages")
public class DynamicMessagesPerTrigger {

    @ColumnInfo
    private List<DynamicMessage> dynamicMessages;

    @ColumnInfo
    private long epochTimeToLive;

    @NonNull
    @PrimaryKey
    private Trigger trigger;

    public DynamicMessagesPerTrigger(final Trigger trigger, final List<DynamicMessage> dynamicMessages, final long epochTimeToLive) {
        this.trigger = trigger;
        this.dynamicMessages = dynamicMessages;
        this.epochTimeToLive = epochTimeToLive;
    }

    public Trigger getTrigger() {
        return this.trigger;
    }

    public List<DynamicMessage> getDynamicMessages() {
        return this.dynamicMessages;
    }

    public long getEpochTimeToLive() {
        return this.epochTimeToLive;
    }
}
