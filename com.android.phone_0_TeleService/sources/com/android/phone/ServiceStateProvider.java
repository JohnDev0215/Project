package com.android.phone;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Parcel;
import android.provider.Telephony;
import android.telephony.ServiceState;
import android.telephony.SubscriptionManager;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ServiceStateProvider extends ContentProvider {
    private static final String[] sColumns;
    private final HashMap<Integer, ServiceState> mServiceStates = new HashMap<>();

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    static {
        Uri.parse("content://service-state");
        sColumns = new String[]{"voice_reg_state", "data_reg_state", "voice_roaming_type", "data_roaming_type", "voice_operator_alpha_long", "voice_operator_alpha_short", "voice_operator_numeric", "data_operator_alpha_long", "data_operator_alpha_short", "data_operator_numeric", "is_manual_network_selection", "ril_voice_radio_technology", "ril_data_radio_technology", "css_indicator", "network_id", "system_id", "cdma_roaming_indicator", "cdma_default_roaming_indicator", "cdma_eri_icon_index", "cdma_eri_icon_mode", "is_emergency_only", "is_using_carrier_aggregation", "operator_alpha_long_raw", "operator_alpha_short_raw"};
    }

    @VisibleForTesting
    public ServiceState getServiceState(int i) {
        return this.mServiceStates.get(Integer.valueOf(i));
    }

    @VisibleForTesting
    public int getDefaultSubId() {
        return SubscriptionManager.getDefaultSubscriptionId();
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        if (!isPathPrefixMatch(uri, Telephony.ServiceStateTable.CONTENT_URI)) {
            return null;
        }
        try {
            int defaultSubId = Integer.parseInt(uri.getLastPathSegment());
            Log.d("ServiceStateProvider", "subId=" + defaultSubId);
            if (defaultSubId == Integer.MAX_VALUE) {
                defaultSubId = getDefaultSubId();
            }
            Parcel parcelObtain = Parcel.obtain();
            byte[] asByteArray = contentValues.getAsByteArray("service_state");
            parcelObtain.unmarshall(asByteArray, 0, asByteArray.length);
            parcelObtain.setDataPosition(0);
            ServiceState serviceState = (ServiceState) ServiceState.CREATOR.createFromParcel(parcelObtain);
            ServiceState serviceState2 = getServiceState(defaultSubId);
            notifyChangeForSubIdAndField(getContext(), serviceState2, serviceState, defaultSubId);
            notifyChangeForSubId(getContext(), serviceState2, serviceState, defaultSubId);
            this.mServiceStates.put(Integer.valueOf(defaultSubId), serviceState);
            return uri;
        } catch (NumberFormatException e) {
            Log.e("ServiceStateProvider", "insert: no subId provided in uri");
            throw e;
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        throw new RuntimeException("Not supported");
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new RuntimeException("Not supported");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        throw new RuntimeException("Not supported");
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int defaultSubId;
        if (!isPathPrefixMatch(uri, Telephony.ServiceStateTable.CONTENT_URI)) {
            throw new IllegalArgumentException("Invalid URI: " + uri);
        }
        try {
            defaultSubId = Integer.parseInt(uri.getLastPathSegment());
        } catch (NumberFormatException unused) {
            Log.d("ServiceStateProvider", "query: no subId provided in uri, using default.");
            defaultSubId = getDefaultSubId();
        }
        Log.d("ServiceStateProvider", "subId=" + defaultSubId);
        if (defaultSubId == Integer.MAX_VALUE) {
            defaultSubId = getDefaultSubId();
        }
        ServiceState serviceState = getServiceState(defaultSubId);
        if (serviceState == null) {
            Log.d("ServiceStateProvider", "returning null");
            return null;
        }
        int state = serviceState.getState();
        int dataRegistrationState = serviceState.getDataRegistrationState();
        int voiceRoamingType = serviceState.getVoiceRoamingType();
        int dataRoamingType = serviceState.getDataRoamingType();
        return buildSingleRowResult(strArr, sColumns, new Object[]{Integer.valueOf(state), Integer.valueOf(dataRegistrationState), Integer.valueOf(voiceRoamingType), Integer.valueOf(dataRoamingType), serviceState.getOperatorAlphaLong(), serviceState.getOperatorAlphaShort(), serviceState.getOperatorNumeric(), serviceState.getOperatorAlphaLong(), serviceState.getOperatorAlphaShort(), serviceState.getOperatorNumeric(), Integer.valueOf(serviceState.getIsManualSelection() ? 1 : 0), Integer.valueOf(serviceState.getRilVoiceRadioTechnology()), Integer.valueOf(serviceState.getRilDataRadioTechnology()), Integer.valueOf(serviceState.getCssIndicator()), Integer.valueOf(serviceState.getCdmaNetworkId()), Integer.valueOf(serviceState.getCdmaSystemId()), Integer.valueOf(serviceState.getCdmaRoamingIndicator()), Integer.valueOf(serviceState.getCdmaDefaultRoamingIndicator()), Integer.valueOf(serviceState.getCdmaEriIconIndex()), Integer.valueOf(serviceState.getCdmaEriIconMode()), Integer.valueOf(serviceState.isEmergencyOnly() ? 1 : 0), Integer.valueOf(serviceState.isUsingCarrierAggregation() ? 1 : 0), serviceState.getOperatorAlphaLongRaw(), serviceState.getOperatorAlphaShortRaw()});
    }

    private static Cursor buildSingleRowResult(String[] strArr, String[] strArr2, Object[] objArr) {
        boolean z;
        if (strArr == null) {
            strArr = strArr2;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr, 1);
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        for (int i = 0; i < matrixCursor.getColumnCount(); i++) {
            String columnName = matrixCursor.getColumnName(i);
            int i2 = 0;
            while (true) {
                if (i2 >= strArr2.length) {
                    z = false;
                    break;
                }
                if (strArr2[i2].equals(columnName)) {
                    rowBuilderNewRow.add(objArr[i2]);
                    z = true;
                    break;
                }
                i2++;
            }
            if (!z) {
                throw new IllegalArgumentException("Invalid column " + strArr[i]);
            }
        }
        return matrixCursor;
    }

    @VisibleForTesting
    public static void notifyChangeForSubIdAndField(Context context, ServiceState serviceState, ServiceState serviceState2, int i) {
        boolean z = serviceState == null;
        if (z || voiceRegStateChanged(serviceState, serviceState2)) {
            context.getContentResolver().notifyChange(Telephony.ServiceStateTable.getUriForSubscriptionIdAndField(i, "voice_reg_state"), (ContentObserver) null, false);
        }
        if (z || dataRegStateChanged(serviceState, serviceState2)) {
            context.getContentResolver().notifyChange(Telephony.ServiceStateTable.getUriForSubscriptionIdAndField(i, "data_reg_state"), (ContentObserver) null, false);
        }
        if (z || voiceRoamingTypeChanged(serviceState, serviceState2)) {
            context.getContentResolver().notifyChange(Telephony.ServiceStateTable.getUriForSubscriptionIdAndField(i, "voice_roaming_type"), (ContentObserver) null, false);
        }
        if (z || dataRoamingTypeChanged(serviceState, serviceState2)) {
            context.getContentResolver().notifyChange(Telephony.ServiceStateTable.getUriForSubscriptionIdAndField(i, "data_roaming_type"), (ContentObserver) null, false);
        }
    }

    private static boolean voiceRegStateChanged(ServiceState serviceState, ServiceState serviceState2) {
        return serviceState.getState() != serviceState2.getState();
    }

    private static boolean dataRegStateChanged(ServiceState serviceState, ServiceState serviceState2) {
        return serviceState.getDataRegistrationState() != serviceState2.getDataRegistrationState();
    }

    private static boolean voiceRoamingTypeChanged(ServiceState serviceState, ServiceState serviceState2) {
        return serviceState.getVoiceRoamingType() != serviceState2.getVoiceRoamingType();
    }

    private static boolean dataRoamingTypeChanged(ServiceState serviceState, ServiceState serviceState2) {
        return serviceState.getDataRoamingType() != serviceState2.getDataRoamingType();
    }

    @VisibleForTesting
    public static void notifyChangeForSubId(Context context, ServiceState serviceState, ServiceState serviceState2, int i) {
        if (serviceState == null || voiceRegStateChanged(serviceState, serviceState2) || dataRegStateChanged(serviceState, serviceState2) || voiceRoamingTypeChanged(serviceState, serviceState2) || dataRoamingTypeChanged(serviceState, serviceState2)) {
            context.getContentResolver().notifyChange(Telephony.ServiceStateTable.getUriForSubscriptionId(i), (ContentObserver) null, false);
        }
    }

    private boolean isPathPrefixMatch(Uri uri, Uri uri2) {
        if (!Objects.equals(uri.getScheme(), uri2.getScheme()) || !Objects.equals(uri.getAuthority(), uri2.getAuthority())) {
            return false;
        }
        List<String> pathSegments = uri.getPathSegments();
        List<String> pathSegments2 = uri2.getPathSegments();
        int size = pathSegments2.size();
        if (pathSegments.size() < size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(pathSegments.get(i), pathSegments2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
