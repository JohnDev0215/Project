package com.ts.lib.settings.common.utilities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class ServiceBindHelper {
    private static final int MSG_RETRY_BIND_SERVICE = 201;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_RETRYING = 3;
    private static final String TAG = "ServiceBindHelper";
    private BindPolicy mBindPolicy;
    private int mConnectionState;
    private final Context mContext;
    private Handler mHandler;
    private Intent mIntent;
    private int mRetryCount;
    private final ServiceConnection mServiceConnection;
    private ServiceConnection mUserCallback;

    public static abstract class BindPolicy {
        public abstract int getBindFlags();

        public abstract long getReBindInterval();

        public abstract int getRetryLimit();

        public abstract boolean isAutoReBind();
    }

    static /* synthetic */ int access$608(ServiceBindHelper x0) {
        int i = x0.mRetryCount;
        x0.mRetryCount = i + 1;
        return i;
    }

    public ServiceBindHelper(Context context, ServiceConnection userCallback) {
        this(context, userCallback, new DefaultBindPolicy());
    }

    public ServiceBindHelper(Context context, ServiceConnection userCallback, BindPolicy bindPolicy) {
        this.mServiceConnection = new ServiceConnection() { // from class: com.ts.lib.settings.common.utilities.ServiceBindHelper.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(ServiceBindHelper.TAG, "onServiceConnected() called with: name = [" + name + "], service = [" + service + "]");
                if (ServiceBindHelper.this.mConnectionState != 2) {
                    ServiceBindHelper.this.mConnectionState = 2;
                    ServiceBindHelper.this.mUserCallback.onServiceConnected(name, service);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                Log.d(ServiceBindHelper.TAG, "onServiceDisconnected() called with: name = [" + name + "]");
                ServiceBindHelper.this.mUserCallback.onServiceDisconnected(name);
                ServiceBindHelper.this.mContext.unbindService(ServiceBindHelper.this.mServiceConnection);
                ServiceBindHelper.this.mConnectionState = 0;
                ServiceBindHelper.this.requestAutoReBind();
            }
        };
        Log.d(TAG, "ServiceBindHelper() called with: context = [" + context + "], userCallback = [" + userCallback + "], bindPolicy = [" + bindPolicy + "]");
        if (context == null) {
            throw new NullPointerException("context is null");
        }
        if (userCallback == null) {
            throw new NullPointerException("userCallback is null");
        }
        if (bindPolicy == null) {
            throw new NullPointerException("bindPolicy is null");
        }
        this.mContext = context;
        this.mUserCallback = userCallback;
        this.mBindPolicy = bindPolicy;
        initHandler();
    }

    private void initHandler() {
        if (this.mHandler == null) {
            this.mHandler = new Handler(this.mContext.getMainLooper()) { // from class: com.ts.lib.settings.common.utilities.ServiceBindHelper.2
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what == 201) {
                        if (ServiceBindHelper.this.reBind()) {
                            ServiceBindHelper.this.mRetryCount = 0;
                        } else {
                            ServiceBindHelper.access$608(ServiceBindHelper.this);
                            ServiceBindHelper.this.requestAutoReBind();
                        }
                    }
                }
            };
        }
    }

    public void bind(Intent intent) {
        Log.d(TAG, "bind() called with: intent = [" + intent + "]");
        if (intent == null) {
            throw new NullPointerException("intent is null");
        }
        if (this.mConnectionState != 0) {
            Log.e(TAG, "bind: isBinding");
            return;
        }
        this.mConnectionState = 1;
        this.mIntent = intent;
        boolean bindRes = this.mContext.bindService(intent, this.mServiceConnection, this.mBindPolicy.getBindFlags());
        if (!bindRes) {
            requestAutoReBind();
        }
    }

    public void unbind() {
        this.mHandler.removeCallbacksAndMessages(null);
        this.mContext.unbindService(this.mServiceConnection);
        this.mConnectionState = 0;
        this.mIntent = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestAutoReBind() {
        Log.d(TAG, "requestAutoReBind() called");
        if (this.mBindPolicy.isAutoReBind() && this.mRetryCount < this.mBindPolicy.getRetryLimit()) {
            if (this.mHandler.hasMessages(201)) {
                this.mHandler.removeMessages(201);
            }
            this.mHandler.sendEmptyMessageDelayed(201, this.mBindPolicy.getReBindInterval());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean reBind() {
        Log.d(TAG, "reBind() called");
        if (this.mConnectionState == 2) {
            return true;
        }
        this.mConnectionState = 3;
        boolean bindRes = this.mContext.bindService(this.mIntent, this.mServiceConnection, this.mBindPolicy.getBindFlags());
        Log.d(TAG, "reBind() returned: " + bindRes);
        return false;
    }

    public int getConnectionState() {
        return this.mConnectionState;
    }

    public static class DefaultBindPolicy extends BindPolicy {
        private static final int RETRY_INTERVAL = 2000;
        public static final int RETRY_LIMIT = 3;

        @Override // com.ts.lib.settings.common.utilities.ServiceBindHelper.BindPolicy
        public long getReBindInterval() {
            return FileUtils.FAT_FILE_TIMESTAMP_GRANULARITY;
        }

        @Override // com.ts.lib.settings.common.utilities.ServiceBindHelper.BindPolicy
        public int getBindFlags() {
            return 1;
        }

        @Override // com.ts.lib.settings.common.utilities.ServiceBindHelper.BindPolicy
        public boolean isAutoReBind() {
            return true;
        }

        @Override // com.ts.lib.settings.common.utilities.ServiceBindHelper.BindPolicy
        public int getRetryLimit() {
            return 3;
        }
    }
}
