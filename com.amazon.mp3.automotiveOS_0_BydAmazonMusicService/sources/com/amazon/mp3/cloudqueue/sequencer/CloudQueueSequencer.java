package com.amazon.mp3.cloudqueue.sequencer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.digitalmusicxp.callbacks.Outcome;
import com.amazon.digitalmusicxp.clients.CloudQueueClient;
import com.amazon.digitalmusicxp.enums.GetNextQueueEntityResponseStatusEnum;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.digitalmusicxp.enums.QueueSequenceSliceName;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.digitalmusicxp.inputs.ParentalControlsInput;
import com.amazon.digitalmusicxp.inputs.QueueSeedContextInput;
import com.amazon.digitalmusicxp.models.Queue;
import com.amazon.digitalmusicxp.models.QueueEntityMetadata;
import com.amazon.digitalmusicxp.models.QueueSequenceSlice;
import com.amazon.digitalmusicxp.types.EntityReference;
import com.amazon.digitalmusicxp.types.GenericQueueEntity;
import com.amazon.digitalmusicxp.types.ImageSizeUrlTuple;
import com.amazon.digitalmusicxp.types.QueueCapability;
import com.amazon.digitalmusicxp.types.QueueSeed;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.basequeue.NoOpCloudQueueClient;
import com.amazon.mp3.catalog.fragment.datasource.dao.AmplifyPageModelDAO;
import com.amazon.mp3.cloudqueue.CloudQueueAction;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.cloudqueue.CloudQueueDigitalXPUtil;
import com.amazon.mp3.cloudqueue.CloudQueuePlaybackUtil;
import com.amazon.mp3.cloudqueue.QueueEntityCapabilityTranslator;
import com.amazon.mp3.cloudqueue.metrics.CloudQueueFlexMetricsManager;
import com.amazon.mp3.cloudqueue.model.MetricsContext;
import com.amazon.mp3.dagger.DaggerSubcomponents;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.skiplimit.impl.SkipLimitProviderImpl;
import com.amazon.mp3.starlight.provider.HybridQueueStateProvider;
import com.amazon.mp3.util.CQEThrottleUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.view.stage.StageAutomatedSequencingHandler;
import com.amazon.mp3.view.stage.overlay.OverlayLyricsStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayTypeStateProvider;
import com.amazon.music.ContentAccessType;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.media.playback.ActionAvailability;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.sequencer.IndexedSequencerBase;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.data.entity.EntityItem;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: CloudQueueSequencer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 Å\u00012\u00020\u0001:\u0002Å\u0001B\u0095\u0001\u0012\u0006\u0010;\u001a\u00020)\u0012\u0006\u0010n\u001a\u00020,\u0012\u0007\u0010À\u0001\u001a\u00020.\u0012\u0011\b\u0002\u0010Á\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000e\u0012\b\b\u0002\u0010`\u001a\u00020\n\u0012\u0006\u0010\u001c\u001a\u00020\u0013\u0012\u0006\u0010\u001d\u001a\u00020\u0013\u0012\u000f\u0010Â\u0001\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e\u0012\u0006\u0010t\u001a\u00020s\u0012\u0006\u0010v\u001a\u00020F\u0012\u0006\u0010\\\u001a\u00020[\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010~\u001a\u0004\u0018\u00010U\u0012\u0007\u0010\u0080\u0001\u001a\u00020)¢\u0006\u0006\bÃ\u0001\u0010Ä\u0001J\"\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\b\u0010\t\u001a\u00020\u0007H\u0003J\u001a\u0010\b\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002J\u0016\u0010\u0010\u001a\u00020\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0002J\u0016\u0010\u0011\u001a\u00020\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0002J\u0014\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\u0016\u0010\u0015\u001a\u00020\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0002J\b\u0010\u0016\u001a\u00020\u0007H\u0002J\u0012\u0010\u0018\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0013H\u0002JD\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u000e0\u001f2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0013H\u0002J\b\u0010\"\u001a\u00020\u0007H\u0002J\u0012\u0010$\u001a\u00020\u00072\b\u0010#\u001a\u0004\u0018\u00010\fH\u0002J&\u0010*\u001a\u00020)2\b\u0010&\u001a\u0004\u0018\u00010%2\b\u0010'\u001a\u0004\u0018\u00010\u00042\b\u0010(\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010+\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0006\u0010-\u001a\u00020,J\u0006\u0010/\u001a\u00020.J\u0006\u00100\u001a\u00020.J\u0017\u00102\u001a\u0004\u0018\u00010\n2\u0006\u00101\u001a\u00020\u0013¢\u0006\u0004\b2\u00103J\f\u00104\u001a\b\u0012\u0004\u0012\u00020\f0\u000eJ\u0006\u00105\u001a\u00020\u0013J\u0006\u00106\u001a\u00020\nJ\u000e\u00109\u001a\u00020\u00072\u0006\u00108\u001a\u000207J\u0006\u0010:\u001a\u00020\u0007J\u0006\u0010;\u001a\u00020)J\u0010\u0010=\u001a\u00020\u00072\u0006\u0010<\u001a\u00020\nH\u0017J\b\u0010?\u001a\u00020>H\u0016J\b\u0010@\u001a\u00020)H\u0016J\b\u0010A\u001a\u00020\u0013H\u0016J\b\u0010C\u001a\u00020BH\u0016J\b\u0010D\u001a\u00020BH\u0016J\u0014\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\b\u0010G\u001a\u00020FH\u0016J\u0010\u0010H\u001a\u00020\u00072\u0006\u0010H\u001a\u00020)H\u0007J\u0016\u0010K\u001a\u00020\u00072\u0006\u0010I\u001a\u00020\n2\u0006\u0010J\u001a\u00020\nJ\u0016\u0010N\u001a\u00020\u00072\u0006\u0010L\u001a\u00020\n2\u0006\u0010M\u001a\u00020)J\u000e\u0010O\u001a\u00020\u00072\u0006\u0010L\u001a\u00020\nJ\u0006\u0010P\u001a\u00020\u0007J\u0012\u0010R\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00020QJ\u0012\u0010S\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00020QJ\u0006\u0010T\u001a\u00020\nJ\b\u0010V\u001a\u0004\u0018\u00010UJ\u001c\u0010X\u001a\u00020\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\u0006\u0010W\u001a\u00020)J\n\u0010Z\u001a\u0004\u0018\u00010YH\u0016Ji\u0010d\u001a\u00020)2\u0006\u0010;\u001a\u00020)2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\\\u001a\u00020[2\u0006\u0010^\u001a\u00020]2\u0006\u0010_\u001a\u00020)2\n\b\u0002\u0010`\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010a\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010c\u001a\u0004\u0018\u00010b¢\u0006\u0004\bd\u0010eJ\u000e\u0010g\u001a\u00020)2\u0006\u0010f\u001a\u00020]J\u0006\u0010h\u001a\u00020\nJ*\u0010k\u001a\u00020\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\u0006\u0010i\u001a\u00020)2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eJ\u0006\u0010l\u001a\u00020\u0007J\u0006\u0010m\u001a\u00020)R\u0014\u0010n\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bn\u0010oR\u0017\u0010`\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b`\u0010p\u001a\u0004\bq\u0010rR\u0014\u0010t\u001a\u00020s8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bt\u0010uR\u0014\u0010v\u001a\u00020F8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bv\u0010wR\u0017\u0010\\\u001a\u00020[8\u0006¢\u0006\f\n\u0004\b\\\u0010x\u001a\u0004\by\u0010zR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010{\u001a\u0004\b|\u0010}R\u0016\u0010~\u001a\u0004\u0018\u00010U8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b~\u0010\u007fR\u001c\u0010\u0080\u0001\u001a\u00020)8\u0006¢\u0006\u0010\n\u0006\b\u0080\u0001\u0010\u0081\u0001\u001a\u0006\b\u0080\u0001\u0010\u0082\u0001R\u0016\u0010\u0083\u0001\u001a\u00020\n8\u0002X\u0082D¢\u0006\u0007\n\u0005\b\u0083\u0001\u0010pR*\u0010\u0085\u0001\u001a\u00030\u0084\u00018\u0006@\u0006X\u0086.¢\u0006\u0018\n\u0006\b\u0085\u0001\u0010\u0086\u0001\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001\"\u0006\b\u0089\u0001\u0010\u008a\u0001R\u0019\u0010\u008b\u0001\u001a\u00020.8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008b\u0001\u0010\u008c\u0001R\u001a\u0010\u008e\u0001\u001a\u00030\u008d\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0017\u0010\u001c\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u001c\u0010\u0090\u0001R\u0017\u0010\u001d\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u001d\u0010\u0090\u0001R\u0019\u0010\u0091\u0001\u001a\u00020.8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0091\u0001\u0010\u008c\u0001R#\u0010\u0092\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00020Q8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0092\u0001\u0010\u0093\u0001R#\u0010\u0094\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00020Q8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0094\u0001\u0010\u0093\u0001R\u0018\u0010\u0095\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0095\u0001\u0010pR\u0015\u0010;\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b;\u0010\u0081\u0001R\u0018\u0010\u0096\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0096\u0001\u0010pR\u001a\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0097\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0098\u0001\u0010\u0099\u0001R\u001b\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009a\u0001\u0010\u0090\u0001R,\u0010\u009f\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u00128BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u009b\u0001\u0010\u009c\u0001\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R\"\u0010£\u0001\u001a\u0004\u0018\u00010\u00138BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b \u0001\u0010\u009c\u0001\u001a\u0006\b¡\u0001\u0010¢\u0001R*\u0010¥\u0001\u001a\u00030¤\u00018\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\b¥\u0001\u0010¦\u0001\u001a\u0006\b§\u0001\u0010¨\u0001\"\u0006\b©\u0001\u0010ª\u0001R*\u0010¬\u0001\u001a\u00030«\u00018\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\b¬\u0001\u0010\u00ad\u0001\u001a\u0006\b®\u0001\u0010¯\u0001\"\u0006\b°\u0001\u0010±\u0001R*\u0010³\u0001\u001a\u00030²\u00018\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\b³\u0001\u0010´\u0001\u001a\u0006\bµ\u0001\u0010¶\u0001\"\u0006\b·\u0001\u0010¸\u0001R*\u0010º\u0001\u001a\u00030¹\u00018\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\bº\u0001\u0010»\u0001\u001a\u0006\b¼\u0001\u0010½\u0001\"\u0006\b¾\u0001\u0010¿\u0001¨\u0006Æ\u0001"}, d2 = {"Lcom/amazon/mp3/cloudqueue/sequencer/CloudQueueSequencer;", "Lcom/amazon/mp3/cloudqueue/sequencer/RemoteSourceSequencer;", "Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "queueEntityMetadata", "Landroid/net/Uri;", "contentUri", "collectionUri", "", "replace", "updateSliceToDatastoreHelper", "", "index", "Lcom/amazon/music/media/playback/MediaItem;", "newMediaItem", "", "mediaItems", "insertAllToBottom", "insertAllToNext", "", "", "constructImageUrlMap", "addMediaItemsIntoHarley", "resetToggleState", "queueId", "resetOverlayState", "Landroid/content/Context;", "context", "lastId", Splash.PARAMS_DEVICE_ID, Splash.PARAMS_DEVICE_TYPE, "customerId", "Lcom/amazon/digitalmusicxp/callbacks/Outcome;", "Lcom/amazon/digitalmusicxp/types/GenericQueueEntity;", "fetchMoreTracks", "updateAdditionalMediaCollectionId", "mediaItem", "updateAdditionalMediaCollectionIdForMediaItem", "Lcom/amazon/digitalmusicxp/inputs/QueueSeedContextInput;", "queueSeedContextInput", "newContentUri", "currentContentUri", "", "isSFAPlaybackTheSame", "getIdentifier", "Lcom/amazon/digitalmusicxp/models/Queue;", "getCurrentPlayingQueue", "Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;", "getCurrentPlayingQueueSequenceSlice", "getCurrentPlayingLocalQueueSequenceSlice", "currentPlayingAsin", "getCurrentPlayingIndex", "(Ljava/lang/String;)Ljava/lang/Integer;", "getQueue", "getQueueId", "getSequenceVersion", "Lcom/amazon/mp3/cloudqueue/CloudQueueAction;", "cloudQueueAction", "updateLocalQueueSequenceSlice", "updateSliceToDatastore", "isOnlineInteraction", "numItems", "fetchUpcomingMediaItems", "Lcom/amazon/music/media/playback/access/MediaAccessInfo;", "constructMediaAccessInfo", "hasInitTrackLoaded", "getQueueTitle", "Lcom/amazon/music/media/playback/MediaCollectionId$Type;", "getCollectionPrimaryIdType", "getCollectionImageUrlIdType", "getImageUrlMap", "Lcom/amazon/mp3/cloudqueue/model/MetricsContext;", "getMetricsContext", "shuffle", "fromIndex", "toIndex", "move", "deleteIndex", "backgroundPlayback", "removeTracks", "delete", "clearQueue", "", "getEntityMap", "getLocalEntityMap", "getMaxAvailableStationItems", "Lcom/amazon/music/media/playback/MediaCollectionId;", "getAdditionalMediaCollectionId", "playNext", "addTrackToPlayback", "Lcom/amazon/music/media/playback/ActionAvailability;", "getSkipNextAvailability", "Lcom/amazon/music/media/playback/ControlSource;", "controlSource", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "isShuffle", "startPosition", "tracksCount", "Lcom/amazon/digitalmusicxp/types/QueueSeed;", "queueSeed", "shouldResumePlayback", "(ZLandroid/net/Uri;Lcom/amazon/music/media/playback/ControlSource;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;ZLjava/lang/Integer;Ljava/lang/Integer;Lcom/amazon/digitalmusicxp/inputs/QueueSeedContextInput;Lcom/amazon/digitalmusicxp/types/QueueSeed;)Z", "pageType", "isPlaylistDetailPageType", "getSkipsRemaining", "addNext", "tracks", "addTracksToPlayQueue", "updateSequencerWithAOMLocalQueueInteractionLogic", "enforceSkipLimitOrTooltip", "queue", "Lcom/amazon/digitalmusicxp/models/Queue;", "I", "getStartPosition", "()I", "Lcom/amazon/digitalmusicxp/types/QueueCapability;", "queueCapability", "Lcom/amazon/digitalmusicxp/types/QueueCapability;", "collectionMetricsContext", "Lcom/amazon/mp3/cloudqueue/model/MetricsContext;", "Lcom/amazon/music/media/playback/ControlSource;", "getControlSource", "()Lcom/amazon/music/media/playback/ControlSource;", "Landroid/net/Uri;", "getContentUri", "()Landroid/net/Uri;", "additionalMediaCollectionId", "Lcom/amazon/music/media/playback/MediaCollectionId;", "isUserInitiated", "Z", "()Z", "NATURAL_SEQUENCE_VERSION", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/AmplifyPageModelDAO;", "amplifyDao", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/AmplifyPageModelDAO;", "getAmplifyDao", "()Lcom/amazon/mp3/catalog/fragment/datasource/dao/AmplifyPageModelDAO;", "setAmplifyDao", "(Lcom/amazon/mp3/catalog/fragment/datasource/dao/AmplifyPageModelDAO;)V", "localQueueSequenceSlice", "Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "fetchingUpcoming", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/lang/String;", "slice", "entityMap", "Ljava/util/Map;", "localEntityMap", "maxStationItems", "shuffleSequenceVersion", "Lcom/amazon/mp3/likes/LikesServiceProxy;", "likesService", "Lcom/amazon/mp3/likes/LikesServiceProxy;", "lastOnlineEntityReferenceId", "mImageUrlMap$delegate", "Lkotlin/Lazy;", "getMImageUrlMap", "()Ljava/util/Map;", "mImageUrlMap", "serviceTier$delegate", "getServiceTier", "()Ljava/lang/String;", "serviceTier", "Lcom/amazon/mp3/starlight/provider/HybridQueueStateProvider;", "hybridQueueStateProvider", "Lcom/amazon/mp3/starlight/provider/HybridQueueStateProvider;", "getHybridQueueStateProvider", "()Lcom/amazon/mp3/starlight/provider/HybridQueueStateProvider;", "setHybridQueueStateProvider", "(Lcom/amazon/mp3/starlight/provider/HybridQueueStateProvider;)V", "Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "overlayTypeStateProvider", "Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "getOverlayTypeStateProvider", "()Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "setOverlayTypeStateProvider", "(Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;)V", "Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;", "overlayLyricsStateProvider", "Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;", "getOverlayLyricsStateProvider", "()Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;", "setOverlayLyricsStateProvider", "(Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;)V", "Lcom/amazon/mp3/view/stage/StageAutomatedSequencingHandler;", "stageAutomatedSequencingHandler", "Lcom/amazon/mp3/view/stage/StageAutomatedSequencingHandler;", "getStageAutomatedSequencingHandler", "()Lcom/amazon/mp3/view/stage/StageAutomatedSequencingHandler;", "setStageAutomatedSequencingHandler", "(Lcom/amazon/mp3/view/stage/StageAutomatedSequencingHandler;)V", "queueSequenceSlice", "initialMediaItems", "entityList", "<init>", "(ZLcom/amazon/digitalmusicxp/models/Queue;Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;Ljava/util/List;ILjava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/amazon/digitalmusicxp/types/QueueCapability;Lcom/amazon/mp3/cloudqueue/model/MetricsContext;Lcom/amazon/music/media/playback/ControlSource;Landroid/net/Uri;Lcom/amazon/music/media/playback/MediaCollectionId;Z)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CloudQueueSequencer extends RemoteSourceSequencer {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = CloudQueueSequencer.class.getSimpleName();
    private final int NATURAL_SEQUENCE_VERSION;
    private final MediaCollectionId additionalMediaCollectionId;
    public AmplifyPageModelDAO amplifyDao;
    private final MetricsContext collectionMetricsContext;
    private final Uri contentUri;
    private final ControlSource controlSource;
    private String deviceId;
    private String deviceType;
    private final Map<String, QueueEntityMetadata> entityMap;
    private AtomicBoolean fetchingUpcoming;
    public HybridQueueStateProvider hybridQueueStateProvider;
    private final boolean isOnlineInteraction;
    private final boolean isUserInitiated;
    private String lastOnlineEntityReferenceId;
    private final LikesServiceProxy likesService;
    private final Map<String, QueueEntityMetadata> localEntityMap;
    private QueueSequenceSlice localQueueSequenceSlice;

    /* JADX INFO: renamed from: mImageUrlMap$delegate, reason: from kotlin metadata */
    private final Lazy mImageUrlMap;
    private int maxStationItems;
    public OverlayLyricsStateProvider overlayLyricsStateProvider;
    public OverlayTypeStateProvider overlayTypeStateProvider;
    private final Queue queue;
    private final QueueCapability queueCapability;

    /* JADX INFO: renamed from: serviceTier$delegate, reason: from kotlin metadata */
    private final Lazy serviceTier;
    private int shuffleSequenceVersion;
    private QueueSequenceSlice slice;
    public StageAutomatedSequencingHandler stageAutomatedSequencingHandler;
    private final int startPosition;

    @JvmStatic
    public static final CloudQueueSequencer getCloudQueueSequencer() {
        return INSTANCE.getCloudQueueSequencer();
    }

    @JvmStatic
    public static final boolean isCloudQueuePlaying() {
        return INSTANCE.isCloudQueuePlaying();
    }

    @JvmStatic
    public static final void shuffleAll(boolean z) {
        INSTANCE.shuffleAll(z);
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    public boolean hasInitTrackLoaded() {
        return true;
    }

    public final int getStartPosition() {
        return this.startPosition;
    }

    public final ControlSource getControlSource() {
        return this.controlSource;
    }

    public final Uri getContentUri() {
        return this.contentUri;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CloudQueueSequencer(boolean z, Queue queue, QueueSequenceSlice queueSequenceSlice, List<? extends MediaItem> initialMediaItems, int i, String deviceId, String deviceType, List<QueueEntityMetadata> list, QueueCapability queueCapability, MetricsContext collectionMetricsContext, ControlSource controlSource, Uri uri, MediaCollectionId mediaCollectionId, boolean z2) {
        int size;
        super(z);
        Intrinsics.checkNotNullParameter(queue, "queue");
        Intrinsics.checkNotNullParameter(queueSequenceSlice, "queueSequenceSlice");
        Intrinsics.checkNotNullParameter(initialMediaItems, "initialMediaItems");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(queueCapability, "queueCapability");
        Intrinsics.checkNotNullParameter(collectionMetricsContext, "collectionMetricsContext");
        Intrinsics.checkNotNullParameter(controlSource, "controlSource");
        this.queue = queue;
        this.startPosition = i;
        this.queueCapability = queueCapability;
        this.collectionMetricsContext = collectionMetricsContext;
        this.controlSource = controlSource;
        this.contentUri = uri;
        this.additionalMediaCollectionId = mediaCollectionId;
        this.isUserInitiated = z2;
        int i2 = 1;
        this.NATURAL_SEQUENCE_VERSION = 1;
        this.localQueueSequenceSlice = queueSequenceSlice;
        this.fetchingUpcoming = new AtomicBoolean(false);
        this.entityMap = new LinkedHashMap();
        this.localEntityMap = new LinkedHashMap();
        this.maxStationItems = 1;
        this.isOnlineInteraction = z;
        this.mImageUrlMap = LazyKt__LazyJVMKt.lazy(new Function0<Map<String, ? extends String>>() { // from class: com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer$mImageUrlMap$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<String, ? extends String> invoke() {
                return this.this$0.constructImageUrlMap();
            }
        });
        this.serviceTier = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer$serviceTier$2
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                ServiceTierEnum serviceTier = CloudQueueDigitalXPUtil.INSTANCE.getServiceTier();
                if (serviceTier == null) {
                    return null;
                }
                return serviceTier.name();
            }
        });
        DaggerSubcomponents.INSTANCE.getNowPlayingComponent().inject(this);
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        sequencerInfoCopySequencerInfo.setMediaCollectionInfo(new MediaCollectionInfo(queue.getTitle(), null, null, MediaCollectionType.SONGS, constructMediaAccessInfo(), new MediaCollectionId[0]));
        DefaultConstructorMarker defaultConstructorMarker = null;
        sequencerInfoCopySequencerInfo.setCanShuffle(CloudQueueDigitalXPUtil.canPerformActionStrategy$default(queueCapability.getShuffle(), null, 2, null));
        sequencerInfoCopySequencerInfo.setRepeatModes(CloudQueueDigitalXPUtil.canPerformActionStrategy$default(queueCapability.getLoopAll(), null, 2, null) ? RepeatMode.ALL_REPEAT_MODES : RepeatMode.REPEAT_ONE_OR_NONE);
        sequencerInfoCopySequencerInfo.setCanLoadMore(z);
        Context context = AmazonApplication.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext()");
        this.likesService = new LikesServiceProxy(context);
        if (!initialMediaItems.isEmpty()) {
            if (mediaCollectionId != null) {
                Iterator<T> it = initialMediaItems.iterator();
                while (it.hasNext()) {
                    updateAdditionalMediaCollectionIdForMediaItem((MediaItem) it.next());
                }
            }
            sequencerInfoCopySequencerInfo.getQueue().addAll(CollectionsKt___CollectionsKt.filterNotNull(initialMediaItems));
            size = sequencerInfoCopySequencerInfo.getQueue().size();
            sequencerInfoCopySequencerInfo.setCurrentPosition(this.startPosition);
            if (z && AmazonApplication.getCapabilities().isNPSLikesSupported()) {
                CloudQueuePlaybackUtil.INSTANCE.fetchMediaItemLikeStates(initialMediaItems, this.likesService);
            }
        } else {
            size = 0;
        }
        if (!z) {
            sequencerInfoCopySequencerInfo.setMaximumPosition(size);
        } else {
            this.maxStationItems = size;
            EntityReference entityReference = (EntityReference) CollectionsKt___CollectionsKt.lastOrNull((List) queueSequenceSlice.getEntityReferences());
            this.lastOnlineEntityReferenceId = entityReference == null ? null : entityReference.getEntityReferenceId();
        }
        setSequencerInfo(sequencerInfoCopySequencerInfo);
        if (z) {
            updateMediaCollectionInfo();
        }
        updateAdditionalMediaCollectionId();
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        setAmplifyDao(new AmplifyPageModelDAO(false, i2, defaultConstructorMarker));
        this.slice = queueSequenceSlice;
        this.localQueueSequenceSlice = queueSequenceSlice;
        if (list != null) {
            for (QueueEntityMetadata queueEntityMetadata : list) {
                this.entityMap.put(queueEntityMetadata.getIdentifier(), queueEntityMetadata);
                this.localEntityMap.put(queueEntityMetadata.getIdentifier(), queueEntityMetadata);
            }
        }
        resetToggleState();
        resetOverlayState(this.queue.getId());
    }

    /* JADX INFO: renamed from: isUserInitiated, reason: from getter */
    public final boolean getIsUserInitiated() {
        return this.isUserInitiated;
    }

    public final AmplifyPageModelDAO getAmplifyDao() {
        AmplifyPageModelDAO amplifyPageModelDAO = this.amplifyDao;
        if (amplifyPageModelDAO != null) {
            return amplifyPageModelDAO;
        }
        Intrinsics.throwUninitializedPropertyAccessException("amplifyDao");
        return null;
    }

    public final void setAmplifyDao(AmplifyPageModelDAO amplifyPageModelDAO) {
        Intrinsics.checkNotNullParameter(amplifyPageModelDAO, "<set-?>");
        this.amplifyDao = amplifyPageModelDAO;
    }

    private final Map<String, String> getMImageUrlMap() {
        return (Map) this.mImageUrlMap.getValue();
    }

    private final String getServiceTier() {
        return (String) this.serviceTier.getValue();
    }

    public final HybridQueueStateProvider getHybridQueueStateProvider() {
        HybridQueueStateProvider hybridQueueStateProvider = this.hybridQueueStateProvider;
        if (hybridQueueStateProvider != null) {
            return hybridQueueStateProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hybridQueueStateProvider");
        return null;
    }

    public final OverlayTypeStateProvider getOverlayTypeStateProvider() {
        OverlayTypeStateProvider overlayTypeStateProvider = this.overlayTypeStateProvider;
        if (overlayTypeStateProvider != null) {
            return overlayTypeStateProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("overlayTypeStateProvider");
        return null;
    }

    public final OverlayLyricsStateProvider getOverlayLyricsStateProvider() {
        OverlayLyricsStateProvider overlayLyricsStateProvider = this.overlayLyricsStateProvider;
        if (overlayLyricsStateProvider != null) {
            return overlayLyricsStateProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("overlayLyricsStateProvider");
        return null;
    }

    public final StageAutomatedSequencingHandler getStageAutomatedSequencingHandler() {
        StageAutomatedSequencingHandler stageAutomatedSequencingHandler = this.stageAutomatedSequencingHandler;
        if (stageAutomatedSequencingHandler != null) {
            return stageAutomatedSequencingHandler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("stageAutomatedSequencingHandler");
        return null;
    }

    /* JADX INFO: compiled from: CloudQueueSequencer.kt */
    @Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\b\u0010\u0003\u001a\u00020\u0002H\u0007J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\n\u0010\t\u001a\u0004\u0018\u00010\bH\u0007J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0002H\u0007J\b\u0010\r\u001a\u0004\u0018\u00010\u0006J\u001e\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010J\b\u0010\u0014\u001a\u00020\u0002H\u0007J\b\u0010\u0015\u001a\u00020\u0002H\u0007R\u001c\u0010\u0018\u001a\n \u0017*\u0004\u0018\u00010\u00160\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/cloudqueue/sequencer/CloudQueueSequencer$Companion;", "", "", "isCloudQueuePlaying", "Lcom/amazon/digitalmusicxp/models/Queue;", "getCurrentPlayingQueueObject", "Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;", "getCurrentPlayingQueueSequenceSlice", "Lcom/amazon/mp3/cloudqueue/sequencer/CloudQueueSequencer;", "getCloudQueueSequencer", "shuffle", "", "shuffleAll", "getCurrentPlayingLocalQueueSequenceSlice", "Lcom/amazon/digitalmusicxp/models/QueueEntityMetadata;", "queueEntityMetadata", "Landroid/net/Uri;", "contentUri", "collectionUri", "replaceTrack", "blockQueueMutation", "hideUpcomingItem", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final boolean isCloudQueuePlaying() {
            return getCloudQueueSequencer() != null;
        }

        public final Queue getCurrentPlayingQueueObject() {
            CloudQueueSequencer cloudQueueSequencer = getCloudQueueSequencer();
            if (cloudQueueSequencer == null) {
                return null;
            }
            return cloudQueueSequencer.getQueue();
        }

        public final QueueSequenceSlice getCurrentPlayingQueueSequenceSlice() {
            CloudQueueSequencer cloudQueueSequencer = getCloudQueueSequencer();
            if (cloudQueueSequencer == null) {
                return null;
            }
            return cloudQueueSequencer.getSlice();
        }

        @JvmStatic
        public final CloudQueueSequencer getCloudQueueSequencer() {
            if (!(Playback.getInstance().getPlaybackProvider() instanceof SequencerPlaybackProvider)) {
                return null;
            }
            PlaybackProvider playbackProvider = Playback.getInstance().getPlaybackProvider();
            if (playbackProvider == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.media.playback.SequencerPlaybackProvider");
            }
            Sequencer sequencer = ((SequencerPlaybackProvider) playbackProvider).getSequencer();
            if (sequencer instanceof CloudQueueSequencer) {
                return (CloudQueueSequencer) sequencer;
            }
            return null;
        }

        @JvmStatic
        public final void shuffleAll(boolean shuffle) {
            CloudQueueSequencer cloudQueueSequencer = getCloudQueueSequencer();
            if (cloudQueueSequencer == null) {
                return;
            }
            cloudQueueSequencer.shuffle(shuffle);
        }

        public final QueueSequenceSlice getCurrentPlayingLocalQueueSequenceSlice() {
            CloudQueueSequencer cloudQueueSequencer = getCloudQueueSequencer();
            if (cloudQueueSequencer == null) {
                return null;
            }
            return cloudQueueSequencer.getLocalQueueSequenceSlice();
        }

        public final void replaceTrack(QueueEntityMetadata queueEntityMetadata, Uri contentUri, Uri collectionUri) {
            Intrinsics.checkNotNullParameter(queueEntityMetadata, "queueEntityMetadata");
            Intrinsics.checkNotNullParameter(contentUri, "contentUri");
            Intrinsics.checkNotNullParameter(collectionUri, "collectionUri");
            CloudQueueSequencer cloudQueueSequencer = getCloudQueueSequencer();
            if (cloudQueueSequencer == null) {
                return;
            }
            cloudQueueSequencer.replace(queueEntityMetadata, contentUri, collectionUri);
        }

        @JvmStatic
        public final boolean blockQueueMutation() {
            QueueCapability queueCapability;
            CloudQueueSequencer cloudQueueSequencer = getCloudQueueSequencer();
            return CloudQueueDigitalXPUtil.canPerformActionStrategy$default((cloudQueueSequencer == null || (queueCapability = cloudQueueSequencer.queueCapability) == null) ? null : queueCapability.getBlockQueueMutations(), null, 2, null);
        }

        @JvmStatic
        public final boolean hideUpcomingItem() {
            QueueCapability queueCapability;
            CloudQueueSequencer cloudQueueSequencer = getCloudQueueSequencer();
            return CloudQueueDigitalXPUtil.canPerformActionStrategy$default((cloudQueueSequencer == null || (queueCapability = cloudQueueSequencer.queueCapability) == null) ? null : queueCapability.getHideUpcomingEntities(), null, 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void replace(QueueEntityMetadata queueEntityMetadata, Uri contentUri, Uri collectionUri) {
        int iIntValue;
        if (INSTANCE.blockQueueMutation()) {
            Log.warning(TAG, "replaceTrack: queue mutation is blocked");
            return;
        }
        MusicTrack musicTrack = AmazonApplication.getLibraryItemFactory().getTrack(contentUri);
        CTAPrimeCache cTAPrimeCache = new CTAPrimeCache(AmazonApplication.getApplication());
        Queue queue = getQueue();
        if (queue == null) {
            return;
        }
        CloudQueuePlaybackUtil.Companion companion = CloudQueuePlaybackUtil.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(musicTrack, "musicTrack");
        QueueEntityMetadata queueEntityMetadataConvertTrackToSingleQueueEntity = companion.convertTrackToSingleQueueEntity(musicTrack, queueEntityMetadata.getId(), queue.getId(), queue.getCustomerId(), cTAPrimeCache);
        List listMutableListOf = CollectionsKt__CollectionsKt.mutableListOf(queueEntityMetadataConvertTrackToSingleQueueEntity);
        MetricsContext collectionMetricsContext = getCollectionMetricsContext();
        ControlSource controlSource = getControlSource();
        String title = queue.getTitle();
        if (title == null) {
            title = "";
        }
        List<? extends MediaItem> listConvertQueueEntitiesToMediaItems$default = CloudQueuePlaybackUtil.Companion.convertQueueEntitiesToMediaItems$default(companion, listMutableListOf, collectionMetricsContext, controlSource, collectionUri, title, null, false, 0, 224, null);
        if (AmazonApplication.getCapabilities().isNPSLikesSupported()) {
            companion.fetchMediaItemLikeStates(listConvertQueueEntitiesToMediaItems$default, this.likesService);
        }
        Iterator<EntityReference> it = getLocalQueueSequenceSlice().getEntityReferences().iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (Intrinsics.areEqual(it.next().getIdentifier(), queueEntityMetadata.getIdentifier())) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            if (isShuffled()) {
                Integer num = getShuffleMap().getShuffleOrder().get(i);
                Intrinsics.checkNotNullExpressionValue(num, "shuffleMap.shuffleOrder[index]");
                iIntValue = num.intValue();
            } else {
                iIntValue = i;
            }
            replace(iIntValue, listConvertQueueEntitiesToMediaItems$default.get(0));
            updateLocalQueueSequenceSlice(new CloudQueueAction.Replace(i, queueEntityMetadataConvertTrackToSingleQueueEntity));
        }
    }

    /* JADX INFO: renamed from: getCurrentPlayingQueue, reason: from getter */
    public final Queue getQueue() {
        return this.queue;
    }

    /* JADX INFO: renamed from: getCurrentPlayingQueueSequenceSlice, reason: from getter */
    public final QueueSequenceSlice getSlice() {
        return this.slice;
    }

    /* JADX INFO: renamed from: getCurrentPlayingLocalQueueSequenceSlice, reason: from getter */
    public final QueueSequenceSlice getLocalQueueSequenceSlice() {
        return this.localQueueSequenceSlice;
    }

    public final Integer getCurrentPlayingIndex(String currentPlayingAsin) {
        int i;
        Intrinsics.checkNotNullParameter(currentPlayingAsin, "currentPlayingAsin");
        Iterator<EntityReference> it = this.localQueueSequenceSlice.getEntityReferences().iterator();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = -1;
            if (!it.hasNext()) {
                i3 = -1;
                break;
            }
            if (Intrinsics.areEqual(it.next().getIdentifier(), currentPlayingAsin)) {
                break;
            }
            i3++;
        }
        if (i3 == -1) {
            return null;
        }
        Iterator<EntityReference> it2 = this.slice.getEntityReferences().iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            if (Intrinsics.areEqual(it2.next().getEntityReferenceId(), this.localQueueSequenceSlice.getEntityReferences().get(i3).getEntityReferenceId())) {
                i = i2;
                break;
            }
            i2++;
        }
        return Integer.valueOf(i);
    }

    public final List<MediaItem> getQueue() {
        List<MediaItem> queue = ((IndexedSequencerBase.SequencerInfo) this.info).getQueue();
        Intrinsics.checkNotNullExpressionValue(queue, "info.queue");
        return queue;
    }

    public final String getQueueId() {
        return this.queue.getId();
    }

    public final int getSequenceVersion() {
        if (isShuffled()) {
            return this.shuffleSequenceVersion;
        }
        return this.NATURAL_SEQUENCE_VERSION;
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0213  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateLocalQueueSequenceSlice(com.amazon.mp3.cloudqueue.CloudQueueAction r18) {
        /*
            Method dump skipped, instruction units count: 984
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer.updateLocalQueueSequenceSlice(com.amazon.mp3.cloudqueue.CloudQueueAction):void");
    }

    public final void updateSliceToDatastore() {
        ThreadUtils.runInBackground(new Runnable() { // from class: com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.updateSliceToDatastoreHelper();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public final void updateSliceToDatastoreHelper() {
        if (this.isOnlineInteraction) {
            return;
        }
        List<EntityReference> entityReferences = this.slice.getEntityReferences();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(entityReferences, 10)), 16));
        for (EntityReference entityReference : entityReferences) {
            linkedHashMap.put(entityReference.getEntityReferenceId(), entityReference);
        }
        List<EntityReference> entityReferences2 = this.localQueueSequenceSlice.getEntityReferences();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = entityReferences2.iterator();
        while (it.hasNext()) {
            EntityReference entityReference2 = (EntityReference) linkedHashMap.get(((EntityReference) it.next()).getEntityReferenceId());
            if (entityReference2 != null) {
                arrayList.add(entityReference2);
            }
        }
        if (isShuffled()) {
            this.shuffleSequenceVersion++;
        }
        int sequenceVersion = getSequenceVersion();
        final boolean z = !isShuffled() && Intrinsics.areEqual(this.slice.getSequenceName(), QueueSequenceSliceName.SHUFFLE.name());
        QueueSequenceSlice queueSequenceSlice = this.slice;
        final QueueSequenceSlice queueSequenceSliceCopy = queueSequenceSlice.copy((383 & 1) != 0 ? queueSequenceSlice.queueId : null, (383 & 2) != 0 ? queueSequenceSlice.customerId : null, (383 & 4) != 0 ? queueSequenceSlice.id : null, (383 & 8) != 0 ? queueSequenceSlice.sequenceName : (isShuffled() ? QueueSequenceSliceName.SHUFFLE : QueueSequenceSliceName.NATURAL).name(), (383 & 16) != 0 ? queueSequenceSlice.sequenceVersion : sequenceVersion, (383 & 32) != 0 ? queueSequenceSlice.sliceOrdinal : 0, (383 & 64) != 0 ? queueSequenceSlice.isLastSlice : false, (383 & 128) != 0 ? queueSequenceSlice.entityReferences : arrayList, (383 & 256) != 0 ? queueSequenceSlice.createdAt : 0L, (383 & 512) != 0 ? queueSequenceSlice.updatedAt : System.currentTimeMillis());
        CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                CloudQueueSequencer.m733updateSliceToDatastoreHelper$lambda18(queueSequenceSliceCopy, z, (CloudQueueClient) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                CloudQueueSequencer.m734updateSliceToDatastoreHelper$lambda19((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSliceToDatastoreHelper$lambda-18, reason: not valid java name */
    public static final void m733updateSliceToDatastoreHelper$lambda18(QueueSequenceSlice queueSequenceSlice, boolean z, CloudQueueClient cloudQueueClient) {
        String str;
        String id;
        Intrinsics.checkNotNullParameter(queueSequenceSlice, "$queueSequenceSlice");
        if (cloudQueueClient instanceof NoOpCloudQueueClient) {
            return;
        }
        if (Intrinsics.areEqual(queueSequenceSlice.getSequenceName(), QueueSequenceSliceName.NATURAL.name())) {
            if (z) {
                Outcome<QueueSequenceSlice> queueSequenceSliceById = cloudQueueClient.getQueueSequenceSliceById(queueSequenceSlice.getQueueId(), queueSequenceSlice.getSequenceName());
                if (!(queueSequenceSliceById instanceof Outcome.Failure)) {
                    if (!(queueSequenceSliceById instanceof Outcome.Success)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    id = ((QueueSequenceSlice) ((Outcome.Success) queueSequenceSliceById).getValue()).getId();
                }
                str = null;
            } else {
                id = queueSequenceSlice.getId();
            }
            str = id;
        } else {
            str = null;
        }
        if (str == null) {
            cloudQueueClient.createNewQueueSequenceSlice(queueSequenceSlice.copy((383 & 1) != 0 ? queueSequenceSlice.queueId : null, (383 & 2) != 0 ? queueSequenceSlice.customerId : null, (383 & 4) != 0 ? queueSequenceSlice.id : null, (383 & 8) != 0 ? queueSequenceSlice.sequenceName : null, (383 & 16) != 0 ? queueSequenceSlice.sequenceVersion : 0, (383 & 32) != 0 ? queueSequenceSlice.sliceOrdinal : 0, (383 & 64) != 0 ? queueSequenceSlice.isLastSlice : false, (383 & 128) != 0 ? queueSequenceSlice.entityReferences : null, (383 & 256) != 0 ? queueSequenceSlice.createdAt : 0L, (383 & 512) != 0 ? queueSequenceSlice.updatedAt : 0L));
        } else {
            cloudQueueClient.changeQueueSequenceSlice(queueSequenceSlice.copy((383 & 1) != 0 ? queueSequenceSlice.queueId : null, (383 & 2) != 0 ? queueSequenceSlice.customerId : null, (383 & 4) != 0 ? queueSequenceSlice.id : str, (383 & 8) != 0 ? queueSequenceSlice.sequenceName : null, (383 & 16) != 0 ? queueSequenceSlice.sequenceVersion : 0, (383 & 32) != 0 ? queueSequenceSlice.sliceOrdinal : 0, (383 & 64) != 0 ? queueSequenceSlice.isLastSlice : false, (383 & 128) != 0 ? queueSequenceSlice.entityReferences : null, (383 & 256) != 0 ? queueSequenceSlice.createdAt : 0L, (383 & 512) != 0 ? queueSequenceSlice.updatedAt : 0L));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSliceToDatastoreHelper$lambda-19, reason: not valid java name */
    public static final void m734updateSliceToDatastoreHelper$lambda19(Throwable th) {
        Log.error(TAG, "updateSliceToDatastoreHelper failed", th);
    }

    /* JADX INFO: renamed from: isOnlineInteraction, reason: from getter */
    public final boolean getIsOnlineInteraction() {
        return this.isOnlineInteraction;
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    @SuppressLint({"CheckResult"})
    public void fetchUpcomingMediaItems(int numItems) {
        if (this.fetchingUpcoming.get()) {
            return;
        }
        if (getIsOnlineInteraction()) {
            this.maxStationItems = Math.max(this.maxStationItems, getCurrentIndex() + 1);
        }
        this.fetchingUpcoming.set(true);
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                CloudQueueSequencer.m732fetchUpcomingMediaItems$lambda23(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchUpcomingMediaItems$lambda-23, reason: not valid java name */
    public static final void m732fetchUpcomingMediaItems$lambda23(CloudQueueSequencer this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = this$0.copySequencerInfo();
        if (sequencerInfoCopySequencerInfo.getQueue().size() > 0) {
            Context context = AmazonApplication.getContext();
            String customerId = AccountDetailUtil.get(context).getCustomerId();
            String str = this$0.lastOnlineEntityReferenceId;
            if (str == null) {
                Log.warning(TAG, "fetchUpcomingMediaItems queueId = " + this$0.queue.getId() + " lastItemId is null, skip fetchMoreTracks");
                return;
            }
            Intrinsics.checkNotNullExpressionValue(context, "context");
            String id = this$0.queue.getId();
            String str2 = this$0.deviceId;
            String str3 = this$0.deviceType;
            Intrinsics.checkNotNullExpressionValue(customerId, "customerId");
            Outcome<List<GenericQueueEntity>> outcomeFetchMoreTracks = this$0.fetchMoreTracks(context, id, str, str2, str3, customerId);
            ArrayList arrayList = new ArrayList();
            if (outcomeFetchMoreTracks instanceof Outcome.Success) {
                arrayList.addAll((Collection) ((Outcome.Success) outcomeFetchMoreTracks).getValue());
                GenericQueueEntity genericQueueEntity = (GenericQueueEntity) CollectionsKt___CollectionsKt.lastOrNull((List) arrayList);
                if (genericQueueEntity != null) {
                    this$0.lastOnlineEntityReferenceId = genericQueueEntity.getEntityReferenceId();
                }
            } else if (outcomeFetchMoreTracks instanceof Outcome.Failure) {
                String message = ((Outcome.Failure) outcomeFetchMoreTracks).getCause().getMessage();
                Log.error(TAG, "fetchUpcomingMediaItems failed, lastId = " + ((Object) str) + ", error=" + ((Object) message));
                if (message != null && StringsKt__StringsKt.contains$default((CharSequence) message, (CharSequence) GetNextQueueEntityResponseStatusEnum.QUEUE_DETACHED_FROM_DEVICE.toString(), false, 2, (Object) null)) {
                    NowPlayingUtil.clearAndFinishNowPlaying(context, true, PlayStateMutationReason.CLOUD_QUEUE_TERMINATED);
                    return;
                }
                if (message != null && StringsKt__StringsKt.contains$default((CharSequence) message, (CharSequence) GetNextQueueEntityResponseStatusEnum.END_OF_QUEUE.toString(), false, 2, (Object) null)) {
                    sequencerInfoCopySequencerInfo.setMaximumPosition(sequencerInfoCopySequencerInfo.getQueue().size());
                    sequencerInfoCopySequencerInfo.setCanLoadMore(false);
                    this$0.setSequencerInfo(sequencerInfoCopySequencerInfo);
                    return;
                }
                if (message != null && StringsKt__StringsKt.contains$default((CharSequence) message, (CharSequence) GetNextQueueEntityResponseStatusEnum.THROTTLING_LIMIT_REACHED.toString(), false, 2, (Object) null)) {
                    PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI).pause();
                    this$0.fetchingUpcoming.set(false);
                    return;
                }
                if (message != null && StringsKt__StringsKt.contains$default((CharSequence) message, (CharSequence) GetNextQueueEntityResponseStatusEnum.SKIP_LIMIT_REACHED.toString(), false, 2, (Object) null)) {
                    this$0.fetchingUpcoming.set(false);
                    return;
                }
                if (message != null && StringsKt__StringsKt.contains$default((CharSequence) message, (CharSequence) "errorType=500", false, 2, (Object) null)) {
                    return;
                }
                this$0.fetchingUpcoming.set(false);
                return;
            }
            List<? extends MediaItem> listFilterNotNull = CollectionsKt___CollectionsKt.filterNotNull(CloudQueuePlaybackUtil.INSTANCE.convertGenericQueueEntitiesToMediaItems(arrayList, this$0.queue, this$0.getCollectionMetricsContext(), this$0.controlSource, this$0.contentUri));
            if (!listFilterNotNull.isEmpty()) {
                Iterator<T> it = listFilterNotNull.iterator();
                while (it.hasNext()) {
                    this$0.updateAdditionalMediaCollectionIdForMediaItem((MediaItem) it.next());
                }
                this$0.addMediaItemsIntoHarley(listFilterNotNull);
            }
            try {
                CloudQueueSequencer cloudQueueSequencer = INSTANCE.getCloudQueueSequencer();
                ArrayList arrayList2 = new ArrayList();
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(CloudQueuePlaybackUtil.INSTANCE.convertGenericQueueEntityToQueueEntityMetadata((GenericQueueEntity) it2.next(), this$0.queue.getId(), customerId));
                }
                if (cloudQueueSequencer != null) {
                    CloudQueuePlaybackUtil.INSTANCE.addTrackToDataStore(arrayList2, cloudQueueSequencer.getSlice(), false, Integer.valueOf(this$0.getCurrentIndex()));
                }
                AmazonApplication.getApplication().sendBroadcast(new Intent("com.amazon.mp3.playback.video.actionRefreshNowPlayingQueueScreen"));
                if (AmazonApplication.getCapabilities().isNPSLikesSupported()) {
                    CloudQueuePlaybackUtil.INSTANCE.fetchMediaItemLikeStates(listFilterNotNull, this$0.likesService);
                }
            } catch (Exception e) {
                Log.error(TAG, "error when updating amplify models", e);
            }
        }
        this$0.fetchingUpcoming.set(false);
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    public MediaAccessInfo constructMediaAccessInfo() {
        MediaAccessInfo mediaAccessInfoBuild = new MediaAccessInfo.Builder().withAllowedOnline(true).withAvailableOffline(true).withExplicit(true).withCastable(true).withContentAccessType(ContentAccessType.PRIME).build();
        Intrinsics.checkNotNullExpressionValue(mediaAccessInfoBuild, "Builder()\n              …\n                .build()");
        return mediaAccessInfoBuild;
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    public String getQueueTitle() {
        String title = this.queue.getTitle();
        return title == null ? "" : title;
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    public MediaCollectionId.Type getCollectionPrimaryIdType() {
        return MediaCollectionId.Type.MPQS;
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    public MediaCollectionId.Type getCollectionImageUrlIdType() {
        return MediaCollectionId.Type.CLOUD_QUEUE_IMAGE_URL;
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    public Map<String, String> getImageUrlMap() {
        return getMImageUrlMap();
    }

    @Override // com.amazon.mp3.cloudqueue.sequencer.RemoteSourceSequencer
    /* JADX INFO: renamed from: getMetricsContext, reason: from getter */
    public MetricsContext getCollectionMetricsContext() {
        return this.collectionMetricsContext;
    }

    @SuppressLint({"CheckResult"})
    public final void shuffle(boolean shuffle) {
        int iIntValue;
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        if (!sequencerInfoCopySequencerInfo.isCanShuffle()) {
            Log.warning(TAG, "invalid shuffle action");
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (shuffle) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(getSlice().getEntityReferences());
            List<Integer> mutableList = CollectionsKt___CollectionsKt.toMutableList(RangesKt___RangesKt.until(0, arrayList2.size()));
            Collections.shuffle(mutableList);
            List<Integer> shuffleOrder = getShuffleMap().getShuffleOrder();
            Intrinsics.checkNotNullExpressionValue(shuffleOrder, "shuffleMap.shuffleOrder");
            Integer num = (Integer) CollectionsKt___CollectionsKt.getOrNull(shuffleOrder, sequencerInfoCopySequencerInfo.getCurrentPosition());
            Integer numValueOf = null;
            if (num != null && (iIntValue = num.intValue()) != -1) {
                numValueOf = Integer.valueOf(iIntValue);
            }
            int currentPosition = numValueOf == null ? sequencerInfoCopySequencerInfo.getCurrentPosition() : numValueOf.intValue();
            mutableList.remove(Integer.valueOf(currentPosition));
            mutableList.add(0, Integer.valueOf(currentPosition));
            getShuffleMap().clear();
            setShuffled(mutableList, 0);
            List<Integer> shuffleOrder2 = getShuffleMap().getShuffleOrder();
            Intrinsics.checkNotNullExpressionValue(shuffleOrder2, "shuffleMap.shuffleOrder");
            ArrayList<Integer> arrayList3 = new ArrayList();
            for (Object obj : shuffleOrder2) {
                Integer num2 = (Integer) obj;
                if (num2 == null || num2.intValue() != -1) {
                    arrayList3.add(obj);
                }
            }
            for (Integer it : arrayList3) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                arrayList.add(arrayList2.get(it.intValue()));
            }
            Iterator<Integer> it2 = RangesKt___RangesKt.until(0, arrayList2.size()).iterator();
            while (it2.hasNext()) {
                int iNextInt = ((IntIterator) it2).nextInt();
                if (!arrayList3.contains(Integer.valueOf(iNextInt))) {
                    arrayList.add(arrayList2.get(iNextInt));
                }
            }
        } else {
            HashSet hashSet = new HashSet();
            Iterator<T> it3 = this.localQueueSequenceSlice.getEntityReferences().iterator();
            while (it3.hasNext()) {
                hashSet.add(((EntityReference) it3.next()).getEntityReferenceId());
            }
            for (EntityReference entityReference : this.slice.getEntityReferences()) {
                if (hashSet.contains(entityReference.getEntityReferenceId())) {
                    arrayList.add(entityReference);
                }
            }
        }
        updateLocalQueueSequenceSlice(new CloudQueueAction.Shuffle(arrayList));
        updateSliceToDatastore();
    }

    public final void move(int fromIndex, int toIndex) {
        if (INSTANCE.blockQueueMutation()) {
            Log.warning(TAG, "move: queue mutation is blocked");
            return;
        }
        boolean z = false;
        if (isShuffled()) {
            ArrayList arrayList = new ArrayList();
            List<Integer> shuffleOrder = getShuffleMap().getShuffleOrder();
            Intrinsics.checkNotNullExpressionValue(shuffleOrder, "shuffleMap.shuffleOrder");
            arrayList.addAll(shuffleOrder);
            int iIntValue = arrayList.get(fromIndex).intValue();
            int currentPosition = ((IndexedSequencerBase.SequencerInfo) this.info).getCurrentPosition();
            int i = fromIndex;
            if (fromIndex <= toIndex) {
                while (i < toIndex) {
                    int i2 = i + 1;
                    arrayList.set(i, arrayList.get(i2));
                    i = i2;
                }
            } else {
                while (i > toIndex) {
                    arrayList.set(i, arrayList.get(i - 1));
                    i--;
                }
            }
            arrayList.set(toIndex, Integer.valueOf(iIntValue));
            if (fromIndex + 1 <= currentPosition && currentPosition <= toIndex) {
                currentPosition--;
            } else {
                if (toIndex <= currentPosition && currentPosition < fromIndex) {
                    z = true;
                }
                if (z) {
                    currentPosition++;
                }
            }
            setShuffled(arrayList, currentPosition);
        } else {
            IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
            MediaItem mediaItem = sequencerInfoCopySequencerInfo.getQueue().get(fromIndex);
            int currentPosition2 = sequencerInfoCopySequencerInfo.getCurrentPosition();
            int i3 = fromIndex;
            if (fromIndex <= toIndex) {
                while (i3 < toIndex) {
                    int i4 = i3 + 1;
                    sequencerInfoCopySequencerInfo.getQueue().set(i3, sequencerInfoCopySequencerInfo.getQueue().get(i4));
                    i3 = i4;
                }
            } else {
                while (i3 > toIndex) {
                    sequencerInfoCopySequencerInfo.getQueue().set(i3, sequencerInfoCopySequencerInfo.getQueue().get(i3 - 1));
                    i3--;
                }
            }
            sequencerInfoCopySequencerInfo.getQueue().set(toIndex, mediaItem);
            if (fromIndex + 1 <= currentPosition2 && currentPosition2 <= toIndex) {
                sequencerInfoCopySequencerInfo.setCurrentPosition(sequencerInfoCopySequencerInfo.getCurrentPosition() - 1);
            } else {
                if (toIndex <= currentPosition2 && currentPosition2 < fromIndex) {
                    z = true;
                }
                if (z) {
                    sequencerInfoCopySequencerInfo.setCurrentPosition(sequencerInfoCopySequencerInfo.getCurrentPosition() + 1);
                }
            }
            setSequencerInfo(sequencerInfoCopySequencerInfo);
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(this.localQueueSequenceSlice.getEntityReferences());
        updateLocalQueueSequenceSlice(new CloudQueueAction.Move(((EntityReference) arrayList2.get(fromIndex)).getEntityReferenceId(), ((EntityReference) arrayList2.get(toIndex)).getEntityReferenceId()));
        updateSliceToDatastore();
    }

    public final void removeTracks(int deleteIndex, boolean backgroundPlayback) {
        if (INSTANCE.blockQueueMutation()) {
            Log.warning(TAG, "removeTracks: queue mutation is blocked");
            return;
        }
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        if (deleteIndex < 0 || deleteIndex >= sequencerInfoCopySequencerInfo.getQueue().size()) {
            Log.warning(TAG, "removeTracks outOfBound deleteIndex=" + deleteIndex + ", size=" + sequencerInfoCopySequencerInfo.getQueue().size() + ", backgroundPlayback=" + backgroundPlayback);
            return;
        }
        if (isShuffled()) {
            ArrayList arrayList = new ArrayList();
            List<Integer> shuffleOrder = getShuffleMap().getShuffleOrder();
            Intrinsics.checkNotNullExpressionValue(shuffleOrder, "shuffleMap.shuffleOrder");
            arrayList.addAll(shuffleOrder);
            Integer fromItem = getShuffleMap().getShuffleOrder().get(deleteIndex);
            int currentPosition = sequencerInfoCopySequencerInfo.getCurrentPosition();
            if (deleteIndex < currentPosition) {
                currentPosition--;
            }
            arrayList.remove(deleteIndex);
            ArrayList arrayList2 = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(fromItem, "fromItem");
            delete(fromItem.intValue());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                int iIntValue = ((Number) it.next()).intValue();
                if (iIntValue > fromItem.intValue()) {
                    arrayList2.add(Integer.valueOf(iIntValue - 1));
                } else {
                    arrayList2.add(Integer.valueOf(iIntValue));
                }
            }
            getShuffleMap().clear();
            setShuffled(arrayList2, currentPosition);
        } else {
            delete(deleteIndex);
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(this.localQueueSequenceSlice.getEntityReferences());
        EntityReference entityReference = (EntityReference) arrayList3.remove(deleteIndex);
        if (backgroundPlayback) {
            return;
        }
        ArrayList arrayList4 = new ArrayList();
        arrayList4.addAll(this.slice.getEntityReferences());
        int i = 0;
        Iterator it2 = arrayList4.iterator();
        while (true) {
            if (!it2.hasNext()) {
                i = -1;
                break;
            } else if (Intrinsics.areEqual(((EntityReference) it2.next()).getEntityReferenceId(), entityReference.getEntityReferenceId())) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            arrayList4.remove(i);
        }
        this.slice = CloudQueuePlaybackUtil.INSTANCE.copySequenceSlice(this.slice, arrayList4);
        updateLocalQueueSequenceSlice(new CloudQueueAction.Delete(entityReference.getEntityReferenceId()));
        updateSliceToDatastore();
    }

    public final void delete(int deleteIndex) {
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        if (deleteIndex < sequencerInfoCopySequencerInfo.getCurrentPosition()) {
            sequencerInfoCopySequencerInfo.setCurrentPosition(sequencerInfoCopySequencerInfo.getCurrentPosition() - 1);
        }
        Log.info(TAG, Intrinsics.stringPlus("delete track=", sequencerInfoCopySequencerInfo.getQueue().get(deleteIndex).getName()));
        sequencerInfoCopySequencerInfo.getQueue().remove(deleteIndex);
        sequencerInfoCopySequencerInfo.setMaximumPosition(sequencerInfoCopySequencerInfo.getQueue().size());
        setSequencerInfo(sequencerInfoCopySequencerInfo);
    }

    private final void replace(int index, MediaItem newMediaItem) {
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        sequencerInfoCopySequencerInfo.getQueue().set(index, newMediaItem);
        setSequencerInfo(sequencerInfoCopySequencerInfo);
    }

    private final void insertAllToBottom(List<? extends MediaItem> mediaItems) {
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        sequencerInfoCopySequencerInfo.getQueue().addAll(mediaItems);
        sequencerInfoCopySequencerInfo.setMaximumPosition(sequencerInfoCopySequencerInfo.getQueue().size());
        setSequencerInfo(sequencerInfoCopySequencerInfo);
    }

    private final void insertAllToNext(List<? extends MediaItem> mediaItems) {
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        sequencerInfoCopySequencerInfo.getQueue().addAll(sequencerInfoCopySequencerInfo.getQueue().indexOf(getCurrentMediaItem()) + 1, mediaItems);
        sequencerInfoCopySequencerInfo.setMaximumPosition(sequencerInfoCopySequencerInfo.getQueue().size());
        setSequencerInfo(sequencerInfoCopySequencerInfo);
    }

    public final void clearQueue() {
        resetToggleState();
        resetOverlayState(this.queue.getId());
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        sequencerInfoCopySequencerInfo.getQueue().clear();
        setSequencerInfo(sequencerInfoCopySequencerInfo);
        CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable().subscribe(new Consumer() { // from class: com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                CloudQueueSequencer.m730clearQueue$lambda33((CloudQueueClient) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                CloudQueueSequencer.m731clearQueue$lambda34((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: clearQueue$lambda-33, reason: not valid java name */
    public static final void m730clearQueue$lambda33(CloudQueueClient cloudQueueClient) {
        Queue queue;
        CloudQueueSequencer cloudQueueSequencer = INSTANCE.getCloudQueueSequencer();
        if (cloudQueueSequencer == null || (queue = cloudQueueSequencer.getQueue()) == null) {
            return;
        }
        cloudQueueClient.deleteQueueById(queue.getId());
        cloudQueueClient.deleteQueueSequenceSliceById(queue.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: clearQueue$lambda-34, reason: not valid java name */
    public static final void m731clearQueue$lambda34(Throwable th) {
        Log.error(TAG, Intrinsics.stringPlus("Failed to start cloudqueue with exception ", th.getMessage()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<String, String> constructImageUrlMap() {
        if (this.queue.getImages() != null) {
            List<ImageSizeUrlTuple> images = this.queue.getImages();
            Intrinsics.checkNotNull(images);
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(images, 10)), 16));
            for (ImageSizeUrlTuple imageSizeUrlTuple : images) {
                Intrinsics.checkNotNull(imageSizeUrlTuple);
                String size = imageSizeUrlTuple.getSize();
                Intrinsics.checkNotNull(imageSizeUrlTuple);
                linkedHashMap.put(size, imageSizeUrlTuple.getUrl());
            }
            return linkedHashMap;
        }
        return MapsKt__MapsKt.emptyMap();
    }

    private final void addMediaItemsIntoHarley(List<? extends MediaItem> mediaItems) {
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        sequencerInfoCopySequencerInfo.getQueue().addAll(mediaItems);
        setSequencerInfo(sequencerInfoCopySequencerInfo);
    }

    private final void resetToggleState() {
        if (this.hybridQueueStateProvider != null) {
            getHybridQueueStateProvider().reset();
        }
    }

    private final void resetOverlayState(String queueId) {
        if (this.overlayTypeStateProvider != null) {
            getOverlayTypeStateProvider().reset(queueId);
        }
        if (this.overlayLyricsStateProvider != null) {
            getOverlayLyricsStateProvider().reset();
        }
        if (this.stageAutomatedSequencingHandler != null) {
            getStageAutomatedSequencingHandler().resetAutomatedSequenceCurrentOverlay();
        }
    }

    public final Map<String, QueueEntityMetadata> getEntityMap() {
        return this.entityMap;
    }

    public final Map<String, QueueEntityMetadata> getLocalEntityMap() {
        return this.localEntityMap;
    }

    /* JADX INFO: renamed from: getMaxAvailableStationItems, reason: from getter */
    public final int getMaxStationItems() {
        return this.maxStationItems;
    }

    public final MediaCollectionId getAdditionalMediaCollectionId() {
        return this.additionalMediaCollectionId;
    }

    public final void addTrackToPlayback(List<? extends MediaItem> mediaItems, boolean playNext) {
        Intrinsics.checkNotNullParameter(mediaItems, "mediaItems");
        if (INSTANCE.blockQueueMutation()) {
            Log.warning(TAG, "addTrackToPlayback: queue mutation is blocked");
            return;
        }
        if (this.additionalMediaCollectionId != null) {
            Iterator<T> it = mediaItems.iterator();
            while (it.hasNext()) {
                updateAdditionalMediaCollectionIdForMediaItem((MediaItem) it.next());
            }
        }
        if (AmazonApplication.getCapabilities().isNPSLikesSupported()) {
            CloudQueuePlaybackUtil.INSTANCE.fetchMediaItemLikeStates(mediaItems, this.likesService);
        }
        if (playNext) {
            insertAllToNext(mediaItems);
        } else {
            insertAllToBottom(mediaItems);
        }
    }

    private final Outcome<List<GenericQueueEntity>> fetchMoreTracks(Context context, String queueId, String lastId, String deviceId, String deviceType, String customerId) {
        if (!ConnectivityUtil.hasAnyInternetConnection(context)) {
            Log.warning(TAG, "fetchMoreTracks queueId=" + queueId + " skipped due to no internet connection");
            return new Outcome.Failure(new RuntimeException("no internet connection"));
        }
        Log.info(TAG, "fetchMoreTracks queueId=" + queueId + " lastId=" + lastId);
        Outcome<List<GenericQueueEntity>> nextTracks = CloudQueueClientSingleton.INSTANCE.getCloudQueueClientObservable().blockingSingle().getNextTracks(queueId, customerId, deviceId, deviceType, lastId, false, new ParentalControlsInput(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() ^ true));
        if (nextTracks instanceof Outcome.Failure) {
            final FlexEvent flexEventBuild = FlexEvent.builder(CloudQueueFlexMetricsManager.Event.GET_NEXT_ENTITIES.getValue()).withFlexStr1(((Outcome.Failure) nextTracks).getCause().getMessage()).withFlexStr4(getServiceTier()).withFlexNum1(Float.valueOf(0.0f)).withFlexNum2(Float.valueOf(0.0f)).withFlexNum3(Float.valueOf(this.isOnlineInteraction ? 0.0f : 1.0f)).build();
            CQEThrottleUtil.INSTANCE.getNextEntitiesThrottle(context, new Function0<Unit>() { // from class: com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer.fetchMoreTracks.1
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
                    CloudQueueFlexMetricsManager cloudQueueFlexMetricsManager = CloudQueueFlexMetricsManager.INSTANCE;
                    FlexEvent event = flexEventBuild;
                    Intrinsics.checkNotNullExpressionValue(event, "event");
                    cloudQueueFlexMetricsManager.trackEvent(event);
                }
            });
        }
        return nextTracks;
    }

    private final void updateAdditionalMediaCollectionId() {
        MediaCollectionId mediaCollectionId = this.additionalMediaCollectionId;
        if (mediaCollectionId == null) {
            return;
        }
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        sequencerInfoCopySequencerInfo.getMediaCollectionInfo().addId(mediaCollectionId);
        setSequencerInfo(sequencerInfoCopySequencerInfo);
    }

    private final void updateAdditionalMediaCollectionIdForMediaItem(MediaItem mediaItem) {
        MediaCollectionInfo sourceMediaCollectionInfo;
        MediaCollectionId mediaCollectionId = this.additionalMediaCollectionId;
        if (mediaCollectionId == null || mediaItem == null || (sourceMediaCollectionInfo = mediaItem.getSourceMediaCollectionInfo()) == null) {
            return;
        }
        sourceMediaCollectionInfo.addId(mediaCollectionId);
    }

    @Override // com.amazon.music.media.playback.sequencer.IndexedSequencerBase, com.amazon.music.media.playback.sequencer.Sequencer
    public ActionAvailability getSkipNextAvailability() {
        ActionAvailability skipNextAvailabilityFromCapability;
        EntityItem currentMediaItem = getCurrentMediaItem();
        return (!(currentMediaItem instanceof QueueEntityCapabilityQueryable) || (skipNextAvailabilityFromCapability = QueueEntityCapabilityTranslator.INSTANCE.getSkipNextAvailabilityFromCapability((QueueEntityCapabilityQueryable) currentMediaItem)) == null) ? super.getSkipNextAvailability() : skipNextAvailabilityFromCapability;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean shouldResumePlayback(boolean r3, android.net.Uri r4, com.amazon.music.media.playback.ControlSource r5, com.amazon.music.metrics.mts.event.types.PlaybackPageType r6, boolean r7, java.lang.Integer r8, java.lang.Integer r9, com.amazon.digitalmusicxp.inputs.QueueSeedContextInput r10, com.amazon.digitalmusicxp.types.QueueSeed r11) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer.shouldResumePlayback(boolean, android.net.Uri, com.amazon.music.media.playback.ControlSource, com.amazon.music.metrics.mts.event.types.PlaybackPageType, boolean, java.lang.Integer, java.lang.Integer, com.amazon.digitalmusicxp.inputs.QueueSeedContextInput, com.amazon.digitalmusicxp.types.QueueSeed):boolean");
    }

    private final boolean isSFAPlaybackTheSame(QueueSeedContextInput queueSeedContextInput, Uri newContentUri, Uri currentContentUri) {
        if ((queueSeedContextInput == null ? null : queueSeedContextInput.getPlayMode()) == PlayModeEnum.SIMILAR && Intrinsics.areEqual(getMediaCollectionInfo().getType(), MediaCollectionType.PRIME_STATION_SEED) && newContentUri != null && currentContentUri != null) {
            String identifier = getIdentifier(newContentUri);
            String identifier2 = getIdentifier(currentContentUri);
            if (identifier != null && identifier2 != null) {
                return Intrinsics.areEqual(identifier, identifier2);
            }
        }
        return false;
    }

    private final String getIdentifier(Uri contentUri) {
        if (MediaProvider.PrimeBrowseTracksCollection.FirstTrack.isCollectionFirstTrack(contentUri)) {
            return MediaProvider.PrimeBrowseTracksCollection.FirstTrack.getFirstTrackAsin(contentUri);
        }
        if (MediaProvider.Station.isStationFromAnything(contentUri)) {
            return MediaProvider.Station.getStationFromAnythingSeedId(contentUri);
        }
        return null;
    }

    public final boolean isPlaylistDetailPageType(PlaybackPageType pageType) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        return pageType == PlaybackPageType.PLAYLIST_DETAIL || pageType == PlaybackPageType.PRIME_PLAYLIST_DETAIL;
    }

    public final int getSkipsRemaining() {
        return SkipLimitProviderImpl.INSTANCE.getCount();
    }

    public final void addTracksToPlayQueue(List<? extends MediaItem> mediaItems, boolean addNext, List<QueueEntityMetadata> tracks) {
        Intrinsics.checkNotNullParameter(mediaItems, "mediaItems");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        addTrackToPlayback(mediaItems, addNext);
        updateLocalQueueSequenceSlice(new CloudQueueAction.Add(tracks, addNext));
        this.lastOnlineEntityReferenceId = ((EntityReference) CollectionsKt___CollectionsKt.last((List) this.localQueueSequenceSlice.getEntityReferences())).getEntityReferenceId();
        AmazonApplication.getApplication().sendBroadcast(new Intent("com.amazon.mp3.playback.video.actionRefreshNowPlayingQueueScreen"));
    }

    public final void updateSequencerWithAOMLocalQueueInteractionLogic() {
        this.fetchingUpcoming.set(true);
        ((IndexedSequencerBase.SequencerInfo) this.info).setCanLoadMore(false);
    }

    public final boolean enforceSkipLimitOrTooltip() {
        List<QueueSeed> queueSeeds = this.queue.getQueueSeeds();
        QueueSeed queueSeed = queueSeeds == null ? null : (QueueSeed) CollectionsKt___CollectionsKt.firstOrNull((List) queueSeeds);
        return (queueSeed == null ? null : queueSeed.getPlayMode()) != PlayModeEnum.ON_DEMAND || (queueSeed != null ? queueSeed.getQueueSeedType() : null) == QueueSeedTypeEnum.STATION_SEED;
    }
}
