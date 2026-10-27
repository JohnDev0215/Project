package androidx.appcompat.widget;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.inputmethod.InputContentInfo;
import android.widget.TextView;
import i0.c;
import k0.b;

/* JADX INFO: compiled from: AppCompatReceiveContentHelper.java */
/* JADX INFO: loaded from: classes.dex */
final class s {

    /* JADX INFO: compiled from: AppCompatReceiveContentHelper.java */
    class a implements b.InterfaceC0096b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f1182a;

        a(View view) {
            this.f1182a = view;
        }

        @Override // k0.b.InterfaceC0096b
        public boolean a(k0.c cVar, int i7, Bundle bundle) {
            if ((i7 & 1) != 0) {
                try {
                    cVar.d();
                    InputContentInfo inputContentInfo = (InputContentInfo) cVar.e();
                    bundle = bundle == null ? new Bundle() : new Bundle(bundle);
                    bundle.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", inputContentInfo);
                } catch (Exception e7) {
                    Log.w("ReceiveContent", "Can't insert content from IME; requestPermission() failed", e7);
                    return false;
                }
            }
            return i0.u.a0(this.f1182a, new c.a(new ClipData(cVar.b(), new ClipData.Item(cVar.a())), 2).d(cVar.c()).b(bundle).a()) == null;
        }
    }

    /* JADX INFO: compiled from: AppCompatReceiveContentHelper.java */
    private static final class b {
        static boolean a(DragEvent dragEvent, TextView textView, Activity activity) {
            activity.requestDragAndDropPermissions(dragEvent);
            int offsetForPosition = textView.getOffsetForPosition(dragEvent.getX(), dragEvent.getY());
            textView.beginBatchEdit();
            try {
                Selection.setSelection((Spannable) textView.getText(), offsetForPosition);
                i0.u.a0(textView, new c.a(dragEvent.getClipData(), 3).a());
                textView.endBatchEdit();
                return true;
            } catch (Throwable th) {
                textView.endBatchEdit();
                throw th;
            }
        }

        static boolean b(DragEvent dragEvent, View view, Activity activity) {
            activity.requestDragAndDropPermissions(dragEvent);
            i0.u.a0(view, new c.a(dragEvent.getClipData(), 3).a());
            return true;
        }
    }

    static b.InterfaceC0096b a(View view) {
        return new a(view);
    }

    static boolean b(View view, DragEvent dragEvent) {
        if (dragEvent.getLocalState() == null && i0.u.B(view) != null) {
            Activity activityD = d(view);
            if (activityD == null) {
                Log.i("ReceiveContent", "Can't handle drop: no activity: view=" + view);
                return false;
            }
            if (dragEvent.getAction() == 1) {
                return !(view instanceof TextView);
            }
            if (dragEvent.getAction() == 3) {
                return view instanceof TextView ? b.a(dragEvent, (TextView) view, activityD) : b.b(dragEvent, view, activityD);
            }
        }
        return false;
    }

    static boolean c(TextView textView, int i7) {
        if ((i7 != 16908322 && i7 != 16908337) || i0.u.B(textView) == null) {
            return false;
        }
        ClipboardManager clipboardManager = (ClipboardManager) textView.getContext().getSystemService("clipboard");
        ClipData primaryClip = clipboardManager == null ? null : clipboardManager.getPrimaryClip();
        if (primaryClip != null && primaryClip.getItemCount() > 0) {
            i0.u.a0(textView, new c.a(primaryClip, 1).c(i7 != 16908322 ? 1 : 0).a());
        }
        return true;
    }

    static Activity d(View view) {
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }
}
