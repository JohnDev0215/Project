package com.neusoft.na.common;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import api.notifications.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseNotifications;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyAbandonSpeak;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyAccessCodingAvailable;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyAppLocale;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyApplyUpdateStatus;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyCallUpStatus;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyCheckDBAvailable;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyCloseAndRestartApp;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyCloseApp;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyCloseMainActivity;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyCurrentLocation;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyDBDataIntegrityCheck;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyDeleteAllDBData;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyDeleteFile;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyExportDirectoryToUDisk;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyLaunchApp;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyLocalDBVersionInfo;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyMonitorCOMMStatus;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyNaviDayNightStatusChange;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyNaviSoftwareVersionInfo;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyNavigationRunningStatus;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyNotifyOpenGPS;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyOnKeyDown;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyPlayBeepTone;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyPlaySoundEffect;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyPostCustomEvent;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyReadFile;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyRequestInstallationPath;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyRequestUpdateExternalStoragePath;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyShowDiagnoseSetting;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyShowToastMessage;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifySpeak;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyStorageSpaceStatus;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifySwitchArgumentedReality;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifySwitchNaviToBackground;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifySwitchTraceLog;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyWriteFile;
import api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnAccessCodingAvailable;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnDeleteAllDBData;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnDeleteFile;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnExportDirectoryToUDisk;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnStopOneCoreStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqOnWriteFile;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqReqSwitchDayNightMode;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetBrightnessValue;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetClusterSurfaceStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetDownloadPath;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetExtCloudServerStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetInstallationPath;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetInternetSwitchStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetMapScreenStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetMapSurfaceStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetOnKeyDownValue;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetOnlineFeatureAvailableStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetScreenDirection;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetScreenMode;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetSystemLocale;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetSystemTime;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetSystemTimeChange;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetSystemTimeFormat;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetSystemTimeZoneOffset;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetVehicleBrakingStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetWidgetSurfaceStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqStartUpState;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqStorageSpace;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSwitchNaviLayoutDirection;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqUpdateExternalStoragePath;
import api.structs.com.neusoft.common.AROnOff;
import api.structs.com.neusoft.common.AccessCodingAvailableStatus;
import api.structs.com.neusoft.common.AccessCodingStatus;
import api.structs.com.neusoft.common.DeleteFileStatus;
import api.structs.com.neusoft.common.DeletePathStatus;
import api.structs.com.neusoft.common.ExportStatus;
import api.structs.com.neusoft.common.ExternalStorageStatus;
import api.structs.com.neusoft.common.ILocale;
import api.structs.com.neusoft.common.LogOnOff;
import api.structs.com.neusoft.common.MapStyleType;
import api.structs.com.neusoft.common.MediaType;
import api.structs.com.neusoft.common.ModuleName;
import api.structs.com.neusoft.common.NavAPPRunningStatus;
import api.structs.com.neusoft.common.NaviLayoutDirection;
import api.structs.com.neusoft.common.NoticeStatus;
import api.structs.com.neusoft.common.ScreenDirectionStatus;
import api.structs.com.neusoft.common.ScreenModeStatus;
import api.structs.com.neusoft.common.ServiceProvider;
import api.structs.com.neusoft.common.SpaceInfo;
import api.structs.com.neusoft.common.WriteFileStatus;
import cerence.ark.assistant.controller.enumeration.PhoneNumberTypeEnum;
import com.bydux.api.common.views.skin.entity.AttrFactory;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.neusoft.associatedinterface.INeusoftBuryPointInterface;
import com.neusoft.de.nts.OneCoreTest.communication.transports.websocketclient.OneCoreClientEndpoint;
import com.neusoft.de.nts.OneCoreTest.communication.utils.OneCoreWSContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.IGettableAttribute;
import com.neusoft.na.common.bean.TtsBean;
import com.neusoft.na.common.util.MyAppCrashHandler;
import com.neusoft.na.common.util.SystemUtils;
import com.neusoft.na.data.collection.DataCollectionManger;
import com.neusoft.na.dltdroid.LogService;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import com.neusoft.na.ocservice.OneCoreService;
import java.io.File;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import mu.KLogging;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: CommonSystem.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0094\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b.\u0018\u0000 ¿\u00012\u00020\u00012\u00020\u00022\u00020\u0003:\u0004¿\u0001À\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010g\u001a\u00020hH\u0002J\b\u0010i\u001a\u00020hH\u0002J\u0010\u0010j\u001a\u00020h2\u0006\u0010k\u001a\u00020\nH\u0002J\u0010\u0010l\u001a\u00020h2\u0006\u0010m\u001a\u00020\nH\u0003J\u001a\u0010n\u001a\u00020+2\b\u0010o\u001a\u0004\u0018\u00010\n2\u0006\u0010p\u001a\u00020qH\u0002J \u0010r\u001a\u00020h2\u0006\u0010s\u001a\u00020\u00192\u0006\u0010t\u001a\u00020\n2\u0006\u0010u\u001a\u00020\nH\u0002J\b\u0010v\u001a\u00020hH\u0002J\b\u0010w\u001a\u00020hH\u0002J\b\u0010x\u001a\u00020hH\u0002J\f\u0010y\u001a\b\u0012\u0004\u0012\u00020\u000f0zJ\b\u0010{\u001a\u00020|H\u0004J\f\u0010}\u001a\b\u0012\u0004\u0012\u00020\u00140zJ\b\u0010~\u001a\u00020+H\u0016J\f\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020\u000f0zJ\u0007\u0010\u0080\u0001\u001a\u00020|J\r\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0zJ\r\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020+0zJ\u0007\u0010\u0083\u0001\u001a\u00020+J\r\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0zJ\r\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0zJ\r\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020\n0zJ\r\u0010\u0087\u0001\u001a\b\u0012\u0004\u0012\u00020\u00190zJ\u000f\u0010\u0088\u0001\u001a\n\u0012\u0004\u0012\u00020\\\u0018\u00010zJ\r\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020+0zJ\r\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0zJ\r\u0010\u008b\u0001\u001a\b\u0012\u0004\u0012\u00020`0zJ\r\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0zJ\t\u0010\u008d\u0001\u001a\u00020+H\u0002J\u0007\u0010\u008e\u0001\u001a\u00020hJ\u0011\u0010\u008f\u0001\u001a\u00020h2\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001J\u0012\u0010\u0092\u0001\u001a\u00020h2\u0007\u0010\u0004\u001a\u00030\u0093\u0001H\u0016J\t\u0010\u0094\u0001\u001a\u00020hH\u0016J\t\u0010\u0095\u0001\u001a\u00020hH\u0002J\u0007\u0010\u0096\u0001\u001a\u00020hJ\u0012\u0010\u0097\u0001\u001a\u00020h2\u0007\u0010\u0098\u0001\u001a\u00020+H\u0002J\u0010\u0010\u0099\u0001\u001a\u00020h2\u0007\u0010\u009a\u0001\u001a\u00020\u0019J\u0012\u0010\u009b\u0001\u001a\u00020h2\u0007\u0010\u009c\u0001\u001a\u00020+H\u0002J\u0019\u0010\u009d\u0001\u001a\u00020h2\u0006\u0010s\u001a\u00020\u00192\u0006\u00106\u001a\u00020+H\u0002J\u0012\u0010\u009e\u0001\u001a\u00020h2\u0007\u0010\u009a\u0001\u001a\u00020\u0019H\u0002J\u0012\u0010\u009f\u0001\u001a\u00020h2\u0007\u0010 \u0001\u001a\u00020\u0016H\u0016J\u0010\u0010¡\u0001\u001a\u00020h2\u0007\u0010¢\u0001\u001a\u00020#J\u0010\u0010£\u0001\u001a\u00020h2\u0007\u0010¢\u0001\u001a\u00020+J\u0010\u0010¤\u0001\u001a\u00020h2\u0007\u0010¢\u0001\u001a\u00020+J\u0010\u0010¥\u0001\u001a\u00020h2\u0007\u0010¢\u0001\u001a\u00020+J\u0010\u0010¦\u0001\u001a\u00020h2\u0007\u0010¢\u0001\u001a\u00020\u0019J\u0010\u0010§\u0001\u001a\u00020h2\u0007\u0010¢\u0001\u001a\u00020+J\u0010\u0010¨\u0001\u001a\u00020h2\u0007\u0010¢\u0001\u001a\u00020+J\u0010\u0010©\u0001\u001a\u00020h2\u0007\u0010¢\u0001\u001a\u00020\u0019J\u0010\u0010ª\u0001\u001a\u00020h2\u0007\u0010«\u0001\u001a\u00020PJ\u0010\u0010¬\u0001\u001a\u00020h2\u0007\u0010¢\u0001\u001a\u00020\u0014J\t\u0010\u00ad\u0001\u001a\u00020hH\u0002J\t\u0010®\u0001\u001a\u00020hH\u0002J\u0007\u0010¯\u0001\u001a\u00020hJ\u0010\u0010°\u0001\u001a\u00020h2\u0007\u0010¢\u0001\u001a\u00020+J\u0007\u0010±\u0001\u001a\u00020hJ\u0010\u0010²\u0001\u001a\u00020h2\u0007\u0010¢\u0001\u001a\u00020+J\u0010\u0010³\u0001\u001a\u00020h2\u0007\u0010¢\u0001\u001a\u00020+J\t\u0010´\u0001\u001a\u00020hH\u0016J\t\u0010µ\u0001\u001a\u00020hH\u0002J\t\u0010¶\u0001\u001a\u00020hH\u0016J\t\u0010·\u0001\u001a\u00020hH\u0002J\t\u0010¸\u0001\u001a\u00020hH\u0002J\u001a\u0010¹\u0001\u001a\u00020h2\u0006\u0010s\u001a\u00020\u00192\u0007\u0010º\u0001\u001a\u00020\nH\u0002J\t\u0010»\u0001\u001a\u00020hH\u0002J\u0010\u0010¼\u0001\u001a\u00020h2\u0007\u0010½\u0001\u001a\u00020\nJ\u0007\u0010¾\u0001\u001a\u00020hR\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0004\n\u0002\u0010$R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0004\n\u0002\u0010,R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0010\u0010/\u001a\u0002008\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020+0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00103\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00104\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u00105\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0004\n\u0002\u0010,R\u000e\u00106\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00107\u001a\b\u0012\u0002\b\u0003\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u00109\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0004\n\u0002\u0010,R\u000e\u0010:\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010>\u001a\n @*\u0004\u0018\u00010?0?X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020CX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010E\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0004\n\u0002\u0010,R\u0010\u0010F\u001a\u0004\u0018\u00010GX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010H\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0004\n\u0002\u0010IR\u0014\u0010J\u001a\b\u0018\u00010KR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010L\u001a\b\u0012\u0004\u0012\u00020\n0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010M\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00190\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u0004\u0018\u00010PX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010Q\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030S0RX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010T\u001a\b\u0012\u0004\u0012\u00020U0RX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010V\u001a\u0004\u0018\u00010WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010[\u001a\b\u0012\u0004\u0012\u00020\\0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010]\u001a\b\u0012\u0004\u0012\u00020+0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010^\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010_\u001a\b\u0012\u0004\u0012\u00020`0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010b\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0004\n\u0002\u0010,R\u0010\u0010c\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010d\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0004\n\u0002\u0010,R\u0012\u0010e\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0004\n\u0002\u0010,R\u0012\u0010f\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0004\n\u0002\u0010,¨\u0006Á\u0001"}, d2 = {"Lcom/neusoft/na/common/CommonSystem;", "Lcom/neusoft/na/common/IGreatWallComponent;", "Lcom/neusoft/na/common/IOptionalComponent;", "Lcom/neusoft/na/common/ClientEndpointMangerCallback;", "context", "Landroid/content/Context;", "commonCallback", "Lcom/neusoft/na/common/CommonSystemCallBack;", "(Landroid/content/Context;Lcom/neusoft/na/common/CommonSystemCallBack;)V", "BURY_POINT_MANAGER", "", "TYPE_NAME_NAVI_RETRIEVAL", "TYPE_NAME_NAVI_ROAD", "abandonSpeakSubject", "Lrx/subjects/PublishSubject;", "Ljava/lang/Void;", "am", "Landroid/media/AudioManager;", "appLocaleSubject", "Lrx/subjects/BehaviorSubject;", "Ljava/util/Locale;", "appRunningStatusListener", "Lcom/neusoft/na/common/IAppRunningStatusListener;", "appSwitchToBackgroundSubject", "blHeartbeatCheckNotReceiveCount", "", "blHeartbeatCheckNotReceivePollCount", "blHeartbeatCheckTime", "", "blHeartbeatElapsedRealtime", "blHeartbeatMonitoringTimer", "Ljava/util/Timer;", "bluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "brightnessValue", "", "Ljava/lang/Float;", "buryPointInterfaceObj", "Lcom/neusoft/associatedinterface/INeusoftBuryPointInterface;", "cEManger", "Lcom/neusoft/na/common/ClientEndpointManger;", "closeMainActivitySubject", "clusterSurfaceStatus", "", "Ljava/lang/Boolean;", "getContext", "()Landroid/content/Context;", "dateFormat", "Ljava/text/SimpleDateFormat;", "dltLogSwitchSubject", "durationHint", "exitAndRestartAppSubject", "exitWithoutRestartingSubject", "extCloudServerStatus", "flag", "future", "Ljava/util/concurrent/ScheduledFuture;", OneCoreService.PAYLOAD_IN_MULTI_WINDOW_MODE, "lastBrightness", "mActivateKey", "mHandler", "Landroid/os/Handler;", "mScheduledExecutorService", "Ljava/util/concurrent/ScheduledExecutorService;", "kotlin.jvm.PlatformType", "mapScreenStatus", OneCoreService.PAYLOAD_MAP_STATUS, "Lcom/neusoft/na/common/MapStatus;", "musicType", "networkState", "notifications", "Lapi/notifications/com/neusoft/common/commonsysapiinverse/CommonSysApiInverseNotifications;", "onKeyDownValue", "Ljava/lang/Integer;", "oneCoreBroadcast", "Lcom/neusoft/na/common/CommonSystem$OneCoreBroadcast;", "playBeepToneSubject", "playSoundEffectSubject", "presentationDayNightModeChangeSubject", "protonCallBack", "Lcom/neusoft/na/common/ProtonCallBack;", "remoteAttributes", "Ljava/util/ArrayList;", "Lcom/neusoft/de/nts/OneCoreTest/interfaces/IGettableAttribute;", "remoteSubscriptions", "Lrx/Subscription;", "requests", "Lapi/requests/com/neusoft/common/commonsysapiinverse/CommonSysApiInverseRequests;", "runningStatus", "scheduleAtFixedRateElapsedRealtime", "screenDirection", "setCurrentLocationSubject", "", "setGpsOnOffSubject", "showDiagnoseSettingSubject", "speakAnnouncementSubject", "Lcom/neusoft/na/common/bean/TtsBean;", "startOc", "surfaceStatus", "systemLocale", "systemTimeFormatChanged", "vehicleBrakingStatus", "widgetSurfaceStatus", "UpdateExternalStoragePath", "", "applyDb", "autonaviStandardBroadcastSend", "version", "callPhone", "phoneNumber", "checkFilesSize", "dbFilePath", "fileJSONArray", "Lorg/json/JSONArray;", "copyDlt2Usb", "tokenId", AttrFactory.SRC, "target", "createBLHeartbeatMonitoringTimer", "disableTraceLog", "enableTraceLog", "getAbandonSpeakSubject", "Lrx/Observable;", "getAppCacheDir", "Ljava/io/File;", "getAppLocaleObs", "getAppRunningStatus", "getAppSwitchToBackgroundObs", "getAssetsInstallDir", "getCloseMainActivityObs", "getDltLogSwitchObs", "getDltStatus", "getExitAndRestartAppObs", "getExitWithoutRestartingAppObs", "getPlayBeepToneObs", "getPresentationDayNightModeChangeObs", "getSetCurrentLocationObs", "getSetGpsOnOffSubjectObs", "getShowDiagnoseSettingObs", "getSpeakAnnouncementObs", "getplaySoundEffectObs", "isBluetoothConnect", "ocStartError", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onServerConnect", "Lcom/neusoft/de/nts/OneCoreTest/communication/utils/OneCoreWSContextUtil;", "onServerDisconnect", "register", "replenishSendToBL", "saveDltStatus", "traceLog", "screenDirection2Bl", "direction", "sendArStatus", "status", "sendCopyResult", "sendLayoutDirection", "setAppRunningStatusListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setBrightnessValue", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "setClusterSurfaceStatus", "setExtCloudServerStatus", "setInMultiWindowMode", "setMapScreenStatus", "setMapSurfaceStatus", "setNetworkState", "setOnKeyDownValue", "setProtonCallBack", "callback", "setSystemLocale", "setSystemTime", "setSystemTimeChange", "setSystemTimeFormat", "setSystemTimeFormatChanged", "setSystemTimeZoneOffset", "setVehicleBrakingStatus", "setWidgetSurfaceStatus", "start", "startDltService", "stop", "stopDltService", "unregister", "updateExternalStoragePathHasFile", "fileName", "updateOnlineBusinessSwitch", "usbMount", OneCoreService.PAYLOAD_KEY_PATH, "usbUnMount", "Companion", "OneCoreBroadcast", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class CommonSystem implements IGreatWallComponent, IOptionalComponent, ClientEndpointMangerCallback {
    public static final String ACTION_COMMON_SYSTEM_ON_SERVER_CONNECT = "com.neusoft.na.navigation.action.commonSystem.onServerConnect";
    public static final String ACTION_SCREEN_DIRECTION = "com.neusoft.na.navigation.action.screen.direction";
    private static final String AR_STATUS_BROADCAST = "com.neusoft.ar.status";
    private static final String AUTONAVI_STANDARD_BROADCAST_SEND = "AUTONAVI_STANDARD_BROADCAST_SEND";
    public static final String CHANGAN = "changan";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DBU_PATH = "/dbu";
    private static final String INTENT_ACTION_RESTART_APP = "com.neusoft.na.navigation.action.restart.app";
    public static final String MOBILE = "mobile";
    public static final int NAVI_TTS = 10;
    public static final String ONE_CORE_STOP_ACTION = "com.neusoft.navigation.action.stop";
    public static final String PROTON = "proton";
    public static final String PROTONX70 = "protonx70";
    private static final String ROOT_METADATA_PATH = "/dbu/metadata.json";
    private static final String ROOT_NDS_PATH = "/dbu/ROOT.NDS";
    public static final String SHARED_DLT_TAG = "dltStatus";
    public static final String SHARED_TAG = "commonShared";
    private static final String TAG = "XJCommonSystem";
    public static final long TimeRound = 10000;
    public static final int X70NAVI_TTS = 12;
    private final String BURY_POINT_MANAGER;
    private final String TYPE_NAME_NAVI_RETRIEVAL;
    private final String TYPE_NAME_NAVI_ROAD;
    private final PublishSubject<Void> abandonSpeakSubject;
    private AudioManager am;
    private final BehaviorSubject<Locale> appLocaleSubject;
    private IAppRunningStatusListener appRunningStatusListener;
    private final PublishSubject<Void> appSwitchToBackgroundSubject;
    private int blHeartbeatCheckNotReceiveCount;
    private int blHeartbeatCheckNotReceivePollCount;
    private final long blHeartbeatCheckTime;
    private long blHeartbeatElapsedRealtime;
    private Timer blHeartbeatMonitoringTimer;
    private BluetoothAdapter bluetoothAdapter;
    private Float brightnessValue;
    private INeusoftBuryPointInterface buryPointInterfaceObj;
    private final ClientEndpointManger cEManger;
    private final PublishSubject<Void> closeMainActivitySubject;
    private Boolean clusterSurfaceStatus;
    private final CommonSystemCallBack commonCallback;
    private final Context context;
    private final SimpleDateFormat dateFormat;
    private final PublishSubject<Boolean> dltLogSwitchSubject;
    private int durationHint;
    private final PublishSubject<Void> exitAndRestartAppSubject;
    private final PublishSubject<Void> exitWithoutRestartingSubject;
    private Boolean extCloudServerStatus;
    private boolean flag;
    private ScheduledFuture<?> future;
    private Boolean isInMultiWindowMode;
    private float lastBrightness;
    private String mActivateKey;
    private Handler mHandler;
    private ScheduledExecutorService mScheduledExecutorService;
    private int mapScreenStatus;
    private MapStatus mapStatus;
    private int musicType;
    private Boolean networkState;
    private CommonSysApiInverseNotifications notifications;
    private Integer onKeyDownValue;
    private OneCoreBroadcast oneCoreBroadcast;
    private final PublishSubject<String> playBeepToneSubject;
    private final PublishSubject<Void> playSoundEffectSubject;
    private final PublishSubject<Integer> presentationDayNightModeChangeSubject;
    private ProtonCallBack protonCallBack;
    private final ArrayList<IGettableAttribute<?>> remoteAttributes;
    private final ArrayList<Subscription> remoteSubscriptions;
    private CommonSysApiInverseRequests requests;
    private boolean runningStatus;
    private long scheduleAtFixedRateElapsedRealtime;
    private int screenDirection;
    private final PublishSubject<float[]> setCurrentLocationSubject;
    private final PublishSubject<Boolean> setGpsOnOffSubject;
    private final PublishSubject<Void> showDiagnoseSettingSubject;
    private final PublishSubject<TtsBean> speakAnnouncementSubject;
    private boolean startOc;
    private Boolean surfaceStatus;
    private Locale systemLocale;
    private Boolean systemTimeFormatChanged;
    private Boolean vehicleBrakingStatus;
    private Boolean widgetSurfaceStatus;

    /* JADX INFO: renamed from: com.neusoft.na.common.CommonSystem$2, reason: invalid class name */
    /* JADX INFO: compiled from: CommonSystem.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass2 extends Lambda implements Function0<Object> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return "instance does not implement INeusoftBuryPointInterface";
        }
    }

    /* JADX INFO: renamed from: com.neusoft.na.common.CommonSystem$3, reason: invalid class name */
    /* JADX INFO: compiled from: CommonSystem.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass3 extends Lambda implements Function0<Object> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        AnonymousClass3() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return "failed to create instance";
        }
    }

    @Override // com.neusoft.na.common.IOptionalComponent
    public void start() {
    }

    public CommonSystem(Context context, CommonSystemCallBack commonCallback) {
        OneCoreWSContextUtil wsContext;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(commonCallback, "commonCallback");
        this.context = context;
        this.commonCallback = commonCallback;
        this.mHandler = new Handler();
        this.startOc = true;
        this.mActivateKey = "";
        this.remoteAttributes = new ArrayList<>();
        this.remoteSubscriptions = new ArrayList<>();
        ClientEndpointManger companion = ClientEndpointManger.INSTANCE.getInstance(context);
        this.cEManger = companion;
        this.mapStatus = MapStatus.INSTANCE;
        this.musicType = 3;
        this.durationHint = 3;
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.BURY_POINT_MANAGER = "com.neusoft.protonburypointsdk.NeusoftBuryPointService";
        this.TYPE_NAME_NAVI_RETRIEVAL = "NaviRetrieval";
        this.TYPE_NAME_NAVI_ROAD = "NaviRoad";
        this.screenDirection = -1;
        this.blHeartbeatCheckTime = 180000L;
        this.mapScreenStatus = -1;
        this.mScheduledExecutorService = Executors.newScheduledThreadPool(4);
        this.scheduleAtFixedRateElapsedRealtime = -10000L;
        PublishSubject<float[]> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this.setCurrentLocationSubject = publishSubjectCreate;
        PublishSubject<Boolean> publishSubjectCreate2 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate2, "create()");
        this.setGpsOnOffSubject = publishSubjectCreate2;
        BehaviorSubject<Locale> behaviorSubjectCreate = BehaviorSubject.create();
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate, "create()");
        this.appLocaleSubject = behaviorSubjectCreate;
        PublishSubject<Void> publishSubjectCreate3 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate3, "create()");
        this.closeMainActivitySubject = publishSubjectCreate3;
        PublishSubject<Void> publishSubjectCreate4 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate4, "create()");
        this.exitWithoutRestartingSubject = publishSubjectCreate4;
        PublishSubject<Void> publishSubjectCreate5 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate5, "create()");
        this.exitAndRestartAppSubject = publishSubjectCreate5;
        PublishSubject<Void> publishSubjectCreate6 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate6, "create()");
        this.showDiagnoseSettingSubject = publishSubjectCreate6;
        PublishSubject<Void> publishSubjectCreate7 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate7, "create()");
        this.appSwitchToBackgroundSubject = publishSubjectCreate7;
        PublishSubject<TtsBean> publishSubjectCreate8 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate8, "create()");
        this.speakAnnouncementSubject = publishSubjectCreate8;
        PublishSubject<Void> publishSubjectCreate9 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate9, "create()");
        this.abandonSpeakSubject = publishSubjectCreate9;
        PublishSubject<String> publishSubjectCreate10 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate10, "create()");
        this.playBeepToneSubject = publishSubjectCreate10;
        PublishSubject<Void> publishSubjectCreate11 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate11, "create()");
        this.playSoundEffectSubject = publishSubjectCreate11;
        PublishSubject<Boolean> publishSubjectCreate12 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate12, "create()");
        this.dltLogSwitchSubject = publishSubjectCreate12;
        PublishSubject<Integer> publishSubjectCreate13 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate13, "create()");
        this.presentationDayNightModeChangeSubject = publishSubjectCreate13;
        Log.i(TAG, "init");
        if (getDltStatus()) {
            startDltService();
        }
        if (companion != null) {
            companion.setClientEndpointMangerCallback(this);
        }
        if ((companion != null ? companion.getConnectionState() : null) == OneCoreClientEndpoint.ConnectionState.CONNECTED && (wsContext = companion.getWsContext()) != null) {
            onServerConnect(wsContext);
        }
        this.future = this.mScheduledExecutorService.scheduleAtFixedRate(new Runnable() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda30
            @Override // java.lang.Runnable
            public final void run() {
                CommonSystem._init_$lambda$1(this.f$0);
            }
        }, 0L, 10000L, TimeUnit.MILLISECONDS);
        this.dateFormat = new SimpleDateFormat("HH:mm:ss");
        this.flag = true;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: compiled from: CommonSystem.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/neusoft/na/common/CommonSystem$Companion;", "Lmu/KLogging;", "()V", "ACTION_COMMON_SYSTEM_ON_SERVER_CONNECT", "", "ACTION_SCREEN_DIRECTION", "AR_STATUS_BROADCAST", CommonSystem.AUTONAVI_STANDARD_BROADCAST_SEND, "CHANGAN", "DBU_PATH", "INTENT_ACTION_RESTART_APP", PhoneNumberTypeEnum.MOBILE, "NAVI_TTS", "", "ONE_CORE_STOP_ACTION", "PROTON", "PROTONX70", "ROOT_METADATA_PATH", "ROOT_NDS_PATH", "SHARED_DLT_TAG", "SHARED_TAG", "TAG", "TimeRound", "", "X70NAVI_TTS", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final Observable<float[]> getSetCurrentLocationObs() {
        return this.setCurrentLocationSubject.asObservable();
    }

    public final Observable<Boolean> getSetGpsOnOffSubjectObs() {
        Observable<Boolean> observableAsObservable = this.setGpsOnOffSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.setGpsOnOffSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Locale> getAppLocaleObs() {
        Observable<Locale> observableAsObservable = this.appLocaleSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.appLocaleSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Void> getCloseMainActivityObs() {
        Observable<Void> observableAsObservable = this.closeMainActivitySubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.closeMainActivitySubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Void> getExitWithoutRestartingAppObs() {
        Observable<Void> observableAsObservable = this.exitWithoutRestartingSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.exitWithoutRestartingSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Void> getExitAndRestartAppObs() {
        Observable<Void> observableAsObservable = this.exitAndRestartAppSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.exitAndRestartAppSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Void> getShowDiagnoseSettingObs() {
        Observable<Void> observableAsObservable = this.showDiagnoseSettingSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.showDiagnoseSettingSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Void> getAppSwitchToBackgroundObs() {
        Observable<Void> observableAsObservable = this.appSwitchToBackgroundSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.appSwitchToBackgroundSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<TtsBean> getSpeakAnnouncementObs() {
        Observable<TtsBean> observableAsObservable = this.speakAnnouncementSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.speakAnnouncementSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Void> getAbandonSpeakSubject() {
        Observable<Void> observableAsObservable = this.abandonSpeakSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.abandonSpeakSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<String> getPlayBeepToneObs() {
        Observable<String> observableAsObservable = this.playBeepToneSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.playBeepToneSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Void> getplaySoundEffectObs() {
        Observable<Void> observableAsObservable = this.playSoundEffectSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.playSoundEffectSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Boolean> getDltLogSwitchObs() {
        Observable<Boolean> observableAsObservable = this.dltLogSwitchSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.dltLogSwitchSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Integer> getPresentationDayNightModeChangeObs() {
        Observable<Integer> observableAsObservable = this.presentationDayNightModeChangeSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "this.presentationDayNigh…ngeSubject.asObservable()");
        return observableAsObservable;
    }

    /* JADX INFO: renamed from: com.neusoft.na.common.CommonSystem$4, reason: invalid class name */
    /* JADX INFO: compiled from: CommonSystem.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass4 extends Lambda implements Function0<Object> {
        AnonymousClass4() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return "failed to find class '" + CommonSystem.this.BURY_POINT_MANAGER + '\'';
        }
    }

    /* JADX INFO: renamed from: com.neusoft.na.common.CommonSystem$5, reason: invalid class name */
    /* JADX INFO: compiled from: CommonSystem.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass5 extends Lambda implements Function0<Object> {
        AnonymousClass5() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return "failed to load class '" + CommonSystem.this.BURY_POINT_MANAGER + '\'';
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(CommonSystem this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(TAG, "mScheduledExecutorService.scheduleAtFixedRate");
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - this$0.scheduleAtFixedRateElapsedRealtime >= 10000) {
            Log.i(TAG, "mScheduledExecutorService.scheduleAtFixedRate check time >= TimeRound[" + this$0.scheduleAtFixedRateElapsedRealtime + ", " + jElapsedRealtime + "], do setTimeFormat");
            this$0.scheduleAtFixedRateElapsedRealtime = jElapsedRealtime;
            this$0.setSystemTimeFormat();
        }
    }

    @Override // com.neusoft.na.common.ClientEndpointMangerCallback
    public void onServerConnect(OneCoreWSContextUtil context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Log.i(TAG, "onServerConnect");
        OneCoreWSContextUtil oneCoreWSContextUtil = context;
        this.requests = new CommonSysApiInverseRequests(oneCoreWSContextUtil);
        this.notifications = new CommonSysApiInverseNotifications(oneCoreWSContextUtil);
        register();
        setSystemTime();
        this.context.sendBroadcast(new Intent(ACTION_COMMON_SYSTEM_ON_SERVER_CONNECT));
        CommonSysApiInverseNotifications commonSysApiInverseNotifications = this.notifications;
        if (commonSysApiInverseNotifications != null) {
            IAttribute<NotifyCurrentLocation> iAttributeCurrentLocationAttribute = commonSysApiInverseNotifications.currentLocationAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeCurrentLocationAttribute, "it.currentLocationAttribute()");
            IAttribute<NotifyCurrentLocation> iAttribute = iAttributeCurrentLocationAttribute;
            iAttribute.signIn();
            this.remoteAttributes.add(iAttribute);
            Log.i(TAG, "register NotifyCurrentLocation notifications");
            ArrayList<Subscription> arrayList = this.remoteSubscriptions;
            Observable<NotifyCurrentLocation> observable = iAttribute.getObservable();
            final Function1<NotifyCurrentLocation, Unit> function1 = new Function1<NotifyCurrentLocation, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyCurrentLocation notifyCurrentLocation) {
                    invoke2(notifyCurrentLocation);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyCurrentLocation notifyCurrentLocation) {
                    Log.i("XJCommonSystem", "NotifyCurrentLocation --> " + notifyCurrentLocation.getCurrentLocation());
                    String longitude = notifyCurrentLocation.getCurrentLocation().getLongitude();
                    String latitude = notifyCurrentLocation.getCurrentLocation().getLatitude();
                    String heading = notifyCurrentLocation.getCurrentLocation().getHeading();
                    float[] fArr = new float[3];
                    Intrinsics.checkNotNullExpressionValue(latitude, "latitude");
                    if (latitude.length() > 0) {
                        fArr[0] = Float.parseFloat(latitude);
                    }
                    Intrinsics.checkNotNullExpressionValue(longitude, "longitude");
                    if (longitude.length() > 0) {
                        fArr[1] = Float.parseFloat(longitude);
                    }
                    Intrinsics.checkNotNullExpressionValue(heading, "heading");
                    if (heading.length() > 0) {
                        float f = Float.parseFloat(heading);
                        fArr[2] = f;
                        if (f == 0.0f) {
                            fArr[2] = 0.1f;
                        }
                    }
                    this.this$0.setCurrentLocationSubject.onNext(fArr);
                }
            };
            arrayList.add(observable.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda11
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$3$lambda$2(function1, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications2 = this.notifications;
        if (commonSysApiInverseNotifications2 != null) {
            IAttribute<NotifyNotifyOpenGPS> iAttributeNotifyOpenGPSAttribute = commonSysApiInverseNotifications2.notifyOpenGPSAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeNotifyOpenGPSAttribute, "it.notifyOpenGPSAttribute()");
            IAttribute<NotifyNotifyOpenGPS> iAttribute2 = iAttributeNotifyOpenGPSAttribute;
            iAttribute2.signIn();
            this.remoteAttributes.add(iAttribute2);
            Log.i(TAG, "register NotifyNotifyOpenGPS notifications");
            ArrayList<Subscription> arrayList2 = this.remoteSubscriptions;
            Observable<NotifyNotifyOpenGPS> observable2 = iAttribute2.getObservable();
            final Function1<NotifyNotifyOpenGPS, Unit> function12 = new Function1<NotifyNotifyOpenGPS, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$2$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyNotifyOpenGPS notifyNotifyOpenGPS) {
                    invoke2(notifyNotifyOpenGPS);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyNotifyOpenGPS notifyNotifyOpenGPS) {
                    Log.i("XJCommonSystem", "NotifyNotifyOpenGPS --> " + notifyNotifyOpenGPS.getNotifyOpenGPS().getInfoBool());
                    this.this$0.setGpsOnOffSubject.onNext(notifyNotifyOpenGPS.getNotifyOpenGPS().getInfoBool());
                }
            };
            arrayList2.add(observable2.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda3
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$5$lambda$4(function12, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications3 = this.notifications;
        if (commonSysApiInverseNotifications3 != null) {
            IAttribute<NotifyAppLocale> iAttributeAppLocaleAttribute = commonSysApiInverseNotifications3.appLocaleAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeAppLocaleAttribute, "it.appLocaleAttribute()");
            IAttribute<NotifyAppLocale> iAttribute3 = iAttributeAppLocaleAttribute;
            iAttribute3.signIn();
            this.remoteAttributes.add(iAttribute3);
            Log.i(TAG, "register NotifyAppLocale notifications");
            ArrayList<Subscription> arrayList3 = this.remoteSubscriptions;
            Observable<NotifyAppLocale> observable3 = iAttribute3.getObservable();
            final Function1<NotifyAppLocale, Unit> function13 = new Function1<NotifyAppLocale, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$3$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyAppLocale notifyAppLocale) {
                    invoke2(notifyAppLocale);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyAppLocale notifyAppLocale) {
                    Log.i("XJCommonSystem", "NotifyAppLocale --> " + notifyAppLocale.getAppLocale());
                    Locale locale = new Locale(notifyAppLocale.getAppLocale().getLanguage());
                    Log.i("XJCommonSystem", "NotifyAppLocale --> locale == " + locale);
                    this.this$0.appLocaleSubject.onNext(locale);
                }
            };
            arrayList3.add(observable3.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda15
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$7$lambda$6(function13, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications4 = this.notifications;
        if (commonSysApiInverseNotifications4 != null) {
            IAttribute<NotifyCloseMainActivity> iAttributeCloseMainActivityAttribute = commonSysApiInverseNotifications4.closeMainActivityAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeCloseMainActivityAttribute, "it.closeMainActivityAttribute()");
            IAttribute<NotifyCloseMainActivity> iAttribute4 = iAttributeCloseMainActivityAttribute;
            iAttribute4.signIn();
            this.remoteAttributes.add(iAttribute4);
            Log.i(TAG, "register NotifyCloseMainActivity notifications");
            ArrayList<Subscription> arrayList4 = this.remoteSubscriptions;
            Observable<NotifyCloseMainActivity> observable4 = iAttribute4.getObservable();
            final Function1<NotifyCloseMainActivity, Unit> function14 = new Function1<NotifyCloseMainActivity, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$4$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyCloseMainActivity notifyCloseMainActivity) {
                    invoke2(notifyCloseMainActivity);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyCloseMainActivity notifyCloseMainActivity) {
                    Log.i("XJCommonSystem", "NotifyCloseMainActivity");
                    this.this$0.closeMainActivitySubject.onNext(null);
                }
            };
            arrayList4.add(observable4.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda23
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$9$lambda$8(function14, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications5 = this.notifications;
        if (commonSysApiInverseNotifications5 != null) {
            IAttribute<NotifyCloseApp> iAttributeCloseAppAttribute = commonSysApiInverseNotifications5.closeAppAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeCloseAppAttribute, "it.closeAppAttribute()");
            IAttribute<NotifyCloseApp> iAttribute5 = iAttributeCloseAppAttribute;
            iAttribute5.signIn();
            this.remoteAttributes.add(iAttribute5);
            Log.i(TAG, "register NotifyCloseApp notifications");
            ArrayList<Subscription> arrayList5 = this.remoteSubscriptions;
            Observable<NotifyCloseApp> observable5 = iAttribute5.getObservable();
            final Function1<NotifyCloseApp, Unit> function15 = new Function1<NotifyCloseApp, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$5$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyCloseApp notifyCloseApp) {
                    invoke2(notifyCloseApp);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyCloseApp notifyCloseApp) {
                    Log.i("XJCommonSystem", "NotifyCloseApp");
                    MyAppCrashHandler.setNaviAppExit(this.this$0.requests, 3);
                    this.this$0.exitWithoutRestartingSubject.onNext(null);
                }
            };
            arrayList5.add(observable5.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda24
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$11$lambda$10(function15, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications6 = this.notifications;
        if (commonSysApiInverseNotifications6 != null) {
            IAttribute<NotifyCloseAndRestartApp> iAttributeCloseAndRestartAppAttribute = commonSysApiInverseNotifications6.closeAndRestartAppAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeCloseAndRestartAppAttribute, "it.closeAndRestartAppAttribute()");
            IAttribute<NotifyCloseAndRestartApp> iAttribute6 = iAttributeCloseAndRestartAppAttribute;
            iAttribute6.signIn();
            this.remoteAttributes.add(iAttribute6);
            Log.i(TAG, "register NotifyCloseAndRestartApp notifications");
            ArrayList<Subscription> arrayList6 = this.remoteSubscriptions;
            Observable<NotifyCloseAndRestartApp> observable6 = iAttribute6.getObservable();
            final Function1<NotifyCloseAndRestartApp, Unit> function16 = new Function1<NotifyCloseAndRestartApp, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$6$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyCloseAndRestartApp notifyCloseAndRestartApp) {
                    invoke2(notifyCloseAndRestartApp);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyCloseAndRestartApp notifyCloseAndRestartApp) {
                    Log.i("XJCommonSystem", "NotifyCloseAndRestartApp");
                    MyAppCrashHandler.setNaviAppExit(this.this$0.requests, 3);
                    this.this$0.exitAndRestartAppSubject.onNext(null);
                }
            };
            arrayList6.add(observable6.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda25
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$13$lambda$12(function16, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications7 = this.notifications;
        if (commonSysApiInverseNotifications7 != null) {
            IAttribute<NotifyShowDiagnoseSetting> iAttributeShowDiagnoseSettingAttribute = commonSysApiInverseNotifications7.showDiagnoseSettingAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeShowDiagnoseSettingAttribute, "it.showDiagnoseSettingAttribute()");
            IAttribute<NotifyShowDiagnoseSetting> iAttribute7 = iAttributeShowDiagnoseSettingAttribute;
            iAttribute7.signIn();
            this.remoteAttributes.add(iAttribute7);
            Log.i(TAG, "register NotifyShowDiagnoseSetting notifications");
            ArrayList<Subscription> arrayList7 = this.remoteSubscriptions;
            Observable<NotifyShowDiagnoseSetting> observable7 = iAttribute7.getObservable();
            final Function1<NotifyShowDiagnoseSetting, Unit> function17 = new Function1<NotifyShowDiagnoseSetting, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$7$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyShowDiagnoseSetting notifyShowDiagnoseSetting) {
                    invoke2(notifyShowDiagnoseSetting);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyShowDiagnoseSetting notifyShowDiagnoseSetting) {
                    Log.i("XJCommonSystem", "NotifyShowDiagnoseSetting");
                    this.this$0.showDiagnoseSettingSubject.onNext(null);
                }
            };
            arrayList7.add(observable7.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda26
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$15$lambda$14(function17, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications8 = this.notifications;
        if (commonSysApiInverseNotifications8 != null) {
            IAttribute<NotifySwitchNaviToBackground> iAttributeSwitchNaviToBackgroundAttribute = commonSysApiInverseNotifications8.switchNaviToBackgroundAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeSwitchNaviToBackgroundAttribute, "it.switchNaviToBackgroundAttribute()");
            IAttribute<NotifySwitchNaviToBackground> iAttribute8 = iAttributeSwitchNaviToBackgroundAttribute;
            iAttribute8.signIn();
            this.remoteAttributes.add(iAttribute8);
            Log.i(TAG, "register NotifySwitchNaviToBackground notifications");
            ArrayList<Subscription> arrayList8 = this.remoteSubscriptions;
            Observable<NotifySwitchNaviToBackground> observable8 = iAttribute8.getObservable();
            final Function1<NotifySwitchNaviToBackground, Unit> function18 = new Function1<NotifySwitchNaviToBackground, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$8$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifySwitchNaviToBackground notifySwitchNaviToBackground) {
                    invoke2(notifySwitchNaviToBackground);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifySwitchNaviToBackground notifySwitchNaviToBackground) {
                    Log.i("XJCommonSystem", "NotifySwitchNaviToBackground");
                    this.this$0.appSwitchToBackgroundSubject.onNext(null);
                }
            };
            arrayList8.add(observable8.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda27
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$17$lambda$16(function18, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications9 = this.notifications;
        if (commonSysApiInverseNotifications9 != null) {
            IAttribute<NotifySpeak> iAttributeSpeakAttribute = commonSysApiInverseNotifications9.speakAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeSpeakAttribute, "it.speakAttribute()");
            IAttribute<NotifySpeak> iAttribute9 = iAttributeSpeakAttribute;
            iAttribute9.signIn();
            this.remoteAttributes.add(iAttribute9);
            Log.i(TAG, "register NotifySpeak notifications");
            ArrayList<Subscription> arrayList9 = this.remoteSubscriptions;
            Observable<NotifySpeak> observable9 = iAttribute9.getObservable();
            final Function1<NotifySpeak, Unit> function19 = new Function1<NotifySpeak, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$9$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifySpeak notifySpeak) {
                    invoke2(notifySpeak);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifySpeak notifySpeak) {
                    Log.i("XJCommonSystem", "NotifySpeak --> " + notifySpeak.getSpeak().getMsg());
                    if (notifySpeak.getSpeak().getMsg() != null) {
                        TtsBean ttsBean = new TtsBean();
                        String msg = notifySpeak.getSpeak().getMsg();
                        Intrinsics.checkNotNullExpressionValue(msg, "it.speak.msg");
                        ttsBean.setMsg(msg);
                        this.this$0.speakAnnouncementSubject.onNext(ttsBean);
                    }
                }
            };
            arrayList9.add(observable9.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda28
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$19$lambda$18(function19, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications10 = this.notifications;
        if (commonSysApiInverseNotifications10 != null) {
            IAttribute<NotifyAbandonSpeak> iAttributeAbandonSpeakAttribute = commonSysApiInverseNotifications10.abandonSpeakAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeAbandonSpeakAttribute, "it.abandonSpeakAttribute()");
            IAttribute<NotifyAbandonSpeak> iAttribute10 = iAttributeAbandonSpeakAttribute;
            iAttribute10.signIn();
            this.remoteAttributes.add(iAttribute10);
            Log.i(TAG, "register NotifyAbandonSpeak notifications");
            ArrayList<Subscription> arrayList10 = this.remoteSubscriptions;
            Observable<NotifyAbandonSpeak> observable10 = iAttribute10.getObservable();
            final Function1<NotifyAbandonSpeak, Unit> function110 = new Function1<NotifyAbandonSpeak, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$10$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyAbandonSpeak notifyAbandonSpeak) {
                    invoke2(notifyAbandonSpeak);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyAbandonSpeak notifyAbandonSpeak) {
                    Log.i("XJCommonSystem", "NotifyAbandonSpeak");
                    this.this$0.abandonSpeakSubject.onNext(null);
                }
            };
            arrayList10.add(observable10.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda29
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$21$lambda$20(function110, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications11 = this.notifications;
        if (commonSysApiInverseNotifications11 != null) {
            IAttribute<NotifyPlayBeepTone> iAttributePlayBeepToneAttribute = commonSysApiInverseNotifications11.playBeepToneAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributePlayBeepToneAttribute, "it.playBeepToneAttribute()");
            IAttribute<NotifyPlayBeepTone> iAttribute11 = iAttributePlayBeepToneAttribute;
            iAttribute11.signIn();
            this.remoteAttributes.add(iAttribute11);
            Log.i(TAG, "register NotifyPlayBeepTone notifications");
            ArrayList<Subscription> arrayList11 = this.remoteSubscriptions;
            Observable<NotifyPlayBeepTone> observable11 = iAttribute11.getObservable();
            final Function1<NotifyPlayBeepTone, Unit> function111 = new Function1<NotifyPlayBeepTone, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$11$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyPlayBeepTone notifyPlayBeepTone) {
                    invoke2(notifyPlayBeepTone);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyPlayBeepTone notifyPlayBeepTone) {
                    Log.i("XJCommonSystem", "NotifyPlayBeepTone --> " + notifyPlayBeepTone.getPlayBeepTone().getName());
                    this.this$0.playBeepToneSubject.onNext(notifyPlayBeepTone.getPlayBeepTone().getName());
                }
            };
            arrayList11.add(observable11.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda22
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$23$lambda$22(function111, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications12 = this.notifications;
        if (commonSysApiInverseNotifications12 != null) {
            IAttribute<NotifyPlaySoundEffect> iAttributePlaySoundEffectAttribute = commonSysApiInverseNotifications12.playSoundEffectAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributePlaySoundEffectAttribute, "it.playSoundEffectAttribute()");
            IAttribute<NotifyPlaySoundEffect> iAttribute12 = iAttributePlaySoundEffectAttribute;
            iAttribute12.signIn();
            this.remoteAttributes.add(iAttribute12);
            Log.i(TAG, "register NotifyPlaySoundEffect notifications");
            ArrayList<Subscription> arrayList12 = this.remoteSubscriptions;
            Observable<NotifyPlaySoundEffect> observable12 = iAttribute12.getObservable();
            final Function1<NotifyPlaySoundEffect, Unit> function112 = new Function1<NotifyPlaySoundEffect, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$12$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyPlaySoundEffect notifyPlaySoundEffect) {
                    invoke2(notifyPlaySoundEffect);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyPlaySoundEffect notifyPlaySoundEffect) {
                    Log.i("XJCommonSystem", "NotifyPlaySoundEffect");
                    this.this$0.playSoundEffectSubject.onNext(null);
                }
            };
            arrayList12.add(observable12.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda31
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$25$lambda$24(function112, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications13 = this.notifications;
        if (commonSysApiInverseNotifications13 != null) {
            IAttribute<NotifyAccessCodingAvailable> iAttributeAccessCodingAvailableAttribute = commonSysApiInverseNotifications13.accessCodingAvailableAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeAccessCodingAvailableAttribute, "it.accessCodingAvailableAttribute()");
            IAttribute<NotifyAccessCodingAvailable> iAttribute13 = iAttributeAccessCodingAvailableAttribute;
            iAttribute13.signIn();
            this.remoteAttributes.add(iAttribute13);
            ArrayList<Subscription> arrayList13 = this.remoteSubscriptions;
            Observable<NotifyAccessCodingAvailable> observable13 = iAttribute13.getObservable();
            final Function1<NotifyAccessCodingAvailable, Unit> function113 = new Function1<NotifyAccessCodingAvailable, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$13$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyAccessCodingAvailable notifyAccessCodingAvailable) throws Throwable {
                    invoke2(notifyAccessCodingAvailable);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyAccessCodingAvailable notifyAccessCodingAvailable) throws Throwable {
                    Log.i("usbdbupdate", "NotifyAccessCodingAvailable  " + this.this$0.dateFormat.format(Long.valueOf(System.currentTimeMillis())));
                    CommonSystem commonSystem = this.this$0;
                    String code = notifyAccessCodingAvailable.getAccessCodingAvailable().getCode();
                    Intrinsics.checkNotNullExpressionValue(code, "it.accessCodingAvailable.code");
                    commonSystem.mActivateKey = code;
                    if (notifyAccessCodingAvailable.getAccessCodingAvailable().getFile().getType() == MediaType.E_FILESYSTEM) {
                        Log.i("usbdbupdate", "MediaType.E_FILESYSTEM.  " + this.this$0.dateFormat.format(Long.valueOf(System.currentTimeMillis())));
                        Boolean has = Tool.FileHasStr(notifyAccessCodingAvailable.getAccessCodingAvailable().getFile().getPath(), this.this$0.mActivateKey);
                        Log.i("XJCommonSystem", "file HasStr accesscodingAvailable. has:" + has);
                        CommonSysApiInverseRequests commonSysApiInverseRequests = this.this$0.requests;
                        if (commonSysApiInverseRequests != null) {
                            Integer tokenId = notifyAccessCodingAvailable.getAccessCodingAvailable().getTokenId();
                            Intrinsics.checkNotNullExpressionValue(has, "has");
                            commonSysApiInverseRequests.fireOnAccessCodingAvailable(new ReqOnAccessCodingAvailable(new AccessCodingAvailableStatus(tokenId, has.booleanValue() ? AccessCodingStatus.E_SUCCESS : AccessCodingStatus.E_ACTIVATION_CODE_MISMATCH)));
                        }
                        Log.i("XJCommonSystem", "fire onAccesscodingAvailable. tokenId:" + notifyAccessCodingAvailable.getAccessCodingAvailable().getTokenId());
                    } else if (notifyAccessCodingAvailable.getAccessCodingAvailable().getFile().getType() == MediaType.E_USB) {
                        Log.i("usbdbupdate", "MediaType.E_USB.  " + this.this$0.dateFormat.format(Long.valueOf(System.currentTimeMillis())));
                        CommonSystem commonSystem2 = this.this$0;
                        Integer tokenId2 = notifyAccessCodingAvailable.getAccessCodingAvailable().getTokenId();
                        Intrinsics.checkNotNullExpressionValue(tokenId2, "it.accessCodingAvailable.tokenId");
                        int iIntValue = tokenId2.intValue();
                        String path = notifyAccessCodingAvailable.getAccessCodingAvailable().getFile().getPath();
                        Intrinsics.checkNotNullExpressionValue(path, "it.accessCodingAvailable.file.path");
                        commonSystem2.updateExternalStoragePathHasFile(iIntValue, path);
                    }
                    Log.i("XJ", "mActivateKey::");
                }
            };
            arrayList13.add(observable13.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda32
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$27$lambda$26(function113, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications14 = this.notifications;
        if (commonSysApiInverseNotifications14 != null) {
            IAttribute<NotifyReadFile> fileAttribute = commonSysApiInverseNotifications14.readFileAttribute();
            Intrinsics.checkNotNullExpressionValue(fileAttribute, "it.readFileAttribute()");
            IAttribute<NotifyReadFile> iAttribute14 = fileAttribute;
            iAttribute14.signIn();
            this.remoteAttributes.add(iAttribute14);
            ArrayList<Subscription> arrayList14 = this.remoteSubscriptions;
            Observable<NotifyReadFile> observable14 = iAttribute14.getObservable();
            final Function1<NotifyReadFile, Unit> function114 = new Function1<NotifyReadFile, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$14$1
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
                    if (Intrinsics.areEqual(notifyReadFile.getReadFile().getPath(), this.this$0.getContext().getCacheDir().getParent() + "/shared_prefs/Disclaimer.bin")) {
                        Log.i("XJCommonSystem", "BYD skip read Disclaimer.bin file!");
                        return;
                    }
                    Log.i("XJCommonSystem", "Common read file!");
                    CommonSysApiInverseRequests commonSysApiInverseRequests = this.this$0.requests;
                    if (commonSysApiInverseRequests != null) {
                        Integer tokenId = notifyReadFile.getReadFile().getTokenId();
                        Intrinsics.checkNotNullExpressionValue(tokenId, "it.readFile.tokenId");
                        commonSysApiInverseRequests.fireOnReadFile(Tool.ReadTxtFile(tokenId.intValue(), notifyReadFile.getReadFile().getPath()));
                    }
                }
            };
            arrayList14.add(observable14.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda33
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$29$lambda$28(function114, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications15 = this.notifications;
        if (commonSysApiInverseNotifications15 != null) {
            IAttribute<NotifyLaunchApp> iAttributeLaunchAppAttribute = commonSysApiInverseNotifications15.launchAppAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeLaunchAppAttribute, "it.launchAppAttribute()");
            IAttribute<NotifyLaunchApp> iAttribute15 = iAttributeLaunchAppAttribute;
            iAttribute15.signIn();
            this.remoteAttributes.add(iAttribute15);
            ArrayList<Subscription> arrayList15 = this.remoteSubscriptions;
            Observable<NotifyLaunchApp> observable15 = iAttribute15.getObservable();
            final Function1<NotifyLaunchApp, Unit> function115 = new Function1<NotifyLaunchApp, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$15$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyLaunchApp notifyLaunchApp) {
                    invoke2(notifyLaunchApp);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyLaunchApp notifyLaunchApp) {
                    Log.i("XJCommonSystem", "NotifyLaunchApp: ");
                    Intent launchIntentForPackage = this.this$0.getContext().getPackageManager().getLaunchIntentForPackage("com.neusoft.na.navigation");
                    if (launchIntentForPackage == null) {
                        launchIntentForPackage = new Intent();
                        launchIntentForPackage.setClassName(this.this$0.getContext(), "com.neusoft.na.mots.MainActivity");
                        launchIntentForPackage.setFlags(268435456);
                    }
                    this.this$0.getContext().startActivity(launchIntentForPackage);
                }
            };
            arrayList15.add(observable15.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda34
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$31$lambda$30(function115, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications16 = this.notifications;
        if (commonSysApiInverseNotifications16 != null) {
            IAttribute<NotifyCheckDBAvailable> iAttributeCheckDBAvailableAttribute = commonSysApiInverseNotifications16.checkDBAvailableAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeCheckDBAvailableAttribute, "it.checkDBAvailableAttribute()");
            IAttribute<NotifyCheckDBAvailable> iAttribute16 = iAttributeCheckDBAvailableAttribute;
            iAttribute16.signIn();
            this.remoteAttributes.add(iAttribute16);
            ArrayList<Subscription> arrayList16 = this.remoteSubscriptions;
            Observable<NotifyCheckDBAvailable> observable16 = iAttribute16.getObservable();
            final Function1<NotifyCheckDBAvailable, Unit> function116 = new Function1<NotifyCheckDBAvailable, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$16$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyCheckDBAvailable notifyCheckDBAvailable) {
                    invoke2(notifyCheckDBAvailable);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyCheckDBAvailable notifyCheckDBAvailable) {
                    String str = this.this$0.getContext().getFilesDir().getAbsolutePath() + "/files/__org.liquidplayer.node__/_onecloud_client/local/dbu";
                    CommonSysApiInverseRequests commonSysApiInverseRequests = this.this$0.requests;
                    if (commonSysApiInverseRequests != null) {
                        commonSysApiInverseRequests.fireSetDownloadPath(new ReqSetDownloadPath(str));
                    }
                    this.this$0.applyDb();
                    NaviStatus companion = NaviStatus.INSTANCE.getInstance(this.this$0.getContext());
                    Boolean boolValueOf = companion != null ? Boolean.valueOf(companion.checkDBAvailable()) : null;
                    Intrinsics.checkNotNull(boolValueOf);
                    if (boolValueOf.booleanValue()) {
                        return;
                    }
                    Tool.createDirectory(str);
                }
            };
            arrayList16.add(observable16.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda35
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$33$lambda$32(function116, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications17 = this.notifications;
        if (commonSysApiInverseNotifications17 != null) {
            IAttribute<NotifySwitchTraceLog> iAttributeSwitchTraceLogAttribute = commonSysApiInverseNotifications17.switchTraceLogAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeSwitchTraceLogAttribute, "it.switchTraceLogAttribute()");
            IAttribute<NotifySwitchTraceLog> iAttribute17 = iAttributeSwitchTraceLogAttribute;
            iAttribute17.signIn();
            this.remoteAttributes.add(iAttribute17);
            ArrayList<Subscription> arrayList17 = this.remoteSubscriptions;
            Observable<NotifySwitchTraceLog> observable17 = iAttribute17.getObservable();
            final Function1<NotifySwitchTraceLog, Unit> function117 = new Function1<NotifySwitchTraceLog, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$17$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifySwitchTraceLog notifySwitchTraceLog) {
                    invoke2(notifySwitchTraceLog);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifySwitchTraceLog notifySwitchTraceLog) {
                    Log.i("XJCommonSystem", "NotifySwitchTraceLog:" + notifySwitchTraceLog.getSwitchTraceLog().getValue());
                    if (notifySwitchTraceLog.getSwitchTraceLog() == LogOnOff.ENABLE_TRACE_LOG) {
                        this.this$0.enableTraceLog();
                    } else if (notifySwitchTraceLog.getSwitchTraceLog() == LogOnOff.DISABLE_TRACE_LOG) {
                        this.this$0.disableTraceLog();
                    }
                    this.this$0.dltLogSwitchSubject.onNext(Boolean.valueOf(notifySwitchTraceLog.getSwitchTraceLog() == LogOnOff.ENABLE_TRACE_LOG));
                }
            };
            arrayList17.add(observable17.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda36
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$35$lambda$34(function117, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications18 = this.notifications;
        if (commonSysApiInverseNotifications18 != null) {
            IAttribute<NotifyNavigationRunningStatus> iAttributeNavigationRunningStatusAttribute = commonSysApiInverseNotifications18.navigationRunningStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeNavigationRunningStatusAttribute, "it.navigationRunningStatusAttribute()");
            IAttribute<NotifyNavigationRunningStatus> iAttribute18 = iAttributeNavigationRunningStatusAttribute;
            iAttribute18.signIn();
            Log.i(TAG, "notifications == null ? == " + (this.notifications == null));
            this.remoteAttributes.add(iAttribute18);
            ArrayList<Subscription> arrayList18 = this.remoteSubscriptions;
            Observable<NotifyNavigationRunningStatus> observable18 = iAttribute18.getObservable();
            final Function1<NotifyNavigationRunningStatus, Unit> function118 = new Function1<NotifyNavigationRunningStatus, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$18$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyNavigationRunningStatus notifyNavigationRunningStatus) {
                    invoke2(notifyNavigationRunningStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyNavigationRunningStatus notifyNavigationRunningStatus) {
                    Log.i("XJCommonSystem", "NotifyNavigationRunningStatus[" + notifyNavigationRunningStatus.getNavigationRunningStatus() + ']');
                    CommonSystem.INSTANCE.getLogger().debug("NotifyNavigationRunningStatus[" + notifyNavigationRunningStatus.getNavigationRunningStatus() + ']');
                    if (notifyNavigationRunningStatus.getNavigationRunningStatus() == NavAPPRunningStatus.NAV_MAINAPP_OPERABLE) {
                        this.this$0.runningStatus = true;
                        IAppRunningStatusListener iAppRunningStatusListener = this.this$0.appRunningStatusListener;
                        if (iAppRunningStatusListener != null) {
                            iAppRunningStatusListener.navAppOperable();
                            return;
                        }
                        return;
                    }
                    if (notifyNavigationRunningStatus.getNavigationRunningStatus() == NavAPPRunningStatus.NAV_SLAVEAPP_OPERABLE) {
                        IAppRunningStatusListener iAppRunningStatusListener2 = this.this$0.appRunningStatusListener;
                        if (iAppRunningStatusListener2 != null) {
                            iAppRunningStatusListener2.slaveAppOperable();
                            return;
                        }
                        return;
                    }
                    if (notifyNavigationRunningStatus.getNavigationRunningStatus() == NavAPPRunningStatus.NAV_CLUSTER_OPERABLE) {
                        IAppRunningStatusListener iAppRunningStatusListener3 = this.this$0.appRunningStatusListener;
                        if (iAppRunningStatusListener3 != null) {
                            iAppRunningStatusListener3.clusterOperable();
                            return;
                        }
                        return;
                    }
                    this.this$0.runningStatus = false;
                    IAppRunningStatusListener iAppRunningStatusListener4 = this.this$0.appRunningStatusListener;
                    if (iAppRunningStatusListener4 != null) {
                        iAppRunningStatusListener4.other();
                    }
                }
            };
            arrayList18.add(observable18.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda37
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$37$lambda$36(function118, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications19 = this.notifications;
        if (commonSysApiInverseNotifications19 != null) {
            IAttribute<NotifyRequestInstallationPath> iAttributeRequestInstallationPathAttribute = commonSysApiInverseNotifications19.requestInstallationPathAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeRequestInstallationPathAttribute, "it.requestInstallationPathAttribute()");
            IAttribute<NotifyRequestInstallationPath> iAttribute19 = iAttributeRequestInstallationPathAttribute;
            iAttribute19.signIn();
            this.remoteAttributes.add(iAttribute19);
            ArrayList<Subscription> arrayList19 = this.remoteSubscriptions;
            Observable<NotifyRequestInstallationPath> observable19 = iAttribute19.getObservable();
            final Function1<NotifyRequestInstallationPath, Unit> function119 = new Function1<NotifyRequestInstallationPath, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$19$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyRequestInstallationPath notifyRequestInstallationPath) {
                    invoke2(notifyRequestInstallationPath);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyRequestInstallationPath notifyRequestInstallationPath) {
                    Log.i("XJCommonSystem", "NotifyRequestInstallationPath: ");
                    CommonSysApiInverseRequests commonSysApiInverseRequests = this.this$0.requests;
                    if (commonSysApiInverseRequests != null) {
                        commonSysApiInverseRequests.fireSetInstallationPath(new ReqSetInstallationPath(this.this$0.getAssetsInstallDir().getPath()));
                    }
                }
            };
            arrayList19.add(observable19.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$39$lambda$38(function119, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications20 = this.notifications;
        if (commonSysApiInverseNotifications20 != null) {
            IAttribute<NotifyNaviSoftwareVersionInfo> iAttributeNaviSoftwareVersionInfoAttribute = commonSysApiInverseNotifications20.naviSoftwareVersionInfoAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeNaviSoftwareVersionInfoAttribute, "it.naviSoftwareVersionInfoAttribute()");
            IAttribute<NotifyNaviSoftwareVersionInfo> iAttribute20 = iAttributeNaviSoftwareVersionInfoAttribute;
            iAttribute20.signIn();
            this.remoteAttributes.add(iAttribute20);
            ArrayList<Subscription> arrayList20 = this.remoteSubscriptions;
            Observable<NotifyNaviSoftwareVersionInfo> observable20 = iAttribute20.getObservable();
            final Function1<NotifyNaviSoftwareVersionInfo, Unit> function120 = new Function1<NotifyNaviSoftwareVersionInfo, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$20$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyNaviSoftwareVersionInfo notifyNaviSoftwareVersionInfo) {
                    invoke2(notifyNaviSoftwareVersionInfo);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyNaviSoftwareVersionInfo notifyNaviSoftwareVersionInfo) {
                    Log.i("XJCommonSystem", "naviSoftwareVersionInfoAttribute:" + notifyNaviSoftwareVersionInfo.getNaviSoftwareVersionInfo());
                    CommonSystem commonSystem = this.this$0;
                    String versionInfo = notifyNaviSoftwareVersionInfo.getNaviSoftwareVersionInfo().getVersionInfo();
                    Intrinsics.checkNotNullExpressionValue(versionInfo, "it.naviSoftwareVersionInfo.versionInfo");
                    commonSystem.autonaviStandardBroadcastSend(versionInfo);
                }
            };
            arrayList20.add(observable20.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$41$lambda$40(function120, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications21 = this.notifications;
        if (commonSysApiInverseNotifications21 != null) {
            IAttribute<NotifyOnKeyDown> iAttributeOnKeyDownAttribute = commonSysApiInverseNotifications21.onKeyDownAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeOnKeyDownAttribute, "it.onKeyDownAttribute()");
            IAttribute<NotifyOnKeyDown> iAttribute21 = iAttributeOnKeyDownAttribute;
            iAttribute21.signIn();
            this.remoteAttributes.add(iAttribute21);
            ArrayList<Subscription> arrayList21 = this.remoteSubscriptions;
            Observable<NotifyOnKeyDown> observable21 = iAttribute21.getObservable();
            final Function1<NotifyOnKeyDown, Unit> function121 = new Function1<NotifyOnKeyDown, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$21$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyOnKeyDown notifyOnKeyDown) {
                    invoke2(notifyOnKeyDown);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyOnKeyDown notifyOnKeyDown) {
                    CommonSystem.INSTANCE.getLogger().info("home key trigger befored!");
                    Intent intent = new Intent("android.intent.action.MAIN");
                    intent.addFlags(268435456);
                    intent.addCategory("android.intent.category.HOME");
                    this.this$0.getContext().startActivity(intent);
                }
            };
            arrayList21.add(observable21.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$43$lambda$42(function121, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications22 = this.notifications;
        if (commonSysApiInverseNotifications22 != null) {
            IAttribute<NotifyNaviDayNightStatusChange> iAttributeNaviDayNightStatusChangeAttribute = commonSysApiInverseNotifications22.naviDayNightStatusChangeAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeNaviDayNightStatusChangeAttribute, "it.naviDayNightStatusChangeAttribute()");
            IAttribute<NotifyNaviDayNightStatusChange> iAttribute22 = iAttributeNaviDayNightStatusChangeAttribute;
            iAttribute22.signIn();
            this.remoteAttributes.add(iAttribute22);
            ArrayList<Subscription> arrayList22 = this.remoteSubscriptions;
            Observable<NotifyNaviDayNightStatusChange> observable22 = iAttribute22.getObservable();
            final Function1<NotifyNaviDayNightStatusChange, Unit> function122 = new Function1<NotifyNaviDayNightStatusChange, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$22$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyNaviDayNightStatusChange notifyNaviDayNightStatusChange) {
                    invoke2(notifyNaviDayNightStatusChange);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyNaviDayNightStatusChange notifyNaviDayNightStatusChange) {
                    Log.i("XJCommonSystem", "NotifyNaviDayNightStatusChange");
                    MapStatus mapStatus = this.this$0.mapStatus;
                    Integer value = notifyNaviDayNightStatusChange.getNaviDayNightStatusChange().getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "it.naviDayNightStatusChange.value");
                    mapStatus.setDayNightMode(value.intValue());
                }
            };
            arrayList22.add(observable22.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda5
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$45$lambda$44(function122, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications23 = this.notifications;
        if (commonSysApiInverseNotifications23 != null) {
            IAttribute<NotifyWriteFile> iAttributeWriteFileAttribute = commonSysApiInverseNotifications23.writeFileAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeWriteFileAttribute, "it.writeFileAttribute()");
            IAttribute<NotifyWriteFile> iAttribute23 = iAttributeWriteFileAttribute;
            if (iAttribute23 != null) {
                iAttribute23.signIn();
            }
            this.remoteAttributes.add(iAttribute23);
            ArrayList<Subscription> arrayList23 = this.remoteSubscriptions;
            Observable<NotifyWriteFile> observable23 = iAttribute23.getObservable();
            final Function1<NotifyWriteFile, Unit> function123 = new Function1<NotifyWriteFile, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$23$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyWriteFile notifyWriteFile) throws Throwable {
                    invoke2(notifyWriteFile);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyWriteFile notifyWriteFile) throws Throwable {
                    boolean zWriteToTxt = Tool.writeToTxt(notifyWriteFile.getWriteFile().getPath(), notifyWriteFile.getWriteFile().getName(), notifyWriteFile.getWriteFile().getValue());
                    Log.i("XJCommonSystem", "writeToTxt ::" + zWriteToTxt);
                    if (zWriteToTxt) {
                        CommonSysApiInverseRequests commonSysApiInverseRequests = this.this$0.requests;
                        if (commonSysApiInverseRequests != null) {
                            commonSysApiInverseRequests.fireOnWriteFile(new ReqOnWriteFile(notifyWriteFile.getWriteFile().getTokenId(), WriteFileStatus.E_SUCCESS));
                            return;
                        }
                        return;
                    }
                    CommonSysApiInverseRequests commonSysApiInverseRequests2 = this.this$0.requests;
                    if (commonSysApiInverseRequests2 != null) {
                        commonSysApiInverseRequests2.fireOnWriteFile(new ReqOnWriteFile(notifyWriteFile.getWriteFile().getTokenId(), WriteFileStatus.E_ACCESS_DENIED));
                    }
                }
            };
            arrayList23.add(observable23.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda6
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$47$lambda$46(function123, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications24 = this.notifications;
        if (commonSysApiInverseNotifications24 != null) {
            IAttribute<NotifyCallUpStatus> iAttributeCallUpStatusAttribute = commonSysApiInverseNotifications24.callUpStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeCallUpStatusAttribute, "it.callUpStatusAttribute()");
            IAttribute<NotifyCallUpStatus> iAttribute24 = iAttributeCallUpStatusAttribute;
            iAttribute24.signIn();
            this.remoteAttributes.add(iAttribute24);
            ArrayList<Subscription> arrayList24 = this.remoteSubscriptions;
            Observable<NotifyCallUpStatus> observable24 = iAttribute24.getObservable();
            final Function1<NotifyCallUpStatus, Unit> function124 = new Function1<NotifyCallUpStatus, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$24$1
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
                    String phoneNumber = notifyCallUpStatus.getCallUpStatus().getPhoneNumber();
                    CommonSystem commonSystem = this.this$0;
                    Intrinsics.checkNotNullExpressionValue(phoneNumber, "phoneNumber");
                    commonSystem.callPhone(phoneNumber);
                }
            };
            arrayList24.add(observable24.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda7
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$49$lambda$48(function124, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications25 = this.notifications;
        if (commonSysApiInverseNotifications25 != null) {
            IAttribute<NotifyStorageSpaceStatus> iAttributeStorageSpaceStatusAttribute = commonSysApiInverseNotifications25.storageSpaceStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeStorageSpaceStatusAttribute, "it.storageSpaceStatusAttribute()");
            IAttribute<NotifyStorageSpaceStatus> iAttribute25 = iAttributeStorageSpaceStatusAttribute;
            iAttribute25.signIn();
            ArrayList<Subscription> arrayList25 = this.remoteSubscriptions;
            Observable<NotifyStorageSpaceStatus> observable25 = iAttribute25.getObservable();
            final Function1<NotifyStorageSpaceStatus, Unit> function125 = new Function1<NotifyStorageSpaceStatus, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$25$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyStorageSpaceStatus notifyStorageSpaceStatus) {
                    invoke2(notifyStorageSpaceStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyStorageSpaceStatus notifyStorageSpaceStatus) {
                    Log.i("XJCommonSystem", "NotifyStorageSpaceStatus");
                    SpaceInfo storageSpaceStatus = notifyStorageSpaceStatus.getStorageSpaceStatus();
                    com.neusoft.na.common.bean.SpaceInfo storageSpace = Tool.getStorageSpace(storageSpaceStatus.getPartitionName());
                    storageSpaceStatus.setAvailable(BigInteger.valueOf(storageSpace.getAvailable()));
                    storageSpaceStatus.setCapacity(BigInteger.valueOf(storageSpace.getCapacity()));
                    storageSpaceStatus.setFree(BigInteger.valueOf(storageSpace.getFree()));
                    CommonSysApiInverseRequests commonSysApiInverseRequests = this.this$0.requests;
                    if (commonSysApiInverseRequests != null) {
                        commonSysApiInverseRequests.fireStorageSpace(new ReqStorageSpace(storageSpaceStatus));
                    }
                }
            };
            arrayList25.add(observable25.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda8
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$51$lambda$50(function125, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications26 = this.notifications;
        if (commonSysApiInverseNotifications26 != null) {
            IAttribute<NotifyDeleteFile> iAttributeDeleteFileAttribute = commonSysApiInverseNotifications26.deleteFileAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeDeleteFileAttribute, "it.deleteFileAttribute()");
            IAttribute<NotifyDeleteFile> iAttribute26 = iAttributeDeleteFileAttribute;
            iAttribute26.signIn();
            ArrayList<Subscription> arrayList26 = this.remoteSubscriptions;
            Observable<NotifyDeleteFile> observable26 = iAttribute26.getObservable();
            final Function1<NotifyDeleteFile, Unit> function126 = new Function1<NotifyDeleteFile, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$26$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyDeleteFile notifyDeleteFile) {
                    invoke2(notifyDeleteFile);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyDeleteFile notifyDeleteFile) {
                    Log.i("XJCommonSystem", "NotifyDeleteFile fileName:" + notifyDeleteFile.getDeleteFile().getPath());
                    if (Tool.delete(notifyDeleteFile.getDeleteFile().getPath())) {
                        CommonSysApiInverseRequests commonSysApiInverseRequests = this.this$0.requests;
                        if (commonSysApiInverseRequests != null) {
                            commonSysApiInverseRequests.fireOnDeleteFile(new ReqOnDeleteFile(notifyDeleteFile.getDeleteFile().getTokenId(), DeleteFileStatus.E_SUCCESS));
                            return;
                        }
                        return;
                    }
                    CommonSysApiInverseRequests commonSysApiInverseRequests2 = this.this$0.requests;
                    if (commonSysApiInverseRequests2 != null) {
                        commonSysApiInverseRequests2.fireOnDeleteFile(new ReqOnDeleteFile(notifyDeleteFile.getDeleteFile().getTokenId(), DeleteFileStatus.E_ACCESS_DENIED));
                    }
                }
            };
            arrayList26.add(observable26.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda9
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$53$lambda$52(function126, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications27 = this.notifications;
        if (commonSysApiInverseNotifications27 != null) {
            IAttribute<NotifyShowToastMessage> iAttributeShowToastMessageAttribute = commonSysApiInverseNotifications27.showToastMessageAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeShowToastMessageAttribute, "it.showToastMessageAttribute()");
            IAttribute<NotifyShowToastMessage> iAttribute27 = iAttributeShowToastMessageAttribute;
            iAttribute27.signIn();
            ArrayList<Subscription> arrayList27 = this.remoteSubscriptions;
            Observable<NotifyShowToastMessage> observable27 = iAttribute27.getObservable();
            final CommonSystem$onServerConnect$27$1 commonSystem$onServerConnect$27$1 = new CommonSystem$onServerConnect$27$1(this);
            arrayList27.add(observable27.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda10
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$55$lambda$54(commonSystem$onServerConnect$27$1, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications28 = this.notifications;
        if (commonSysApiInverseNotifications28 != null) {
            Intrinsics.checkNotNull(commonSysApiInverseNotifications28);
            IAttribute<NotifyRequestUpdateExternalStoragePath> iAttributeRequestUpdateExternalStoragePathAttribute = commonSysApiInverseNotifications28.requestUpdateExternalStoragePathAttribute();
            if (iAttributeRequestUpdateExternalStoragePathAttribute != null) {
                iAttributeRequestUpdateExternalStoragePathAttribute.signIn();
            }
            INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$28$1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "NotifyRequestUpdateExternalStoragePath signIn";
                }
            });
            ArrayList<IGettableAttribute<?>> arrayList28 = this.remoteAttributes;
            Intrinsics.checkNotNull(iAttributeRequestUpdateExternalStoragePathAttribute);
            arrayList28.add(iAttributeRequestUpdateExternalStoragePathAttribute);
            ArrayList<Subscription> arrayList29 = this.remoteSubscriptions;
            Observable<NotifyRequestUpdateExternalStoragePath> observable28 = iAttributeRequestUpdateExternalStoragePathAttribute.getObservable();
            final Function1<NotifyRequestUpdateExternalStoragePath, Unit> function127 = new Function1<NotifyRequestUpdateExternalStoragePath, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$28$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyRequestUpdateExternalStoragePath notifyRequestUpdateExternalStoragePath) {
                    invoke2(notifyRequestUpdateExternalStoragePath);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyRequestUpdateExternalStoragePath notifyRequestUpdateExternalStoragePath) {
                    CommonSystem.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$28$2.1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "NotifyRequestUpdateExternalStoragePathCallback";
                        }
                    });
                    this.this$0.UpdateExternalStoragePath();
                }
            };
            arrayList29.add(observable28.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda12
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$57$lambda$56(function127, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications29 = this.notifications;
        if (commonSysApiInverseNotifications29 != null) {
            IAttribute<NotifyApplyUpdateStatus> iAttributeApplyUpdateStatusAttribute = commonSysApiInverseNotifications29.applyUpdateStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeApplyUpdateStatusAttribute, "it.applyUpdateStatusAttribute()");
            IAttribute<NotifyApplyUpdateStatus> iAttribute28 = iAttributeApplyUpdateStatusAttribute;
            iAttribute28.signIn();
            this.remoteAttributes.add(iAttribute28);
            ArrayList<Subscription> arrayList30 = this.remoteSubscriptions;
            Observable<NotifyApplyUpdateStatus> observable29 = iAttribute28.getObservable();
            final Function1<NotifyApplyUpdateStatus, Unit> function128 = new Function1<NotifyApplyUpdateStatus, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$29$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyApplyUpdateStatus notifyApplyUpdateStatus) {
                    invoke2(notifyApplyUpdateStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyApplyUpdateStatus notifyApplyUpdateStatus) {
                    Log.i("XJCommonSystem", "NotifyApplyUpdateStatus");
                    this.this$0.commonCallback.applyFinish();
                }
            };
            arrayList30.add(observable29.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda13
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$59$lambda$58(function128, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications30 = this.notifications;
        if (commonSysApiInverseNotifications30 != null) {
            IAttribute<NotifySwitchArgumentedReality> iAttributeSwitchArgumentedRealityAttribute = commonSysApiInverseNotifications30.switchArgumentedRealityAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeSwitchArgumentedRealityAttribute, "it.switchArgumentedRealityAttribute()");
            IAttribute<NotifySwitchArgumentedReality> iAttribute29 = iAttributeSwitchArgumentedRealityAttribute;
            iAttribute29.signIn();
            ArrayList<Subscription> arrayList31 = this.remoteSubscriptions;
            Observable<NotifySwitchArgumentedReality> observable30 = iAttribute29.getObservable();
            final Function1<NotifySwitchArgumentedReality, Unit> function129 = new Function1<NotifySwitchArgumentedReality, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$30$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifySwitchArgumentedReality notifySwitchArgumentedReality) {
                    invoke2(notifySwitchArgumentedReality);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifySwitchArgumentedReality notifySwitchArgumentedReality) {
                    Log.i("XJCommonSystem", "NotifySwitchArgumentedReality");
                    if (notifySwitchArgumentedReality.getSwitchArgumentedReality() == AROnOff.ENABLE_AR) {
                        this.this$0.sendArStatus(true);
                    } else {
                        this.this$0.sendArStatus(false);
                    }
                }
            };
            arrayList31.add(observable30.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda14
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$61$lambda$60(function129, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications31 = this.notifications;
        if (commonSysApiInverseNotifications31 != null) {
            IAttribute<NotifyPostCustomEvent> iAttributePostCustomEventAttribute = commonSysApiInverseNotifications31.postCustomEventAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributePostCustomEventAttribute, "it.postCustomEventAttribute()");
            IAttribute<NotifyPostCustomEvent> iAttribute30 = iAttributePostCustomEventAttribute;
            iAttribute30.signIn();
            this.remoteAttributes.add(iAttribute30);
            ArrayList<Subscription> arrayList32 = this.remoteSubscriptions;
            Observable<NotifyPostCustomEvent> observable31 = iAttribute30.getObservable();
            final Function1<NotifyPostCustomEvent, Unit> function130 = new Function1<NotifyPostCustomEvent, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$31$1

                /* JADX INFO: compiled from: CommonSystem.kt */
                @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[ServiceProvider.values().length];
                        try {
                            iArr[ServiceProvider.NAIS_CLOUD.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[ServiceProvider.NEUSOFT_CLOUD.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        try {
                            iArr[ServiceProvider.BIGDATA_CLOUD.ordinal()] = 3;
                        } catch (NoSuchFieldError unused3) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyPostCustomEvent notifyPostCustomEvent) throws JSONException {
                    invoke2(notifyPostCustomEvent);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyPostCustomEvent notifyPostCustomEvent) throws JSONException {
                    Boolean boolSendNaviRoad;
                    Boolean boolSendNaviRetrieval;
                    Log.i("XJCommonSystem", "NotifyPostCustomEvent");
                    ServiceProvider serviceProvider = notifyPostCustomEvent.getPostCustomEvent().getServiceProvider();
                    String type = notifyPostCustomEvent.getPostCustomEvent().getCustomEventName();
                    Map<String, String> customEventArgs = notifyPostCustomEvent.getPostCustomEvent().getCustomEventArgs();
                    String strValueOf = String.valueOf(customEventArgs.get("oem"));
                    JSONObject jSONObject = new JSONObject(customEventArgs.get("header"));
                    JSONObject jSONObject2 = new JSONObject(customEventArgs.get("content"));
                    Log.i("XJCommonSystem", "NotifyPostCustomEvent serviceProvider" + serviceProvider + "content:" + jSONObject2);
                    int i = serviceProvider == null ? -1 : WhenMappings.$EnumSwitchMapping$0[serviceProvider.ordinal()];
                    if (i == 1) {
                        Log.i("XJCommonSystem", "ServiceProvider: NAIS_CLOUD");
                        DataCollectionManger companion = DataCollectionManger.INSTANCE.getInstance();
                        if (companion != null) {
                            Intrinsics.checkNotNullExpressionValue(type, "type");
                            companion.post(type, strValueOf, jSONObject, jSONObject2);
                            return;
                        }
                        return;
                    }
                    if (i != 2) {
                        if (i == 3) {
                            Log.i("XJCommonSystem", "ServiceProvider: BIGDATA_CLOUD");
                            return;
                        } else {
                            Log.i("XJCommonSystem", "ServiceProvider: OTHER");
                            return;
                        }
                    }
                    Log.i("XJCommonSystem", "ServiceProvider: NEUSOFT_CLOUD");
                    if (this.this$0.buryPointInterfaceObj != null) {
                        JSONObject jSONObject3 = jSONObject2.getJSONObject("dataBlob");
                        if (!Intrinsics.areEqual(type, this.this$0.TYPE_NAME_NAVI_RETRIEVAL)) {
                            if (Intrinsics.areEqual(type, this.this$0.TYPE_NAME_NAVI_ROAD)) {
                                String startName = jSONObject3.getString("startName");
                                String startLongitude = jSONObject3.getString("startLongitude");
                                String startLatitude = jSONObject3.getString("startLatitude");
                                String destinationName = jSONObject3.getString("destinationName");
                                String destinationLongitude = jSONObject3.getString("destinationLongitude");
                                String destinationLatitude = jSONObject3.getString("destinationLatitude");
                                String destinationPoiType = jSONObject3.getString("destinationPoiType");
                                JSONArray channelInfo = jSONObject3.getJSONArray("channelInfo");
                                boolean z = jSONObject3.getBoolean("guide");
                                String guideStartTime = jSONObject3.getString("guideStartTime");
                                String guideOverTime = jSONObject3.getString("guideOverTime");
                                boolean z2 = jSONObject3.getBoolean("state");
                                String routeType = jSONObject3.getString("routeType");
                                double d = jSONObject3.getDouble("routeLong");
                                long j = jSONObject3.getLong("time");
                                INeusoftBuryPointInterface iNeusoftBuryPointInterface = this.this$0.buryPointInterfaceObj;
                                if (iNeusoftBuryPointInterface != null) {
                                    Intrinsics.checkNotNullExpressionValue(startName, "startName");
                                    Intrinsics.checkNotNullExpressionValue(startLongitude, "startLongitude");
                                    Intrinsics.checkNotNullExpressionValue(startLatitude, "startLatitude");
                                    Intrinsics.checkNotNullExpressionValue(destinationName, "destinationName");
                                    Intrinsics.checkNotNullExpressionValue(destinationLongitude, "destinationLongitude");
                                    Intrinsics.checkNotNullExpressionValue(destinationLatitude, "destinationLatitude");
                                    Intrinsics.checkNotNullExpressionValue(destinationPoiType, "destinationPoiType");
                                    Intrinsics.checkNotNullExpressionValue(channelInfo, "channelInfo");
                                    Intrinsics.checkNotNullExpressionValue(guideStartTime, "guideStartTime");
                                    Intrinsics.checkNotNullExpressionValue(guideOverTime, "guideOverTime");
                                    Intrinsics.checkNotNullExpressionValue(routeType, "routeType");
                                    boolSendNaviRoad = iNeusoftBuryPointInterface.sendNaviRoad(startName, startLongitude, startLatitude, destinationName, destinationLongitude, destinationLatitude, destinationPoiType, channelInfo, z, guideStartTime, guideOverTime, z2, routeType, d, j);
                                } else {
                                    boolSendNaviRoad = null;
                                }
                                Log.i("XJCommonSystem", "type: TYPE_NAME_NAVI_ROAD, res:" + boolSendNaviRoad);
                                return;
                            }
                            Log.i("XJCommonSystem", "type: OTHER");
                            return;
                        }
                        String type2 = jSONObject3.getString("type");
                        String keywordType = jSONObject3.getString("keywordType");
                        String retrievalContent = jSONObject3.getString("retrievalContent");
                        String retrievalTime = jSONObject3.getString("retrievalTime");
                        String targetResultName = jSONObject3.getString("targetResultName");
                        String targetResulPoiType = jSONObject3.getString("targetResulPoiType");
                        String targetResulLongitude = jSONObject3.getString("targetResulLongitude");
                        String targetResulLatitude = jSONObject3.getString("targetResulLatitude");
                        String targetResulCoordinate = jSONObject3.getString("targetResulCoordinate");
                        int i2 = jSONObject3.getInt("targetResulNumber");
                        INeusoftBuryPointInterface iNeusoftBuryPointInterface2 = this.this$0.buryPointInterfaceObj;
                        if (iNeusoftBuryPointInterface2 != null) {
                            Intrinsics.checkNotNullExpressionValue(type2, "type");
                            Intrinsics.checkNotNullExpressionValue(keywordType, "keywordType");
                            Intrinsics.checkNotNullExpressionValue(retrievalContent, "retrievalContent");
                            Intrinsics.checkNotNullExpressionValue(retrievalTime, "retrievalTime");
                            Intrinsics.checkNotNullExpressionValue(targetResultName, "targetResultName");
                            Intrinsics.checkNotNullExpressionValue(targetResulPoiType, "targetResulPoiType");
                            Intrinsics.checkNotNullExpressionValue(targetResulLongitude, "targetResulLongitude");
                            Intrinsics.checkNotNullExpressionValue(targetResulLatitude, "targetResulLatitude");
                            Intrinsics.checkNotNullExpressionValue(targetResulCoordinate, "targetResulCoordinate");
                            boolSendNaviRetrieval = iNeusoftBuryPointInterface2.sendNaviRetrieval(type2, keywordType, retrievalContent, retrievalTime, targetResultName, targetResulPoiType, targetResulLongitude, targetResulLatitude, targetResulCoordinate, i2);
                        } else {
                            boolSendNaviRetrieval = null;
                        }
                        Log.i("XJCommonSystem", "type: TYPE_NAME_NAVI_RETRIEVAL, res:" + boolSendNaviRetrieval);
                        return;
                    }
                    Log.i("XJCommonSystem", "buryPointInterfaceObj is null");
                }
            };
            arrayList32.add(observable31.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda16
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$63$lambda$62(function130, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications32 = this.notifications;
        if (commonSysApiInverseNotifications32 != null) {
            IAttribute<NotifyExportDirectoryToUDisk> iAttributeExportDirectoryToUDiskAttribute = commonSysApiInverseNotifications32.exportDirectoryToUDiskAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeExportDirectoryToUDiskAttribute, "it.exportDirectoryToUDiskAttribute()");
            IAttribute<NotifyExportDirectoryToUDisk> iAttribute31 = iAttributeExportDirectoryToUDiskAttribute;
            iAttribute31.signIn();
            this.remoteAttributes.add(iAttribute31);
            ArrayList<Subscription> arrayList33 = this.remoteSubscriptions;
            Observable<NotifyExportDirectoryToUDisk> observable32 = iAttribute31.getObservable();
            final Function1<NotifyExportDirectoryToUDisk, Unit> function131 = new Function1<NotifyExportDirectoryToUDisk, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$32$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyExportDirectoryToUDisk notifyExportDirectoryToUDisk) {
                    invoke2(notifyExportDirectoryToUDisk);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyExportDirectoryToUDisk notifyExportDirectoryToUDisk) {
                    Log.i("XJCommonSystem", "NotifyExportDirectoryToUDisk");
                    try {
                        Integer tokenId = notifyExportDirectoryToUDisk.getExportDirectoryToUDisk().getTokenId();
                        String src = notifyExportDirectoryToUDisk.getExportDirectoryToUDisk().getSourcePath();
                        String target = notifyExportDirectoryToUDisk.getExportDirectoryToUDisk().getTargetPath();
                        CommonSystem commonSystem = this.this$0;
                        Intrinsics.checkNotNullExpressionValue(tokenId, "tokenId");
                        int iIntValue = tokenId.intValue();
                        Intrinsics.checkNotNullExpressionValue(src, "src");
                        Intrinsics.checkNotNullExpressionValue(target, "target");
                        commonSystem.copyDlt2Usb(iIntValue, src, target);
                    } catch (Exception e) {
                        Log.e("XJCommonSystem", "NotifyExportDirectoryToUDisk: " + e.getMessage());
                    }
                }
            };
            arrayList33.add(observable32.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda17
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$65$lambda$64(function131, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications33 = this.notifications;
        if (commonSysApiInverseNotifications33 != null) {
            IAttribute<NotifyMonitorCOMMStatus> iAttributeMonitorCOMMStatusAttribute = commonSysApiInverseNotifications33.monitorCOMMStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeMonitorCOMMStatusAttribute, "it.monitorCOMMStatusAttribute()");
            IAttribute<NotifyMonitorCOMMStatus> iAttribute32 = iAttributeMonitorCOMMStatusAttribute;
            iAttribute32.signIn();
            this.remoteAttributes.add(iAttribute32);
            ArrayList<Subscription> arrayList34 = this.remoteSubscriptions;
            Observable<NotifyMonitorCOMMStatus> observable33 = iAttribute32.getObservable();
            final Function1<NotifyMonitorCOMMStatus, Unit> function132 = new Function1<NotifyMonitorCOMMStatus, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$33$1
                {
                    super(1);
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyMonitorCOMMStatus notifyMonitorCOMMStatus) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyMonitorCOMMStatus notifyMonitorCOMMStatus) {
                    invoke2(notifyMonitorCOMMStatus);
                    return Unit.INSTANCE;
                }
            };
            arrayList34.add(observable33.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda18
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$67$lambda$66(function132, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications34 = this.notifications;
        if (commonSysApiInverseNotifications34 != null) {
            IAttribute<NotifyDBDataIntegrityCheck> iAttributeDBDataIntegrityCheckAttribute = commonSysApiInverseNotifications34.dBDataIntegrityCheckAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeDBDataIntegrityCheckAttribute, "it.dBDataIntegrityCheckAttribute()");
            IAttribute<NotifyDBDataIntegrityCheck> iAttribute33 = iAttributeDBDataIntegrityCheckAttribute;
            iAttribute33.signIn();
            ArrayList<Subscription> arrayList35 = this.remoteSubscriptions;
            Observable<NotifyDBDataIntegrityCheck> observable34 = iAttribute33.getObservable();
            final CommonSystem$onServerConnect$34$1 commonSystem$onServerConnect$34$1 = new CommonSystem$onServerConnect$34$1(this);
            arrayList35.add(observable34.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda19
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$69$lambda$68(commonSystem$onServerConnect$34$1, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications35 = this.notifications;
        if (commonSysApiInverseNotifications35 != null) {
            IAttribute<NotifyDeleteAllDBData> iAttributeDeleteAllDBDataAttribute = commonSysApiInverseNotifications35.deleteAllDBDataAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeDeleteAllDBDataAttribute, "it.deleteAllDBDataAttribute()");
            IAttribute<NotifyDeleteAllDBData> iAttribute34 = iAttributeDeleteAllDBDataAttribute;
            iAttribute34.signIn();
            this.remoteAttributes.add(iAttribute34);
            ArrayList<Subscription> arrayList36 = this.remoteSubscriptions;
            Observable<NotifyDeleteAllDBData> observable35 = iAttribute34.getObservable();
            final Function1<NotifyDeleteAllDBData, Unit> function133 = new Function1<NotifyDeleteAllDBData, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$35$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyDeleteAllDBData notifyDeleteAllDBData) {
                    invoke2(notifyDeleteAllDBData);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyDeleteAllDBData notifyDeleteAllDBData) {
                    boolean zDeleteRecursively;
                    Log.i("XJCommonSystem", "NotifyDeleteAllDBData, and path == " + notifyDeleteAllDBData.getDeleteAllDBData().getPath());
                    File file = new File(notifyDeleteAllDBData.getDeleteAllDBData().getPath());
                    if (file.exists()) {
                        Log.i("XJCommonSystem", "NotifyDeleteAllDBData, folder.exists()");
                        File file2 = new File(notifyDeleteAllDBData.getDeleteAllDBData().getPath(), "ROOT.NDS");
                        if (file2.exists()) {
                            Log.i("XJCommonSystem", "NotifyDeleteAllDBData, rootFile exists, start delete, " + System.currentTimeMillis());
                            file2.delete();
                            Log.i("XJCommonSystem", "NotifyDeleteAllDBData, rootFile exists, end delete, " + System.currentTimeMillis());
                        }
                        Log.i("XJCommonSystem", "NotifyDeleteAllDBData, folder exists, start delete, " + System.currentTimeMillis());
                        zDeleteRecursively = FilesKt.deleteRecursively(file);
                        Log.i("XJCommonSystem", "NotifyDeleteAllDBData, folder exists, end delete, " + System.currentTimeMillis());
                    } else {
                        Log.i("XJCommonSystem", "NotifyDeleteAllDBData, folder not exists()");
                        zDeleteRecursively = true;
                    }
                    Log.i("XJCommonSystem", "NotifyDeleteAllDBData, requestsCommon?.requestGetDeleteAllDBData(ReqOnDeleteAllDBData())");
                    Log.i("XJCommonSystem", "deleteSuccess == " + zDeleteRecursively);
                    ReqOnDeleteAllDBData reqOnDeleteAllDBData = new ReqOnDeleteAllDBData();
                    reqOnDeleteAllDBData.getArguments().setStatus(zDeleteRecursively ? DeletePathStatus.E_SUCCESS : DeletePathStatus.E_ACCESS_DENIED);
                    reqOnDeleteAllDBData.getArguments().setRequestDelPath(notifyDeleteAllDBData.getDeleteAllDBData().getPath());
                    CommonSysApiInverseRequests commonSysApiInverseRequests = this.this$0.requests;
                    if (commonSysApiInverseRequests != null) {
                        commonSysApiInverseRequests.fireOnDeleteAllDBData(reqOnDeleteAllDBData);
                    }
                }
            };
            arrayList36.add(observable35.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda20
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$71$lambda$70(function133, obj);
                }
            }));
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications36 = this.notifications;
        if (commonSysApiInverseNotifications36 != null) {
            IAttribute<NotifyLocalDBVersionInfo> iAttributeLocalDBVersionInfoAttribute = commonSysApiInverseNotifications36.localDBVersionInfoAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeLocalDBVersionInfoAttribute, "it.localDBVersionInfoAttribute()");
            IAttribute<NotifyLocalDBVersionInfo> iAttribute35 = iAttributeLocalDBVersionInfoAttribute;
            iAttribute35.signIn();
            this.remoteAttributes.add(iAttribute35);
            Log.i(TAG, "register NotifyLocalDBVersionInfo notifications");
            ArrayList<Subscription> arrayList37 = this.remoteSubscriptions;
            Observable<NotifyLocalDBVersionInfo> observable36 = iAttribute35.getObservable();
            final Function1<NotifyLocalDBVersionInfo, Unit> function134 = new Function1<NotifyLocalDBVersionInfo, Unit>() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$36$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyLocalDBVersionInfo notifyLocalDBVersionInfo) {
                    invoke2(notifyLocalDBVersionInfo);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyLocalDBVersionInfo notifyLocalDBVersionInfo) {
                    Log.i("XJCommonSystem", "NotifyLocalDBVersionInfo --> dbVersion == " + notifyLocalDBVersionInfo.getLocalDBVersionInfo().getInfoString());
                    SystemUtils.Companion companion = SystemUtils.INSTANCE;
                    Context context2 = this.this$0.getContext();
                    String infoString = notifyLocalDBVersionInfo.getLocalDBVersionInfo().getInfoString();
                    Intrinsics.checkNotNullExpressionValue(infoString, "it.localDBVersionInfo.infoString");
                    companion.setSystemProperties(context2, "persist.sys.byd.neusoft.dbVer", infoString);
                }
            };
            arrayList37.add(observable36.subscribe(new Action1() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda21
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CommonSystem.onServerConnect$lambda$73$lambda$72(function134, obj);
                }
            }));
        }
        updateOnlineBusinessSwitch();
        if (!this.startOc) {
            ocStartError();
        }
        Log.i(TAG, "Message Reissue");
        usbMount("");
        sendLayoutDirection(this.context.getResources().getConfiguration().getLayoutDirection());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$3$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$5$lambda$4(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$7$lambda$6(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$9$lambda$8(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$11$lambda$10(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$13$lambda$12(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$15$lambda$14(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$17$lambda$16(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$19$lambda$18(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$21$lambda$20(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$23$lambda$22(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$25$lambda$24(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$27$lambda$26(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$29$lambda$28(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$31$lambda$30(Function1 tmp0, Object obj) {
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
    public static final void onServerConnect$lambda$47$lambda$46(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$49$lambda$48(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$51$lambda$50(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$53$lambda$52(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$55$lambda$54(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$57$lambda$56(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$59$lambda$58(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$61$lambda$60(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$63$lambda$62(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$65$lambda$64(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$67$lambda$66(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$69$lambda$68(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$71$lambda$70(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$73$lambda$72(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkFilesSize(String dbFilePath, JSONArray fileJSONArray) throws JSONException {
        Log.i(TAG, "checkFilesSize --> fileJSONArray == " + fileJSONArray);
        if (TextUtils.isEmpty(dbFilePath)) {
            return false;
        }
        int length = fileJSONArray.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = fileJSONArray.getJSONObject(i);
                String string = jSONObject.getString("filePath");
                Log.i(TAG, "check file name == " + string);
                long j = jSONObject.getLong("fileSize");
                Log.i(TAG, "check file fileSize == " + j);
                File file = new File(dbFilePath, string);
                if (!file.exists() || file.length() != j) {
                    Log.i(TAG, file + " --> check file not found, or check file size != fileSize");
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyDb() {
        CommonSysApiInverseRequests commonSysApiInverseRequests;
        NaviStatus companion = NaviStatus.INSTANCE.getInstance(this.context);
        Boolean boolValueOf = companion != null ? Boolean.valueOf(companion.checkDbApply()) : null;
        Intrinsics.checkNotNull(boolValueOf);
        if (!boolValueOf.booleanValue() || (commonSysApiInverseRequests = this.requests) == null) {
            return;
        }
        commonSysApiInverseRequests.fireApplyUpdate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callPhone(String phoneNumber) {
        Log.i(TAG, "callPhone：" + phoneNumber);
        if (this.context.checkSelfPermission("android.permission.CALL_PHONE") == 0) {
            if (isBluetoothConnect()) {
                Log.i(TAG, "bluetooth connected");
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.setFlags(268435456);
                ComponentName componentName = new ComponentName("com.byd.bluetoothcall", "com.byd.bluetoothcall.view.activity.MainActivity");
                intent.putExtra("page", 3);
                intent.putExtra("dial_number", phoneNumber);
                intent.setComponent(componentName);
                this.context.startActivity(intent);
                Log.i(TAG, "startActivity dialpad");
                return;
            }
            Log.i(TAG, "bluetooth not connected");
            PackageManager packageManager = this.context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "this.context.packageManager");
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?activity=Bluetooth"));
            intent2.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
            Intrinsics.checkNotNullExpressionValue(packageManager.queryIntentServices(intent2, 0), "pm.queryIntentServices(intent, 0)");
            if (!r6.isEmpty()) {
                this.context.startService(intent2);
            }
            Log.i(TAG, "startActivity bluetooth setting");
            return;
        }
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage("com.neusoft.na.navigation");
        if (launchIntentForPackage != null) {
            launchIntentForPackage.putExtra(NotificationCompat.CATEGORY_CALL, "tel:$" + phoneNumber);
            this.context.startActivity(launchIntentForPackage);
        }
    }

    private final boolean isBluetoothConnect() {
        boolean z;
        Object objInvoke;
        Log.i(TAG, "isBluetoothConnect");
        Iterator<BluetoothDevice> it = BluetoothAdapter.getDefaultAdapter().getBondedDevices().iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            BluetoothDevice next = it.next();
            try {
                Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", new Class[0]);
                declaredMethod.setAccessible(true);
                objInvoke = declaredMethod.invoke(next, new Object[0]);
                Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.Boolean");
            } catch (Exception unused) {
                Log.i(TAG, "isBluetoothConnect: not connected");
            }
            if (((Boolean) objInvoke).booleanValue()) {
                z = true;
                break;
            }
        }
        Log.i(TAG, "isBluetoothConnect: " + z);
        return z;
    }

    private final void updateOnlineBusinessSwitch() {
        File file = new File(Environment.getExternalStorageDirectory(), "naviexternconfig/na_business_logical.ini");
        if (file.exists() && file.isFile()) {
            INSTANCE.getLogger().debug("using external na_business_logical.ini file: " + file);
            StringBuilder sb = new StringBuilder();
            for (String str : FilesKt.readLines$default(file, null, 1, null)) {
                if (str.length() == 0) {
                    INSTANCE.getLogger().debug("this line is blank");
                } else if (str.charAt(0) == '#') {
                    INSTANCE.getLogger().debug("this line is comment");
                } else {
                    INSTANCE.getLogger().debug("this line is the switch line");
                    StringBuilder sbAppend = sb.append(str);
                    Intrinsics.checkNotNullExpressionValue(sbAppend, "append(value)");
                    StringsKt.appendln(sbAppend);
                }
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
            if (string.length() == 0) {
                INSTANCE.getLogger().debug("na_business_logical.ini file exist, but blank");
                return;
            }
            CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
            if (commonSysApiInverseRequests != null) {
                commonSysApiInverseRequests.fireSetOnlineFeatureAvailableStatus(new ReqSetOnlineFeatureAvailableStatus(string));
                return;
            }
            return;
        }
        INSTANCE.getLogger().debug("external config file not found: " + file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, api.structs.com.neusoft.common.ExternalStorageStatus] */
    /* JADX WARN: Type inference failed for: r2v13, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v14, types: [T, api.structs.com.neusoft.common.ExternalStorageStatus] */
    /* JADX WARN: Type inference failed for: r2v3, types: [T, api.structs.com.neusoft.common.ExternalStorageStatus] */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, api.structs.com.neusoft.common.ExternalStorageStatus] */
    public final void UpdateExternalStoragePath() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = ExternalStorageStatus.USB_UNMOUNTED;
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = "";
        List<String> uSBPaths = Tool.getUSBPaths(this.context);
        if (uSBPaths.size() > 0) {
            int size = uSBPaths.size() - 1;
            if (size >= 0) {
                int i = 0;
                while (true) {
                    String str = uSBPaths.get(i) + ROOT_NDS_PATH;
                    String str2 = uSBPaths.get(i) + ROOT_METADATA_PATH;
                    if (!Tool.fileIsExists(str) || !Tool.fileIsExists(str2)) {
                        if (i == size) {
                            break;
                        } else {
                            i++;
                        }
                    } else {
                        objectRef2.element = uSBPaths.get(i) + DBU_PATH;
                        objectRef.element = ExternalStorageStatus.USB_MOUNTED;
                        break;
                    }
                }
            }
            if (objectRef.element != ExternalStorageStatus.USB_MOUNTED) {
                objectRef.element = ExternalStorageStatus.USB_MOUNTED_WITHOUT_DB_FILE;
            }
        } else {
            objectRef.element = ExternalStorageStatus.USB_UNMOUNTED;
        }
        INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.common.CommonSystem.UpdateExternalStoragePath.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return "UpdateExternalStoragePath::fireUpdateExternalStoragePath::" + objectRef2.element + "containsDbUpdateFiles::" + objectRef.element;
            }
        });
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireUpdateExternalStoragePath(new ReqUpdateExternalStoragePath((String) objectRef2.element, (ExternalStorageStatus) objectRef.element));
        }
    }

    public final void ocStartError() {
        boolean zCreateNewFile;
        boolean zDelete;
        Log.i(TAG, "ocStartError");
        this.startOc = false;
        try {
            File file = new File("/navi/NeusoftHere/db/test.txt");
            zCreateNewFile = file.createNewFile();
            zDelete = file.delete();
            Log.i(TAG, "ocStartError file: " + zCreateNewFile + ", " + zDelete);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean z = zCreateNewFile && zDelete;
        Map mapMapOf = MapsKt.mapOf(TuplesKt.to(ModuleName.ONECORE, false));
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireStartUpState(new ReqStartUpState(mapMapOf, Boolean.valueOf(z)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateExternalStoragePathHasFile(int tokenId, final String fileName) throws Throwable {
        Companion companion = INSTANCE;
        companion.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.common.CommonSystem.updateExternalStoragePathHasFile.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return "updateExternalStoragePathHasFile::fileName::" + fileName;
            }
        });
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        List<String> uSBPaths = Tool.getUSBPaths(this.context);
        Log.i("usbdbupdate", "usbList(start).  " + this.dateFormat.format(Long.valueOf(System.currentTimeMillis())));
        if (uSBPaths.size() > 0) {
            Log.i(TAG, "usbList::" + uSBPaths.size());
            int size = uSBPaths.size() - 1;
            if (size >= 0) {
                int i = 0;
                while (true) {
                    String str = uSBPaths.get(i) + IOUtils.DIR_SEPARATOR_UNIX + fileName;
                    Log.i(TAG, "usbpath::" + str);
                    if (!Tool.fileIsExists(str)) {
                        if (i == size) {
                            break;
                        } else {
                            i++;
                        }
                    } else {
                        Boolean boolFileHasStr = Tool.FileHasStr(str, this.mActivateKey);
                        Intrinsics.checkNotNullExpressionValue(boolFileHasStr, "FileHasStr(usbpath, mActivateKey)");
                        booleanRef.element = boolFileHasStr.booleanValue();
                        break;
                    }
                }
            }
            Log.i("usbdbupdate", "usbList(end).  " + this.dateFormat.format(Long.valueOf(System.currentTimeMillis())));
            INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.common.CommonSystem.updateExternalStoragePathHasFile.3
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "updateExternalStoragePathHasFile::containsDbUpdateFiles::" + booleanRef.element;
                }
            });
            Log.i("usbdbupdate", "fireOnAccessCodingAvailable(start)  " + this.dateFormat.format(Long.valueOf(System.currentTimeMillis())));
            CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
            if (commonSysApiInverseRequests != null) {
                commonSysApiInverseRequests.fireOnAccessCodingAvailable(new ReqOnAccessCodingAvailable(new AccessCodingAvailableStatus(Integer.valueOf(tokenId), booleanRef.element ? AccessCodingStatus.E_SUCCESS : AccessCodingStatus.E_ACTIVATION_CODE_MISMATCH)));
            }
            Log.i("usbdbupdate", "fireOnAccessCodingAvailable(end)  " + this.dateFormat.format(Long.valueOf(System.currentTimeMillis())));
            return;
        }
        companion.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.common.CommonSystem.updateExternalStoragePathHasFile.2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return "updateExternalStoragePathHasFile::USB_NOT_INSERTED";
            }
        });
        CommonSysApiInverseRequests commonSysApiInverseRequests2 = this.requests;
        if (commonSysApiInverseRequests2 != null) {
            commonSysApiInverseRequests2.fireOnAccessCodingAvailable(new ReqOnAccessCodingAvailable(new AccessCodingAvailableStatus(Integer.valueOf(tokenId), AccessCodingStatus.E_USB_NOT_INSERTED)));
        }
    }

    @Override // com.neusoft.na.common.ClientEndpointMangerCallback
    public void onServerDisconnect() {
        Log.i(TAG, "onServerDisconnect");
        unregister();
        this.requests = null;
        this.notifications = null;
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
    }

    @Override // com.neusoft.na.common.IOptionalComponent
    public void stop() {
        ClientEndpointManger clientEndpointManger = this.cEManger;
        if (clientEndpointManger != null) {
            clientEndpointManger.unsetClientEndpointMangerCallback(this);
        }
        ScheduledFuture<?> scheduledFuture = this.future;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void autonaviStandardBroadcastSend(String version) {
        Log.i(TAG, "autonaviStandardBroadcastSend::" + version);
        Intent intent = new Intent();
        intent.setAction(AUTONAVI_STANDARD_BROADCAST_SEND);
        intent.putExtra("KEY_TYPE", 10041);
        intent.putExtra("VERSION_NUM", version);
        this.context.sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendArStatus(boolean status) {
        Intent intent = new Intent();
        intent.setAction("com.neusoft.ar.status");
        intent.putExtra("status", status);
        this.context.sendBroadcast(intent);
    }

    public final void usbMount(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        Log.i(TAG, "usbMount");
        List<String> uSBPaths = Tool.getUSBPaths(this.context);
        if (uSBPaths.size() <= 0) {
            return;
        }
        Log.i(TAG, "usbList::" + uSBPaths.size());
        int size = uSBPaths.size() - 1;
        if (size < 0) {
            return;
        }
        int i = 0;
        while (true) {
            final String str = uSBPaths.get(i) + IOUtils.DIR_SEPARATOR_UNIX;
            Log.i(TAG, "usbpath::" + str);
            if (Tool.fileIsExists(str)) {
                if (this.requests != null) {
                    INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.common.CommonSystem.usbMount.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "UpdateExternalStoragePath::fireUpdateExternalStoragePath::" + str;
                        }
                    });
                    ReqUpdateExternalStoragePath reqUpdateExternalStoragePath = new ReqUpdateExternalStoragePath();
                    reqUpdateExternalStoragePath.getArguments().setMountPath(str);
                    reqUpdateExternalStoragePath.getArguments().setStatus(ExternalStorageStatus.USB_MOUNTED);
                    CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
                    if (commonSysApiInverseRequests != null) {
                        commonSysApiInverseRequests.fireUpdateExternalStoragePath(reqUpdateExternalStoragePath);
                        return;
                    }
                    return;
                }
                return;
            }
            if (i == size) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void usbUnMount() {
        Log.i(TAG, "usbUnMount");
        if (this.requests != null) {
            INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.common.CommonSystem.usbUnMount.1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "UpdateExternalStoragePath::usbUnMount";
                }
            });
            ReqUpdateExternalStoragePath reqUpdateExternalStoragePath = new ReqUpdateExternalStoragePath();
            reqUpdateExternalStoragePath.getArguments().setMountPath("");
            reqUpdateExternalStoragePath.getArguments().setStatus(ExternalStorageStatus.USB_UNMOUNTED);
            CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
            if (commonSysApiInverseRequests != null) {
                commonSysApiInverseRequests.fireUpdateExternalStoragePath(reqUpdateExternalStoragePath);
            }
        }
    }

    protected final File getAppCacheDir() {
        if ((this.context.getApplicationInfo().flags & 262144) != 0) {
            File externalCacheDir = this.context.getExternalCacheDir();
            Intrinsics.checkNotNull(externalCacheDir);
            Intrinsics.checkNotNullExpressionValue(externalCacheDir, "{\n         context.externalCacheDir!!\n      }");
            return externalCacheDir;
        }
        File cacheDir = this.context.getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "{\n         context.cacheDir\n      }");
        return cacheDir;
    }

    public final File getAssetsInstallDir() {
        return new File(this.context.getFilesDir(), "install");
    }

    @Override // com.neusoft.na.common.IGreatWallComponent
    /* JADX INFO: renamed from: getAppRunningStatus, reason: from getter */
    public boolean getRunningStatus() {
        return this.runningStatus;
    }

    @Override // com.neusoft.na.common.IGreatWallComponent
    public void setAppRunningStatusListener(IAppRunningStatusListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.appRunningStatusListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enableTraceLog() {
        saveDltStatus(true);
        FileUtils.getInstance(this.context).copyAssetsToSD("naviexternconfig", "naviexternconfig");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disableTraceLog() {
        saveDltStatus(false);
        stopDltService();
        Tool.deleteDirectory("/sdcard/naviexternconfig");
    }

    private final void saveDltStatus(boolean traceLog) {
        Log.i(TAG, "saveDltStatus:" + traceLog);
        SharedPreferences sharedPreferences = this.context.createDeviceProtectedStorageContext().getSharedPreferences(SHARED_TAG, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "{\n         context.creat…AG, MODE_PRIVATE)\n      }");
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean(SHARED_DLT_TAG, traceLog);
        editorEdit.commit();
    }

    public final boolean getDltStatus() {
        SharedPreferences sharedPreferences = this.context.createDeviceProtectedStorageContext().getSharedPreferences(SHARED_TAG, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "{\n         context.creat…AG, MODE_PRIVATE)\n      }");
        return sharedPreferences.getBoolean(SHARED_DLT_TAG, false);
    }

    private final void startDltService() {
        Log.i(TAG, "startDltService");
        this.context.startForegroundService(new Intent(this.context, (Class<?>) LogService.class));
    }

    private final void stopDltService() {
        Log.i(TAG, "stopDltService");
        this.context.stopService(new Intent(this.context, (Class<?>) LogService.class));
    }

    public final void setProtonCallBack(ProtonCallBack callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.protonCallBack = callback;
    }

    private final void register() {
        this.oneCoreBroadcast = new OneCoreBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ONE_CORE_STOP_ACTION);
        intentFilter.addAction(ACTION_SCREEN_DIRECTION);
        this.context.registerReceiver(this.oneCoreBroadcast, intentFilter);
        Log.i(TAG, "onecore register");
    }

    private final void unregister() {
        Log.i(TAG, "onecore unregister");
        try {
            OneCoreBroadcast oneCoreBroadcast = this.oneCoreBroadcast;
            if (oneCoreBroadcast != null) {
                this.context.unregisterReceiver(oneCoreBroadcast);
            }
        } catch (Exception unused) {
            Log.e(TAG, "unregister");
        }
    }

    /* JADX INFO: compiled from: CommonSystem.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/neusoft/na/common/CommonSystem$OneCoreBroadcast;", "Landroid/content/BroadcastReceiver;", "(Lcom/neusoft/na/common/CommonSystem;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    private final class OneCoreBroadcast extends BroadcastReceiver {
        public OneCoreBroadcast() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i(CommonSystem.TAG, "action == " + (intent != null ? intent.getAction() : null));
            if (!Intrinsics.areEqual(CommonSystem.ONE_CORE_STOP_ACTION, intent != null ? intent.getAction() : null)) {
                if (Intrinsics.areEqual(CommonSystem.ACTION_SCREEN_DIRECTION, intent != null ? intent.getAction() : null)) {
                    CommonSystem.this.screenDirection2Bl(intent.getIntExtra("direction", 0));
                }
            } else {
                Log.i(CommonSystem.TAG, "onReceive: stop onecore");
                CommonSysApiInverseRequests commonSysApiInverseRequests = CommonSystem.this.requests;
                if (commonSysApiInverseRequests != null) {
                    commonSysApiInverseRequests.fireOnStopOneCoreStatus(new ReqOnStopOneCoreStatus.Arguments(NoticeStatus.STATUS_FINISH));
                }
                Log.i(CommonSystem.TAG, "onReceive: stop onecore done");
            }
        }
    }

    private final void sendLayoutDirection(int direction) {
        Log.i(TAG, "direction: " + direction);
        ReqSwitchNaviLayoutDirection reqSwitchNaviLayoutDirection = new ReqSwitchNaviLayoutDirection(NaviLayoutDirection.getByValue(Integer.valueOf(direction)));
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireSwitchNaviLayoutDirection(reqSwitchNaviLayoutDirection);
        }
    }

    public final void screenDirection2Bl(int direction) {
        Log.i(TAG, "fireSetScreenDirection --> direction == " + direction);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireSetScreenDirection(new ReqSetScreenDirection(direction == 0 ? ScreenDirectionStatus.HORIZONTAL : ScreenDirectionStatus.VERTICAL));
        }
        if (this.requests == null) {
            this.screenDirection = direction;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void copyDlt2Usb(final int tokenId, final String src, final String target) {
        Log.i(TAG, "copy2Usb: " + this.flag + ',' + tokenId + ',' + src + ',' + target);
        if (this.flag) {
            this.flag = false;
            Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.neusoft.na.common.CommonSystem$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CommonSystem.copyDlt2Usb$lambda$79(src, this, target, tokenId);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void copyDlt2Usb$lambda$79(String src, CommonSystem this$0, String target, int i) {
        Intrinsics.checkNotNullParameter(src, "$src");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(target, "$target");
        try {
            File file = new File(src);
            List<String> uSBPaths = Tool.getUSBPaths(this$0.context);
            if (file.exists() && uSBPaths.size() > 0) {
                Log.i(TAG, "copyDlt2Usb:" + uSBPaths.get(0));
                if (TextUtils.isEmpty(target)) {
                    target = "/navilog";
                }
                FileUtils.copyFolder(src, uSBPaths.get(0) + target);
                this$0.sendCopyResult(i, true);
            } else {
                Log.w(TAG, src + " folder not exists");
                this$0.sendCopyResult(i, false);
            }
        } catch (Exception e) {
            Log.e(TAG, "copyDlt2Usb: " + e.getMessage());
            this$0.sendCopyResult(i, false);
            e.printStackTrace();
        }
        this$0.flag = true;
        Log.i(TAG, "copy2Usb done");
    }

    private final void sendCopyResult(int tokenId, boolean flag) {
        ExportStatus exportStatus;
        Log.i(TAG, "sendCopyResult: " + flag);
        if (flag) {
            exportStatus = ExportStatus.E_SUCCESS;
        } else {
            exportStatus = ExportStatus.E_FAILURE;
        }
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireOnExportDirectoryToUDisk(new ReqOnExportDirectoryToUDisk(Integer.valueOf(tokenId), exportStatus));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createBLHeartbeatMonitoringTimer() {
        Log.i(TAG, "createMaskVisibleMonitoringTimer");
        this.blHeartbeatMonitoringTimer = new Timer(true);
        TimerTask timerTask = new TimerTask() { // from class: com.neusoft.na.common.CommonSystem$createBLHeartbeatMonitoringTimer$task$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Log.i("XJCommonSystem", "check time --> blHeartbeatCheckNotReceiveCount == " + this.this$0.blHeartbeatCheckNotReceiveCount);
                if (this.this$0.blHeartbeatCheckNotReceiveCount != 0) {
                    this.this$0.blHeartbeatCheckNotReceiveCount = 0;
                    this.this$0.blHeartbeatCheckNotReceivePollCount = 0;
                    Log.i("XJCommonSystem", "blHeartbeat receive data, reset count --> blHeartbeatCheckNotReceiveCount == " + this.this$0.blHeartbeatCheckNotReceiveCount + ", blHeartbeatCheckNotReceivePollCount == " + this.this$0.blHeartbeatCheckNotReceivePollCount);
                    return;
                }
                this.this$0.blHeartbeatCheckNotReceivePollCount++;
                Log.i("XJCommonSystem", "blHeartbeat not receive data --> blHeartbeatCheckNotReceiveCount == " + this.this$0.blHeartbeatCheckNotReceiveCount + ", blHeartbeatCheckNotReceivePollCount == " + this.this$0.blHeartbeatCheckNotReceivePollCount);
                if (this.this$0.blHeartbeatCheckNotReceivePollCount >= 5) {
                    Log.i("XJCommonSystem", "blHeartbeat not receive data == " + this.this$0.blHeartbeatCheckNotReceivePollCount + ", need restart app.");
                    this.this$0.blHeartbeatCheckNotReceivePollCount = 0;
                    this.this$0.getContext().sendBroadcast(new Intent("com.neusoft.na.navigation.action.restart.app"));
                }
            }
        };
        Timer timer = this.blHeartbeatMonitoringTimer;
        if (timer != null) {
            timer.schedule(timerTask, 0L, this.blHeartbeatCheckTime);
        }
    }

    public final void setMapSurfaceStatus(boolean value) {
        Log.i(TAG, "[setMapSurfaceStatus] --> value == " + value);
        this.surfaceStatus = Boolean.valueOf(value);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            Log.i(TAG, "[setMapSurfaceStatus] send " + value);
            commonSysApiInverseRequests.fireSetMapSurfaceStatus(new ReqSetMapSurfaceStatus().getArguments().setMapSurfaceStatus(Boolean.valueOf(value)));
            INSTANCE.getLogger().info("[setMapSurfaceStatus] send " + value);
            this.surfaceStatus = null;
        }
    }

    public final void setWidgetSurfaceStatus(boolean value) {
        Log.i(TAG, "[setWidgetSurfaceStatus] --> value == " + value);
        this.widgetSurfaceStatus = Boolean.valueOf(value);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            Log.i(TAG, "[setWidgetSurfaceStatus] send " + value);
            commonSysApiInverseRequests.fireSetWidgetSurfaceStatus(new ReqSetWidgetSurfaceStatus().getArguments().setWidgetSurfaceStatus(Boolean.valueOf(value)));
            this.widgetSurfaceStatus = null;
        }
    }

    public final void setClusterSurfaceStatus(boolean value) {
        Log.i(TAG, "[setClusterSurfaceStatus] --> value == " + value);
        this.clusterSurfaceStatus = Boolean.valueOf(value);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            Log.i(TAG, "[setClusterSurfaceStatus] send " + value);
            commonSysApiInverseRequests.fireSetClusterSurfaceStatus(new ReqSetClusterSurfaceStatus().getArguments().setClusterSurfaceStatus(Boolean.valueOf(value)));
            this.clusterSurfaceStatus = null;
        }
    }

    public final void setMapScreenStatus(int value) {
        Log.i(TAG, "[setMapScreenStatus] --> value == " + value);
        this.mapScreenStatus = value;
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            Log.i(TAG, "[setMapScreenStatus] send " + value);
            commonSysApiInverseRequests.fireSetMapScreenStatus(new ReqSetMapScreenStatus().getArguments().setMapScreenStatus(Integer.valueOf(value)));
            this.mapScreenStatus = -1;
        }
    }

    public final void setNetworkState(boolean value) {
        Log.i(TAG, "[setNetworkState] --> networkState == " + value);
        this.networkState = Boolean.valueOf(value);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            Log.i(TAG, "[setNetworkState] send " + value);
            commonSysApiInverseRequests.fireSetInternetSwitchStatus(new ReqSetInternetSwitchStatus().getArguments().setSwitchStatus(Boolean.valueOf(value)));
            this.networkState = null;
        }
    }

    public final void setBrightnessValue(float value) {
        Log.i(TAG, "[setBrightnessValue] --> value == " + value);
        this.brightnessValue = Float.valueOf(value);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            if (((double) (Math.abs(value - this.lastBrightness) / this.lastBrightness)) > 0.1d) {
                Log.i(TAG, "[setBrightnessValue] send " + value);
                commonSysApiInverseRequests.fireSetBrightnessValue(new ReqSetBrightnessValue().getArguments().setBrightness(Float.valueOf(value)));
                this.lastBrightness = value;
                this.brightnessValue = null;
                return;
            }
            Log.i(TAG, "[setBrightnessValue] transmit is false, so do not send.");
        }
    }

    public final void setOnKeyDownValue(int value) {
        Log.i(TAG, "[setOnKeyDownValue] --> value == " + value);
        this.onKeyDownValue = Integer.valueOf(value);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            Log.i(TAG, "[setOnKeyDownValue] send " + value);
            commonSysApiInverseRequests.fireSetOnKeyDownValue(new ReqSetOnKeyDownValue().getArguments().setOnKeyDownValue(Integer.valueOf(value)));
            this.onKeyDownValue = null;
        }
    }

    public final void setVehicleBrakingStatus(boolean value) {
        Log.i(TAG, "[setVehicleBrakingStatus] --> value == " + value);
        this.vehicleBrakingStatus = Boolean.valueOf(value);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            Log.i(TAG, "[setVehicleBrakingStatus] send " + value);
            commonSysApiInverseRequests.fireSetVehicleBrakingStatus(new ReqSetVehicleBrakingStatus().getArguments().setBrakingStatus(Boolean.valueOf(value)));
            this.vehicleBrakingStatus = null;
        }
    }

    public final void setExtCloudServerStatus(boolean value) {
        Log.i(TAG, "[setExtCloudServerStatus] --> value == " + value);
        this.extCloudServerStatus = Boolean.valueOf(value);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            Log.i(TAG, "[setExtCloudServerStatus] send " + value);
            commonSysApiInverseRequests.fireSetExtCloudServerStatus(new ReqSetExtCloudServerStatus().getArguments().setServerStatus(Boolean.valueOf(value)));
            this.extCloudServerStatus = null;
        }
    }

    public final void setSystemLocale(Locale value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Log.i(TAG, "[setSystemLocale] --> value == " + value);
        this.systemLocale = value;
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            ILocale iLocale = new ILocale();
            iLocale.setLanguage(value.getISO3Language());
            String iSO3Country = value.getISO3Country();
            iLocale.setCountry(!(iSO3Country == null || iSO3Country.length() == 0) ? value.getISO3Country() : "");
            String script = value.getScript();
            if (!(script == null || script.length() == 0)) {
                iLocale.setScript(value.getScript());
            }
            Log.i(TAG, "[setSystemLocale] send " + iLocale);
            commonSysApiInverseRequests.fireSetSystemLocale(new ReqSetSystemLocale().getArguments().setLocale(iLocale));
            this.systemLocale = null;
        }
    }

    private final void setSystemTime() {
        Log.i(TAG, "[setSystemTime]");
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            Calendar calendar = Calendar.getInstance();
            long timeInMillis = calendar.getTimeInMillis() + ((long) calendar.getTimeZone().getRawOffset()) + ((long) calendar.get(16));
            Log.i(TAG, "[setSystemTime] send " + timeInMillis);
            ReqSetSystemTime.Arguments arguments = new ReqSetSystemTime().getArguments();
            BigInteger bigIntegerValueOf = BigInteger.valueOf(timeInMillis);
            Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "valueOf(this)");
            commonSysApiInverseRequests.fireSetSystemTime(arguments.setSystemTime(bigIntegerValueOf));
        }
    }

    private final void setSystemTimeChange() {
        Log.i(TAG, "[setSystemTimeChange]");
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            Calendar calendar = Calendar.getInstance();
            long timeInMillis = calendar.getTimeInMillis() + ((long) calendar.getTimeZone().getRawOffset()) + ((long) calendar.get(16));
            Log.i(TAG, "[setSystemTime] send " + timeInMillis);
            ReqSetSystemTimeChange.Arguments arguments = new ReqSetSystemTimeChange().getArguments();
            BigInteger bigIntegerValueOf = BigInteger.valueOf(timeInMillis);
            Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "valueOf(this)");
            commonSysApiInverseRequests.fireSetSystemTimeChange(arguments.setSystemTime(bigIntegerValueOf));
        }
    }

    public final void setSystemTimeFormat() {
        Log.i(TAG, "[setSystemTimeFormat]");
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            int i = DateFormat.is24HourFormat(this.context) ? 2 : 1;
            Log.i(TAG, "[setSystemTimeFormat] send " + i);
            commonSysApiInverseRequests.fireSetSystemTimeFormat(new ReqSetSystemTimeFormat().getArguments().setSystemTimeFormat(Integer.valueOf(i)));
            setSystemTime();
        }
    }

    public final void setSystemTimeFormatChanged(boolean value) {
        Log.i(TAG, "[setSystemTimeFormatChanged]");
        this.systemTimeFormatChanged = Boolean.valueOf(value);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            int i = value ? 2 : 1;
            Log.i(TAG, "[setSystemTimeFormatChanged] send " + i);
            commonSysApiInverseRequests.fireSetSystemTimeFormat(new ReqSetSystemTimeFormat().getArguments().setSystemTimeFormat(Integer.valueOf(i)));
            this.systemTimeFormatChanged = null;
            setSystemTime();
        }
    }

    public final void setSystemTimeZoneOffset() {
        Log.i(TAG, "[setSystemTimeZoneOffset]");
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            Calendar calendar = Calendar.getInstance();
            int rawOffset = (calendar.getTimeZone().getRawOffset() + calendar.get(16)) / 1000;
            Log.i(TAG, "[setSystemTimeZoneOffset] send " + rawOffset);
            commonSysApiInverseRequests.fireSetSystemTimeZoneOffset(new ReqSetSystemTimeZoneOffset().getArguments().setSystemTimeZoneOffset(Integer.valueOf(rawOffset)));
            setSystemTimeChange();
            setSystemTime();
        }
    }

    public final void setInMultiWindowMode(boolean value) {
        Log.i(TAG, "[setInMultiWindowMode] --> value == " + value);
        this.isInMultiWindowMode = Boolean.valueOf(value);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            ReqSetScreenMode reqSetScreenMode = new ReqSetScreenMode();
            reqSetScreenMode.getArguments().setStatus(value ? ScreenModeStatus.SPLIT : ScreenModeStatus.FULL);
            Log.i(TAG, "[setInMultiWindowMode] send == " + value);
            commonSysApiInverseRequests.fireSetScreenMode(reqSetScreenMode);
            this.isInMultiWindowMode = null;
        }
    }

    public final void replenishSendToBL() {
        Log.i(TAG, "[replenishSendToBL]");
        int i = this.screenDirection;
        if (i != -1) {
            screenDirection2Bl(i);
        }
        Boolean bool = this.isInMultiWindowMode;
        if (bool != null) {
            Intrinsics.checkNotNull(bool);
            setInMultiWindowMode(bool.booleanValue());
        }
        Boolean bool2 = this.surfaceStatus;
        if (bool2 != null) {
            Intrinsics.checkNotNull(bool2);
            setMapSurfaceStatus(bool2.booleanValue());
        }
        int i2 = this.mapScreenStatus;
        if (i2 != -1) {
            setMapScreenStatus(i2);
        }
        Boolean bool3 = this.widgetSurfaceStatus;
        if (bool3 != null) {
            Intrinsics.checkNotNull(bool3);
            setWidgetSurfaceStatus(bool3.booleanValue());
        }
        Boolean bool4 = this.clusterSurfaceStatus;
        if (bool4 != null) {
            Intrinsics.checkNotNull(bool4);
            setClusterSurfaceStatus(bool4.booleanValue());
        }
        Boolean bool5 = this.networkState;
        if (bool5 != null) {
            Intrinsics.checkNotNull(bool5);
            setNetworkState(bool5.booleanValue());
        }
        Float f = this.brightnessValue;
        if (f != null) {
            Intrinsics.checkNotNull(f);
            setBrightnessValue(f.floatValue());
        }
        Integer num = this.onKeyDownValue;
        if (num != null) {
            Intrinsics.checkNotNull(num);
            setOnKeyDownValue(num.intValue());
        }
        Boolean bool6 = this.vehicleBrakingStatus;
        if (bool6 != null) {
            Intrinsics.checkNotNull(bool6);
            setVehicleBrakingStatus(bool6.booleanValue());
        }
        Boolean bool7 = this.extCloudServerStatus;
        if (bool7 != null) {
            Intrinsics.checkNotNull(bool7);
            setExtCloudServerStatus(bool7.booleanValue());
        }
        Locale locale = this.systemLocale;
        if (locale != null) {
            Intrinsics.checkNotNull(locale);
            setSystemLocale(locale);
        }
        Boolean bool8 = this.systemTimeFormatChanged;
        if (bool8 != null) {
            Intrinsics.checkNotNull(bool8);
            setSystemTimeFormatChanged(bool8.booleanValue());
        }
        setSystemTimeZoneOffset();
    }

    public final void onConfigurationChanged(Configuration newConfig) {
        MapStyleType mapStyleType;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        sendLayoutDirection(newConfig.getLayoutDirection());
        int i = newConfig.uiMode & 48;
        Log.i(TAG, "onConfigurationChanged systemUiMode == " + i);
        if (i == 32) {
            mapStyleType = MapStyleType.NIGHT;
        } else {
            mapStyleType = MapStyleType.DAY;
        }
        this.presentationDayNightModeChangeSubject.onNext(Integer.valueOf(mapStyleType == MapStyleType.NIGHT ? 2 : 1));
        ReqReqSwitchDayNightMode reqReqSwitchDayNightMode = new ReqReqSwitchDayNightMode(mapStyleType);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requests;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireReqSwitchDayNightMode(reqReqSwitchDayNightMode);
        }
    }
}
