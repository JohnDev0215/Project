package OooO00o.OooO00o.OooO0O0.OooO0o0;

import android.app.Application;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import kotlin.Result;
import kotlin.g;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.h;
import kotlin.l;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0OO {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static SoundPool f26c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final OooO0OO f24a = new OooO0OO();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final kotlin.a f25b = kotlin.d.b(OooO00o.f28OooO00o);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final HashMap<String, Integer> f27d = new HashMap<>();
    public static final HashMap<String, MediaPlayer> e = new HashMap<>();

    public static final class OooO00o extends Lambda implements kotlin.jvm.b.a<Application> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO00o f28OooO00o = new OooO00o();

        public OooO00o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final Application invoke() {
            return OooO00o.OooO00o.OooO0O0.a.f39a.a();
        }
    }

    public static final void b(float f, SoundPool soundPool, int i, int i2) {
        h.d(soundPool, "sp");
        if (i2 == 0) {
            soundPool.play(i, f, f, 1, 0, 1.0f);
            return;
        }
        Set<String> setKeySet = f27d.keySet();
        h.c(setKeySet, "soundMap.keys");
        for (String str : setKeySet) {
            HashMap<String, Integer> map = f27d;
            Integer num = map.get(str);
            if (num != null && num.intValue() == i) {
                h.c(str, "it");
                map.put(str, 0);
            }
        }
    }

    public final Application a() {
        return (Application) f25b.getValue();
    }

    public final void c(String str) {
        Object objM23constructorimpl;
        int identifier = a().getResources().getIdentifier(str, "raw", a().getPackageName());
        if (identifier <= 0) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("SoundPlay", h.i("rawId: ", Integer.valueOf(identifier)));
            return;
        }
        AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = a().getResources().openRawResourceFd(identifier);
        h.c(assetFileDescriptorOpenRawResourceFd, "context.resources.openRawResourceFd(rawId)");
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(6).build());
            mediaPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
            assetFileDescriptorOpenRawResourceFd.close();
            mediaPlayer.prepare();
            mediaPlayer.start();
            objM23constructorimpl = Result.m23constructorimpl(l.f4084a);
        } catch (Throwable th) {
            objM23constructorimpl = Result.m23constructorimpl(g.a(th));
        }
        if (Result.m29isSuccessimpl(objM23constructorimpl)) {
            e.put(str, mediaPlayer);
        }
        Throwable thM26exceptionOrNullimpl = Result.m26exceptionOrNullimpl(objM23constructorimpl);
        if (thM26exceptionOrNullimpl != null) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("SoundPlay", "MediaPlayer [" + str + "] failure");
            thM26exceptionOrNullimpl.printStackTrace();
        }
    }

    public final void d(String str, final float f) {
        h.d(str, "name");
        if (f26c == null) {
            if (Build.VERSION.SDK_INT >= 21) {
                SoundPool.Builder builder = new SoundPool.Builder();
                builder.setMaxStreams(1);
                AudioAttributes.Builder builder2 = new AudioAttributes.Builder();
                builder2.setLegacyStreamType(3);
                builder.setAudioAttributes(builder2.build());
                f26c = builder.build();
            } else {
                f26c = new SoundPool(1, 3, 0);
            }
        }
        SoundPool soundPool = f26c;
        if (soundPool == null) {
            return;
        }
        HashMap<String, Integer> map = f27d;
        Integer num = map.get(str);
        if (num != null && num.intValue() > 0) {
            soundPool.play(num.intValue(), f, f, 1, 0, 1.0f);
            return;
        }
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: OooO00o.OooO00o.OooO0O0.OooO0o0.a
            @Override // android.media.SoundPool.OnLoadCompleteListener
            public final void onLoadComplete(SoundPool soundPool2, int i, int i2) {
                OooO0OO.b(f, soundPool2, i, i2);
            }
        });
        try {
            OooO0OO oooO0OO = f24a;
            int identifier = oooO0OO.a().getResources().getIdentifier(str, "raw", oooO0OO.a().getPackageName());
            if (identifier <= 0) {
                return;
            }
            OooO00o.OooO00o.OooO0O0.b.f41a.d("SoundPlay", "soundPool load " + str + ", rawId = " + identifier);
            map.put(str, Integer.valueOf(soundPool.load(oooO0OO.a(), identifier, 1)));
        } catch (IOException e2) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("SoundPlay", "soundPool load " + str + " error");
            e2.printStackTrace();
        }
    }

    public final void e() {
        Collection<MediaPlayer> collectionValues = e.values();
        h.c(collectionValues, "mediaMap.values");
        for (MediaPlayer mediaPlayer : collectionValues) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
        }
        e.clear();
    }

    public final void f(String str) {
        Object objM23constructorimpl;
        h.d(str, "name");
        MediaPlayer mediaPlayer = e.get(str);
        if (mediaPlayer == null) {
            mediaPlayer = null;
        } else {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.prepare();
                }
                mediaPlayer.start();
                objM23constructorimpl = Result.m23constructorimpl(l.f4084a);
            } catch (Throwable th) {
                objM23constructorimpl = Result.m23constructorimpl(g.a(th));
            }
            Throwable thM26exceptionOrNullimpl = Result.m26exceptionOrNullimpl(objM23constructorimpl);
            if (thM26exceptionOrNullimpl != null) {
                e.put(str, null);
                OooO00o.OooO00o.OooO0O0.b.f41a.d("SoundPlay", "MediaPlayer cached [" + str + "] failure");
                thM26exceptionOrNullimpl.printStackTrace();
                f24a.c(str);
            }
        }
        if (mediaPlayer == null) {
            c(str);
        }
    }
}
