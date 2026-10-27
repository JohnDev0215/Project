package p;

import android.view.View;
import android.widget.AdapterView;
import com.spotify.automotive.baseui.settings.MusicQuality;
import com.spotify.automotive.baseui.settings.SettingsActivity;
import com.spotify.automotive.playerapi.BitrateSetting;
import com.spotify.base.java.logging.Logger;
import io.reactivex.rxjava3.core.Completable;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class gc2 implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ h6 c;
    public final /* synthetic */ SettingsActivity e;

    public gc2(SettingsActivity settingsActivity, h6 h6Var) {
        this.e = settingsActivity;
        this.c = h6Var;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        o23 o23Var;
        jc2 jc2Var;
        BitrateSetting bitrateSettingA;
        BitrateSetting bitrateSettingA2;
        io.reactivex.rxjava3.internal.operators.completable.b bVarB;
        MusicQuality musicQuality = (MusicQuality) this.c.getItem(i);
        if (musicQuality == null || (o23Var = this.e.J) == null || (bitrateSettingA = (jc2Var = (jc2) o23Var.n).a()) == (bitrateSettingA2 = musicQuality.a())) {
            return;
        }
        Logger.e("Changing music quality, old value = %s, new value = %s.", bitrateSettingA, bitrateSettingA2);
        io.reactivex.rxjava3.disposables.a aVar = (io.reactivex.rxjava3.disposables.a) o23Var.c;
        BitrateSetting bitrateSettingA3 = musicQuality.a();
        v03.j(bitrateSettingA3, "bitrate");
        if (((com.spotify.remoteconfig.a) jc2Var.d.a.get()).e()) {
            fh0 fh0Var = jc2Var.c;
            fh0Var.getClass();
            Logger.e("EsperantoPrefsApi: setStreamingBitrate(%s)", bitrateSettingA3);
            Completable completableB = fh0Var.b(he1.C(new Pair("audio.play_bitrate_enumeration", fh0.a(bitrateSettingA3.a()))));
            Logger.e("EsperantoPrefsApi: setStreamingBitrateWifi(%s)", bitrateSettingA3);
            bVarB = completableB.b(fh0Var.b(he1.C(new Pair("audio.play_bitrate_non_metered_enumeration", fh0.a(bitrateSettingA3.a())))));
        } else {
            com.spotify.playerlimited.player.models.BitrateSetting bitrateSettingA4 = ux2.a(bitrateSettingA3);
            i10 i10Var = (i10) jc2Var.b;
            i10Var.getClass();
            Logger.e("setStreamingBitrate(%s)", bitrateSettingA4);
            Completable completableE = i10Var.e(he1.C(new Pair("audio.play_bitrate_enumeration", i10.c(bitrateSettingA4.a()))));
            com.spotify.playerlimited.player.models.BitrateSetting bitrateSettingA5 = ux2.a(bitrateSettingA3);
            Logger.e("setStreamingBitrateWifi(%s)", bitrateSettingA5);
            bVarB = completableE.b(i10Var.e(he1.C(new Pair("audio.play_bitrate_non_metered_enumeration", i10.c(bitrateSettingA5.a())))));
        }
        aVar.c(bVarB.b(new io.reactivex.rxjava3.internal.operators.completable.e(1, new ic2(jc2Var, 0, bitrateSettingA3))).subscribe(new ot(2), new ed1(12)));
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
    }
}
