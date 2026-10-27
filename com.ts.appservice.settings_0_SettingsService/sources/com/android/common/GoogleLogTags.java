package com.android.common;

import android.util.EventLog;

/* JADX INFO: loaded from: classes.dex */
public class GoogleLogTags {
    public static final int C2DM = 204005;
    public static final int EXP_DET_SNET = 206003;
    public static final int GLS_ACCOUNT_SAVED = 205009;
    public static final int GLS_ACCOUNT_TRIED = 205008;
    public static final int GLS_AUTHENTICATE = 205010;
    public static final int GOOGLE_HTTP_REQUEST = 203002;
    public static final int GOOGLE_MAIL_SWITCH = 205011;
    public static final int GTALKSERVICE = 204001;
    public static final int GTALK_CONNECTION = 204002;
    public static final int GTALK_CONN_CLOSE = 204003;
    public static final int GTALK_HEARTBEAT_RESET = 204004;
    public static final int METRICS_HEARTBEAT = 208000;
    public static final int SETUP_COMPLETED = 205007;
    public static final int SETUP_IO_ERROR = 205003;
    public static final int SETUP_NO_DATA_NETWORK = 205006;
    public static final int SETUP_REQUIRED_CAPTCHA = 205002;
    public static final int SETUP_RETRIES_EXHAUSTED = 205005;
    public static final int SETUP_SERVER_ERROR = 205004;
    public static final int SETUP_SERVER_TIMEOUT = 205001;
    public static final int SNET = 206001;
    public static final int SYNC_DETAILS = 203001;
    public static final int SYSTEM_UPDATE = 201001;
    public static final int SYSTEM_UPDATE_USER = 201002;
    public static final int TRANSACTION_EVENT = 202901;
    public static final int VENDING_RECONSTRUCT = 202001;

    private GoogleLogTags() {
    }

    public static void writeSystemUpdate(int status, int downloadResult, long bytes, String url) {
        EventLog.writeEvent(SYSTEM_UPDATE, Integer.valueOf(status), Integer.valueOf(downloadResult), Long.valueOf(bytes), url);
    }

    public static void writeSystemUpdateUser(String action) {
        EventLog.writeEvent(SYSTEM_UPDATE_USER, action);
    }

    public static void writeVendingReconstruct(int changes) {
        EventLog.writeEvent(VENDING_RECONSTRUCT, changes);
    }

    public static void writeTransactionEvent(String data) {
        EventLog.writeEvent(TRANSACTION_EVENT, data);
    }

    public static void writeSyncDetails(String authority, int send, int recv, String details) {
        EventLog.writeEvent(SYNC_DETAILS, authority, Integer.valueOf(send), Integer.valueOf(recv), details);
    }

    public static void writeGoogleHttpRequest(long elapsed, int status, String appname, int reused) {
        EventLog.writeEvent(GOOGLE_HTTP_REQUEST, Long.valueOf(elapsed), Integer.valueOf(status), appname, Integer.valueOf(reused));
    }

    public static void writeGtalkservice(int eventtype) {
        EventLog.writeEvent(GTALKSERVICE, eventtype);
    }

    public static void writeGtalkConnection(int status) {
        EventLog.writeEvent(GTALK_CONNECTION, status);
    }

    public static void writeGtalkConnClose(int status, int duration) {
        EventLog.writeEvent(GTALK_CONN_CLOSE, Integer.valueOf(status), Integer.valueOf(duration));
    }

    public static void writeGtalkHeartbeatReset(int intervalAndNt, String ip) {
        EventLog.writeEvent(GTALK_HEARTBEAT_RESET, Integer.valueOf(intervalAndNt), ip);
    }

    public static void writeC2Dm(int packetType, String persistentId, int streamId, int lastStreamId) {
        EventLog.writeEvent(C2DM, Integer.valueOf(packetType), persistentId, Integer.valueOf(streamId), Integer.valueOf(lastStreamId));
    }

    public static void writeSetupServerTimeout() {
        EventLog.writeEvent(SETUP_SERVER_TIMEOUT, new Object[0]);
    }

    public static void writeSetupRequiredCaptcha(String action) {
        EventLog.writeEvent(SETUP_REQUIRED_CAPTCHA, action);
    }

    public static void writeSetupIoError(String status) {
        EventLog.writeEvent(SETUP_IO_ERROR, status);
    }

    public static void writeSetupServerError() {
        EventLog.writeEvent(SETUP_SERVER_ERROR, new Object[0]);
    }

    public static void writeSetupRetriesExhausted() {
        EventLog.writeEvent(SETUP_RETRIES_EXHAUSTED, new Object[0]);
    }

    public static void writeSetupNoDataNetwork() {
        EventLog.writeEvent(SETUP_NO_DATA_NETWORK, new Object[0]);
    }

    public static void writeSetupCompleted() {
        EventLog.writeEvent(SETUP_COMPLETED, new Object[0]);
    }

    public static void writeGlsAccountTried(int status) {
        EventLog.writeEvent(GLS_ACCOUNT_TRIED, status);
    }

    public static void writeGlsAccountSaved(int status) {
        EventLog.writeEvent(GLS_ACCOUNT_SAVED, status);
    }

    public static void writeGlsAuthenticate(int status, String service) {
        EventLog.writeEvent(GLS_AUTHENTICATE, Integer.valueOf(status), service);
    }

    public static void writeGoogleMailSwitch(int direction) {
        EventLog.writeEvent(GOOGLE_MAIL_SWITCH, direction);
    }

    public static void writeSnet(String payload) {
        EventLog.writeEvent(SNET, payload);
    }

    public static void writeExpDetSnet(String payload) {
        EventLog.writeEvent(EXP_DET_SNET, payload);
    }

    public static void writeMetricsHeartbeat() {
        EventLog.writeEvent(METRICS_HEARTBEAT, new Object[0]);
    }
}
