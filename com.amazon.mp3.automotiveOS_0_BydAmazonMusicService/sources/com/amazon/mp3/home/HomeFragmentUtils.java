package com.amazon.mp3.home;

import android.content.Context;
import com.amazon.mp3.navigation.PlaylistDestinationHandler;
import com.amazon.mp3.util.coroutines.LookupError;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.destination.PlaylistDestination;
import com.amazon.music.destination.parser.Action;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeFragmentUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J<\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00040\fH\u0007¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/home/HomeFragmentUtils;", "", "()V", "navigateToPlaylistDetail", "", "context", "Landroid/content/Context;", "playlistMetadata", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "", "lookupError", "Lkotlin/Function1;", "Lcom/amazon/mp3/util/coroutines/LookupError;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HomeFragmentUtils {
    public static final HomeFragmentUtils INSTANCE = new HomeFragmentUtils();

    private HomeFragmentUtils() {
    }

    @JvmStatic
    public static final void navigateToPlaylistDetail(Context context, PlaylistMetadata playlistMetadata, String trackAsin, Function1<? super LookupError, Unit> lookupError) {
        String asin;
        Intrinsics.checkNotNullParameter(lookupError, "lookupError");
        if (context == null || playlistMetadata == null || (asin = playlistMetadata.getAsin()) == null) {
            return;
        }
        new PlaylistDestinationHandler(lookupError).navigateTo(context, new PlaylistDestination(asin, trackAsin, trackAsin != null ? Action.PLAY : null, null, null, null));
    }

    @JvmStatic
    public static final void navigateToPlaylistDetail(final Context context, PlaylistMetadata playlistMetadata, String trackAsin) {
        Intrinsics.checkNotNullParameter(playlistMetadata, "playlistMetadata");
        navigateToPlaylistDetail(context, playlistMetadata, trackAsin, new Function1<LookupError, Unit>() { // from class: com.amazon.mp3.home.HomeFragmentUtils.navigateToPlaylistDetail.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LookupError lookupError) {
                invoke2(lookupError);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final LookupError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                Context context2 = context;
                if (context2 == null) {
                    return;
                }
                ContextKt.runOnUiThread$default(context2, 0L, new Function1<Context, Unit>() { // from class: com.amazon.mp3.home.HomeFragmentUtils.navigateToPlaylistDetail.2.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Context context3) {
                        invoke2(context3);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Context it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        error.handleError(new Function0<Unit>() { // from class: com.amazon.mp3.home.HomeFragmentUtils.navigateToPlaylistDetail.2.1.1
                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }
                        });
                    }
                }, 1, null);
            }
        });
    }
}
