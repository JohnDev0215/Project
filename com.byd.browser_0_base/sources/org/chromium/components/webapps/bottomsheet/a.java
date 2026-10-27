package org.chromium.components.webapps.bottomsheet;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.components.webapps.R$dimen;
import org.chromium.components.webapps.R$id;
import org.chromium.components.webapps.R$layout;
import org.chromium.components.webapps.bottomsheet.PwaBottomSheetController;

/* JADX INFO: compiled from: PwaInstallBottomSheetView.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a {
    public final Context a;
    public final View b;
    public final View c;

    /* JADX INFO: renamed from: org.chromium.components.webapps.bottomsheet.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PwaInstallBottomSheetView.java */
    public class C0145a extends RecyclerView.l {
        public C0145a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l
        public final void f(Rect rect, int i, RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
            RecyclerView.e adapter = recyclerView.getAdapter();
            int dimensionPixelSize = a.this.a.getResources().getDimensionPixelSize(R$dimen.webapk_screenshot_margin);
            rect.left = dimensionPixelSize;
            if (i == adapter.k() - 1) {
                rect.right = dimensionPixelSize;
            }
        }
    }

    public a(Context context, PwaBottomSheetController.c cVar) {
        this.a = context;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.pwa_install_bottom_sheet_content, (ViewGroup) null);
        this.c = viewInflate;
        this.b = LayoutInflater.from(context).inflate(R$layout.pwa_install_bottom_sheet_toolbar, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R$id.screenshots_container);
        recyclerView.g(new C0145a());
        recyclerView.setAdapter(cVar);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        int i = R$id.button_install;
        View view = this.b;
        view.findViewById(i).setOnClickListener(onClickListener);
        view.findViewById(R$id.drag_handlebar).setOnClickListener(onClickListener);
    }
}
