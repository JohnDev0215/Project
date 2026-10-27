package com.ts.appservice.settings.impl.microphone;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.appservice.settings.callback.DoCallback;
import com.ts.appservice.settings.impl.key.IKeyServiceBinder;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.microphone.IMicSettingsCallback;

/* JADX INFO: loaded from: classes.dex */
public class MicCallback extends DoCallback<IMicSettingsCallback> {
    private static final String TAG = MicCallback.class.getSimpleName();

    public MicCallback(Context context) {
        super(context);
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected String getLogTag() {
        return TAG;
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected void dequeueMessage(Message msg) {
        if (msg.what == 101) {
            boolean status = 1 == msg.arg1;
            String packageName = (String) msg.obj;
            callbackOnMicUseChanged(packageName, status);
        } else {
            LogUtil.verbose(TAG, "unhandle Message : " + msg.what);
        }
    }

    public void onMicUseChanged(String str, boolean z) {
        LogUtil.debug(TAG, "onMicUseChanged() packageName : " + str + " status :" + z);
        Message message = getMessage(IKeyServiceBinder.SCENE_BT_HMI_BG);
        message.arg1 = z ? 1 : 0;
        message.obj = str;
        enqueueMessage(message);
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
    private void callbackOnMicUseChanged(String packageName, boolean status) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "onMicUseChanged broadcast count : " + n + "packageName: " + packageName + "status :" + status);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onMicUseChanged(packageName, status);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        this.mRemoteCallbacks.finishBroadcast();
    }
}
