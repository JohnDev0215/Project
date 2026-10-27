package com.android.server.telecom;

import android.net.Uri;
import android.os.Bundle;
import android.telecom.DisconnectCause;
import android.telecom.GatewayInfo;
import android.telecom.ParcelableCall;
import android.telecom.ParcelableRttCall;
import android.telecom.PhoneAccountHandle;
import android.telecom.StatusHints;
import android.text.TextUtils;
import com.android.internal.telecom.IVideoProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ParcelableCallUtils {
    private static final int[] CONNECTION_TO_CALL_CAPABILITY;
    private static final int[] CONNECTION_TO_CALL_PROPERTIES;
    private static List<String> EXTRA_KEYS_TO_SANITIZE;
    private static List<String> RESTRICTED_CALL_SCREENING_EXTRA_KEYS;

    private static int removeCapability(int i, int i2) {
        return i & (~i2);
    }

    static {
        ArrayList arrayList = new ArrayList();
        EXTRA_KEYS_TO_SANITIZE = arrayList;
        arrayList.add("android.telecom.extra.SIP_INVITE");
        ArrayList arrayList2 = new ArrayList();
        RESTRICTED_CALL_SCREENING_EXTRA_KEYS = arrayList2;
        arrayList2.add("android.telecom.extra.SIP_INVITE");
        CONNECTION_TO_CALL_CAPABILITY = new int[]{1, 1, 2, 2, 4, 4, 8, 8, 32, 32, 64, 64, 128, 128, 256, 256, 512, 512, 768, 768, 1024, 1024, 2048, 2048, 3072, 3072, 4096, 4096, 8192, 8192, 524288, 524288, 1048576, 1048576, 4194304, 2097152, 8388608, 4194304, 16777216, 8388608, 33554432, 16777216, 67108864, 33554432, 134217728, 67108864, 268435456, 134217728};
        CONNECTION_TO_CALL_PROPERTIES = new int[]{4, 16, 8, 8, 2, 2, 1, 4, 16, 64, 32, 128, 128, 256, 512, 512, 256, 1024, 1024, 2048, 4096, 8192};
    }

    public static class Converter {
        public ParcelableCall toParcelableCallForScreening(Call call, boolean z) {
            return ParcelableCallUtils.toParcelableCallForScreening(call, z);
        }
    }

    public static ParcelableCall toParcelableCall(Call call, boolean z, PhoneAccountRegistrar phoneAccountRegistrar, boolean z2, boolean z3, boolean z4) {
        return toParcelableCall(call, z, phoneAccountRegistrar, z2, -1, z3, z4);
    }

    public static ParcelableCall toParcelableCall(Call call, boolean z, PhoneAccountRegistrar phoneAccountRegistrar, boolean z2, int i, boolean z3, boolean z4) {
        Bundle bundleSanitizeExtras;
        int parcelableState = i;
        if (parcelableState == -1) {
            parcelableState = getParcelableState(call, z2);
        }
        int iConvertConnectionToCallCapabilities = convertConnectionToCallCapabilities(call.getConnectionCapabilities());
        int iConvertConnectionToCallProperties = convertConnectionToCallProperties(call.getConnectionProperties());
        int supportedAudioRoutes = call.getSupportedAudioRoutes();
        if (call.isConference()) {
            iConvertConnectionToCallProperties |= 1;
        }
        if (call.isWorkCall()) {
            iConvertConnectionToCallProperties |= 32;
        }
        if (call.getIsVoipAudioMode()) {
            iConvertConnectionToCallProperties |= 4096;
        }
        int i2 = 1;
        boolean z5 = phoneAccountRegistrar != null && phoneAccountRegistrar.isUserSelectedSmsPhoneAccount(call.getTargetPhoneAccount());
        if (call.isRespondViaSmsCapable() && z5) {
            iConvertConnectionToCallCapabilities |= 32;
        }
        if (call.isEmergencyCall()) {
            iConvertConnectionToCallCapabilities = removeCapability(iConvertConnectionToCallCapabilities, 64);
        }
        if (parcelableState == 1) {
            iConvertConnectionToCallCapabilities = removeCapability(removeCapability(iConvertConnectionToCallCapabilities, 768), 3072);
        }
        Call parentCall = call.getParentCall();
        String id = parentCall != null ? parentCall.getId() : null;
        List<Call> childCalls = call.getChildCalls();
        ArrayList arrayList = new ArrayList();
        if (!childCalls.isEmpty()) {
            Iterator<Call> it = childCalls.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getId());
            }
        }
        Uri handle = call.getHandlePresentation() == 1 ? call.getHandle() : null;
        String callerDisplayName = call.getCallerDisplayNamePresentation() == 1 ? call.getCallerDisplayName() : null;
        List<Call> conferenceableCalls = call.getConferenceableCalls();
        ArrayList arrayList2 = new ArrayList(conferenceableCalls.size());
        Iterator<Call> it2 = conferenceableCalls.iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next().getId());
        }
        ParcelableRttCall parcelableRttCall = z3 ? getParcelableRttCall(call) : null;
        if (call.isIncoming()) {
            i2 = 0;
        } else if (call.isUnknown()) {
            i2 = -1;
        }
        String id2 = call.getConferenceLevelActiveCall() != null ? call.getConferenceLevelActiveCall().getId() : null;
        if (z4) {
            bundleSanitizeExtras = call.getExtras();
        } else {
            bundleSanitizeExtras = sanitizeExtras(call.getExtras());
        }
        return new ParcelableCall.ParcelableCallBuilder().setId(call.getId()).setState(parcelableState).setDisconnectCause(call.getDisconnectCause()).setCannedSmsResponses(call.getCannedSmsResponses()).setCapabilities(iConvertConnectionToCallCapabilities).setProperties(iConvertConnectionToCallProperties).setSupportedAudioRoutes(supportedAudioRoutes).setConnectTimeMillis(call.getConnectTimeMillis()).setHandle(handle).setHandlePresentation(call.getHandlePresentation()).setCallerDisplayName(callerDisplayName).setCallerDisplayNamePresentation(call.getCallerDisplayNamePresentation()).setGatewayInfo(call.getGatewayInfo()).setAccountHandle(call.getTargetPhoneAccount()).setIsVideoCallProviderChanged(z).setVideoCallProvider(z ? call.getVideoProvider() : null).setIsRttCallChanged(z3).setRttCall(parcelableRttCall).setParentCallId(id).setChildCallIds(arrayList).setStatusHints(call.getStatusHints()).setVideoState(call.getVideoState()).setConferenceableCallIds(arrayList2).setIntentExtras(call.getIntentExtras()).setExtras(bundleSanitizeExtras).setCreationTimeMillis(call.getCreationTimeMillis()).setCallDirection(i2).setCallerNumberVerificationStatus(call.getCallerNumberVerificationStatus()).setContactDisplayName(call.getName()).setActiveChildCallId(id2).createParcelableCall();
    }

    public static ParcelableCall toParcelableCallForScreening(Call call, boolean z) {
        Bundle bundle;
        int i = 1;
        Uri handle = call.getHandlePresentation() == 1 ? call.getHandle() : null;
        if (call.isIncoming()) {
            i = 0;
        } else if (call.isUnknown()) {
            i = -1;
        }
        if (z) {
            bundle = sanitizeRestrictedCallExtras(call.getExtras());
        } else {
            bundle = new Bundle();
        }
        return new ParcelableCall.ParcelableCallBuilder().setId(call.getId()).setState(getParcelableState(call, false)).setDisconnectCause(new DisconnectCause(0)).setCannedSmsResponses((List) null).setCapabilities(0).setProperties(0).setSupportedAudioRoutes(0).setConnectTimeMillis(call.getConnectTimeMillis()).setHandle(handle).setHandlePresentation(call.getHandlePresentation()).setCallerDisplayName((String) null).setCallerDisplayNamePresentation(0).setGatewayInfo((GatewayInfo) null).setAccountHandle((PhoneAccountHandle) null).setIsVideoCallProviderChanged(false).setVideoCallProvider((IVideoProvider) null).setIsRttCallChanged(false).setRttCall((ParcelableRttCall) null).setParentCallId((String) null).setChildCallIds((List) null).setStatusHints((StatusHints) null).setVideoState(0).setConferenceableCallIds(Collections.emptyList()).setIntentExtras((Bundle) null).setExtras(bundle).setCreationTimeMillis(call.getCreationTimeMillis()).setCallDirection(i).setCallerNumberVerificationStatus(call.getCallerNumberVerificationStatus()).setContactDisplayName((String) null).setActiveChildCallId((String) null).createParcelableCall();
    }

    private static Bundle sanitizeExtras(Bundle bundle) {
        if (bundle == null) {
            return new Bundle();
        }
        Bundle bundle2 = new Bundle(bundle);
        Iterator<String> it = EXTRA_KEYS_TO_SANITIZE.iterator();
        while (it.hasNext()) {
            bundle2.remove(it.next());
        }
        Iterator<String> it2 = bundle2.keySet().iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            if (TextUtils.isEmpty(next) || !next.startsWith("android.")) {
                it2.remove();
            }
        }
        return bundle2;
    }

    private static Bundle sanitizeRestrictedCallExtras(Bundle bundle) {
        if (bundle == null) {
            return new Bundle();
        }
        Bundle bundle2 = new Bundle(bundle);
        Iterator<String> it = bundle2.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (TextUtils.isEmpty(next) || !RESTRICTED_CALL_SCREENING_EXTRA_KEYS.contains(next)) {
                it.remove();
            }
        }
        return bundle2;
    }

    private static int getParcelableState(Call call, boolean z) {
        switch (call.getParcelableCallState()) {
            case 0:
            default:
                return 0;
            case 1:
                return 9;
            case HeadsetMediaButton.LONG_PRESS /* 2 */:
                return 8;
            case 3:
                return 1;
            case 4:
            case 11:
                return 2;
            case 5:
                return 4;
            case 6:
                return 3;
            case 7:
            case 8:
                return 7;
            case PhoneAccountRegistrar.EXPECTED_STATE_VERSION /* 9 */:
                return 10;
            case 10:
                return z ? 11 : 1;
            case 12:
                return 12;
            case 13:
                return 13;
        }
    }

    private static int convertConnectionToCallCapabilities(int i) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = CONNECTION_TO_CALL_CAPABILITY;
            if (i2 >= iArr.length) {
                return i3;
            }
            if ((iArr[i2] & i) == iArr[i2]) {
                i3 |= iArr[i2 + 1];
            }
            i2 += 2;
        }
    }

    private static int convertConnectionToCallProperties(int i) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = CONNECTION_TO_CALL_PROPERTIES;
            if (i2 >= iArr.length) {
                return i3;
            }
            if ((iArr[i2] & i) == iArr[i2]) {
                i3 |= iArr[i2 + 1];
            }
            i2 += 2;
        }
    }

    private static ParcelableRttCall getParcelableRttCall(Call call) {
        if (call.isRttCall()) {
            return new ParcelableRttCall(call.getRttMode(), call.getInCallToCsRttPipeForInCall(), call.getCsToInCallRttPipeForInCall());
        }
        return null;
    }
}
