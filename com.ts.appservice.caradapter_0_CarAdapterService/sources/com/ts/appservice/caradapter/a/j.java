package com.ts.appservice.caradapter.a;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import com.ts.lib.caradapter.setting.ISettingAdapterCallback;
import com.ts.lib.caradapter.setting.SettingPacket;

/* JADX INFO: compiled from: SettingCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends g<ISettingAdapterCallback> {
    public j(Context context) {
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
        LogUtil.optionError("SettingCallback", "onSettingBinderChanged broadcast count : " + iBeginBroadcast);
        int i = 0;
        ISettingAdapterCallback iSettingAdapterCallback = null;
        while (i < iBeginBroadcast) {
            try {
                ISettingAdapterCallback broadcastItem = this.c.getBroadcastItem(i);
                try {
                    if (!broadcastItem.asBinder().isBinderAlive()) {
                        LogUtil.info("SettingCallback", "current binder is invalid");
                    } else if (!this.f10b.get(broadcastItem.asBinder()).booleanValue()) {
                        this.f10b.put(broadcastItem.asBinder(), Boolean.TRUE);
                        broadcastItem.onSettingBinderChanged();
                    }
                    i++;
                    iSettingAdapterCallback = broadcastItem;
                } catch (RemoteException unused) {
                    iSettingAdapterCallback = broadcastItem;
                    i(iSettingAdapterCallback);
                    LogUtil.info("SettingCallback", "remove not alive binder!!!!");
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
    private void k(int i, int i2, SettingPacket settingPacket) {
        int iBeginBroadcast = this.c.beginBroadcast();
        LogUtil.optionError("SettingCallback", "onSettingChanged broadcast count : " + iBeginBroadcast + "  data:" + settingPacket.toString());
        int i3 = 0;
        ISettingAdapterCallback iSettingAdapterCallback = null;
        while (i3 < iBeginBroadcast) {
            try {
                ISettingAdapterCallback broadcastItem = this.c.getBroadcastItem(i3);
                try {
                    if (broadcastItem.asBinder().isBinderAlive()) {
                        broadcastItem.onSettingStateChanged(i, i2, settingPacket);
                    } else {
                        LogUtil.info("SettingCallback", "current binder is invalid");
                    }
                    i3++;
                    iSettingAdapterCallback = broadcastItem;
                } catch (RemoteException unused) {
                    iSettingAdapterCallback = broadcastItem;
                    i(iSettingAdapterCallback);
                    LogUtil.info("SettingCallback", "remove not alive binder!!!!");
                    this.c.finishBroadcast();
                }
            } catch (RemoteException unused2) {
            }
        }
        this.c.finishBroadcast();
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected void a(Message message) {
        int i = message.what;
        if (i == 155) {
            k(message.arg1, message.arg2, (SettingPacket) message.obj);
        } else {
            if (i != 156) {
                return;
            }
            j();
        }
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected String d() {
        return "SettingCallback";
    }

    public void l() {
        c(156);
    }

    public void m(int i, int i2, SettingPacket settingPacket) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 155;
        messageObtain.arg1 = i;
        messageObtain.arg2 = i2;
        messageObtain.obj = settingPacket;
        b(messageObtain);
    }
}
