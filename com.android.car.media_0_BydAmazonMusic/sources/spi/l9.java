package spi;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/* JADX INFO: compiled from: ProgressLiveData.java */
/* JADX INFO: loaded from: classes.dex */
public final class l9 extends LiveData<u8> {
    public static final Set<Integer> e;
    public final PlaybackStateCompat a;
    public final long b;
    public final Handler c;
    public final Supplier<Long> d;

    static {
        HashSet hashSet = new HashSet(10);
        e = hashSet;
        hashSet.add(0);
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(6);
        hashSet.add(7);
        hashSet.add(8);
        hashSet.add(9);
        hashSet.add(10);
        hashSet.add(11);
    }

    public l9(@NonNull PlaybackStateCompat playbackStateCompat, long j) {
        this(playbackStateCompat, j, new Supplier() { // from class: spi.k9
            @Override // java.util.function.Supplier
            public final Object get() {
                return Long.valueOf(SystemClock.elapsedRealtime());
            }
        });
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.HashSet, java.util.Set<java.lang.Integer>] */
    public final void a() {
        long jMin = -1;
        if (this.a.getPosition() != -1) {
            long jLongValue = this.d.get().longValue() - this.a.getLastPositionUpdateTime();
            float playbackSpeed = this.a.getPlaybackSpeed();
            if (e.contains(Integer.valueOf(this.a.getState()))) {
                playbackSpeed = 0.0f;
            }
            jMin = Math.min(this.a.getPosition() + ((long) (jLongValue * playbackSpeed)), this.b);
        }
        setValue(new u8(jMin, this.b));
        if (this.a.getState() == 2 || this.a.getState() == 1 || this.a.getPlaybackSpeed() == 0.0f) {
            return;
        }
        this.c.postDelayed(new f2(this, 1), 500L);
    }

    @Override // androidx.lifecycle.LiveData
    public final void onActive() {
        super.onActive();
        a();
    }

    @Override // androidx.lifecycle.LiveData
    public final void onInactive() {
        super.onInactive();
        this.c.removeCallbacksAndMessages(null);
    }

    public l9(@NonNull PlaybackStateCompat playbackStateCompat, long j, Supplier<Long> supplier) {
        this.c = new Handler(Looper.getMainLooper());
        this.a = playbackStateCompat;
        this.b = j;
        this.d = supplier;
    }
}
