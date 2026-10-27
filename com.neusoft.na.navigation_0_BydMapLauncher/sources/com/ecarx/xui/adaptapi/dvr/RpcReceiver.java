package com.ecarx.xui.adaptapi.dvr;

import android.os.SystemProperties;
import android.util.Log;
import com.ecarx.xui.adaptapi.dvr.IOperation;
import com.ecarx.xui.adaptapi.dvr.RPCVariables;
import com.ecarx.xui.adaptapi.wpc.specific.WPCModel;
import com.neusoft.alfus.RpcManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class RpcReceiver {
    private static final int RPC_DATA_LEN = 25;
    public static final int cGetDTCLost = 1913;
    private static final String cTag = "RPCReceiver";
    private IOperation.IOperationCallback callback;
    private RpcNotifiter mRpcNotifiter;
    public Boolean sPepsAccOn = false;
    RpcManager.OnRpcListener _onCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.dvr.RpcReceiver.1
        public void onCallback(int i, byte[] bArr) {
            byte b;
            if (bArr == null) {
                Log.d("RpcReceiver", "DVR callback is null");
                return;
            }
            byte b2 = bArr[0];
            byte b3 = bArr[1];
            if (b2 != 0 || b3 != 1) {
                if (b2 == 0 && b3 == 2) {
                    if (bArr[2] == 25) {
                        Log.d("DvrImp", "PEPS callback : %s" + Arrays.toString(bArr));
                        if (bArr[9] == 2 && ((b = bArr[8]) == 1 || b == 2 || b == 4)) {
                            if (RpcReceiver.this.isDVRLostCanData().booleanValue()) {
                                if (RpcReceiver.this.callback != null) {
                                    RpcReceiver.this.callback.onMode(8192);
                                    return;
                                }
                                return;
                            }
                            RpcReceiver.this.reloadSystemDate();
                            return;
                        }
                        Log.d("DvrImp", "PEPS ACC OFF");
                        return;
                    }
                    return;
                }
                if (b2 == 0 && b3 == 5) {
                    Log.d("DvrImp", "RpcReceiver dvrlost callback: %s" + Arrays.toString(bArr));
                    if (bArr[2] == 0 && bArr[15] == 1) {
                        Log.d("DvrImp", "Lose DVR can data !!!!!!");
                        if (RpcReceiver.this.callback != null) {
                            RpcReceiver.this.callback.onMode(8192);
                            return;
                        }
                        return;
                    }
                    if (RpcReceiver.this.getAccState().booleanValue()) {
                        RpcReceiver.this.reloadSystemDate();
                        return;
                    } else {
                        Log.d("DvrImp", " acc off");
                        return;
                    }
                }
                return;
            }
            if (bArr[2] == 9) {
                Log.d("DvrImp", "sSystemState: %s" + Arrays.toString(bArr));
                byte b4 = bArr[3];
                if (b4 == 0) {
                    if (RpcReceiver.this.callback != null) {
                        Log.d("DvrImp", "return initializing");
                        RpcReceiver.this.callback.onMode(1);
                        return;
                    }
                    return;
                }
                if (b4 == 2 || b4 == 12) {
                    if (RpcReceiver.this.callback != null) {
                        Log.d("DvrImp", "return pause");
                        RpcReceiver.this.callback.onMode(4);
                        return;
                    }
                    return;
                }
                if (b4 == 6 || b4 == 7 || b4 == 8 || b4 == 9) {
                    Log.d("DvrImp", "browse");
                    if (RpcReceiver.this.callback == null || !SystemProperties.get("ro.product.device").contains("KC")) {
                        return;
                    }
                    Log.d("DvrImp", "is kc");
                    Log.d("DvrImp", "return pause");
                    RpcReceiver.this.callback.onMode(4);
                    return;
                }
                if (b4 == 1) {
                    if (RpcReceiver.this.callback != null) {
                        Log.d("DvrImp", "return record");
                        RpcReceiver.this.callback.onMode(2);
                        return;
                    }
                    return;
                }
                if (b4 == 5 || b4 == 3 || b4 == 4) {
                    if (RpcReceiver.this.callback != null) {
                        Log.d("DvrImp", "return record");
                        RpcReceiver.this.callback.onMode(32);
                        return;
                    }
                    return;
                }
                if (b4 == 15) {
                    if (RpcReceiver.this.callback != null) {
                        Log.d("DvrImp", "return fault");
                        RpcReceiver.this.callback.onMode(8192);
                        return;
                    }
                    return;
                }
                if (b4 != 10 && b4 != 11) {
                    if (RpcReceiver.this.callback != null) {
                        Log.d("DvrImp", "return fault");
                        RpcReceiver.this.callback.onMode(8192);
                        return;
                    }
                    return;
                }
                Log.d("DvrImp", "enter playview");
                Log.d("DvrImp", "do nothing");
            }
        }
    };
    RpcManager.OnRpcListener _onSpeedCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.dvr.RpcReceiver.2
        public void onCallback(int i, byte[] bArr) {
            if (bArr == null) {
                Log.d("DvrImp", "_onSpeedCallback is null");
            } else {
                Log.d("DvrImp", "speed callback data = %s" + Arrays.toString(bArr));
                RpcReceiver.this.mRpcNotifiter.dispatchSpeedMessage(bArr);
            }
        }
    };

    public RpcReceiver(IOperation.IOperationCallback iOperationCallback) {
        this.mRpcNotifiter = null;
        this.mRpcNotifiter = RpcNotifiter.getInstance();
        this.callback = iOperationCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reloadSystemDate() {
        Log.d("DvrImp", "getDVRSystemData");
        byte[] bArr = new byte[25];
        bArr[0] = 0;
        bArr[1] = 1;
        bArr[2] = 20;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        if (message == null) {
            Log.d("DvrImp", "data nll");
            IOperation.IOperationCallback iOperationCallback = this.callback;
            if (iOperationCallback != null) {
                iOperationCallback.onMode(8192);
                return;
            }
            return;
        }
        byte b = message[8];
        if (b == 0) {
            Log.d("DvrImp", "initializing");
            IOperation.IOperationCallback iOperationCallback2 = this.callback;
            if (iOperationCallback2 != null) {
                iOperationCallback2.onMode(1);
                return;
            }
            return;
        }
        if (b == 1) {
            Log.d("DvrImp", "generalrecord");
            IOperation.IOperationCallback iOperationCallback3 = this.callback;
            if (iOperationCallback3 != null) {
                iOperationCallback3.onMode(2);
                return;
            }
            return;
        }
        if (b == 2) {
            Log.d("DvrImp", "pauserecord");
            IOperation.IOperationCallback iOperationCallback4 = this.callback;
            if (iOperationCallback4 != null) {
                iOperationCallback4.onMode(4);
                return;
            }
            return;
        }
        if (b == 3) {
            Log.d("DvrImp", "emergencyrecordbyuser");
            IOperation.IOperationCallback iOperationCallback5 = this.callback;
            if (iOperationCallback5 != null) {
                iOperationCallback5.onMode(32);
                return;
            }
            return;
        }
        if (b == 4) {
            Log.d("DvrImp", "emergencyrecordbycrash");
            IOperation.IOperationCallback iOperationCallback6 = this.callback;
            if (iOperationCallback6 != null) {
                iOperationCallback6.onMode(32);
                return;
            }
            return;
        }
        if (b == 5) {
            Log.d("DvrImp", "riskrecord");
            IOperation.IOperationCallback iOperationCallback7 = this.callback;
            if (iOperationCallback7 != null) {
                iOperationCallback7.onMode(32);
                return;
            }
            return;
        }
        if (b == 6) {
            Log.d("DvrImp", "generalbrowse");
            if (this.callback == null || !SystemProperties.get("ro.product.device").contains("KC")) {
                return;
            }
            this.callback.onMode(4);
            return;
        }
        if (b == 7) {
            Log.d("DvrImp", "emergencybrowse");
            if (this.callback == null || !SystemProperties.get("ro.product.device").contains("KC")) {
                return;
            }
            this.callback.onMode(4);
            return;
        }
        if (b == 8) {
            Log.d("DvrImp", "riskbrowse");
            if (this.callback == null || !SystemProperties.get("ro.product.device").contains("KC")) {
                return;
            }
            this.callback.onMode(4);
            return;
        }
        if (b == 9) {
            Log.d("DvrImp", "photobrowse");
            if (this.callback == null || !SystemProperties.get("ro.product.device").contains("KC")) {
                return;
            }
            this.callback.onMode(4);
            return;
        }
        if (b == 12) {
            Log.d("DvrImp", "updatemode");
            IOperation.IOperationCallback iOperationCallback8 = this.callback;
            if (iOperationCallback8 != null) {
                iOperationCallback8.onMode(4);
                return;
            }
            return;
        }
        if (b == 15) {
            Log.d("DvrImp", "error");
            IOperation.IOperationCallback iOperationCallback9 = this.callback;
            if (iOperationCallback9 != null) {
                iOperationCallback9.onMode(8192);
            }
        }
    }

    public void init() {
        Log.d("DvrImp", "register RPC Callback");
        RpcManager.getInstance().registerListener(122355712, (byte) 1, this._onCallback);
        RpcManager.getInstance().registerListener(RPCVariables.Speed.sSpeedCallbackOpecode, (byte) 1, this._onSpeedCallback);
    }

    public void exit() {
        Log.d("DvrImp", "unregister RPC Callback");
        RpcManager.getInstance().unregisterListener(122355712, (byte) 1, this._onCallback);
        RpcManager.getInstance().unregisterListener(RPCVariables.Speed.sSpeedCallbackOpecode, (byte) 1, this._onSpeedCallback);
    }

    public Boolean getAccState() {
        byte b;
        Log.d("DvrImp", "getAccState");
        byte[] bArr = new byte[25];
        bArr[0] = 0;
        bArr[1] = 2;
        bArr[2] = WPCModel.cSignalId0x27;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        if (message == null) {
            Log.d("DvrImp", "Peps data is null");
            this.sPepsAccOn = false;
        } else {
            Log.d("DvrImp", "PEPS valid = " + ((int) message[14]) + "   ACC = " + ((int) message[13]));
            if (message[14] == 2 && ((b = message[13]) == 1 || b == 2 || b == 4)) {
                Log.d("DvrImp", "PEPS valid is ACC ON");
                this.sPepsAccOn = true;
            } else {
                this.sPepsAccOn = false;
            }
        }
        return this.sPepsAccOn;
    }

    public Boolean isDVRLostCanData() {
        byte[] bArr = new byte[25];
        bArr[0] = 25;
        bArr[1] = 2;
        byte[] message = RpcManager.getInstance().getMessage(1913, bArr);
        Log.d(cTag, "GET-DVRDataLost = " + Arrays.toString(message));
        if (message != null && (message[0] & 1) == 1) {
            Log.d("DvrImp", "DVR lost CAN data");
            return true;
        }
        return false;
    }
}
