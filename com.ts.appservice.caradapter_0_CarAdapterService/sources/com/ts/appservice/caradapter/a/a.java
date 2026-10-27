package com.ts.appservice.caradapter.a;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.lib.caradapter.assist.CarAssistPacket;
import com.ts.lib.caradapter.assist.ICarAssistAdapterCallback;
import com.ts.lib.caradapter.common.utilities.LogUtil;

/* JADX INFO: compiled from: CarAssistCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends g<ICarAssistAdapterCallback> {
    private static final String f = "a";

    public a(Context context) {
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
        LogUtil.optionError(f, "OnAssistBinderChanged broadcast count : " + iBeginBroadcast);
        int i = 0;
        ICarAssistAdapterCallback iCarAssistAdapterCallback = null;
        while (i < iBeginBroadcast) {
            try {
                ICarAssistAdapterCallback broadcastItem = this.c.getBroadcastItem(i);
                try {
                    if (!broadcastItem.asBinder().isBinderAlive()) {
                        LogUtil.info(f, "current binder is invalid");
                    } else if (!this.f10b.get(broadcastItem.asBinder()).booleanValue()) {
                        this.f10b.put(broadcastItem.asBinder(), Boolean.TRUE);
                        broadcastItem.onCarAssistBinderChanged();
                    }
                    i++;
                    iCarAssistAdapterCallback = broadcastItem;
                } catch (RemoteException unused) {
                    iCarAssistAdapterCallback = broadcastItem;
                    i(iCarAssistAdapterCallback);
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
    private void k(int i, int i2, CarAssistPacket carAssistPacket) {
        ICarAssistAdapterCallback broadcastItem;
        int iBeginBroadcast = this.c.beginBroadcast();
        LogUtil.optionError(f, "onAssistChanged broadcast count : " + iBeginBroadcast + "  data" + carAssistPacket.toString());
        int i3 = 0;
        ICarAssistAdapterCallback iCarAssistAdapterCallback = null;
        while (i3 < iBeginBroadcast) {
            try {
                broadcastItem = this.c.getBroadcastItem(i3);
            } catch (RemoteException unused) {
            }
            try {
                if (broadcastItem.asBinder().isBinderAlive()) {
                    broadcastItem.onCarAssistStateChanged(i, i2, carAssistPacket);
                } else {
                    LogUtil.info(f, "current binder is invalid");
                }
                i3++;
                iCarAssistAdapterCallback = broadcastItem;
            } catch (RemoteException unused2) {
                iCarAssistAdapterCallback = broadcastItem;
                i(iCarAssistAdapterCallback);
                LogUtil.info(f, "remove not alive binder!!!!");
                this.c.finishBroadcast();
            }
        }
        this.c.finishBroadcast();
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected void a(Message message) {
        int i = message.what;
        if (i != 125) {
            if (i != 149) {
                return;
            }
            j();
        } else {
            Object obj = message.obj;
            if (obj instanceof CarAssistPacket) {
                k(message.arg1, message.arg2, (CarAssistPacket) obj);
            }
        }
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected String d() {
        return f;
    }

    public void l() {
        c(149);
    }

    public void m(int i, int i2, CarAssistPacket carAssistPacket) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 125;
        messageObtain.arg1 = i;
        messageObtain.arg2 = i2;
        messageObtain.obj = carAssistPacket;
        b(messageObtain);
    }
}
