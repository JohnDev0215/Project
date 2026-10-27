package com.amazon.device.crashmanager.rtla;

import com.amazon.device.crashmanager.CrashDetailsCollectable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class RtlaCrashDetailsCollectable implements CrashDetailsCollectable {
    static final String DEFAULT_SESSION_ID = "000-0000000-0000000";
    private static final String EMPTY_STRING = "";
    static final String RTLA_PAGE_TYPE_KEY = "RtlaPageType";
    static final String RTLA_SESSION_ID_KEY = "RtlaSessionId";
    static final String RTLA_SUBPAGE_TYPE_KEY = "RtlaSubPageType";
    static final String RTLA_USER_AGENT_KEY = "RtlaUserAgent";
    static final String RTLA_WEB_LAB_KEY = "RtlaWebLabs";
    private final RtlaCrashDetails mRtlaCrashDetails;

    private String defaultOnNull(String str, String str2) {
        return str == null ? str2 : str;
    }

    public RtlaCrashDetailsCollectable(RtlaCrashDetails rtlaCrashDetails) {
        validateCrashDetails(rtlaCrashDetails);
        this.mRtlaCrashDetails = rtlaCrashDetails;
    }

    @Override // com.amazon.device.crashmanager.CrashDetailsCollectable
    public Map<String, String> collect(Throwable th) {
        String sessionId = this.mRtlaCrashDetails.getSessionId();
        String userAgent = this.mRtlaCrashDetails.getUserAgent();
        String pageType = this.mRtlaCrashDetails.getPageType();
        String subPageType = this.mRtlaCrashDetails.getSubPageType();
        LinkedList linkedList = new LinkedList();
        if (this.mRtlaCrashDetails.getActiveWebLabs() != null) {
            linkedList.addAll(this.mRtlaCrashDetails.getActiveWebLabs());
        }
        HashMap map = new HashMap();
        map.put(RTLA_SESSION_ID_KEY, defaultOnNull(sessionId, DEFAULT_SESSION_ID));
        if (userAgent != null) {
            map.put(RTLA_USER_AGENT_KEY, userAgent);
        }
        if (pageType != null) {
            map.put(RTLA_PAGE_TYPE_KEY, pageType);
        }
        if (subPageType != null) {
            map.put(RTLA_SUBPAGE_TYPE_KEY, subPageType);
        }
        if (linkedList.size() > 0) {
            map.put(RTLA_WEB_LAB_KEY, formatWebLabs(linkedList));
        }
        return map;
    }

    private void validateCrashDetails(RtlaCrashDetails rtlaCrashDetails) {
        if (rtlaCrashDetails == null) {
            throw new IllegalArgumentException("RtlaCrashDetails must not be null.");
        }
    }

    private String formatWebLabs(List<String> list) {
        if (list == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
