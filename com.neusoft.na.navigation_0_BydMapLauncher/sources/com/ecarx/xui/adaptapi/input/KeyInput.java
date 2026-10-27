package com.ecarx.xui.adaptapi.input;

import android.car.user.CarUserManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.neusoft.optimus.wheeljack.popview.AIDL.IEcarxkeyInterface;
import com.neusoft.optimus.wheeljack.popview.AIDL.IRequestInterface;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class KeyInput extends Input {
    public static final String cRegisterAction = "com.neusoft.Action_RegisterHardKeyAIDL";
    private static final String cTag = "KeyInput";
    private IRequestInterface _RequestInterface;
    private Context _context;
    private IKeyCallback _callback = null;
    private List<Integer> list = new ArrayList();
    private ServiceConnection sc = new ServiceConnection() { // from class: com.ecarx.xui.adaptapi.input.KeyInput.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(KeyInput.cTag, "onServiceConnected");
            KeyInput.this._RequestInterface = IRequestInterface.Stub.asInterface(iBinder);
            Log.d(KeyInput.cTag, "_RequestInterface=" + KeyInput.this._RequestInterface);
            try {
                Log.d(KeyInput.cTag, "requestXCallInfoPopViewCallBack");
                KeyInput.this._RequestInterface.requestEcarxkeyCallBack(KeyInput.this.ecarxkey);
            } catch (RemoteException e) {
                Log.d(KeyInput.cTag, "=========================RemoteException=====" + e);
                e.printStackTrace();
                KeyInput.this._RequestInterface = null;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(KeyInput.cTag, "onServiceDisconnected");
            KeyInput.this._RequestInterface = null;
        }
    };
    private IEcarxkeyInterface ecarxkey = new IEcarxkeyInterface.Stub() { // from class: com.ecarx.xui.adaptapi.input.KeyInput.2
        @Override // com.neusoft.optimus.wheeljack.popview.AIDL.IEcarxkeyInterface
        public void hardKeyCallBack(int i, int i2) throws RemoteException {
            Log.d(KeyInput.cTag, "keyCode" + i + CarUserManager.BUNDLE_PARAM_ACTION + i2);
            if (i2 == 1) {
                if (!KeyInput.this.sendKey(i) || KeyInput.this._callback == null) {
                    return;
                }
                if (i == 114) {
                    Log.d(KeyInput.cTag, "onKeyPressed=200025");
                    return;
                }
                if (i == 115) {
                    Log.d(KeyInput.cTag, "onKeyPressed=200024");
                    return;
                }
                if (i == 163) {
                    KeyInput.this._callback.onKeyPressed(KeyCode.KEYCODE_R_MEDIA_NEXT);
                    Log.d(KeyInput.cTag, "onKeyPressed=200087");
                    Log.d(KeyInput.cTag, "onKeyPressed=KEYCODE_MEDIA_NEXT");
                    return;
                }
                if (i == 165) {
                    KeyInput.this._callback.onKeyPressed(KeyCode.KEYCODE_R_MEDIA_PREVIOUS);
                    Log.d(KeyInput.cTag, "onKeyPressed=200088");
                    Log.d(KeyInput.cTag, "onKeyPressed=KEYCODE_R_MEDIA_PREVIOUS");
                    return;
                }
                if (i == 172) {
                    KeyInput.this._callback.onKeyPressed(KeyCode.KEYCODE_R_HOME);
                    Log.d(KeyInput.cTag, "onKeyPressed=200003");
                    return;
                }
                if (i == 224) {
                    KeyInput.this._callback.onKeyPressed(KeyCode.KEYCODE_R_VOICE_ASSIST);
                    Log.d(KeyInput.cTag, "onKeyPressed=200231");
                    return;
                } else if (i == 239) {
                    KeyInput.this._callback.onKeyPressed(KeyCode.KEYCODE_R_SRC);
                    Log.d(KeyInput.cTag, "onKeyPressed=210004");
                    return;
                } else {
                    if (i != 269) {
                        return;
                    }
                    KeyInput.this._callback.onKeyPressed(KeyCode.KEYCODE_R_CALL);
                    Log.d(KeyInput.cTag, "onKeyPressed=200005");
                    return;
                }
            }
            if (i2 == 0 && KeyInput.this.sendKey(i) && KeyInput.this._callback != null) {
                if (i == 114) {
                    Log.d(KeyInput.cTag, "onKeyReleased=200025");
                    return;
                }
                if (i == 115) {
                    Log.d(KeyInput.cTag, "onKeyReleased=200024");
                    return;
                }
                if (i == 163) {
                    KeyInput.this._callback.onKeyReleased(KeyCode.KEYCODE_R_MEDIA_NEXT);
                    Log.d(KeyInput.cTag, "onKeyReleased=200087");
                    return;
                }
                if (i == 165) {
                    KeyInput.this._callback.onKeyReleased(KeyCode.KEYCODE_R_MEDIA_PREVIOUS);
                    Log.d(KeyInput.cTag, "onKeyReleased=200088");
                    return;
                }
                if (i == 172) {
                    KeyInput.this._callback.onKeyReleased(KeyCode.KEYCODE_R_HOME);
                    Log.d(KeyInput.cTag, "onKeyReleased=200003");
                    return;
                }
                if (i == 224) {
                    KeyInput.this._callback.onKeyReleased(KeyCode.KEYCODE_R_VOICE_ASSIST);
                    Log.d(KeyInput.cTag, "onKeyReleased=200231");
                } else if (i == 239) {
                    KeyInput.this._callback.onKeyReleased(KeyCode.KEYCODE_R_SRC);
                    Log.d(KeyInput.cTag, "onKeyReleased=210004");
                } else {
                    if (i != 269) {
                        return;
                    }
                    KeyInput.this._callback.onKeyReleased(KeyCode.KEYCODE_R_CALL);
                    Log.d(KeyInput.cTag, "onKeyReleased=200005");
                }
            }
        }
    };
    BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.ecarx.xui.adaptapi.input.KeyInput.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(KeyInput.cRegisterAction)) {
                KeyInput.this._context.unbindService(KeyInput.this.sc);
                Intent intent2 = new Intent();
                intent2.setAction("com.neusoft.HardKeyAidlInterface");
                intent2.setPackage("com.neusoft.optimus.wheeljack.popview");
                Log.d(KeyInput.cTag, "isBindv=" + KeyInput.this._context.bindService(intent2, KeyInput.this.sc, 1));
            }
        }
    };

    public KeyInput(Context context) {
        Log.d(cTag, "KeyInputregisterHardkey");
        this._context = context;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(cRegisterAction);
        context.registerReceiver(this.receiver, intentFilter);
        Intent intent = new Intent();
        intent.setAction("com.neusoft.HardKeyAidlInterface");
        intent.setPackage("com.neusoft.optimus.wheeljack.popview");
        Log.d(cTag, "isBindv=" + this._context.bindService(intent, this.sc, 1));
    }

    @Override // com.ecarx.xui.adaptapi.input.Input
    public int[] requestKeysInterception(int[] iArr, IKeyCallback iKeyCallback) {
        Log.d(cTag, "KeyInputrequestKeysInterception");
        this._callback = iKeyCallback;
        for (int i : iArr) {
            switch (i) {
                case KeyCode.KEYCODE_R_HOME /* 200003 */:
                    this.list.add(172);
                    break;
                case KeyCode.KEYCODE_R_CALL /* 200005 */:
                    this.list.add(269);
                    break;
                case KeyCode.KEYCODE_R_VOLUME_UP /* 200024 */:
                    this.list.add(115);
                    break;
                case KeyCode.KEYCODE_R_VOLUME_DOWN /* 200025 */:
                    this.list.add(114);
                    break;
                case KeyCode.KEYCODE_R_MEDIA_NEXT /* 200087 */:
                    this.list.add(163);
                    break;
                case KeyCode.KEYCODE_R_MEDIA_PREVIOUS /* 200088 */:
                    this.list.add(165);
                    break;
                case KeyCode.KEYCODE_R_VOLUME_MUTE /* 200164 */:
                    this.list.add(113);
                    break;
                case KeyCode.KEYCODE_R_VOICE_ASSIST /* 200231 */:
                    this.list.add(224);
                    break;
                case KeyCode.KEYCODE_R_SRC /* 210004 */:
                    this.list.add(239);
                    break;
                default:
                    this.list.add(Integer.valueOf(i));
                    break;
            }
        }
        return iArr;
    }

    @Override // com.ecarx.xui.adaptapi.input.Input
    public boolean abandonKeysInterception(IKeyCallback iKeyCallback) {
        Log.d(cTag, "KeyInputabandonKeysInterception");
        try {
            this._RequestInterface.unrequestEcarxkeyCallBack(this.ecarxkey);
        } catch (RemoteException e) {
            Log.d(cTag, "==============RemoteException=====" + e);
            e.printStackTrace();
            this._RequestInterface = null;
        }
        this._callback = null;
        this._context.unregisterReceiver(this.receiver);
        return true;
    }

    public boolean sendKey(int i) {
        for (int i2 = 0; i2 < this.list.size(); i2++) {
            if (this.list.get(i2).intValue() == i) {
                return true;
            }
        }
        return false;
    }
}
