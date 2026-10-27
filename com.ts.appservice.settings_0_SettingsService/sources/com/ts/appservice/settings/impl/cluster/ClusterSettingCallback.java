package com.ts.appservice.settings.impl.cluster;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.appservice.settings.callback.DoCallback;
import com.ts.lib.settings.cluster.IClusterSettingsCallback;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ClusterSettingCallback extends DoCallback<IClusterSettingsCallback> {
    private static final String TAG = ClusterSettingCallback.class.getSimpleName();

    public ClusterSettingCallback(Context context) {
        super(context);
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected String getLogTag() {
        return TAG;
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected void dequeueMessage(Message msg) {
        int i = msg.what;
        if (i == 106) {
            int insDisplayValue = msg.arg1;
            callbackOnINSAdasCenterDisplayChanged(insDisplayValue);
            return;
        }
        if (i == 108) {
            int dayNightModeValue = msg.arg1;
            callbackOnINSCenterDisplayChanged(dayNightModeValue);
        } else {
            if (i == 109) {
                int dayNightModeValue2 = msg.arg1;
                callbackOnIDayNightModeChanged(dayNightModeValue2);
                return;
            }
            LogUtil.verbose(TAG, "unhandle Message : " + msg.what);
        }
    }

    public void onAdasCenterDisplayChanged(int value) {
        LogUtil.debug(TAG, "onAdasCenterDisplayChanged() value : " + value);
        Message msg = getMessage(106);
        msg.arg1 = value;
        enqueueMessage(msg);
    }

    public void onINSCenterDisplayChanged(int value) {
        LogUtil.debug(TAG, "onINSCenterDisplayChanged() value : " + value);
        Message msg = getMessage(108);
        msg.arg1 = value;
        enqueueMessage(msg);
    }

    public void onDayNightModeChanged(int mode) {
        LogUtil.debug(TAG, "onDayNightModeChanged mode : " + mode);
        Message msg = getMessage(109);
        msg.arg1 = mode;
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
    private void callbackOnINSAdasCenterDisplayChanged(int value) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "OnINSAdasCenterDisplayChanged broadcast count : " + n + "theme: " + value);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onINSAdasCenterDisplayChanged(value);
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
    private void callbackOnINSCenterDisplayChanged(int value) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "onINSCenterDisplay broadcast count : " + n + "theme: " + value);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onINSCenterDisplay(value);
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
    private void callbackOnIDayNightModeChanged(int mode) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "onDayNightModeChanged broadcast count : " + n + "theme: " + mode);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onDayNightModeChanged(mode);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        this.mRemoteCallbacks.finishBroadcast();
    }
}
