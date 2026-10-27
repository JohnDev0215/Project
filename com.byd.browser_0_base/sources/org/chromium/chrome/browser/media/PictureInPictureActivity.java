package org.chromium.chrome.browser.media;

import J.N;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Rational;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import defpackage.a8;
import defpackage.g3;
import defpackage.nh0;
import defpackage.q11;
import defpackage.t83;
import defpackage.xz;
import java.util.ArrayList;
import java.util.HashSet;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.BuildInfo;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.init.AsyncInitializationActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.components.browser_ui.media.R$drawable;
import org.chromium.components.thinwebview.internal.CompositorViewImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class PictureInPictureActivity extends AsyncInitializationActivity {
    public static long k0;
    public static final a l0 = new a();
    public long c0;
    public TabImpl d0;
    public c e0;
    public CompositorViewImpl f0;
    public Rational g0;
    public int h0;
    public e i0;
    public d j0;

    public class a {
        public final Bundle a(Rect rect) {
            if (!BuildInfo.a()) {
                return null;
            }
            return ActivityOptions.makeLaunchIntoPip(new PictureInPictureParams.Builder().setSourceRectHint(rect).setAspectRatio(new Rational(rect.width(), rect.height())).build()).toBundle();
        }
    }

    public class b implements View.OnLayoutChangeListener {
        public b() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            int i9;
            PictureInPictureActivity pictureInPictureActivity = PictureInPictureActivity.this;
            long j = pictureInPictureActivity.c0;
            if (j == 0 || i2 == i4 || i == i3 || (i9 = i3 - i) > pictureInPictureActivity.h0) {
                return;
            }
            N.MLM3OS4j(j, i9, i4 - i2);
        }
    }

    public class c extends nh0 {
        public c() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void D0(Tab tab, boolean z) {
            if (z) {
                a aVar = PictureInPictureActivity.l0;
                PictureInPictureActivity.this.i2(false);
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void F0(Tab tab) {
            if (tab.n()) {
                a aVar = PictureInPictureActivity.l0;
                PictureInPictureActivity.this.i2(false);
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void I0(TabImpl tabImpl) {
            a aVar = PictureInPictureActivity.l0;
            PictureInPictureActivity.this.i2(false);
        }
    }

    public class d {
        public final RemoteAction a = a(0, 2, R$drawable.ic_skip_previous_white_24dp, R$string.accessibility_previous_track, null);
        public final RemoteAction b = a(1, 0, R$drawable.ic_play_arrow_white_24dp, R$string.accessibility_play, null);
        public final RemoteAction c = a(2, 1, R$drawable.ic_pause_white_24dp, R$string.accessibility_pause, null);
        public final RemoteAction d = a(3, 0, R$drawable.ic_replay_white_24dp, R$string.accessibility_replay, null);
        public final RemoteAction e = a(4, 3, R$drawable.ic_skip_next_white_24dp, R$string.accessibility_next_track, null);
        public final RemoteAction f = a(5, 15, R$drawable.ic_call_end_white_24dp, R$string.accessibility_hang_up, null);
        public final a g;
        public final a h;
        public int i;
        public HashSet<Integer> j;

        public class a {
            public final RemoteAction a;
            public final RemoteAction b;
            public boolean c = false;

            public a(RemoteAction remoteAction, RemoteAction remoteAction2) {
                this.a = remoteAction;
                this.b = remoteAction2;
            }
        }

        public d() {
            int i = R$drawable.ic_mic_white_24dp;
            int i2 = R$string.accessibility_mute_microphone;
            Boolean bool = Boolean.TRUE;
            RemoteAction remoteActionA = a(6, 13, i, i2, bool);
            int i3 = R$drawable.ic_mic_off_white_24dp;
            int i4 = R$string.accessibility_unmute_microphone;
            Boolean bool2 = Boolean.FALSE;
            this.g = new a(remoteActionA, a(7, 13, i3, i4, bool2));
            this.h = new a(a(8, 14, R$drawable.ic_videocam_24dp, R$string.accessibility_turn_off_camera, bool), a(9, 14, R$drawable.ic_videocam_off_white_24dp, R$string.accessibility_turn_on_camera, bool2));
            this.i = 2;
            this.j = new HashSet<>();
        }

        @SuppressLint({"NewApi"})
        public final RemoteAction a(int i, int i2, int i3, int i4, Boolean bool) {
            Intent intent = new Intent("org.chromium.chrome.browser.media.PictureInPictureActivity.MediaAction");
            PictureInPictureActivity pictureInPictureActivity = PictureInPictureActivity.this;
            intent.setPackage(pictureInPictureActivity.getApplicationContext().getPackageName());
            q11.a(intent);
            intent.putExtra("org.chromium.chrome.browser.media.PictureInPictureActivity.ControlType", i2);
            intent.putExtra("org.chromium.chrome.browser.media.PictureInPictureActivity.NativePointer", pictureInPictureActivity.c0);
            if (bool != null) {
                intent.putExtra("org.chromium.chrome.browser.media.PictureInPictureActivity.ControlState", bool);
            }
            return new RemoteAction(Icon.createWithResource(pictureInPictureActivity.getApplicationContext(), i3), pictureInPictureActivity.getApplicationContext().getResources().getText(i4), "", PendingIntent.getBroadcast(pictureInPictureActivity.getApplicationContext(), i, intent, 201326592));
        }
    }

    public class e extends BroadcastReceiver {
        public e() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (q11.g(intent)) {
                long longExtra = intent.getLongExtra("org.chromium.chrome.browser.media.PictureInPictureActivity.NativePointer", 0L);
                long j = PictureInPictureActivity.this.c0;
                if (longExtra != j || j == 0 || intent.getAction() == null || !intent.getAction().equals("org.chromium.chrome.browser.media.PictureInPictureActivity.MediaAction")) {
                    return;
                }
                Boolean boolValueOf = intent.hasExtra("org.chromium.chrome.browser.media.PictureInPictureActivity.ControlState") ? Boolean.valueOf(intent.getBooleanExtra("org.chromium.chrome.browser.media.PictureInPictureActivity.ControlState", true)) : null;
                int intExtra = intent.getIntExtra("org.chromium.chrome.browser.media.PictureInPictureActivity.ControlType", -1);
                if (intExtra == 0) {
                    N.M3$g8Z3M(longExtra, true);
                    return;
                }
                if (intExtra == 1) {
                    N.M3$g8Z3M(longExtra, false);
                    return;
                }
                if (intExtra == 2) {
                    N.MJCP0RbB(longExtra);
                    return;
                }
                if (intExtra == 3) {
                    N.MBFQ8411(longExtra);
                    return;
                }
                switch (intExtra) {
                    case 13:
                        N.MN8xMwUO(longExtra, !boolValueOf.booleanValue());
                        break;
                    case 14:
                        N.MEGluScb(longExtra, !boolValueOf.booleanValue());
                        break;
                    case 15:
                        N.MX2r0gA2(longExtra);
                        break;
                }
            }
        }
    }

    @CalledByNative
    public static void createActivity(long j, Object obj, int i, int i2, int i3, int i4) {
        long j2 = k0;
        if (j2 != 0) {
            N.MrWAWBMN(j2);
        }
        k0 = j;
        Tab tab = (Tab) obj;
        WindowAndroid windowAndroidD = tab.D();
        Bundle bundleA = null;
        Context context = windowAndroidD != null ? (Context) windowAndroidD.k().get() : null;
        Context context2 = context != null ? context : xz.a;
        Intent intent = new Intent(context2, (Class<?>) PictureInPictureActivity.class);
        intent.putExtra("org.chromium.chrome.browser.media.PictureInPicture.WebContents", tab.c());
        intent.putExtra("org.chromium.chrome.browser.media.PictureInPictureActivity.NativePointer", j);
        if (i4 != 0) {
            float f = i4;
            i3 = (int) (a8.o(i3 / f, 0.41841003f, 2.39f) * f);
        }
        if (i3 > 0 && i4 > 0) {
            Rect rect = new Rect(i, i2, i + i3, i2 + i4);
            if (context != null && rect.left >= 0 && rect.top >= 0) {
                bundleA = l0.a(rect);
            }
            if (bundleA != null) {
                intent.putExtra("com.android.chrome.pictureinpicture.launched", true);
            }
            intent.putExtra("org.chromium.chrome.browser.media.PictureInPictureActivity.source.width", i3);
            intent.putExtra("org.chromium.chrome.browser.media.PictureInPictureActivity.source.height", i4);
        }
        context2.startActivity(intent, bundleA);
    }

    @CalledByNative
    public static void onWindowDestroyed(long j) {
        if (j == k0) {
            k0 = 0L;
        }
        for (Activity activity : ApplicationStatus.b()) {
            if (activity instanceof PictureInPictureActivity) {
                PictureInPictureActivity pictureInPictureActivity = (PictureInPictureActivity) activity;
                if (j == pictureInPictureActivity.c0) {
                    pictureInPictureActivity.c0 = 0L;
                    pictureInPictureActivity.i2(true);
                }
            }
        }
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public final void K() {
        super.K();
        g3 g3Var = this.O;
        this.h0 = (int) (((double) g3Var.h.c.x) * 0.95d);
        CompositorViewImpl compositorViewImpl = new CompositorViewImpl(this, g3Var, new t83());
        this.f0 = compositorViewImpl;
        addContentView(compositorViewImpl.a, new ViewGroup.LayoutParams(-1, -1));
        this.f0.a.addOnLayoutChangeListener(new b());
        N.MxJhtvhD(this.c0, this.f0);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final g3 L1() {
        return new g3((Context) this, true, (org.chromium.ui.base.c) this.M);
    }

    @Override // defpackage.nj
    public final boolean O() {
        return true;
    }

    @CalledByNative
    public void close() {
        i2(true);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void g2() {
        W1();
    }

    @SuppressLint({"NewApi"})
    public final PictureInPictureParams h2() {
        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
        d dVar = this.j0;
        dVar.getClass();
        ArrayList arrayList = new ArrayList();
        boolean z = dVar.j.contains(2) || dVar.j.contains(3);
        if (z) {
            boolean zContains = dVar.j.contains(2);
            RemoteAction remoteAction = dVar.a;
            remoteAction.setEnabled(zContains);
            arrayList.add(remoteAction);
        }
        if (dVar.j.contains(0)) {
            int i = dVar.i;
            if (i == 0) {
                arrayList.add(dVar.c);
            } else if (i == 1) {
                arrayList.add(dVar.b);
            } else if (i == 2) {
                arrayList.add(dVar.d);
            }
        }
        if (z) {
            boolean zContains2 = dVar.j.contains(3);
            RemoteAction remoteAction2 = dVar.e;
            remoteAction2.setEnabled(zContains2);
            arrayList.add(remoteAction2);
        }
        if (dVar.j.contains(13)) {
            d.a aVar = dVar.g;
            arrayList.add(aVar.c ? aVar.a : aVar.b);
        }
        if (dVar.j.contains(14)) {
            d.a aVar2 = dVar.h;
            arrayList.add(aVar2.c ? aVar2.a : aVar2.b);
        }
        if (dVar.j.contains(15)) {
            arrayList.add(dVar.f);
        }
        if (arrayList.isEmpty()) {
            RemoteAction remoteAction3 = new RemoteAction(Icon.createWithBitmap(Bitmap.createBitmap(new int[]{0}, 1, 1, Bitmap.Config.ARGB_8888)), "", "", PendingIntent.getBroadcast(PictureInPictureActivity.this.getApplicationContext(), -1, new Intent("org.chromium.chrome.browser.media.PictureInPictureActivity.MediaAction"), 67108864));
            remoteAction3.setEnabled(false);
            arrayList.add(remoteAction3);
        }
        builder.setActions(arrayList);
        builder.setAspectRatio(this.g0);
        return builder.build();
    }

    public final void i2(boolean z) {
        if (!z) {
            long j = this.c0;
            if (j != 0) {
                N.MrWAWBMN(j);
            }
        }
        CompositorViewImpl compositorViewImpl = this.f0;
        if (compositorViewImpl != null) {
            long j2 = compositorViewImpl.b;
            if (j2 != 0) {
                N.M_L66GG1(j2, compositorViewImpl);
                compositorViewImpl.b = 0L;
            }
            this.f0 = null;
        }
        e eVar = this.i0;
        if (eVar != null) {
            unregisterReceiver(eVar);
            this.i0 = null;
        }
        TabImpl tabImpl = this.d0;
        if (tabImpl != null) {
            tabImpl.t(this.e0);
            this.d0 = null;
        }
        this.e0 = null;
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        super.onPictureInPictureModeChanged(z, configuration);
        if (z) {
            return;
        }
        N.MCu7CiY_(this.c0);
        i2(false);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    @SuppressLint({"NewAPI"})
    public final void onStart() {
        super.onStart();
        Intent intent = getIntent();
        this.c0 = intent.getLongExtra("org.chromium.chrome.browser.media.PictureInPictureActivity.NativePointer", 0L);
        intent.setExtrasClassLoader(WebContents.class.getClassLoader());
        TabImpl tabImpl = (TabImpl) N.MMqeq$AW((WebContents) intent.getParcelableExtra("org.chromium.chrome.browser.media.PictureInPicture.WebContents"));
        this.d0 = tabImpl;
        if (this.c0 != k0 || TabUtils.b(tabImpl) == null) {
            i2(false);
            return;
        }
        k0 = 0L;
        c cVar = new c();
        this.e0 = cVar;
        this.d0.x(cVar);
        e eVar = new e();
        this.i0 = eVar;
        xz.f(this, eVar, new IntentFilter("org.chromium.chrome.browser.media.PictureInPictureActivity.MediaAction"));
        this.j0 = new d();
        N.MjkqYLC6(this.c0, this, this.O);
        Size size = new Size(intent.getIntExtra("org.chromium.chrome.browser.media.PictureInPictureActivity.source.width", 0), intent.getIntExtra("org.chromium.chrome.browser.media.PictureInPictureActivity.source.height", 0));
        if (size.getWidth() > 0 && size.getHeight() > 0) {
            int width = size.getWidth();
            int height = size.getHeight();
            if (height != 0) {
                float f = height;
                width = (int) (a8.o(width / f, 0.41841003f, 2.39f) * f);
            }
            this.g0 = new Rational(width, height);
        }
        if (getIntent().hasExtra("com.android.chrome.pictureinpicture.launched")) {
            return;
        }
        enterPictureInPictureMode(h2());
    }

    @CalledByNative
    public void setCameraState(boolean z) {
        this.j0.h.c = z;
        setPictureInPictureParams(h2());
    }

    @CalledByNative
    public void setMicrophoneMuted(boolean z) {
        this.j0.g.c = !z;
        setPictureInPictureParams(h2());
    }

    @CalledByNative
    public void setPlaybackState(int i) {
        this.j0.i = i;
        setPictureInPictureParams(h2());
    }

    @SuppressLint({"NewApi"})
    @CalledByNative
    public final void updateVideoSize(int i, int i2) {
        if (i2 != 0) {
            float f = i2;
            i = (int) (a8.o(i / f, 0.41841003f, 2.39f) * f);
        }
        this.g0 = new Rational(i, i2);
        setPictureInPictureParams(h2());
    }

    @CalledByNative
    public void updateVisibleActions(int[] iArr) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i : iArr) {
            hashSet.add(Integer.valueOf(i));
        }
        this.j0.j = hashSet;
        setPictureInPictureParams(h2());
    }
}
