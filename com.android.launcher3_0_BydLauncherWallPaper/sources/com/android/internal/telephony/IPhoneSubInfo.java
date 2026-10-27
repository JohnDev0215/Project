package com.android.internal.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.ImsiEncryptionInfo;

/* JADX INFO: loaded from: classes4.dex */
public interface IPhoneSubInfo extends IInterface {

    public static class Default implements IPhoneSubInfo {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public ImsiEncryptionInfo getCarrierInfoForImsiEncryption(int i, int i2, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getDeviceId(String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getDeviceIdForPhone(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getDeviceIdWithFeature(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getDeviceSvn(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getDeviceSvnUsingSubId(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getGroupIdLevel1ForSubscriber(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getIccSerialNumber(String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getIccSerialNumberForSubscriber(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getIccSerialNumberWithFeature(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getIccSimChallengeResponse(int i, int i2, int i3, String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getImeiForSubscriber(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getIsimDomain(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getIsimImpi(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String[] getIsimImpu(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getIsimIst(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String[] getIsimPcscf(int i) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getLine1AlphaTag(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getLine1AlphaTagForSubscriber(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getLine1Number(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getLine1NumberForSubscriber(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getMsisdn(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getMsisdnForSubscriber(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getNaiForSubscriber(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getSubscriberId(String str) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getSubscriberIdForSubscriber(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getSubscriberIdWithFeature(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getVoiceMailAlphaTag(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getVoiceMailAlphaTagForSubscriber(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getVoiceMailNumber(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public String getVoiceMailNumberForSubscriber(int i, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public void resetCarrierKeysForImsiEncryption(int i, String str) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneSubInfo
        public void setCarrierInfoForImsiEncryption(int i, String str, ImsiEncryptionInfo imsiEncryptionInfo) throws RemoteException {
        }
    }

    ImsiEncryptionInfo getCarrierInfoForImsiEncryption(int i, int i2, String str) throws RemoteException;

    String getDeviceId(String str) throws RemoteException;

    String getDeviceIdForPhone(int i, String str, String str2) throws RemoteException;

    String getDeviceIdWithFeature(String str, String str2) throws RemoteException;

    String getDeviceSvn(String str, String str2) throws RemoteException;

    String getDeviceSvnUsingSubId(int i, String str, String str2) throws RemoteException;

    String getGroupIdLevel1ForSubscriber(int i, String str, String str2) throws RemoteException;

    String getIccSerialNumber(String str) throws RemoteException;

    String getIccSerialNumberForSubscriber(int i, String str, String str2) throws RemoteException;

    String getIccSerialNumberWithFeature(String str, String str2) throws RemoteException;

    String getIccSimChallengeResponse(int i, int i2, int i3, String str) throws RemoteException;

    String getImeiForSubscriber(int i, String str, String str2) throws RemoteException;

    String getIsimDomain(int i) throws RemoteException;

    String getIsimImpi(int i) throws RemoteException;

    String[] getIsimImpu(int i) throws RemoteException;

    String getIsimIst(int i) throws RemoteException;

    String[] getIsimPcscf(int i) throws RemoteException;

    String getLine1AlphaTag(String str, String str2) throws RemoteException;

    String getLine1AlphaTagForSubscriber(int i, String str, String str2) throws RemoteException;

    String getLine1Number(String str, String str2) throws RemoteException;

    String getLine1NumberForSubscriber(int i, String str, String str2) throws RemoteException;

    String getMsisdn(String str, String str2) throws RemoteException;

    String getMsisdnForSubscriber(int i, String str, String str2) throws RemoteException;

    String getNaiForSubscriber(int i, String str, String str2) throws RemoteException;

    String getSubscriberId(String str) throws RemoteException;

    String getSubscriberIdForSubscriber(int i, String str, String str2) throws RemoteException;

    String getSubscriberIdWithFeature(String str, String str2) throws RemoteException;

    String getVoiceMailAlphaTag(String str, String str2) throws RemoteException;

    String getVoiceMailAlphaTagForSubscriber(int i, String str, String str2) throws RemoteException;

    String getVoiceMailNumber(String str, String str2) throws RemoteException;

    String getVoiceMailNumberForSubscriber(int i, String str, String str2) throws RemoteException;

    void resetCarrierKeysForImsiEncryption(int i, String str) throws RemoteException;

    void setCarrierInfoForImsiEncryption(int i, String str, ImsiEncryptionInfo imsiEncryptionInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IPhoneSubInfo {
        private static final String DESCRIPTOR = "com.android.internal.telephony.IPhoneSubInfo";
        static final int TRANSACTION_getCarrierInfoForImsiEncryption = 23;
        static final int TRANSACTION_getDeviceId = 1;
        static final int TRANSACTION_getDeviceIdForPhone = 4;
        static final int TRANSACTION_getDeviceIdWithFeature = 2;
        static final int TRANSACTION_getDeviceSvn = 6;
        static final int TRANSACTION_getDeviceSvnUsingSubId = 7;
        static final int TRANSACTION_getGroupIdLevel1ForSubscriber = 11;
        static final int TRANSACTION_getIccSerialNumber = 12;
        static final int TRANSACTION_getIccSerialNumberForSubscriber = 14;
        static final int TRANSACTION_getIccSerialNumberWithFeature = 13;
        static final int TRANSACTION_getIccSimChallengeResponse = 33;
        static final int TRANSACTION_getImeiForSubscriber = 5;
        static final int TRANSACTION_getIsimDomain = 29;
        static final int TRANSACTION_getIsimImpi = 28;
        static final int TRANSACTION_getIsimImpu = 30;
        static final int TRANSACTION_getIsimIst = 31;
        static final int TRANSACTION_getIsimPcscf = 32;
        static final int TRANSACTION_getLine1AlphaTag = 17;
        static final int TRANSACTION_getLine1AlphaTagForSubscriber = 18;
        static final int TRANSACTION_getLine1Number = 15;
        static final int TRANSACTION_getLine1NumberForSubscriber = 16;
        static final int TRANSACTION_getMsisdn = 19;
        static final int TRANSACTION_getMsisdnForSubscriber = 20;
        static final int TRANSACTION_getNaiForSubscriber = 3;
        static final int TRANSACTION_getSubscriberId = 8;
        static final int TRANSACTION_getSubscriberIdForSubscriber = 10;
        static final int TRANSACTION_getSubscriberIdWithFeature = 9;
        static final int TRANSACTION_getVoiceMailAlphaTag = 26;
        static final int TRANSACTION_getVoiceMailAlphaTagForSubscriber = 27;
        static final int TRANSACTION_getVoiceMailNumber = 21;
        static final int TRANSACTION_getVoiceMailNumberForSubscriber = 22;
        static final int TRANSACTION_resetCarrierKeysForImsiEncryption = 25;
        static final int TRANSACTION_setCarrierInfoForImsiEncryption = 24;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getDeviceId";
                case 2:
                    return "getDeviceIdWithFeature";
                case 3:
                    return "getNaiForSubscriber";
                case 4:
                    return "getDeviceIdForPhone";
                case 5:
                    return "getImeiForSubscriber";
                case 6:
                    return "getDeviceSvn";
                case 7:
                    return "getDeviceSvnUsingSubId";
                case 8:
                    return "getSubscriberId";
                case 9:
                    return "getSubscriberIdWithFeature";
                case 10:
                    return "getSubscriberIdForSubscriber";
                case 11:
                    return "getGroupIdLevel1ForSubscriber";
                case 12:
                    return "getIccSerialNumber";
                case 13:
                    return "getIccSerialNumberWithFeature";
                case 14:
                    return "getIccSerialNumberForSubscriber";
                case 15:
                    return "getLine1Number";
                case 16:
                    return "getLine1NumberForSubscriber";
                case 17:
                    return "getLine1AlphaTag";
                case 18:
                    return "getLine1AlphaTagForSubscriber";
                case 19:
                    return "getMsisdn";
                case 20:
                    return "getMsisdnForSubscriber";
                case 21:
                    return "getVoiceMailNumber";
                case 22:
                    return "getVoiceMailNumberForSubscriber";
                case 23:
                    return "getCarrierInfoForImsiEncryption";
                case 24:
                    return "setCarrierInfoForImsiEncryption";
                case 25:
                    return "resetCarrierKeysForImsiEncryption";
                case 26:
                    return "getVoiceMailAlphaTag";
                case 27:
                    return "getVoiceMailAlphaTagForSubscriber";
                case 28:
                    return "getIsimImpi";
                case 29:
                    return "getIsimDomain";
                case 30:
                    return "getIsimImpu";
                case 31:
                    return "getIsimIst";
                case 32:
                    return "getIsimPcscf";
                case 33:
                    return "getIccSimChallengeResponse";
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

        public static IPhoneSubInfo asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPhoneSubInfo)) {
                return (IPhoneSubInfo) iInterfaceQueryLocalInterface;
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
                    String deviceId = getDeviceId(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(deviceId);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String deviceIdWithFeature = getDeviceIdWithFeature(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(deviceIdWithFeature);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String naiForSubscriber = getNaiForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(naiForSubscriber);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String deviceIdForPhone = getDeviceIdForPhone(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(deviceIdForPhone);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    String imeiForSubscriber = getImeiForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(imeiForSubscriber);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String deviceSvn = getDeviceSvn(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(deviceSvn);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String deviceSvnUsingSubId = getDeviceSvnUsingSubId(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(deviceSvnUsingSubId);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    String subscriberId = getSubscriberId(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(subscriberId);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    String subscriberIdWithFeature = getSubscriberIdWithFeature(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(subscriberIdWithFeature);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    String subscriberIdForSubscriber = getSubscriberIdForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(subscriberIdForSubscriber);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    String groupIdLevel1ForSubscriber = getGroupIdLevel1ForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(groupIdLevel1ForSubscriber);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    String iccSerialNumber = getIccSerialNumber(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(iccSerialNumber);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    String iccSerialNumberWithFeature = getIccSerialNumberWithFeature(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(iccSerialNumberWithFeature);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    String iccSerialNumberForSubscriber = getIccSerialNumberForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(iccSerialNumberForSubscriber);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    String line1Number = getLine1Number(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(line1Number);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    String line1NumberForSubscriber = getLine1NumberForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(line1NumberForSubscriber);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    String line1AlphaTag = getLine1AlphaTag(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(line1AlphaTag);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    String line1AlphaTagForSubscriber = getLine1AlphaTagForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(line1AlphaTagForSubscriber);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    String msisdn = getMsisdn(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(msisdn);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    String msisdnForSubscriber = getMsisdnForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(msisdnForSubscriber);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    String voiceMailNumber = getVoiceMailNumber(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(voiceMailNumber);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    String voiceMailNumberForSubscriber = getVoiceMailNumberForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(voiceMailNumberForSubscriber);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    ImsiEncryptionInfo carrierInfoForImsiEncryption = getCarrierInfoForImsiEncryption(parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    if (carrierInfoForImsiEncryption != null) {
                        parcel2.writeInt(1);
                        carrierInfoForImsiEncryption.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCarrierInfoForImsiEncryption(parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? ImsiEncryptionInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetCarrierKeysForImsiEncryption(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    String voiceMailAlphaTag = getVoiceMailAlphaTag(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(voiceMailAlphaTag);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    String voiceMailAlphaTagForSubscriber = getVoiceMailAlphaTagForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(voiceMailAlphaTagForSubscriber);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    String isimImpi = getIsimImpi(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(isimImpi);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    String isimDomain = getIsimDomain(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(isimDomain);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] isimImpu = getIsimImpu(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(isimImpu);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    String isimIst = getIsimIst(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(isimIst);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] isimPcscf = getIsimPcscf(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(isimPcscf);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    String iccSimChallengeResponse = getIccSimChallengeResponse(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(iccSimChallengeResponse);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPhoneSubInfo {
            public static IPhoneSubInfo sDefaultImpl;
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

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getDeviceId(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceId(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getDeviceIdWithFeature(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceIdWithFeature(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getNaiForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNaiForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getDeviceIdForPhone(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceIdForPhone(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getImeiForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImeiForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getDeviceSvn(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceSvn(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getDeviceSvnUsingSubId(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceSvnUsingSubId(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getSubscriberId(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubscriberId(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getSubscriberIdWithFeature(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubscriberIdWithFeature(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getSubscriberIdForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSubscriberIdForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getGroupIdLevel1ForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGroupIdLevel1ForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getIccSerialNumber(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIccSerialNumber(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getIccSerialNumberWithFeature(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIccSerialNumberWithFeature(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getIccSerialNumberForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIccSerialNumberForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getLine1Number(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLine1Number(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getLine1NumberForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLine1NumberForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getLine1AlphaTag(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLine1AlphaTag(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getLine1AlphaTagForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLine1AlphaTagForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getMsisdn(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMsisdn(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getMsisdnForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMsisdnForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getVoiceMailNumber(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoiceMailNumber(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getVoiceMailNumberForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoiceMailNumberForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public ImsiEncryptionInfo getCarrierInfoForImsiEncryption(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarrierInfoForImsiEncryption(i, i2, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ImsiEncryptionInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public void setCarrierInfoForImsiEncryption(int i, String str, ImsiEncryptionInfo imsiEncryptionInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (imsiEncryptionInfo != null) {
                        parcelObtain.writeInt(1);
                        imsiEncryptionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCarrierInfoForImsiEncryption(i, str, imsiEncryptionInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public void resetCarrierKeysForImsiEncryption(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetCarrierKeysForImsiEncryption(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getVoiceMailAlphaTag(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoiceMailAlphaTag(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getVoiceMailAlphaTagForSubscriber(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVoiceMailAlphaTagForSubscriber(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getIsimImpi(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIsimImpi(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getIsimDomain(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIsimDomain(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String[] getIsimImpu(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIsimImpu(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getIsimIst(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIsimIst(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String[] getIsimPcscf(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIsimPcscf(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneSubInfo
            public String getIccSimChallengeResponse(int i, int i2, int i3, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIccSimChallengeResponse(i, i2, i3, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPhoneSubInfo iPhoneSubInfo) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPhoneSubInfo == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPhoneSubInfo;
            return true;
        }

        public static IPhoneSubInfo getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
