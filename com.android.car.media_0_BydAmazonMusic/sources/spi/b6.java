package spi;

import android.app.PendingIntent;
import android.car.utils.Logs;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.arch.core.util.Function;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import com.android.car.media.MediaActivity;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$id;
import com.android.car.ui.preference.MultiSelectListPreferenceFragment;
import com.android.car.ui.recyclerview.a;
import com.android.car.ui.toolbar.b;
import java.util.Objects;
import java.util.function.Consumer;
import spi.l0;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class b6 implements Function, l0.a, b.e, AsyncLayoutInflater.OnInflateFinishedListener, a.d, a.c {
    public final /* synthetic */ Object a;
    public final /* synthetic */ Object b;

    public /* synthetic */ b6(Object obj, Object obj2) {
        this.a = obj;
        this.b = obj2;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.HashSet, java.util.Set<java.lang.String>] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.util.HashSet, java.util.Set<java.lang.String>] */
    @Override // com.android.car.ui.recyclerview.a.c
    public final void a(com.android.car.ui.recyclerview.a aVar, boolean z) {
        MultiSelectListPreferenceFragment multiSelectListPreferenceFragment = (MultiSelectListPreferenceFragment) this.a;
        String str = (String) this.b;
        if (z) {
            multiSelectListPreferenceFragment.b.add(str);
        } else {
            multiSelectListPreferenceFragment.b.remove(str);
        }
        if (multiSelectListPreferenceFragment.c && multiSelectListPreferenceFragment.a.callChangeListener(multiSelectListPreferenceFragment.b)) {
            multiSelectListPreferenceFragment.a.setValues(multiSelectListPreferenceFragment.b);
        }
    }

    @Override // androidx.arch.core.util.Function
    public final Object apply(Object obj) {
        return obj == null ? this.a : ((Function) this.b).apply(obj);
    }

    @Override // com.android.car.ui.toolbar.b.e
    public final void b(com.android.car.ui.toolbar.b bVar) {
        ((Context) this.a).startActivity((Intent) this.b);
    }

    public final void c() {
        MediaActivity mediaActivity = (MediaActivity) this.a;
        PendingIntent pendingIntent = (PendingIntent) this.b;
        int i = MediaActivity.w;
        Objects.requireNonNull(mediaActivity);
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException unused) {
                Logs.e("MediaActivity", "Pending intent canceled");
                return;
            }
        }
        mediaActivity.k.dismiss();
    }

    public final void d() {
        jb jbVar = (jb) this.a;
        com.android.car.ui.toolbar.b bVar = (com.android.car.ui.toolbar.b) this.b;
        Objects.requireNonNull(jbVar);
        bVar.b();
        jbVar.Q.hide();
    }

    @Override // androidx.asynclayoutinflater.view.AsyncLayoutInflater.OnInflateFinishedListener
    public final void onInflateFinished(View view, int i, ViewGroup viewGroup) {
        f7 f7Var = (f7) this.a;
        Consumer consumer = (Consumer) this.b;
        f7Var.e = view;
        f7Var.f = b2.n(view, R$id.car_ui_toolbar_menu_item_icon_container);
        f7Var.g = (ImageView) b2.n(f7Var.e, R$id.car_ui_toolbar_menu_item_icon);
        f7Var.h = (Switch) b2.n(f7Var.e, R$id.car_ui_toolbar_menu_item_switch);
        f7Var.i = (TextView) b2.n(f7Var.e, R$id.car_ui_toolbar_menu_item_text);
        f7Var.j = (TextView) b2.n(f7Var.e, R$id.car_ui_toolbar_menu_item_text_with_icon);
        f7Var.k = f7Var.e.getContext().getResources().getBoolean(R$bool.car_ui_toolbar_menuitem_individual_click_listeners);
        f7Var.c();
        consumer.accept(f7Var.e);
    }
}
