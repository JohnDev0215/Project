package m3;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.TsNormalNum;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.stingray.drivemodedetector.R;
import d5.AbstractC1074C;
import d5.AbstractC1095q;
import d5.d0;
import e2.C1208p;
import e2.P;
import e2.W;
import e2.c0;
import e2.e0;
import e2.f0;
import e2.l0;
import e2.m0;
import e2.n0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import s3.AbstractC2405y;
import x1.AbstractC2671j;

/* JADX INFO: loaded from: classes.dex */
public final class s extends FrameLayout {

    /* JADX INFO: renamed from: L0, reason: collision with root package name */
    public static final float[] f19077L0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final View f19078A;

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public boolean f19079A0;
    public final View B;
    public boolean B0;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final TextView f19080C;

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public int f19081C0;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final TextView f19082D;

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public int f19083D0;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final ImageView f19084E;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public int f19085E0;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final ImageView f19086F;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public long[] f19087F0;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final ImageView f19088G;
    public boolean[] G0;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final ImageView f19089H;

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public final long[] f19090H0;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final ImageView f19091I;

    /* JADX INFO: renamed from: I0, reason: collision with root package name */
    public final boolean[] f19092I0;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final ImageView f19093J;

    /* JADX INFO: renamed from: J0, reason: collision with root package name */
    public long f19094J0;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final View f19095K;

    /* JADX INFO: renamed from: K0, reason: collision with root package name */
    public boolean f19096K0;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final View f19097L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final View f19098M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public final TextView f19099N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final TextView f19100O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final I f19101P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final StringBuilder f19102Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final Formatter f19103R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final c0 f19104S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final e0 f19105T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public final A2.l f19106U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public final Drawable f19107V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final Drawable f19108W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final Drawable f19109a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public final Drawable f19110b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final Drawable f19111c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public final String f19112d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final String f19113e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final String f19114f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final Drawable f19115g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final Drawable f19116h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final float f19117i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public final float f19118j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final String f19119k0;
    public final x l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final String f19120l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Resources f19121m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final Drawable f19122m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ViewOnClickListenerC1808h f19123n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final Drawable f19124n0;
    public final CopyOnWriteArrayList o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final String f19125o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final RecyclerView f19126p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final String f19127p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C1814n f19128q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public final Drawable f19129q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C1811k f19130r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public final Drawable f19131r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C1807g f19132s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public final String f19133s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C1807g f19134t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public final String f19135t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final k4.x f19136u;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public W f19137u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final PopupWindow f19138v;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public InterfaceC1809i f19139v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f19140w;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public boolean f19141w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final ImageView f19142x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public boolean f19143x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final ImageView f19144y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public boolean f19145y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final ImageView f19146z;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public boolean f19147z0;

    static {
        e2.G.a("media3.ui");
        f19077L0 = new float[]{0.25f, 0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 2.0f};
    }

    public s(Context context) {
        super(context, null, 0);
        this.f19147z0 = true;
        this.f19081C0 = TsNormalNum.THOUSAND_FIVE;
        this.f19085E0 = 0;
        this.f19083D0 = 200;
        LayoutInflater.from(context).inflate(R.layout.exo_player_control_view, this);
        setDescendantFocusability(262144);
        ViewOnClickListenerC1808h viewOnClickListenerC1808h = new ViewOnClickListenerC1808h(this);
        this.f19123n = viewOnClickListenerC1808h;
        this.o = new CopyOnWriteArrayList();
        this.f19104S = new c0();
        this.f19105T = new e0();
        StringBuilder sb = new StringBuilder();
        this.f19102Q = sb;
        this.f19103R = new Formatter(sb, Locale.getDefault());
        this.f19087F0 = new long[0];
        this.G0 = new boolean[0];
        this.f19090H0 = new long[0];
        this.f19092I0 = new boolean[0];
        this.f19106U = new A2.l(this, 27);
        this.f19099N = (TextView) findViewById(R.id.exo_duration);
        this.f19100O = (TextView) findViewById(R.id.exo_position);
        ImageView imageView = (ImageView) findViewById(R.id.exo_subtitle);
        this.f19089H = imageView;
        if (imageView != null) {
            imageView.setOnClickListener(viewOnClickListenerC1808h);
        }
        ImageView imageView2 = (ImageView) findViewById(R.id.exo_fullscreen);
        this.f19091I = imageView2;
        K6.e eVar = new K6.e(this, 1);
        if (imageView2 != null) {
            imageView2.setVisibility(8);
            imageView2.setOnClickListener(eVar);
        }
        ImageView imageView3 = (ImageView) findViewById(R.id.exo_minimal_fullscreen);
        this.f19093J = imageView3;
        K6.e eVar2 = new K6.e(this, 1);
        if (imageView3 != null) {
            imageView3.setVisibility(8);
            imageView3.setOnClickListener(eVar2);
        }
        View viewFindViewById = findViewById(R.id.exo_settings);
        this.f19095K = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(viewOnClickListenerC1808h);
        }
        View viewFindViewById2 = findViewById(R.id.exo_playback_speed);
        this.f19097L = viewFindViewById2;
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(viewOnClickListenerC1808h);
        }
        View viewFindViewById3 = findViewById(R.id.exo_audio_track);
        this.f19098M = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.setOnClickListener(viewOnClickListenerC1808h);
        }
        I i7 = (I) findViewById(R.id.exo_progress);
        View viewFindViewById4 = findViewById(R.id.exo_progress_placeholder);
        if (i7 != null) {
            this.f19101P = i7;
        } else if (viewFindViewById4 != null) {
            C1805e c1805e = new C1805e(context);
            c1805e.setId(R.id.exo_progress);
            c1805e.setLayoutParams(viewFindViewById4.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) viewFindViewById4.getParent();
            int iIndexOfChild = viewGroup.indexOfChild(viewFindViewById4);
            viewGroup.removeView(viewFindViewById4);
            viewGroup.addView(c1805e, iIndexOfChild);
            this.f19101P = c1805e;
        } else {
            this.f19101P = null;
        }
        I i10 = this.f19101P;
        if (i10 != null) {
            ((C1805e) i10).f19024I.add(viewOnClickListenerC1808h);
        }
        Resources resources = context.getResources();
        this.f19121m = resources;
        ImageView imageView4 = (ImageView) findViewById(R.id.exo_play_pause);
        this.f19146z = imageView4;
        if (imageView4 != null) {
            imageView4.setOnClickListener(viewOnClickListenerC1808h);
        }
        ImageView imageView5 = (ImageView) findViewById(R.id.exo_prev);
        this.f19142x = imageView5;
        if (imageView5 != null) {
            imageView5.setImageDrawable(resources.getDrawable(R.drawable.exo_styled_controls_previous, context.getTheme()));
            imageView5.setOnClickListener(viewOnClickListenerC1808h);
        }
        ImageView imageView6 = (ImageView) findViewById(R.id.exo_next);
        this.f19144y = imageView6;
        if (imageView6 != null) {
            imageView6.setImageDrawable(resources.getDrawable(R.drawable.exo_styled_controls_next, context.getTheme()));
            imageView6.setOnClickListener(viewOnClickListenerC1808h);
        }
        Typeface typefaceA = AbstractC2671j.a(context, R.font.roboto_medium_numbers);
        ImageView imageView7 = (ImageView) findViewById(R.id.exo_rew);
        TextView textView = (TextView) findViewById(R.id.exo_rew_with_amount);
        if (imageView7 != null) {
            imageView7.setImageDrawable(resources.getDrawable(R.drawable.exo_styled_controls_simple_rewind, context.getTheme()));
            this.B = imageView7;
            this.f19082D = null;
        } else if (textView != null) {
            textView.setTypeface(typefaceA);
            this.f19082D = textView;
            this.B = textView;
        } else {
            this.f19082D = null;
            this.B = null;
        }
        View view = this.B;
        if (view != null) {
            view.setOnClickListener(viewOnClickListenerC1808h);
        }
        ImageView imageView8 = (ImageView) findViewById(R.id.exo_ffwd);
        TextView textView2 = (TextView) findViewById(R.id.exo_ffwd_with_amount);
        if (imageView8 != null) {
            imageView8.setImageDrawable(resources.getDrawable(R.drawable.exo_styled_controls_simple_fastforward, context.getTheme()));
            this.f19078A = imageView8;
            this.f19080C = null;
        } else if (textView2 != null) {
            textView2.setTypeface(typefaceA);
            this.f19080C = textView2;
            this.f19078A = textView2;
        } else {
            this.f19080C = null;
            this.f19078A = null;
        }
        View view2 = this.f19078A;
        if (view2 != null) {
            view2.setOnClickListener(viewOnClickListenerC1808h);
        }
        ImageView imageView9 = (ImageView) findViewById(R.id.exo_repeat_toggle);
        this.f19084E = imageView9;
        if (imageView9 != null) {
            imageView9.setOnClickListener(viewOnClickListenerC1808h);
        }
        ImageView imageView10 = (ImageView) findViewById(R.id.exo_shuffle);
        this.f19086F = imageView10;
        if (imageView10 != null) {
            imageView10.setOnClickListener(viewOnClickListenerC1808h);
        }
        this.f19117i0 = resources.getInteger(R.integer.exo_media_button_opacity_percentage_enabled) / 100.0f;
        this.f19118j0 = resources.getInteger(R.integer.exo_media_button_opacity_percentage_disabled) / 100.0f;
        ImageView imageView11 = (ImageView) findViewById(R.id.exo_vr);
        this.f19088G = imageView11;
        if (imageView11 != null) {
            imageView11.setImageDrawable(resources.getDrawable(R.drawable.exo_styled_controls_vr, context.getTheme()));
            j(imageView11, false);
        }
        x xVar = new x(this);
        this.l = xVar;
        xVar.f19156C = true;
        C1814n c1814n = new C1814n(this, new String[]{resources.getString(R.string.exo_controls_playback_speed), resources.getString(R.string.exo_track_selection_title_audio)}, new Drawable[]{resources.getDrawable(R.drawable.exo_styled_controls_speed, context.getTheme()), resources.getDrawable(R.drawable.exo_styled_controls_audiotrack, context.getTheme())});
        this.f19128q = c1814n;
        this.f19140w = resources.getDimensionPixelSize(R.dimen.exo_settings_offset);
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(context).inflate(R.layout.exo_styled_settings_list, (ViewGroup) null);
        this.f19126p = recyclerView;
        recyclerView.setAdapter(c1814n);
        getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        PopupWindow popupWindow = new PopupWindow((View) recyclerView, -2, -2, true);
        this.f19138v = popupWindow;
        if (AbstractC1343A.f15195a < 23) {
            popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        }
        popupWindow.setOnDismissListener(viewOnClickListenerC1808h);
        this.f19096K0 = true;
        this.f19136u = new k4.x(getResources());
        this.f19122m0 = resources.getDrawable(R.drawable.exo_styled_controls_subtitle_on, context.getTheme());
        this.f19124n0 = resources.getDrawable(R.drawable.exo_styled_controls_subtitle_off, context.getTheme());
        this.f19125o0 = resources.getString(R.string.exo_controls_cc_enabled_description);
        this.f19127p0 = resources.getString(R.string.exo_controls_cc_disabled_description);
        this.f19132s = new C1807g(this, 1);
        this.f19134t = new C1807g(this, 0);
        this.f19130r = new C1811k(this, resources.getStringArray(R.array.exo_controls_playback_speeds), f19077L0);
        this.f19107V = resources.getDrawable(R.drawable.exo_styled_controls_play, context.getTheme());
        this.f19108W = resources.getDrawable(R.drawable.exo_styled_controls_pause, context.getTheme());
        this.f19129q0 = resources.getDrawable(R.drawable.exo_styled_controls_fullscreen_exit, context.getTheme());
        this.f19131r0 = resources.getDrawable(R.drawable.exo_styled_controls_fullscreen_enter, context.getTheme());
        this.f19109a0 = resources.getDrawable(R.drawable.exo_styled_controls_repeat_off, context.getTheme());
        this.f19110b0 = resources.getDrawable(R.drawable.exo_styled_controls_repeat_one, context.getTheme());
        this.f19111c0 = resources.getDrawable(R.drawable.exo_styled_controls_repeat_all, context.getTheme());
        this.f19115g0 = resources.getDrawable(R.drawable.exo_styled_controls_shuffle_on, context.getTheme());
        this.f19116h0 = resources.getDrawable(R.drawable.exo_styled_controls_shuffle_off, context.getTheme());
        this.f19133s0 = resources.getString(R.string.exo_controls_fullscreen_exit_description);
        this.f19135t0 = resources.getString(R.string.exo_controls_fullscreen_enter_description);
        this.f19112d0 = resources.getString(R.string.exo_controls_repeat_off_description);
        this.f19113e0 = resources.getString(R.string.exo_controls_repeat_one_description);
        this.f19114f0 = resources.getString(R.string.exo_controls_repeat_all_description);
        this.f19119k0 = resources.getString(R.string.exo_controls_shuffle_on_description);
        this.f19120l0 = resources.getString(R.string.exo_controls_shuffle_off_description);
        xVar.h((ViewGroup) findViewById(R.id.exo_bottom_bar), true);
        xVar.h(this.f19078A, true);
        xVar.h(this.B, true);
        xVar.h(imageView5, true);
        xVar.h(imageView6, true);
        xVar.h(imageView10, false);
        xVar.h(imageView, false);
        xVar.h(imageView11, false);
        xVar.h(imageView9, this.f19085E0 != 0);
        addOnLayoutChangeListener(new ViewOnLayoutChangeListenerC1806f(this, 0));
    }

    public static boolean b(W w10, e0 e0Var) {
        f0 f0VarT0;
        int iO;
        if (!w10.R0(17) || (iO = (f0VarT0 = w10.t0()).o()) <= 1 || iO > 100) {
            return false;
        }
        for (int i7 = 0; i7 < iO; i7++) {
            if (f0VarT0.m(i7, e0Var, 0L).f14094m == -9223372036854775807L) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlaybackSpeed(float f10) {
        W w10 = this.f19137u0;
        if (w10 == null || !w10.R0(13)) {
            return;
        }
        W w11 = this.f19137u0;
        w11.f(new P(f10, w11.j().f13996b));
    }

    public final boolean c(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        W w10 = this.f19137u0;
        if (w10 == null) {
            return false;
        }
        if (keyCode != 90 && keyCode != 89 && keyCode != 85 && keyCode != 79 && keyCode != 126 && keyCode != 127 && keyCode != 87 && keyCode != 88) {
            return false;
        }
        if (keyEvent.getAction() == 0) {
            if (keyCode == 90) {
                if (w10.e() != 4 && w10.R0(12)) {
                    w10.E0();
                }
            } else if (keyCode == 89 && w10.R0(11)) {
                w10.H0();
            } else if (keyEvent.getRepeatCount() == 0) {
                if (keyCode == 79 || keyCode == 85) {
                    if (AbstractC1343A.P(w10, this.f19147z0)) {
                        AbstractC1343A.A(w10);
                    } else if (w10.R0(1)) {
                        w10.b();
                    }
                } else if (keyCode != 87) {
                    if (keyCode != 88) {
                        if (keyCode == 126) {
                            AbstractC1343A.A(w10);
                        } else if (keyCode == 127) {
                            int i7 = AbstractC1343A.f15195a;
                            if (w10.R0(1)) {
                                w10.b();
                            }
                        }
                    } else if (w10.R0(7)) {
                        w10.M0();
                    }
                } else if (w10.R0(9)) {
                    w10.C0();
                }
            }
        }
        return true;
    }

    public final void d(AbstractC2405y abstractC2405y, View view) {
        this.f19126p.setAdapter(abstractC2405y);
        q();
        this.f19096K0 = false;
        PopupWindow popupWindow = this.f19138v;
        popupWindow.dismiss();
        this.f19096K0 = true;
        int width = getWidth() - popupWindow.getWidth();
        int i7 = this.f19140w;
        popupWindow.showAsDropDown(view, width - i7, (-popupWindow.getHeight()) - i7);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return c(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public final d0 e(n0 n0Var, int i7) {
        AbstractC1095q.e(4, "initialCapacity");
        Object[] objArrCopyOf = new Object[4];
        d5.I i10 = n0Var.f14226a;
        int i11 = 0;
        for (int i12 = 0; i12 < i10.size(); i12++) {
            m0 m0Var = (m0) i10.get(i12);
            if (m0Var.f14222b.c == i7) {
                for (int i13 = 0; i13 < m0Var.f14221a; i13++) {
                    if (m0Var.b(i13)) {
                        C1208p c1208p = m0Var.f14222b.d[i13];
                        if ((c1208p.f14308e & 2) == 0) {
                            p pVar = new p(n0Var, i12, i13, this.f19136u.c(c1208p));
                            int i14 = i11 + 1;
                            int iG = AbstractC1074C.g(objArrCopyOf.length, i14);
                            if (iG > objArrCopyOf.length) {
                                objArrCopyOf = Arrays.copyOf(objArrCopyOf, iG);
                            }
                            objArrCopyOf[i11] = pVar;
                            i11 = i14;
                        }
                    }
                }
            }
        }
        return d5.I.t(i11, objArrCopyOf);
    }

    public final void f() {
        x xVar = this.l;
        int i7 = xVar.f19178z;
        if (i7 == 3 || i7 == 2) {
            return;
        }
        xVar.f();
        if (!xVar.f19156C) {
            xVar.i(2);
        } else if (xVar.f19178z == 1) {
            xVar.f19166m.start();
        } else {
            xVar.f19167n.start();
        }
    }

    public final boolean g() {
        x xVar = this.l;
        return xVar.f19178z == 0 && xVar.f19157a.h();
    }

    public W getPlayer() {
        return this.f19137u0;
    }

    public int getRepeatToggleModes() {
        return this.f19085E0;
    }

    public boolean getShowShuffleButton() {
        return this.l.b(this.f19086F);
    }

    public boolean getShowSubtitleButton() {
        return this.l.b(this.f19089H);
    }

    public int getShowTimeoutMs() {
        return this.f19081C0;
    }

    public boolean getShowVrButton() {
        return this.l.b(this.f19088G);
    }

    public final boolean h() {
        return getVisibility() == 0;
    }

    public final void i() {
        m();
        l();
        p();
        r();
        t();
        n();
        s();
    }

    public final void j(View view, boolean z6) {
        if (view == null) {
            return;
        }
        view.setEnabled(z6);
        view.setAlpha(z6 ? this.f19117i0 : this.f19118j0);
    }

    public final void k(boolean z6) {
        if (this.f19141w0 == z6) {
            return;
        }
        this.f19141w0 = z6;
        String str = this.f19135t0;
        Drawable drawable = this.f19131r0;
        String str2 = this.f19133s0;
        Drawable drawable2 = this.f19129q0;
        ImageView imageView = this.f19091I;
        if (imageView != null) {
            if (z6) {
                imageView.setImageDrawable(drawable2);
                imageView.setContentDescription(str2);
            } else {
                imageView.setImageDrawable(drawable);
                imageView.setContentDescription(str);
            }
        }
        ImageView imageView2 = this.f19093J;
        if (imageView2 != null) {
            if (z6) {
                imageView2.setImageDrawable(drawable2);
                imageView2.setContentDescription(str2);
            } else {
                imageView2.setImageDrawable(drawable);
                imageView2.setContentDescription(str);
            }
        }
        InterfaceC1809i interfaceC1809i = this.f19139v0;
        if (interfaceC1809i != null) {
            ((z) interfaceC1809i).f19181n.getClass();
        }
    }

    public final void l() {
        boolean zR0;
        boolean zR02;
        boolean zR03;
        boolean zR04;
        boolean zR05;
        if (h() && this.f19143x0) {
            W w10 = this.f19137u0;
            if (w10 != null) {
                zR0 = (this.f19145y0 && b(w10, this.f19105T)) ? w10.R0(10) : w10.R0(5);
                zR03 = w10.R0(7);
                zR04 = w10.R0(11);
                zR05 = w10.R0(12);
                zR02 = w10.R0(9);
            } else {
                zR0 = false;
                zR02 = false;
                zR03 = false;
                zR04 = false;
                zR05 = false;
            }
            Resources resources = this.f19121m;
            View view = this.B;
            if (zR04) {
                W w11 = this.f19137u0;
                int iO0 = (int) ((w11 != null ? w11.O0() : 5000L) / 1000);
                TextView textView = this.f19082D;
                if (textView != null) {
                    textView.setText(String.valueOf(iO0));
                }
                if (view != null) {
                    view.setContentDescription(resources.getQuantityString(R.plurals.exo_controls_rewind_by_amount_description, iO0, Integer.valueOf(iO0)));
                }
            }
            View view2 = this.f19078A;
            if (zR05) {
                W w12 = this.f19137u0;
                int iU = (int) ((w12 != null ? w12.u() : 15000L) / 1000);
                TextView textView2 = this.f19080C;
                if (textView2 != null) {
                    textView2.setText(String.valueOf(iU));
                }
                if (view2 != null) {
                    view2.setContentDescription(resources.getQuantityString(R.plurals.exo_controls_fastforward_by_amount_description, iU, Integer.valueOf(iU)));
                }
            }
            j(this.f19142x, zR03);
            j(view, zR04);
            j(view2, zR05);
            j(this.f19144y, zR02);
            I i7 = this.f19101P;
            if (i7 != null) {
                ((C1805e) i7).setEnabled(zR0);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m() {
        /*
            r4 = this;
            boolean r0 = r4.h()
            if (r0 == 0) goto L59
            boolean r0 = r4.f19143x0
            if (r0 != 0) goto Lb
            goto L59
        Lb:
            android.widget.ImageView r0 = r4.f19146z
            if (r0 == 0) goto L59
            e2.W r1 = r4.f19137u0
            boolean r2 = r4.f19147z0
            boolean r1 = h2.AbstractC1343A.P(r1, r2)
            if (r1 == 0) goto L1c
            android.graphics.drawable.Drawable r2 = r4.f19107V
            goto L1e
        L1c:
            android.graphics.drawable.Drawable r2 = r4.f19108W
        L1e:
            if (r1 == 0) goto L24
            r1 = 2131821010(0x7f1101d2, float:1.9274751E38)
            goto L27
        L24:
            r1 = 2131821009(0x7f1101d1, float:1.927475E38)
        L27:
            r0.setImageDrawable(r2)
            android.content.res.Resources r2 = r4.f19121m
            java.lang.String r1 = r2.getString(r1)
            r0.setContentDescription(r1)
            e2.W r1 = r4.f19137u0
            if (r1 == 0) goto L55
            r2 = 1
            boolean r1 = r1.R0(r2)
            if (r1 == 0) goto L55
            e2.W r1 = r4.f19137u0
            r3 = 17
            boolean r1 = r1.R0(r3)
            if (r1 == 0) goto L56
            e2.W r1 = r4.f19137u0
            e2.f0 r1 = r1.t0()
            boolean r1 = r1.p()
            if (r1 != 0) goto L55
            goto L56
        L55:
            r2 = 0
        L56:
            r4.j(r0, r2)
        L59:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: m3.s.m():void");
    }

    public final void n() {
        C1811k c1811k;
        W w10 = this.f19137u0;
        if (w10 == null) {
            return;
        }
        float f10 = w10.j().f13995a;
        float f11 = Float.MAX_VALUE;
        int i7 = 0;
        int i10 = 0;
        while (true) {
            c1811k = this.f19130r;
            float[] fArr = c1811k.f19061e;
            if (i7 >= fArr.length) {
                break;
            }
            float fAbs = Math.abs(f10 - fArr[i7]);
            if (fAbs < f11) {
                i10 = i7;
                f11 = fAbs;
            }
            i7++;
        }
        c1811k.f19062f = i10;
        String str = c1811k.d[i10];
        C1814n c1814n = this.f19128q;
        c1814n.f19068e[0] = str;
        j(this.f19095K, c1814n.e(1) || c1814n.e(0));
    }

    public final void o() {
        long jX;
        long jZ0;
        if (h() && this.f19143x0) {
            W w10 = this.f19137u0;
            if (w10 == null || !w10.R0(16)) {
                jX = 0;
                jZ0 = 0;
            } else {
                jX = w10.x() + this.f19094J0;
                jZ0 = w10.z0() + this.f19094J0;
            }
            TextView textView = this.f19100O;
            if (textView != null && !this.B0) {
                textView.setText(AbstractC1343A.w(this.f19102Q, this.f19103R, jX));
            }
            I i7 = this.f19101P;
            if (i7 != null) {
                C1805e c1805e = (C1805e) i7;
                c1805e.setPosition(jX);
                c1805e.setBufferedPosition(jZ0);
            }
            A2.l lVar = this.f19106U;
            removeCallbacks(lVar);
            int iE = w10 == null ? 1 : w10.e();
            if (w10 != null && w10.O()) {
                long jMin = Math.min(i7 != null ? ((C1805e) i7).getPreferredUpdateDelay() : 1000L, 1000 - (jX % 1000));
                float f10 = w10.j().f13995a;
                postDelayed(lVar, AbstractC1343A.j(f10 > 0.0f ? (long) (jMin / f10) : 1000L, this.f19083D0, 1000L));
            } else {
                if (iE == 4 || iE == 1) {
                    return;
                }
                postDelayed(lVar, 1000L);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        x xVar = this.l;
        xVar.f19157a.addOnLayoutChangeListener(xVar.f19176x);
        this.f19143x0 = true;
        if (g()) {
            xVar.g();
        }
        i();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        x xVar = this.l;
        xVar.f19157a.removeOnLayoutChangeListener(xVar.f19176x);
        this.f19143x0 = false;
        removeCallbacks(this.f19106U);
        xVar.f();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        super.onLayout(z6, i7, i10, i11, i12);
        View view = this.l.f19158b;
        if (view != null) {
            view.layout(0, 0, i11 - i7, i12 - i10);
        }
    }

    public final void p() {
        ImageView imageView;
        if (h() && this.f19143x0 && (imageView = this.f19084E) != null) {
            if (this.f19085E0 == 0) {
                j(imageView, false);
                return;
            }
            W w10 = this.f19137u0;
            String str = this.f19112d0;
            Drawable drawable = this.f19109a0;
            if (w10 == null || !w10.R0(15)) {
                j(imageView, false);
                imageView.setImageDrawable(drawable);
                imageView.setContentDescription(str);
                return;
            }
            j(imageView, true);
            int i7 = w10.i();
            if (i7 == 0) {
                imageView.setImageDrawable(drawable);
                imageView.setContentDescription(str);
            } else if (i7 == 1) {
                imageView.setImageDrawable(this.f19110b0);
                imageView.setContentDescription(this.f19113e0);
            } else {
                if (i7 != 2) {
                    return;
                }
                imageView.setImageDrawable(this.f19111c0);
                imageView.setContentDescription(this.f19114f0);
            }
        }
    }

    public final void q() {
        RecyclerView recyclerView = this.f19126p;
        recyclerView.measure(0, 0);
        int width = getWidth();
        int i7 = this.f19140w;
        int iMin = Math.min(recyclerView.getMeasuredWidth(), width - (i7 * 2));
        PopupWindow popupWindow = this.f19138v;
        popupWindow.setWidth(iMin);
        popupWindow.setHeight(Math.min(getHeight() - (i7 * 2), recyclerView.getMeasuredHeight()));
    }

    public final void r() {
        ImageView imageView;
        if (h() && this.f19143x0 && (imageView = this.f19086F) != null) {
            W w10 = this.f19137u0;
            if (!this.l.b(imageView)) {
                j(imageView, false);
                return;
            }
            String str = this.f19120l0;
            Drawable drawable = this.f19116h0;
            if (w10 == null || !w10.R0(14)) {
                j(imageView, false);
                imageView.setImageDrawable(drawable);
                imageView.setContentDescription(str);
                return;
            }
            j(imageView, true);
            if (w10.x0()) {
                drawable = this.f19115g0;
            }
            imageView.setImageDrawable(drawable);
            if (w10.x0()) {
                str = this.f19119k0;
            }
            imageView.setContentDescription(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x0162  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void s() {
        /*
            Method dump skipped, instruction units count: 450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: m3.s.s():void");
    }

    public void setAnimationEnabled(boolean z6) {
        this.l.f19156C = z6;
    }

    @Deprecated
    public void setOnFullScreenModeChangedListener(InterfaceC1809i interfaceC1809i) {
        this.f19139v0 = interfaceC1809i;
        boolean z6 = interfaceC1809i != null;
        ImageView imageView = this.f19091I;
        if (imageView != null) {
            if (z6) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
        }
        boolean z10 = interfaceC1809i != null;
        ImageView imageView2 = this.f19093J;
        if (imageView2 == null) {
            return;
        }
        if (z10) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
    }

    public void setPlayer(W w10) {
        AbstractC1346c.j(Looper.myLooper() == Looper.getMainLooper());
        AbstractC1346c.c(w10 == null || w10.T0() == Looper.getMainLooper());
        W w11 = this.f19137u0;
        if (w11 == w10) {
            return;
        }
        ViewOnClickListenerC1808h viewOnClickListenerC1808h = this.f19123n;
        if (w11 != null) {
            w11.H(viewOnClickListenerC1808h);
        }
        this.f19137u0 = w10;
        if (w10 != null) {
            w10.D0(viewOnClickListenerC1808h);
        }
        i();
    }

    public void setProgressUpdateListener(InterfaceC1812l interfaceC1812l) {
    }

    public void setRepeatToggleModes(int i7) {
        this.f19085E0 = i7;
        W w10 = this.f19137u0;
        if (w10 != null && w10.R0(15)) {
            int i10 = this.f19137u0.i();
            if (i7 == 0 && i10 != 0) {
                this.f19137u0.h(0);
            } else if (i7 == 1 && i10 == 2) {
                this.f19137u0.h(1);
            } else if (i7 == 2 && i10 == 1) {
                this.f19137u0.h(2);
            }
        }
        this.l.h(this.f19084E, i7 != 0);
        p();
    }

    public void setShowFastForwardButton(boolean z6) {
        this.l.h(this.f19078A, z6);
        l();
    }

    @Deprecated
    public void setShowMultiWindowTimeBar(boolean z6) {
        this.f19145y0 = z6;
        s();
    }

    public void setShowNextButton(boolean z6) {
        this.l.h(this.f19144y, z6);
        l();
    }

    public void setShowPlayButtonIfPlaybackIsSuppressed(boolean z6) {
        this.f19147z0 = z6;
        m();
    }

    public void setShowPreviousButton(boolean z6) {
        this.l.h(this.f19142x, z6);
        l();
    }

    public void setShowRewindButton(boolean z6) {
        this.l.h(this.B, z6);
        l();
    }

    public void setShowShuffleButton(boolean z6) {
        this.l.h(this.f19086F, z6);
        r();
    }

    public void setShowSubtitleButton(boolean z6) {
        this.l.h(this.f19089H, z6);
    }

    public void setShowTimeoutMs(int i7) {
        this.f19081C0 = i7;
        if (g()) {
            this.l.g();
        }
    }

    public void setShowVrButton(boolean z6) {
        this.l.h(this.f19088G, z6);
    }

    public void setTimeBarMinUpdateInterval(int i7) {
        this.f19083D0 = AbstractC1343A.i(i7, 16, 1000);
    }

    public void setVrButtonListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.f19088G;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
            j(imageView, onClickListener != null);
        }
    }

    public final void t() {
        C1807g c1807g = this.f19132s;
        c1807g.getClass();
        c1807g.d = Collections.emptyList();
        C1807g c1807g2 = this.f19134t;
        c1807g2.getClass();
        c1807g2.d = Collections.emptyList();
        W w10 = this.f19137u0;
        ImageView imageView = this.f19089H;
        if (w10 != null && w10.R0(30) && this.f19137u0.R0(29)) {
            n0 n0VarJ = this.f19137u0.J();
            d0 d0VarE = e(n0VarJ, 1);
            c1807g2.d = d0VarE;
            s sVar = c1807g2.f19059g;
            W w11 = sVar.f19137u0;
            w11.getClass();
            l0 l0VarY0 = w11.y0();
            boolean zIsEmpty = d0VarE.isEmpty();
            C1814n c1814n = sVar.f19128q;
            if (!zIsEmpty) {
                if (c1807g2.e(l0VarY0)) {
                    int i7 = 0;
                    while (true) {
                        if (i7 >= d0VarE.o) {
                            break;
                        }
                        p pVar = (p) d0VarE.get(i7);
                        if (pVar.f19073a.f14223e[pVar.f19074b]) {
                            c1814n.f19068e[1] = pVar.c;
                            break;
                        }
                        i7++;
                    }
                } else {
                    c1814n.f19068e[1] = sVar.getResources().getString(R.string.exo_track_selection_auto);
                }
            } else {
                c1814n.f19068e[1] = sVar.getResources().getString(R.string.exo_track_selection_none);
            }
            if (this.l.b(imageView)) {
                c1807g.f(e(n0VarJ, 3));
            } else {
                c1807g.f(d0.f13415p);
            }
        }
        j(imageView, c1807g.a() > 0);
        C1814n c1814n2 = this.f19128q;
        j(this.f19095K, c1814n2.e(1) || c1814n2.e(0));
    }
}
