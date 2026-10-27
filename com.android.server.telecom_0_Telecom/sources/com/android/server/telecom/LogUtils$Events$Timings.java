package com.android.server.telecom;

import android.telecom.Logging.EventManager;

/* JADX INFO: loaded from: classes.dex */
public class LogUtils$Events$Timings {
    private static final EventManager.TimedEventPair[] sTimedEvents = {new EventManager.TimedEventPair("REQUEST_ACCEPT", "SET_ACTIVE", "accept"), new EventManager.TimedEventPair("REQUEST_REJECT", "SET_DISCONNECTED", "reject"), new EventManager.TimedEventPair("REQUEST_DISCONNECT", "SET_DISCONNECTED", "disconnect"), new EventManager.TimedEventPair("REQUEST_HOLD", "SET_HOLD", "hold"), new EventManager.TimedEventPair("REQUEST_UNHOLD", "SET_ACTIVE", "unhold"), new EventManager.TimedEventPair("START_CONNECTION", "SET_DIALING", "outgoing_time_to_dialing"), new EventManager.TimedEventPair("BIND_CS", "CS_BOUND", "bind_cs"), new EventManager.TimedEventPair("SCREENING_SENT", "SCREENING_COMPLETED", "screening_completed"), new EventManager.TimedEventPair("DIRECT_TO_VM_INITIATED", "DIRECT_TO_VM_FINISHED", "direct_to_vm_finished"), new EventManager.TimedEventPair("BLOCK_CHECK_INITIATED", "BLOCK_CHECK_FINISHED", "block_check_finished"), new EventManager.TimedEventPair("FILTERING_INITIATED", "FILTERING_COMPLETED", "filtering_completed"), new EventManager.TimedEventPair("FILTERING_INITIATED", "FILTERING_TIMED_OUT", "filtering_timed_out", 6000), new EventManager.TimedEventPair("START_CONNECTION", "REQUEST_DISCONNECT", "start_connection_to_request_disconnect")};
}
