package com.amazon.mp3.fragment.contextmenu.shoveler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.contextmenu.PlaylistTransactionsViewModel;
import com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment;
import com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2;
import com.amazon.mp3.library.activity.AllAccessPlaylistLimitReachedActivity;
import com.amazon.mp3.library.activity.CreateOrRenamePlaylistActivity;
import com.amazon.mp3.library.activity.NumberOfPlaylistsLimitReachedActivity;
import com.amazon.mp3.library.fragment.LibraryBaseFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.service.sync.PlaylistSyncHelper;
import com.amazon.mp3.library.util.AddToPlaylistManager;
import com.amazon.mp3.library.util.AllAccessUserPlaylistLimitTracker;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.prime.browse.GetAlbumTracksAndAddJob;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.DbUtil;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: ContextMenuAddToPlaylistBaseFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0016\u0018\u0000 a2\u00020\u0001:\u0002abB\u0007¢\u0006\u0004\b_\u0010`J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\n\u001a\u00020\u0002H\u0004J\b\u0010\u000b\u001a\u00020\u0002H\u0014J2\u0010\u0014\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0004J\b\u0010\u0015\u001a\u00020\u0002H\u0004J\u0010\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0004J\u0010\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0017H\u0014J\u0010\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0017H\u0004J,\u0010\"\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001f\u001a\u00020\u000e2\b\u0010!\u001a\u0004\u0018\u00010 H\u0014J\u0010\u0010#\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0004J\"\u0010'\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\b\u0010&\u001a\u0004\u0018\u00010%H\u0016R\"\u0010(\u001a\u00020\u00108\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\"\u0010.\u001a\u00020\u00048\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R$\u00105\u001a\u0004\u0018\u0001048\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\"\u0010\u001a\u001a\u00020\u00178\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R$\u0010@\u001a\u0004\u0018\u00010\u00108\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b@\u0010)\u001a\u0004\bA\u0010+\"\u0004\bB\u0010-R$\u0010C\u001a\u0004\u0018\u00010\u00128\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\"\u0010I\u001a\u00020\u00178\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bI\u0010;\u001a\u0004\bJ\u0010=\"\u0004\bK\u0010?R\u0014\u0010L\u001a\u00020\u00108\u0002X\u0082D¢\u0006\u0006\n\u0004\bL\u0010)R\u001b\u0010R\u001a\u00020M8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\bN\u0010O\u001a\u0004\bP\u0010QR\u001b\u0010W\u001a\u00020S8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\bT\u0010O\u001a\u0004\bU\u0010VR$\u0010Y\u001a\u0004\u0018\u00010X8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bY\u0010Z\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^¨\u0006c"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuAddToPlaylistBaseFragment;", "Lcom/amazon/mp3/library/fragment/LibraryBaseFragment;", "", "updatePlaylistUri", "Landroid/net/Uri;", "playlistUri", "addAlbumTrack", "Landroid/content/Context;", "context", "onAttach", "setupArguments", "observeChangesToPlaylists", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "", "playlistIndex", "", "playlistId", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "overflowPageType", "sendUiClickMetrics", "sendUiContentViewMetrics", "navigateToPlaylist", "", Splash.PARAMS_SESSION_ID, "onSessionConnected", TetheredMessageKey.requestId, "setLastRequestId", "jobId", "Lcom/amazon/mp3/service/job/Job;", "job", "resultCode", "Landroid/os/Bundle;", "resultBundle", "onJobFinished", "addToUserPlaylist", "requestCode", "Landroid/content/Intent;", "data", "onActivityResult", "selectedPlaylistName", "Ljava/lang/String;", "getSelectedPlaylistName", "()Ljava/lang/String;", "setSelectedPlaylistName", "(Ljava/lang/String;)V", "selectedPlaylist", "Landroid/net/Uri;", "getSelectedPlaylist", "()Landroid/net/Uri;", "setSelectedPlaylist", "(Landroid/net/Uri;)V", "Lcom/amazon/mp3/activity/NavigationActivity;", "callbackActivity", "Lcom/amazon/mp3/activity/NavigationActivity;", "getCallbackActivity", "()Lcom/amazon/mp3/activity/NavigationActivity;", "setCallbackActivity", "(Lcom/amazon/mp3/activity/NavigationActivity;)V", "J", "getRequestId", "()J", "setRequestId", "(J)V", "mediaSourceId", "getMediaSourceId", "setMediaSourceId", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "setPageType", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "addTracksJobId", "getAddTracksJobId", "setAddTracksJobId", "SHARED_PREF_NAME", "Lcom/amazon/mp3/fragment/contextmenu/PlaylistTransactionsViewModel;", "playlistsViewModel$delegate", "Lkotlin/Lazy;", "getPlaylistsViewModel", "()Lcom/amazon/mp3/fragment/contextmenu/PlaylistTransactionsViewModel;", "playlistsViewModel", "Lcom/amazon/mp3/library/util/AddToPlaylistManager$AddToPlaylistListener;", "addToPlaylistListener$delegate", "getAddToPlaylistListener", "()Lcom/amazon/mp3/library/util/AddToPlaylistManager$AddToPlaylistListener;", "addToPlaylistListener", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuAddToPlaylistBaseFragment$ShovelerCallbacksListener;", "callbacksListener", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuAddToPlaylistBaseFragment$ShovelerCallbacksListener;", "getCallbacksListener", "()Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuAddToPlaylistBaseFragment$ShovelerCallbacksListener;", "setCallbacksListener", "(Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuAddToPlaylistBaseFragment$ShovelerCallbacksListener;)V", "<init>", "()V", "Companion", "ShovelerCallbacksListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class ContextMenuAddToPlaylistBaseFragment extends LibraryBaseFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static long lastRequestId = -1;
    private static final Logger logger = LoggerFactory.getLogger(ContextMenuAddToPlaylistBaseFragment.class.getName());
    private static long nextRequestId;

    /* JADX INFO: renamed from: addToPlaylistListener$delegate, reason: from kotlin metadata */
    private final Lazy addToPlaylistListener;
    private NavigationActivity callbackActivity;
    private ShovelerCallbacksListener callbacksListener;
    private String mediaSourceId;
    private PageType pageType;

    /* JADX INFO: renamed from: playlistsViewModel$delegate, reason: from kotlin metadata */
    private final Lazy playlistsViewModel;
    protected Uri selectedPlaylist;
    protected String selectedPlaylistName;
    private long requestId = -1;
    private long addTracksJobId = -1;
    private final String SHARED_PREF_NAME = "com.amazon.mp3.CreatePlaylistDialogFragment";

    public ContextMenuAddToPlaylistBaseFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        this.playlistsViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(PlaylistTransactionsViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, null);
        this.addToPlaylistListener = LazyKt__LazyJVMKt.lazy(new Function0<ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2.AnonymousClass1>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2
            {
                super(0);
            }

            /* JADX INFO: renamed from: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2$1, reason: invalid class name */
            /* JADX INFO: compiled from: ContextMenuAddToPlaylistBaseFragment.kt */
            @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, d2 = {"com/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2$1", "Lcom/amazon/mp3/library/util/AddToPlaylistManager$AddToPlaylistListener;", "onError", "", "errorCode", "Lcom/amazon/mp3/library/util/AddToPlaylistManager$ErrorReason;", "onSuccess", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
            public static final class AnonymousClass1 implements AddToPlaylistManager.AddToPlaylistListener {
                final /* synthetic */ ContextMenuAddToPlaylistBaseFragment this$0;

                /* JADX INFO: renamed from: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2$1$WhenMappings */
                /* JADX INFO: compiled from: ContextMenuAddToPlaylistBaseFragment.kt */
                @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[AddToPlaylistManager.ErrorReason.values().length];
                        iArr[AddToPlaylistManager.ErrorReason.ITEM_ALREADY_IN_PLAYLIST.ordinal()] = 1;
                        iArr[AddToPlaylistManager.ErrorReason.MAX_PLAYLIST_SIZE_REACHED.ordinal()] = 2;
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                AnonymousClass1(ContextMenuAddToPlaylistBaseFragment contextMenuAddToPlaylistBaseFragment) {
                    this.this$0 = contextMenuAddToPlaylistBaseFragment;
                }

                @Override // com.amazon.mp3.library.util.AddToPlaylistManager.AddToPlaylistListener
                public void onSuccess() {
                    ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener callbacksListener = this.this$0.getCallbacksListener();
                    if (callbacksListener != null) {
                        String selectedPlaylistName = this.this$0.getSelectedPlaylistName();
                        final ContextMenuAddToPlaylistBaseFragment contextMenuAddToPlaylistBaseFragment = this.this$0;
                        callbacksListener.onAddToPlaylistSuccess(selectedPlaylistName, 
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0016: INVOKE 
                              (r0v1 'callbacksListener' com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$ShovelerCallbacksListener)
                              (r1v4 'selectedPlaylistName' java.lang.String)
                              (wrap:kotlin.jvm.functions.Function0<kotlin.Unit>:0x0013: CONSTRUCTOR 
                              (r3v0 'contextMenuAddToPlaylistBaseFragment' com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment A[DONT_INLINE])
                             A[MD:(com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment):void (m), WRAPPED] call: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2$1$onSuccess$1.<init>(com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment):void type: CONSTRUCTOR)
                             INTERFACE call: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener.onAddToPlaylistSuccess(java.lang.String, kotlin.jvm.functions.Function0):void A[MD:(java.lang.String, kotlin.jvm.functions.Function0<kotlin.Unit>):void (m)] in method: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2.1.onSuccess():void, file: classes3.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2$1$onSuccess$1, state: NOT_LOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                            	... 21 more
                            */
                        /*
                            this = this;
                            com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment r0 = r4.this$0
                            com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$ShovelerCallbacksListener r0 = r0.getCallbacksListener()
                            if (r0 != 0) goto L9
                            goto L19
                        L9:
                            com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment r1 = r4.this$0
                            java.lang.String r1 = r1.getSelectedPlaylistName()
                            com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2$1$onSuccess$1 r2 = new com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2$1$onSuccess$1
                            com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment r3 = r4.this$0
                            r2.<init>(r3)
                            r0.onAddToPlaylistSuccess(r1, r2)
                        L19:
                            com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment r0 = r4.this$0
                            android.net.Uri r0 = r0.getSelectedPlaylist()
                            if (r0 == 0) goto L4b
                            com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment r0 = r4.this$0
                            android.net.Uri r0 = r0.getSelectedPlaylist()
                            java.lang.String r0 = com.amazon.mp3.library.provider.MediaProvider.getSource(r0)
                            java.lang.String r1 = "cirrus-local"
                            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
                            if (r0 == 0) goto L4b
                            com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment r0 = r4.this$0
                            android.net.Uri r0 = r0.getSelectedPlaylist()
                            boolean r0 = com.amazon.mp3.library.provider.MediaProvider.UdoPlaylists.isUdoPlaylist(r0)
                            if (r0 == 0) goto L4b
                            com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment r0 = r4.this$0
                            com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2$1$$ExternalSyntheticLambda0 r1 = new com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2$1$$ExternalSyntheticLambda0
                            r1.<init>(r0)
                            r2 = 2000(0x7d0, double:9.88E-321)
                            com.amazon.music.media.playback.util.ThreadUtils.postInBackgroundDelayed(r1, r2)
                        L4b:
                            com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment r0 = r4.this$0
                            long r1 = r0.getRequestId()
                            r0.setLastRequestId(r1)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$addToPlaylistListener$2.AnonymousClass1.onSuccess():void");
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    /* JADX INFO: renamed from: onSuccess$lambda-0, reason: not valid java name */
                    public static final void m1149onSuccess$lambda0(ContextMenuAddToPlaylistBaseFragment this$0) {
                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                        try {
                            PlaylistSyncHelper.syncNewDevicePlaylistsToCloud(AmazonApplication.getApplication().getApplicationContext());
                            Logger logger = ContextMenuAddToPlaylistBaseFragment.logger;
                            String selectedPlaylistName = this$0.getSelectedPlaylistName();
                            Uri selectedPlaylist = this$0.getSelectedPlaylist();
                            logger.debug("Sync offline playlist {} to cloud, uri = {}", selectedPlaylistName, selectedPlaylist == null ? null : selectedPlaylist.toString());
                        } catch (Exception e) {
                            ContextMenuAddToPlaylistBaseFragment.logger.error(e.getMessage());
                        }
                    }

                    @Override // com.amazon.mp3.library.util.AddToPlaylistManager.AddToPlaylistListener
                    public void onError(AddToPlaylistManager.ErrorReason errorCode) {
                        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
                        int i = WhenMappings.$EnumSwitchMapping$0[errorCode.ordinal()];
                        if (i == 1) {
                            Context context = this.this$0.getContext();
                            Context applicationContext = context == null ? null : context.getApplicationContext();
                            if (applicationContext == null) {
                                applicationContext = AmazonApplication.getApplication().getApplicationContext();
                            }
                            String quantityString = applicationContext.getResources().getQuantityString(R.plurals.dmusic_playlist_track_already_in_playlist, 1);
                            Intrinsics.checkNotNullExpressionValue(quantityString, "context.resources\n      …k_already_in_playlist, 1)");
                            BauhausToastUtils.showDefaultToast(applicationContext, quantityString, 0);
                            ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener callbacksListener = this.this$0.getCallbacksListener();
                            if (callbacksListener != null) {
                                callbacksListener.onAddToPlaylistError(quantityString);
                            }
                        } else if (i == 2) {
                            ContextMenuAddToPlaylistBaseFragment contextMenuAddToPlaylistBaseFragment = this.this$0;
                            contextMenuAddToPlaylistBaseFragment.startActivity(DialogActivity.getStartIntent(contextMenuAddToPlaylistBaseFragment.getContext(), R.string.dmusic_playlist_edit_max_size_title, R.string.dmusic_playlist_edit_max_size_desc));
                            ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener callbacksListener2 = this.this$0.getCallbacksListener();
                            if (callbacksListener2 != null) {
                                callbacksListener2.onAddToPlaylistError(this.this$0.getString(R.string.dmusic_playlist_edit_max_size_desc));
                            }
                        }
                        ContextMenuAddToPlaylistBaseFragment contextMenuAddToPlaylistBaseFragment2 = this.this$0;
                        contextMenuAddToPlaylistBaseFragment2.setLastRequestId(contextMenuAddToPlaylistBaseFragment2.getRequestId());
                    }
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final AnonymousClass1 invoke() {
                    return new AnonymousClass1(this.this$0);
                }
            });
        }

        protected final String getSelectedPlaylistName() {
            String str = this.selectedPlaylistName;
            if (str != null) {
                return str;
            }
            Intrinsics.throwUninitializedPropertyAccessException("selectedPlaylistName");
            return null;
        }

        protected final void setSelectedPlaylistName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.selectedPlaylistName = str;
        }

        protected final Uri getSelectedPlaylist() {
            Uri uri = this.selectedPlaylist;
            if (uri != null) {
                return uri;
            }
            Intrinsics.throwUninitializedPropertyAccessException("selectedPlaylist");
            return null;
        }

        protected final void setSelectedPlaylist(Uri uri) {
            Intrinsics.checkNotNullParameter(uri, "<set-?>");
            this.selectedPlaylist = uri;
        }

        protected final long getRequestId() {
            return this.requestId;
        }

        protected final void setRequestId(long j) {
            this.requestId = j;
        }

        protected final String getMediaSourceId() {
            return this.mediaSourceId;
        }

        protected final void setMediaSourceId(String str) {
            this.mediaSourceId = str;
        }

        protected final PageType getPageType() {
            return this.pageType;
        }

        protected final void setPageType(PageType pageType) {
            this.pageType = pageType;
        }

        protected final PlaylistTransactionsViewModel getPlaylistsViewModel() {
            return (PlaylistTransactionsViewModel) this.playlistsViewModel.getValue();
        }

        protected final AddToPlaylistManager.AddToPlaylistListener getAddToPlaylistListener() {
            return (AddToPlaylistManager.AddToPlaylistListener) this.addToPlaylistListener.getValue();
        }

        public final ShovelerCallbacksListener getCallbacksListener() {
            return this.callbacksListener;
        }

        public final void setCallbacksListener(ShovelerCallbacksListener shovelerCallbacksListener) {
            this.callbacksListener = shovelerCallbacksListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void updatePlaylistUri() {
            if (getSelectedPlaylist() == null || !Intrinsics.areEqual("cirrus-local", MediaProvider.getSource(getSelectedPlaylist()))) {
                return;
            }
            long playlistId = MediaProvider.Playlists.getPlaylistId(getSelectedPlaylist());
            Cursor cursorQuery = null;
            try {
                try {
                    cursorQuery = CirrusDatabase.getReadOnlyDatabase(getContext()).query("Playlist", new String[]{"_id"}, "(_id = ? OR name = ?)  AND version = ?  AND download_state = ?  AND source = ? ", new String[]{String.valueOf(playlistId), getSelectedPlaylistName(), "2:0", Integer.toString(0), Integer.toString(0)}, null, null, "dateLastModified DESC");
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                if (cursorQuery != null && cursorQuery.getCount() != 0) {
                    if (cursorQuery.moveToNext()) {
                        long j = cursorQuery.getLong(cursorQuery.getColumnIndex("_id"));
                        Uri contentUri = MediaProvider.UdoPlaylists.getContentUri("cirrus-local", j);
                        Intrinsics.checkNotNullExpressionValue(contentUri, "getContentUri(CirrusMedi…rce.ID_LOCAL, playlistId)");
                        setSelectedPlaylist(contentUri);
                        logger.debug("Replacing device playlist id {} with {}", getSelectedPlaylist(), Long.valueOf(j));
                    }
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }

        @Override // androidx.fragment.app.Fragment
        public void onAttach(Context context) throws Throwable {
            Intrinsics.checkNotNullParameter(context, "context");
            super.onAttach(context);
            if (getActivity() instanceof NavigationActivity) {
                FragmentActivity activity = getActivity();
                if (activity == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.NavigationActivity");
                }
                this.callbackActivity = (NavigationActivity) activity;
                return;
            }
            throw new Throwable("Activity must extend NavigationActivity");
        }

        protected final void setupArguments() {
            Bundle arguments = getArguments();
            if (arguments == null) {
                return;
            }
            PlaylistTransactionsViewModel playlistsViewModel = getPlaylistsViewModel();
            playlistsViewModel.setAlbumCollection(arguments.getBoolean("EXTRA_PRIME_COLLECTION", false));
            playlistsViewModel.setTracksUri((Uri) arguments.getParcelable("EXTRA_TRACKS_CONTENT_URI"));
            playlistsViewModel.setAlbumAsin(arguments.getString("com.amazon.mp3.library.activity.EXTRA_PRIME_ALBUM_ASIN"));
            setMediaSourceId(MediaProvider.getSource(getPlaylistsViewModel().getTracksUri()));
            setPageType((PageType) arguments.getSerializable("EXTRA_PAGE_TYPE"));
            setRequestId(arguments.getLong("com.amazon.mp3.library.activity.EXTRA_UNIQUE_ADD_ID", -1L));
        }

        protected void observeChangesToPlaylists() {
            LiveData<Boolean> canCreatePlaylist = getPlaylistsViewModel().getCanCreatePlaylist();
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
            canCreatePlaylist.observe(viewLifecycleOwner, new Observer() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$observeChangesToPlaylists$$inlined$observe$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // androidx.lifecycle.Observer
                public final void onChanged(T t) {
                    Boolean bool = (Boolean) t;
                    if (bool == null) {
                        return;
                    }
                    if (bool.booleanValue()) {
                        ContextMenuAddToPlaylistBaseFragment contextMenuAddToPlaylistBaseFragment = this.this$0;
                        contextMenuAddToPlaylistBaseFragment.startActivityForResult(CreateOrRenamePlaylistActivity.getStartIntentForCreate(contextMenuAddToPlaylistBaseFragment.getContext(), PageType.DETAIL_PLAYLIST), CreateOrRenamePlaylistActivity.REQUEST_CODE);
                    } else {
                        this.this$0.startActivity(NumberOfPlaylistsLimitReachedActivity.getStartIntent());
                    }
                }
            });
            LiveData<Uri> liveDataIsAlbumTrack = getPlaylistsViewModel().isAlbumTrack();
            LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "viewLifecycleOwner");
            liveDataIsAlbumTrack.observe(viewLifecycleOwner2, new Observer() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$observeChangesToPlaylists$$inlined$observe$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // androidx.lifecycle.Observer
                public final void onChanged(T t) {
                    Uri uri = (Uri) t;
                    if (uri == null) {
                        return;
                    }
                    this.this$0.addAlbumTrack(uri);
                }
            });
            LiveData<Uri> newPlaylistUri = getPlaylistsViewModel().getNewPlaylistUri();
            LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner3, "viewLifecycleOwner");
            newPlaylistUri.observe(viewLifecycleOwner3, new Observer() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment$observeChangesToPlaylists$$inlined$observe$3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // androidx.lifecycle.Observer
                public final void onChanged(T t) {
                    SharedPreferences sharedPreferences;
                    Uri uri = (Uri) t;
                    if (uri == null) {
                        return;
                    }
                    this.this$0.setSelectedPlaylist(uri);
                    Uri uriExtractPlaylistUri = MediaProvider.Playlists.extractPlaylistUri(this.this$0.getSelectedPlaylist());
                    FragmentActivity activity = this.this$0.getActivity();
                    boolean z = false;
                    if (activity != null && (sharedPreferences = activity.getSharedPreferences(this.this$0.SHARED_PREF_NAME, 0)) != null) {
                        z = sharedPreferences.getBoolean(this.this$0.getString(R.string.playlist_creation_public_toggle_last_state), false);
                    }
                    if (z) {
                        PlaylistUtil.setPlaylistVisibility(this.this$0.getActivity(), uriExtractPlaylistUri, MediaProvider.Playlists.VisibilityState.PUBLIC);
                    }
                    UiClickEvent.Builder builderWithEventTime = UiClickEvent.builder(z ? ActionType.CREATE_PUBLIC_PLAYLIST : ActionType.CREATE_PRIVATE_PLAYLIST).withInteractionType(InteractionType.TAP).withPageType(this.this$0.getPageType()).withEntityId(MediaProvider.Playlists.getPlaylistLuid(uriExtractPlaylistUri)).withEntityIdType(EntityIdType.PLAYLIST_ID).withEventTime(System.currentTimeMillis());
                    if (this.this$0.getPageType() != null) {
                        builderWithEventTime = builderWithEventTime.withPageType(this.this$0.getPageType());
                    }
                    MetricsHolder.getManager().handleEvent(builderWithEventTime.build());
                }
            });
        }

        public static /* synthetic */ void sendUiClickMetrics$default(ContextMenuAddToPlaylistBaseFragment contextMenuAddToPlaylistBaseFragment, ActionType actionType, int i, String str, PageType pageType, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendUiClickMetrics");
            }
            if ((i2 & 2) != 0) {
                i = 0;
            }
            if ((i2 & 4) != 0) {
                str = null;
            }
            if ((i2 & 8) != 0) {
                pageType = null;
            }
            contextMenuAddToPlaylistBaseFragment.sendUiClickMetrics(actionType, i, str, pageType);
        }

        protected final void sendUiClickMetrics(ActionType actionType, int playlistIndex, String playlistId, PageType overflowPageType) {
            PageType pageType;
            Intrinsics.checkNotNullParameter(actionType, "actionType");
            UiClickEvent.Builder builderWithEventTime = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis());
            String str = this instanceof ContextMenuAddToPlaylistShovelerFragment ? "shovelerPlaylistMenu" : "seeAllPlaylistMenu";
            ArrayList arrayList = new ArrayList();
            HashMap map = new HashMap();
            map.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
            map.put("contentName", str);
            if (playlistIndex > 0) {
                map.put("entityPos", String.valueOf(playlistIndex));
                if (playlistId != null) {
                    map.put(ContextMappingConstants.ENTITY_ID, playlistId);
                }
            }
            arrayList.add(map);
            builderWithEventTime.withContentInfo(arrayList);
            if ((overflowPageType == null ? null : builderWithEventTime.withPageType(overflowPageType)) == null && (pageType = getPageType()) != null) {
                builderWithEventTime.withPageType(pageType);
            }
            MetricsHolder.getManager().handleEvent(builderWithEventTime.build());
        }

        protected final void sendUiContentViewMetrics() {
            String str = this instanceof ContextMenuAddToPlaylistShovelerFragment ? "shovelerPlaylistMenu" : "seeAllPlaylistMenu";
            ArrayList arrayList = new ArrayList();
            HashMap map = new HashMap();
            map.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
            map.put("contentName", str);
            arrayList.add(map);
            UiContentViewEvent.Builder builderWithContentInfo = UiContentViewEvent.builder().withEventTime(System.currentTimeMillis()).withContentInfo(arrayList);
            PageType pageType = this.pageType;
            if (pageType != null) {
                builderWithContentInfo.withPageType(pageType.getMetricsValue());
            }
            MetricsHolder.getManager().handleEvent(builderWithContentInfo.build());
        }

        protected final void navigateToPlaylist(Uri playlistUri) {
            Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
            NavigationActivity navigationActivity = this.callbackActivity;
            if (navigationActivity != null) {
                ShovelerExtensionFunctionsKt.navigateToPlaylist(navigationActivity, playlistUri);
            }
            this.callbackActivity = null;
        }

        @Override // com.amazon.mp3.service.job.JobSessionFragment
        protected void onSessionConnected(long sessionId) {
            ShovelerCallbacksListener shovelerCallbacksListener;
            super.onSessionConnected(sessionId);
            if (this.requestId != lastRequestId || (shovelerCallbacksListener = this.callbacksListener) == null) {
                return;
            }
            ShovelerCallbacksListener.DefaultImpls.onAddToPlaylistError$default(shovelerCallbacksListener, null, 1, null);
        }

        protected final void setLastRequestId(long requestId) {
            lastRequestId = requestId;
        }

        @Override // com.amazon.mp3.service.job.JobSessionFragment
        protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
            long j = this.addTracksJobId;
            if (jobId == j && resultCode == 1) {
                this.addTracksJobId = -1L;
                Uri selectedPlaylist = getSelectedPlaylist();
                if (selectedPlaylist != null) {
                    getPlaylistsViewModel().addTracksToPlaylist(selectedPlaylist);
                }
            } else if (jobId != j && resultCode == 1) {
                ShovelerCallbacksListener shovelerCallbacksListener = this.callbacksListener;
                if (shovelerCallbacksListener != null) {
                    shovelerCallbacksListener.onAddToPlaylistSuccess(getSelectedPlaylistName(), new Function0<Unit>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment.onJobFinished.2
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
                            ContextMenuAddToPlaylistBaseFragment.sendUiClickMetrics$default(ContextMenuAddToPlaylistBaseFragment.this, ActionType.ADD_TO_PLAYLIST_CONFIRMATION_TOAST, 0, null, null, 14, null);
                            ContextMenuAddToPlaylistBaseFragment contextMenuAddToPlaylistBaseFragment = ContextMenuAddToPlaylistBaseFragment.this;
                            contextMenuAddToPlaylistBaseFragment.navigateToPlaylist(contextMenuAddToPlaylistBaseFragment.getSelectedPlaylist());
                        }
                    });
                }
                setLastRequestId(this.requestId);
            }
            super.onJobFinished(jobId, job, resultCode, resultBundle);
        }

        protected final void addToUserPlaylist(Uri playlistUri) {
            Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
            if (!(!Intrinsics.areEqual("cirrus", MediaProvider.getSource(playlistUri)) || getPlaylistsViewModel().canEditOnlinePlaylist(getContext(), playlistUri))) {
                startActivity(NetworkErrorDialogActivity.getStartIntent(getContext()));
                return;
            }
            AllAccessUserPlaylistLimitTracker.Response responseCanAdd = AllAccessUserPlaylistLimitTracker.canAdd(playlistUri, null);
            if (responseCanAdd != AllAccessUserPlaylistLimitTracker.Response.SUCCESS) {
                startActivity(AllAccessPlaylistLimitReachedActivity.getStartIntent(responseCanAdd.toString()));
            } else {
                setSelectedPlaylist(playlistUri);
                getPlaylistsViewModel().addToUserPlaylist(playlistUri);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void addAlbumTrack(Uri playlistUri) {
            this.addTracksJobId = addJob(new GetAlbumTracksAndAddJob(getContext(), getPlaylistsViewModel().getAlbumAsin()));
            setSelectedPlaylist(playlistUri);
        }

        @Override // androidx.fragment.app.Fragment
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == CreateOrRenamePlaylistActivity.REQUEST_CODE && resultCode == -1 && data != null) {
                String stringExtra = data.getStringExtra("com.amazon.mp3.library.activity.playlist.EXTRA_PLAYLIST_NAME");
                if (stringExtra == null) {
                    stringExtra = "";
                }
                setSelectedPlaylistName(stringExtra);
                getPlaylistsViewModel().createPlaylist(getSelectedPlaylistName(), getMediaSourceId(), true);
            }
        }

        /* JADX INFO: compiled from: ContextMenuAddToPlaylistBaseFragment.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0014\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH&J\"\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\t2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rH&¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuAddToPlaylistBaseFragment$ShovelerCallbacksListener;", "", "navigateToSeeAllPage", "", "bundle", "Landroid/os/Bundle;", "navigateToShovelerMenu", "onAddToPlaylistError", "error", "", "onAddToPlaylistSuccess", "selectedPlaylistName", "onToastClickListener", "Lkotlin/Function0;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public interface ShovelerCallbacksListener {
            void navigateToSeeAllPage(Bundle bundle);

            void navigateToShovelerMenu(Bundle bundle);

            void onAddToPlaylistError(String error);

            void onAddToPlaylistSuccess(String selectedPlaylistName, Function0<Unit> onToastClickListener);

            /* JADX INFO: compiled from: ContextMenuAddToPlaylistBaseFragment.kt */
            @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
            public static final class DefaultImpls {
                public static /* synthetic */ void onAddToPlaylistError$default(ShovelerCallbacksListener shovelerCallbacksListener, String str, int i, Object obj) {
                    if (obj != null) {
                        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onAddToPlaylistError");
                    }
                    if ((i & 1) != 0) {
                        str = null;
                    }
                    shovelerCallbacksListener.onAddToPlaylistError(str);
                }
            }
        }

        /* JADX INFO: compiled from: ContextMenuAddToPlaylistBaseFragment.kt */
        @Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b$\u0010%J>\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\n\u001a\u00020\u0004R\"\u0010\u000e\u001a\u00020\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\r8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0014\u0010\u000fR\u0014\u0010\u0015\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0019\u0010\u0016R\u0014\u0010\u001a\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001a\u0010\u0016R\u0014\u0010\u001b\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001b\u0010\u0016R\u0014\u0010\u001c\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001c\u0010\u0016R\u0014\u0010\u001d\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001d\u0010\u0016R\u0014\u0010\u001e\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001e\u0010\u0016R\u0014\u0010\u001f\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001f\u0010\u0016R\u001c\u0010\"\u001a\n !*\u0004\u0018\u00010 0 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006&"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuAddToPlaylistBaseFragment$Companion;", "", "Landroid/net/Uri;", "tracksUri", "", "albumCollection", "", "albumAsin", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "isSideloaded", "Landroid/os/Bundle;", "getArgumentsBundle", "", "nextRequestId", "J", "getNextRequestId", "()J", "setNextRequestId", "(J)V", "DEFAULT_REQUEST_ID", "EXTRA_ALBUM_ASIN", "Ljava/lang/String;", "EXTRA_ALBUM_COLLECTION", "EXTRA_BACKGROUND", "EXTRA_GO_TO_PLAYLIST", "EXTRA_IS_SIDE_LOADED", "EXTRA_PAGE_TYPE", "EXTRA_TRACKS_CONTENT_URI", "EXTRA_UNIQUE_ADD_ID", "SEE_ALL_PLAYLIST_MENU", "SHOVELER_PLAYLIST_MENU", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final long getNextRequestId() {
                return ContextMenuAddToPlaylistBaseFragment.nextRequestId;
            }

            public final void setNextRequestId(long j) {
                ContextMenuAddToPlaylistBaseFragment.nextRequestId = j;
            }

            public static /* synthetic */ Bundle getArgumentsBundle$default(Companion companion, Uri uri, boolean z, String str, PageType pageType, boolean z2, int i, Object obj) {
                if ((i & 1) != 0) {
                    uri = null;
                }
                if ((i & 2) != 0) {
                    z = false;
                }
                if ((i & 4) != 0) {
                    str = null;
                }
                if ((i & 8) != 0) {
                    pageType = null;
                }
                if ((i & 16) != 0) {
                    z2 = false;
                }
                return companion.getArgumentsBundle(uri, z, str, pageType, z2);
            }

            public final Bundle getArgumentsBundle(Uri tracksUri, boolean albumCollection, String albumAsin, PageType pageType, boolean isSideloaded) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("EXTRA_TRACKS_CONTENT_URI", tracksUri);
                Companion companion = ContextMenuAddToPlaylistBaseFragment.INSTANCE;
                long nextRequestId = companion.getNextRequestId();
                companion.setNextRequestId(1 + nextRequestId);
                bundle.putLong("com.amazon.mp3.library.activity.EXTRA_UNIQUE_ADD_ID", nextRequestId);
                bundle.putBoolean("EXTRA_PRIME_COLLECTION", albumCollection);
                bundle.putString("com.amazon.mp3.library.activity.EXTRA_PRIME_ALBUM_ASIN", albumAsin);
                bundle.putSerializable("EXTRA_PAGE_TYPE", pageType);
                bundle.putBoolean("EXTRA_IS_SIDE_LOADED", isSideloaded);
                return bundle;
            }
        }
    }
