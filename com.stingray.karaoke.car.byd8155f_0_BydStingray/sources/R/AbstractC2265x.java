package r;

import F1.C0245d;
import F1.InterfaceC0244c;
import android.app.Activity;
import android.content.ClipData;
import android.os.Build;
import android.text.Selection;
import android.text.Spannable;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: renamed from: r.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2265x {
    public static boolean a(DragEvent dragEvent, TextView textView, Activity activity) {
        InterfaceC0244c o;
        activity.requestDragAndDropPermissions(dragEvent);
        int offsetForPosition = textView.getOffsetForPosition(dragEvent.getX(), dragEvent.getY());
        textView.beginBatchEdit();
        try {
            Selection.setSelection((Spannable) textView.getText(), offsetForPosition);
            ClipData clipData = dragEvent.getClipData();
            if (Build.VERSION.SDK_INT >= 31) {
                o = new B2.O(clipData, 3);
            } else {
                C0245d c0245d = new C0245d();
                c0245d.f2326m = clipData;
                c0245d.f2327n = 3;
                o = c0245d;
            }
            F1.H.d(textView, o.a());
            textView.endBatchEdit();
            return true;
        } catch (Throwable th) {
            textView.endBatchEdit();
            throw th;
        }
    }

    public static boolean b(DragEvent dragEvent, View view, Activity activity) {
        InterfaceC0244c o;
        activity.requestDragAndDropPermissions(dragEvent);
        ClipData clipData = dragEvent.getClipData();
        if (Build.VERSION.SDK_INT >= 31) {
            o = new B2.O(clipData, 3);
        } else {
            C0245d c0245d = new C0245d();
            c0245d.f2326m = clipData;
            c0245d.f2327n = 3;
            o = c0245d;
        }
        F1.H.d(view, o.a());
        return true;
    }
}
