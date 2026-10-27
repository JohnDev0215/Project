package androidx.media3.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.TsNormalNum;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.SurfaceSyncGroup;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.image.ImageOutput;
import androidx.media3.ui.PlayerView;
import com.stingray.drivemodedetector.R;
import d5.I;
import e2.InterfaceC1205m;
import e2.Q;
import e2.W;
import e2.q0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import l3.C1764u;
import m3.InterfaceC1795A;
import m3.InterfaceC1796B;
import m3.InterfaceC1801a;
import m3.InterfaceC1809i;
import m3.r;
import m3.s;
import m3.x;
import m3.z;

/* JADX INFO: loaded from: classes.dex */
public class PlayerView extends FrameLayout {

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public static final /* synthetic */ int f12021R = 0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final Class f12022A;
    public final Method B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final Object f12023C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public W f12024D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f12025E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public r f12026F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f12027G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public int f12028H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public Drawable f12029I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f12030J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f12031K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public CharSequence f12032L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public int f12033M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public boolean f12034N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public boolean f12035O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public boolean f12036P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public boolean f12037Q;
    public final z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AspectRatioFrameLayout f12038m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final View f12039n;
    public final View o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f12040p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C1764u f12041q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ImageView f12042r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final ImageView f12043s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final SubtitleView f12044t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final View f12045u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final TextView f12046v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final s f12047w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final FrameLayout f12048x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final FrameLayout f12049y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final Handler f12050z;

    public PlayerView(Context context) {
        Class<ExoPlayer> cls;
        Object objNewProxyInstance;
        Method method;
        super(context, null, 0);
        z zVar = new z(this);
        this.l = zVar;
        this.f12050z = new Handler(Looper.getMainLooper());
        if (isInEditMode()) {
            this.f12038m = null;
            this.f12039n = null;
            this.o = null;
            this.f12040p = false;
            this.f12041q = null;
            this.f12042r = null;
            this.f12043s = null;
            this.f12044t = null;
            this.f12045u = null;
            this.f12046v = null;
            this.f12047w = null;
            this.f12048x = null;
            this.f12049y = null;
            this.f12022A = null;
            this.B = null;
            this.f12023C = null;
            ImageView imageView = new ImageView(context);
            if (AbstractC1343A.f15195a >= 23) {
                Resources resources = getResources();
                imageView.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo, context.getTheme()));
                imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color, null));
            } else {
                Resources resources2 = getResources();
                imageView.setImageDrawable(resources2.getDrawable(R.drawable.exo_edit_mode_logo, context.getTheme()));
                imageView.setBackgroundColor(resources2.getColor(R.color.exo_edit_mode_background_color));
            }
            addView(imageView);
            return;
        }
        LayoutInflater.from(context).inflate(R.layout.exo_player_view, this);
        setDescendantFocusability(262144);
        AspectRatioFrameLayout aspectRatioFrameLayout = (AspectRatioFrameLayout) findViewById(R.id.exo_content_frame);
        this.f12038m = aspectRatioFrameLayout;
        if (aspectRatioFrameLayout != null) {
            aspectRatioFrameLayout.setResizeMode(0);
        }
        this.f12039n = findViewById(R.id.exo_shutter);
        if (aspectRatioFrameLayout != null) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            SurfaceView surfaceView = new SurfaceView(context);
            if (AbstractC1343A.f15195a >= 34) {
                surfaceView.setSurfaceLifecycle(2);
            }
            this.o = surfaceView;
            surfaceView.setLayoutParams(layoutParams);
            surfaceView.setOnClickListener(zVar);
            surfaceView.setClickable(false);
            aspectRatioFrameLayout.addView(surfaceView, 0);
        } else {
            this.o = null;
        }
        this.f12040p = false;
        this.f12041q = AbstractC1343A.f15195a == 34 ? new C1764u(5, false) : null;
        this.f12048x = (FrameLayout) findViewById(R.id.exo_ad_overlay);
        this.f12049y = (FrameLayout) findViewById(R.id.exo_overlay);
        this.f12042r = (ImageView) findViewById(R.id.exo_image);
        this.f12028H = 0;
        try {
            cls = ExoPlayer.class;
            method = cls.getMethod("setImageOutput", ImageOutput.class);
            objNewProxyInstance = Proxy.newProxyInstance(ImageOutput.class.getClassLoader(), new Class[]{ImageOutput.class}, new InvocationHandler() { // from class: m3.y
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method2, Object[] objArr) {
                    int i7 = PlayerView.f12021R;
                    PlayerView playerView = this.f19179a;
                    playerView.getClass();
                    if (!method2.getName().equals("onImageAvailable")) {
                        return null;
                    }
                    playerView.f12050z.post(new A2.m(playerView, 29, (Bitmap) objArr[1]));
                    return null;
                }
            });
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            cls = null;
            objNewProxyInstance = null;
            method = null;
        }
        this.f12022A = cls;
        this.B = method;
        this.f12023C = objNewProxyInstance;
        ImageView imageView2 = (ImageView) findViewById(R.id.exo_artwork);
        this.f12043s = imageView2;
        this.f12027G = imageView2 != null ? 1 : 0;
        SubtitleView subtitleView = (SubtitleView) findViewById(R.id.exo_subtitles);
        this.f12044t = subtitleView;
        if (subtitleView != null) {
            subtitleView.a();
            subtitleView.b();
        }
        View viewFindViewById = findViewById(R.id.exo_buffering);
        this.f12045u = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(8);
        }
        this.f12030J = 0;
        TextView textView = (TextView) findViewById(R.id.exo_error_message);
        this.f12046v = textView;
        if (textView != null) {
            textView.setVisibility(8);
        }
        s sVar = (s) findViewById(R.id.exo_controller);
        View viewFindViewById2 = findViewById(R.id.exo_controller_placeholder);
        if (sVar != null) {
            this.f12047w = sVar;
        } else if (viewFindViewById2 != null) {
            s sVar2 = new s(context);
            this.f12047w = sVar2;
            sVar2.setId(R.id.exo_controller);
            sVar2.setLayoutParams(viewFindViewById2.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) viewFindViewById2.getParent();
            int iIndexOfChild = viewGroup.indexOfChild(viewFindViewById2);
            viewGroup.removeView(viewFindViewById2);
            viewGroup.addView(sVar2, iIndexOfChild);
        } else {
            this.f12047w = null;
        }
        s sVar3 = this.f12047w;
        this.f12033M = sVar3 != null ? TsNormalNum.THOUSAND_FIVE : 0;
        this.f12036P = true;
        this.f12034N = true;
        this.f12035O = true;
        this.f12025E = sVar3 != null;
        if (sVar3 != null) {
            x xVar = sVar3.l;
            int i7 = xVar.f19178z;
            if (i7 != 3 && i7 != 2) {
                xVar.f();
                xVar.i(2);
            }
            s sVar4 = this.f12047w;
            z zVar2 = this.l;
            sVar4.getClass();
            zVar2.getClass();
            sVar4.o.add(zVar2);
        }
        setClickable(true);
        l();
    }

    public static void a(PlayerView playerView, Bitmap bitmap) {
        playerView.getClass();
        playerView.setImage(new BitmapDrawable(playerView.getResources(), bitmap));
        W w10 = playerView.f12024D;
        if (w10 != null && w10.R0(30) && w10.J().b(2)) {
            return;
        }
        ImageView imageView = playerView.f12042r;
        if (imageView != null) {
            imageView.setVisibility(0);
            playerView.o();
        }
        View view = playerView.f12039n;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    private void setImage(Drawable drawable) {
        ImageView imageView = this.f12042r;
        if (imageView == null) {
            return;
        }
        imageView.setImageDrawable(drawable);
        o();
    }

    private void setImageOutput(W w10) {
        Class cls = this.f12022A;
        if (cls == null || !cls.isAssignableFrom(w10.getClass())) {
            return;
        }
        try {
            Method method = this.B;
            method.getClass();
            Object obj = this.f12023C;
            obj.getClass();
            method.invoke(w10, obj);
        } catch (IllegalAccessException | InvocationTargetException e10) {
            throw new RuntimeException(e10);
        }
    }

    public final boolean b() {
        W w10 = this.f12024D;
        return w10 != null && this.f12023C != null && w10.R0(30) && w10.J().b(4);
    }

    public final void c() {
        ImageView imageView = this.f12042r;
        if (imageView != null) {
            imageView.setVisibility(4);
        }
        if (imageView != null) {
            imageView.setImageResource(android.R.color.transparent);
        }
    }

    public final boolean d() {
        W w10 = this.f12024D;
        return w10 != null && w10.R0(16) && this.f12024D.s() && this.f12024D.C();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        C1764u c1764u;
        SurfaceSyncGroup surfaceSyncGroup;
        super.dispatchDraw(canvas);
        if (AbstractC1343A.f15195a != 34 || (c1764u = this.f12041q) == null || !this.f12037Q || (surfaceSyncGroup = (SurfaceSyncGroup) c1764u.f18608m) == null) {
            return;
        }
        surfaceSyncGroup.markSyncReady();
        c1764u.f18608m = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        W w10 = this.f12024D;
        if (w10 != null && w10.R0(16) && this.f12024D.s()) {
            return super.dispatchKeyEvent(keyEvent);
        }
        int keyCode = keyEvent.getKeyCode();
        boolean z6 = keyCode == 19 || keyCode == 270 || keyCode == 22 || keyCode == 271 || keyCode == 20 || keyCode == 269 || keyCode == 21 || keyCode == 268 || keyCode == 23;
        s sVar = this.f12047w;
        if (z6 && p() && !sVar.g()) {
            e(true);
        } else {
            if ((!p() || !sVar.c(keyEvent)) && !super.dispatchKeyEvent(keyEvent)) {
                if (!z6 || !p()) {
                    return false;
                }
                e(true);
                return false;
            }
            e(true);
        }
        return true;
    }

    public final void e(boolean z6) {
        if (!(d() && this.f12035O) && p()) {
            s sVar = this.f12047w;
            boolean z10 = sVar.g() && sVar.getShowTimeoutMs() <= 0;
            boolean zG = g();
            if (z6 || z10 || zG) {
                h(zG);
            }
        }
    }

    public final boolean f(Drawable drawable) {
        ImageView imageView = this.f12043s;
        if (imageView != null && drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                float width = intrinsicWidth / intrinsicHeight;
                ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;
                if (this.f12027G == 2) {
                    width = getWidth() / getHeight();
                    scaleType = ImageView.ScaleType.CENTER_CROP;
                }
                AspectRatioFrameLayout aspectRatioFrameLayout = this.f12038m;
                if (aspectRatioFrameLayout != null) {
                    aspectRatioFrameLayout.setAspectRatio(width);
                }
                imageView.setScaleType(scaleType);
                imageView.setImageDrawable(drawable);
                imageView.setVisibility(0);
                return true;
            }
        }
        return false;
    }

    public final boolean g() {
        W w10 = this.f12024D;
        if (w10 == null) {
            return true;
        }
        int iE = w10.e();
        if (this.f12034N && (!this.f12024D.R0(17) || !this.f12024D.t0().p())) {
            if (iE == 1 || iE == 4) {
                return true;
            }
            W w11 = this.f12024D;
            w11.getClass();
            if (!w11.C()) {
                return true;
            }
        }
        return false;
    }

    public List<Q> getAdOverlayInfos() {
        ArrayList arrayList = new ArrayList();
        FrameLayout frameLayout = this.f12049y;
        if (frameLayout != null) {
            arrayList.add(new Q(frameLayout));
        }
        s sVar = this.f12047w;
        if (sVar != null) {
            arrayList.add(new Q(sVar));
        }
        return I.w(arrayList);
    }

    public ViewGroup getAdViewGroup() {
        FrameLayout frameLayout = this.f12048x;
        AbstractC1346c.l(frameLayout, "exo_ad_overlay must be present for ad playback");
        return frameLayout;
    }

    public int getArtworkDisplayMode() {
        return this.f12027G;
    }

    public boolean getControllerAutoShow() {
        return this.f12034N;
    }

    public boolean getControllerHideOnTouch() {
        return this.f12036P;
    }

    public int getControllerShowTimeoutMs() {
        return this.f12033M;
    }

    public Drawable getDefaultArtwork() {
        return this.f12029I;
    }

    public int getImageDisplayMode() {
        return this.f12028H;
    }

    public FrameLayout getOverlayFrameLayout() {
        return this.f12049y;
    }

    public W getPlayer() {
        return this.f12024D;
    }

    public int getResizeMode() {
        AspectRatioFrameLayout aspectRatioFrameLayout = this.f12038m;
        AbstractC1346c.k(aspectRatioFrameLayout);
        return aspectRatioFrameLayout.getResizeMode();
    }

    public SubtitleView getSubtitleView() {
        return this.f12044t;
    }

    @Deprecated
    public boolean getUseArtwork() {
        return this.f12027G != 0;
    }

    public boolean getUseController() {
        return this.f12025E;
    }

    public View getVideoSurfaceView() {
        return this.o;
    }

    public final void h(boolean z6) {
        if (p()) {
            int i7 = z6 ? 0 : this.f12033M;
            s sVar = this.f12047w;
            sVar.setShowTimeoutMs(i7);
            x xVar = sVar.l;
            s sVar2 = xVar.f19157a;
            if (!sVar2.h()) {
                sVar2.setVisibility(0);
                sVar2.i();
                ImageView imageView = sVar2.f19146z;
                if (imageView != null) {
                    imageView.requestFocus();
                }
            }
            xVar.k();
        }
    }

    public final void i() {
        if (!p() || this.f12024D == null) {
            return;
        }
        s sVar = this.f12047w;
        if (!sVar.g()) {
            e(true);
        } else if (this.f12036P) {
            sVar.f();
        }
    }

    public final void j() {
        W w10 = this.f12024D;
        q0 q0VarT = w10 != null ? w10.T() : q0.d;
        int i7 = q0VarT.f14332a;
        int i10 = q0VarT.f14333b;
        float f10 = this.f12040p ? 0.0f : (i10 == 0 || i7 == 0) ? 0.0f : (i7 * q0VarT.c) / i10;
        AspectRatioFrameLayout aspectRatioFrameLayout = this.f12038m;
        if (aspectRatioFrameLayout != null) {
            aspectRatioFrameLayout.setAspectRatio(f10);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k() {
        /*
            r5 = this;
            android.view.View r0 = r5.f12045u
            if (r0 == 0) goto L29
            e2.W r1 = r5.f12024D
            r2 = 0
            if (r1 == 0) goto L20
            int r1 = r1.e()
            r3 = 2
            if (r1 != r3) goto L20
            int r1 = r5.f12030J
            r4 = 1
            if (r1 == r3) goto L21
            if (r1 != r4) goto L20
            e2.W r5 = r5.f12024D
            boolean r5 = r5.C()
            if (r5 == 0) goto L20
            goto L21
        L20:
            r4 = r2
        L21:
            if (r4 == 0) goto L24
            goto L26
        L24:
            r2 = 8
        L26:
            r0.setVisibility(r2)
        L29:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.ui.PlayerView.k():void");
    }

    public final void l() {
        s sVar = this.f12047w;
        if (sVar == null || !this.f12025E) {
            setContentDescription(null);
        } else if (sVar.g()) {
            setContentDescription(this.f12036P ? getResources().getString(R.string.exo_controls_hide) : null);
        } else {
            setContentDescription(getResources().getString(R.string.exo_controls_show));
        }
    }

    public final void m() {
        TextView textView = this.f12046v;
        if (textView != null) {
            CharSequence charSequence = this.f12032L;
            if (charSequence != null) {
                textView.setText(charSequence);
                textView.setVisibility(0);
            } else {
                W w10 = this.f12024D;
                if (w10 != null) {
                    w10.n();
                }
                textView.setVisibility(8);
            }
        }
    }

    public final void n(boolean z6) {
        byte[] bArr;
        Drawable drawable;
        W w10 = this.f12024D;
        boolean zF = false;
        boolean z10 = (w10 == null || !w10.R0(30) || w10.J().f14226a.isEmpty()) ? false : true;
        boolean z11 = this.f12031K;
        ImageView imageView = this.f12043s;
        View view = this.f12039n;
        if (!z11 && (!z10 || z6)) {
            if (imageView != null) {
                imageView.setImageResource(android.R.color.transparent);
                imageView.setVisibility(4);
            }
            if (view != null) {
                view.setVisibility(0);
            }
            c();
        }
        if (z10) {
            W w11 = this.f12024D;
            boolean z12 = w11 != null && w11.R0(30) && w11.J().b(2);
            boolean zB = b();
            if (!z12 && !zB) {
                if (view != null) {
                    view.setVisibility(0);
                }
                c();
            }
            ImageView imageView2 = this.f12042r;
            boolean z13 = (view == null || view.getVisibility() != 4 || imageView2 == null || (drawable = imageView2.getDrawable()) == null || drawable.getAlpha() == 0) ? false : true;
            if (zB && !z12 && z13) {
                if (view != null) {
                    view.setVisibility(0);
                }
                if (imageView2 != null) {
                    imageView2.setVisibility(0);
                    o();
                }
            } else if (z12 && !zB && z13) {
                c();
            }
            if (!z12 && !zB && this.f12027G != 0) {
                AbstractC1346c.k(imageView);
                if (w10 != null && w10.R0(18) && (bArr = w10.K0().f13967k) != null) {
                    zF = f(new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(bArr, 0, bArr.length)));
                }
                if (zF || f(this.f12029I)) {
                    return;
                }
            }
            if (imageView != null) {
                imageView.setImageResource(android.R.color.transparent);
                imageView.setVisibility(4);
            }
        }
    }

    public final void o() {
        Drawable drawable;
        AspectRatioFrameLayout aspectRatioFrameLayout;
        ImageView imageView = this.f12042r;
        if (imageView == null || (drawable = imageView.getDrawable()) == null) {
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            return;
        }
        float width = intrinsicWidth / intrinsicHeight;
        ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;
        if (this.f12028H == 1) {
            width = getWidth() / getHeight();
            scaleType = ImageView.ScaleType.CENTER_CROP;
        }
        if (imageView.getVisibility() == 0 && (aspectRatioFrameLayout = this.f12038m) != null) {
            aspectRatioFrameLayout.setAspectRatio(width);
        }
        imageView.setScaleType(scaleType);
    }

    @Override // android.view.View
    public final boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!p() || this.f12024D == null) {
            return false;
        }
        e(true);
        return true;
    }

    public final boolean p() {
        if (!this.f12025E) {
            return false;
        }
        AbstractC1346c.k(this.f12047w);
        return true;
    }

    @Override // android.view.View
    public final boolean performClick() {
        i();
        return super.performClick();
    }

    public void setArtworkDisplayMode(int i7) {
        AbstractC1346c.j(i7 == 0 || this.f12043s != null);
        if (this.f12027G != i7) {
            this.f12027G = i7;
            n(false);
        }
    }

    public void setAspectRatioListener(InterfaceC1801a interfaceC1801a) {
        AspectRatioFrameLayout aspectRatioFrameLayout = this.f12038m;
        AbstractC1346c.k(aspectRatioFrameLayout);
        aspectRatioFrameLayout.setAspectRatioListener(interfaceC1801a);
    }

    public void setControllerAnimationEnabled(boolean z6) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.setAnimationEnabled(z6);
    }

    public void setControllerAutoShow(boolean z6) {
        this.f12034N = z6;
    }

    public void setControllerHideDuringAds(boolean z6) {
        this.f12035O = z6;
    }

    public void setControllerHideOnTouch(boolean z6) {
        AbstractC1346c.k(this.f12047w);
        this.f12036P = z6;
        l();
    }

    @Deprecated
    public void setControllerOnFullScreenModeChangedListener(InterfaceC1809i interfaceC1809i) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.setOnFullScreenModeChangedListener(interfaceC1809i);
    }

    public void setControllerShowTimeoutMs(int i7) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        this.f12033M = i7;
        if (sVar.g()) {
            h(g());
        }
    }

    public void setControllerVisibilityListener(InterfaceC1795A interfaceC1795A) {
        if (interfaceC1795A != null) {
            setControllerVisibilityListener((r) null);
        }
    }

    public void setCustomErrorMessage(CharSequence charSequence) {
        AbstractC1346c.j(this.f12046v != null);
        this.f12032L = charSequence;
        m();
    }

    public void setDefaultArtwork(Drawable drawable) {
        if (this.f12029I != drawable) {
            this.f12029I = drawable;
            n(false);
        }
    }

    public void setEnableComposeSurfaceSyncWorkaround(boolean z6) {
        this.f12037Q = z6;
    }

    public void setErrorMessageProvider(InterfaceC1205m interfaceC1205m) {
        if (interfaceC1205m != null) {
            m();
        }
    }

    public void setFullscreenButtonClickListener(InterfaceC1796B interfaceC1796B) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.setOnFullScreenModeChangedListener(this.l);
    }

    public void setFullscreenButtonState(boolean z6) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.k(z6);
    }

    public void setImageDisplayMode(int i7) {
        AbstractC1346c.j(this.f12042r != null);
        if (this.f12028H != i7) {
            this.f12028H = i7;
            o();
        }
    }

    public void setKeepContentOnPlayerReset(boolean z6) {
        if (this.f12031K != z6) {
            this.f12031K = z6;
            n(false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x00ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setPlayer(e2.W r11) {
        /*
            Method dump skipped, instruction units count: 277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.ui.PlayerView.setPlayer(e2.W):void");
    }

    public void setRepeatToggleModes(int i7) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.setRepeatToggleModes(i7);
    }

    public void setResizeMode(int i7) {
        AspectRatioFrameLayout aspectRatioFrameLayout = this.f12038m;
        AbstractC1346c.k(aspectRatioFrameLayout);
        aspectRatioFrameLayout.setResizeMode(i7);
    }

    public void setShowBuffering(int i7) {
        if (this.f12030J != i7) {
            this.f12030J = i7;
            k();
        }
    }

    public void setShowFastForwardButton(boolean z6) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.setShowFastForwardButton(z6);
    }

    @Deprecated
    public void setShowMultiWindowTimeBar(boolean z6) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.setShowMultiWindowTimeBar(z6);
    }

    public void setShowNextButton(boolean z6) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.setShowNextButton(z6);
    }

    public void setShowPlayButtonIfPlaybackIsSuppressed(boolean z6) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.setShowPlayButtonIfPlaybackIsSuppressed(z6);
    }

    public void setShowPreviousButton(boolean z6) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.setShowPreviousButton(z6);
    }

    public void setShowRewindButton(boolean z6) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.setShowRewindButton(z6);
    }

    public void setShowShuffleButton(boolean z6) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.setShowShuffleButton(z6);
    }

    public void setShowSubtitleButton(boolean z6) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.setShowSubtitleButton(z6);
    }

    public void setShowVrButton(boolean z6) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        sVar.setShowVrButton(z6);
    }

    public void setShutterBackgroundColor(int i7) {
        View view = this.f12039n;
        if (view != null) {
            view.setBackgroundColor(i7);
        }
    }

    @Deprecated
    public void setUseArtwork(boolean z6) {
        setArtworkDisplayMode(!z6 ? 1 : 0);
    }

    public void setUseController(boolean z6) {
        boolean z10 = true;
        s sVar = this.f12047w;
        AbstractC1346c.j((z6 && sVar == null) ? false : true);
        if (!z6 && !hasOnClickListeners()) {
            z10 = false;
        }
        setClickable(z10);
        if (this.f12025E == z6) {
            return;
        }
        this.f12025E = z6;
        if (p()) {
            sVar.setPlayer(this.f12024D);
        } else if (sVar != null) {
            sVar.f();
            sVar.setPlayer(null);
        }
        l();
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        View view = this.o;
        if (view instanceof SurfaceView) {
            view.setVisibility(i7);
        }
    }

    @Deprecated
    public void setControllerVisibilityListener(r rVar) {
        s sVar = this.f12047w;
        AbstractC1346c.k(sVar);
        r rVar2 = this.f12026F;
        if (rVar2 == rVar) {
            return;
        }
        CopyOnWriteArrayList copyOnWriteArrayList = sVar.o;
        if (rVar2 != null) {
            copyOnWriteArrayList.remove(rVar2);
        }
        this.f12026F = rVar;
        if (rVar != null) {
            copyOnWriteArrayList.add(rVar);
            setControllerVisibilityListener((InterfaceC1795A) null);
        }
    }
}
