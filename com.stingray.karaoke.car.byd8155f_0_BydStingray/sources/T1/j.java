package T1;

import android.os.Looper;
import androidx.emoji2.text.EmojiCompatInitializer;
import androidx.lifecycle.A;
import androidx.lifecycle.DefaultLifecycleObserver;

/* JADX INFO: loaded from: classes.dex */
public final class j implements DefaultLifecycleObserver {
    public final /* synthetic */ A9.b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ EmojiCompatInitializer f8503m;

    public j(EmojiCompatInitializer emojiCompatInitializer, A9.b bVar) {
        this.f8503m = emojiCompatInitializer;
        this.l = bVar;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onResume(A a10) {
        this.f8503m.getClass();
        b.a(Looper.getMainLooper()).postDelayed(new m(0), 500L);
        this.l.V0(this);
    }
}
