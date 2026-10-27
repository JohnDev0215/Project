package androidx.mediarouter.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.mediarouter.R$dimen;
import androidx.mediarouter.R$id;
import androidx.mediarouter.R$integer;
import androidx.mediarouter.R$layout;
import androidx.mediarouter.R$string;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.t;
import androidx.mediarouter.media.u;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class i extends androidx.appcompat.app.c {
    static final boolean S = Log.isLoggable("MediaRouteCtrlDialog", 3);
    private boolean A;
    private ImageButton B;
    private Button C;
    private ImageView D;
    private View E;
    ImageView F;
    private TextView G;
    private TextView H;
    private String I;
    MediaControllerCompat J;
    e K;
    MediaDescriptionCompat L;
    d M;
    Bitmap N;
    Uri O;
    boolean P;
    Bitmap Q;
    int R;
    final u e;
    private final g f;
    private t g;
    u.g h;
    final List<u.g> i;
    final List<u.g> j;
    final List<u.g> k;
    final List<u.g> l;
    Context m;
    private boolean n;
    private boolean o;
    private long p;
    final Handler q;
    RecyclerView r;
    h s;
    j t;
    Map<String, f> u;
    u.g v;
    Map<String, Integer> w;
    boolean x;
    boolean y;
    private boolean z;

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                i.this.v();
                return;
            }
            if (i != 2) {
                return;
            }
            i iVar = i.this;
            if (iVar.v != null) {
                iVar.v = null;
                iVar.w();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            i.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (i.this.h.B()) {
                i.this.e.p(2);
            }
            i.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class d extends AsyncTask<Void, Void, Bitmap> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Bitmap f894a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Uri f895b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f896c;

        d() {
            MediaDescriptionCompat mediaDescriptionCompat = i.this.L;
            Bitmap bitmapB = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.b();
            if (i.k(bitmapB)) {
                Log.w("MediaRouteCtrlDialog", "Can't fetch the given art bitmap because it's already recycled.");
                bitmapB = null;
            }
            this.f894a = bitmapB;
            MediaDescriptionCompat mediaDescriptionCompat2 = i.this.L;
            this.f895b = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.c() : null;
        }

        private InputStream e(Uri uri) throws IOException {
            InputStream inputStreamOpenInputStream;
            String lowerCase = uri.getScheme().toLowerCase();
            if ("android.resource".equals(lowerCase) || "content".equals(lowerCase) || "file".equals(lowerCase)) {
                inputStreamOpenInputStream = i.this.m.getContentResolver().openInputStream(uri);
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

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0 */
        /* JADX WARN: Type inference failed for: r4v1 */
        /* JADX WARN: Type inference failed for: r4v2, types: [java.io.InputStream] */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) throws Throwable {
            InputStream inputStreamE;
            Bitmap bitmap = this.f894a;
            ?? r4 = 0;
            if (bitmap == null) {
                Uri uri = this.f895b;
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
                                Log.w("MediaRouteCtrlDialog", "Unable to open: " + this.f895b);
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
                                inputStreamE = e(this.f895b);
                                if (inputStreamE == null) {
                                    Log.w("MediaRouteCtrlDialog", "Unable to open: " + this.f895b);
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
                            options.inSampleSize = Math.max(1, Integer.highestOneBit(options.outHeight / i.this.m.getResources().getDimensionPixelSize(R$dimen.mr_cast_meta_art_size)));
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
                            Log.w("MediaRouteCtrlDialog", "Unable to open: " + this.f895b, e);
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
            if (i.k(bitmap)) {
                Log.w("MediaRouteCtrlDialog", "Can't use recycled bitmap: " + bitmap);
                return null;
            }
            if (bitmap != null && bitmap.getWidth() < bitmap.getHeight()) {
                Palette paletteGenerate = new Palette.Builder(bitmap).maximumColorCount(1).generate();
                this.f896c = paletteGenerate.getSwatches().isEmpty() ? 0 : ((Palette.Swatch) paletteGenerate.getSwatches().get(0)).getRgb();
            }
            return bitmap;
        }

        Bitmap b() {
            return this.f894a;
        }

        Uri c() {
            return this.f895b;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            i iVar = i.this;
            iVar.M = null;
            if (androidx.core.d.a.a(iVar.N, this.f894a) && androidx.core.d.a.a(i.this.O, this.f895b)) {
                return;
            }
            i iVar2 = i.this;
            iVar2.N = this.f894a;
            iVar2.Q = bitmap;
            iVar2.O = this.f895b;
            iVar2.R = this.f896c;
            iVar2.P = true;
            iVar2.t();
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            i.this.f();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class e extends MediaControllerCompat.a {
        e() {
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            i.this.L = mediaMetadataCompat == null ? null : mediaMetadataCompat.c();
            i.this.n();
            i.this.t();
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void i() {
            i iVar = i.this;
            MediaControllerCompat mediaControllerCompat = iVar.J;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.h(iVar.K);
                i.this.J = null;
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private abstract class f extends RecyclerView.A {
        u.g t;
        final ImageButton u;
        final MediaRouteVolumeSlider v;

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements View.OnClickListener {
            a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                i iVar = i.this;
                if (iVar.v != null) {
                    iVar.q.removeMessages(2);
                }
                f fVar = f.this;
                i.this.v = fVar.t;
                boolean z = !view.isActivated();
                int iN = z ? 0 : f.this.N();
                f.this.O(z);
                f.this.v.setProgress(iN);
                f.this.t.F(iN);
                i.this.q.sendEmptyMessageDelayed(2, 500L);
            }
        }

        f(View view, ImageButton imageButton, MediaRouteVolumeSlider mediaRouteVolumeSlider) {
            super(view);
            this.u = imageButton;
            this.v = mediaRouteVolumeSlider;
            this.u.setImageDrawable(androidx.mediarouter.app.j.k(i.this.m));
            androidx.mediarouter.app.j.v(i.this.m, this.v);
        }

        @CallSuper
        void M(u.g gVar) {
            this.t = gVar;
            int iR = gVar.r();
            this.u.setActivated(iR == 0);
            this.u.setOnClickListener(new a());
            this.v.setTag(this.t);
            this.v.setMax(gVar.t());
            this.v.setProgress(iR);
            this.v.setOnSeekBarChangeListener(i.this.t);
        }

        int N() {
            Integer num = i.this.w.get(this.t.j());
            if (num == null) {
                return 1;
            }
            return Math.max(1, num.intValue());
        }

        void O(boolean z) {
            if (this.u.isActivated() == z) {
                return;
            }
            this.u.setActivated(z);
            if (z) {
                i.this.w.put(this.t.j(), Integer.valueOf(this.v.getProgress()));
            } else {
                i.this.w.remove(this.t.j());
            }
        }

        void P() {
            int iR = this.t.r();
            O(iR == 0);
            this.v.setProgress(iR);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class g extends u.b {
        g() {
        }

        @Override // androidx.mediarouter.media.u.b
        public void d(u uVar, u.g gVar) {
            i.this.v();
        }

        @Override // androidx.mediarouter.media.u.b
        public void e(u uVar, u.g gVar) {
            boolean z;
            u.g.a aVarH;
            if (gVar != i.this.h || gVar.h() == null) {
                z = false;
            } else {
                for (u.g gVar2 : gVar.p().f()) {
                    if (!i.this.h.k().contains(gVar2) && (aVarH = gVar2.h()) != null && aVarH.b() && !i.this.j.contains(gVar2)) {
                        z = true;
                        break;
                    }
                }
                z = false;
            }
            if (!z) {
                i.this.v();
            } else {
                i.this.w();
                i.this.u();
            }
        }

        @Override // androidx.mediarouter.media.u.b
        public void g(u uVar, u.g gVar) {
            i.this.v();
        }

        @Override // androidx.mediarouter.media.u.b
        public void h(u uVar, u.g gVar) {
            i iVar = i.this;
            iVar.h = gVar;
            iVar.x = false;
            iVar.w();
            i.this.u();
        }

        @Override // androidx.mediarouter.media.u.b
        public void j(u uVar, u.g gVar) {
            i.this.v();
        }

        @Override // androidx.mediarouter.media.u.b
        public void l(u uVar, u.g gVar) {
            f fVar;
            int iR = gVar.r();
            if (i.S) {
                Log.d("MediaRouteCtrlDialog", "onRouteVolumeChanged(), route.getVolume:" + iR);
            }
            i iVar = i.this;
            if (iVar.v == gVar || (fVar = iVar.u.get(gVar.j())) == null) {
                return;
            }
            fVar.P();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private final class h extends RecyclerView.Adapter<RecyclerView.A> {
        private final LayoutInflater e;
        private final Drawable f;
        private final Drawable g;
        private final Drawable h;
        private final Drawable i;
        private f j;
        private final int k;
        private final ArrayList<f> d = new ArrayList<>();
        private final Interpolator l = new AccelerateDecelerateInterpolator();

        /* JADX INFO: compiled from: caradapter-bug */
        class a extends Animation {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ int f899c;
            final /* synthetic */ int d;
            final /* synthetic */ View e;

            a(h hVar, int i, int i2, View view) {
                this.f899c = i;
                this.d = i2;
                this.e = view;
            }

            @Override // android.view.animation.Animation
            protected void applyTransformation(float f, Transformation transformation) {
                int i = this.f899c;
                i.o(this.e, this.d + ((int) ((i - r0) * f)));
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        class b implements Animation.AnimationListener {
            b() {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                i iVar = i.this;
                iVar.y = false;
                iVar.w();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                i.this.y = true;
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private class c extends RecyclerView.A {
            final View t;
            final ImageView u;
            final ProgressBar v;
            final TextView w;
            final float x;
            u.g y;

            /* JADX INFO: compiled from: caradapter-bug */
            class a implements View.OnClickListener {
                a() {
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    c cVar = c.this;
                    i.this.e.o(cVar.y);
                    c.this.u.setVisibility(4);
                    c.this.v.setVisibility(0);
                }
            }

            c(View view) {
                super(view);
                this.t = view;
                this.u = (ImageView) view.findViewById(R$id.mr_cast_group_icon);
                this.v = (ProgressBar) view.findViewById(R$id.mr_cast_group_progress_bar);
                this.w = (TextView) view.findViewById(R$id.mr_cast_group_name);
                this.x = androidx.mediarouter.app.j.h(i.this.m);
                androidx.mediarouter.app.j.t(i.this.m, this.v);
            }

            private boolean N(u.g gVar) {
                List<u.g> listK = i.this.h.k();
                return (listK.size() == 1 && listK.get(0) == gVar) ? false : true;
            }

            void M(f fVar) {
                u.g gVar = (u.g) fVar.a();
                this.y = gVar;
                this.u.setVisibility(0);
                this.v.setVisibility(4);
                this.t.setAlpha(N(gVar) ? 1.0f : this.x);
                this.t.setOnClickListener(new a());
                this.u.setImageDrawable(h.this.E(gVar));
                this.w.setText(gVar.l());
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private class d extends f {
            private final TextView x;
            private final int y;

            d(View view) {
                super(view, (ImageButton) view.findViewById(R$id.mr_cast_mute_button), (MediaRouteVolumeSlider) view.findViewById(R$id.mr_cast_volume_slider));
                this.x = (TextView) view.findViewById(R$id.mr_group_volume_route_name);
                Resources resources = i.this.m.getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                TypedValue typedValue = new TypedValue();
                resources.getValue(R$dimen.mr_dynamic_volume_group_list_item_height, typedValue, true);
                this.y = (int) typedValue.getDimension(displayMetrics);
            }

            void Q(f fVar) {
                i.o(this.f1092a, h.this.G() ? this.y : 0);
                u.g gVar = (u.g) fVar.a();
                super.M(gVar);
                this.x.setText(gVar.l());
            }

            int R() {
                return this.y;
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private class e extends RecyclerView.A {
            private final TextView t;

            e(h hVar, View view) {
                super(view);
                this.t = (TextView) view.findViewById(R$id.mr_cast_header_name);
            }

            void M(f fVar) {
                this.t.setText(fVar.a().toString());
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private class f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final Object f902a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final int f903b;

            f(h hVar, Object obj, int i) {
                this.f902a = obj;
                this.f903b = i;
            }

            public Object a() {
                return this.f902a;
            }

            public int b() {
                return this.f903b;
            }
        }

        /* JADX INFO: compiled from: caradapter-bug */
        private class g extends f {
            final TextView A;
            final RelativeLayout B;
            final CheckBox C;
            final float D;
            final int E;
            final int F;
            final View.OnClickListener G;
            final View x;
            final ImageView y;
            final ProgressBar z;

            /* JADX INFO: compiled from: caradapter-bug */
            class a implements View.OnClickListener {
                a() {
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    g gVar = g.this;
                    boolean z = !gVar.S(gVar.t);
                    boolean zX = g.this.t.x();
                    if (z) {
                        g gVar2 = g.this;
                        i.this.e.c(gVar2.t);
                    } else {
                        g gVar3 = g.this;
                        i.this.e.n(gVar3.t);
                    }
                    g.this.T(z, !zX);
                    if (zX) {
                        List<u.g> listK = i.this.h.k();
                        for (u.g gVar4 : g.this.t.k()) {
                            if (listK.contains(gVar4) != z) {
                                f fVar = i.this.u.get(gVar4.j());
                                if (fVar instanceof g) {
                                    ((g) fVar).T(z, true);
                                }
                            }
                        }
                    }
                    g gVar5 = g.this;
                    h.this.H(gVar5.t, z);
                }
            }

            g(View view) {
                super(view, (ImageButton) view.findViewById(R$id.mr_cast_mute_button), (MediaRouteVolumeSlider) view.findViewById(R$id.mr_cast_volume_slider));
                this.G = new a();
                this.x = view;
                this.y = (ImageView) view.findViewById(R$id.mr_cast_route_icon);
                this.z = (ProgressBar) view.findViewById(R$id.mr_cast_route_progress_bar);
                this.A = (TextView) view.findViewById(R$id.mr_cast_route_name);
                this.B = (RelativeLayout) view.findViewById(R$id.mr_cast_volume_layout);
                this.C = (CheckBox) view.findViewById(R$id.mr_cast_checkbox);
                this.C.setButtonDrawable(androidx.mediarouter.app.j.e(i.this.m));
                androidx.mediarouter.app.j.t(i.this.m, this.z);
                this.D = androidx.mediarouter.app.j.h(i.this.m);
                Resources resources = i.this.m.getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                TypedValue typedValue = new TypedValue();
                resources.getValue(R$dimen.mr_dynamic_dialog_row_height, typedValue, true);
                this.E = (int) typedValue.getDimension(displayMetrics);
                this.F = 0;
            }

            private boolean R(u.g gVar) {
                if (i.this.l.contains(gVar)) {
                    return false;
                }
                if (S(gVar) && i.this.h.k().size() < 2) {
                    return false;
                }
                if (!S(gVar)) {
                    return true;
                }
                u.g.a aVarH = gVar.h();
                return aVarH != null && aVarH.d();
            }

            void Q(f fVar) {
                u.g gVar = (u.g) fVar.a();
                if (gVar == i.this.h && gVar.k().size() > 0) {
                    Iterator<u.g> it = gVar.k().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        u.g next = it.next();
                        if (!i.this.j.contains(next)) {
                            gVar = next;
                            break;
                        }
                    }
                }
                M(gVar);
                this.y.setImageDrawable(h.this.E(gVar));
                this.A.setText(gVar.l());
                this.C.setVisibility(0);
                boolean zS = S(gVar);
                boolean zR = R(gVar);
                this.C.setChecked(zS);
                this.z.setVisibility(4);
                this.y.setVisibility(0);
                this.x.setEnabled(zR);
                this.C.setEnabled(zR);
                this.u.setEnabled(zR || zS);
                this.v.setEnabled(zR || zS);
                this.x.setOnClickListener(this.G);
                this.C.setOnClickListener(this.G);
                i.o(this.B, (!zS || this.t.x()) ? this.F : this.E);
                float f = 1.0f;
                this.x.setAlpha((zR || zS) ? 1.0f : this.D);
                CheckBox checkBox = this.C;
                if (!zR && zS) {
                    f = this.D;
                }
                checkBox.setAlpha(f);
            }

            boolean S(u.g gVar) {
                if (gVar.B()) {
                    return true;
                }
                u.g.a aVarH = gVar.h();
                return aVarH != null && aVarH.a() == 3;
            }

            void T(boolean z, boolean z2) {
                this.C.setEnabled(false);
                this.x.setEnabled(false);
                this.C.setChecked(z);
                if (z) {
                    this.y.setVisibility(4);
                    this.z.setVisibility(0);
                }
                if (z2) {
                    h.this.C(this.B, z ? this.E : this.F);
                }
            }
        }

        h() {
            this.e = LayoutInflater.from(i.this.m);
            this.f = androidx.mediarouter.app.j.g(i.this.m);
            this.g = androidx.mediarouter.app.j.q(i.this.m);
            this.h = androidx.mediarouter.app.j.m(i.this.m);
            this.i = androidx.mediarouter.app.j.n(i.this.m);
            this.k = i.this.m.getResources().getInteger(R$integer.mr_cast_volume_slider_layout_animation_duration_ms);
            J();
        }

        private Drawable D(u.g gVar) {
            int iF = gVar.f();
            return iF != 1 ? iF != 2 ? gVar.x() ? this.i : this.f : this.h : this.g;
        }

        void C(View view, int i) {
            a aVar = new a(this, i, view.getLayoutParams().height, view);
            aVar.setAnimationListener(new b());
            aVar.setDuration(this.k);
            aVar.setInterpolator(this.l);
            view.startAnimation(aVar);
        }

        Drawable E(u.g gVar) {
            Uri uriI = gVar.i();
            if (uriI != null) {
                try {
                    Drawable drawableCreateFromStream = Drawable.createFromStream(i.this.m.getContentResolver().openInputStream(uriI), null);
                    if (drawableCreateFromStream != null) {
                        return drawableCreateFromStream;
                    }
                } catch (IOException e2) {
                    Log.w("MediaRouteCtrlDialog", "Failed to load " + uriI, e2);
                }
            }
            return D(gVar);
        }

        public f F(int i) {
            return i == 0 ? this.j : this.d.get(i - 1);
        }

        boolean G() {
            return i.this.h.k().size() > 1;
        }

        void H(u.g gVar, boolean z) {
            List<u.g> listK = i.this.h.k();
            int iMax = Math.max(1, listK.size());
            if (gVar.x()) {
                Iterator<u.g> it = gVar.k().iterator();
                while (it.hasNext()) {
                    if (listK.contains(it.next()) != z) {
                        iMax += z ? 1 : -1;
                    }
                }
            } else {
                iMax += z ? 1 : -1;
            }
            boolean zG = G();
            boolean z2 = iMax >= 2;
            if (zG != z2) {
                RecyclerView.A aW = i.this.r.W(0);
                if (aW instanceof d) {
                    d dVar = (d) aW;
                    C(dVar.f1092a, z2 ? dVar.R() : 0);
                }
            }
        }

        void I() {
            i.this.l.clear();
            i iVar = i.this;
            iVar.l.addAll(androidx.mediarouter.app.g.g(iVar.j, iVar.h()));
            k();
        }

        void J() {
            this.d.clear();
            this.j = new f(this, i.this.h, 1);
            if (i.this.i.isEmpty()) {
                this.d.add(new f(this, i.this.h, 3));
            } else {
                Iterator<u.g> it = i.this.i.iterator();
                while (it.hasNext()) {
                    this.d.add(new f(this, it.next(), 3));
                }
            }
            boolean z = false;
            if (!i.this.j.isEmpty()) {
                boolean z2 = false;
                for (u.g gVar : i.this.j) {
                    if (!i.this.i.contains(gVar)) {
                        if (!z2) {
                            MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerG = i.this.h.g();
                            String strK = dynamicGroupRouteControllerG != null ? dynamicGroupRouteControllerG.k() : null;
                            if (TextUtils.isEmpty(strK)) {
                                strK = i.this.m.getString(R$string.mr_dialog_groupable_header);
                            }
                            this.d.add(new f(this, strK, 2));
                            z2 = true;
                        }
                        this.d.add(new f(this, gVar, 3));
                    }
                }
            }
            if (!i.this.k.isEmpty()) {
                for (u.g gVar2 : i.this.k) {
                    u.g gVar3 = i.this.h;
                    if (gVar3 != gVar2) {
                        if (!z) {
                            MediaRouteProvider.DynamicGroupRouteController dynamicGroupRouteControllerG2 = gVar3.g();
                            String strL = dynamicGroupRouteControllerG2 != null ? dynamicGroupRouteControllerG2.l() : null;
                            if (TextUtils.isEmpty(strL)) {
                                strL = i.this.m.getString(R$string.mr_dialog_transferable_header);
                            }
                            this.d.add(new f(this, strL, 2));
                            z = true;
                        }
                        this.d.add(new f(this, gVar2, 4));
                    }
                }
            }
            I();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int f() {
            return this.d.size() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int h(int i) {
            return F(i).b();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void r(@NonNull RecyclerView.A a2, int i) {
            int iH = h(i);
            f fVarF = F(i);
            if (iH == 1) {
                i.this.u.put(((u.g) fVarF.a()).j(), (f) a2);
                ((d) a2).Q(fVarF);
            } else {
                if (iH == 2) {
                    ((e) a2).M(fVarF);
                    return;
                }
                if (iH == 3) {
                    i.this.u.put(((u.g) fVarF.a()).j(), (f) a2);
                    ((g) a2).Q(fVarF);
                } else if (iH != 4) {
                    Log.w("MediaRouteCtrlDialog", "Cannot bind item to ViewHolder because of wrong view type");
                } else {
                    ((c) a2).M(fVarF);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.A t(@NonNull ViewGroup viewGroup, int i) {
            if (i == 1) {
                return new d(this.e.inflate(R$layout.mr_cast_group_volume_item, viewGroup, false));
            }
            if (i == 2) {
                return new e(this, this.e.inflate(R$layout.mr_cast_header_item, viewGroup, false));
            }
            if (i == 3) {
                return new g(this.e.inflate(R$layout.mr_cast_route_item, viewGroup, false));
            }
            if (i == 4) {
                return new c(this.e.inflate(R$layout.mr_cast_group_item, viewGroup, false));
            }
            Log.w("MediaRouteCtrlDialog", "Cannot create ViewHolder because of wrong view type");
            return null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void y(@NonNull RecyclerView.A a2) {
            super.y(a2);
            i.this.u.values().remove(a2);
        }
    }

    /* JADX INFO: renamed from: androidx.mediarouter.app.i$i, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    static final class C0032i implements Comparator<u.g> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        static final C0032i f905c = new C0032i();

        C0032i() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(u.g gVar, u.g gVar2) {
            return gVar.l().compareToIgnoreCase(gVar2.l());
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class j implements SeekBar.OnSeekBarChangeListener {
        j() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                u.g gVar = (u.g) seekBar.getTag();
                f fVar = i.this.u.get(gVar.j());
                if (fVar != null) {
                    fVar.O(i == 0);
                }
                gVar.F(i);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            i iVar = i.this;
            if (iVar.v != null) {
                iVar.q.removeMessages(2);
            }
            i.this.v = (u.g) seekBar.getTag();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            i.this.q.sendEmptyMessageDelayed(2, 500L);
        }
    }

    public i(Context context) {
        this(context, 0);
    }

    @RequiresApi(17)
    private static Bitmap e(Bitmap bitmap, float f2, Context context) {
        RenderScript renderScriptCreate = RenderScript.create(context);
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmap);
        Allocation allocationCreateTyped = Allocation.createTyped(renderScriptCreate, allocationCreateFromBitmap.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
        scriptIntrinsicBlurCreate.setRadius(f2);
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.forEach(allocationCreateTyped);
        Bitmap bitmapCopy = bitmap.copy(bitmap.getConfig(), true);
        allocationCreateTyped.copyTo(bitmapCopy);
        allocationCreateFromBitmap.destroy();
        allocationCreateTyped.destroy();
        scriptIntrinsicBlurCreate.destroy();
        renderScriptCreate.destroy();
        return bitmapCopy;
    }

    static boolean k(Bitmap bitmap) {
        return bitmap != null && bitmap.isRecycled();
    }

    static void o(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }

    private void p(MediaSessionCompat.Token token) {
        MediaControllerCompat mediaControllerCompat = this.J;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.h(this.K);
            this.J = null;
        }
        if (token != null && this.o) {
            MediaControllerCompat mediaControllerCompat2 = new MediaControllerCompat(this.m, token);
            this.J = mediaControllerCompat2;
            mediaControllerCompat2.f(this.K);
            MediaMetadataCompat mediaMetadataCompatB = this.J.b();
            this.L = mediaMetadataCompatB != null ? mediaMetadataCompatB.c() : null;
            n();
            t();
        }
    }

    private boolean r() {
        if (this.v != null || this.x || this.y) {
            return true;
        }
        return !this.n;
    }

    void f() {
        this.P = false;
        this.Q = null;
        this.R = 0;
    }

    List<u.g> h() {
        ArrayList arrayList = new ArrayList();
        for (u.g gVar : this.h.p().f()) {
            u.g.a aVarH = gVar.h();
            if (aVarH != null && aVarH.b()) {
                arrayList.add(gVar);
            }
        }
        return arrayList;
    }

    public boolean l(@NonNull u.g gVar) {
        return !gVar.v() && gVar.w() && gVar.D(this.g) && this.h != gVar;
    }

    public void m(@NonNull List<u.g> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (!l(list.get(size))) {
                list.remove(size);
            }
        }
    }

    void n() {
        MediaDescriptionCompat mediaDescriptionCompat = this.L;
        Bitmap bitmapB = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.b();
        MediaDescriptionCompat mediaDescriptionCompat2 = this.L;
        Uri uriC = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.c() : null;
        d dVar = this.M;
        Bitmap bitmapB2 = dVar == null ? this.N : dVar.b();
        d dVar2 = this.M;
        Uri uriC2 = dVar2 == null ? this.O : dVar2.c();
        if (bitmapB2 != bitmapB || (bitmapB2 == null && !androidx.core.d.a.a(uriC2, uriC))) {
            d dVar3 = this.M;
            if (dVar3 != null) {
                dVar3.cancel(true);
            }
            d dVar4 = new d();
            this.M = dVar4;
            dVar4.execute(new Void[0]);
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.o = true;
        this.e.b(this.g, this.f, 1);
        u();
        p(this.e.h());
    }

    @Override // androidx.appcompat.app.c, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.mr_cast_dialog);
        androidx.mediarouter.app.j.s(this.m, this);
        ImageButton imageButton = (ImageButton) findViewById(R$id.mr_cast_close_button);
        this.B = imageButton;
        imageButton.setColorFilter(-1);
        this.B.setOnClickListener(new b());
        Button button = (Button) findViewById(R$id.mr_cast_stop_button);
        this.C = button;
        button.setTextColor(-1);
        this.C.setOnClickListener(new c());
        this.s = new h();
        RecyclerView recyclerView = (RecyclerView) findViewById(R$id.mr_cast_list);
        this.r = recyclerView;
        recyclerView.l1(this.s);
        this.r.o1(new LinearLayoutManager(this.m));
        this.t = new j();
        this.u = new HashMap();
        this.w = new HashMap();
        this.D = (ImageView) findViewById(R$id.mr_cast_meta_background);
        this.E = findViewById(R$id.mr_cast_meta_black_scrim);
        this.F = (ImageView) findViewById(R$id.mr_cast_meta_art);
        TextView textView = (TextView) findViewById(R$id.mr_cast_meta_title);
        this.G = textView;
        textView.setTextColor(-1);
        TextView textView2 = (TextView) findViewById(R$id.mr_cast_meta_subtitle);
        this.H = textView2;
        textView2.setTextColor(-1);
        this.I = this.m.getResources().getString(R$string.mr_cast_dialog_title_view_placeholder);
        this.n = true;
        s();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.o = false;
        this.e.m(this.f);
        this.q.removeCallbacksAndMessages(null);
        p(null);
    }

    public void q(@NonNull t tVar) {
        if (tVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        if (this.g.equals(tVar)) {
            return;
        }
        this.g = tVar;
        if (this.o) {
            this.e.m(this.f);
            this.e.b(tVar, this.f, 1);
            u();
        }
    }

    void s() {
        getWindow().setLayout(androidx.mediarouter.app.g.c(this.m), androidx.mediarouter.app.g.a(this.m));
        this.N = null;
        this.O = null;
        n();
        t();
        v();
    }

    void t() {
        if (r()) {
            this.A = true;
            return;
        }
        this.A = false;
        if (!this.h.B() || this.h.v()) {
            dismiss();
        }
        if (!this.P || k(this.Q) || this.Q == null) {
            if (k(this.Q)) {
                Log.w("MediaRouteCtrlDialog", "Can't set artwork image with recycled bitmap: " + this.Q);
            }
            this.F.setVisibility(8);
            this.E.setVisibility(8);
            this.D.setImageBitmap(null);
        } else {
            this.F.setVisibility(0);
            this.F.setImageBitmap(this.Q);
            this.F.setBackgroundColor(this.R);
            this.E.setVisibility(0);
            if (Build.VERSION.SDK_INT >= 17) {
                this.D.setImageBitmap(e(this.Q, 10.0f, this.m));
            } else {
                this.D.setImageBitmap(Bitmap.createBitmap(this.Q));
            }
        }
        f();
        MediaDescriptionCompat mediaDescriptionCompat = this.L;
        CharSequence charSequenceG = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.g();
        boolean z = !TextUtils.isEmpty(charSequenceG);
        MediaDescriptionCompat mediaDescriptionCompat2 = this.L;
        CharSequence charSequenceF = mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.f() : null;
        boolean zIsEmpty = true ^ TextUtils.isEmpty(charSequenceF);
        if (z) {
            this.G.setText(charSequenceG);
        } else {
            this.G.setText(this.I);
        }
        if (!zIsEmpty) {
            this.H.setVisibility(8);
        } else {
            this.H.setText(charSequenceF);
            this.H.setVisibility(0);
        }
    }

    void u() {
        this.i.clear();
        this.j.clear();
        this.k.clear();
        this.i.addAll(this.h.k());
        for (u.g gVar : this.h.p().f()) {
            u.g.a aVarH = gVar.h();
            if (aVarH != null) {
                if (aVarH.b()) {
                    this.j.add(gVar);
                }
                if (aVarH.c()) {
                    this.k.add(gVar);
                }
            }
        }
        m(this.j);
        m(this.k);
        Collections.sort(this.i, C0032i.f905c);
        Collections.sort(this.j, C0032i.f905c);
        Collections.sort(this.k, C0032i.f905c);
        this.s.J();
    }

    void v() {
        if (this.o) {
            if (SystemClock.uptimeMillis() - this.p < 300) {
                this.q.removeMessages(1);
                this.q.sendEmptyMessageAtTime(1, this.p + 300);
            } else {
                if (r()) {
                    this.z = true;
                    return;
                }
                this.z = false;
                if (!this.h.B() || this.h.v()) {
                    dismiss();
                }
                this.p = SystemClock.uptimeMillis();
                this.s.I();
            }
        }
    }

    void w() {
        if (this.z) {
            v();
        }
        if (this.A) {
            t();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public i(Context context, int i) {
        Context contextB = androidx.mediarouter.app.j.b(context, i, false);
        super(contextB, androidx.mediarouter.app.j.c(contextB));
        this.g = t.f996c;
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.l = new ArrayList();
        this.q = new a();
        Context context2 = getContext();
        this.m = context2;
        this.e = u.g(context2);
        this.f = new g();
        this.h = this.e.j();
        this.K = new e();
        p(this.e.h());
    }
}
