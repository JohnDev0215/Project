package org.chromium.components.browser_ui.share;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import defpackage.oj2;
import defpackage.q11;
import defpackage.xz;
import java.util.ArrayList;
import org.chromium.base.ThreadUtils;
import org.chromium.components.browser_ui.share.a;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class ShareHelper {

    public static class TargetChosenReceiver extends BroadcastReceiver implements WindowAndroid.c {
        public static final Object b = new Object();
        public static String c;
        public static TargetChosenReceiver d;
        public a.b a;

        public TargetChosenReceiver(a.b bVar) {
            this.a = bVar;
        }

        @Override // org.chromium.ui.base.WindowAndroid.c
        public final void b(Intent intent, int i) {
            a.b bVar;
            if (i != 0 || (bVar = this.a) == null) {
                return;
            }
            bVar.onCancel();
            this.a = null;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            Object obj = ThreadUtils.a;
            if (q11.g(intent)) {
                synchronized (b) {
                    TargetChosenReceiver targetChosenReceiver = d;
                    if (targetChosenReceiver != this) {
                        return;
                    }
                    xz.a.unregisterReceiver(targetChosenReceiver);
                    d = null;
                    ComponentName componentName = (ComponentName) intent.getParcelableExtra("android.intent.extra.CHOSEN_COMPONENT");
                    a.b bVar = this.a;
                    if (bVar != null) {
                        bVar.a(componentName);
                        this.a = null;
                    }
                }
            }
        }
    }

    public static void a(WindowAndroid windowAndroid, Intent intent, TargetChosenReceiver targetChosenReceiver) {
        if (targetChosenReceiver != null) {
            windowAndroid.u(intent, targetChosenReceiver, null);
        } else {
            ((Activity) windowAndroid.k().get()).startActivity(intent);
        }
    }

    public static Intent b(a aVar) {
        ArrayList<Uri> arrayList = aVar.g;
        boolean z = arrayList != null;
        boolean z2 = z && arrayList.size() > 1;
        Intent intent = new Intent(z2 ? "android.intent.action.SEND_MULTIPLE" : "android.intent.action.SEND");
        intent.addFlags(319291392);
        intent.putExtra("org.chromium.chrome.extra.TASK_ID", ((Activity) aVar.a.k().get()).getTaskId());
        Uri uri = aVar.j;
        if (uri != null) {
            intent.addFlags(1);
            intent.setClipData(ClipData.newRawUri("", uri));
            intent.putExtra("share_screenshot_as_stream", uri);
        }
        Uri uri2 = aVar.i;
        String str = aVar.b;
        if (uri2 != null) {
            intent.putExtra("android.intent.extra.SUBJECT", str);
            intent.addFlags(134217728);
            intent.addFlags(1);
            intent.putExtra("android.intent.extra.STREAM", uri2);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setType("multipart/related");
        } else {
            if (!TextUtils.equals(aVar.b(), str)) {
                intent.putExtra("android.intent.extra.SUBJECT", str);
            }
            intent.putExtra("android.intent.extra.TEXT", aVar.b());
            if (z) {
                intent.setType(aVar.f);
                intent.addFlags(1);
                ArrayList<String> arrayList2 = aVar.h;
                boolean z3 = (arrayList2 == null || arrayList2.isEmpty()) ? false : true;
                ArrayList<Uri> arrayList3 = aVar.g;
                if (z2) {
                    intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList3);
                    if (z3) {
                        intent.putStringArrayListExtra("android.intent.extra.STREAM_ALT_TEXT", arrayList2);
                    }
                } else {
                    intent.putExtra("android.intent.extra.STREAM", arrayList3.get(0));
                    if (z3) {
                        intent.putExtra("android.intent.extra.STREAM_ALT_TEXT", arrayList2.get(0));
                    }
                }
            } else {
                intent.setType("text/plain");
            }
        }
        return intent;
    }

    public static void c(int i) {
        oj2.U(i, 3, "Sharing.AnyShareStarted");
    }
}
