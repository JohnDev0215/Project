package com.ts.appservice.settings.impl.key;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import android.view.KeyEvent;
import com.ts.appservice.settings.callback.DoCallback;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.key.IKeySettingsLongPressEventCallback;

/* JADX INFO: loaded from: classes.dex */
public class LongSceneKeyCallback extends DoCallback<IKeySettingsLongPressEventCallback> {
    private static final String TAG = LongSceneKeyCallback.class.getSimpleName();

    protected LongSceneKeyCallback(Context context) {
        super(context);
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected String getLogTag() {
        return TAG;
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected void dequeueMessage(Message msg) {
        if (msg.what == 105) {
            KeyEvent event = (KeyEvent) msg.obj;
            int sceneType = msg.arg1;
            int targetDisplay = msg.arg2;
            callbackOnLongKeyEvent(event, sceneType, targetDisplay);
            return;
        }
        LogUtil.info(TAG, "unhandle Message : " + msg.what);
    }

    public void onLongKeyEvent(KeyEvent event, int sceneType, int targetDisplay) {
        LogUtil.debug(TAG, "longOnKeyEvent event: " + event + ",sceneType: " + sceneType + "targetDisplay: " + targetDisplay);
        Message msg = getMessage(105);
        msg.obj = event;
        msg.arg1 = sceneType;
        msg.arg2 = targetDisplay;
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
    private void callbackOnLongKeyEvent(KeyEvent event, int sceneType, int targetDisplay) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackLongOnKeyEvent broadcast count : " + n + "KeyEvent: " + event + " sceneType: " + sceneType + "targetDisplay: " + targetDisplay);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onKeyLongPressed(event, sceneType, targetDisplay);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        this.mRemoteCallbacks.finishBroadcast();
    }
}
