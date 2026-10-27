package androidx.mediarouter.app;

import android.R;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.mediarouter.R$dimen;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$integer;
import androidx.mediarouter.R$interpolator;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.R$string;
import androidx.mediarouter.app.OverlayListView;
import androidx.mediarouter.media.h;
import defpackage.gv;
import defpackage.md1;
import defpackage.qd1;
import defpackage.rv1;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteControllerDialog extends AlertDialog {
    public static final int t0 = (int) TimeUnit.SECONDS.toMillis(30);
    public TextView A;
    public TextView B;
    public final boolean C;
    public final boolean D;
    public LinearLayout E;
    public RelativeLayout F;
    public LinearLayout G;
    public View H;
    public OverlayListView I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public m f14J;
    public ArrayList K;
    public HashSet L;
    public HashSet M;
    public HashSet N;
    public SeekBar O;
    public l P;
    public h.C0020h Q;
    public int R;
    public int S;
    public int T;
    public final int U;
    public HashMap V;
    public MediaControllerCompat W;
    public final j X;
    public PlaybackStateCompat Y;
    public MediaDescriptionCompat Z;
    public i a0;
    public Bitmap b0;
    public Uri c0;
    public boolean d0;
    public Bitmap e0;
    public int f0;
    public boolean g0;
    public boolean h0;
    public boolean i0;
    public boolean j0;
    public final androidx.mediarouter.media.h k;
    public boolean k0;
    public final k l;
    public int l0;
    public final h.C0020h m;
    public int m0;
    public final Context n;
    public int n0;
    public boolean o;
    public Interpolator o0;
    public boolean p;
    public final Interpolator p0;
    public int q;
    public final Interpolator q0;
    public Button r;
    public final AccessibilityManager r0;
    public Button s;
    public final a s0;
    public ImageButton t;
    public MediaRouteExpandCollapseButton u;
    public FrameLayout v;
    public LinearLayout w;
    public FrameLayout x;
    public ImageView y;
    public TextView z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            mediaRouteControllerDialog.k(true);
            mediaRouteControllerDialog.I.requestLayout();
            mediaRouteControllerDialog.I.getViewTreeObserver().addOnGlobalLayoutListener(new md1(mediaRouteControllerDialog));
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            MediaRouteControllerDialog.this.dismiss();
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            PendingIntent sessionActivity;
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            MediaControllerCompat mediaControllerCompat = mediaRouteControllerDialog.W;
            if (mediaControllerCompat == null || (sessionActivity = mediaControllerCompat.getSessionActivity()) == null) {
                return;
            }
            try {
                sessionActivity.send();
                mediaRouteControllerDialog.dismiss();
            } catch (PendingIntent.CanceledException unused) {
                Log.e("MediaRouteCtrlDialog", sessionActivity + " was not sent, it had been canceled.");
            }
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            boolean z = !mediaRouteControllerDialog.i0;
            mediaRouteControllerDialog.i0 = z;
            if (z) {
                mediaRouteControllerDialog.I.setVisibility(0);
            }
            mediaRouteControllerDialog.o0 = mediaRouteControllerDialog.i0 ? mediaRouteControllerDialog.p0 : mediaRouteControllerDialog.q0;
            mediaRouteControllerDialog.t(true);
        }
    }

    public class f implements ViewTreeObserver.OnGlobalLayoutListener {
        public final /* synthetic */ boolean e;

        public f(boolean z) {
            this.e = z;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            int i;
            HashMap map;
            HashMap map2;
            Bitmap bitmap;
            float f;
            float f2;
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            mediaRouteControllerDialog.x.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            if (mediaRouteControllerDialog.j0) {
                mediaRouteControllerDialog.k0 = true;
                return;
            }
            int i2 = mediaRouteControllerDialog.E.getLayoutParams().height;
            MediaRouteControllerDialog.o(mediaRouteControllerDialog.E, -1);
            mediaRouteControllerDialog.u(mediaRouteControllerDialog.j());
            View decorView = mediaRouteControllerDialog.getWindow().getDecorView();
            decorView.measure(View.MeasureSpec.makeMeasureSpec(mediaRouteControllerDialog.getWindow().getAttributes().width, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), 0);
            MediaRouteControllerDialog.o(mediaRouteControllerDialog.E, i2);
            if (!(mediaRouteControllerDialog.y.getDrawable() instanceof BitmapDrawable) || (bitmap = ((BitmapDrawable) mediaRouteControllerDialog.y.getDrawable()).getBitmap()) == null) {
                i = 0;
            } else {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                if (width >= height) {
                    f = mediaRouteControllerDialog.q * height;
                    f2 = width;
                } else {
                    f = mediaRouteControllerDialog.q * 9.0f;
                    f2 = 16.0f;
                }
                i = (int) ((f / f2) + 0.5f);
                mediaRouteControllerDialog.y.setScaleType(bitmap.getWidth() >= bitmap.getHeight() ? ImageView.ScaleType.FIT_XY : ImageView.ScaleType.FIT_CENTER);
            }
            int iM = mediaRouteControllerDialog.m(mediaRouteControllerDialog.j());
            int size = mediaRouteControllerDialog.K.size();
            boolean zN = mediaRouteControllerDialog.n();
            h.C0020h c0020h = mediaRouteControllerDialog.m;
            int size2 = zN ? c0020h.c().size() * mediaRouteControllerDialog.S : 0;
            if (size > 0) {
                size2 += mediaRouteControllerDialog.U;
            }
            int iMin = Math.min(size2, mediaRouteControllerDialog.T);
            if (!mediaRouteControllerDialog.i0) {
                iMin = 0;
            }
            int iMax = Math.max(i, iMin) + iM;
            Rect rect = new Rect();
            decorView.getWindowVisibleDisplayFrame(rect);
            int iHeight = rect.height() - (mediaRouteControllerDialog.w.getMeasuredHeight() - mediaRouteControllerDialog.x.getMeasuredHeight());
            if (i <= 0 || iMax > iHeight) {
                if (mediaRouteControllerDialog.E.getMeasuredHeight() + mediaRouteControllerDialog.I.getLayoutParams().height >= mediaRouteControllerDialog.x.getMeasuredHeight()) {
                    mediaRouteControllerDialog.y.setVisibility(8);
                }
                iMax = iMin + iM;
                i = 0;
            } else {
                mediaRouteControllerDialog.y.setVisibility(0);
                MediaRouteControllerDialog.o(mediaRouteControllerDialog.y, i);
            }
            if (!mediaRouteControllerDialog.j() || iMax > iHeight) {
                mediaRouteControllerDialog.F.setVisibility(8);
            } else {
                mediaRouteControllerDialog.F.setVisibility(0);
            }
            mediaRouteControllerDialog.u(mediaRouteControllerDialog.F.getVisibility() == 0);
            int iM2 = mediaRouteControllerDialog.m(mediaRouteControllerDialog.F.getVisibility() == 0);
            int iMax2 = Math.max(i, iMin) + iM2;
            if (iMax2 > iHeight) {
                iMin -= iMax2 - iHeight;
            } else {
                iHeight = iMax2;
            }
            mediaRouteControllerDialog.E.clearAnimation();
            mediaRouteControllerDialog.I.clearAnimation();
            mediaRouteControllerDialog.x.clearAnimation();
            boolean z = this.e;
            if (z) {
                mediaRouteControllerDialog.i(mediaRouteControllerDialog.E, iM2);
                mediaRouteControllerDialog.i(mediaRouteControllerDialog.I, iMin);
                mediaRouteControllerDialog.i(mediaRouteControllerDialog.x, iHeight);
            } else {
                MediaRouteControllerDialog.o(mediaRouteControllerDialog.E, iM2);
                MediaRouteControllerDialog.o(mediaRouteControllerDialog.I, iMin);
                MediaRouteControllerDialog.o(mediaRouteControllerDialog.x, iHeight);
            }
            MediaRouteControllerDialog.o(mediaRouteControllerDialog.v, rect.height());
            List<h.C0020h> listC = c0020h.c();
            if (listC.isEmpty()) {
                mediaRouteControllerDialog.K.clear();
                mediaRouteControllerDialog.f14J.notifyDataSetChanged();
                return;
            }
            if (new HashSet(mediaRouteControllerDialog.K).equals(new HashSet(listC))) {
                mediaRouteControllerDialog.f14J.notifyDataSetChanged();
                return;
            }
            if (z) {
                OverlayListView overlayListView = mediaRouteControllerDialog.I;
                m mVar = mediaRouteControllerDialog.f14J;
                map = new HashMap();
                int firstVisiblePosition = overlayListView.getFirstVisiblePosition();
                for (int i3 = 0; i3 < overlayListView.getChildCount(); i3++) {
                    h.C0020h item = mVar.getItem(firstVisiblePosition + i3);
                    View childAt = overlayListView.getChildAt(i3);
                    map.put(item, new Rect(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom()));
                }
            } else {
                map = null;
            }
            if (z) {
                OverlayListView overlayListView2 = mediaRouteControllerDialog.I;
                m mVar2 = mediaRouteControllerDialog.f14J;
                map2 = new HashMap();
                int firstVisiblePosition2 = overlayListView2.getFirstVisiblePosition();
                for (int i4 = 0; i4 < overlayListView2.getChildCount(); i4++) {
                    h.C0020h item2 = mVar2.getItem(firstVisiblePosition2 + i4);
                    View childAt2 = overlayListView2.getChildAt(i4);
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(childAt2.getWidth(), childAt2.getHeight(), Bitmap.Config.ARGB_8888);
                    childAt2.draw(new Canvas(bitmapCreateBitmap));
                    map2.put(item2, new BitmapDrawable(mediaRouteControllerDialog.n.getResources(), bitmapCreateBitmap));
                }
            } else {
                map2 = null;
            }
            ArrayList arrayList = mediaRouteControllerDialog.K;
            HashSet hashSet = new HashSet(listC);
            hashSet.removeAll(arrayList);
            mediaRouteControllerDialog.L = hashSet;
            HashSet hashSet2 = new HashSet(mediaRouteControllerDialog.K);
            hashSet2.removeAll(listC);
            mediaRouteControllerDialog.M = hashSet2;
            mediaRouteControllerDialog.K.addAll(0, mediaRouteControllerDialog.L);
            mediaRouteControllerDialog.K.removeAll(mediaRouteControllerDialog.M);
            mediaRouteControllerDialog.f14J.notifyDataSetChanged();
            if (z && mediaRouteControllerDialog.i0) {
                if (mediaRouteControllerDialog.M.size() + mediaRouteControllerDialog.L.size() > 0) {
                    mediaRouteControllerDialog.I.setEnabled(false);
                    mediaRouteControllerDialog.I.requestLayout();
                    mediaRouteControllerDialog.j0 = true;
                    mediaRouteControllerDialog.I.getViewTreeObserver().addOnGlobalLayoutListener(new androidx.mediarouter.app.b(mediaRouteControllerDialog, map, map2));
                    return;
                }
            }
            mediaRouteControllerDialog.L = null;
            mediaRouteControllerDialog.M = null;
        }
    }

    public class g extends Animation {
        public final /* synthetic */ int e;
        public final /* synthetic */ int f;
        public final /* synthetic */ View g;

        public g(int i, int i2, ViewGroup viewGroup) {
            this.e = i;
            this.f = i2;
            this.g = viewGroup;
        }

        @Override // android.view.animation.Animation
        public final void applyTransformation(float f, Transformation transformation) {
            int i = this.f;
            MediaRouteControllerDialog.o(this.g, this.e - ((int) ((r0 - i) * f)));
        }
    }

    public final class h implements View.OnClickListener {
        public h() {
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x004c  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x006c  */
        @Override // android.view.View.OnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onClick(android.view.View r10) {
            /*
                Method dump skipped, instruction units count: 220
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.MediaRouteControllerDialog.h.onClick(android.view.View):void");
        }
    }

    public class i extends AsyncTask<Void, Void, Bitmap> {
        public final Bitmap a;
        public final Uri b;
        public int c;
        public long d;

        public i() {
            MediaDescriptionCompat mediaDescriptionCompat = MediaRouteControllerDialog.this.Z;
            Bitmap iconBitmap = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.getIconBitmap();
            if (iconBitmap != null && iconBitmap.isRecycled()) {
                Log.w("MediaRouteCtrlDialog", "Can't fetch the given art bitmap because it's already recycled.");
                iconBitmap = null;
            }
            this.a = iconBitmap;
            MediaDescriptionCompat mediaDescriptionCompat2 = MediaRouteControllerDialog.this.Z;
            this.b = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.getIconUri() : null;
        }

        public final BufferedInputStream a(Uri uri) throws IOException {
            InputStream inputStreamOpenInputStream;
            String lowerCase = uri.getScheme().toLowerCase();
            if ("android.resource".equals(lowerCase) || "content".equals(lowerCase) || "file".equals(lowerCase)) {
                inputStreamOpenInputStream = MediaRouteControllerDialog.this.n.getContentResolver().openInputStream(uri);
            } else {
                URLConnection uRLConnectionOpenConnection = new URL(uri.toString()).openConnection();
                int i = MediaRouteControllerDialog.t0;
                uRLConnectionOpenConnection.setConnectTimeout(i);
                uRLConnectionOpenConnection.setReadTimeout(i);
                inputStreamOpenInputStream = uRLConnectionOpenConnection.getInputStream();
            }
            if (inputStreamOpenInputStream == null) {
                return null;
            }
            return new BufferedInputStream(inputStreamOpenInputStream);
        }

        /* JADX WARN: Not initialized variable reg: 5, insn: 0x009e: MOVE (r3 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]) (LINE:159), block:B:39:0x009e */
        @Override // android.os.AsyncTask
        public final Bitmap doInBackground(Void[] voidArr) throws Throwable {
            InputStream inputStream;
            BufferedInputStream bufferedInputStreamA;
            float f;
            float f2;
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
                                        MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
                                        int i = options.outWidth;
                                        int i2 = options.outHeight;
                                        if (i >= i2) {
                                            f = mediaRouteControllerDialog.q * i2;
                                            f2 = i;
                                        } else {
                                            f = mediaRouteControllerDialog.q * 9.0f;
                                            f2 = 16.0f;
                                        }
                                        options.inSampleSize = Math.max(1, Integer.highestOneBit(i2 / ((int) ((f / f2) + 0.5f))));
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
            int i3 = MediaRouteControllerDialog.t0;
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
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            mediaRouteControllerDialog.a0 = null;
            Bitmap bitmap3 = mediaRouteControllerDialog.b0;
            Bitmap bitmap4 = this.a;
            boolean zEquals = Objects.equals(bitmap3, bitmap4);
            Uri uri = this.b;
            if (zEquals && Objects.equals(mediaRouteControllerDialog.c0, uri)) {
                return;
            }
            mediaRouteControllerDialog.b0 = bitmap4;
            mediaRouteControllerDialog.e0 = bitmap2;
            mediaRouteControllerDialog.c0 = uri;
            mediaRouteControllerDialog.f0 = this.c;
            mediaRouteControllerDialog.d0 = true;
            mediaRouteControllerDialog.q(SystemClock.uptimeMillis() - this.d > 120);
        }

        @Override // android.os.AsyncTask
        public final void onPreExecute() {
            this.d = SystemClock.uptimeMillis();
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            mediaRouteControllerDialog.d0 = false;
            mediaRouteControllerDialog.e0 = null;
            mediaRouteControllerDialog.f0 = 0;
        }
    }

    public final class j extends MediaControllerCompat.Callback {
        public j() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public final void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            MediaDescriptionCompat description = mediaMetadataCompat == null ? null : mediaMetadataCompat.getDescription();
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            mediaRouteControllerDialog.Z = description;
            mediaRouteControllerDialog.r();
            mediaRouteControllerDialog.q(false);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public final void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            mediaRouteControllerDialog.Y = playbackStateCompat;
            mediaRouteControllerDialog.q(false);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public final void onSessionDestroyed() {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            MediaControllerCompat mediaControllerCompat = mediaRouteControllerDialog.W;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.unregisterCallback(mediaRouteControllerDialog.X);
                mediaRouteControllerDialog.W = null;
            }
        }
    }

    public final class k extends h.a {
        public k() {
        }

        @Override // androidx.mediarouter.media.h.a
        public final void e(h.C0020h c0020h) {
            MediaRouteControllerDialog.this.q(true);
        }

        @Override // androidx.mediarouter.media.h.a
        public final void h() {
            MediaRouteControllerDialog.this.q(false);
        }

        @Override // androidx.mediarouter.media.h.a
        public final void i(h.C0020h c0020h) {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            SeekBar seekBar = (SeekBar) mediaRouteControllerDialog.V.get(c0020h);
            int i = c0020h.o;
            int i2 = MediaRouteControllerDialog.t0;
            if (seekBar == null || mediaRouteControllerDialog.Q == c0020h) {
                return;
            }
            seekBar.setProgress(i);
        }
    }

    public class l implements SeekBar.OnSeekBarChangeListener {
        public final a e = new a();

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
                if (mediaRouteControllerDialog.Q != null) {
                    mediaRouteControllerDialog.Q = null;
                    if (mediaRouteControllerDialog.g0) {
                        mediaRouteControllerDialog.q(mediaRouteControllerDialog.h0);
                    }
                }
            }
        }

        public l() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                h.C0020h c0020h = (h.C0020h) seekBar.getTag();
                int i2 = MediaRouteControllerDialog.t0;
                c0020h.l(i);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStartTrackingTouch(SeekBar seekBar) {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            if (mediaRouteControllerDialog.Q != null) {
                mediaRouteControllerDialog.O.removeCallbacks(this.e);
            }
            mediaRouteControllerDialog.Q = (h.C0020h) seekBar.getTag();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStopTrackingTouch(SeekBar seekBar) {
            MediaRouteControllerDialog.this.O.postDelayed(this.e, 500L);
        }
    }

    public class m extends ArrayAdapter<h.C0020h> {
        public final float e;

        public m(Context context, List<h.C0020h> list) {
            super(context, 0, list);
            this.e = androidx.mediarouter.app.f.d(context);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            MediaRouteControllerDialog mediaRouteControllerDialog = MediaRouteControllerDialog.this;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mr_controller_volume_item, viewGroup, false);
            } else {
                mediaRouteControllerDialog.getClass();
                MediaRouteControllerDialog.o((LinearLayout) view.findViewById(R$id.volume_item_container), mediaRouteControllerDialog.S);
                View viewFindViewById = view.findViewById(R$id.mr_volume_item_icon);
                ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
                int i2 = mediaRouteControllerDialog.R;
                layoutParams.width = i2;
                layoutParams.height = i2;
                viewFindViewById.setLayoutParams(layoutParams);
            }
            h.C0020h item = getItem(i);
            if (item != null) {
                boolean z = item.g;
                TextView textView = (TextView) view.findViewById(R$id.mr_name);
                textView.setEnabled(z);
                textView.setText(item.d);
                MediaRouteVolumeSlider mediaRouteVolumeSlider = (MediaRouteVolumeSlider) view.findViewById(R$id.mr_volume_slider);
                androidx.mediarouter.app.f.l(viewGroup.getContext(), mediaRouteVolumeSlider, mediaRouteControllerDialog.I);
                mediaRouteVolumeSlider.setTag(item);
                mediaRouteControllerDialog.V.put(item, mediaRouteVolumeSlider);
                mediaRouteVolumeSlider.b(!z);
                mediaRouteVolumeSlider.setEnabled(z);
                if (z) {
                    if (mediaRouteControllerDialog.C && item.e() == 1) {
                        mediaRouteVolumeSlider.setMax(item.p);
                        mediaRouteVolumeSlider.setProgress(item.o);
                        mediaRouteVolumeSlider.setOnSeekBarChangeListener(mediaRouteControllerDialog.P);
                    } else {
                        mediaRouteVolumeSlider.setMax(100);
                        mediaRouteVolumeSlider.setProgress(100);
                        mediaRouteVolumeSlider.setEnabled(false);
                    }
                }
                ((ImageView) view.findViewById(R$id.mr_volume_item_icon)).setAlpha(z ? 255 : (int) (this.e * 255.0f));
                ((LinearLayout) view.findViewById(R$id.volume_item_container)).setVisibility(mediaRouteControllerDialog.N.contains(item) ? 4 : 0);
                HashSet hashSet = mediaRouteControllerDialog.L;
                if (hashSet != null && hashSet.contains(item)) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
                    alphaAnimation.setDuration(0L);
                    alphaAnimation.setFillEnabled(true);
                    alphaAnimation.setFillAfter(true);
                    view.clearAnimation();
                    view.startAnimation(alphaAnimation);
                }
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public final boolean isEnabled(int i) {
            return false;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MediaRouteControllerDialog(Context context) {
        boolean z = false;
        ContextThemeWrapper contextThemeWrapperA = androidx.mediarouter.app.f.a(0, context, true);
        super(contextThemeWrapperA, androidx.mediarouter.app.f.b(contextThemeWrapperA));
        this.C = true;
        this.s0 = new a();
        Context context2 = getContext();
        this.n = context2;
        this.X = new j();
        this.k = androidx.mediarouter.media.h.d(context2);
        if (androidx.mediarouter.media.h.c != null) {
            androidx.mediarouter.media.h.c().getClass();
            z = true;
        }
        this.D = z;
        this.l = new k();
        this.m = androidx.mediarouter.media.h.f();
        p(androidx.mediarouter.media.h.e());
        this.U = context2.getResources().getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_padding_top);
        this.r0 = (AccessibilityManager) context2.getSystemService("accessibility");
        this.p0 = AnimationUtils.loadInterpolator(contextThemeWrapperA, R$interpolator.mr_linear_out_slow_in);
        this.q0 = AnimationUtils.loadInterpolator(contextThemeWrapperA, R$interpolator.mr_fast_out_slow_in);
        new AccelerateDecelerateInterpolator();
    }

    public static void o(View view, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i2;
        view.setLayoutParams(layoutParams);
    }

    public final void i(View view, int i2) {
        g gVar = new g(view.getLayoutParams().height, i2, (ViewGroup) view);
        gVar.setDuration(this.l0);
        gVar.setInterpolator(this.o0);
        view.startAnimation(gVar);
    }

    public final boolean j() {
        return (this.Z == null && this.Y == null) ? false : true;
    }

    public final void k(boolean z) {
        HashSet hashSet;
        int firstVisiblePosition = this.I.getFirstVisiblePosition();
        for (int i2 = 0; i2 < this.I.getChildCount(); i2++) {
            View childAt = this.I.getChildAt(i2);
            h.C0020h item = this.f14J.getItem(firstVisiblePosition + i2);
            if (!z || (hashSet = this.L) == null || !hashSet.contains(item)) {
                ((LinearLayout) childAt.findViewById(R$id.volume_item_container)).setVisibility(0);
                AnimationSet animationSet = new AnimationSet(true);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 1.0f);
                alphaAnimation.setDuration(0L);
                animationSet.addAnimation(alphaAnimation);
                new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f).setDuration(0L);
                animationSet.setFillAfter(true);
                animationSet.setFillEnabled(true);
                childAt.clearAnimation();
                childAt.startAnimation(animationSet);
            }
        }
        for (OverlayListView.a aVar : this.I.e) {
            aVar.k = true;
            aVar.l = true;
            OverlayListView.a.InterfaceC0012a interfaceC0012a = aVar.m;
            if (interfaceC0012a != null) {
                androidx.mediarouter.app.a aVar2 = (androidx.mediarouter.app.a) interfaceC0012a;
                MediaRouteControllerDialog mediaRouteControllerDialog = aVar2.b;
                mediaRouteControllerDialog.N.remove(aVar2.a);
                mediaRouteControllerDialog.f14J.notifyDataSetChanged();
            }
        }
        if (z) {
            return;
        }
        l(false);
    }

    public final void l(boolean z) {
        this.L = null;
        this.M = null;
        this.j0 = false;
        if (this.k0) {
            this.k0 = false;
            t(z);
        }
        this.I.setEnabled(true);
    }

    public final int m(boolean z) {
        if (!z && this.G.getVisibility() != 0) {
            return 0;
        }
        int paddingBottom = this.E.getPaddingBottom() + this.E.getPaddingTop() + 0;
        if (z) {
            paddingBottom += this.F.getMeasuredHeight();
        }
        int measuredHeight = this.G.getVisibility() == 0 ? this.G.getMeasuredHeight() + paddingBottom : paddingBottom;
        return (z && this.G.getVisibility() == 0) ? measuredHeight + this.H.getMeasuredHeight() : measuredHeight;
    }

    public final boolean n() {
        h.C0020h c0020h = this.m;
        return c0020h.g() && c0020h.c().size() > 1;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.p = true;
        this.k.a(androidx.mediarouter.media.g.c, this.l, 2);
        p(androidx.mediarouter.media.h.e());
    }

    @Override // androidx.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        setContentView(R$layout.mr_controller_material_dialog_b);
        findViewById(R.id.button3).setVisibility(8);
        h hVar = new h();
        FrameLayout frameLayout = (FrameLayout) findViewById(R$id.mr_expandable_area);
        this.v = frameLayout;
        frameLayout.setOnClickListener(new b());
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.mr_dialog_area);
        this.w = linearLayout;
        linearLayout.setOnClickListener(new c());
        int i2 = androidx.appcompat.R.attr.colorPrimary;
        Context context = this.n;
        int iG = androidx.mediarouter.app.f.g(context, 0, i2);
        if (gv.c(iG, androidx.mediarouter.app.f.g(context, 0, R.attr.colorBackground)) < 3.0d) {
            iG = androidx.mediarouter.app.f.g(context, 0, androidx.appcompat.R.attr.colorAccent);
        }
        Button button = (Button) findViewById(R.id.button2);
        this.r = button;
        button.setText(R$string.mr_controller_disconnect);
        this.r.setTextColor(iG);
        this.r.setOnClickListener(hVar);
        Button button2 = (Button) findViewById(R.id.button1);
        this.s = button2;
        button2.setText(R$string.mr_controller_stop_casting);
        this.s.setTextColor(iG);
        this.s.setOnClickListener(hVar);
        this.B = (TextView) findViewById(R$id.mr_name);
        ((ImageButton) findViewById(R$id.mr_close)).setOnClickListener(hVar);
        this.x = (FrameLayout) findViewById(R$id.mr_default_control);
        d dVar = new d();
        ImageView imageView = (ImageView) findViewById(R$id.mr_art);
        this.y = imageView;
        imageView.setOnClickListener(dVar);
        findViewById(R$id.mr_control_title_container).setOnClickListener(dVar);
        this.E = (LinearLayout) findViewById(R$id.mr_media_main_control);
        this.H = findViewById(R$id.mr_control_divider);
        this.F = (RelativeLayout) findViewById(R$id.mr_playback_control);
        this.z = (TextView) findViewById(R$id.mr_control_title);
        this.A = (TextView) findViewById(R$id.mr_control_subtitle);
        ImageButton imageButton = (ImageButton) findViewById(R$id.mr_control_playback_ctrl);
        this.t = imageButton;
        imageButton.setOnClickListener(hVar);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R$id.mr_volume_control);
        this.G = linearLayout2;
        linearLayout2.setVisibility(8);
        SeekBar seekBar = (SeekBar) findViewById(R$id.mr_volume_slider);
        this.O = seekBar;
        h.C0020h c0020h = this.m;
        seekBar.setTag(c0020h);
        l lVar = new l();
        this.P = lVar;
        this.O.setOnSeekBarChangeListener(lVar);
        this.I = (OverlayListView) findViewById(R$id.mr_volume_group_list);
        this.K = new ArrayList();
        m mVar = new m(this.I.getContext(), this.K);
        this.f14J = mVar;
        this.I.setAdapter((ListAdapter) mVar);
        this.N = new HashSet();
        LinearLayout linearLayout3 = this.E;
        OverlayListView overlayListView = this.I;
        boolean zN = n();
        int iG2 = androidx.mediarouter.app.f.g(context, 0, i2);
        int iG3 = androidx.mediarouter.app.f.g(context, 0, androidx.appcompat.R.attr.colorPrimaryDark);
        if (zN && androidx.mediarouter.app.f.c(context, 0) == -570425344) {
            iG3 = iG2;
            iG2 = -1;
        }
        linearLayout3.setBackgroundColor(iG2);
        overlayListView.setBackgroundColor(iG3);
        linearLayout3.setTag(Integer.valueOf(iG2));
        overlayListView.setTag(Integer.valueOf(iG3));
        androidx.mediarouter.app.f.l(context, (MediaRouteVolumeSlider) this.O, this.E);
        HashMap map = new HashMap();
        this.V = map;
        map.put(c0020h, this.O);
        MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = (MediaRouteExpandCollapseButton) findViewById(R$id.mr_group_expand_collapse);
        this.u = mediaRouteExpandCollapseButton;
        mediaRouteExpandCollapseButton.setOnClickListener(new e());
        this.o0 = this.i0 ? this.p0 : this.q0;
        this.l0 = context.getResources().getInteger(R$integer.mr_controller_volume_group_list_animation_duration_ms);
        this.m0 = context.getResources().getInteger(R$integer.mr_controller_volume_group_list_fade_in_duration_ms);
        this.n0 = context.getResources().getInteger(R$integer.mr_controller_volume_group_list_fade_out_duration_ms);
        this.o = true;
        s();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onDetachedFromWindow() {
        this.k.h(this.l);
        p(null);
        this.p = false;
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.app.AlertDialog, android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 25 && i2 != 24) {
            return super.onKeyDown(i2, keyEvent);
        }
        if (this.D || !this.i0) {
            this.m.m(i2 == 25 ? -1 : 1);
        }
        return true;
    }

    @Override // androidx.appcompat.app.AlertDialog, android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 == 25 || i2 == 24) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    public final void p(MediaSessionCompat.Token token) {
        MediaControllerCompat mediaControllerCompat = this.W;
        j jVar = this.X;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.unregisterCallback(jVar);
            this.W = null;
        }
        if (token != null && this.p) {
            MediaControllerCompat mediaControllerCompat2 = new MediaControllerCompat(this.n, token);
            this.W = mediaControllerCompat2;
            mediaControllerCompat2.registerCallback(jVar);
            MediaMetadataCompat metadata = this.W.getMetadata();
            this.Z = metadata != null ? metadata.getDescription() : null;
            this.Y = this.W.getPlaybackState();
            r();
            q(false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x015e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void q(boolean r13) {
        /*
            Method dump skipped, instruction units count: 497
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.MediaRouteControllerDialog.q(boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void r() {
        /*
            r6 = this;
            android.support.v4.media.MediaDescriptionCompat r0 = r6.Z
            r1 = 0
            if (r0 != 0) goto L7
            r0 = r1
            goto Lb
        L7:
            android.graphics.Bitmap r0 = r0.getIconBitmap()
        Lb:
            android.support.v4.media.MediaDescriptionCompat r2 = r6.Z
            if (r2 != 0) goto L10
            goto L14
        L10:
            android.net.Uri r1 = r2.getIconUri()
        L14:
            androidx.mediarouter.app.MediaRouteControllerDialog$i r2 = r6.a0
            if (r2 != 0) goto L1b
            android.graphics.Bitmap r3 = r6.b0
            goto L1d
        L1b:
            android.graphics.Bitmap r3 = r2.a
        L1d:
            if (r2 != 0) goto L22
            android.net.Uri r2 = r6.c0
            goto L24
        L22:
            android.net.Uri r2 = r2.b
        L24:
            r4 = 0
            r5 = 1
            if (r3 == r0) goto L29
            goto L3d
        L29:
            if (r3 != 0) goto L3f
            if (r2 == 0) goto L34
            boolean r0 = r2.equals(r1)
            if (r0 == 0) goto L34
            goto L38
        L34:
            if (r2 != 0) goto L3a
            if (r1 != 0) goto L3a
        L38:
            r0 = r5
            goto L3b
        L3a:
            r0 = r4
        L3b:
            if (r0 != 0) goto L3f
        L3d:
            r0 = r5
            goto L40
        L3f:
            r0 = r4
        L40:
            if (r0 == 0) goto L60
            boolean r0 = r6.n()
            if (r0 == 0) goto L4d
            boolean r0 = r6.D
            if (r0 != 0) goto L4d
            goto L60
        L4d:
            androidx.mediarouter.app.MediaRouteControllerDialog$i r0 = r6.a0
            if (r0 == 0) goto L54
            r0.cancel(r5)
        L54:
            androidx.mediarouter.app.MediaRouteControllerDialog$i r0 = new androidx.mediarouter.app.MediaRouteControllerDialog$i
            r0.<init>()
            r6.a0 = r0
            java.lang.Void[] r6 = new java.lang.Void[r4]
            r0.execute(r6)
        L60:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.MediaRouteControllerDialog.r():void");
    }

    public final void s() {
        Context context = this.n;
        int iA = qd1.a(context);
        getWindow().setLayout(iA, -2);
        View decorView = getWindow().getDecorView();
        this.q = (iA - decorView.getPaddingLeft()) - decorView.getPaddingRight();
        Resources resources = context.getResources();
        this.R = resources.getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_item_icon_size);
        this.S = resources.getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_item_height);
        this.T = resources.getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_max_height);
        this.b0 = null;
        this.c0 = null;
        r();
        q(false);
    }

    public final void t(boolean z) {
        this.x.requestLayout();
        this.x.getViewTreeObserver().addOnGlobalLayoutListener(new f(z));
    }

    public final void u(boolean z) {
        int i2 = 0;
        this.H.setVisibility((this.G.getVisibility() == 0 && z) ? 0 : 8);
        LinearLayout linearLayout = this.E;
        if (this.G.getVisibility() == 8 && !z) {
            i2 = 8;
        }
        linearLayout.setVisibility(i2);
    }

    public class c implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
        }
    }
}
