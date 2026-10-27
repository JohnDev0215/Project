package com.amazon.cirrus.libraryservice.v3;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class ReportEventRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.ReportEventRequest");
    private String clientVersion;
    private Integer eventCount;
    private String eventDescription;
    private String eventName;
    private String eventSeverity;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof ReportEventRequest)) {
            return 1;
        }
        ReportEventRequest reportEventRequest = (ReportEventRequest) cirrusBaseRequestV2;
        String eventDescription = getEventDescription();
        String eventDescription2 = reportEventRequest.getEventDescription();
        if (eventDescription != eventDescription2) {
            if (eventDescription == null) {
                return -1;
            }
            if (eventDescription2 == null) {
                return 1;
            }
            int iCompareTo = eventDescription.compareTo(eventDescription2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String clientVersion = getClientVersion();
        String clientVersion2 = reportEventRequest.getClientVersion();
        if (clientVersion != clientVersion2) {
            if (clientVersion == null) {
                return -1;
            }
            if (clientVersion2 == null) {
                return 1;
            }
            int iCompareTo2 = clientVersion.compareTo(clientVersion2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String eventName = getEventName();
        String eventName2 = reportEventRequest.getEventName();
        if (eventName != eventName2) {
            if (eventName == null) {
                return -1;
            }
            if (eventName2 == null) {
                return 1;
            }
            int iCompareTo3 = eventName.compareTo(eventName2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Integer eventCount = getEventCount();
        Integer eventCount2 = reportEventRequest.getEventCount();
        if (eventCount != eventCount2) {
            if (eventCount == null) {
                return -1;
            }
            if (eventCount2 == null) {
                return 1;
            }
            int iCompareTo4 = eventCount.compareTo(eventCount2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String eventSeverity = getEventSeverity();
        String eventSeverity2 = reportEventRequest.getEventSeverity();
        if (eventSeverity != eventSeverity2) {
            if (eventSeverity == null) {
                return -1;
            }
            if (eventSeverity2 == null) {
                return 1;
            }
            int iCompareTo5 = eventSeverity.compareTo(eventSeverity2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof ReportEventRequest)) {
            return false;
        }
        ReportEventRequest reportEventRequest = (ReportEventRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getEventDescription(), reportEventRequest.getEventDescription()) && internalEqualityCheck(getClientVersion(), reportEventRequest.getClientVersion()) && internalEqualityCheck(getEventName(), reportEventRequest.getEventName()) && internalEqualityCheck(getEventCount(), reportEventRequest.getEventCount()) && internalEqualityCheck(getEventSeverity(), reportEventRequest.getEventSeverity());
    }

    public String getClientVersion() {
        return this.clientVersion;
    }

    public Integer getEventCount() {
        return this.eventCount;
    }

    public String getEventDescription() {
        return this.eventDescription;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getEventSeverity() {
        return this.eventSeverity;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getEventDescription(), getClientVersion(), getEventName(), getEventCount(), getEventSeverity());
    }

    public void setClientVersion(String str) {
        this.clientVersion = str;
    }

    public void setEventCount(Integer num) {
        this.eventCount = num;
    }

    public void setEventDescription(String str) {
        this.eventDescription = str;
    }

    public void setEventName(String str) {
        this.eventName = str;
    }

    public void setEventSeverity(String str) {
        this.eventSeverity = str;
    }
}
