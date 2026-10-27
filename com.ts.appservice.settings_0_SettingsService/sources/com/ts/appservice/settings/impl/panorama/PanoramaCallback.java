package com.ts.appservice.settings.impl.panorama;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.appservice.settings.callback.DoCallback;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.panorama.IPanoramaSettingsCallback;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.nntp.NNTPReply;

/* JADX INFO: loaded from: classes.dex */
public class PanoramaCallback extends DoCallback<IPanoramaSettingsCallback> {
    private static final String TAG = PanoramaCallback.class.getSimpleName();

    public PanoramaCallback(Context context) {
        super(context);
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected String getLogTag() {
        return TAG;
    }

    @Override // com.ts.appservice.settings.callback.DoCallback
    protected void dequeueMessage(Message msg) {
        switch (msg.what) {
            case 201:
                callbackOnPanoOutputStateChanged(msg.arg1);
                break;
            case 202:
                callbackOnPanoWorkStateChanged(msg.arg1);
                break;
            case 203:
                callbackOnPanOutputSignalChanged(msg.arg1);
                break;
            case 204:
                callbackOnBackLineConfigChanged(msg.arg1);
                break;
            case NNTPReply.CLOSING_CONNECTION /* 205 */:
                callbackOnPanoramaOnlineStateChanged(msg.arg1);
                break;
            case 206:
                callbackOnRFCameraSwitchStateChanged(msg.arg1);
                break;
            case 207:
                callbackOnPanoRotationChanged(msg.arg1);
                break;
            case 208:
                callbackOnLVDSStateChanged(msg.arg1);
                break;
            case 209:
                callbackDisplayModeChanged(msg.arg1);
                break;
            case 210:
                callbackOnEmergencyButtonStateChanged(msg.arg1);
                break;
            case 211:
                callbackOnACUStateChanged(msg.arg1);
                break;
            case FTPReply.DIRECTORY_STATUS /* 212 */:
                boolean state = 1 == msg.arg1;
                String packageName = (String) msg.obj;
                callbackOnCameraUseChanged(packageName, state);
                break;
            case FTPReply.FILE_STATUS /* 213 */:
                callbackOnCarInfoChanged(msg.arg1);
                break;
            case 214:
                callbackOnPanoramaTransparenceChanged(msg.arg1);
                break;
            case FTPReply.NAME_SYSTEM_TYPE /* 215 */:
                callbackOnPanoAPAStateChanged(msg.arg1);
                break;
            case 216:
                callbackOnCameraStateChanged(msg.arg1, msg.arg2);
                break;
            default:
                LogUtil.verbose(TAG, "unhandle Message : " + msg.what);
                break;
        }
    }

    public void onPanoOutputStateChanged(int mode) {
        LogUtil.debug(TAG, "onPanoOutputStateChanged mode : " + mode);
        Message msg = getMessage(201);
        msg.arg1 = mode;
        enqueueMessage(msg);
    }

    public void onPanoWorkStateChanged(int mode) {
        LogUtil.debug(TAG, "onPanoWorkStateChanged mode : " + mode);
        Message msg = getMessage(202);
        msg.arg1 = mode;
        enqueueMessage(msg);
    }

    public void onPanOutputSignalChanged(int value) {
        LogUtil.debug(TAG, "onPanOutputSignalChanged value : " + value);
        Message msg = getMessage(203);
        msg.arg1 = value;
        enqueueMessage(msg);
    }

    public void onBackLineConfigChanged(int mode) {
        LogUtil.debug(TAG, "onBackLineConfigChanged mode : " + mode);
        Message msg = getMessage(204);
        msg.arg1 = mode;
        enqueueMessage(msg);
    }

    public void onPanoramaOnlineStateChanged(int value) {
        LogUtil.debug(TAG, "onPanoramaOnlineStateChanged value : " + value);
        Message msg = getMessage(NNTPReply.CLOSING_CONNECTION);
        msg.arg1 = value;
        enqueueMessage(msg);
    }

    public void onRFCameraSwitchStateChanged(int state) {
        LogUtil.debug(TAG, "onRFCameraSwitchStateChanged state : " + state);
        Message msg = getMessage(206);
        msg.arg1 = state;
        enqueueMessage(msg);
    }

    public void onPanoRotationChanged(int value) {
        LogUtil.debug(TAG, "onPanoRotationChanged value : " + value);
        Message msg = getMessage(207);
        msg.arg1 = value;
        enqueueMessage(msg);
    }

    public void onLVDSStateChanged(int state) {
        LogUtil.debug(TAG, "onLVDSStateChanged state : " + state);
        Message msg = getMessage(208);
        msg.arg1 = state;
        enqueueMessage(msg);
    }

    public void onDisplayModeChanged(int mode) {
        LogUtil.debug(TAG, "onDisplayModeChanged mode : " + mode);
        Message msg = getMessage(209);
        msg.arg1 = mode;
        enqueueMessage(msg);
    }

    public void onEmergencyButtonStateChanged(int state) {
        LogUtil.debug(TAG, "onEmergencyButtonStateChanged state : " + state);
        Message msg = getMessage(210);
        msg.arg1 = state;
        enqueueMessage(msg);
    }

    public void onACUStateChanged(int state) {
        LogUtil.debug(TAG, "onACUStateChanged state : " + state);
        Message msg = getMessage(211);
        msg.arg1 = state;
        enqueueMessage(msg);
    }

    public void onCameraUseChange(String str, boolean z) {
        LogUtil.debug(TAG, "onThemeChanged() packageName : " + str + " state :" + z);
        Message message = getMessage(FTPReply.DIRECTORY_STATUS);
        message.arg1 = z ? 1 : 0;
        message.obj = str;
        enqueueMessage(message);
    }

    public void onCarInfoChanged(int state) {
        LogUtil.debug(TAG, "onCarInfoChanged state : " + state);
        Message msg = getMessage(FTPReply.FILE_STATUS);
        msg.arg1 = state;
        enqueueMessage(msg);
    }

    public void onPanoramaTransparenceChanged(int state) {
        LogUtil.debug(TAG, "onPanoramaTransparenceChanged state : " + state);
        Message msg = getMessage(214);
        msg.arg1 = state;
        enqueueMessage(msg);
    }

    public void onPanoAPAStateChanged(int state) {
        LogUtil.debug(TAG, "onPanoAPAStateChanged state : " + state);
        Message msg = getMessage(FTPReply.NAME_SYSTEM_TYPE);
        msg.arg1 = state;
        enqueueMessage(msg);
    }

    public void onCameraStateChanged(int cameraId, int state) {
        LogUtil.debug(TAG, "onCameraStateChanged camera id is " + cameraId + " state : " + state);
        Message msg = getMessage(216);
        msg.arg1 = cameraId;
        msg.arg2 = state;
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
    private void callbackOnPanoOutputStateChanged(int mode) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnPanoOutputStateChanged broadcast count : " + n + "mode :" + mode);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onPanoOutputStateChanged(mode);
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
    private void callbackOnPanoWorkStateChanged(int mode) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnPanoWorkStateChanged broadcast count : " + n + "mode :" + mode);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onPanoWorkStateChanged(mode);
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
    private void callbackOnPanOutputSignalChanged(int value) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnPanOutputSignalChanged broadcast count : " + n + "value :" + value);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onPanOutputSignalChanged(value);
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
    private void callbackOnBackLineConfigChanged(int mode) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnBackLineConfigChanged broadcast count : " + n + "mode :" + mode);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onBackLineConfigChanged(mode);
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
    private void callbackOnPanoramaOnlineStateChanged(int value) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnPanoramaOnlineStateChanged broadcast count : " + n + "value :" + value);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onPanoramaOnlineStateChanged(value);
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
    private void callbackOnRFCameraSwitchStateChanged(int state) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnRFCameraSwitchStateChanged broadcast count : " + n + "state :" + state);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onRFCameraSwitchStateChanged(state);
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
    private void callbackOnPanoRotationChanged(int value) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnPanoRotationChanged broadcast count : " + n + "value :" + value);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onPanoRotationChanged(value);
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
    private void callbackOnLVDSStateChanged(int state) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnLVDSStateChanged broadcast count : " + n + "state :" + state);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onLVDSStateChanged(state);
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
    private void callbackDisplayModeChanged(int mode) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackDisplayModeChanged broadcast count : " + n + "mode :" + mode);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onDisplayModeChanged(mode);
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
    private void callbackOnEmergencyButtonStateChanged(int state) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnEmergencyButtonStateChanged broadcast count : " + n + "state :" + state);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onEmergencyButtonStateChanged(state);
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
    private void callbackOnACUStateChanged(int state) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnACUStateChanged broadcast count : " + n + "state :" + state);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onACUStateChanged(state);
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
    private void callbackOnCameraUseChanged(String packageName, boolean state) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "onINSThemeChanged broadcast count : " + n + "packageName: " + packageName + "state :" + state);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onCameraUseChange(packageName, state);
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
    private void callbackOnCarInfoChanged(int state) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnCarInfoChanged broadcast count : " + n + "state :" + state);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onCarInfoChanged(state);
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
    private void callbackOnPanoramaTransparenceChanged(int state) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnPanoramaTransparenceChanged broadcast count : " + n + "state :" + state);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onPanoramaTransparenceChanged(state);
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
    private void callbackOnPanoAPAStateChanged(int state) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnPanoAPAStateChanged broadcast count : " + n + "state :" + state);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onPanoAPAStateChanged(state);
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
    private void callbackOnCameraStateChanged(int cameraId, int state) {
        int n = this.mRemoteCallbacks.beginBroadcast();
        LogUtil.debug(TAG, "callbackOnCameraStateChanged broadcast count : " + n + "camera id :" + cameraId + "state :" + state);
        for (int i = 0; i < n; i++) {
            try {
                this.mRemoteCallbacks.getBroadcastItem(i).onCameraStateChanged(cameraId, state);
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
        this.mRemoteCallbacks.finishBroadcast();
    }
}
