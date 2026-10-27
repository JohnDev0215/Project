package org.chromium.components.browser_ui.photo_picker;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.a12;
import defpackage.ao3;
import defpackage.b12;
import defpackage.e12;
import defpackage.na0;
import defpackage.oj2;
import defpackage.y02;
import defpackage.ys0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.components.browser_ui.photo_picker.PhotoPickerToolbar;
import org.chromium.components.browser_ui.photo_picker.c;
import org.chromium.components.browser_ui.photo_picker.d;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListLayout;
import org.chromium.components.browser_ui.widget.selectable_list.a;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class PickerCategoryView extends RelativeLayout implements d.a, RecyclerView.t, c.InterfaceC0121c, View.OnClickListener, a.InterfaceC0128a<b12> {
    public static ArrayList I;
    public int A;
    public int B;
    public d C;
    public long D;
    public boolean E;
    public ArrayList F;
    public final PickerVideoPlayer G;
    public final ImageView H;
    public h e;
    public final WindowAndroid f;
    public final ContentResolver g;
    public List<b12> h;
    public final boolean i;
    public y02 j;
    public c k;
    public final RecyclerView l;
    public final a12 m;
    public final GridLayoutManager n;
    public a o;
    public final org.chromium.components.browser_ui.widget.selectable_list.a<b12> p;
    public na0.a<LruCache<String, b>> q;
    public na0.a<LruCache<String, b>> r;
    public na0.a<LruCache<String, b>> s;
    public final int t;
    public final int u;
    public final int v;
    public boolean w;
    public boolean x;
    public int y;
    public int z;

    public class a extends RecyclerView.l {
        public final int a;
        public final int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l
        public final void g(Rect rect, View view, RecyclerView recyclerView, RecyclerView.w wVar) {
            int i;
            int i2;
            boolean z = PickerCategoryView.this.w;
            int i3 = this.b;
            if (z) {
                rect.set(0, 0, 0, i3);
                return;
            }
            recyclerView.getClass();
            int I = RecyclerView.I(view);
            if (I >= 0) {
                int i4 = this.a;
                int i5 = I % i4;
                int i6 = i3 - ((i5 * i3) / i4);
                i2 = ((i5 + 1) * i3) / i4;
                i = I < i4 ? i3 : 0;
                i = i6;
            } else {
                i = 0;
                i2 = 0;
                i3 = 0;
            }
            rect.set(i, i, i2, i3);
        }
    }

    public static class b {
        public final List<Bitmap> a;
        public final String b;
        public final float c;

        public b(List list, String str, float f) {
            this.a = list;
            this.b = str;
            this.c = f;
        }
    }

    public PickerCategoryView(WindowAndroid windowAndroid, ContentResolver contentResolver, boolean z, PhotoPickerToolbar.a aVar) {
        super(windowAndroid.i.get());
        this.f = windowAndroid;
        Context context = windowAndroid.i.get();
        this.g = contentResolver;
        this.i = z;
        c cVar = new c(this, context);
        this.k = cVar;
        Intent intent = c.v.get();
        intent.setAction(e.class.getName());
        cVar.e.bindService(intent, cVar.s, 1);
        cVar.q = true;
        org.chromium.components.browser_ui.widget.selectable_list.a<b12> aVar2 = new org.chromium.components.browser_ui.widget.selectable_list.a<>();
        this.p = aVar2;
        aVar2.a(this);
        if (!z) {
            aVar2.a = true;
        }
        SelectableListLayout selectableListLayout = (SelectableListLayout) LayoutInflater.from(context).inflate(R$layout.photo_picker_dialog, this).findViewById(R$id.selectable_list);
        a12 a12Var = new a12(this);
        this.m = a12Var;
        RecyclerView recyclerViewJ = selectableListLayout.j(a12Var, null);
        this.l = recyclerViewJ;
        PhotoPickerToolbar photoPickerToolbar = (PhotoPickerToolbar) selectableListLayout.k(R$layout.photo_picker_toolbar, aVar2, z ? R$string.photo_picker_select_images : R$string.photo_picker_select_image, 0, 0, null, false);
        photoPickerToolbar.setNavigationOnClickListener(this);
        photoPickerToolbar.setDelegate(aVar);
        ((Button) photoPickerToolbar.findViewById(R$id.done)).setOnClickListener(this);
        this.G = (PickerVideoPlayer) findViewById(R$id.playback_container);
        this.H = (ImageView) findViewById(R$id.zoom);
        c();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, this.y);
        this.n = gridLayoutManager;
        recyclerViewJ.setHasFixedSize(true);
        recyclerViewJ.setLayoutManager(gridLayoutManager);
        a aVar3 = new a(this.y, this.z);
        this.o = aVar3;
        recyclerViewJ.g(aVar3);
        recyclerViewJ.setRecyclerListener(this);
        long jMaxMemory = Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        int i = (int) (jMaxMemory / 4);
        this.v = i;
        this.t = i;
        this.u = (int) (jMaxMemory / 8);
    }

    public static void setTestFiles(List<b12> list) {
        I = new ArrayList(list);
    }

    @Override // org.chromium.components.browser_ui.photo_picker.c.InterfaceC0121c
    public final void b() {
        this.E = true;
        if (this.h != null) {
            this.m.o();
        }
    }

    public final void c() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Context context = this.f.i.get();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.photo_picker_tile_min_size);
        int dimensionPixelSize2 = this.w ? 0 : context.getResources().getDimensionPixelSize(R$dimen.photo_picker_tile_gap);
        this.z = dimensionPixelSize2;
        int iMax = this.w ? 1 : Math.max(1, (i - dimensionPixelSize2) / (dimensionPixelSize + dimensionPixelSize2));
        this.y = iMax;
        this.A = (i - ((iMax + 1) * this.z)) / iMax;
        if (this.w) {
            findViewById(R$id.action_bar_bg).getHeight();
        }
        boolean z = this.w;
        if (!z) {
            this.B = this.A;
        }
        if (z) {
            return;
        }
        boolean z2 = this.y % 2 == 0;
        int i2 = this.z;
        if (z2 != (i2 % 2 == 0)) {
            this.z = i2 + 1;
        }
    }

    public final void d(int i, Uri[] uriArr, int i2) {
        this.j.a(i, uriArr);
        h hVar = this.e;
        if (hVar != null) {
            hVar.dismiss();
        }
        oj2.U(i2, 4, "Android.PhotoPicker.DialogAction");
        a12 a12Var = this.m;
        oj2.R(a12Var.j, "Android.PhotoPicker.DecodeRequests");
        oj2.R(a12Var.i, "Android.PhotoPicker.CacheHits");
    }

    public final void e(List<b12> list) {
        if (list == null) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.D;
        int size = (int) (((long) (list.size() * 100)) / jElapsedRealtime);
        oj2.b0(jElapsedRealtime, "Android.PhotoPicker.EnumerationTime");
        oj2.S(list.size(), 1, TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE, 50, "Android.PhotoPicker.EnumeratedFiles");
        oj2.P(size, "Android.PhotoPicker.EnumeratedRate");
        this.h = list;
        if (this.E) {
            this.m.o();
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        ImageView imageView = this.H;
        if (imageView.getVisibility() != 0) {
            imageView.setVisibility(0);
            imageView.setOnClickListener(this);
        }
    }

    public c getDecoderServiceHost() {
        return this.k;
    }

    public LruCache<String, b> getFullScreenBitmaps() {
        na0.a<LruCache<String, b>> aVar = this.s;
        if (aVar == null || aVar.a == null) {
            this.s = ys0.a.a(new LruCache(this.v));
        }
        return this.s.a;
    }

    public LruCache<String, b> getHighResThumbnails() {
        na0.a<LruCache<String, b>> aVar = this.r;
        if (aVar == null || aVar.a == null) {
            this.r = ys0.a.a(new LruCache(this.t));
        }
        return this.r.a;
    }

    public int getImageWidth() {
        return this.A;
    }

    public LruCache<String, b> getLowResThumbnails() {
        na0.a<LruCache<String, b>> aVar = this.q;
        if (aVar == null || aVar.a == null) {
            this.q = ys0.a.a(new LruCache(this.u));
        }
        return this.q.a;
    }

    public List<b12> getPickerBitmaps() {
        return this.h;
    }

    public org.chromium.components.browser_ui.widget.selectable_list.a<b12> getSelectionDelegate() {
        return this.p;
    }

    public org.chromium.components.browser_ui.widget.selectable_list.a<b12> getSelectionDelegateForTesting() {
        return this.p;
    }

    public int getSpecialTileHeight() {
        return this.B;
    }

    public PickerVideoPlayer getVideoPlayerForTesting() {
        return this.G;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.t
    public final void h(RecyclerView.a0 a0Var) {
        int i;
        b12 b12Var = ((i) a0Var).A;
        String path = (b12Var == null || !((i = b12Var.g) == 0 || i == 3)) ? null : b12Var.e.getPath();
        if (path != null) {
            c decoderServiceHost = getDecoderServiceHost();
            decoderServiceHost.getClass();
            Object obj = ThreadUtils.a;
            Iterator<c.b> it = decoderServiceHost.t.iterator();
            while (it.hasNext()) {
                if (it.next().a.getPath().equals(path)) {
                    it.remove();
                }
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int id = view.getId();
        int i = R$id.done;
        org.chromium.components.browser_ui.widget.selectable_list.a<b12> aVar = this.p;
        int i2 = 0;
        if (id == i) {
            ArrayList arrayListC = aVar.c();
            Collections.sort(arrayListC);
            Uri[] uriArr = new Uri[arrayListC.size()];
            Iterator it = arrayListC.iterator();
            while (it.hasNext()) {
                uriArr[i2] = ((b12) it.next()).e;
                i2++;
            }
            d(1, uriArr, 1);
            return;
        }
        if (id != R$id.zoom) {
            d(0, null, 0);
            return;
        }
        if (this.x) {
            return;
        }
        HashSet hashSet = new HashSet(aVar.c);
        aVar.b();
        this.w = !this.w;
        Context context = this.f.i.get();
        boolean z = this.w;
        ImageView imageView = this.H;
        if (z) {
            imageView.setImageResource(R$drawable.zoom_out);
            imageView.setContentDescription(context.getString(R$string.photo_picker_accessibility_zoom_out));
        } else {
            imageView.setImageResource(R$drawable.zoom_in);
            imageView.setContentDescription(context.getString(R$string.photo_picker_accessibility_zoom_in));
        }
        c();
        if (!this.w) {
            getFullScreenBitmaps().evictAll();
        }
        this.x = true;
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.addListener(new e12(this, hashSet));
        RecyclerView recyclerView = this.l;
        TransitionManager.beginDelayedTransition(recyclerView, changeBounds);
        this.n.s1(this.y);
        this.m.o();
        ao3.e(recyclerView, "PickerCategoryView.flipZoomMode");
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        c();
        this.n.s1(this.y);
        a aVar = this.o;
        RecyclerView recyclerView = this.l;
        recyclerView.c0(aVar);
        a aVar2 = new a(this.y, this.z);
        this.o = aVar2;
        recyclerView.g(aVar2);
        if (this.h != null) {
            this.m.o();
            ao3.e(recyclerView, "PickerCategoryView.onConfigurationChanged");
        }
    }

    @Override // org.chromium.components.browser_ui.photo_picker.c.InterfaceC0121c
    public final void a() {
    }
}
