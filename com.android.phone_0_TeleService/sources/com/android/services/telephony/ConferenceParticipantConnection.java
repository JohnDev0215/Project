package com.android.services.telephony;

import android.net.Uri;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import android.telephony.SubscriptionInfo;
import android.text.TextUtils;
import com.android.ims.internal.ConferenceParticipant;
import com.android.internal.telephony.Phone;
import com.android.telephony.Rlog;

/* JADX INFO: loaded from: classes.dex */
public class ConferenceParticipantConnection extends Connection {
    private final Uri mEndpoint;
    private final com.android.internal.telephony.Connection mParentConnection;
    private final Uri mUserEntity;

    public ConferenceParticipantConnection(com.android.internal.telephony.Connection connection, ConferenceParticipant conferenceParticipant, boolean z) {
        Uri participantAddress;
        this.mParentConnection = connection;
        int participantPresentation = conferenceParticipant.getParticipantPresentation();
        if (participantPresentation != 1) {
            participantAddress = null;
        } else {
            participantAddress = ConferenceParticipant.getParticipantAddress(conferenceParticipant.getHandle(), getCountryIso(connection.getCall().getPhone()));
        }
        setAddress(participantAddress, participantPresentation);
        setVideoState(connection.getVideoState());
        setCallerDisplayName(conferenceParticipant.getDisplayName(), participantPresentation);
        this.mUserEntity = conferenceParticipant.getHandle();
        this.mEndpoint = conferenceParticipant.getEndpoint();
        setCapabilitiesAndProperties(z);
    }

    public void updateState(int i) {
        Log.v(this, "updateState endPoint: %s state: %s", Rlog.pii("ConferenceParticipantConnection", this.mEndpoint), Connection.stateToString(i));
        if (i == getState()) {
            return;
        }
        if (i == 0) {
            setInitializing();
            return;
        }
        if (i == 2) {
            setRinging();
            return;
        }
        if (i == 3) {
            setDialing();
            return;
        }
        if (i == 4) {
            setActive();
            return;
        }
        if (i == 5) {
            setOnHold();
        } else if (i == 6) {
            setDisconnected(new DisconnectCause(4));
            destroy();
        } else {
            setActive();
        }
    }

    @Override // android.telecom.Connection
    public void onDisconnect() {
        this.mParentConnection.onDisconnectConferenceParticipant(this.mUserEntity);
    }

    public Uri getUserEntity() {
        return this.mUserEntity;
    }

    public Uri getEndpoint() {
        return this.mEndpoint;
    }

    private void setCapabilitiesAndProperties(boolean z) {
        setConnectionCapabilities(8192);
        if (z) {
            setConnectionProperties(2048);
        }
    }

    private String getCountryIso(Phone phone) {
        if (phone == null) {
            return null;
        }
        SubscriptionInfo activeSubscriptionInfo = TelecomAccountRegistry.getInstance(null).getSubscriptionManager().getActiveSubscriptionInfo(phone.getSubId());
        if (activeSubscriptionInfo == null || TextUtils.isEmpty(activeSubscriptionInfo.getCountryIso())) {
            return null;
        }
        return activeSubscriptionInfo.getCountryIso().toUpperCase();
    }

    public String toString() {
        return "[ConferenceParticipantConnection objId:" + System.identityHashCode(this) + " endPoint:" + Rlog.pii("ConferenceParticipantConnection", this.mEndpoint) + " address:" + Rlog.pii("ConferenceParticipantConnection", getAddress()) + " addressPresentation:" + getAddressPresentation() + " parentConnection:" + Rlog.pii("ConferenceParticipantConnection", this.mParentConnection.getAddress()) + " state:" + Connection.stateToString(getState()) + " connectTime:" + getConnectTimeMillis() + " connectElapsedTime:" + getConnectionStartElapsedRealtimeMillis() + "]";
    }
}
