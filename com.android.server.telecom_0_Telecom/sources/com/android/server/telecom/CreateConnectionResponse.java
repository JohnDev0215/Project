package com.android.server.telecom;

import android.telecom.DisconnectCause;
import android.telecom.ParcelableConference;
import android.telecom.ParcelableConnection;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public interface CreateConnectionResponse {
    void handleCreateConferenceFailure(DisconnectCause disconnectCause);

    void handleCreateConferenceSuccess(CallIdMapper callIdMapper, ParcelableConference parcelableConference);

    void handleCreateConnectionFailure(DisconnectCause disconnectCause);

    void handleCreateConnectionSuccess(CallIdMapper callIdMapper, ParcelableConnection parcelableConnection);
}
