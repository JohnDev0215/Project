package com.ts.appservice.caradapter.a;

import android.content.Context;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import com.ts.lib.caradapter.cloud.CarCloudPacket;
import com.ts.lib.caradapter.cloud.CarCollectDataPacket;
import com.ts.lib.caradapter.cloud.CarRemotePacket;
import com.ts.lib.caradapter.cloud.ICarCloudCallback;
import com.ts.lib.caradapter.common.utilities.LogUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: CarCloudCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends g<ICarCloudCallback> {
    private static final String g = "d";
    private Map<IBinder, List<CarCloudPacket>> f;

    public d(Context context) {
        super(context);
        this.f = new HashMap();
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
        LogUtil.optionError(g, "onCloudChangedBinderChanged broadcast count : " + iBeginBroadcast);
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                ICarCloudCallback broadcastItem = this.c.getBroadcastItem(i);
                if (!this.f10b.get(broadcastItem.asBinder()).booleanValue()) {
                    this.f10b.put(broadcastItem.asBinder(), Boolean.TRUE);
                    broadcastItem.onCarCloudBinderChanged();
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
    private void k(int i, int i2, CarCloudPacket carCloudPacket) {
        CarCloudPacket carCloudPacketClone;
        int iBeginBroadcast = this.c.beginBroadcast();
        LogUtil.optionError(g, "callbackOnCloudChanged broadcast count : " + iBeginBroadcast + " data " + carCloudPacket.toString());
        try {
            carCloudPacketClone = carCloudPacket.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            carCloudPacketClone = null;
        }
        carCloudPacket.recycle();
        for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
            try {
                ICarCloudCallback broadcastItem = this.c.getBroadcastItem(i3);
                List<CarCloudPacket> list = this.f.get(broadcastItem.asBinder());
                LogUtil.optionError(g, broadcastItem.asBinder() + " ICarCloudCallback is " + list);
                if (list != null && list.size() > 0) {
                    LogUtil.optionError(g, "ICarCloudCallback callback cloud data");
                    byte b2 = carCloudPacketClone.getData()[0];
                    for (CarCloudPacket carCloudPacket2 : list) {
                        if (Arrays.equals(carCloudPacketClone.getCanId(), carCloudPacket2.getCanId())) {
                            if (carCloudPacket2.getChildId() == 0) {
                                broadcastItem.onCarCloudChanged(i, 0, carCloudPacketClone);
                            } else if (carCloudPacket2.getChildId() == b2) {
                                broadcastItem.onCarCloudChanged(i, b2, carCloudPacketClone);
                            } else {
                                LogUtil.debug(g, "cloud message execute error!");
                            }
                        }
                    }
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
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
    private void l(int i, int i2, CarRemotePacket carRemotePacket) {
        int iBeginBroadcast = this.c.beginBroadcast();
        LogUtil.optionError(g, "callbackOnCloudChanged broadcast count : " + iBeginBroadcast + " data " + carRemotePacket.toString());
        for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
            try {
                this.c.getBroadcastItem(i3).onCarRemoteChanged(i, i2, carRemotePacket);
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
    private void m(int i, int i2, CarCollectDataPacket carCollectDataPacket) {
        int iBeginBroadcast = this.c.beginBroadcast();
        LogUtil.optionError(g, "callbackOnCollectDataChanged broadcast count : " + iBeginBroadcast + " data " + carCollectDataPacket.toString());
        for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
            try {
                this.c.getBroadcastItem(i3).onCarCollectDataChanged(i, i2, carCollectDataPacket);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        this.c.finishBroadcast();
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected void a(Message message) {
        int i = message.what;
        if (i == 149) {
            j();
        }
        switch (i) {
            case 158:
                Object obj = message.obj;
                if (obj instanceof CarCloudPacket) {
                    k(message.arg1, message.arg2, (CarCloudPacket) obj);
                }
                break;
            case 159:
                Object obj2 = message.obj;
                if (obj2 instanceof CarRemotePacket) {
                    l(message.arg1, message.arg2, (CarRemotePacket) obj2);
                }
                break;
            case 160:
                Object obj3 = message.obj;
                if (obj3 instanceof CarCollectDataPacket) {
                    m(message.arg1, message.arg2, (CarCollectDataPacket) obj3);
                }
                break;
        }
    }

    @Override // com.ts.appservice.caradapter.a.g
    protected String d() {
        return g;
    }

    public void n(ICarCloudCallback iCarCloudCallback, List<CarCloudPacket> list) {
        LogUtil.info(g, iCarCloudCallback.asBinder() + " subscribe list is " + list.toString());
        this.f.put(iCarCloudCallback.asBinder(), list);
    }

    public void o(int i, int i2, CarCollectDataPacket carCollectDataPacket) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 160;
        messageObtain.arg1 = i;
        messageObtain.arg2 = i2;
        messageObtain.obj = carCollectDataPacket;
        b(messageObtain);
    }

    public void p() {
        c(149);
    }

    public void q(int i, int i2, CarCloudPacket carCloudPacket) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 158;
        messageObtain.arg1 = i;
        messageObtain.arg2 = i2;
        messageObtain.obj = carCloudPacket;
        b(messageObtain);
    }

    public void r(int i, int i2, CarRemotePacket carRemotePacket) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 159;
        messageObtain.arg1 = i;
        messageObtain.arg2 = i2;
        messageObtain.obj = carRemotePacket;
        b(messageObtain);
    }

    public void s(ICarCloudCallback iCarCloudCallback, List<CarCloudPacket> list) {
        this.f.remove(iCarCloudCallback.asBinder());
    }
}
