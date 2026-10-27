package defpackage;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: ConsentTextTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lx {
    public final Resources a;
    public final HashMap b = new HashMap();

    /* JADX INFO: compiled from: ConsentTextTracker.java */
    public static class a {
        public final int a;

        public a(String str, int i) {
            this.a = i;
        }
    }

    public lx(Resources resources) {
        this.a = resources;
    }

    public static void a(View view, ArrayList arrayList) {
        if (view.getVisibility() != 0) {
            return;
        }
        arrayList.add(view);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                a(viewGroup.getChildAt(i), arrayList);
            }
        }
    }

    public final void b(TextView textView, int i) {
        CharSequence text = this.a.getText(i);
        textView.setText(text);
        this.b.put(textView, new a(text.toString(), i));
    }
}
