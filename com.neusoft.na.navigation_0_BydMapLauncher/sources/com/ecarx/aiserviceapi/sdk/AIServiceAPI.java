package com.ecarx.aiserviceapi.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.aiserviceapi.AIServiceCallback;
import com.ecarx.aiserviceapi.ConnectCallBack;
import com.ecarx.aiserviceapi.IAIServiceAPI;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class AIServiceAPI {
    private static final int MSG_CONNECT = 102;
    private static final int MSG_RECONNECT = 101;
    private static final String SERVICE_CLASS = "com.ecarx.xiaoka.base.service.AIVoiceService";
    private static final String SERVICE_PACKAGE = "com.ecarx.xiaokagui";
    private static AIServiceAPI mInstance;
    private ConnectCallBack connectCallBack;
    private Context mContext;
    private IAIServiceAPI mService;
    private IBinder mServiceBinder;
    private String TAG = "AIServiceAPI";
    private final Map<String, AIServiceObserver> callbackMap = new HashMap();
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.ecarx.aiserviceapi.sdk.AIServiceAPI.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(AIServiceAPI.this.TAG, "onServiceConnected name = " + name);
            AIServiceAPI.this.mService = IAIServiceAPI.Stub.asInterface(service);
            AIServiceAPI.this.mServiceBinder = service;
            AIServiceAPI.this.reconnectTime = 1000;
            Log.d(AIServiceAPI.this.TAG, "onAPIReady 1,true " + name);
            AIServiceAPI.this.connectCallBack.onAPIReady(true);
            Log.d(AIServiceAPI.this.TAG, "onAPIReady 2,true " + name);
            try {
                AIServiceAPI.this.mServiceBinder.linkToDeath(AIServiceAPI.this.mDeathRecipient, 1);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            AIServiceAPI.this.reconnectTime = 1000;
            Log.d(AIServiceAPI.this.TAG, "onServiceDisconnected name = " + name);
            AIServiceAPI.this.mService = null;
            AIServiceAPI.this.requestReconnect();
            Log.d(AIServiceAPI.this.TAG, "onAPIReady 1,false " + name);
            AIServiceAPI.this.connectCallBack.onAPIReady(false);
            Log.d(AIServiceAPI.this.TAG, "onAPIReady 2,false " + name);
        }
    };
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() { // from class: com.ecarx.aiserviceapi.sdk.AIServiceAPI.2
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            if (AIServiceAPI.this.mServiceBinder != null) {
                AIServiceAPI.this.mServiceBinder.unlinkToDeath(AIServiceAPI.this.mDeathRecipient, 1);
            }
            AIServiceAPI.this.reconnectTime = 1000;
            AIServiceAPI.this.mService = null;
            AIServiceAPI.this.mServiceBinder = null;
            AIServiceAPI.this.connectCallBack.onAPIReady(false);
            AIServiceAPI.this.requestReconnect();
            Log.d(AIServiceAPI.this.TAG, "binderDied ");
        }
    };
    private Handler mHandler = new Handler() { // from class: com.ecarx.aiserviceapi.sdk.AIServiceAPI.3
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i != 101) {
                if (i == 102 && AIServiceAPI.this.mService == null) {
                    AIServiceAPI.this.bindRemoteService();
                    return;
                }
                return;
            }
            AIServiceAPI.this.mHandler.removeMessages(101);
            Log.d(AIServiceAPI.this.TAG, "MSG_RECONNECT mService = " + AIServiceAPI.this.mService);
            if (AIServiceAPI.this.mService == null) {
                AIServiceAPI.this.bindRemoteServiceReconnect();
            }
        }
    };
    private int reconnectTime = 1000;

    AIServiceAPI(Context context) {
        this.mContext = context;
    }

    public static AIServiceAPI get(Context context) {
        if (mInstance == null) {
            synchronized (AIServiceAPI.class) {
                if (mInstance == null) {
                    mInstance = new AIServiceAPI(context);
                }
            }
        }
        return mInstance;
    }

    public void init(Context context, ConnectCallBack callBack) {
        this.connectCallBack = callBack;
        requestConnect();
    }

    private void requestConnect() {
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.what = 102;
        this.mHandler.sendMessage(messageObtainMessage);
    }

    public boolean registerAIServiceObserver(AIServiceCallback callback) {
        if (this.mService != null) {
            if (_attachMascotObserverWrapper(callback)) {
                try {
                    this.mService.registerAIServiceObserver(getMascotObserver(callback.toString()));
                    return true;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        Log.e(this.TAG, "registerAIServiceObserver false,mService isNull = ".concat(this.mService == null ? "null" : "NonNull"));
        return false;
    }

    public boolean unregisterAIServiceObserver(AIServiceCallback callback) {
        if (this.mService == null || !_attachMascotObserverWrapper(callback)) {
            return false;
        }
        try {
            this.mService.unregisterAIServiceObserver(getMascotObserver(callback.toString()));
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void playTTS(String words) {
        Log.e(this.TAG, "playTTS,words = " + words);
        IAIServiceAPI iAIServiceAPI = this.mService;
        if (iAIServiceAPI != null) {
            try {
                iAIServiceAPI.playTTS(words);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(this.TAG, "playTTS,mService == null,words = " + words);
    }

    public void stopTTS() {
        Log.e(this.TAG, "stopTTS");
        IAIServiceAPI iAIServiceAPI = this.mService;
        if (iAIServiceAPI != null) {
            try {
                iAIServiceAPI.stopTTS();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(this.TAG, "stopTTS mService == null");
    }

    public void playTTSByType(int instanceType, String words) {
        Log.e(this.TAG, "playTTSByType,type = " + instanceType + ",words = " + words);
        IAIServiceAPI iAIServiceAPI = this.mService;
        if (iAIServiceAPI != null) {
            try {
                iAIServiceAPI.playTTSByType(instanceType, words);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(this.TAG, "playTTSByType,mService == null,instanceType = " + instanceType + ",words = " + words);
    }

    public void playSMSTTS(String words) {
        Log.e(this.TAG, "playSMSTTS,words = " + words);
        IAIServiceAPI iAIServiceAPI = this.mService;
        if (iAIServiceAPI != null) {
            try {
                iAIServiceAPI.playSMSTTS(words);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(this.TAG, "playSMSTTS,mService == null,words = " + words);
    }

    public void stopTTSByType(int instanceType) {
        Log.e(this.TAG, "stopTTSByType,instanceType = " + instanceType);
        IAIServiceAPI iAIServiceAPI = this.mService;
        if (iAIServiceAPI != null) {
            try {
                iAIServiceAPI.stopTTSByType(instanceType);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(this.TAG, "stopTTSByType,mService == null,instanceType = " + instanceType);
    }

    public void awakenAI() {
        Log.e(this.TAG, "awakenAI");
        IAIServiceAPI iAIServiceAPI = this.mService;
        if (iAIServiceAPI != null) {
            try {
                iAIServiceAPI.awakenAI();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(this.TAG, "awakenAI,mService == null");
    }

    public void startVoiceToText() {
        Log.e(this.TAG, "startVoiceToText");
        IAIServiceAPI iAIServiceAPI = this.mService;
        if (iAIServiceAPI != null) {
            try {
                iAIServiceAPI.startVoiceToText();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(this.TAG, "startVoiceToText,mService == null");
    }

    public void stopVoiceToText() {
        Log.e(this.TAG, "stopVoiceToText");
        IAIServiceAPI iAIServiceAPI = this.mService;
        if (iAIServiceAPI != null) {
            try {
                iAIServiceAPI.stopVoiceToText();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(this.TAG, "stopVoiceToText,mService == null");
    }

    private AIServiceObserver getMascotObserver(String key) {
        AIServiceObserver aIServiceObserver;
        synchronized (this.callbackMap) {
            aIServiceObserver = this.callbackMap.get(key);
        }
        return aIServiceObserver;
    }

    private boolean _attachMascotObserverWrapper(final AIServiceCallback callback) {
        if (callback == null) {
            return false;
        }
        synchronized (this.callbackMap) {
            String string = callback.toString();
            if (this.callbackMap.containsKey(string)) {
                return false;
            }
            this.callbackMap.put(string, new AIServiceObserver(callback));
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestReconnect() {
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.what = 101;
        this.mHandler.sendMessageDelayed(messageObtainMessage, this.reconnectTime);
        this.reconnectTime += 5000;
        Log.d(this.TAG, "reconnectTime==== " + this.reconnectTime);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindRemoteService() {
        Log.d(this.TAG, "bindRemoteService");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(SERVICE_PACKAGE, SERVICE_CLASS));
        this.mContext.bindService(intent, this.mServiceConnection, 1);
        requestReconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindRemoteServiceReconnect() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(SERVICE_PACKAGE, SERVICE_CLASS));
        this.mContext.bindService(intent, this.mServiceConnection, 1);
        requestReconnect();
    }
}
