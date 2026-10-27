package spi;

import android.car.XCarRoute;
import android.car.media.IXCarMediaControl;
import android.car.utils.Logs;
import android.content.Intent;
import android.text.TextUtils;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.car.media.BaseMediaApplication;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.blankj.utilcode.util.ThreadUtils;
import com.byd.amazon.music.bean.MusicItem;
import com.byd.amazon.music.bean.MusicItemResult;
import com.byd.amazon.music.bean.Result;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: MusicVoiceControlImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class p7 {
    public static CountDownLatch b;
    public transient IXCarMediaControl a;

    public final String a(String str, List<String> list) {
        Logs.d("MusicVoiceControlImpl", "convertVodTypeToTag vodType = " + str);
        Objects.requireNonNull(str);
        switch (str) {
            case "SINGER":
                return "Artists";
            case "SONG_NAME":
                return (list == null || list.isEmpty()) ? "Playlists" : list.contains("Top Results") ? "Top Results" : list.contains("Songs") ? "Songs" : "Playlists";
            case "ALBUM":
                return "Albums";
            default:
                return "unknown_tag";
        }
    }

    public final MusicItemResult b() {
        MusicItem musicItem;
        String string;
        String string2;
        StringBuilder sb = new StringBuilder("getNowPlayingSong ");
        MusicItemResult musicItemResult = new MusicItemResult(0);
        MediaItemMetadata value = c().e.getValue();
        if (value != null) {
            String strB = value.b();
            if (TextUtils.isEmpty(value.f())) {
                string = "";
            } else {
                CharSequence charSequenceF = value.f();
                Objects.requireNonNull(charSequenceF);
                string = charSequenceF.toString();
            }
            if (TextUtils.isEmpty(value.f)) {
                string2 = "";
            } else {
                String str = value.f;
                Objects.requireNonNull(str);
                string2 = str.toString();
            }
            musicItem = new MusicItem(strB, string, string2, value.e, "");
            sb.append(" mediaId = ");
            sb.append(musicItem.a);
            sb.append(" mediaTitle = ");
            sb.append(musicItem.b);
            sb.append(" Singer = ");
            sb.append(musicItem.c);
            sb.append(" Alum = ");
            sb.append(musicItem.d);
        } else {
            sb.append(" isNull");
            musicItem = null;
        }
        musicItemResult.b = musicItem;
        Logs.d("MusicVoiceControlImpl", sb.toString());
        return musicItemResult;
    }

    public final PlaybackViewModel c() {
        return PlaybackViewModel.a(BaseMediaApplication.b, 1);
    }

    public final void d() {
        if (this.a == null) {
            this.a = (IXCarMediaControl) ARouter.getInstance().build(XCarRoute.CAR_MEDIA_CONTROL_SERVICE).navigation();
        }
    }

    public final boolean e() {
        PlaybackViewModel playbackViewModelC = c();
        return playbackViewModelC.g.getValue() != null && Boolean.TRUE.equals(playbackViewModelC.g.getValue());
    }

    public final boolean f() {
        MediaItemMetadata value = c().e.getValue();
        if (value != null) {
            String strB = value.b();
            Objects.requireNonNull(strB);
            if (strB.contains("adurl:")) {
                return true;
            }
        }
        return false;
    }

    public final int g() {
        StringBuilder sb = new StringBuilder("isAmazonWorking: ");
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int i = 1;
        b = new CountDownLatch(1);
        try {
            ThreadUtils.runOnUiThread(new h6(this, atomicInteger, sb, i));
            b.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sb.append(" tName= ");
        sb.append(Thread.currentThread().getName());
        Logs.d("MusicVoiceControlImpl", sb.toString());
        return atomicInteger.get();
    }

    public final Result h() {
        Logs.d("MusicVoiceControlImpl", "pause()");
        d();
        IXCarMediaControl iXCarMediaControl = this.a;
        if (iXCarMediaControl == null) {
            return Result.a();
        }
        iXCarMediaControl.pause();
        return Result.b();
    }

    public final void i() {
        Intent intent = new Intent();
        intent.setClassName(BaseMediaApplication.b.getPackageName(), "com.android.car.media.MediaDispatcherActivity");
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.INFO");
        intent.addCategory("android.intent.category.APP_MUSIC");
        intent.setFlags(268435456);
        BaseMediaApplication.b.startActivity(intent);
    }
}
