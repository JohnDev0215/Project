package com.ts.appservice.settings.impl.location;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.appservice.settings.callback.DoCallback;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.location.ILocationSettingsCallback;

/* JADX INFO: loaded from: classes.dex */
public class LocationCallback extends DoCallback<ILocationSettingsCallback> {
    private static final String TAG = LocationCallback.class.getSimpleName();

    public LocationCallback(Context context) {
        super(context);
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected String getLogTag() {
        return TAG;
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected void dequeueMessage(Message msg) {
        if (msg.what == 111) {
            callbackOnLocationUseChanged();
            return;
        }
        LogUtil.verbose(TAG, "unhandle Message : " + msg.what);
    }

    public void onLocUseChange() {
        LogUtil.debug(TAG, "onLocUseChange");
        Message msg = getMessage(111);
        enqueueMessage(msg);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void callbackOnLocationUseChanged() {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "onLocUseChange broadcast count : " + n);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onLocUseChange();
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        this.mRemoteCallbacks.finishBroadcast();
    }
}
