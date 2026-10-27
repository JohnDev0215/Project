package org.chromium.chrome.browser.accessibility_tab_switcher;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import defpackage.w03;
import defpackage.z7;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.accessibility_tab_switcher.AccessibilityTabModelListItem;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: AccessibilityTabModelAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends BaseAdapter {
    public final Context e;
    public w03 f;
    public TabModel g;
    public c h;
    public final AccessibilityTabModelListView i;
    public AccessibilityTabModelListItem k;
    public int j = -1;
    public final ViewOnAttachStateChangeListenerC0071a l = new ViewOnAttachStateChangeListenerC0071a();
    public final b m = new b();

    /* JADX INFO: compiled from: AccessibilityTabModelAdapter.java */
    public class b implements AccessibilityTabModelListItem.e {
        public b() {
        }
    }

    /* JADX INFO: compiled from: AccessibilityTabModelAdapter.java */
    public interface c {
    }

    public a(Context context, AccessibilityTabModelListView accessibilityTabModelListView) {
        this.e = context;
        this.i = accessibilityTabModelListView;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        w03 w03Var = this.f;
        if (w03Var != null) {
            return w03Var.getCount();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        return new Object();
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        w03 w03Var = this.f;
        if (w03Var == null || w03Var.getTabAt(i) == null) {
            return -1L;
        }
        return this.f.getTabAt(i).getId();
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        int itemId = (int) getItemId(i);
        AccessibilityTabModelListItem accessibilityTabModelListItem = view instanceof AccessibilityTabModelListItem ? (AccessibilityTabModelListItem) view : (AccessibilityTabModelListItem) LayoutInflater.from(this.e).inflate(R$layout.accessibility_tab_switcher_list_item, (ViewGroup) null, false);
        Tab tabF = z7.F(this.f, itemId);
        boolean zU = this.g.u();
        boolean z = z7.p(this.f).getId() == itemId;
        Tab tab = accessibilityTabModelListItem.y;
        AccessibilityTabModelListItem.d dVar = accessibilityTabModelListItem.K;
        if (tab != null) {
            tab.t(dVar);
        }
        accessibilityTabModelListItem.y = tabF;
        tabF.x(dVar);
        accessibilityTabModelListItem.z = zU;
        accessibilityTabModelListItem.A = z;
        accessibilityTabModelListItem.g();
        accessibilityTabModelListItem.f();
        accessibilityTabModelListItem.B = this.m;
        accessibilityTabModelListItem.E = this.i;
        accessibilityTabModelListItem.setTranslationX(0.0f);
        accessibilityTabModelListItem.setAlpha(1.0f);
        accessibilityTabModelListItem.setScaleX(1.0f);
        accessibilityTabModelListItem.setScaleY(1.0f);
        accessibilityTabModelListItem.setHeight(accessibilityTabModelListItem.D);
        accessibilityTabModelListItem.a();
        Handler handler = accessibilityTabModelListItem.H;
        AccessibilityTabModelListItem.a aVar = accessibilityTabModelListItem.G;
        handler.removeCallbacks(aVar);
        AccessibilityTabModelListItem.e eVar = accessibilityTabModelListItem.B;
        if (eVar != null) {
            boolean zW = a.this.g.w(accessibilityTabModelListItem.y.getId());
            accessibilityTabModelListItem.e(zW);
            if (zW) {
                handler.postDelayed(aVar, accessibilityTabModelListItem.g);
            }
        } else {
            accessibilityTabModelListItem.e(false);
        }
        if (itemId == this.j) {
            if (accessibilityTabModelListItem.isAttachedToWindow()) {
                accessibilityTabModelListItem.requestFocus();
                this.j = -1;
            } else {
                this.k = accessibilityTabModelListItem;
                accessibilityTabModelListItem.addOnAttachStateChangeListener(this.l);
            }
        }
        return accessibilityTabModelListItem;
    }

    /* JADX INFO: renamed from: org.chromium.chrome.browser.accessibility_tab_switcher.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccessibilityTabModelAdapter.java */
    public class ViewOnAttachStateChangeListenerC0071a implements View.OnAttachStateChangeListener {
        public ViewOnAttachStateChangeListenerC0071a() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            view.removeOnAttachStateChangeListener(this);
            a aVar = a.this;
            if (view != aVar.k) {
                return;
            }
            view.requestFocus();
            aVar.j = -1;
            aVar.k = null;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
        }
    }
}
