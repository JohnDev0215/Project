package org.joda.time.tz;

import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.joda.time.DateTimeZone;

/* JADX INFO: loaded from: classes6.dex */
public final class FixedDateTimeZone extends DateTimeZone {
    private final String iNameKey;
    private final int iStandardOffset;
    private final int iWallOffset;

    @Override // org.joda.time.DateTimeZone
    public boolean isFixed() {
        return true;
    }

    @Override // org.joda.time.DateTimeZone
    public long nextTransition(long j) {
        return j;
    }

    @Override // org.joda.time.DateTimeZone
    public long previousTransition(long j) {
        return j;
    }

    public FixedDateTimeZone(String str, String str2, int i, int i2) {
        super(str);
        this.iNameKey = str2;
        this.iWallOffset = i;
        this.iStandardOffset = i2;
    }

    @Override // org.joda.time.DateTimeZone
    public String getNameKey(long j) {
        return this.iNameKey;
    }

    @Override // org.joda.time.DateTimeZone
    public int getOffset(long j) {
        return this.iWallOffset;
    }

    @Override // org.joda.time.DateTimeZone
    public int getStandardOffset(long j) {
        return this.iStandardOffset;
    }

    @Override // org.joda.time.DateTimeZone
    public int getOffsetFromLocal(long j) {
        return this.iWallOffset;
    }

    @Override // org.joda.time.DateTimeZone
    public TimeZone toTimeZone() {
        String id = getID();
        if (id.length() == 6 && (id.startsWith(MqttTopic.SINGLE_LEVEL_WILDCARD) || id.startsWith("-"))) {
            return TimeZone.getTimeZone("GMT" + getID());
        }
        return new SimpleTimeZone(this.iWallOffset, getID());
    }

    @Override // org.joda.time.DateTimeZone
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FixedDateTimeZone)) {
            return false;
        }
        FixedDateTimeZone fixedDateTimeZone = (FixedDateTimeZone) obj;
        return getID().equals(fixedDateTimeZone.getID()) && this.iStandardOffset == fixedDateTimeZone.iStandardOffset && this.iWallOffset == fixedDateTimeZone.iWallOffset;
    }

    @Override // org.joda.time.DateTimeZone
    public int hashCode() {
        return getID().hashCode() + (this.iStandardOffset * 37) + (this.iWallOffset * 31);
    }
}
