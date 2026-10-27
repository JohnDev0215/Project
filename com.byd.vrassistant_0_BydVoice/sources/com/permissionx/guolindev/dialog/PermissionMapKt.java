package com.permissionx.guolindev.dialog;

import com.permissionx.guolindev.request.RequestBackgroundLocationPermission;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;

/* JADX INFO: compiled from: PermissionMap.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001H\u0007\u001a\u0014\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001H\u0007¨\u0006\u0004"}, d2 = {"getPermissionMapOnQ", "", "", "getPermissionMapOnR", "library_release"}, k = 2, mv = {1, 4, 0})
public final class PermissionMapKt {
    public static final Map<String, String> getPermissionMapOnQ() {
        return MapsKt.mapOf(TuplesKt.to("android.permission.READ_CALENDAR", "android.permission-group.CALENDAR"), TuplesKt.to("android.permission.WRITE_CALENDAR", "android.permission-group.CALENDAR"), TuplesKt.to("android.permission.READ_CALL_LOG", "android.permission-group.CALL_LOG"), TuplesKt.to("android.permission.WRITE_CALL_LOG", "android.permission-group.CALL_LOG"), TuplesKt.to("android.permission.PROCESS_OUTGOING_CALLS", "android.permission-group.CALL_LOG"), TuplesKt.to("android.permission.CAMERA", "android.permission-group.CAMERA"), TuplesKt.to("android.permission.READ_CONTACTS", "android.permission-group.CONTACTS"), TuplesKt.to("android.permission.WRITE_CONTACTS", "android.permission-group.CONTACTS"), TuplesKt.to("android.permission.GET_ACCOUNTS", "android.permission-group.CONTACTS"), TuplesKt.to("android.permission.ACCESS_FINE_LOCATION", "android.permission-group.LOCATION"), TuplesKt.to("android.permission.ACCESS_COARSE_LOCATION", "android.permission-group.LOCATION"), TuplesKt.to(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION, "android.permission-group.LOCATION"), TuplesKt.to("android.permission.RECORD_AUDIO", "android.permission-group.MICROPHONE"), TuplesKt.to("android.permission.READ_PHONE_STATE", "android.permission-group.PHONE"), TuplesKt.to("android.permission.READ_PHONE_NUMBERS", "android.permission-group.PHONE"), TuplesKt.to("android.permission.CALL_PHONE", "android.permission-group.PHONE"), TuplesKt.to("android.permission.ANSWER_PHONE_CALLS", "android.permission-group.PHONE"), TuplesKt.to("com.android.voicemail.permission.ADD_VOICEMAIL", "android.permission-group.PHONE"), TuplesKt.to("android.permission.USE_SIP", "android.permission-group.PHONE"), TuplesKt.to("android.permission.ACCEPT_HANDOVER", "android.permission-group.PHONE"), TuplesKt.to("android.permission.BODY_SENSORS", "android.permission-group.SENSORS"), TuplesKt.to("android.permission.ACTIVITY_RECOGNITION", "android.permission-group.ACTIVITY_RECOGNITION"), TuplesKt.to("android.permission.SEND_SMS", "android.permission-group.SMS"), TuplesKt.to("android.permission.RECEIVE_SMS", "android.permission-group.SMS"), TuplesKt.to("android.permission.READ_SMS", "android.permission-group.SMS"), TuplesKt.to("android.permission.RECEIVE_WAP_PUSH", "android.permission-group.SMS"), TuplesKt.to("android.permission.RECEIVE_MMS", "android.permission-group.SMS"), TuplesKt.to("android.permission.READ_EXTERNAL_STORAGE", "android.permission-group.STORAGE"), TuplesKt.to("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission-group.STORAGE"), TuplesKt.to("android.permission.ACCESS_MEDIA_LOCATION", "android.permission-group.STORAGE"));
    }

    public static final Map<String, String> getPermissionMapOnR() {
        return getPermissionMapOnQ();
    }
}
