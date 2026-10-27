package com.neusoft.na.byd;

import android.app.UiStyleConfig;
import android.car.AoapService;
import android.car.Car;
import android.car.media.CarEnhancedAudioManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.panorama.AbsBYDAutoPanoramaListener;
import android.hardware.bydauto.panorama.BYDAutoPanoramaDevice;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.hardware.bydauto.whud.BYDAutoWhudDevice;
import android.hardware.bydauto.yun.BYDAutoYunDevice;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import api.notifications.com.neusoft.byd.bydnavapi.BydNavApiNotifications;
import api.notifications.com.neusoft.byd.bydnavapi.NotifyAuthInfo;
import api.notifications.com.neusoft.byd.bydnavapi.NotifyClusterViewMode;
import api.notifications.com.neusoft.byd.bydnavapi.NotifyDelPrivacyPolicyInfo;
import api.notifications.com.neusoft.byd.bydnavapi.NotifyGuidanceInfoUpdate;
import api.notifications.com.neusoft.byd.bydnavapi.NotifyGuidanceStatus;
import api.notifications.com.neusoft.byd.bydnavapi.NotifyIsNaviHMIFullScreenPage;
import api.notifications.com.neusoft.byd.bydnavapi.NotifyReqGPSInfo;
import api.notifications.com.neusoft.byd.bydnavapi.NotifyRevokePrivacyPolicy;
import api.notifications.com.neusoft.byd.bydnavapi.NotifySwitchDesktopMode;
import api.notifications.com.neusoft.byd.bydnavapi.NotifyUpdatePrivacyPolicySyncStatus;
import api.notifications.com.neusoft.byd.bydnavapi.NotifyVerifyInfo;
import api.notifications.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseNotifications;
import api.notifications.com.neusoft.byd.bydsysapiinverse.NotifyNaviMainMapDayNightStatusChange;
import api.notifications.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseNotifications;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyCallUpStatus;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyDelegateSystemEvent;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyExtractFiles;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyGetLowFuelStatus;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyImmersionStatus;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyLoadURL;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyNaviStatusChanged;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyReadFile;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyRequestDeviceId;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyResponseExpiredDate;
import api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests;
import api.requests.com.neusoft.byd.bydnavapi.ReqClusterViewModeChanged;
import api.requests.com.neusoft.byd.bydnavapi.ReqOnAuthInfo;
import api.requests.com.neusoft.byd.bydnavapi.ReqOnVerifyInfo;
import api.requests.com.neusoft.byd.bydnavapi.ReqPowerSavingModeChanged;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqSmartNaviStart;
import api.requests.com.neusoft.byd.bydnavapi.ReqRequestGoHomeOrCompany;
import api.requests.com.neusoft.byd.bydnavapi.ReqSetClusterMapFocus;
import api.requests.com.neusoft.byd.bydnavapi.ReqSetDesktopMode;
import api.requests.com.neusoft.byd.bydnavapi.ReqSetMobileEquipmentID;
import api.requests.com.neusoft.byd.bydnavapi.ReqSetPlatformVersion;
import api.requests.com.neusoft.byd.bydnavapi.ReqSetPrivacyPolicyFullPath;
import api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests;
import api.requests.com.neusoft.byd.bydsysapiinverse.ReqOnDayNightChanged;
import api.requests.com.neusoft.byd.bydsysapiinverse.ReqSkipFunction;
import api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqAppFocusEventInfo;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqDashboardMirrorEventInfo;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqFeedbackActivateResult;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqHMIFocusChanged;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqMigrateNaviHMIPage;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnLoadURL;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnReadFile;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqReqNaviGuideStop;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetBatteryCapacityWH;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetDeviceId;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetEnduranceMileage;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetFuelType;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetLowBatterySignal;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetLowFuelSignal;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetNaviMuteModeComm;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetRemainingElectricQuantity;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetRemainingElectricQuantityKWH;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetScreenDirection;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetUnitOfMeasurement;
import api.structs.com.neusoft.byd.AuthConditionInfo;
import api.structs.com.neusoft.byd.ClusterMapFocusMode;
import api.structs.com.neusoft.byd.ClusterViewMode;
import api.structs.com.neusoft.byd.DayNightStatus;
import api.structs.com.neusoft.byd.DesktopMode;
import api.structs.com.neusoft.byd.FavoriteType;
import api.structs.com.neusoft.byd.FileFullPath;
import api.structs.com.neusoft.byd.FunctionTypes;
import api.structs.com.neusoft.byd.GuidedStatus;
import api.structs.com.neusoft.byd.NaviHMIPageStatus;
import api.structs.com.neusoft.byd.PlaceInfo;
import api.structs.com.neusoft.byd.PowerSavingModeStatus;
import api.structs.com.neusoft.byd.TopTipUnit;
import api.structs.com.neusoft.byd.VerifyConditionInfo;
import api.structs.com.neusoft.common.ActivateResultDetails;
import api.structs.com.neusoft.common.ActivateStatus;
import api.structs.com.neusoft.common.DashboardMirrorInfo;
import api.structs.com.neusoft.common.DashboardType;
import api.structs.com.neusoft.common.DeviceType;
import api.structs.com.neusoft.common.FocusStatus;
import api.structs.com.neusoft.common.FuelType;
import api.structs.com.neusoft.common.LengthUnit;
import api.structs.com.neusoft.common.MessageType;
import api.structs.com.neusoft.common.MuteTypeComm;
import api.structs.com.neusoft.common.ReadFileStatus;
import api.structs.com.neusoft.common.ResultStatus;
import api.structs.com.neusoft.common.ScreenDirectionStatus;
import api.structs.com.neusoft.greatwall.GwCurrentWTInfoState;
import api.structs.com.neusoft.greatwall.SystemSwitchStatus;
import cerence.ark.assistant.controller.domain.ude.UDERequestsEnum;
import com.byd.bydnaviapi.AbsBYDAutoNaviListener;
import com.byd.bydnaviapi.BydAutoNaviDevice;
import com.bydux.api.common.tools.util.ListUtils;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.neusoft.de.nts.OneCoreTest.communication.transports.websocketclient.OneCoreClientEndpoint;
import com.neusoft.de.nts.OneCoreTest.communication.utils.OneCoreWSContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.IGettableAttribute;
import com.neusoft.na.byd.tts.TTSPlayer;
import com.neusoft.na.byd.util.BydUtils;
import com.neusoft.na.byd.util.ChangeHistoryUtil;
import com.neusoft.na.common.BydSystemAdapterCallback;
import com.neusoft.na.common.ClientEndpointManger;
import com.neusoft.na.common.ClientEndpointMangerCallback;
import com.neusoft.na.common.EncryptAlgorithm;
import com.neusoft.na.common.FileUtils;
import com.neusoft.na.common.IBydSystemAdapterComponent;
import com.neusoft.na.common.IExtensionComponent;
import com.neusoft.na.common.ILanOnOffListener;
import com.neusoft.na.common.IOptionalComponent;
import com.neusoft.na.common.ITTSListener;
import com.neusoft.na.common.ITimeFormatListener;
import com.neusoft.na.common.NaviStatus;
import com.neusoft.na.common.ServiceBase;
import com.neusoft.na.common.presentation.IMapPresentationComponent;
import com.neusoft.na.common.util.SystemUtils;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import com.neusoft.na.ocservice.OneCoreService;
import com.neusoft.permission.check.PermissionChecker;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import mu.KLogging;
import org.bouncycastle.i18n.ErrorBundle;
import org.bouncycastle.i18n.TextBundle;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import vendor.bosch.tbox.V1_0.ITbox;

/* JADX INFO: compiled from: Byd8155fSystem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009d\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e*\u00014\b\u0007\u0018\u0000 ¨\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0002¨\u0001B\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020JH\u0002J\u0010\u0010K\u001a\u00020H2\u0006\u0010L\u001a\u00020.H\u0002J\b\u0010M\u001a\u00020HH\u0016J\b\u0010N\u001a\u00020,H\u0016J\n\u0010O\u001a\u0004\u0018\u00010.H\u0002J\u0010\u0010P\u001a\u00020,2\u0006\u0010Q\u001a\u00020RH\u0002J\b\u0010S\u001a\u00020,H\u0002J\u001a\u0010T\u001a\u00020.2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010U\u001a\u0004\u0018\u00010.H\u0002J\u0010\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020.H\u0002J\b\u0010Y\u001a\u00020,H\u0016J\b\u0010Z\u001a\u00020,H\u0016J\b\u0010[\u001a\u00020HH\u0002J\b\u0010\\\u001a\u00020HH\u0002J\u0012\u0010]\u001a\u00020H2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010^\u001a\u00020&2\u0006\u0010_\u001a\u00020.H\u0002J\b\u0010`\u001a\u00020&H\u0016J\u0010\u0010a\u001a\u00020H2\u0006\u0010b\u001a\u00020&H\u0002J\u001a\u0010c\u001a\u00020H2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010d\u001a\u0004\u0018\u00010eH\u0016J\u0010\u0010f\u001a\u00020H2\u0006\u0010\u0006\u001a\u00020gH\u0017J\b\u0010h\u001a\u00020HH\u0016J\u0010\u0010i\u001a\u00020H2\u0006\u0010j\u001a\u00020kH\u0002J\u0012\u0010l\u001a\u00020H2\b\u0010m\u001a\u0004\u0018\u00010.H\u0016J\b\u0010n\u001a\u00020HH\u0002J\u0010\u0010o\u001a\u00020H2\u0006\u0010b\u001a\u00020&H\u0002J\u0010\u0010p\u001a\u00020H2\u0006\u0010q\u001a\u00020,H\u0002J\u0018\u0010r\u001a\u00020H2\u0006\u0010s\u001a\u00020,2\u0006\u0010t\u001a\u00020,H\u0002J\u0018\u0010u\u001a\u00020H2\u0006\u0010v\u001a\u00020&2\u0006\u0010w\u001a\u00020&H\u0002J\b\u0010x\u001a\u00020HH\u0002J\u0010\u0010y\u001a\u00020H2\u0006\u0010z\u001a\u00020&H\u0002J\u0010\u0010{\u001a\u00020H2\u0006\u0010|\u001a\u00020,H\u0002J\u0010\u0010}\u001a\u00020H2\u0006\u0010b\u001a\u00020&H\u0002J\u0018\u0010~\u001a\u00020H2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u007f\u001a\u00020,H\u0002J\u0011\u0010\u0080\u0001\u001a\u00020H2\u0006\u0010|\u001a\u00020,H\u0002J\u0012\u0010\u0081\u0001\u001a\u00020H2\u0007\u0010\u0082\u0001\u001a\u00020.H\u0002J\t\u0010\u0083\u0001\u001a\u00020HH\u0002J\u0011\u0010\u0084\u0001\u001a\u00020H2\u0006\u0010|\u001a\u00020,H\u0002J\u0011\u0010\u0085\u0001\u001a\u00020H2\u0006\u0010|\u001a\u00020,H\u0002J\u0011\u0010\u0086\u0001\u001a\u00020H2\u0006\u0010|\u001a\u00020&H\u0002J\u0012\u0010\u0087\u0001\u001a\u00020H2\u0007\u0010\u0088\u0001\u001a\u000202H\u0016J\u0011\u0010\u0089\u0001\u001a\u00020H2\u0006\u0010|\u001a\u00020,H\u0002J\u0011\u0010\u008a\u0001\u001a\u00020H2\u0006\u0010|\u001a\u00020,H\u0002J\u0011\u0010\u008b\u0001\u001a\u00020H2\u0006\u0010q\u001a\u00020,H\u0002J\u0012\u0010\u008c\u0001\u001a\u00020H2\u0007\u0010\u008d\u0001\u001a\u00020,H\u0002J\u001a\u0010\u008e\u0001\u001a\u00020H2\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010&H\u0002¢\u0006\u0003\u0010\u0090\u0001J\u0012\u0010\u0091\u0001\u001a\u00020H2\u0007\u0010\u0092\u0001\u001a\u00020,H\u0016J\u0012\u0010\u0093\u0001\u001a\u00020H2\u0007\u0010|\u001a\u00030\u0094\u0001H\u0002J\u0012\u0010\u0095\u0001\u001a\u00020H2\u0007\u0010|\u001a\u00030\u0096\u0001H\u0002J\u0013\u0010\u0097\u0001\u001a\u00020H2\b\u0010\u0088\u0001\u001a\u00030\u0098\u0001H\u0016J\u0015\u0010\u0099\u0001\u001a\u00020H2\n\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u009b\u0001H\u0016J\u0012\u0010\u0099\u0001\u001a\u00020H2\u0007\u0010\u0088\u0001\u001a\u00020CH\u0016J\u0012\u0010\u009c\u0001\u001a\u00020H2\u0007\u0010\u009d\u0001\u001a\u00020.H\u0016J\t\u0010\u009e\u0001\u001a\u00020HH\u0016J\t\u0010\u009f\u0001\u001a\u00020HH\u0016J\t\u0010 \u0001\u001a\u00020HH\u0016J\t\u0010¡\u0001\u001a\u00020HH\u0016J\t\u0010¢\u0001\u001a\u00020HH\u0016J\t\u0010£\u0001\u001a\u00020HH\u0003J\u0014\u0010¤\u0001\u001a\u00020H2\t\u0010¥\u0001\u001a\u0004\u0018\u00010.H\u0016J\t\u0010¦\u0001\u001a\u00020HH\u0002J\u0011\u0010§\u0001\u001a\u00020H2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0004\n\u0002\u00105R\u000e\u00106\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010<\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030>0=j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030>`?X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010@\u001a\u0012\u0012\u0004\u0012\u00020A0=j\b\u0012\u0004\u0012\u00020A`?X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010B\u001a\u0004\u0018\u00010CX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006©\u0001"}, d2 = {"Lcom/neusoft/na/byd/Byd8155fSystem;", "Lcom/neusoft/na/common/IExtensionComponent;", "Lcom/neusoft/na/common/IOptionalComponent;", "Lcom/neusoft/na/common/IBydSystemAdapterComponent;", "Lcom/neusoft/na/common/ClientEndpointMangerCallback;", "Landroid/content/BroadcastReceiver;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "autoPanoramaDevice", "Landroid/hardware/bydauto/panorama/BYDAutoPanoramaDevice;", "bydAutoBodyworkDevice", "Landroid/hardware/bydauto/bodywork/BYDAutoBodyworkDevice;", "bydAutoInstrumentDevice", "Landroid/hardware/bydauto/instrument/BYDAutoInstrumentDevice;", "bydAutoNaviDevice", "Lcom/byd/bydnaviapi/BydAutoNaviDevice;", "bydAutoStatisticDevice", "Landroid/hardware/bydauto/statistic/BYDAutoStatisticDevice;", "bydAutoTestDevice", "Landroid/hardware/bydauto/test/BYDAutoTestDevice;", "bydAutoWhudDevice", "Landroid/hardware/bydauto/whud/BYDAutoWhudDevice;", "bydAutoYunDevice", "Landroid/hardware/bydauto/yun/BYDAutoYunDevice;", "bydNavApiNotifications", "Lapi/notifications/com/neusoft/byd/bydnavapi/BydNavApiNotifications;", "bydSysApiInverseNotifications", "Lapi/notifications/com/neusoft/byd/bydsysapiinverse/BydSysApiInverseNotifications;", "bydSysApiInverseRequests", "Lapi/requests/com/neusoft/byd/bydsysapiinverse/BydSysApiInverseRequests;", "bydSysApiRequests", "Lapi/requests/com/neusoft/byd/bydnavapi/BydNavApiRequests;", "cEManger", "Lcom/neusoft/na/common/ClientEndpointManger;", "carEnhancedAudioManager", "Landroid/car/media/CarEnhancedAudioManager;", "clientEndpointMangerInit", "", "commonNotifications", "Lapi/notifications/com/neusoft/common/commonsysapiinverse/CommonSysApiInverseNotifications;", "commonRequests", "Lapi/requests/com/neusoft/common/commonsysapiinverse/CommonSysApiInverseRequests;", "currentINSNavDisplayMode", "", "imei", "", "isPause", "isSendToWidget", "lanOnOffListener", "Lcom/neusoft/na/common/ILanOnOffListener;", "mScheduledHandler", "com/neusoft/na/byd/Byd8155fSystem$mScheduledHandler$1", "Lcom/neusoft/na/byd/Byd8155fSystem$mScheduledHandler$1;", "naviForegroundState", "naviGuideState", "nowWeatherInfo", "Lapi/structs/com/neusoft/greatwall/GwCurrentWTInfoState;", "openFloatCard", "presentationMapType", "remoteAttributes", "Ljava/util/ArrayList;", "Lcom/neusoft/de/nts/OneCoreTest/interfaces/IGettableAttribute;", "Lkotlin/collections/ArrayList;", "remoteSubscriptions", "Lrx/Subscription;", "ttsListener", "Lcom/neusoft/na/common/ITTSListener;", "ttsPlayer", "Lcom/neusoft/na/byd/tts/TTSPlayer;", "usageGroupId", "activation", "", ErrorBundle.DETAIL_ENTRY, "Lapi/structs/com/neusoft/common/ActivateResultDetails;", "callPhone", "phoneNum", "destroyTts", "getCarType", "getCurrentLauncherPackageName", "getPresentationTbtUnit", "unit", "Lapi/structs/com/neusoft/byd/TopTipUnit;", "getSteeringWheelPosition", "getSystemProperties", "key", "getSystemSwitchStatus", "Lapi/structs/com/neusoft/greatwall/SystemSwitchStatus;", "name", "getTtsAudioUsage", "getUiStyle", "initBYDAutoDevice", "initClientEndpointManger", "initTTS", "isBrowserInstalled", ImagesContract.URL, "isRightRudderMode", "onGuideChanged", "flag", "onReceive", "intent", "Landroid/content/Intent;", "onServerConnect", "Lcom/neusoft/de/nts/OneCoreTest/communication/utils/OneCoreWSContextUtil;", "onServerDisconnect", UDERequestsEnum.OPEN_MAP, "uri", "Landroid/net/Uri;", "playTts", TextBundle.TEXT_ENTRY, "presentationMapTypeChanged", "sendFloatCardStatus", "sendFocusDashboardMirrorEventInfo", "mapType", "sendINSNavDisplayModeDashboardMirrorEventInfo", "enterValue", "exitValue", "sendMapActivateAndPrivacyPolicyToByd", "activationStatus", "privacyPolicyStatus", "sendNaviState", "sendNaviStatusBroadcast", "isStart", "sendOrientation", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "sendResult", "sendToBl", "type", "setBatteryCapacityWH2BL", "setDayNightMode2BL", "it", "setDesktopModeToBl", "setEnduranceMileage2BL", "setFuelType2BL", "setHmiFocusChanged", "setLanguageOnOffCallback", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setLowBatterySignal2BL", "setLowFuelSignal2BL", "setMapFocusChanged2BL", "setMileageUnit2BL", "state", "setNaviMuteState2BL", "isVolumeGroupMuted", "(Ljava/lang/Boolean;)V", "setOverseasMapSupplier", "code", "setRemainingElectricQuantity2BL", "", "setRemainingElectricQuantityKWH2BL", "", "setTimeFormatCallback", "Lcom/neusoft/na/common/ITimeFormatListener;", "setTtsCallback", "callback", "Lcom/neusoft/na/common/BydSystemAdapterCallback;", "speak", NotificationCompat.CATEGORY_MESSAGE, "start", "startListenCarSpeedChange", "stop", "stopTTS", "stopTts", "switchDayNightMode", "switchTtsLanguage", "voiceName", "unregisterObserver", "updatePrivacyUrl", "Companion", "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class Byd8155fSystem extends BroadcastReceiver implements IExtensionComponent, IOptionalComponent, IBydSystemAdapterComponent, ClientEndpointMangerCallback {
    public static final String ACTION_GPS = "com.neusoft.na.navigation.action.ignore";
    public static final String ACTION_HMI_FOCUS_CHANGED = "com.byd.action.gesture.dialog.event";
    private static final String ACTION_HMI_PAGE_FULL_CHANGED = "com.neusoft.na.navigation.hmi.page.full.changed";
    public static final String ACTION_NAVI_FOREGROUND_CHANGED = "com.neusoft.na.navigation.foreground.changed";
    public static final String ACTION_PAUSE = "com.neusoft.na.navigation.action.pause";
    public static final String ACTION_PERMISSION_ALL_SUCCESS = "com.neusoft.na.navigation.action.permission.all.success";
    public static final String ACTION_PRESENTATION_MAP_FOCUS_CHANGED = "com.neusoft.na.navigation.presentation.mapFocusChanged";
    public static final String ACTION_PRESENTATION_MODE_SWITCH = "com.neusoft.na.navigation.presentation.mode.switch";
    public static final String ACTION_RESUME = "com.neusoft.na.navigation.action.resume";
    private static final String ACTION_SET_DEFAULT_LAUNCHER = "com.neusoft.na.navigation.setDefaultLauncher";
    public static final String ACTION_STATE_CHANGED = "byd.intent.action.NAVIGATION_STATE_CHANGED";
    private static final String ACTION_SYSTEM_HOME_KEY = "homekey";
    private static final String ACTION_SYSTEM_REASON = "reason";
    public static final String ACTION_WIDGET_INFO = "com.neusoft.na.navigation.action.widget.info.update";
    public static final String ACTION_WIDGET_RESET = "com.neusoft.na.navigation.action.widget.reset";
    public static final String ACTION_WIDGET_SCREEN_CHANGED = "com.neusoft.na.navigation.action.widget.SCREEN.CHANGED";
    public static final String ACTION_WIDGET_TYPE = "com.neusoft.na.navigation.action.widget.TYPE";
    public static final String ACTION_WIDGET_UUID = "com.neusoft.na.navigation.action.widget.uuid.update";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "Byd8155fSystem";
    public static final int WHAT = 10086;
    public static final String WIDGET_BUNDLE_KEY = "BUNDLE_KEY";
    public static final String WIDGET_CAR_TYPE_KEY = "CAR_TYPE_KEY";
    public static final String WIDGET_TYPE_KEY = "TYPE";
    public static final String WIDGET_UUID_KEY = "uuid";
    private BYDAutoPanoramaDevice autoPanoramaDevice;
    private BYDAutoBodyworkDevice bydAutoBodyworkDevice;
    private BYDAutoInstrumentDevice bydAutoInstrumentDevice;
    private BydAutoNaviDevice bydAutoNaviDevice;
    private BYDAutoStatisticDevice bydAutoStatisticDevice;
    private BYDAutoTestDevice bydAutoTestDevice;
    private BYDAutoWhudDevice bydAutoWhudDevice;
    private BYDAutoYunDevice bydAutoYunDevice;
    private BydNavApiNotifications bydNavApiNotifications;
    private BydSysApiInverseNotifications bydSysApiInverseNotifications;
    private BydSysApiInverseRequests bydSysApiInverseRequests;
    private BydNavApiRequests bydSysApiRequests;
    private final ClientEndpointManger cEManger;
    private CarEnhancedAudioManager carEnhancedAudioManager;
    private boolean clientEndpointMangerInit;
    private CommonSysApiInverseNotifications commonNotifications;
    private CommonSysApiInverseRequests commonRequests;
    private final Context context;
    private int currentINSNavDisplayMode;
    private String imei;
    private boolean isPause;
    private boolean isSendToWidget;
    private ILanOnOffListener lanOnOffListener;
    private final Byd8155fSystem$mScheduledHandler$1 mScheduledHandler;
    private boolean naviForegroundState;
    private boolean naviGuideState;
    private GwCurrentWTInfoState nowWeatherInfo;
    private boolean openFloatCard;
    private int presentationMapType;
    private final ArrayList<IGettableAttribute<?>> remoteAttributes;
    private final ArrayList<Subscription> remoteSubscriptions;
    private ITTSListener ttsListener;
    private TTSPlayer ttsPlayer;
    private int usageGroupId;

    /* JADX INFO: compiled from: Byd8155fSystem.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TopTipUnit.values().length];
            try {
                iArr[TopTipUnit.UNIT_KM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TopTipUnit.UNIT_MILES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TopTipUnit.UNIT_M.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TopTipUnit.UNIT_YARDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[TopTipUnit.UNIT_FT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callPhone(String phoneNum) {
    }

    private final void unregisterObserver() {
    }

    @Override // com.neusoft.na.common.IExtensionComponent
    public void speak(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
    }

    @Override // com.neusoft.na.common.IExtensionComponent
    public void stopTTS() {
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.neusoft.na.byd.Byd8155fSystem$mScheduledHandler$1] */
    public Byd8155fSystem(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.remoteAttributes = new ArrayList<>();
        this.remoteSubscriptions = new ArrayList<>();
        this.presentationMapType = 1;
        this.usageGroupId = -1;
        this.cEManger = ClientEndpointManger.INSTANCE.getInstance(context);
        this.nowWeatherInfo = new GwCurrentWTInfoState();
        this.imei = "";
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.neusoft.byd.evtool");
        intentFilter.addAction("com.neusoft.na.navigation.daynight.switch");
        intentFilter.addAction("android.broadcast.result.virtual_vin_action");
        intentFilter.addAction("com.neusoft.na.navigation.multiWindowMode");
        intentFilter.addAction("com.neusoft.na.navigation.action.openmap");
        intentFilter.addAction(ACTION_WIDGET_TYPE);
        intentFilter.addAction(ACTION_STATE_CHANGED);
        intentFilter.addAction(ACTION_GPS);
        intentFilter.addAction(ACTION_RESUME);
        intentFilter.addAction(ACTION_PAUSE);
        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
        intentFilter.addAction("com.neusoft.na.navigation.presentation.mode.switch");
        intentFilter.addAction("com.neusoft.na.navigation.presentation.mapFocusChanged");
        intentFilter.addAction(ACTION_HMI_FOCUS_CHANGED);
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction(ACTION_SET_DEFAULT_LAUNCHER);
        intentFilter.addAction(ACTION_NAVI_FOREGROUND_CHANGED);
        intentFilter.addAction(ACTION_PERMISSION_ALL_SUCCESS);
        context.registerReceiver(this, intentFilter);
        initClientEndpointManger();
        File file = new File("/sdcard/naviexternconfig/permission_check.ini");
        if (file.exists()) {
            PermissionChecker permissionChecker = new PermissionChecker();
            String absolutePath = file.getAbsolutePath();
            NaviStatus companion = NaviStatus.INSTANCE.getInstance(context);
            permissionChecker.start(absolutePath, companion != null ? companion.getDbPath() : null);
        } else {
            Log.i(TAG, file.getAbsoluteFile() + " not exists");
        }
        this.openFloatCard = true;
        this.currentINSNavDisplayMode = -1;
        final Looper looperMyLooper = Looper.myLooper();
        Intrinsics.checkNotNull(looperMyLooper);
        this.mScheduledHandler = new Handler(looperMyLooper) { // from class: com.neusoft.na.byd.Byd8155fSystem$mScheduledHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                Log.i("Byd8155fSystem", "activation: time out");
                this.this$0.sendResult(false);
            }
        };
    }

    /* JADX INFO: compiled from: Byd8155fSystem.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/neusoft/na/byd/Byd8155fSystem$Companion;", "Lmu/KLogging;", "()V", "ACTION_GPS", "", "ACTION_HMI_FOCUS_CHANGED", "ACTION_HMI_PAGE_FULL_CHANGED", "ACTION_NAVI_FOREGROUND_CHANGED", "ACTION_PAUSE", "ACTION_PERMISSION_ALL_SUCCESS", "ACTION_PRESENTATION_MAP_FOCUS_CHANGED", "ACTION_PRESENTATION_MODE_SWITCH", "ACTION_RESUME", "ACTION_SET_DEFAULT_LAUNCHER", "ACTION_STATE_CHANGED", "ACTION_SYSTEM_HOME_KEY", "ACTION_SYSTEM_REASON", "ACTION_WIDGET_INFO", "ACTION_WIDGET_RESET", "ACTION_WIDGET_SCREEN_CHANGED", "ACTION_WIDGET_TYPE", "ACTION_WIDGET_UUID", "TAG", "WHAT", "", "WIDGET_BUNDLE_KEY", "WIDGET_CAR_TYPE_KEY", "WIDGET_TYPE_KEY", "WIDGET_UUID_KEY", "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // com.neusoft.na.common.IBydSystemAdapterComponent
    public int getCarType() {
        return SystemUtils.INSTANCE.getSystemPropertiesIntValue(this.context, "persist.sys.AutoType");
    }

    @Override // com.neusoft.na.common.IBydSystemAdapterComponent
    public int getUiStyle() {
        return UiStyleConfig.getInstance(this.context).getUiStyle();
    }

    @Override // com.neusoft.na.common.IBydSystemAdapterComponent
    public void setOverseasMapSupplier(int code) {
        Log.i(TAG, "setOverseasMapSupplier --> code == " + code);
        BYDAutoWhudDevice bYDAutoWhudDevice = this.bydAutoWhudDevice;
        if (bYDAutoWhudDevice != null) {
            Log.i(TAG, "send setOverseasMapSupplier " + code);
            bYDAutoWhudDevice.setOverseasMapSupplier(code);
        }
    }

    private final synchronized void initClientEndpointManger() {
        final OneCoreWSContextUtil wsContext;
        Log.i(TAG, "[initClientEndpointManger]");
        if (!FileUtils.getInstance(this.context).isHasPermissionByd()) {
            Log.i(TAG, "permission check fail, so don't onServerConnect.");
            return;
        }
        if (!this.clientEndpointMangerInit) {
            this.clientEndpointMangerInit = true;
            try {
                ClientEndpointManger clientEndpointManger = this.cEManger;
                if (clientEndpointManger != null) {
                    clientEndpointManger.setClientEndpointMangerCallback(this);
                }
                ClientEndpointManger clientEndpointManger2 = this.cEManger;
                if ((clientEndpointManger2 != null ? clientEndpointManger2.getConnectionState() : null) == OneCoreClientEndpoint.ConnectionState.CONNECTED && (wsContext = this.cEManger.getWsContext()) != null) {
                    new Thread(new Runnable() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda14
                        @Override // java.lang.Runnable
                        public final void run() {
                            Byd8155fSystem.initClientEndpointManger$lambda$2$lambda$1(this.f$0, wsContext);
                        }
                    }).start();
                }
                Log.i(TAG, "init successful.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initClientEndpointManger$lambda$2$lambda$1(Byd8155fSystem this$0, OneCoreWSContextUtil it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        this$0.onServerConnect(it);
    }

    @Override // com.neusoft.na.common.IOptionalComponent
    public void start() {
        Log.d(TAG, "start()");
    }

    @Override // com.neusoft.na.common.IOptionalComponent
    public void stop() {
        Log.d(TAG, "stop()");
    }

    private final void switchDayNightMode() {
        int i = this.context.getResources().getConfiguration().uiMode & 48;
        Log.d(TAG, "systemUiMode == " + i);
        setDayNightMode2BL(i == 32 ? "1" : FileUtils.DEFAULT_VERSION);
    }

    @Override // com.neusoft.na.common.ClientEndpointMangerCallback
    public void onServerConnect(OneCoreWSContextUtil context) {
        ClusterViewMode clusterViewMode;
        String strJoinToString$default = "";
        Intrinsics.checkNotNullParameter(context, "context");
        Log.d(TAG, "onServerConnect");
        INSTANCE.getLogger().debug("onServerConnect");
        OneCoreWSContextUtil oneCoreWSContextUtil = context;
        this.commonRequests = new CommonSysApiInverseRequests(oneCoreWSContextUtil);
        this.commonNotifications = new CommonSysApiInverseNotifications(oneCoreWSContextUtil);
        this.bydNavApiNotifications = new BydNavApiNotifications(oneCoreWSContextUtil);
        this.bydSysApiInverseRequests = new BydSysApiInverseRequests(oneCoreWSContextUtil);
        this.bydSysApiRequests = new BydNavApiRequests(oneCoreWSContextUtil);
        this.bydSysApiInverseNotifications = new BydSysApiInverseNotifications(oneCoreWSContextUtil);
        updatePrivacyUrl(this.context);
        setDesktopModeToBl();
        switchDayNightMode();
        initBYDAutoDevice();
        CommonSysApiInverseNotifications commonSysApiInverseNotifications = this.commonNotifications;
        if (commonSysApiInverseNotifications != null) {
            IAttribute<NotifyRequestDeviceId> iAttributeRequestDeviceIdAttribute = commonSysApiInverseNotifications.requestDeviceIdAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeRequestDeviceIdAttribute, "it.requestDeviceIdAttribute()");
            IAttribute<NotifyRequestDeviceId> iAttribute = iAttributeRequestDeviceIdAttribute;
            iAttribute.signIn();
            this.remoteAttributes.add(iAttribute);
            ArrayList<Subscription> arrayList = this.remoteSubscriptions;
            Observable<NotifyRequestDeviceId> observable = iAttribute.getObservable();
            final Function1<NotifyRequestDeviceId, Unit> function1 = new Function1<NotifyRequestDeviceId, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyRequestDeviceId notifyRequestDeviceId) {
                    invoke2(notifyRequestDeviceId);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyRequestDeviceId notifyRequestDeviceId) {
                    Log.d("Byd8155fSystem", "NotifyRequestDeviceId");
                    BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.this$0.bydAutoBodyworkDevice;
                    String autoVIN = bYDAutoBodyworkDevice != null ? bYDAutoBodyworkDevice.getAutoVIN() : null;
                    if (autoVIN == null) {
                        autoVIN = "";
                    }
                    Log.i("Byd8155fSystem", "autoVin == " + autoVIN);
                    CommonSysApiInverseRequests commonSysApiInverseRequests = this.this$0.commonRequests;
                    if (commonSysApiInverseRequests != null) {
                        commonSysApiInverseRequests.fireSetDeviceId(new ReqSetDeviceId.Arguments(MapsKt.mapOf(new Pair(DeviceType.VIN_CODE, autoVIN))));
                    }
                }
            };
            arrayList.add(observable.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$4$lambda$3(function1, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications2 = this.commonNotifications;
        if (commonSysApiInverseNotifications2 != null) {
            IAttribute<NotifyResponseExpiredDate> iAttributeResponseExpiredDateAttribute = commonSysApiInverseNotifications2.responseExpiredDateAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeResponseExpiredDateAttribute, "it.responseExpiredDateAttribute()");
            IAttribute<NotifyResponseExpiredDate> iAttribute2 = iAttributeResponseExpiredDateAttribute;
            iAttribute2.signIn();
            this.remoteAttributes.add(iAttribute2);
            ArrayList<Subscription> arrayList2 = this.remoteSubscriptions;
            Observable<NotifyResponseExpiredDate> observable2 = iAttribute2.getObservable();
            final Function1<NotifyResponseExpiredDate, Unit> function12 = new Function1<NotifyResponseExpiredDate, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$2$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyResponseExpiredDate notifyResponseExpiredDate) {
                    invoke2(notifyResponseExpiredDate);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyResponseExpiredDate notifyResponseExpiredDate) {
                    Log.d("Byd8155fSystem", "NotifyResponseExpiredDate");
                    Byd8155fSystem byd8155fSystem = this.this$0;
                    ActivateResultDetails responseExpiredDate = notifyResponseExpiredDate.getResponseExpiredDate();
                    Intrinsics.checkNotNullExpressionValue(responseExpiredDate, "it.responseExpiredDate");
                    byd8155fSystem.activation(responseExpiredDate);
                }
            };
            arrayList2.add(observable2.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$6$lambda$5(function12, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications3 = this.commonNotifications;
        if (commonSysApiInverseNotifications3 != null) {
            IAttribute<NotifyCallUpStatus> iAttributeCallUpStatusAttribute = commonSysApiInverseNotifications3.callUpStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeCallUpStatusAttribute, "it.callUpStatusAttribute()");
            IAttribute<NotifyCallUpStatus> iAttribute3 = iAttributeCallUpStatusAttribute;
            iAttribute3.signIn();
            this.remoteAttributes.add(iAttribute3);
            ArrayList<Subscription> arrayList3 = this.remoteSubscriptions;
            Observable<NotifyCallUpStatus> observable3 = iAttribute3.getObservable();
            final Function1<NotifyCallUpStatus, Unit> function13 = new Function1<NotifyCallUpStatus, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$3$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyCallUpStatus notifyCallUpStatus) {
                    invoke2(notifyCallUpStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyCallUpStatus notifyCallUpStatus) {
                    try {
                        Byd8155fSystem byd8155fSystem = this.this$0;
                        String phoneNumber = notifyCallUpStatus.getCallUpStatus().getPhoneNumber();
                        Intrinsics.checkNotNullExpressionValue(phoneNumber, "it.callUpStatus.phoneNumber");
                        byd8155fSystem.callPhone(phoneNumber);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            arrayList3.add(observable3.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda5
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$8$lambda$7(function13, obj);
                }
            }));
        }
        BydNavApiNotifications bydNavApiNotifications = this.bydNavApiNotifications;
        if (bydNavApiNotifications != null) {
            IAttribute<NotifyGuidanceStatus> iAttributeGuidanceStatusAttribute = bydNavApiNotifications.guidanceStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeGuidanceStatusAttribute, "it.guidanceStatusAttribute()");
            IAttribute<NotifyGuidanceStatus> iAttribute4 = iAttributeGuidanceStatusAttribute;
            iAttribute4.signIn();
            this.remoteAttributes.add(iAttribute4);
            ArrayList<Subscription> arrayList4 = this.remoteSubscriptions;
            Observable<NotifyGuidanceStatus> observable4 = iAttribute4.getObservable();
            final Function1<NotifyGuidanceStatus, Unit> function14 = new Function1<NotifyGuidanceStatus, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$4$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyGuidanceStatus notifyGuidanceStatus) {
                    invoke2(notifyGuidanceStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyGuidanceStatus notifyGuidanceStatus) {
                    GuidedStatus guidanceStatus;
                    Log.d("Byd8155fSystem", "NotifyGuidanceStatus --> it?.guidanceStatus?.value == " + ((notifyGuidanceStatus == null || (guidanceStatus = notifyGuidanceStatus.getGuidanceStatus()) == null) ? null : guidanceStatus.getValue()));
                    if ((notifyGuidanceStatus != null ? notifyGuidanceStatus.getGuidanceStatus() : null) == GuidedStatus.GUIDED_INACTIVE) {
                        Intent intent = new Intent(Byd8155fSystem.ACTION_WIDGET_INFO);
                        boolean z = false;
                        intent.putExtra(Byd8155fSystem.WIDGET_BUNDLE_KEY, false);
                        BYDAutoTestDevice bYDAutoTestDevice = this.this$0.bydAutoTestDevice;
                        if (!(bYDAutoTestDevice != null && bYDAutoTestDevice.getAutoPowerType() == 1)) {
                            BYDAutoTestDevice bYDAutoTestDevice2 = this.this$0.bydAutoTestDevice;
                            if (!(bYDAutoTestDevice2 != null && bYDAutoTestDevice2.getAutoPowerType() == 2)) {
                                z = true;
                            }
                        }
                        intent.putExtra(Byd8155fSystem.WIDGET_CAR_TYPE_KEY, z);
                        intent.setComponent(new ComponentName(this.this$0.context, (Class<?>) BydAppWidget.class));
                        this.this$0.context.sendBroadcast(intent);
                    }
                    Log.d("Byd8155fSystem", "NotifyGuidanceStatus --> sendBroadcast");
                }
            };
            arrayList4.add(observable4.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda6
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$10$lambda$9(function14, obj);
                }
            }));
        }
        BydNavApiNotifications bydNavApiNotifications2 = this.bydNavApiNotifications;
        if (bydNavApiNotifications2 != null) {
            IAttribute<NotifySwitchDesktopMode> iAttributeSwitchDesktopModeAttribute = bydNavApiNotifications2.switchDesktopModeAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeSwitchDesktopModeAttribute, "it.switchDesktopModeAttribute()");
            IAttribute<NotifySwitchDesktopMode> iAttribute5 = iAttributeSwitchDesktopModeAttribute;
            iAttribute5.signIn();
            this.remoteAttributes.add(iAttribute5);
            ArrayList<Subscription> arrayList5 = this.remoteSubscriptions;
            Observable<NotifySwitchDesktopMode> observable5 = iAttribute5.getObservable();
            final Function1<NotifySwitchDesktopMode, Unit> function15 = new Function1<NotifySwitchDesktopMode, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$5$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifySwitchDesktopMode notifySwitchDesktopMode) {
                    invoke2(notifySwitchDesktopMode);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifySwitchDesktopMode notifySwitchDesktopMode) {
                    if (notifySwitchDesktopMode.getSwitchDesktopMode() == DesktopMode.WALLPAPER_DESKTOP) {
                        Log.i("Byd8155fSystem", "NotifySwitchDesktop DesktopMode.WALLPAPER_DESKTOP");
                        this.this$0.context.sendBroadcast(new Intent("com.neusoft.na.navigation.switchToWallpaperDesktop"));
                    } else {
                        Log.i("Byd8155fSystem", "NotifySwitchDesktop DesktopMode.MAP_DESKTOP");
                    }
                }
            };
            arrayList5.add(observable5.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda7
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$12$lambda$11(function15, obj);
                }
            }));
        }
        BydNavApiNotifications bydNavApiNotifications3 = this.bydNavApiNotifications;
        if (bydNavApiNotifications3 != null) {
            IAttribute<NotifyAuthInfo> iAttributeAuthInfoAttribute = bydNavApiNotifications3.authInfoAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeAuthInfoAttribute, "it.authInfoAttribute()");
            IAttribute<NotifyAuthInfo> iAttribute6 = iAttributeAuthInfoAttribute;
            iAttribute6.signIn();
            this.remoteAttributes.add(iAttribute6);
            ArrayList<Subscription> arrayList6 = this.remoteSubscriptions;
            Observable<NotifyAuthInfo> observable6 = iAttribute6.getObservable();
            final Function1<NotifyAuthInfo, Unit> function16 = new Function1<NotifyAuthInfo, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$6$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyAuthInfo notifyAuthInfo) throws Exception {
                    invoke2(notifyAuthInfo);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyAuthInfo notifyAuthInfo) throws Exception {
                    StringBuilder sb = new StringBuilder("vehicleAuthInfo vid:");
                    AuthConditionInfo authInfo = notifyAuthInfo.getAuthInfo();
                    Log.i("Byd8155fSystem", sb.append(authInfo != null ? authInfo.getVid() : null).toString());
                    AuthConditionInfo authInfo2 = notifyAuthInfo.getAuthInfo();
                    if (TextUtils.isEmpty(authInfo2 != null ? authInfo2.getVid() : null) || TextUtils.isEmpty(notifyAuthInfo.getAuthInfo().getPubKey())) {
                        return;
                    }
                    String strVehicleAuth = EncryptAlgorithm.vehicleAuth(notifyAuthInfo.getAuthInfo().getVid(), notifyAuthInfo.getAuthInfo().getPubKey(), this.this$0.imei);
                    Log.i("Byd8155fSystem", "vehicleAuthInfo ::" + strVehicleAuth);
                    BydNavApiRequests bydNavApiRequests = this.this$0.bydSysApiRequests;
                    if (bydNavApiRequests != null) {
                        bydNavApiRequests.fireOnAuthInfo(new ReqOnAuthInfo(strVehicleAuth));
                    }
                }
            };
            arrayList6.add(observable6.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda8
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$14$lambda$13(function16, obj);
                }
            }));
        }
        BydNavApiNotifications bydNavApiNotifications4 = this.bydNavApiNotifications;
        if (bydNavApiNotifications4 != null) {
            IAttribute<NotifyVerifyInfo> iAttributeVerifyInfoAttribute = bydNavApiNotifications4.verifyInfoAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeVerifyInfoAttribute, "it.verifyInfoAttribute()");
            IAttribute<NotifyVerifyInfo> iAttribute7 = iAttributeVerifyInfoAttribute;
            iAttribute7.signIn();
            this.remoteAttributes.add(iAttribute7);
            ArrayList<Subscription> arrayList7 = this.remoteSubscriptions;
            Observable<NotifyVerifyInfo> observable7 = iAttribute7.getObservable();
            final Function1<NotifyVerifyInfo, Unit> function17 = new Function1<NotifyVerifyInfo, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$7$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyVerifyInfo notifyVerifyInfo) throws Exception {
                    invoke2(notifyVerifyInfo);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyVerifyInfo notifyVerifyInfo) throws Exception {
                    StringBuilder sb = new StringBuilder("vehicleVerify condition:");
                    VerifyConditionInfo verifyInfo = notifyVerifyInfo.getVerifyInfo();
                    Log.i("Byd8155fSystem", sb.append(verifyInfo != null ? verifyInfo.getVerCondition() : null).toString());
                    VerifyConditionInfo verifyInfo2 = notifyVerifyInfo.getVerifyInfo();
                    if (TextUtils.isEmpty(verifyInfo2 != null ? verifyInfo2.getVerCondition() : null)) {
                        return;
                    }
                    String strVehicleVerify = EncryptAlgorithm.vehicleVerify(notifyVerifyInfo.getVerifyInfo().getVerCondition(), this.this$0.imei);
                    Log.i("Byd8155fSystem", "vehicleVerify ::" + strVehicleVerify);
                    BydNavApiRequests bydNavApiRequests = this.this$0.bydSysApiRequests;
                    if (bydNavApiRequests != null) {
                        bydNavApiRequests.fireOnVerifyInfo(new ReqOnVerifyInfo(strVehicleVerify));
                    }
                }
            };
            arrayList7.add(observable7.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda9
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$16$lambda$15(function17, obj);
                }
            }));
        }
        BydNavApiNotifications bydNavApiNotifications5 = this.bydNavApiNotifications;
        if (bydNavApiNotifications5 != null) {
            IAttribute<NotifyClusterViewMode> iAttributeClusterViewModeAttribute = bydNavApiNotifications5.clusterViewModeAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeClusterViewModeAttribute, "it.clusterViewModeAttribute()");
            IAttribute<NotifyClusterViewMode> iAttribute8 = iAttributeClusterViewModeAttribute;
            iAttribute8.signIn();
            this.remoteAttributes.add(iAttribute8);
            ArrayList<Subscription> arrayList8 = this.remoteSubscriptions;
            Observable<NotifyClusterViewMode> observable8 = iAttribute8.getObservable();
            final Function1<NotifyClusterViewMode, Unit> function18 = new Function1<NotifyClusterViewMode, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$8$1

                /* JADX INFO: compiled from: Byd8155fSystem.kt */
                @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[ClusterViewMode.values().length];
                        try {
                            iArr[ClusterViewMode.TURN_BY_TURN_MODE.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[ClusterViewMode.CLOSE_SCREEN_MODE.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        try {
                            iArr[ClusterViewMode.SMALL_SCREEN_MODE.ordinal()] = 3;
                        } catch (NoSuchFieldError unused3) {
                        }
                        try {
                            iArr[ClusterViewMode.LARGE_SCREEN_MODE.ordinal()] = 4;
                        } catch (NoSuchFieldError unused4) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyClusterViewMode notifyClusterViewMode) {
                    invoke2(notifyClusterViewMode);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:18:0x004e A[PHI: r3
  0x004e: PHI (r3v2 int) = (r3v1 int), (r3v3 int) binds: [B:11:0x003e, B:16:0x0046] A[DONT_GENERATE, DONT_INLINE]] */
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void invoke2(api.notifications.com.neusoft.byd.bydnavapi.NotifyClusterViewMode r5) {
                    /*
                        r4 = this;
                        java.lang.StringBuilder r0 = new java.lang.StringBuilder
                        java.lang.String r1 = "NotifyClusterViewMode --> "
                        r0.<init>(r1)
                        api.structs.com.neusoft.byd.ClusterViewMode r1 = r5.getClusterViewMode()
                        java.lang.Integer r1 = r1.getValue()
                        java.lang.StringBuilder r0 = r0.append(r1)
                        java.lang.String r0 = r0.toString()
                        java.lang.String r1 = "Byd8155fSystem"
                        android.util.Log.i(r1, r0)
                        com.neusoft.na.byd.Byd8155fSystem r0 = r4.this$0
                        int r0 = com.neusoft.na.byd.Byd8155fSystem.access$getPresentationMapType$p(r0)
                        r2 = 1
                        if (r0 != r2) goto L69
                        com.neusoft.na.byd.Byd8155fSystem r0 = r4.this$0
                        com.byd.bydnaviapi.BydAutoNaviDevice r0 = com.neusoft.na.byd.Byd8155fSystem.access$getBydAutoNaviDevice$p(r0)
                        if (r0 == 0) goto L6e
                        api.structs.com.neusoft.byd.ClusterViewMode r5 = r5.getClusterViewMode()
                        if (r5 != 0) goto L35
                        r5 = -1
                        goto L3d
                    L35:
                        int[] r3 = com.neusoft.na.byd.Byd8155fSystem$onServerConnect$8$1.WhenMappings.$EnumSwitchMapping$0
                        int r5 = r5.ordinal()
                        r5 = r3[r5]
                    L3d:
                        r3 = 2
                        if (r5 == r2) goto L4e
                        if (r5 == r3) goto L4f
                        r2 = 3
                        if (r5 == r2) goto L4f
                        r3 = 4
                        if (r5 == r3) goto L4e
                        java.lang.String r5 = "not found value, so use default NAV_SMALL"
                        android.util.Log.e(r1, r5)
                        goto L4f
                    L4e:
                        r2 = r3
                    L4f:
                        r0.setInsNavDisplayMode(r2)
                        java.lang.StringBuilder r5 = new java.lang.StringBuilder
                        java.lang.String r2 = "NotifyClusterViewMode --> send "
                        r5.<init>(r2)
                        int r0 = r0.getInsNavDisplayMode()
                        java.lang.StringBuilder r5 = r5.append(r0)
                        java.lang.String r5 = r5.toString()
                        android.util.Log.i(r1, r5)
                        goto L6e
                    L69:
                        java.lang.String r5 = "NotifyClusterViewMode --> MapFocus is not local map, so not do set."
                        android.util.Log.i(r1, r5)
                    L6e:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$8$1.invoke2(api.notifications.com.neusoft.byd.bydnavapi.NotifyClusterViewMode):void");
                }
            };
            arrayList8.add(observable8.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda10
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$18$lambda$17(function18, obj);
                }
            }));
        }
        BydNavApiNotifications bydNavApiNotifications6 = this.bydNavApiNotifications;
        if (bydNavApiNotifications6 != null) {
            IAttribute<NotifyGuidanceInfoUpdate> iAttributeGuidanceInfoUpdateAttribute = bydNavApiNotifications6.guidanceInfoUpdateAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeGuidanceInfoUpdateAttribute, "it.guidanceInfoUpdateAttribute()");
            IAttribute<NotifyGuidanceInfoUpdate> iAttribute9 = iAttributeGuidanceInfoUpdateAttribute;
            iAttribute9.signIn();
            this.remoteAttributes.add(iAttribute9);
            ArrayList<Subscription> arrayList9 = this.remoteSubscriptions;
            Observable<NotifyGuidanceInfoUpdate> observable9 = iAttribute9.getObservable();
            final Function1<NotifyGuidanceInfoUpdate, Unit> function19 = new Function1<NotifyGuidanceInfoUpdate, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$9$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyGuidanceInfoUpdate notifyGuidanceInfoUpdate) {
                    invoke2(notifyGuidanceInfoUpdate);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyGuidanceInfoUpdate notifyGuidanceInfoUpdate) {
                    Log.d("Byd8155fSystem", "NotifyGuidanceInfoUpdate");
                    if (this.this$0.presentationMapType != 1) {
                        Log.i("Byd8155fSystem", "current not map focus, so do not send tbt data.");
                        return;
                    }
                    BydAutoNaviDevice bydAutoNaviDevice = this.this$0.bydAutoNaviDevice;
                    if (bydAutoNaviDevice != null) {
                        StringBuilder sb = new StringBuilder("sendSimpleNaviInfo send --> 1, ");
                        sb.append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getTurnId()).append(", ").append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getNextRoadName()).append(", ").append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getNextTurnDist()).append(", ").append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getNextTurnUnit()).append(" , ").append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getNextTurnId()).append(", ").append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getNextNextTurnDist()).append(", ").append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getNextNextTurnUnit()).append(", ").append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getRemainDist()).append(", ").append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getRemainUnit()).append(", ").append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getArrivalTimeD()).append(", ").append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getArrivalTimeH()).append(", ");
                        sb.append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getArrivalTimeM()).append(", ").append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getArrivalTimeS()).append(',').append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getRemainTime()).append(',').append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getTargetDisUnit()).append(',').append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getToDestDays()).append(',').append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getArrivalTimeStr()).append(',').append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getMultipleText()).append(',').append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getMultipleText1()).append(',').append(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getMultipleText2()).append(',');
                        Log.i("Byd8155fSystem", sb.toString());
                        Integer turnId = notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getTurnId();
                        Intrinsics.checkNotNullExpressionValue(turnId, "guidanceInfo.guidanceInfoUpdate.turnId");
                        int iIntValue = turnId.intValue();
                        String nextRoadName = notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getNextRoadName();
                        String nextTurnDist = notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getNextTurnDist();
                        String nextTurnUnit = notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getNextTurnUnit();
                        Integer nextTurnId = notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getNextTurnId();
                        Intrinsics.checkNotNullExpressionValue(nextTurnId, "guidanceInfo.guidanceInfoUpdate.nextTurnId");
                        Log.i("Byd8155fSystem", "sendTbtNaviInfo code == " + bydAutoNaviDevice.sendSimpleNaviInfo(1, iIntValue, nextRoadName, nextTurnDist, nextTurnUnit, nextTurnId.intValue(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getNextNextTurnDist(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getNextNextTurnUnit(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getRemainDist(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getRemainUnit(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getArrivalTimeD(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getArrivalTimeH(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getArrivalTimeM(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getArrivalTimeS(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getRemainTime(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getTargetDisUnit(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getToDestDays(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getArrivalTimeStr(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getMultipleText(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getMultipleText1(), notifyGuidanceInfoUpdate.getGuidanceInfoUpdate().getMultipleText2()));
                    }
                }
            };
            arrayList9.add(observable9.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda12
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$20$lambda$19(function19, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications4 = this.commonNotifications;
        if (commonSysApiInverseNotifications4 != null) {
            IAttribute<NotifyNaviStatusChanged> iAttributeNaviStatusChangedAttribute = commonSysApiInverseNotifications4.naviStatusChangedAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeNaviStatusChangedAttribute, "it.naviStatusChangedAttribute()");
            iAttributeNaviStatusChangedAttribute.signIn();
            this.remoteAttributes.add(iAttributeNaviStatusChangedAttribute);
            ArrayList<Subscription> arrayList10 = this.remoteSubscriptions;
            Observable<NotifyNaviStatusChanged> observable10 = iAttributeNaviStatusChangedAttribute.getObservable();
            final Byd8155fSystem$onServerConnect$10$1 byd8155fSystem$onServerConnect$10$1 = new Byd8155fSystem$onServerConnect$10$1(this);
            arrayList10.add(observable10.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda13
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$22$lambda$21(byd8155fSystem$onServerConnect$10$1, obj);
                }
            }));
        }
        BydNavApiNotifications bydNavApiNotifications7 = this.bydNavApiNotifications;
        if (bydNavApiNotifications7 != null) {
            IAttribute<NotifyReqGPSInfo> iAttributeReqGPSInfoAttribute = bydNavApiNotifications7.reqGPSInfoAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeReqGPSInfoAttribute, "it.reqGPSInfoAttribute()");
            IAttribute<NotifyReqGPSInfo> iAttribute10 = iAttributeReqGPSInfoAttribute;
            iAttribute10.signIn();
            this.remoteAttributes.add(iAttribute10);
            ArrayList<Subscription> arrayList11 = this.remoteSubscriptions;
            Observable<NotifyReqGPSInfo> observable11 = iAttribute10.getObservable();
            final Byd8155fSystem$onServerConnect$11$1 byd8155fSystem$onServerConnect$11$1 = new Byd8155fSystem$onServerConnect$11$1(this);
            arrayList11.add(observable11.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda11
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$24$lambda$23(byd8155fSystem$onServerConnect$11$1, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications5 = this.commonNotifications;
        if (commonSysApiInverseNotifications5 != null) {
            IAttribute<NotifyImmersionStatus> iAttributeImmersionStatusAttribute = commonSysApiInverseNotifications5.immersionStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeImmersionStatusAttribute, "it.immersionStatusAttribute()");
            IAttribute<NotifyImmersionStatus> iAttribute11 = iAttributeImmersionStatusAttribute;
            iAttribute11.signIn();
            this.remoteAttributes.add(iAttribute11);
            ArrayList<Subscription> arrayList12 = this.remoteSubscriptions;
            Observable<NotifyImmersionStatus> observable12 = iAttribute11.getObservable();
            final Function1<NotifyImmersionStatus, Unit> function110 = new Function1<NotifyImmersionStatus, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$12$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyImmersionStatus notifyImmersionStatus) {
                    invoke2(notifyImmersionStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyImmersionStatus notifyImmersionStatus) {
                    Log.d("Byd8155fSystem", "NotifyImmersionStatus");
                    Byd8155fSystem byd8155fSystem = this.this$0;
                    Boolean immersionStatus = notifyImmersionStatus.getImmersionStatus().getImmersionStatus();
                    byd8155fSystem.openFloatCard = immersionStatus == null ? true : immersionStatus.booleanValue();
                    Byd8155fSystem byd8155fSystem2 = this.this$0;
                    byd8155fSystem2.sendFloatCardStatus(byd8155fSystem2.openFloatCard);
                }
            };
            arrayList12.add(observable12.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda15
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$26$lambda$25(function110, obj);
                }
            }));
        }
        BydNavApiRequests bydNavApiRequests = this.bydSysApiRequests;
        if (bydNavApiRequests != null) {
            ReqClusterViewModeChanged reqClusterViewModeChanged = new ReqClusterViewModeChanged();
            BydAutoNaviDevice bydAutoNaviDevice = BydAutoNaviDevice.getInstance(this.context);
            ReqClusterViewModeChanged.Arguments arguments = reqClusterViewModeChanged.getArguments();
            Intrinsics.checkNotNull(bydAutoNaviDevice);
            int insNavDisplayMode = bydAutoNaviDevice.getInsNavDisplayMode();
            if (insNavDisplayMode == 1) {
                clusterViewMode = ClusterViewMode.CLOSE_SCREEN_MODE;
            } else if (insNavDisplayMode == 2) {
                clusterViewMode = ClusterViewMode.TURN_BY_TURN_MODE;
            } else if (insNavDisplayMode == 3) {
                clusterViewMode = ClusterViewMode.SMALL_SCREEN_MODE;
            } else if (insNavDisplayMode == 4) {
                clusterViewMode = ClusterViewMode.LARGE_SCREEN_MODE;
            } else {
                clusterViewMode = ClusterViewMode.DEFAULT_MODE;
            }
            arguments.setViewMode(clusterViewMode);
            bydNavApiRequests.fireClusterViewModeChanged(reqClusterViewModeChanged);
            Log.i(TAG, "fireClusterViewModeChanged --> bydAutoNaviDevice!!.insNavDisplayMode == " + bydAutoNaviDevice.getInsNavDisplayMode() + ", reqClusterViewModeChanged.arguments.viewMode == " + reqClusterViewModeChanged.getArguments().getViewMode());
        }
        final ChangeHistoryUtil changeHistoryUtil = new ChangeHistoryUtil(this.context);
        CommonSysApiInverseNotifications commonSysApiInverseNotifications6 = this.commonNotifications;
        if (commonSysApiInverseNotifications6 != null) {
            IAttribute<NotifyReadFile> fileAttribute = commonSysApiInverseNotifications6.readFileAttribute();
            fileAttribute.signIn();
            this.remoteAttributes.add(fileAttribute);
            ArrayList<Subscription> arrayList13 = this.remoteSubscriptions;
            Observable<NotifyReadFile> observable13 = fileAttribute.getObservable();
            final Function1<NotifyReadFile, Unit> function111 = new Function1<NotifyReadFile, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$14$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyReadFile notifyReadFile) {
                    invoke2(notifyReadFile);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyReadFile notifyReadFile) {
                    Log.i("Byd8155fSystem", "NotifyReadFile");
                    if (changeHistoryUtil.isSamePath(notifyReadFile.getReadFile().getPath())) {
                        changeHistoryUtil.checkVinCode();
                        ReqOnReadFile reqOnReadFile = new ReqOnReadFile(notifyReadFile.getReadFile().getTokenId(), ReadFileStatus.E_SUCCESS, changeHistoryUtil.getJsonString());
                        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
                        if (commonSysApiInverseRequests != null) {
                            commonSysApiInverseRequests.fireOnReadFile(reqOnReadFile);
                        }
                    }
                }
            };
            arrayList13.add(observable13.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda16
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$29$lambda$28(function111, obj);
                }
            }));
        }
        BydNavApiNotifications bydNavApiNotifications8 = this.bydNavApiNotifications;
        if (bydNavApiNotifications8 != null) {
            IAttribute<NotifyDelPrivacyPolicyInfo> iAttributeDelPrivacyPolicyInfoAttribute = bydNavApiNotifications8.delPrivacyPolicyInfoAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeDelPrivacyPolicyInfoAttribute, "it.delPrivacyPolicyInfoAttribute()");
            iAttributeDelPrivacyPolicyInfoAttribute.signIn();
            this.remoteAttributes.add(iAttributeDelPrivacyPolicyInfoAttribute);
            ArrayList<Subscription> arrayList14 = this.remoteSubscriptions;
            Observable<NotifyDelPrivacyPolicyInfo> observable14 = iAttributeDelPrivacyPolicyInfoAttribute.getObservable();
            final Byd8155fSystem$onServerConnect$15$1 byd8155fSystem$onServerConnect$15$1 = new Byd8155fSystem$onServerConnect$15$1(changeHistoryUtil, this);
            arrayList14.add(observable14.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda17
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$33$lambda$30(byd8155fSystem$onServerConnect$15$1, obj);
                }
            }));
            IAttribute<NotifyRevokePrivacyPolicy> iAttributeRevokePrivacyPolicyAttribute = bydNavApiNotifications8.revokePrivacyPolicyAttribute();
            iAttributeRevokePrivacyPolicyAttribute.signIn();
            this.remoteAttributes.add(iAttributeRevokePrivacyPolicyAttribute);
            ArrayList<Subscription> arrayList15 = this.remoteSubscriptions;
            Observable<NotifyRevokePrivacyPolicy> observable15 = iAttributeRevokePrivacyPolicyAttribute.getObservable();
            final Byd8155fSystem$onServerConnect$15$2 byd8155fSystem$onServerConnect$15$2 = new Byd8155fSystem$onServerConnect$15$2(this, changeHistoryUtil);
            arrayList15.add(observable15.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda18
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$33$lambda$31(byd8155fSystem$onServerConnect$15$2, obj);
                }
            }));
            IAttribute<NotifyUpdatePrivacyPolicySyncStatus> iAttributeUpdatePrivacyPolicySyncStatusAttribute = bydNavApiNotifications8.updatePrivacyPolicySyncStatusAttribute();
            iAttributeUpdatePrivacyPolicySyncStatusAttribute.signIn();
            this.remoteAttributes.add(iAttributeUpdatePrivacyPolicySyncStatusAttribute);
            ArrayList<Subscription> arrayList16 = this.remoteSubscriptions;
            Observable<NotifyUpdatePrivacyPolicySyncStatus> observable16 = iAttributeUpdatePrivacyPolicySyncStatusAttribute.getObservable();
            final Byd8155fSystem$onServerConnect$15$3 byd8155fSystem$onServerConnect$15$3 = new Byd8155fSystem$onServerConnect$15$3(changeHistoryUtil, this);
            arrayList16.add(observable16.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda19
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$33$lambda$32(byd8155fSystem$onServerConnect$15$3, obj);
                }
            }));
        }
        BydNavApiNotifications bydNavApiNotifications9 = this.bydNavApiNotifications;
        if (bydNavApiNotifications9 != null) {
            IAttribute<NotifyIsNaviHMIFullScreenPage> iAttributeIsNaviHMIFullScreenPageAttribute = bydNavApiNotifications9.isNaviHMIFullScreenPageAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeIsNaviHMIFullScreenPageAttribute, "it.isNaviHMIFullScreenPageAttribute");
            iAttributeIsNaviHMIFullScreenPageAttribute.signIn();
            this.remoteAttributes.add(iAttributeIsNaviHMIFullScreenPageAttribute);
            ArrayList<Subscription> arrayList17 = this.remoteSubscriptions;
            Observable<NotifyIsNaviHMIFullScreenPage> observable17 = iAttributeIsNaviHMIFullScreenPageAttribute.getObservable();
            final Function1<NotifyIsNaviHMIFullScreenPage, Unit> function112 = new Function1<NotifyIsNaviHMIFullScreenPage, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$16$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyIsNaviHMIFullScreenPage notifyIsNaviHMIFullScreenPage) {
                    invoke2(notifyIsNaviHMIFullScreenPage);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyIsNaviHMIFullScreenPage notifyIsNaviHMIFullScreenPage) {
                    Log.i("Byd8155fSystem", "NotifyIsNaviHMIFullScreenPage --> " + notifyIsNaviHMIFullScreenPage.getIsNaviHMIFullScreenPage());
                    Intent intent = new Intent(MotsBroadcastReceiver.ACTION_HMI_PAGE_FULL_CHANGED);
                    intent.putExtra(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, Intrinsics.areEqual(notifyIsNaviHMIFullScreenPage.getIsNaviHMIFullScreenPage().getValue(), NaviHMIPageStatus.FULL_SCREEN_PAGE.getValue()));
                    this.this$0.context.sendBroadcast(intent);
                }
            };
            arrayList17.add(observable17.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda20
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$35$lambda$34(function112, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications7 = this.commonNotifications;
        if (commonSysApiInverseNotifications7 != null) {
            IAttribute<NotifyExtractFiles> iAttributeExtractFilesAttribute = commonSysApiInverseNotifications7.extractFilesAttribute();
            iAttributeExtractFilesAttribute.signIn();
            this.remoteAttributes.add(iAttributeExtractFilesAttribute);
            ArrayList<Subscription> arrayList18 = this.remoteSubscriptions;
            Observable<NotifyExtractFiles> observable18 = iAttributeExtractFilesAttribute.getObservable();
            final Byd8155fSystem$onServerConnect$17$1 byd8155fSystem$onServerConnect$17$1 = new Byd8155fSystem$onServerConnect$17$1(changeHistoryUtil, this);
            arrayList18.add(observable18.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda21
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$37$lambda$36(byd8155fSystem$onServerConnect$17$1, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications8 = this.commonNotifications;
        if (commonSysApiInverseNotifications8 != null) {
            IAttribute<NotifyDelegateSystemEvent> iAttributeDelegateSystemEventAttribute = commonSysApiInverseNotifications8.delegateSystemEventAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeDelegateSystemEventAttribute, "it.delegateSystemEventAttribute()");
            IAttribute<NotifyDelegateSystemEvent> iAttribute12 = iAttributeDelegateSystemEventAttribute;
            iAttribute12.signIn();
            this.remoteAttributes.add(iAttribute12);
            ArrayList<Subscription> arrayList19 = this.remoteSubscriptions;
            Observable<NotifyDelegateSystemEvent> observable19 = iAttribute12.getObservable();
            final Function1<NotifyDelegateSystemEvent, Unit> function113 = new Function1<NotifyDelegateSystemEvent, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$18$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyDelegateSystemEvent notifyDelegateSystemEvent) {
                    invoke2(notifyDelegateSystemEvent);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyDelegateSystemEvent notifyDelegateSystemEvent) {
                    Log.i("Byd8155fSystem", "NotifyDelegateSystemEvent");
                    this.this$0.context.sendBroadcast(new Intent(MotsBroadcastReceiver.ACTION_HMI_REQUEST_FOCUS));
                }
            };
            arrayList19.add(observable19.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda22
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$39$lambda$38(function113, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications9 = this.commonNotifications;
        if (commonSysApiInverseNotifications9 != null) {
            IAttribute<NotifyLoadURL> iAttributeLoadURLAttribute = commonSysApiInverseNotifications9.loadURLAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeLoadURLAttribute, "it.loadURLAttribute()");
            IAttribute<NotifyLoadURL> iAttribute13 = iAttributeLoadURLAttribute;
            iAttribute13.signIn();
            this.remoteAttributes.add(iAttribute13);
            Log.i(TAG, "register NotifyLoadURL notifications");
            ArrayList<Subscription> arrayList20 = this.remoteSubscriptions;
            Observable<NotifyLoadURL> observable20 = iAttribute13.getObservable();
            final Function1<NotifyLoadURL, Unit> function114 = new Function1<NotifyLoadURL, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$19$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyLoadURL notifyLoadURL) {
                    invoke2(notifyLoadURL);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyLoadURL notifyLoadURL) {
                    Log.i("Byd8155fSystem", "NotifyLoadURL --> " + notifyLoadURL.getLoadURL().getURL());
                    ReqOnLoadURL reqOnLoadURL = new ReqOnLoadURL();
                    Byd8155fSystem byd8155fSystem = this.this$0;
                    String url = notifyLoadURL.getLoadURL().getURL();
                    Intrinsics.checkNotNullExpressionValue(url, "it.loadURL.url");
                    if (byd8155fSystem.isBrowserInstalled(url)) {
                        Log.i("Byd8155fSystem", "jump to browser");
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(notifyLoadURL.getLoadURL().getURL()));
                        intent.addFlags(268435456);
                        this.this$0.context.startActivity(intent);
                        reqOnLoadURL.getArguments().setStatus(ResultStatus.E_SUCCESS);
                    } else {
                        Log.i("Byd8155fSystem", "not install browser, notify BL");
                        reqOnLoadURL.getArguments().setStatus(ResultStatus.E_FAILURE);
                    }
                    reqOnLoadURL.getArguments().setTokenId(notifyLoadURL.getLoadURL().getTokenId());
                    CommonSysApiInverseRequests commonSysApiInverseRequests = this.this$0.commonRequests;
                    if (commonSysApiInverseRequests != null) {
                        commonSysApiInverseRequests.fireOnLoadURL(reqOnLoadURL);
                    }
                }
            };
            arrayList20.add(observable20.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$41$lambda$40(function114, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications10 = this.commonNotifications;
        if (commonSysApiInverseNotifications10 != null) {
            IAttribute<NotifyGetLowFuelStatus> lowFuelStatusAttribute = commonSysApiInverseNotifications10.getLowFuelStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(lowFuelStatusAttribute, "it.lowFuelStatusAttribute");
            IAttribute<NotifyGetLowFuelStatus> iAttribute14 = lowFuelStatusAttribute;
            iAttribute14.signIn();
            this.remoteAttributes.add(iAttribute14);
            Log.i(TAG, "register NotifyGetLowFuelStatus notifications");
            ArrayList<Subscription> arrayList21 = this.remoteSubscriptions;
            Observable<NotifyGetLowFuelStatus> observable21 = iAttribute14.getObservable();
            final Function1<NotifyGetLowFuelStatus, Unit> function115 = new Function1<NotifyGetLowFuelStatus, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$20$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyGetLowFuelStatus notifyGetLowFuelStatus) {
                    invoke2(notifyGetLowFuelStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyGetLowFuelStatus notifyGetLowFuelStatus) {
                    Log.i("Byd8155fSystem", "NotifyGetLowFuelStatus");
                    if (ContextCompat.checkSelfPermission(this.this$0.context, BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) == 0) {
                        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.this$0.bydAutoInstrumentDevice;
                        if (bYDAutoInstrumentDevice != null) {
                            this.this$0.setLowFuelSignal2BL(bYDAutoInstrumentDevice.getLowFuelWarnLightColor());
                            return;
                        }
                        return;
                    }
                    Log.w("Byd8155fSystem", "has no BYDAUTO_INSTRUMENT_COMMON permission.");
                }
            };
            arrayList21.add(observable21.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda3
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$43$lambda$42(function115, obj);
                }
            }));
        }
        BydSysApiInverseNotifications bydSysApiInverseNotifications = this.bydSysApiInverseNotifications;
        if (bydSysApiInverseNotifications != null) {
            IAttribute<NotifyNaviMainMapDayNightStatusChange> iAttributeNaviMainMapDayNightStatusChangeAttribute = bydSysApiInverseNotifications.naviMainMapDayNightStatusChangeAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeNaviMainMapDayNightStatusChangeAttribute, "it.naviMainMapDayNightStatusChangeAttribute()");
            IAttribute<NotifyNaviMainMapDayNightStatusChange> iAttribute15 = iAttributeNaviMainMapDayNightStatusChangeAttribute;
            iAttribute15.signIn();
            this.remoteAttributes.add(iAttribute15);
            Log.i(TAG, "register NotifyNaviMainMapDayNightStatusChange notifications");
            ArrayList<Subscription> arrayList22 = this.remoteSubscriptions;
            Observable<NotifyNaviMainMapDayNightStatusChange> observable22 = iAttribute15.getObservable();
            final Function1<NotifyNaviMainMapDayNightStatusChange, Unit> function116 = new Function1<NotifyNaviMainMapDayNightStatusChange, Unit>() { // from class: com.neusoft.na.byd.Byd8155fSystem$onServerConnect$21$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyNaviMainMapDayNightStatusChange notifyNaviMainMapDayNightStatusChange) {
                    invoke2(notifyNaviMainMapDayNightStatusChange);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyNaviMainMapDayNightStatusChange notifyNaviMainMapDayNightStatusChange) {
                    Integer value = notifyNaviMainMapDayNightStatusChange.getNaviMainMapDayNightStatusChange().getValue();
                    Log.d("Byd8155fSystem", "NotifyNaviMainMapDayNightStatusChange --> value == " + value);
                    Intent intent = new Intent("com.neusoft.na.navigation.action.hmi.settings.dayNightMode.changed");
                    Intrinsics.checkNotNullExpressionValue(value, "value");
                    intent.putExtra("hmiSettingsDayNightMode", value.intValue());
                    this.this$0.context.sendStickyBroadcast(intent);
                }
            };
            arrayList22.add(observable22.subscribe(new Action1() { // from class: com.neusoft.na.byd.Byd8155fSystem$$ExternalSyntheticLambda4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Byd8155fSystem.onServerConnect$lambda$45$lambda$44(function116, obj);
                }
            }));
        }
        BYDAutoPanoramaDevice bYDAutoPanoramaDevice = this.autoPanoramaDevice;
        int panoRotation = bYDAutoPanoramaDevice != null ? bYDAutoPanoramaDevice.getPanoRotation() : 1;
        Log.i(TAG, "panoRotation == " + panoRotation);
        sendOrientation(panoRotation);
        try {
            String imei = ITbox.getService().getImei();
            Intrinsics.checkNotNullExpressionValue(imei, "service.imei");
            this.imei = imei;
            Log.i(TAG, "TDQ length: " + this.imei.length());
            StringBuilder sb = new StringBuilder();
            String str = this.imei;
            int length = str.length();
            for (int i = 0; i < length; i++) {
                String binary = Integer.toBinaryString(str.charAt(i));
                Intrinsics.checkNotNullExpressionValue(binary, "binary");
                sb.append(StringsKt.padStart(binary, 8, '0'));
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "binaryStringBuilder.toString()");
            char[] charArray = string.toCharArray();
            Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
            List<Character> mutableList = ArraysKt.toMutableList(charArray);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (int i2 = 0; i2 < 60; i2++) {
                int iNextInt = Random.INSTANCE.nextInt(mutableList.size() + 1);
                while (linkedHashSet.contains(Integer.valueOf(iNextInt))) {
                    iNextInt = Random.INSTANCE.nextInt(mutableList.size() + 1);
                }
                linkedHashSet.add(Integer.valueOf(iNextInt));
                mutableList.add(iNextInt, '*');
            }
            strJoinToString$default = CollectionsKt.joinToString$default(mutableList, "", null, null, 0, null, null, 62, null);
            Log.i(TAG, "TDQ bStr:" + strJoinToString$default);
        } catch (Exception e) {
            Log.w(TAG, "imei: " + e);
        }
        ReqSetMobileEquipmentID reqSetMobileEquipmentID = new ReqSetMobileEquipmentID(strJoinToString$default);
        BydNavApiRequests bydNavApiRequests2 = this.bydSysApiRequests;
        if (bydNavApiRequests2 != null) {
            bydNavApiRequests2.fireSetMobileEquipmentID(reqSetMobileEquipmentID);
        }
        Log.i(TAG, "onServerConnect: done");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$4$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$6$lambda$5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$8$lambda$7(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$10$lambda$9(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$12$lambda$11(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$14$lambda$13(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$16$lambda$15(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$18$lambda$17(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$20$lambda$19(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$22$lambda$21(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$24$lambda$23(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$26$lambda$25(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$29$lambda$28(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$33$lambda$30(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$33$lambda$31(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$33$lambda$32(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$35$lambda$34(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$37$lambda$36(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$39$lambda$38(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$41$lambda$40(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$43$lambda$42(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$45$lambda$44(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isBrowserInstalled(String url) {
        Log.i(TAG, "isBrowserInstalled --> url == " + url);
        PackageManager packageManager = this.context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        intent.addCategory("android.intent.category.BROWSABLE");
        boolean z = packageManager.resolveActivity(intent, 65536) != null;
        Log.i(TAG, "installed == " + z);
        return z;
    }

    private final int getPresentationTbtUnit(TopTipUnit unit) {
        int i = WhenMappings.$EnumSwitchMapping$0[unit.ordinal()];
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        if (i == 3) {
            return 3;
        }
        if (i != 4) {
            return i != 5 ? 0 : 5;
        }
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onGuideChanged(boolean r5) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "onGuideChanged: "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "Byd8155fSystem"
            android.util.Log.i(r1, r0)
            r4.naviGuideState = r5
            r4.sendNaviStatusBroadcast(r5)
            r4.isSendToWidget = r5
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.neusoft.na.navigation.action.widget.info.update"
            r0.<init>(r1)
            java.lang.String r1 = "BUNDLE_KEY"
            r0.putExtra(r1, r5)
            android.hardware.bydauto.test.BYDAutoTestDevice r5 = r4.bydAutoTestDevice
            r1 = 1
            r2 = 0
            if (r5 == 0) goto L35
            int r5 = r5.getAutoPowerType()
            if (r5 != r1) goto L35
            r5 = r1
            goto L36
        L35:
            r5 = r2
        L36:
            if (r5 != 0) goto L49
            android.hardware.bydauto.test.BYDAutoTestDevice r5 = r4.bydAutoTestDevice
            if (r5 == 0) goto L45
            int r5 = r5.getAutoPowerType()
            r3 = 2
            if (r5 != r3) goto L45
            r5 = r1
            goto L46
        L45:
            r5 = r2
        L46:
            if (r5 != 0) goto L49
            goto L4a
        L49:
            r1 = r2
        L4a:
            java.lang.String r5 = "CAR_TYPE_KEY"
            r0.putExtra(r5, r1)
            android.content.ComponentName r5 = new android.content.ComponentName
            android.content.Context r1 = r4.context
            java.lang.Class<com.neusoft.na.byd.BydAppWidget> r2 = com.neusoft.na.byd.BydAppWidget.class
            r5.<init>(r1, r2)
            r0.setComponent(r5)
            android.content.Context r5 = r4.context
            r5.sendBroadcast(r0)
            r4.sendNaviState()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.byd.Byd8155fSystem.onGuideChanged(boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendFloatCardStatus(boolean flag) {
        Log.i(TAG, "sendFloatCardStatus: " + flag + ", pause:" + this.isPause);
        if (this.isPause) {
            return;
        }
        Intent intent = new Intent(flag ? "com.byd.card.open.FloatCard" : "com.byd.card.close.FloatCard");
        intent.addFlags(16777216);
        intent.putExtra(Car.PACKAGE_SERVICE, this.context.getPackageName());
        this.context.sendBroadcast(intent);
    }

    private final void sendNaviStatusBroadcast(boolean isStart) {
        Log.d(TAG, "sendNaviStatusBroadcast: " + isStart);
        Intent intent = new Intent(ACTION_STATE_CHANGED);
        intent.putExtra("packageName", this.context.getPackageName());
        intent.putExtra("state", isStart ? 1 : 0);
        this.context.sendBroadcast(intent);
        Log.d(TAG, "sendNaviStatusBroadcast: done");
    }

    private final void initBYDAutoDevice() {
        Log.d(TAG, "initBYDAutoDevice");
        this.bydAutoYunDevice = BYDAutoYunDevice.getInstance(this.context);
        this.bydAutoWhudDevice = BYDAutoWhudDevice.getInstance(this.context);
        this.bydAutoStatisticDevice = BYDAutoStatisticDevice.getInstance(this.context);
        Log.d(TAG, "BYDAutoStatisticDevice instance");
        this.bydAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this.context);
        Log.d(TAG, "BYDAutoBodyworkDevice instance");
        this.bydAutoInstrumentDevice = BYDAutoInstrumentDevice.getInstance(this.context);
        Log.i(TAG, "BYDAutoInstrumentDevice instance");
        this.bydAutoNaviDevice = BydAutoNaviDevice.getInstance(this.context);
        Log.i(TAG, "BydAutoNaviDevice instance");
        this.autoPanoramaDevice = BYDAutoPanoramaDevice.getInstance(this.context);
        Log.i(TAG, "BYDAutoPanoramaDevice instance");
        this.bydAutoTestDevice = BYDAutoTestDevice.getInstance(this.context);
        Log.i(TAG, "BYDAutoTestDevice instance");
        BYDAutoVersionDevice bYDAutoVersionDevice = BYDAutoVersionDevice.getInstance(this.context);
        try {
            Thread.sleep(300L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setOverseasMapSupplier(1);
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.bydAutoInstrumentDevice;
        if (bYDAutoInstrumentDevice != null) {
            setMileageUnit2BL(bYDAutoInstrumentDevice.getMileageUnit());
        }
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice2 = this.bydAutoInstrumentDevice;
        if (bYDAutoInstrumentDevice2 != null) {
            setLowFuelSignal2BL(bYDAutoInstrumentDevice2.getLowFuelWarnLightColor());
        }
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice3 = this.bydAutoInstrumentDevice;
        if (bYDAutoInstrumentDevice3 != null) {
            setLowBatterySignal2BL(bYDAutoInstrumentDevice3.getLowPowerBatteryWarnLightColor());
        }
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice4 = this.bydAutoInstrumentDevice;
        if (bYDAutoInstrumentDevice4 != null) {
            bYDAutoInstrumentDevice4.registerListener(new AbsBYDAutoInstrumentListener() { // from class: com.neusoft.na.byd.Byd8155fSystem.initBYDAutoDevice.4
                @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
                public void onMileageUnitChanged(int state) {
                    Log.i(Byd8155fSystem.TAG, "onMileageUnitChanged state == " + state);
                    super.onMileageUnitChanged(state);
                    Byd8155fSystem.this.setMileageUnit2BL(state);
                    BYDAutoStatisticDevice bYDAutoStatisticDevice = Byd8155fSystem.this.bydAutoStatisticDevice;
                    if (bYDAutoStatisticDevice != null) {
                        int elecDrivingRangeValue = bYDAutoStatisticDevice.getElecDrivingRangeValue();
                        Byd8155fSystem byd8155fSystem = Byd8155fSystem.this;
                        Log.i(Byd8155fSystem.TAG, "onMileageUnitChanged --> elecDrivingRangeValue == " + elecDrivingRangeValue);
                        byd8155fSystem.setEnduranceMileage2BL(elecDrivingRangeValue);
                    }
                }

                @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
                public void onLowFuelWarnLightColorChanged(int value) {
                    Log.i(Byd8155fSystem.TAG, "onLowFuelWarnLightColorChanged value == " + value);
                    super.onLowFuelWarnLightColorChanged(value);
                    Byd8155fSystem.this.setLowFuelSignal2BL(value);
                }

                @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
                public void onLowPowerBatteryWarnLightColorChanged(int value) {
                    Log.i(Byd8155fSystem.TAG, "onLowPowerBatteryWarnLightColorChanged value == " + value);
                    super.onLowPowerBatteryWarnLightColorChanged(value);
                    Byd8155fSystem.this.setLowBatterySignal2BL(value);
                }
            });
        }
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.bydAutoStatisticDevice;
        if (bYDAutoStatisticDevice != null) {
            setEnduranceMileage2BL(bYDAutoStatisticDevice.getElecDrivingRangeValue());
        }
        BYDAutoStatisticDevice bYDAutoStatisticDevice2 = this.bydAutoStatisticDevice;
        if (bYDAutoStatisticDevice2 != null) {
            setRemainingElectricQuantity2BL(bYDAutoStatisticDevice2.getElecPercentageValue());
        }
        BYDAutoStatisticDevice bYDAutoStatisticDevice3 = this.bydAutoStatisticDevice;
        if (bYDAutoStatisticDevice3 != null) {
            setRemainingElectricQuantityKWH2BL(bYDAutoStatisticDevice3.getEVRemainingBatteryPower());
        }
        BYDAutoStatisticDevice bYDAutoStatisticDevice4 = this.bydAutoStatisticDevice;
        if (bYDAutoStatisticDevice4 != null) {
            bYDAutoStatisticDevice4.registerListener(new AbsBYDAutoStatisticListener() { // from class: com.neusoft.na.byd.Byd8155fSystem.initBYDAutoDevice.8
                @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
                public void onElecDrivingRangeChanged(int value) {
                    Log.d(Byd8155fSystem.TAG, "onElecDrivingRangeChanged value == " + value);
                    super.onElecDrivingRangeChanged(value);
                    Byd8155fSystem.this.setEnduranceMileage2BL(value);
                }

                @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
                public void onElecPercentageChanged(double value) {
                    Log.d(Byd8155fSystem.TAG, "onElecPercentageChanged value == " + value);
                    super.onElecPercentageChanged(value);
                    Byd8155fSystem.this.setRemainingElectricQuantity2BL(value);
                }

                @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
                public void onEVRemainingBatteryPowerChanged(float value) {
                    super.onEVRemainingBatteryPowerChanged(value);
                    Log.i(Byd8155fSystem.TAG, "onEVRemainingBatteryPowerChanged value == " + value + ' ');
                    Byd8155fSystem.this.setRemainingElectricQuantityKWH2BL(value);
                }
            });
        }
        StringBuilder sb = new StringBuilder("bydAutoTestDevice.autoPowerType == ");
        BYDAutoTestDevice bYDAutoTestDevice = this.bydAutoTestDevice;
        Log.i(TAG, sb.append(bYDAutoTestDevice != null ? Integer.valueOf(bYDAutoTestDevice.getAutoPowerType()) : null).toString());
        BYDAutoTestDevice bYDAutoTestDevice2 = this.bydAutoTestDevice;
        if (bYDAutoTestDevice2 != null) {
            setFuelType2BL(bYDAutoTestDevice2.getAutoPowerType());
        }
        BydAutoNaviDevice bydAutoNaviDevice = this.bydAutoNaviDevice;
        if (bydAutoNaviDevice != null) {
            this.presentationMapType = bydAutoNaviDevice.getFocusMapType();
            Log.i(TAG, "it.focusMapType == " + bydAutoNaviDevice.getFocusMapType());
            int i = this.presentationMapType;
            this.presentationMapType = i != 0 ? i : 1;
            Log.i(TAG, "presentationMapType == " + this.presentationMapType);
            presentationMapTypeChanged();
            sendFocusDashboardMirrorEventInfo(this.presentationMapType);
            this.currentINSNavDisplayMode = bydAutoNaviDevice.getInsNavDisplayMode();
            Log.i(TAG, "currentINSNavDisplayMode == " + this.currentINSNavDisplayMode);
            sendINSNavDisplayModeDashboardMirrorEventInfo(this.currentINSNavDisplayMode, -1);
            bydAutoNaviDevice.registerListener(new AbsBYDAutoNaviListener() { // from class: com.neusoft.na.byd.Byd8155fSystem$initBYDAutoDevice$10$1
                @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
                public void onMapFocusChanged(int mapType) {
                    super.onMapFocusChanged(mapType);
                    Log.i("Byd8155fSystem", "onMapFocusChanged == " + mapType);
                    Byd8155fSystem byd8155fSystem = this.this$0;
                    if (mapType == 0) {
                        mapType = 1;
                    }
                    byd8155fSystem.presentationMapType = mapType;
                    this.this$0.presentationMapTypeChanged();
                    Byd8155fSystem byd8155fSystem2 = this.this$0;
                    byd8155fSystem2.sendFocusDashboardMirrorEventInfo(byd8155fSystem2.presentationMapType);
                }

                @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
                public void onINSNavDisplayMode(int value) {
                    super.onINSNavDisplayMode(value);
                    Log.i("Byd8155fSystem", "onINSNavDisplayMode == value == " + value + ", currentINSNavDisplayMode == " + this.this$0.currentINSNavDisplayMode);
                    Byd8155fSystem byd8155fSystem = this.this$0;
                    byd8155fSystem.sendINSNavDisplayModeDashboardMirrorEventInfo(value, byd8155fSystem.currentINSNavDisplayMode);
                }
            });
        }
        BYDAutoPanoramaDevice bYDAutoPanoramaDevice = this.autoPanoramaDevice;
        if (bYDAutoPanoramaDevice != null) {
            bYDAutoPanoramaDevice.registerListener(new AbsBYDAutoPanoramaListener() { // from class: com.neusoft.na.byd.Byd8155fSystem.initBYDAutoDevice.11
                @Override // android.hardware.bydauto.panorama.AbsBYDAutoPanoramaListener
                public void onPanoRotationChanged(int value) {
                    super.onPanoRotationChanged(value);
                    Log.d("AbsBYDAutoPanoramaListener", "onPanoRotationChanged: " + value);
                    Byd8155fSystem.this.sendOrientation(value);
                }
            });
        }
        String bydSystemVersion = bYDAutoVersionDevice != null ? bYDAutoVersionDevice.getBydSystemVersion() : null;
        Log.i(TAG, "initBYDAutoDevice: " + bydSystemVersion);
        if (bydSystemVersion != null) {
            ReqSetPlatformVersion reqSetPlatformVersion = new ReqSetPlatformVersion(bydSystemVersion);
            BydNavApiRequests bydNavApiRequests = this.bydSysApiRequests;
            if (bydNavApiRequests != null) {
                bydNavApiRequests.fireSetPlatformVersion(reqSetPlatformVersion);
            }
        }
        Log.i(TAG, "initBYDAutoDevice end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendFocusDashboardMirrorEventInfo(int mapType) {
        Log.i(TAG, "sendFocusDashboardMirrorEventInfo --> mapType == " + mapType);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            ReqAppFocusEventInfo reqAppFocusEventInfo = new ReqAppFocusEventInfo();
            reqAppFocusEventInfo.getArguments().setType(MessageType.NAVI_FOCUS);
            reqAppFocusEventInfo.getArguments().getInfo().setFocus(Boolean.valueOf(mapType == 1));
            commonSysApiInverseRequests.fireAppFocusEventInfo(reqAppFocusEventInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendINSNavDisplayModeDashboardMirrorEventInfo(int enterValue, int exitValue) {
        DashboardType dashboardType;
        DashboardType dashboardType2;
        Log.i(TAG, "sendINSNavDisplayModeDashboardMirrorEventInfo --> enterValue == " + enterValue + ", exitValue == " + exitValue);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            if (exitValue != -1) {
                ReqDashboardMirrorEventInfo reqDashboardMirrorEventInfo = new ReqDashboardMirrorEventInfo();
                reqDashboardMirrorEventInfo.getArguments().setType(MessageType.EXIT_DASHBOARD_NAVI_MIRROR);
                DashboardMirrorInfo info = reqDashboardMirrorEventInfo.getArguments().getInfo();
                if (exitValue == 2) {
                    dashboardType2 = DashboardType.DBT_SIMPLE_NAVIGATION;
                } else if (exitValue == 3) {
                    dashboardType2 = DashboardType.DBT_SMALL_SCREEN_NAVIGATION;
                } else if (exitValue == 4) {
                    dashboardType2 = DashboardType.DBT_FULL_SCREEN_NAVIGATION;
                } else {
                    dashboardType2 = DashboardType.DBT_DEFAULT;
                }
                info.setType(dashboardType2);
                commonSysApiInverseRequests.fireDashboardMirrorEventInfo(reqDashboardMirrorEventInfo);
                Log.i(TAG, "send exit -> exitReqDashboardMirrorEventInfo == " + reqDashboardMirrorEventInfo);
            } else {
                Log.i(TAG, "first in, has no old data.");
            }
            ReqDashboardMirrorEventInfo reqDashboardMirrorEventInfo2 = new ReqDashboardMirrorEventInfo();
            reqDashboardMirrorEventInfo2.getArguments().setType(MessageType.ENTER_DASHBOARD_NAVI_MIRROR);
            DashboardMirrorInfo info2 = reqDashboardMirrorEventInfo2.getArguments().getInfo();
            if (enterValue == 2) {
                dashboardType = DashboardType.DBT_SIMPLE_NAVIGATION;
            } else if (enterValue == 3) {
                dashboardType = DashboardType.DBT_SMALL_SCREEN_NAVIGATION;
            } else if (enterValue == 4) {
                dashboardType = DashboardType.DBT_FULL_SCREEN_NAVIGATION;
            } else {
                dashboardType = DashboardType.DBT_DEFAULT;
            }
            info2.setType(dashboardType);
            commonSysApiInverseRequests.fireDashboardMirrorEventInfo(reqDashboardMirrorEventInfo2);
            Log.i(TAG, "send enter -> enterReqDashboardMirrorEventInfo == " + reqDashboardMirrorEventInfo2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendMapActivateAndPrivacyPolicyToByd(boolean activationStatus, boolean privacyPolicyStatus) {
        Log.i(TAG, "[sendMapActivateAndPrivacyPolicyToByd] --> activationStatus == " + activationStatus + ", privacyPolicyStatus == " + privacyPolicyStatus);
        BydAutoNaviDevice bydAutoNaviDevice = this.bydAutoNaviDevice;
        if (bydAutoNaviDevice != null) {
            BydUtils.INSTANCE.sendMapActivate(this.context, bydAutoNaviDevice, activationStatus);
            BydUtils.INSTANCE.sendPrivacyPolicy(this.context, bydAutoNaviDevice, privacyPolicyStatus);
        }
        Intent intent = new Intent(IMapPresentationComponent.INTENT_ACTION_ACTIVATION_PRIVACY_POLICY_STATUS_CHANGED);
        intent.putExtra("activationStatus", activationStatus);
        intent.putExtra("privacyPolicyStatus", privacyPolicyStatus);
        this.context.sendBroadcast(intent);
    }

    private final void setMapFocusChanged2BL(int mapType) {
        ClusterMapFocusMode clusterMapFocusMode;
        Log.i(TAG, "[setMapFocusChanged2BL] --> mapType == " + mapType);
        ReqSetClusterMapFocus reqSetClusterMapFocus = new ReqSetClusterMapFocus();
        ReqSetClusterMapFocus.Arguments arguments = reqSetClusterMapFocus.getArguments();
        if (mapType == 1) {
            clusterMapFocusMode = ClusterMapFocusMode.LOCAL_MAP_MODE;
        } else if (mapType == 2) {
            clusterMapFocusMode = ClusterMapFocusMode.CAR_PLAY_MODE;
        } else if (mapType == 3) {
            clusterMapFocusMode = ClusterMapFocusMode.ANDROID_AUTO_MODE;
        } else {
            clusterMapFocusMode = ClusterMapFocusMode.DEFAULT_MODE;
        }
        arguments.setFocusMode(clusterMapFocusMode);
        BydNavApiRequests bydNavApiRequests = this.bydSysApiRequests;
        if (bydNavApiRequests != null) {
            bydNavApiRequests.fireSetClusterMapFocus(reqSetClusterMapFocus);
        }
        Log.i(TAG, "[setMapFocusChanged2BL] --> send == " + reqSetClusterMapFocus.getArguments().getFocusMode());
    }

    private final void setNaviMuteState2BL(Boolean isVolumeGroupMuted) {
        Log.i(TAG, "[setNaviMuteState2BL] --> isVolumeGroupMuted == " + isVolumeGroupMuted);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireSetNaviMuteModeComm(new ReqSetNaviMuteModeComm.Arguments().setMuteState(Intrinsics.areEqual((Object) isVolumeGroupMuted, (Object) true) ? MuteTypeComm.Navi_Com_Silent : MuteTypeComm.Navi_Com_NonSilent));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLowFuelSignal2BL(int value) {
        Log.i(TAG, "[setLowFuelSignal2BL] --> value == " + value);
        ReqSetLowFuelSignal reqSetLowFuelSignal = new ReqSetLowFuelSignal();
        reqSetLowFuelSignal.getArguments().setIsLowFuel(Boolean.valueOf(value == 2 || value == 3));
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireSetLowFuelSignal(reqSetLowFuelSignal);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLowBatterySignal2BL(int value) {
        Log.i(TAG, "[setLowBatterySignal2BL] --> value == " + value);
        ReqSetLowBatterySignal reqSetLowBatterySignal = new ReqSetLowBatterySignal();
        reqSetLowBatterySignal.getArguments().setIsLowBattery(Boolean.valueOf(value == 2 || value == 3));
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireSetLowBatterySignal(reqSetLowBatterySignal);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRemainingElectricQuantity2BL(double value) {
        Log.d(TAG, "setRemainingElectricQuantity2BL value == " + value);
        ReqSetRemainingElectricQuantity reqSetRemainingElectricQuantity = new ReqSetRemainingElectricQuantity();
        reqSetRemainingElectricQuantity.getArguments().setRemainingElectricQuantity(Long.valueOf((long) value));
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireSetRemainingElectricQuantity(reqSetRemainingElectricQuantity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRemainingElectricQuantityKWH2BL(float value) {
        Log.d(TAG, "setRemainingElectricQuantityKWH2BL value == " + value);
        ReqSetRemainingElectricQuantityKWH reqSetRemainingElectricQuantityKWH = new ReqSetRemainingElectricQuantityKWH();
        reqSetRemainingElectricQuantityKWH.getArguments().setRemainingElectricQuantityKWH(Float.valueOf(value));
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireSetRemainingElectricQuantityKWH(reqSetRemainingElectricQuantityKWH);
        }
    }

    private final void setFuelType2BL(int value) {
        FuelType fuelType;
        Log.i(TAG, "[setFuelType2BL] value == " + value);
        if (value == 1) {
            fuelType = FuelType.PHEV;
        } else if (value == 2) {
            fuelType = FuelType.GASOLINE;
        } else {
            fuelType = FuelType.EV;
        }
        Log.i(TAG, "[setFuelType2BL] type == " + fuelType);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireSetFuelType(new ReqSetFuelType().getArguments().setType(fuelType));
        }
    }

    private final void setBatteryCapacityWH2BL(int value) {
        Log.d(TAG, "setBatteryCapacityWH2BL value == " + value);
        ReqSetBatteryCapacityWH reqSetBatteryCapacityWH = new ReqSetBatteryCapacityWH();
        reqSetBatteryCapacityWH.getArguments().setCapacityWH(Float.valueOf(value));
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireSetBatteryCapacityWH(reqSetBatteryCapacityWH);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setEnduranceMileage2BL(int value) {
        Log.d(TAG, "setEnduranceMileage2BL value == " + value);
        ReqSetEnduranceMileage reqSetEnduranceMileage = new ReqSetEnduranceMileage();
        reqSetEnduranceMileage.getArguments().setAvailableMileage(Long.valueOf(value));
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireSetEnduranceMileage(reqSetEnduranceMileage);
        }
    }

    private final void setDayNightMode2BL(String it) {
        ReqOnDayNightChanged reqOnDayNightChanged = new ReqOnDayNightChanged();
        reqOnDayNightChanged.getArguments().setDayNightStatus(Intrinsics.areEqual(it, "1") ? DayNightStatus.DAYNIGHT_STATUS_NIGHT : DayNightStatus.DAYNIGHT_STATUS_DAY);
        Log.d(TAG, "fireOnDayNightChanged[" + reqOnDayNightChanged.getArguments().getDayNightStatus() + ']');
        INSTANCE.getLogger().debug("fireOnDayNightChanged[" + reqOnDayNightChanged.getArguments().getDayNightStatus() + ']');
        BydSysApiInverseRequests bydSysApiInverseRequests = this.bydSysApiInverseRequests;
        if (bydSysApiInverseRequests != null) {
            bydSysApiInverseRequests.fireOnDayNightChanged(reqOnDayNightChanged);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setMileageUnit2BL(int state) {
        LengthUnit lengthUnit;
        Log.d(TAG, "setMileageUnit2BL state == " + state);
        ReqSetUnitOfMeasurement reqSetUnitOfMeasurement = new ReqSetUnitOfMeasurement();
        ReqSetUnitOfMeasurement.Arguments arguments = reqSetUnitOfMeasurement.getArguments();
        if (state == 1) {
            lengthUnit = LengthUnit.METRIC_UNIT;
        } else if (state == 2) {
            lengthUnit = LengthUnit.IMPERIAL_UNIT;
        } else {
            lengthUnit = LengthUnit.INVALID;
        }
        arguments.setBaseUnit(lengthUnit);
        Log.d(TAG, "setMileageUnit2BL --> BL  == " + reqSetUnitOfMeasurement.getArguments().getBaseUnit());
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireSetUnitOfMeasurement(reqSetUnitOfMeasurement);
        }
    }

    @Override // com.neusoft.na.common.ClientEndpointMangerCallback
    public void onServerDisconnect() {
        Log.d(TAG, "onServerDisconnect");
        INSTANCE.getLogger().debug("onServerDisconnect");
        unregisterObserver();
        this.commonRequests = null;
        this.commonNotifications = null;
        this.bydSysApiInverseNotifications = null;
        this.bydNavApiNotifications = null;
        try {
            Iterator<IGettableAttribute<?>> it = this.remoteAttributes.iterator();
            while (it.hasNext()) {
                it.next().signOut();
            }
            this.remoteAttributes.clear();
            Iterator<Subscription> it2 = this.remoteSubscriptions.iterator();
            while (it2.hasNext()) {
                it2.next().unsubscribe();
            }
            this.remoteSubscriptions.clear();
        } catch (Exception unused) {
            Log.i(TAG, "[onServerDisconnect] ...");
        }
    }

    @Override // com.neusoft.na.common.IExtensionComponent
    public void setTtsCallback(ITTSListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Log.d(TAG, "[TTSTAG] setTtsCallback");
        INSTANCE.getLogger().debug("[TTSTAG] setTtsCallback");
        this.ttsListener = listener;
    }

    @Override // com.neusoft.na.common.IExtensionComponent
    public void setTimeFormatCallback(ITimeFormatListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Log.d(TAG, "setTimeFormatCallback");
        INSTANCE.getLogger().debug("setTimeFormatCallback");
    }

    @Override // com.neusoft.na.common.IExtensionComponent
    public void setLanguageOnOffCallback(ILanOnOffListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Log.d(TAG, "setLanguageOnOffCallback");
        INSTANCE.getLogger().debug("setLanguageOnOffCallback");
        this.lanOnOffListener = listener;
    }

    private final void sendNaviState() {
        Log.i(TAG, "[sendNaviState] --> naviGuideState == " + this.naviGuideState + ", naviForegroundState == " + this.naviForegroundState);
        int i = this.naviGuideState ? 1 : 2;
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.bydAutoInstrumentDevice;
        if (bYDAutoInstrumentDevice != null) {
            bYDAutoInstrumentDevice.sendNaviState(1, i, this.naviForegroundState, "com.neusoft.na.navigation");
        }
    }

    private final SystemSwitchStatus getSystemSwitchStatus(String name) {
        int i = Settings.System.getInt(this.context.getContentResolver(), name, 0);
        Log.i(TAG, "getSystemSwitchStatus:" + name + '=' + i);
        if (i == 1) {
            return SystemSwitchStatus.SWITCH_ON;
        }
        return SystemSwitchStatus.SWITCH_OFF;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void presentationMapTypeChanged() {
        Log.i(TAG, "presentationMapTypeChanged");
        setMapFocusChanged2BL(this.presentationMapType);
        if (this.presentationMapType == 1) {
            if (this.naviGuideState) {
                Log.i(TAG, "sendNaviState to instrument >> start");
                BydAutoNaviDevice bydAutoNaviDevice = this.bydAutoNaviDevice;
                if (bydAutoNaviDevice != null) {
                    bydAutoNaviDevice.sendNaviState(1, 1);
                }
                BYDAutoWhudDevice bYDAutoWhudDevice = this.bydAutoWhudDevice;
                if (bYDAutoWhudDevice != null) {
                    bYDAutoWhudDevice.setNaviState(2);
                }
            } else {
                Log.i(TAG, "sendNaviState to instrument >> stop");
                BydAutoNaviDevice bydAutoNaviDevice2 = this.bydAutoNaviDevice;
                if (bydAutoNaviDevice2 != null) {
                    bydAutoNaviDevice2.sendNaviState(1, 2);
                }
                BYDAutoWhudDevice bYDAutoWhudDevice2 = this.bydAutoWhudDevice;
                if (bYDAutoWhudDevice2 != null) {
                    bYDAutoWhudDevice2.setNaviState(4);
                }
            }
        } else {
            Log.i(TAG, "send NaviGuideStop 2 BL.");
            CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
            if (commonSysApiInverseRequests != null) {
                commonSysApiInverseRequests.fireReqNaviGuideStop(new ReqReqNaviGuideStop());
            }
        }
        sendFocusDashboardMirrorEventInfo(this.presentationMapType);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ClusterViewMode clusterViewMode;
        BydNavApiRequests bydNavApiRequests;
        BydNavApiRequests bydNavApiRequests2;
        CommonSysApiInverseRequests commonSysApiInverseRequests;
        Intrinsics.checkNotNullParameter(context, "context");
        Log.d(TAG, "onReceive: " + (intent != null ? intent.getAction() : null));
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, ACTION_WIDGET_TYPE)) {
            Integer numValueOf = intent != null ? Integer.valueOf(intent.getIntExtra(WIDGET_TYPE_KEY, -1)) : null;
            Log.i(TAG, "onReceive ACTION_WIDGET_TYPE " + numValueOf);
            sendToBl(context, numValueOf != null ? numValueOf.intValue() : -1);
            return;
        }
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "com.neusoft.byd.evtool")) {
            Integer numValueOf2 = intent != null ? Integer.valueOf(intent.getIntExtra("type", -1)) : null;
            Integer numValueOf3 = intent != null ? Integer.valueOf(intent.getIntExtra(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, -1)) : null;
            Log.d(TAG, "onReceive evtool [" + numValueOf2 + "] [" + numValueOf3 + ']');
            INSTANCE.getLogger().debug("onReceive evtool [" + numValueOf2 + "] [" + numValueOf3 + ']');
            if (numValueOf2 != null && numValueOf2.intValue() == 0) {
                setLowBatterySignal2BL(numValueOf3 != null ? numValueOf3.intValue() : 0);
                return;
            }
            if (numValueOf2 != null && numValueOf2.intValue() == 1) {
                setRemainingElectricQuantityKWH2BL((numValueOf3 != null ? numValueOf3.intValue() : 0) * 0.1f);
                return;
            }
            if (numValueOf2 != null && numValueOf2.intValue() == 2) {
                setRemainingElectricQuantity2BL(numValueOf3 != null ? numValueOf3.intValue() : 0);
                return;
            }
            if (numValueOf2 != null && numValueOf2.intValue() == 4) {
                setEnduranceMileage2BL(numValueOf3 != null ? numValueOf3.intValue() : 0);
                return;
            }
            if (numValueOf2 != null && numValueOf2.intValue() == 5) {
                CommonSysApiInverseRequests commonSysApiInverseRequests2 = this.commonRequests;
                if (commonSysApiInverseRequests2 != null) {
                    commonSysApiInverseRequests2.fireSetFuelType(new ReqSetFuelType(FuelType.getByValue(numValueOf3)));
                    return;
                }
                return;
            }
            if (numValueOf2 == null || numValueOf2.intValue() != 10 || (commonSysApiInverseRequests = this.commonRequests) == null) {
                return;
            }
            commonSysApiInverseRequests.fireSetLowFuelSignal(new ReqSetLowFuelSignal(Boolean.valueOf(numValueOf3 != null && numValueOf3.intValue() == 1)));
            return;
        }
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "com.neusoft.na.navigation.daynight.switch")) {
            int intExtra = intent.getIntExtra(ServiceBase.PAYLOAD_MODE, 1);
            Log.e("uiModeManager", "mode == " + intExtra);
            setDayNightMode2BL(intExtra == 2 ? "1" : FileUtils.DEFAULT_VERSION);
            updatePrivacyUrl(context);
            return;
        }
        if (Intrinsics.areEqual("android.broadcast.result.virtual_vin_action", intent != null ? intent.getAction() : null)) {
            Integer numValueOf4 = intent != null ? Integer.valueOf(intent.getIntExtra("supplier", -1)) : null;
            if (numValueOf4 != null && numValueOf4.intValue() == 1) {
                removeMessages(WHAT);
                int intExtra2 = intent.getIntExtra(AoapService.KEY_RESULT, 2);
                Log.i(TAG, "activation: result " + intExtra2);
                sendResult(intExtra2 == 1);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "com.neusoft.na.navigation.multiWindowMode")) {
            boolean booleanExtra = intent.getBooleanExtra(OneCoreService.PAYLOAD_IN_MULTI_WINDOW_MODE, false);
            Log.d(TAG, "isInMultiWindowMode == " + booleanExtra + ", " + this.openFloatCard);
            Log.d(TAG, "requestsCommon?.fireSetScreenMode(reqSetScreenMode)");
            if (booleanExtra) {
                return;
            }
            sendFloatCardStatus(this.openFloatCard);
            return;
        }
        if (Intrinsics.areEqual(ACTION_GPS, intent != null ? intent.getAction() : null)) {
            ReqSkipFunction reqSkipFunction = new ReqSkipFunction(true, FunctionTypes.GPS_FUNC);
            BydSysApiInverseRequests bydSysApiInverseRequests = this.bydSysApiInverseRequests;
            if (bydSysApiInverseRequests != null) {
                bydSysApiInverseRequests.fireSkipFunction(reqSkipFunction);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual("android.intent.action.ACTION_SHUTDOWN", intent != null ? intent.getAction() : null)) {
            Boolean boolValueOf = intent != null ? Boolean.valueOf(intent.getBooleanExtra("from_quickboot", false)) : null;
            Log.i(TAG, "onReceive: flag " + boolValueOf);
            if (!Intrinsics.areEqual((Object) boolValueOf, (Object) true) || (bydNavApiRequests2 = this.bydSysApiRequests) == null) {
                return;
            }
            bydNavApiRequests2.firePowerSavingModeChanged(new ReqPowerSavingModeChanged(PowerSavingModeStatus.POWER_SAVING_MODE));
            return;
        }
        if (Intrinsics.areEqual("android.intent.action.BOOT_COMPLETED", intent != null ? intent.getAction() : null)) {
            Boolean boolValueOf2 = intent != null ? Boolean.valueOf(intent.getBooleanExtra("from_quickboot", false)) : null;
            Log.i(TAG, "onReceive: flag " + boolValueOf2);
            if (!Intrinsics.areEqual((Object) boolValueOf2, (Object) true) || (bydNavApiRequests = this.bydSysApiRequests) == null) {
                return;
            }
            bydNavApiRequests.firePowerSavingModeChanged(new ReqPowerSavingModeChanged(PowerSavingModeStatus.RELEASE_POWER_SAVING_MODE));
            return;
        }
        if (Intrinsics.areEqual(ACTION_RESUME, intent != null ? intent.getAction() : null)) {
            Log.i(TAG, "onReceive: openFloatCard " + this.openFloatCard);
            this.isPause = false;
            sendFloatCardStatus(this.openFloatCard);
            return;
        }
        if (Intrinsics.areEqual(ACTION_PAUSE, intent != null ? intent.getAction() : null)) {
            this.isPause = true;
            return;
        }
        Intrinsics.checkNotNull(intent);
        if (Intrinsics.areEqual(intent.getAction(), "com.neusoft.na.navigation.presentation.mode.switch")) {
            Log.i(TAG, "receive com.neusoft.na.navigation.presentation.mode.switch");
            int intExtra3 = intent.getIntExtra(ServiceBase.PAYLOAD_MODE, -1);
            Log.i(TAG, "mode == " + intExtra3);
            BydNavApiRequests bydNavApiRequests3 = this.bydSysApiRequests;
            if (bydNavApiRequests3 != null) {
                ReqClusterViewModeChanged reqClusterViewModeChanged = new ReqClusterViewModeChanged();
                ReqClusterViewModeChanged.Arguments arguments = reqClusterViewModeChanged.getArguments();
                if (intExtra3 == 1) {
                    clusterViewMode = ClusterViewMode.CLOSE_SCREEN_MODE;
                } else if (intExtra3 == 2) {
                    clusterViewMode = ClusterViewMode.TURN_BY_TURN_MODE;
                } else if (intExtra3 == 3) {
                    clusterViewMode = ClusterViewMode.SMALL_SCREEN_MODE;
                } else if (intExtra3 == 4) {
                    clusterViewMode = ClusterViewMode.LARGE_SCREEN_MODE;
                } else {
                    clusterViewMode = ClusterViewMode.DEFAULT_MODE;
                }
                arguments.setViewMode(clusterViewMode);
                bydNavApiRequests3.fireClusterViewModeChanged(reqClusterViewModeChanged);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(intent.getAction(), ACTION_HMI_FOCUS_CHANGED)) {
            Log.i(TAG, "action == " + intent.getAction());
            int intExtra4 = intent.getIntExtra(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, 0);
            Log.i(TAG, "value == " + intExtra4);
            setHmiFocusChanged(intExtra4 == 0);
            return;
        }
        if (Intrinsics.areEqual(intent.getAction(), "android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
            if (TextUtils.equals(intent.getStringExtra(ACTION_SYSTEM_REASON), ACTION_SYSTEM_HOME_KEY)) {
                Log.i(TAG, "home key down");
                Intent intent2 = new Intent();
                intent2.setAction("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.HOME");
                ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent2, 0);
                if (resolveInfoResolveActivity != null) {
                    Log.i(TAG, "resolveInfo.activityInfo.packageName == " + resolveInfoResolveActivity.activityInfo.packageName);
                    if (Intrinsics.areEqual(resolveInfoResolveActivity.activityInfo.packageName, context.getPackageName())) {
                        ReqSetDesktopMode reqSetDesktopMode = new ReqSetDesktopMode();
                        reqSetDesktopMode.getArguments().setDesktopMode(DesktopMode.MAP_DESKTOP);
                        BydNavApiRequests bydNavApiRequests4 = this.bydSysApiRequests;
                        if (bydNavApiRequests4 != null) {
                            bydNavApiRequests4.fireSetDesktopMode(reqSetDesktopMode);
                        }
                        Log.i(TAG, "fireSetDesktopMode --> reqSetDesktopMode == " + reqSetDesktopMode + " (homeKey)");
                        Log.i(TAG, "byd8155F is launcher and top, send HMI to home page.");
                        CommonSysApiInverseRequests commonSysApiInverseRequests3 = this.commonRequests;
                        if (commonSysApiInverseRequests3 != null) {
                            commonSysApiInverseRequests3.fireMigrateNaviHMIPage(new ReqMigrateNaviHMIPage());
                            return;
                        }
                        return;
                    }
                    ReqSetDesktopMode reqSetDesktopMode2 = new ReqSetDesktopMode();
                    reqSetDesktopMode2.getArguments().setDesktopMode(DesktopMode.WALLPAPER_DESKTOP);
                    BydNavApiRequests bydNavApiRequests5 = this.bydSysApiRequests;
                    if (bydNavApiRequests5 != null) {
                        bydNavApiRequests5.fireSetDesktopMode(reqSetDesktopMode2);
                    }
                    Log.i(TAG, "fireSetDesktopMode --> reqSetDesktopMode == " + reqSetDesktopMode2 + " (homeKey)");
                    Log.i(TAG, "byd8155F is not launcher, do not send HMI to home page.");
                    return;
                }
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(intent.getAction(), ACTION_SET_DEFAULT_LAUNCHER)) {
            setDesktopModeToBl();
            return;
        }
        if (Intrinsics.areEqual(intent.getAction(), ACTION_NAVI_FOREGROUND_CHANGED)) {
            this.naviForegroundState = intent.getBooleanExtra("isForeground", false);
            sendNaviState();
            return;
        }
        if (Intrinsics.areEqual(intent.getAction(), ACTION_PERMISSION_ALL_SUCCESS)) {
            initClientEndpointManger();
            return;
        }
        if (Intrinsics.areEqual("com.neusoft.na.navigation.action.openmap", intent.getAction())) {
            String stringExtra = intent.getStringExtra("uriString");
            Log.i(TAG, "onReceive open map: " + (stringExtra != null ? Integer.valueOf(stringExtra.length()) : null));
            if (TextUtils.isEmpty(stringExtra)) {
                return;
            }
            Uri uri = Uri.parse("http://" + stringExtra);
            Intrinsics.checkNotNullExpressionValue(uri, "parse(\"http://$uriString\")");
            openMap(uri);
        }
    }

    private final void openMap(Uri uri) {
        Log.i(TAG, UDERequestsEnum.OPEN_MAP);
        String queryParameter = uri.getQueryParameter("q");
        if (queryParameter == null) {
            queryParameter = "";
        }
        String queryParameter2 = uri.getQueryParameter("name");
        String str = queryParameter2 != null ? queryParameter2 : "";
        String latLonString = Uri.parse(uri.getAuthority()).getSchemeSpecificPart();
        Intrinsics.checkNotNullExpressionValue(latLonString, "latLonString");
        List listSplit$default = StringsKt.split$default((CharSequence) latLonString, new String[]{ListUtils.DEFAULT_JOIN_SEPARATOR}, false, 0, 6, (Object) null);
        Log.i(TAG, "openMap size" + listSplit$default.size());
        if (listSplit$default.size() >= 2) {
            double d = Double.parseDouble((String) listSplit$default.get(0));
            double d2 = Double.parseDouble((String) listSplit$default.get(1));
            PlaceInfo placeInfo = new PlaceInfo();
            placeInfo.setLatitude(Double.valueOf(d));
            placeInfo.setLongitude(Double.valueOf(d2));
            placeInfo.setName(str);
            placeInfo.setAddress(queryParameter);
            ReqReqSmartNaviStart reqReqSmartNaviStart = new ReqReqSmartNaviStart(placeInfo);
            BydNavApiRequests bydNavApiRequests = this.bydSysApiRequests;
            if (bydNavApiRequests != null) {
                bydNavApiRequests.fireReqSmartNaviStart(reqReqSmartNaviStart);
            }
            Log.i(TAG, "openMap done");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendOrientation(int value) {
        Log.i(TAG, "sendOrientation: " + value);
        ReqSetScreenDirection reqSetScreenDirection = new ReqSetScreenDirection(value == 1 ? ScreenDirectionStatus.HORIZONTAL : ScreenDirectionStatus.VERTICAL);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireSetScreenDirection(reqSetScreenDirection);
        }
        Log.i(TAG, "sendOrientation done");
    }

    private final void setDesktopModeToBl() {
        Log.i(TAG, "[setDesktopModeToBl]");
        String currentLauncherPackageName = getCurrentLauncherPackageName();
        ReqSetDesktopMode reqSetDesktopMode = new ReqSetDesktopMode();
        if (Intrinsics.areEqual(currentLauncherPackageName, this.context.getPackageName())) {
            reqSetDesktopMode.getArguments().setDesktopMode(DesktopMode.MAP_DESKTOP);
        } else {
            reqSetDesktopMode.getArguments().setDesktopMode(DesktopMode.WALLPAPER_DESKTOP);
        }
        BydNavApiRequests bydNavApiRequests = this.bydSysApiRequests;
        if (bydNavApiRequests != null) {
            bydNavApiRequests.fireSetDesktopMode(reqSetDesktopMode);
        }
        Log.i(TAG, "fireSetDesktopMode --> reqSetDesktopMode == " + reqSetDesktopMode);
    }

    private final void updatePrivacyUrl(Context context) {
        String privacyUrlForHMI = new ChangeHistoryUtil(context).getPrivacyUrlForHMI();
        Log.i(TAG, "updatePrivacyUrl:" + privacyUrlForHMI);
        ReqSetPrivacyPolicyFullPath reqSetPrivacyPolicyFullPath = new ReqSetPrivacyPolicyFullPath(new FileFullPath(privacyUrlForHMI));
        BydNavApiRequests bydNavApiRequests = this.bydSysApiRequests;
        if (bydNavApiRequests != null) {
            bydNavApiRequests.fireSetPrivacyPolicyFullPath(reqSetPrivacyPolicyFullPath);
        }
    }

    private final void sendToBl(Context context, int type) {
        BydNavApiRequests bydNavApiRequests;
        if (type > 4 || type < 1) {
            return;
        }
        if (type == 1 || type == 2) {
            BydNavApiRequests bydNavApiRequests2 = this.bydSysApiRequests;
            if (bydNavApiRequests2 != null) {
                bydNavApiRequests2.fireRequestGoHomeOrCompany(new ReqRequestGoHomeOrCompany(FavoriteType.getByValue(Integer.valueOf(type - 1))));
                return;
            }
            return;
        }
        if (type != 3) {
            if (type == 4 && (bydNavApiRequests = this.bydSysApiRequests) != null) {
                bydNavApiRequests.fireRequestShowSearchResultPage();
                return;
            }
            return;
        }
        BydNavApiRequests bydNavApiRequests3 = this.bydSysApiRequests;
        if (bydNavApiRequests3 != null) {
            bydNavApiRequests3.fireRequestShowSearchAlongRoutePage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0498  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void activation(api.structs.com.neusoft.common.ActivateResultDetails r18) {
        /*
            Method dump skipped, instruction units count: 1622
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.byd.Byd8155fSystem.activation(api.structs.com.neusoft.common.ActivateResultDetails):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendResult(boolean flag) {
        ActivateStatus activateStatus;
        if (flag) {
            activateStatus = ActivateStatus.E_SUCCESS;
        } else {
            activateStatus = ActivateStatus.E_FAILURE;
        }
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireFeedbackActivateResult(new ReqFeedbackActivateResult.Arguments(activateStatus));
        }
    }

    private final void setHmiFocusChanged(boolean value) {
        Log.i(TAG, "[setHmiFocusChanged] --> value == " + value);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.commonRequests;
        if (commonSysApiInverseRequests != null) {
            ReqHMIFocusChanged reqHMIFocusChanged = new ReqHMIFocusChanged();
            reqHMIFocusChanged.getArguments().setHMIFocusStatus(value ? FocusStatus.E_FOREGROUND : FocusStatus.E_BACKGROUND);
            Log.i(TAG, "[setHmiFocusChanged] send == " + value);
            commonSysApiInverseRequests.fireHMIFocusChanged(reqHMIFocusChanged);
        }
    }

    private final String getSystemProperties(Context context, String key) {
        String str;
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            Object objInvoke = clsLoadClass.getMethod("get", String.class).invoke(clsLoadClass, key);
            Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.String");
            str = (String) objInvoke;
        } catch (Exception e) {
            Log.e(TAG, "get: " + e.getMessage());
            str = "";
        }
        Log.i(TAG, "[getSystemProperties] --> key == " + key + ", value == " + str);
        return str;
    }

    private final int getSteeringWheelPosition() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("getInt", String.class, Integer.TYPE);
            method.setAccessible(true);
            Object objInvoke = method.invoke(cls, "persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
            if (objInvoke == null || !(objInvoke instanceof Integer)) {
                return 0;
            }
            return ((Number) objInvoke).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private final String getCurrentLauncherPackageName() {
        Log.i(TAG, "[getCurrentLauncherPackageName]");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = this.context.getPackageManager().resolveActivity(intent, 0);
        if (resolveInfoResolveActivity != null) {
            Log.i(TAG, "resolveInfo.activityInfo.packageName == " + resolveInfoResolveActivity.activityInfo.packageName);
            String str = resolveInfoResolveActivity.activityInfo.packageName;
            Log.i(TAG, "packageName == " + str);
            return str;
        }
        Log.i(TAG, "get launcher packageName == null");
        return null;
    }

    @Override // com.neusoft.na.common.IBydSystemAdapterComponent
    public boolean isRightRudderMode() {
        int iIntValue;
        Log.i(TAG, "[isRightRudderMode]");
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("getInt", String.class, Integer.TYPE);
            method.setAccessible(true);
            Object objInvoke = method.invoke(cls, "persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
            Log.i(TAG, "obj == " + objInvoke);
            iIntValue = (objInvoke == null || !(objInvoke instanceof Integer)) ? 0 : ((Number) objInvoke).intValue();
            try {
                Log.i(TAG, "position == " + iIntValue);
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
            iIntValue = 0;
        }
        boolean z = iIntValue == 1;
        Log.i(TAG, "isRightRudderMode == " + z);
        return z;
    }

    @Override // com.neusoft.na.common.IBydSystemAdapterComponent
    public void initTTS(Context context) {
        Log.i(TAG, "[initTTS]");
        this.ttsPlayer = TTSPlayer.getInstance(context, "/navi_tts/NeusoftTTS");
    }

    @Override // com.neusoft.na.common.IBydSystemAdapterComponent
    public void switchTtsLanguage(String voiceName) {
        Log.i(TAG, "[switchTtsLanguage] --> voiceName == " + voiceName);
        TTSPlayer tTSPlayer = this.ttsPlayer;
        if (tTSPlayer != null) {
            tTSPlayer.switchTtsLanguage(voiceName);
        }
    }

    @Override // com.neusoft.na.common.IBydSystemAdapterComponent
    public void playTts(String text) {
        Log.i(TAG, "[playTts] --> text == " + text);
        TTSPlayer tTSPlayer = this.ttsPlayer;
        if (tTSPlayer != null) {
            tTSPlayer.playTtsWithVoiceId(text);
        }
    }

    @Override // com.neusoft.na.common.IBydSystemAdapterComponent
    public void stopTts() {
        Log.i(TAG, "[stopTts]");
        TTSPlayer tTSPlayer = this.ttsPlayer;
        if (tTSPlayer != null) {
            tTSPlayer.stopTts();
        }
    }

    @Override // com.neusoft.na.common.IBydSystemAdapterComponent
    public void destroyTts() {
        Log.i(TAG, "[destroyTts]");
        TTSPlayer tTSPlayer = this.ttsPlayer;
        if (tTSPlayer != null) {
            tTSPlayer.destroyTts();
        }
    }

    @Override // com.neusoft.na.common.IBydSystemAdapterComponent
    public void setTtsCallback(BydSystemAdapterCallback callback) {
        Log.i(TAG, "[setTtsCallback]");
        TTSPlayer tTSPlayer = this.ttsPlayer;
        if (tTSPlayer != null) {
            tTSPlayer.setTtsCallback(callback);
        }
    }

    @Override // com.neusoft.na.common.IBydSystemAdapterComponent
    public void startListenCarSpeedChange() throws InterruptedException {
        Log.i(TAG, "[startCarSpeed]");
        new Byd8155fSystemAdapter(this.context).start();
    }

    @Override // com.neusoft.na.common.IBydSystemAdapterComponent
    public int getTtsAudioUsage() {
        Log.i(TAG, "usage == 12");
        return 12;
    }
}
