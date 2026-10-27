package defpackage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.f;

/* JADX INFO: compiled from: TargetSelectorCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t63 {

    @SuppressLint({"StaticFieldLeak"})
    public static t63 j;
    public final Context a;
    public final Callback<b11> b;
    public final ya1 c;
    public final th3 d;
    public final View e;
    public final bi1 f;
    public PropertyModel g;
    public b11 h;
    public int i;

    public t63(Activity activity, bi1 bi1Var, LargeIconBridge largeIconBridge, gp gpVar) {
        ya1 ya1Var = new ya1();
        this.c = ya1Var;
        this.a = activity;
        this.f = bi1Var;
        this.b = gpVar;
        this.d = new th3(activity, largeIconBridge);
        f fVar = new f(ya1Var);
        fVar.a(0, new uu0(2, this), new bg(13));
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.target_selector_dialog, (ViewGroup) null);
        this.e = viewInflate;
        ((ListView) viewInflate.findViewById(R$id.list_view)).setAdapter((ListAdapter) fVar);
    }
}
