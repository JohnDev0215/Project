package com.android.services.telephony;

import android.os.Handler;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import com.android.phone.PhoneUtils;
import com.android.services.telephony.TelephonyConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class CdmaConferenceController {
    private CdmaConference mConference;
    private final TelephonyConnectionService mConnectionService;
    private final Handler mHandler;
    private final TelephonyConnection.TelephonyConnectionListener mTelephonyConnectionListener = new TelephonyConnection.TelephonyConnectionListener() { // from class: com.android.services.telephony.CdmaConferenceController.1
        @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
        public void onStateChanged(Connection connection, int i) {
            CdmaConferenceController.this.recalculateConference();
        }

        @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
        public void onDisconnected(Connection connection, DisconnectCause disconnectCause) {
            CdmaConferenceController.this.recalculateConference();
        }

        @Override // com.android.services.telephony.TelephonyConnection.TelephonyConnectionListener
        public void onDestroyed(Connection connection) {
            CdmaConferenceController.this.remove((CdmaConnection) connection);
        }
    };
    private final List<CdmaConnection> mCdmaConnections = new ArrayList();

    public CdmaConferenceController(TelephonyConnectionService telephonyConnectionService) {
        new ArrayList();
        this.mHandler = new Handler();
        this.mConnectionService = telephonyConnectionService;
    }

    void add(final CdmaConnection cdmaConnection) {
        if (this.mCdmaConnections.contains(cdmaConnection)) {
            Log.w(this, "add - connection already tracked; connection=%s", cdmaConnection);
            return;
        }
        if (!this.mCdmaConnections.isEmpty() && cdmaConnection.isOutgoingCall()) {
            cdmaConnection.forceAsDialing(true);
            final ArrayList arrayList = new ArrayList(this.mCdmaConnections.size());
            for (CdmaConnection cdmaConnection2 : this.mCdmaConnections) {
                if (cdmaConnection2.setHoldingForConference()) {
                    arrayList.add(cdmaConnection2);
                }
            }
            this.mHandler.postDelayed(new Runnable() { // from class: com.android.services.telephony.CdmaConferenceController.2
                @Override // java.lang.Runnable
                public void run() {
                    cdmaConnection.forceAsDialing(false);
                    CdmaConferenceController.this.lambda$add$0(cdmaConnection);
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((CdmaConnection) it.next()).resetStateForConference();
                    }
                }
            }, 6000L);
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.android.services.telephony.-$$Lambda$CdmaConferenceController$ZfgEA3wIDs0JYE6Y4er2e9egZcU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$add$0$CdmaConferenceController(cdmaConnection);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$add$0$CdmaConferenceController(CdmaConnection cdmaConnection) {
        this.mCdmaConnections.add(cdmaConnection);
        cdmaConnection.addTelephonyConnectionListener(this.mTelephonyConnectionListener);
        recalculateConference();
    }

    void remove(CdmaConnection cdmaConnection) {
        if (!this.mCdmaConnections.contains(cdmaConnection)) {
            Log.d(this, "remove - connection not tracked; connection=%s", cdmaConnection);
            return;
        }
        cdmaConnection.removeTelephonyConnectionListener(this.mTelephonyConnectionListener);
        this.mCdmaConnections.remove(cdmaConnection);
        recalculateConference();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recalculateConference() {
        boolean z;
        ArrayList<CdmaConnection> arrayList = new ArrayList(this.mCdmaConnections.size());
        for (CdmaConnection cdmaConnection : this.mCdmaConnections) {
            if (!cdmaConnection.isCallWaiting() && cdmaConnection.getState() != 6) {
                arrayList.add(cdmaConnection);
            }
        }
        Log.d(this, "recalculating conference calls %d", Integer.valueOf(arrayList.size()));
        if (arrayList.size() >= 2) {
            List<CdmaConnection> list = this.mCdmaConnections;
            CdmaConnection cdmaConnection2 = list.get(list.size() - 1);
            if (this.mConference == null) {
                Log.i(this, "Creating new Cdma conference call", new Object[0]);
                this.mConference = new CdmaConference(PhoneUtils.makePstnPhoneAccountHandle(cdmaConnection2.getPhone()));
                z = true;
            } else {
                z = false;
            }
            if (cdmaConnection2.isOutgoingCall()) {
                this.mConference.updateCapabilities(4);
            } else {
                this.mConference.updateCapabilities(8);
            }
            ArrayList<Connection> arrayList2 = new ArrayList(this.mConference.getConnections());
            for (CdmaConnection cdmaConnection3 : arrayList) {
                if (!arrayList2.contains(cdmaConnection3)) {
                    Log.i(this, "Adding connection to conference call: %s", cdmaConnection3);
                    this.mConference.addTelephonyConnection(cdmaConnection3);
                }
                arrayList2.remove(cdmaConnection3);
            }
            for (Connection connection : arrayList2) {
                this.mConference.removeTelephonyConnection(connection);
                Log.i(this, "Removing connection from conference call: %s", connection);
            }
            if (z) {
                Log.d(this, "Adding the conference call", new Object[0]);
                this.mConference.updateCallRadioTechAfterCreation();
                this.mConnectionService.addTelephonyConference(this.mConference);
                return;
            }
            return;
        }
        if (!arrayList.isEmpty() || this.mConference == null) {
            return;
        }
        Log.i(this, "Destroying the CDMA conference connection.", new Object[0]);
        this.mConference.destroyTelephonyConference();
        this.mConference = null;
    }
}
