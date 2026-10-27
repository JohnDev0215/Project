package com.android.server.telecom;

import android.content.Context;
import android.os.SystemProperties;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import android.telecom.Log;
import android.telecom.Logging.EventManager;
import android.telecom.ParcelableCallAnalytics;
import android.telecom.TelecomAnalytics;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Base64;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.IndentingPrintWriter;
import com.android.server.telecom.Analytics;
import com.android.server.telecom.nano.TelecomLogClass$CallLog;
import com.android.server.telecom.nano.TelecomLogClass$Event;
import com.android.server.telecom.nano.TelecomLogClass$EventTimingEntry;
import com.android.server.telecom.nano.TelecomLogClass$InCallServiceInfo;
import com.android.server.telecom.nano.TelecomLogClass$LogSessionTiming;
import com.android.server.telecom.nano.TelecomLogClass$TelecomLog;
import com.android.server.telecom.nano.TelecomLogClass$VideoEvent;
import com.google.protobuf.nano.MessageNano;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class Analytics {
    private static final LinkedList<String> sActiveCallIds;
    private static final Map<String, CallInfoImpl> sCallIdToInfo;
    private static final LinkedBlockingDeque<Long> sDumpTimes;
    private static final Object sLock;
    private static final List<TelecomAnalytics.SessionTiming> sSessionTimings;
    public static final Map<String, Integer> sLogEventToAnalyticsEvent = new HashMap<String, Integer>() { // from class: com.android.server.telecom.Analytics.1
        {
            put("SET_SELECT_PHONE_ACCOUNT", 0);
            put("REQUEST_HOLD", 400);
            put("REQUEST_UNHOLD", 401);
            put("SWAP", 405);
            put("SKIP_RINGING", 200);
            put("CONF_WITH", 300);
            put("CONF_SPLIT", 301);
            put("SET_PARENT", 302);
            put("MUTE", 202);
            put("UNMUTE", 203);
            put("AUDIO_ROUTE_BT", 204);
            put("AUDIO_ROUTE_EARPIECE", 205);
            put("AUDIO_ROUTE_HEADSET", 206);
            put("AUDIO_ROUTE_SPEAKER", 207);
            put("SILENCE", 201);
            put("SCREENING_COMPLETED", 101);
            put("BLOCK_CHECK_FINISHED", 105);
            put("DIRECT_TO_VM_FINISHED", 103);
            put("REMOTELY_HELD", 402);
            put("REMOTELY_UNHELD", 403);
            put("PULL", 500);
            put("REQUEST_ACCEPT", 7);
            put("REQUEST_REJECT", 8);
            put("SET_ACTIVE", 1);
            put("SET_DISCONNECTED", 2);
            put("SET_HOLD", 404);
            put("SET_DIALING", 4);
            put("START_CONNECTION", 3);
            put("BIND_CS", 5);
            put("CS_BOUND", 6);
            put("SCREENING_SENT", 100);
            put("DIRECT_TO_VM_INITIATED", 102);
            put("BLOCK_CHECK_INITIATED", 104);
            put("FILTERING_INITIATED", 106);
            put("FILTERING_COMPLETED", 107);
            put("FILTERING_TIMED_OUT", 108);
        }
    };
    public static final Map<String, Integer> sLogSessionToSessionId = new HashMap<String, Integer>() { // from class: com.android.server.telecom.Analytics.2
        {
            put("ICA.aC", 1);
            put("ICA.rC", 2);
            put("ICA.dC", 3);
            put("ICA.hC", 4);
            put("ICA.uC", 5);
            put("ICA.m", 6);
            put("ICA.sAR", 7);
            put("ICA.c", 8);
            put("CSW.hCCC", 100);
            put("CSW.sA", 101);
            put("CSW.sR", 102);
            put("CSW.sD", 103);
            put("CSW.sDc", 104);
            put("CSW.sOH", 105);
            put("CSW.rC", 106);
            put("CSW.sIC", 107);
            put("CSW.aCC", 108);
        }
    };
    public static final Map<String, Integer> sLogEventTimingToAnalyticsEventTiming = new HashMap<String, Integer>() { // from class: com.android.server.telecom.Analytics.3
        {
            put("accept", 0);
            put("reject", 1);
            put("disconnect", 2);
            put("hold", 3);
            put("unhold", 4);
            put("outgoing_time_to_dialing", 5);
            put("bind_cs", 6);
            put("screening_completed", 7);
            put("direct_to_vm_finished", 8);
            put("block_check_finished", 9);
            put("filtering_completed", 10);
            put("filtering_timed_out", 11);
            put("start_connection_to_request_disconnect", 12);
        }
    };
    public static final Map<Integer, String> sSessionIdToLogSession = new HashMap();

    public static class CallInfo {
        public void addCallProperties(int i) {
        }

        public void addCallTechnology(int i) {
        }

        public void addInCallService(String str, int i, long j, boolean z) {
        }

        public void addVideoEvent(int i, int i2) {
        }

        public void setCallConnectionService(String str) {
        }

        public void setCallDisconnectCause(DisconnectCause disconnectCause) {
        }

        public void setCallEndTime(long j) {
        }

        public void setCallEvents(EventManager.EventRecord eventRecord) {
        }

        public void setCallIsAdditional(boolean z) {
        }

        public void setCallIsEmergency(boolean z) {
        }

        public void setCallIsInterrupted(boolean z) {
        }

        public void setCallIsVideo(boolean z) {
        }

        public void setCallSource(int i) {
        }

        public void setCallStartTime(long j) {
        }

        public void setCreatedFromExistingConnection(boolean z) {
        }
    }

    static {
        for (Map.Entry<String, Integer> entry : sLogSessionToSessionId.entrySet()) {
            sSessionIdToLogSession.put(entry.getValue(), entry.getKey());
        }
        sLock = new Object();
        sDumpTimes = new LinkedBlockingDeque<>(100);
        sCallIdToInfo = new HashMap();
        sActiveCallIds = new LinkedList<>();
        sSessionTimings = new LinkedList();
    }

    @VisibleForTesting
    public static class CallInfoImpl extends CallInfo {
        public int callDirection;
        public EventManager.EventRecord callEvents;
        public String callId;
        public int callProperties;
        public int callSource;
        public int callTechnologies;
        public DisconnectCause callTerminationReason;
        public String connectionService;
        public boolean createdFromExistingConnection;
        public long endTime;
        public List<TelecomLogClass$InCallServiceInfo> inCallServiceInfos;
        public boolean isAdditionalCall;
        public boolean isEmergency;
        public boolean isInterrupted;
        public boolean isVideo;
        private long mTimeOfLastVideoEvent;
        public long startTime;
        public List<TelecomLogClass$VideoEvent> videoEvents;

        CallInfoImpl(String str, int i) {
            this.isAdditionalCall = false;
            this.isInterrupted = false;
            this.createdFromExistingConnection = false;
            this.isEmergency = false;
            this.isVideo = false;
            this.callProperties = 0;
            this.callSource = 0;
            this.mTimeOfLastVideoEvent = -1L;
            this.callId = str;
            this.startTime = 0L;
            this.endTime = 0L;
            this.callDirection = i;
            this.callTechnologies = 0;
            this.connectionService = "";
            this.videoEvents = new LinkedList();
            this.inCallServiceInfos = new LinkedList();
        }

        CallInfoImpl(CallInfoImpl callInfoImpl) {
            this.isAdditionalCall = false;
            this.isInterrupted = false;
            this.createdFromExistingConnection = false;
            this.isEmergency = false;
            this.isVideo = false;
            this.callProperties = 0;
            this.callSource = 0;
            this.mTimeOfLastVideoEvent = -1L;
            this.callId = callInfoImpl.callId;
            this.startTime = callInfoImpl.startTime;
            this.endTime = callInfoImpl.endTime;
            this.callDirection = callInfoImpl.callDirection;
            this.isAdditionalCall = callInfoImpl.isAdditionalCall;
            this.isInterrupted = callInfoImpl.isInterrupted;
            this.callTechnologies = callInfoImpl.callTechnologies;
            this.createdFromExistingConnection = callInfoImpl.createdFromExistingConnection;
            this.connectionService = callInfoImpl.connectionService;
            this.isEmergency = callInfoImpl.isEmergency;
            this.callEvents = callInfoImpl.callEvents;
            this.isVideo = callInfoImpl.isVideo;
            this.videoEvents = callInfoImpl.videoEvents;
            this.callProperties = callInfoImpl.callProperties;
            this.callSource = callInfoImpl.callSource;
            if (callInfoImpl.callTerminationReason != null) {
                this.callTerminationReason = new DisconnectCause(callInfoImpl.callTerminationReason.getCode(), callInfoImpl.callTerminationReason.getLabel(), callInfoImpl.callTerminationReason.getDescription(), callInfoImpl.callTerminationReason.getReason(), callInfoImpl.callTerminationReason.getTone());
            } else {
                this.callTerminationReason = null;
            }
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void setCallStartTime(long j) {
            Log.d("TelecomAnalytics", "setting startTime for call " + this.callId + " to " + j, new Object[0]);
            this.startTime = j;
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void setCallEndTime(long j) {
            Log.d("TelecomAnalytics", "setting endTime for call " + this.callId + " to " + j, new Object[0]);
            this.endTime = j;
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void setCallIsAdditional(boolean z) {
            Log.d("TelecomAnalytics", "setting isAdditional for call " + this.callId + " to " + z, new Object[0]);
            this.isAdditionalCall = z;
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void setCallIsInterrupted(boolean z) {
            Log.d("TelecomAnalytics", "setting isInterrupted for call " + this.callId + " to " + z, new Object[0]);
            this.isInterrupted = z;
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void addCallTechnology(int i) {
            Log.d("TelecomAnalytics", "adding callTechnology for call " + this.callId + ": " + i, new Object[0]);
            this.callTechnologies = i | this.callTechnologies;
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void setCallIsEmergency(boolean z) {
            Log.d("TelecomAnalytics", "setting call as emergency: " + z, new Object[0]);
            this.isEmergency = z;
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void setCallDisconnectCause(DisconnectCause disconnectCause) {
            Log.d("TelecomAnalytics", "setting disconnectCause for call " + this.callId + " to " + disconnectCause, new Object[0]);
            this.callTerminationReason = disconnectCause;
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void setCreatedFromExistingConnection(boolean z) {
            Log.d("TelecomAnalytics", "setting createdFromExistingConnection for call " + this.callId + " to " + z, new Object[0]);
            this.createdFromExistingConnection = z;
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void setCallConnectionService(String str) {
            Log.d("TelecomAnalytics", "setting connection service for call " + this.callId + ": " + str, new Object[0]);
            this.connectionService = str;
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void setCallEvents(EventManager.EventRecord eventRecord) {
            this.callEvents = eventRecord;
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void setCallIsVideo(boolean z) {
            this.isVideo = z;
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void addVideoEvent(int i, int i2) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = this.mTimeOfLastVideoEvent;
            long jRoundToOneSigFig = j < 0 ? -1L : Analytics.roundToOneSigFig(jCurrentTimeMillis - j);
            this.mTimeOfLastVideoEvent = jCurrentTimeMillis;
            List<TelecomLogClass$VideoEvent> list = this.videoEvents;
            TelecomLogClass$VideoEvent telecomLogClass$VideoEvent = new TelecomLogClass$VideoEvent();
            telecomLogClass$VideoEvent.setEventName(i);
            telecomLogClass$VideoEvent.setTimeSinceLastEventMillis(jRoundToOneSigFig);
            telecomLogClass$VideoEvent.setVideoState(i2);
            list.add(telecomLogClass$VideoEvent);
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void addInCallService(String str, int i, long j, boolean z) {
            List<TelecomLogClass$InCallServiceInfo> list = this.inCallServiceInfos;
            TelecomLogClass$InCallServiceInfo telecomLogClass$InCallServiceInfo = new TelecomLogClass$InCallServiceInfo();
            telecomLogClass$InCallServiceInfo.setInCallServiceName(str);
            telecomLogClass$InCallServiceInfo.setInCallServiceType(i);
            telecomLogClass$InCallServiceInfo.setBoundDurationMillis(j);
            telecomLogClass$InCallServiceInfo.setIsNullBinding(z);
            list.add(telecomLogClass$InCallServiceInfo);
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void addCallProperties(int i) {
            this.callProperties = i | this.callProperties;
        }

        @Override // com.android.server.telecom.Analytics.CallInfo
        public void setCallSource(int i) {
            this.callSource = i;
        }

        public String toString() {
            return "{\n    startTime: " + this.startTime + "\n    endTime: " + this.endTime + "\n    direction: " + getCallDirectionString() + "\n    isAdditionalCall: " + this.isAdditionalCall + "\n    isInterrupted: " + this.isInterrupted + "\n    isEmergency: " + this.isEmergency + "\n    callTechnologies: " + getCallTechnologiesAsString() + "\n    callTerminationReason: " + getCallDisconnectReasonString() + "\n    connectionService: " + this.connectionService + "\n    isVideoCall: " + this.isVideo + "\n    inCallServices: " + getInCallServicesString() + "\n    callProperties: " + Connection.propertiesToStringShort(this.callProperties) + "\n    callSource: " + getCallSourceString() + "\n}\n";
        }

        public ParcelableCallAnalytics toParcelableAnalytics() {
            TelecomLogClass$CallLog proto = toProto();
            ParcelableCallAnalytics parcelableCallAnalytics = new ParcelableCallAnalytics(proto.getStartTime5Min(), proto.getCallDurationMillis(), proto.getType(), proto.getIsAdditionalCall(), proto.getIsInterrupted(), proto.getCallTechnologies(), proto.getCallTerminationCode(), proto.getIsEmergencyCall(), proto.connectionService[0], proto.getIsCreatedFromExistingConnection(), (List) Arrays.stream(proto.callEvents).map(new Function() { // from class: com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$j_8PZg7h1A0XPaEwP42P7jyV4SA
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Analytics.CallInfoImpl.lambda$toParcelableAnalytics$0((TelecomLogClass$Event) obj);
                }
            }).collect(Collectors.toList()), (List) Arrays.stream(proto.callTimings).map(new Function() { // from class: com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$NdA3x9IrlSA9W29N4vx9aSfsgpQ
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Analytics.CallInfoImpl.lambda$toParcelableAnalytics$1((TelecomLogClass$EventTimingEntry) obj);
                }
            }).collect(Collectors.toList()));
            parcelableCallAnalytics.setIsVideoCall(proto.getIsVideoCall());
            parcelableCallAnalytics.setVideoEvents((List) Arrays.stream(proto.videoEvents).map(new Function() { // from class: com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$gJOMnB9nbSBF_kw59Z3LgN96BsM
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Analytics.CallInfoImpl.lambda$toParcelableAnalytics$2((TelecomLogClass$VideoEvent) obj);
                }
            }).collect(Collectors.toList()));
            parcelableCallAnalytics.setCallSource(proto.getCallSource());
            return parcelableCallAnalytics;
        }

        static /* synthetic */ ParcelableCallAnalytics.AnalyticsEvent lambda$toParcelableAnalytics$0(TelecomLogClass$Event telecomLogClass$Event) {
            return new ParcelableCallAnalytics.AnalyticsEvent(telecomLogClass$Event.getEventName(), telecomLogClass$Event.getTimeSinceLastEventMillis());
        }

        static /* synthetic */ ParcelableCallAnalytics.EventTiming lambda$toParcelableAnalytics$1(TelecomLogClass$EventTimingEntry telecomLogClass$EventTimingEntry) {
            return new ParcelableCallAnalytics.EventTiming(telecomLogClass$EventTimingEntry.getTimingName(), telecomLogClass$EventTimingEntry.getTimeMillis());
        }

        static /* synthetic */ ParcelableCallAnalytics.VideoEvent lambda$toParcelableAnalytics$2(TelecomLogClass$VideoEvent telecomLogClass$VideoEvent) {
            return new ParcelableCallAnalytics.VideoEvent(telecomLogClass$VideoEvent.getEventName(), telecomLogClass$VideoEvent.getTimeSinceLastEventMillis(), telecomLogClass$VideoEvent.getVideoState());
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.android.server.telecom.nano.TelecomLogClass$CallLog toProto() {
            /*
                r10 = this;
                com.android.server.telecom.nano.TelecomLogClass$CallLog r0 = new com.android.server.telecom.nano.TelecomLogClass$CallLog
                r0.<init>()
                long r1 = r10.startTime
                r3 = 300000(0x493e0, double:1.482197E-318)
                long r3 = r1 % r3
                long r1 = r1 - r3
                r0.setStartTime5Min(r1)
                long r1 = r10.endTime
                r3 = 0
                int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                if (r5 == 0) goto L21
                long r5 = r10.startTime
                int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r7 != 0) goto L1f
                goto L21
            L1f:
                long r1 = r1 - r5
                goto L22
            L21:
                r1 = r3
            L22:
                r5 = 1000(0x3e8, double:4.94E-321)
                long r7 = r1 % r5
                int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
                if (r9 != 0) goto L2b
                goto L2d
            L2b:
                long r3 = r5 - r7
            L2d:
                long r1 = r1 + r3
                r0.setCallDurationMillis(r1)
                int r1 = r10.callDirection
                r0.setType(r1)
                boolean r1 = r10.isAdditionalCall
                r0.setIsAdditionalCall(r1)
                boolean r1 = r10.isInterrupted
                r0.setIsInterrupted(r1)
                int r1 = r10.callTechnologies
                r0.setCallTechnologies(r1)
                android.telecom.DisconnectCause r1 = r10.callTerminationReason
                if (r1 != 0) goto L4b
                r1 = -1
                goto L4f
            L4b:
                int r1 = r1.getCode()
            L4f:
                r0.setCallTerminationCode(r1)
                boolean r1 = r10.isEmergency
                r0.setIsEmergencyCall(r1)
                boolean r1 = r10.createdFromExistingConnection
                r0.setIsCreatedFromExistingConnection(r1)
                boolean r1 = r10.isEmergency
                r0.setIsEmergencyCall(r1)
                boolean r1 = r10.isVideo
                r0.setIsVideoCall(r1)
                int r1 = r10.callProperties
                r0.setConnectionProperties(r1)
                int r1 = r10.callSource
                r0.setCallSource(r1)
                r1 = 1
                java.lang.String[] r1 = new java.lang.String[r1]
                r2 = 0
                java.lang.String r3 = r10.connectionService
                r1[r2] = r3
                r0.connectionService = r1
                android.telecom.Logging.EventManager$EventRecord r1 = r10.callEvents
                if (r1 == 0) goto La2
                java.util.List r1 = r1.getEvents()
                com.android.server.telecom.nano.TelecomLogClass$Event[] r1 = com.android.server.telecom.Analytics.access$000(r1)
                r0.callEvents = r1
                android.telecom.Logging.EventManager$EventRecord r1 = r10.callEvents
                java.util.List r1 = r1.extractEventTimings()
                java.util.stream.Stream r1 = r1.stream()
                com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$RixzQ5mx2t0oYC_F0z16ifjuUeg r2 = new java.util.function.Function() { // from class: com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$RixzQ5mx2t0oYC_F0z16ifjuUeg
                    static {
                        /*
                            com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$RixzQ5mx2t0oYC_F0z16ifjuUeg r0 = new com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$RixzQ5mx2t0oYC_F0z16ifjuUeg
                            r0.<init>()
                            
                            // error: 0x0005: SPUT (r0 I:com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$RixzQ5mx2t0oYC_F0z16ifjuUeg) com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$RixzQ5mx2t0oYC_F0z16ifjuUeg.INSTANCE com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$RixzQ5mx2t0oYC_F0z16ifjuUeg
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.$$Lambda$Analytics$CallInfoImpl$RixzQ5mx2t0oYC_F0z16ifjuUeg.<clinit>():void");
                    }

                    {
                        /*
                            r0 = this;
                            r0.<init>()
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.$$Lambda$Analytics$CallInfoImpl$RixzQ5mx2t0oYC_F0z16ifjuUeg.<init>():void");
                    }

                    @Override // java.util.function.Function
                    public final java.lang.Object apply(java.lang.Object r1) {
                        /*
                            r0 = this;
                            android.telecom.Logging.EventManager$EventRecord$EventTiming r1 = (android.telecom.Logging.EventManager.EventRecord.EventTiming) r1
                            com.android.server.telecom.nano.TelecomLogClass$EventTimingEntry r0 = com.android.server.telecom.Analytics.CallInfoImpl.lambda$toProto$3(r1)
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.$$Lambda$Analytics$CallInfoImpl$RixzQ5mx2t0oYC_F0z16ifjuUeg.apply(java.lang.Object):java.lang.Object");
                    }
                }
                java.util.stream.Stream r1 = r1.map(r2)
                com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$4xjwZgzLsZffCcLjTeweOcjGl04 r2 = new java.util.function.IntFunction() { // from class: com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$4xjwZgzLsZffCcLjTeweOcjGl04
                    static {
                        /*
                            com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$4xjwZgzLsZffCcLjTeweOcjGl04 r0 = new com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$4xjwZgzLsZffCcLjTeweOcjGl04
                            r0.<init>()
                            
                            // error: 0x0005: SPUT (r0 I:com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$4xjwZgzLsZffCcLjTeweOcjGl04) com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$4xjwZgzLsZffCcLjTeweOcjGl04.INSTANCE com.android.server.telecom.-$$Lambda$Analytics$CallInfoImpl$4xjwZgzLsZffCcLjTeweOcjGl04
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.$$Lambda$Analytics$CallInfoImpl$4xjwZgzLsZffCcLjTeweOcjGl04.<clinit>():void");
                    }

                    {
                        /*
                            r0 = this;
                            r0.<init>()
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.$$Lambda$Analytics$CallInfoImpl$4xjwZgzLsZffCcLjTeweOcjGl04.<init>():void");
                    }

                    @Override // java.util.function.IntFunction
                    public final java.lang.Object apply(int r1) {
                        /*
                            r0 = this;
                            com.android.server.telecom.nano.TelecomLogClass$EventTimingEntry[] r0 = com.android.server.telecom.Analytics.CallInfoImpl.lambda$toProto$4(r1)
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.$$Lambda$Analytics$CallInfoImpl$4xjwZgzLsZffCcLjTeweOcjGl04.apply(int):java.lang.Object");
                    }
                }
                java.lang.Object[] r1 = r1.toArray(r2)
                com.android.server.telecom.nano.TelecomLogClass$EventTimingEntry[] r1 = (com.android.server.telecom.nano.TelecomLogClass$EventTimingEntry[]) r1
                r0.callTimings = r1
            La2:
                java.util.List<com.android.server.telecom.nano.TelecomLogClass$VideoEvent> r1 = r10.videoEvents
                int r2 = r1.size()
                com.android.server.telecom.nano.TelecomLogClass$VideoEvent[] r2 = new com.android.server.telecom.nano.TelecomLogClass$VideoEvent[r2]
                java.lang.Object[] r1 = r1.toArray(r2)
                com.android.server.telecom.nano.TelecomLogClass$VideoEvent[] r1 = (com.android.server.telecom.nano.TelecomLogClass$VideoEvent[]) r1
                r0.videoEvents = r1
                java.util.List<com.android.server.telecom.nano.TelecomLogClass$InCallServiceInfo> r10 = r10.inCallServiceInfos
                int r1 = r10.size()
                com.android.server.telecom.nano.TelecomLogClass$InCallServiceInfo[] r1 = new com.android.server.telecom.nano.TelecomLogClass$InCallServiceInfo[r1]
                java.lang.Object[] r10 = r10.toArray(r1)
                com.android.server.telecom.nano.TelecomLogClass$InCallServiceInfo[] r10 = (com.android.server.telecom.nano.TelecomLogClass$InCallServiceInfo[]) r10
                r0.inCallServices = r10
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.Analytics.CallInfoImpl.toProto():com.android.server.telecom.nano.TelecomLogClass$CallLog");
        }

        static /* synthetic */ TelecomLogClass$EventTimingEntry[] lambda$toProto$4(int i) {
            return new TelecomLogClass$EventTimingEntry[i];
        }

        private String getCallDirectionString() {
            int i = this.callDirection;
            return i != 1 ? i != 2 ? "UNKNOWN" : "OUTGOING" : "INCOMING";
        }

        private String getCallTechnologiesAsString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            if ((this.callTechnologies & 1) != 0) {
                sb.append("CDMA ");
            }
            if ((this.callTechnologies & 2) != 0) {
                sb.append("GSM ");
            }
            if ((this.callTechnologies & 8) != 0) {
                sb.append("SIP ");
            }
            if ((this.callTechnologies & 4) != 0) {
                sb.append("IMS ");
            }
            if ((this.callTechnologies & 16) != 0) {
                sb.append("THIRD_PARTY ");
            }
            sb.append(']');
            return sb.toString();
        }

        private String getCallDisconnectReasonString() {
            DisconnectCause disconnectCause = this.callTerminationReason;
            return disconnectCause != null ? disconnectCause.toString() : "NOT SET";
        }

        private String getInCallServicesString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[\n");
            for (TelecomLogClass$InCallServiceInfo telecomLogClass$InCallServiceInfo : this.inCallServiceInfos) {
                sb.append("    ");
                sb.append("name: ");
                sb.append(telecomLogClass$InCallServiceInfo.getInCallServiceName());
                sb.append(" type: ");
                sb.append(telecomLogClass$InCallServiceInfo.getInCallServiceType());
                sb.append(" is crashed: ");
                sb.append(telecomLogClass$InCallServiceInfo.getIsNullBinding());
                sb.append(" service last time in ms: ");
                sb.append(telecomLogClass$InCallServiceInfo.getBoundDurationMillis());
                sb.append("\n");
            }
            sb.append("]");
            return sb.toString();
        }

        private String getCallSourceString() {
            int i = this.callSource;
            return i != 1 ? i != 2 ? "UNSPECIFIED" : "EMERGENCY_SHORTCUT" : "EMERGENCY_DIALPAD";
        }
    }

    public static void addSessionTiming(String str, long j) {
        if (sLogSessionToSessionId.containsKey(str)) {
            synchronized (sLock) {
                sSessionTimings.add(new TelecomAnalytics.SessionTiming(sLogSessionToSessionId.get(str).intValue(), j));
            }
        }
    }

    public static CallInfo initiateCallAnalytics(String str, int i) {
        Log.d("TelecomAnalytics", "Starting analytics for call " + str, new Object[0]);
        CallInfoImpl callInfoImpl = new CallInfoImpl(str, i);
        synchronized (sLock) {
            while (sActiveCallIds.size() >= 100) {
                sCallIdToInfo.remove(sActiveCallIds.remove());
            }
            sCallIdToInfo.put(str, callInfoImpl);
            sActiveCallIds.add(str);
        }
        return callInfoImpl;
    }

    public static TelecomAnalytics dumpToParcelableAnalytics() {
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        synchronized (sLock) {
            linkedList.addAll((Collection) sCallIdToInfo.values().stream().map(new Function() { // from class: com.android.server.telecom.-$$Lambda$svN1dnRADZAlDFHaT-KBXMbYV40
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Analytics.CallInfoImpl) obj).toParcelableAnalytics();
                }
            }).collect(Collectors.toList()));
            linkedList2.addAll(sSessionTimings);
            sCallIdToInfo.clear();
            sSessionTimings.clear();
        }
        return new TelecomAnalytics(linkedList2, linkedList);
    }

    public static void dumpToEncodedProto(Context context, PrintWriter printWriter, String[] strArr) {
        TelecomLogClass$TelecomLog telecomLogClass$TelecomLog = new TelecomLogClass$TelecomLog();
        synchronized (sLock) {
            noteDumpTime();
            telecomLogClass$TelecomLog.callLogs = (TelecomLogClass$CallLog[]) sCallIdToInfo.values().stream().map(new Function() { // from class: com.android.server.telecom.-$$Lambda$rKXCQZ1c9ZDb-VFMj_uZpQutQhA
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Analytics.CallInfoImpl) obj).toProto();
                }
            }).toArray(new IntFunction() { // from class: com.android.server.telecom.-$$Lambda$Analytics$-cIWcmxaUJ-zXVcjezrPY9siOP0
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return Analytics.lambda$dumpToEncodedProto$0(i);
                }
            });
            telecomLogClass$TelecomLog.sessionTimings = (TelecomLogClass$LogSessionTiming[]) sSessionTimings.stream().map(new Function() { // from class: com.android.server.telecom.-$$Lambda$Analytics$h80tE31J3k0g6GGoDqCFetvFL3I
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Analytics.lambda$dumpToEncodedProto$1((TelecomAnalytics.SessionTiming) obj);
                }
            }).toArray(new IntFunction() { // from class: com.android.server.telecom.-$$Lambda$Analytics$MLXu_0GeutcQA56ezOxzrtlUVbE
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return Analytics.lambda$dumpToEncodedProto$2(i);
                }
            });
            telecomLogClass$TelecomLog.setHardwareRevision(SystemProperties.get("ro.boot.revision", ""));
            telecomLogClass$TelecomLog.setCarrierId(getCarrierId(context));
            if (strArr.length > 1 && "clear".equals(strArr[1])) {
                sCallIdToInfo.clear();
                sSessionTimings.clear();
            }
        }
        printWriter.write(Base64.encodeToString(MessageNano.toByteArray(telecomLogClass$TelecomLog), 0));
    }

    static /* synthetic */ TelecomLogClass$CallLog[] lambda$dumpToEncodedProto$0(int i) {
        return new TelecomLogClass$CallLog[i];
    }

    static /* synthetic */ TelecomLogClass$LogSessionTiming lambda$dumpToEncodedProto$1(TelecomAnalytics.SessionTiming sessionTiming) {
        TelecomLogClass$LogSessionTiming telecomLogClass$LogSessionTiming = new TelecomLogClass$LogSessionTiming();
        telecomLogClass$LogSessionTiming.setSessionEntryPoint(sessionTiming.getKey().intValue());
        telecomLogClass$LogSessionTiming.setTimeMillis(sessionTiming.getTime());
        return telecomLogClass$LogSessionTiming;
    }

    static /* synthetic */ TelecomLogClass$LogSessionTiming[] lambda$dumpToEncodedProto$2(int i) {
        return new TelecomLogClass$LogSessionTiming[i];
    }

    private static int getCarrierId(Context context) {
        List<SubscriptionInfo> activeSubscriptionInfoList = ((SubscriptionManager) context.getSystemService(SubscriptionManager.class)).getActiveSubscriptionInfoList();
        if (activeSubscriptionInfoList == null) {
            return -1;
        }
        return ((Integer) activeSubscriptionInfoList.stream().max(Comparator.comparing(new Function() { // from class: com.android.server.telecom.-$$Lambda$Analytics$imKgoZAYPqoCvRSit8XHaQS-dpc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(Analytics.scoreSubscriptionInfo((SubscriptionInfo) obj));
            }
        })).map(new Function() { // from class: com.android.server.telecom.-$$Lambda$yQ2mp_90H4U-GTw-4Jz2P2-UQJI
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((SubscriptionInfo) obj).getCarrierId());
            }
        }).orElse(-1)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int scoreSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
        return (subscriptionInfo.getCarrierId() >= 0 ? 4 : 0) + (subscriptionInfo.isOpportunistic() ? 0 : 2) + (subscriptionInfo.getSimSlotIndex() == 0 ? 1 : 0);
    }

    public static void dump(final IndentingPrintWriter indentingPrintWriter) {
        synchronized (sLock) {
            final int i = 3;
            ArrayList<String> arrayList = new ArrayList(sCallIdToInfo.keySet());
            try {
                Collections.sort(arrayList, new Comparator() { // from class: com.android.server.telecom.-$$Lambda$Analytics$gFdvbU62A9UDQ2zYt2yzJRJAS4U
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return Analytics.lambda$dump$3(i, (String) obj, (String) obj2);
                    }
                });
            } catch (IllegalArgumentException unused) {
            }
            for (String str : arrayList) {
                indentingPrintWriter.printf("Call %s: ", new Object[]{str});
                indentingPrintWriter.println(sCallIdToInfo.get(str).toString());
            }
            TelecomAnalytics.SessionTiming.averageTimings(sSessionTimings).entrySet().stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$Analytics$Wu7Buz5BYI7hqMvw7Lpo1OHLQkA
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Analytics.sSessionIdToLogSession.containsKey(((Map.Entry) obj).getKey());
                }
            }).forEach(new Consumer() { // from class: com.android.server.telecom.-$$Lambda$Analytics$F5tHpvMBv3cB55ve-uCNcqhfYlI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Map.Entry entry = (Map.Entry) obj;
                    indentingPrintWriter.printf("%s: %.2f\n", new Object[]{Analytics.sSessionIdToLogSession.get(entry.getKey()), entry.getValue()});
                }
            });
            indentingPrintWriter.println("Hardware Version: " + SystemProperties.get("ro.boot.revision", ""));
            indentingPrintWriter.println("Past analytics dumps: ");
            indentingPrintWriter.increaseIndent();
            Iterator<Long> it = sDumpTimes.iterator();
            while (it.hasNext()) {
                indentingPrintWriter.println(Instant.ofEpochMilli(it.next().longValue()).atZone(ZoneOffset.UTC));
            }
            indentingPrintWriter.decreaseIndent();
        }
    }

    static /* synthetic */ int lambda$dump$3(int i, String str, String str2) {
        int iIntValue;
        int iIntValue2 = Integer.MAX_VALUE;
        try {
            iIntValue = Integer.valueOf(str.substring(i)).intValue();
        } catch (NumberFormatException unused) {
            iIntValue = Integer.MAX_VALUE;
        }
        try {
            iIntValue2 = Integer.valueOf(str2.substring(i)).intValue();
        } catch (NumberFormatException unused2) {
        }
        return iIntValue - iIntValue2;
    }

    public static void noteDumpTime() {
        if (sDumpTimes.remainingCapacity() == 0) {
            sDumpTimes.removeLast();
        }
        try {
            sDumpTimes.addFirst(Long.valueOf(System.currentTimeMillis()));
        } catch (IllegalStateException unused) {
            Log.w("TelecomAnalytics", "Failed to note dump time -- full", new Object[0]);
        }
    }

    @VisibleForTesting
    public static Map<String, CallInfoImpl> cloneData() {
        HashMap map;
        synchronized (sLock) {
            map = new HashMap(sCallIdToInfo.size());
            for (Map.Entry<String, CallInfoImpl> entry : sCallIdToInfo.entrySet()) {
                map.put(entry.getKey(), new CallInfoImpl(entry.getValue()));
            }
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TelecomLogClass$Event[] convertLogEventsToProtoEvents(List<EventManager.Event> list) {
        ArrayList arrayList = new ArrayList(list.size());
        long j = -1;
        for (EventManager.Event event : list) {
            if (sLogEventToAnalyticsEvent.containsKey(event.eventId)) {
                TelecomLogClass$Event telecomLogClass$Event = new TelecomLogClass$Event();
                telecomLogClass$Event.setEventName(sLogEventToAnalyticsEvent.get(event.eventId).intValue());
                telecomLogClass$Event.setTimeSinceLastEventMillis(roundToOneSigFig(j < 0 ? -1L : event.time - j));
                arrayList.add(telecomLogClass$Event);
                j = event.time;
            }
        }
        return (TelecomLogClass$Event[]) arrayList.toArray(new TelecomLogClass$Event[arrayList.size()]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TelecomLogClass$EventTimingEntry logEventTimingToProtoEventTiming(EventManager.EventRecord.EventTiming eventTiming) {
        int iIntValue = sLogEventTimingToAnalyticsEventTiming.containsKey(eventTiming.name) ? sLogEventTimingToAnalyticsEventTiming.get(eventTiming.name).intValue() : 999999;
        TelecomLogClass$EventTimingEntry telecomLogClass$EventTimingEntry = new TelecomLogClass$EventTimingEntry();
        telecomLogClass$EventTimingEntry.setTimingName(iIntValue);
        telecomLogClass$EventTimingEntry.setTimeMillis(eventTiming.time);
        return telecomLogClass$EventTimingEntry;
    }

    @VisibleForTesting
    public static long roundToOneSigFig(long j) {
        if (j == 0) {
            return j;
        }
        return (long) (Math.round(j / r0) * Math.pow(10.0d, (int) Math.floor(Math.log10(j < 0 ? -j : j))));
    }
}
