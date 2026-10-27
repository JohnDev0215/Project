package com.ecarx.xui.adaptapi.bt.gattserver;

import android.os.ParcelUuid;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IGattServer {
    List<ParcelUuid> getGattAddedGattCharacteristicUuidList(ParcelUuid parcelUuid);

    List<ParcelUuid> getGattAddedGattDescriptorUuidList(ParcelUuid parcelUuid, ParcelUuid parcelUuid2);

    List<ParcelUuid> getGattAddedGattServiceUuidList();

    int getGattServerConnectionState();

    boolean isGattServerServiceReady();

    boolean registerGattServerCallback(IGattServerCallback iGattServerCallback);

    boolean reqGattServerAddCharacteristic(ParcelUuid parcelUuid, int i, int i2);

    boolean reqGattServerAddDescriptor(ParcelUuid parcelUuid, int i);

    boolean reqGattServerBeginServiceDeclaration(int i, ParcelUuid parcelUuid);

    boolean reqGattServerClearServices();

    boolean reqGattServerDisconnect(String str);

    boolean reqGattServerEndServiceDeclaration();

    boolean reqGattServerListen(boolean z);

    boolean reqGattServerRemoveService(int i, ParcelUuid parcelUuid);

    boolean reqGattServerSendNotification(String str, int i, ParcelUuid parcelUuid, ParcelUuid parcelUuid2, boolean z, byte[] bArr);

    boolean reqGattServerSendResponse(String str, int i, int i2, int i3, byte[] bArr);

    boolean unregisterGattServerCallback(IGattServerCallback iGattServerCallback);
}
