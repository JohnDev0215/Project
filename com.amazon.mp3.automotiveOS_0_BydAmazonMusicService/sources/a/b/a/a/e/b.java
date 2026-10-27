package a.b.a.a.e;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public final Context d;
    public final a e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f47a = "AudioCapReceiver";
    public a.b.a.a.e.a c = a.b.a.a.e.a.b.a();
    public final BroadcastReceiver b = new C0007b();

    public interface a {
        void a(a.b.a.a.e.a aVar);
    }

    /* JADX INFO: renamed from: a.b.a.a.e.b$b, reason: collision with other inner class name */
    public final class C0007b extends BroadcastReceiver {
        public C0007b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            a.b.a.a.e.a aVarA = a.b.a.a.e.a.b.a(intent);
            String str = b.this.f47a;
            isInitialStickyBroadcast();
            Objects.toString(b.this.c);
            Objects.toString(aVarA);
            Intrinsics.areEqual(aVarA, b.this.c);
            if (!Intrinsics.areEqual(aVarA, b.this.c)) {
                b bVar = b.this;
                bVar.getClass();
                bVar.c = aVarA;
                b.this.e.a(aVarA);
            }
        }
    }

    public b(Context context, a aVar) {
        this.d = context;
        this.e = aVar;
    }

    public final a.b.a.a.e.a a() {
        a.b.a.a.e.a aVarA = a.b.a.a.e.a.b.a(this.d.registerReceiver(this.b, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG")));
        this.c = aVarA;
        return aVarA;
    }
}
