package p;

import android.content.DialogInterface;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class rh1 extends Handler {
    public final /* synthetic */ int a;
    public Object b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ rh1(Object obj, Looper looper, int i) {
        super(looper);
        this.a = i;
        this.b = obj;
    }

    public final void a(Runnable runnable) {
        if (Thread.currentThread() == getLooper().getThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        uh1 uh1Var;
        Object obj;
        rh1 rh1Var;
        int i = 1;
        switch (this.a) {
            case 0:
                if (message.what == 1) {
                    synchronized (((th1) this.b).a) {
                        uh1Var = (uh1) ((th1) this.b).d.get();
                        obj = this.b;
                        rh1Var = ((th1) obj).e;
                        break;
                    }
                    if (uh1Var == null || ((th1) obj) != uh1Var.c() || rh1Var == null) {
                        return;
                    }
                    uh1Var.b((gi1) message.obj);
                    ((th1) this.b).a(uh1Var, rh1Var);
                    uh1Var.b(null);
                    return;
                }
                return;
            case 1:
                ((zh1) this.b).getClass();
                return;
            case 2:
                int i2 = message.what;
                if (i2 == -3 || i2 == -2 || i2 == -1) {
                    ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) ((WeakReference) this.b).get(), message.what);
                    return;
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    ((DialogInterface) message.obj).dismiss();
                    return;
                }
            default:
                bh1 bh1Var = (bh1) this.b;
                if (bh1Var == null) {
                    removeCallbacksAndMessages(null);
                    return;
                }
                Bundle data = message.getData();
                int i3 = message.what;
                wg1 wg1Var = bh1Var.e;
                int i4 = 0;
                switch (i3) {
                    case 1:
                        Bundle bundle = data.getBundle("data_root_hints");
                        bi1.a(bundle);
                        String string = data.getString("data_package_name");
                        int i5 = data.getInt("data_calling_pid");
                        int i6 = data.getInt("data_calling_uid");
                        wg1 wg1Var2 = new wg1(message.replyTo);
                        bh1 bh1Var2 = (bh1) wg1Var.a;
                        if (string != null) {
                            String[] packagesForUid = bh1Var2.getPackageManager().getPackagesForUid(i6);
                            int length = packagesForUid.length;
                            while (i4 < length) {
                                if (packagesForUid[i4].equals(string)) {
                                    bh1Var2.n.a(new xg1(wg1Var, wg1Var2, string, i5, i6, bundle));
                                    return;
                                }
                                i4++;
                            }
                        } else {
                            bh1Var2.getClass();
                        }
                        throw new IllegalArgumentException("Package/uid mismatch: uid=" + i6 + " package=" + string);
                    case 2:
                        ((bh1) wg1Var.a).n.a(new yg1(wg1Var, new wg1(message.replyTo), i4));
                        return;
                    case 3:
                        Bundle bundle2 = data.getBundle("data_options");
                        bi1.a(bundle2);
                        ((bh1) wg1Var.a).n.a(new ah1(wg1Var, new wg1(message.replyTo), data.getString("data_media_item_id"), uo.a(data, "data_callback_token"), bundle2));
                        return;
                    case 4:
                        ((bh1) wg1Var.a).n.a(new zg1(wg1Var, new wg1(message.replyTo), data.getString("data_media_item_id"), uo.a(data, "data_callback_token"), 0));
                        return;
                    case 5:
                        String string2 = data.getString("data_media_item_id");
                        b52 b52Var = (b52) data.getParcelable("data_result_receiver");
                        wg1 wg1Var3 = new wg1(message.replyTo);
                        wg1Var.getClass();
                        if (TextUtils.isEmpty(string2) || b52Var == null) {
                            return;
                        }
                        ((bh1) wg1Var.a).n.a(new zg1(wg1Var, wg1Var3, string2, b52Var, 1));
                        return;
                    case 6:
                        Bundle bundle3 = data.getBundle("data_root_hints");
                        bi1.a(bundle3);
                        ((bh1) wg1Var.a).n.a(new xg1(wg1Var, new wg1(message.replyTo), data.getInt("data_calling_uid"), data.getString("data_package_name"), data.getInt("data_calling_pid"), bundle3));
                        return;
                    case 7:
                        ((bh1) wg1Var.a).n.a(new yg1(wg1Var, new wg1(message.replyTo), i));
                        return;
                    case 8:
                        Bundle bundle4 = data.getBundle("data_search_extras");
                        bi1.a(bundle4);
                        String string3 = data.getString("data_search_query");
                        b52 b52Var2 = (b52) data.getParcelable("data_result_receiver");
                        wg1 wg1Var4 = new wg1(message.replyTo);
                        wg1Var.getClass();
                        if (TextUtils.isEmpty(string3) || b52Var2 == null) {
                            return;
                        }
                        ((bh1) wg1Var.a).n.a(new ah1(wg1Var, wg1Var4, string3, bundle4, b52Var2, 0));
                        return;
                    case 9:
                        Bundle bundle5 = data.getBundle("data_custom_action_extras");
                        bi1.a(bundle5);
                        String string4 = data.getString("data_custom_action");
                        b52 b52Var3 = (b52) data.getParcelable("data_result_receiver");
                        wg1 wg1Var5 = new wg1(message.replyTo);
                        wg1Var.getClass();
                        if (TextUtils.isEmpty(string4) || b52Var3 == null) {
                            return;
                        }
                        ((bh1) wg1Var.a).n.a(new ah1(wg1Var, wg1Var5, string4, bundle5, b52Var3, 1));
                        return;
                    default:
                        message.toString();
                        return;
                }
        }
    }

    @Override // android.os.Handler
    public final boolean sendMessageAtTime(Message message, long j) {
        switch (this.a) {
            case 3:
                Bundle data = message.getData();
                data.setClassLoader(cg1.class.getClassLoader());
                data.putInt("data_calling_uid", Binder.getCallingUid());
                int callingPid = Binder.getCallingPid();
                if (callingPid > 0) {
                    data.putInt("data_calling_pid", callingPid);
                } else if (!data.containsKey("data_calling_pid")) {
                    data.putInt("data_calling_pid", -1);
                }
                break;
        }
        return super.sendMessageAtTime(message, j);
    }

    public rh1(DialogInterface dialogInterface) {
        this.a = 2;
        this.b = new WeakReference(dialogInterface);
    }

    public rh1(bh1 bh1Var) {
        this.a = 3;
        this.b = bh1Var;
    }
}
