package org.chromium.components.browser_ui.photo_picker;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import defpackage.a8;
import defpackage.a9;
import defpackage.b12;
import defpackage.i9;
import defpackage.kb3;
import defpackage.ku2;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.rh3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import org.chromium.base.task.PostTask;
import org.chromium.components.browser_ui.photo_picker.PickerCategoryView;
import org.chromium.components.browser_ui.photo_picker.b;
import org.chromium.components.browser_ui.photo_picker.e;
import org.chromium.components.browser_ui.photo_picker.f;

/* JADX INFO: compiled from: DecoderServiceHost.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c extends f.a implements b.a {
    public static int u;
    public static nw2<Intent> v;
    public final Context e;
    public final ContentResolver f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public b o;
    public final ArrayList p;
    public boolean q;
    public e r;
    public final a s;
    public final PriorityQueue<b> t;

    /* JADX INFO: compiled from: DecoderServiceHost.java */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            e c0122a;
            int i = e.a.e;
            if (iBinder == null) {
                c0122a = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.components.browser_ui.photo_picker.IDecoderService");
                c0122a = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof e)) ? new e.a.C0122a(iBinder) : (e) iInterfaceQueryLocalInterface;
            }
            c cVar = c.this;
            cVar.r = c0122a;
            Iterator it = cVar.p.iterator();
            while (it.hasNext()) {
                ((InterfaceC0121c) it.next()).b();
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            Log.e("cr_ImageDecoderHost", "Service has unexpectedly disconnected");
            c.this.r = null;
        }
    }

    /* JADX INFO: compiled from: DecoderServiceHost.java */
    public static class b {
        public final Uri a;
        public final int b;
        public final boolean c;
        public final int d;
        public final boolean e = true;
        public final int f;
        public final d g;
        public long h;

        public b(Uri uri, int i, boolean z, int i2, d dVar) {
            this.a = uri;
            this.b = i;
            this.c = z;
            this.d = i2;
            int i3 = c.u;
            c.u = i3 + 1;
            this.f = i3;
            this.g = dVar;
        }
    }

    /* JADX INFO: renamed from: org.chromium.components.browser_ui.photo_picker.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DecoderServiceHost.java */
    public interface InterfaceC0121c {
        void a();

        void b();
    }

    /* JADX INFO: compiled from: DecoderServiceHost.java */
    public interface d {
    }

    public c(InterfaceC0121c interfaceC0121c, Context context) {
        ArrayList arrayList = new ArrayList();
        this.p = arrayList;
        this.s = new a();
        this.t = new PriorityQueue<>(1, new kb3(2));
        arrayList.add(interfaceC0121c);
        this.e = context;
        this.f = context.getContentResolver();
    }

    public final void A(String str, boolean z, boolean z2, List<Bitmap> list, String str2, long j, float f) {
        PickerBitmapView pickerBitmapView;
        int i;
        List<Bitmap> list2;
        Bitmap bitmap;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (!z || list == null) {
            oj2.b0(jElapsedRealtime - this.o.h, "Android.PhotoPicker.RequestProcessTime");
        } else if (list.size() > 1) {
            oj2.b0(jElapsedRealtime - this.o.h, "Android.PhotoPicker.RequestProcessTimeAnimation");
        } else {
            oj2.b0(jElapsedRealtime - this.o.h, "Android.PhotoPicker.RequestProcessTimeThumbnail");
        }
        i iVar = (i) this.o.g;
        iVar.getClass();
        if (list != null && list.size() != 0 && (z || ((bitmap = list.get(0)) != null && bitmap.getWidth() != 0 && bitmap.getHeight() != 0))) {
            PickerCategoryView.b bVar = z2 ? iVar.y.getFullScreenBitmaps().get(str) : iVar.y.getHighResThumbnails().get(str);
            if (bVar == null || ((list2 = bVar.a) != null && list2.size() < list.size())) {
                if (z2) {
                    iVar.y.getFullScreenBitmaps().put(str, new PickerCategoryView.b(list, str2, f));
                } else {
                    iVar.y.getHighResThumbnails().put(str, new PickerCategoryView.b(list, str2, f));
                }
            }
            PickerCategoryView.b bVar2 = iVar.y.getLowResThumbnails().get(str);
            PickerBitmapView pickerBitmapView2 = iVar.z;
            if (bVar2 == null) {
                pickerBitmapView = pickerBitmapView2;
                new org.chromium.components.browser_ui.photo_picker.a(iVar.y.getLowResThumbnails(), list.get(0), str, str2, pickerBitmapView2.getContext().getResources().getDimensionPixelSize(R$dimen.photo_picker_grainy_thumbnail_size), f).c(i9.e);
            } else {
                pickerBitmapView = pickerBitmapView2;
            }
            b12 b12Var = iVar.A;
            if (TextUtils.equals((b12Var == null || !((i = b12Var.g) == 0 || i == 3)) ? null : b12Var.e.getPath(), str) && pickerBitmapView.t(list, str2, f)) {
                pickerBitmapView.q.setAlpha(0.0f);
                pickerBitmapView.q.animate().alpha(1.0f).setDuration(200L).start();
            }
        }
        if (j != -1 && list != null && list.get(0) != null) {
            int byteCount = list.get(0).getByteCount() / 1024;
            if (!z) {
                oj2.b0(j, "Android.PhotoPicker.ImageDecodeTime");
                oj2.S(byteCount, 1, 100000, 50, "Android.PhotoPicker.ImageByteCount");
            } else if (list.size() > 1) {
                oj2.b0(j, "Android.PhotoPicker.VideoDecodeTimeAnimation");
            } else {
                oj2.b0(j, "Android.PhotoPicker.VideoDecodeTimeThumbnail");
                oj2.S(byteCount, 1, 100000, 50, "Android.PhotoPicker.VideoByteCount");
            }
        }
        this.o = null;
        C();
    }

    public final void B(String str) {
        A(str, false, false, null, null, -1L, 1.0f);
    }

    public final void C() {
        ParcelFileDescriptor parcelFileDescriptor;
        PriorityQueue<b> priorityQueue = this.t;
        b bVarRemove = priorityQueue.isEmpty() ? null : priorityQueue.remove();
        this.o = bVarRemove;
        if (bVarRemove == null) {
            int i = this.g;
            int i2 = this.h;
            int i3 = i + i2 + this.i;
            if (i3 > 0) {
                oj2.Z((i2 * 100) / i3, "Android.PhotoPicker.DecoderHostFailureRuntime");
                oj2.Z((this.i * 100) / i3, "Android.PhotoPicker.DecoderHostFailureOutOfMemory");
                this.g = 0;
                this.h = 0;
                this.i = 0;
            }
            int i4 = this.j;
            int i5 = this.k;
            int i6 = i4 + i5 + this.l + this.m + this.n;
            if (i6 > 0) {
                oj2.Z((i5 * 100) / i6, "Android.PhotoPicker.DecoderHostVideoFileError");
                oj2.Z((this.l * 100) / i6, "Android.PhotoPicker.DecoderHostVideoRuntimeError");
                oj2.Z((this.m * 100) / i6, "Android.PhotoPicker.DecoderHostVideoIoError");
                oj2.Z((this.n * 100) / i6, "Android.PhotoPicker.DecoderHostVideoUnknownError");
                this.j = 0;
                this.k = 0;
                this.l = 0;
                this.m = 0;
                this.n = 0;
            }
            Iterator it = this.p.iterator();
            while (it.hasNext()) {
                ((InterfaceC0121c) it.next()).a();
            }
            return;
        }
        bVarRemove.h = SystemClock.elapsedRealtime();
        b bVar = this.o;
        int i7 = bVar.d;
        Uri uri = bVar.a;
        if (i7 == 3) {
            new org.chromium.components.browser_ui.photo_picker.b(this, this.f, bVar.a, bVar.b, bVar.c, bVar.e ? 1 : 10, 2000).c(i9.e);
            return;
        }
        if (this.r == null) {
            Log.e("cr_ImageDecoderHost", "Connection to decoder service unexpectedly terminated.");
            B(this.o.a.getPath());
            return;
        }
        Bundle bundle = new Bundle();
        ku2 ku2VarQ = ku2.q();
        try {
            try {
                parcelFileDescriptor = this.f.openAssetFileDescriptor(uri, "r").getParcelFileDescriptor();
            } catch (Throwable th) {
                try {
                    ku2VarQ.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (Exception e) {
            Log.e("cr_ImageDecoderHost", "Unable to obtain FileDescriptor", e);
            B(uri.getPath());
        }
        if (parcelFileDescriptor == null) {
            B(uri.getPath());
            ku2VarQ.close();
            return;
        }
        ku2VarQ.close();
        bundle.putString("file_path", uri.getPath());
        bundle.putParcelable("file_descriptor", parcelFileDescriptor);
        bundle.putInt("width", bVar.b);
        bundle.putBoolean("full_width", bVar.c);
        try {
            this.r.e(bundle, this);
        } catch (Exception e2) {
            Log.e("cr_ImageDecoderHost", "IPC Failed", e2);
            B(uri.getPath());
        }
        a8.s(parcelFileDescriptor);
    }

    public final void D(Uri uri, List<Bitmap> list, String str, boolean z, int i, float f) {
        if (i != 0) {
            if (i == 1) {
                this.k++;
            } else if (i == 2) {
                this.l++;
            } else if (i == 3) {
                this.m++;
            }
        } else if (list == null || list.size() == 0) {
            this.n++;
        } else {
            this.j++;
        }
        A(uri.getPath(), true, z, list, str, -1L, f);
    }

    @Override // org.chromium.components.browser_ui.photo_picker.f
    public final void n(Bundle bundle) {
        PostTask.d(rh3.a, new a9(this, 25, bundle));
    }
}
