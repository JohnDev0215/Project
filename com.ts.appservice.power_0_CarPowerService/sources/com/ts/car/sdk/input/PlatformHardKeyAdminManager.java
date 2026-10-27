package com.ts.car.sdk.input;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import ts.car.input.IHardKeyPolicyManager;
import ts.car.service.input.IHardKeyAdminManager;

/* JADX INFO: loaded from: classes.dex */
@PlatformService
public class PlatformHardKeyAdminManager extends SimpleServiceBase implements IHardKeyAdminManager {
    private static final String REMOTE_SERVICE_NAME = "input_policy";
    private static final String SERVICE_COMPONENT = "com.ts.car.input/.InputCoreService";
    private static final String TAG = PlatformHardKeyAdminManager.class.getSimpleName();
    private IHardKeyPolicyManager mService;

    @PlatformConstruction
    public PlatformHardKeyAdminManager(Context context, IBinder binder) {
        super(context, SERVICE_COMPONENT, REMOTE_SERVICE_NAME, binder);
    }

    @Override // ts.car.service.IPlatformService
    public String serviceName() {
        return "TS-InputService_1.0.0";
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceConnected(IBinder iBinder) {
        Log.d(TAG, "onServiceConnected.");
        this.mService = IHardKeyPolicyManager.Stub.asInterface(iBinder);
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceDied() {
        Log.d(TAG, "onServiceDied.");
        this.mService = null;
    }

    @Override // ts.car.service.input.IHardKeyAdminManager
    public void performKeyEvent(KeyEvent keyEvent, int targetDisplay) throws RemoteException {
        IHardKeyPolicyManager iHardKeyPolicyManager = this.mService;
        if (iHardKeyPolicyManager == null) {
            throw new IllegalStateException("IHardKeyAdminManager is not Ready!");
        }
        iHardKeyPolicyManager.processHardKeyFromPolicy(keyEvent, targetDisplay);
    }

    @Override // ts.car.service.input.IHardKeyAdminManager
    public void performKeyLongPress(KeyEvent keyEvent, int targetDisplay, int millisecond) throws RemoteException {
        throw new RemoteException("Function not implemented!");
    }
}
