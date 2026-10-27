package org.apache.commons.net.ntp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TimeInfo {
    private List<String> comments;
    private Long delayMillis;
    private boolean detailsComputed;
    private final NtpV3Packet message;
    private Long offsetMillis;
    private final long returnTimeMillis;

    public TimeInfo(NtpV3Packet message, long returnTimeMillis) {
        this(message, returnTimeMillis, null, true);
    }

    public TimeInfo(NtpV3Packet message, long returnTimeMillis, List<String> comments) {
        this(message, returnTimeMillis, comments, true);
    }

    public TimeInfo(NtpV3Packet msgPacket, long returnTimeMillis, boolean doComputeDetails) {
        this(msgPacket, returnTimeMillis, null, doComputeDetails);
    }

    public TimeInfo(NtpV3Packet message, long returnTimeMillis, List<String> comments, boolean doComputeDetails) {
        if (message == null) {
            throw new IllegalArgumentException("message cannot be null");
        }
        this.returnTimeMillis = returnTimeMillis;
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
            long origTimeMillis = origNtpTime.getTime();
            TimeStamp rcvNtpTime = this.message.getReceiveTimeStamp();
            long rcvTimeMillis = rcvNtpTime.getTime();
            TimeStamp xmitNtpTime = this.message.getTransmitTimeStamp();
            long xmitTimeMillis = xmitNtpTime.getTime();
            if (origNtpTime.ntpValue() == 0) {
                if (xmitNtpTime.ntpValue() != 0) {
                    this.offsetMillis = Long.valueOf(xmitTimeMillis - this.returnTimeMillis);
                    this.comments.add("Error: zero orig time -- cannot compute delay");
                    return;
                } else {
                    this.comments.add("Error: zero orig time -- cannot compute delay/offset");
                    return;
                }
            }
            if (rcvNtpTime.ntpValue() == 0 || xmitNtpTime.ntpValue() == 0) {
                this.comments.add("Warning: zero rcvNtpTime or xmitNtpTime");
                long j = this.returnTimeMillis;
                if (origTimeMillis <= j) {
                    this.delayMillis = Long.valueOf(j - origTimeMillis);
                } else {
                    this.comments.add("Error: OrigTime > DestRcvTime");
                }
                if (rcvNtpTime.ntpValue() != 0) {
                    this.offsetMillis = Long.valueOf(rcvTimeMillis - origTimeMillis);
                    return;
                } else {
                    if (xmitNtpTime.ntpValue() != 0) {
                        this.offsetMillis = Long.valueOf(xmitTimeMillis - this.returnTimeMillis);
                        return;
                    }
                    return;
                }
            }
            long delayValueMillis = this.returnTimeMillis - origTimeMillis;
            if (xmitTimeMillis < rcvTimeMillis) {
                this.comments.add("Error: xmitTime < rcvTime");
            } else {
                long deltaMillis = xmitTimeMillis - rcvTimeMillis;
                if (deltaMillis <= delayValueMillis) {
                    delayValueMillis -= deltaMillis;
                } else if (deltaMillis - delayValueMillis != 1) {
                    this.comments.add("Warning: processing time > total network time");
                } else if (delayValueMillis != 0) {
                    this.comments.add("Info: processing time > total network time by 1 ms -> assume zero delay");
                    delayValueMillis = 0;
                }
            }
            this.delayMillis = Long.valueOf(delayValueMillis);
            if (origTimeMillis > this.returnTimeMillis) {
                this.comments.add("Error: OrigTime > DestRcvTime");
            }
            this.offsetMillis = Long.valueOf(((rcvTimeMillis - origTimeMillis) + (xmitTimeMillis - this.returnTimeMillis)) / 2);
        }
    }

    public List<String> getComments() {
        return this.comments;
    }

    public Long getDelay() {
        return this.delayMillis;
    }

    public Long getOffset() {
        return this.offsetMillis;
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
        return this.returnTimeMillis;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TimeInfo other = (TimeInfo) obj;
        if (this.returnTimeMillis == other.returnTimeMillis && this.message.equals(other.message)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = (int) this.returnTimeMillis;
        return (result * 31) + this.message.hashCode();
    }
}
