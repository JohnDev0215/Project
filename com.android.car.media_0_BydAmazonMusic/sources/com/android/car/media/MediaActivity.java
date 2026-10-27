package com.android.car.media;

import android.annotation.SuppressLint;
import android.app.Application;
import android.car.XCar;
import android.car.XCarRoute;
import android.car.content.pm.XCarPackageManager;
import android.car.drivingstate.IXCarUxRestrictionsManager;
import android.car.media.XCarMediaIntents;
import android.car.media.XCarMediaManager;
import android.car.test.ITestViewControl;
import android.car.utils.Logs;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.car.media.MediaActivity;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.MinimizedPlaybackControlBar;
import com.android.car.media.common.PlaybackControlsActionBar;
import com.android.car.media.common.browse.MediaItemsRepository;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.android.car.media.common.source.MediaSourceViewModel;
import com.android.car.media.common.source.MediaTrampolineHelper;
import com.android.car.media.d;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import org.greenrobot.eventbus.ThreadMode;
import spi.a1;
import spi.b2;
import spi.b7;
import spi.d4;
import spi.e2;
import spi.e6;
import spi.i2;
import spi.j8;
import spi.ja;
import spi.l0;
import spi.m;
import spi.o3;
import spi.oc;
import spi.q6;
import spi.r3;
import spi.s0;
import spi.s6;
import spi.uc;
import spi.vc;
import spi.wc;
import spi.xc;
import spi.z0;

/* JADX INFO: loaded from: classes.dex */
public class MediaActivity extends FragmentActivity implements d.g {
    public static final /* synthetic */ int w = 0;
    public int a;
    public PlaybackViewModel.d b;
    public PlaybackFragment c;
    public com.android.car.media.d d;
    public MinimizedPlaybackControlBar e;
    public ViewGroup f;
    public ViewGroup g;
    public ViewGroup h;
    public com.android.car.media.c i;
    public Toast j;
    public l0 k;
    public d l;
    public boolean m;
    public XCar n;
    public XCarPackageManager o;
    public float p;
    public float q;
    public float r;
    public MediaTrampolineHelper t;
    public final q6 s = new q6(this);
    public ITestViewControl u = null;
    public final a v = new a(this);

    public static class ViewModel extends AndroidViewModel {
        public boolean a;
        public PlaybackViewModel b;
        public final MutableLiveData<d4<b7>> c;
        public final Map<b7, a> d;
        public MutableLiveData<Boolean> e;

        public static class a {
            public boolean d;
            public boolean e;
            public String f;
            public d a = d.BROWSING;
            public Stack<MediaItemMetadata> b = new Stack<>();
            public Stack<MediaItemMetadata> c = new Stack<>();
            public boolean g = false;
            public int h = 0;
        }

        public ViewModel(@NonNull Application application) {
            super(application);
            this.a = true;
            this.c = e6.a(new d4(true, null));
            this.d = new HashMap();
            this.e = new MutableLiveData<>();
        }

        @Nullable
        public final b7 a() {
            d4<b7> value = this.c.getValue();
            return value != null ? value.c : null;
        }

        public final d b() {
            return c().a;
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [java.util.HashMap, java.util.Map<spi.b7, com.android.car.media.MediaActivity$ViewModel$a>] */
        /* JADX WARN: Type inference failed for: r2v1, types: [java.util.HashMap, java.util.Map<spi.b7, com.android.car.media.MediaActivity$ViewModel$a>] */
        public final a c() {
            b7 b7VarA = a();
            a aVar = (a) this.d.get(b7VarA);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            this.d.put(b7VarA, aVar2);
            return aVar2;
        }

        @Nullable
        public final MediaItemMetadata d() {
            Stack<MediaItemMetadata> stack = c().b;
            if (stack == null || stack.empty()) {
                return null;
            }
            return stack.firstElement();
        }

        public final boolean e() {
            return c().d;
        }

        public final boolean f() {
            return c().e;
        }

        public final void g(int i) {
            c().h = i;
        }
    }

    public class a extends j8 {
        public a(Context context) {
            super(context);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x007e  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0085  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00d3  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00db  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x011d  */
        @Override // spi.j8
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void b(java.lang.String r9, android.app.PendingIntent r10, java.lang.String r11, int r12) {
            /*
                Method dump skipped, instruction units count: 330
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.car.media.MediaActivity.a.b(java.lang.String, android.app.PendingIntent, java.lang.String, int):void");
        }

        @Override // spi.j8
        public final void c(@NonNull String str, PlaybackViewModel.e eVar, boolean z) {
            MediaActivity mediaActivity = MediaActivity.this;
            boolean z2 = false;
            if (eVar != null) {
                if (eVar.b == null) {
                    Logs.e("PlaybackViewModel", "mMetadata is null !!!");
                }
                if ((eVar.c.getState() == 0 || eVar.b == null || eVar.b() == 0) ? false : true) {
                    z2 = true;
                }
            }
            mediaActivity.m = z2;
            MediaActivity.this.h(true);
            super.c(str, eVar, z);
        }
    }

    public static /* synthetic */ class b {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[d.values().length];
            a = iArr;
            try {
                iArr[d.PLAYBACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[d.BROWSING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[d.FATAL_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public class c extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {
        public final ViewConfiguration a;
        public final GestureDetectorCompat b;

        public c(Context context) {
            this.a = ViewConfiguration.get(context);
            this.b = new GestureDetectorCompat(context, this);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onDown(MotionEvent motionEvent) {
            MediaActivity mediaActivity = MediaActivity.this;
            return mediaActivity.l == d.PLAYBACK && mediaActivity.r > 1.0E-4f;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            float x = motionEvent2.getX() - motionEvent.getX();
            float y = motionEvent2.getY() - motionEvent.getY();
            float fA = oc.a(x, y);
            if (fA <= this.a.getScaledTouchSlop() || oc.a(f, f2) <= this.a.getScaledMinimumFlingVelocity()) {
                return true;
            }
            MediaActivity mediaActivity = MediaActivity.this;
            if (((mediaActivity.q * y) + (mediaActivity.p * x)) / (mediaActivity.r * fA) < 0.866f) {
                return true;
            }
            mediaActivity.a(d.BROWSING);
            return true;
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            return this.b.onTouchEvent(motionEvent);
        }
    }

    public enum d {
        BROWSING,
        PLAYBACK,
        FATAL_ERROR
    }

    public final void a(d dVar) {
        if (this.l != dVar) {
            b(dVar, true, false);
            return;
        }
        StringBuilder sbK = spi.c.k("Mode ");
        sbK.append(this.l);
        sbK.append(" change is ignored");
        Logs.i("MediaActivity", sbK.toString());
    }

    public final void b(d dVar, boolean z, boolean z2) {
        float f;
        StringBuilder sbK = spi.c.k("Changing mode from: ");
        sbK.append(this.l);
        sbK.append(" to: ");
        sbK.append(dVar);
        Logs.i("MediaActivity", sbK.toString());
        int i = z ? this.a : 0;
        d dVar2 = this.l;
        c().c().a = dVar;
        this.l = dVar;
        PlaybackControlsActionBar playbackControlsActionBar = this.c.f;
        if (playbackControlsActionBar != null && playbackControlsActionBar.h) {
            playbackControlsActionBar.h();
        }
        h(z);
        int i2 = b.a[this.l.ordinal()];
        float f2 = 0.0f;
        if (i2 == 1) {
            this.g.setX(0.0f);
            this.g.setY(0.0f);
            this.g.setAlpha(0.0f);
            uc.b(this.h, i, null);
            if (z2) {
                uc.g(this.g, i, null);
            } else {
                uc.g(this.g, this.a, null);
            }
            uc.b(this.f, 0, null);
            return;
        }
        if (i2 != 2) {
            if (i2 != 3) {
                return;
            }
            uc.g(this.h, this.a, null);
            uc.b(this.g, i, null);
            uc.b(this.f, i, null);
            return;
        }
        if (dVar2 != d.PLAYBACK) {
            uc.b(this.h, i, null);
            uc.b(this.g, i, null);
            uc.g(this.f, this.a, null);
            return;
        }
        this.d.l();
        uc.b(this.h, 0, null);
        if (z2) {
            uc.g(this.f, i, null);
        } else {
            uc.g(this.f, 0, null);
        }
        if (this.r <= 1.0E-4f) {
            uc.b(this.g, i, null);
        } else {
            float width = this.g.getWidth() + 1;
            float height = this.g.getHeight() + 1;
            if (Math.abs(this.q) <= 1.0E-4f) {
                float f3 = (this.p * width) / this.r;
                f = 0.0f;
                f2 = f3;
            } else if (Math.abs(this.p) <= 1.0E-4f) {
                f = (this.q * height) / this.r;
            } else {
                Logs.d("MediaActivity", "The vector to close the playback container must be vertical or horizontal");
                uc.b(this.g, i, null);
            }
            this.g.animate().translationX(f2).translationY(f).setDuration(i).setListener(new vc(this.g)).start();
        }
        c().c().g = false;
    }

    public final ViewModel c() {
        return (ViewModel) ViewModelProviders.of(this).get(ViewModel.class);
    }

    public final PlaybackViewModel d() {
        return PlaybackViewModel.a(getApplication(), 1);
    }

    public final boolean e() {
        return e2.b(64, e2.a(this).a);
    }

    public final void f(boolean z) {
        Logs.e("MediaActivity", "needRegisterEventbus enabled= " + z);
        if (z) {
            o3.b().j(this);
        } else {
            o3.b().l(this);
        }
    }

    public final void g() {
        String[] strArr = s0.b;
        final int i = 1;
        s0.b.a.a = true;
        ((IXCarUxRestrictionsManager) ARouter.getInstance().build(XCarRoute.CAR_UXR_SERVICE).navigation()).initGearBox();
        Resources resources = getResources();
        this.p = resources.getFloat(com.android.car.media.main.R$dimen.media_activity_close_vector_x);
        float f = resources.getFloat(com.android.car.media.main.R$dimen.media_activity_close_vector_y);
        this.q = f;
        this.r = oc.a(this.p, f);
        PlaybackViewModel playbackViewModelD = d();
        ViewModel viewModelC = c();
        final int i2 = 0;
        if (viewModelC.a && viewModelC.b != playbackViewModelD) {
            viewModelC.b = playbackViewModelD;
            viewModelC.a = false;
        }
        this.l = viewModelC.b();
        viewModelC.c.observe(this, new Observer(this) { // from class: spi.p6
            public final /* synthetic */ MediaActivity b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i2) {
                    case 0:
                        MediaActivity mediaActivity = this.b;
                        d4 d4Var = (d4) obj;
                        int i3 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity);
                        b7 b7Var = (b7) (d4Var != null ? d4Var.c : null);
                        b7 b7Var2 = (b7) (d4Var != null ? d4Var.b : null);
                        com.android.car.media.c cVar = mediaActivity.i;
                        if (cVar != null) {
                            cVar.a(b7Var);
                        }
                        Toast toast = mediaActivity.j;
                        if (toast != null) {
                            toast.cancel();
                            mediaActivity.j = null;
                        }
                        l0 l0Var = mediaActivity.k;
                        if (l0Var != null) {
                            l0Var.cancel();
                            mediaActivity.k = null;
                        }
                        if (b7Var != null) {
                            StringBuilder sbK = c.k("Browsing: ");
                            sbK.append((Object) b7Var.b);
                            Logs.i("MediaActivity", sbK.toString());
                            if (!Objects.equals(b7Var2, b7Var)) {
                                mediaActivity.b(MediaActivity.d.BROWSING, false, false);
                            } else {
                                mediaActivity.b(mediaActivity.c().b(), false, false);
                            }
                        }
                        break;
                    case 1:
                        MediaActivity mediaActivity2 = this.b;
                        PlaybackViewModel.d dVar = (PlaybackViewModel.d) obj;
                        int i4 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity2);
                        if (dVar != null) {
                            dVar.d();
                        }
                        mediaActivity2.b = dVar;
                        break;
                    case 2:
                        this.b.v.c("MediaActivity", (PlaybackViewModel.e) obj, true);
                        break;
                    default:
                        MediaActivity mediaActivity3 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i5 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity3);
                        if (bool != null && bool.booleanValue()) {
                            MediaActivity.d dVar2 = mediaActivity3.l;
                            MediaActivity.d dVar3 = MediaActivity.d.PLAYBACK;
                            if (dVar2 != dVar3) {
                                mediaActivity3.b(dVar3, false, true);
                            }
                        }
                        break;
                }
            }
        });
        this.t = new MediaTrampolineHelper(this);
        PlaybackFragment playbackFragment = new PlaybackFragment();
        this.c = playbackFragment;
        playbackFragment.s = this.s;
        Size sizeA = s6.a(this);
        this.e = (MinimizedPlaybackControlBar) findViewById(com.android.car.media.main.R$id.minimized_playback_controls);
        StringBuilder sbK = spi.c.k("setPlaybackBarBg bg_linear_progress_bar_fancy BydUiStyleConfig");
        sbK.append(a1.a);
        Logs.d("MediaActivity", sbK.toString());
        if (a1.a()) {
            this.e.setPlaybackBarBg(getDrawable(com.android.car.media.main.R$drawable.minimized_control_bar_background_denza));
        } else {
            int i3 = a1.a;
            this.e.setPlaybackBarBg(getDrawable(com.android.car.media.main.R$drawable.minimized_control_bar_background));
        }
        this.e.f(playbackViewModelD, this, sizeA);
        this.e.setOnClickListener(new View.OnClickListener(this) { // from class: spi.o6
            public final /* synthetic */ MediaActivity b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i2) {
                    case 0:
                        MediaActivity mediaActivity = this.b;
                        int i4 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity);
                        mediaActivity.a(MediaActivity.d.PLAYBACK);
                        break;
                    default:
                        MediaActivity mediaActivity2 = this.b;
                        if (mediaActivity2.u == null) {
                            mediaActivity2.u = (ITestViewControl) ARouter.getInstance().build(XCarRoute.CAR_TEST_SETTING).navigation();
                        }
                        mediaActivity2.u.onClicked(mediaActivity2);
                        break;
                }
            }
        });
        this.a = resources.getInteger(com.android.car.media.main.R$integer.new_album_art_fade_in_duration);
        this.f = (ViewGroup) findViewById(com.android.car.media.main.R$id.fragment_container);
        if (a1.a()) {
            this.f.setBackgroundResource(com.android.car.media.main.R$color.bg_main_denza);
        } else {
            int i4 = a1.a;
            this.f.setBackgroundResource(com.android.car.media.main.R$drawable.bg_main);
        }
        this.h = (ViewGroup) findViewById(com.android.car.media.main.R$id.error_container);
        int i5 = com.android.car.media.main.R$id.playback_container;
        this.g = (ViewGroup) findViewById(i5);
        getSupportFragmentManager().beginTransaction().replace(i5, this.c).commit();
        playbackViewModelD.i.observe(this, new Observer(this) { // from class: spi.p6
            public final /* synthetic */ MediaActivity b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i) {
                    case 0:
                        MediaActivity mediaActivity = this.b;
                        d4 d4Var = (d4) obj;
                        int i32 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity);
                        b7 b7Var = (b7) (d4Var != null ? d4Var.c : null);
                        b7 b7Var2 = (b7) (d4Var != null ? d4Var.b : null);
                        com.android.car.media.c cVar = mediaActivity.i;
                        if (cVar != null) {
                            cVar.a(b7Var);
                        }
                        Toast toast = mediaActivity.j;
                        if (toast != null) {
                            toast.cancel();
                            mediaActivity.j = null;
                        }
                        l0 l0Var = mediaActivity.k;
                        if (l0Var != null) {
                            l0Var.cancel();
                            mediaActivity.k = null;
                        }
                        if (b7Var != null) {
                            StringBuilder sbK2 = c.k("Browsing: ");
                            sbK2.append((Object) b7Var.b);
                            Logs.i("MediaActivity", sbK2.toString());
                            if (!Objects.equals(b7Var2, b7Var)) {
                                mediaActivity.b(MediaActivity.d.BROWSING, false, false);
                            } else {
                                mediaActivity.b(mediaActivity.c().b(), false, false);
                            }
                        }
                        break;
                    case 1:
                        MediaActivity mediaActivity2 = this.b;
                        PlaybackViewModel.d dVar = (PlaybackViewModel.d) obj;
                        int i42 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity2);
                        if (dVar != null) {
                            dVar.d();
                        }
                        mediaActivity2.b = dVar;
                        break;
                    case 2:
                        this.b.v.c("MediaActivity", (PlaybackViewModel.e) obj, true);
                        break;
                    default:
                        MediaActivity mediaActivity3 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i52 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity3);
                        if (bool != null && bool.booleanValue()) {
                            MediaActivity.d dVar2 = mediaActivity3.l;
                            MediaActivity.d dVar3 = MediaActivity.d.PLAYBACK;
                            if (dVar2 != dVar3) {
                                mediaActivity3.b(dVar3, false, true);
                            }
                        }
                        break;
                }
            }
        });
        XCar xCarCreateCar = XCar.createCar(this);
        this.n = xCarCreateCar;
        this.o = (XCarPackageManager) xCarCreateCar.getCarManager(XCar.PACKAGE_SERVICE);
        this.d = new com.android.car.media.d(this, MediaItemsRepository.a(getApplication(), 1), this.o, this.f);
        this.g.setOnTouchListener(new c(this));
        findViewById(com.android.car.media.main.R$id.car_ui_toolbar_logo_icon_container).setOnClickListener(new View.OnClickListener(this) { // from class: spi.o6
            public final /* synthetic */ MediaActivity b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i) {
                    case 0:
                        MediaActivity mediaActivity = this.b;
                        int i42 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity);
                        mediaActivity.a(MediaActivity.d.PLAYBACK);
                        break;
                    default:
                        MediaActivity mediaActivity2 = this.b;
                        if (mediaActivity2.u == null) {
                            mediaActivity2.u = (ITestViewControl) ARouter.getInstance().build(XCarRoute.CAR_TEST_SETTING).navigation();
                        }
                        mediaActivity2.u.onClicked(mediaActivity2);
                        break;
                }
            }
        });
        MutableLiveData<PlaybackViewModel.e> mutableLiveData = playbackViewModelD.j;
        final int i6 = 2;
        mutableLiveData.observe(this, new Observer(this) { // from class: spi.p6
            public final /* synthetic */ MediaActivity b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i6) {
                    case 0:
                        MediaActivity mediaActivity = this.b;
                        d4 d4Var = (d4) obj;
                        int i32 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity);
                        b7 b7Var = (b7) (d4Var != null ? d4Var.c : null);
                        b7 b7Var2 = (b7) (d4Var != null ? d4Var.b : null);
                        com.android.car.media.c cVar = mediaActivity.i;
                        if (cVar != null) {
                            cVar.a(b7Var);
                        }
                        Toast toast = mediaActivity.j;
                        if (toast != null) {
                            toast.cancel();
                            mediaActivity.j = null;
                        }
                        l0 l0Var = mediaActivity.k;
                        if (l0Var != null) {
                            l0Var.cancel();
                            mediaActivity.k = null;
                        }
                        if (b7Var != null) {
                            StringBuilder sbK2 = c.k("Browsing: ");
                            sbK2.append((Object) b7Var.b);
                            Logs.i("MediaActivity", sbK2.toString());
                            if (!Objects.equals(b7Var2, b7Var)) {
                                mediaActivity.b(MediaActivity.d.BROWSING, false, false);
                            } else {
                                mediaActivity.b(mediaActivity.c().b(), false, false);
                            }
                        }
                        break;
                    case 1:
                        MediaActivity mediaActivity2 = this.b;
                        PlaybackViewModel.d dVar = (PlaybackViewModel.d) obj;
                        int i42 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity2);
                        if (dVar != null) {
                            dVar.d();
                        }
                        mediaActivity2.b = dVar;
                        break;
                    case 2:
                        this.b.v.c("MediaActivity", (PlaybackViewModel.e) obj, true);
                        break;
                    default:
                        MediaActivity mediaActivity3 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i52 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity3);
                        if (bool != null && bool.booleanValue()) {
                            MediaActivity.d dVar2 = mediaActivity3.l;
                            MediaActivity.d dVar3 = MediaActivity.d.PLAYBACK;
                            if (dVar2 != dVar3) {
                                mediaActivity3.b(dVar3, false, true);
                            }
                        }
                        break;
                }
            }
        });
        final int i7 = 3;
        d().k.observe(this, new Observer(this) { // from class: spi.p6
            public final /* synthetic */ MediaActivity b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i7) {
                    case 0:
                        MediaActivity mediaActivity = this.b;
                        d4 d4Var = (d4) obj;
                        int i32 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity);
                        b7 b7Var = (b7) (d4Var != null ? d4Var.c : null);
                        b7 b7Var2 = (b7) (d4Var != null ? d4Var.b : null);
                        com.android.car.media.c cVar = mediaActivity.i;
                        if (cVar != null) {
                            cVar.a(b7Var);
                        }
                        Toast toast = mediaActivity.j;
                        if (toast != null) {
                            toast.cancel();
                            mediaActivity.j = null;
                        }
                        l0 l0Var = mediaActivity.k;
                        if (l0Var != null) {
                            l0Var.cancel();
                            mediaActivity.k = null;
                        }
                        if (b7Var != null) {
                            StringBuilder sbK2 = c.k("Browsing: ");
                            sbK2.append((Object) b7Var.b);
                            Logs.i("MediaActivity", sbK2.toString());
                            if (!Objects.equals(b7Var2, b7Var)) {
                                mediaActivity.b(MediaActivity.d.BROWSING, false, false);
                            } else {
                                mediaActivity.b(mediaActivity.c().b(), false, false);
                            }
                        }
                        break;
                    case 1:
                        MediaActivity mediaActivity2 = this.b;
                        PlaybackViewModel.d dVar = (PlaybackViewModel.d) obj;
                        int i42 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity2);
                        if (dVar != null) {
                            dVar.d();
                        }
                        mediaActivity2.b = dVar;
                        break;
                    case 2:
                        this.b.v.c("MediaActivity", (PlaybackViewModel.e) obj, true);
                        break;
                    default:
                        MediaActivity mediaActivity3 = this.b;
                        Boolean bool = (Boolean) obj;
                        int i52 = MediaActivity.w;
                        Objects.requireNonNull(mediaActivity3);
                        if (bool != null && bool.booleanValue()) {
                            MediaActivity.d dVar2 = mediaActivity3.l;
                            MediaActivity.d dVar3 = MediaActivity.d.PLAYBACK;
                            if (dVar2 != dVar3) {
                                mediaActivity3.b(dVar3, false, true);
                            }
                        }
                        break;
                }
            }
        });
    }

    public final void h(boolean z) {
        boolean z2 = false;
        int i = z ? this.a : 0;
        if (getResources().getBoolean(com.android.car.media.main.R$bool.show_mini_playback_controls) && this.m && this.l != d.PLAYBACK) {
            z2 = true;
        }
        if (!z2) {
            MinimizedPlaybackControlBar minimizedPlaybackControlBar = this.e;
            minimizedPlaybackControlBar.animate().cancel();
            if (minimizedPlaybackControlBar.isLaidOut()) {
                minimizedPlaybackControlBar.animate().setDuration(i).setListener(new xc(new wc(minimizedPlaybackControlBar), null, minimizedPlaybackControlBar)).alpha(0.0f);
            } else {
                minimizedPlaybackControlBar.setVisibility(4);
            }
        } else if (c().e.getValue() != Boolean.TRUE) {
            uc.g(this.e, this.a, null);
        }
        c().e.setValue(Boolean.valueOf(z2));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onBackPressed() {
        boolean z;
        int i = b.a[this.l.ordinal()];
        if (i == 1) {
            PlaybackFragment playbackFragment = this.c;
            if (playbackFragment.w && playbackFragment.E.c().g) {
                playbackFragment.g(false);
                return;
            }
            MediaActivity mediaActivity = (MediaActivity) ((q6) playbackFragment.s).a;
            Objects.requireNonNull(mediaActivity);
            mediaActivity.a(d.BROWSING);
            return;
        }
        if (i != 2) {
            super.onBackPressed();
            return;
        }
        com.android.car.media.d dVar = this.d;
        if (dVar.j()) {
            z = false;
        } else {
            dVar.i(dVar.h().pop());
            dVar.m(true);
            if (dVar.j() && dVar.u.e()) {
                dVar.p(true);
            }
            dVar.q();
            z = true;
        }
        if (z || !dVar.u.e()) {
            z = z;
        } else {
            dVar.o(false);
            dVar.q();
        }
        if (z) {
            dVar.l();
            try {
                if (dVar.A.hasCallbacks(dVar.B)) {
                    dVar.A.removeCallbacks(dVar.B);
                }
                if (dVar.A.hasCallbacks(dVar.C)) {
                    dVar.A.removeCallbacks(dVar.C);
                }
                dVar.A.post(dVar.C);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (z) {
            return;
        }
        super.onBackPressed();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(com.android.car.media.main.R$layout.media_activity);
        Logs.w("MediaActivity", "onCreate");
        String strM = b2.m(BYDAutoSysPropertiesDevice.COUNTRY_CODE);
        if (strM != null) {
            Logs.w("MediaActivity", "countrycode is: " + strM);
        } else {
            Logs.w("MediaActivity", "countrycode not found");
        }
        if (i2.a(this)) {
            return;
        }
        String[] strArr = s0.b;
        s0 s0Var = s0.b.a;
        if (s0Var.a || s0Var.b(this, false).length == 0) {
            g();
        }
        f(true);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        XCar xCar = this.n;
        if (xCar != null) {
            xCar.disconnect();
        }
        com.android.car.media.d dVar = this.d;
        if (dVar != null) {
            dVar.i.c.removeObserver(dVar.E);
            try {
                if (dVar.A.hasCallbacks(dVar.B)) {
                    dVar.A.removeCallbacks(dVar.B);
                    dVar.A.removeCallbacksAndMessages(null);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                if (dVar.A.hasCallbacks(dVar.C)) {
                    dVar.A.removeCallbacks(dVar.C);
                    dVar.A.removeCallbacksAndMessages(null);
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
        ITestViewControl iTestViewControl = this.u;
        if (iTestViewControl != null) {
            iTestViewControl.hide();
        }
        super.onDestroy();
        f(false);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Logs.d("MediaActivity", "onNewIntent: " + intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 123 || iArr.length <= 0) {
            return;
        }
        String[] strArr2 = s0.b;
        if (s0.b.a.b(this, true).length == 0) {
            Logs.w("MediaActivity", "grantResults: permissionGranted");
            g();
        } else {
            StringBuilder sbK = spi.c.k("grantResults===false: ");
            sbK.append(iArr[0]);
            Logs.w("MediaActivity", sbK.toString());
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        super.onResume();
        com.android.car.media.d dVar = this.d;
        if (dVar != null) {
            dVar.t(c().e());
        }
        if (i2.a(this)) {
            return;
        }
        int i = z0.g;
        Logs.d("MediaActivity", "BydTrafficNoticeDialog.isShow:false");
        String[] strArr = s0.b;
        s0 s0Var = s0.b.a;
        if (!(s0Var.a || s0Var.b(this, false).length == 0)) {
            ActivityCompat.requestPermissions(this, s0Var.b(this, false), 123);
            return;
        }
        Intent intent = getIntent();
        Logs.d("MediaActivity", "onResume intent: " + intent);
        if (intent != null) {
            String stringExtra = getIntent().getStringExtra(XCarMediaIntents.EXTRA_MEDIA_COMPONENT);
            ComponentName componentNameUnflattenFromString = stringExtra == null ? null : ComponentName.unflattenFromString(stringExtra);
            if (componentNameUnflattenFromString == null) {
                Logs.w("MediaActivity", "launchedSourceComp should almost never be null: " + stringExtra);
            }
            MediaTrampolineHelper mediaTrampolineHelper = this.t;
            if (mediaTrampolineHelper != null) {
                if (componentNameUnflattenFromString != null) {
                    mediaTrampolineHelper.c = b7.a(mediaTrampolineHelper.a, componentNameUnflattenFromString);
                }
                b7 b7Var = mediaTrampolineHelper.c;
                if (b7Var != null) {
                    MediaSourceViewModel mediaSourceViewModel = mediaTrampolineHelper.b;
                    if (mediaSourceViewModel.g == 1) {
                        mediaSourceViewModel.b(b7Var);
                    } else if (Log.isLoggable("MediaSourceViewModel", 5)) {
                        StringBuilder sbL = spi.c.l("Inconsistent media source mode ", 1, " mMode: ");
                        sbL.append(mediaSourceViewModel.g);
                        Log.w("MediaSourceViewModel", sbL.toString());
                    }
                    XCarMediaManager xCarMediaManager = mediaSourceViewModel.b;
                    if (xCarMediaManager != null) {
                        xCarMediaManager.setMediaSource(b7Var.a, 1);
                    } else {
                        Log.e("MediaSourceViewModel", "mCarMediaManager == null !!!");
                    }
                } else {
                    Log.e("MediaTrampolineHlp", "Null mLaunchedSource!!!");
                }
                if (Log.isLoggable("MediaTrampolineHlp", 3)) {
                    b7 value = mediaTrampolineHelper.b.c.getValue();
                    StringBuilder sbK = spi.c.k("Launched: ");
                    sbK.append(mediaTrampolineHelper.c);
                    sbK.append(" current: ");
                    sbK.append(value);
                    Log.d("MediaTrampolineHlp", sbK.toString());
                }
            }
            setIntent(null);
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.Map<java.lang.Class<?>, java.lang.Object>, java.util.concurrent.ConcurrentHashMap] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.util.Map<java.lang.Class<?>, java.lang.Object>, java.util.concurrent.ConcurrentHashMap] */
    @ja(sticky = true, threadMode = ThreadMode.MAIN)
    public void receiveEventString(r3 r3Var) {
        if (r3Var != null && "CMD_VOICE_PLAY_BY_VOID".equals(r3Var.a)) {
            Logs.e("voice_bus_cswp", "CMD_VOICE_PLAY_BY_VOID");
            String[] strArrSplit = r3Var.b.split(",");
            if (strArrSplit.length == 2) {
                String str = strArrSplit[0];
                String str2 = strArrSplit[1];
                Logs.e("onVoiceSearch", "CMD_VOICE_PLAY_BY_VOIDquery = " + str + " filter = " + str2);
                b(d.BROWSING, true, true);
                com.android.car.media.d dVar = this.d;
                dVar.r.r = str2;
                m mVar = dVar.f;
                mVar.l = true;
                mVar.f.e(mVar.k);
                mVar.c.setSearchQuery(str);
                mVar.f.b();
                dVar.u.c().f = str;
                dVar.r(str);
            }
        }
        o3 o3VarB = o3.b();
        synchronized (o3VarB.c) {
            Class<?> cls = r3Var.getClass();
            if (r3Var.equals(o3VarB.c.get(cls))) {
                o3VarB.c.remove(cls);
            }
        }
    }
}
