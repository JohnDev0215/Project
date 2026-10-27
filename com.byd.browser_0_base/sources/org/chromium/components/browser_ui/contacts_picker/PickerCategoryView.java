package org.chromium.components.browser_ui.contacts_picker;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.bi1;
import defpackage.hy;
import defpackage.i9;
import defpackage.nx0;
import defpackage.oj2;
import defpackage.qh3;
import defpackage.uc2;
import defpackage.ue;
import defpackage.xx;
import defpackage.y70;
import defpackage.ys0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.chromium.chrome.browser.contacts_picker.ChromePickerAdapter;
import org.chromium.components.browser_ui.contacts_picker.ContactsPickerToolbar;
import org.chromium.components.browser_ui.contacts_picker.TopView;
import org.chromium.components.browser_ui.contacts_picker.a;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListLayout;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar;
import org.chromium.components.browser_ui.widget.selectable_list.a;
import org.chromium.content.browser.ContactsDialogHost;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.widget.OptimizedFrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class PickerCategoryView extends OptimizedFrameLayout implements View.OnClickListener, RecyclerView.t, a.InterfaceC0128a<xx>, SelectableListToolbar.b, TopView.b, a.InterfaceC0120a {
    public static final /* synthetic */ int x = 0;
    public d f;
    public final WindowAndroid g;
    public hy h;
    public final ContactsPickerToolbar i;
    public TopView j;
    public final f k;
    public final uc2 l;
    public final org.chromium.components.browser_ui.widget.selectable_list.a<xx> m;
    public final a n;
    public final ImageView o;
    public Set<xx> p;
    public final Button q;
    public final boolean r;
    public final boolean s;
    public final boolean t;
    public final boolean u;
    public final boolean v;
    public final boolean w;

    public static class a {
        public final ue a = new ue(ys0.a, Math.min((int) ((Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / 8), 5242880));
        public final HashSet b = new HashSet();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public PickerCategoryView(WindowAndroid windowAndroid, ChromePickerAdapter chromePickerAdapter, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, String str, ContactsPickerToolbar.a aVar) {
        nx0<Context> nx0Var = windowAndroid.i;
        super(nx0Var.get(), null);
        this.g = windowAndroid;
        Context context = nx0Var.get();
        this.r = z;
        this.s = z2;
        this.t = z3;
        this.u = z4;
        this.v = z5;
        this.w = z6;
        org.chromium.components.browser_ui.widget.selectable_list.a<xx> aVar2 = new org.chromium.components.browser_ui.widget.selectable_list.a<>();
        this.m = aVar2;
        if (!z) {
            aVar2.a = true;
        }
        aVar2.a(this);
        this.l = new uc2(context.getResources(), 36, 36, 20, context.getColor(R$color.default_favicon_background_color), 12);
        SelectableListLayout selectableListLayout = (SelectableListLayout) LayoutInflater.from(context).inflate(R$layout.contacts_picker_dialog, this).findViewById(R$id.selectable_list);
        selectableListLayout.setEmptyViewText(R$string.contacts_picker_no_contacts_found);
        selectableListLayout.h.setOnTouchListener(new View.OnTouchListener() { // from class: ai2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                int i = SelectableListLayout.r;
                return true;
            }
        });
        this.k = chromePickerAdapter;
        chromePickerAdapter.h = context;
        chromePickerAdapter.i = this;
        chromePickerAdapter.l = context.getContentResolver();
        chromePickerAdapter.k = str;
        f.q = true;
        f.r = true;
        f.s = true;
        f.t = true;
        f.u = true;
        if (chromePickerAdapter.m == null) {
            PickerCategoryView pickerCategoryView = chromePickerAdapter.i;
            new c(context, chromePickerAdapter, pickerCategoryView.s, pickerCategoryView.t, pickerCategoryView.u, pickerCategoryView.v).c(i9.e);
        } else {
            chromePickerAdapter.E(null);
        }
        RecyclerView recyclerViewJ = selectableListLayout.j(chromePickerAdapter, null);
        ContactsPickerToolbar contactsPickerToolbar = (ContactsPickerToolbar) selectableListLayout.k(R$layout.contacts_picker_toolbar, aVar2, z ? R$string.contacts_picker_select_contacts : R$string.contacts_picker_select_contact, 0, 0, null, false);
        this.i = contactsPickerToolbar;
        contactsPickerToolbar.setNavigationOnClickListener(this);
        contactsPickerToolbar.z(this, R$string.contacts_picker_search, 0);
        contactsPickerToolbar.setDelegate(aVar);
        chromePickerAdapter.A(new g(this));
        qh3 qh3Var = new qh3(selectableListLayout);
        selectableListLayout.o = qh3Var;
        SelectableListToolbar<E> selectableListToolbar = selectableListLayout.l;
        selectableListToolbar.u0 = selectableListToolbar.getResources().getDimensionPixelSize(org.chromium.components.browser_ui.widget.R$dimen.toolbar_wide_display_start_offset);
        selectableListToolbar.t0 = qh3Var;
        qh3Var.a(selectableListToolbar);
        selectableListLayout.o.a(selectableListLayout);
        ImageView imageView = (ImageView) contactsPickerToolbar.findViewById(R$id.search);
        this.o = imageView;
        imageView.setOnClickListener(this);
        Button button = (Button) contactsPickerToolbar.findViewById(R$id.done);
        this.q = button;
        button.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerViewJ.setHasFixedSize(true);
        recyclerViewJ.setLayoutManager(linearLayoutManager);
        this.n = new a();
    }

    public static List l(List list, boolean z, boolean z2) {
        if (z) {
            return !z2 ? new ArrayList() : list;
        }
        return null;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        ContactsPickerToolbar contactsPickerToolbar = this.i;
        if (contactsPickerToolbar.A() && arrayList.size() > 0) {
            contactsPickerToolbar.x();
        }
        boolean z = arrayList.size() == this.k.k() - 1;
        TopView topView = this.j;
        if (topView != null) {
            topView.p = true;
            topView.g.setChecked(z);
            topView.p = false;
        }
    }

    public a getIconCache() {
        return this.n;
    }

    public uc2 getIconGenerator() {
        return this.l;
    }

    public bi1 getModalDialogManager() {
        return this.g.n();
    }

    public org.chromium.components.browser_ui.widget.selectable_list.a<xx> getSelectionDelegate() {
        return this.m;
    }

    public org.chromium.components.browser_ui.widget.selectable_list.a<xx> getSelectionDelegateForTesting() {
        return this.m;
    }

    public TopView getTopViewForTesting() {
        return this.j;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.t
    public final void h(RecyclerView.a0 a0Var) {
        b bVar = (b) a0Var;
        bVar.C.a(true);
        bVar.C = null;
    }

    public final void j(ArrayList arrayList, int i, int i2) {
        int size = arrayList != null ? arrayList.size() : 0;
        int size2 = this.k.m.size();
        int i3 = size2 > 0 ? (size * 100) / size2 : 0;
        int i4 = this.s ? 4 : 0;
        if (this.t) {
            i4 |= 2;
        }
        if (this.u) {
            i4 |= 1;
        }
        if (this.v) {
            i4 |= 8;
        }
        if (this.w) {
            i4 |= 16;
        }
        ((ContactsDialogHost) this.h).a(i, arrayList, i3, i4);
        this.f.dismiss();
        oj2.U(i2, 2, "Android.ContactsPicker.DialogAction");
        oj2.R(size2, "Android.ContactsPicker.ContactCount");
        oj2.R(size, "Android.ContactsPicker.SelectCount");
        oj2.Z(i3, "Android.ContactsPicker.SelectPercentage");
        oj2.U(i4, 32, "Android.ContactsPicker.PropertiesRequested");
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar.b
    public final void k() {
        f fVar = this.k;
        fVar.G("");
        fVar.o = false;
        fVar.o();
        ContactsPickerToolbar contactsPickerToolbar = this.i;
        contactsPickerToolbar.setNavigationOnClickListener(this);
        this.q.setVisibility(0);
        this.o.setVisibility(0);
        HashSet hashSet = new HashSet();
        Iterator<xx> it = this.m.c.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next());
        }
        contactsPickerToolbar.x();
        Iterator<xx> it2 = this.p.iterator();
        while (it2.hasNext()) {
            hashSet.add(it2.next());
        }
        getHandler().post(new y70(this, 19, hashSet));
    }

    public final void m(List<xx> list) {
        ArrayList arrayList = new ArrayList();
        for (xx xxVar : list) {
            arrayList.add(new hy.a(l(Arrays.asList(xxVar.f), this.s, f.r), l(xxVar.g, this.t, f.s), l(xxVar.h, this.u, f.t), l(xxVar.i, this.v, f.q), l(xxVar.j, this.w, f.u)));
        }
        j(arrayList, 1, 1);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int id = view.getId();
        int i = R$id.done;
        org.chromium.components.browser_ui.widget.selectable_list.a<xx> aVar = this.m;
        if (id == i) {
            ArrayList arrayListC = aVar.c();
            Collections.sort(arrayListC);
            if (this.w && f.u) {
                new org.chromium.components.browser_ui.contacts_picker.a(this.g.i.get().getContentResolver(), this.n, arrayListC, this).c(i9.e);
                return;
            } else {
                m(arrayListC);
                return;
            }
        }
        if (id != R$id.search) {
            j(null, 0, 0);
            return;
        }
        this.q.setVisibility(8);
        this.p = new HashSet(aVar.c);
        this.o.setVisibility(8);
        f fVar = this.k;
        fVar.o = true;
        fVar.o();
        this.i.D(true);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar.b
    public final void r(String str) {
        this.k.G(str);
    }

    public void setTopView(TopView topView) {
        this.j = topView;
    }
}
