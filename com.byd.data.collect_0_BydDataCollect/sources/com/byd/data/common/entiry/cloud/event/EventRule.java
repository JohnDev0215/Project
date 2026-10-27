package com.byd.data.common.entiry.cloud.event;

import com.google.gson.u.c;
import java.util.Arrays;
import java.util.function.IntPredicate;

/* JADX INFO: loaded from: classes.dex */
public class EventRule implements Comparable<EventRule> {

    @c("eventName")
    public String eventName;
    public int eventUuid;
    private boolean hasHop;
    public byte preValue = -1;
    public byte triggerBep;
    public byte triggerBsp;
    private byte triggerDiagnosis;
    public byte triggerHopValue;
    public short triggerId;
    public int triggerJudgeCondition;
    public byte triggerSubId;
    public boolean triggerSubTag;
    private int[] triggerValueCollect;

    static /* synthetic */ boolean d(byte b2, int i2) {
        return i2 == b2;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(EventRule eventRule) {
        byte b2;
        byte b3;
        short s = this.triggerId;
        short s2 = eventRule.triggerId;
        if (s != s2) {
            return s - s2;
        }
        if (this.triggerSubTag || eventRule.triggerSubTag) {
            b2 = this.triggerSubId;
            b3 = eventRule.triggerSubId;
        } else {
            byte b4 = this.triggerBsp;
            byte b5 = eventRule.triggerBsp;
            if (b4 != b5) {
                return b4 - b5;
            }
            b2 = this.triggerBep;
            b3 = eventRule.triggerBep;
        }
        return b2 - b3;
    }

    public byte b(byte[] bArr) {
        byte b2 = this.triggerBsp;
        int i2 = b2 / 8;
        if (this.triggerBep / 8 != i2) {
            return bArr[i2];
        }
        return (byte) (((bArr[i2] & 255) >>> (b2 % 8)) & ((2 << (r2 - b2)) - 1));
    }

    public boolean c(short s, byte b2) {
        return this.triggerId == s && (!this.triggerSubTag || this.triggerSubId == b2);
    }

    public boolean e(byte[] bArr, short s) {
        int[] iArr;
        final byte b2;
        if (s != this.triggerId || this.triggerBsp > this.triggerBep || (iArr = this.triggerValueCollect) == null) {
            return false;
        }
        if (iArr.length < 1 || this.preValue == (b2 = b(bArr))) {
            return false;
        }
        this.preValue = b2;
        if (!this.hasHop ? this.triggerJudgeCondition != 0 : this.triggerJudgeCondition != 3) {
            return Arrays.stream(this.triggerValueCollect).allMatch(new IntPredicate() { // from class: com.byd.data.common.entiry.cloud.event.a
                @Override // java.util.function.IntPredicate
                public final boolean test(int i2) {
                    return EventRule.d(b2, i2);
                }
            });
        }
        int i2 = this.triggerJudgeCondition;
        if (i2 != 1 && i2 != 2) {
            if (i2 == 3) {
                this.hasHop = b2 == this.triggerHopValue;
            }
            return false;
        }
        int i3 = this.triggerValueCollect[0];
        if (i2 == 1) {
            if (b2 <= i3) {
                return false;
            }
        } else if (b2 >= i3) {
            return false;
        }
        return true;
    }
}
