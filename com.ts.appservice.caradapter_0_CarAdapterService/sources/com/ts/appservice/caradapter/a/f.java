package com.ts.appservice.caradapter.a;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import com.ts.lib.caradapter.sensor.ISensorCallback;
import com.ts.lib.caradapter.sensor.SensorPacket;

/* JADX INFO: compiled from: CarSensorCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends g<ISensorCallback> {
    private static final String f = "f";

    public f(Context context) {
        super(context);
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
    private void j() {
        int iBeginBroadcast = this.c.beginBroadcast();
        LogUtil.optionError(f, "onSensorBinderChanged broadcast count : " + iBeginBroadcast);
        int i = 0;
        ISensorCallback iSensorCallback = null;
        while (i < iBeginBroadcast) {
            try {
                ISensorCallback broadcastItem = this.c.getBroadcastItem(i);
                try {
                    if (!broadcastItem.asBinder().isBinderAlive()) {
                        LogUtil.info(f, "current binder is invalid");
                    } else if (!this.f10b.get(broadcastItem.asBinder()).booleanValue()) {
                        this.f10b.put(broadcastItem.asBinder(), Boolean.TRUE);
                        broadcastItem.onCarSensorBinderChanged();
                    }
                    i++;
                    iSensorCallback = broadcastItem;
                } catch (RemoteException unused) {
                    iSensorCallback = broadcastItem;
                    i(iSensorCallback);
                    LogUtil.info(f, "remove not alive binder!!!!");
                    this.c.finishBroadcast();
                }
            } catch (RemoteException unused2) {
            }
        }
        this.c.finishBroadcast();
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
    private void k(int i, int i2, SensorPacket sensorPacket) {
        ISensorCallback broadcastItem;
        int iBeginBroadcast = this.c.beginBroadcast();
        LogUtil.optionError(f, "onCarSensorChanged broadcast count : " + iBeginBroadcast + "  data:" + sensorPacket.toString());
        int i3 = 0;
        ISensorCallback iSensorCallback = null;
        while (i3 < iBeginBroadcast) {
            try {
                broadcastItem = this.c.getBroadcastItem(i3);
            } catch (RemoteException unused) {
            }
            try {
                if (broadcastItem.asBinder().isBinderAlive()) {
                    broadcastItem.onCarSensorStateChanged(i, i2, sensorPacket);
                } else {
                    LogUtil.info(f, "current binder is invalid");
                }
                i3++;
                iSensorCallback = broadcastItem;
            } catch (RemoteException unused2) {
                iSensorCallback = broadcastItem;
                i(iSensorCallback);
                LogUtil.info(f, "remove not alive binder!!!!");
                this.c.finishBroadcast();
            }
        }
        this.c.finishBroadcast();
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected void a(Message message) {
        int i = message.what;
        if (i == 132) {
            k(message.arg1, message.arg2, (SensorPacket) message.obj);
        } else {
            if (i != 155) {
                return;
            }
            j();
        }
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected String d() {
        return f;
    }

    public void l(int i, int i2, SensorPacket sensorPacket) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 132;
        messageObtain.arg1 = i;
        messageObtain.arg2 = i2;
        messageObtain.obj = sensorPacket;
        b(messageObtain);
    }

    public void m() {
        c(155);
    }
}
