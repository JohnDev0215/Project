package defpackage;

import J.N;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import java.util.List;
import org.chromium.chrome.browser.notifications.NotificationPlatformBridge;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: NotificationSuspender.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hp1 {
    public final Profile a;
    public final Context b;
    public final NotificationManager c;

    public hp1(Profile profile) {
        this.a = profile;
        Context context = xz.a;
        this.b = context;
        this.c = (NotificationManager) context.getSystemService("notification");
    }

    public final void a(List<lp1> list) {
        if (list.isEmpty()) {
            return;
        }
        String[] strArr = new String[list.size()];
        String[] strArr2 = new String[list.size()];
        Bitmap[] bitmapArr = new Bitmap[list.size() * 3];
        for (int i = 0; i < list.size(); i++) {
            Notification notification = list.get(i).a;
            String str = (String) list.get(i).b.c;
            strArr[i] = str;
            strArr2[i] = NotificationPlatformBridge.c(str);
            int i2 = i * 3;
            int i3 = i2 + 0;
            Icon largeIcon = notification.getLargeIcon();
            Context context = this.b;
            Bitmap bitmap = null;
            bitmapArr[i3] = (largeIcon == null || largeIcon.getType() != 1) ? null : ((BitmapDrawable) largeIcon.loadDrawable(context)).getBitmap();
            int i4 = i2 + 1;
            Icon smallIcon = notification.getSmallIcon();
            if (smallIcon != null && smallIcon.getType() == 1) {
                bitmap = ((BitmapDrawable) smallIcon.loadDrawable(context)).getBitmap();
            }
            bitmapArr[i4] = bitmap;
            bitmapArr[i2 + 2] = (Bitmap) notification.extras.get("android.picture");
            this.c.cancel(str, -1);
        }
        N.Mj9WitTn(this.a, strArr, strArr2, bitmapArr);
    }
}
