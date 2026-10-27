package com.amazon.mp3.platform.providers.library;

import android.content.Context;
import com.amazon.mp3.service.job.JobQueue;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.music.platform.data.Callback;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemSingle;
import com.amazon.music.platform.data.library.LibraryUseCase;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseInteractor.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000 \"*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0001\"B\u0019\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b \u0010!J\b\u0010\u0005\u001a\u00020\u0004H\u0004J \u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0004J&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0007\u001a\u00020\u0006H\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0012\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0017\u001a\u00020\u00168\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010\u001e\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"Lcom/amazon/mp3/platform/providers/library/BaseInteractor;", "Lcom/amazon/music/platform/data/entity/EntityItem;", "T", "Lcom/amazon/music/platform/data/library/LibraryUseCase;", "", "notifyLibraryChanged", "", "resultCode", "", "successMsg", "failMsg", "announceDeleteJobFinished", "Lkotlin/Result;", "", "getJobResult-IoAF18A", "(I)Ljava/lang/Object;", "getJobResult", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/service/job/JobQueue;", "jobQueue", "Lcom/amazon/mp3/service/job/JobQueue;", "getJobQueue", "()Lcom/amazon/mp3/service/job/JobQueue;", "", "Lcom/amazon/music/platform/data/Callback;", "listeners", "Ljava/util/Set;", "autoRemoveListeners", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/service/job/JobQueue;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class BaseInteractor<T extends EntityItem> implements LibraryUseCase<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = BaseInteractor.class.getSimpleName();
    private final Set<Callback> autoRemoveListeners;
    private final Context context;
    private final JobQueue jobQueue;
    private final Set<Callback> listeners;

    public BaseInteractor(Context context, JobQueue jobQueue) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(jobQueue, "jobQueue");
        this.context = context;
        this.jobQueue = jobQueue;
        this.listeners = new HashSet();
        this.autoRemoveListeners = new HashSet();
    }

    public final Context getContext() {
        return this.context;
    }

    protected final JobQueue getJobQueue() {
        return this.jobQueue;
    }

    /* JADX INFO: compiled from: BaseInteractor.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/platform/providers/library/BaseInteractor$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "toEntityItemSingle", "Lcom/amazon/music/platform/data/entity/EntityItemSingle;", "item", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EntityItemSingle toEntityItemSingle(EntityEligibilitiesProvider item) {
            Intrinsics.checkNotNullParameter(item, "item");
            final EntityItemIdentifier entityItemIdentifier = item.getEntityItem().getEntityItemIdentifier();
            return new EntityItemSingle() { // from class: com.amazon.mp3.platform.providers.library.BaseInteractor$Companion$toEntityItemSingle$1
                private final EntityItemIdentifier entityItemIdentifier;

                {
                    this.entityItemIdentifier = this.$entityItemIdentifier;
                }

                @Override // com.amazon.music.platform.data.entity.EntityItem
                public EntityItemIdentifier getEntityItemIdentifier() {
                    return this.entityItemIdentifier;
                }
            };
        }
    }

    protected final synchronized void notifyLibraryChanged() {
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).callback();
        }
        Iterator<T> it2 = this.autoRemoveListeners.iterator();
        while (it2.hasNext()) {
            ((Callback) it2.next()).callback();
        }
        this.autoRemoveListeners.clear();
    }

    protected final void announceDeleteJobFinished(int resultCode, String successMsg, String failMsg) {
        Intrinsics.checkNotNullParameter(successMsg, "successMsg");
        Intrinsics.checkNotNullParameter(failMsg, "failMsg");
        if (1 == resultCode) {
            AccessibilityUtil.announceForAccessibility(this.context, successMsg);
        } else {
            AccessibilityUtil.announceForAccessibility(this.context, failMsg);
        }
    }

    /* JADX INFO: renamed from: getJobResult-IoAF18A, reason: not valid java name */
    protected final Object m1379getJobResultIoAF18A(int resultCode) {
        if (1 == resultCode) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m2810constructorimpl(Boolean.TRUE);
        }
        Result.Companion companion2 = Result.INSTANCE;
        return Result.m2810constructorimpl(Boolean.FALSE);
    }
}
