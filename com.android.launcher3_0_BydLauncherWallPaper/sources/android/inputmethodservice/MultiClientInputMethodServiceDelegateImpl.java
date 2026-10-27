package android.inputmethodservice;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.MultiClientInputMethodServiceDelegate;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.InputChannel;
import android.view.KeyEvent;
import com.android.internal.inputmethod.IMultiClientInputMethod;
import com.android.internal.inputmethod.IMultiClientInputMethodPrivilegedOperations;
import com.android.internal.inputmethod.MultiClientInputMethodPrivilegedOperations;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
final class MultiClientInputMethodServiceDelegateImpl {
    private static final String TAG = "MultiClientInputMethodServiceDelegateImpl";
    private final Context mContext;
    private final MultiClientInputMethodServiceDelegate.ServiceCallback mServiceCallback;
    private final Object mLock = new Object();
    private final MultiClientInputMethodPrivilegedOperations mPrivOps = new MultiClientInputMethodPrivilegedOperations();
    private int mInitializationPhase = 1;

    @Retention(RetentionPolicy.SOURCE)
    private @interface InitializationPhase {
        public static final int INITIALIZE_CALLED = 3;
        public static final int INSTANTIATED = 1;
        public static final int ON_BIND_CALLED = 2;
        public static final int ON_DESTROY_CALLED = 5;
        public static final int ON_UNBIND_CALLED = 4;
    }

    MultiClientInputMethodServiceDelegateImpl(Context context, MultiClientInputMethodServiceDelegate.ServiceCallback serviceCallback) {
        this.mContext = context;
        this.mServiceCallback = serviceCallback;
    }

    void onDestroy() {
        synchronized (this.mLock) {
            int i = this.mInitializationPhase;
            if (i == 1 || i == 4) {
                this.mInitializationPhase = 5;
            } else {
                Log.e(TAG, "unexpected state=" + this.mInitializationPhase);
            }
        }
    }

    private static final class ServiceImpl extends IMultiClientInputMethod.Stub {
        private final WeakReference<MultiClientInputMethodServiceDelegateImpl> mImpl;

        ServiceImpl(MultiClientInputMethodServiceDelegateImpl multiClientInputMethodServiceDelegateImpl) {
            this.mImpl = new WeakReference<>(multiClientInputMethodServiceDelegateImpl);
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethod
        public void initialize(IMultiClientInputMethodPrivilegedOperations iMultiClientInputMethodPrivilegedOperations) {
            MultiClientInputMethodServiceDelegateImpl multiClientInputMethodServiceDelegateImpl = this.mImpl.get();
            if (multiClientInputMethodServiceDelegateImpl == null) {
                return;
            }
            synchronized (multiClientInputMethodServiceDelegateImpl.mLock) {
                if (multiClientInputMethodServiceDelegateImpl.mInitializationPhase != 2) {
                    Log.e(MultiClientInputMethodServiceDelegateImpl.TAG, "unexpected state=" + multiClientInputMethodServiceDelegateImpl.mInitializationPhase);
                } else {
                    multiClientInputMethodServiceDelegateImpl.mPrivOps.set(iMultiClientInputMethodPrivilegedOperations);
                    multiClientInputMethodServiceDelegateImpl.mInitializationPhase = 3;
                    multiClientInputMethodServiceDelegateImpl.mServiceCallback.initialized();
                }
            }
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethod
        public void addClient(int i, int i2, int i3, int i4) {
            MultiClientInputMethodServiceDelegateImpl multiClientInputMethodServiceDelegateImpl = this.mImpl.get();
            if (multiClientInputMethodServiceDelegateImpl == null) {
                return;
            }
            multiClientInputMethodServiceDelegateImpl.mServiceCallback.addClient(i, i2, i3, i4);
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethod
        public void removeClient(int i) {
            MultiClientInputMethodServiceDelegateImpl multiClientInputMethodServiceDelegateImpl = this.mImpl.get();
            if (multiClientInputMethodServiceDelegateImpl == null) {
                return;
            }
            multiClientInputMethodServiceDelegateImpl.mServiceCallback.removeClient(i);
        }
    }

    IBinder onBind(Intent intent) {
        synchronized (this.mLock) {
            if (this.mInitializationPhase == 1) {
                this.mInitializationPhase = 2;
                return new ServiceImpl(this);
            }
            Log.e(TAG, "unexpected state=" + this.mInitializationPhase);
            return null;
        }
    }

    boolean onUnbind(Intent intent) {
        synchronized (this.mLock) {
            int i = this.mInitializationPhase;
            if (i == 2 || i == 3) {
                this.mInitializationPhase = 4;
                this.mPrivOps.dispose();
            } else {
                Log.e(TAG, "unexpected state=" + this.mInitializationPhase);
            }
        }
        return false;
    }

    IBinder createInputMethodWindowToken(int i) {
        return this.mPrivOps.createInputMethodWindowToken(i);
    }

    void acceptClient(int i, MultiClientInputMethodServiceDelegate.ClientCallback clientCallback, KeyEvent.DispatcherState dispatcherState, Looper looper) {
        InputChannel[] inputChannelArrOpenInputChannelPair = InputChannel.openInputChannelPair("MSIMS-session");
        InputChannel inputChannel = inputChannelArrOpenInputChannelPair[0];
        try {
            MultiClientInputMethodClientCallbackAdaptor multiClientInputMethodClientCallbackAdaptor = new MultiClientInputMethodClientCallbackAdaptor(clientCallback, looper, dispatcherState, inputChannelArrOpenInputChannelPair[1]);
            this.mPrivOps.acceptClient(i, multiClientInputMethodClientCallbackAdaptor.createIInputMethodSession(), multiClientInputMethodClientCallbackAdaptor.createIMultiClientInputMethodSession(), inputChannel);
        } finally {
            inputChannel.dispose();
        }
    }

    void reportImeWindowTarget(int i, int i2, IBinder iBinder) {
        this.mPrivOps.reportImeWindowTarget(i, i2, iBinder);
    }

    boolean isUidAllowedOnDisplay(int i, int i2) {
        return this.mPrivOps.isUidAllowedOnDisplay(i, i2);
    }

    void setActive(int i, boolean z) {
        this.mPrivOps.setActive(i, z);
    }
}
