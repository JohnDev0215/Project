package com.neusoft.na.data.collection;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: DataCollectionManger.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/neusoft/na/data/collection/DataCollectionManger;", "", "()V", "TAG", "", "dataProcessing", "Lcom/neusoft/na/data/collection/DataProcessing;", "netWorkManger", "Lcom/neusoft/na/data/collection/NetWorkManger;", "post", "", "type", "oem", "header", "Lorg/json/JSONObject;", "content", "Companion", "data_collection_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class DataCollectionManger {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static DataCollectionManger instance;
    private DataProcessing dataProcessing = DataProcessing.INSTANCE.getInstance();
    private NetWorkManger netWorkManger = NetWorkManger.INSTANCE.getInstance();
    private final String TAG = "DataCollectionManger";

    /* JADX INFO: compiled from: DataCollectionManger.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/neusoft/na/data/collection/DataCollectionManger$Companion;", "", "()V", "instance", "Lcom/neusoft/na/data/collection/DataCollectionManger;", "getInstance", "data_collection_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DataCollectionManger getInstance() {
            if (DataCollectionManger.instance == null) {
                DataCollectionManger.instance = new DataCollectionManger();
            }
            return DataCollectionManger.instance;
        }
    }

    public final void post(String type, String oem, JSONObject header, JSONObject content) {
        JSONObject result;
        NetWorkManger netWorkManger;
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(oem, "oem");
        Intrinsics.checkNotNullParameter(header, "header");
        Intrinsics.checkNotNullParameter(content, "content");
        Log.d(this.TAG, "post:type:" + type + ":oem:" + oem + ":header:" + header + ":content:" + content);
        DataProcessing dataProcessing = this.dataProcessing;
        if (dataProcessing == null || (result = dataProcessing.getResult(type, oem, header, content)) == null || (netWorkManger = this.netWorkManger) == null) {
            return;
        }
        netWorkManger.send(result);
    }
}
