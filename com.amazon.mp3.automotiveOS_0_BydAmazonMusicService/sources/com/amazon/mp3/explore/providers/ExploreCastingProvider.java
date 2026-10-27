package com.amazon.mp3.explore.providers;

import android.content.Context;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.music.explore.providers.CastingProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExploreCastingProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/explore/providers/ExploreCastingProvider;", "Lcom/amazon/music/explore/providers/CastingProvider;", "", "isCasting", "", "showCastingContentUnavailable", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ExploreCastingProvider implements CastingProvider {
    private final Context context;

    public ExploreCastingProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.explore.providers.CastingProvider
    public boolean isCasting() {
        return CastingUtil.isCasting();
    }

    @Override // com.amazon.music.explore.providers.CastingProvider
    public void showCastingContentUnavailable() {
        CastingUtil.showCastingContentUnavailable(this.context);
    }
}
