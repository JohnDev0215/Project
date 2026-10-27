package ts.car.service.dcm.data;

/* JADX INFO: loaded from: classes.dex */
public class CmsConstants {
    public static final String ACTION_DCM_DEVICE_ATTACHED = "com.ts.car.DeviceConnectionManager.DeviceAttached";
    public static final String ACTION_DCM_DEVICE_CONNECTION_CHANGED = "com.ts.car.DeviceConnectionManager.DeviceConnectionChanged";
    public static final String ACTION_DCM_DEVICE_DETACHED = "com.ts.car.DeviceConnectionManager.DeviceDetached";
    public static final String ACTION_DCM_INCOMING_PHONE_CHANGED = "com.ts.car.DeviceConnectionManager.IncomingPhoneChanged";
    public static final String ACTION_DCM_OUTGOING_PHONE_CHANGED = "com.ts.car.DeviceConnectionManager.OutgoingPhoneChanged";
    public static final String DEVICE_MESSAGE = "Message";
    public static final int FAILURE = -1;
    public static final int FALSE = 0;
    public static final int FRIENDLY_NAME_LENGTH = 64;
    public static final int SUCCESS = 0;
    public static final int TRUE = 1;
    public static final int USB_AOA_AUTO = 1;
    public static final int USB_AOA_CARLIFE = 0;
    public static final int USB_DEVICE = 1;
    public static final int USB_HOST = 0;

    public static final class AbnormalDeviceStatus {
        public static final int ATTACHED = 1;
        public static final int DETACHED = 2;
    }

    public static final class AbnormalType {
        public static final int NO_RESPONSE = 2;
        public static final int NO_SUPPORT = 1;
        public static final int USB_HUB = 3;
    }

    public static final class BtOobPairStatus {
        public static final int FAILURE = 0;
        public static final int SUCCESS = 1;
    }

    public static final class BtProfile {
        public static final int A2DP = 4;
        public static final int AVRCP = 8;
        public static final int HFP = 1;
        public static final int MAP = 16;
        public static final int PAN = 32;
        public static final int PBAP = 2;
    }

    public static final class BtProfileState {
        public static final int CONNECTED = 2;
        public static final int CONNECTING = 1;
        public static final int DISCONNECTED = 0;
        public static final int DISCONNECTING = 3;
        public static final int UNKNOWN = 4;
    }

    public static final class BtState {
        public static final int STATE_OFF = 10;
        public static final int STATE_ON = 12;
        public static final int STATE_TURNING_OFF = 13;
        public static final int STATE_TURNING_ON = 11;
    }

    public static final class ConnetionState {
        public static final int CONNECTED = 1;
        public static final int DISCONNECTED = 2;
    }

    public static final class DisconnectProjectionReason {
        public static final int LINK_DISCONNECTED = 4;
        public static final int MERGE_SAME_DEVICE = 5;
        public static final int NO_ERROR = 0;
        public static final int POWER_ACC_OFF = 1;
        public static final int PROJECTON_CAPABILITY_GONE = 6;
        public static final int SWITCH_PROJECTION = 2;
        public static final int USER_DISCONNECT = 3;
    }

    public static final class DmRegisterObjectType {
        public static final int DMHMI = 1;
        public static final int OTHER_OBJECT = 0;
    }

    public static final class Iap2NotifyReason {
        public static final int CANCEL_WIRELESS_CARPLAY = 3;
        public static final int CHECK_WIRELESS_CAPLAY_WHEN_BT_CONNECTED = 5;
        public static final int CHECK_WIRELESS_CAPLAY_WHEN_BT_PAIRED = 1;
        public static final int CONNECTING = 2;
        public static final int PRECHECK_WIRELESS_CARPLAY = 4;
    }

    public static final class IapSessionState {
        public static final int CONNECTED = 1;
        public static final int DISCONNECTED = 2;
    }

    public static final class IapSessionStateReason {
        public static final int CONNECTION_TIME_OUT = 3;
        public static final int DISCONNECTION_TIME_OUT = 4;
        public static final int LINK_ERROR = 2;
        public static final int LINK_LOST = 1;
        public static final int NO_ERROR = 0;
    }

    public static final class PowerWorkMode {
        public static final int ACC_OFF = 1;
        public static final int ACC_ON = 2;
        public static final int UNKNOWN = 0;
    }

    public static final class ProjectionCapabilityUpdatedReason {
        public static final int BT_UNPAIRED = 4;
        public static final int IAP_AUTHENTICATION_FAILED = 2;
        public static final int NOERR = 0;
        public static final int RFCOMM_FAILD = 1;
        public static final int SERVICE_NOT_REGISTER = 3;
    }

    public static final class ProjectionName {
        public static final String ANDROIDAUTO = "AndroidAuto";
        public static final String CARLIFE = "CarLife";
        public static final String CARPLAY = "CarPlay";
    }

    public static final class RoleSwitchPurpose {
        public static final int CARLIFE = 2;
        public static final int CARPLAY = 1;
        public static final int IAP = 3;
        public static final int NO_PURPOSE = 0;
    }

    public static final class WifiApConnectState {
        public static final int AP_DEVICE_CONNECT = 1;
        public static final int AP_DEVICE_DISCONNECT = 0;
    }

    public static final class WifiApState {
        public static final int WIFI_AP_STATE_DISABLED = 11;
        public static final int WIFI_AP_STATE_DISABLING = 10;
        public static final int WIFI_AP_STATE_ENABLED = 13;
        public static final int WIFI_AP_STATE_ENABLING = 12;
        public static final int WIFI_AP_STATE_FAILED = 14;
    }

    public static final class WirelessCarplayDetectedState {
        public static final int CANCELED = 2;
        public static final int ENABLED = 1;
        public static final int NOSUPPORTED = 0;
    }

    public static final class ConnectionType {
        public static final int BT = 2;
        public static final int ETHERNET = 4;
        public static final int NONE = 0;
        public static final int USB = 1;
        public static final int WIFI = 3;

        public static boolean isAvailableType(int value) {
            if (value == 1 || value == 2 || value == 3) {
                return true;
            }
            return false;
        }
    }

    public static final class ServiceType {
        public static final int ANDROIDAUTO = 3;
        public static final int CARLIFE = 2;
        public static final int CARPLAY = 1;
        public static final int HICAR = 5;
        public static final int IPOD = 4;
        public static final int NONE = 0;

        public static boolean isAvailableType(int value) {
            if (value == 1 || value == 2 || value == 3 || value == 4) {
                return true;
            }
            return false;
        }
    }

    public static final class ActivateServiceResult {
        public static final int CONNECTING_TIMEOUT = 6;
        public static final int NO_RESOURCE = 2;
        public static final int OTHER_PROJECTION_EXIST = 1;
        public static final int PARAMETER_ERROR = 5;
        public static final int PROJECTION_NOT_ENABLE = 7;
        public static final int SERVICE_ERROR = 4;
        public static final int SERVICE_NOT_SUPPORTED = 3;
        public static final int SUCCESS = 0;

        private ActivateServiceResult() {
        }
    }

    public static final class ServiceSessionUpdateReason {
        public static final int AUTHENTICATION_FAILED = 1;
        public static final int CONNECTION_TIME_OUT = 4;
        public static final int CONNECT_SWITCH_TIME_OUT = 6;
        public static final int DEIVCE_CONNECT_HFP_FAILED = 15;
        public static final int DEVICE_BT_BONDED = 12;
        public static final int DEVICE_BT_BONDING = 11;
        public static final int DEVICE_BT_BOND_FAILED = 10;
        public static final int DISCONNECTION_TIME_OUT = 5;
        public static final int INVALID_PROJECTION_CAPABILITY = 7;
        public static final int LINK_ERROR = 3;
        public static final int LINK_LOST = 2;
        public static final int NOERROR = 0;
        public static final int NO_BT_ADDRESS = 13;
        public static final int NO_PROJECTION_CAPABILITY = 9;
        public static final int PROJECTION_SERVICE_NOT_REGISTERED = 14;
        public static final int RFCOMM_FAILED = 8;
        public static final int USER_DISCONNECT = 16;

        private ServiceSessionUpdateReason() {
        }
    }

    public static final class WifiBand {
        public static final int WIFI_2GHZ_BAND = 0;
        public static final int WIFI_5GHZ_BAND = 1;

        private WifiBand() {
        }
    }

    public static final class UsbMode {
        public static final int DEVICE = 1;
        public static final int HOST = 0;

        private UsbMode() {
        }
    }

    public static final class UsbAoaMode {
        public static final int AOA_AUTO = 1;
        public static final int AOA_CARLIFE = 0;

        private UsbAoaMode() {
        }
    }

    public static final class DeviceConnectionUpdateReason {
        public static final int ADD_CONNETION_TYPE = 1;
        public static final int DELETE_CONNETION_TYPE = 0;

        private DeviceConnectionUpdateReason() {
        }
    }

    public static final class Capability {
        public static final int CAP_ALL = 131071;
        public static final int CAP_ANDROID_AUTO_USB = 128;
        public static final int CAP_ANDROID_AUTO_WIRELESS = 1024;
        public static final int CAP_BT_ALL = 67078;
        public static final int CAP_BT_AUDIO = 4;
        public static final int CAP_BT_BASE = 6;
        public static final int CAP_BT_HFP = 2;
        public static final int CAP_CARLIFE_USB = 256;
        public static final int CAP_CARLIFE_WIRELESS = 2048;
        public static final int CAP_CARPLAY_USB = 64;
        public static final int CAP_CARPLAY_WIRELESS = 512;
        public static final int CAP_CHARGE = 1;
        public static final int CAP_IAP2_OVER_BT = 65536;
        public static final int CAP_IPOD = 8;
        public static final int CAP_MTP = 16;
        public static final int CAP_NONE = 0;
        public static final int CAP_PROJECTION_ALL = 4040;
        public static final int CAP_USBMEDIA = 32;
        public static final int CAP_USB_ALL = 33273;
        public static final int CAP_USB_STORAGE = 32768;
        public static final int CAP_WIFI_ALL = 30720;
        public static final int CAP_WIFI_AP = 4096;
        public static final int CAP_WIFI_BASE = 28672;
        public static final int CAP_WIFI_DIRECT = 16384;
        public static final int CAP_WIFI_STA = 8192;

        private Capability() {
        }

        public static boolean isProjectionCapability(int capability) {
            return capability > 0 && (capability & CAP_PROJECTION_ALL) > 0;
        }
    }

    public static final class CapabilityState {
        public static final int CONNECTED = 4;
        public static final int CONNECTING = 3;
        public static final int DISCONNECTED = 1;
        public static final int DISCONNECTING = 2;
        public static final int INVALID = 0;

        CapabilityState() {
        }
    }

    public static final class ConnectedTypeState {
        public static final int DETECTING = 2;
        public static final int DISCONNECTED = 4;
        public static final int INVALID = 0;
        public static final int READY = 3;
        public static final int UNKNOWN = 1;

        ConnectedTypeState() {
        }

        public static boolean isAvailableType(int value) {
            if (value == 1 || value == 2 || value == 3 || value == 4) {
                return true;
            }
            return false;
        }
    }

    public static final class DeviceType {
        public static final int ANDROID_PHONE = 1;
        public static final int BTDEVICE = 4;
        public static final int IPHONE = 2;
        public static final int IPOD = 3;
        public static final int UNKNOWN = 0;

        private DeviceType() {
        }

        public static String getDeviceTypeDescribe(int deviceType) {
            if (deviceType == 1) {
                return "android";
            }
            if (deviceType == 2) {
                return "iphone";
            }
            if (deviceType == 3) {
                return "ipod";
            }
            if (deviceType == 4) {
                return "btdevice";
            }
            return "unknown";
        }
    }

    public static final class DeviceState {
        public static final int DISABLE = 3;
        public static final int OFFLINE = 0;
        public static final int ONLINE = 1;
        public static final int REMOVING = 2;
        public static final int VISIBLE = 4;

        private DeviceState() {
        }
    }

    public static final class MergeKeyType {
        public static final int BT_MACADDRESS = 2;
        public static final int DEVICE_IDENTIFICATION = 4;
        public static final int USB_SERIALNUMBER = 1;
        public static final int WIFI_MACADDRESS = 3;

        public static boolean isAvailableType(int value) {
            if (value == 1 || value == 2 || value == 3) {
                return true;
            }
            return false;
        }
    }
}
