package com.byd.overseaappstore.module.ability.data.generator;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.byd.car.locale.ccTLD;
import com.byd.overseaappstore.module.ability.R$string;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDtFixInfo;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareInterceptorRetData;
import com.byd.overseaappstore.module.ability.data.req.DownloadUrlInfo;
import com.byd.overseaappstore.module.ability.data.req.DownloadUrlRefreshReq;
import com.byd.overseaappstore.module.ability.data.rsp.DownloadUrlRefreshRsp;
import com.byd.overseaappstore.module.ability.data.rsp.FeatureTopicSoftwareRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDetailRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareDeviceInstalledRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareExtraRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareIntermediaryRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareSearchRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareThemRsp;
import com.byd.overseaappstore.module.ability.data.rsp.SoftwareUpdateRsp;
import com.byd.overseaappstore.module.ability.data.upper.FeatureThemTopicGroup;
import com.byd.overseaappstore.module.ability.data.upper.SearchResult;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareDetail;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareInstalled;
import com.byd.overseaappstore.module.ability.data.upper.TopicGroupTitle;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareExtParam;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareFileUnzipInfo;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParamWrap;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import com.byd.overseaappstore.module.ability.util.ipc.IpcSoftware;
import com.byd.overseaappstore.module.ability.util.ipc.IpcSoftwareRsp;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.a2;
import spi.ce;
import spi.ct;
import spi.d;
import spi.e;
import spi.kg;
import spi.mv;
import spi.o5;
import spi.oq;
import spi.pq;
import spi.qq;
import spi.sr;
import spi.vm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000¶\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\u000b\b\u0007¢\u0006\u0006\b\u0093\u0001\u0010\u0094\u0001J\u001a\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bJ\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\rJ\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0011J\u0016\u0010\n\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015J\u001e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00172\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017J\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u001eJ(\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00172\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00020\u00172\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\u0017J\u000e\u0010$\u001a\u00020#2\u0006\u0010\"\u001a\u00020\u0002J\u0016\u0010$\u001a\u00020#2\u0006\u0010\"\u001a\u00020\u00022\u0006\u0010&\u001a\u00020%J\u000e\u0010)\u001a\u00020\u00182\u0006\u0010(\u001a\u00020'J\u0016\u0010)\u001a\u00020\u00182\u0006\u0010+\u001a\u00020*2\u0006\u0010-\u001a\u00020,J\u0016\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00172\u0006\u0010\"\u001a\u00020\u0002J.\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00172\u0006\u0010+\u001a\u00020*2\u0006\u00100\u001a\u00020/2\u000e\u00101\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0017J\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0013J&\u00103\u001a\n\u0012\u0004\u0012\u00020,\u0018\u00010\u00172\u0006\u0010+\u001a\u00020*2\u000e\u00102\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0017J\u001e\u00103\u001a\n\u0012\u0004\u0012\u00020,\u0018\u00010\u00172\u000e\u00104\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017J\u0016\u00106\u001a\u00020,2\u0006\u0010+\u001a\u00020*2\u0006\u00105\u001a\u00020\u001aJ\u000e\u00106\u001a\u00020,2\u0006\u0010\u001c\u001a\u00020\u0018J \u0010:\u001a\u0004\u0018\u00010'2\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020*2\u0006\u0010+\u001a\u00020*J*\u0010<\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00180;2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u0018JH\u0010B\u001a\"\u0012\u0004\u0012\u00020>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020%\u0012\u0006\u0012\u0004\u0018\u00010\u00020A0;2\u0006\u0010=\u001a\u00020*2\u0006\u0010?\u001a\u00020>2\u0006\u0010@\u001a\u00020%2\b\u0010\"\u001a\u0004\u0018\u00010\u0002J\u0006\u0010C\u001a\u00020/J\u0016\u0010E\u001a\u00020D2\u0006\u0010\"\u001a\u00020\u00022\u0006\u0010&\u001a\u00020%J \u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020*2\b\b\u0002\u00100\u001a\u00020/2\u0006\u0010&\u001a\u00020%J\u0016\u0010E\u001a\u00020D2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010&\u001a\u00020%J\u0014\u0010I\u001a\u00020H2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00020\u0017J\u001a\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00040\u00172\f\u0010K\u001a\b\u0012\u0004\u0012\u00020J0\u0017J$\u0010Q\u001a\u00020P2\u0006\u0010M\u001a\u00020\u00022\u0014\u0010O\u001a\u0010\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020N\u0018\u00010;J&\u0010X\u001a\u00020W2\u0006\u0010R\u001a\u00020N2\u0006\u0010S\u001a\u00020*2\u0006\u0010U\u001a\u00020T2\u0006\u0010V\u001a\u00020%J*\u0010[\u001a\b\u0012\u0004\u0012\u00020\u00020\u00172\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00020\u00172\u000e\u0010Z\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0017J\u000e\u0010_\u001a\u00020^2\u0006\u0010]\u001a\u00020\\J\u0010\u0010_\u001a\u0004\u0018\u00010^2\u0006\u0010`\u001a\u00020*J*\u0010f\u001a\u00020e2\u0006\u0010a\u001a\u00020*2\u0006\u0010b\u001a\u00020*2\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020*0cJ\u0016\u0010j\u001a\u00020*2\u0006\u0010h\u001a\u00020g2\u0006\u0010i\u001a\u00020gJU\u0010q\u001a\u001c\u0012\u0004\u0012\u00020>\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020k\u0012\u0006\u0012\u0004\u0018\u00010p0;0;2\u0006\u0010?\u001a\u00020>2\u0006\u0010l\u001a\u00020k2\b\u0010M\u001a\u0004\u0018\u00010\u00022\b\u0010m\u001a\u0004\u0018\u00010%2\b\u0010o\u001a\u0004\u0018\u00010n¢\u0006\u0004\bq\u0010rJ\u000e\u0010s\u001a\u00020n2\u0006\u0010M\u001a\u00020\u0002J\u0014\u0010s\u001a\u00020n2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00020\u0017J\u001c\u0010[\u001a\b\u0012\u0004\u0012\u00020\u00020\u00172\u000e\u0010Z\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0017J\u0016\u0010t\u001a\u00020p2\u0006\u0010M\u001a\u00020\u00022\u0006\u0010m\u001a\u00020%J\u001e\u0010x\u001a\u00020>2\u0006\u0010u\u001a\u00020k2\u0006\u0010v\u001a\u00020k2\u0006\u0010w\u001a\u00020kJ\u0016\u0010x\u001a\u00020>2\u0006\u0010?\u001a\u00020>2\u0006\u0010w\u001a\u00020kJ&\u0010{\u001a\n\u0012\u0004\u0012\u00020y\u0018\u00010\u00172\u000e\u0010z\u001a\n\u0012\u0004\u0012\u00020y\u0018\u00010\u00172\u0006\u00108\u001a\u000207J&\u0010}\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u00172\u000e\u0010|\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u00172\u0006\u00108\u001a\u000207J'\u0010\u0080\u0001\u001a\n\u0012\u0004\u0012\u00020~\u0018\u00010\u00172\u000e\u0010\u007f\u001a\n\u0012\u0004\u0012\u00020~\u0018\u00010\u00172\u0006\u00108\u001a\u000207J)\u0010\u0083\u0001\u001a\u00020*2\u0007\u0010\u0081\u0001\u001a\u00020%2\u0007\u0010\u0082\u0001\u001a\u00020*2\u000e\u0010z\u001a\n\u0012\u0004\u0012\u00020y\u0018\u00010\u0017J\u001e\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0084\u00012\n\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0084\u00012\u0006\u00108\u001a\u000207J\u001e\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u0087\u00012\n\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0087\u00012\u0006\u00108\u001a\u000207J#\u0010\u008b\u0001\u001a\u00020*2\t\u0010\u008a\u0001\u001a\u0004\u0018\u00010%2\u0006\u00108\u001a\u000207¢\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001JH\u0010B\u001a\"\u0012\u0004\u0012\u00020k\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020%\u0012\u0006\u0012\u0004\u0018\u00010\u00020A0;2\u0006\u0010=\u001a\u00020*2\u0006\u0010?\u001a\u00020k2\u0006\u0010@\u001a\u00020%2\b\u0010\"\u001a\u0004\u0018\u00010\u0002J\u0018\u0010\u008e\u0001\u001a\u00020*2\u0007\u0010\u008d\u0001\u001a\u00020T2\u0006\u00108\u001a\u000207J\u0018\u0010\u0090\u0001\u001a\u00020*2\u0007\u0010\u008f\u0001\u001a\u00020%2\u0006\u00108\u001a\u000207J\u0018\u0010\u0092\u0001\u001a\u00020*2\u0007\u0010\u0091\u0001\u001a\u00020*2\u0006\u00108\u001a\u000207¨\u0006\u0095\u0001"}, d2 = {"Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;", "prev", "Lcom/byd/overseaappstore/module/ability/data/req/DownloadUrlInfo;", "refresh", "parseToSoftwareOptParam", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareUpdateRsp;", "softwareUpdateRsp", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareIntermediaryRsp;", "parseToSoftwareIntermediaryRsp", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareThemRsp;", "softwareThemRsp", "Lcom/byd/overseaappstore/module/ability/data/rsp/FeatureTopicSoftwareRsp;", "featureTopicSoftwareRsp", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDetailRsp;", "softwareDetailRsp", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareSearchRsp;", "softwareSearchRsp", "Lspi/qq;", "softwareLocalWrap", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareDeviceInstalledRsp;", "softwareDeviceInstalledRsp", "", "Lspi/oq;", "extraList", "Lcom/byd/overseaappstore/module/ability/data/rsp/SoftwareExtraRsp;", "parseToSoftwareExtraRspList", "softwareExtraLocal", "parseToSoftwareExtraRsp", "Lcom/byd/overseaappstore/module/ability/data/upper/Software;", "software", "prevList", "refreshList", "softwareOptParam", "Lspi/pq;", "parseToSoftwareLocal", "", "state", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareFileUnzipInfo;", "softwareFileUnzipInfo", "parseToSoftwareExtraLocal", "", "primePackageName", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareExtParam;", "softwareExtParam", "parseToSoftwareExtraLocalList", "", "sourceType", "extraRspList", "extra", "parseToSoftwareExtParamList", NotificationCompat.MessagingStyle.Message.KEY_EXTRAS_BUNDLE, "softwareExtraRsp", "parseToSoftwareExtParam", "Landroid/content/Context;", "context", "apkPath", "parseToSoftwareFileUnzipInfo", "Lkotlin/Pair;", "parseToPair", "tip", "Lspi/a2;", "bizSrc", "processType", "Lkotlin/Triple;", "generateDownloadTaskProcessPair", "getLanguageCode", "Lcom/byd/overseaappstore/module/ability/util/notify/router/iuu/IUUState;", "parseToIUUState", "packageName", "optParamList", "Lcom/byd/overseaappstore/module/ability/data/req/DownloadUrlRefreshReq;", "parseToDownloadUrlRefreshReq", "Lcom/byd/overseaappstore/module/ability/data/rsp/DownloadUrlRefreshRsp;", "rspList", "parseToDownloadUrlInfoList", "optParam", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareStateData;", "pair", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParamWrap;", "parseToSoftwareOptParamWrap", "softwareStateData", "downloadUrl", "", "totalSize", "versionCode", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareInterceptorRetData;", "parseToSoftwareInterceptorRetData", "softwareOptParamList", "softwareLocalWrapList", "parseToSoftwareOptParamList", "Lcom/byd/overseaappstore/module/ability/util/ipc/IpcSoftwareRsp;", "ipcSoftwareRsp", "Lcom/byd/overseaappstore/module/ability/util/ipc/IpcSoftware;", "parseToIpcSoftware", "json", "scheme", "host", "", "mapParam", "Landroid/net/Uri;", "parseToUri", "", "permissionAllGranted", "protocolAgree", "parseToUserActInState", "", "dispatchType", "currentState", "Lspi/mv;", "carrier", "Lcom/byd/overseaappstore/module/ability/util/operate/factory/lineb/CacheCarrier;", "parseToDispatchCacheCarrierPair", "(Lspi/a2;BLcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;Ljava/lang/Integer;Lspi/mv;)Lkotlin/Pair;", "parseToWorkCarrier", "parseToCacheCarrier", "channel", "bizType", "bizWay", "parseToBizSrc", "Lcom/byd/overseaappstore/module/ability/data/upper/FeatureThemTopicGroup;", "featureThemTopicGroupList", "parseLanguageToUpdateFeatureThemTopicGroupList", "softwareList", "parseLanguageToUpdateSoftwareList", "Lcom/byd/overseaappstore/module/ability/data/upper/SoftwareInstalled;", "softwareInstalledList", "parseLanguageToUpdateInstalledSoftwareList", "topicGroupId", "defaultTitle", "parseLanguageToUpdateTopicTitle", "Lcom/byd/overseaappstore/module/ability/data/upper/SoftwareDetail;", "softwareDetail", "parseLanguageToUpdateSoftwareDetail", "Lcom/byd/overseaappstore/module/ability/data/upper/SearchResult;", "searchResult", "parseLanguageToUpdateSearchResultItem", ccTLD.INDONESIA, "generateAPPClassifyName", "(Ljava/lang/Integer;Landroid/content/Context;)Ljava/lang/String;", "updateTime", "generateUpdateTimeTip", "screenCompatibility", "generateScreenCompatibilityTip", "versionTip", "generateVersionTip", "<init>", "()V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class DataGenerator {
    public static /* synthetic */ IUUState parseToIUUState$default(DataGenerator dataGenerator, String str, short s, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            s = 1;
        }
        return dataGenerator.parseToIUUState(str, s, i);
    }

    private final SoftwareOptParam parseToSoftwareOptParam(SoftwareOptParam prev, DownloadUrlInfo refresh) {
        String downloadUrl;
        int id = prev.getId();
        String packageName = prev.getPackageName();
        String name = prev.getName();
        String icon = prev.getIcon();
        if (refresh == null || (downloadUrl = refresh.getDownloadUrl()) == null) {
            downloadUrl = prev.getDownloadUrl();
        }
        return new SoftwareOptParam(id, packageName, name, icon, downloadUrl, prev.getTotalSize(), prev.getVersionCode(), prev.getClassifyName(), prev.getClassifyId(), prev.getSignature(), prev.getState(), prev.getPath(), prev.getSourceType(), prev.getExtra());
    }

    @NotNull
    public final String generateAPPClassifyName(@Nullable Integer id, @NotNull Context context) {
        vm vmVar;
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        if (id != null && id.intValue() == 1) {
            vmVar = vm.a;
            i = R$string.app_classify_name_navi;
        } else if (id != null && id.intValue() == 2) {
            vmVar = vm.a;
            i = R$string.app_classify_name_music;
        } else if (id != null && id.intValue() == 3) {
            vmVar = vm.a;
            i = R$string.app_classify_name_video;
        } else if (id != null && id.intValue() == 4) {
            vmVar = vm.a;
            i = R$string.app_classify_name_game;
        } else if (id != null && id.intValue() == 5) {
            vmVar = vm.a;
            i = R$string.app_classify_name_social;
        } else if (id != null && id.intValue() == 6) {
            vmVar = vm.a;
            i = R$string.app_classify_name_weather;
        } else if (id != null && id.intValue() == 7) {
            vmVar = vm.a;
            i = R$string.app_classify_name_efficiency;
        } else if (id != null && id.intValue() == 8) {
            vmVar = vm.a;
            i = R$string.app_classify_name_news;
        } else if (id != null && id.intValue() == 9) {
            vmVar = vm.a;
            i = R$string.app_classify_name_entertainment;
        } else if (id != null && id.intValue() == 10) {
            vmVar = vm.a;
            i = R$string.app_classify_name_tool;
        } else {
            vmVar = vm.a;
            i = R$string.download_manager_no_classify;
        }
        return vmVar.e(context, i);
    }

    @NotNull
    public final Pair<Byte, Triple<String, Integer, SoftwareOptParam>> generateDownloadTaskProcessPair(@NotNull String tip, byte bizSrc, int processType, @Nullable SoftwareOptParam softwareOptParam) {
        Intrinsics.checkNotNullParameter(tip, "tip");
        return new Pair<>(Byte.valueOf(bizSrc), new Triple(tip, Integer.valueOf(processType), softwareOptParam));
    }

    @NotNull
    public final Pair<a2, Triple<String, Integer, SoftwareOptParam>> generateDownloadTaskProcessPair(@NotNull String tip, @NotNull a2 bizSrc, int processType, @Nullable SoftwareOptParam softwareOptParam) {
        Intrinsics.checkNotNullParameter(tip, "tip");
        Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
        return new Pair<>(bizSrc, new Triple(tip, Integer.valueOf(processType), softwareOptParam));
    }

    @NotNull
    public final String generateScreenCompatibilityTip(int screenCompatibility, @NotNull Context context) {
        vm vmVar;
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        if (screenCompatibility == 1) {
            vmVar = vm.a;
            i = R$string.software_detail_suit_land;
        } else if (screenCompatibility == 2) {
            vmVar = vm.a;
            i = R$string.software_detail_suit_port;
        } else {
            if (screenCompatibility != 3) {
                return "";
            }
            vmVar = vm.a;
            i = R$string.software_detail_suit_rotated;
        }
        return vmVar.e(context, i);
    }

    @NotNull
    public final String generateUpdateTimeTip(long updateTime, @NotNull Context context) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        vm vmVar = vm.a;
        int i = R$string.software_detail_update_date;
        Object[] objArr = new Object[1];
        o5 o5Var = o5.a;
        Long lValueOf = Long.valueOf(updateTime);
        Objects.requireNonNull(o5Var);
        if (lValueOf == null) {
            str = "";
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            str = simpleDateFormat.format(new Date(lValueOf.longValue()));
            Intrinsics.checkNotNullExpressionValue(str, "dateFormat.format(Date(millis))");
        }
        objArr[0] = str;
        return vmVar.f(context, i, objArr);
    }

    @NotNull
    public final String generateVersionTip(@NotNull String versionTip, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(versionTip, "versionTip");
        Intrinsics.checkNotNullParameter(context, "context");
        return vm.a.f(context, R$string.software_detail_version, versionTip);
    }

    public final short getLanguageCode() {
        kg kgVar = kg.a;
        if (kgVar.a() != -1) {
            return kgVar.a();
        }
        if (kgVar.b() != -1) {
            return kgVar.b();
        }
        if (kgVar.c() != -1) {
            return kgVar.c();
        }
        return (short) 1;
    }

    @Nullable
    public final List<FeatureThemTopicGroup> parseLanguageToUpdateFeatureThemTopicGroupList(@Nullable List<FeatureThemTopicGroup> featureThemTopicGroupList, @NotNull Context context) {
        TopicGroupTitle topicGroupTitle;
        String tip;
        Object next;
        Intrinsics.checkNotNullParameter(context, "context");
        if (featureThemTopicGroupList == null || featureThemTopicGroupList.isEmpty()) {
            ct.b("parseLanguageToUpdateFeatureThemTopicGroupList featureThemTopicGroupList.isNullOrEmpty", new Object[0]);
            return null;
        }
        short languageCode = getLanguageCode();
        ArrayList arrayList = new ArrayList(CollectionsKt.b(featureThemTopicGroupList));
        for (FeatureThemTopicGroup featureThemTopicGroup : featureThemTopicGroupList) {
            List<TopicGroupTitle> topicTitleList = featureThemTopicGroup.getTopicTitleList();
            if (topicTitleList != null) {
                Iterator<T> it = topicTitleList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    if (((TopicGroupTitle) next).getLanguage() == languageCode) {
                        break;
                    }
                }
                topicGroupTitle = (TopicGroupTitle) next;
            } else {
                topicGroupTitle = null;
            }
            if (topicGroupTitle == null) {
                tip = featureThemTopicGroup.getTitle();
                ct.a(e.c("parseLanguageToUpdateFeatureThemTopicGroupList no languageCodeMatch useRaw deviceLanguageCode:", languageCode), new Object[0]);
            } else {
                tip = topicGroupTitle.getTip();
            }
            featureThemTopicGroup.setTitle(tip);
            List<Software> languageToUpdateSoftwareList = parseLanguageToUpdateSoftwareList(featureThemTopicGroup.getSoftwareList(), context);
            if (languageToUpdateSoftwareList == null || languageToUpdateSoftwareList.isEmpty()) {
                ct.b("parseLanguageToUpdateFeatureThemTopicGroupList updateSoftwareList.isNullOrEmpty", new Object[0]);
            }
            featureThemTopicGroup.setSoftwareList(languageToUpdateSoftwareList);
            arrayList.add(featureThemTopicGroup);
        }
        return arrayList;
    }

    @Nullable
    public final List<SoftwareInstalled> parseLanguageToUpdateInstalledSoftwareList(@Nullable List<SoftwareInstalled> softwareInstalledList, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (softwareInstalledList == null || softwareInstalledList.isEmpty()) {
            ct.b("parseLanguageToUpdateSoftwareList softwareInstalledList.isNullOrEmpty", new Object[0]);
            return null;
        }
        for (SoftwareInstalled softwareInstalled : softwareInstalledList) {
            softwareInstalled.setClassifyName(generateAPPClassifyName(Integer.valueOf(softwareInstalled.getClassifyId()), context));
            softwareInstalled.setVersionTip(generateVersionTip(softwareInstalled.getVersionTipRaw(), context));
        }
        return softwareInstalledList;
    }

    @Nullable
    public final SearchResult parseLanguageToUpdateSearchResultItem(@Nullable SearchResult searchResult, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (searchResult == null) {
            return null;
        }
        List<Software> list = searchResult.getList();
        if (list != null) {
            for (Software software : list) {
                software.setClassifyName(generateAPPClassifyName(Integer.valueOf(software.getClassifyId()), context));
                software.setVersionTip(generateVersionTip(software.getVersionTipRaw(), context));
            }
        }
        return searchResult;
    }

    @Nullable
    public final SoftwareDetail parseLanguageToUpdateSoftwareDetail(@Nullable SoftwareDetail softwareDetail, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (softwareDetail == null || softwareDetail.getSoftware() == null) {
            return null;
        }
        String strGenerateUpdateTimeTip = generateUpdateTimeTip(softwareDetail.getUpdateTime(), context);
        String strGenerateScreenCompatibilityTip = generateScreenCompatibilityTip(softwareDetail.getScreenCompatibility(), context);
        String strGenerateAPPClassifyName = generateAPPClassifyName(Integer.valueOf(softwareDetail.getSoftware().getClassifyId()), context);
        String strGenerateVersionTip = generateVersionTip(softwareDetail.getSoftware().getVersionTipRaw(), context);
        StringBuilder sbL = d.l("parseLanguageToUpdateSoftwareDetail updateUpdateTimeTip:", strGenerateUpdateTimeTip, " updateScreenCompatibilityTip:", strGenerateScreenCompatibilityTip, " updateClassifyName:");
        sbL.append(strGenerateAPPClassifyName);
        sbL.append(" updateVersionTip:");
        sbL.append(strGenerateVersionTip);
        ct.a(sbL.toString(), new Object[0]);
        softwareDetail.setUpdateTimeTip(strGenerateUpdateTimeTip);
        softwareDetail.setScreenCompatibilityTip(strGenerateScreenCompatibilityTip);
        softwareDetail.getSoftware().setClassifyName(strGenerateAPPClassifyName);
        softwareDetail.getSoftware().setVersionTip(strGenerateVersionTip);
        return softwareDetail;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final List<Software> parseLanguageToUpdateSoftwareList(@Nullable List<? extends Software> softwareList, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (softwareList == 0 || softwareList.isEmpty()) {
            ct.b("parseLanguageToUpdateSoftwareList softwareList.isNullOrEmpty", new Object[0]);
            return null;
        }
        for (Software software : softwareList) {
            software.setClassifyName(generateAPPClassifyName(Integer.valueOf(software.getClassifyId()), context));
            software.setVersionTip(generateVersionTip(software.getVersionTipRaw(), context));
        }
        return softwareList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final String parseLanguageToUpdateTopicTitle(int topicGroupId, @NotNull String defaultTitle, @Nullable List<FeatureThemTopicGroup> featureThemTopicGroupList) {
        TopicGroupTitle topicGroupTitle;
        Object next;
        Object obj;
        Intrinsics.checkNotNullParameter(defaultTitle, "defaultTitle");
        if ((featureThemTopicGroupList == null || featureThemTopicGroupList.isEmpty()) == true) {
            ct.b("parseLanguageToUpdateTopicTitle featureThemTopicGroupList.isNullOrEmpty", new Object[0]);
            return defaultTitle;
        }
        Iterator<T> it = featureThemTopicGroupList.iterator();
        while (true) {
            topicGroupTitle = null;
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if ((((FeatureThemTopicGroup) next).getId() == topicGroupId) != false) {
                break;
            }
        }
        FeatureThemTopicGroup featureThemTopicGroup = (FeatureThemTopicGroup) next;
        if (featureThemTopicGroup == null) {
            ct.b("parseLanguageToUpdateTopicTitle targetFeatureThemTopicGroup isNull", new Object[0]);
            return defaultTitle;
        }
        short languageCode = getLanguageCode();
        List<TopicGroupTitle> topicTitleList = featureThemTopicGroup.getTopicTitleList();
        if (topicTitleList != null) {
            Iterator<T> it2 = topicTitleList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next2 = it2.next();
                if ((((TopicGroupTitle) next2).getLanguage() == languageCode) != false) {
                    obj = next2;
                    break;
                }
            }
            topicGroupTitle = (TopicGroupTitle) obj;
        }
        if (topicGroupTitle != null) {
            return topicGroupTitle.getTip();
        }
        ct.b("parseLanguageToUpdateTopicTitle targetTopicGroupTitle isNull", new Object[0]);
        return defaultTitle;
    }

    @NotNull
    public final a2 parseToBizSrc(byte channel, byte bizType, byte bizWay) {
        return new a2(channel, bizType, bizWay);
    }

    @NotNull
    public final a2 parseToBizSrc(@NotNull a2 bizSrc, byte bizWay) {
        Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
        bizSrc.c = bizWay;
        return bizSrc;
    }

    @NotNull
    public final CacheCarrier parseToCacheCarrier(@NotNull SoftwareOptParam optParam, int currentState) {
        Intrinsics.checkNotNullParameter(optParam, "optParam");
        CacheCarrier cacheCarrier = new CacheCarrier(currentState);
        cacheCarrier.setOptParam(optParam);
        return cacheCarrier;
    }

    @NotNull
    public final Pair<a2, Pair<Byte, CacheCarrier>> parseToDispatchCacheCarrierPair(@NotNull a2 bizSrc, byte dispatchType, @Nullable SoftwareOptParam optParam, @Nullable Integer currentState, @Nullable mv carrier) {
        Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
        CacheCarrier cacheCarrier = new CacheCarrier(currentState != null ? currentState.intValue() : 0);
        cacheCarrier.setOptParam(optParam);
        cacheCarrier.setOptParamList(carrier != null ? carrier.getOptParamList() : null);
        cacheCarrier.setOptParamWrap(carrier != null ? carrier.getOptParamWrap() : null);
        return new Pair<>(bizSrc, new Pair(Byte.valueOf(dispatchType), cacheCarrier));
    }

    @NotNull
    public final List<DownloadUrlInfo> parseToDownloadUrlInfoList(@NotNull List<DownloadUrlRefreshRsp> rspList) {
        Intrinsics.checkNotNullParameter(rspList, "rspList");
        ArrayList arrayList = new ArrayList(CollectionsKt.b(rspList));
        for (DownloadUrlRefreshRsp downloadUrlRefreshRsp : rspList) {
            arrayList.add(new DownloadUrlInfo(downloadUrlRefreshRsp.getPackageName(), downloadUrlRefreshRsp.getDownloadUrl()));
        }
        return arrayList;
    }

    @NotNull
    public final DownloadUrlRefreshReq parseToDownloadUrlRefreshReq(@NotNull List<SoftwareOptParam> optParamList) {
        Intrinsics.checkNotNullParameter(optParamList, "optParamList");
        ArrayList arrayList = new ArrayList(CollectionsKt.b(optParamList));
        for (SoftwareOptParam softwareOptParam : optParamList) {
            arrayList.add(new DownloadUrlInfo(softwareOptParam.getPackageName(), softwareOptParam.getDownloadUrl()));
        }
        return new DownloadUrlRefreshReq(arrayList);
    }

    @NotNull
    public final IUUState parseToIUUState(@NotNull SoftwareOptParam softwareOptParam, int state) {
        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
        return new IUUState(softwareOptParam, state);
    }

    @NotNull
    public final IUUState parseToIUUState(@NotNull String packageName, short sourceType, int state) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return new IUUState(new SoftwareOptParam(0, packageName, "", "", "", 0L, 0, "", 0, "", 0, "", sourceType, null), state);
    }

    @NotNull
    public final IUUState parseToIUUState(@NotNull qq softwareLocalWrap, int state) {
        Intrinsics.checkNotNullParameter(softwareLocalWrap, "softwareLocalWrap");
        return new IUUState(parseToSoftwareOptParam(softwareLocalWrap), state);
    }

    @NotNull
    public final IpcSoftware parseToIpcSoftware(@NotNull IpcSoftwareRsp ipcSoftwareRsp) {
        Intrinsics.checkNotNullParameter(ipcSoftwareRsp, "ipcSoftwareRsp");
        Integer id = ipcSoftwareRsp.getId();
        int iIntValue = id != null ? id.intValue() : 0;
        String name = ipcSoftwareRsp.getName();
        String str = name == null ? "" : name;
        String package_name = ipcSoftwareRsp.getPackage_name();
        String str2 = package_name == null ? "" : package_name;
        Integer ver_code = ipcSoftwareRsp.getVer_code();
        int iIntValue2 = ver_code != null ? ver_code.intValue() : 0;
        String version = ipcSoftwareRsp.getVersion();
        String str3 = version == null ? "" : version;
        Long size = ipcSoftwareRsp.getSize();
        long jLongValue = size != null ? size.longValue() : 0L;
        Short source_type = ipcSoftwareRsp.getSource_type();
        return new IpcSoftware(iIntValue, str, str2, iIntValue2, str3, jLongValue, source_type != null ? source_type.shortValue() : (short) 1);
    }

    @Nullable
    public final IpcSoftware parseToIpcSoftware(@NotNull String json) {
        Object objM63constructorimpl;
        Intrinsics.checkNotNullParameter(json, "json");
        try {
            Result.Companion companion = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl((IpcSoftwareRsp) ce.a(json, IpcSoftwareRsp.class));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m69isFailureimpl(objM63constructorimpl)) {
            objM63constructorimpl = null;
        }
        IpcSoftwareRsp ipcSoftwareRsp = (IpcSoftwareRsp) objM63constructorimpl;
        if (ipcSoftwareRsp == null) {
            ct.a(d.f("parseToIpcSoftware ipcSoftwareRsp is null json:", json), new Object[0]);
            return null;
        }
        Integer id = ipcSoftwareRsp.getId();
        int iIntValue = id != null ? id.intValue() : 0;
        String name = ipcSoftwareRsp.getName();
        String str = name == null ? "" : name;
        String package_name = ipcSoftwareRsp.getPackage_name();
        String str2 = package_name == null ? "" : package_name;
        Integer ver_code = ipcSoftwareRsp.getVer_code();
        int iIntValue2 = ver_code != null ? ver_code.intValue() : 0;
        String version = ipcSoftwareRsp.getVersion();
        String str3 = version == null ? "" : version;
        Long size = ipcSoftwareRsp.getSize();
        long jLongValue = size != null ? size.longValue() : 0L;
        Short source_type = ipcSoftwareRsp.getSource_type();
        return new IpcSoftware(iIntValue, str, str2, iIntValue2, str3, jLongValue, source_type != null ? source_type.shortValue() : (short) 1);
    }

    @NotNull
    public final Pair<qq, oq> parseToPair(@Nullable qq softwareLocalWrap, @Nullable oq softwareExtraLocal) {
        return new Pair<>(softwareLocalWrap, softwareExtraLocal);
    }

    @NotNull
    public final SoftwareExtParam parseToSoftwareExtParam(@NotNull String primePackageName, @NotNull SoftwareExtraRsp softwareExtraRsp) {
        Intrinsics.checkNotNullParameter(primePackageName, "primePackageName");
        Intrinsics.checkNotNullParameter(softwareExtraRsp, "softwareExtraRsp");
        String package_name = softwareExtraRsp.getPackage_name();
        String str = package_name == null ? "" : package_name;
        String signature = softwareExtraRsp.getSignature();
        String str2 = signature == null ? "" : signature;
        Integer ver_code = softwareExtraRsp.getVer_code();
        int iIntValue = ver_code != null ? ver_code.intValue() : 0;
        String version = softwareExtraRsp.getVersion();
        return new SoftwareExtParam(str, primePackageName, str2, iIntValue, version == null ? "" : version);
    }

    @NotNull
    public final SoftwareExtParam parseToSoftwareExtParam(@NotNull oq softwareExtraLocal) {
        Intrinsics.checkNotNullParameter(softwareExtraLocal, "softwareExtraLocal");
        return new SoftwareExtParam(softwareExtraLocal.a, softwareExtraLocal.b, softwareExtraLocal.c, softwareExtraLocal.d, softwareExtraLocal.e);
    }

    @Nullable
    public final List<SoftwareExtParam> parseToSoftwareExtParamList(@NotNull String primePackageName, @Nullable List<SoftwareExtraRsp> extra) {
        Intrinsics.checkNotNullParameter(primePackageName, "primePackageName");
        if (extra == null || extra.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.b(extra));
        Iterator<T> it = extra.iterator();
        while (it.hasNext()) {
            arrayList.add(parseToSoftwareExtParam(primePackageName, (SoftwareExtraRsp) it.next()));
        }
        return arrayList;
    }

    @Nullable
    public final List<SoftwareExtParam> parseToSoftwareExtParamList(@Nullable List<oq> extras) {
        if (extras == null || extras.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.b(extras));
        Iterator<T> it = extras.iterator();
        while (it.hasNext()) {
            arrayList.add(parseToSoftwareExtParam((oq) it.next()));
        }
        return arrayList;
    }

    @NotNull
    public final oq parseToSoftwareExtraLocal(@NotNull SoftwareFileUnzipInfo softwareFileUnzipInfo) {
        Intrinsics.checkNotNullParameter(softwareFileUnzipInfo, "softwareFileUnzipInfo");
        return new oq(softwareFileUnzipInfo.getPackageName(), softwareFileUnzipInfo.getPrimePackageName(), softwareFileUnzipInfo.getSignature(), softwareFileUnzipInfo.getVersionCode(), softwareFileUnzipInfo.getVersionName());
    }

    @NotNull
    public final oq parseToSoftwareExtraLocal(@NotNull String primePackageName, @NotNull SoftwareExtParam softwareExtParam) {
        Intrinsics.checkNotNullParameter(primePackageName, "primePackageName");
        Intrinsics.checkNotNullParameter(softwareExtParam, "softwareExtParam");
        return new oq(softwareExtParam.getPackageName(), primePackageName, softwareExtParam.getSignature(), softwareExtParam.getVersionCode(), softwareExtParam.getVersionName());
    }

    @Nullable
    public final List<oq> parseToSoftwareExtraLocalList(@NotNull SoftwareOptParam softwareOptParam) {
        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
        if (sr.a.k(softwareOptParam.getSourceType())) {
            List<SoftwareExtParam> extra = softwareOptParam.getExtra();
            if (!(extra == null || extra.isEmpty())) {
                List<SoftwareExtParam> extra2 = softwareOptParam.getExtra();
                ArrayList arrayList = new ArrayList(CollectionsKt.b(extra2));
                Iterator<T> it = extra2.iterator();
                while (it.hasNext()) {
                    arrayList.add(parseToSoftwareExtraLocal(softwareOptParam.getPackageName(), (SoftwareExtParam) it.next()));
                }
                return arrayList;
            }
        }
        return null;
    }

    @Nullable
    public final List<oq> parseToSoftwareExtraLocalList(@NotNull String primePackageName, short sourceType, @Nullable List<SoftwareExtraRsp> extraRspList) {
        Intrinsics.checkNotNullParameter(primePackageName, "primePackageName");
        if (!sr.a.k(sourceType)) {
            ct.a("parseToSoftwareExtraLocalList isSingle primePackageName:" + primePackageName + " sourceType:" + ((int) sourceType), new Object[0]);
            return null;
        }
        if (extraRspList == null || extraRspList.isEmpty()) {
            ct.b("parseToSoftwareExtraLocalList isMulti but no extras primePackageName:" + primePackageName + " sourceType:" + ((int) sourceType), new Object[0]);
            return null;
        }
        ct.a("parseToSoftwareExtraLocalList isMulti and extras primePackageName:" + primePackageName + " sourceType:" + ((int) sourceType), new Object[0]);
        ArrayList arrayList = new ArrayList(CollectionsKt.b(extraRspList));
        for (SoftwareExtraRsp softwareExtraRsp : extraRspList) {
            String package_name = softwareExtraRsp.getPackage_name();
            String str = package_name == null ? "" : package_name;
            String signature = softwareExtraRsp.getSignature();
            String str2 = signature == null ? "" : signature;
            Integer ver_code = softwareExtraRsp.getVer_code();
            int iIntValue = ver_code != null ? ver_code.intValue() : 1;
            String version = softwareExtraRsp.getVersion();
            arrayList.add(new oq(str, primePackageName, str2, iIntValue, version == null ? "" : version));
        }
        return arrayList;
    }

    @NotNull
    public final SoftwareExtraRsp parseToSoftwareExtraRsp(@NotNull oq softwareExtraLocal) {
        Intrinsics.checkNotNullParameter(softwareExtraLocal, "softwareExtraLocal");
        return new SoftwareExtraRsp(softwareExtraLocal.a, softwareExtraLocal.c, Integer.valueOf(softwareExtraLocal.d), softwareExtraLocal.e);
    }

    @Nullable
    public final List<SoftwareExtraRsp> parseToSoftwareExtraRspList(@Nullable List<oq> extraList) {
        if (extraList == null || extraList.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.b(extraList));
        Iterator<T> it = extraList.iterator();
        while (it.hasNext()) {
            arrayList.add(parseToSoftwareExtraRsp((oq) it.next()));
        }
        return arrayList;
    }

    @Nullable
    public final SoftwareFileUnzipInfo parseToSoftwareFileUnzipInfo(@NotNull Context context, @NotNull String apkPath, @NotNull String primePackageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(apkPath, "apkPath");
        Intrinsics.checkNotNullParameter(primePackageName, "primePackageName");
        sr srVar = sr.a;
        PackageInfo packageInfoA = srVar.a(context, apkPath);
        if (packageInfoA == null) {
            return null;
        }
        String str = packageInfoA.packageName;
        Intrinsics.checkNotNullExpressionValue(str, "packageInfo.packageName");
        int longVersionCode = (int) packageInfoA.getLongVersionCode();
        String str2 = packageInfoA.versionName;
        Intrinsics.checkNotNullExpressionValue(str2, "packageInfo.versionName");
        SigningInfo signingInfo = packageInfoA.signingInfo;
        Signature[] apkContentsSigners = signingInfo != null ? signingInfo.getApkContentsSigners() : null;
        String str3 = packageInfoA.packageName;
        Intrinsics.checkNotNullExpressionValue(str3, "packageInfo.packageName");
        return new SoftwareFileUnzipInfo(primePackageName, str, apkPath, longVersionCode, str2, srVar.b(apkContentsSigners, str3), null, 64, null);
    }

    @NotNull
    public final SoftwareInterceptorRetData parseToSoftwareInterceptorRetData(@NotNull SoftwareStateData softwareStateData, @NotNull String downloadUrl, long totalSize, int versionCode) {
        Intrinsics.checkNotNullParameter(softwareStateData, "softwareStateData");
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        return new SoftwareInterceptorRetData(softwareStateData, new SoftwareDtFixInfo(downloadUrl, totalSize, versionCode));
    }

    @NotNull
    public final SoftwareIntermediaryRsp parseToSoftwareIntermediaryRsp(@NotNull FeatureTopicSoftwareRsp featureTopicSoftwareRsp) {
        Intrinsics.checkNotNullParameter(featureTopicSoftwareRsp, "featureTopicSoftwareRsp");
        Integer id = featureTopicSoftwareRsp.getId();
        int iIntValue = id != null ? id.intValue() : 0;
        String name = featureTopicSoftwareRsp.getName();
        String str = name == null ? "" : name;
        String icon = featureTopicSoftwareRsp.getIcon();
        String str2 = icon == null ? "" : icon;
        String package_name = featureTopicSoftwareRsp.getPackage_name();
        String str3 = package_name == null ? "" : package_name;
        Integer classification_id = featureTopicSoftwareRsp.getClassification_id();
        int iIntValue2 = classification_id != null ? classification_id.intValue() : 0;
        Long size = featureTopicSoftwareRsp.getSize();
        long jLongValue = size != null ? size.longValue() : 0L;
        String signature = featureTopicSoftwareRsp.getSignature();
        String str4 = signature == null ? "" : signature;
        Integer ver_code = featureTopicSoftwareRsp.getVer_code();
        int iIntValue3 = ver_code != null ? ver_code.intValue() : 0;
        String version = featureTopicSoftwareRsp.getVersion();
        String str5 = version == null ? "" : version;
        String download = featureTopicSoftwareRsp.getDownload();
        String str6 = download == null ? "" : download;
        Short source_type = featureTopicSoftwareRsp.getSource_type();
        return new SoftwareIntermediaryRsp(iIntValue, str, str2, null, str3, iIntValue2, null, jLongValue, 0, null, iIntValue3, str5, null, str6, 0L, 0, 0, str4, source_type != null ? source_type.shortValue() : (short) 1, featureTopicSoftwareRsp.getExtra(), null, 1102400, null);
    }

    @NotNull
    public final SoftwareIntermediaryRsp parseToSoftwareIntermediaryRsp(@NotNull SoftwareDetailRsp softwareDetailRsp) {
        Intrinsics.checkNotNullParameter(softwareDetailRsp, "softwareDetailRsp");
        Integer id = softwareDetailRsp.getId();
        int iIntValue = id != null ? id.intValue() : 0;
        String name = softwareDetailRsp.getName();
        String str = name == null ? "" : name;
        String icon = softwareDetailRsp.getIcon();
        String str2 = icon == null ? "" : icon;
        String package_name = softwareDetailRsp.getPackage_name();
        String str3 = package_name == null ? "" : package_name;
        Integer classification_id = softwareDetailRsp.getClassification_id();
        int iIntValue2 = classification_id != null ? classification_id.intValue() : 0;
        Long size = softwareDetailRsp.getSize();
        long jLongValue = size != null ? size.longValue() : 0L;
        String signature = softwareDetailRsp.getSignature();
        String str4 = signature == null ? "" : signature;
        String introduction = softwareDetailRsp.getIntroduction();
        String str5 = introduction == null ? "" : introduction;
        Long updateTime = softwareDetailRsp.getUpdateTime();
        long jLongValue2 = updateTime != null ? updateTime.longValue() : 0L;
        Integer screen_compatibility = softwareDetailRsp.getScreen_compatibility();
        int iIntValue3 = screen_compatibility != null ? screen_compatibility.intValue() : 3;
        String version = softwareDetailRsp.getVersion();
        if (version == null) {
            version = "";
        }
        Integer ver_code = softwareDetailRsp.getVer_code();
        int iIntValue4 = ver_code != null ? ver_code.intValue() : 0;
        String download = softwareDetailRsp.getDownload();
        String str6 = download == null ? "" : download;
        Short source_type = softwareDetailRsp.getSource_type();
        short sShortValue = source_type != null ? source_type.shortValue() : (short) 1;
        List<SoftwareExtraRsp> extra = softwareDetailRsp.getExtra();
        String version_introduction = softwareDetailRsp.getVersion_introduction();
        return new SoftwareIntermediaryRsp(iIntValue, str, str2, null, str3, iIntValue2, null, jLongValue, 0, null, iIntValue4, version, str5, str6, jLongValue2, iIntValue3, 0, str4, sShortValue, extra, version_introduction == null ? "" : version_introduction, 576, null);
    }

    @NotNull
    public final SoftwareIntermediaryRsp parseToSoftwareIntermediaryRsp(@NotNull SoftwareSearchRsp softwareSearchRsp) {
        Intrinsics.checkNotNullParameter(softwareSearchRsp, "softwareSearchRsp");
        Integer id = softwareSearchRsp.getId();
        int iIntValue = id != null ? id.intValue() : 0;
        String name = softwareSearchRsp.getName();
        String str = name == null ? "" : name;
        String icon = softwareSearchRsp.getIcon();
        String str2 = icon == null ? "" : icon;
        String package_name = softwareSearchRsp.getPackage_name();
        String str3 = package_name == null ? "" : package_name;
        Integer classification_id = softwareSearchRsp.getClassification_id();
        int iIntValue2 = classification_id != null ? classification_id.intValue() : 0;
        Long size = softwareSearchRsp.getSize();
        long jLongValue = size != null ? size.longValue() : 0L;
        String signature = softwareSearchRsp.getSignature();
        String str4 = signature == null ? "" : signature;
        String version = softwareSearchRsp.getVersion();
        String str5 = version == null ? "" : version;
        String introduction = softwareSearchRsp.getIntroduction();
        String str6 = introduction == null ? "" : introduction;
        Integer ver_code = softwareSearchRsp.getVer_code();
        int iIntValue3 = ver_code != null ? ver_code.intValue() : 0;
        String download = softwareSearchRsp.getDownload();
        String str7 = download == null ? "" : download;
        Short source_type = softwareSearchRsp.getSource_type();
        return new SoftwareIntermediaryRsp(iIntValue, str, str2, null, str3, iIntValue2, null, jLongValue, 0, null, iIntValue3, str5, str6, str7, 0L, 0, 0, str4, source_type != null ? source_type.shortValue() : (short) 1, softwareSearchRsp.getExtra(), null, 1098304, null);
    }

    @NotNull
    public final SoftwareIntermediaryRsp parseToSoftwareIntermediaryRsp(@NotNull SoftwareThemRsp softwareThemRsp) {
        Intrinsics.checkNotNullParameter(softwareThemRsp, "softwareThemRsp");
        Integer id = softwareThemRsp.getId();
        int iIntValue = id != null ? id.intValue() : 0;
        String name = softwareThemRsp.getName();
        String str = name == null ? "" : name;
        String icon = softwareThemRsp.getIcon();
        String str2 = icon == null ? "" : icon;
        String package_name = softwareThemRsp.getPackage_name();
        String str3 = package_name == null ? "" : package_name;
        Integer classification_id = softwareThemRsp.getClassification_id();
        int iIntValue2 = classification_id != null ? classification_id.intValue() : 0;
        Long size = softwareThemRsp.getSize();
        long jLongValue = size != null ? size.longValue() : 0L;
        String signature = softwareThemRsp.getSignature();
        String str4 = signature == null ? "" : signature;
        Integer ver_code = softwareThemRsp.getVer_code();
        int iIntValue3 = ver_code != null ? ver_code.intValue() : 0;
        String version = softwareThemRsp.getVersion();
        String str5 = version == null ? "" : version;
        String download = softwareThemRsp.getDownload();
        String str6 = download == null ? "" : download;
        Short source_type = softwareThemRsp.getSource_type();
        return new SoftwareIntermediaryRsp(iIntValue, str, str2, null, str3, iIntValue2, null, jLongValue, 0, null, iIntValue3, str5, null, str6, 0L, 0, 0, str4, source_type != null ? source_type.shortValue() : (short) 1, softwareThemRsp.getExtra(), null, 1102400, null);
    }

    @NotNull
    public final SoftwareIntermediaryRsp parseToSoftwareIntermediaryRsp(@NotNull SoftwareUpdateRsp softwareUpdateRsp) {
        Intrinsics.checkNotNullParameter(softwareUpdateRsp, "softwareUpdateRsp");
        Integer id = softwareUpdateRsp.getId();
        int iIntValue = id != null ? id.intValue() : 0;
        String name = softwareUpdateRsp.getName();
        String str = name == null ? "" : name;
        String icon = softwareUpdateRsp.getIcon();
        String str2 = icon == null ? "" : icon;
        String package_name = softwareUpdateRsp.getPackage_name();
        String str3 = package_name == null ? "" : package_name;
        Integer classification_id = softwareUpdateRsp.getClassification_id();
        int iIntValue2 = classification_id != null ? classification_id.intValue() : 0;
        Long size = softwareUpdateRsp.getSize();
        long jLongValue = size != null ? size.longValue() : 0L;
        String signature = softwareUpdateRsp.getSignature();
        String str4 = signature == null ? "" : signature;
        Integer ver_code = softwareUpdateRsp.getVer_code();
        int iIntValue3 = ver_code != null ? ver_code.intValue() : 0;
        String download = softwareUpdateRsp.getDownload();
        String str5 = download == null ? "" : download;
        Short source_type = softwareUpdateRsp.getSource_type();
        return new SoftwareIntermediaryRsp(iIntValue, str, str2, null, str3, iIntValue2, null, jLongValue, 0, null, iIntValue3, null, null, str5, 0L, 0, 0, str4, source_type != null ? source_type.shortValue() : (short) 1, softwareUpdateRsp.getExtra(), null, 1104448, null);
    }

    @NotNull
    public final SoftwareIntermediaryRsp parseToSoftwareIntermediaryRsp(@NotNull qq softwareLocalWrap, @NotNull SoftwareDeviceInstalledRsp softwareDeviceInstalledRsp) {
        Intrinsics.checkNotNullParameter(softwareLocalWrap, "softwareLocalWrap");
        Intrinsics.checkNotNullParameter(softwareDeviceInstalledRsp, "softwareDeviceInstalledRsp");
        pq pqVar = softwareLocalWrap.a;
        int i = pqVar.b;
        String str = pqVar.c;
        String str2 = pqVar.g;
        Drawable icon = softwareDeviceInstalledRsp.getIcon();
        pq pqVar2 = softwareLocalWrap.a;
        String str3 = pqVar2.a;
        int i2 = pqVar2.h;
        String str4 = pqVar2.i;
        long j = pqVar2.e;
        String strA = o5.a.a(Long.valueOf(j));
        pq pqVar3 = softwareLocalWrap.a;
        String str5 = pqVar3.f;
        String str6 = pqVar3.l;
        return new SoftwareIntermediaryRsp(i, str, str2, icon, str3, i2, str4, j, 0, strA, pqVar3.j, softwareDeviceInstalledRsp.getVersionTip(), null, str5, 0L, 0, pqVar3.k, str6, softwareLocalWrap.a.m, parseToSoftwareExtraRspList(softwareLocalWrap.b), null, 1101824, null);
    }

    @NotNull
    public final pq parseToSoftwareLocal(@NotNull SoftwareOptParam softwareOptParam) {
        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
        int id = softwareOptParam.getId();
        String name = softwareOptParam.getName();
        String icon = softwareOptParam.getIcon();
        String packageName = softwareOptParam.getPackageName();
        String path = softwareOptParam.getPath();
        long totalSize = softwareOptParam.getTotalSize();
        int state = softwareOptParam.getState();
        String downloadUrl = softwareOptParam.getDownloadUrl();
        String classifyName = softwareOptParam.getClassifyName();
        return new pq(packageName, id, name, path, totalSize, downloadUrl, icon, softwareOptParam.getClassifyId(), classifyName, softwareOptParam.getVersionCode(), state, softwareOptParam.getSignature(), softwareOptParam.getSourceType());
    }

    @NotNull
    public final pq parseToSoftwareLocal(@NotNull SoftwareOptParam softwareOptParam, int state) {
        Intrinsics.checkNotNullParameter(softwareOptParam, "softwareOptParam");
        int id = softwareOptParam.getId();
        String name = softwareOptParam.getName();
        String icon = softwareOptParam.getIcon();
        String packageName = softwareOptParam.getPackageName();
        String path = softwareOptParam.getPath();
        long totalSize = softwareOptParam.getTotalSize();
        String downloadUrl = softwareOptParam.getDownloadUrl();
        String classifyName = softwareOptParam.getClassifyName();
        return new pq(packageName, id, name, path, totalSize, downloadUrl, icon, softwareOptParam.getClassifyId(), classifyName, softwareOptParam.getVersionCode(), state, softwareOptParam.getSignature(), softwareOptParam.getSourceType());
    }

    @NotNull
    public final SoftwareOptParam parseToSoftwareOptParam(@NotNull Software software) {
        Intrinsics.checkNotNullParameter(software, "software");
        return new SoftwareOptParam(software.getId(), software.getPackageName(), software.getName(), software.getIcon(), software.getDtFixInfo().getDownloadUrl(), software.getDtFixInfo().getTotalSize(), software.getDtFixInfo().getVersionCode(), software.getClassifyName(), software.getClassifyId(), software.getSignature(), 0, "", software.getSourceType(), software.getExtra());
    }

    @NotNull
    public final SoftwareOptParam parseToSoftwareOptParam(@NotNull qq softwareLocalWrap) {
        Intrinsics.checkNotNullParameter(softwareLocalWrap, "softwareLocalWrap");
        pq pqVar = softwareLocalWrap.a;
        List<SoftwareExtParam> toSoftwareExtParamList = parseToSoftwareExtParamList(softwareLocalWrap.b);
        return new SoftwareOptParam(pqVar.b, pqVar.a, pqVar.c, pqVar.g, pqVar.f, pqVar.e, pqVar.j, pqVar.i, pqVar.h, pqVar.l, pqVar.k, pqVar.d, pqVar.m, toSoftwareExtParamList);
    }

    @NotNull
    public final List<SoftwareOptParam> parseToSoftwareOptParam(@NotNull List<SoftwareOptParam> prevList, @NotNull List<? extends DownloadUrlInfo> refreshList) {
        Object next;
        Intrinsics.checkNotNullParameter(prevList, "prevList");
        Intrinsics.checkNotNullParameter(refreshList, "refreshList");
        if (prevList.size() != refreshList.size()) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.b(prevList));
        for (SoftwareOptParam softwareOptParam : prevList) {
            Iterator<T> it = refreshList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (Intrinsics.areEqual(((DownloadUrlInfo) next).getPackageName(), softwareOptParam.getPackageName())) {
                    break;
                }
            }
            DownloadUrlInfo downloadUrlInfo = (DownloadUrlInfo) next;
            if (downloadUrlInfo == null) {
                ct.b("parseToSoftwareOptParam refresh not findRefresh", new Object[0]);
            }
            arrayList.add(parseToSoftwareOptParam(softwareOptParam, downloadUrlInfo));
        }
        return arrayList;
    }

    @NotNull
    public final List<SoftwareOptParam> parseToSoftwareOptParamList(@Nullable List<qq> softwareLocalWrapList) {
        if (softwareLocalWrapList == null || softwareLocalWrapList.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.b(softwareLocalWrapList));
        Iterator<T> it = softwareLocalWrapList.iterator();
        while (it.hasNext()) {
            arrayList.add(parseToSoftwareOptParam((qq) it.next()));
        }
        return arrayList;
    }

    @NotNull
    public final List<SoftwareOptParam> parseToSoftwareOptParamList(@NotNull List<SoftwareOptParam> softwareOptParamList, @Nullable List<qq> softwareLocalWrapList) {
        Object objValueOf;
        Intrinsics.checkNotNullParameter(softwareOptParamList, "softwareOptParamList");
        LinkedList linkedList = new LinkedList();
        if (softwareLocalWrapList != null) {
            Iterator<T> it = softwareLocalWrapList.iterator();
            while (it.hasNext()) {
                linkedList.add(parseToSoftwareOptParam((qq) it.next()));
            }
        }
        Iterator<T> it2 = softwareOptParamList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            SoftwareOptParam softwareOptParam = (SoftwareOptParam) it2.next();
            Iterator it3 = linkedList.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                Object next = it3.next();
                if (Intrinsics.areEqual(softwareOptParam.getPackageName(), ((SoftwareOptParam) next).getPackageName())) {
                    objValueOf = next;
                    break;
                }
            }
            if (((SoftwareOptParam) objValueOf) == null) {
                linkedList.add(softwareOptParam);
            }
        }
        int size = softwareOptParamList.size();
        objValueOf = softwareLocalWrapList != null ? Integer.valueOf(softwareLocalWrapList.size()) : null;
        ct.a("parseToSoftwareOptParamList softwareOptParamList:" + size + " softwareLocalWrapList:" + objValueOf + " totalList:" + linkedList.size(), new Object[0]);
        return linkedList;
    }

    @NotNull
    public final SoftwareOptParamWrap parseToSoftwareOptParamWrap(@NotNull SoftwareOptParam optParam, @Nullable Pair<SoftwareStateData, SoftwareStateData> pair) {
        Intrinsics.checkNotNullParameter(optParam, "optParam");
        return new SoftwareOptParamWrap(optParam, pair);
    }

    @NotNull
    public final Uri parseToUri(@NotNull String scheme, @NotNull String host, @NotNull Map<String, String> mapParam) {
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(mapParam, "mapParam");
        Uri.Builder builderAuthority = new Uri.Builder().scheme(scheme).authority(host);
        for (Map.Entry<String, String> entry : mapParam.entrySet()) {
            builderAuthority.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        Uri uriBuild = builderAuthority.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "builder.build()");
        return uriBuild;
    }

    @NotNull
    public final String parseToUserActInState(boolean permissionAllGranted, boolean protocolAgree) {
        return (permissionAllGranted && protocolAgree) ? "YY" : (!permissionAllGranted || protocolAgree) ? (permissionAllGranted || !protocolAgree) ? "NN" : "NY" : "YN";
    }

    @NotNull
    public final mv parseToWorkCarrier(@NotNull SoftwareOptParam optParam) {
        Intrinsics.checkNotNullParameter(optParam, "optParam");
        mv mvVar = new mv();
        mvVar.setOptParam(optParam);
        return mvVar;
    }

    @NotNull
    public final mv parseToWorkCarrier(@NotNull List<SoftwareOptParam> optParamList) {
        Intrinsics.checkNotNullParameter(optParamList, "optParamList");
        mv mvVar = new mv();
        mvVar.setOptParamList(optParamList);
        return mvVar;
    }
}
