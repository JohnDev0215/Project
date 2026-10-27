package com.amazon.music.find.viewmodels;

import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.music.destination.FindDestination;
import com.amazon.music.find.R;
import com.amazon.music.find.api.searchsuggestion.SearchSuggestionResponse;
import com.amazon.music.find.featuregating.FindFeatureGating;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.metrics.UiEventMetadata;
import com.amazon.music.find.model.ContentMetadataPlaybackState;
import com.amazon.music.find.model.EntityMetaData;
import com.amazon.music.find.model.FindPageGridViewModel;
import com.amazon.music.find.model.SearchSuggestion;
import com.amazon.music.find.model.configuration.SearchSuggestionConfiguration;
import com.amazon.music.find.model.deeplink.HandleDeeplinkResult;
import com.amazon.music.find.model.event.SearchViewQueryTextEvent;
import com.amazon.music.find.model.filter.FilterElement;
import com.amazon.music.find.model.filter.FilterMetadata;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.model.search.ContentOwnershipItem;
import com.amazon.music.find.model.search.SearchRequest;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.SeeMoreRequest;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.ConverterService;
import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.find.service.ExecutionContextDefault;
import com.amazon.music.find.service.LibraryStateService;
import com.amazon.music.find.service.SearchHistoryService;
import com.amazon.music.find.service.SearchService;
import com.amazon.music.find.service.SearchSuggestionService;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.find.utils.SearchResponseUtils;
import com.amazon.music.metrics.mts.event.types.ActivityIdType;
import com.amazon.music.metrics.mts.event.types.ResponseIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.search.SpellCorrections;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.NavigationMetadata;
import com.amazon.music.views.library.metadata.SearchQueryMetadata;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.LinkNavigatorModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PillCellModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.enums.LinkNavigatorType;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.models.enums.ViewType;
import com.amazon.music.views.library.providers.ItemOrdinalProvider;
import com.amazon.music.voice.launcher.CannotHandle;
import com.amazon.music.voice.launcher.LaunchTargetError;
import com.amazon.music.voice.launcher.LauncherTokens;
import com.amazon.musicensembleservice.brush.Page;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u009a\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0016\u0018\u0000 \u0099\u00022\u00020\u00012\u00020\u0002:\b\u0098\u0002\u0099\u0002\u009a\u0002\u009b\u0002BS\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0002\u0010\u0016J\u0006\u0010S\u001a\u00020@J\u001e\u0010T\u001a\b\u0012\u0004\u0012\u00020V0U2\u000e\u0010W\u001a\n\u0012\u0004\u0012\u00020V\u0018\u00010UH\u0002J\u0010\u0010X\u001a\u00020#2\u0006\u0010Y\u001a\u00020\u0018H\u0007J\u000e\u0010Z\u001a\u00020#2\u0006\u0010[\u001a\u00020@J\u0006\u0010\\\u001a\u00020#J\u0006\u0010]\u001a\u00020#J\u0006\u0010^\u001a\u00020#J\u0006\u0010_\u001a\u00020`J\u0012\u0010a\u001a\u00020@2\b\u0010b\u001a\u0004\u0018\u00010<H\u0002J@\u0010c\u001a\u0004\u0018\u00010d2\u0006\u0010e\u001a\u00020f2\b\u0010g\u001a\u0004\u0018\u00010h2\b\u0010i\u001a\u0004\u0018\u00010<2\u0006\u0010j\u001a\u00020@2\u0006\u0010k\u001a\u00020@2\b\b\u0002\u0010l\u001a\u00020@H\u0002J0\u0010m\u001a\u0004\u0018\u00010d2\u0006\u0010e\u001a\u00020f2\b\u0010n\u001a\u0004\u0018\u00010d2\u0006\u0010o\u001a\u00020\u00182\n\b\u0002\u0010i\u001a\u0004\u0018\u00010<H\u0007J\u0006\u0010p\u001a\u00020#J\u0016\u0010q\u001a\b\u0012\u0004\u0012\u00020d0\u001a2\u0006\u0010e\u001a\u00020fH\u0002J\u0012\u0010r\u001a\u00020#2\n\b\u0002\u0010s\u001a\u0004\u0018\u00010tJ\u0006\u0010u\u001a\u00020#J\u001a\u0010v\u001a\u00020#2\b\u0010i\u001a\u0004\u0018\u00010<2\b\u0010w\u001a\u0004\u0018\u00010xJ\u001a\u0010y\u001a\u00020#2\b\u0010i\u001a\u0004\u0018\u00010<2\b\u0010b\u001a\u0004\u0018\u00010<J4\u0010z\u001a\u00020#2\u0006\u0010{\u001a\u00020|2\b\u0010b\u001a\u0004\u0018\u00010<2\b\b\u0002\u0010}\u001a\u00020~2\n\b\u0002\u0010\u007f\u001a\u0004\u0018\u00010@¢\u0006\u0003\u0010\u0080\u0001J\u0011\u0010\u0081\u0001\u001a\u00020#2\u0006\u0010e\u001a\u00020fH\u0002JI\u0010\u0082\u0001\u001a\u00020#2\r\u0010\u0083\u0001\u001a\b\u0012\u0004\u0012\u00020'0U2\r\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020<0U2 \u0010\u0085\u0001\u001a\u001b\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0087\u00010U\u0012\u0004\u0012\u00020#0\u0086\u0001j\u0003`\u0088\u0001H\u0016J\u0011\u0010\u0089\u0001\u001a\u00020#2\u0006\u0010e\u001a\u00020fH\u0002JR\u0010\u008a\u0001\u001a\u00020#2\u0006\u0010e\u001a\u00020f2\u0006\u0010i\u001a\u00020<2\b\u0010w\u001a\u0004\u0018\u00010x2\u0007\u0010\u008b\u0001\u001a\u00020`2\u0006\u0010j\u001a\u00020@2\u0006\u0010k\u001a\u00020@2\t\u0010\u008c\u0001\u001a\u0004\u0018\u00010<2\t\b\u0002\u0010\u008d\u0001\u001a\u00020@H\u0002J\u0010\u0010\u008e\u0001\u001a\u00020#2\u0007\u0010\u008f\u0001\u001a\u000209JJ\u0010\u0090\u0001\u001a\u00020#2\u0006\u0010e\u001a\u00020f2\u0006\u0010i\u001a\u00020<2\u0006\u0010{\u001a\u00020|2\u0007\u0010\u0091\u0001\u001a\u0002092\u000b\b\u0002\u0010\u0092\u0001\u001a\u0004\u0018\u00010<2\u0006\u0010j\u001a\u00020@2\t\u0010\u008c\u0001\u001a\u0004\u0018\u00010<H\u0002J\u0019\u0010\u0093\u0001\u001a\u00020#2\u0006\u0010e\u001a\u00020f2\u0006\u0010i\u001a\u00020<H\u0002J\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bJ\t\u0010\u0094\u0001\u001a\u00020\u001bH\u0002J\u0011\u0010\u0095\u0001\u001a\u00020\u001b2\u0006\u0010j\u001a\u00020@H\u0002J\t\u0010\u0096\u0001\u001a\u00020\u001bH\u0002J\t\u0010\u0097\u0001\u001a\u00020\u001bH\u0002J\t\u0010\u0098\u0001\u001a\u00020dH\u0002J\u0014\u0010\u0099\u0001\u001a\u00020\u00182\u000b\b\u0002\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u0018J\u000e\u0010\u009b\u0001\u001a\t\u0012\u0005\u0012\u00030\u009c\u00010UJ\u0011\u0010\u009d\u0001\u001a\u00020`2\u0006\u0010j\u001a\u00020@H\u0002J\u0007\u0010\u009e\u0001\u001a\u00020@J;\u0010\u009f\u0001\u001a\u00030 \u00012\t\u0010¡\u0001\u001a\u0004\u0018\u00010\u00182\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u00182\t\u0010£\u0001\u001a\u0004\u0018\u0001092\b\u0010F\u001a\u0004\u0018\u00010<H\u0002¢\u0006\u0003\u0010¤\u0001J\u000f\u0010¥\u0001\u001a\u0004\u0018\u000109¢\u0006\u0003\u0010¦\u0001J\u0017\u0010§\u0001\u001a\b\u0012\u0004\u0012\u00020d0\u001a2\u0006\u0010e\u001a\u00020fH\u0002JV\u0010¨\u0001\u001a\b\u0012\u0004\u0012\u00020d0\u001a2\u0006\u0010e\u001a\u00020f2\u0006\u0010i\u001a\u00020<2\b\u0010w\u001a\u0004\u0018\u00010x2\u0007\u0010\u008d\u0001\u001a\u00020@2\u0007\u0010\u008b\u0001\u001a\u00020`2\u0006\u0010j\u001a\u00020@2\u0006\u0010k\u001a\u00020@2\t\u0010\u008c\u0001\u001a\u0004\u0018\u00010<H\u0002JX\u0010©\u0001\u001a\b\u0012\u0004\u0012\u00020d0\u001a2\u0006\u0010e\u001a\u00020f2\u0006\u0010i\u001a\u00020<2\b\u0010w\u001a\u0004\u0018\u00010x2\u0007\u0010\u008b\u0001\u001a\u00020`2\u0006\u0010{\u001a\u00020|2\t\u0010\u0092\u0001\u001a\u0004\u0018\u00010<2\u0006\u0010j\u001a\u00020@2\t\u0010\u008c\u0001\u001a\u0004\u0018\u00010<H\u0002J\u000b\u0010ª\u0001\u001a\u0004\u0018\u00010<H\u0002J\u001f\u0010«\u0001\u001a\b\u0012\u0004\u0012\u00020d0\u001a2\u0006\u0010e\u001a\u00020f2\u0006\u0010i\u001a\u00020<H\u0007J\f\u0010¬\u0001\u001a\u0005\u0018\u00010\u00ad\u0001H\u0016J\u0007\u0010®\u0001\u001a\u00020#J_\u0010¯\u0001\u001a\u00020#2\b\u0010°\u0001\u001a\u00030±\u00012\b\u0010²\u0001\u001a\u00030³\u00012B\u0010´\u0001\u001a=\u0012\u0019\u0012\u0017\u0018\u00010¶\u0001¢\u0006\u000f\b·\u0001\u0012\n\b¸\u0001\u0012\u0005\b\b(¹\u0001\u0012\u0017\u0012\u00150³\u0001¢\u0006\u000f\b·\u0001\u0012\n\b¸\u0001\u0012\u0005\b\b(²\u0001\u0012\u0004\u0012\u00020#0µ\u0001Jw\u0010º\u0001\u001a\u00020#2\b\u0010²\u0001\u001a\u00030³\u00012B\u0010´\u0001\u001a=\u0012\u0019\u0012\u0017\u0018\u00010¶\u0001¢\u0006\u000f\b·\u0001\u0012\n\b¸\u0001\u0012\u0005\b\b(¹\u0001\u0012\u0017\u0012\u00150³\u0001¢\u0006\u000f\b·\u0001\u0012\n\b¸\u0001\u0012\u0005\b\b(²\u0001\u0012\u0004\u0012\u00020#0µ\u00012\u0007\u0010»\u0001\u001a\u00020@2\t\u0010¼\u0001\u001a\u0004\u0018\u00010<2\n\u0010½\u0001\u001a\u0005\u0018\u00010¾\u0001H\u0002J\u0011\u0010¿\u0001\u001a\u00020#2\b\u0010À\u0001\u001a\u00030Á\u0001Jl\u0010Â\u0001\u001a\u00030Ã\u00012\b\u0010²\u0001\u001a\u00030³\u00012B\u0010´\u0001\u001a=\u0012\u0019\u0012\u0017\u0018\u00010¶\u0001¢\u0006\u000f\b·\u0001\u0012\n\b¸\u0001\u0012\u0005\b\b(¹\u0001\u0012\u0017\u0012\u00150³\u0001¢\u0006\u000f\b·\u0001\u0012\n\b¸\u0001\u0012\u0005\b\b(²\u0001\u0012\u0004\u0012\u00020#0µ\u00012\b\u0010Ä\u0001\u001a\u00030Å\u00012\b\u0010½\u0001\u001a\u00030¾\u0001H\u0002J\u0011\u0010Æ\u0001\u001a\u00020#2\u0006\u0010e\u001a\u00020fH\u0014Jj\u0010Ç\u0001\u001a\u00030Ã\u00012\b\u0010²\u0001\u001a\u00030³\u00012B\u0010´\u0001\u001a=\u0012\u0019\u0012\u0017\u0018\u00010¶\u0001¢\u0006\u000f\b·\u0001\u0012\n\b¸\u0001\u0012\u0005\b\b(¹\u0001\u0012\u0017\u0012\u00150³\u0001¢\u0006\u000f\b·\u0001\u0012\n\b¸\u0001\u0012\u0005\b\b(²\u0001\u0012\u0004\u0012\u00020#0µ\u00012\u0006\u0010F\u001a\u00020<2\b\u0010½\u0001\u001a\u00030¾\u0001H\u0002Jj\u0010È\u0001\u001a\u00030Ã\u00012\b\u0010²\u0001\u001a\u00030³\u00012B\u0010´\u0001\u001a=\u0012\u0019\u0012\u0017\u0018\u00010¶\u0001¢\u0006\u000f\b·\u0001\u0012\n\b¸\u0001\u0012\u0005\b\b(¹\u0001\u0012\u0017\u0012\u00150³\u0001¢\u0006\u000f\b·\u0001\u0012\n\b¸\u0001\u0012\u0005\b\b(²\u0001\u0012\u0004\u0012\u00020#0µ\u00012\u0006\u0010F\u001a\u00020<2\b\u0010½\u0001\u001a\u00030¾\u0001H\u0002J\u0007\u0010É\u0001\u001a\u00020@J\u0007\u0010Ê\u0001\u001a\u00020#JG\u0010Ë\u0001\u001a\u00020#2\u0006\u0010e\u001a\u00020f2\u0006\u0010o\u001a\u00020\u00182\u0007\u0010Ì\u0001\u001a\u00020<2\u0007\u0010Í\u0001\u001a\u0002092\b\u0010Î\u0001\u001a\u00030Ï\u00012\u0010\u0010Ð\u0001\u001a\u000b\u0012\u0005\u0012\u00030Ñ\u0001\u0018\u00010UH\u0002J\u0007\u0010Ò\u0001\u001a\u00020@J\u0006\u0010j\u001a\u00020@J\t\u0010Ó\u0001\u001a\u00020#H\u0002J\u001b\u0010Ô\u0001\u001a\u00020#2\b\u0010Õ\u0001\u001a\u00030Ö\u00012\u0006\u0010o\u001a\u00020\u0018H\u0002J\u001d\u0010×\u0001\u001a\u00020#2\b\u0010Õ\u0001\u001a\u00030Ø\u00012\b\u0010i\u001a\u0004\u0018\u00010<H\u0002J\t\u0010Ù\u0001\u001a\u00020#H\u0016J\t\u0010Ú\u0001\u001a\u00020#H\u0016J\u0007\u0010Û\u0001\u001a\u00020#J+\u0010Ü\u0001\u001a\u00020@2\u000f\u0010Ý\u0001\u001a\n\u0012\u0005\u0012\u00030ß\u00010Þ\u00012\t\u0010à\u0001\u001a\u0004\u0018\u00010'H\u0002¢\u0006\u0003\u0010á\u0001J#\u0010â\u0001\u001a\u00020#2\u0006\u0010Y\u001a\u00020\u00182\u0007\u0010ã\u0001\u001a\u00020@2\u0007\u0010ä\u0001\u001a\u00020@H\u0002J\u0019\u0010å\u0001\u001a\u00020#2\u0007\u0010ã\u0001\u001a\u00020@2\u0007\u0010ä\u0001\u001a\u00020@J\u0019\u0010æ\u0001\u001a\u00020#2\u0007\u0010ã\u0001\u001a\u00020@2\u0007\u0010ä\u0001\u001a\u00020@J\u0007\u0010ç\u0001\u001a\u00020#J\u0013\u0010è\u0001\u001a\u0002092\b\u0010Ä\u0001\u001a\u00030Å\u0001H\u0016J<\u0010é\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0087\u00010U0\u001a2\u0006\u0010e\u001a\u00020f2\r\u0010\u0083\u0001\u001a\b\u0012\u0004\u0012\u00020'0U2\r\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020<0UH\u0002J\t\u0010ê\u0001\u001a\u00020#H\u0002J \u0010ë\u0001\u001a\u00020#2\u000e\u0010ì\u0001\u001a\t\u0012\u0005\u0012\u00030Å\u00010U2\u0007\u0010í\u0001\u001a\u000209J\u0012\u0010î\u0001\u001a\u00020#2\t\u0010ï\u0001\u001a\u0004\u0018\u00010\u0018J\u0010\u0010ð\u0001\u001a\u00020#2\u0007\u0010ï\u0001\u001a\u000209J\u0012\u0010ñ\u0001\u001a\u00020#2\t\u0010ï\u0001\u001a\u0004\u0018\u00010<J\u0012\u0010ò\u0001\u001a\u00020#2\t\u0010ó\u0001\u001a\u0004\u0018\u00010EJ\u0007\u0010ô\u0001\u001a\u00020@J\t\u0010õ\u0001\u001a\u00020#H\u0002J\t\u0010ö\u0001\u001a\u00020#H\u0002J\u0018\u0010÷\u0001\u001a\u00030ø\u00012\u000e\u0010ù\u0001\u001a\t\u0012\u0005\u0012\u00030ú\u00010\u001aJ\u0007\u0010û\u0001\u001a\u00020#J\u0007\u0010ü\u0001\u001a\u00020#J\u0010\u0010ý\u0001\u001a\u00020#2\u0007\u0010þ\u0001\u001a\u00020<J\u0007\u0010ÿ\u0001\u001a\u00020#JA\u0010\u0080\u0002\u001a\u00020#2\u0007\u0010\u0081\u0002\u001a\u00020\u00182\b\u0010\u0082\u0002\u001a\u00030\u0083\u00022\n\u0010Ä\u0001\u001a\u0005\u0018\u00010Å\u00012\u0006\u0010}\u001a\u00020~2\u000b\b\u0002\u0010\u0084\u0002\u001a\u0004\u0018\u000109¢\u0006\u0003\u0010\u0085\u0002J\u0007\u0010\u0086\u0002\u001a\u00020#J\u001d\u0010\u0087\u0002\u001a\u00020#2\t\u0010\u0088\u0002\u001a\u0004\u0018\u00010<2\t\u0010\u0089\u0002\u001a\u0004\u0018\u00010<J7\u0010\u008a\u0002\u001a\u00020#2\u0007\u0010\u0081\u0002\u001a\u00020\u00182\n\u0010\u008b\u0002\u001a\u0005\u0018\u00010\u008c\u00022\u0006\u0010}\u001a\u00020~2\u000b\b\u0002\u0010\u0084\u0002\u001a\u0004\u0018\u000109¢\u0006\u0003\u0010\u008d\u0002J\u001b\u0010\u008e\u0002\u001a\u00020#2\u0007\u0010\u0081\u0002\u001a\u00020\u00182\t\u0010\u0084\u0002\u001a\u0004\u0018\u00010<J\u0011\u0010\u008f\u0002\u001a\u00020#2\u0006\u0010\u0019\u001a\u00020\u001bH\u0002J\u0010\u0010\u0090\u0002\u001a\u00020#2\u0007\u0010\u0091\u0002\u001a\u00020`J\t\u0010\u0092\u0002\u001a\u00020#H\u0002J\u0010\u0010\u0093\u0002\u001a\u00020#2\u0007\u0010\u0094\u0002\u001a\u00020@J\t\u0010\u0095\u0002\u001a\u00020#H\u0002J\u0013\u0010\u0096\u0002\u001a\u00020#2\b\u0010\u0097\u0002\u001a\u00030 \u0001H\u0002R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001a8F¢\u0006\u0006\u001a\u0004\b \u0010\u001dR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"8F¢\u0006\u0006\u001a\u0004\b$\u0010%R\u001d\u0010&\u001a\u0004\u0018\u00010'8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b(\u0010)R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001b0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010.\u001a\b\u0012\u0004\u0012\u00020\u001f0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010/\u001a\u0002008\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b1\u00102\u001a\u0004\b3\u00104R\u0010\u00105\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00106\u001a\b\u0012\u0004\u0012\u00020#0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u00108\u001a\u0004\u0018\u000109X\u0082\u000e¢\u0006\u0004\n\u0002\u0010:R\u0010\u0010;\u001a\u0004\u0018\u00010<X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010=\u001a\u0004\u0018\u000109X\u0082\u000e¢\u0006\u0004\n\u0002\u0010:R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020<0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020@X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010B\u001a\b\u0012\u0004\u0012\u00020@0CX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010F\u001a\u0004\u0018\u00010<8F¢\u0006\u0006\u001a\u0004\bG\u0010HR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010I\u001a\b\u0012\u0004\u0012\u00020<0\u001a8F¢\u0006\u0006\u001a\u0004\bJ\u0010\u001dR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010K\u001a\u0004\u0018\u00010<8F¢\u0006\u0006\u001a\u0004\bL\u0010HR\u0017\u0010M\u001a\b\u0012\u0004\u0012\u00020@0\u001a8F¢\u0006\u0006\u001a\u0004\bN\u0010\u001dR\u0017\u0010O\u001a\b\u0012\u0004\u0012\u00020@0P8F¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u009c\u0002"}, d2 = {"Lcom/amazon/music/find/viewmodels/SearchViewModel;", "Lcom/amazon/music/find/viewmodels/BasePageGridViewModel;", "Lcom/amazon/music/views/library/providers/ItemOrdinalProvider;", "pageGridConverter", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/musicensembleservice/brush/Page;", "searchSuggestionService", "Lcom/amazon/music/find/service/SearchSuggestionService;", "searchHistoryService", "Lcom/amazon/music/find/service/SearchHistoryService;", "converterService", "Lcom/amazon/music/find/service/ConverterService;", "searchService", "Lcom/amazon/music/find/service/SearchService;", "searchMetricsService", "Lcom/amazon/music/find/metrics/SearchMetricsService;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "libraryStateService", "Lcom/amazon/music/find/service/LibraryStateService;", "(Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;Lcom/amazon/music/find/service/SearchSuggestionService;Lcom/amazon/music/find/service/SearchHistoryService;Lcom/amazon/music/find/service/ConverterService;Lcom/amazon/music/find/service/SearchService;Lcom/amazon/music/find/metrics/SearchMetricsService;Lcom/amazon/music/find/providers/SearchFeaturesProvider;Lcom/amazon/music/find/service/UIConfigurationService;Lcom/amazon/music/find/service/LibraryStateService;)V", "DEFAULT_SEARCH_EXPERIENCE_MODE", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "actionBarState", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/music/find/viewmodels/SearchViewModel$ActionBarState;", "getActionBarState", "()Lio/reactivex/rxjava3/core/Observable;", "contentMetadataPlaybackStateUpdated", "Lcom/amazon/music/find/model/ContentMetadataPlaybackState;", "getContentMetadataPlaybackStateUpdated", "externalNavigationRequested", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "", "getExternalNavigationRequested", "()Lio/reactivex/rxjava3/subjects/PublishSubject;", "invalidateRecycleViewDelayedDuration", "", "getInvalidateRecycleViewDelayedDuration", "()Ljava/lang/Long;", "invalidateRecycleViewDelayedDuration$delegate", "Lkotlin/Lazy;", "mActionBarState", "mContentMetadataPlaybackState", "mContentMetadataPlaybackStateUpdated", "mData", "Lcom/amazon/music/find/viewmodels/SearchViewModel$SearchViewModelData;", "getMData$annotations", "()V", "getMData", "()Lcom/amazon/music/find/viewmodels/SearchViewModel$SearchViewModelData;", "mExperienceMode", "mExternalNavigationRequested", "mInitialExperienceMode", "mNewScreenOrientation", "", "Ljava/lang/Integer;", "mPageUri", "", "mScreenOrientation", "mSearchQueryTextUpdated", "mSearchViewFocusUpdated", "", "mSearchViewFocused", "mShouldShowLoadingSpinner", "Landroidx/lifecycle/MutableLiveData;", "mVoiceContentMetadataHandlerFactory", "Lcom/amazon/music/find/viewmodels/ContentMetadataHandlerFactory;", "pageUri", "getPageUri", "()Ljava/lang/String;", "searchQueryTextUpdated", "getSearchQueryTextUpdated", "searchTerm", "getSearchTerm", "searchViewFocusUpdated", "getSearchViewFocusUpdated", "shouldShowLoadingSpinner", "Landroidx/lifecycle/LiveData;", "getShouldShowLoadingSpinner", "()Landroidx/lifecycle/LiveData;", "actionBarStateHasObservers", "addRemoveButton", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "parentModels", "changeSearchExperienceMode", "toMode", "changeSearchFocus", "hasFocus", "clearSearchHistory", "clearSearchQuery", "clearTrackPlaybackState", "cloneFilterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "containsBlockRef", "viewId", "convertSearchResponse", "Lcom/amazon/music/find/model/FindPageGridViewModel;", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "searchResponse", "Lcom/amazon/music/find/model/search/SearchResponse;", SearchIntents.EXTRA_QUERY, "isOffline", "showOrdinal", "showSpellCorrections", "customizePageGridViewModel", "pageModel", "searchExperienceMode", "deleteExpiredHistory", "deleteSearchHistory", "executeSearchFromMetadata", "searchQueryMetadata", "Lcom/amazon/music/views/library/metadata/SearchQueryMetadata;", "executeSearchFromQuery", "executeSearchFromSpellCorrection", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "executeSearchFromTerm", "executeSeeMore", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "showOrdinals", "(Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;Ljava/lang/Boolean;)V", "fetchDeleteSearchHistoryPage", "fetchRequestState", "trackIds", "trackAsins", "callback", "Lkotlin/Function1;", "Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "Lcom/amazon/music/find/model/search/ContentOwnershipCallback;", "fetchSearchHistoryPageData", "fetchSearchResultsPageData", "searchFilterSelections", PageUriUtils.QUERYMETADATA_QUERY_PARAM, "enableSpellCorrections", "fetchSeeMoreNextPage", "page", "fetchSeeMorePageData", "pageIndex", "pageToken", "fetchSuggestionPageData", "getActionBarStateForSearchHistory", "getActionBarStateForSearchResults", "getActionBarStateForSearchSuggestion", "getActionBarStateForSeeMore", "getEmptySearchHistoryPageData", "getExperienceModeOrDefault", "defaultValue", "getFilterItemModels", "Lcom/amazon/music/views/library/models/FilterItemModel;", "getFilterSelections", "getIsTrackSearch", "getResumeContextDataFromModeAndPageUri", "Lcom/amazon/music/find/viewmodels/SearchViewModel$ResumeContextData;", "currentMode", "newMode", "newScreenOrientation", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Integer;Ljava/lang/String;)Lcom/amazon/music/find/viewmodels/SearchViewModel$ResumeContextData;", "getScreenOrientation", "()Ljava/lang/Integer;", "getSearchHistoryPageData", "getSearchResultsPageData", "getSeeMoreResultsPageData", "getSeeMoreTitle", "getSuggestionPageData", "getUiPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "goBack", "handleDeeplink", "uri", "Landroid/net/Uri;", "launcherTokens", "Lcom/amazon/music/voice/launcher/LauncherTokens;", "onComplete", "Lkotlin/Function2;", "Lcom/amazon/music/voice/launcher/LaunchTargetError;", "Lkotlin/ParameterName;", "name", "launchTargetError", "handleDeeplinkOnComplete", "isSuccess", "failureMessage", "action", "Lcom/amazon/music/find/viewmodels/SearchAction;", "handleDestination", "findDestination", "Lcom/amazon/music/destination/FindDestination;", "handleEntityActionDeeplink", "Lcom/amazon/music/find/model/deeplink/HandleDeeplinkResult;", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "handleOperationCompleted", "handlePlaybackDeeplink", "handleSeeMoreDeeplink", "hasFilterItemModels", "initiateSearch", "injectContentInfo", ContentUtils.KEY_EVENT_ID, "pos", "containerModel", "Lcom/amazon/music/views/library/models/SectionGridViewModel;", "suggestions", "Lcom/amazon/music/find/model/SearchSuggestion;", "isLibrary", "markDeeplinkAsHandled", "modifyHorizontalItems", "model", "Lcom/amazon/music/views/library/models/HorizontalTileModel;", "modifyLinkNavigator", "Lcom/amazon/music/views/library/models/LinkNavigatorModel;", "onRefresh", "onResume", "openFilterDialog", "pageShouldBeUpdated", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/music/find/viewmodels/PageDataShouldBeUpdatedEvent;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "fetchRequestedAt", "(Lcom/amazon/music/find/viewmodels/PageDataShouldBeUpdatedEvent;Ljava/lang/Long;)Z", "refreshPage", "force", "resetScrollPosition", "refreshSearch", "refreshSeeMore", "refreshSuggestions", "registerItem", "requestState", "resumeFromContextData", "saveTrackPlaybackState", "contentMetadataList", "positionToStart", "setInitialExperienceMode", "value", "setNewScreenOrientation", "setPageUri", "setVoiceContentMetadataHandlerFactory", "contentMetadataHandlerFactory", "showIvyRefinementPills", "showSearchHistory", "showSuggestions", "subscribeToSearchViewQueryTextChange", "Lio/reactivex/rxjava3/disposables/Disposable;", "observable", "Lcom/amazon/music/find/model/event/SearchViewQueryTextEvent;", "syncDefaultProfileHistory", "toggleExposedRefinements", "toggleFilterSelection", "filterId", "trackClearSearchHistoryUiClickEvent", "trackContentMetadataUiClickEvent", "mode", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "entityPos", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;Lcom/amazon/music/views/library/metadata/ContentMetadata;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;Ljava/lang/Integer;)V", "trackFindHomeUiPageView", "trackGenericUiClickEvent", ImagesContract.URL, "blockRef", "trackNavigationMetadataUiClickEvent", "navigationMetadata", "Lcom/amazon/music/views/library/metadata/NavigationMetadata;", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Lcom/amazon/music/views/library/metadata/NavigationMetadata;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;Ljava/lang/Integer;)V", "trackSearchQueryUiClickEvent", "updateActionBar", "updateFilterSelections", "filterSelections", "updateSearchTerm", "updateSearchViewFocus", "visible", "updateTrackPlaybackState", "updateViewModelDataFromResumeContext", "resumeContextData", "ActionBarState", "Companion", "ResumeContextData", "SearchViewModelData", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class SearchViewModel extends BasePageGridViewModel implements ItemOrdinalProvider {
    private static final int RESET_ORDINAL_VALUE = 0;
    private final SearchExperienceMode DEFAULT_SEARCH_EXPERIENCE_MODE;
    private final ConverterService converterService;

    /* JADX INFO: renamed from: invalidateRecycleViewDelayedDuration$delegate, reason: from kotlin metadata */
    private final Lazy invalidateRecycleViewDelayedDuration;
    private final LibraryStateService libraryStateService;
    private final PublishSubject<ActionBarState> mActionBarState;
    private ContentMetadataPlaybackState mContentMetadataPlaybackState;
    private final PublishSubject<ContentMetadataPlaybackState> mContentMetadataPlaybackStateUpdated;
    private final SearchViewModelData mData;
    private SearchExperienceMode mExperienceMode;
    private final PublishSubject<Unit> mExternalNavigationRequested;
    private SearchExperienceMode mInitialExperienceMode;
    private Integer mNewScreenOrientation;
    private String mPageUri;
    private Integer mScreenOrientation;
    private final PublishSubject<String> mSearchQueryTextUpdated;
    private final PublishSubject<Boolean> mSearchViewFocusUpdated;
    private boolean mSearchViewFocused;
    private final MutableLiveData<Boolean> mShouldShowLoadingSpinner;
    private ContentMetadataHandlerFactory mVoiceContentMetadataHandlerFactory;
    private final BaseContainerModelConverter<Page> pageGridConverter;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final SearchHistoryService searchHistoryService;
    private final SearchMetricsService searchMetricsService;
    private final SearchService searchService;
    private final SearchSuggestionService searchSuggestionService;
    private final UIConfigurationService uiConfigurationService;
    private static final String TAG = "SPL";
    private static final Logger logger = LoggerFactory.getLogger(SearchViewModel.class.getName());

    /* JADX INFO: compiled from: SearchViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[SearchExperienceMode.values().length];
            iArr[SearchExperienceMode.SEARCH_HISTORY.ordinal()] = 1;
            iArr[SearchExperienceMode.SEARCH_SUGGESTION.ordinal()] = 2;
            iArr[SearchExperienceMode.SEARCH_RESULT.ordinal()] = 3;
            iArr[SearchExperienceMode.SEE_ALL.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[SearchAction.values().length];
            iArr2[SearchAction.PLAY.ordinal()] = 1;
            iArr2[SearchAction.PLAY_FULL.ordinal()] = 2;
            iArr2[SearchAction.SEE_MORE.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[EntityType.values().length];
            iArr3[EntityType.ARTIST.ordinal()] = 1;
            iArr3[EntityType.ALBUM.ordinal()] = 2;
            iArr3[EntityType.TRACK.ordinal()] = 3;
            iArr3[EntityType.PLAYLIST.ordinal()] = 4;
            iArr3[EntityType.STATION.ordinal()] = 5;
            iArr3[EntityType.VIDEO.ordinal()] = 6;
            iArr3[EntityType.VIDEO_PLAYLIST.ordinal()] = 7;
            iArr3[EntityType.PODCAST_SHOW.ordinal()] = 8;
            iArr3[EntityType.PODCAST_EPISODE.ordinal()] = 9;
            iArr3[EntityType.COMMUNITY_PLAYLIST.ordinal()] = 10;
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteExpiredHistory$lambda-0, reason: not valid java name */
    public static final void m2091deleteExpiredHistory$lambda0(Unit unit) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSearchResultsPageData$lambda-38$lambda-37$lambda-35$lambda-33, reason: not valid java name */
    public static final void m2103x41f47e60(Unit unit) {
    }

    @VisibleForTesting
    public static /* synthetic */ void getMData$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: syncDefaultProfileHistory$lambda-2, reason: not valid java name */
    public static final void m2122syncDefaultProfileHistory$lambda2(Unit unit) {
    }

    public SearchViewModel(BaseContainerModelConverter<Page> pageGridConverter, SearchSuggestionService searchSuggestionService, SearchHistoryService searchHistoryService, ConverterService converterService, SearchService searchService, SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider, UIConfigurationService uiConfigurationService, LibraryStateService libraryStateService) {
        Intrinsics.checkNotNullParameter(pageGridConverter, "pageGridConverter");
        Intrinsics.checkNotNullParameter(searchSuggestionService, "searchSuggestionService");
        Intrinsics.checkNotNullParameter(searchHistoryService, "searchHistoryService");
        Intrinsics.checkNotNullParameter(converterService, "converterService");
        Intrinsics.checkNotNullParameter(searchService, "searchService");
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(libraryStateService, "libraryStateService");
        this.pageGridConverter = pageGridConverter;
        this.searchSuggestionService = searchSuggestionService;
        this.searchHistoryService = searchHistoryService;
        this.converterService = converterService;
        this.searchService = searchService;
        this.searchMetricsService = searchMetricsService;
        this.searchFeaturesProvider = searchFeaturesProvider;
        this.uiConfigurationService = uiConfigurationService;
        this.libraryStateService = libraryStateService;
        this.mShouldShowLoadingSpinner = new MutableLiveData<>();
        this.mData = new SearchViewModelData("", null, false, null, null, null, null, null, null, false, new SearchFilterSelections(uiConfigurationService.getFilterMetadata(isOffline()), CollectionsKt__CollectionsKt.mutableListOf(uiConfigurationService.getMyMusicFilterElement())), false, 0, new LinkedHashMap(), null, false, null, null, null, 507940, null);
        PublishSubject<String> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this.mSearchQueryTextUpdated = publishSubjectCreate;
        PublishSubject<ActionBarState> publishSubjectCreate2 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate2, "create()");
        this.mActionBarState = publishSubjectCreate2;
        PublishSubject<Unit> publishSubjectCreate3 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate3, "create()");
        this.mExternalNavigationRequested = publishSubjectCreate3;
        PublishSubject<Boolean> publishSubjectCreate4 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate4, "create()");
        this.mSearchViewFocusUpdated = publishSubjectCreate4;
        PublishSubject<ContentMetadataPlaybackState> publishSubjectCreate5 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate5, "create()");
        this.mContentMetadataPlaybackStateUpdated = publishSubjectCreate5;
        this.invalidateRecycleViewDelayedDuration = LazyKt__LazyJVMKt.lazy(new Function0<Long>() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$invalidateRecycleViewDelayedDuration$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Long invoke() {
                return this.this$0.uiConfigurationService.getRecycleViewConfiguration().getInvalidateDelayedDurationInMilliseconds();
            }
        });
        this.DEFAULT_SEARCH_EXPERIENCE_MODE = SearchExperienceMode.SEARCH_HISTORY;
    }

    public final LiveData<Boolean> getShouldShowLoadingSpinner() {
        return this.mShouldShowLoadingSpinner;
    }

    public final SearchViewModelData getMData() {
        return this.mData;
    }

    /* JADX INFO: renamed from: getPageUri, reason: from getter */
    public final String getMPageUri() {
        return this.mPageUri;
    }

    public final String getSearchTerm() {
        return this.mData.getExecutedTerm();
    }

    public final Observable<String> getSearchQueryTextUpdated() {
        return this.mSearchQueryTextUpdated;
    }

    /* JADX INFO: renamed from: getActionBarState, reason: collision with other method in class */
    public final Observable<ActionBarState> m2124getActionBarState() {
        return this.mActionBarState;
    }

    public final PublishSubject<Unit> getExternalNavigationRequested() {
        return this.mExternalNavigationRequested;
    }

    public final Observable<Boolean> getSearchViewFocusUpdated() {
        return this.mSearchViewFocusUpdated;
    }

    public final Observable<ContentMetadataPlaybackState> getContentMetadataPlaybackStateUpdated() {
        return this.mContentMetadataPlaybackStateUpdated;
    }

    public final Long getInvalidateRecycleViewDelayedDuration() {
        return (Long) this.invalidateRecycleViewDelayedDuration.getValue();
    }

    public final void deleteExpiredHistory() {
        addDisposable(this.searchHistoryService.deleteExpiredHistory(null).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda17
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2091deleteExpiredHistory$lambda0((Unit) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda18
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2092deleteExpiredHistory$lambda1((Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteExpiredHistory$lambda-1, reason: not valid java name */
    public static final void m2092deleteExpiredHistory$lambda1(Throwable th) {
        logger.error(Intrinsics.stringPlus("Error deleting expired history: ", th));
    }

    public final void syncDefaultProfileHistory() {
        addDisposable(this.searchHistoryService.syncDefaultProfileHistory().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2122syncDefaultProfileHistory$lambda2((Unit) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2123syncDefaultProfileHistory$lambda3((Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: syncDefaultProfileHistory$lambda-3, reason: not valid java name */
    public static final void m2123syncDefaultProfileHistory$lambda3(Throwable th) {
        logger.error(Intrinsics.stringPlus("Error syncing default profile history: ", th));
    }

    public final boolean showIvyRefinementPills() {
        return this.searchFeaturesProvider.getAreIvyRefinementPillsEnabled();
    }

    public final void setPageUri(String value) {
        logger.debug(TAG + "::setPageUri. value=" + ((Object) value));
        this.mPageUri = value;
    }

    public final void setNewScreenOrientation(int value) {
        this.mNewScreenOrientation = Integer.valueOf(value);
    }

    /* JADX INFO: renamed from: getScreenOrientation, reason: from getter */
    public final Integer getMScreenOrientation() {
        return this.mScreenOrientation;
    }

    public final void setInitialExperienceMode(SearchExperienceMode value) {
        logger.debug(TAG + "::setResumeExperienceMode. value=" + value);
        this.mInitialExperienceMode = value;
    }

    public static /* synthetic */ SearchExperienceMode getExperienceModeOrDefault$default(SearchViewModel searchViewModel, SearchExperienceMode searchExperienceMode, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getExperienceModeOrDefault");
        }
        if ((i & 1) != 0) {
            searchExperienceMode = null;
        }
        return searchViewModel.getExperienceModeOrDefault(searchExperienceMode);
    }

    public final SearchExperienceMode getExperienceModeOrDefault(SearchExperienceMode defaultValue) {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        return searchExperienceMode == null ? defaultValue == null ? this.DEFAULT_SEARCH_EXPERIENCE_MODE : defaultValue : searchExperienceMode;
    }

    public final boolean getIsTrackSearch() {
        return SearchResponseUtils.INSTANCE.isTrackSearch(this.mData.getSearchResponse());
    }

    @Override // com.amazon.music.find.viewmodels.BasePageGridViewModel
    public PageType getUiPageType() {
        return SearchMetricsService.DefaultImpls.getUiPageType$default(this.searchMetricsService, this.mExperienceMode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), Boolean.valueOf(isLibrary()), false, this.mData.getSeeMoreEntityType(), 8, null);
    }

    public final boolean isOffline() {
        return this.searchFeaturesProvider.isOffline();
    }

    public final void toggleExposedRefinements() {
        boolean zIsOffline = isOffline();
        this.mData.setShowExposedRefinements(!r1.getShowExposedRefinements());
        updateActionBar(getActionBarStateForSearchResults(zIsOffline));
    }

    public final boolean isLibrary() {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        int i = searchExperienceMode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[searchExperienceMode.ordinal()];
        if (i == 1 || i == 2) {
            return false;
        }
        return this.mData.getFilterSelections().hasMyMusicFilter();
    }

    public final SearchFilterSelections cloneFilterSelections() {
        return this.mData.getFilterSelections().deepCopy();
    }

    public final List<FilterItemModel> getFilterItemModels() {
        boolean zIsOffline = isOffline();
        this.mData.updateFilterSelections(new SearchFilterSelections(this.uiConfigurationService.getFilterMetadata(zIsOffline), this.mData.getFilterSelections().getSelections()), zIsOffline);
        return this.mData.getFilterSelections().getFilterItemModels();
    }

    public final boolean hasFilterItemModels() {
        return !getFilterItemModels().isEmpty();
    }

    public final ActionBarState getActionBarState() {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        int i = searchExperienceMode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[searchExperienceMode.ordinal()];
        if (i == 1) {
            return getActionBarStateForSearchHistory();
        }
        if (i == 2) {
            return getActionBarStateForSearchSuggestion();
        }
        if (i == 3) {
            return getActionBarStateForSearchResults(isOffline());
        }
        if (i != 4) {
            return null;
        }
        return getActionBarStateForSeeMore();
    }

    @Override // com.amazon.music.views.library.providers.ItemOrdinalProvider
    public int registerItem(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        return this.mData.updateItemMap(contentMetadata);
    }

    public final void setVoiceContentMetadataHandlerFactory(ContentMetadataHandlerFactory contentMetadataHandlerFactory) {
        this.mVoiceContentMetadataHandlerFactory = contentMetadataHandlerFactory;
    }

    public final void handleDeeplink(Uri uri, LauncherTokens launcherTokens, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> onComplete) {
        HandleDeeplinkResult handleDeeplinkResultHandlePlaybackDeeplink;
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(launcherTokens, "launcherTokens");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        String string = uri.toString();
        Intrinsics.checkNotNullExpressionValue(string, "uri.toString()");
        SearchAction searchAction = PageUriUtils.getSearchAction(string);
        int i = searchAction == null ? -1 : WhenMappings.$EnumSwitchMapping$1[searchAction.ordinal()];
        if (i == 1 || i == 2) {
            handleDeeplinkResultHandlePlaybackDeeplink = handlePlaybackDeeplink(launcherTokens, onComplete, string, searchAction);
        } else if (i == 3) {
            handleDeeplinkResultHandlePlaybackDeeplink = handleSeeMoreDeeplink(launcherTokens, onComplete, string, searchAction);
        } else {
            handleDeeplinkResultHandlePlaybackDeeplink = new HandleDeeplinkResult(false, "No handler available for action " + searchAction + '.');
        }
        handleDeeplinkOnComplete(launcherTokens, onComplete, handleDeeplinkResultHandlePlaybackDeeplink.isSuccess(), handleDeeplinkResultHandlePlaybackDeeplink.getErrorMessage(), searchAction);
    }

    private final void handleDeeplinkOnComplete(LauncherTokens launcherTokens, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> onComplete, boolean isSuccess, String failureMessage, SearchAction action) {
        if (isSuccess) {
            onComplete.mo4invoke(null, launcherTokens);
            return;
        }
        onComplete.mo4invoke(new CannotHandle(Intrinsics.stringPlus(((Object) SearchViewModel.class.getName()) + " is unable to handle App Interaction Uri - action " + action + '.', failureMessage)), launcherTokens);
    }

    private final HandleDeeplinkResult handlePlaybackDeeplink(LauncherTokens launcherTokens, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> onComplete, String pageUri, SearchAction action) {
        if (!this.mData.getShowOrdinals()) {
            return new HandleDeeplinkResult(false, "Currently not in voice mode.");
        }
        Integer contentPosition = PageUriUtils.getContentPosition(pageUri);
        if (contentPosition == null) {
            return new HandleDeeplinkResult(false, "Null content position.");
        }
        ContentMetadata contentMetadata = this.mData.getItemMap().get(contentPosition);
        if (contentMetadata == null) {
            return new HandleDeeplinkResult(false, "Invalid content position " + contentPosition + '.');
        }
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        if (!(searchExperienceMode == SearchExperienceMode.SEARCH_RESULT || searchExperienceMode == SearchExperienceMode.SEE_ALL)) {
            return new HandleDeeplinkResult(false, "SearchExperienceMode " + this.mExperienceMode + " cannot handle playback.");
        }
        return handleEntityActionDeeplink(launcherTokens, onComplete, contentMetadata, action);
    }

    private final HandleDeeplinkResult handleEntityActionDeeplink(LauncherTokens launcherTokens, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> onComplete, ContentMetadata contentMetadata, SearchAction action) {
        ContentMetadataHandlerFactory contentMetadataHandlerFactory = this.mVoiceContentMetadataHandlerFactory;
        if (contentMetadataHandlerFactory == null) {
            return new HandleDeeplinkResult(false, "Null metadata handler.");
        }
        contentMetadataHandlerFactory.handle(contentMetadata, action, InteractionType.VOICE);
        markDeeplinkAsHandled();
        return new HandleDeeplinkResult(true, null, 2, null);
    }

    private final HandleDeeplinkResult handleSeeMoreDeeplink(LauncherTokens launcherTokens, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> onComplete, String pageUri, SearchAction action) {
        EntityType seeMoreEntityType = PageUriUtils.getSeeMoreEntityType(pageUri);
        if (seeMoreEntityType == null) {
            return new HandleDeeplinkResult(false, "Not valid See More entity type.");
        }
        if (!(this.mExperienceMode == SearchExperienceMode.SEARCH_RESULT)) {
            return new HandleDeeplinkResult(false, "SearchExperienceMode " + this.mExperienceMode + " cannot handle See More.");
        }
        InteractionType interactionType = PageUriUtils.getInteractionType(pageUri);
        executeSeeMore(seeMoreEntityType, null, interactionType, Boolean.valueOf(interactionType == InteractionType.VOICE));
        return new HandleDeeplinkResult(true, null, 2, null);
    }

    public final void handleDestination(FindDestination findDestination) {
        Intrinsics.checkNotNullParameter(findDestination, "findDestination");
        String strConstructPageUri = PageUriUtils.constructPageUri(findDestination);
        SearchExperienceMode experienceMode = PageUriUtils.getExperienceMode(strConstructPageUri);
        setPageUri(strConstructPageUri);
        setInitialExperienceMode(experienceMode);
        resumeFromContextData();
    }

    public final void goBack() {
        EntityType seeMoreEntityType;
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        logger.debug(TAG + "::goBack. mode: " + searchExperienceMode);
        SearchViewModelData.reset$default(this.mData, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, 32511, null);
        if ((searchExperienceMode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[searchExperienceMode.ordinal()]) == 4) {
            seeMoreEntityType = this.mData.getSeeMoreEntityType();
            refreshSearch(true, true);
        } else {
            this.mExternalNavigationRequested.onNext(Unit.INSTANCE);
            seeMoreEntityType = null;
        }
        this.searchMetricsService.trackGoBackButtonUiClick(searchExperienceMode, Boolean.valueOf(isLibrary()), seeMoreEntityType);
    }

    public final void initiateSearch() {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        logger.debug(TAG + "::initiateSearch. mode: " + searchExperienceMode);
    }

    public final void changeSearchFocus(boolean hasFocus) {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean z = this.mSearchViewFocused;
        logger.debug(TAG + "::changeFocus: " + hasFocus + ". mode: " + searchExperienceMode + ", mSearchViewFocused: " + z + ", hasFocus: " + hasFocus);
        this.mSearchViewFocused = hasFocus;
        if (z || !hasFocus) {
            return;
        }
        this.searchMetricsService.trackInitiateSearchUiClick(searchExperienceMode, Boolean.valueOf(isLibrary()));
    }

    public final void clearSearchQuery() {
        ExecutionContextDefault executionContextDefaultBuild = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.CLEAR_SEARCH_QUERY).build();
        executionContextDefaultBuild.markOperationStarted();
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::clearSearchQuery. mode: " + searchExperienceMode);
        this.mSearchViewFocused = true;
        this.mData.updateIsOffline(zIsOffline);
        SearchViewModelData.reset$default(this.mData, true, true, false, false, false, true, true, false, true, true, true, true, false, false, false, 28828, null);
        if (searchExperienceMode == null || searchExperienceMode != SearchExperienceMode.SEARCH_HISTORY) {
            changeSearchExperienceMode(SearchExperienceMode.SEARCH_HISTORY);
            updateActionBar(getActionBarStateForSearchHistory());
        }
        fetchSearchHistoryPageData(executionContextDefaultBuild);
        this.searchMetricsService.trackClearSearchButtonUiClick(this.mExperienceMode, Boolean.valueOf(isLibrary()));
    }

    private final void showSuggestions() {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        if (searchExperienceMode == null || searchExperienceMode != SearchExperienceMode.SEARCH_SUGGESTION || this.mData.getActivityId() == null) {
            this.mData.createActivityId();
            SearchViewModelData.reset$default(this.mData, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, 24575, null);
        }
        ExecutionContextDefault.Builder builderWithSearchOperation = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.SHOW_SEARCH_SUGGESTIONS);
        SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_SUGGESTION;
        ExecutionContextDefault executionContextDefaultBuild = ExecutionContextDefault.Builder.withPageContext$default(builderWithSearchOperation, searchExperienceMode2, Boolean.valueOf(isOffline()), Boolean.valueOf(isLibrary()), null, 8, null).withEventId(string).withInjectContentInfo(true).withUiEventMetadata(new UiEventMetadata(this.mData.getQueryString(), this.mData.getActivityId(), ActivityIdType.SEARCH_SUGGESTION_ID, this.mData.getResponseId(), ResponseIdType.INSTANT_SEARCH_ID)).withPageLatencies().withUiPageView(true).build();
        executionContextDefaultBuild.markOperationStarted();
        changeSearchExperienceMode(searchExperienceMode2);
        if (searchExperienceMode == null || (searchExperienceMode != SearchExperienceMode.SEARCH_HISTORY && searchExperienceMode != searchExperienceMode2)) {
            updateActionBar(getActionBarStateForSearchSuggestion());
        }
        updateSearchViewFocus(true);
        fetchSuggestionPageData(executionContextDefaultBuild, this.mData.getQueryString());
    }

    public final void refreshSuggestions() {
        updateSearchTerm();
        showSuggestions();
    }

    private final void showSearchHistory() {
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        ExecutionContextDefault.Builder builderWithSearchViewFocus = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.SHOW_SEARCH_HISTORY).withSearchViewFocus(true);
        SearchExperienceMode searchExperienceMode = SearchExperienceMode.SEARCH_HISTORY;
        ExecutionContextDefault executionContextDefaultBuild = ExecutionContextDefault.Builder.withPageContext$default(builderWithSearchViewFocus, searchExperienceMode, Boolean.valueOf(isOffline()), Boolean.valueOf(isLibrary()), null, 8, null).withPageLatencies().withUiPageView(true).withEventId(string).withInjectContentInfo(true).build();
        executionContextDefaultBuild.markOperationStarted();
        SearchExperienceMode searchExperienceMode2 = this.mExperienceMode;
        Logger logger2 = logger;
        StringBuilder sb = new StringBuilder();
        String str = TAG;
        sb.append(str);
        sb.append("::showSearchHistory. mode: ");
        sb.append(searchExperienceMode2);
        logger2.debug(sb.toString());
        changeSearchExperienceMode(searchExperienceMode);
        logger2.debug(str + "::showSearchHistory. mode: " + searchExperienceMode2 + ", expMode: " + this.mExperienceMode);
        updateActionBar(getActionBarStateForSearchHistory());
        updateSearchViewFocus(true);
        fetchSearchHistoryPageData(executionContextDefaultBuild);
    }

    public final void clearSearchHistory() {
        ExecutionContextDefault executionContextDefaultBuild = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.CLEAR_SEARCH_HISTORY).withSearchViewFocus(true).build();
        executionContextDefaultBuild.markOperationStarted();
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        logger.debug(TAG + "::clearSearchHistory. mode: " + searchExperienceMode);
        SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_HISTORY;
        changeSearchExperienceMode(searchExperienceMode2);
        if (searchExperienceMode == null || searchExperienceMode != searchExperienceMode2) {
            updateActionBar(getActionBarStateForSearchHistory());
        }
        updateSearchViewFocus(true);
        fetchDeleteSearchHistoryPage(executionContextDefaultBuild);
        this.searchMetricsService.trackClearSearchHistoryUiClick();
    }

    public final void executeSearchFromTerm(String query, String viewId) {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::executeSearchFromTerm. mode: " + searchExperienceMode);
        if (query == null) {
            return;
        }
        String queryString = getMData().getQueryString();
        getMData().updateQueryString(query, zIsOffline);
        SearchViewModelData.reset$default(getMData(), false, true, false, false, false, true, true, false, true, true, true, true, false, false, false, 28829, null);
        ExecutionContextDefault.Builder builderWithSearchOperation = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.EXECUTE_SEARCH_FROM_TERM);
        SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_RESULT;
        ExecutionContextDefault.Builder builderWithSearchViewFocus = ExecutionContextDefault.Builder.withPageContext$default(builderWithSearchOperation, searchExperienceMode2, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), null, 8, null).withUiPageView(true).withPageLatencies().withSearchViewFocus(false);
        if (searchExperienceMode == SearchExperienceMode.SEARCH_SUGGESTION) {
            builderWithSearchViewFocus.withUiEventMetadata(new UiEventMetadata(queryString, getMData().getActivityId(), ActivityIdType.SEARCH_SUGGESTION_ID, getMData().getResponseId(), ResponseIdType.INSTANT_SEARCH_ID));
        }
        ExecutionContextDefault executionContextDefaultBuild = builderWithSearchViewFocus.build();
        executionContextDefaultBuild.markOperationStarted();
        updateSearchTerm();
        changeSearchExperienceMode(searchExperienceMode2);
        updateActionBar(getActionBarStateForSearchResults(zIsOffline));
        updateSearchViewFocus(false);
        fetchSearchResultsPageData$default(this, executionContextDefaultBuild, StringsKt__StringsKt.trim((CharSequence) getMData().getQueryString()).toString(), getMData().getCatalogSpellCorrections(), getFilterSelections(zIsOffline), zIsOffline, getMData().getShowOrdinals(), getMData().getQueryMetadata(), false, 128, null);
        boolean zContainsBlockRef = containsBlockRef(viewId);
        this.searchMetricsService.trackSearchQueryUiClick(searchExperienceMode, Boolean.valueOf(isLibrary()), !zContainsBlockRef ? viewId : null, executionContextDefaultBuild.getUiEventMetadata(), zContainsBlockRef ? viewId : null);
    }

    private final boolean containsBlockRef(String viewId) {
        return viewId != null && StringsKt__StringsKt.contains$default((CharSequence) viewId, (CharSequence) "|", false, 2, (Object) null);
    }

    public static /* synthetic */ void executeSearchFromMetadata$default(SearchViewModel searchViewModel, SearchQueryMetadata searchQueryMetadata, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: executeSearchFromMetadata");
        }
        if ((i & 1) != 0) {
            searchQueryMetadata = null;
        }
        searchViewModel.executeSearchFromMetadata(searchQueryMetadata);
    }

    public final void executeSearchFromMetadata(SearchQueryMetadata searchQueryMetadata) {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::executeSearchFromMetadata. mode: " + searchExperienceMode);
        if (searchQueryMetadata == null) {
            return;
        }
        String queryString = getMData().getQueryString();
        getMData().updateQueryString(searchQueryMetadata.getQuery(), zIsOffline);
        SearchViewModelData.reset$default(getMData(), false, true, false, false, false, true, true, false, true, true, true, true, false, false, false, 28829, null);
        ExecutionContextDefault.Builder builderWithSearchOperation = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.EXECUTE_SEARCH_FROM_METADATA);
        SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_RESULT;
        ExecutionContextDefault.Builder builderWithSearchViewFocus = ExecutionContextDefault.Builder.withPageContext$default(builderWithSearchOperation, searchExperienceMode2, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), null, 8, null).withUiPageView(true).withPageLatencies().withSearchViewFocus(false);
        if (searchExperienceMode == SearchExperienceMode.SEARCH_SUGGESTION) {
            builderWithSearchViewFocus.withUiEventMetadata(new UiEventMetadata(queryString, getMData().getActivityId(), ActivityIdType.SEARCH_SUGGESTION_ID, getMData().getResponseId(), ResponseIdType.INSTANT_SEARCH_ID));
        }
        ExecutionContextDefault executionContextDefaultBuild = builderWithSearchViewFocus.build();
        executionContextDefaultBuild.markOperationStarted();
        updateSearchTerm();
        changeSearchExperienceMode(searchExperienceMode2);
        updateActionBar(getActionBarStateForSearchResults(zIsOffline));
        updateSearchViewFocus(false);
        fetchSearchResultsPageData$default(this, executionContextDefaultBuild, StringsKt__StringsKt.trim((CharSequence) getMData().getQueryString()).toString(), getMData().getCatalogSpellCorrections(), getFilterSelections(zIsOffline), zIsOffline, getMData().getShowOrdinals(), getMData().getQueryMetadata(), false, 128, null);
        SearchMetricsService.DefaultImpls.trackSearchQueryUiClick$default(this.searchMetricsService, searchExperienceMode, Boolean.valueOf(isLibrary()), searchQueryMetadata.getViewId(), executionContextDefaultBuild.getUiEventMetadata(), null, 16, null);
    }

    public final void executeSearchFromSpellCorrection(String query, SpellCorrections spellCorrections) {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::executeSearchFromSpellCorrection. mode: " + searchExperienceMode);
        if (query == null) {
            return;
        }
        getMData().updateQueryStringAndCatalogSpellCorrection(query, zIsOffline, spellCorrections);
        SearchViewModelData.reset$default(getMData(), false, false, false, false, false, false, true, false, true, true, true, true, false, false, false, 28863, null);
        ExecutionContextDefault.Builder builderWithSearchOperation = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.EXECUTE_SEARCH_FROM_SPELL_CORRECTION);
        SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_RESULT;
        ExecutionContextDefault executionContextDefaultBuild = ExecutionContextDefault.Builder.withPageContext$default(builderWithSearchOperation, searchExperienceMode2, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), null, 8, null).withUiPageView(true).withPageLatencies().withSearchViewFocus(false).build();
        executionContextDefaultBuild.markOperationStarted();
        updateSearchTerm();
        changeSearchExperienceMode(searchExperienceMode2);
        updateActionBar(getActionBarStateForSearchResults(zIsOffline));
        updateSearchViewFocus(false);
        SearchFilterSelections filterSelections = getFilterSelections(zIsOffline);
        SpellCorrections catalogSpellCorrections = filterSelections.hasMyMusicFilter() ? null : getMData().getCatalogSpellCorrections();
        fetchSearchResultsPageData$default(this, executionContextDefaultBuild, StringsKt__StringsKt.trim((CharSequence) getMData().getQueryString()).toString(), catalogSpellCorrections, filterSelections, zIsOffline, getMData().getShowOrdinals(), getMData().getQueryMetadata(), false, 128, null);
        if (catalogSpellCorrections == null) {
            return;
        }
        if (catalogSpellCorrections.hasAutoCorrection()) {
            this.searchMetricsService.trackSearchAutoCorrectOriginalUiClick(searchExperienceMode, Boolean.valueOf(isLibrary()), catalogSpellCorrections.getBlockRef());
        } else if (catalogSpellCorrections.hasDidYouMeanCorrection()) {
            this.searchMetricsService.trackSearchDidYouMeanUiClick(searchExperienceMode, Boolean.valueOf(isLibrary()), catalogSpellCorrections.getBlockRef());
        }
    }

    public final void toggleFilterSelection(String filterId) {
        Intrinsics.checkNotNullParameter(filterId, "filterId");
        if (this.mData.getFilterSelections().containsSelection(filterId)) {
            this.mData.getFilterSelections().removeSelection(filterId);
        } else {
            this.mData.getFilterSelections().addSelection(filterId);
        }
        updateFilterSelections(this.mData.getFilterSelections());
    }

    public final void updateFilterSelections(SearchFilterSelections filterSelections) {
        Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::updateFilterSelections. mode: " + searchExperienceMode);
        this.mData.updateFilterSelections(filterSelections, zIsOffline);
        SearchViewModelData.reset$default(this.mData, false, false, false, true, false, false, true, false, true, true, false, false, false, false, false, 31927, null);
        SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_RESULT;
        changeSearchExperienceMode(searchExperienceMode2);
        updateActionBar(getActionBarStateForSearchResults(zIsOffline));
        boolean zHasMyMusicFilter = filterSelections.hasMyMusicFilter();
        ExecutionContextDefault executionContextDefaultBuild = ExecutionContextDefault.Builder.withPageContext$default(new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.EXECUTE_SEARCH_FROM_APPLY_FILTER), searchExperienceMode2, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), null, 8, null).withUiPageView(true).withPageLatencies().withSearchViewFocus(false).build();
        executionContextDefaultBuild.markOperationStarted();
        fetchSearchResultsPageData(executionContextDefaultBuild, StringsKt__StringsKt.trim((CharSequence) this.mData.getQueryString()).toString(), zHasMyMusicFilter ? this.mData.getLibrarySpellCorrections() : this.mData.getCatalogSpellCorrections(), getFilterSelections(zIsOffline), zIsOffline, this.mData.getShowOrdinals(), this.mData.getQueryMetadata(), this.mData.getEnableSpellCorrections());
    }

    public final void executeSearchFromQuery() {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::submitQuery. mode: " + searchExperienceMode);
        if (StringsKt__StringsKt.trim((CharSequence) this.mData.getQueryString()).toString().length() > 0) {
            this.mData.updateIsOffline(zIsOffline);
            SearchViewModelData.reset$default(this.mData, false, true, false, false, false, true, true, false, true, true, true, true, false, false, false, 28829, null);
            ExecutionContextDefault.Builder builderWithSearchOperation = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.EXECUTE_SEARCH_FROM_QUERY);
            SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_RESULT;
            ExecutionContextDefault executionContextDefaultBuild = ExecutionContextDefault.Builder.withPageContext$default(builderWithSearchOperation, searchExperienceMode2, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), null, 8, null).withUiPageView(true).withPageLatencies().withSearchViewFocus(false).build();
            executionContextDefaultBuild.markOperationStarted();
            changeSearchExperienceMode(searchExperienceMode2);
            updateActionBar(getActionBarStateForSearchResults(zIsOffline));
            updateSearchViewFocus(false);
            fetchSearchResultsPageData$default(this, executionContextDefaultBuild, StringsKt__StringsKt.trim((CharSequence) this.mData.getQueryString()).toString(), this.mData.getCatalogSpellCorrections(), getFilterSelections(zIsOffline), zIsOffline, this.mData.getShowOrdinals(), this.mData.getQueryMetadata(), false, 128, null);
            SearchMetricsService.DefaultImpls.trackExecuteSearchUiClick$default(this.searchMetricsService, searchExperienceMode, Boolean.valueOf(isLibrary()), null, 4, null);
        }
    }

    public final void refreshSearch(boolean force, boolean resetScrollPosition) {
        PageGridViewModel value;
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::refreshSearch. mode: " + searchExperienceMode);
        if (this.mData.getExecutedTerm() != null) {
            updateSearchTerm();
            SearchViewModelData.reset$default(this.mData, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, 32703, null);
            SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_RESULT;
            changeSearchExperienceMode(searchExperienceMode2);
            updateActionBar(getActionBarStateForSearchResults(zIsOffline));
            if (force) {
                this.mData.updateIsOffline(zIsOffline);
                ExecutionContextDefault executionContextDefaultBuild = ExecutionContextDefault.Builder.withPageContext$default(new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.REFRESH_SEARCH), searchExperienceMode2, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), null, 8, null).withUiPageView(true).withPageLatencies().withResetScrollPosition(resetScrollPosition).build();
                executionContextDefaultBuild.markOperationStarted();
                boolean zHasMyMusicFilter = getFilterSelections(zIsOffline).hasMyMusicFilter();
                fetchSearchResultsPageData(executionContextDefaultBuild, StringsKt__StringsKt.trim((CharSequence) this.mData.getQueryString()).toString(), zHasMyMusicFilter ? this.mData.getLibrarySpellCorrections() : this.mData.getCatalogSpellCorrections(), getFilterSelections(zIsOffline), zIsOffline, this.mData.getShowOrdinals(), this.mData.getQueryMetadata(), this.mData.getEnableSpellCorrections());
            } else {
                ExecutionContextDefault executionContextDefaultBuild2 = ExecutionContextDefault.Builder.withPageContext$default(new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.REFRESH_SEARCH), searchExperienceMode2, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), null, 8, null).withUiPageView(false).withResetScrollPosition(resetScrollPosition).build();
                executionContextDefaultBuild2.markOperationStarted();
                if (resetScrollPosition) {
                    FindPageGridViewModel findPageGridViewModelConvertSearchResponse = convertSearchResponse(executionContextDefaultBuild2, this.mData.getSearchResponse(), this.mData.getExecutedTerm(), isOffline(), this.mData.getShowOrdinals(), this.mData.getEnableSpellCorrections());
                    value = findPageGridViewModelConvertSearchResponse == null ? null : findPageGridViewModelConvertSearchResponse.getPageGridViewModel();
                } else {
                    value = getMPageModel().getValue();
                }
                if (value != null) {
                    updatePageData(executionContextDefaultBuild2, value);
                }
            }
        }
        String str = this.mPageUri;
        if (str == null) {
            return;
        }
        this.searchMetricsService.trackExecuteSearchUiClick(searchExperienceMode, Boolean.valueOf(isLibrary()), PageUriUtils.getInteractionType(str));
        markDeeplinkAsHandled();
    }

    public static /* synthetic */ void executeSeeMore$default(SearchViewModel searchViewModel, EntityType entityType, String str, InteractionType interactionType, Boolean bool, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: executeSeeMore");
        }
        if ((i & 4) != 0) {
            interactionType = InteractionType.TAP;
        }
        if ((i & 8) != 0) {
            bool = null;
        }
        searchViewModel.executeSeeMore(entityType, str, interactionType, bool);
    }

    public final void executeSeeMore(EntityType entityType, String viewId, InteractionType interactionType, Boolean showOrdinals) {
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        boolean zIsOffline = isOffline();
        ExecutionContextDefault.Builder builderWithSearchOperation = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.EXECUTE_SEE_MORE);
        SearchExperienceMode searchExperienceMode = SearchExperienceMode.SEE_ALL;
        ExecutionContextDefault executionContextDefaultBuild = builderWithSearchOperation.withPageContext(searchExperienceMode, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), entityType).withUiPageView(true).build();
        executionContextDefaultBuild.markOperationStarted();
        SearchExperienceMode searchExperienceMode2 = this.mExperienceMode;
        logger.debug(TAG + "::executeSeeMore. mode: " + searchExperienceMode2);
        String executedTerm = this.mData.getExecutedTerm();
        if (executedTerm != null) {
            if (StringsKt__StringsKt.trim((CharSequence) executedTerm).toString().length() > 0) {
                SearchViewModelData.reset$default(this.mData, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, 32511, null);
                SearchViewModelData searchViewModelData = this.mData;
                searchViewModelData.updateShowOrdinals(showOrdinals == null ? searchViewModelData.getShowOrdinals() : showOrdinals.booleanValue());
                this.mData.updateSeeMoreEntityType(entityType, zIsOffline);
                changeSearchExperienceMode(searchExperienceMode);
                updateActionBar(getActionBarStateForSeeMore());
                fetchSeeMorePageData(executionContextDefaultBuild, executedTerm, entityType, 0, null, zIsOffline, this.mData.getQueryMetadata());
                this.searchMetricsService.trackSeeMoreButtonUiClick(searchExperienceMode2, Boolean.valueOf(isLibrary()), viewId, interactionType);
            }
        }
    }

    public final void fetchSeeMoreNextPage(int page) {
        ExecutionContextDefault executionContextDefaultBuild = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.FETCH_SEE_MORE_NEXT_PAGE).withResetScrollPosition(false).build();
        executionContextDefaultBuild.markOperationStarted();
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::fetchSeeMoreNextPage. mode: " + searchExperienceMode);
        this.mData.updateSeeMorePageIndex(Integer.valueOf(page), zIsOffline);
        String seeMorePageToken = this.mData.getSeeMorePageToken();
        EntityType seeMoreEntityType = this.mData.getSeeMoreEntityType();
        Integer seeMorePageIndex = this.mData.getSeeMorePageIndex();
        String executedTerm = this.mData.getExecutedTerm();
        if (searchExperienceMode != SearchExperienceMode.SEE_ALL || seeMorePageToken == null || seeMoreEntityType == null || seeMorePageIndex == null || executedTerm == null) {
            return;
        }
        fetchSeeMorePageData(executionContextDefaultBuild, executedTerm, seeMoreEntityType, seeMorePageIndex.intValue(), seeMorePageToken, zIsOffline, this.mData.getQueryMetadata());
    }

    public final void refreshSeeMore(boolean force, boolean resetScrollPosition) {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::refreshSeeMore. mode: " + searchExperienceMode);
        EntityType seeMoreEntityType = this.mData.getSeeMoreEntityType();
        String executedTerm = this.mData.getExecutedTerm();
        SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEE_ALL;
        if (searchExperienceMode != searchExperienceMode2 || seeMoreEntityType == null || executedTerm == null) {
            return;
        }
        updateSearchTerm();
        updateActionBar(getActionBarStateForSeeMore());
        if (force) {
            ExecutionContextDefault executionContextDefaultBuild = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.REFRESH_SEE_MORE).withPageContext(searchExperienceMode2, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), this.mData.getSeeMoreEntityType()).withUiPageView(true).withResetScrollPosition(resetScrollPosition).build();
            executionContextDefaultBuild.markOperationStarted();
            fetchSeeMorePageData(executionContextDefaultBuild, executedTerm, seeMoreEntityType, 0, null, zIsOffline, this.mData.getQueryMetadata());
        } else {
            ExecutionContextDefault executionContextDefaultBuild2 = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.REFRESH_SEE_MORE).withPageContext(searchExperienceMode2, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), this.mData.getSeeMoreEntityType()).withUiPageView(false).withResetScrollPosition(false).build();
            executionContextDefaultBuild2.markOperationStarted();
            PageGridViewModel value = getMPageModel().getValue();
            if (value != null) {
                value.setPageIndex(0);
                updatePageData(executionContextDefaultBuild2, value);
            }
        }
        String str = this.mPageUri;
        if (str == null) {
            return;
        }
        this.searchMetricsService.trackSeeMoreButtonUiClick(searchExperienceMode, Boolean.valueOf(isLibrary()), null, PageUriUtils.getInteractionType(str));
        markDeeplinkAsHandled();
    }

    public final void openFilterDialog() {
        this.searchMetricsService.trackFilterButtonUiClick(this.mExperienceMode, Boolean.valueOf(isLibrary()));
    }

    public final void saveTrackPlaybackState(List<? extends ContentMetadata> contentMetadataList, int positionToStart) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        logger.debug(TAG + "::saveTrackPlaybackState. contentMetadataList: " + contentMetadataList + ", positionToStart: " + positionToStart);
        this.mContentMetadataPlaybackState = new ContentMetadataPlaybackState(contentMetadataList, positionToStart);
    }

    public final void clearTrackPlaybackState() {
        logger.debug(Intrinsics.stringPlus(TAG, "::clearTrackPlaybackState"));
        this.mContentMetadataPlaybackState = null;
    }

    private final Observable<FindPageGridViewModel> deleteSearchHistory(ExecutionContext executionContext) {
        Observable map = this.searchHistoryService.deleteSearchHistory(executionContext).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda27
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return SearchViewModel.m2093deleteSearchHistory$lambda12(this.f$0, (Unit) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "searchHistoryService.del…eData()\n                }");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteSearchHistory$lambda-12, reason: not valid java name */
    public static final FindPageGridViewModel m2093deleteSearchHistory$lambda12(SearchViewModel this$0, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return this$0.getEmptySearchHistoryPageData();
    }

    private final void fetchDeleteSearchHistoryPage(final ExecutionContext executionContext) {
        setIsFetching(true);
        addDisposable(deleteSearchHistory(executionContext).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2094fetchDeleteSearchHistoryPage$lambda14(this.f$0, executionContext, (FindPageGridViewModel) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2095fetchDeleteSearchHistoryPage$lambda15(this.f$0, (Throwable) obj);
            }
        }, new Action() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                SearchViewModel.m2096fetchDeleteSearchHistoryPage$lambda16(this.f$0);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchDeleteSearchHistoryPage$lambda-14, reason: not valid java name */
    public static final void m2094fetchDeleteSearchHistoryPage$lambda14(SearchViewModel this$0, ExecutionContext executionContext, FindPageGridViewModel findPageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        if (findPageGridViewModel == null) {
            return;
        }
        this$0.updatePageData(executionContext, findPageGridViewModel.getPageGridViewModel());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchDeleteSearchHistoryPage$lambda-15, reason: not valid java name */
    public static final void m2095fetchDeleteSearchHistoryPage$lambda15(SearchViewModel this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
        logger.error(Intrinsics.stringPlus("Error deleting search history: ", th));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchDeleteSearchHistoryPage$lambda-16, reason: not valid java name */
    public static final void m2096fetchDeleteSearchHistoryPage$lambda16(SearchViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
    }

    @VisibleForTesting
    public final Observable<FindPageGridViewModel> getSuggestionPageData(final ExecutionContext executionContext, final String query) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(query, "query");
        executionContext.markResponseStarted();
        Observable<FindPageGridViewModel> map = this.searchSuggestionService.getSearchSuggestions(executionContext, query).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda22
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return SearchViewModel.m2118getSuggestionPageData$lambda17(executionContext, this, (SearchSuggestionResponse) obj);
            }
        }).map(new Function() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda23
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return SearchViewModel.m2119getSuggestionPageData$lambda18(this.f$0, executionContext, query, (FindPageGridViewModel) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "searchSuggestionService.… query)\n                }");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSuggestionPageData$lambda-17, reason: not valid java name */
    public static final FindPageGridViewModel m2118getSuggestionPageData$lambda17(ExecutionContext executionContext, SearchViewModel this$0, SearchSuggestionResponse searchSuggestionResponse) {
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        executionContext.markResponseCompleted();
        executionContext.markConversionStarted();
        this$0.mData.setResponseId(searchSuggestionResponse.getQueryId());
        List<SearchSuggestion> suggestions = searchSuggestionResponse.getSuggestions();
        PageResponse pageResponseConvertEmptySearchSuggestion = suggestions.isEmpty() ? this$0.converterService.convertEmptySearchSuggestion() : this$0.converterService.convertSearchSuggestion(suggestions);
        BaseContainerModelConverter<Page> baseContainerModelConverter = this$0.pageGridConverter;
        return new FindPageGridViewModel((PageGridViewModel) baseContainerModelConverter.convert(pageResponseConvertEmptySearchSuggestion, baseContainerModelConverter.getChildrenModels(pageResponseConvertEmptySearchSuggestion)), suggestions.isEmpty(), null, null, null, suggestions, 28, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSuggestionPageData$lambda-18, reason: not valid java name */
    public static final FindPageGridViewModel m2119getSuggestionPageData$lambda18(SearchViewModel this$0, ExecutionContext executionContext, String query, FindPageGridViewModel findPageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(query, "$query");
        return this$0.customizePageGridViewModel(executionContext, findPageGridViewModel, SearchExperienceMode.SEARCH_SUGGESTION, query);
    }

    private final void fetchSuggestionPageData(final ExecutionContext executionContext, String query) {
        final Long isFetching = setIsFetching(true);
        addDisposable(getSuggestionPageData(executionContext, query).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda24
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2110fetchSuggestionPageData$lambda20(executionContext, this, isFetching, (FindPageGridViewModel) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda25
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2111fetchSuggestionPageData$lambda21(this.f$0, (Throwable) obj);
            }
        }, new Action() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda26
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                SearchViewModel.m2112fetchSuggestionPageData$lambda22(this.f$0);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSuggestionPageData$lambda-20, reason: not valid java name */
    public static final void m2110fetchSuggestionPageData$lambda20(ExecutionContext executionContext, final SearchViewModel this$0, final Long l, FindPageGridViewModel findPageGridViewModel) {
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (findPageGridViewModel == null) {
            return;
        }
        executionContext.markConversionCompleted();
        if (findPageGridViewModel.isEmpty()) {
            return;
        }
        this$0.updatePageData(executionContext, findPageGridViewModel.getPageGridViewModel(), new Function1<PageDataShouldBeUpdatedEvent<PageGridViewModel>, Boolean>() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$fetchSuggestionPageData$1$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(PageDataShouldBeUpdatedEvent<PageGridViewModel> event) {
                Intrinsics.checkNotNullParameter(event, "event");
                return Boolean.valueOf(this.this$0.pageShouldBeUpdated(event, l));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSuggestionPageData$lambda-21, reason: not valid java name */
    public static final void m2111fetchSuggestionPageData$lambda21(SearchViewModel this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
        logger.error(Intrinsics.stringPlus("Error fetching suggestion page data: ", th));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSuggestionPageData$lambda-22, reason: not valid java name */
    public static final void m2112fetchSuggestionPageData$lambda22(SearchViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
    }

    private final FindPageGridViewModel getEmptySearchHistoryPageData() {
        PageResponse pageResponseConvertEmptySearchHistory = this.converterService.convertEmptySearchHistory();
        BaseContainerModelConverter<Page> baseContainerModelConverter = this.pageGridConverter;
        return new FindPageGridViewModel((PageGridViewModel) baseContainerModelConverter.convert(pageResponseConvertEmptySearchHistory, baseContainerModelConverter.getChildrenModels(pageResponseConvertEmptySearchHistory)), true, null, null, null, null, 60, null);
    }

    private final Observable<FindPageGridViewModel> getSearchHistoryPageData(final ExecutionContext executionContext) {
        executionContext.markResponseStarted();
        Observable<FindPageGridViewModel> map = this.searchHistoryService.getRecentSearchHistory(executionContext).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda13
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return SearchViewModel.m2113getSearchHistoryPageData$lambda23(executionContext, this, (List) obj);
            }
        }).map(new Function() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda14
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return SearchViewModel.m2114getSearchHistoryPageData$lambda24(this.f$0, executionContext, (FindPageGridViewModel) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "searchHistoryService.get…ISTORY)\n                }");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSearchHistoryPageData$lambda-23, reason: not valid java name */
    public static final FindPageGridViewModel m2113getSearchHistoryPageData$lambda23(ExecutionContext executionContext, SearchViewModel this$0, List it) {
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        executionContext.markResponseCompleted();
        executionContext.markConversionStarted();
        if (it.isEmpty()) {
            return this$0.getEmptySearchHistoryPageData();
        }
        ConverterService converterService = this$0.converterService;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        PageResponse pageResponseConvertSearchHistory = converterService.convertSearchHistory(it);
        BaseContainerModelConverter<Page> baseContainerModelConverter = this$0.pageGridConverter;
        return new FindPageGridViewModel((PageGridViewModel) baseContainerModelConverter.convert(pageResponseConvertSearchHistory, baseContainerModelConverter.getChildrenModels(pageResponseConvertSearchHistory)), it.isEmpty(), null, null, null, null, 60, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSearchHistoryPageData$lambda-24, reason: not valid java name */
    public static final FindPageGridViewModel m2114getSearchHistoryPageData$lambda24(SearchViewModel this$0, ExecutionContext executionContext, FindPageGridViewModel findPageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        return customizePageGridViewModel$default(this$0, executionContext, findPageGridViewModel, SearchExperienceMode.SEARCH_HISTORY, null, 8, null);
    }

    private final void fetchSearchHistoryPageData(final ExecutionContext executionContext) {
        setIsFetching(true);
        addDisposable(getSearchHistoryPageData(executionContext).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda19
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2099fetchSearchHistoryPageData$lambda26(executionContext, this, (FindPageGridViewModel) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda20
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2100fetchSearchHistoryPageData$lambda27(this.f$0, executionContext, (Throwable) obj);
            }
        }, new Action() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda21
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                SearchViewModel.m2101fetchSearchHistoryPageData$lambda28(this.f$0);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSearchHistoryPageData$lambda-26, reason: not valid java name */
    public static final void m2099fetchSearchHistoryPageData$lambda26(ExecutionContext executionContext, SearchViewModel this$0, FindPageGridViewModel findPageGridViewModel) {
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (findPageGridViewModel == null) {
            return;
        }
        executionContext.markConversionCompleted();
        this$0.updatePageData(executionContext, findPageGridViewModel.getPageGridViewModel());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSearchHistoryPageData$lambda-27, reason: not valid java name */
    public static final void m2100fetchSearchHistoryPageData$lambda27(SearchViewModel this$0, ExecutionContext executionContext, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        this$0.updatePageData(executionContext, this$0.getEmptySearchHistoryPageData().getPageGridViewModel());
        this$0.setIsFetching(false);
        logger.error(Intrinsics.stringPlus("Error fetching search history page data: ", th));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSearchHistoryPageData$lambda-28, reason: not valid java name */
    public static final void m2101fetchSearchHistoryPageData$lambda28(SearchViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
    }

    private final Observable<FindPageGridViewModel> getSearchResultsPageData(final ExecutionContext executionContext, final String query, SpellCorrections spellCorrections, final boolean enableSpellCorrections, SearchFilterSelections searchFilterSelections, final boolean isOffline, final boolean showOrdinal, String queryMetadata) {
        SearchRequest searchRequest = new SearchRequest(query, enableSpellCorrections, spellCorrections, searchFilterSelections, isOffline, queryMetadata, null, 64, null);
        executionContext.markResponseStarted();
        Observable map = this.searchService.search(executionContext, searchRequest).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda12
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return SearchViewModel.m2115getSearchResultsPageData$lambda29(executionContext, this, query, isOffline, showOrdinal, enableSpellCorrections, (SearchResponse) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "searchService.search(exe…      )\n                }");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSearchResultsPageData$lambda-29, reason: not valid java name */
    public static final FindPageGridViewModel m2115getSearchResultsPageData$lambda29(ExecutionContext executionContext, SearchViewModel this$0, String query, boolean z, boolean z2, boolean z3, SearchResponse searchResponse) {
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(query, "$query");
        executionContext.markResponseCompleted();
        executionContext.markConversionStarted();
        this$0.mData.setSearchResponse(searchResponse);
        return this$0.convertSearchResponse(executionContext, searchResponse, query, z, z2, z3);
    }

    static /* synthetic */ FindPageGridViewModel convertSearchResponse$default(SearchViewModel searchViewModel, ExecutionContext executionContext, SearchResponse searchResponse, String str, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertSearchResponse");
        }
        if ((i & 32) != 0) {
            z3 = true;
        }
        return searchViewModel.convertSearchResponse(executionContext, searchResponse, str, z, z2, z3);
    }

    private final FindPageGridViewModel convertSearchResponse(ExecutionContext executionContext, SearchResponse searchResponse, String query, boolean isOffline, boolean showOrdinal, boolean showSpellCorrections) {
        PageResponse pageResponseConvertSearchResults;
        FindPageGridViewModel findPageGridViewModel;
        if (searchResponse == null) {
            findPageGridViewModel = null;
        } else {
            boolean zIsNothingFound = searchResponse.isNothingFound();
            if (zIsNothingFound) {
                pageResponseConvertSearchResults = this.converterService.convertEmptySearchResults(searchResponse.getOriginalQuery(), searchResponse.getSpellCorrections());
            } else {
                pageResponseConvertSearchResults = this.converterService.convertSearchResults(searchResponse, showOrdinal, showSpellCorrections);
            }
            getMData().updateLibrarySpellCorrections(isOffline, searchResponse.getSpellCorrections());
            BaseContainerModelConverter<Page> baseContainerModelConverter = this.pageGridConverter;
            findPageGridViewModel = new FindPageGridViewModel((PageGridViewModel) baseContainerModelConverter.convert(pageResponseConvertSearchResults, baseContainerModelConverter.getChildrenModels(pageResponseConvertSearchResults)), zIsNothingFound, searchResponse.getFilterMetadata(), searchResponse.getExecutedTerm(), null, null, 48, null);
        }
        return customizePageGridViewModel(executionContext, findPageGridViewModel, SearchExperienceMode.SEARCH_RESULT, query);
    }

    static /* synthetic */ void fetchSearchResultsPageData$default(SearchViewModel searchViewModel, ExecutionContext executionContext, String str, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, boolean z, boolean z2, String str2, boolean z3, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fetchSearchResultsPageData");
        }
        searchViewModel.fetchSearchResultsPageData(executionContext, str, spellCorrections, searchFilterSelections, z, z2, str2, (i & 128) != 0 ? true : z3);
    }

    private final void fetchSearchResultsPageData(final ExecutionContext executionContext, String query, SpellCorrections spellCorrections, final SearchFilterSelections searchFilterSelections, final boolean isOffline, boolean showOrdinal, String queryMetadata, boolean enableSpellCorrections) {
        setIsFetching(true);
        this.mShouldShowLoadingSpinner.postValue(Boolean.TRUE);
        this.mData.updateQueryString(query, isOffline);
        if (spellCorrections != null) {
            getMData().setCatalogSpellCorrections(spellCorrections);
        }
        addDisposable(getSearchResultsPageData(executionContext, query, spellCorrections, enableSpellCorrections, searchFilterSelections, isOffline, showOrdinal, queryMetadata).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda28
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2102fetchSearchResultsPageData$lambda38(executionContext, this, isOffline, searchFilterSelections, (FindPageGridViewModel) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda29
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2105fetchSearchResultsPageData$lambda39(this.f$0, (Throwable) obj);
            }
        }, new Action() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda30
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                SearchViewModel.m2106fetchSearchResultsPageData$lambda40(this.f$0);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSearchResultsPageData$lambda-38, reason: not valid java name */
    public static final void m2102fetchSearchResultsPageData$lambda38(ExecutionContext executionContext, SearchViewModel this$0, boolean z, SearchFilterSelections searchFilterSelections, FindPageGridViewModel findPageGridViewModel) {
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "$searchFilterSelections");
        if (findPageGridViewModel == null) {
            return;
        }
        executionContext.markConversionCompleted();
        this$0.getMData().updateExecutedTerm(findPageGridViewModel.getExecutedTerm(), z);
        String executedTerm = findPageGridViewModel.getExecutedTerm();
        if (executedTerm != null) {
            this$0.addDisposable(this$0.searchHistoryService.putRecentSearchQuery(executionContext, executedTerm, System.currentTimeMillis()).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda8
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    SearchViewModel.m2103x41f47e60((Unit) obj);
                }
            }, new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda9
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    SearchViewModel.m2104x41f47e61((Throwable) obj);
                }
            }));
        }
        this$0.updatePageData(executionContext, findPageGridViewModel.getPageGridViewModel());
        this$0.mShouldShowLoadingSpinner.postValue(Boolean.FALSE);
        FilterMetadata filterMetadata = findPageGridViewModel.getFilterMetadata();
        if (filterMetadata == null) {
            return;
        }
        SearchViewModelData mData = this$0.getMData();
        List<FilterElement> selections = searchFilterSelections.getSelections();
        if (selections == null) {
            selections = new ArrayList<>();
        }
        mData.updateFilterSelections(new SearchFilterSelections(filterMetadata, selections), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSearchResultsPageData$lambda-38$lambda-37$lambda-35$lambda-34, reason: not valid java name */
    public static final void m2104x41f47e61(Throwable th) {
        logger.error(Intrinsics.stringPlus("Error storing search query: ", th));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSearchResultsPageData$lambda-39, reason: not valid java name */
    public static final void m2105fetchSearchResultsPageData$lambda39(SearchViewModel this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
        logger.error(Intrinsics.stringPlus("Error fetching search result page data: ", th));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSearchResultsPageData$lambda-40, reason: not valid java name */
    public static final void m2106fetchSearchResultsPageData$lambda40(SearchViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
    }

    private final Observable<FindPageGridViewModel> getSeeMoreResultsPageData(final ExecutionContext executionContext, final String query, SpellCorrections spellCorrections, SearchFilterSelections searchFilterSelections, EntityType entityType, String pageToken, boolean isOffline, String queryMetadata) {
        SeeMoreRequest seeMoreRequest = new SeeMoreRequest(query, entityType, this.mData.getEnableSpellCorrections(), spellCorrections, searchFilterSelections, pageToken, isOffline, queryMetadata, null, 256, null);
        executionContext.markResponseStarted();
        Observable<FindPageGridViewModel> map = this.searchService.seeMore(executionContext, seeMoreRequest).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda10
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return SearchViewModel.m2116getSeeMoreResultsPageData$lambda41(executionContext, this, query, (SeeMoreResponse) obj);
            }
        }).map(new Function() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda11
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return SearchViewModel.m2117getSeeMoreResultsPageData$lambda42(this.f$0, executionContext, query, (FindPageGridViewModel) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "searchService.seeMore(ex… query)\n                }");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSeeMoreResultsPageData$lambda-41, reason: not valid java name */
    public static final FindPageGridViewModel m2116getSeeMoreResultsPageData$lambda41(ExecutionContext executionContext, SearchViewModel this$0, String query, SeeMoreResponse it) {
        PageResponse pageResponseConvertSeeMoreResults;
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(query, "$query");
        executionContext.markResponseCompleted();
        executionContext.markConversionStarted();
        if (it.getSearchItems().isEmpty()) {
            pageResponseConvertSeeMoreResults = this$0.converterService.convertEmptySeeMoreResults(query);
        } else {
            ConverterService converterService = this$0.converterService;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            pageResponseConvertSeeMoreResults = converterService.convertSeeMoreResults(it, this$0.mData.getShowOrdinals(), false);
        }
        BaseContainerModelConverter<Page> baseContainerModelConverter = this$0.pageGridConverter;
        return new FindPageGridViewModel((PageGridViewModel) baseContainerModelConverter.convert(pageResponseConvertSeeMoreResults, baseContainerModelConverter.getChildrenModels(pageResponseConvertSeeMoreResults)), false, null, null, null, null, 60, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSeeMoreResultsPageData$lambda-42, reason: not valid java name */
    public static final FindPageGridViewModel m2117getSeeMoreResultsPageData$lambda42(SearchViewModel this$0, ExecutionContext executionContext, String query, FindPageGridViewModel findPageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(query, "$query");
        return this$0.customizePageGridViewModel(executionContext, findPageGridViewModel, SearchExperienceMode.SEE_ALL, query);
    }

    static /* synthetic */ void fetchSeeMorePageData$default(SearchViewModel searchViewModel, ExecutionContext executionContext, String str, EntityType entityType, int i, String str2, boolean z, String str3, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fetchSeeMorePageData");
        }
        searchViewModel.fetchSeeMorePageData(executionContext, str, entityType, i, (i2 & 16) != 0 ? null : str2, z, str3);
    }

    private final void fetchSeeMorePageData(final ExecutionContext executionContext, String query, EntityType entityType, final int pageIndex, String pageToken, final boolean isOffline, String queryMetadata) {
        setIsFetching(true);
        this.mData.updateSeeMoreEntityType(entityType, isOffline);
        addDisposable(getSeeMoreResultsPageData(executionContext, query, this.mData.getCatalogSpellCorrections(), getFilterSelections(isOffline), entityType, pageToken, isOffline, queryMetadata).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2107fetchSeeMorePageData$lambda45(executionContext, pageIndex, this, isOffline, (FindPageGridViewModel) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2108fetchSeeMorePageData$lambda46(this.f$0, (Throwable) obj);
            }
        }, new Action() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                SearchViewModel.m2109fetchSeeMorePageData$lambda47(this.f$0);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSeeMorePageData$lambda-45, reason: not valid java name */
    public static final void m2107fetchSeeMorePageData$lambda45(ExecutionContext executionContext, int i, SearchViewModel this$0, boolean z, FindPageGridViewModel findPageGridViewModel) {
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (findPageGridViewModel == null) {
            return;
        }
        executionContext.markConversionCompleted();
        if (i != 0) {
            PageGridViewModel value = this$0.getMPageModel().getValue();
            if (value != null) {
                List<com.amazon.music.views.library.models.base.BaseViewModel> models = value.getModels();
                PageGridViewModel pageGridViewModel = findPageGridViewModel.getPageGridViewModel();
                List<com.amazon.music.views.library.models.base.BaseViewModel> models2 = pageGridViewModel == null ? null : pageGridViewModel.getModels();
                if (models2 == null) {
                    models2 = Collections.emptyList();
                    Intrinsics.checkNotNullExpressionValue(models2, "emptyList()");
                }
                List listPlus = CollectionsKt___CollectionsKt.plus((Collection) models, (Iterable) models2);
                PageGridViewModel pageGridViewModel2 = findPageGridViewModel.getPageGridViewModel();
                this$0.updatePageData(executionContext, value.copy((1839 & 1) != 0 ? value.viewId : null, (1839 & 2) != 0 ? value.nestedModelViewId : null, (1839 & 4) != 0 ? value.title : null, (1839 & 8) != 0 ? value.subTitle : null, (1839 & 16) != 0 ? value.getModels() : listPlus, (1839 & 32) != 0 ? value.getTileType() : 0, (1839 & 64) != 0 ? value.pageIndex : i, (1839 & 128) != 0 ? value.nextPageToken : pageGridViewModel2 == null ? null : pageGridViewModel2.getNextPageToken(), (1839 & 256) != 0 ? value.filters : null, (1839 & 512) != 0 ? value.context : null, (1839 & 1024) != 0 ? value.header : null));
            }
        } else {
            this$0.updatePageData(executionContext, findPageGridViewModel.getPageGridViewModel());
        }
        SearchViewModelData mData = this$0.getMData();
        PageGridViewModel pageGridViewModel3 = findPageGridViewModel.getPageGridViewModel();
        mData.updateSeeMorePageToken(pageGridViewModel3 != null ? pageGridViewModel3.getNextPageToken() : null, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSeeMorePageData$lambda-46, reason: not valid java name */
    public static final void m2108fetchSeeMorePageData$lambda46(SearchViewModel this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
        logger.error(Intrinsics.stringPlus("Error fetching see more page data: ", th));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSeeMorePageData$lambda-47, reason: not valid java name */
    public static final void m2109fetchSeeMorePageData$lambda47(SearchViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModel
    public void fetchRequestState(List<Long> trackIds, List<String> trackAsins, final Function1<? super List<ContentOwnershipItem>, Unit> callback) {
        Intrinsics.checkNotNullParameter(trackIds, "trackIds");
        Intrinsics.checkNotNullParameter(trackAsins, "trackAsins");
        Intrinsics.checkNotNullParameter(callback, "callback");
        addDisposable(requestState(new ExecutionContextDefault.Builder().build(), trackIds, trackAsins).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda15
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2097fetchRequestState$lambda49(callback, (List) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda16
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2098fetchRequestState$lambda50((Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchRequestState$lambda-49, reason: not valid java name */
    public static final void m2097fetchRequestState$lambda49(Function1 callback, List list) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        if (list == null) {
            return;
        }
        callback.invoke(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchRequestState$lambda-50, reason: not valid java name */
    public static final void m2098fetchRequestState$lambda50(Throwable th) {
        logger.error(Intrinsics.stringPlus("Error fetchRequestState: ", th));
    }

    public final Disposable subscribeToSearchViewQueryTextChange(Observable<SearchViewQueryTextEvent> observable) {
        Intrinsics.checkNotNullParameter(observable, "observable");
        SearchSuggestionConfiguration searchSuggestionConfiguration = this.uiConfigurationService.getSearchSuggestionConfiguration();
        final boolean zIsOffline = isOffline();
        Disposable disposableSubscribe = observable.map(new Function() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda31
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return SearchViewModel.m2120subscribeToSearchViewQueryTextChange$lambda51((SearchViewQueryTextEvent) obj);
            }
        }).debounce(searchSuggestionConfiguration.getDebounceDurationInMilliseconds(), TimeUnit.MILLISECONDS).distinctUntilChanged().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.SearchViewModel$$ExternalSyntheticLambda32
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchViewModel.m2121subscribeToSearchViewQueryTextChange$lambda52(this.f$0, zIsOffline, (SearchViewQueryTextEvent) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "observable\n            .…          }\n            }");
        return disposableSubscribe;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeToSearchViewQueryTextChange$lambda-51, reason: not valid java name */
    public static final SearchViewQueryTextEvent m2120subscribeToSearchViewQueryTextChange$lambda51(SearchViewQueryTextEvent searchViewQueryTextEvent) {
        return new SearchViewQueryTextEvent(StringsKt__StringsKt.trim((CharSequence) searchViewQueryTextEvent.getQuery()).toString(), searchViewQueryTextEvent.isSubmit());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeToSearchViewQueryTextChange$lambda-52, reason: not valid java name */
    public static final void m2121subscribeToSearchViewQueryTextChange$lambda52(SearchViewModel this$0, boolean z, SearchViewQueryTextEvent searchViewQueryTextEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (searchViewQueryTextEvent.isSubmit()) {
            logger.debug(Intrinsics.stringPlus(TAG, "::subscribeToSearchViewQueryTextChange. Submit query"));
            this$0.mData.updateQueryString(searchViewQueryTextEvent.getQuery(), z);
            this$0.executeSearchFromQuery();
            return;
        }
        if (Intrinsics.areEqual(this$0.mData.getQueryString(), searchViewQueryTextEvent.getQuery())) {
            return;
        }
        this$0.mData.updateQueryString(searchViewQueryTextEvent.getQuery(), z);
        if (this$0.mData.getQueryString().length() == 0) {
            logger.debug(Intrinsics.stringPlus(TAG, "::subscribeToSearchViewQueryTextChange. showSearchHistory. mQueryString is empty"));
            this$0.showSearchHistory();
            return;
        }
        logger.debug(TAG + "::subscribeToSearchViewQueryTextChange. showSearchSuggestions. mQueryString: " + this$0.mData + ".mQueryString");
        this$0.showSuggestions();
    }

    public final boolean actionBarStateHasObservers() {
        return this.mActionBarState.hasObservers();
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void onResume() {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        boolean zIsOffline = isOffline();
        logger.debug(TAG + "::onResume->currentMode: " + searchExperienceMode + ", pageUri: " + ((Object) this.mPageUri));
        if (!this.mData.isOffline() && zIsOffline && (searchExperienceMode == SearchExperienceMode.SEARCH_RESULT || searchExperienceMode == SearchExperienceMode.SEE_ALL)) {
            SearchViewModelData.reset$default(this.mData, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, 16383, null);
            updateSearchTerm();
            refreshPage(SearchExperienceMode.SEARCH_HISTORY, true, true);
            return;
        }
        resumeFromContextData();
    }

    private final void resumeFromContextData() {
        ResumeContextData resumeContextDataFromModeAndPageUri = getResumeContextDataFromModeAndPageUri(this.mExperienceMode, this.mInitialExperienceMode, this.mNewScreenOrientation, this.mPageUri);
        this.mInitialExperienceMode = null;
        this.mNewScreenOrientation = null;
        updateViewModelDataFromResumeContext(resumeContextDataFromModeAndPageUri);
        refreshPage(resumeContextDataFromModeAndPageUri.getMode(), resumeContextDataFromModeAndPageUri.getForce(), resumeContextDataFromModeAndPageUri.getResetOrdinals());
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x00d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.find.viewmodels.SearchViewModel.ResumeContextData getResumeContextDataFromModeAndPageUri(com.amazon.music.find.viewmodels.SearchExperienceMode r20, com.amazon.music.find.viewmodels.SearchExperienceMode r21, java.lang.Integer r22, java.lang.String r23) {
        /*
            Method dump skipped, instruction units count: 301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.find.viewmodels.SearchViewModel.getResumeContextDataFromModeAndPageUri(com.amazon.music.find.viewmodels.SearchExperienceMode, com.amazon.music.find.viewmodels.SearchExperienceMode, java.lang.Integer, java.lang.String):com.amazon.music.find.viewmodels.SearchViewModel$ResumeContextData");
    }

    private final void updateViewModelDataFromResumeContext(ResumeContextData resumeContextData) {
        this.mData.updateInteractionType(resumeContextData.getInteractionType());
        this.mData.updateShowOrdinals(resumeContextData.getShowOrdinals());
        Integer screenOrientation = resumeContextData.getScreenOrientation();
        if (screenOrientation != null) {
            this.mScreenOrientation = Integer.valueOf(screenOrientation.intValue());
        }
        if (resumeContextData.getFilterSelectionsChanged()) {
            SearchViewModelData.reset$default(this.mData, false, false, false, true, true, false, true, false, false, false, false, false, false, false, false, 32679, null);
            this.mData.updateFilterSelections(resumeContextData.getFilterSelections(), isOffline());
        }
        String searchTerm = resumeContextData.getSearchTerm();
        if (searchTerm != null && resumeContextData.getSearchTermChanged()) {
            boolean zIsOffline = isOffline();
            getMData().updateExecutedTerm(searchTerm, zIsOffline);
            getMData().updateQueryString(searchTerm, zIsOffline);
        }
        EntityType seeMoreEntityType = resumeContextData.getSeeMoreEntityType();
        if (seeMoreEntityType != null) {
            getMData().updateSeeMoreEntityType(seeMoreEntityType, isOffline());
            this.mExperienceMode = SearchExperienceMode.SEE_ALL;
        }
        String queryMetadata = resumeContextData.getQueryMetadata();
        if (queryMetadata != null) {
            getMData().updateQueryMetadata(queryMetadata);
        }
        SearchViewModelData.reset$default(this.mData, false, false, false, false, false, false, false, false, resumeContextData.getResetOrdinals(), false, false, false, false, false, false, 32511, null);
        this.mData.updateEnableSpellCorrections(!resumeContextData.getShowOrdinals());
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void onRefresh() {
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        logger.debug(TAG + "::onRefresh. currentMode: " + searchExperienceMode);
        if (searchExperienceMode != null) {
            refreshPage(searchExperienceMode, false, false);
            updateTrackPlaybackState();
            clearTrackPlaybackState();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean pageShouldBeUpdated(PageDataShouldBeUpdatedEvent<PageGridViewModel> event, Long fetchRequestedAt) {
        return event.getLastFetchRequestedAt() == null || fetchRequestedAt == null || fetchRequestedAt.longValue() >= event.getLastFetchRequestedAt().longValue();
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModel
    protected void handleOperationCompleted(ExecutionContext executionContext) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        logger.debug(TAG + "::handleOperationCompleted. operation: " + executionContext.getSearchOperation());
        executionContext.markOperationCompleted();
        executionContext.complete(this.searchMetricsService);
    }

    public final void trackGenericUiClickEvent(String url, String blockRef) {
        this.searchMetricsService.trackGenericUiClick(url, blockRef);
    }

    public final void trackSearchQueryUiClickEvent(SearchExperienceMode mode, String entityPos) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        SearchMetricsService.DefaultImpls.trackSearchQueryUiClick$default(this.searchMetricsService, mode, Boolean.valueOf(isLibrary()), entityPos, null, null, 24, null);
    }

    public final void trackClearSearchHistoryUiClickEvent() {
        this.searchMetricsService.trackClearSearchHistoryUiClick();
    }

    public final void trackFindHomeUiPageView() {
        this.searchMetricsService.trackFindHomeUiPageView(Boolean.valueOf(isLibrary()), null);
    }

    public static /* synthetic */ void trackContentMetadataUiClickEvent$default(SearchViewModel searchViewModel, SearchExperienceMode searchExperienceMode, ActionType actionType, ContentMetadata contentMetadata, InteractionType interactionType, Integer num, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackContentMetadataUiClickEvent");
        }
        if ((i & 16) != 0) {
            num = null;
        }
        searchViewModel.trackContentMetadataUiClickEvent(searchExperienceMode, actionType, contentMetadata, interactionType, num);
    }

    public final void trackContentMetadataUiClickEvent(SearchExperienceMode mode, ActionType actionType, ContentMetadata contentMetadata, InteractionType interactionType, Integer entityPos) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        UiEventMetadata uiEventMetadata = mode == SearchExperienceMode.SEARCH_SUGGESTION ? new UiEventMetadata(this.mData.getQueryString(), this.mData.getActivityId(), ActivityIdType.SEARCH_SUGGESTION_ID, this.mData.getResponseId(), ResponseIdType.INSTANT_SEARCH_ID) : null;
        if (contentMetadata == null) {
            return;
        }
        EntityMetaData entityInfoFromContentMetaData = this.searchFeaturesProvider.getEntityInfoFromContentMetaData(contentMetadata);
        String viewId = contentMetadata.getViewId();
        if (entityInfoFromContentMetaData == null) {
            return;
        }
        this.searchMetricsService.trackMetadataUiClickEvent(mode, Boolean.valueOf(isLibrary()), actionType, viewId, entityInfoFromContentMetaData.getEntityType(), entityInfoFromContentMetaData.getEntityIdType(), entityInfoFromContentMetaData.getEntityId(), entityPos, getMData().getSeeMoreEntityType(), interactionType, uiEventMetadata);
    }

    public static /* synthetic */ void trackNavigationMetadataUiClickEvent$default(SearchViewModel searchViewModel, SearchExperienceMode searchExperienceMode, NavigationMetadata navigationMetadata, InteractionType interactionType, Integer num, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackNavigationMetadataUiClickEvent");
        }
        if ((i & 8) != 0) {
            num = null;
        }
        searchViewModel.trackNavigationMetadataUiClickEvent(searchExperienceMode, navigationMetadata, interactionType, num);
    }

    public final void trackNavigationMetadataUiClickEvent(SearchExperienceMode mode, NavigationMetadata navigationMetadata, InteractionType interactionType, Integer entityPos) {
        EntityMetaData entityInfoFromNavigationMetaData;
        ActionType actionType;
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        if (navigationMetadata == null || (entityInfoFromNavigationMetaData = this.searchFeaturesProvider.getEntityInfoFromNavigationMetaData(navigationMetadata)) == null || (actionType = entityInfoFromNavigationMetaData.getActionType()) == null) {
            return;
        }
        SearchMetricsService.DefaultImpls.trackMetadataUiClickEvent$default(this.searchMetricsService, mode, Boolean.valueOf(isLibrary()), actionType, navigationMetadata.getViewId(), entityInfoFromNavigationMetaData.getEntityType(), entityInfoFromNavigationMetaData.getEntityIdType(), entityInfoFromNavigationMetaData.getEntityId(), entityPos, getMData().getSeeMoreEntityType(), interactionType, null, 1024, null);
    }

    @VisibleForTesting
    public final void changeSearchExperienceMode(SearchExperienceMode toMode) {
        Intrinsics.checkNotNullParameter(toMode, "toMode");
        SearchExperienceMode searchExperienceMode = this.mExperienceMode;
        if (searchExperienceMode == null || searchExperienceMode != toMode) {
            this.mExperienceMode = toMode;
        }
    }

    private final void updateActionBar(ActionBarState actionBarState) {
        logger.debug(Intrinsics.stringPlus(TAG, "::updateActionBar"));
        this.mActionBarState.onNext(actionBarState);
    }

    public final void updateSearchViewFocus(boolean visible) {
        logger.debug(TAG + "::focusSearchView. visible=" + visible + ", mSearchViewFocused=" + this.mSearchViewFocused);
        if (this.mSearchViewFocused != visible) {
            this.mSearchViewFocusUpdated.onNext(Boolean.valueOf(visible));
        }
    }

    public static /* synthetic */ FindPageGridViewModel customizePageGridViewModel$default(SearchViewModel searchViewModel, ExecutionContext executionContext, FindPageGridViewModel findPageGridViewModel, SearchExperienceMode searchExperienceMode, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: customizePageGridViewModel");
        }
        if ((i & 8) != 0) {
            str = null;
        }
        return searchViewModel.customizePageGridViewModel(executionContext, findPageGridViewModel, searchExperienceMode, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00c0  */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amazon.music.find.model.FindPageGridViewModel customizePageGridViewModel(com.amazon.music.find.service.ExecutionContext r20, com.amazon.music.find.model.FindPageGridViewModel r21, com.amazon.music.find.viewmodels.SearchExperienceMode r22, java.lang.String r23) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.find.viewmodels.SearchViewModel.customizePageGridViewModel(com.amazon.music.find.service.ExecutionContext, com.amazon.music.find.model.FindPageGridViewModel, com.amazon.music.find.viewmodels.SearchExperienceMode, java.lang.String):com.amazon.music.find.model.FindPageGridViewModel");
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final void injectContentInfo(ExecutionContext executionContext, SearchExperienceMode searchExperienceMode, String eventId, int pos, SectionGridViewModel containerModel, List<? extends SearchSuggestion> suggestions) {
        int i = WhenMappings.$EnumSwitchMapping$0[searchExperienceMode.ordinal()];
        Map<String, String> contentViewDataForSearchHistory = null;
        if (i == 1) {
            ContentUtils contentUtils = ContentUtils.INSTANCE;
            ContainerType containerType = ContainerType.ITEM_LIST;
            UiEventMetadata uiEventMetadata = executionContext.getUiEventMetadata();
            contentViewDataForSearchHistory = contentUtils.getContentViewDataForSearchHistory(eventId, pos, containerType, uiEventMetadata != null ? uiEventMetadata.getSearchQuery() : null);
        } else if (i == 2) {
            ContentUtils contentUtils2 = ContentUtils.INSTANCE;
            ContainerType containerType2 = ContainerType.ITEM_LIST;
            UiEventMetadata uiEventMetadata2 = executionContext.getUiEventMetadata();
            String activityId = uiEventMetadata2 == null ? null : uiEventMetadata2.getActivityId();
            UiEventMetadata uiEventMetadata3 = executionContext.getUiEventMetadata();
            String searchQuery = uiEventMetadata3 == null ? null : uiEventMetadata3.getSearchQuery();
            UiEventMetadata uiEventMetadata4 = executionContext.getUiEventMetadata();
            contentViewDataForSearchHistory = contentUtils2.getContentViewDataForSearchSuggestion(eventId, pos, containerType2, activityId, searchQuery, suggestions, uiEventMetadata4 != null ? uiEventMetadata4.getResponseId() : null);
        }
        containerModel.setContentInfo(contentViewDataForSearchHistory);
    }

    private final void modifyLinkNavigator(LinkNavigatorModel model, String query) {
        model.setTargetType(TargetType.SEARCH_QUERY);
        model.setLinkNavigatorType(LinkNavigatorType.LARGE);
        model.setIconType(null);
        model.setDeemphasizedText(query);
    }

    private final void modifyHorizontalItems(HorizontalTileModel model, SearchExperienceMode searchExperienceMode) {
        SearchExperienceMode searchExperienceMode2 = SearchExperienceMode.SEARCH_RESULT;
        boolean z = true;
        model.setEnableAdd(searchExperienceMode == searchExperienceMode2 && model.getEnableAdd());
        if (searchExperienceMode != searchExperienceMode2 && (searchExperienceMode != SearchExperienceMode.SEARCH_SUGGESTION || !FindFeatureGating.INSTANT_SEARCH_API_ENTITIES_WITH_OVERFLOW.isEnabled())) {
            z = false;
        }
        model.setShouldShowOverflowMenu(z);
    }

    private final List<com.amazon.music.views.library.models.base.BaseViewModel> addRemoveButton(List<? extends com.amazon.music.views.library.models.base.BaseViewModel> parentModels) {
        if (parentModels == null) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        SectionGridViewModel sectionGridViewModel = new SectionGridViewModel(null, CollectionsKt__CollectionsJVMKt.listOf(new PillCellModel(ContentUtils.CLEAR_SEARCH_HISTORY_BUTTON_NAME, this.searchFeaturesProvider.getLocalizedString(R.string.clear_search_history_label), "", TargetType.CLEAR_VIEW, ViewType.CLEAR)), 2, null, null, null, null, null, null, null, false, null, false, null, null, null, null, 98296, null);
        List<com.amazon.music.views.library.models.base.BaseViewModel> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) parentModels);
        mutableList.add(sectionGridViewModel);
        return mutableList;
    }

    private final void updateSearchTerm() {
        this.mSearchQueryTextUpdated.onNext(this.mData.getQueryString());
    }

    private final void updateTrackPlaybackState() {
        ContentMetadataPlaybackState contentMetadataPlaybackState = this.mContentMetadataPlaybackState;
        if (contentMetadataPlaybackState == null) {
            return;
        }
        this.mContentMetadataPlaybackStateUpdated.onNext(contentMetadataPlaybackState);
    }

    private final SearchFilterSelections getFilterSelections(boolean isOffline) {
        if (isOffline) {
            FilterMetadata filterMetadata = this.uiConfigurationService.getFilterMetadata(true);
            List<FilterElement> selections = this.mData.getFilterSelections().getSelections();
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(selections, 10));
            Iterator<T> it = selections.iterator();
            while (it.hasNext()) {
                arrayList.add((FilterElement) it.next());
            }
            SearchFilterSelections searchFilterSelections = new SearchFilterSelections(filterMetadata, CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList));
            if (searchFilterSelections.hasMyMusicFilter()) {
                return searchFilterSelections;
            }
            searchFilterSelections.addMyMusicFilter();
            return searchFilterSelections;
        }
        return this.mData.getFilterSelections();
    }

    private final void refreshPage(SearchExperienceMode toMode, boolean force, boolean resetScrollPosition) {
        logger.debug(TAG + "::refreshPage. force: " + force + ", mData: " + this.mData);
        int i = WhenMappings.$EnumSwitchMapping$0[toMode.ordinal()];
        if (i == 1) {
            showSearchHistory();
            return;
        }
        if (i == 2) {
            refreshSuggestions();
        } else if (i == 3) {
            refreshSearch(force, resetScrollPosition);
        } else {
            if (i != 4) {
                return;
            }
            refreshSeeMore(force, resetScrollPosition);
        }
    }

    private final void markDeeplinkAsHandled() {
        setPageUri(null);
    }

    private final ActionBarState getActionBarStateForSearchHistory() {
        return new ActionBarState(false, null, true, true, false, false, false, false, true, true, 2, null);
    }

    private final ActionBarState getActionBarStateForSearchSuggestion() {
        return new ActionBarState(false, null, true, true, false, false, false, false, true, false, 2, null);
    }

    private final ActionBarState getActionBarStateForSearchResults(boolean isOffline) {
        return new ActionBarState(false, null, true, false, false, showIvyRefinementPills() || !isOffline, this.mData.getShowExposedRefinements(), this.mData.getFilterSelections().getHasActiveFilters(), false, false, 2, null);
    }

    private final ActionBarState getActionBarStateForSeeMore() {
        return new ActionBarState(false, getSeeMoreTitle(), true, false, false, false, false, false, false, false);
    }

    private final String getSeeMoreTitle() {
        String executedTerm = this.mData.getExecutedTerm();
        if (executedTerm == null) {
            executedTerm = this.mData.getQueryString();
        }
        EntityType seeMoreEntityType = this.mData.getSeeMoreEntityType();
        switch (seeMoreEntityType == null ? -1 : WhenMappings.$EnumSwitchMapping$2[seeMoreEntityType.ordinal()]) {
            case 1:
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_artists), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                return str;
            case 2:
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String str2 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_albums), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
                return str2;
            case 3:
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                String str3 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_songs), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
                return str3;
            case 4:
                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                String str4 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_playlists), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str4, "format(format, *args)");
                return str4;
            case 5:
                StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                String str5 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_stations), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str5, "format(format, *args)");
                return str5;
            case 6:
                StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
                String str6 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_videos), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str6, "format(format, *args)");
                return str6;
            case 7:
                StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
                String str7 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_video_playlists), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str7, "format(format, *args)");
                return str7;
            case 8:
                StringCompanionObject stringCompanionObject8 = StringCompanionObject.INSTANCE;
                String str8 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_podcasts), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str8, "format(format, *args)");
                return str8;
            case 9:
                StringCompanionObject stringCompanionObject9 = StringCompanionObject.INSTANCE;
                String str9 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_podcast_episodes), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str9, "format(format, *args)");
                return str9;
            case 10:
                StringCompanionObject stringCompanionObject10 = StringCompanionObject.INSTANCE;
                String str10 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title_community_playlists), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str10, "format(format, *args)");
                return str10;
            default:
                StringCompanionObject stringCompanionObject11 = StringCompanionObject.INSTANCE;
                String str11 = String.format(this.searchFeaturesProvider.getLocalizedString(R.string.see_more_title), Arrays.copyOf(new Object[]{executedTerm}, 1));
                Intrinsics.checkNotNullExpressionValue(str11, "format(format, *args)");
                return str11;
        }
    }

    private final Observable<List<ContentOwnershipItem>> requestState(ExecutionContext executionContext, List<Long> trackIds, List<String> trackAsins) {
        return LibraryStateService.DefaultImpls.requestState$default(this.libraryStateService, executionContext, trackIds, trackAsins, null, 8, null);
    }

    /* JADX INFO: compiled from: SearchViewModel.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b/\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003Jo\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u00102\u001a\u00020\u00032\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u000205HÖ\u0001J\t\u00106\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010\u0012R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0010\"\u0004\b\"\u0010\u0012R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0010\"\u0004\b&\u0010\u0012¨\u00067"}, d2 = {"Lcom/amazon/music/find/viewmodels/SearchViewModel$ActionBarState;", "", "showActionBar", "", "findSearchBarTitle", "", "showBackButton", "showAlexaButton", "showCancelTextView", "showFilterButton", "showExposedRefinements", "activeFilter", "showClearButton", "updateQueryHint", "(ZLjava/lang/String;ZZZZZZZZ)V", "getActiveFilter", "()Z", "setActiveFilter", "(Z)V", "getFindSearchBarTitle", "()Ljava/lang/String;", "setFindSearchBarTitle", "(Ljava/lang/String;)V", "getShowActionBar", "setShowActionBar", "getShowAlexaButton", "setShowAlexaButton", "getShowBackButton", "setShowBackButton", "getShowCancelTextView", "setShowCancelTextView", "getShowClearButton", "setShowClearButton", "getShowExposedRefinements", "setShowExposedRefinements", "getShowFilterButton", "setShowFilterButton", "getUpdateQueryHint", "setUpdateQueryHint", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class ActionBarState {
        private boolean activeFilter;
        private String findSearchBarTitle;
        private boolean showActionBar;
        private boolean showAlexaButton;
        private boolean showBackButton;
        private boolean showCancelTextView;
        private boolean showClearButton;
        private boolean showExposedRefinements;
        private boolean showFilterButton;
        private boolean updateQueryHint;

        public ActionBarState() {
            this(false, null, false, false, false, false, false, false, false, false, 1023, null);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getShowActionBar() {
            return this.showActionBar;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final boolean getUpdateQueryHint() {
            return this.updateQueryHint;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFindSearchBarTitle() {
            return this.findSearchBarTitle;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getShowAlexaButton() {
            return this.showAlexaButton;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getShowCancelTextView() {
            return this.showCancelTextView;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getShowFilterButton() {
            return this.showFilterButton;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getShowExposedRefinements() {
            return this.showExposedRefinements;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final boolean getActiveFilter() {
            return this.activeFilter;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final boolean getShowClearButton() {
            return this.showClearButton;
        }

        public final ActionBarState copy(boolean showActionBar, String findSearchBarTitle, boolean showBackButton, boolean showAlexaButton, boolean showCancelTextView, boolean showFilterButton, boolean showExposedRefinements, boolean activeFilter, boolean showClearButton, boolean updateQueryHint) {
            return new ActionBarState(showActionBar, findSearchBarTitle, showBackButton, showAlexaButton, showCancelTextView, showFilterButton, showExposedRefinements, activeFilter, showClearButton, updateQueryHint);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ActionBarState)) {
                return false;
            }
            ActionBarState actionBarState = (ActionBarState) other;
            return this.showActionBar == actionBarState.showActionBar && Intrinsics.areEqual(this.findSearchBarTitle, actionBarState.findSearchBarTitle) && this.showBackButton == actionBarState.showBackButton && this.showAlexaButton == actionBarState.showAlexaButton && this.showCancelTextView == actionBarState.showCancelTextView && this.showFilterButton == actionBarState.showFilterButton && this.showExposedRefinements == actionBarState.showExposedRefinements && this.activeFilter == actionBarState.activeFilter && this.showClearButton == actionBarState.showClearButton && this.updateQueryHint == actionBarState.updateQueryHint;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v20 */
        /* JADX WARN: Type inference failed for: r0v21 */
        /* JADX WARN: Type inference failed for: r1v0 */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r2v10, types: [int] */
        /* JADX WARN: Type inference failed for: r2v12, types: [int] */
        /* JADX WARN: Type inference failed for: r2v14, types: [int] */
        /* JADX WARN: Type inference failed for: r2v16, types: [int] */
        /* JADX WARN: Type inference failed for: r2v18 */
        /* JADX WARN: Type inference failed for: r2v19 */
        /* JADX WARN: Type inference failed for: r2v20 */
        /* JADX WARN: Type inference failed for: r2v21 */
        /* JADX WARN: Type inference failed for: r2v22 */
        /* JADX WARN: Type inference failed for: r2v23 */
        /* JADX WARN: Type inference failed for: r2v24 */
        /* JADX WARN: Type inference failed for: r2v26 */
        /* JADX WARN: Type inference failed for: r2v27 */
        /* JADX WARN: Type inference failed for: r2v28 */
        /* JADX WARN: Type inference failed for: r2v29 */
        /* JADX WARN: Type inference failed for: r2v30 */
        /* JADX WARN: Type inference failed for: r2v31 */
        /* JADX WARN: Type inference failed for: r2v32 */
        /* JADX WARN: Type inference failed for: r2v4, types: [int] */
        /* JADX WARN: Type inference failed for: r2v6, types: [int] */
        /* JADX WARN: Type inference failed for: r2v8, types: [int] */
        public int hashCode() {
            boolean z = this.showActionBar;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.findSearchBarTitle;
            int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
            boolean z2 = this.showBackButton;
            ?? r2 = z2;
            if (z2) {
                r2 = 1;
            }
            int i2 = (iHashCode + r2) * 31;
            boolean z3 = this.showAlexaButton;
            ?? r22 = z3;
            if (z3) {
                r22 = 1;
            }
            int i3 = (i2 + r22) * 31;
            boolean z4 = this.showCancelTextView;
            ?? r23 = z4;
            if (z4) {
                r23 = 1;
            }
            int i4 = (i3 + r23) * 31;
            boolean z5 = this.showFilterButton;
            ?? r24 = z5;
            if (z5) {
                r24 = 1;
            }
            int i5 = (i4 + r24) * 31;
            boolean z6 = this.showExposedRefinements;
            ?? r25 = z6;
            if (z6) {
                r25 = 1;
            }
            int i6 = (i5 + r25) * 31;
            boolean z7 = this.activeFilter;
            ?? r26 = z7;
            if (z7) {
                r26 = 1;
            }
            int i7 = (i6 + r26) * 31;
            boolean z8 = this.showClearButton;
            ?? r27 = z8;
            if (z8) {
                r27 = 1;
            }
            int i8 = (i7 + r27) * 31;
            boolean z9 = this.updateQueryHint;
            return i8 + (z9 ? 1 : z9);
        }

        public String toString() {
            return "ActionBarState(showActionBar=" + this.showActionBar + ", findSearchBarTitle=" + ((Object) this.findSearchBarTitle) + ", showBackButton=" + this.showBackButton + ", showAlexaButton=" + this.showAlexaButton + ", showCancelTextView=" + this.showCancelTextView + ", showFilterButton=" + this.showFilterButton + ", showExposedRefinements=" + this.showExposedRefinements + ", activeFilter=" + this.activeFilter + ", showClearButton=" + this.showClearButton + ", updateQueryHint=" + this.updateQueryHint + ')';
        }

        public ActionBarState(boolean z, String str, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9) {
            this.showActionBar = z;
            this.findSearchBarTitle = str;
            this.showBackButton = z2;
            this.showAlexaButton = z3;
            this.showCancelTextView = z4;
            this.showFilterButton = z5;
            this.showExposedRefinements = z6;
            this.activeFilter = z7;
            this.showClearButton = z8;
            this.updateQueryHint = z9;
        }

        public /* synthetic */ ActionBarState(boolean z, String str, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : str, (i & 4) != 0 ? true : z2, (i & 8) != 0 ? true : z3, (i & 16) != 0 ? false : z4, (i & 32) != 0 ? false : z5, (i & 64) != 0 ? false : z6, (i & 128) != 0 ? false : z7, (i & 256) == 0 ? z8 : false, (i & 512) == 0 ? z9 : true);
        }

        public final boolean getShowActionBar() {
            return this.showActionBar;
        }

        public final void setShowActionBar(boolean z) {
            this.showActionBar = z;
        }

        public final String getFindSearchBarTitle() {
            return this.findSearchBarTitle;
        }

        public final void setFindSearchBarTitle(String str) {
            this.findSearchBarTitle = str;
        }

        public final boolean getShowBackButton() {
            return this.showBackButton;
        }

        public final void setShowBackButton(boolean z) {
            this.showBackButton = z;
        }

        public final boolean getShowAlexaButton() {
            return this.showAlexaButton;
        }

        public final void setShowAlexaButton(boolean z) {
            this.showAlexaButton = z;
        }

        public final boolean getShowCancelTextView() {
            return this.showCancelTextView;
        }

        public final void setShowCancelTextView(boolean z) {
            this.showCancelTextView = z;
        }

        public final boolean getShowFilterButton() {
            return this.showFilterButton;
        }

        public final void setShowFilterButton(boolean z) {
            this.showFilterButton = z;
        }

        public final boolean getShowExposedRefinements() {
            return this.showExposedRefinements;
        }

        public final void setShowExposedRefinements(boolean z) {
            this.showExposedRefinements = z;
        }

        public final boolean getActiveFilter() {
            return this.activeFilter;
        }

        public final void setActiveFilter(boolean z) {
            this.activeFilter = z;
        }

        public final boolean getShowClearButton() {
            return this.showClearButton;
        }

        public final void setShowClearButton(boolean z) {
            this.showClearButton = z;
        }

        public final boolean getUpdateQueryHint() {
            return this.updateQueryHint;
        }

        public final void setUpdateQueryHint(boolean z) {
            this.updateQueryHint = z;
        }
    }

    /* JADX INFO: compiled from: SearchViewModel.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\bU\n\u0002\u0010\u0002\n\u0002\b \b\u0086\b\u0018\u00002\u00020\u0001Bá\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0006\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0010\u0012\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00180\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u001fJ\t\u0010Z\u001a\u00020\u0003HÆ\u0003J\t\u0010[\u001a\u00020\u0006HÆ\u0003J\t\u0010\\\u001a\u00020\u0013HÆ\u0003J\t\u0010]\u001a\u00020\u0006HÆ\u0003J\t\u0010^\u001a\u00020\u0010HÆ\u0003J\u0015\u0010_\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00180\u0017HÆ\u0003J\t\u0010`\u001a\u00020\u001aHÆ\u0003J\t\u0010a\u001a\u00020\u0006HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010f\u001a\u00020\u0006HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0010\u0010l\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010PJì\u0001\u0010m\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00102\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00180\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00062\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010nJ\u0006\u0010o\u001a\u00020pJ\u0013\u0010q\u001a\u00020\u00062\b\u0010r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010s\u001a\u00020\u0010HÖ\u0001J\u009c\u0001\u0010t\u001a\u00020p2\b\b\u0002\u0010u\u001a\u00020\u00062\b\b\u0002\u0010v\u001a\u00020\u00062\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010w\u001a\u00020\u00062\b\b\u0002\u0010x\u001a\u00020\u00062\b\b\u0002\u0010y\u001a\u00020\u00062\b\b\u0002\u0010z\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\u001d\u001a\u00020\u00062\b\b\u0002\u0010\u001e\u001a\u00020\u00062\b\b\u0002\u0010{\u001a\u00020\u0006J\t\u0010|\u001a\u00020\u0003HÖ\u0001J\u000e\u0010}\u001a\u00020p2\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010~\u001a\u00020p2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u0006J\u0016\u0010\u007f\u001a\u00020p2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u0006J\u000f\u0010\u0080\u0001\u001a\u00020p2\u0006\u0010\u0019\u001a\u00020\u001aJ\u000f\u0010\u0081\u0001\u001a\u00020p2\u0006\u0010\u0011\u001a\u00020\u0006J\u0010\u0010\u0082\u0001\u001a\u00020\u00102\u0007\u0010\u0083\u0001\u001a\u00020\u0018J\u0019\u0010\u0084\u0001\u001a\u00020p2\u0006\u0010\u0011\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\bJ\u0011\u0010\u0085\u0001\u001a\u00020p2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003J\u0017\u0010\u0086\u0001\u001a\u00020p2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0006J!\u0010\u0087\u0001\u001a\u00020p2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u001a\u0010\u0088\u0001\u001a\u00020p2\t\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u0006J \u0010\u008a\u0001\u001a\u00020p2\t\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0006¢\u0006\u0003\u0010\u008c\u0001J\u001a\u0010\u008d\u0001\u001a\u00020p2\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u0006J\u000f\u0010\u008f\u0001\u001a\u00020p2\u0006\u0010\u001b\u001a\u00020\u0006R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010!\"\u0004\b-\u0010#R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u0010\u0011\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010)\"\u0004\b6\u0010+R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R&\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00180\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001c\u0010\t\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010%\"\u0004\b@\u0010'R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010!\"\u0004\bB\u0010#R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010!\"\u0004\bD\u0010#R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010!\"\u0004\bF\u0010#R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010S\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010!\"\u0004\bU\u0010#R\u001a\u0010\u0014\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010)\"\u0004\bW\u0010+R\u001a\u0010\u001b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010)\"\u0004\bY\u0010+¨\u0006\u0090\u0001"}, d2 = {"Lcom/amazon/music/find/viewmodels/SearchViewModel$SearchViewModelData;", "", "queryString", "", "executedTerm", "enableSpellCorrections", "", "catalogSpellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "librarySpellCorrections", "searchResponse", "Lcom/amazon/music/find/model/search/SearchResponse;", "seeMorePageToken", "seeMoreEntityType", "Lcom/amazon/music/platform/find/common/EntityType;", "seeMorePageIndex", "", "isOffline", "filterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "showExposedRefinements", "itemIndex", "itemMap", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "showOrdinals", PageUriUtils.QUERYMETADATA_QUERY_PARAM, ContentUtils.KEY_ACTIVITY_ID, ContentUtils.KEY_RESPONSE_ID, "(Ljava/lang/String;Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/find/model/search/SearchResponse;Ljava/lang/String;Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/Integer;ZLcom/amazon/music/find/model/filter/SearchFilterSelections;ZILjava/util/Map;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getActivityId", "()Ljava/lang/String;", "setActivityId", "(Ljava/lang/String;)V", "getCatalogSpellCorrections", "()Lcom/amazon/music/search/SpellCorrections;", "setCatalogSpellCorrections", "(Lcom/amazon/music/search/SpellCorrections;)V", "getEnableSpellCorrections", "()Z", "setEnableSpellCorrections", "(Z)V", "getExecutedTerm", "setExecutedTerm", "getFilterSelections", "()Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "setFilterSelections", "(Lcom/amazon/music/find/model/filter/SearchFilterSelections;)V", "getInteractionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "setInteractionType", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;)V", "setOffline", "getItemIndex", "()I", "setItemIndex", "(I)V", "getItemMap", "()Ljava/util/Map;", "setItemMap", "(Ljava/util/Map;)V", "getLibrarySpellCorrections", "setLibrarySpellCorrections", "getQueryMetadata", "setQueryMetadata", "getQueryString", "setQueryString", "getResponseId", "setResponseId", "getSearchResponse", "()Lcom/amazon/music/find/model/search/SearchResponse;", "setSearchResponse", "(Lcom/amazon/music/find/model/search/SearchResponse;)V", "getSeeMoreEntityType", "()Lcom/amazon/music/platform/find/common/EntityType;", "setSeeMoreEntityType", "(Lcom/amazon/music/platform/find/common/EntityType;)V", "getSeeMorePageIndex", "()Ljava/lang/Integer;", "setSeeMorePageIndex", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getSeeMorePageToken", "setSeeMorePageToken", "getShowExposedRefinements", "setShowExposedRefinements", "getShowOrdinals", "setShowOrdinals", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;ZLcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/search/SpellCorrections;Lcom/amazon/music/find/model/search/SearchResponse;Ljava/lang/String;Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/Integer;ZLcom/amazon/music/find/model/filter/SearchFilterSelections;ZILjava/util/Map;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/amazon/music/find/viewmodels/SearchViewModel$SearchViewModelData;", "createActivityId", "", "equals", "other", "hashCode", "reset", ContentUtils.KEY_SEARCH_QUERY, "filterSelection", "allSpellCorrections", PageUriUtils.DO_SEE_MORE_VALUE, "seeMorePage", "ordinals", "all", "toString", "updateEnableSpellCorrections", "updateExecutedTerm", "updateFilterSelections", "updateInteractionType", "updateIsOffline", "updateItemMap", "contentMetadata", "updateLibrarySpellCorrections", "updateQueryMetadata", "updateQueryString", "updateQueryStringAndCatalogSpellCorrection", "updateSeeMoreEntityType", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "updateSeeMorePageIndex", "pageIndex", "(Ljava/lang/Integer;Z)V", "updateSeeMorePageToken", "pageToken", "updateShowOrdinals", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class SearchViewModelData {
        private String activityId;
        private SpellCorrections catalogSpellCorrections;
        private boolean enableSpellCorrections;
        private String executedTerm;
        private SearchFilterSelections filterSelections;
        private InteractionType interactionType;
        private boolean isOffline;
        private int itemIndex;
        private Map<Integer, ContentMetadata> itemMap;
        private SpellCorrections librarySpellCorrections;
        private String queryMetadata;
        private String queryString;
        private String responseId;
        private SearchResponse searchResponse;
        private EntityType seeMoreEntityType;
        private Integer seeMorePageIndex;
        private String seeMorePageToken;
        private boolean showExposedRefinements;
        private boolean showOrdinals;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getQueryString() {
            return this.queryString;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final boolean getIsOffline() {
            return this.isOffline;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final SearchFilterSelections getFilterSelections() {
            return this.filterSelections;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final boolean getShowExposedRefinements() {
            return this.showExposedRefinements;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final int getItemIndex() {
            return this.itemIndex;
        }

        public final Map<Integer, ContentMetadata> component14() {
            return this.itemMap;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final InteractionType getInteractionType() {
            return this.interactionType;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final boolean getShowOrdinals() {
            return this.showOrdinals;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final String getQueryMetadata() {
            return this.queryMetadata;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final String getActivityId() {
            return this.activityId;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final String getResponseId() {
            return this.responseId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getExecutedTerm() {
            return this.executedTerm;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getEnableSpellCorrections() {
            return this.enableSpellCorrections;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final SpellCorrections getCatalogSpellCorrections() {
            return this.catalogSpellCorrections;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final SpellCorrections getLibrarySpellCorrections() {
            return this.librarySpellCorrections;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final SearchResponse getSearchResponse() {
            return this.searchResponse;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getSeeMorePageToken() {
            return this.seeMorePageToken;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final EntityType getSeeMoreEntityType() {
            return this.seeMoreEntityType;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getSeeMorePageIndex() {
            return this.seeMorePageIndex;
        }

        public final SearchViewModelData copy(String queryString, String executedTerm, boolean enableSpellCorrections, SpellCorrections catalogSpellCorrections, SpellCorrections librarySpellCorrections, SearchResponse searchResponse, String seeMorePageToken, EntityType seeMoreEntityType, Integer seeMorePageIndex, boolean isOffline, SearchFilterSelections filterSelections, boolean showExposedRefinements, int itemIndex, Map<Integer, ContentMetadata> itemMap, InteractionType interactionType, boolean showOrdinals, String queryMetadata, String activityId, String responseId) {
            Intrinsics.checkNotNullParameter(queryString, "queryString");
            Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
            Intrinsics.checkNotNullParameter(itemMap, "itemMap");
            Intrinsics.checkNotNullParameter(interactionType, "interactionType");
            return new SearchViewModelData(queryString, executedTerm, enableSpellCorrections, catalogSpellCorrections, librarySpellCorrections, searchResponse, seeMorePageToken, seeMoreEntityType, seeMorePageIndex, isOffline, filterSelections, showExposedRefinements, itemIndex, itemMap, interactionType, showOrdinals, queryMetadata, activityId, responseId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SearchViewModelData)) {
                return false;
            }
            SearchViewModelData searchViewModelData = (SearchViewModelData) other;
            return Intrinsics.areEqual(this.queryString, searchViewModelData.queryString) && Intrinsics.areEqual(this.executedTerm, searchViewModelData.executedTerm) && this.enableSpellCorrections == searchViewModelData.enableSpellCorrections && Intrinsics.areEqual(this.catalogSpellCorrections, searchViewModelData.catalogSpellCorrections) && Intrinsics.areEqual(this.librarySpellCorrections, searchViewModelData.librarySpellCorrections) && Intrinsics.areEqual(this.searchResponse, searchViewModelData.searchResponse) && Intrinsics.areEqual(this.seeMorePageToken, searchViewModelData.seeMorePageToken) && this.seeMoreEntityType == searchViewModelData.seeMoreEntityType && Intrinsics.areEqual(this.seeMorePageIndex, searchViewModelData.seeMorePageIndex) && this.isOffline == searchViewModelData.isOffline && Intrinsics.areEqual(this.filterSelections, searchViewModelData.filterSelections) && this.showExposedRefinements == searchViewModelData.showExposedRefinements && this.itemIndex == searchViewModelData.itemIndex && Intrinsics.areEqual(this.itemMap, searchViewModelData.itemMap) && this.interactionType == searchViewModelData.interactionType && this.showOrdinals == searchViewModelData.showOrdinals && Intrinsics.areEqual(this.queryMetadata, searchViewModelData.queryMetadata) && Intrinsics.areEqual(this.activityId, searchViewModelData.activityId) && Intrinsics.areEqual(this.responseId, searchViewModelData.responseId);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v24, types: [int] */
        /* JADX WARN: Type inference failed for: r1v28, types: [int] */
        /* JADX WARN: Type inference failed for: r1v4, types: [int] */
        /* JADX WARN: Type inference failed for: r1v45 */
        /* JADX WARN: Type inference failed for: r1v46 */
        /* JADX WARN: Type inference failed for: r1v53 */
        /* JADX WARN: Type inference failed for: r1v55 */
        /* JADX WARN: Type inference failed for: r1v56 */
        /* JADX WARN: Type inference failed for: r1v57 */
        /* JADX WARN: Type inference failed for: r3v0 */
        /* JADX WARN: Type inference failed for: r3v1, types: [int] */
        /* JADX WARN: Type inference failed for: r3v2 */
        public int hashCode() {
            int iHashCode = this.queryString.hashCode() * 31;
            String str = this.executedTerm;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            boolean z = this.enableSpellCorrections;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            int i = (iHashCode2 + r1) * 31;
            SpellCorrections spellCorrections = this.catalogSpellCorrections;
            int iHashCode3 = (i + (spellCorrections == null ? 0 : spellCorrections.hashCode())) * 31;
            SpellCorrections spellCorrections2 = this.librarySpellCorrections;
            int iHashCode4 = (iHashCode3 + (spellCorrections2 == null ? 0 : spellCorrections2.hashCode())) * 31;
            SearchResponse searchResponse = this.searchResponse;
            int iHashCode5 = (iHashCode4 + (searchResponse == null ? 0 : searchResponse.hashCode())) * 31;
            String str2 = this.seeMorePageToken;
            int iHashCode6 = (iHashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31;
            EntityType entityType = this.seeMoreEntityType;
            int iHashCode7 = (iHashCode6 + (entityType == null ? 0 : entityType.hashCode())) * 31;
            Integer num = this.seeMorePageIndex;
            int iHashCode8 = (iHashCode7 + (num == null ? 0 : num.hashCode())) * 31;
            boolean z2 = this.isOffline;
            ?? r12 = z2;
            if (z2) {
                r12 = 1;
            }
            int iHashCode9 = (((iHashCode8 + r12) * 31) + this.filterSelections.hashCode()) * 31;
            boolean z3 = this.showExposedRefinements;
            ?? r13 = z3;
            if (z3) {
                r13 = 1;
            }
            int iHashCode10 = (((((((iHashCode9 + r13) * 31) + Integer.hashCode(this.itemIndex)) * 31) + this.itemMap.hashCode()) * 31) + this.interactionType.hashCode()) * 31;
            boolean z4 = this.showOrdinals;
            int i2 = (iHashCode10 + (z4 ? 1 : z4)) * 31;
            String str3 = this.queryMetadata;
            int iHashCode11 = (i2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.activityId;
            int iHashCode12 = (iHashCode11 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.responseId;
            return iHashCode12 + (str5 != null ? str5.hashCode() : 0);
        }

        public String toString() {
            return "SearchViewModelData(queryString=" + this.queryString + ", executedTerm=" + ((Object) this.executedTerm) + ", enableSpellCorrections=" + this.enableSpellCorrections + ", catalogSpellCorrections=" + this.catalogSpellCorrections + ", librarySpellCorrections=" + this.librarySpellCorrections + ", searchResponse=" + this.searchResponse + ", seeMorePageToken=" + ((Object) this.seeMorePageToken) + ", seeMoreEntityType=" + this.seeMoreEntityType + ", seeMorePageIndex=" + this.seeMorePageIndex + ", isOffline=" + this.isOffline + ", filterSelections=" + this.filterSelections + ", showExposedRefinements=" + this.showExposedRefinements + ", itemIndex=" + this.itemIndex + ", itemMap=" + this.itemMap + ", interactionType=" + this.interactionType + ", showOrdinals=" + this.showOrdinals + ", queryMetadata=" + ((Object) this.queryMetadata) + ", activityId=" + ((Object) this.activityId) + ", responseId=" + ((Object) this.responseId) + ')';
        }

        public SearchViewModelData(String queryString, String str, boolean z, SpellCorrections spellCorrections, SpellCorrections spellCorrections2, SearchResponse searchResponse, String str2, EntityType entityType, Integer num, boolean z2, SearchFilterSelections filterSelections, boolean z3, int i, Map<Integer, ContentMetadata> itemMap, InteractionType interactionType, boolean z4, String str3, String str4, String str5) {
            Intrinsics.checkNotNullParameter(queryString, "queryString");
            Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
            Intrinsics.checkNotNullParameter(itemMap, "itemMap");
            Intrinsics.checkNotNullParameter(interactionType, "interactionType");
            this.queryString = queryString;
            this.executedTerm = str;
            this.enableSpellCorrections = z;
            this.catalogSpellCorrections = spellCorrections;
            this.librarySpellCorrections = spellCorrections2;
            this.searchResponse = searchResponse;
            this.seeMorePageToken = str2;
            this.seeMoreEntityType = entityType;
            this.seeMorePageIndex = num;
            this.isOffline = z2;
            this.filterSelections = filterSelections;
            this.showExposedRefinements = z3;
            this.itemIndex = i;
            this.itemMap = itemMap;
            this.interactionType = interactionType;
            this.showOrdinals = z4;
            this.queryMetadata = str3;
            this.activityId = str4;
            this.responseId = str5;
        }

        public final String getQueryString() {
            return this.queryString;
        }

        public final void setQueryString(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.queryString = str;
        }

        public final String getExecutedTerm() {
            return this.executedTerm;
        }

        public final void setExecutedTerm(String str) {
            this.executedTerm = str;
        }

        public final boolean getEnableSpellCorrections() {
            return this.enableSpellCorrections;
        }

        public final void setEnableSpellCorrections(boolean z) {
            this.enableSpellCorrections = z;
        }

        public final SpellCorrections getCatalogSpellCorrections() {
            return this.catalogSpellCorrections;
        }

        public final void setCatalogSpellCorrections(SpellCorrections spellCorrections) {
            this.catalogSpellCorrections = spellCorrections;
        }

        public final SpellCorrections getLibrarySpellCorrections() {
            return this.librarySpellCorrections;
        }

        public final void setLibrarySpellCorrections(SpellCorrections spellCorrections) {
            this.librarySpellCorrections = spellCorrections;
        }

        public final SearchResponse getSearchResponse() {
            return this.searchResponse;
        }

        public final void setSearchResponse(SearchResponse searchResponse) {
            this.searchResponse = searchResponse;
        }

        public final String getSeeMorePageToken() {
            return this.seeMorePageToken;
        }

        public final void setSeeMorePageToken(String str) {
            this.seeMorePageToken = str;
        }

        public final EntityType getSeeMoreEntityType() {
            return this.seeMoreEntityType;
        }

        public final void setSeeMoreEntityType(EntityType entityType) {
            this.seeMoreEntityType = entityType;
        }

        public final Integer getSeeMorePageIndex() {
            return this.seeMorePageIndex;
        }

        public final void setSeeMorePageIndex(Integer num) {
            this.seeMorePageIndex = num;
        }

        public final boolean isOffline() {
            return this.isOffline;
        }

        public final void setOffline(boolean z) {
            this.isOffline = z;
        }

        public final SearchFilterSelections getFilterSelections() {
            return this.filterSelections;
        }

        public final void setFilterSelections(SearchFilterSelections searchFilterSelections) {
            Intrinsics.checkNotNullParameter(searchFilterSelections, "<set-?>");
            this.filterSelections = searchFilterSelections;
        }

        public final boolean getShowExposedRefinements() {
            return this.showExposedRefinements;
        }

        public final void setShowExposedRefinements(boolean z) {
            this.showExposedRefinements = z;
        }

        public final int getItemIndex() {
            return this.itemIndex;
        }

        public final void setItemIndex(int i) {
            this.itemIndex = i;
        }

        public /* synthetic */ SearchViewModelData(String str, String str2, boolean z, SpellCorrections spellCorrections, SpellCorrections spellCorrections2, SearchResponse searchResponse, String str3, EntityType entityType, Integer num, boolean z2, SearchFilterSelections searchFilterSelections, boolean z3, int i, Map map, InteractionType interactionType, boolean z4, String str4, String str5, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? true : z, (i2 & 8) != 0 ? null : spellCorrections, (i2 & 16) != 0 ? null : spellCorrections2, (i2 & 32) != 0 ? null : searchResponse, (i2 & 64) != 0 ? null : str3, (i2 & 128) != 0 ? null : entityType, (i2 & 256) != 0 ? null : num, (i2 & 512) != 0 ? false : z2, searchFilterSelections, (i2 & 2048) != 0 ? false : z3, (i2 & 4096) != 0 ? 0 : i, (i2 & 8192) != 0 ? new LinkedHashMap() : map, (i2 & 16384) != 0 ? InteractionType.TAP : interactionType, (32768 & i2) != 0 ? false : z4, (65536 & i2) != 0 ? null : str4, (131072 & i2) != 0 ? null : str5, (i2 & 262144) != 0 ? null : str6);
        }

        public final Map<Integer, ContentMetadata> getItemMap() {
            return this.itemMap;
        }

        public final void setItemMap(Map<Integer, ContentMetadata> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.itemMap = map;
        }

        public final InteractionType getInteractionType() {
            return this.interactionType;
        }

        public final void setInteractionType(InteractionType interactionType) {
            Intrinsics.checkNotNullParameter(interactionType, "<set-?>");
            this.interactionType = interactionType;
        }

        public final boolean getShowOrdinals() {
            return this.showOrdinals;
        }

        public final void setShowOrdinals(boolean z) {
            this.showOrdinals = z;
        }

        public final String getQueryMetadata() {
            return this.queryMetadata;
        }

        public final void setQueryMetadata(String str) {
            this.queryMetadata = str;
        }

        public final String getActivityId() {
            return this.activityId;
        }

        public final void setActivityId(String str) {
            this.activityId = str;
        }

        public final String getResponseId() {
            return this.responseId;
        }

        public final void setResponseId(String str) {
            this.responseId = str;
        }

        public static /* synthetic */ void reset$default(SearchViewModelData searchViewModelData, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, int i, Object obj) {
            searchViewModelData.reset((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4, (i & 16) != 0 ? false : z5, (i & 32) != 0 ? false : z6, (i & 64) != 0 ? false : z7, (i & 128) != 0 ? false : z8, (i & 256) != 0 ? false : z9, (i & 512) != 0 ? false : z10, (i & 1024) != 0 ? false : z11, (i & 2048) != 0 ? false : z12, (i & 4096) != 0 ? false : z13, (i & 8192) != 0 ? false : z14, (i & 16384) == 0 ? z15 : false);
        }

        public final void reset(boolean searchQuery, boolean filterSelection, boolean enableSpellCorrections, boolean catalogSpellCorrections, boolean librarySpellCorrections, boolean allSpellCorrections, boolean seeMore, boolean seeMorePage, boolean ordinals, boolean interactionType, boolean showOrdinals, boolean queryMetadata, boolean activityId, boolean responseId, boolean all) {
            if (all || searchQuery) {
                this.queryString = "";
                this.executedTerm = null;
                this.searchResponse = null;
            }
            if (all || filterSelection) {
                this.filterSelections.reset();
            }
            if (all || enableSpellCorrections || allSpellCorrections) {
                this.enableSpellCorrections = true;
            }
            if (all || catalogSpellCorrections || allSpellCorrections) {
                this.catalogSpellCorrections = null;
            }
            if (all || librarySpellCorrections || allSpellCorrections) {
                this.librarySpellCorrections = null;
            }
            if (all || seeMore) {
                this.seeMoreEntityType = null;
            }
            if (all || seeMore || seeMorePage) {
                this.seeMorePageToken = null;
                this.seeMorePageIndex = null;
            }
            if (all || ordinals || searchQuery) {
                this.itemMap.clear();
                this.itemIndex = 0;
            }
            if (all || interactionType) {
                this.interactionType = InteractionType.TAP;
            }
            if (all || showOrdinals) {
                this.showOrdinals = false;
            }
            if (all || queryMetadata) {
                this.queryMetadata = null;
            }
            if (all || activityId) {
                this.activityId = null;
            }
            if (all || responseId) {
                this.responseId = null;
            }
        }

        public final void updateSeeMoreEntityType(EntityType entityType, boolean isOffline) {
            this.seeMoreEntityType = entityType;
            this.seeMorePageToken = null;
            this.seeMorePageIndex = null;
            this.isOffline = isOffline;
        }

        public final void updateSeeMorePageToken(String pageToken, boolean isOffline) {
            this.seeMorePageToken = pageToken;
            this.isOffline = isOffline;
        }

        public final void updateSeeMorePageIndex(Integer pageIndex, boolean isOffline) {
            this.seeMorePageIndex = pageIndex;
            this.isOffline = isOffline;
        }

        public final void updateIsOffline(boolean isOffline) {
            this.isOffline = isOffline;
        }

        public final void updateQueryString(String queryString, boolean isOffline) {
            Intrinsics.checkNotNullParameter(queryString, "queryString");
            this.queryString = queryString;
            this.isOffline = isOffline;
        }

        public final void updateQueryStringAndCatalogSpellCorrection(String queryString, boolean isOffline, SpellCorrections catalogSpellCorrections) {
            Intrinsics.checkNotNullParameter(queryString, "queryString");
            this.queryString = queryString;
            this.catalogSpellCorrections = catalogSpellCorrections;
            this.isOffline = isOffline;
        }

        public final void updateEnableSpellCorrections(boolean enableSpellCorrections) {
            this.enableSpellCorrections = enableSpellCorrections;
        }

        public final void updateLibrarySpellCorrections(boolean isOffline, SpellCorrections librarySpellCorrections) {
            this.librarySpellCorrections = librarySpellCorrections;
        }

        public final void updateExecutedTerm(String executedTerm, boolean isOffline) {
            this.executedTerm = executedTerm;
            this.isOffline = isOffline;
        }

        public final void updateFilterSelections(SearchFilterSelections filterSelections, boolean isOffline) {
            Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
            if (isOffline) {
                filterSelections.removeMyMusicFilter();
            }
            this.filterSelections = filterSelections;
            this.isOffline = isOffline;
        }

        public final int updateItemMap(ContentMetadata contentMetadata) {
            Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
            Map<Integer, ContentMetadata> map = this.itemMap;
            int i = this.itemIndex + 1;
            this.itemIndex = i;
            map.put(Integer.valueOf(i), contentMetadata);
            return this.itemIndex;
        }

        public final void updateInteractionType(InteractionType interactionType) {
            Intrinsics.checkNotNullParameter(interactionType, "interactionType");
            this.interactionType = interactionType;
        }

        public final void updateShowOrdinals(boolean showOrdinals) {
            this.showOrdinals = showOrdinals;
        }

        public final void updateQueryMetadata(String queryMetadata) {
            this.queryMetadata = queryMetadata;
        }

        public final void createActivityId() {
            this.activityId = UUID.randomUUID().toString();
        }
    }

    /* JADX INFO: compiled from: SearchViewModel.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b)\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0015J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u0010\u0010,\u001a\u0004\u0018\u00010\u0013HÆ\u0003¢\u0006\u0002\u0010#J\u000b\u0010-\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\t\u0010/\u001a\u00020\u0007HÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\t\u00103\u001a\u00020\fHÆ\u0003J\t\u00104\u001a\u00020\u0005HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u008e\u0001\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000fHÆ\u0001¢\u0006\u0002\u00107J\u0013\u00108\u001a\u00020\u00052\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010:\u001a\u00020\u0013HÖ\u0001J\t\u0010;\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0019¨\u0006<"}, d2 = {"Lcom/amazon/music/find/viewmodels/SearchViewModel$ResumeContextData;", "", "mode", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "force", "", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "showOrdinals", "resetOrdinals", "filterSelectionsChanged", "filterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "searchTermChanged", "searchTerm", "", "seeMoreEntityType", "Lcom/amazon/music/platform/find/common/EntityType;", "screenOrientation", "", PageUriUtils.QUERYMETADATA_QUERY_PARAM, "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;ZLcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;ZZZLcom/amazon/music/find/model/filter/SearchFilterSelections;ZLjava/lang/String;Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/Integer;Ljava/lang/String;)V", "getFilterSelections", "()Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "getFilterSelectionsChanged", "()Z", "getForce", "getInteractionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "getMode", "()Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "getQueryMetadata", "()Ljava/lang/String;", "getResetOrdinals", "getScreenOrientation", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSearchTerm", "getSearchTermChanged", "getSeeMoreEntityType", "()Lcom/amazon/music/platform/find/common/EntityType;", "getShowOrdinals", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;ZLcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;ZZZLcom/amazon/music/find/model/filter/SearchFilterSelections;ZLjava/lang/String;Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/Integer;Ljava/lang/String;)Lcom/amazon/music/find/viewmodels/SearchViewModel$ResumeContextData;", "equals", "other", "hashCode", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class ResumeContextData {
        private final SearchFilterSelections filterSelections;
        private final boolean filterSelectionsChanged;
        private final boolean force;
        private final InteractionType interactionType;
        private final SearchExperienceMode mode;
        private final String queryMetadata;
        private final boolean resetOrdinals;
        private final Integer screenOrientation;
        private final String searchTerm;
        private final boolean searchTermChanged;
        private final EntityType seeMoreEntityType;
        private final boolean showOrdinals;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final SearchExperienceMode getMode() {
            return this.mode;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final EntityType getSeeMoreEntityType() {
            return this.seeMoreEntityType;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Integer getScreenOrientation() {
            return this.screenOrientation;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getQueryMetadata() {
            return this.queryMetadata;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getForce() {
            return this.force;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final InteractionType getInteractionType() {
            return this.interactionType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getShowOrdinals() {
            return this.showOrdinals;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getResetOrdinals() {
            return this.resetOrdinals;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getFilterSelectionsChanged() {
            return this.filterSelectionsChanged;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final SearchFilterSelections getFilterSelections() {
            return this.filterSelections;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final boolean getSearchTermChanged() {
            return this.searchTermChanged;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getSearchTerm() {
            return this.searchTerm;
        }

        public final ResumeContextData copy(SearchExperienceMode mode, boolean force, InteractionType interactionType, boolean showOrdinals, boolean resetOrdinals, boolean filterSelectionsChanged, SearchFilterSelections filterSelections, boolean searchTermChanged, String searchTerm, EntityType seeMoreEntityType, Integer screenOrientation, String queryMetadata) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(interactionType, "interactionType");
            Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
            return new ResumeContextData(mode, force, interactionType, showOrdinals, resetOrdinals, filterSelectionsChanged, filterSelections, searchTermChanged, searchTerm, seeMoreEntityType, screenOrientation, queryMetadata);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResumeContextData)) {
                return false;
            }
            ResumeContextData resumeContextData = (ResumeContextData) other;
            return this.mode == resumeContextData.mode && this.force == resumeContextData.force && this.interactionType == resumeContextData.interactionType && this.showOrdinals == resumeContextData.showOrdinals && this.resetOrdinals == resumeContextData.resetOrdinals && this.filterSelectionsChanged == resumeContextData.filterSelectionsChanged && Intrinsics.areEqual(this.filterSelections, resumeContextData.filterSelections) && this.searchTermChanged == resumeContextData.searchTermChanged && Intrinsics.areEqual(this.searchTerm, resumeContextData.searchTerm) && this.seeMoreEntityType == resumeContextData.seeMoreEntityType && Intrinsics.areEqual(this.screenOrientation, resumeContextData.screenOrientation) && Intrinsics.areEqual(this.queryMetadata, resumeContextData.queryMetadata);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v26 */
        /* JADX WARN: Type inference failed for: r1v27 */
        /* JADX WARN: Type inference failed for: r1v28 */
        /* JADX WARN: Type inference failed for: r1v29 */
        /* JADX WARN: Type inference failed for: r1v30 */
        /* JADX WARN: Type inference failed for: r1v31 */
        /* JADX WARN: Type inference failed for: r1v32 */
        /* JADX WARN: Type inference failed for: r1v33 */
        /* JADX WARN: Type inference failed for: r1v5, types: [int] */
        /* JADX WARN: Type inference failed for: r1v7, types: [int] */
        /* JADX WARN: Type inference failed for: r1v9, types: [int] */
        /* JADX WARN: Type inference failed for: r2v0 */
        /* JADX WARN: Type inference failed for: r2v1, types: [int] */
        /* JADX WARN: Type inference failed for: r2v5 */
        public int hashCode() {
            int iHashCode = this.mode.hashCode() * 31;
            boolean z = this.force;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            int iHashCode2 = (((iHashCode + r1) * 31) + this.interactionType.hashCode()) * 31;
            boolean z2 = this.showOrdinals;
            ?? r12 = z2;
            if (z2) {
                r12 = 1;
            }
            int i = (iHashCode2 + r12) * 31;
            boolean z3 = this.resetOrdinals;
            ?? r13 = z3;
            if (z3) {
                r13 = 1;
            }
            int i2 = (i + r13) * 31;
            boolean z4 = this.filterSelectionsChanged;
            ?? r14 = z4;
            if (z4) {
                r14 = 1;
            }
            int iHashCode3 = (((i2 + r14) * 31) + this.filterSelections.hashCode()) * 31;
            boolean z5 = this.searchTermChanged;
            int i3 = (iHashCode3 + (z5 ? 1 : z5)) * 31;
            String str = this.searchTerm;
            int iHashCode4 = (i3 + (str == null ? 0 : str.hashCode())) * 31;
            EntityType entityType = this.seeMoreEntityType;
            int iHashCode5 = (iHashCode4 + (entityType == null ? 0 : entityType.hashCode())) * 31;
            Integer num = this.screenOrientation;
            int iHashCode6 = (iHashCode5 + (num == null ? 0 : num.hashCode())) * 31;
            String str2 = this.queryMetadata;
            return iHashCode6 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "ResumeContextData(mode=" + this.mode + ", force=" + this.force + ", interactionType=" + this.interactionType + ", showOrdinals=" + this.showOrdinals + ", resetOrdinals=" + this.resetOrdinals + ", filterSelectionsChanged=" + this.filterSelectionsChanged + ", filterSelections=" + this.filterSelections + ", searchTermChanged=" + this.searchTermChanged + ", searchTerm=" + ((Object) this.searchTerm) + ", seeMoreEntityType=" + this.seeMoreEntityType + ", screenOrientation=" + this.screenOrientation + ", queryMetadata=" + ((Object) this.queryMetadata) + ')';
        }

        public ResumeContextData(SearchExperienceMode mode, boolean z, InteractionType interactionType, boolean z2, boolean z3, boolean z4, SearchFilterSelections filterSelections, boolean z5, String str, EntityType entityType, Integer num, String str2) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(interactionType, "interactionType");
            Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
            this.mode = mode;
            this.force = z;
            this.interactionType = interactionType;
            this.showOrdinals = z2;
            this.resetOrdinals = z3;
            this.filterSelectionsChanged = z4;
            this.filterSelections = filterSelections;
            this.searchTermChanged = z5;
            this.searchTerm = str;
            this.seeMoreEntityType = entityType;
            this.screenOrientation = num;
            this.queryMetadata = str2;
        }

        public /* synthetic */ ResumeContextData(SearchExperienceMode searchExperienceMode, boolean z, InteractionType interactionType, boolean z2, boolean z3, boolean z4, SearchFilterSelections searchFilterSelections, boolean z5, String str, EntityType entityType, Integer num, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(searchExperienceMode, z, interactionType, z2, z3, z4, searchFilterSelections, z5, (i & 256) != 0 ? null : str, (i & 512) != 0 ? null : entityType, num, (i & 2048) != 0 ? null : str2);
        }

        public final SearchExperienceMode getMode() {
            return this.mode;
        }

        public final boolean getForce() {
            return this.force;
        }

        public final InteractionType getInteractionType() {
            return this.interactionType;
        }

        public final boolean getShowOrdinals() {
            return this.showOrdinals;
        }

        public final boolean getResetOrdinals() {
            return this.resetOrdinals;
        }

        public final boolean getFilterSelectionsChanged() {
            return this.filterSelectionsChanged;
        }

        public final SearchFilterSelections getFilterSelections() {
            return this.filterSelections;
        }

        public final boolean getSearchTermChanged() {
            return this.searchTermChanged;
        }

        public final String getSearchTerm() {
            return this.searchTerm;
        }

        public final EntityType getSeeMoreEntityType() {
            return this.seeMoreEntityType;
        }

        public final Integer getScreenOrientation() {
            return this.screenOrientation;
        }

        public final String getQueryMetadata() {
            return this.queryMetadata;
        }
    }
}
