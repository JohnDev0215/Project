package com.ts.appservice.caradapter.a;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.ICarBodyCallback;
import com.ts.lib.caradapter.common.utilities.LogUtil;

/* JADX INFO: compiled from: CarBodyCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends g<ICarBodyCallback> {
    private static final String f = "b";

    public b(Context context) {
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
        LogUtil.optionError(f, "onBodyBinderChanged broadcast count : " + iBeginBroadcast);
        int i = 0;
        ICarBodyCallback iCarBodyCallback = null;
        while (i < iBeginBroadcast) {
            try {
                ICarBodyCallback broadcastItem = this.c.getBroadcastItem(i);
                try {
                    if (!broadcastItem.asBinder().isBinderAlive()) {
                        LogUtil.info(f, "current binder is invalid");
                    } else if (!this.f10b.get(broadcastItem.asBinder()).booleanValue()) {
                        this.f10b.put(broadcastItem.asBinder(), Boolean.TRUE);
                        broadcastItem.onCarBodyBinderChanged();
                    }
                    i++;
                    iCarBodyCallback = broadcastItem;
                } catch (RemoteException unused) {
                    iCarBodyCallback = broadcastItem;
                    i(iCarBodyCallback);
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
    private void k(int i, int i2, CarBodyPacket carBodyPacket) {
        ICarBodyCallback broadcastItem;
        int iBeginBroadcast = this.c.beginBroadcast();
        LogUtil.optionError(f, "onBodyChanged broadcast count : " + iBeginBroadcast + "  data" + carBodyPacket.toString());
        int i3 = 0;
        ICarBodyCallback iCarBodyCallback = null;
        while (i3 < iBeginBroadcast) {
            try {
                broadcastItem = this.c.getBroadcastItem(i3);
            } catch (RemoteException unused) {
            }
            try {
                if (broadcastItem.asBinder().isBinderAlive()) {
                    broadcastItem.onCarBodyStateChanged(i, i2, carBodyPacket);
                } else {
                    LogUtil.info(f, "current binder is invalid");
                }
                i3++;
                iCarBodyCallback = broadcastItem;
            } catch (RemoteException unused2) {
                iCarBodyCallback = broadcastItem;
                i(iCarBodyCallback);
                LogUtil.info(f, "remove not alive binder!!!!");
                this.c.finishBroadcast();
            }
        }
        this.c.finishBroadcast();
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected void a(Message message) {
        int i = message.what;
        if (i != 103) {
            if (i != 148) {
                return;
            }
            j();
        } else {
            Object obj = message.obj;
            if (obj instanceof CarBodyPacket) {
                k(message.arg1, message.arg2, (CarBodyPacket) obj);
            }
        }
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected String d() {
        return f;
    }

    public void l() {
        c(148);
    }

    public void m(int i, int i2, CarBodyPacket carBodyPacket) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 103;
        messageObtain.arg1 = i;
        messageObtain.arg2 = i2;
        messageObtain.obj = carBodyPacket;
        b(messageObtain);
    }
}
