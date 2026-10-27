package F1;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class C {
    public static int a(View view) {
        return view.getImportantForAutofill();
    }

    public static void b(View view, int i7) {
        view.setImportantForAutofill(i7);
    }
}
