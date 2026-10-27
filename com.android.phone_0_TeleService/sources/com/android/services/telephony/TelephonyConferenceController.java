package com.android.services.telephony;

import android.telecom.Conference;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import com.android.internal.telephony.Call;
import com.android.phone.PhoneUtils;
import com.android.services.telephony.TelephonyConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
final class TelephonyConferenceController {
    private final TelephonyConnectionServiceProxy mConnectionService;
    private TelephonyConference mTelephonyConference;
    private final TelephonyConnection.TelephonyConnectionListener mTelephonyConnectionListener = new TelephonyConnection.TelephonyConnectionListener() { // from class: com.android.services.telephony.TelephonyConferenceController.1
        @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
        public void onStateChanged(Connection connection, int i) {
            Log.v(this, "onStateChange triggered in Conf Controller : connection = " + connection + " state = " + i, new Object[0]);
            TelephonyConferenceController.this.recalculate();
        }

        @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
        public void onDisconnected(Connection connection, DisconnectCause disconnectCause) {
            TelephonyConferenceController.this.recalculate();
        }

        @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
        public void onDestroyed(Connection connection) {
            TelephonyConferenceController.this.remove((TelephonyConnection) connection);
        }
    };
    private final List<TelephonyConnection> mTelephonyConnections = new ArrayList();
    private boolean mTriggerRecalculate = false;

    static /* synthetic */ boolean lambda$recalculateConferenceable$0(Connection connection, Connection connection2) {
        return connection != connection2;
    }

    public TelephonyConferenceController(TelephonyConnectionServiceProxy telephonyConnectionServiceProxy) {
        this.mConnectionService = telephonyConnectionServiceProxy;
    }

    boolean shouldRecalculate() {
        Log.d(this, "shouldRecalculate is " + this.mTriggerRecalculate, new Object[0]);
        return this.mTriggerRecalculate;
    }

    void add(TelephonyConnection telephonyConnection) {
        if (this.mTelephonyConnections.contains(telephonyConnection)) {
            Log.w(this, "add - connection already tracked; connection=%s", telephonyConnection);
            return;
        }
        this.mTelephonyConnections.add(telephonyConnection);
        telephonyConnection.addTelephonyConnectionListener(this.mTelephonyConnectionListener);
        recalculate();
    }

    void remove(TelephonyConnection telephonyConnection) {
        if (!this.mTelephonyConnections.contains(telephonyConnection)) {
            Log.d(this, "remove - connection not tracked; connection=%s", telephonyConnection);
            return;
        }
        telephonyConnection.removeTelephonyConnectionListener(this.mTelephonyConnectionListener);
        this.mTelephonyConnections.remove(telephonyConnection);
        recalculate();
    }

    void recalculate() {
        recalculateConference();
        recalculateConferenceable();
    }

    private boolean isFullConference(Conference conference) {
        return conference.getConnections().size() >= 5;
    }

    private boolean participatesInFullConference(Connection connection) {
        return connection.getConference() != null && isFullConference(connection.getConference());
    }

    private void recalculateConferenceable() {
        int state;
        Log.v(this, "recalculateConferenceable : %d", Integer.valueOf(this.mTelephonyConnections.size()));
        HashSet<Connection> hashSet = new HashSet(this.mTelephonyConnections.size());
        for (TelephonyConnection telephonyConnection : this.mTelephonyConnections) {
            Log.d(this, "recalc - %s %s supportsConf? %s", Integer.valueOf(telephonyConnection.getState()), telephonyConnection, Boolean.valueOf(telephonyConnection.isConferenceSupported()));
            if (telephonyConnection.isConferenceSupported() && !participatesInFullConference(telephonyConnection) && ((state = telephonyConnection.getState()) == 4 || state == 5)) {
                hashSet.add(telephonyConnection);
            } else {
                telephonyConnection.setConferenceableConnections(Collections.emptyList());
            }
        }
        Log.v(this, "conferenceable: " + hashSet.size(), new Object[0]);
        for (final Connection connection : hashSet) {
            connection.setConferenceableConnections((List) hashSet.stream().filter(new Predicate() { // from class: com.android.services.telephony.-$$Lambda$TelephonyConferenceController$u5quPVONGOK8C9jvFYypc_dLXtM
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return TelephonyConferenceController.lambda$recalculateConferenceable$0(connection, (Connection) obj);
                }
            }).collect(Collectors.toList()));
        }
        Conference conference = this.mTelephonyConference;
        if (conference != null) {
            if (!isFullConference(conference)) {
                this.mTelephonyConference.setConferenceableConnections((List) this.mTelephonyConnections.stream().filter(new Predicate() { // from class: com.android.services.telephony.-$$Lambda$TelephonyConferenceController$q6S-emj_e5Oej_c826_IWFY1cgQ
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return TelephonyConferenceController.lambda$recalculateConferenceable$1((TelephonyConnection) obj);
                    }
                }).collect(Collectors.toList()));
            } else {
                Log.d(this, "cannot merge anymore due it is full", new Object[0]);
                this.mTelephonyConference.setConferenceableConnections(Collections.emptyList());
            }
        }
    }

    static /* synthetic */ boolean lambda$recalculateConferenceable$1(TelephonyConnection telephonyConnection) {
        return telephonyConnection.isConferenceSupported() && telephonyConnection.getConference() == null;
    }

    private void recalculateConference() {
        boolean z;
        HashSet<Connection> hashSet = new HashSet();
        int i = 0;
        for (TelephonyConnection telephonyConnection : this.mTelephonyConnections) {
            com.android.internal.telephony.Connection originalConnection = telephonyConnection.getOriginalConnection();
            if (originalConnection != null) {
                Call.State state = originalConnection.getState();
                Call call = originalConnection.getCall();
                if (state == Call.State.ACTIVE || state == Call.State.HOLDING) {
                    if (call != null && call.isMultiparty()) {
                        i++;
                        hashSet.add(telephonyConnection);
                    }
                }
            }
        }
        Log.d(this, "Recalculate conference calls %s %s.", this.mTelephonyConference, hashSet);
        Collection<Connection> allConnections = this.mConnectionService.getAllConnections();
        Iterator it = hashSet.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            Connection connection = (Connection) it.next();
            Log.v(this, "Finding connection in Connection Service for " + connection, new Object[0]);
            if (!allConnections.contains(connection)) {
                Log.v(this, "Finding connection in Connection Service Failed", new Object[0]);
                z = false;
                break;
            }
        }
        Log.d(this, "Is there a match for all connections in connection service " + z, new Object[0]);
        if (i < 2) {
            Log.d(this, "not enough connections to be a conference!", new Object[0]);
            if (this.mTelephonyConference != null) {
                Log.d(this, "with a conference to destroy!", new Object[0]);
                this.mTelephonyConference.destroyTelephonyConference();
                this.mTelephonyConference = null;
                return;
            }
            return;
        }
        TelephonyConference telephonyConference = this.mTelephonyConference;
        if (telephonyConference != null) {
            List<Connection> connections = telephonyConference.getConnections();
            for (Connection connection2 : connections) {
                if ((connection2 instanceof TelephonyConnection) && !hashSet.contains(connection2)) {
                    this.mTelephonyConference.removeTelephonyConnection(connection2);
                }
            }
            if (z) {
                this.mTriggerRecalculate = false;
                for (Connection connection3 : hashSet) {
                    if (!connections.contains(connection3)) {
                        this.mTelephonyConference.addTelephonyConnection(connection3);
                    }
                }
            } else {
                Log.d(this, "Trigger recalculate later", new Object[0]);
                this.mTriggerRecalculate = true;
            }
        } else if (z) {
            this.mTriggerRecalculate = false;
            this.mTelephonyConference = new TelephonyConference(hashSet.isEmpty() ? null : PhoneUtils.makePstnPhoneAccountHandle(((TelephonyConnection) hashSet.iterator().next()).getPhone()));
            for (Connection connection4 : hashSet) {
                Log.d(this, "Adding a connection to a conference call: %s %s", this.mTelephonyConference, connection4);
                this.mTelephonyConference.addTelephonyConnection(connection4);
            }
            this.mTelephonyConference.updateCallRadioTechAfterCreation();
            this.mConnectionService.addConference(this.mTelephonyConference);
        } else {
            Log.d(this, "Trigger recalculate later", new Object[0]);
            this.mTriggerRecalculate = true;
        }
        TelephonyConference telephonyConference2 = this.mTelephonyConference;
        if (telephonyConference2 != null) {
            Connection primaryConnection = telephonyConference2.getPrimaryConnection();
            Log.v(this, "Primary Conferenced connection is " + primaryConnection, new Object[0]);
            if (primaryConnection != null) {
                int state2 = primaryConnection.getState();
                if (state2 == 4) {
                    Log.v(this, "Setting conference to active", new Object[0]);
                    this.mTelephonyConference.setActive();
                } else {
                    if (state2 != 5) {
                        return;
                    }
                    Log.v(this, "Setting conference to hold", new Object[0]);
                    this.mTelephonyConference.setOnHold();
                }
            }
        }
    }
}
