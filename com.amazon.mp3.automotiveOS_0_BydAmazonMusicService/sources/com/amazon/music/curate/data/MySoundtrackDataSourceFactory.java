package com.amazon.music.curate.data;

import android.content.Context;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import com.amazon.music.ContentAccessType;
import com.amazon.music.curate.R;
import com.amazon.music.curate.model.station;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: MySoundtrackDataSourceFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \n2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\nB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\bH\u0016J\b\u0010\t\u001a\u00020\u0003H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/curate/data/MySoundtrackDataSourceFactory;", "Landroidx/paging/DataSource$Factory;", "", "Lcom/amazon/music/curate/model/Station;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "create", "Landroidx/paging/DataSource;", "mySoundtrack", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MySoundtrackDataSourceFactory extends DataSource.Factory<String, station> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOG = LoggerFactory.getLogger(MySoundtrackDataSourceFactory.class.getSimpleName());
    private final Context context;

    public MySoundtrackDataSourceFactory(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    /* JADX INFO: compiled from: MySoundtrackDataSourceFactory.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/curate/data/MySoundtrackDataSourceFactory$Companion;", "", "()V", "LOG", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "getLOG", "()Lorg/slf4j/Logger;", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Logger getLOG() {
            return MySoundtrackDataSourceFactory.LOG;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final station mySoundtrack() {
        Context context = this.context;
        Intrinsics.checkNotNull(context);
        String string = context.getResources().getString(R.string.my_soundtrack_label);
        Intrinsics.checkNotNullExpressionValue(string, "context!!.resources.getS…ring.my_soundtrack_label)");
        Context context2 = this.context;
        Intrinsics.checkNotNull(context2);
        String string2 = context2.getResources().getString(R.string.my_soundtrack_artwork_url);
        Intrinsics.checkNotNullExpressionValue(string2, "context!!.resources.getS…y_soundtrack_artwork_url)");
        Boolean bool = Boolean.TRUE;
        Boolean bool2 = Boolean.FALSE;
        return new station(ContextMappingConstants.MY_SOUNDTRACK_STATION_KEY, string, string2, bool, bool, bool, bool2, null, MapsKt__MapsKt.hashMapOf(TuplesKt.to(ContentAccessType.PRIME, bool2), TuplesKt.to(ContentAccessType.HAWKFIRE, bool2)));
    }

    @Override // androidx.paging.DataSource.Factory
    public DataSource<String, station> create() {
        return new PageKeyedDataSource<String, station>() { // from class: com.amazon.music.curate.data.MySoundtrackDataSourceFactory$create$dataSource$1
            @Override // androidx.paging.PageKeyedDataSource
            public void loadInitial(PageKeyedDataSource.LoadInitialParams<String> params, PageKeyedDataSource.LoadInitialCallback<String, station> callback) {
                Intrinsics.checkNotNullParameter(params, "params");
                Intrinsics.checkNotNullParameter(callback, "callback");
                callback.onResult(CollectionsKt__CollectionsJVMKt.listOf(this.this$0.mySoundtrack()), null, null);
            }

            @Override // androidx.paging.PageKeyedDataSource
            public void loadAfter(PageKeyedDataSource.LoadParams<String> params, PageKeyedDataSource.LoadCallback<String, station> callback) {
                Intrinsics.checkNotNullParameter(params, "params");
                Intrinsics.checkNotNullParameter(callback, "callback");
                throw new NotImplementedError(Intrinsics.stringPlus("An operation is not implemented: ", "not implemented"));
            }

            @Override // androidx.paging.PageKeyedDataSource
            public void loadBefore(PageKeyedDataSource.LoadParams<String> params, PageKeyedDataSource.LoadCallback<String, station> callback) {
                Intrinsics.checkNotNullParameter(params, "params");
                Intrinsics.checkNotNullParameter(callback, "callback");
                throw new NotImplementedError(Intrinsics.stringPlus("An operation is not implemented: ", "not implemented"));
            }
        };
    }
}
