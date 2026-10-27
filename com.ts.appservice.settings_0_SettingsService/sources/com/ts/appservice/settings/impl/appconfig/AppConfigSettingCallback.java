package com.ts.appservice.settings.impl.appconfig;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.appservice.settings.callback.DoCallback;
import com.ts.lib.settings.appconfig.IAppConfigSettingsCallback;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class AppConfigSettingCallback extends DoCallback<IAppConfigSettingsCallback> {
    private static final String TAG = AppConfigSettingCallback.class.getSimpleName();

    public AppConfigSettingCallback(Context context) {
        super(context);
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected String getLogTag() {
        return TAG;
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected void dequeueMessage(Message msg) {
        switch (msg.what) {
            case 301:
                int cpaaState = msg.arg1;
                callbackOnConfigAreaChanged(cpaaState);
                break;
            case 302:
                int cpaaState2 = msg.arg1;
                callbackOnCpaaStateChanged(cpaaState2);
                break;
            case 303:
                boolean carTypeStatus = ((Boolean) msg.obj).booleanValue();
                callbackOnCarTypeChanged(carTypeStatus);
                break;
            default:
                LogUtil.verbose(TAG, "unhandle Message : " + msg.what);
                break;
        }
    }

    public void onConfigAreaChanged(int state) {
        LogUtil.debug(TAG, "onConfigAreaChanged() value : " + state);
        Message msg = getMessage(301);
        msg.arg1 = state;
        enqueueMessage(msg);
    }

    public void onCpaaInStallChanged(int state) {
        LogUtil.debug(TAG, "onCpaaInStallChanged state : " + state);
        Message msg = getMessage(302);
        msg.arg1 = state;
        enqueueMessage(msg);
    }

    public void onCarTypeChanged(boolean status) {
        LogUtil.debug(TAG, "onCarTypeChanged status : " + status);
        Message msg = getMessage(303);
        msg.obj = Boolean.valueOf(status);
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
    private void callbackOnConfigAreaChanged(int state) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnConfigAreaChanged broadcast count : " + n + "state: " + state);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onChangeConfigArea(state);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        this.mRemoteCallbacks.finishBroadcast();
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
    private void callbackOnCpaaStateChanged(int state) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnCpaaStateChanged broadcast count : " + n + "state: " + state);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onChangedCpaa(state);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        this.mRemoteCallbacks.finishBroadcast();
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
    private void callbackOnCarTypeChanged(boolean status) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnCarTypeChanged broadcast count : " + n + "status: " + status);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onStatusChangeCarType(status);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        this.mRemoteCallbacks.finishBroadcast();
    }
}
