package org.apache.commons.net.ntp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class TimeInfo {
    private List<String> comments;
    private Long delay;
    private boolean detailsComputed;
    private final NtpV3Packet message;
    private Long offset;
    private final long returnTime;

    public TimeInfo(NtpV3Packet message, long returnTime) {
        this(message, returnTime, null, true);
    }

    public TimeInfo(NtpV3Packet message, long returnTime, List<String> comments) {
        this(message, returnTime, comments, true);
    }

    public TimeInfo(NtpV3Packet msgPacket, long returnTime, boolean doComputeDetails) {
        this(msgPacket, returnTime, null, doComputeDetails);
    }

    public TimeInfo(NtpV3Packet message, long returnTime, List<String> comments, boolean doComputeDetails) {
        if (message == null) {
            throw new IllegalArgumentException("message cannot be null");
        }
        this.returnTime = returnTime;
        this.message = message;
        this.comments = comments;
        if (doComputeDetails) {
            computeDetails();
        }
    }

    public void addComment(String comment) {
        if (this.comments == null) {
            this.comments = new ArrayList();
        }
        this.comments.add(comment);
    }

    public void computeDetails() {
        if (!this.detailsComputed) {
            this.detailsComputed = true;
            if (this.comments == null) {
                this.comments = new ArrayList();
            }
            TimeStamp origNtpTime = this.message.getOriginateTimeStamp();
            long origTime = origNtpTime.getTime();
            TimeStamp rcvNtpTime = this.message.getReceiveTimeStamp();
            long rcvTime = rcvNtpTime.getTime();
            TimeStamp xmitNtpTime = this.message.getTransmitTimeStamp();
            long xmitTime = xmitNtpTime.getTime();
            if (origNtpTime.ntpValue() == 0) {
                if (xmitNtpTime.ntpValue() != 0) {
                    this.offset = Long.valueOf(xmitTime - this.returnTime);
                    this.comments.add("Error: zero orig time -- cannot compute delay");
                    return;
                } else {
                    this.comments.add("Error: zero orig time -- cannot compute delay/offset");
                    return;
                }
            }
            if (rcvNtpTime.ntpValue() == 0 || xmitNtpTime.ntpValue() == 0) {
                this.comments.add("Warning: zero rcvNtpTime or xmitNtpTime");
                long j = this.returnTime;
                if (origTime <= j) {
                    this.delay = Long.valueOf(j - origTime);
                } else {
                    this.comments.add("Error: OrigTime > DestRcvTime");
                }
                if (rcvNtpTime.ntpValue() != 0) {
                    this.offset = Long.valueOf(rcvTime - origTime);
                    return;
                } else {
                    if (xmitNtpTime.ntpValue() != 0) {
                        this.offset = Long.valueOf(xmitTime - this.returnTime);
                        return;
                    }
                    return;
                }
            }
            long delayValue = this.returnTime - origTime;
            if (xmitTime < rcvTime) {
                this.comments.add("Error: xmitTime < rcvTime");
            } else {
                long delta = xmitTime - rcvTime;
                if (delta <= delayValue) {
                    delayValue -= delta;
                } else if (delta - delayValue != 1) {
                    this.comments.add("Warning: processing time > total network time");
                } else if (delayValue != 0) {
                    this.comments.add("Info: processing time > total network time by 1 ms -> assume zero delay");
                    delayValue = 0;
                }
            }
            this.delay = Long.valueOf(delayValue);
            if (origTime > this.returnTime) {
                this.comments.add("Error: OrigTime > DestRcvTime");
            }
            this.offset = Long.valueOf(((rcvTime - origTime) + (xmitTime - this.returnTime)) / 2);
        }
    }

    public List<String> getComments() {
        return this.comments;
    }

    public Long getDelay() {
        return this.delay;
    }

    public Long getOffset() {
        return this.offset;
    }

    public NtpV3Packet getMessage() {
        return this.message;
    }

    public InetAddress getAddress() {
        DatagramPacket pkt = this.message.getDatagramPacket();
        if (pkt == null) {
            return null;
        }
        return pkt.getAddress();
    }

    public long getReturnTime() {
        return this.returnTime;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TimeInfo other = (TimeInfo) obj;
        if (this.returnTime == other.returnTime && this.message.equals(other.message)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = (int) this.returnTime;
        return (result * 31) + this.message.hashCode();
    }
}
