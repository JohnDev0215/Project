package defpackage;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: renamed from: Ne, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class HandlerC0237Ne extends HandlerC0347Tg {
    public final /* synthetic */ BaseGmsClient a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC0237Ne(BaseGmsClient baseGmsClient, Looper looper) {
        super(looper);
        this.a = baseGmsClient;
    }

    public static boolean a(Message message) {
        int i = message.what;
        return i == 2 || i == 1 || i == 7;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
    @Override // android.os.Handler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void handleMessage(android.os.Message r8) {
        /*
            Method dump skipped, instruction units count: 370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.HandlerC0237Ne.handleMessage(android.os.Message):void");
    }
}
