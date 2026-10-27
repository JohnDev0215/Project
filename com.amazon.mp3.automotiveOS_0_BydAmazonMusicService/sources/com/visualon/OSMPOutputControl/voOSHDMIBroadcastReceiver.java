package com.visualon.OSMPOutputControl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes7.dex */
public class voOSHDMIBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "HDMIBroadcastReceiver";
    protected voOSHDMIDeviceType m_ManufacturerModel;
    protected ICheckHDMIState m_implCheckHDMIState;

    public interface ICheckHDMIState {
        void checkHDMIState(int i);
    }

    public voOSHDMIBroadcastReceiver(voOSHDMIDeviceType vooshdmidevicetype) {
        voOSHDMIDeviceType vooshdmidevicetype2 = voOSHDMIDeviceType.NoHDMI;
        this.m_implCheckHDMIState = null;
        this.m_ManufacturerModel = vooshdmidevicetype;
    }

    public void setCheckHDMIStateInterface(ICheckHDMIState iCheckHDMIState) {
        this.m_implCheckHDMIState = iCheckHDMIState;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle extras = intent.getExtras();
        switch (AnonymousClass1.$SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[this.m_ManufacturerModel.ordinal()]) {
            case 1:
                receiveMotorola_V2_3Broadcast(action, extras);
                break;
            case 2:
                receiveSELTsBroadcast(action, extras);
                break;
            case 3:
                receiveHTCPC36100Broadcast(action, extras);
                break;
            case 4:
                receiveLGP920Broadcast(action, extras);
                break;
            case 5:
                receiveSharpSHsBroadcast(action, extras);
                break;
            case 6:
                receiveVersion3Broadcast(action, extras);
                break;
            case 7:
                receiveVersion4Broadcast(action, extras);
                break;
        }
    }

    /* JADX INFO: renamed from: com.visualon.OSMPOutputControl.voOSHDMIBroadcastReceiver$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType;

        static {
            int[] iArr = new int[voOSHDMIDeviceType.values().length];
            $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType = iArr;
            try {
                iArr[voOSHDMIDeviceType.Motorola_V2_3.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[voOSHDMIDeviceType.SE_LTs.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[voOSHDMIDeviceType.HTC_PC36100.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[voOSHDMIDeviceType.LG_P920.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[voOSHDMIDeviceType.SHARP_SHs.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[voOSHDMIDeviceType.StandardVersion3.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[voOSHDMIDeviceType.StandardVersion4.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$visualon$OSMPOutputControl$voOSHDMIDeviceType[voOSHDMIDeviceType.NoHDMI.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private void receiveMotorola_V2_3Broadcast(String str, Bundle bundle) {
        if (!str.equals("com.motorola.intent.action.externaldisplaystate") || bundle == null || this.m_implCheckHDMIState == null) {
            return;
        }
        this.m_implCheckHDMIState.checkHDMIState(bundle.getInt("hdmi"));
    }

    private void receiveSELTsBroadcast(String str, Bundle bundle) {
        if (!str.equals("com.sonyericsson.intent.action.HDMI_EVENT") || bundle == null) {
            return;
        }
        String string = bundle.getString("com.sonyericsson.intent.extra.HDMI_STATE");
        if (this.m_implCheckHDMIState == null) {
            return;
        }
        this.m_implCheckHDMIState.checkHDMIState(string.compareToIgnoreCase("HDMI_IN_USE") == 0 ? 1 : 0);
    }

    private void receiveHTCPC36100Broadcast(String str, Bundle bundle) {
        if (!str.equals("android.intent.action.HTC.HEADSET_PLUG") || bundle == null || bundle.getString("name").compareToIgnoreCase("Headset") != 0 || this.m_implCheckHDMIState == null) {
            return;
        }
        this.m_implCheckHDMIState.checkHDMIState(bundle.getInt("state") == 2048 ? 1 : 0);
    }

    private void receiveLGP920Broadcast(String str, Bundle bundle) {
        if (!str.equals("android.intent.action.HDMI_PLUG") || bundle == null || bundle.getString("name").compareToIgnoreCase("tv") != 0 || this.m_implCheckHDMIState == null) {
            return;
        }
        this.m_implCheckHDMIState.checkHDMIState(bundle.getInt("state"));
    }

    private void receiveSharpSHsBroadcast(String str, Bundle bundle) {
        if (this.m_implCheckHDMIState == null) {
            return;
        }
        if (str.equals("HDMI_CONNECTED")) {
            this.m_implCheckHDMIState.checkHDMIState(1);
        } else if (str.equals("HDMI_DISCONNECTED")) {
            this.m_implCheckHDMIState.checkHDMIState(0);
        }
    }

    private void receiveVersion3Broadcast(String str, Bundle bundle) {
        if (!str.equals("android.intent.action.HDMI_AUDIO_PLUG") || bundle == null || bundle.getString("name").compareToIgnoreCase("hdmi") != 0 || this.m_implCheckHDMIState == null) {
            return;
        }
        int i = bundle.getInt("state");
        Log.i(TAG, "nState = " + i);
        this.m_implCheckHDMIState.checkHDMIState(i);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void receiveVersion4Broadcast(String str, Bundle bundle) {
        if (!str.equals("android.intent.action.HDMI_PLUGGED") || bundle == null || this.m_implCheckHDMIState == null) {
            return;
        }
        this.m_implCheckHDMIState.checkHDMIState(bundle.getBoolean("state") ? 1 : 0);
    }
}
