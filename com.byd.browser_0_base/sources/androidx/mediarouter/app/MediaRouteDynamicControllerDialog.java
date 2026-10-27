package androidx.mediarouter.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.mediarouter.R$attr;
import androidx.mediarouter.R$bool;
import androidx.mediarouter.R$color;
import androidx.mediarouter.R$dimen;
import androidx.mediarouter.R$drawable;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$integer;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.R$string;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.h;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.bz;
import defpackage.qd1;
import defpackage.rv1;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteDynamicControllerDialog extends AppCompatDialog {
    public static final /* synthetic */ int X = 0;
    public h.C0020h A;
    public HashMap B;
    public boolean C;
    public boolean D;
    public boolean E;
    public ImageButton F;
    public Button G;
    public ImageView H;
    public View I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public ImageView f15J;
    public TextView K;
    public TextView L;
    public String M;
    public MediaControllerCompat N;
    public final e O;
    public MediaDescriptionCompat P;
    public d Q;
    public Bitmap R;
    public Uri S;
    public boolean T;
    public Bitmap U;
    public int V;
    public final boolean W;
    public final androidx.mediarouter.media.h j;
    public final g k;
    public androidx.mediarouter.media.g l;
    public h.C0020h m;
    public final ArrayList n;
    public final ArrayList o;
    public final ArrayList p;
    public final ArrayList q;
    public final Context r;
    public boolean s;
    public boolean t;
    public long u;
    public final a v;
    public RecyclerView w;
    public h x;
    public j y;
    public HashMap z;

    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            if (i == 1) {
                mediaRouteDynamicControllerDialog.j();
            } else if (i == 2 && mediaRouteDynamicControllerDialog.A != null) {
                mediaRouteDynamicControllerDialog.A = null;
                mediaRouteDynamicControllerDialog.k();
            }
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            MediaRouteDynamicControllerDialog.this.dismiss();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            if (mediaRouteDynamicControllerDialog.m.i()) {
                mediaRouteDynamicControllerDialog.j.getClass();
                androidx.mediarouter.media.h.i(2);
            }
            mediaRouteDynamicControllerDialog.dismiss();
        }
    }

    public class d extends AsyncTask<Void, Void, Bitmap> {
        public final Bitmap a;
        public final Uri b;
        public int c;

        public d() {
            MediaDescriptionCompat mediaDescriptionCompat = MediaRouteDynamicControllerDialog.this.P;
            Bitmap iconBitmap = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.getIconBitmap();
            if (iconBitmap != null && iconBitmap.isRecycled()) {
                Log.w("MediaRouteCtrlDialog", "Can't fetch the given art bitmap because it's already recycled.");
                iconBitmap = null;
            }
            this.a = iconBitmap;
            MediaDescriptionCompat mediaDescriptionCompat2 = MediaRouteDynamicControllerDialog.this.P;
            this.b = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.getIconUri() : null;
        }

        public final BufferedInputStream a(Uri uri) throws IOException {
            InputStream inputStreamOpenInputStream;
            String lowerCase = uri.getScheme().toLowerCase();
            if ("android.resource".equals(lowerCase) || "content".equals(lowerCase) || "file".equals(lowerCase)) {
                inputStreamOpenInputStream = MediaRouteDynamicControllerDialog.this.r.getContentResolver().openInputStream(uri);
            } else {
                URLConnection uRLConnectionOpenConnection = new URL(uri.toString()).openConnection();
                uRLConnectionOpenConnection.setConnectTimeout(30000);
                uRLConnectionOpenConnection.setReadTimeout(30000);
                inputStreamOpenInputStream = uRLConnectionOpenConnection.getInputStream();
            }
            if (inputStreamOpenInputStream == null) {
                return null;
            }
            return new BufferedInputStream(inputStreamOpenInputStream);
        }

        /* JADX WARN: Not initialized variable reg: 5, insn: 0x008f: MOVE (r3 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]) (LINE:144), block:B:33:0x008f */
        @Override // android.os.AsyncTask
        public final Bitmap doInBackground(Void[] voidArr) throws Throwable {
            InputStream inputStream;
            BufferedInputStream bufferedInputStreamA;
            InputStream inputStream2 = null;
            Bitmap bitmapDecodeStream = this.a;
            if (bitmapDecodeStream == null) {
                Uri uri = this.b;
                try {
                    if (uri != null) {
                        try {
                            bufferedInputStreamA = a(uri);
                            try {
                                if (bufferedInputStreamA == null) {
                                    Log.w("MediaRouteCtrlDialog", "Unable to open: " + uri);
                                    if (bufferedInputStreamA == null) {
                                        return null;
                                    }
                                } else {
                                    BitmapFactory.Options options = new BitmapFactory.Options();
                                    options.inJustDecodeBounds = true;
                                    BitmapFactory.decodeStream(bufferedInputStreamA, null, options);
                                    if (options.outWidth != 0 && options.outHeight != 0) {
                                        try {
                                            bufferedInputStreamA.reset();
                                        } catch (IOException unused) {
                                            bufferedInputStreamA.close();
                                            bufferedInputStreamA = a(uri);
                                            if (bufferedInputStreamA == null) {
                                                Log.w("MediaRouteCtrlDialog", "Unable to open: " + uri);
                                                if (bufferedInputStreamA == null) {
                                                    return null;
                                                }
                                            }
                                            bufferedInputStreamA.close();
                                            return null;
                                        }
                                        options.inJustDecodeBounds = false;
                                        options.inSampleSize = Math.max(1, Integer.highestOneBit(options.outHeight / MediaRouteDynamicControllerDialog.this.r.getResources().getDimensionPixelSize(R$dimen.mr_cast_meta_art_size)));
                                        if (!isCancelled()) {
                                            bitmapDecodeStream = BitmapFactory.decodeStream(bufferedInputStreamA, null, options);
                                            try {
                                                bufferedInputStreamA.close();
                                            } catch (IOException unused2) {
                                            }
                                        }
                                    }
                                }
                                try {
                                    bufferedInputStreamA.close();
                                    return null;
                                } catch (IOException unused3) {
                                    return null;
                                }
                            } catch (IOException e) {
                                e = e;
                                Log.w("MediaRouteCtrlDialog", "Unable to open: " + uri, e);
                                if (bufferedInputStreamA != null) {
                                    try {
                                        bufferedInputStreamA.close();
                                    } catch (IOException unused4) {
                                    }
                                }
                                bitmapDecodeStream = null;
                            }
                        } catch (IOException e2) {
                            e = e2;
                            bufferedInputStreamA = null;
                        } catch (Throwable th) {
                            th = th;
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException unused5) {
                                }
                            }
                            throw th;
                        }
                    } else {
                        bitmapDecodeStream = null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream2 = inputStream;
                }
            }
            int i = MediaRouteDynamicControllerDialog.X;
            if (bitmapDecodeStream != null && bitmapDecodeStream.isRecycled()) {
                Log.w("MediaRouteCtrlDialog", "Can't use recycled bitmap: " + bitmapDecodeStream);
                return null;
            }
            if (bitmapDecodeStream != null && bitmapDecodeStream.getWidth() < bitmapDecodeStream.getHeight()) {
                rv1.b bVar = new rv1.b(bitmapDecodeStream);
                bVar.c = 1;
                List<rv1.d> list = bVar.a().a;
                this.c = Collections.unmodifiableList(list).isEmpty() ? 0 : ((rv1.d) Collections.unmodifiableList(list).get(0)).d;
            }
            return bitmapDecodeStream;
        }

        @Override // android.os.AsyncTask
        public final void onPostExecute(Bitmap bitmap) {
            Bitmap bitmap2 = bitmap;
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            mediaRouteDynamicControllerDialog.Q = null;
            Bitmap bitmap3 = mediaRouteDynamicControllerDialog.R;
            Bitmap bitmap4 = this.a;
            boolean zEquals = Objects.equals(bitmap3, bitmap4);
            Uri uri = this.b;
            if (zEquals && Objects.equals(mediaRouteDynamicControllerDialog.S, uri)) {
                return;
            }
            mediaRouteDynamicControllerDialog.R = bitmap4;
            mediaRouteDynamicControllerDialog.U = bitmap2;
            mediaRouteDynamicControllerDialog.S = uri;
            mediaRouteDynamicControllerDialog.V = this.c;
            mediaRouteDynamicControllerDialog.T = true;
            mediaRouteDynamicControllerDialog.h();
        }

        @Override // android.os.AsyncTask
        public final void onPreExecute() {
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            mediaRouteDynamicControllerDialog.T = false;
            mediaRouteDynamicControllerDialog.U = null;
            mediaRouteDynamicControllerDialog.V = 0;
        }
    }

    public final class e extends MediaControllerCompat.Callback {
        public e() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public final void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            MediaDescriptionCompat description = mediaMetadataCompat == null ? null : mediaMetadataCompat.getDescription();
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            mediaRouteDynamicControllerDialog.P = description;
            mediaRouteDynamicControllerDialog.f();
            mediaRouteDynamicControllerDialog.h();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public final void onSessionDestroyed() {
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            MediaControllerCompat mediaControllerCompat = mediaRouteDynamicControllerDialog.N;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.unregisterCallback(mediaRouteDynamicControllerDialog.O);
                mediaRouteDynamicControllerDialog.N = null;
            }
        }
    }

    public abstract class f extends RecyclerView.a0 {
        public final MediaRouteVolumeSlider A;
        public h.C0020h y;
        public final ImageButton z;

        public class a implements View.OnClickListener {
            public a() {
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                f fVar = f.this;
                MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
                if (mediaRouteDynamicControllerDialog.A != null) {
                    mediaRouteDynamicControllerDialog.v.removeMessages(2);
                }
                h.C0020h c0020h = fVar.y;
                MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog2 = MediaRouteDynamicControllerDialog.this;
                mediaRouteDynamicControllerDialog2.A = c0020h;
                int iMax = 1;
                boolean z = !view.isActivated();
                if (z) {
                    iMax = 0;
                } else {
                    Integer num = (Integer) mediaRouteDynamicControllerDialog2.B.get(fVar.y.c);
                    if (num != null) {
                        iMax = Math.max(1, num.intValue());
                    }
                }
                fVar.v(z);
                fVar.A.setProgress(iMax);
                fVar.y.l(iMax);
                mediaRouteDynamicControllerDialog2.v.sendEmptyMessageDelayed(2, 500L);
            }
        }

        public f(View view, ImageButton imageButton, MediaRouteVolumeSlider mediaRouteVolumeSlider) {
            int color;
            int color2;
            super(view);
            this.z = imageButton;
            this.A = mediaRouteVolumeSlider;
            Context context = MediaRouteDynamicControllerDialog.this.r;
            int i = R$drawable.mr_cast_mute_button;
            int i2 = androidx.mediarouter.app.f.a;
            Drawable drawableA = AppCompatResources.a(context, i);
            if (androidx.mediarouter.app.f.i(context)) {
                Object obj = bz.a;
                drawableA.setTint(context.getColor(androidx.mediarouter.app.f.a));
            }
            imageButton.setImageDrawable(drawableA);
            Context context2 = MediaRouteDynamicControllerDialog.this.r;
            if (androidx.mediarouter.app.f.i(context2)) {
                int i3 = R$color.mr_cast_progressbar_progress_and_thumb_light;
                Object obj2 = bz.a;
                color = context2.getColor(i3);
                color2 = context2.getColor(R$color.mr_cast_progressbar_background_light);
            } else {
                int i4 = R$color.mr_cast_progressbar_progress_and_thumb_dark;
                Object obj3 = bz.a;
                color = context2.getColor(i4);
                color2 = context2.getColor(R$color.mr_cast_progressbar_background_dark);
            }
            mediaRouteVolumeSlider.a(color, color2);
        }

        public final void u(h.C0020h c0020h) {
            this.y = c0020h;
            int i = c0020h.o;
            boolean z = i == 0;
            ImageButton imageButton = this.z;
            imageButton.setActivated(z);
            imageButton.setOnClickListener(new a());
            h.C0020h c0020h2 = this.y;
            MediaRouteVolumeSlider mediaRouteVolumeSlider = this.A;
            mediaRouteVolumeSlider.setTag(c0020h2);
            mediaRouteVolumeSlider.setMax(c0020h.p);
            mediaRouteVolumeSlider.setProgress(i);
            mediaRouteVolumeSlider.setOnSeekBarChangeListener(MediaRouteDynamicControllerDialog.this.y);
        }

        public final void v(boolean z) {
            ImageButton imageButton = this.z;
            if (imageButton.isActivated() == z) {
                return;
            }
            imageButton.setActivated(z);
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            if (z) {
                mediaRouteDynamicControllerDialog.B.put(this.y.c, Integer.valueOf(this.A.getProgress()));
            } else {
                mediaRouteDynamicControllerDialog.B.remove(this.y.c);
            }
        }
    }

    public final class g extends h.a {
        public g() {
        }

        @Override // androidx.mediarouter.media.h.a
        public final void d() {
            MediaRouteDynamicControllerDialog.this.j();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void e(h.C0020h c0020h) {
            h.C0020h.a aVarB;
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            boolean z = false;
            if (c0020h == mediaRouteDynamicControllerDialog.m && h.C0020h.a() != null) {
                h.g gVar = c0020h.a;
                gVar.getClass();
                androidx.mediarouter.media.h.b();
                Iterator it = Collections.unmodifiableList(gVar.b).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    h.C0020h c0020h2 = (h.C0020h) it.next();
                    if (!mediaRouteDynamicControllerDialog.m.c().contains(c0020h2) && (aVarB = mediaRouteDynamicControllerDialog.m.b(c0020h2)) != null) {
                        MediaRouteProvider.b.C0013b c0013b = aVarB.a;
                        if ((c0013b != null && c0013b.d) && !mediaRouteDynamicControllerDialog.o.contains(c0020h2)) {
                            z = true;
                            break;
                        }
                    }
                }
            }
            if (!z) {
                mediaRouteDynamicControllerDialog.j();
            } else {
                mediaRouteDynamicControllerDialog.k();
                mediaRouteDynamicControllerDialog.i();
            }
        }

        @Override // androidx.mediarouter.media.h.a
        public final void f() {
            MediaRouteDynamicControllerDialog.this.j();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void g(h.C0020h c0020h) {
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            mediaRouteDynamicControllerDialog.m = c0020h;
            mediaRouteDynamicControllerDialog.k();
            mediaRouteDynamicControllerDialog.i();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void h() {
            MediaRouteDynamicControllerDialog.this.j();
        }

        @Override // androidx.mediarouter.media.h.a
        public final void i(h.C0020h c0020h) {
            f fVar;
            int i = MediaRouteDynamicControllerDialog.X;
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            if (mediaRouteDynamicControllerDialog.A == c0020h || (fVar = (f) mediaRouteDynamicControllerDialog.z.get(c0020h.c)) == null) {
                return;
            }
            int i2 = fVar.y.o;
            fVar.v(i2 == 0);
            fVar.A.setProgress(i2);
        }
    }

    public static final class i implements Comparator<h.C0020h> {
        public static final i e = new i();

        @Override // java.util.Comparator
        public final int compare(h.C0020h c0020h, h.C0020h c0020h2) {
            return c0020h.d.compareToIgnoreCase(c0020h2.d);
        }
    }

    public class j implements SeekBar.OnSeekBarChangeListener {
        public j() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                h.C0020h c0020h = (h.C0020h) seekBar.getTag();
                f fVar = (f) MediaRouteDynamicControllerDialog.this.z.get(c0020h.c);
                if (fVar != null) {
                    fVar.v(i == 0);
                }
                c0020h.l(i);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStartTrackingTouch(SeekBar seekBar) {
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            if (mediaRouteDynamicControllerDialog.A != null) {
                mediaRouteDynamicControllerDialog.v.removeMessages(2);
            }
            mediaRouteDynamicControllerDialog.A = (h.C0020h) seekBar.getTag();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStopTrackingTouch(SeekBar seekBar) {
            MediaRouteDynamicControllerDialog.this.v.sendEmptyMessageDelayed(2, 500L);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MediaRouteDynamicControllerDialog(Context context) {
        boolean z = false;
        ContextThemeWrapper contextThemeWrapperA = androidx.mediarouter.app.f.a(0, context, false);
        super(contextThemeWrapperA, androidx.mediarouter.app.f.b(contextThemeWrapperA));
        this.l = androidx.mediarouter.media.g.c;
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.p = new ArrayList();
        this.q = new ArrayList();
        this.v = new a();
        Context context2 = getContext();
        this.r = context2;
        this.j = androidx.mediarouter.media.h.d(context2);
        if (androidx.mediarouter.media.h.c != null) {
            androidx.mediarouter.media.h.c().getClass();
            z = true;
        }
        this.W = z;
        this.k = new g();
        this.m = androidx.mediarouter.media.h.f();
        this.O = new e();
        g(androidx.mediarouter.media.h.e());
    }

    public final void e(List<h.C0020h> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            h.C0020h c0020h = list.get(size);
            if (!(!c0020h.f() && c0020h.g && c0020h.j(this.l) && this.m != c0020h)) {
                list.remove(size);
            }
        }
    }

    public final void f() {
        MediaDescriptionCompat mediaDescriptionCompat = this.P;
        Bitmap iconBitmap = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.getIconBitmap();
        MediaDescriptionCompat mediaDescriptionCompat2 = this.P;
        Uri iconUri = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.getIconUri() : null;
        d dVar = this.Q;
        Bitmap bitmap = dVar == null ? this.R : dVar.a;
        Uri uri = dVar == null ? this.S : dVar.b;
        if (bitmap != iconBitmap || (bitmap == null && !Objects.equals(uri, iconUri))) {
            d dVar2 = this.Q;
            if (dVar2 != null) {
                dVar2.cancel(true);
            }
            d dVar3 = new d();
            this.Q = dVar3;
            dVar3.execute(new Void[0]);
        }
    }

    public final void g(MediaSessionCompat.Token token) {
        MediaControllerCompat mediaControllerCompat = this.N;
        e eVar = this.O;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.unregisterCallback(eVar);
            this.N = null;
        }
        if (token != null && this.t) {
            MediaControllerCompat mediaControllerCompat2 = new MediaControllerCompat(this.r, token);
            this.N = mediaControllerCompat2;
            mediaControllerCompat2.registerCallback(eVar);
            MediaMetadataCompat metadata = this.N.getMetadata();
            this.P = metadata != null ? metadata.getDescription() : null;
            f();
            h();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h() {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.h():void");
    }

    public final void i() {
        ArrayList arrayList = this.n;
        arrayList.clear();
        ArrayList arrayList2 = this.o;
        arrayList2.clear();
        ArrayList arrayList3 = this.p;
        arrayList3.clear();
        arrayList.addAll(this.m.c());
        h.g gVar = this.m.a;
        gVar.getClass();
        androidx.mediarouter.media.h.b();
        for (h.C0020h c0020h : Collections.unmodifiableList(gVar.b)) {
            h.C0020h.a aVarB = this.m.b(c0020h);
            if (aVarB != null) {
                MediaRouteProvider.b.C0013b c0013b = aVarB.a;
                if (c0013b != null && c0013b.d) {
                    arrayList2.add(c0020h);
                }
                if (c0013b != null && c0013b.e) {
                    arrayList3.add(c0020h);
                }
            }
        }
        e(arrayList2);
        e(arrayList3);
        i iVar = i.e;
        Collections.sort(arrayList, iVar);
        Collections.sort(arrayList2, iVar);
        Collections.sort(arrayList3, iVar);
        this.x.G();
    }

    public final void j() {
        if (this.t) {
            if (SystemClock.uptimeMillis() - this.u < 300) {
                a aVar = this.v;
                aVar.removeMessages(1);
                aVar.sendEmptyMessageAtTime(1, this.u + 300);
                return;
            }
            if ((this.A != null || this.C) ? true : !this.s) {
                this.D = true;
                return;
            }
            this.D = false;
            if (!this.m.i() || this.m.f()) {
                dismiss();
            }
            this.u = SystemClock.uptimeMillis();
            this.x.F();
        }
    }

    public final void k() {
        if (this.D) {
            j();
        }
        if (this.E) {
            h();
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.t = true;
        this.j.a(this.l, this.k, 1);
        i();
        g(androidx.mediarouter.media.h.e());
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.mr_cast_dialog);
        Context context = this.r;
        androidx.mediarouter.app.f.j(context, this);
        ImageButton imageButton = (ImageButton) findViewById(R$id.mr_cast_close_button);
        this.F = imageButton;
        imageButton.setColorFilter(-1);
        this.F.setOnClickListener(new b());
        Button button = (Button) findViewById(R$id.mr_cast_stop_button);
        this.G = button;
        button.setTextColor(-1);
        this.G.setOnClickListener(new c());
        this.x = new h();
        RecyclerView recyclerView = (RecyclerView) findViewById(R$id.mr_cast_list);
        this.w = recyclerView;
        recyclerView.setAdapter(this.x);
        this.w.setLayoutManager(new LinearLayoutManager(context));
        this.y = new j();
        this.z = new HashMap();
        this.B = new HashMap();
        this.H = (ImageView) findViewById(R$id.mr_cast_meta_background);
        this.I = findViewById(R$id.mr_cast_meta_black_scrim);
        this.f15J = (ImageView) findViewById(R$id.mr_cast_meta_art);
        TextView textView = (TextView) findViewById(R$id.mr_cast_meta_title);
        this.K = textView;
        textView.setTextColor(-1);
        TextView textView2 = (TextView) findViewById(R$id.mr_cast_meta_subtitle);
        this.L = textView2;
        textView2.setTextColor(-1);
        this.M = context.getResources().getString(R$string.mr_cast_dialog_title_view_placeholder);
        this.s = true;
        Resources resources = context.getResources();
        int i2 = R$bool.is_tablet;
        getWindow().setLayout(!resources.getBoolean(i2) ? -1 : qd1.a(context), context.getResources().getBoolean(i2) ? -2 : -1);
        this.R = null;
        this.S = null;
        f();
        h();
        j();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.t = false;
        this.j.h(this.k);
        this.v.removeCallbacksAndMessages(null);
        g(null);
    }

    public final class h extends RecyclerView.e<RecyclerView.a0> {
        public final ArrayList<f> h = new ArrayList<>();
        public final LayoutInflater i;
        public final Drawable j;
        public final Drawable k;
        public final Drawable l;
        public final Drawable m;
        public f n;
        public final int o;
        public final AccelerateDecelerateInterpolator p;

        public class a extends Animation {
            public final /* synthetic */ int e;
            public final /* synthetic */ int f;
            public final /* synthetic */ View g;

            public a(int i, int i2, View view) {
                this.e = i;
                this.f = i2;
                this.g = view;
            }

            @Override // android.view.animation.Animation
            public final void applyTransformation(float f, Transformation transformation) {
                int i = this.e;
                int i2 = this.f + ((int) ((i - r0) * f));
                int i3 = MediaRouteDynamicControllerDialog.X;
                View view = this.g;
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = i2;
                view.setLayoutParams(layoutParams);
            }
        }

        public class c extends RecyclerView.a0 {
            public final ProgressBar A;
            public final TextView B;
            public final float C;
            public h.C0020h D;
            public final View y;
            public final ImageView z;

            public c(View view) {
                super(view);
                this.y = view;
                this.z = (ImageView) view.findViewById(R$id.mr_cast_group_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R$id.mr_cast_group_progress_bar);
                this.A = progressBar;
                this.B = (TextView) view.findViewById(R$id.mr_cast_group_name);
                this.C = androidx.mediarouter.app.f.d(MediaRouteDynamicControllerDialog.this.r);
                androidx.mediarouter.app.f.k(MediaRouteDynamicControllerDialog.this.r, progressBar);
            }
        }

        public class d extends f {
            public final TextView C;
            public final int D;

            public d(View view) {
                super(view, (ImageButton) view.findViewById(R$id.mr_cast_mute_button), (MediaRouteVolumeSlider) view.findViewById(R$id.mr_cast_volume_slider));
                this.C = (TextView) view.findViewById(R$id.mr_group_volume_route_name);
                Resources resources = MediaRouteDynamicControllerDialog.this.r.getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                TypedValue typedValue = new TypedValue();
                resources.getValue(R$dimen.mr_dynamic_volume_group_list_item_height, typedValue, true);
                this.D = (int) typedValue.getDimension(displayMetrics);
            }
        }

        public class e extends RecyclerView.a0 {
            public final TextView y;

            public e(View view) {
                super(view);
                this.y = (TextView) view.findViewById(R$id.mr_cast_header_name);
            }
        }

        public class f {
            public final Object a;
            public final int b;

            public f(Object obj, int i) {
                this.a = obj;
                this.b = i;
            }
        }

        public class g extends f {
            public final View C;
            public final ImageView D;
            public final ProgressBar E;
            public final TextView F;
            public final RelativeLayout G;
            public final CheckBox H;
            public final float I;

            /* JADX INFO: renamed from: J, reason: collision with root package name */
            public final int f16J;
            public final a K;

            public class a implements View.OnClickListener {
                public a() {
                }

                /* JADX WARN: Removed duplicated region for block: B:20:0x0060  */
                /* JADX WARN: Removed duplicated region for block: B:45:0x00d6  */
                @Override // android.view.View.OnClickListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void onClick(android.view.View r10) {
                    /*
                        Method dump skipped, instruction units count: 424
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.MediaRouteDynamicControllerDialog.h.g.a.onClick(android.view.View):void");
                }
            }

            public g(View view) {
                super(view, (ImageButton) view.findViewById(R$id.mr_cast_mute_button), (MediaRouteVolumeSlider) view.findViewById(R$id.mr_cast_volume_slider));
                this.K = new a();
                this.C = view;
                this.D = (ImageView) view.findViewById(R$id.mr_cast_route_icon);
                ProgressBar progressBar = (ProgressBar) view.findViewById(R$id.mr_cast_route_progress_bar);
                this.E = progressBar;
                this.F = (TextView) view.findViewById(R$id.mr_cast_route_name);
                this.G = (RelativeLayout) view.findViewById(R$id.mr_cast_volume_layout);
                CheckBox checkBox = (CheckBox) view.findViewById(R$id.mr_cast_checkbox);
                this.H = checkBox;
                MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
                Context context = mediaRouteDynamicControllerDialog.r;
                Drawable drawableA = AppCompatResources.a(context, R$drawable.mr_cast_checkbox);
                if (androidx.mediarouter.app.f.i(context)) {
                    Object obj = bz.a;
                    drawableA.setTint(context.getColor(androidx.mediarouter.app.f.a));
                }
                checkBox.setButtonDrawable(drawableA);
                Context context2 = mediaRouteDynamicControllerDialog.r;
                androidx.mediarouter.app.f.k(context2, progressBar);
                this.I = androidx.mediarouter.app.f.d(context2);
                Resources resources = context2.getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                TypedValue typedValue = new TypedValue();
                resources.getValue(R$dimen.mr_dynamic_dialog_row_height, typedValue, true);
                this.f16J = (int) typedValue.getDimension(displayMetrics);
            }

            public final boolean w(h.C0020h c0020h) {
                if (c0020h.i()) {
                    return true;
                }
                h.C0020h.a aVarB = MediaRouteDynamicControllerDialog.this.m.b(c0020h);
                if (aVarB != null) {
                    MediaRouteProvider.b.C0013b c0013b = aVarB.a;
                    if ((c0013b != null ? c0013b.b : 1) == 3) {
                        return true;
                    }
                }
                return false;
            }

            public final void x(boolean z, boolean z2) {
                CheckBox checkBox = this.H;
                checkBox.setEnabled(false);
                this.C.setEnabled(false);
                checkBox.setChecked(z);
                if (z) {
                    this.D.setVisibility(4);
                    this.E.setVisibility(0);
                }
                if (z2) {
                    h.this.D(this.G, z ? this.f16J : 0);
                }
            }
        }

        public h() {
            this.i = LayoutInflater.from(MediaRouteDynamicControllerDialog.this.r);
            int i = R$attr.mediaRouteDefaultIconDrawable;
            Context context = MediaRouteDynamicControllerDialog.this.r;
            this.j = androidx.mediarouter.app.f.e(context, i);
            this.k = androidx.mediarouter.app.f.e(context, R$attr.mediaRouteTvIconDrawable);
            this.l = androidx.mediarouter.app.f.e(context, R$attr.mediaRouteSpeakerIconDrawable);
            this.m = androidx.mediarouter.app.f.e(context, R$attr.mediaRouteSpeakerGroupIconDrawable);
            this.o = context.getResources().getInteger(R$integer.mr_cast_volume_slider_layout_animation_duration_ms);
            this.p = new AccelerateDecelerateInterpolator();
            G();
        }

        public final void D(View view, int i) {
            a aVar = new a(i, view.getLayoutParams().height, view);
            aVar.setAnimationListener(new b());
            aVar.setDuration(this.o);
            aVar.setInterpolator(this.p);
            view.startAnimation(aVar);
        }

        public final Drawable E(h.C0020h c0020h) {
            Uri uri = c0020h.f;
            if (uri != null) {
                try {
                    Drawable drawableCreateFromStream = Drawable.createFromStream(MediaRouteDynamicControllerDialog.this.r.getContentResolver().openInputStream(uri), null);
                    if (drawableCreateFromStream != null) {
                        return drawableCreateFromStream;
                    }
                } catch (IOException e2) {
                    Log.w("MediaRouteCtrlDialog", "Failed to load " + uri, e2);
                }
            }
            int i = c0020h.m;
            return i != 1 ? i != 2 ? c0020h.g() ? this.m : this.j : this.l : this.k;
        }

        public final void F() {
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            mediaRouteDynamicControllerDialog.q.clear();
            ArrayList arrayList = mediaRouteDynamicControllerDialog.q;
            ArrayList arrayList2 = mediaRouteDynamicControllerDialog.o;
            ArrayList arrayList3 = new ArrayList();
            h.g gVar = mediaRouteDynamicControllerDialog.m.a;
            gVar.getClass();
            androidx.mediarouter.media.h.b();
            for (h.C0020h c0020h : Collections.unmodifiableList(gVar.b)) {
                h.C0020h.a aVarB = mediaRouteDynamicControllerDialog.m.b(c0020h);
                if (aVarB != null) {
                    MediaRouteProvider.b.C0013b c0013b = aVarB.a;
                    if (c0013b != null && c0013b.d) {
                        arrayList3.add(c0020h);
                    }
                }
            }
            HashSet hashSet = new HashSet(arrayList2);
            hashSet.removeAll(arrayList3);
            arrayList.addAll(hashSet);
            o();
        }

        public final void G() {
            ArrayList<f> arrayList = this.h;
            arrayList.clear();
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            this.n = new f(mediaRouteDynamicControllerDialog.m, 1);
            ArrayList arrayList2 = mediaRouteDynamicControllerDialog.n;
            if (arrayList2.isEmpty()) {
                arrayList.add(new f(mediaRouteDynamicControllerDialog.m, 3));
            } else {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList.add(new f((h.C0020h) it.next(), 3));
                }
            }
            ArrayList<h.C0020h> arrayList3 = mediaRouteDynamicControllerDialog.o;
            boolean zIsEmpty = arrayList3.isEmpty();
            Context context = mediaRouteDynamicControllerDialog.r;
            boolean z = false;
            if (!zIsEmpty) {
                boolean z2 = false;
                for (h.C0020h c0020h : arrayList3) {
                    if (!arrayList2.contains(c0020h)) {
                        if (!z2) {
                            mediaRouteDynamicControllerDialog.m.getClass();
                            MediaRouteProvider.b bVarA = h.C0020h.a();
                            String strK = bVarA != null ? bVarA.k() : null;
                            if (TextUtils.isEmpty(strK)) {
                                strK = context.getString(R$string.mr_dialog_groupable_header);
                            }
                            arrayList.add(new f(strK, 2));
                            z2 = true;
                        }
                        arrayList.add(new f(c0020h, 3));
                    }
                }
            }
            ArrayList<h.C0020h> arrayList4 = mediaRouteDynamicControllerDialog.p;
            if (!arrayList4.isEmpty()) {
                for (h.C0020h c0020h2 : arrayList4) {
                    h.C0020h c0020h3 = mediaRouteDynamicControllerDialog.m;
                    if (c0020h3 != c0020h2) {
                        if (!z) {
                            c0020h3.getClass();
                            MediaRouteProvider.b bVarA2 = h.C0020h.a();
                            String strL = bVarA2 != null ? bVarA2.l() : null;
                            if (TextUtils.isEmpty(strL)) {
                                strL = context.getString(R$string.mr_dialog_transferable_header);
                            }
                            arrayList.add(new f(strL, 2));
                            z = true;
                        }
                        arrayList.add(new f(c0020h2, 4));
                    }
                }
            }
            F();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final int k() {
            return this.h.size() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final int m(int i) {
            f fVar;
            if (i == 0) {
                fVar = this.n;
            } else {
                fVar = this.h.get(i - 1);
            }
            return fVar.b;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final void t(RecyclerView.a0 a0Var, int i) {
            boolean z;
            int iM = m(i);
            f fVar = i == 0 ? this.n : this.h.get(i - 1);
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
            if (iM == 1) {
                mediaRouteDynamicControllerDialog.z.put(((h.C0020h) fVar.a).c, (f) a0Var);
                d dVar = (d) a0Var;
                MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog2 = MediaRouteDynamicControllerDialog.this;
                i = mediaRouteDynamicControllerDialog2.W && mediaRouteDynamicControllerDialog2.m.c().size() > 1 ? dVar.D : 0;
                View view = dVar.e;
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = i;
                view.setLayoutParams(layoutParams);
                h.C0020h c0020h = (h.C0020h) fVar.a;
                dVar.u(c0020h);
                dVar.C.setText(c0020h.d);
                return;
            }
            if (iM == 2) {
                ((e) a0Var).y.setText(fVar.a.toString());
                return;
            }
            if (iM != 3) {
                if (iM != 4) {
                    throw new IllegalStateException();
                }
                c cVar = (c) a0Var;
                h.C0020h c0020h2 = (h.C0020h) fVar.a;
                cVar.D = c0020h2;
                ImageView imageView = cVar.z;
                imageView.setVisibility(0);
                cVar.A.setVisibility(4);
                h hVar = h.this;
                List<h.C0020h> listC = MediaRouteDynamicControllerDialog.this.m.c();
                if (listC.size() == 1 && listC.get(0) == c0020h2) {
                    z = false;
                }
                f = z ? 1.0f : cVar.C;
                View view2 = cVar.y;
                view2.setAlpha(f);
                view2.setOnClickListener(new androidx.mediarouter.app.e(cVar));
                imageView.setImageDrawable(hVar.E(c0020h2));
                cVar.B.setText(c0020h2.d);
                return;
            }
            mediaRouteDynamicControllerDialog.z.put(((h.C0020h) fVar.a).c, (f) a0Var);
            g gVar = (g) a0Var;
            h.C0020h c0020h3 = (h.C0020h) fVar.a;
            h hVar2 = h.this;
            MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog3 = MediaRouteDynamicControllerDialog.this;
            if (c0020h3 == mediaRouteDynamicControllerDialog3.m && c0020h3.c().size() > 0) {
                Iterator<h.C0020h> it = c0020h3.c().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    h.C0020h next = it.next();
                    if (!mediaRouteDynamicControllerDialog3.o.contains(next)) {
                        c0020h3 = next;
                        break;
                    }
                }
            }
            gVar.u(c0020h3);
            Drawable drawableE = hVar2.E(c0020h3);
            ImageView imageView2 = gVar.D;
            imageView2.setImageDrawable(drawableE);
            gVar.F.setText(c0020h3.d);
            CheckBox checkBox = gVar.H;
            checkBox.setVisibility(0);
            boolean zW = gVar.w(c0020h3);
            if (!mediaRouteDynamicControllerDialog3.q.contains(c0020h3) && (!gVar.w(c0020h3) || mediaRouteDynamicControllerDialog3.m.c().size() >= 2)) {
                if (gVar.w(c0020h3)) {
                    h.C0020h.a aVarB = mediaRouteDynamicControllerDialog3.m.b(c0020h3);
                    if (aVarB != null) {
                        MediaRouteProvider.b.C0013b c0013b = aVarB.a;
                        if (c0013b == null || c0013b.c) {
                        }
                    }
                    z = false;
                }
                z = true;
            } else {
                z = false;
            }
            checkBox.setChecked(zW);
            gVar.E.setVisibility(4);
            imageView2.setVisibility(0);
            View view3 = gVar.C;
            view3.setEnabled(z);
            checkBox.setEnabled(z);
            gVar.z.setEnabled(z || zW);
            if (!z && !zW) {
                z = false;
            }
            gVar.A.setEnabled(z);
            g.a aVar = gVar.K;
            view3.setOnClickListener(aVar);
            checkBox.setOnClickListener(aVar);
            if (zW && !gVar.y.g()) {
                i = gVar.f16J;
            }
            RelativeLayout relativeLayout = gVar.G;
            ViewGroup.LayoutParams layoutParams2 = relativeLayout.getLayoutParams();
            layoutParams2.height = i;
            relativeLayout.setLayoutParams(layoutParams2);
            float f2 = gVar.I;
            view3.setAlpha((z || zW) ? 1.0f : f2);
            if (!z && zW) {
                f = f2;
            }
            checkBox.setAlpha(f);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
            LayoutInflater layoutInflater = this.i;
            if (i == 1) {
                return new d(layoutInflater.inflate(R$layout.mr_cast_group_volume_item, (ViewGroup) recyclerView, false));
            }
            if (i == 2) {
                return new e(layoutInflater.inflate(R$layout.mr_cast_header_item, (ViewGroup) recyclerView, false));
            }
            if (i == 3) {
                return new g(layoutInflater.inflate(R$layout.mr_cast_route_item, (ViewGroup) recyclerView, false));
            }
            if (i == 4) {
                return new c(layoutInflater.inflate(R$layout.mr_cast_group_item, (ViewGroup) recyclerView, false));
            }
            throw new IllegalStateException();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.e
        public final void z(RecyclerView.a0 a0Var) {
            MediaRouteDynamicControllerDialog.this.z.values().remove(a0Var);
        }

        public class b implements Animation.AnimationListener {
            public b() {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public final void onAnimationEnd(Animation animation) {
                MediaRouteDynamicControllerDialog mediaRouteDynamicControllerDialog = MediaRouteDynamicControllerDialog.this;
                mediaRouteDynamicControllerDialog.C = false;
                mediaRouteDynamicControllerDialog.k();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public final void onAnimationStart(Animation animation) {
                MediaRouteDynamicControllerDialog.this.C = true;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public final void onAnimationRepeat(Animation animation) {
            }
        }
    }
}
