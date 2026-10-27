package com.android.services.telephony;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import android.telecom.PhoneAccountHandle;
import android.telecom.StatusHints;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Pair;
import com.android.ims.internal.ConferenceParticipant;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telephony.Call;
import com.android.internal.telephony.CallStateException;
import com.android.internal.telephony.Phone;
import com.android.phone.PhoneUtils;
import com.android.phone.R;
import com.android.services.telephony.TelephonyConnection;
import com.android.telephony.Rlog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class ImsConference extends TelephonyConferenceBase implements Holdable {
    private final CarrierConfiguration mCarrierConfig;
    private TelephonyConnection mConferenceHost;
    private Uri[] mConferenceHostAddress;
    private PhoneAccountHandle mConferenceHostPhoneAccountHandle;
    private final HashMap<Pair<Uri, Uri>, ConferenceParticipantConnection> mConferenceParticipantConnections;
    private boolean mCouldManageConference;
    private FeatureFlagProxy mFeatureFlagProxy;
    private Pair<Uri, Uri> mHostParticipantIdentity;
    private boolean mIsHoldable;
    private boolean mIsUsingSimCallManager;
    private Pair<Uri, Uri> mLoneParticipantIdentity;
    private TelecomAccountRegistry mTelecomAccountRegistry;
    private final TelephonyConnection.TelephonyConnectionListener mTelephonyConnectionListener;
    private TelephonyConnectionServiceProxy mTelephonyConnectionService;
    private final Object mUpdateSyncRoot;

    public interface FeatureFlagProxy {
        boolean isUsingSinglePartyCallEmulation();
    }

    private int changeBitmask(int i, int i2, boolean z) {
        return z ? i | i2 : (~i2) & i;
    }

    public Connection getPrimaryConnection() {
        return null;
    }

    @Override // com.android.services.telephony.Holdable
    public boolean isChildHoldable() {
        return false;
    }

    public final void setParticipants(List<Uri> list) {
    }

    public static class CarrierConfiguration {
        private boolean mIsHoldAllowed;
        private boolean mIsMaximumConferenceSizeEnforced;
        private int mMaximumConferenceSize;
        private boolean mShouldLocalDisconnectEmptyConference;

        public static class Builder {
            private boolean mIsMaximumConferenceSizeEnforced = false;
            private int mMaximumConferenceSize = 5;
            private boolean mShouldLocalDisconnectEmptyConference = false;
            private boolean mIsHoldAllowed = false;

            public Builder setIsMaximumConferenceSizeEnforced(boolean z) {
                this.mIsMaximumConferenceSizeEnforced = z;
                return this;
            }

            public Builder setMaximumConferenceSize(int i) {
                this.mMaximumConferenceSize = i;
                return this;
            }

            public Builder setShouldLocalDisconnectEmptyConference(boolean z) {
                this.mShouldLocalDisconnectEmptyConference = z;
                return this;
            }

            public Builder setIsHoldAllowed(boolean z) {
                this.mIsHoldAllowed = z;
                return this;
            }

            public CarrierConfiguration build() {
                return new CarrierConfiguration(this.mIsMaximumConferenceSizeEnforced, this.mMaximumConferenceSize, this.mShouldLocalDisconnectEmptyConference, this.mIsHoldAllowed);
            }
        }

        private CarrierConfiguration(boolean z, int i, boolean z2, boolean z3) {
            this.mIsMaximumConferenceSizeEnforced = z;
            this.mMaximumConferenceSize = i;
            this.mShouldLocalDisconnectEmptyConference = z2;
            this.mIsHoldAllowed = z3;
        }

        public boolean isMaximumConferenceSizeEnforced() {
            return this.mIsMaximumConferenceSizeEnforced;
        }

        public int getMaximumConferenceSize() {
            return this.mMaximumConferenceSize;
        }

        public boolean shouldLocalDisconnectEmptyConference() {
            return this.mShouldLocalDisconnectEmptyConference;
        }

        public boolean isHoldAllowed() {
            return this.mIsHoldAllowed;
        }
    }

    public void updateConferenceParticipantsAfterCreation() {
        if (this.mConferenceHost != null) {
            Log.v(this, "updateConferenceStateAfterCreation :: process participant update", new Object[0]);
            TelephonyConnection telephonyConnection = this.mConferenceHost;
            handleConferenceParticipantsUpdate(telephonyConnection, telephonyConnection.getConferenceParticipants());
            return;
        }
        Log.v(this, "updateConferenceStateAfterCreation :: null mConferenceHost", new Object[0]);
    }

    public ImsConference(TelecomAccountRegistry telecomAccountRegistry, TelephonyConnectionServiceProxy telephonyConnectionServiceProxy, TelephonyConnection telephonyConnection, PhoneAccountHandle phoneAccountHandle, FeatureFlagProxy featureFlagProxy, CarrierConfiguration carrierConfiguration) {
        int i;
        super(phoneAccountHandle);
        this.mTelephonyConnectionListener = new TelephonyConnection.TelephonyConnectionListener() { // from class: com.android.services.telephony.ImsConference.1
            @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
            public void onStateChanged(Connection connection, int i2) {
                ImsConference.this.setState(i2);
            }

            @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
            public void onDisconnected(Connection connection, DisconnectCause disconnectCause) {
                ImsConference.this.setDisconnected(disconnectCause);
            }

            @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
            public void onVideoStateChanged(Connection connection, int i2) {
                Log.d(this, "onVideoStateChanged video state %d", Integer.valueOf(i2));
                ImsConference.this.setVideoState(connection, i2);
            }

            @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
            public void onVideoProviderChanged(Connection connection, Connection.VideoProvider videoProvider) {
                Log.d(this, "onVideoProviderChanged: Connection: %s, VideoProvider: %s", connection, videoProvider);
                ImsConference.this.setVideoProvider(connection, videoProvider);
            }

            @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
            public void onConnectionCapabilitiesChanged(Connection connection, int i2) {
                Log.d(this, "onConnectionCapabilitiesChanged: Connection: %s, connectionCapabilities: %s", connection, Integer.valueOf(i2));
                int connectionCapabilities = ImsConference.this.getConnectionCapabilities();
                boolean zIsCarrierVideoConferencingSupported = ImsConference.this.mConferenceHost != null ? ImsConference.this.mConferenceHost.isCarrierVideoConferencingSupported() : false;
                ImsConference imsConference = ImsConference.this;
                imsConference.setConnectionCapabilities(imsConference.applyHostCapabilities(connectionCapabilities, i2, zIsCarrierVideoConferencingSupported));
            }

            @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
            public void onConnectionPropertiesChanged(Connection connection, int i2) {
                Log.d(this, "onConnectionPropertiesChanged: Connection: %s, connectionProperties: %s", connection, Integer.valueOf(i2));
                ImsConference.this.updateConnectionProperties(i2);
            }

            @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
            public void onStatusHintsChanged(Connection connection, StatusHints statusHints) {
                Log.v(this, "onStatusHintsChanged", new Object[0]);
                ImsConference.this.updateStatusHints();
            }

            @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
            public void onExtrasChanged(Connection connection, Bundle bundle) {
                Log.v(this, "onExtrasChanged: c=" + connection + " Extras=" + Rlog.pii("ImsConference", bundle), new Object[0]);
                ImsConference.this.updateExtras(bundle);
            }

            @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
            public void onExtrasRemoved(Connection connection, List<String> list) {
                Log.v(this, "onExtrasRemoved: c=" + connection + " key=" + list, new Object[0]);
                ImsConference.this.removeExtras(list);
            }

            @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
            public void onConnectionEvent(Connection connection, String str, Bundle bundle) {
                if ("android.telecom.event.MERGE_START".equals(str)) {
                    return;
                }
                ImsConference.this.sendConferenceEvent(str, bundle);
            }

            @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
            public void onOriginalConnectionConfigured(TelephonyConnection telephonyConnection2) {
                if (telephonyConnection2 == ImsConference.this.mConferenceHost) {
                    ImsConference.this.handleOriginalConnectionChange();
                }
            }

            @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
            public void onConferenceParticipantsChanged(Connection connection, List<ConferenceParticipant> list) {
                if (connection == null || list == null) {
                    return;
                }
                Log.v(this, "onConferenceParticipantsChanged: %d participants", Integer.valueOf(list.size()));
                ImsConference.this.handleConferenceParticipantsUpdate((TelephonyConnection) connection, list);
            }

            @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
            public void onRingbackRequested(Connection connection, boolean z) {
                Object[] objArr = new Object[1];
                objArr[0] = z ? "Y" : "N";
                Log.d(this, "onRingbackRequested ringback %s", objArr);
                ImsConference.this.setRingbackRequested(z);
            }
        };
        this.mConferenceParticipantConnections = new HashMap<>();
        this.mUpdateSyncRoot = new Object();
        this.mIsUsingSimCallManager = false;
        this.mLoneParticipantIdentity = null;
        this.mHostParticipantIdentity = null;
        this.mTelecomAccountRegistry = telecomAccountRegistry;
        this.mFeatureFlagProxy = featureFlagProxy;
        this.mCarrierConfig = carrierConfiguration;
        long connectTime = telephonyConnection.getOriginalConnection().getConnectTime();
        long connectTimeReal = telephonyConnection.getOriginalConnection().getConnectTimeReal();
        setConnectionTime(connectTime);
        setConnectionStartElapsedRealtimeMillis(connectTimeReal);
        telephonyConnection.setConnectTimeMillis(connectTime);
        telephonyConnection.setConnectionStartElapsedRealtimeMillis(connectTimeReal);
        this.mTelephonyConnectionService = telephonyConnectionServiceProxy;
        setConferenceHost(telephonyConnection);
        setVideoProvider(telephonyConnection, telephonyConnection.getVideoProvider());
        if (this.mCarrierConfig.isHoldAllowed()) {
            i = 2097219;
            this.mIsHoldable = true;
        } else {
            i = 2097216;
        }
        setConnectionCapabilities(applyHostCapabilities(i, this.mConferenceHost.getConnectionCapabilities(), this.mConferenceHost.isCarrierVideoConferencingSupported()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int applyHostCapabilities(int i, int i2, boolean z) {
        int iChangeBitmask;
        int iChangeBitmask2 = changeBitmask(i, 768, (i2 & 768) != 0);
        if (z) {
            iChangeBitmask = changeBitmask(changeBitmask(iChangeBitmask2, 3072, (i2 & 3072) != 0), 524288, (i2 & 524288) != 0);
        } else {
            Log.v(this, "applyHostCapabilities : video conferencing not supported", new Object[0]);
            iChangeBitmask = changeBitmask(changeBitmask(iChangeBitmask2, 3072, false), 524288, false);
        }
        return changeBitmask(changeBitmask(changeBitmask(iChangeBitmask, 8388608, (i2 & 8388608) != 0), 1048576, this.mConferenceHost.getVideoPauseSupported() && isVideoCapable()), 67108864, (i2 & 67108864) != 0);
    }

    private int applyHostProperties(int i, int i2) {
        return changeBitmask(changeBitmask(changeBitmask(changeBitmask(changeBitmask(i, 4, (i2 & 4) != 0), 8, (i2 & 8) != 0), 16, (i2 & 16) != 0), 2048, !isConferenceHost()), 4096, (i2 & 4096) != 0);
    }

    @Override // android.telecom.Conference
    public Connection.VideoProvider getVideoProvider() {
        TelephonyConnection telephonyConnection = this.mConferenceHost;
        if (telephonyConnection != null) {
            return telephonyConnection.getVideoProvider();
        }
        return null;
    }

    @Override // android.telecom.Conference
    public int getVideoState() {
        TelephonyConnection telephonyConnection = this.mConferenceHost;
        if (telephonyConnection != null) {
            return telephonyConnection.getVideoState();
        }
        return 0;
    }

    public Connection getConferenceHost() {
        return this.mConferenceHost;
    }

    @Override // android.telecom.Conference
    public void onDisconnect() {
        Log.v(this, "onDisconnect: hanging up conference host.", new Object[0]);
        if (this.mConferenceHost == null) {
            return;
        }
        disconnectConferenceParticipants();
        Call call = this.mConferenceHost.getCall();
        if (call != null) {
            try {
                call.hangup();
                return;
            } catch (CallStateException e) {
                Log.e(this, e, "Exception thrown trying to hangup conference", new Object[0]);
                return;
            }
        }
        Log.w(this, "onDisconnect - null call", new Object[0]);
    }

    @Override // android.telecom.Conference
    public void onSeparate(Connection connection) {
        Log.wtf(this, "Cannot separate connections from an IMS conference.", new Object[0]);
    }

    @Override // android.telecom.Conference
    public void onMerge(Connection connection) {
        try {
            Phone phone = this.mConferenceHost.getPhone();
            if (phone != null) {
                phone.conference();
            }
        } catch (CallStateException e) {
            Log.e(this, e, "Exception thrown trying to merge call into a conference", new Object[0]);
        }
    }

    @Override // android.telecom.Conference
    public void onAddConferenceParticipants(List<Uri> list) {
        TelephonyConnection telephonyConnection = this.mConferenceHost;
        if (telephonyConnection == null) {
            return;
        }
        telephonyConnection.performAddConferenceParticipants(list);
    }

    @Override // android.telecom.Conference
    public void onAnswer(int i) {
        TelephonyConnection telephonyConnection = this.mConferenceHost;
        if (telephonyConnection == null) {
            return;
        }
        telephonyConnection.performAnswer(i);
    }

    @Override // android.telecom.Conference
    public void onReject() {
        TelephonyConnection telephonyConnection = this.mConferenceHost;
        if (telephonyConnection == null) {
            return;
        }
        telephonyConnection.performReject(1);
    }

    @Override // android.telecom.Conference
    public void onHold() {
        TelephonyConnection telephonyConnection = this.mConferenceHost;
        if (telephonyConnection == null) {
            return;
        }
        telephonyConnection.performHold();
    }

    @Override // android.telecom.Conference
    public void onUnhold() {
        TelephonyConnection telephonyConnection = this.mConferenceHost;
        if (telephonyConnection == null) {
            return;
        }
        telephonyConnection.performUnhold();
    }

    @Override // android.telecom.Conference
    public void onPlayDtmfTone(char c) {
        TelephonyConnection telephonyConnection = this.mConferenceHost;
        if (telephonyConnection == null) {
            return;
        }
        telephonyConnection.onPlayDtmfTone(c);
    }

    @Override // android.telecom.Conference
    public void onStopDtmfTone() {
        TelephonyConnection telephonyConnection = this.mConferenceHost;
        if (telephonyConnection == null) {
            return;
        }
        telephonyConnection.onStopDtmfTone();
    }

    @Override // android.telecom.Conference
    public void onConnectionAdded(Connection connection) {
        Log.d(this, "connection added: " + connection + ", time: " + connection.getConnectTimeMillis(), new Object[0]);
    }

    @Override // com.android.services.telephony.Holdable
    public void setHoldable(boolean z) {
        this.mIsHoldable = z;
        if (!z) {
            removeCapability(1);
        } else {
            addCapability(1);
        }
    }

    public boolean isConferenceHost() {
        com.android.internal.telephony.Connection originalConnection;
        TelephonyConnection telephonyConnection = this.mConferenceHost;
        return telephonyConnection != null && (originalConnection = telephonyConnection.getOriginalConnection()) != null && originalConnection.isMultiparty() && originalConnection.isConferenceHost();
    }

    private void updateManageConference() {
        boolean z = (getConnectionCapabilities() & 128) != 0;
        boolean z2 = !this.mFeatureFlagProxy.isUsingSinglePartyCallEmulation() || isMultiparty() ? this.mConferenceParticipantConnections.size() != 0 : this.mConferenceParticipantConnections.size() > 1;
        Object[] objArr = new Object[2];
        objArr[0] = z ? "Y" : "N";
        objArr[1] = z2 ? "Y" : "N";
        Log.v(this, "updateManageConference was :%s is:%s", objArr);
        if (z != z2) {
            int connectionCapabilities = getConnectionCapabilities();
            setConnectionCapabilities(z2 ? (connectionCapabilities | 128) & (-2097153) : (connectionCapabilities & (-129)) | 2097152);
        }
    }

    private void setConferenceHost(TelephonyConnection telephonyConnection) {
        Log.i(this, "setConferenceHost " + telephonyConnection, new Object[0]);
        this.mConferenceHost = telephonyConnection;
        if (telephonyConnection.getPhone() != null && this.mConferenceHost.getPhone().getPhoneType() == 5) {
            Phone phone = this.mConferenceHost.getPhone();
            PhoneAccountHandle phoneAccountHandleMakePstnPhoneAccountHandle = PhoneUtils.makePstnPhoneAccountHandle(phone.getDefaultPhone());
            this.mConferenceHostPhoneAccountHandle = phoneAccountHandleMakePstnPhoneAccountHandle;
            Uri address = this.mTelecomAccountRegistry.getAddress(phoneAccountHandleMakePstnPhoneAccountHandle);
            ArrayList arrayList = new ArrayList();
            if (address != null) {
                arrayList.add(address);
            }
            if (phone.getCurrentSubscriberUris() != null) {
                arrayList.addAll(new ArrayList(Arrays.asList(phone.getCurrentSubscriberUris())));
            }
            Uri[] uriArr = new Uri[arrayList.size()];
            this.mConferenceHostAddress = uriArr;
            this.mConferenceHostAddress = (Uri[]) arrayList.toArray(uriArr);
            Log.i(this, "setConferenceHost: hosts are " + ((String) Arrays.stream(this.mConferenceHostAddress).map(new Function() { // from class: com.android.services.telephony.-$$Lambda$wr_wgB602_efe1QOIYIN-zTeEoE
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Uri) obj).getSchemeSpecificPart();
                }
            }).map(new Function() { // from class: com.android.services.telephony.-$$Lambda$ImsConference$_0IekDm9fjRonJYVReXsgFNJyAY
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Rlog.pii("ImsConference", (String) obj);
                }
            }).collect(Collectors.joining(", "))), new Object[0]);
            this.mIsUsingSimCallManager = this.mTelecomAccountRegistry.isUsingSimCallManager(this.mConferenceHostPhoneAccountHandle);
        }
        if (!isConferenceHost()) {
            setAddress(this.mConferenceHost.getAddress(), this.mConferenceHost.getAddressPresentation());
            setCallerDisplayName(this.mConferenceHost.getCallerDisplayName(), this.mConferenceHost.getCallerDisplayNamePresentation());
            setConnectionStartElapsedRealtimeMillis(this.mConferenceHost.getConnectionStartElapsedRealtimeMillis());
            setConnectionTime(this.mConferenceHost.getConnectTimeMillis());
        }
        this.mConferenceHost.addTelephonyConnectionListener(this.mTelephonyConnectionListener);
        setConnectionCapabilities(applyHostCapabilities(getConnectionCapabilities(), this.mConferenceHost.getConnectionCapabilities(), this.mConferenceHost.isCarrierVideoConferencingSupported()));
        setConnectionProperties(applyHostProperties(getConnectionProperties(), this.mConferenceHost.getConnectionProperties()));
        setState(this.mConferenceHost.getState());
        updateStatusHints();
        putExtras(this.mConferenceHost.getExtras());
    }

    @VisibleForTesting
    public void handleConferenceParticipantsUpdate(TelephonyConnection telephonyConnection, List<ConferenceParticipant> list) {
        boolean z;
        boolean z2;
        if (list == null) {
            return;
        }
        if (telephonyConnection != null && !telephonyConnection.isManageImsConferenceCallSupported()) {
            Log.i(this, "handleConferenceParticipantsUpdate: manage conference is disallowed", new Object[0]);
            return;
        }
        Log.i(this, "handleConferenceParticipantsUpdate: size=%d", Integer.valueOf(list.size()));
        synchronized (this.mUpdateSyncRoot) {
            int size = this.mConferenceParticipantConnections.size();
            ArrayList<ConferenceParticipant> arrayList = new ArrayList(list.size());
            HashSet hashSet = new HashSet(list.size());
            boolean z3 = list.stream().filter(new Predicate() { // from class: com.android.services.telephony.-$$Lambda$ImsConference$SmpiNuX3iAIrs5RjfBdZh1EfwqE
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.f$0.lambda$handleConferenceParticipantsUpdate$1$ImsConference((ConferenceParticipant) obj);
                }
            }).count() <= 1;
            if ((isMultiparty() || z3) && !isMultiparty()) {
                z = false;
                z2 = false;
            } else {
                z2 = false;
                for (ConferenceParticipant conferenceParticipant : list) {
                    Pair<Uri, Uri> pair = new Pair<>(conferenceParticipant.getHandle(), conferenceParticipant.getEndpoint());
                    if (conferenceParticipant.getState() != 6) {
                        hashSet.add(pair);
                    }
                    if (!this.mConferenceParticipantConnections.containsKey(pair)) {
                        if (!isParticipantHost(this.mConferenceHostAddress, conferenceParticipant.getHandle())) {
                            createConferenceParticipantConnection(telephonyConnection, conferenceParticipant);
                            arrayList.add(conferenceParticipant);
                            z2 = true;
                        } else {
                            this.mHostParticipantIdentity = pair;
                        }
                    } else {
                        ConferenceParticipantConnection conferenceParticipantConnection = this.mConferenceParticipantConnections.get(pair);
                        Log.i(this, "handleConferenceParticipantsUpdate: updateState, participant = %s", conferenceParticipant);
                        conferenceParticipantConnection.updateState(conferenceParticipant.getState());
                        if (conferenceParticipant.getState() == 6) {
                            handleConnectionDestruction(conferenceParticipantConnection);
                        }
                        conferenceParticipantConnection.setVideoState(telephonyConnection.getVideoState());
                    }
                }
                if (z2) {
                    for (ConferenceParticipant conferenceParticipant2 : arrayList) {
                        ConferenceParticipantConnection conferenceParticipantConnection2 = this.mConferenceParticipantConnections.get(new Pair(conferenceParticipant2.getHandle(), conferenceParticipant2.getEndpoint()));
                        conferenceParticipantConnection2.updateState(conferenceParticipant2.getState());
                        if (conferenceParticipant2.getState() == 6) {
                            handleConnectionDestruction(conferenceParticipantConnection2);
                        }
                        conferenceParticipantConnection2.setVideoState(telephonyConnection.getVideoState());
                    }
                }
                Iterator<Map.Entry<Pair<Uri, Uri>, ConferenceParticipantConnection>> it = this.mConferenceParticipantConnections.entrySet().iterator();
                z = false;
                while (it.hasNext()) {
                    Map.Entry<Pair<Uri, Uri>, ConferenceParticipantConnection> next = it.next();
                    if (!hashSet.contains(next.getKey())) {
                        ConferenceParticipantConnection value = next.getValue();
                        value.setDisconnected(new DisconnectCause(4));
                        removeTelephonyConnection(value);
                        value.destroy();
                        it.remove();
                        z = true;
                    }
                }
            }
            int size2 = this.mConferenceParticipantConnections.size();
            Log.v(this, "handleConferenceParticipantsUpdate: oldParticipantCount=%d, newParticipantcount=%d", Integer.valueOf(size), Integer.valueOf(size2));
            if (this.mFeatureFlagProxy.isUsingSinglePartyCallEmulation() && !this.mConferenceHost.isAdhocConferenceCall()) {
                if (size != 1 && size2 == 1) {
                    startEmulatingSinglePartyCall();
                } else if (!isMultiparty() && !z3) {
                    stopEmulatingSinglePartyCall();
                }
            }
            if (z2 || z) {
                updateManageConference();
            }
            if (this.mCarrierConfig.shouldLocalDisconnectEmptyConference() && size > 0 && size2 == 0) {
                Log.i(this, "handleConferenceParticipantsUpdate: empty conference; local disconnect.", new Object[0]);
                onDisconnect();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$handleConferenceParticipantsUpdate$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$handleConferenceParticipantsUpdate$1$ImsConference(ConferenceParticipant conferenceParticipant) {
        return (Objects.equals(this.mHostParticipantIdentity, new Pair(conferenceParticipant.getHandle(), conferenceParticipant.getEndpoint())) || conferenceParticipant.getState() == 6) ? false : true;
    }

    private void stopEmulatingSinglePartyCall() {
        if (this.mIsUsingSimCallManager) {
            Log.i(this, "stopEmulatingSinglePartyCall: using sim call manager; skip.", new Object[0]);
            return;
        }
        Log.i(this, "stopEmulatingSinglePartyCall: conference now has more than one participant; make it look conference-like again.", new Object[0]);
        if (this.mCouldManageConference) {
            setConnectionCapabilities(getConnectionCapabilities() | 128);
        }
        setAddress(null, 3);
        setCallerDisplayName(null, 3);
        ConferenceParticipantConnection conferenceParticipantConnection = this.mConferenceParticipantConnections.get(this.mLoneParticipantIdentity);
        if (conferenceParticipantConnection != null) {
            Log.d(this, "stopEmulatingSinglePartyCall: restored lone participant connect time", new Object[0]);
            conferenceParticipantConnection.setConnectTimeMillis(getConnectionTime());
            conferenceParticipantConnection.setConnectionStartElapsedRealtimeMillis(getConnectionStartElapsedRealtimeMillis());
        }
        setConferenceState(true);
    }

    private void startEmulatingSinglePartyCall() {
        if (this.mIsUsingSimCallManager) {
            Log.i(this, "startEmulatingSinglePartyCall: using sim call manager; skip.", new Object[0]);
            return;
        }
        Log.i(this, "startEmulatingSinglePartyCall: conference has a single participant; downgrade to single party call.", new Object[0]);
        Iterator<ConferenceParticipantConnection> it = this.mConferenceParticipantConnections.values().iterator();
        if (it.hasNext()) {
            ConferenceParticipantConnection next = it.next();
            setAddress(next.getAddress(), next.getAddressPresentation());
            setCallerDisplayName(next.getCallerDisplayName(), next.getCallerDisplayNamePresentation());
            setConnectionStartElapsedRealtimeMillis(next.getConnectionStartElapsedRealtimeMillis());
            setConnectionTime(next.getConnectTimeMillis());
            setCallDirection(next.getCallDirection());
            this.mLoneParticipantIdentity = new Pair<>(next.getUserEntity(), next.getEndpoint());
            next.setDisconnected(new DisconnectCause(4, "EMULATING_SINGLE_CALL"));
            removeTelephonyConnection(next);
            next.destroy();
            it.remove();
        }
        setConferenceState(false);
        this.mCouldManageConference = (getConnectionCapabilities() & 128) != 0;
        setConnectionCapabilities(getConnectionCapabilities() & (-129));
    }

    private void createConferenceParticipantConnection(TelephonyConnection telephonyConnection, ConferenceParticipant conferenceParticipant) {
        ConferenceParticipantConnection conferenceParticipantConnection = new ConferenceParticipantConnection(telephonyConnection.getOriginalConnection(), conferenceParticipant, !isConferenceHost());
        if (conferenceParticipant.getConnectTime() == 0) {
            conferenceParticipantConnection.setConnectTimeMillis(telephonyConnection.getConnectTimeMillis());
            conferenceParticipantConnection.setConnectionStartElapsedRealtimeMillis(telephonyConnection.getConnectionStartElapsedRealtimeMillis());
        } else {
            conferenceParticipantConnection.setConnectTimeMillis(conferenceParticipant.getConnectTime());
            conferenceParticipantConnection.setConnectionStartElapsedRealtimeMillis(conferenceParticipant.getConnectElapsedTime());
        }
        conferenceParticipantConnection.setCallDirection(conferenceParticipant.getCallDirection());
        conferenceParticipantConnection.setConnectionProperties(applyHostPropertiesToChild(conferenceParticipantConnection.getConnectionProperties(), telephonyConnection.getConnectionProperties()));
        conferenceParticipantConnection.setStatusHints(telephonyConnection.getStatusHints());
        conferenceParticipantConnection.setExtras(getChildExtrasFromHostBundle(telephonyConnection.getExtras()));
        Log.i(this, "createConferenceParticipantConnection: participant=%s, connection=%s", conferenceParticipant, conferenceParticipantConnection);
        synchronized (this.mUpdateSyncRoot) {
            this.mConferenceParticipantConnections.put(new Pair<>(conferenceParticipant.getHandle(), conferenceParticipant.getEndpoint()), conferenceParticipantConnection);
        }
        this.mTelephonyConnectionService.addExistingConnection(this.mConferenceHostPhoneAccountHandle, conferenceParticipantConnection, this);
        addTelephonyConnection(conferenceParticipantConnection);
    }

    private void removeConferenceParticipant(ConferenceParticipantConnection conferenceParticipantConnection) {
        Log.i(this, "removeConferenceParticipant: %s", conferenceParticipantConnection);
        synchronized (this.mUpdateSyncRoot) {
            this.mConferenceParticipantConnections.remove(new Pair(conferenceParticipantConnection.getUserEntity(), conferenceParticipantConnection.getEndpoint()));
        }
        conferenceParticipantConnection.destroy();
    }

    private void disconnectConferenceParticipants() {
        Log.v(this, "disconnectConferenceParticipants", new Object[0]);
        synchronized (this.mUpdateSyncRoot) {
            for (ConferenceParticipantConnection conferenceParticipantConnection : this.mConferenceParticipantConnections.values()) {
                conferenceParticipantConnection.setDisconnected(new DisconnectCause(4));
                conferenceParticipantConnection.destroy();
            }
            this.mConferenceParticipantConnections.clear();
            updateManageConference();
        }
    }

    private static String extractPhoneNumber(Uri uri) {
        String[] strArrSplit = uri.getSchemeSpecificPart().split("[@;:]");
        if (strArrSplit.length == 0) {
            Log.v("ImsConference", "extractPhoneNumber(N) : no number in handle", new Object[0]);
            return "";
        }
        return strArrSplit[0];
    }

    @VisibleForTesting
    public static boolean isParticipantHost(Uri[] uriArr, Uri uri) {
        if (uriArr == null || uriArr.length == 0 || uri == null) {
            Log.v("ImsConference", "isParticipantHost(N) : host or participant uri null", new Object[0]);
            return false;
        }
        String strExtractPhoneNumber = extractPhoneNumber(uri);
        if (TextUtils.isEmpty(strExtractPhoneNumber)) {
            return false;
        }
        for (Uri uri2 : uriArr) {
            if (uri2 != null) {
                String strExtractPhoneNumber2 = extractPhoneNumber(uri2);
                boolean zCompare = PhoneNumberUtils.compare(strExtractPhoneNumber2, strExtractPhoneNumber);
                Object[] objArr = new Object[3];
                objArr[0] = zCompare ? "Y" : "N";
                objArr[1] = Rlog.pii("ImsConference", strExtractPhoneNumber2);
                objArr[2] = Rlog.pii("ImsConference", strExtractPhoneNumber);
                Log.v("ImsConference", "isParticipantHost(%s) : host: %s, participant %s", objArr);
                if (zCompare) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOriginalConnectionChange() {
        PhoneAccountHandle phoneAccountHandleMakePstnPhoneAccountHandle;
        TelephonyConnection telephonyConnection = this.mConferenceHost;
        if (telephonyConnection == null) {
            Log.w(this, "handleOriginalConnectionChange; conference host missing.", new Object[0]);
            return;
        }
        com.android.internal.telephony.Connection originalConnection = telephonyConnection.getOriginalConnection();
        if (originalConnection != null && originalConnection.getPhoneType() != 5) {
            Log.i(this, "handleOriginalConnectionChange : handover from IMS connection to new connection: %s", originalConnection);
            if (this.mConferenceHost.getPhone() == null) {
                phoneAccountHandleMakePstnPhoneAccountHandle = null;
            } else if (this.mConferenceHost.getPhone().getPhoneType() == 5) {
                phoneAccountHandleMakePstnPhoneAccountHandle = PhoneUtils.makePstnPhoneAccountHandle(this.mConferenceHost.getPhone().getDefaultPhone());
            } else {
                phoneAccountHandleMakePstnPhoneAccountHandle = PhoneUtils.makePstnPhoneAccountHandle(this.mConferenceHost.getPhone());
            }
            if (this.mConferenceHost.getPhone().getPhoneType() == 1) {
                Log.i(this, "handleOriginalConnectionChange : SRVCC to GSM", new Object[0]);
                GsmConnection gsmConnection = new GsmConnection(originalConnection, getTelecomCallId(), this.mConferenceHost.getCallDirection());
                gsmConnection.setConferenceSupported(true);
                gsmConnection.setTelephonyConnectionProperties(gsmConnection.getConnectionProperties() | 64);
                gsmConnection.updateState();
                gsmConnection.setConnectTimeMillis(this.mConferenceHost.getConnectTimeMillis());
                gsmConnection.setConnectionStartElapsedRealtimeMillis(this.mConferenceHost.getConnectionStartElapsedRealtimeMillis());
                this.mTelephonyConnectionService.addExistingConnection(phoneAccountHandleMakePstnPhoneAccountHandle, gsmConnection);
                this.mTelephonyConnectionService.addConnectionToConferenceController(gsmConnection);
            }
            this.mConferenceHost.removeTelephonyConnectionListener(this.mTelephonyConnectionListener);
            this.mConferenceHost = null;
            setDisconnected(new DisconnectCause(9));
            disconnectConferenceParticipants();
            destroyTelephonyConference();
        }
        updateStatusHints();
    }

    public void setState(int i) {
        DisconnectCause telecomDisconnectCause;
        Log.v(this, "setState %s", Connection.stateToString(i));
        if (i == 2) {
            setConferenceOnRinging();
            return;
        }
        if (i == 3) {
            setConferenceOnDialing();
            return;
        }
        if (i == 4) {
            setConferenceOnActive();
            return;
        }
        if (i == 5) {
            setConferenceOnHold();
            return;
        }
        if (i != 6) {
            return;
        }
        TelephonyConnection telephonyConnection = this.mConferenceHost;
        if (telephonyConnection == null) {
            telecomDisconnectCause = new DisconnectCause(4);
        } else if (telephonyConnection.getPhone() != null) {
            telecomDisconnectCause = DisconnectCauseUtil.toTelecomDisconnectCause(this.mConferenceHost.getOriginalConnection().getDisconnectCause(), (String) null, this.mConferenceHost.getPhone().getPhoneId());
        } else {
            telecomDisconnectCause = DisconnectCauseUtil.toTelecomDisconnectCause(this.mConferenceHost.getOriginalConnection().getDisconnectCause());
        }
        setDisconnected(telecomDisconnectCause);
        disconnectConferenceParticipants();
        destroyTelephonyConference();
    }

    private boolean isVideoCapable() {
        int connectionCapabilities = this.mConferenceHost.getConnectionCapabilities();
        return ((connectionCapabilities & 768) == 0 || (connectionCapabilities & 3072) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStatusHints() {
        TelephonyConnection telephonyConnection = this.mConferenceHost;
        if (telephonyConnection == null) {
            setStatusHints(null);
            return;
        }
        if (telephonyConnection.isWifi()) {
            Phone phone = this.mConferenceHost.getPhone();
            if (phone != null) {
                Context context = phone.getContext();
                StatusHints statusHints = new StatusHints(context.getString(R.string.status_hint_label_wifi_call), Icon.createWithResource(context, R.drawable.ic_signal_wifi_4_bar_24dp), null);
                setStatusHints(statusHints);
                Iterator<Connection> it = getConnections().iterator();
                while (it.hasNext()) {
                    it.next().setStatusHints(statusHints);
                }
                return;
            }
            return;
        }
        setStatusHints(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConnectionProperties(int i) {
        setConnectionProperties(applyHostProperties(getConnectionProperties(), i));
        for (Connection connection : getConnections()) {
            connection.setConnectionProperties(applyHostPropertiesToChild(connection.getConnectionProperties(), i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateExtras(Bundle bundle) {
        putExtras(bundle);
        if (bundle == null) {
            return;
        }
        Bundle childExtrasFromHostBundle = getChildExtrasFromHostBundle(bundle);
        Iterator<Connection> it = getConnections().iterator();
        while (it.hasNext()) {
            it.next().putExtras(childExtrasFromHostBundle);
        }
    }

    private Bundle getChildExtrasFromHostBundle(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null && bundle.containsKey("android.telecom.extra.CALL_NETWORK_TYPE")) {
            bundle2.putInt("android.telecom.extra.CALL_NETWORK_TYPE", bundle.getInt("android.telecom.extra.CALL_NETWORK_TYPE"));
        }
        return bundle2;
    }

    private int applyHostPropertiesToChild(int i, int i2) {
        return changeBitmask(i, 8, (i2 & 8) != 0);
    }

    @Override // android.telecom.Conference
    public String toString() {
        return "[ImsConference objId:" + System.identityHashCode(this) + " telecomCallID:" + getTelecomCallId() + " state:" + Connection.stateToString(getState()) + " hostConnection:" + this.mConferenceHost + " participants:" + this.mConferenceParticipantConnections.size() + "]";
    }

    public int getNumberOfParticipants() {
        return this.mConferenceParticipantConnections.size();
    }

    public boolean isFullConference() {
        return this.mCarrierConfig.isMaximumConferenceSizeEnforced() && getNumberOfParticipants() >= this.mCarrierConfig.getMaximumConferenceSize();
    }

    private void handleConnectionDestruction(ConferenceParticipantConnection conferenceParticipantConnection) {
        removeConferenceParticipant(conferenceParticipantConnection);
        updateManageConference();
    }
}
