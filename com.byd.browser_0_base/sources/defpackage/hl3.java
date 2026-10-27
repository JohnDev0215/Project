package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import java.util.HashMap;

/* JADX INFO: compiled from: VerifiedHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class hl3 extends Handler {
    public final Context d;
    public final zi0 e;
    public final HashMap c = new HashMap();
    public final int a = 1;
    public final String b = "";

    public hl3(Context context, zi0 zi0Var) {
        this.d = context;
        this.e = zi0Var;
    }

    @Override // android.os.Handler
    public final boolean sendMessageAtTime(Message message, long j) {
        Messenger messenger = message.replyTo;
        HashMap map = this.c;
        if (!map.containsKey(messenger)) {
            String str = this.b;
            boolean zIsEmpty = TextUtils.isEmpty(str);
            int i = this.a;
            Context context = this.d;
            zi0 zi0Var = this.e;
            map.put(messenger, Boolean.valueOf(zIsEmpty ? zi0Var.a(i, context, "") : zi0Var.a(i, context, str)));
        }
        if (((Boolean) map.get(messenger)).booleanValue()) {
            return super.sendMessageAtTime(message, j);
        }
        return false;
    }
}
