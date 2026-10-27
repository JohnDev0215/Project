package com.ts.appservice.caradapter.a;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import com.ts.lib.caradapter.selfstudy.ISelfStudyCallback;
import com.ts.lib.caradapter.selfstudy.SelfStudyPacket;

/* JADX INFO: compiled from: SelfStudyCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends g<ISelfStudyCallback> {
    private static final String f = "i";

    public i(Context context) {
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
        LogUtil.optionError(f, "on self study BinderChanged broadcast count : " + iBeginBroadcast);
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                ISelfStudyCallback broadcastItem = this.c.getBroadcastItem(i);
                if (!this.f10b.get(broadcastItem.asBinder()).booleanValue()) {
                    this.f10b.put(broadcastItem.asBinder(), Boolean.TRUE);
                    broadcastItem.onSelfStudyBinderChanged();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
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
    private void k(int i, int i2, SelfStudyPacket selfStudyPacket) {
        ISelfStudyCallback broadcastItem;
        int iBeginBroadcast = this.c.beginBroadcast();
        LogUtil.optionError(f, "onSelf Study Changed broadcast count : " + iBeginBroadcast + "  data:" + selfStudyPacket.toString());
        int i3 = 0;
        ISelfStudyCallback iSelfStudyCallback = null;
        while (i3 < iBeginBroadcast) {
            try {
                broadcastItem = this.c.getBroadcastItem(i3);
            } catch (RemoteException unused) {
            }
            try {
                if (broadcastItem.asBinder().isBinderAlive()) {
                    broadcastItem.onSelfStudyStateChanged(i, i2, selfStudyPacket);
                } else {
                    LogUtil.info(f, "current binder is invalid");
                }
                i3++;
                iSelfStudyCallback = broadcastItem;
            } catch (RemoteException unused2) {
                iSelfStudyCallback = broadcastItem;
                i(iSelfStudyCallback);
                LogUtil.info(f, "remove not alive binder!!!!");
                this.c.finishBroadcast();
            }
        }
        this.c.finishBroadcast();
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected void a(Message message) {
        int i = message.what;
        if (i == 156) {
            j();
        } else {
            if (i != 157) {
                return;
            }
            Object obj = message.obj;
            if (obj instanceof SelfStudyPacket) {
                k(message.arg1, message.arg2, (SelfStudyPacket) obj);
            }
        }
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected String d() {
        return f;
    }

    public void l() {
        c(156);
    }

    public void m(int i, int i2, SelfStudyPacket selfStudyPacket) {
        Message messageE = e(157);
        messageE.arg1 = i;
        messageE.arg2 = i2;
        messageE.obj = selfStudyPacket;
        b(messageE);
    }
}
