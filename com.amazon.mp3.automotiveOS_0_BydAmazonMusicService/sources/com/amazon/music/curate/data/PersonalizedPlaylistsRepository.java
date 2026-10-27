package com.amazon.music.curate.data;

import android.content.Context;
import android.view.ContextMenu;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.R;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.VisualModel;
import com.amazon.music.curate.model.VisualRowItem;
import com.amazon.music.curate.provider.Configuration;
import com.amazon.music.curate.provider.OverflowMenuProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* JADX INFO: compiled from: PersonalizedPlaylistsRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\r\u001a\u00020\u000eJX\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00102!\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u000e0\u00142!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u000e0\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/curate/data/PersonalizedPlaylistsRepository;", "", "context", "Landroid/content/Context;", "configuration", "Lcom/amazon/music/curate/provider/Configuration;", "cache", "Lcom/amazon/music/curate/data/database/PlaylistDB;", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/curate/data/PlaylistService;", "(Landroid/content/Context;Lcom/amazon/music/curate/provider/Configuration;Lcom/amazon/music/curate/data/database/PlaylistDB;Lcom/amazon/music/curate/data/PlaylistService;)V", "overflowMenuProvider", "Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "deletePersonalizedPlaylists", "", "getPersonalizedPlaylists", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lcom/amazon/music/curate/model/VisualModel;", "onClickAction", "Lkotlin/Function1;", "Lcom/amazon/music/curate/model/Playlist;", "Lkotlin/ParameterName;", "name", Environment.PLAYLIST_PATH, "onMoreClickAction", "Landroid/view/View;", "view", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PersonalizedPlaylistsRepository {
    private static final Logger LOG;
    private static final int PAGE_SIZE = 1;
    private final PlaylistDB cache;
    private final Configuration configuration;
    private final Context context;
    private final OverflowMenuProvider overflowMenuProvider;
    private final PlaylistService service;

    public PersonalizedPlaylistsRepository(Context context, Configuration configuration, PlaylistDB cache, PlaylistService service) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(service, "service");
        this.context = context;
        this.configuration = configuration;
        this.cache = cache;
        this.service = service;
        OverflowMenuProvider overflowMenuProvider = configuration.getOverflowMenuProvider();
        Intrinsics.checkNotNull(overflowMenuProvider);
        this.overflowMenuProvider = overflowMenuProvider;
    }

    static {
        Logger logger = LoggerFactory.getLogger(PersonalizedPlaylistsRepository.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(PersonalizedPl…y::class.java.simpleName)");
        LOG = logger;
    }

    public final LiveData<PagedList<VisualModel>> getPersonalizedPlaylists(final Function1<? super Playlist, Unit> onClickAction, final Function1<? super View, Unit> onMoreClickAction) {
        Intrinsics.checkNotNullParameter(onClickAction, "onClickAction");
        Intrinsics.checkNotNullParameter(onMoreClickAction, "onMoreClickAction");
        DataSource.Factory map = this.cache.getPersonalizedPlaylists().map(new Function1<Playlist, VisualModel>() { // from class: com.amazon.music.curate.data.PersonalizedPlaylistsRepository$getPersonalizedPlaylists$dataSourceFactory$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final VisualModel invoke(final Playlist playlist) {
                Intrinsics.checkNotNullParameter(playlist, "playlist");
                VisualRowItem.Builder builder = new VisualRowItem.Builder(playlist.getId(), playlist.getArtworkUrl(), playlist.getTitle());
                Context context = this.this$0.context;
                Intrinsics.checkNotNull(context);
                String string = context.getResources().getString(R.string.playlist_label);
                Intrinsics.checkNotNullExpressionValue(string, "context!!.getResources()…(R.string.playlist_label)");
                VisualRowItem.Builder builderSecondaryText = builder.secondaryText(string);
                final Function1<Playlist, Unit> function1 = onClickAction;
                VisualRowItem.Builder builderOnClick = builderSecondaryText.onClick(new Function0<Unit>() { // from class: com.amazon.music.curate.data.PersonalizedPlaylistsRepository$getPersonalizedPlaylists$dataSourceFactory$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function1.invoke(playlist);
                    }
                });
                final Function1<View, Unit> function12 = onMoreClickAction;
                VisualRowItem.Builder builderOnMoreClick = builderOnClick.onMoreClick(new Function1<View, Unit>() { // from class: com.amazon.music.curate.data.PersonalizedPlaylistsRepository$getPersonalizedPlaylists$dataSourceFactory$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(View view) {
                        invoke2(view);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(View it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        function12.invoke(it);
                    }
                });
                final PersonalizedPlaylistsRepository personalizedPlaylistsRepository = this.this$0;
                return builderOnMoreClick.onShowContextMenu(new Function2<ContextMenu, FragmentActivity, Unit>() { // from class: com.amazon.music.curate.data.PersonalizedPlaylistsRepository$getPersonalizedPlaylists$dataSourceFactory$1.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    /* JADX INFO: renamed from: invoke */
                    public /* bridge */ /* synthetic */ Unit mo4invoke(ContextMenu contextMenu, FragmentActivity fragmentActivity) {
                        invoke2(contextMenu, fragmentActivity);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ContextMenu menu, FragmentActivity activity) {
                        Intrinsics.checkNotNullParameter(menu, "menu");
                        Intrinsics.checkNotNullParameter(activity, "activity");
                        personalizedPlaylistsRepository.overflowMenuProvider.showOverflowMenu(playlist, menu, activity);
                    }
                }).expiryTime(playlist.getExpiryTime()).build();
            }
        });
        return new LivePagedListBuilder(map, 1).setBoundaryCallback(new PersonalizedPlaylistsBoundaryCallback(this.service, this.cache)).build();
    }

    public final void deletePersonalizedPlaylists() {
        this.cache.deletePersonalizedPlaylists().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.music.curate.data.PersonalizedPlaylistsRepository$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PersonalizedPlaylistsRepository.m1909deletePersonalizedPlaylists$lambda0((Unit) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deletePersonalizedPlaylists$lambda-0, reason: not valid java name */
    public static final void m1909deletePersonalizedPlaylists$lambda0(Unit unit) {
        LOG.debug("deletePersonalizedPlaylists successful");
    }
}
