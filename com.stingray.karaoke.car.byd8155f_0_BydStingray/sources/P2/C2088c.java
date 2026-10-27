package p2;

import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import h2.AbstractC1343A;
import java.util.Objects;
import l3.C1764u;

/* JADX INFO: renamed from: p2.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2088c extends AudioDeviceCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C2090e f20698a;

    public C2088c(C2090e c2090e) {
        this.f20698a = c2090e;
    }

    @Override // android.media.AudioDeviceCallback
    public final void onAudioDevicesAdded(AudioDeviceInfo[] audioDeviceInfoArr) {
        C2090e c2090e = this.f20698a;
        c2090e.a(C2087b.c(c2090e.f20701a, c2090e.f20707i, c2090e.f20706h));
    }

    @Override // android.media.AudioDeviceCallback
    public final void onAudioDevicesRemoved(AudioDeviceInfo[] audioDeviceInfoArr) {
        C2090e c2090e = this.f20698a;
        C1764u c1764u = c2090e.f20706h;
        int i7 = AbstractC1343A.f15195a;
        int length = audioDeviceInfoArr.length;
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                break;
            }
            if (Objects.equals(audioDeviceInfoArr[i10], c1764u)) {
                c2090e.f20706h = null;
                break;
            }
            i10++;
        }
        c2090e.a(C2087b.c(c2090e.f20701a, c2090e.f20707i, c2090e.f20706h));
    }
}
