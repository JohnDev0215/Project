package com.ts.appservice.settings.impl.cartips;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.appservice.settings.callback.DoCallback;
import com.ts.lib.settings.cartips.ICartipsSettingsCallback;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CartipsSettingCallback extends DoCallback<ICartipsSettingsCallback> {
    private static final String TAG = CartipsSettingCallback.class.getSimpleName();

    public CartipsSettingCallback(Context context) {
        super(context);
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected String getLogTag() {
        return TAG;
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected void dequeueMessage(Message msg) {
        if (msg.what == 112) {
            int carTipsSoundTypeValue = msg.arg1;
            callbackOnCarTipsSoundTypeChanged(carTipsSoundTypeValue);
            return;
        }
        LogUtil.verbose(TAG, "unhandle Message : " + msg.what);
    }

    public void onCarTipsSoundTypeChanged(int value) {
        LogUtil.debug(TAG, "onCarTipsSoundTypeChanged() value : " + value);
        Message msg = getMessage(112);
        msg.arg1 = value;
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
    private void callbackOnCarTipsSoundTypeChanged(int value) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "OnCarTipsSoundTypeChanged broadcast count : " + n + "theme: " + value);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onCarTipsSoundTypeChanged(value);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        this.mRemoteCallbacks.finishBroadcast();
    }
}
