package com.ts.appservice.caradapter.a;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.ICabinStateCallback;
import com.ts.lib.caradapter.common.utilities.LogUtil;

/* JADX INFO: compiled from: CarCabinCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends g<ICabinStateCallback> {
    private static final String f = "c";

    public c(Context context) {
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
        LogUtil.optionError(f, "callbackCabinBinderChanged broadcast count : " + iBeginBroadcast);
        int i = 0;
        ICabinStateCallback iCabinStateCallback = null;
        while (i < iBeginBroadcast) {
            try {
                ICabinStateCallback broadcastItem = this.c.getBroadcastItem(i);
                try {
                    if (!broadcastItem.asBinder().isBinderAlive()) {
                        LogUtil.info(f, "current binder is invalid");
                    } else if (!this.f10b.get(broadcastItem.asBinder()).booleanValue()) {
                        this.f10b.put(broadcastItem.asBinder(), Boolean.TRUE);
                        broadcastItem.onCabinBinderChanged();
                    }
                    i++;
                    iCabinStateCallback = broadcastItem;
                } catch (RemoteException unused) {
                    iCabinStateCallback = broadcastItem;
                    i(iCabinStateCallback);
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
    private void k(int i, int i2, CabinPacket cabinPacket) {
        ICabinStateCallback broadcastItem;
        int iBeginBroadcast = this.c.beginBroadcast();
        LogUtil.optionError(f, "callbackOnCabinChanged broadcast count : " + iBeginBroadcast + "  data" + cabinPacket.toString());
        int i3 = 0;
        ICabinStateCallback iCabinStateCallback = null;
        while (i3 < iBeginBroadcast) {
            try {
                broadcastItem = this.c.getBroadcastItem(i3);
            } catch (RemoteException unused) {
            }
            try {
                if (broadcastItem.asBinder().isBinderAlive()) {
                    broadcastItem.onCabinStateChanged(i, i2, cabinPacket);
                } else {
                    LogUtil.info(f, "current binder is invalid");
                }
                i3++;
                iCabinStateCallback = broadcastItem;
            } catch (RemoteException unused2) {
                iCabinStateCallback = broadcastItem;
                i(iCabinStateCallback);
                LogUtil.info(f, "remove not alive binder!!!!");
                this.c.finishBroadcast();
            }
        }
        this.c.finishBroadcast();
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected void a(Message message) {
        int i = message.what;
        if (i != 102) {
            if (i != 150) {
                return;
            }
            j();
        } else {
            Object obj = message.obj;
            if (obj instanceof CabinPacket) {
                k(message.arg1, message.arg2, (CabinPacket) obj);
            }
        }
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected String d() {
        return f;
    }

    public void l() {
        c(150);
    }

    public void m(int i, int i2, CabinPacket cabinPacket) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 102;
        messageObtain.arg1 = i;
        messageObtain.arg2 = i2;
        messageObtain.obj = cabinPacket;
        b(messageObtain);
    }
}
