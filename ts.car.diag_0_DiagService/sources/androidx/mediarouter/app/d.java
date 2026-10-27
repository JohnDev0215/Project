package androidx.mediarouter.app;

import android.R;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
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
import androidx.mediarouter.R$dimen;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$integer;
import androidx.mediarouter.R$interpolator;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.R$string;
import androidx.mediarouter.app.OverlayListView;
import androidx.mediarouter.media.t;
import androidx.mediarouter.media.u;
import androidx.palette.graphics.Palette;
import com.byd.mediacenter.api.media.MediaControl;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class d extends androidx.appcompat.app.a {
    static final boolean r0 = Log.isLoggable("MediaRouteCtrlDialog", 3);
    static final int s0 = (int) TimeUnit.SECONDS.toMillis(30);
    private boolean A;
    private LinearLayout B;
    private RelativeLayout C;
    private LinearLayout D;
    private View E;
    OverlayListView F;
    r G;
    private List<u.g> H;
    Set<u.g> I;
    private Set<u.g> J;
    Set<u.g> K;
    SeekBar L;
    q M;
    u.g N;
    private int O;
    private int P;
    private int Q;
    private final int R;
    Map<u.g, SeekBar> S;
    MediaControllerCompat T;
    o U;
    PlaybackStateCompat V;
    MediaDescriptionCompat W;
    n X;
    Bitmap Y;
    Uri Z;
    boolean a0;
    Bitmap b0;
    int c0;
    boolean d0;
    boolean e0;
    final u f;
    boolean f0;
    private final p g;
    boolean g0;
    final u.g h;
    boolean h0;
    Context i;
    int i0;
    private boolean j;
    private int j0;
    private boolean k;
    private int k0;
    private int l;
    private Interpolator l0;
    private View m;
    private Interpolator m0;
    private Button n;
    private Interpolator n0;
    private Button o;
    private Interpolator o0;
    private ImageButton p;
    final AccessibilityManager p0;
    private ImageButton q;
    Runnable q0;
    private MediaRouteExpandCollapseButton r;
    private FrameLayout s;
    private LinearLayout t;
    FrameLayout u;
    private FrameLayout v;
    private ImageView w;
    private TextView x;
    private TextView y;
    private TextView z;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements OverlayListView.a.InterfaceC0028a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ u.g f862a;

        a(u.g gVar) {
            this.f862a = gVar;
        }

        @Override // androidx.mediarouter.app.OverlayListView.a.InterfaceC0028a
        public void a() {
            d.this.K.remove(this.f862a);
            d.this.G.notifyDataSetChanged();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements ViewTreeObserver.OnGlobalLayoutListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            d.this.F.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            d.this.G();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements Animation.AnimationListener {
        c() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            d.this.q(true);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: renamed from: androidx.mediarouter.app.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class RunnableC0031d implements Runnable {
        RunnableC0031d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.F();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class f implements View.OnClickListener {
        f(d dVar) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class g implements View.OnClickListener {
        g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PendingIntent pendingIntentD;
            MediaControllerCompat mediaControllerCompat = d.this.T;
            if (mediaControllerCompat == null || (pendingIntentD = mediaControllerCompat.d()) == null) {
                return;
            }
            try {
                pendingIntentD.send();
                d.this.dismiss();
            } catch (PendingIntent.CanceledException unused) {
                Log.e("MediaRouteCtrlDialog", pendingIntentD + " was not sent, it had been canceled.");
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class h implements View.OnClickListener {
        h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d dVar = d.this;
            boolean z = !dVar.f0;
            dVar.f0 = z;
            if (z) {
                dVar.F.setVisibility(0);
            }
            d.this.A();
            d.this.K(true);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class i implements ViewTreeObserver.OnGlobalLayoutListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f870c;

        i(boolean z) {
            this.f870c = z;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            d.this.u.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            d dVar = d.this;
            if (dVar.g0) {
                dVar.h0 = true;
            } else {
                dVar.L(this.f870c);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class j extends Animation {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f871c;
        final /* synthetic */ int d;
        final /* synthetic */ View e;

        j(d dVar, int i, int i2, View view) {
            this.f871c = i;
            this.d = i2;
            this.e = view;
        }

        @Override // android.view.animation.Animation
        protected void applyTransformation(float f, Transformation transformation) {
            d.D(this.e, this.f871c - ((int) ((r3 - this.d) * f)));
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class k implements ViewTreeObserver.OnGlobalLayoutListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Map f872c;
        final /* synthetic */ Map d;

        k(Map map, Map map2) {
            this.f872c = map;
            this.d = map2;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            d.this.F.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            d.this.k(this.f872c, this.d);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class l implements Animation.AnimationListener {
        l() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            d.this.F.b();
            d dVar = d.this;
            dVar.F.postDelayed(dVar.q0, dVar.i0);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class m implements View.OnClickListener {
        m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PlaybackStateCompat playbackStateCompat;
            int id = view.getId();
            if (id == 16908313 || id == 16908314) {
                if (d.this.h.B()) {
                    d.this.f.p(id == 16908313 ? 2 : 1);
                }
                d.this.dismiss();
                return;
            }
            if (id != R$id.mr_control_playback_ctrl) {
                if (id == R$id.mr_close) {
                    d.this.dismiss();
                    return;
                }
                return;
            }
            d dVar = d.this;
            if (dVar.T == null || (playbackStateCompat = dVar.V) == null) {
                return;
            }
            int i = 0;
            int i2 = playbackStateCompat.c() != 3 ? 0 : 1;
            if (i2 != 0 && d.this.w()) {
                d.this.T.e().a();
                i = R$string.mr_controller_pause;
            } else if (i2 != 0 && d.this.y()) {
                d.this.T.e().c();
                i = R$string.mr_controller_stop;
            } else if (i2 == 0 && d.this.x()) {
                d.this.T.e().b();
                i = R$string.mr_controller_play;
            }
            AccessibilityManager accessibilityManager = d.this.p0;
            if (accessibilityManager == null || !accessibilityManager.isEnabled() || i == 0) {
                return;
            }
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(16384);
            accessibilityEventObtain.setPackageName(d.this.i.getPackageName());
            accessibilityEventObtain.setClassName(m.class.getName());
            accessibilityEventObtain.getText().add(d.this.i.getString(i));
            d.this.p0.sendAccessibilityEvent(accessibilityEventObtain);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class n extends AsyncTask<Void, Void, Bitmap> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Bitmap f875a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Uri f876b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f877c;
        private long d;

        n() {
            MediaDescriptionCompat mediaDescriptionCompat = d.this.W;
            Bitmap bitmapB = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.b();
            if (d.u(bitmapB)) {
                Log.w("MediaRouteCtrlDialog", "Can't fetch the given art bitmap because it's already recycled.");
                bitmapB = null;
            }
            this.f875a = bitmapB;
            MediaDescriptionCompat mediaDescriptionCompat2 = d.this.W;
            this.f876b = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.c() : null;
        }

        private InputStream e(Uri uri) throws IOException {
            InputStream inputStreamOpenInputStream;
            String lowerCase = uri.getScheme().toLowerCase();
            if ("android.resource".equals(lowerCase) || "content".equals(lowerCase) || "file".equals(lowerCase)) {
                inputStreamOpenInputStream = d.this.i.getContentResolver().openInputStream(uri);
            } else {
                URLConnection uRLConnectionOpenConnection = new URL(uri.toString()).openConnection();
                uRLConnectionOpenConnection.setConnectTimeout(d.s0);
                uRLConnectionOpenConnection.setReadTimeout(d.s0);
                inputStreamOpenInputStream = uRLConnectionOpenConnection.getInputStream();
            }
            if (inputStreamOpenInputStream == null) {
                return null;
            }
            return new BufferedInputStream(inputStreamOpenInputStream);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0 */
        /* JADX WARN: Type inference failed for: r4v1 */
        /* JADX WARN: Type inference failed for: r4v2, types: [java.io.InputStream] */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) throws Throwable {
            InputStream inputStreamE;
            Bitmap bitmap = this.f875a;
            ?? r4 = 0;
            if (bitmap == null) {
                Uri uri = this.f876b;
                try {
                    if (uri != null) {
                        try {
                            inputStreamE = e(uri);
                        } catch (IOException e) {
                            e = e;
                            inputStreamE = null;
                        } catch (Throwable th) {
                            th = th;
                            if (r4 != 0) {
                                try {
                                    r4.close();
                                } catch (IOException unused) {
                                }
                            }
                            throw th;
                        }
                        try {
                            if (inputStreamE == null) {
                                Log.w("MediaRouteCtrlDialog", "Unable to open: " + this.f876b);
                                if (inputStreamE != null) {
                                    try {
                                        inputStreamE.close();
                                    } catch (IOException unused2) {
                                    }
                                }
                                return null;
                            }
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = true;
                            BitmapFactory.decodeStream(inputStreamE, null, options);
                            if (options.outWidth == 0 || options.outHeight == 0) {
                                if (inputStreamE != null) {
                                    try {
                                        inputStreamE.close();
                                    } catch (IOException unused3) {
                                    }
                                }
                                return null;
                            }
                            try {
                                inputStreamE.reset();
                            } catch (IOException unused4) {
                                inputStreamE.close();
                                inputStreamE = e(this.f876b);
                                if (inputStreamE == null) {
                                    Log.w("MediaRouteCtrlDialog", "Unable to open: " + this.f876b);
                                    if (inputStreamE != null) {
                                        try {
                                            inputStreamE.close();
                                        } catch (IOException unused5) {
                                        }
                                    }
                                    return null;
                                }
                            }
                            options.inJustDecodeBounds = false;
                            options.inSampleSize = Math.max(1, Integer.highestOneBit(options.outHeight / d.this.r(options.outWidth, options.outHeight)));
                            if (isCancelled()) {
                                if (inputStreamE != null) {
                                    try {
                                        inputStreamE.close();
                                    } catch (IOException unused6) {
                                    }
                                }
                                return null;
                            }
                            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamE, null, options);
                            if (inputStreamE != null) {
                                try {
                                    inputStreamE.close();
                                } catch (IOException unused7) {
                                }
                            }
                            bitmap = bitmapDecodeStream;
                        } catch (IOException e2) {
                            e = e2;
                            Log.w("MediaRouteCtrlDialog", "Unable to open: " + this.f876b, e);
                            if (inputStreamE != null) {
                                try {
                                    inputStreamE.close();
                                } catch (IOException unused8) {
                                }
                            }
                            bitmap = null;
                        }
                    } else {
                        bitmap = null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    r4 = uri;
                }
            }
            if (d.u(bitmap)) {
                Log.w("MediaRouteCtrlDialog", "Can't use recycled bitmap: " + bitmap);
                return null;
            }
            if (bitmap != null && bitmap.getWidth() < bitmap.getHeight()) {
                Palette paletteGenerate = new Palette.Builder(bitmap).maximumColorCount(1).generate();
                this.f877c = paletteGenerate.getSwatches().isEmpty() ? 0 : ((Palette.Swatch) paletteGenerate.getSwatches().get(0)).getRgb();
            }
            return bitmap;
        }

        public Bitmap b() {
            return this.f875a;
        }

        public Uri c() {
            return this.f876b;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            d dVar = d.this;
            dVar.X = null;
            if (androidx.core.d.a.a(dVar.Y, this.f875a) && androidx.core.d.a.a(d.this.Z, this.f876b)) {
                return;
            }
            d dVar2 = d.this;
            dVar2.Y = this.f875a;
            dVar2.b0 = bitmap;
            dVar2.Z = this.f876b;
            dVar2.c0 = this.f877c;
            dVar2.a0 = true;
            d.this.H(SystemClock.uptimeMillis() - this.d > 120);
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.d = SystemClock.uptimeMillis();
            d.this.o();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class o extends MediaControllerCompat.a {
        o() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            d.this.W = mediaMetadataCompat == null ? null : mediaMetadataCompat.c();
            d.this.I();
            d.this.H(false);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            d dVar = d.this;
            dVar.V = playbackStateCompat;
            dVar.H(false);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void i() {
            d dVar = d.this;
            MediaControllerCompat mediaControllerCompat = dVar.T;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.h(dVar.U);
                d.this.T = null;
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class p extends u.b {
        p() {
        }

        @Override // androidx.mediarouter.media.u.b
        public void e(u uVar, u.g gVar) {
            d.this.H(true);
        }

        @Override // androidx.mediarouter.media.u.b
        public void j(u uVar, u.g gVar) {
            d.this.H(false);
        }

        @Override // androidx.mediarouter.media.u.b
        public void l(u uVar, u.g gVar) {
            SeekBar seekBar = d.this.S.get(gVar);
            int iR = gVar.r();
            if (d.r0) {
                Log.d("MediaRouteCtrlDialog", "onRouteVolumeChanged(), route.getVolume:" + iR);
            }
            if (seekBar == null || d.this.N == gVar) {
                return;
            }
            seekBar.setProgress(iR);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class q implements SeekBar.OnSeekBarChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Runnable f879a = new a();

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                d dVar = d.this;
                if (dVar.N != null) {
                    dVar.N = null;
                    if (dVar.d0) {
                        dVar.H(dVar.e0);
                    }
                }
            }
        }

        q() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                u.g gVar = (u.g) seekBar.getTag();
                if (d.r0) {
                    Log.d("MediaRouteCtrlDialog", "onProgressChanged(): calling MediaRouter.RouteInfo.requestSetVolume(" + i + ")");
                }
                gVar.F(i);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            d dVar = d.this;
            if (dVar.N != null) {
                dVar.L.removeCallbacks(this.f879a);
            }
            d.this.N = (u.g) seekBar.getTag();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            d.this.L.postDelayed(this.f879a, 500L);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class r extends ArrayAdapter<u.g> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final float f882c;

        public r(Context context, List<u.g> list) {
            super(context, 0, list);
            this.f882c = androidx.mediarouter.app.j.h(context);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.mr_controller_volume_item, viewGroup, false);
            } else {
                d.this.P(view);
            }
            u.g item = getItem(i);
            if (item != null) {
                boolean zW = item.w();
                TextView textView = (TextView) view.findViewById(R$id.mr_name);
                textView.setEnabled(zW);
                textView.setText(item.l());
                MediaRouteVolumeSlider mediaRouteVolumeSlider = (MediaRouteVolumeSlider) view.findViewById(R$id.mr_volume_slider);
                androidx.mediarouter.app.j.w(viewGroup.getContext(), mediaRouteVolumeSlider, d.this.F);
                mediaRouteVolumeSlider.setTag(item);
                d.this.S.put(item, mediaRouteVolumeSlider);
                mediaRouteVolumeSlider.c(!zW);
                mediaRouteVolumeSlider.setEnabled(zW);
                if (zW) {
                    if (d.this.z(item)) {
                        mediaRouteVolumeSlider.setMax(item.t());
                        mediaRouteVolumeSlider.setProgress(item.r());
                        mediaRouteVolumeSlider.setOnSeekBarChangeListener(d.this.M);
                    } else {
                        mediaRouteVolumeSlider.setMax(100);
                        mediaRouteVolumeSlider.setProgress(100);
                        mediaRouteVolumeSlider.setEnabled(false);
                    }
                }
                ((ImageView) view.findViewById(R$id.mr_volume_item_icon)).setAlpha(zW ? MediaControl.ACTION_NONE : (int) (this.f882c * 255.0f));
                ((LinearLayout) view.findViewById(R$id.volume_item_container)).setVisibility(d.this.K.contains(item) ? 4 : 0);
                Set<u.g> set = d.this.I;
                if (set != null && set.contains(item)) {
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
        public boolean isEnabled(int i) {
            return false;
        }
    }

    public d(Context context) {
        this(context, 0);
    }

    private void C(boolean z) {
        List<u.g> listK = this.h.k();
        if (listK.isEmpty()) {
            this.H.clear();
            this.G.notifyDataSetChanged();
            return;
        }
        if (androidx.mediarouter.app.g.i(this.H, listK)) {
            this.G.notifyDataSetChanged();
            return;
        }
        HashMap mapE = z ? androidx.mediarouter.app.g.e(this.F, this.G) : null;
        HashMap mapD = z ? androidx.mediarouter.app.g.d(this.i, this.F, this.G) : null;
        this.I = androidx.mediarouter.app.g.f(this.H, listK);
        this.J = androidx.mediarouter.app.g.g(this.H, listK);
        this.H.addAll(0, this.I);
        this.H.removeAll(this.J);
        this.G.notifyDataSetChanged();
        if (z && this.f0 && this.I.size() + this.J.size() > 0) {
            h(mapE, mapD);
        } else {
            this.I = null;
            this.J = null;
        }
    }

    static void D(View view, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i2;
        view.setLayoutParams(layoutParams);
    }

    private void E(MediaSessionCompat.Token token) {
        MediaControllerCompat mediaControllerCompat = this.T;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.h(this.U);
            this.T = null;
        }
        if (token != null && this.k) {
            MediaControllerCompat mediaControllerCompat2 = new MediaControllerCompat(this.i, token);
            this.T = mediaControllerCompat2;
            mediaControllerCompat2.f(this.U);
            MediaMetadataCompat mediaMetadataCompatB = this.T.b();
            this.W = mediaMetadataCompatB != null ? mediaMetadataCompatB.c() : null;
            this.V = this.T.c();
            I();
            H(false);
        }
    }

    private void M(boolean z) {
        int i2 = 0;
        this.E.setVisibility((this.D.getVisibility() == 0 && z) ? 0 : 8);
        LinearLayout linearLayout = this.B;
        if (this.D.getVisibility() == 8 && !z) {
            i2 = 8;
        }
        linearLayout.setVisibility(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void N() {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.d.N():void");
    }

    private void O() {
        if (!z(this.h)) {
            this.D.setVisibility(8);
        } else if (this.D.getVisibility() == 8) {
            this.D.setVisibility(0);
            this.L.setMax(this.h.t());
            this.L.setProgress(this.h.r());
            this.r.setVisibility(this.h.x() ? 0 : 8);
        }
    }

    private static boolean Q(Uri uri, Uri uri2) {
        if (uri == null || !uri.equals(uri2)) {
            return uri == null && uri2 == null;
        }
        return true;
    }

    private void h(Map<u.g, Rect> map, Map<u.g, BitmapDrawable> map2) {
        this.F.setEnabled(false);
        this.F.requestLayout();
        this.g0 = true;
        this.F.getViewTreeObserver().addOnGlobalLayoutListener(new k(map, map2));
    }

    private void l(View view, int i2) {
        j jVar = new j(this, s(view), i2, view);
        jVar.setDuration(this.i0);
        if (Build.VERSION.SDK_INT >= 21) {
            jVar.setInterpolator(this.l0);
        }
        view.startAnimation(jVar);
    }

    private boolean m() {
        return this.m == null && !(this.W == null && this.V == null);
    }

    private void p() {
        c cVar = new c();
        int firstVisiblePosition = this.F.getFirstVisiblePosition();
        boolean z = false;
        for (int i2 = 0; i2 < this.F.getChildCount(); i2++) {
            View childAt = this.F.getChildAt(i2);
            if (this.I.contains(this.G.getItem(firstVisiblePosition + i2))) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(this.j0);
                alphaAnimation.setFillEnabled(true);
                alphaAnimation.setFillAfter(true);
                if (!z) {
                    alphaAnimation.setAnimationListener(cVar);
                    z = true;
                }
                childAt.clearAnimation();
                childAt.startAnimation(alphaAnimation);
            }
        }
    }

    private static int s(View view) {
        return view.getLayoutParams().height;
    }

    private int t(boolean z) {
        if (!z && this.D.getVisibility() != 0) {
            return 0;
        }
        int paddingTop = 0 + this.B.getPaddingTop() + this.B.getPaddingBottom();
        if (z) {
            paddingTop += this.C.getMeasuredHeight();
        }
        if (this.D.getVisibility() == 0) {
            paddingTop += this.D.getMeasuredHeight();
        }
        return (z && this.D.getVisibility() == 0) ? paddingTop + this.E.getMeasuredHeight() : paddingTop;
    }

    static boolean u(Bitmap bitmap) {
        return bitmap != null && bitmap.isRecycled();
    }

    private boolean v() {
        MediaDescriptionCompat mediaDescriptionCompat = this.W;
        Bitmap bitmapB = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.b();
        MediaDescriptionCompat mediaDescriptionCompat2 = this.W;
        Uri uriC = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.c() : null;
        n nVar = this.X;
        Bitmap bitmapB2 = nVar == null ? this.Y : nVar.b();
        n nVar2 = this.X;
        Uri uriC2 = nVar2 == null ? this.Z : nVar2.c();
        if (bitmapB2 != bitmapB) {
            return true;
        }
        return bitmapB2 == null && !Q(uriC2, uriC);
    }

    void A() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.l0 = this.f0 ? this.m0 : this.n0;
        } else {
            this.l0 = this.o0;
        }
    }

    public View B(Bundle bundle) {
        return null;
    }

    void F() {
        n(true);
        this.F.requestLayout();
        this.F.getViewTreeObserver().addOnGlobalLayoutListener(new b());
    }

    void G() {
        Set<u.g> set = this.I;
        if (set == null || set.size() == 0) {
            q(true);
        } else {
            p();
        }
    }

    void H(boolean z) {
        if (this.N != null) {
            this.d0 = true;
            this.e0 = z | this.e0;
            return;
        }
        this.d0 = false;
        this.e0 = false;
        if (!this.h.B() || this.h.v()) {
            dismiss();
            return;
        }
        if (this.j) {
            this.z.setText(this.h.l());
            this.n.setVisibility(this.h.a() ? 0 : 8);
            if (this.m == null && this.a0) {
                if (u(this.b0)) {
                    Log.w("MediaRouteCtrlDialog", "Can't set artwork image with recycled bitmap: " + this.b0);
                } else {
                    this.w.setImageBitmap(this.b0);
                    this.w.setBackgroundColor(this.c0);
                }
                o();
            }
            O();
            N();
            K(z);
        }
    }

    void I() {
        if (this.m == null && v()) {
            n nVar = this.X;
            if (nVar != null) {
                nVar.cancel(true);
            }
            n nVar2 = new n();
            this.X = nVar2;
            nVar2.execute(new Void[0]);
        }
    }

    void J() {
        int iB = androidx.mediarouter.app.g.b(this.i);
        getWindow().setLayout(iB, -2);
        View decorView = getWindow().getDecorView();
        this.l = (iB - decorView.getPaddingLeft()) - decorView.getPaddingRight();
        Resources resources = this.i.getResources();
        this.O = resources.getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_item_icon_size);
        this.P = resources.getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_item_height);
        this.Q = resources.getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_max_height);
        this.Y = null;
        this.Z = null;
        I();
        H(false);
    }

    void K(boolean z) {
        this.u.requestLayout();
        this.u.getViewTreeObserver().addOnGlobalLayoutListener(new i(z));
    }

    void L(boolean z) {
        int iR;
        Bitmap bitmap;
        int iS = s(this.B);
        D(this.B, -1);
        M(m());
        View decorView = getWindow().getDecorView();
        decorView.measure(View.MeasureSpec.makeMeasureSpec(getWindow().getAttributes().width, 1073741824), 0);
        D(this.B, iS);
        if (this.m == null && (this.w.getDrawable() instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) this.w.getDrawable()).getBitmap()) != null) {
            iR = r(bitmap.getWidth(), bitmap.getHeight());
            this.w.setScaleType(bitmap.getWidth() >= bitmap.getHeight() ? ImageView.ScaleType.FIT_XY : ImageView.ScaleType.FIT_CENTER);
        } else {
            iR = 0;
        }
        int iT = t(m());
        int size = this.H.size();
        int size2 = this.h.x() ? this.P * this.h.k().size() : 0;
        if (size > 0) {
            size2 += this.R;
        }
        int iMin = Math.min(size2, this.Q);
        if (!this.f0) {
            iMin = 0;
        }
        int iMax = Math.max(iR, iMin) + iT;
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int iHeight = rect.height() - (this.t.getMeasuredHeight() - this.u.getMeasuredHeight());
        if (this.m != null || iR <= 0 || iMax > iHeight) {
            if (s(this.F) + this.B.getMeasuredHeight() >= this.u.getMeasuredHeight()) {
                this.w.setVisibility(8);
            }
            iMax = iMin + iT;
            iR = 0;
        } else {
            this.w.setVisibility(0);
            D(this.w, iR);
        }
        if (!m() || iMax > iHeight) {
            this.C.setVisibility(8);
        } else {
            this.C.setVisibility(0);
        }
        M(this.C.getVisibility() == 0);
        int iT2 = t(this.C.getVisibility() == 0);
        int iMax2 = Math.max(iR, iMin) + iT2;
        if (iMax2 > iHeight) {
            iMin -= iMax2 - iHeight;
        } else {
            iHeight = iMax2;
        }
        this.B.clearAnimation();
        this.F.clearAnimation();
        this.u.clearAnimation();
        if (z) {
            l(this.B, iT2);
            l(this.F, iMin);
            l(this.u, iHeight);
        } else {
            D(this.B, iT2);
            D(this.F, iMin);
            D(this.u, iHeight);
        }
        D(this.s, rect.height());
        C(z);
    }

    void P(View view) {
        D((LinearLayout) view.findViewById(R$id.volume_item_container), this.P);
        View viewFindViewById = view.findViewById(R$id.mr_volume_item_icon);
        ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
        int i2 = this.O;
        layoutParams.width = i2;
        layoutParams.height = i2;
        viewFindViewById.setLayoutParams(layoutParams);
    }

    void k(Map<u.g, Rect> map, Map<u.g, BitmapDrawable> map2) {
        OverlayListView.a aVar;
        Set<u.g> set = this.I;
        if (set == null || this.J == null) {
            return;
        }
        int size = set.size() - this.J.size();
        l lVar = new l();
        int firstVisiblePosition = this.F.getFirstVisiblePosition();
        boolean z = false;
        for (int i2 = 0; i2 < this.F.getChildCount(); i2++) {
            View childAt = this.F.getChildAt(i2);
            u.g item = this.G.getItem(firstVisiblePosition + i2);
            Rect rect = map.get(item);
            int top = childAt.getTop();
            int i3 = rect != null ? rect.top : (this.P * size) + top;
            AnimationSet animationSet = new AnimationSet(true);
            Set<u.g> set2 = this.I;
            if (set2 != null && set2.contains(item)) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
                alphaAnimation.setDuration(this.j0);
                animationSet.addAnimation(alphaAnimation);
                i3 = top;
            }
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, i3 - top, 0.0f);
            translateAnimation.setDuration(this.i0);
            animationSet.addAnimation(translateAnimation);
            animationSet.setFillAfter(true);
            animationSet.setFillEnabled(true);
            animationSet.setInterpolator(this.l0);
            if (!z) {
                animationSet.setAnimationListener(lVar);
                z = true;
            }
            childAt.clearAnimation();
            childAt.startAnimation(animationSet);
            map.remove(item);
            map2.remove(item);
        }
        for (Map.Entry<u.g, BitmapDrawable> entry : map2.entrySet()) {
            u.g key = entry.getKey();
            BitmapDrawable value = entry.getValue();
            Rect rect2 = map.get(key);
            if (this.J.contains(key)) {
                aVar = new OverlayListView.a(value, rect2);
                aVar.c(1.0f, 0.0f);
                aVar.e(this.k0);
                aVar.f(this.l0);
            } else {
                int i4 = this.P * size;
                OverlayListView.a aVar2 = new OverlayListView.a(value, rect2);
                aVar2.g(i4);
                aVar2.e(this.i0);
                aVar2.f(this.l0);
                aVar2.d(new a(key));
                this.K.add(key);
                aVar = aVar2;
            }
            this.F.a(aVar);
        }
    }

    void n(boolean z) {
        Set<u.g> set;
        int firstVisiblePosition = this.F.getFirstVisiblePosition();
        for (int i2 = 0; i2 < this.F.getChildCount(); i2++) {
            View childAt = this.F.getChildAt(i2);
            u.g item = this.G.getItem(firstVisiblePosition + i2);
            if (!z || (set = this.I) == null || !set.contains(item)) {
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
        this.F.c();
        if (z) {
            return;
        }
        q(false);
    }

    void o() {
        this.a0 = false;
        this.b0 = null;
        this.c0 = 0;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.k = true;
        this.f.b(t.f996c, this.g, 2);
        E(this.f.h());
    }

    @Override // androidx.appcompat.app.a, androidx.appcompat.app.c, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        setContentView(R$layout.mr_controller_material_dialog_b);
        findViewById(R.id.button3).setVisibility(8);
        m mVar = new m();
        FrameLayout frameLayout = (FrameLayout) findViewById(R$id.mr_expandable_area);
        this.s = frameLayout;
        frameLayout.setOnClickListener(new e());
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.mr_dialog_area);
        this.t = linearLayout;
        linearLayout.setOnClickListener(new f(this));
        int iD = androidx.mediarouter.app.j.d(this.i);
        Button button = (Button) findViewById(R.id.button2);
        this.n = button;
        button.setText(R$string.mr_controller_disconnect);
        this.n.setTextColor(iD);
        this.n.setOnClickListener(mVar);
        Button button2 = (Button) findViewById(R.id.button1);
        this.o = button2;
        button2.setText(R$string.mr_controller_stop_casting);
        this.o.setTextColor(iD);
        this.o.setOnClickListener(mVar);
        this.z = (TextView) findViewById(R$id.mr_name);
        ImageButton imageButton = (ImageButton) findViewById(R$id.mr_close);
        this.q = imageButton;
        imageButton.setOnClickListener(mVar);
        this.v = (FrameLayout) findViewById(R$id.mr_custom_control);
        this.u = (FrameLayout) findViewById(R$id.mr_default_control);
        g gVar = new g();
        ImageView imageView = (ImageView) findViewById(R$id.mr_art);
        this.w = imageView;
        imageView.setOnClickListener(gVar);
        findViewById(R$id.mr_control_title_container).setOnClickListener(gVar);
        this.B = (LinearLayout) findViewById(R$id.mr_media_main_control);
        this.E = findViewById(R$id.mr_control_divider);
        this.C = (RelativeLayout) findViewById(R$id.mr_playback_control);
        this.x = (TextView) findViewById(R$id.mr_control_title);
        this.y = (TextView) findViewById(R$id.mr_control_subtitle);
        ImageButton imageButton2 = (ImageButton) findViewById(R$id.mr_control_playback_ctrl);
        this.p = imageButton2;
        imageButton2.setOnClickListener(mVar);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R$id.mr_volume_control);
        this.D = linearLayout2;
        linearLayout2.setVisibility(8);
        SeekBar seekBar = (SeekBar) findViewById(R$id.mr_volume_slider);
        this.L = seekBar;
        seekBar.setTag(this.h);
        q qVar = new q();
        this.M = qVar;
        this.L.setOnSeekBarChangeListener(qVar);
        this.F = (OverlayListView) findViewById(R$id.mr_volume_group_list);
        this.H = new ArrayList();
        r rVar = new r(this.F.getContext(), this.H);
        this.G = rVar;
        this.F.setAdapter((ListAdapter) rVar);
        this.K = new HashSet();
        androidx.mediarouter.app.j.u(this.i, this.B, this.F, this.h.x());
        androidx.mediarouter.app.j.w(this.i, (MediaRouteVolumeSlider) this.L, this.B);
        HashMap map = new HashMap();
        this.S = map;
        map.put(this.h, this.L);
        MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = (MediaRouteExpandCollapseButton) findViewById(R$id.mr_group_expand_collapse);
        this.r = mediaRouteExpandCollapseButton;
        mediaRouteExpandCollapseButton.setOnClickListener(new h());
        A();
        this.i0 = this.i.getResources().getInteger(R$integer.mr_controller_volume_group_list_animation_duration_ms);
        this.j0 = this.i.getResources().getInteger(R$integer.mr_controller_volume_group_list_fade_in_duration_ms);
        this.k0 = this.i.getResources().getInteger(R$integer.mr_controller_volume_group_list_fade_out_duration_ms);
        View viewB = B(bundle);
        this.m = viewB;
        if (viewB != null) {
            this.v.addView(viewB);
            this.v.setVisibility(0);
        }
        this.j = true;
        J();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.f.m(this.g);
        E(null);
        this.k = false;
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.app.a, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 25 && i2 != 24) {
            return super.onKeyDown(i2, keyEvent);
        }
        this.h.G(i2 == 25 ? -1 : 1);
        return true;
    }

    @Override // androidx.appcompat.app.a, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 == 25 || i2 == 24) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    void q(boolean z) {
        this.I = null;
        this.J = null;
        this.g0 = false;
        if (this.h0) {
            this.h0 = false;
            K(z);
        }
        this.F.setEnabled(true);
    }

    int r(int i2, int i3) {
        float f2;
        float f3;
        if (i2 >= i3) {
            f2 = this.l * i3;
            f3 = i2;
        } else {
            f2 = this.l * 9.0f;
            f3 = 16.0f;
        }
        return (int) ((f2 / f3) + 0.5f);
    }

    boolean w() {
        return (this.V.b() & 514) != 0;
    }

    boolean x() {
        return (this.V.b() & 516) != 0;
    }

    boolean y() {
        return (this.V.b() & 1) != 0;
    }

    boolean z(u.g gVar) {
        return this.A && gVar.s() == 1;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public d(Context context, int i2) {
        Context contextB = androidx.mediarouter.app.j.b(context, i2, true);
        super(contextB, androidx.mediarouter.app.j.c(contextB));
        this.A = true;
        this.q0 = new RunnableC0031d();
        this.i = getContext();
        this.U = new o();
        this.f = u.g(this.i);
        this.g = new p();
        this.h = this.f.j();
        E(this.f.h());
        this.R = this.i.getResources().getDimensionPixelSize(R$dimen.mr_controller_volume_group_list_padding_top);
        this.p0 = (AccessibilityManager) this.i.getSystemService("accessibility");
        if (Build.VERSION.SDK_INT >= 21) {
            this.m0 = AnimationUtils.loadInterpolator(contextB, R$interpolator.mr_linear_out_slow_in);
            this.n0 = AnimationUtils.loadInterpolator(contextB, R$interpolator.mr_fast_out_slow_in);
        }
        this.o0 = new AccelerateDecelerateInterpolator();
    }
}
