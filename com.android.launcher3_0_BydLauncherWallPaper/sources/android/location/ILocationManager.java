package android.location;

import android.app.PendingIntent;
import android.location.IBatchedLocationCallback;
import android.location.IGnssAntennaInfoListener;
import android.location.IGnssMeasurementsListener;
import android.location.IGnssNavigationMessageListener;
import android.location.IGnssStatusListener;
import android.location.ILocationListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.location.ProviderProperties;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface ILocationManager extends IInterface {

    public static class Default implements ILocationManager {
        @Override // android.location.ILocationManager
        public boolean addGnssAntennaInfoListener(IGnssAntennaInfoListener iGnssAntennaInfoListener, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public boolean addGnssBatchingCallback(IBatchedLocationCallback iBatchedLocationCallback, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public boolean addGnssMeasurementsListener(GnssRequest gnssRequest, IGnssMeasurementsListener iGnssMeasurementsListener, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public boolean addGnssNavigationMessageListener(IGnssNavigationMessageListener iGnssNavigationMessageListener, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public void addTestProvider(String str, ProviderProperties providerProperties, String str2, String str3) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.location.ILocationManager
        public void flushGnssBatch(String str) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public boolean geocoderIsPresent() throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public List<String> getAllProviders() throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public String[] getBackgroundThrottlingWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public String getBestProvider(Criteria criteria, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public ICancellationSignal getCurrentLocation(LocationRequest locationRequest, ILocationListener iLocationListener, String str, String str2, String str3) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public String getExtraLocationControllerPackage() throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public String getFromLocation(double d, double d2, int i, GeocoderParams geocoderParams, List<Address> list) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public String getFromLocationName(String str, double d, double d2, double d3, double d4, int i, GeocoderParams geocoderParams, List<Address> list) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public int getGnssBatchSize(String str) throws RemoteException {
            return 0;
        }

        @Override // android.location.ILocationManager
        public long getGnssCapabilities() throws RemoteException {
            return 0L;
        }

        @Override // android.location.ILocationManager
        public String getGnssHardwareModelName() throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public LocationTime getGnssTimeMillis() throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public int getGnssYearOfHardware() throws RemoteException {
            return 0;
        }

        @Override // android.location.ILocationManager
        public String[] getIgnoreSettingsWhitelist() throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public Location getLastLocation(LocationRequest locationRequest, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public List<String> getProviderPackages(String str) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public ProviderProperties getProviderProperties(String str) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public List<String> getProviders(Criteria criteria, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public List<LocationRequest> getTestProviderCurrentRequests(String str) throws RemoteException {
            return null;
        }

        @Override // android.location.ILocationManager
        public void injectGnssMeasurementCorrections(GnssMeasurementCorrections gnssMeasurementCorrections, String str) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void injectLocation(Location location) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public boolean isExtraLocationControllerPackageEnabled() throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public boolean isLocationEnabledForUser(int i) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public boolean isProviderEnabledForUser(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public boolean isProviderPackage(String str) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public void locationCallbackFinished(ILocationListener iLocationListener) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public boolean registerGnssStatusCallback(IGnssStatusListener iGnssStatusListener, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public void removeGeofence(Geofence geofence, PendingIntent pendingIntent, String str) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void removeGnssAntennaInfoListener(IGnssAntennaInfoListener iGnssAntennaInfoListener) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void removeGnssBatchingCallback() throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void removeGnssMeasurementsListener(IGnssMeasurementsListener iGnssMeasurementsListener) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void removeGnssNavigationMessageListener(IGnssNavigationMessageListener iGnssNavigationMessageListener) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void removeTestProvider(String str, String str2, String str3) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void removeUpdates(ILocationListener iLocationListener, PendingIntent pendingIntent) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void requestGeofence(LocationRequest locationRequest, Geofence geofence, PendingIntent pendingIntent, String str, String str2) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void requestLocationUpdates(LocationRequest locationRequest, ILocationListener iLocationListener, PendingIntent pendingIntent, String str, String str2, String str3) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public boolean sendExtraCommand(String str, String str2, Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public void setExtraLocationControllerPackage(String str) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void setExtraLocationControllerPackageEnabled(boolean z) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void setLocationEnabledForUser(boolean z, int i) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void setTestProviderEnabled(String str, boolean z, String str2, String str3) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public void setTestProviderLocation(String str, Location location, String str2, String str3) throws RemoteException {
        }

        @Override // android.location.ILocationManager
        public boolean startGnssBatch(long j, boolean z, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public boolean stopGnssBatch() throws RemoteException {
            return false;
        }

        @Override // android.location.ILocationManager
        public void unregisterGnssStatusCallback(IGnssStatusListener iGnssStatusListener) throws RemoteException {
        }
    }

    boolean addGnssAntennaInfoListener(IGnssAntennaInfoListener iGnssAntennaInfoListener, String str, String str2) throws RemoteException;

    boolean addGnssBatchingCallback(IBatchedLocationCallback iBatchedLocationCallback, String str, String str2) throws RemoteException;

    boolean addGnssMeasurementsListener(GnssRequest gnssRequest, IGnssMeasurementsListener iGnssMeasurementsListener, String str, String str2) throws RemoteException;

    boolean addGnssNavigationMessageListener(IGnssNavigationMessageListener iGnssNavigationMessageListener, String str, String str2) throws RemoteException;

    void addTestProvider(String str, ProviderProperties providerProperties, String str2, String str3) throws RemoteException;

    void flushGnssBatch(String str) throws RemoteException;

    boolean geocoderIsPresent() throws RemoteException;

    List<String> getAllProviders() throws RemoteException;

    String[] getBackgroundThrottlingWhitelist() throws RemoteException;

    String getBestProvider(Criteria criteria, boolean z) throws RemoteException;

    ICancellationSignal getCurrentLocation(LocationRequest locationRequest, ILocationListener iLocationListener, String str, String str2, String str3) throws RemoteException;

    String getExtraLocationControllerPackage() throws RemoteException;

    String getFromLocation(double d, double d2, int i, GeocoderParams geocoderParams, List<Address> list) throws RemoteException;

    String getFromLocationName(String str, double d, double d2, double d3, double d4, int i, GeocoderParams geocoderParams, List<Address> list) throws RemoteException;

    int getGnssBatchSize(String str) throws RemoteException;

    long getGnssCapabilities() throws RemoteException;

    String getGnssHardwareModelName() throws RemoteException;

    LocationTime getGnssTimeMillis() throws RemoteException;

    int getGnssYearOfHardware() throws RemoteException;

    String[] getIgnoreSettingsWhitelist() throws RemoteException;

    Location getLastLocation(LocationRequest locationRequest, String str, String str2) throws RemoteException;

    List<String> getProviderPackages(String str) throws RemoteException;

    ProviderProperties getProviderProperties(String str) throws RemoteException;

    List<String> getProviders(Criteria criteria, boolean z) throws RemoteException;

    List<LocationRequest> getTestProviderCurrentRequests(String str) throws RemoteException;

    void injectGnssMeasurementCorrections(GnssMeasurementCorrections gnssMeasurementCorrections, String str) throws RemoteException;

    void injectLocation(Location location) throws RemoteException;

    boolean isExtraLocationControllerPackageEnabled() throws RemoteException;

    boolean isLocationEnabledForUser(int i) throws RemoteException;

    boolean isProviderEnabledForUser(String str, int i) throws RemoteException;

    boolean isProviderPackage(String str) throws RemoteException;

    void locationCallbackFinished(ILocationListener iLocationListener) throws RemoteException;

    boolean registerGnssStatusCallback(IGnssStatusListener iGnssStatusListener, String str, String str2) throws RemoteException;

    void removeGeofence(Geofence geofence, PendingIntent pendingIntent, String str) throws RemoteException;

    void removeGnssAntennaInfoListener(IGnssAntennaInfoListener iGnssAntennaInfoListener) throws RemoteException;

    void removeGnssBatchingCallback() throws RemoteException;

    void removeGnssMeasurementsListener(IGnssMeasurementsListener iGnssMeasurementsListener) throws RemoteException;

    void removeGnssNavigationMessageListener(IGnssNavigationMessageListener iGnssNavigationMessageListener) throws RemoteException;

    void removeTestProvider(String str, String str2, String str3) throws RemoteException;

    void removeUpdates(ILocationListener iLocationListener, PendingIntent pendingIntent) throws RemoteException;

    void requestGeofence(LocationRequest locationRequest, Geofence geofence, PendingIntent pendingIntent, String str, String str2) throws RemoteException;

    void requestLocationUpdates(LocationRequest locationRequest, ILocationListener iLocationListener, PendingIntent pendingIntent, String str, String str2, String str3) throws RemoteException;

    boolean sendExtraCommand(String str, String str2, Bundle bundle) throws RemoteException;

    void setExtraLocationControllerPackage(String str) throws RemoteException;

    void setExtraLocationControllerPackageEnabled(boolean z) throws RemoteException;

    void setLocationEnabledForUser(boolean z, int i) throws RemoteException;

    void setTestProviderEnabled(String str, boolean z, String str2, String str3) throws RemoteException;

    void setTestProviderLocation(String str, Location location, String str2, String str3) throws RemoteException;

    boolean startGnssBatch(long j, boolean z, String str, String str2) throws RemoteException;

    boolean stopGnssBatch() throws RemoteException;

    void unregisterGnssStatusCallback(IGnssStatusListener iGnssStatusListener) throws RemoteException;

    public static abstract class Stub extends Binder implements ILocationManager {
        private static final String DESCRIPTOR = "android.location.ILocationManager";
        static final int TRANSACTION_addGnssAntennaInfoListener = 16;
        static final int TRANSACTION_addGnssBatchingCallback = 23;
        static final int TRANSACTION_addGnssMeasurementsListener = 12;
        static final int TRANSACTION_addGnssNavigationMessageListener = 18;
        static final int TRANSACTION_addTestProvider = 42;
        static final int TRANSACTION_flushGnssBatch = 26;
        static final int TRANSACTION_geocoderIsPresent = 9;
        static final int TRANSACTION_getAllProviders = 29;
        static final int TRANSACTION_getBackgroundThrottlingWhitelist = 50;
        static final int TRANSACTION_getBestProvider = 31;
        static final int TRANSACTION_getCurrentLocation = 2;
        static final int TRANSACTION_getExtraLocationControllerPackage = 36;
        static final int TRANSACTION_getFromLocation = 10;
        static final int TRANSACTION_getFromLocationName = 11;
        static final int TRANSACTION_getGnssBatchSize = 22;
        static final int TRANSACTION_getGnssCapabilities = 14;
        static final int TRANSACTION_getGnssHardwareModelName = 21;
        static final int TRANSACTION_getGnssTimeMillis = 47;
        static final int TRANSACTION_getGnssYearOfHardware = 20;
        static final int TRANSACTION_getIgnoreSettingsWhitelist = 51;
        static final int TRANSACTION_getLastLocation = 1;
        static final int TRANSACTION_getProviderPackages = 34;
        static final int TRANSACTION_getProviderProperties = 32;
        static final int TRANSACTION_getProviders = 30;
        static final int TRANSACTION_getTestProviderCurrentRequests = 46;
        static final int TRANSACTION_injectGnssMeasurementCorrections = 13;
        static final int TRANSACTION_injectLocation = 28;
        static final int TRANSACTION_isExtraLocationControllerPackageEnabled = 38;
        static final int TRANSACTION_isLocationEnabledForUser = 40;
        static final int TRANSACTION_isProviderEnabledForUser = 39;
        static final int TRANSACTION_isProviderPackage = 33;
        static final int TRANSACTION_locationCallbackFinished = 49;
        static final int TRANSACTION_registerGnssStatusCallback = 7;
        static final int TRANSACTION_removeGeofence = 6;
        static final int TRANSACTION_removeGnssAntennaInfoListener = 17;
        static final int TRANSACTION_removeGnssBatchingCallback = 24;
        static final int TRANSACTION_removeGnssMeasurementsListener = 15;
        static final int TRANSACTION_removeGnssNavigationMessageListener = 19;
        static final int TRANSACTION_removeTestProvider = 43;
        static final int TRANSACTION_removeUpdates = 4;
        static final int TRANSACTION_requestGeofence = 5;
        static final int TRANSACTION_requestLocationUpdates = 3;
        static final int TRANSACTION_sendExtraCommand = 48;
        static final int TRANSACTION_setExtraLocationControllerPackage = 35;
        static final int TRANSACTION_setExtraLocationControllerPackageEnabled = 37;
        static final int TRANSACTION_setLocationEnabledForUser = 41;
        static final int TRANSACTION_setTestProviderEnabled = 45;
        static final int TRANSACTION_setTestProviderLocation = 44;
        static final int TRANSACTION_startGnssBatch = 25;
        static final int TRANSACTION_stopGnssBatch = 27;
        static final int TRANSACTION_unregisterGnssStatusCallback = 8;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getLastLocation";
                case 2:
                    return "getCurrentLocation";
                case 3:
                    return "requestLocationUpdates";
                case 4:
                    return "removeUpdates";
                case 5:
                    return "requestGeofence";
                case 6:
                    return "removeGeofence";
                case 7:
                    return "registerGnssStatusCallback";
                case 8:
                    return "unregisterGnssStatusCallback";
                case 9:
                    return "geocoderIsPresent";
                case 10:
                    return "getFromLocation";
                case 11:
                    return "getFromLocationName";
                case 12:
                    return "addGnssMeasurementsListener";
                case 13:
                    return "injectGnssMeasurementCorrections";
                case 14:
                    return "getGnssCapabilities";
                case 15:
                    return "removeGnssMeasurementsListener";
                case 16:
                    return "addGnssAntennaInfoListener";
                case 17:
                    return "removeGnssAntennaInfoListener";
                case 18:
                    return "addGnssNavigationMessageListener";
                case 19:
                    return "removeGnssNavigationMessageListener";
                case 20:
                    return "getGnssYearOfHardware";
                case 21:
                    return "getGnssHardwareModelName";
                case 22:
                    return "getGnssBatchSize";
                case 23:
                    return "addGnssBatchingCallback";
                case 24:
                    return "removeGnssBatchingCallback";
                case 25:
                    return "startGnssBatch";
                case 26:
                    return "flushGnssBatch";
                case 27:
                    return "stopGnssBatch";
                case 28:
                    return "injectLocation";
                case 29:
                    return "getAllProviders";
                case 30:
                    return "getProviders";
                case 31:
                    return "getBestProvider";
                case 32:
                    return "getProviderProperties";
                case 33:
                    return "isProviderPackage";
                case 34:
                    return "getProviderPackages";
                case 35:
                    return "setExtraLocationControllerPackage";
                case 36:
                    return "getExtraLocationControllerPackage";
                case 37:
                    return "setExtraLocationControllerPackageEnabled";
                case 38:
                    return "isExtraLocationControllerPackageEnabled";
                case 39:
                    return "isProviderEnabledForUser";
                case 40:
                    return "isLocationEnabledForUser";
                case 41:
                    return "setLocationEnabledForUser";
                case 42:
                    return "addTestProvider";
                case 43:
                    return "removeTestProvider";
                case 44:
                    return "setTestProviderLocation";
                case 45:
                    return "setTestProviderEnabled";
                case 46:
                    return "getTestProviderCurrentRequests";
                case 47:
                    return "getGnssTimeMillis";
                case 48:
                    return "sendExtraCommand";
                case 49:
                    return "locationCallbackFinished";
                case 50:
                    return "getBackgroundThrottlingWhitelist";
                case 51:
                    return "getIgnoreSettingsWhitelist";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocationManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ILocationManager)) {
                return (ILocationManager) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    Location lastLocation = getLastLocation(parcel.readInt() != 0 ? LocationRequest.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (lastLocation != null) {
                        parcel2.writeInt(1);
                        lastLocation.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    ICancellationSignal currentLocation = getCurrentLocation(parcel.readInt() != 0 ? LocationRequest.CREATOR.createFromParcel(parcel) : null, ILocationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(currentLocation != null ? currentLocation.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestLocationUpdates(parcel.readInt() != 0 ? LocationRequest.CREATOR.createFromParcel(parcel) : null, ILocationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeUpdates(ILocationListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestGeofence(parcel.readInt() != 0 ? LocationRequest.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Geofence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeGeofence(parcel.readInt() != 0 ? Geofence.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterGnssStatusCallback = registerGnssStatusCallback(IGnssStatusListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterGnssStatusCallback ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterGnssStatusCallback(IGnssStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zGeocoderIsPresent = geocoderIsPresent();
                    parcel2.writeNoException();
                    parcel2.writeInt(zGeocoderIsPresent ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    double d = parcel.readDouble();
                    double d2 = parcel.readDouble();
                    int i3 = parcel.readInt();
                    GeocoderParams geocoderParamsCreateFromParcel = parcel.readInt() != 0 ? GeocoderParams.CREATOR.createFromParcel(parcel) : null;
                    ArrayList arrayList = new ArrayList();
                    String fromLocation = getFromLocation(d, d2, i3, geocoderParamsCreateFromParcel, arrayList);
                    parcel2.writeNoException();
                    parcel2.writeString(fromLocation);
                    parcel2.writeTypedList(arrayList);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string = parcel.readString();
                    double d3 = parcel.readDouble();
                    double d4 = parcel.readDouble();
                    double d5 = parcel.readDouble();
                    double d6 = parcel.readDouble();
                    int i4 = parcel.readInt();
                    GeocoderParams geocoderParamsCreateFromParcel2 = parcel.readInt() != 0 ? GeocoderParams.CREATOR.createFromParcel(parcel) : null;
                    ArrayList arrayList2 = new ArrayList();
                    String fromLocationName = getFromLocationName(string, d3, d4, d5, d6, i4, geocoderParamsCreateFromParcel2, arrayList2);
                    parcel2.writeNoException();
                    parcel2.writeString(fromLocationName);
                    parcel2.writeTypedList(arrayList2);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddGnssMeasurementsListener = addGnssMeasurementsListener(parcel.readInt() != 0 ? GnssRequest.CREATOR.createFromParcel(parcel) : null, IGnssMeasurementsListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddGnssMeasurementsListener ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    injectGnssMeasurementCorrections(parcel.readInt() != 0 ? GnssMeasurementCorrections.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    long gnssCapabilities = getGnssCapabilities();
                    parcel2.writeNoException();
                    parcel2.writeLong(gnssCapabilities);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeGnssMeasurementsListener(IGnssMeasurementsListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddGnssAntennaInfoListener = addGnssAntennaInfoListener(IGnssAntennaInfoListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddGnssAntennaInfoListener ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeGnssAntennaInfoListener(IGnssAntennaInfoListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddGnssNavigationMessageListener = addGnssNavigationMessageListener(IGnssNavigationMessageListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddGnssNavigationMessageListener ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeGnssNavigationMessageListener(IGnssNavigationMessageListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    int gnssYearOfHardware = getGnssYearOfHardware();
                    parcel2.writeNoException();
                    parcel2.writeInt(gnssYearOfHardware);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    String gnssHardwareModelName = getGnssHardwareModelName();
                    parcel2.writeNoException();
                    parcel2.writeString(gnssHardwareModelName);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int gnssBatchSize = getGnssBatchSize(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(gnssBatchSize);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddGnssBatchingCallback = addGnssBatchingCallback(IBatchedLocationCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddGnssBatchingCallback ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeGnssBatchingCallback();
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStartGnssBatch = startGnssBatch(parcel.readLong(), parcel.readInt() != 0, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartGnssBatch ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    flushGnssBatch(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStopGnssBatch = stopGnssBatch();
                    parcel2.writeNoException();
                    parcel2.writeInt(zStopGnssBatch ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    injectLocation(parcel.readInt() != 0 ? Location.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> allProviders = getAllProviders();
                    parcel2.writeNoException();
                    parcel2.writeStringList(allProviders);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> providers = getProviders(parcel.readInt() != 0 ? Criteria.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeStringList(providers);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    String bestProvider = getBestProvider(parcel.readInt() != 0 ? Criteria.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeString(bestProvider);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    ProviderProperties providerProperties = getProviderProperties(parcel.readString());
                    parcel2.writeNoException();
                    if (providerProperties != null) {
                        parcel2.writeInt(1);
                        providerProperties.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsProviderPackage = isProviderPackage(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsProviderPackage ? 1 : 0);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> providerPackages = getProviderPackages(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringList(providerPackages);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    setExtraLocationControllerPackage(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    String extraLocationControllerPackage = getExtraLocationControllerPackage();
                    parcel2.writeNoException();
                    parcel2.writeString(extraLocationControllerPackage);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    setExtraLocationControllerPackageEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsExtraLocationControllerPackageEnabled = isExtraLocationControllerPackageEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsExtraLocationControllerPackageEnabled ? 1 : 0);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsProviderEnabledForUser = isProviderEnabledForUser(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsProviderEnabledForUser ? 1 : 0);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsLocationEnabledForUser = isLocationEnabledForUser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsLocationEnabledForUser ? 1 : 0);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLocationEnabledForUser(parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    addTestProvider(parcel.readString(), parcel.readInt() != 0 ? ProviderProperties.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeTestProvider(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTestProviderLocation(parcel.readString(), parcel.readInt() != 0 ? Location.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTestProviderEnabled(parcel.readString(), parcel.readInt() != 0, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<LocationRequest> testProviderCurrentRequests = getTestProviderCurrentRequests(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(testProviderCurrentRequests);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    LocationTime gnssTimeMillis = getGnssTimeMillis();
                    parcel2.writeNoException();
                    if (gnssTimeMillis != null) {
                        parcel2.writeInt(1);
                        gnssTimeMillis.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string2 = parcel.readString();
                    String string3 = parcel.readString();
                    Bundle bundleCreateFromParcel = parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null;
                    boolean zSendExtraCommand = sendExtraCommand(string2, string3, bundleCreateFromParcel);
                    parcel2.writeNoException();
                    parcel2.writeInt(zSendExtraCommand ? 1 : 0);
                    if (bundleCreateFromParcel != null) {
                        parcel2.writeInt(1);
                        bundleCreateFromParcel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    locationCallbackFinished(ILocationListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] backgroundThrottlingWhitelist = getBackgroundThrottlingWhitelist();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(backgroundThrottlingWhitelist);
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] ignoreSettingsWhitelist = getIgnoreSettingsWhitelist();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(ignoreSettingsWhitelist);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ILocationManager {
            public static ILocationManager sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.location.ILocationManager
            public Location getLastLocation(LocationRequest locationRequest, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (locationRequest != null) {
                        parcelObtain.writeInt(1);
                        locationRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastLocation(locationRequest, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Location.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public ICancellationSignal getCurrentLocation(LocationRequest locationRequest, ILocationListener iLocationListener, String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (locationRequest != null) {
                        parcelObtain.writeInt(1);
                        locationRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iLocationListener != null ? iLocationListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentLocation(locationRequest, iLocationListener, str, str2, str3);
                    }
                    parcelObtain2.readException();
                    return ICancellationSignal.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void requestLocationUpdates(LocationRequest locationRequest, ILocationListener iLocationListener, PendingIntent pendingIntent, String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (locationRequest != null) {
                        parcelObtain.writeInt(1);
                        locationRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iLocationListener != null ? iLocationListener.asBinder() : null);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestLocationUpdates(locationRequest, iLocationListener, pendingIntent, str, str2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void removeUpdates(ILocationListener iLocationListener, PendingIntent pendingIntent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iLocationListener != null ? iLocationListener.asBinder() : null);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeUpdates(iLocationListener, pendingIntent);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void requestGeofence(LocationRequest locationRequest, Geofence geofence, PendingIntent pendingIntent, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (locationRequest != null) {
                        parcelObtain.writeInt(1);
                        locationRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (geofence != null) {
                        parcelObtain.writeInt(1);
                        geofence.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestGeofence(locationRequest, geofence, pendingIntent, str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void removeGeofence(Geofence geofence, PendingIntent pendingIntent, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (geofence != null) {
                        parcelObtain.writeInt(1);
                        geofence.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeGeofence(geofence, pendingIntent, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean registerGnssStatusCallback(IGnssStatusListener iGnssStatusListener, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGnssStatusListener != null ? iGnssStatusListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerGnssStatusCallback(iGnssStatusListener, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void unregisterGnssStatusCallback(IGnssStatusListener iGnssStatusListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGnssStatusListener != null ? iGnssStatusListener.asBinder() : null);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterGnssStatusCallback(iGnssStatusListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean geocoderIsPresent() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().geocoderIsPresent();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public String getFromLocation(double d, double d2, int i, GeocoderParams geocoderParams, List<Address> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeDouble(d);
                    parcelObtain.writeDouble(d2);
                    parcelObtain.writeInt(i);
                    if (geocoderParams != null) {
                        parcelObtain.writeInt(1);
                        geocoderParams.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFromLocation(d, d2, i, geocoderParams, list);
                    }
                    parcelObtain2.readException();
                    String string = parcelObtain2.readString();
                    parcelObtain2.readTypedList(list, Address.CREATOR);
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public String getFromLocationName(String str, double d, double d2, double d3, double d4, int i, GeocoderParams geocoderParams, List<Address> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeDouble(d);
                    parcelObtain.writeDouble(d2);
                    parcelObtain.writeDouble(d3);
                    parcelObtain.writeDouble(d4);
                    parcelObtain.writeInt(i);
                    if (geocoderParams != null) {
                        parcelObtain.writeInt(1);
                        geocoderParams.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFromLocationName(str, d, d2, d3, d4, i, geocoderParams, list);
                    }
                    parcelObtain2.readException();
                    String string = parcelObtain2.readString();
                    parcelObtain2.readTypedList(list, Address.CREATOR);
                    return string;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean addGnssMeasurementsListener(GnssRequest gnssRequest, IGnssMeasurementsListener iGnssMeasurementsListener, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (gnssRequest != null) {
                        parcelObtain.writeInt(1);
                        gnssRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iGnssMeasurementsListener != null ? iGnssMeasurementsListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addGnssMeasurementsListener(gnssRequest, iGnssMeasurementsListener, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void injectGnssMeasurementCorrections(GnssMeasurementCorrections gnssMeasurementCorrections, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (gnssMeasurementCorrections != null) {
                        parcelObtain.writeInt(1);
                        gnssMeasurementCorrections.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().injectGnssMeasurementCorrections(gnssMeasurementCorrections, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public long getGnssCapabilities() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGnssCapabilities();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void removeGnssMeasurementsListener(IGnssMeasurementsListener iGnssMeasurementsListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGnssMeasurementsListener != null ? iGnssMeasurementsListener.asBinder() : null);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeGnssMeasurementsListener(iGnssMeasurementsListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean addGnssAntennaInfoListener(IGnssAntennaInfoListener iGnssAntennaInfoListener, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGnssAntennaInfoListener != null ? iGnssAntennaInfoListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addGnssAntennaInfoListener(iGnssAntennaInfoListener, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void removeGnssAntennaInfoListener(IGnssAntennaInfoListener iGnssAntennaInfoListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGnssAntennaInfoListener != null ? iGnssAntennaInfoListener.asBinder() : null);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeGnssAntennaInfoListener(iGnssAntennaInfoListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean addGnssNavigationMessageListener(IGnssNavigationMessageListener iGnssNavigationMessageListener, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGnssNavigationMessageListener != null ? iGnssNavigationMessageListener.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addGnssNavigationMessageListener(iGnssNavigationMessageListener, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void removeGnssNavigationMessageListener(IGnssNavigationMessageListener iGnssNavigationMessageListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGnssNavigationMessageListener != null ? iGnssNavigationMessageListener.asBinder() : null);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeGnssNavigationMessageListener(iGnssNavigationMessageListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public int getGnssYearOfHardware() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGnssYearOfHardware();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public String getGnssHardwareModelName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGnssHardwareModelName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public int getGnssBatchSize(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGnssBatchSize(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean addGnssBatchingCallback(IBatchedLocationCallback iBatchedLocationCallback, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBatchedLocationCallback != null ? iBatchedLocationCallback.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addGnssBatchingCallback(iBatchedLocationCallback, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void removeGnssBatchingCallback() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeGnssBatchingCallback();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean startGnssBatch(long j, boolean z, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startGnssBatch(j, z, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void flushGnssBatch(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().flushGnssBatch(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean stopGnssBatch() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopGnssBatch();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void injectLocation(Location location) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (location != null) {
                        parcelObtain.writeInt(1);
                        location.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().injectLocation(location);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public List<String> getAllProviders() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllProviders();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public List<String> getProviders(Criteria criteria, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (criteria != null) {
                        parcelObtain.writeInt(1);
                        criteria.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProviders(criteria, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public String getBestProvider(Criteria criteria, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (criteria != null) {
                        parcelObtain.writeInt(1);
                        criteria.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBestProvider(criteria, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public ProviderProperties getProviderProperties(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProviderProperties(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ProviderProperties.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean isProviderPackage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isProviderPackage(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public List<String> getProviderPackages(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProviderPackages(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void setExtraLocationControllerPackage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setExtraLocationControllerPackage(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public String getExtraLocationControllerPackage() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getExtraLocationControllerPackage();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void setExtraLocationControllerPackageEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setExtraLocationControllerPackageEnabled(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean isExtraLocationControllerPackageEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isExtraLocationControllerPackageEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean isProviderEnabledForUser(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isProviderEnabledForUser(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean isLocationEnabledForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLocationEnabledForUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void setLocationEnabledForUser(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLocationEnabledForUser(z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void addTestProvider(String str, ProviderProperties providerProperties, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (providerProperties != null) {
                        parcelObtain.writeInt(1);
                        providerProperties.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addTestProvider(str, providerProperties, str2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void removeTestProvider(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeTestProvider(str, str2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void setTestProviderLocation(String str, Location location, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (location != null) {
                        parcelObtain.writeInt(1);
                        location.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTestProviderLocation(str, location, str2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void setTestProviderEnabled(String str, boolean z, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTestProviderEnabled(str, z, str2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public List<LocationRequest> getTestProviderCurrentRequests(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTestProviderCurrentRequests(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(LocationRequest.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public LocationTime getGnssTimeMillis() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGnssTimeMillis();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? LocationTime.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public boolean sendExtraCommand(String str, String str2, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    boolean z = true;
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendExtraCommand(str, str2, bundle);
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() == 0) {
                        z = false;
                    }
                    if (parcelObtain2.readInt() != 0) {
                        bundle.readFromParcel(parcelObtain2);
                    }
                    return z;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public void locationCallbackFinished(ILocationListener iLocationListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iLocationListener != null ? iLocationListener.asBinder() : null);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().locationCallbackFinished(iLocationListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public String[] getBackgroundThrottlingWhitelist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBackgroundThrottlingWhitelist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.location.ILocationManager
            public String[] getIgnoreSettingsWhitelist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIgnoreSettingsWhitelist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocationManager iLocationManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iLocationManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLocationManager;
            return true;
        }

        public static ILocationManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
