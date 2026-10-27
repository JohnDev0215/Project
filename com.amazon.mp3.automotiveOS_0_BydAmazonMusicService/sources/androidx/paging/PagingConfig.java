package androidx.paging;

import androidx.annotation.IntRange;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PagingConfig.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bBA\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0003\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0003\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nR\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/paging/PagingConfig;", "", "pageSize", "", "prefetchDistance", "enablePlaceholders", "", "initialLoadSize", "maxSize", "jumpThreshold", "(IIZIII)V", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class PagingConfig {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int DEFAULT_INITIAL_PAGE_MULTIPLIER = 3;
    public static final int MAX_SIZE_UNBOUNDED = Integer.MAX_VALUE;

    @JvmField
    public final boolean enablePlaceholders;

    @JvmField
    public final int initialLoadSize;

    @JvmField
    public final int jumpThreshold;

    @JvmField
    public final int maxSize;

    @JvmField
    public final int pageSize;

    @JvmField
    public final int prefetchDistance;

    @JvmOverloads
    public PagingConfig(int i) {
        this(i, 0, false, 0, 0, 0, 62, null);
    }

    @JvmOverloads
    public PagingConfig(int i, @IntRange(from = 0) int i2) {
        this(i, i2, false, 0, 0, 0, 60, null);
    }

    @JvmOverloads
    public PagingConfig(int i, @IntRange(from = 0) int i2, boolean z) {
        this(i, i2, z, 0, 0, 0, 56, null);
    }

    @JvmOverloads
    public PagingConfig(int i, @IntRange(from = 0) int i2, boolean z, @IntRange(from = 1) int i3) {
        this(i, i2, z, i3, 0, 0, 48, null);
    }

    @JvmOverloads
    public PagingConfig(int i, @IntRange(from = 0) int i2, boolean z, @IntRange(from = 1) int i3, @IntRange(from = 2) int i4) {
        this(i, i2, z, i3, i4, 0, 32, null);
    }

    @JvmOverloads
    public PagingConfig(int i, @IntRange(from = 0) int i2, boolean z, @IntRange(from = 1) int i3, @IntRange(from = 2) int i4, int i5) {
        this.pageSize = i;
        this.prefetchDistance = i2;
        this.enablePlaceholders = z;
        this.initialLoadSize = i3;
        this.maxSize = i4;
        this.jumpThreshold = i5;
        if (!z && i2 == 0) {
            throw new IllegalArgumentException("Placeholders and prefetch are the only ways to trigger loading of more data in PagingData, so either placeholders must be enabled, or prefetch distance must be > 0.");
        }
        if (i4 == Integer.MAX_VALUE || i4 >= (i2 * 2) + i) {
            if (!(i5 == Integer.MIN_VALUE || i5 > 0)) {
                throw new IllegalArgumentException("jumpThreshold must be positive to enable jumps or COUNT_UNDEFINED to disable jumping.".toString());
            }
            return;
        }
        throw new IllegalArgumentException("Maximum size must be at least pageSize + 2*prefetchDist, pageSize=" + i + ", prefetchDist=" + i2 + ", maxSize=" + i4);
    }

    public /* synthetic */ PagingConfig(int i, int i2, boolean z, int i3, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i6 & 2) != 0 ? i : i2, (i6 & 4) != 0 ? true : z, (i6 & 8) != 0 ? i * 3 : i3, (i6 & 16) != 0 ? Integer.MAX_VALUE : i4, (i6 & 32) != 0 ? Integer.MIN_VALUE : i5);
    }

    /* JADX INFO: compiled from: PagingConfig.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0002¨\u0006\u0007"}, d2 = {"Landroidx/paging/PagingConfig$Companion;", "", "()V", "DEFAULT_INITIAL_PAGE_MULTIPLIER", "", "MAX_SIZE_UNBOUNDED", "getMAX_SIZE_UNBOUNDED$annotations", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getMAX_SIZE_UNBOUNDED$annotations() {
        }

        private Companion() {
        }
    }
}
