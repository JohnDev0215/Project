package p;

import android.content.ClipData;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.MediaMetadataCompat;
import android.view.ViewGroup;
import androidx.profileinstaller.ProfileInstallReceiver;
import androidx.work.impl.WorkDatabase;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.spotify.automotive.baseui.login.AAMLoginPinActivity;
import com.spotify.automotive.baseui.settings.MusicQuality;
import com.spotify.automotive.baseui.settings.SettingsActivity;
import com.spotify.automotive.playerapi.BitrateSetting;
import com.spotify.automotive.socialconnect.SocialConnectController;
import com.spotify.automotive.ubi.LoginUbiLogger$Page;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
public class oh1 implements da, wo2, fy1, ze1, dq1, wp1, d00, im1, zj, lp {
    public final /* synthetic */ int c;
    public Object e;

    public /* synthetic */ oh1(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    public final void A(Exception exc) {
        z80 z80Var = (z80) this.e;
        int i = z80.q;
        z80Var.getClass();
        if (g2.n.g(z80Var, null, new androidx.concurrent.futures.a(exc))) {
            g2.d(z80Var);
        }
    }

    public final void B(int i) {
        Object obj = this.e;
        ((ByteBuffer) obj).position(((ByteBuffer) obj).position() + i);
    }

    @Override // p.da
    public void a(int i) {
    }

    @Override // p.im1
    public final File b() {
        return ((wb2) this.e).d;
    }

    @Override // p.zj
    public final void c() {
        switch (this.c) {
            case 24:
                pd1 pd1Var = ((AAMLoginPinActivity) this.e).H;
                pd1Var.i.d();
                ((com.spotify.pinpairing.pinpairingimpl.c) pd1Var.e).c.remove(pd1Var);
                break;
            default:
                ((io.reactivex.rxjava3.disposables.a) ((SettingsActivity) this.e).J.c).d();
                break;
        }
    }

    @Override // p.wp1
    public final void d(Exception exc) {
        ((CountDownLatch) this.e).countDown();
    }

    @Override // p.im1
    public final File e() {
        return ((wb2) this.e).f;
    }

    @Override // p.lp
    public final Type f() {
        return (Type) this.e;
    }

    @Override // p.da
    public void g(int i) {
    }

    @Override // p.im1
    public final File h() {
        return ((wb2) this.e).e;
    }

    @Override // p.im1
    public final m30 i() {
        Object obj = this.e;
        if (((wb2) obj).a != null) {
            return (m30) ((wb2) obj).a.k;
        }
        return null;
    }

    @Override // p.d00
    public final /* bridge */ /* synthetic */ Object j(aj2 aj2Var) {
        return null;
    }

    @Override // p.wo2
    public final int k(Object obj) {
        return ((cw0) obj).c;
    }

    @Override // p.wo2
    public final boolean l(Object obj) {
        return ((cw0) obj).d;
    }

    @Override // p.zj
    public final void m(yj yjVar) {
        int i = 1;
        switch (this.c) {
            case 24:
                AAMLoginPinActivity aAMLoginPinActivity = (AAMLoginPinActivity) this.e;
                ad1 ad1Var = ((ek) yjVar).b.f;
                int i2 = AAMLoginPinActivity.U;
                aAMLoginPinActivity.getClass();
                new n(aAMLoginPinActivity);
                pd1 pd1Var = new pd1(aAMLoginPinActivity, new Handler(), ad1Var, new l0(aAMLoginPinActivity), new com.spotify.pinpairing.pinpairingimpl.c(), new y3(aAMLoginPinActivity, i), aAMLoginPinActivity.S, LoginUbiLogger$Page.PIN);
                aAMLoginPinActivity.H = pd1Var;
                pd1Var.a();
                aAMLoginPinActivity.H.c();
                break;
            default:
                SettingsActivity settingsActivity = (SettingsActivity) this.e;
                fk fkVar = ((ek) yjVar).b;
                settingsActivity.J = new o23(settingsActivity, fkVar.f, fkVar.g, fkVar.h, fkVar.f121p, fkVar.q.j, fkVar.m, new l0(settingsActivity), settingsActivity.U);
                o23 o23Var = ((SettingsActivity) this.e).J;
                io.reactivex.rxjava3.disposables.a aVar = (io.reactivex.rxjava3.disposables.a) o23Var.c;
                int i3 = 13;
                aVar.c(((sd1) ((rd1) o23Var.k)).a.distinctUntilChanged().filter(new lj(i3)).subscribe(new lc2(o23Var, 2), new ed1(i3)));
                aVar.c(((vv2) o23Var.l).b.d().observeOn(io.reactivex.rxjava3.android.schedulers.c.a()).subscribe(new lc2(o23Var, 3), new ed1(14)));
                boolean zI = ((com.spotify.remoteconfig.a) ((m) o23Var.f161p).a.get()).i();
                Object obj = o23Var.r;
                int i4 = 4;
                if (zI) {
                    aVar.c(((SocialConnectController) o23Var.o).getCurrentSession().observeOn(io.reactivex.rxjava3.android.schedulers.c.a()).subscribe(new lc2(o23Var, i4), new ed1(15)));
                } else {
                    ((SettingsActivity) ((mc2) obj)).L.setVisibility(8);
                }
                mc2 mc2Var = (mc2) obj;
                BitrateSetting bitrateSettingA = ((jc2) o23Var.n).a();
                MusicQuality musicQuality = MusicQuality.AUTO;
                int iOrdinal = bitrateSettingA.ordinal();
                MusicQuality musicQuality2 = iOrdinal != 1 ? iOrdinal != 2 ? iOrdinal != 3 ? iOrdinal != 4 ? MusicQuality.AUTO : MusicQuality.VERY_HIGH : MusicQuality.HIGH : MusicQuality.NORMAL : MusicQuality.LOW;
                SettingsActivity settingsActivity2 = (SettingsActivity) mc2Var;
                settingsActivity2.getClass();
                int iIndexOf = MusicQuality.n.indexOf(musicQuality2);
                if (-1 != iIndexOf) {
                    settingsActivity2.I.setSelection(iIndexOf);
                }
                break;
        }
    }

    @Override // p.fy1
    public final void n() {
    }

    @Override // p.fy1
    public final void o(int i, Object obj) {
        ux2.d.o(i, obj);
        ((ProfileInstallReceiver) this.e).setResultCode(i);
    }

    @Override // p.dq1
    public final void onSuccess(Object obj) {
        ((CountDownLatch) this.e).countDown();
    }

    @Override // p.im1
    public final File p() {
        return (File) ((wb2) this.e).a.e;
    }

    @Override // p.im1
    public final File q() {
        return ((wb2) this.e).c;
    }

    @Override // p.im1
    public final File r() {
        return ((wb2) this.e).b;
    }

    @Override // p.lp
    public final Object s(ip1 ip1Var) {
        dv dvVar = new dv(ip1Var);
        ip1Var.E(new cv(this, 1, dvVar));
        return dvVar;
    }

    public c3 t(int i) {
        return null;
    }

    public c3 u(int i) {
        return null;
    }

    public final pv0 v(ProviderInfo providerInfo, PackageManager packageManager) {
        String str = providerInfo.authority;
        String str2 = providerInfo.packageName;
        Signature[] signatureArrD = ((u11) this.e).D(packageManager, str2);
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArrD) {
            arrayList.add(signature.toByteArray());
        }
        return new pv0(str, Collections.singletonList(arrayList), str2, "emojicompat-emoji-font");
    }

    public boolean w(int i, int i2, Bundle bundle) {
        return false;
    }

    public final void x(String str, long j) {
        nb nbVar = MediaMetadataCompat.k;
        if (nbVar.containsKey(str) && ((Integer) nbVar.get(str)).intValue() != 0) {
            throw new IllegalArgumentException(u4.j("The ", str, " key cannot be used to put a long"));
        }
        ((Bundle) this.e).putLong(str, j);
    }

    public final void y(String str, String str2) {
        nb nbVar = MediaMetadataCompat.k;
        if (nbVar.containsKey(str) && ((Integer) nbVar.get(str)).intValue() != 1) {
            throw new IllegalArgumentException(u4.j("The ", str, " key cannot be used to put a String"));
        }
        ((Bundle) this.e).putCharSequence(str, str2);
    }

    public final long z() {
        return ((long) ((ByteBuffer) this.e).getInt()) & 4294967295L;
    }

    public /* synthetic */ oh1(u4 u4Var) {
        this.c = 17;
        this.e = new CountDownLatch(1);
    }

    public oh1() {
        this.c = 4;
        this.e = new LinkedHashMap(0, 0.75f, true);
    }

    public oh1(WorkDatabase workDatabase) {
        this.c = 12;
        v03.j(workDatabase, "workDatabase");
        this.e = workDatabase;
    }

    public oh1(ViewGroup viewGroup) {
        this.c = 11;
        this.e = viewGroup.getOverlay();
    }

    public oh1(Map map) {
        this.c = 13;
        this.e = map;
    }

    public oh1(Object obj) {
        Object t60Var;
        this.c = 8;
        if (Build.VERSION.SDK_INT >= 28) {
            t60Var = new u60();
        } else {
            t60Var = new t60();
        }
        this.e = t60Var;
    }

    public oh1(ByteBuffer byteBuffer) {
        this.c = 9;
        this.e = byteBuffer;
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
    }

    public oh1(ClipData clipData, int i) {
        this.c = 6;
        if (Build.VERSION.SDK_INT >= 31) {
            this.e = new fz(clipData, i);
        } else {
            this.e = new hz(clipData, i);
        }
    }

    public oh1(int i) {
        this.c = i;
        if (i == 7) {
            this.e = new f3(this);
        } else if (i != 20) {
            if (i != 23) {
                this.e = new Bundle();
            } else {
                this.e = new u6();
            }
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ oh1(MaterialButtonToggleGroup materialButtonToggleGroup) {
        this(14, materialButtonToggleGroup);
        this.c = 14;
    }
}
