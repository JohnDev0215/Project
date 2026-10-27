package com.android.services.telephony;

import android.os.PersistableBundle;
import android.telecom.Conference;
import android.telecom.Conferenceable;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import android.telecom.PhoneAccountHandle;
import android.telephony.CarrierConfigManager;
import com.android.internal.telephony.Phone;
import com.android.phone.PhoneUtils;
import com.android.services.telephony.ImsConference;
import com.android.services.telephony.TelephonyConferenceBase;
import com.android.services.telephony.TelephonyConnection;
import com.android.telephony.Rlog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class ImsConferenceController {
    private final TelephonyConnectionServiceProxy mConnectionService;
    private final ImsConference.FeatureFlagProxy mFeatureFlagProxy;
    private TelecomAccountRegistry mTelecomAccountRegistry;
    private final TelephonyConferenceBase.TelephonyConferenceListener mConferenceListener = new TelephonyConferenceBase.TelephonyConferenceListener() { // from class: com.android.services.telephony.ImsConferenceController.1
        @Override // com.android.services.telephony.TelephonyConferenceBase.TelephonyConferenceListener
        public void onDestroyed(Conference conference) {
            if (Log.VERBOSE) {
                Log.v(ImsConferenceController.class, "onDestroyed: %s", conference);
            }
            if (conference instanceof ImsConference) {
                ImsConferenceController.this.mTelecomAccountRegistry.refreshAdhocConference(true);
            }
            ImsConferenceController.this.mImsConferences.remove(conference);
        }

        @Override // com.android.services.telephony.TelephonyConferenceBase.TelephonyConferenceListener
        public void onStateChanged(Conference conference, int i, int i2) {
            Log.v(this, "onStateChanged: Conference = " + conference, new Object[0]);
            ImsConferenceController.this.recalculateConferenceable();
        }
    };
    private final TelephonyConnection.TelephonyConnectionListener mTelephonyConnectionListener = new TelephonyConnection.TelephonyConnectionListener() { // from class: com.android.services.telephony.ImsConferenceController.2
        @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
        public void onConferenceStarted() {
            Log.v(this, "onConferenceStarted", new Object[0]);
            ImsConferenceController.this.recalculate();
        }

        @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
        public void onConferenceSupportedChanged(Connection connection, boolean z) {
            Log.v(this, "onConferenceSupportedChanged", new Object[0]);
            ImsConferenceController.this.recalculate();
        }

        @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
        public void onStateChanged(Connection connection, int i) {
            Log.v(this, "onStateChanged: %s", Rlog.pii("ImsConferenceController", connection.getAddress()));
            ImsConferenceController.this.recalculate();
        }

        @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
        public void onDisconnected(Connection connection, DisconnectCause disconnectCause) {
            Log.v(this, "onDisconnected: %s", Rlog.pii("ImsConferenceController", connection.getAddress()));
            ImsConferenceController.this.recalculate();
        }

        @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
        public void onDestroyed(Connection connection) {
            ImsConferenceController.this.remove(connection);
        }
    };
    private final ArrayList<TelephonyConnection> mTelephonyConnections = new ArrayList<>();
    private final ArrayList<ImsConference> mImsConferences = new ArrayList<>(2);

    static /* synthetic */ boolean lambda$recalculateConferenceable$0(Conferenceable conferenceable, Conferenceable conferenceable2) {
        return conferenceable != conferenceable2;
    }

    public ImsConferenceController(TelecomAccountRegistry telecomAccountRegistry, TelephonyConnectionServiceProxy telephonyConnectionServiceProxy, ImsConference.FeatureFlagProxy featureFlagProxy) {
        this.mConnectionService = telephonyConnectionServiceProxy;
        this.mTelecomAccountRegistry = telecomAccountRegistry;
        this.mFeatureFlagProxy = featureFlagProxy;
    }

    void addConference(ImsConference imsConference) {
        if (this.mImsConferences.contains(imsConference)) {
            Log.w(this, "addConference - conference already tracked; conference=%s", imsConference);
            return;
        }
        this.mImsConferences.add(imsConference);
        imsConference.addTelephonyConferenceListener(this.mConferenceListener);
        recalculateConferenceable();
    }

    void add(TelephonyConnection telephonyConnection) {
        if ((telephonyConnection.getConnectionProperties() & 16) == 16) {
            return;
        }
        if (this.mTelephonyConnections.contains(telephonyConnection)) {
            Log.w(this, "add - connection already tracked; connection=%s", telephonyConnection);
            return;
        }
        if (Log.VERBOSE) {
            Log.v(this, "add connection %s", telephonyConnection);
        }
        this.mTelephonyConnections.add(telephonyConnection);
        telephonyConnection.addTelephonyConnectionListener(this.mTelephonyConnectionListener);
        recalculateConference();
        recalculateConferenceable();
    }

    void remove(Connection connection) {
        if ((connection.getConnectionProperties() & 16) == 16) {
            return;
        }
        if (!this.mTelephonyConnections.contains(connection)) {
            Log.d(this, "remove - connection not tracked; connection=%s", connection);
            return;
        }
        if (Log.VERBOSE) {
            Log.v(this, "remove connection: %s", connection);
        }
        if (connection instanceof TelephonyConnection) {
            ((TelephonyConnection) connection).removeTelephonyConnectionListener(this.mTelephonyConnectionListener);
        }
        this.mTelephonyConnections.remove(connection);
        recalculateConferenceable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recalculate() {
        recalculateConferenceable();
        recalculateConference();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recalculateConferenceable() {
        Log.v(this, "recalculateConferenceable : %d", Integer.valueOf(this.mTelephonyConnections.size()));
        HashSet<Conferenceable> hashSet = new HashSet(this.mTelephonyConnections.size() + this.mImsConferences.size());
        HashSet hashSet2 = new HashSet();
        for (TelephonyConnection telephonyConnection : this.mTelephonyConnections) {
            if (Log.DEBUG) {
                Log.d(this, "recalc - %s %s supportsConf? %s", Integer.valueOf(telephonyConnection.getState()), telephonyConnection, Boolean.valueOf(telephonyConnection.isConferenceSupported()));
            }
            if (isMemberOfPeerConference(telephonyConnection)) {
                if (Log.VERBOSE) {
                    Log.v(this, "Skipping connection in peer conference: %s", telephonyConnection);
                }
            } else if (!telephonyConnection.isConferenceSupported()) {
                telephonyConnection.setConferenceables(Collections.emptyList());
            } else {
                int state = telephonyConnection.getState();
                if (state == 4 || state == 5) {
                    hashSet.add(telephonyConnection);
                } else {
                    telephonyConnection.setConferenceables(Collections.emptyList());
                }
            }
        }
        for (ImsConference imsConference : this.mImsConferences) {
            if (Log.DEBUG) {
                Log.d(this, "recalc - %s %s", Integer.valueOf(imsConference.getState()), imsConference);
            }
            if (!imsConference.isConferenceHost()) {
                if (Log.VERBOSE) {
                    Log.v(this, "skipping conference (not hosted on this device): %s", imsConference);
                }
            } else {
                this.mTelecomAccountRegistry.refreshAdhocConference(false);
                int state2 = imsConference.getState();
                if (state2 == 4 || state2 == 5) {
                    if (!imsConference.isFullConference()) {
                        hashSet2.addAll(imsConference.getConnections());
                        hashSet.add(imsConference);
                    }
                }
            }
        }
        Log.v(this, "conferenceableSet size: " + hashSet.size(), new Object[0]);
        for (final Conferenceable conferenceable : hashSet) {
            if (conferenceable instanceof Connection) {
                List<Conferenceable> list = (List) hashSet.stream().filter(new Predicate() { // from class: com.android.services.telephony.-$$Lambda$ImsConferenceController$7cbfQ9Dk9ym-gJXBRcR6FeNeakA
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ImsConferenceController.lambda$recalculateConferenceable$0(conferenceable, (Conferenceable) obj);
                    }
                }).collect(Collectors.toList());
                list.addAll(hashSet2);
                ((Connection) conferenceable).setConferenceables(list);
            } else if (conferenceable instanceof ImsConference) {
                ImsConference imsConference2 = (ImsConference) conferenceable;
                if (imsConference2.isFullConference()) {
                    imsConference2.setConferenceableConnections(Collections.emptyList());
                }
                imsConference2.setConferenceableConnections((List) hashSet.stream().filter(new Predicate() { // from class: com.android.services.telephony.-$$Lambda$ImsConferenceController$hBrdnAK77KuCV1DlAz8TbDqqASE
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ImsConferenceController.lambda$recalculateConferenceable$1((Conferenceable) obj);
                    }
                }).map(new Function() { // from class: com.android.services.telephony.-$$Lambda$ImsConferenceController$TCduEeuw_97rP1KUnEGClH02zUM
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ImsConferenceController.lambda$recalculateConferenceable$2((Conferenceable) obj);
                    }
                }).collect(Collectors.toList()));
            }
        }
    }

    static /* synthetic */ boolean lambda$recalculateConferenceable$1(Conferenceable conferenceable) {
        return conferenceable instanceof Connection;
    }

    static /* synthetic */ Connection lambda$recalculateConferenceable$2(Conferenceable conferenceable) {
        return (Connection) conferenceable;
    }

    private boolean isMemberOfPeerConference(Connection connection) {
        com.android.internal.telephony.Connection originalConnection;
        return (connection instanceof TelephonyConnection) && (originalConnection = ((TelephonyConnection) connection).getOriginalConnection()) != null && originalConnection.isMultiparty() && originalConnection.isMemberOfPeerConference();
    }

    private void recalculateConference() {
        Log.v(this, "recalculateConference", new Object[0]);
        Iterator<TelephonyConnection> it = this.mTelephonyConnections.iterator();
        while (it.hasNext()) {
            TelephonyConnection next = it.next();
            if (next.isImsConnection() && next.getOriginalConnection() != null && next.getOriginalConnection().isMultiparty()) {
                startConference(next);
                it.remove();
            }
        }
    }

    private void startConference(TelephonyConnection telephonyConnection) {
        PhoneAccountHandle phoneAccountHandle;
        ImsConference.CarrierConfiguration carrierConfig;
        if (Log.VERBOSE) {
            Log.v(this, "Start new ImsConference - connection: %s", telephonyConnection);
        }
        if (telephonyConnection.isAdhocConferenceCall()) {
            Log.w(this, "start new ImsConference - control should never come here", new Object[0]);
            return;
        }
        TelephonyConnection telephonyConnectionCloneConnection = telephonyConnection.cloneConnection();
        telephonyConnectionCloneConnection.setVideoPauseSupported(telephonyConnection.getVideoPauseSupported());
        telephonyConnectionCloneConnection.setManageImsConferenceCallSupported(telephonyConnection.isManageImsConferenceCallSupported());
        if (telephonyConnection.getPhone() == null || telephonyConnection.getPhone().getPhoneType() != 5) {
            phoneAccountHandle = null;
            carrierConfig = null;
        } else {
            Phone phone = telephonyConnection.getPhone();
            PhoneAccountHandle phoneAccountHandleMakePstnPhoneAccountHandle = PhoneUtils.makePstnPhoneAccountHandle(phone.getDefaultPhone());
            carrierConfig = getCarrierConfig(phone);
            phoneAccountHandle = phoneAccountHandleMakePstnPhoneAccountHandle;
        }
        ImsConference imsConference = new ImsConference(this.mTelecomAccountRegistry, this.mConnectionService, telephonyConnectionCloneConnection, phoneAccountHandle, this.mFeatureFlagProxy, carrierConfig);
        imsConference.setState(telephonyConnectionCloneConnection.getState());
        imsConference.setCallDirection(telephonyConnectionCloneConnection.getCallDirection());
        imsConference.addTelephonyConferenceListener(this.mConferenceListener);
        imsConference.updateConferenceParticipantsAfterCreation();
        this.mConnectionService.addConference(imsConference);
        telephonyConnectionCloneConnection.setTelecomCallId(imsConference.getTelecomCallId());
        telephonyConnection.removeTelephonyConnectionListener(this.mTelephonyConnectionListener);
        telephonyConnection.setTelephonyConnectionDisconnected(new DisconnectCause(9, android.telephony.DisconnectCause.toString(45)));
        telephonyConnection.close();
        this.mImsConferences.add(imsConference);
        recalculateConferenceable();
    }

    public static ImsConference.CarrierConfiguration getCarrierConfig(Phone phone) {
        ImsConference.CarrierConfiguration.Builder builder = new ImsConference.CarrierConfiguration.Builder();
        if (phone == null) {
            return builder.build();
        }
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) phone.getContext().getSystemService("carrier_config");
        if (carrierConfigManager != null) {
            PersistableBundle configForSubId = carrierConfigManager.getConfigForSubId(phone.getSubId());
            boolean z = configForSubId.getBoolean("is_ims_conference_size_enforced_bool");
            int i = configForSubId.getInt("ims_conference_size_limit_int");
            boolean z2 = configForSubId.getBoolean("allow_hold_in_ims_call");
            boolean z3 = configForSubId.getBoolean("local_disconnect_empty_ims_conference_bool");
            builder.setIsMaximumConferenceSizeEnforced(z);
            builder.setMaximumConferenceSize(i);
            builder.setIsHoldAllowed(z2);
            builder.setShouldLocalDisconnectEmptyConference(z3);
        }
        return builder.build();
    }
}
