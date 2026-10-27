// IRequestInterface.aidl
package com.neusoft.optimus.wheeljack.popview.AIDL;

import com.neusoft.optimus.wheeljack.popview.AIDL.IAudioInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IVideoInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IRadioAidlInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IBluetoothInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IVRAidlInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IAudioBluetoothInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.INotficationAppleInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IDvrInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.ICarLifeInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IEcarxkeyInterface;
interface IRequestInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void requestAudioCallBack( IAudioInterface audioInterface );
    void requestVideoCallBack( IVideoInterface videoInterface );
    void requestRadioCallBack( IRadioAidlInterface radioInterface );
    void requestBluetoothCallBack( IBluetoothInterface bluetoothInterface );
    void requestAudioBluetoothCallBack( IAudioBluetoothInterface bluetoothAudioInterface );
    void requestVRCallBack( IVRAidlInterface vrInterface );
    void requestNotificationApple(INotficationAppleInterface notificationApple);
    void requestDvrCallBack( IDvrInterface dvrInterface );
    void requestCarLifeCallBack( ICarLifeInterface carLifeInterface );
    void requestEcarxkeyCallBack( IEcarxkeyInterface iecarxkeyInterface );
    void unrequestEcarxkeyCallBack( IEcarxkeyInterface iecarxkeyInterface );
    void changeSIRIVRStatus(int sirivrStatus);
}
