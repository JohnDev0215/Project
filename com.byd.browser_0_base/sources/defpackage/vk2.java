package defpackage;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.share.a;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.m;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: ShareSheetBottomSheetContent.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vk2 implements ii, AdapterView.OnItemClickListener {
    public final Activity e;
    public final LargeIconBridge f;
    public final xk2 g;
    public final xe3 h;
    public ViewGroup i;
    public org.chromium.components.browser_ui.share.a j;
    public ScrollView k;
    public int l;
    public Integer m;
    public Toast n;

    /* JADX INFO: compiled from: ShareSheetBottomSheetContent.java */
    public static class a extends RecyclerView.q {
        public boolean e;
        public final String f;

        public a(String str) {
            this.f = str;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.q
        public final void a(int i, RecyclerView recyclerView) {
            if (!this.e && i == 1) {
                n3.X0(this.f);
                this.e = true;
            }
        }
    }

    public vk2(Activity activity, LargeIconBridge largeIconBridge, xk2 xk2Var, org.chromium.components.browser_ui.share.a aVar, xe3 xe3Var) {
        this.e = activity;
        this.f = largeIconBridge;
        this.g = xk2Var;
        this.j = aVar;
        this.h = xe3Var;
        Boolean bool = aVar.k;
        if (bool == null) {
            this.l = 3;
        } else if (bool.booleanValue()) {
            this.l = 1;
            this.m = 0;
        } else {
            this.l = 2;
            this.m = 1;
        }
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(activity).inflate(R$layout.share_sheet_content, (ViewGroup) null);
        this.i = viewGroup;
        this.k = (ScrollView) viewGroup.findViewById(R$id.share_sheet_scrollview);
    }

    public static void b(PropertyModel propertyModel, ViewGroup viewGroup, v62 v62Var) {
        PropertyModel.r<Drawable> rVar = yk2.a;
        if (rVar.equals(v62Var)) {
            ((ImageView) viewGroup.findViewById(R$id.icon)).setImageDrawable((Drawable) propertyModel.j(rVar));
            return;
        }
        PropertyModel.r<String> rVar2 = yk2.b;
        if (rVar2.equals(v62Var)) {
            ((TextView) viewGroup.findViewById(R$id.text)).setText((CharSequence) propertyModel.j(rVar2));
            return;
        }
        PropertyModel.r<String> rVar3 = yk2.c;
        if (rVar3.equals(v62Var)) {
            ((TextView) viewGroup.findViewById(R$id.text)).setContentDescription((CharSequence) propertyModel.j(rVar3));
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar4 = yk2.d;
        if (rVar4.equals(v62Var)) {
            viewGroup.findViewById(R$id.layout).setOnClickListener((View.OnClickListener) propertyModel.j(rVar4));
            return;
        }
        PropertyModel.n nVar = yk2.e;
        if (nVar.equals(v62Var)) {
            ((TextView) viewGroup.findViewById(R$id.display_new)).setVisibility(propertyModel.k(nVar) ? 0 : 8);
        }
    }

    public static int w(int i) {
        switch (i) {
            case 0:
            case 3:
                return R$string.link_toggle_share_content_only;
            case 1:
                return R$string.link_toggle_share_image_only;
            case 2:
                return R$string.link_toggle_share_gif_only;
            case 4:
                return R$string.link_toggle_share_screenshot_only;
            case 5:
                return R$string.link_toggle_share_webnote_only;
            case 6:
                return R$string.link_toggle_share_reaction_only;
            default:
                return 0;
        }
    }

    public final void A(int i) {
        Toast toast = this.n;
        if (toast != null) {
            toast.a();
        }
        Activity activity = this.e;
        Toast toastB = Toast.b(0, activity, activity.getResources().getString(i));
        this.n = toastB;
        toastB.d(toastB.a.getGravity(), this.n.a.getXOffset(), activity.getResources().getDimensionPixelSize(R$dimen.y_offset_full_sharesheet));
        this.n.e();
    }

    public final void c(ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        int dimensionPixelSize = this.e.getResources().getDimensionPixelSize(R$dimen.sharing_hub_preview_icon_padding);
        imageView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
    }

    @Override // defpackage.ii
    public final View d() {
        return this.i;
    }

    @Override // defpackage.ii
    public final void destroy() {
        a.b bVar;
        Toast toast = this.n;
        if (toast != null) {
            toast.a();
        }
        xk2 xk2Var = this.g;
        org.chromium.components.browser_ui.share.a aVar = xk2Var.s;
        if (aVar != null && (bVar = aVar.n) != null) {
            bVar.onCancel();
        }
        WindowAndroid windowAndroid = xk2Var.u;
        if (windowAndroid != null) {
            windowAndroid.v.c(xk2Var);
            xk2Var.u = null;
        }
        q2 q2Var = xk2Var.q;
        if (q2Var != null) {
            q2Var.e(xk2Var);
            xk2Var.q = null;
        }
        ji jiVar = xk2Var.e;
        if (jiVar != null) {
            jiVar.e(xk2Var.h);
        }
    }

    @Override // defpackage.ii
    public final int e() {
        return 0;
    }

    @Override // defpackage.ii
    public final View g() {
        return null;
    }

    @Override // defpackage.ii
    public final int h() {
        return R$string.sharing_hub_sheet_half_height;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.sharing_hub_sheet_closed;
    }

    @Override // defpackage.ii
    public final int j() {
        return -2;
    }

    @Override // defpackage.ii
    public final int m() {
        ScrollView scrollView = this.k;
        if (scrollView != null) {
            return scrollView.getScrollY();
        }
        return 0;
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.sharing_hub_content_description;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.sharing_hub_sheet_full_height;
    }

    @Override // defpackage.ii
    public final float s() {
        return -1.0f;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return true;
    }

    public final void v(List<PropertyModel> list) {
        RecyclerView recyclerView = (RecyclerView) this.i.findViewById(R$id.share_sheet_chrome_apps);
        if (list == null || list.size() <= 0) {
            return;
        }
        this.i.findViewById(R$id.share_sheet_divider).setVisibility(0);
        recyclerView.setVisibility(0);
        y(list, recyclerView, true);
        recyclerView.addOnScrollListener(new a("SharingHubAndroid.FirstPartyAppsScrolled"));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final String x(String str) {
        if (!str.contains("/")) {
            return "";
        }
        byte b = 2;
        String str2 = str.split("/", 2)[0];
        str2.getClass();
        switch (str2.hashCode()) {
            case 3556653:
                b = !str2.equals("text") ? (byte) -1 : (byte) 0;
                break;
            case 93166550:
                b = !str2.equals("audio") ? (byte) -1 : (byte) 1;
                break;
            case 100313435:
                if (!str2.equals("image")) {
                    b = -1;
                }
                break;
            case 112202875:
                b = !str2.equals("video") ? (byte) -1 : (byte) 3;
                break;
            default:
                b = -1;
                break;
        }
        Activity activity = this.e;
        switch (b) {
        }
        return "";
    }

    public final void y(List<PropertyModel> list, RecyclerView recyclerView, boolean z) {
        ya1 ya1Var = new ya1();
        Iterator<PropertyModel> it = list.iterator();
        while (it.hasNext()) {
            ya1Var.t(new xa1(0, it.next()));
        }
        m mVar = new m(ya1Var);
        mVar.H(0, new o61(R$layout.share_sheet_item), z ? new ag(10) : new bg(20));
        recyclerView.setAdapter(mVar);
        recyclerView.setLayoutManager(new LinearLayoutManager(0));
    }

    public final void z(Drawable drawable) {
        ImageView imageView = (ImageView) this.i.findViewById(R$id.image_preview);
        imageView.setImageDrawable(drawable);
        c(imageView);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }
}
