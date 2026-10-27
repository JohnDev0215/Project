package com.amazon.music.identity.profiles;

import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import rx.Observable;

/* JADX INFO: compiled from: DataProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/identity/profiles/DataProvider;", "", "()V", "Companion", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DataProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Observable<StyleSheet> styleSheetObservable;

    /* JADX INFO: compiled from: DataProvider.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/identity/profiles/DataProvider$Companion;", "", "()V", "styleSheetObservable", "Lrx/Observable;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheetObservable", "()Lrx/Observable;", "setStyleSheetObservable", "(Lrx/Observable;)V", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Observable<StyleSheet> getStyleSheetObservable() {
            return DataProvider.styleSheetObservable;
        }

        public final void setStyleSheetObservable(Observable<StyleSheet> observable) {
            DataProvider.styleSheetObservable = observable;
        }
    }
}
