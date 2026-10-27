package com.ts.appservice.caradapter.a;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import com.ts.lib.caradapter.hvac.HvacPacket;
import com.ts.lib.caradapter.hvac.IHvacAdapterCallback;

/* JADX INFO: compiled from: HvacCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends g<IHvacAdapterCallback> {
    private static final String f = "h";

    public h(Context context) {
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
        LogUtil.optionError(f, "onHvacBinderChanged broadcast count : " + iBeginBroadcast);
        int i = 0;
        IHvacAdapterCallback iHvacAdapterCallback = null;
        while (i < iBeginBroadcast) {
            try {
                IHvacAdapterCallback broadcastItem = this.c.getBroadcastItem(i);
                try {
                    if (!broadcastItem.asBinder().isBinderAlive()) {
                        LogUtil.info(f, "current binder is invalid");
                    } else if (!this.f10b.get(broadcastItem.asBinder()).booleanValue()) {
                        this.f10b.put(broadcastItem.asBinder(), Boolean.TRUE);
                        broadcastItem.onHvacBinderChanged();
                    }
                    i++;
                    iHvacAdapterCallback = broadcastItem;
                } catch (RemoteException unused) {
                    iHvacAdapterCallback = broadcastItem;
                    i(iHvacAdapterCallback);
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
    private void k(int i, int i2, HvacPacket hvacPacket) {
        IHvacAdapterCallback broadcastItem;
        int iBeginBroadcast = this.c.beginBroadcast();
        LogUtil.optionError(f, "onHvacChanged broadcast count : " + iBeginBroadcast + "  data:" + hvacPacket.toString());
        int i3 = 0;
        IHvacAdapterCallback iHvacAdapterCallback = null;
        while (i3 < iBeginBroadcast) {
            try {
                broadcastItem = this.c.getBroadcastItem(i3);
            } catch (RemoteException unused) {
            }
            try {
                if (broadcastItem.asBinder().isBinderAlive()) {
                    broadcastItem.onHvacStateChanged(i, i2, hvacPacket);
                } else {
                    LogUtil.info(f, "current binder is invalid");
                }
                i3++;
                iHvacAdapterCallback = broadcastItem;
            } catch (RemoteException unused2) {
                iHvacAdapterCallback = broadcastItem;
                i(iHvacAdapterCallback);
                LogUtil.info(f, "remove not alive binder!!!!");
                this.c.finishBroadcast();
            }
        }
        this.c.finishBroadcast();
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected void a(Message message) {
        int i = message.what;
        if (i != 101) {
            if (i != 144) {
                return;
            }
            j();
        } else {
            Object obj = message.obj;
            if (obj instanceof HvacPacket) {
                k(message.arg1, message.arg2, (HvacPacket) obj);
            }
        }
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected String d() {
        return f;
    }

    public void l() {
        c(144);
    }

    public void m(int i, int i2, HvacPacket hvacPacket) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 101;
        messageObtain.obj = hvacPacket;
        messageObtain.arg1 = i;
        messageObtain.arg2 = i2;
        b(messageObtain);
    }
}
