package org.chromium.chrome.browser.language.settings;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.f51;
import defpackage.in3;
import defpackage.n3;
import defpackage.om3;
import defpackage.pd0;
import defpackage.qy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.WeakHashMap;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.language.settings.b;
import org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* JADX INFO: compiled from: LanguageListBaseAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends DragReorderableListAdapter<f51> {

    /* JADX INFO: compiled from: LanguageListBaseAdapter.java */
    public interface a {
    }

    /* JADX INFO: renamed from: org.chromium.chrome.browser.language.settings.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LanguageListBaseAdapter.java */
    public class C0092b implements pd0 {
        public boolean a;

        public C0092b() {
            AccessibilityManager accessibilityManager = (AccessibilityManager) b.this.h.getSystemService("accessibility");
            this.a = accessibilityManager.isEnabled();
            accessibilityManager.addAccessibilityStateChangeListener(new AccessibilityManager.AccessibilityStateChangeListener() { // from class: j51
                @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
                public final void onAccessibilityStateChanged(boolean z) {
                    b.C0092b c0092b = this.a;
                    c0092b.a = z;
                    b.this.o();
                }
            });
        }

        @Override // defpackage.pd0
        public final boolean a() {
            return !this.a;
        }

        @Override // defpackage.pd0
        public final boolean b() {
            return !this.a;
        }
    }

    /* JADX INFO: compiled from: LanguageListBaseAdapter.java */
    public static class c extends RecyclerView.a0 {
        public static final /* synthetic */ int C = 0;
        public final ImageView A;
        public final ListMenuButton B;
        public final TextView y;
        public final TextView z;

        public c(View view) {
            super(view);
            this.y = (TextView) view.findViewById(R$id.title);
            this.z = (TextView) view.findViewById(R$id.description);
            this.A = (ImageView) view.findViewById(R$id.icon_view);
            this.B = (ListMenuButton) view.findViewById(R$id.more);
        }

        public final void u(qy qyVar) {
            ListMenuButton listMenuButton = this.B;
            listMenuButton.setVisibility(0);
            listMenuButton.setDelegate(qyVar);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            View view = this.e;
            om3.e.i(view, om3.e.e(view), view.getPaddingTop(), 0, view.getPaddingBottom());
        }
    }

    public b(Context context) {
        super(context);
        this.n = new C0092b();
    }

    @Override // org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter
    public final boolean E(RecyclerView.a0 a0Var) {
        return a0Var instanceof c;
    }

    @Override // org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter
    public final boolean F(RecyclerView.a0 a0Var) {
        return a0Var instanceof c;
    }

    @Override // org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter
    public final void G(ArrayList arrayList) {
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = ((f51) arrayList.get(i)).a;
        }
        org.chromium.chrome.browser.language.settings.c.b().getClass();
        N.Mo7xRjdk(strArr);
        org.chromium.chrome.browser.language.settings.c.g(8);
        o();
    }

    public final void H(Collection<f51> collection) {
        this.j = new ArrayList(collection);
        o();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public void t(RecyclerView.a0 a0Var, int i) {
        c cVar = (c) a0Var;
        f51 f51Var = (f51) this.j.get(i);
        cVar.y.setText(f51Var.b);
        String str = f51Var.b;
        String str2 = f51Var.c;
        boolean zEquals = TextUtils.equals(str, str2);
        TextView textView = cVar.z;
        if (zEquals) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(str2);
        }
        ListMenuButton listMenuButton = cVar.B;
        n3.h1(listMenuButton.getContext(), listMenuButton, str, 0);
        cVar.A.setVisibility(8);
        listMenuButton.setVisibility(8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
        return new c(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.accept_languages_item, (ViewGroup) recyclerView, false));
    }
}
