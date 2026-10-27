package com.neusoft.na.mots;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Instrumentation;
import android.app.UiModeManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.car.Car;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.yun.BYDAutoYunDevice;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import cerence.ark.assistant.controller.domain.ude.UDERequestsEnum;
import cerence.ark.assistant.controller.enumeration.YNUEnum;
import com.bydux.api.common.views.skin.entity.AttrFactory;
import com.google.android.gms.actions.SearchIntents;
import com.neusoft.na.byd.Byd8155fSystem;
import com.neusoft.na.common.BasicDialog;
import com.neusoft.na.common.BasicPrivacyView;
import com.neusoft.na.common.CommonSystem;
import com.neusoft.na.common.FileUtils;
import com.neusoft.na.common.IArComponent;
import com.neusoft.na.common.IBydBootAdapterComponent;
import com.neusoft.na.common.IGreatWallAarComponent;
import com.neusoft.na.common.ServiceBase;
import com.neusoft.na.common.presentation.IMapPresentationComponent;
import com.neusoft.na.common.util.KeyboardUtil;
import com.neusoft.na.common.webview.dialog.IWebViewDialogComponent;
import com.neusoft.na.mots.MainActivity;
import com.neusoft.na.mots.back.door.BackDoorDialog;
import com.neusoft.na.navigation.IMotsServiceCallBack;
import com.neusoft.na.navigation.IMotsServiceInterface;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import com.neusoft.na.ocservice.ConstantsKt;
import com.neusoft.na.ocservice.MotsService;
import com.neusoft.na.ocservice.OneCoreService;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import com.neusoft.na.ocservice.util.SystemUtils;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import mu.KLogging;
import org.bouncycastle.i18n.TextBundle;
import org.json.JSONObject;
import org.xwalk.core.XWalkView;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000ê\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\bA\u0018\u0000 Ì\u00012\u00020\u00012\u00020\u0002:\u0002Ì\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u000bH\u0016J\u0018\u0010L\u001a\u00020J2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020\u000bH\u0016J\b\u0010M\u001a\u00020JH\u0003J\b\u0010N\u001a\u00020JH\u0002J\b\u0010O\u001a\u00020JH\u0016J\b\u0010P\u001a\u00020JH\u0002J\b\u0010Q\u001a\u00020JH\u0002J\b\u0010R\u001a\u00020JH\u0002J\u000e\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00050TH\u0002J\b\u0010U\u001a\u00020JH\u0002J\b\u0010V\u001a\u00020JH\u0002J\b\u0010W\u001a\u00020JH\u0016J\b\u0010X\u001a\u00020JH\u0016J\b\u0010Y\u001a\u00020JH\u0002J\b\u0010Z\u001a\u00020JH\u0002J\u0012\u0010[\u001a\u00020\u000b2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0016J\b\u0010^\u001a\u00020JH\u0016J\b\u0010_\u001a\u00020JH\u0016J\b\u0010`\u001a\u00020\u000bH\u0002J\b\u0010a\u001a\u00020JH\u0016J\b\u0010b\u001a\u00020JH\u0002J\b\u0010c\u001a\u00020JH\u0002J\b\u0010d\u001a\u00020JH\u0002J\b\u0010e\u001a\u00020JH\u0002J\b\u0010f\u001a\u00020JH\u0002J\b\u0010g\u001a\u00020JH\u0002J\b\u0010h\u001a\u00020JH\u0002J\b\u0010i\u001a\u00020JH\u0002J\b\u0010j\u001a\u00020JH\u0002J\b\u0010k\u001a\u00020\u000bH\u0002J\b\u0010l\u001a\u00020\u000bH\u0002J\b\u0010m\u001a\u00020\u000bH\u0002J\b\u0010n\u001a\u00020\u000bH\u0002J\u0012\u0010o\u001a\u00020\u000b2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0002J\b\u0010p\u001a\u00020JH\u0016J\b\u0010q\u001a\u00020JH\u0002J\b\u0010r\u001a\u00020JH\u0002J\b\u0010s\u001a\u00020JH\u0002J\u0010\u0010t\u001a\u00020J2\u0006\u0010u\u001a\u00020\u000bH\u0016J\b\u0010v\u001a\u00020JH\u0002J\b\u0010w\u001a\u00020JH\u0002J\b\u0010x\u001a\u00020JH\u0002J\b\u0010y\u001a\u00020JH\u0016J\u0010\u0010z\u001a\u00020J2\u0006\u0010{\u001a\u00020|H\u0016J\u0012\u0010}\u001a\u00020J2\b\u0010~\u001a\u0004\u0018\u00010\u007fH\u0014J\t\u0010\u0080\u0001\u001a\u00020JH\u0014J\u001a\u0010\u0081\u0001\u001a\u00020J2\u0007\u0010\u0082\u0001\u001a\u00020\u000b2\u0006\u0010{\u001a\u00020|H\u0016J\u0015\u0010\u0083\u0001\u001a\u00020J2\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001H\u0014J\t\u0010\u0086\u0001\u001a\u00020JH\u0014J4\u0010\u0087\u0001\u001a\u00020J2\u0007\u0010\u0088\u0001\u001a\u00020\u00172\u0010\u0010\u0089\u0001\u001a\u000b\u0012\u0006\b\u0001\u0012\u00020\u00050\u008a\u00012\b\u0010\u008b\u0001\u001a\u00030\u008c\u0001H\u0017¢\u0006\u0003\u0010\u008d\u0001J\t\u0010\u008e\u0001\u001a\u00020JH\u0015J\t\u0010\u008f\u0001\u001a\u00020JH\u0014J\t\u0010\u0090\u0001\u001a\u00020JH\u0014J\u0015\u0010\u0091\u0001\u001a\u00020J2\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001H\u0002J\u001b\u0010\u0092\u0001\u001a\u00020J2\u0007\u0010\u0093\u0001\u001a\u00020\u00172\u0007\u0010\u0094\u0001\u001a\u00020\u0017H\u0016J\t\u0010\u0095\u0001\u001a\u00020JH\u0002J\t\u0010\u0096\u0001\u001a\u00020JH\u0002J\t\u0010\u0097\u0001\u001a\u00020JH\u0003J\t\u0010\u0098\u0001\u001a\u00020JH\u0002J\u0014\u0010\u0099\u0001\u001a\u00020J2\t\u0010\u009a\u0001\u001a\u0004\u0018\u00010|H\u0002J\t\u0010\u009b\u0001\u001a\u00020JH\u0002J\u0012\u0010\u009c\u0001\u001a\u00020J2\u0007\u0010\u0082\u0001\u001a\u00020\u000bH\u0002J\t\u0010\u009d\u0001\u001a\u00020JH\u0002J\u0012\u0010\u009e\u0001\u001a\u00020J2\u0007\u0010\u009f\u0001\u001a\u00020\u000bH\u0002J\t\u0010 \u0001\u001a\u00020JH\u0003J\t\u0010¡\u0001\u001a\u00020JH\u0002J\u0012\u0010¢\u0001\u001a\u00020J2\u0007\u0010£\u0001\u001a\u00020\u0017H\u0002J\t\u0010¤\u0001\u001a\u00020\u0017H\u0002J\u0012\u0010¥\u0001\u001a\u00020J2\u0007\u0010\u0094\u0001\u001a\u00020\u0017H\u0002J\u0012\u0010¦\u0001\u001a\u00020J2\u0007\u0010§\u0001\u001a\u00020\u000bH\u0003J\t\u0010¨\u0001\u001a\u00020JH\u0002J\t\u0010©\u0001\u001a\u00020JH\u0002J\t\u0010ª\u0001\u001a\u00020JH\u0002J\t\u0010«\u0001\u001a\u00020JH\u0003J\u001a\u0010¬\u0001\u001a\u00020J2\u0007\u0010\u0082\u0001\u001a\u00020\u000b2\u0006\u0010{\u001a\u00020|H\u0002J\u0011\u0010\u00ad\u0001\u001a\u00020J2\u0006\u0010v\u001a\u00020\u000bH\u0002J\u0011\u0010®\u0001\u001a\u00020J2\u0006\u0010v\u001a\u00020\u000bH\u0002J\t\u0010¯\u0001\u001a\u00020JH\u0003J\t\u0010°\u0001\u001a\u00020JH\u0002J\u0012\u0010±\u0001\u001a\u00020J2\u0007\u0010²\u0001\u001a\u00020\u000bH\u0002J \u0010³\u0001\u001a\u00020J2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00050T2\u0007\u0010´\u0001\u001a\u00020\u000bH\u0002J\t\u0010µ\u0001\u001a\u00020JH\u0002J\t\u0010¶\u0001\u001a\u00020JH\u0016J\t\u0010·\u0001\u001a\u00020JH\u0002J\t\u0010¸\u0001\u001a\u00020JH\u0002J\u0014\u0010¹\u0001\u001a\u00020J2\t\u0010º\u0001\u001a\u0004\u0018\u00010\u0005H\u0016J\u0017\u0010»\u0001\u001a\u00020J2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00050TH\u0002J \u0010»\u0001\u001a\u00020J2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00050T2\u0007\u0010´\u0001\u001a\u00020\u000bH\u0002J\t\u0010¼\u0001\u001a\u00020JH\u0002J\t\u0010½\u0001\u001a\u00020JH\u0002J\t\u0010¾\u0001\u001a\u00020JH\u0002J\t\u0010¿\u0001\u001a\u00020JH\u0002J\t\u0010À\u0001\u001a\u00020JH\u0002J\t\u0010Á\u0001\u001a\u00020JH\u0002J\t\u0010Â\u0001\u001a\u00020JH\u0002J\t\u0010Ã\u0001\u001a\u00020JH\u0002J\t\u0010Ä\u0001\u001a\u00020JH\u0016J\t\u0010Å\u0001\u001a\u00020JH\u0016J\t\u0010Æ\u0001\u001a\u00020JH\u0003J\u0012\u0010Ç\u0001\u001a\u00020J2\u0007\u0010È\u0001\u001a\u00020\u000bH\u0002J\t\u0010É\u0001\u001a\u00020JH\u0016J\u0007\u0010Ê\u0001\u001a\u00020JJ\u0007\u0010Ë\u0001\u001a\u00020JR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020@X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010A\u001a\u0004\u0018\u00010BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u0010\u0010G\u001a\u0004\u0018\u00010HX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Í\u0001"}, d2 = {"Lcom/neusoft/na/mots/MainActivity;", "Landroid/app/Activity;", "Lcom/neusoft/na/ocservice/Broadcast/MotsBroadcastCallBack;", "()V", "BL", "", "CL", "EHP", "GS", "OC", "activationStatus", "", "arComponentWapper", "Lcom/neusoft/na/common/IArComponent;", "binder", "Lcom/neusoft/na/navigation/IMotsServiceInterface;", "hmiMaskGoneDelay", "", "hmiMaskGoneTimer", "Ljava/util/Timer;", "hmiMaskGoneTimerTask", "Ljava/util/TimerTask;", "hmiSettingsDayNightMode", "", "isByd8155Kill", "isDisplay", "isFirstStart", "isMask", "isShowSystemPermissionDialog", "keyboardStatus", "Lcom/neusoft/na/mots/MainActivity$Companion$keyBoardValue;", "lockActivity", "mBydType", "mHandler", "Landroid/os/Handler;", "mHmiVisible", "mMotsBroadcastReceiver", "Lcom/neusoft/na/ocservice/Broadcast/MotsBroadcastReceiver;", "mPhoneNumber", "mPrivacyPolicyView", "Lcom/neusoft/na/common/BasicPrivacyView;", "mWebView", "Landroid/webkit/WebView;", "mXWalkView", "Lorg/xwalk/core/XWalkView;", "maarystem", "Lcom/neusoft/na/common/IGreatWallAarComponent;", "mapDisplayAreaPoint", "Landroid/graphics/Point;", "mapMaskTimer", "mapMaskTimerTask", "maskGoneTimer", "maskGoneTimerTask", "motsCallBack", "Lcom/neusoft/na/navigation/IMotsServiceCallBack;", "naviHMIFullScreenPageFlag", "noNeedServiceBroadcastReceiver", "Landroid/content/BroadcastReceiver;", "permissionDialog", "Lcom/neusoft/na/common/BasicDialog;", "presentationSurface", "Landroid/graphics/SurfaceTexture;", "privacyPolicyStatus", "serviceConnection", "Landroid/content/ServiceConnection;", "surfaceMainMap", "Landroid/view/Surface;", "getSurfaceMainMap", "()Landroid/view/Surface;", "setSurfaceMainMap", "(Landroid/view/Surface;)V", "webViewDialogComponent", "Lcom/neusoft/na/common/webview/dialog/IWebViewDialogComponent;", "ARStatus", "", "boolean", "activationPrivacyPolicyStatusChanged", "addGreatWallAddGreatWallV61AAR", "addGreatWallB01Aar", "appSwitchToBackground", "b01DayMode", "b01NightMode", "backToHome", "checkPermissions", "", "checkStartMots", "checkWindowDecorViewLocationOnScreen", "closeActivity", "closePresentation", "createArManagerinstance", "dayMode", "dispatchKeyEvent", "event", "Landroid/view/KeyEvent;", "exitWithoutRestarting", "goneMask", "hideTestImage", "hmiRequestFocus", "init", "initByd", "initGreatWallA01", "initGreatWallB01", "initGreatWallV61", "initPresentationSystemInterfaceByByd8155F", "initSmart", "initWeyforproductv3", "initZeekr", "isBluetoothConnect", "isByd8155Launcher", "isBydHasAll", "isNightModeByByd", "keyCodeFilter", "killActivity", "maskGone", "mc2DayMode", "mc2NightMode", "naviHMIFullScreenPageChanged", "isNaviHMIFullScreenPage", "nightMode", "notifyDiLinkStartedByByd8155f", "notifySystem4HmiSettingsDayNightMode", "onBackPressed", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMultiWindowModeChanged", OneCoreService.PAYLOAD_IN_MULTI_WINDOW_MODE, "onNewIntent", "intent", "Landroid/content/Intent;", "onPause", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onStart", "onStop", UDERequestsEnum.OPEN_MAP, "presentationTest", "key", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "printWindowInfo", "registerMotsBroadcastReceiver", "registerNoNeedServiceReceiver", "removeBydView", "screenDirection2Bl", Car.CAR_CONFIGURATION_SERVICE, "sendBydBroadcast", "sendMultiWindowModeBroadcast", "sendNaviActivityStartBC", "sendNaviForegroundState", "isForeground", "sendPermissionStickyBroadcast", "sendSetDefaultLauncher", "sendStatus", "status", "sendSwitchDayNightModeBroadcast", "sendSwitchDayNightModeBroadcastForByd", "setByd8155fActionBarBackground", "isNight", "setDayNightMode", "setFullSystemUiByByd8155f", "setGreatwallV61ActionBarBackground", "setMapDisplayAreaGlobalLayout", "setMapMaskColor", "setNavbarColor", "setNavbarColor4Byd", "setSmartActionBarBackground", "setStartImageByByd8155F", "setSystemUiByByd8155f", "switchInMultiWindowMode", "show8155Permission", "onlyCall", "showAlertWebView", "showBackDoor", "showHmi", "showMapPresentation", "showMessage", TextBundle.TEXT_ENTRY, "showPermissionDialog", "showPrivacyPolicy", "showV61LaunchHint", "startAppDetailActivity", "startHmiMaskRemoveTask", "startMaskRemoveTask", "startMotsMainService", "switchBydStartImage", "switchZeekrStartImage", "timeChanged", "timeZoneChanged", "transparentStatusBarColor", "updateWidget", "isShow", "webViewPrivacyDetails", "webViewShowHmi", "xWalkViewShowHmi", "Companion", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MainActivity extends Activity implements com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack {
    private static final String ACTION_BACK_TO_HOME = "com.neusoft.na.navigation.backToHome";
    private static final String ACTION_HMI_SETTINGS_DAY_NIGHT_MODE_CHANGED = "com.neusoft.na.navigation.action.hmi.settings.dayNightMode.changed";
    public static final int ACTION_PERMISSIONS_CALL_PHONE = 1431;
    public static final int ACTION_PERMISSIONS_CHANGED = 143;
    public static final int APP_BACKGROUND = 11;
    public static final int APP_FINISH = 9;
    public static final int APP_FOREGROUND = 10;
    public static final String AR_COMPONENT = "com.neusoft.arcomponent.ArComponentWapper";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String MINI_BAR_TOOL = "com.neusoft.greatwallb01aar.MiniBarTool";
    public static final String START_ACTIVITY = "com.neusoft.na.mots.StartActivity";
    public static final String TAG = "XJMainActivity";
    private static final String TAG_BYD_8155 = "TAGBYD8155";
    private static final String TAG_V61_HINT = "TAGV61HINT";
    public static final String WWW_URL = "file:///android_asset/hmi/index.html";
    public static final String WWW_URL_C4PRO = "file:///android_asset/hmi/index_c4pro.html";
    public static final String WWW_URL_C4PRO_SAFE = "https://appassets.androidplatform.net/assets/hmi/index_c4pro.html";
    public static final String WWW_URL_SAFE = "https://appassets.androidplatform.net/assets/hmi/index.html";
    private boolean activationStatus;
    private IArComponent arComponentWapper;
    private IMotsServiceInterface binder;
    private TimerTask hmiMaskGoneTimerTask;
    private boolean isByd8155Kill;
    private boolean isDisplay;
    private boolean isMask;
    private boolean isShowSystemPermissionDialog;
    private boolean lockActivity;
    private boolean mHmiVisible;
    private MotsBroadcastReceiver mMotsBroadcastReceiver;
    private String mPhoneNumber;
    private BasicPrivacyView mPrivacyPolicyView;
    private WebView mWebView;
    private XWalkView mXWalkView;
    private IGreatWallAarComponent maarystem;
    private Timer mapMaskTimer;
    private TimerTask mapMaskTimerTask;
    private TimerTask maskGoneTimerTask;
    private boolean naviHMIFullScreenPageFlag;
    private BasicDialog permissionDialog;
    private SurfaceTexture presentationSurface;
    private boolean privacyPolicyStatus;
    private Surface surfaceMainMap;
    private IWebViewDialogComponent webViewDialogComponent;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String BL = "";
    private String OC = "";
    private String CL = "";
    private String GS = "";
    private String EHP = "";
    private Handler mHandler = new Handler();
    private Companion.keyBoardValue keyboardStatus = Companion.keyBoardValue.NO;
    private IMotsServiceCallBack motsCallBack = new MainActivity$motsCallBack$1(this);
    private final ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.neusoft.na.mots.MainActivity$serviceConnection$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) throws RemoteException {
            IMotsServiceInterface iMotsServiceInterface;
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(service, "service");
            Log.i(MainActivity.TAG, "onServiceConnected");
            this.this$0.binder = IMotsServiceInterface.Stub.asInterface(service);
            IMotsServiceInterface iMotsServiceInterface2 = this.this$0.binder;
            if (iMotsServiceInterface2 != null) {
                iMotsServiceInterface2.setCallBack(this.this$0.motsCallBack);
            }
            IMotsServiceInterface iMotsServiceInterface3 = this.this$0.binder;
            if (!(iMotsServiceInterface3 != null && iMotsServiceInterface3.getMotsStatus() == 3)) {
                IMotsServiceInterface iMotsServiceInterface4 = this.this$0.binder;
                if (iMotsServiceInterface4 != null && iMotsServiceInterface4.getMotsStatus() == 2) {
                    this.this$0.webViewShowHmi();
                }
            } else {
                this.this$0.xWalkViewShowHmi();
            }
            IMotsServiceInterface iMotsServiceInterface5 = this.this$0.binder;
            if (iMotsServiceInterface5 != null) {
                iMotsServiceInterface5.setInMultiWindowMode(this.this$0.isInMultiWindowMode());
            }
            if (this.this$0.isDisplay) {
                IMotsServiceInterface iMotsServiceInterface6 = this.this$0.binder;
                if (iMotsServiceInterface6 != null) {
                    iMotsServiceInterface6.onMainResume();
                }
            } else {
                IMotsServiceInterface iMotsServiceInterface7 = this.this$0.binder;
                if (iMotsServiceInterface7 != null) {
                    iMotsServiceInterface7.onMainPause();
                }
            }
            if (CheckBoardKt.isBydSeries() && this.this$0.isBydHasAll() && (iMotsServiceInterface = this.this$0.binder) != null) {
                iMotsServiceInterface.haspermissions();
            }
            if ((CheckBoardKt.isByd8155f() || CheckBoardKt.isZeekrBx1e()) && this.this$0.getSurfaceMainMap() != null) {
                MainActivity mainActivity = this.this$0;
                IMotsServiceInterface iMotsServiceInterface8 = mainActivity.binder;
                if (iMotsServiceInterface8 != null) {
                    iMotsServiceInterface8.setSurface(mainActivity.getSurfaceMainMap());
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.this$0.binder = null;
            Log.i(MainActivity.TAG, "onServiceDisconnected");
        }
    };
    private int hmiSettingsDayNightMode = -1;
    private final BroadcastReceiver noNeedServiceBroadcastReceiver = new BroadcastReceiver() { // from class: com.neusoft.na.mots.MainActivity$noNeedServiceBroadcastReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!Intrinsics.areEqual(intent != null ? intent.getAction() : null, "com.neusoft.na.navigation.backToHome")) {
                if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "com.neusoft.na.navigation.action.hmi.settings.dayNightMode.changed")) {
                    this.this$0.hmiSettingsDayNightMode = intent.getIntExtra("hmiSettingsDayNightMode", -1);
                    Log.i(MainActivity.TAG, "hmiSettingsDayNightMode == " + this.this$0.hmiSettingsDayNightMode + ", isDisplay == " + this.this$0.isDisplay);
                    if (this.this$0.isDisplay) {
                        this.this$0.notifySystem4HmiSettingsDayNightMode();
                        return;
                    }
                    return;
                }
                return;
            }
            if (CheckBoardKt.isByd8155f()) {
                Application application = this.this$0.getApplication();
                Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
                ((MotsApplication) application).showToast();
            }
            this.this$0.backToHome();
            if (this.this$0.isByd8155Kill) {
                this.this$0.finish();
            }
        }
    };
    private int mBydType = -1;
    private Timer maskGoneTimer = new Timer(true);
    private final Point mapDisplayAreaPoint = new Point(-1, -1);
    private boolean isFirstStart = true;
    private Timer hmiMaskGoneTimer = new Timer(true);
    private final long hmiMaskGoneDelay = 100;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void showMessage(String text) {
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0016B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/neusoft/na/mots/MainActivity$Companion;", "Lmu/KLogging;", "()V", "ACTION_BACK_TO_HOME", "", "ACTION_HMI_SETTINGS_DAY_NIGHT_MODE_CHANGED", "ACTION_PERMISSIONS_CALL_PHONE", "", "ACTION_PERMISSIONS_CHANGED", "APP_BACKGROUND", "APP_FINISH", "APP_FOREGROUND", "AR_COMPONENT", "MINI_BAR_TOOL", "START_ACTIVITY", "TAG", "TAG_BYD_8155", "TAG_V61_HINT", "WWW_URL", "WWW_URL_C4PRO", "WWW_URL_C4PRO_SAFE", "WWW_URL_SAFE", "keyBoardValue", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {

        /* JADX INFO: compiled from: MainActivity.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/neusoft/na/mots/MainActivity$Companion$keyBoardValue;", "", "(Ljava/lang/String;I)V", YNUEnum.NO, "SHOW", "HIDE", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public enum keyBoardValue {
            NO,
            SHOW,
            HIDE
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void maskGone() {
        Log.i(TAG, "isMask == " + this.isMask + " , lockActivity == " + this.lockActivity);
        if (!this.isMask && !this.lockActivity) {
            if (CheckBoardKt.isByd8155f()) {
                openMap(getIntent());
            }
            this.mHandler.post(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() throws RemoteException {
                    MainActivity.maskGone$lambda$1(this.f$0);
                }
            });
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws ClassNotFoundException {
                MainActivity.maskGone$lambda$2(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void maskGone$lambda$1(MainActivity this$0) throws RemoteException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(TAG, "[NeuMapLCM]mask.visibility == GONE");
        if (CheckBoardKt.isByd8155f()) {
            Log.i(TAG, "set mask 1 px");
            ViewGroup.LayoutParams layoutParams = ((ImageView) this$0._$_findCachedViewById(R.id.mask)).getLayoutParams();
            layoutParams.width = 1;
            layoutParams.height = 1;
            ((ImageView) this$0._$_findCachedViewById(R.id.mask)).setLayoutParams(layoutParams);
        }
        IMotsServiceInterface iMotsServiceInterface = this$0.binder;
        if (iMotsServiceInterface != null) {
            iMotsServiceInterface.maskGone();
        }
        Log.i(TAG, "set mask.visibility = GONE");
        ((ImageView) this$0._$_findCachedViewById(R.id.mask)).setVisibility(8);
        if (CheckBoardKt.isGreatwallV61()) {
            ((FrameLayout) this$0._$_findCachedViewById(R.id.frame_minibar)).setVisibility(0);
            RelativeLayout relativeLayout = (RelativeLayout) this$0._$_findCachedViewById(R.id.appRoot);
            TextView textView = relativeLayout != null ? (TextView) relativeLayout.findViewWithTag(TAG_V61_HINT) : null;
            if (textView != null) {
                ((RelativeLayout) this$0._$_findCachedViewById(R.id.appRoot)).removeView(textView);
                return;
            }
            return;
        }
        if (CheckBoardKt.isSmart() || CheckBoardKt.isZeekr()) {
            this$0.setDayNightMode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void maskGone$lambda$2(MainActivity this$0) throws ClassNotFoundException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (CheckBoardKt.isGreatwallV61()) {
            this$0.showV61LaunchHint();
        }
        ((ImageView) this$0._$_findCachedViewById(R.id.mask)).setVisibility(0);
        Log.i(TAG, "mask.visibility == VISIBLE");
    }

    private final void transparentStatusBarColor() {
        if (CheckBoardKt.isGreatwallV61() || CheckBoardKt.isWeyforproductv3()) {
            try {
                Field declaredField = Class.forName("com.android.internal.policy.DecorView").getDeclaredField("mSemiTransparentStatusBarColor");
                declaredField.setAccessible(true);
                declaredField.setInt(getWindow().getDecorView(), 0);
            } catch (Exception unused) {
                INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.mots.MainActivity.transparentStatusBarColor.1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "failed to load class : com.android.internal.policy.DecorView";
                    }
                });
            }
        }
    }

    private final synchronized void showHmi() {
        Log.i(TAG, "showHmi");
        if (!this.mHmiVisible) {
            ((FrameLayout) _$_findCachedViewById(R.id.hmiWebViewContainer)).setVisibility(0);
            if (this.mXWalkView != null) {
                Boolean XWALK_USE_TEXTUREVIEW = BuildConfig.XWALK_USE_TEXTUREVIEW;
                Intrinsics.checkNotNullExpressionValue(XWALK_USE_TEXTUREVIEW, "XWALK_USE_TEXTUREVIEW");
                if (XWALK_USE_TEXTUREVIEW.booleanValue()) {
                }
            }
            new Handler(getMainLooper()).post(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.showHmi$lambda$5(this.f$0);
                }
            });
            this.mHmiVisible = true;
            timeZoneChanged();
        }
    }

    private static final void showHmi$lambda$3(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(TAG, "mapDisplayArea.visibility = View.VISIBLE");
        ((TextureView) this$0._$_findCachedViewById(R.id.mapDisplayArea)).setVisibility(0);
    }

    private static final void showHmi$lambda$4(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(TAG, " welcome.background = null");
        ((ImageView) this$0._$_findCachedViewById(R.id.welcome)).setBackground(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showHmi$lambda$5(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ImageView) this$0._$_findCachedViewById(R.id.welcome)).setBackground(null);
        ((FrameLayout) this$0._$_findCachedViewById(R.id.hmiWebViewContainer)).setAlpha(1.0f);
        ((TextureView) this$0._$_findCachedViewById(R.id.mapDisplayArea)).setVisibility(0);
    }

    private final void setDayNightMode() {
        int i = (getResources().getConfiguration().uiMode & 48) == 32 ? 1 : 0;
        Log.i(TAG, "setDayNightMode mode: " + i);
        if ((CheckBoardKt.isSmart() || CheckBoardKt.isZeekr()) && !this.isMask && !this.lockActivity) {
            setNavbarColor(i == 1);
        }
        if (CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            setNavbarColor4Byd(i == 1);
        }
        if (!CheckBoardKt.isGreatwallV61() || _$_findCachedViewById(R.id.actionBarTopView) == null) {
            return;
        }
        _$_findCachedViewById(R.id.actionBarTopView).setBackgroundResource(i == 0 ? com.neusoft.na.navigation.R.drawable.action_bar_background_day : com.neusoft.na.navigation.R.drawable.action_bar_background_night);
    }

    private final void registerMotsBroadcastReceiver() {
        MotsBroadcastReceiver companion = MotsBroadcastReceiver.INSTANCE.getInstance();
        this.mMotsBroadcastReceiver = companion;
        if (companion != null) {
            companion.setMotsBroadcastCallBack(this);
        }
    }

    private final void registerNoNeedServiceReceiver() {
        Log.i(TAG, "[registerNoNeedServiceReceiver]");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_BACK_TO_HOME);
        intentFilter.addAction(ACTION_HMI_SETTINGS_DAY_NIGHT_MODE_CHANGED);
        registerReceiver(this.noNeedServiceBroadcastReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void backToHome() {
        Log.i(TAG, "[backToHome]");
        new Thread(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.backToHome$lambda$6();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void backToHome$lambda$6() {
        Log.i(TAG, "instrumentation.sendKeyDownUpSync");
        new Instrumentation().sendKeyDownUpSync(3);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        printWindowInfo();
        Log.i(TAG, "onConfigurationChanged");
        StringBuilder sb = new StringBuilder("webview hight = ");
        WebView webView = this.mWebView;
        StringBuilder sbAppend = sb.append(webView != null ? Integer.valueOf(webView.getWidth()) : null).append(", ");
        WebView webView2 = this.mWebView;
        Log.i(TAG, sbAppend.append(webView2 != null ? Integer.valueOf(webView2.getHeight()) : null).toString());
        if (CheckBoardKt.isByd8155f()) {
            setMapDisplayAreaGlobalLayout();
            setByd8155fActionBarBackground(isNightModeByByd());
        }
        if (CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            if (CheckBoardKt.isByd()) {
                switchBydStartImage();
            } else if (CheckBoardKt.isByd8155f()) {
                setStartImageByByd8155F();
            }
            BasicPrivacyView basicPrivacyView = this.mPrivacyPolicyView;
            if (basicPrivacyView != null) {
                basicPrivacyView.setInMultiWindowMode(isInMultiWindowMode());
            }
            Intent intent = new Intent(Byd8155fSystem.ACTION_WIDGET_SCREEN_CHANGED);
            intent.setComponent(new ComponentName(this, "com.neusoft.na.byd.BydAppWidget"));
            sendBroadcast(intent);
            screenDirection2Bl(newConfig);
        }
        int iSendSwitchDayNightModeBroadcast = (CheckBoardKt.isGreatwallV61() || CheckBoardKt.isWeyforproductv3() || CheckBoardKt.isSmart() || CheckBoardKt.isSeres() || CheckBoardKt.isZeekr()) ? sendSwitchDayNightModeBroadcast() : -1;
        if (CheckBoardKt.isZeekr()) {
            switchZeekrStartImage();
        }
        if (CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            int i = newConfig.uiMode & 48;
            Log.i(TAG, "onConfigurationChanged systemUiMode == " + i);
            setNavbarColor4Byd(i == 32);
            sendSwitchDayNightModeBroadcastForByd(i == 32 ? 2 : 1);
        }
        if (CheckBoardKt.isGreatwallV61()) {
            Log.i(TAG, "nightMode == " + iSendSwitchDayNightModeBroadcast);
            if (_$_findCachedViewById(R.id.actionBarTopView) != null) {
                _$_findCachedViewById(R.id.actionBarTopView).setBackgroundResource(iSendSwitchDayNightModeBroadcast == 2 ? com.neusoft.na.navigation.R.drawable.action_bar_background_night : com.neusoft.na.navigation.R.drawable.action_bar_background_day);
            }
        }
        if (CheckBoardKt.isSmart()) {
            setSmartActionBarBackground();
            IWebViewDialogComponent iWebViewDialogComponent = this.webViewDialogComponent;
            if (iWebViewDialogComponent != null) {
                iWebViewDialogComponent.setDayNightMode();
            }
        }
        setDayNightMode();
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) throws RemoteException {
        IWebViewDialogComponent iWebViewDialogComponent;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        printWindowInfo();
        Log.i(TAG, "onMultiWindowModeChanged isInMultiWindowMode == " + isInMultiWindowMode);
        StringBuilder sb = new StringBuilder("webview hight = ");
        WebView webView = this.mWebView;
        StringBuilder sbAppend = sb.append(webView != null ? Integer.valueOf(webView.getWidth()) : null).append(", ");
        WebView webView2 = this.mWebView;
        Log.i(TAG, sbAppend.append(webView2 != null ? Integer.valueOf(webView2.getHeight()) : null).toString());
        if (CheckBoardKt.isByd8155f()) {
            setMapMaskColor(isInMultiWindowMode, newConfig);
        }
        if (CheckBoardKt.isSmart() || CheckBoardKt.isSeres() || CheckBoardKt.isZeekr() || CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            IMotsServiceInterface iMotsServiceInterface = this.binder;
            if (iMotsServiceInterface != null) {
                Log.i(TAG, "setInMultiWindowMode " + isInMultiWindowMode());
                iMotsServiceInterface.setInMultiWindowMode(isInMultiWindowMode());
            }
            sendMultiWindowModeBroadcast(isInMultiWindowMode);
        }
        if (CheckBoardKt.isSmart() && (iWebViewDialogComponent = this.webViewDialogComponent) != null) {
            iWebViewDialogComponent.multiWindowModeChanged(isInMultiWindowMode);
        }
        if (CheckBoardKt.isByd8155f()) {
            setSystemUiByByd8155f(true);
        }
    }

    private final void setMapMaskColor(boolean isInMultiWindowMode, Configuration newConfig) {
        Log.i(TAG, "[setMapMaskColor] --> isInMultiWindowMode == " + isInMultiWindowMode + " , naviHMIFullScreenPageFlag == " + this.naviHMIFullScreenPageFlag);
        if (CheckBoardKt.isByd8155f() && this.naviHMIFullScreenPageFlag && !isInMultiWindowMode) {
            Log.i(TAG, "mapMaskView color --> add color");
            int i = newConfig.uiMode & 48;
            Log.i(TAG, "[onMultiWindowModeChanged] systemUiMode == " + i);
            _$_findCachedViewById(R.id.mapMaskView).setBackgroundColor(getResources().getColor(i == 32 ? com.neusoft.na.navigation.R.color.color_map_mask_background_night_byd : com.neusoft.na.navigation.R.color.color_map_mask_background_day_byd));
            Log.i(TAG, "mapMaskView color --> mapMaskColorResources");
            if (this.mapMaskTimer != null) {
                Log.i(TAG, "mapMaskTimer.cancel()");
                Timer timer = this.mapMaskTimer;
                if (timer != null) {
                    timer.cancel();
                }
            }
            this.mapMaskTimer = new Timer(true);
            C00591 c00591 = new C00591();
            this.mapMaskTimerTask = c00591;
            Timer timer2 = this.mapMaskTimer;
            if (timer2 != null) {
                timer2.schedule(c00591, 1000L);
            }
        }
    }

    /* JADX INFO: renamed from: com.neusoft.na.mots.MainActivity$setMapMaskColor$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/neusoft/na/mots/MainActivity$setMapMaskColor$1", "Ljava/util/TimerTask;", "run", "", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class C00591 extends TimerTask {
        C00591() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            final MainActivity mainActivity = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$setMapMaskColor$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.C00591.run$lambda$0(mainActivity);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void run$lambda$0(MainActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Log.i(MainActivity.TAG, "mapMaskView color --> transparent");
            this$0._$_findCachedViewById(R.id.mapMaskView).setBackgroundColor(this$0.getResources().getColor(android.R.color.transparent));
            this$0.mapMaskTimer = null;
        }
    }

    private final int sendSwitchDayNightModeBroadcast() {
        Object systemService = getSystemService("uimode");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.UiModeManager");
        UiModeManager uiModeManager = (UiModeManager) systemService;
        Log.i(TAG, "uiModeManager.nightMode == " + uiModeManager.getNightMode() + " (onConfigurationChanged --> system)");
        Intent intent = new Intent("com.neusoft.na.navigation.daynight.switch");
        intent.putExtra(ServiceBase.PAYLOAD_MODE, uiModeManager.getNightMode());
        sendBroadcast(intent);
        return uiModeManager.getNightMode();
    }

    private final void sendSwitchDayNightModeBroadcastForByd(int value) {
        Log.i(TAG, "uiModeManager.nightMode == " + value + " (onConfigurationChanged --> system)");
        Intent intent = new Intent("com.neusoft.na.navigation.daynight.switch");
        intent.putExtra(ServiceBase.PAYLOAD_MODE, value);
        sendBroadcast(intent);
    }

    private final void sendMultiWindowModeBroadcast(boolean isInMultiWindowMode) {
        Log.i(TAG, " (sendMultiWindowModeBroadcast --> system)");
        Intent intent = new Intent("com.neusoft.na.navigation.multiWindowMode");
        intent.putExtra(OneCoreService.PAYLOAD_IN_MULTI_WINDOW_MODE, isInMultiWindowMode);
        sendBroadcast(intent);
    }

    private final List<String> checkPermissions() {
        Log.i(TAG, "checkPermissions");
        ArrayList arrayList = new ArrayList();
        if (checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
            arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        }
        if (Build.VERSION.SDK_INT < 30) {
            if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
            }
            if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }
        }
        if (checkSelfPermission("android.permission.CALL_PHONE") != 0) {
            arrayList.add("android.permission.CALL_PHONE");
        }
        if (CheckBoardKt.isBydSeries()) {
            if (checkSelfPermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON) != 0) {
                arrayList.add(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
            }
            if (checkSelfPermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) != 0) {
                arrayList.add(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
            }
        }
        if (CheckBoardKt.isByd8155f()) {
            if (checkSelfPermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON) != 0) {
                arrayList.add(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
            }
            if (checkSelfPermission(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON) != 0) {
                arrayList.add(BYDAutoYunDevice.PERMISSION_BYDAUTO_YUN_COMMON);
            }
            if (checkSelfPermission(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON) != 0) {
                arrayList.add(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
            }
            if (checkSelfPermission(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_COMMON) != 0) {
                arrayList.add(BYDAutoSpeedDevice.PERMISSION_BYDAUTO_SPEED_COMMON);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Log.i(TAG, "missing Permissions: " + ((String) it.next()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkStartMots() throws RemoteException {
        Log.d(TAG, "checkStartMots");
        if (Build.VERSION.SDK_INT >= 30 && !Environment.isExternalStorageManager()) {
            Intent intent = new Intent();
            intent.setAction("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            return;
        }
        ((ConstraintLayout) _$_findCachedViewById(R.id.permissionsError)).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws RemoteException {
                MainActivity.checkStartMots$lambda$9(this.f$0, view);
            }
        });
        List<String> listCheckPermissions = checkPermissions();
        if (!listCheckPermissions.isEmpty()) {
            this.isShowSystemPermissionDialog = true;
            Object[] array = listCheckPermissions.toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            ActivityCompat.requestPermissions(this, (String[]) array, 143);
            return;
        }
        IMotsServiceInterface iMotsServiceInterface = this.binder;
        if (iMotsServiceInterface != null) {
            iMotsServiceInterface.haspermissions();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkStartMots$lambda$9(MainActivity this$0, View view) throws RemoteException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<String> listCheckPermissions = this$0.checkPermissions();
        if (!(!listCheckPermissions.isEmpty())) {
            IMotsServiceInterface iMotsServiceInterface = this$0.binder;
            if (iMotsServiceInterface != null) {
                iMotsServiceInterface.haspermissions();
                return;
            }
            return;
        }
        Object[] array = listCheckPermissions.toArray(new String[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        ActivityCompat.requestPermissions(this$0, (String[]) array, 143);
    }

    private final void init() {
        this.BL = "com.neusoft.na.navigation" + getString(com.neusoft.na.navigation.R.string.service_process_motsbl);
        this.OC = "com.neusoft.na.navigation" + getString(com.neusoft.na.navigation.R.string.service_process_onecore);
        this.CL = "com.neusoft.na.navigation" + getString(com.neusoft.na.navigation.R.string.service_process_telematics);
        this.GS = "com.neusoft.na.navigation" + getString(com.neusoft.na.navigation.R.string.service_process_guard);
        this.EHP = "com.neusoft.na.navigation" + getString(com.neusoft.na.navigation.R.string.service_process_ehp);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        openMap(intent);
        if (CheckBoardKt.isBydSeries()) {
            this.mBydType = intent != null ? intent.getIntExtra("type", -4) : -5;
            Log.i(TAG, "onNewIntent: " + this.mBydType);
            String stringExtra = intent != null ? intent.getStringExtra(NotificationCompat.CATEGORY_CALL) : null;
            this.mPhoneNumber = stringExtra;
            if (stringExtra != null) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.CALL_PHONE"}, 1431);
            }
        }
    }

    private final void sendBydBroadcast() {
        if (this.isMask) {
            return;
        }
        Log.i(TAG, "sendBroadcast: " + this.mBydType);
        if (CheckBoardKt.isByd()) {
            int i = this.mBydType;
            String str = i != 1 ? i != 2 ? "" : "com.neusoft.na.navigation.action.widget.HOME" : "com.neusoft.na.navigation.action.widget.COMPANY";
            if (TextUtils.isEmpty(str)) {
                return;
            } else {
                sendBroadcast(new Intent(str));
            }
        } else if (CheckBoardKt.isByd8155f()) {
            Intent intent = new Intent(Byd8155fSystem.ACTION_WIDGET_TYPE);
            intent.putExtra(Byd8155fSystem.WIDGET_TYPE_KEY, this.mBydType);
            sendBroadcast(intent);
        }
        this.mBydType = -1;
    }

    private final void openMap(Intent intent) {
        Uri data;
        Uri data2;
        String string;
        if (CheckBoardKt.isSmart() || CheckBoardKt.isZeekr() || CheckBoardKt.isByd8155f()) {
            Log.i(TAG, "openMap: " + ((intent == null || (data2 = intent.getData()) == null || (string = data2.toString()) == null) ? null : Integer.valueOf(string.length())));
            if (intent == null || (data = intent.getData()) == null) {
                return;
            }
            String schemeSpecificPart = data.getSchemeSpecificPart();
            String query = data.getQuery();
            Intent intent2 = new Intent("com.neusoft.na.navigation.action.openmap");
            intent2.putExtra("latLon", schemeSpecificPart);
            intent2.putExtra(SearchIntents.EXTRA_QUERY, query);
            intent2.putExtra("uriString", data.toString());
            sendBroadcast(intent2);
            Log.i(TAG, "openMap done");
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws ClassNotFoundException, RemoteException {
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
        ((MotsApplication) application).addCreateCount();
        int i = 12544;
        if (CheckBoardKt.isGreatwallV61()) {
            getWindow().getDecorView().setSystemUiVisibility(12544);
            getWindow().addFlags(2048);
            getWindow().clearFlags(1024);
            getWindow().clearFlags(67108864);
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        }
        if (!CheckBoardKt.isBydSeries()) {
            setRequestedOrientation(0);
        }
        if (CheckBoardKt.isSmart() || CheckBoardKt.isSeres() || CheckBoardKt.isZeekr() || CheckBoardKt.isBydSeries()) {
            sendMultiWindowModeBroadcast(isInMultiWindowMode());
        }
        super.onCreate(savedInstanceState);
        MainActivity mainActivity = this;
        DbCheckUtil.INSTANCE.check(mainActivity);
        openMap(getIntent());
        Log.i(TAG, "[NeuMapLCM]onCreate");
        init();
        if (CheckBoardKt.isGreatwallB01() || CheckBoardKt.isGreatwallV61() || CheckBoardKt.isWeyforproductv3() || CheckBoardKt.isSmart() || CheckBoardKt.isSeres() || CheckBoardKt.isZeekr() || CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            this.lockActivity = true;
            this.mHandler.postDelayed(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.onCreate$lambda$12(this.f$0);
                }
            }, 2000L);
        }
        transparentStatusBarColor();
        registerMotsBroadcastReceiver();
        registerNoNeedServiceReceiver();
        if (!CheckBoardKt.isBydSeries() || isBydHasAll()) {
            startMotsMainService();
        }
        if (CheckBoardKt.isBydSeries()) {
            Intent intent = new Intent();
            intent.setClassName(getPackageName(), "com.neusoft.na.byd.sdk.NaviService");
            startForegroundService(intent);
        }
        if (!CheckBoardKt.isBydSeries()) {
            new KeyboardUtil(mainActivity).setOnKeyboardChangeListener(new KeyboardUtil.KeyboardChangeListener() { // from class: com.neusoft.na.mots.MainActivity.onCreate.2
                @Override // com.neusoft.na.common.util.KeyboardUtil.KeyboardChangeListener
                public void onKeyboardShow(int keyboardHight) throws RemoteException {
                    Log.i(MainActivity.TAG, "Keyboard has been shown.");
                    if (MainActivity.this.keyboardStatus != Companion.keyBoardValue.SHOW) {
                        Log.i(MainActivity.TAG, "send Keyboard has been shown.");
                        MainActivity.this.keyboardStatus = Companion.keyBoardValue.SHOW;
                        IMotsServiceInterface iMotsServiceInterface = MainActivity.this.binder;
                        if (iMotsServiceInterface != null) {
                            iMotsServiceInterface.onKeyCode(3);
                        }
                    }
                }

                @Override // com.neusoft.na.common.util.KeyboardUtil.KeyboardChangeListener
                public void onKeyboardHide() throws RemoteException {
                    Log.i(MainActivity.TAG, "Keyboard has been hidden.");
                    if (MainActivity.this.keyboardStatus != Companion.keyBoardValue.HIDE) {
                        Log.i(MainActivity.TAG, "send Keyboard has been hidden.");
                        MainActivity.this.keyboardStatus = Companion.keyBoardValue.HIDE;
                        IMotsServiceInterface iMotsServiceInterface = MainActivity.this.binder;
                        if (iMotsServiceInterface != null) {
                            iMotsServiceInterface.onKeyCode(2);
                        }
                    }
                }
            });
        }
        if (CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            if (!CheckBoardKt.isByd() && !isInMultiWindowMode()) {
                i = 13568;
            }
            getWindow().getDecorView().setSystemUiVisibility(i);
            getWindow().clearFlags(67108864);
            getWindow().addFlags(Integer.MIN_VALUE);
            setNavbarColor4Byd(isNightModeByByd());
            Intent intent2 = getIntent();
            this.mBydType = intent2 != null ? intent2.getIntExtra("type", -2) : -3;
            Log.i(TAG, "onCreate: " + this.mBydType);
        }
        if (CheckBoardKt.isSeres()) {
            getWindow().getDecorView().setSystemUiVisibility(13056);
            getWindow().addFlags(2048);
            getWindow().clearFlags(67108864);
            getWindow().clearFlags(134217728);
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setStatusBarColor(getColor(android.R.color.transparent));
            getWindow().setNavigationBarColor(getColor(android.R.color.transparent));
        }
        setContentView(com.neusoft.na.navigation.R.layout.activity_main);
        if (CheckBoardKt.isByd8155f()) {
            setStartImageByByd8155F();
        }
        setMapDisplayAreaGlobalLayout();
        setDayNightMode();
        if (CheckBoardKt.isByd8155f()) {
            Intent intent3 = new Intent("com.byd.card.open.FloatCard");
            intent3.addFlags(16777216);
            intent3.putExtra(Car.PACKAGE_SERVICE, getPackageName());
            sendBroadcast(intent3);
        }
        ((ImageView) _$_findCachedViewById(R.id.mask)).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Log.i(MainActivity.TAG, "mask touch");
            }
        });
        if (CheckBoardKt.isGreatwallV61()) {
            initGreatWallV61();
        }
        if (CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            initByd();
        }
        if (CheckBoardKt.isByd8155f()) {
            if (isInMultiWindowMode()) {
                _$_findCachedViewById(R.id.actionBarTopView).setVisibility(8);
            } else {
                setByd8155fActionBarBackground(isNightModeByByd());
            }
        }
        if (CheckBoardKt.isWeyforproductv3()) {
            initWeyforproductv3();
        }
        if (CheckBoardKt.isSmart() || CheckBoardKt.isSeres()) {
            initSmart();
        }
        if (CheckBoardKt.isZeekr()) {
            initZeekr();
            switchZeekrStartImage();
        }
        if ((CheckBoardKt.isSmart() && hideTestImage()) || CheckBoardKt.isBydSeries()) {
            ((ImageView) _$_findCachedViewById(R.id.welcome)).setImageDrawable(null);
            ((ImageView) _$_findCachedViewById(R.id.welcome)).setVisibility(8);
        }
        if (CheckBoardKt.isGreatwallV61() || CheckBoardKt.isGreatwallB01()) {
            String systemPropertiesValue = new SystemUtils().getSystemPropertiesValue(SystemUtils.TYPE_CAR_MODE);
            Log.i(MotsService.TAG, "carModel == " + systemPropertiesValue);
            String systemPropertiesValue2 = new SystemUtils().getSystemPropertiesValue(SystemUtils.TYPE_COUNTRY_CODE);
            Log.i(MotsService.TAG, "countryCode == " + systemPropertiesValue2);
            if ((Intrinsics.areEqual(systemPropertiesValue, "136") || Intrinsics.areEqual(systemPropertiesValue, "115")) && Intrinsics.areEqual(systemPropertiesValue2, "17")) {
                ((ImageView) _$_findCachedViewById(R.id.welcome)).setImageDrawable(null);
                ((ImageView) _$_findCachedViewById(R.id.welcome)).setVisibility(8);
            }
        }
        ((TextureView) _$_findCachedViewById(R.id.mapDisplayArea)).setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.neusoft.na.mots.MainActivity.onCreate.4
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) throws RemoteException {
                Intrinsics.checkNotNullParameter(surface, "surface");
                Log.i(MainActivity.TAG, "onSurfaceTextureSizeChanged --> width == " + width + ", height == " + height);
                IMotsServiceInterface iMotsServiceInterface = MainActivity.this.binder;
                if (iMotsServiceInterface != null) {
                    iMotsServiceInterface.notifySurfaceSizeChanged();
                }
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) throws RemoteException {
                Intrinsics.checkNotNullParameter(surface, "surface");
                Log.d(MainActivity.TAG, "surfaceDestroyed");
                MainActivity.INSTANCE.getLogger().debug("SurfaceView: onSurfaceTextureDestroyed");
                Application application2 = MainActivity.this.getApplication();
                Intrinsics.checkNotNull(application2, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
                if (((MotsApplication) application2).cannotDestroyProcess()) {
                    return true;
                }
                Log.i(MainActivity.TAG, "onSurfaceTextureDestroyed");
                IMotsServiceInterface iMotsServiceInterface = MainActivity.this.binder;
                if (iMotsServiceInterface != null) {
                    iMotsServiceInterface.setSurface(null);
                }
                return true;
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) throws RemoteException {
                Intrinsics.checkNotNullParameter(surface, "surface");
                Log.i(MainActivity.TAG, "[NeuMapLCM]surfaceCreated");
                MainActivity.INSTANCE.getLogger().debug("SurfaceView: surfaceCreated");
                MainActivity.this.setSurfaceMainMap(new Surface(surface));
                IMotsServiceInterface iMotsServiceInterface = MainActivity.this.binder;
                if (iMotsServiceInterface != null) {
                    iMotsServiceInterface.setSurface(MainActivity.this.getSurfaceMainMap());
                }
            }
        });
        ((TextureView) _$_findCachedViewById(R.id.arDisplayArea)).setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.neusoft.na.mots.MainActivity.onCreate.5
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                Log.d(MainActivity.TAG, "AR surfaceAvailable");
                MainActivity.INSTANCE.getLogger().debug("AR SurfaceView: onSurfaceTextureAvailable");
                IArComponent iArComponent = MainActivity.this.arComponentWapper;
                if (iArComponent != null) {
                    iArComponent.setSurface(new Surface(surface));
                }
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Intrinsics.checkNotNullParameter(surface, "surface");
                Log.i(MainActivity.TAG, "AR surfaceDestroyed");
                MainActivity.INSTANCE.getLogger().debug("AR SurfaceView: onSurfaceTextureDestroyed");
                IArComponent iArComponent = MainActivity.this.arComponentWapper;
                if (iArComponent == null) {
                    return true;
                }
                iArComponent.setSurface(null);
                return true;
            }
        });
        if (!CheckBoardKt.isBydSeries() || isBydHasAll()) {
            checkStartMots();
            if (CheckBoardKt.isGreatwallV61() || CheckBoardKt.isWeyforproductv3() || CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
                sendNaviActivityStartBC();
            }
            startMaskRemoveTask();
        }
        if (CheckBoardKt.isBydSeries()) {
            MainActivity mainActivity2 = this;
            if (!FileUtils.getInstance(mainActivity2).isAgreePrivacy()) {
                Log.i(TAG, "onCreate: has privacy");
                if (CheckBoardKt.isByd8155f()) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda5
                        @Override // java.lang.Runnable
                        public final void run() {
                            MainActivity.onCreate$lambda$14(this.f$0);
                        }
                    }, 1500L);
                } else {
                    showPrivacyPolicy();
                }
            } else if (!FileUtils.getInstance(mainActivity2).isHasPermissionByd()) {
                checkStartMots();
            }
        }
        if (CheckBoardKt.isByd8155f()) {
            showMapPresentation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$12(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.lockActivity = false;
        this$0.maskGone();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$14(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showPrivacyPolicy();
    }

    private final void startMaskRemoveTask() {
        if (CheckBoardKt.isByd8155f()) {
            Log.i(TAG, "[startMaskRemoveTask] maskGoneTimerTask == " + this.maskGoneTimerTask);
            if (this.maskGoneTimerTask == null) {
                C00631 c00631 = new C00631();
                this.maskGoneTimerTask = c00631;
                this.maskGoneTimer.schedule(c00631, 20000L);
                Log.i(TAG, "[startMaskRemoveTask] --> start timer");
                return;
            }
            Log.i(TAG, "[startMaskRemoveTask] --> startMaskRemoveTask timer maskGoneTaskRunning...");
        }
    }

    /* JADX INFO: renamed from: com.neusoft.na.mots.MainActivity$startMaskRemoveTask$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/neusoft/na/mots/MainActivity$startMaskRemoveTask$1", "Ljava/util/TimerTask;", "run", "", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class C00631 extends TimerTask {
        C00631() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (((ImageView) MainActivity.this._$_findCachedViewById(R.id.mask)).getVisibility() != 8) {
                final MainActivity mainActivity = MainActivity.this;
                mainActivity.runOnUiThread(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$startMaskRemoveTask$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        MainActivity.C00631.run$lambda$0(mainActivity);
                    }
                });
            } else {
                Log.i(MainActivity.TAG, "[startMaskRemoveTask] --> mask visibility is gone, todo nothing.");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void run$lambda$0(MainActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Log.i(MainActivity.TAG, "[startMaskRemoveTask] --> remove mask, because 20s time over.");
            this$0.isMask = false;
            this$0.lockActivity = false;
            this$0.maskGone();
        }
    }

    public final Surface getSurfaceMainMap() {
        return this.surfaceMainMap;
    }

    public final void setSurfaceMainMap(Surface surface) {
        this.surfaceMainMap = surface;
    }

    private final boolean hideTestImage() {
        return !Intrinsics.areEqual("6", com.neusoft.na.common.util.SystemUtils.INSTANCE.getSystemPropertiesValue(this, "persist.ecarx.config.cc220"));
    }

    private final void setMapDisplayAreaGlobalLayout() {
        Log.i(TAG, "[setMapDisplayAreaGlobalLayout] --> isInMultiWindowMode == " + isInMultiWindowMode());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        Log.i(TAG, "width == " + displayMetrics.widthPixels + ", height == " + displayMetrics.heightPixels);
        if (displayMetrics.widthPixels != this.mapDisplayAreaPoint.x || displayMetrics.heightPixels != this.mapDisplayAreaPoint.y) {
            Log.i(TAG, "update mapDisplayArea.layoutParams --> width == " + displayMetrics.widthPixels + ", height == " + displayMetrics.heightPixels);
            if (CheckBoardKt.isByd8155f() && !this.isDisplay) {
                Log.i(TAG, "screen is rotated, show hmiMaskView.");
                _$_findCachedViewById(R.id.hmiMaskView).setVisibility(0);
                _$_findCachedViewById(R.id.hmiMaskView).setBackgroundResource(isNightModeByByd() ? com.neusoft.na.navigation.R.color.color_hmi_mask_background_night_byd : com.neusoft.na.navigation.R.color.color_hmi_mask_background_day_byd);
            }
            this.mapDisplayAreaPoint.x = displayMetrics.widthPixels;
            this.mapDisplayAreaPoint.y = displayMetrics.heightPixels;
            ViewGroup.LayoutParams layoutParams = ((TextureView) _$_findCachedViewById(R.id.mapDisplayArea)).getLayoutParams();
            layoutParams.width = this.mapDisplayAreaPoint.x;
            layoutParams.height = this.mapDisplayAreaPoint.y;
            ((TextureView) _$_findCachedViewById(R.id.mapDisplayArea)).setLayoutParams(layoutParams);
        }
        ViewGroup.LayoutParams layoutParams2 = ((FrameLayout) _$_findCachedViewById(R.id.hmiWebViewContainer)).getLayoutParams();
        Log.i(TAG, "update hmiWebViewContainer.layoutParams");
        if (isInMultiWindowMode()) {
            Log.i(TAG, "set hmiWebViewContainer layoutParams width and height LayoutParams.MATCH_PARENT");
            layoutParams2.width = -1;
            layoutParams2.height = -1;
        } else {
            Log.i(TAG, "set hmiWebViewContainer layoutParams width == " + displayMetrics.widthPixels + ", height == " + displayMetrics.heightPixels);
            layoutParams2.width = displayMetrics.widthPixels;
            layoutParams2.height = displayMetrics.heightPixels;
        }
        ((FrameLayout) _$_findCachedViewById(R.id.hmiWebViewContainer)).getLayoutParams();
    }

    private final void setSystemUiByByd8155f(boolean switchInMultiWindowMode) {
        Log.i(TAG, "[setSystemUiByByd8155f] --> switchInMultiWindowMode == " + switchInMultiWindowMode);
        if (isInMultiWindowMode()) {
            getWindow().getDecorView().setSystemUiVisibility(12544);
            getWindow().clearFlags(67108864);
            getWindow().addFlags(Integer.MIN_VALUE);
            setNavbarColor4Byd(isNightModeByByd());
            _$_findCachedViewById(R.id.actionBarTopView).setVisibility(8);
            return;
        }
        this.mHandler.postDelayed(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.setSystemUiByByd8155f$lambda$15(this.f$0);
            }
        }, switchInMultiWindowMode ? 300L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setSystemUiByByd8155f$lambda$15(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkWindowDecorViewLocationOnScreen();
    }

    private final void checkWindowDecorViewLocationOnScreen() {
        Log.i(TAG, "[checkWindowDecorViewLocationOnScreen]");
        if (isInMultiWindowMode()) {
            Log.i(TAG, "is multi mode, cancel this pull systemui set.");
            return;
        }
        int[] iArr = new int[2];
        getWindow().getDecorView().getLocationOnScreen(iArr);
        Log.i(TAG, "window.decorView.getLocationOnScreen == " + iArr[0] + ", " + iArr[1]);
        if (iArr[0] == 0) {
            setFullSystemUiByByd8155f();
        } else {
            Log.i(TAG, "[checkWindowDecorViewLocationOnScreen] --> postDelayed");
            this.mHandler.postDelayed(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.checkWindowDecorViewLocationOnScreen$lambda$16(this.f$0);
                }
            }, 50L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkWindowDecorViewLocationOnScreen$lambda$16(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkWindowDecorViewLocationOnScreen();
    }

    private final void setFullSystemUiByByd8155f() {
        Log.i(TAG, "[setFullSystemUiByByd8155f]");
        if (isInMultiWindowMode()) {
            Log.i(TAG, "is multi mode, cancel this pull systemui set.");
            return;
        }
        Log.i(TAG, "is in full screen mode");
        getWindow().getDecorView().setSystemUiVisibility(13568);
        getWindow().clearFlags(67108864);
        getWindow().addFlags(Integer.MIN_VALUE);
        setNavbarColor4Byd(isNightModeByByd());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isBydHasAll() {
        return FileUtils.getInstance(this).isBydHasAll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startAppDetailActivity() {
        Intent intent = new Intent();
        if (CheckBoardKt.isByd8155f()) {
            intent.setAction("com.byd.carsettings.ui.activity.AppDetailActivity");
            Bundle bundle = new Bundle();
            bundle.putString("app_package", getPackageName());
            bundle.putString("jump_to", "jump_permission");
            intent.putExtras(bundle);
        } else {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts(Car.PACKAGE_SERVICE, getPackageName(), null));
            intent.addFlags(402653184);
        }
        startActivity(intent);
    }

    private final void showPermissionDialog(List<String> checkPermissions) {
        if (CheckBoardKt.isByd8155f()) {
            show8155Permission(checkPermissions, false);
        } else {
            showPermissionDialog(checkPermissions, false);
        }
    }

    private final void show8155Permission(List<String> checkPermissions, final boolean onlyCall) {
        Log.i(TAG, "show8155Permission");
        if (((BasicPrivacyView) ((RelativeLayout) _$_findCachedViewById(R.id.appRoot)).findViewWithTag(TAG_BYD_8155)) != null) {
            Log.i(TAG, "PermissionDialog is showing");
            return;
        }
        BasicPrivacyView basicPrivacyViewCreatePrivacy = BasicPrivacyView.createPrivacy(this, "com.neusoft.na.byd.view.PermissionDialog");
        if (basicPrivacyViewCreatePrivacy == null) {
            return;
        }
        basicPrivacyViewCreatePrivacy.setPermissionList(checkPermissions, onlyCall);
        basicPrivacyViewCreatePrivacy.setClickListener(new BasicPrivacyView.IPrivacyViewListener() { // from class: com.neusoft.na.mots.MainActivity.show8155Permission.1
            @Override // com.neusoft.na.common.BasicPrivacyView.IPrivacyViewListener
            public void onClickAgree() {
                MainActivity.this.startAppDetailActivity();
                MainActivity.this.removeBydView();
            }

            @Override // com.neusoft.na.common.BasicPrivacyView.IPrivacyViewListener
            public void onClickCancel() {
                if (onlyCall) {
                    return;
                }
                if (MainActivity.this.isByd8155Launcher()) {
                    MainActivity.this.isByd8155Kill = true;
                    MainActivity.this.sendBroadcast(new Intent("com.neusoft.na.navigation.switchToWallpaperDesktop"));
                } else {
                    MainActivity.this.finish();
                }
            }

            @Override // com.neusoft.na.common.BasicPrivacyView.IPrivacyViewListener
            public void onClickChange() throws RemoteException {
                Log.i(MainActivity.TAG, "onClickChange");
                if (onlyCall) {
                    return;
                }
                MainActivity.this.removeBydView();
                MainActivity.this.startMotsMainService();
                MainActivity.this.sendNaviActivityStartBC();
                IMotsServiceInterface iMotsServiceInterface = MainActivity.this.binder;
                if (iMotsServiceInterface != null) {
                    iMotsServiceInterface.haspermissions();
                }
            }
        });
        basicPrivacyViewCreatePrivacy.setTag(TAG_BYD_8155);
        RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.appRoot);
        if (relativeLayout != null) {
            relativeLayout.addView(basicPrivacyViewCreatePrivacy, new ViewGroup.LayoutParams(-1, -1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeBydView() {
        RelativeLayout relativeLayout;
        BasicPrivacyView basicPrivacyView = (BasicPrivacyView) ((RelativeLayout) _$_findCachedViewById(R.id.appRoot)).findViewWithTag(TAG_BYD_8155);
        if (basicPrivacyView == null || (relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.appRoot)) == null) {
            return;
        }
        relativeLayout.removeView(basicPrivacyView);
    }

    private final void showPermissionDialog(List<String> checkPermissions, final boolean onlyCall) {
        Log.i(TAG, "showPermissionDialog " + onlyCall);
        BasicDialog basicDialog = this.permissionDialog;
        if ((basicDialog == null || basicDialog.isHidden()) ? false : true) {
            BasicDialog basicDialog2 = this.permissionDialog;
            if (basicDialog2 != null && basicDialog2.isAdded()) {
                Log.i(TAG, "PermissionDialog is showing");
                return;
            }
        }
        BasicDialog basicDialogCreateDialog = BasicDialog.createDialog("com.neusoft.na.byd.view.PermissionDialog");
        if (basicDialogCreateDialog == null) {
            return;
        }
        this.permissionDialog = basicDialogCreateDialog;
        if (basicDialogCreateDialog != null) {
            basicDialogCreateDialog.setPermissionList(checkPermissions, onlyCall);
        }
        BasicDialog basicDialog3 = this.permissionDialog;
        if (basicDialog3 != null) {
            basicDialog3.setOnClickListener(new BasicDialog.IDialogClickListener() { // from class: com.neusoft.na.mots.MainActivity.showPermissionDialog.1
                @Override // com.neusoft.na.common.BasicDialog.IDialogClickListener
                public void onSettingClick() {
                    BasicDialog basicDialog4 = MainActivity.this.permissionDialog;
                    if (basicDialog4 != null) {
                        basicDialog4.dismissAllowingStateLoss();
                    }
                    MainActivity.this.startAppDetailActivity();
                }

                @Override // com.neusoft.na.common.BasicDialog.IDialogClickListener
                public void onExitClick(boolean isFinish) throws RemoteException {
                    BasicDialog basicDialog4 = MainActivity.this.permissionDialog;
                    if (basicDialog4 != null) {
                        basicDialog4.dismissAllowingStateLoss();
                    }
                    if (onlyCall) {
                        return;
                    }
                    if (!isFinish) {
                        MainActivity.this.startMotsMainService();
                        MainActivity.this.sendNaviActivityStartBC();
                        IMotsServiceInterface iMotsServiceInterface = MainActivity.this.binder;
                        if (iMotsServiceInterface != null) {
                            iMotsServiceInterface.haspermissions();
                            return;
                        }
                        return;
                    }
                    if (CheckBoardKt.isByd8155f() && MainActivity.this.isByd8155Launcher()) {
                        MainActivity.this.isByd8155Kill = true;
                        MainActivity.this.sendBroadcast(new Intent("com.neusoft.na.navigation.switchToWallpaperDesktop"));
                    } else {
                        MainActivity.this.finish();
                    }
                }
            });
        }
        BasicDialog basicDialog4 = this.permissionDialog;
        if (basicDialog4 != null) {
            getFragmentManager().beginTransaction().add(basicDialog4, "permission").commitAllowingStateLoss();
        }
    }

    private final void showPrivacyPolicy() {
        Log.i(TAG, "showPrivacyPolicy");
        final BasicPrivacyView basicPrivacyViewCreatePrivacy = BasicPrivacyView.createPrivacy(this, "com.neusoft.na.byd.view.PrivacyPolicyView");
        if (basicPrivacyViewCreatePrivacy == null) {
            return;
        }
        this.mPrivacyPolicyView = basicPrivacyViewCreatePrivacy;
        if (basicPrivacyViewCreatePrivacy != null) {
            basicPrivacyViewCreatePrivacy.setInMultiWindowMode(isInMultiWindowMode());
            basicPrivacyViewCreatePrivacy.setLoadListener(new BasicPrivacyView.IPrivacyViewLoadListener() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda6
                @Override // com.neusoft.na.common.BasicPrivacyView.IPrivacyViewLoadListener
                public final void onLoadDone() {
                    MainActivity.showPrivacyPolicy$lambda$20$lambda$19(this.f$0, basicPrivacyViewCreatePrivacy);
                }
            });
            basicPrivacyViewCreatePrivacy.setClickListener(new MainActivity$showPrivacyPolicy$1$2(this, basicPrivacyViewCreatePrivacy));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPrivacyPolicy$lambda$20$lambda$19(MainActivity this$0, BasicPrivacyView it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        Log.i(TAG, "showPrivacyPolicy: load done");
        RelativeLayout relativeLayout = (RelativeLayout) this$0._$_findCachedViewById(R.id.appRoot);
        if (relativeLayout != null) {
            relativeLayout.addView(it, new ViewGroup.LayoutParams(-1, -1));
        }
    }

    private final void screenDirection2Bl(Configuration configuration) {
        Log.i(TAG, "screenDirection2Bl");
        if (configuration == null) {
            configuration = getResources().getConfiguration();
        }
        Log.i(TAG, "resources.configuration == " + configuration.orientation);
        if (this.isMask || this.lockActivity) {
            if (CheckBoardKt.isByd()) {
                switchBydStartImage();
            } else if (CheckBoardKt.isByd8155f()) {
                setStartImageByByd8155F();
            }
        }
        if (CheckBoardKt.isByd8155f()) {
            return;
        }
        Intent intent = new Intent(CommonSystem.ACTION_SCREEN_DIRECTION);
        intent.putExtra("direction", configuration.orientation == 2 ? 0 : 1);
        intent.putExtra("width", configuration.screenWidthDp);
        intent.putExtra("height", configuration.screenHeightDp);
        sendBroadcast(intent);
    }

    private final void initGreatWallA01() {
        ((ImageView) _$_findCachedViewById(R.id.mask)).setVisibility(0);
        this.isMask = true;
        getLayoutInflater().inflate(getResources().getIdentifier("minibar", "layout", getPackageName()), (ViewGroup) _$_findCachedViewById(R.id.frame_minibar), true);
        ((FrameLayout) _$_findCachedViewById(R.id.frame_minibar)).setVisibility(0);
        Log.e("lv.feng", "package:" + getPackageName() + " id:" + getResources().getIdentifier("xxxx", "layout", getPackageName()));
    }

    private final void initGreatWallB01() {
        Log.i(TAG, "initGreatWallB01");
        ((ImageView) _$_findCachedViewById(R.id.mask)).setVisibility(0);
        this.isMask = true;
        b01NightMode();
        addGreatWallB01Aar();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void showV61LaunchHint() throws java.lang.ClassNotFoundException {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.mots.MainActivity.showV61LaunchHint():void");
    }

    private final void initGreatWallV61() throws ClassNotFoundException {
        Log.i(TAG, "initGreatWallV61");
        showV61LaunchHint();
        ((FrameLayout) _$_findCachedViewById(R.id.frame_minibar)).setVisibility(8);
        ((ImageView) _$_findCachedViewById(R.id.mask)).setVisibility(0);
        this.isMask = true;
        b01NightMode();
        addGreatWallAddGreatWallV61AAR();
    }

    private final void initByd() {
        Log.i(TAG, "initByd()");
        Intent intent = new Intent("com.neusoft.na.navigation.action.widget.init");
        intent.setComponent(new ComponentName(this, "com.neusoft.na.byd.BydAppWidget"));
        sendBroadcast(intent);
        if (CheckBoardKt.isByd()) {
            switchBydStartImage();
        } else if (CheckBoardKt.isByd8155f()) {
            setStartImageByByd8155F();
        }
        ((ImageView) _$_findCachedViewById(R.id.mask)).setVisibility(0);
        this.isMask = true;
    }

    private final void switchBydStartImage() {
        if (isInMultiWindowMode()) {
            Log.i(TAG, "switchBydStartImage: InMultiWindowMode");
            int i = getResources().getConfiguration().screenHeightDp;
            int i2 = getResources().getConfiguration().screenWidthDp;
            Log.i(TAG, "switchBydStartImage: h:" + i + ",w:" + i2);
            if (i2 == 720) {
                findViewById(com.neusoft.na.navigation.R.id.mask).setBackgroundResource(com.neusoft.na.navigation.R.drawable.mask_multi_port);
                return;
            } else {
                findViewById(com.neusoft.na.navigation.R.id.mask).setBackgroundResource(com.neusoft.na.navigation.R.drawable.mask_multi);
                return;
            }
        }
        Log.i(TAG, "switchBydStartImage");
        if (getResources().getConfiguration().orientation == 2) {
            ((ImageView) _$_findCachedViewById(R.id.mask)).setBackground(getResources().getDrawable(com.neusoft.na.navigation.R.drawable.mask));
        } else {
            ((ImageView) _$_findCachedViewById(R.id.mask)).setBackground(getResources().getDrawable(com.neusoft.na.navigation.R.drawable.mask_portrait_byd));
        }
    }

    private final void switchZeekrStartImage() {
        Log.i(TAG, "[switchZeekrStartImage]");
        Object systemService = getSystemService("uimode");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.UiModeManager");
        UiModeManager uiModeManager = (UiModeManager) systemService;
        Log.i(TAG, "uiModeManager.nightMode == " + uiModeManager.getNightMode());
        String str = uiModeManager.getNightMode() == 1 ? "mask" : "mask_night";
        Log.i(TAG, "resourceName == ".concat(str));
        ((ImageView) _$_findCachedViewById(R.id.mask)).setBackgroundResource(getResources().getIdentifier(str, AttrFactory.DRAWABLE, getPackageName()));
    }

    private final void initWeyforproductv3() {
        Log.i(TAG, "initWeyforproductv3()");
        ((ImageView) _$_findCachedViewById(R.id.mask)).setVisibility(0);
        this.isMask = true;
        b01DayMode();
    }

    private final void initSmart() {
        Log.i(TAG, "initSmart()");
        ((ImageView) _$_findCachedViewById(R.id.mask)).setBackground(null);
        ((ImageView) _$_findCachedViewById(R.id.mask)).setScaleType(ImageView.ScaleType.CENTER_CROP);
        ((ImageView) _$_findCachedViewById(R.id.mask)).setImageResource(com.neusoft.na.navigation.R.drawable.mask);
        ((ImageView) _$_findCachedViewById(R.id.mask)).setVisibility(0);
        this.isMask = true;
    }

    private final void initZeekr() {
        Log.i(TAG, "[initZeekr]");
        ((ImageView) _$_findCachedViewById(R.id.mask)).setVisibility(0);
        this.isMask = true;
    }

    private final void addGreatWallB01Aar() {
        new Thread(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() throws ClassNotFoundException {
                MainActivity.addGreatWallB01Aar$lambda$23(this.f$0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v17, types: [T, java.lang.String] */
    public static final void addGreatWallB01Aar$lambda$23(final MainActivity this$0) throws ClassNotFoundException {
        T t;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        File externalMotsBlConfigFile = ConstantsKt.getExternalMotsBlConfigFile();
        if (externalMotsBlConfigFile.exists()) {
            FileReader fileReader = new FileReader(externalMotsBlConfigFile);
            StringBuffer stringBuffer = new StringBuffer();
            Iterator<T> it = TextStreamsKt.readLines(fileReader).iterator();
            while (it.hasNext()) {
                stringBuffer.append((String) it.next());
            }
            if (stringBuffer.length() > 0) {
                JSONObject jSONObject = new JSONObject(stringBuffer.toString());
                if (jSONObject.has("cabPosition")) {
                    objectRef.element = jSONObject.getString("cabPosition");
                    Log.i(TAG, "load test file, cabPosition == " + ((String) objectRef.element));
                }
            }
        }
        if (objectRef.element == 0) {
            String systemPropertiesValue = new SystemUtils().getSystemPropertiesValue(SystemUtils.TYPE_RUDDER_MODE);
            if (Intrinsics.areEqual(systemPropertiesValue, FileUtils.DEFAULT_VERSION)) {
                t = "l";
            } else if (Intrinsics.areEqual(systemPropertiesValue, "1")) {
                t = "r";
            } else {
                Log.e(MotsService.TAG, "rudderMode error");
                t = "";
            }
            objectRef.element = t;
            Log.i(TAG, "load system, cabPosition == " + ((String) objectRef.element));
        }
        this$0.mHandler.post(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
                MainActivity.addGreatWallB01Aar$lambda$23$lambda$22(this.f$0, objectRef);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addGreatWallB01Aar$lambda$23$lambda$22(MainActivity this$0, Ref.ObjectRef cabPosition) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cabPosition, "$cabPosition");
        ViewGroup.LayoutParams layoutParams = ((FrameLayout) this$0._$_findCachedViewById(R.id.frame_minibar)).getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        layoutParams2.topMargin = 25;
        layoutParams2.setMarginEnd(0);
        layoutParams2.width = 520;
        layoutParams2.height = 690;
        if (this$0.getWindowManager().getDefaultDisplay().getHeight() >= 1080) {
            Log.i(TAG, "big screen, minibar at bottom.");
            layoutParams2.setMarginStart(0);
            layoutParams2.setMarginEnd(0);
            layoutParams2.gravity = 81;
            layoutParams2.setMargins(0, 0, 0, 0);
        } else if (Intrinsics.areEqual(cabPosition.element, "l")) {
            layoutParams2.gravity = 3;
        } else {
            layoutParams2.gravity = 5;
        }
        ((FrameLayout) this$0._$_findCachedViewById(R.id.frame_minibar)).setLayoutParams(layoutParams2);
        try {
            ClassLoader classLoader = this$0.getClass().getClassLoader();
            Intrinsics.checkNotNull(classLoader);
            Class<?> clsLoadClass = classLoader.loadClass(MINI_BAR_TOOL);
            if (clsLoadClass != null) {
                Object objNewInstance = clsLoadClass.getConstructor(Context.class).newInstance(this$0);
                if (objNewInstance != null) {
                    if (objNewInstance instanceof IGreatWallAarComponent) {
                        this$0.maarystem = (IGreatWallAarComponent) objNewInstance;
                    } else {
                        INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.mots.MainActivity$addGreatWallB01Aar$1$2$1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return "instance does not implement IGreatWallAarComponent";
                            }
                        });
                    }
                } else {
                    INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.mots.MainActivity$addGreatWallB01Aar$1$2$2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "failed to create instance";
                        }
                    });
                }
            } else {
                INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.mots.MainActivity$addGreatWallB01Aar$1$2$3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "failed to find class 'com.neusoft.greatwallb01aar.MiniBarTool'";
                    }
                });
            }
        } catch (RuntimeException unused) {
            INSTANCE.getLogger().error("failed to load class com.neusoft.greatwallb01aar.MiniBarTool");
        }
        IGreatWallAarComponent iGreatWallAarComponent = this$0.maarystem;
        ((FrameLayout) this$0._$_findCachedViewById(R.id.frame_minibar)).addView(iGreatWallAarComponent != null ? iGreatWallAarComponent.getMinibar() : null);
        IGreatWallAarComponent iGreatWallAarComponent2 = this$0.maarystem;
        if (iGreatWallAarComponent2 != null) {
            iGreatWallAarComponent2.minibarInit();
        }
        ((FrameLayout) this$0._$_findCachedViewById(R.id.frame_minibar)).setVisibility(0);
    }

    private final void addGreatWallAddGreatWallV61AAR() {
        new Thread(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() throws ClassNotFoundException {
                MainActivity.addGreatWallAddGreatWallV61AAR$lambda$26(this.f$0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v17, types: [T, java.lang.String] */
    public static final void addGreatWallAddGreatWallV61AAR$lambda$26(final MainActivity this$0) throws ClassNotFoundException {
        T t;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        File externalMotsBlConfigFile = ConstantsKt.getExternalMotsBlConfigFile();
        if (externalMotsBlConfigFile.exists()) {
            FileReader fileReader = new FileReader(externalMotsBlConfigFile);
            StringBuffer stringBuffer = new StringBuffer();
            Iterator<T> it = TextStreamsKt.readLines(fileReader).iterator();
            while (it.hasNext()) {
                stringBuffer.append((String) it.next());
            }
            if (stringBuffer.length() > 0) {
                JSONObject jSONObject = new JSONObject(stringBuffer.toString());
                if (jSONObject.has("cabPosition")) {
                    objectRef.element = jSONObject.getString("cabPosition");
                    Log.i(TAG, "load test file, cabPosition == " + ((String) objectRef.element));
                }
            }
        }
        if (objectRef.element == 0) {
            String systemPropertiesValue = new SystemUtils().getSystemPropertiesValue(SystemUtils.TYPE_RUDDER_MODE);
            if (Intrinsics.areEqual(systemPropertiesValue, FileUtils.DEFAULT_VERSION)) {
                t = "l";
            } else if (Intrinsics.areEqual(systemPropertiesValue, "1")) {
                t = "r";
            } else {
                Log.e(MotsService.TAG, "rudderMode error");
                t = "";
            }
            objectRef.element = t;
            Log.i(TAG, "load system, cabPosition == " + ((String) objectRef.element));
        }
        this$0.mHandler.post(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
                MainActivity.addGreatWallAddGreatWallV61AAR$lambda$26$lambda$25(this.f$0, objectRef);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void addGreatWallAddGreatWallV61AAR$lambda$26$lambda$25(MainActivity this$0, Ref.ObjectRef cabPosition) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cabPosition, "$cabPosition");
        ViewGroup.LayoutParams layoutParams = ((FrameLayout) this$0._$_findCachedViewById(R.id.frame_minibar)).getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        if (this$0.getWindowManager().getDefaultDisplay().getHeight() >= 1080) {
            Log.i(TAG, "big screen, minibar at bottom.");
            layoutParams2.setMarginStart(0);
            layoutParams2.setMarginEnd(0);
            layoutParams2.gravity = 81;
            layoutParams2.setMargins(0, 0, 0, 30);
        } else {
            Log.i(TAG, "small screen, minibar at left or right.");
            if (Intrinsics.areEqual((String) cabPosition.element, "l")) {
                layoutParams2.gravity = 3;
            } else {
                layoutParams2.gravity = 5;
            }
        }
        ((FrameLayout) this$0._$_findCachedViewById(R.id.frame_minibar)).setLayoutParams(layoutParams2);
        try {
            ClassLoader classLoader = this$0.getClass().getClassLoader();
            Intrinsics.checkNotNull(classLoader);
            Class<?> clsLoadClass = classLoader.loadClass("com.neusoft.greatwallv61aar.MiniBarTool");
            if (clsLoadClass != null) {
                Object objNewInstance = clsLoadClass.getConstructor(Context.class, Integer.TYPE).newInstance(this$0, Integer.valueOf(this$0.getWindowManager().getDefaultDisplay().getHeight()));
                if (objNewInstance != null) {
                    if (objNewInstance instanceof IGreatWallAarComponent) {
                        this$0.maarystem = (IGreatWallAarComponent) objNewInstance;
                    } else {
                        INSTANCE.getLogger().error("instance does not implement IGreatWallAarComponent");
                    }
                } else {
                    INSTANCE.getLogger().error("failed to create instance");
                }
            } else {
                INSTANCE.getLogger().error("failed to find class com.neusoft.greatwallv61aar.MiniBarTool");
            }
        } catch (RuntimeException unused) {
            INSTANCE.getLogger().error("failed to load class com.neusoft.greatwallv61aar.MiniBarTool");
        }
        IGreatWallAarComponent iGreatWallAarComponent = this$0.maarystem;
        FrameLayout minibar = iGreatWallAarComponent != null ? iGreatWallAarComponent.getMinibar() : null;
        StringBuilder sb = new StringBuilder("minibar height == ");
        Intrinsics.checkNotNull(minibar);
        Log.i(TAG, sb.append(minibar.getLayoutParams().height).append(", width == ").append(minibar.getLayoutParams().width).toString());
        ((FrameLayout) this$0._$_findCachedViewById(R.id.frame_minibar)).addView(minibar);
        IGreatWallAarComponent iGreatWallAarComponent2 = this$0.maarystem;
        if (iGreatWallAarComponent2 != null) {
            iGreatWallAarComponent2.minibarInit();
        }
        ((FrameLayout) this$0._$_findCachedViewById(R.id.frame_minibar)).setVisibility(0);
    }

    private final void createArManagerinstance() throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Intrinsics.checkNotNull(classLoader);
            Class<?> clsLoadClass = classLoader.loadClass(AR_COMPONENT);
            if (clsLoadClass != null) {
                Object objNewInstance = clsLoadClass.getConstructor(Context.class).newInstance(this);
                if (objNewInstance != null) {
                    if (objNewInstance instanceof IArComponent) {
                        this.arComponentWapper = (IArComponent) objNewInstance;
                    } else {
                        INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.mots.MainActivity.createArManagerinstance.1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return "instance does not implement IGreatWallAarComponent";
                            }
                        });
                    }
                } else {
                    INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.mots.MainActivity.createArManagerinstance.2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "failed to create instance";
                        }
                    });
                }
            } else {
                INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.mots.MainActivity.createArManagerinstance.3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "failed to find class 'com.neusoft.arcomponent.ArComponentWapper'";
                    }
                });
            }
        } catch (RuntimeException unused) {
            INSTANCE.getLogger().error(new Function0<Object>() { // from class: com.neusoft.na.mots.MainActivity.createArManagerinstance.4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "failed to load class 'com.neusoft.arcomponent.ArComponentWapper'";
                }
            });
        }
    }

    private final void mc2DayMode() {
        getWindow().getDecorView().setSystemUiVisibility(14080);
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().setNavigationBarColor(0);
        getWindow().setStatusBarColor(0);
    }

    private final void mc2NightMode() {
        getWindow().getDecorView().setSystemUiVisibility(5888);
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().setNavigationBarColor(0);
        getWindow().setStatusBarColor(0);
    }

    private final void b01DayMode() {
        Log.i(TAG, "b01DayMode");
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.flags = 67108864 | attributes.flags;
        View decorView = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getWindow().decorView");
        decorView.setSystemUiVisibility(9216);
        window.setAttributes(attributes);
    }

    private final void b01NightMode() {
        Log.i(TAG, "b01NightMode");
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.flags = 67108864 | attributes.flags;
        View decorView = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getWindow().decorView");
        decorView.setSystemUiVisibility(1280);
        window.setAttributes(attributes);
    }

    private final void dayMode() {
        RelativeLayout relativeLayout;
        Log.i(TAG, "dayMode");
        TypedValue typedValue = new TypedValue();
        if (!getTheme().resolveAttribute(com.neusoft.na.navigation.R.attr.forceFullScreenLayout, typedValue, true) || typedValue.type != 18 || typedValue.data == 0 || (relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.appRoot)) == null) {
            return;
        }
        relativeLayout.setSystemUiVisibility(9728);
    }

    private final void nightMode() {
        RelativeLayout relativeLayout;
        Log.i(TAG, "nightMode");
        TypedValue typedValue = new TypedValue();
        if (!getTheme().resolveAttribute(com.neusoft.na.navigation.R.attr.forceFullScreenLayout, typedValue, true) || typedValue.type != 18 || typedValue.data == 0 || (relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.appRoot)) == null) {
            return;
        }
        relativeLayout.setSystemUiVisibility(1792);
    }

    private final void setNavbarColor(boolean nightMode) {
        Log.i(TAG, "setNavbarColor nightMode == " + nightMode);
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().setNavigationBarColor(getResources().getColor(nightMode ? com.neusoft.na.navigation.R.color.colorSmartNavigationBarColorBlack : com.neusoft.na.navigation.R.color.colorSmartNavigationBarColorWhile));
    }

    private final void setNavbarColor4Byd(boolean nightMode) {
        Log.i(TAG, "setNavbarColor nightMode == " + nightMode);
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().setBackgroundDrawableResource(nightMode ? com.neusoft.na.navigation.R.color.color_window_background_night_byd : com.neusoft.na.navigation.R.color.color_window_background_day_byd);
        boolean zIsByd = CheckBoardKt.isByd();
        int i = com.neusoft.na.navigation.R.color.colorBydNavigationBarColorDark;
        if (zIsByd) {
            getWindow().setStatusBarColor(getResources().getColor(nightMode ? com.neusoft.na.navigation.R.color.colorBydNavigationBarColorDark : com.neusoft.na.navigation.R.color.colorBydNavigationBarColorLight));
            Window window = getWindow();
            Resources resources = getResources();
            if (!nightMode) {
                i = com.neusoft.na.navigation.R.color.colorBydNavigationBarColorLight;
            }
            window.setNavigationBarColor(resources.getColor(i));
            return;
        }
        if (CheckBoardKt.isByd8155f()) {
            if (isInMultiWindowMode()) {
                getWindow().setStatusBarColor(getResources().getColor(nightMode ? com.neusoft.na.navigation.R.color.colorBydNavigationBarColorDark : com.neusoft.na.navigation.R.color.colorBydNavigationBarColorLight));
                Window window2 = getWindow();
                Resources resources2 = getResources();
                if (!nightMode) {
                    i = com.neusoft.na.navigation.R.color.colorBydNavigationBarColorLight;
                }
                window2.setNavigationBarColor(resources2.getColor(i));
                return;
            }
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
            getWindow().setNavigationBarColor(getResources().getColor(android.R.color.transparent));
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
        ((MotsApplication) application).addStartCount();
        sendStatus(1);
        updateWidget(true);
    }

    @Override // android.app.Activity
    protected void onStop() throws RemoteException {
        super.onStop();
        Log.i(TAG, "[onStop]");
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
        ((MotsApplication) application).delStartCount();
        Application application2 = getApplication();
        Intrinsics.checkNotNull(application2, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
        if (((MotsApplication) application2).cannotStopProcess()) {
            Log.i(TAG, "onStop: invalid");
            return;
        }
        sendNaviForegroundState(false);
        this.isDisplay = false;
        IMotsServiceInterface iMotsServiceInterface = this.binder;
        if (iMotsServiceInterface != null) {
            iMotsServiceInterface.onMainPause();
        }
        sendStatus(0);
        updateWidget(false);
    }

    private final void updateWidget(boolean isShow) {
        if (CheckBoardKt.isByd8155f()) {
            Intent intent = new Intent("com.neusoft.na.navigation.action.widget.update");
            intent.setComponent(new ComponentName("com.neusoft.na.navigation", "com.neusoft.na.byd.BydAppWidget"));
            intent.putExtra("isShow", isShow);
            sendBroadcast(intent);
        }
    }

    private final void sendStatus(int status) {
        if (CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            Log.i(TAG, "sendStatus: " + status);
            Intent intent = new Intent("byd.intent.action.MAP_ACTIVITY_STATE_CHANGED");
            intent.putExtra("packageName", "com.neusoft.na.navigation");
            intent.putExtra("state", status);
            sendBroadcast(intent);
        }
    }

    private final void startHmiMaskRemoveTask() {
        if (CheckBoardKt.isByd8155f()) {
            Log.i(TAG, "[startHmiMaskRemoveTask] hmiMaskGoneTimerTask == " + this.hmiMaskGoneTimerTask);
            if (this.hmiMaskGoneTimerTask == null) {
                C00621 c00621 = new C00621();
                this.hmiMaskGoneTimerTask = c00621;
                this.hmiMaskGoneTimer.schedule(c00621, this.hmiMaskGoneDelay);
                Log.i(TAG, "[hmiMaskGoneTimerTask] --> start timer");
                return;
            }
            Log.i(TAG, "[hmiMaskGoneTimerTask] --> hmiMaskGoneTimerTask timer hmiMaskGoneTimerTaskRunning...");
        }
    }

    /* JADX INFO: renamed from: com.neusoft.na.mots.MainActivity$startHmiMaskRemoveTask$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/neusoft/na/mots/MainActivity$startHmiMaskRemoveTask$1", "Ljava/util/TimerTask;", "run", "", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class C00621 extends TimerTask {
        C00621() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            final MainActivity mainActivity = MainActivity.this;
            mainActivity.runOnUiThread(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$startHmiMaskRemoveTask$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.C00621.run$lambda$0(mainActivity);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void run$lambda$0(MainActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Log.i(MainActivity.TAG, "[startHmiMaskRemoveTask] --> remove hmi mask, because " + this$0.hmiMaskGoneDelay + "ms time over.");
            this$0._$_findCachedViewById(R.id.hmiMaskView).setBackgroundResource(android.R.color.transparent);
            this$0._$_findCachedViewById(R.id.hmiMaskView).setVisibility(8);
            this$0.hmiMaskGoneTimerTask = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifySystem4HmiSettingsDayNightMode() {
        Log.i(TAG, "notifySystem4HmiSettingsDayNightMode");
        int i = this.hmiSettingsDayNightMode;
        int i2 = i != 0 ? i != 1 ? -1 : 0 : 1;
        if (i2 != -1) {
            Log.i(TAG, "notify system hmi day night mode -> notifySystemValue == " + i2);
            Intent intent = new Intent("action.com.neusoft.na.navigation.setting.daynightmode");
            intent.putExtra("dayNightMode", i2);
            sendBroadcast(intent);
        }
    }

    private final void printWindowInfo() {
        View decorView;
        View rootView;
        View decorView2;
        Rect rect = new Rect();
        Point point = new Point();
        Window window = getWindow();
        Log.i(TAG, "printWindowInfo window.decorView.getGlobalVisibleRect: return:" + ((window == null || (decorView2 = window.getDecorView()) == null) ? null : Boolean.valueOf(decorView2.getGlobalVisibleRect(rect, point))) + ", Rect" + rect + " globalOffset:" + point);
        Rect rect2 = new Rect();
        Point point2 = new Point();
        Window window2 = getWindow();
        Log.i(TAG, "printWindowInfo window.decorView.rootView.getGlobalVisibleRect: return:" + ((window2 == null || (decorView = window2.getDecorView()) == null || (rootView = decorView.getRootView()) == null) ? null : Boolean.valueOf(rootView.getGlobalVisibleRect(rect2, point2))) + ", Rect" + rect2 + " globalOffset:" + point2);
        Rect rect3 = new Rect();
        Point point3 = new Point();
        RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.appRoot);
        Log.i(TAG, "printWindowInfo appRoot.getGlobalVisibleRect return:" + (relativeLayout != null ? Boolean.valueOf(relativeLayout.getGlobalVisibleRect(rect3, point3)) : null) + ", Rect" + rect3 + " globalOffset:" + point3);
    }

    @Override // android.app.Activity
    protected void onResume() throws RemoteException {
        IWebViewDialogComponent iWebViewDialogComponent;
        super.onResume();
        printWindowInfo();
        StringBuilder sb = new StringBuilder("[onResume] w:");
        FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(R.id.appPanel);
        StringBuilder sbAppend = sb.append(frameLayout != null ? Integer.valueOf(frameLayout.getWidth()) : null).append(',');
        TextureView textureView = (TextureView) _$_findCachedViewById(R.id.mapDisplayArea);
        StringBuilder sbAppend2 = sbAppend.append(textureView != null ? Integer.valueOf(textureView.getWidth()) : null).append(',');
        FrameLayout frameLayout2 = (FrameLayout) _$_findCachedViewById(R.id.hmiWebViewContainer);
        StringBuilder sbAppend3 = sbAppend2.append(frameLayout2 != null ? Integer.valueOf(frameLayout2.getWidth()) : null).append(";h:");
        FrameLayout frameLayout3 = (FrameLayout) _$_findCachedViewById(R.id.appPanel);
        StringBuilder sbAppend4 = sbAppend3.append(frameLayout3 != null ? Integer.valueOf(frameLayout3.getHeight()) : null).append(',');
        TextureView textureView2 = (TextureView) _$_findCachedViewById(R.id.mapDisplayArea);
        StringBuilder sbAppend5 = sbAppend4.append(textureView2 != null ? Integer.valueOf(textureView2.getHeight()) : null).append(',');
        FrameLayout frameLayout4 = (FrameLayout) _$_findCachedViewById(R.id.hmiWebViewContainer);
        Log.i(TAG, sbAppend5.append(frameLayout4 != null ? Integer.valueOf(frameLayout4.getHeight()) : null).toString());
        if (CheckBoardKt.isByd8155f()) {
            if (this.isFirstStart) {
                notifyDiLinkStartedByByd8155f();
                sendStickyBroadcast(new Intent("com.neusoft.na.navigation.notifyDiLinkStarted"));
                notifySystem4HmiSettingsDayNightMode();
            }
            this.isFirstStart = false;
            sendSetDefaultLauncher();
            if (_$_findCachedViewById(R.id.hmiMaskView).getVisibility() == 0) {
                startHmiMaskRemoveTask();
            }
        }
        Log.i(TAG, "[NeuMapLCM]onResume " + this.isShowSystemPermissionDialog);
        if (!this.isShowSystemPermissionDialog && CheckBoardKt.isBydSeries()) {
            MainActivity mainActivity = this;
            if (FileUtils.getInstance(mainActivity).isAgreePrivacy()) {
                if (FileUtils.getInstance(mainActivity).isHasPermissionByd()) {
                    startMotsMainService();
                    sendNaviActivityStartBC();
                    IMotsServiceInterface iMotsServiceInterface = this.binder;
                    if (iMotsServiceInterface != null) {
                        iMotsServiceInterface.haspermissions();
                    }
                    ((ConstraintLayout) _$_findCachedViewById(R.id.permissionsError)).setVisibility(8);
                    startMaskRemoveTask();
                    sendPermissionStickyBroadcast();
                    initPresentationSystemInterfaceByByd8155F();
                } else {
                    Log.i(TAG, "show permission dialog");
                    showPermissionDialog(checkPermissions());
                }
            }
        }
        if (CheckBoardKt.isByd8155f()) {
            StringBuilder sb2 = new StringBuilder("onResume width:");
            RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.appRoot);
            Log.i(TAG, sb2.append(relativeLayout != null ? Integer.valueOf(relativeLayout.getWidth()) : null).append(", height:").append(((RelativeLayout) _$_findCachedViewById(R.id.appRoot)).getHeight()).toString());
            if (this.isMask) {
                Intent intent = new Intent("com.byd.card.open.FloatCard");
                intent.addFlags(16777216);
                intent.putExtra(Car.PACKAGE_SERVICE, getPackageName());
                sendBroadcast(intent);
            }
        }
        if (CheckBoardKt.isGreatwallV61() || CheckBoardKt.isGreatwallB01()) {
            sendBroadcast(new Intent("com.beantechs.action.BOOT_ANIM_EXIT"));
            Log.i(TAG, "send com.beantechs.action.BOOT_ANIM_EXIT broadcast");
        }
        if (CheckBoardKt.isGreatwallV61() || CheckBoardKt.isWeyforproductv3()) {
            setGreatwallV61ActionBarBackground();
        }
        if (CheckBoardKt.isSmart()) {
            setSmartActionBarBackground();
        }
        sendNaviForegroundState(true);
        this.isDisplay = true;
        IMotsServiceInterface iMotsServiceInterface2 = this.binder;
        if (iMotsServiceInterface2 != null) {
            iMotsServiceInterface2.onMainResume();
        }
        if (CheckBoardKt.isSmart() || CheckBoardKt.isZeekr() || CheckBoardKt.isByd8155f() || CheckBoardKt.isSeres()) {
            sendBroadcast(new Intent(Byd8155fSystem.ACTION_RESUME));
        }
        if (CheckBoardKt.isGreatwallV61() || CheckBoardKt.isGreatwallB01() || CheckBoardKt.isSmart() || CheckBoardKt.isSeres() || CheckBoardKt.isZeekr()) {
            sendSwitchDayNightModeBroadcast();
        }
        if (CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            int i = getResources().getConfiguration().uiMode & 48;
            Log.i(TAG, "onResume systemUiMode == " + i);
            setNavbarColor4Byd(i == 32);
            sendSwitchDayNightModeBroadcastForByd(i == 32 ? 2 : 1);
            sendBydBroadcast();
        }
        if (!CheckBoardKt.isSmart() || (iWebViewDialogComponent = this.webViewDialogComponent) == null || !iWebViewDialogComponent.isShowingDialog() || iWebViewDialogComponent.isInMultiWindowMode() == isInMultiWindowMode()) {
            return;
        }
        iWebViewDialogComponent.dismissDialog();
        iWebViewDialogComponent.showDialog();
    }

    private final void sendPermissionStickyBroadcast() {
        Log.i(TAG, "[sendPermissionStickyBroadcast]");
        sendStickyBroadcast(new Intent(Byd8155fSystem.ACTION_PERMISSION_ALL_SUCCESS));
    }

    private final void sendNaviForegroundState(boolean isForeground) {
        Log.i(TAG, "[sendNaviForegroundState]");
        Intent intent = new Intent(Byd8155fSystem.ACTION_NAVI_FOREGROUND_CHANGED);
        intent.putExtra("isForeground", isForeground);
        sendStickyBroadcast(intent);
    }

    private final void notifyDiLinkStartedByByd8155f() {
        Log.i(TAG, "[notifyDiLinkStartedByByd8155f]");
        ClassLoader classLoader = getClass().getClassLoader();
        Intrinsics.checkNotNull(classLoader);
        IBydBootAdapterComponent iBydBootAdapterComponent = (IBydBootAdapterComponent) classLoader.loadClass("com.neusoft.na.byd.Byd8155fBootAdapter").getConstructor(new Class[0]).newInstance(new Object[0]);
        if (iBydBootAdapterComponent != null) {
            iBydBootAdapterComponent.notifyDiLinkStarted(this);
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        Log.i(TAG, "onPause");
        if (CheckBoardKt.isByd8155f()) {
            sendSetDefaultLauncher();
        }
        this.keyboardStatus = Companion.keyBoardValue.HIDE;
        if (CheckBoardKt.isGreatwallA01() || CheckBoardKt.isGreatwallB01() || CheckBoardKt.isGreatwallV61() || CheckBoardKt.isWeyforproductv3() || CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            sendBroadcast(new Intent(Byd8155fSystem.ACTION_PAUSE));
            if (getWindow().getAttributes().softInputMode != 2 && getCurrentFocus() != null) {
                Object systemService = getSystemService("input_method");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                InputMethodManager inputMethodManager = (InputMethodManager) systemService;
                View currentFocus = getCurrentFocus();
                inputMethodManager.hideSoftInputFromWindow(currentFocus != null ? currentFocus.getWindowToken() : null, 2);
            }
        }
        super.onPause();
    }

    private final void sendSetDefaultLauncher() {
        Log.i(TAG, "[sendSetDefaultLauncher]");
        sendBroadcast(new Intent("com.neusoft.na.navigation.setDefaultLauncher"));
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.maskGoneTimerTask != null) {
            Log.i(TAG, "[startMaskRemoveTask] --> cancel");
            this.maskGoneTimer.cancel();
            this.maskGoneTimerTask = null;
        }
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
        ((MotsApplication) application).delCreateCount();
        Application application2 = getApplication();
        Intrinsics.checkNotNull(application2, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
        if (((MotsApplication) application2).cannotDestroyProcess()) {
            Log.i(TAG, "onDestroy: invalid");
            return;
        }
        try {
            Log.i(TAG, "onDestroy");
            MotsBroadcastReceiver motsBroadcastReceiver = this.mMotsBroadcastReceiver;
            if (motsBroadcastReceiver != null) {
                motsBroadcastReceiver.setMotsBroadcastCallBack(null);
            }
            IMotsServiceInterface iMotsServiceInterface = this.binder;
            if (iMotsServiceInterface != null) {
                iMotsServiceInterface.onMainDestroy();
            }
            unbindService(this.serviceConnection);
            unregisterReceiver(this.noNeedServiceBroadcastReceiver);
            Log.i(TAG, "onDestroy done");
        } catch (Exception unused) {
            Log.d(TAG, "onDestroy: service is not register");
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) throws RemoteException {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        Log.i(TAG, "onRequestPermissionsResult");
        if (requestCode == 143) {
            this.isShowSystemPermissionDialog = false;
            List<String> listCheckPermissions = checkPermissions();
            if (listCheckPermissions.isEmpty()) {
                if (CheckBoardKt.isBydSeries()) {
                    startMotsMainService();
                    sendNaviActivityStartBC();
                    startMaskRemoveTask();
                    sendPermissionStickyBroadcast();
                }
                initPresentationSystemInterfaceByByd8155F();
                IMotsServiceInterface iMotsServiceInterface = this.binder;
                if (iMotsServiceInterface != null) {
                    iMotsServiceInterface.haspermissions();
                }
                ((ConstraintLayout) _$_findCachedViewById(R.id.permissionsError)).setVisibility(8);
            } else if (CheckBoardKt.isBydSeries()) {
                Log.i(TAG, "onRequestPermissionsResult");
                showPermissionDialog(listCheckPermissions);
            } else {
                ((ConstraintLayout) _$_findCachedViewById(R.id.permissionsError)).setVisibility(0);
            }
        } else if (requestCode == 1431) {
            this.isShowSystemPermissionDialog = false;
            if (checkSelfPermission("android.permission.CALL_PHONE") == 0) {
                String str = this.mPhoneNumber;
                if (str != null) {
                    if (CheckBoardKt.isByd()) {
                        Intent intent = new Intent("android.intent.action.DIAL");
                        intent.setData(Uri.parse(str));
                        intent.setFlags(268435456);
                        startActivity(intent);
                    } else if (isBluetoothConnect()) {
                        Log.i(TAG, "bluetooth connected");
                        Intent intent2 = new Intent("android.intent.action.MAIN");
                        intent2.addCategory("android.intent.category.LAUNCHER");
                        intent2.setFlags(268435456);
                        ComponentName componentName = new ComponentName("com.byd.bluetoothcall", "com.byd.bluetoothcall.view.activity.MainActivity");
                        intent2.putExtra("page", 3);
                        intent2.putExtra("dial_number", str);
                        intent2.setComponent(componentName);
                        startActivity(intent2);
                        Log.i(TAG, "startActivity dialpad");
                    } else {
                        Log.i(TAG, "bluetooth not connected");
                        PackageManager packageManager = getPackageManager();
                        Intrinsics.checkNotNullExpressionValue(packageManager, "packageManager");
                        Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?activity=Bluetooth"));
                        intent3.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
                        Intrinsics.checkNotNullExpressionValue(packageManager.queryIntentServices(intent3, 0), "pm.queryIntentServices(intent, 0)");
                        if (!r1.isEmpty()) {
                            startService(intent3);
                        }
                        Log.i(TAG, "startActivity bluetooth setting");
                    }
                    this.mPhoneNumber = null;
                }
            } else {
                showPermissionDialog(checkPermissions(), true);
            }
        }
        Log.i(TAG, "onRequestPermissionsResult: requestCode = " + requestCode);
        for (String str2 : permissions) {
            Log.i(TAG, "onRequestPermissionsResult: permissions = " + str2);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

    private final boolean keyCodeFilter(KeyEvent event) {
        Integer numValueOf = event != null ? Integer.valueOf(event.getKeyCode()) : null;
        if (!((((((((((((numValueOf != null && numValueOf.intValue() == 126) || (numValueOf != null && numValueOf.intValue() == 127)) || (numValueOf != null && numValueOf.intValue() == 85)) || (numValueOf != null && numValueOf.intValue() == 91)) || (numValueOf != null && numValueOf.intValue() == 79)) || (numValueOf != null && numValueOf.intValue() == 86)) || (numValueOf != null && numValueOf.intValue() == 87)) || (numValueOf != null && numValueOf.intValue() == 88)) || (numValueOf != null && numValueOf.intValue() == 89)) || (numValueOf != null && numValueOf.intValue() == 130)) || (numValueOf != null && numValueOf.intValue() == 90)) || (numValueOf != null && numValueOf.intValue() == 222))) {
            return false;
        }
        Log.i(TAG, "Got special key event,return");
        return true;
    }

    @Override // android.app.Activity
    public void onBackPressed() throws RemoteException {
        Log.i(TAG, "onBackPressed");
        if (CheckBoardKt.isBydSeries() && this.isMask) {
            BasicDialog basicDialog = this.permissionDialog;
            boolean z = false;
            if (basicDialog != null && basicDialog.isVisible()) {
                z = true;
            }
            if (z) {
                BasicDialog basicDialog2 = this.permissionDialog;
                if (basicDialog2 != null) {
                    basicDialog2.dismiss();
                }
                checkStartMots();
                return;
            }
            if (((RelativeLayout) _$_findCachedViewById(R.id.appRoot)).findViewWithTag(TAG_BYD_8155) != null) {
                removeBydView();
                checkStartMots();
            } else {
                if (CheckBoardKt.isByd8155f()) {
                    if (isByd8155Launcher()) {
                        Log.i(TAG, "byd8155F is launcher and top");
                        return;
                    } else {
                        Log.i(TAG, "byd8155F is not launcher");
                        moveTaskToBack(true);
                        return;
                    }
                }
                Log.i(TAG, "onBackPressed: moveTaskToBack");
                moveTaskToBack(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isByd8155Launcher() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = getPackageManager().resolveActivity(intent, 0);
        if (resolveInfoResolveActivity == null) {
            return false;
        }
        Log.i(TAG, "resolveInfo.activityInfo.packageName == " + resolveInfoResolveActivity.activityInfo.packageName);
        return Intrinsics.areEqual(resolveInfoResolveActivity.activityInfo.packageName, getPackageName());
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) throws RemoteException {
        IMotsServiceInterface iMotsServiceInterface;
        if (CheckBoardKt.isBydSeries() && this.isMask) {
            Log.i(TAG, "dispatchKeyEvent: Mask is visible");
            return super.dispatchKeyEvent(event);
        }
        if (keyCodeFilter(event)) {
            return false;
        }
        if (event != null && event.getKeyCode() == 4) {
            Log.i(TAG, "KEYCODE_BACK");
            if (event.getAction() == 1 && (iMotsServiceInterface = this.binder) != null) {
                iMotsServiceInterface.onKeyCode(1);
            }
            return false;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void closeActivity() {
        INSTANCE.getLogger().debug("closeActivity");
        finish();
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void killActivity() {
        PackageManager packageManager;
        INSTANCE.getLogger().debug("killActivity");
        finish();
        if (CheckBoardKt.isByd8155f() && isInMultiWindowMode()) {
            sendBroadcast(new Intent("com.neusoft.na.navigation.action.restart.split"));
            Log.i(TAG, "killActivity: send done");
        } else {
            Context context = MotsMainService.INSTANCE.getContext();
            Intent launchIntentForPackage = null;
            launchIntentForPackage = null;
            if (context != null && (packageManager = context.getPackageManager()) != null) {
                Context context2 = MotsMainService.INSTANCE.getContext();
                String packageName = context2 != null ? context2.getPackageName() : null;
                Intrinsics.checkNotNull(packageName);
                launchIntentForPackage = packageManager.getLaunchIntentForPackage(packageName);
            }
            if (launchIntentForPackage != null) {
                launchIntentForPackage.addFlags(67108864);
            }
            startActivity(launchIntentForPackage);
        }
        sendBroadcast(new Intent("com.neusoft.na.navigation.action.stop"));
        Object systemService = getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid != Process.myPid() && (Intrinsics.areEqual(runningAppProcessInfo.processName, this.BL) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.OC) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.CL) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.GS) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.EHP))) {
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
        Process.killProcess(Process.myPid());
    }

    private final void setGreatwallV61ActionBarBackground() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android"));
        Log.i("MainACTIVITY", "statusBarHeight == " + dimensionPixelSize);
        _$_findCachedViewById(R.id.actionBarTopView).setVisibility(0);
        ViewGroup.LayoutParams layoutParams = _$_findCachedViewById(R.id.actionBarTopView).getLayoutParams();
        layoutParams.height = dimensionPixelSize;
        _$_findCachedViewById(R.id.actionBarTopView).setLayoutParams(layoutParams);
    }

    private final void setSmartActionBarBackground() {
        Log.i(TAG, "[setSmartActionBarBackground]");
        int dimensionPixelSize = getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android"));
        Log.i(TAG, "statusBarHeight == " + dimensionPixelSize);
        _$_findCachedViewById(R.id.actionBarTopView).setBackground(null);
        _$_findCachedViewById(R.id.actionBarTopView).setBackgroundResource(com.neusoft.na.navigation.R.drawable.action_bar_background_smart);
        _$_findCachedViewById(R.id.actionBarTopView).setVisibility(0);
        ViewGroup.LayoutParams layoutParams = _$_findCachedViewById(R.id.actionBarTopView).getLayoutParams();
        layoutParams.height = dimensionPixelSize;
        _$_findCachedViewById(R.id.actionBarTopView).setLayoutParams(layoutParams);
    }

    private final void setByd8155fActionBarBackground(boolean isNight) {
        Log.i(TAG, "[setByd8155fActionBarBackground] isNight == " + isNight);
        Log.i(TAG, "statusBarHeight == " + getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android")));
        Log.i(TAG, "navigationBarHeight == " + getResources().getDimensionPixelSize(getResources().getIdentifier("navigation_bar_height", "dimen", "android")));
    }

    private final boolean isNightModeByByd() {
        Log.i(TAG, "[isNightModeByByd]");
        int i = getResources().getConfiguration().uiMode & 48;
        Log.i(TAG, "onResume systemUiMode == " + i);
        boolean z = i == 32;
        Log.i(TAG, "isNightMode == " + z);
        return z;
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void showBackDoor() {
        Log.i(TAG, "showBackDoor");
        new BackDoorDialog(this).show();
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void timeZoneChanged() throws RemoteException {
        Log.i(TAG, "timeZoneChanged");
        IMotsServiceInterface iMotsServiceInterface = this.binder;
        if (iMotsServiceInterface != null) {
            iMotsServiceInterface.timeZoneChanged();
        }
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void timeChanged() throws RemoteException {
        Log.i(TAG, "timeChanged");
        IMotsServiceInterface iMotsServiceInterface = this.binder;
        if (iMotsServiceInterface != null) {
            iMotsServiceInterface.timeZoneChanged();
        }
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void goneMask() {
        Log.i(TAG, "goneMask");
        this.isMask = false;
        maskGone();
        if (CheckBoardKt.isBydSeries()) {
            sendBydBroadcast();
        }
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void ARStatus(boolean z) {
        if (z) {
            this.mHandler.post(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.ARStatus$lambda$31(this.f$0);
                }
            });
        } else {
            this.mHandler.post(new Runnable() { // from class: com.neusoft.na.mots.MainActivity$$ExternalSyntheticLambda12
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.ARStatus$lambda$32(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ARStatus$lambda$31(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((FrameLayout) this$0._$_findCachedViewById(R.id.frame_minibar)).setVisibility(8);
        IArComponent iArComponent = this$0.arComponentWapper;
        if (iArComponent != null) {
            iArComponent.startAr();
        }
        ((TextureView) this$0._$_findCachedViewById(R.id.arDisplayArea)).setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ARStatus$lambda$32(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((FrameLayout) this$0._$_findCachedViewById(R.id.frame_minibar)).setVisibility(0);
        IArComponent iArComponent = this$0.arComponentWapper;
        if (iArComponent != null) {
            iArComponent.stopAr();
        }
        ((TextureView) this$0._$_findCachedViewById(R.id.arDisplayArea)).setVisibility(8);
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void presentationTest(int key, int value) {
        Log.i(TAG, "[presentationTest] key == " + key + ", value == " + value);
        if (key == 0) {
            Application application = getApplication();
            Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
            IMapPresentationComponent mapPresentationComponent = ((MotsApplication) application).getMapPresentationComponent();
            if (mapPresentationComponent != null) {
                mapPresentationComponent.onTestMapFocusChanged(value);
                return;
            }
            return;
        }
        if (key != 1) {
            return;
        }
        Application application2 = getApplication();
        Intrinsics.checkNotNull(application2, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
        IMapPresentationComponent mapPresentationComponent2 = ((MotsApplication) application2).getMapPresentationComponent();
        if (mapPresentationComponent2 != null) {
            mapPresentationComponent2.onTestINSNavDisplayMode(value);
        }
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void exitWithoutRestarting() {
        sendBroadcast(new Intent("com.neusoft.na.navigation.action.stop"));
        Object systemService = getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid != Process.myPid() && (Intrinsics.areEqual(runningAppProcessInfo.processName, this.BL) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.OC) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.CL) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.GS) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.EHP))) {
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
        Process.killProcess(Process.myPid());
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void appSwitchToBackground() {
        Log.i(TAG, "appSwitchToBackground");
        moveTaskToBack(true);
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void webViewPrivacyDetails() {
        Log.i(TAG, "[webViewPrivacyDetails]");
        showAlertWebView();
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void activationPrivacyPolicyStatusChanged(boolean activationStatus, boolean privacyPolicyStatus) {
        Log.i(TAG, "[activationPrivacyPolicyStatusChanged] --> activationStatus == " + activationStatus + ", privacyPolicyStatus == " + privacyPolicyStatus);
        if (this.activationStatus == activationStatus && this.privacyPolicyStatus == privacyPolicyStatus) {
            Log.i(TAG, "activationStatus and privacyPolicyStatus not changed, return");
            return;
        }
        this.activationStatus = activationStatus;
        this.privacyPolicyStatus = privacyPolicyStatus;
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
        IMapPresentationComponent mapPresentationComponent = ((MotsApplication) application).getMapPresentationComponent();
        if (mapPresentationComponent != null) {
            mapPresentationComponent.setActivationPrivacyPolicyStatus(activationStatus, privacyPolicyStatus);
        }
        if (activationStatus && privacyPolicyStatus) {
            Log.i(TAG, "already activation and privacyPolicy, can mapPresentationComponent init.");
            Application application2 = getApplication();
            Intrinsics.checkNotNull(application2, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
            IMapPresentationComponent mapPresentationComponent2 = ((MotsApplication) application2).getMapPresentationComponent();
            if (mapPresentationComponent2 != null) {
                mapPresentationComponent2.init();
                return;
            }
            return;
        }
        Log.i(TAG, "do not mapPresentationComponent init, because activation and privacyPolicy!");
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void naviHMIFullScreenPageChanged(boolean isNaviHMIFullScreenPage) {
        Log.i(TAG, "[naviHMIFullScreenPageChanged] --> isNaviHMIFullScreenPage == " + isNaviHMIFullScreenPage);
        this.naviHMIFullScreenPageFlag = isNaviHMIFullScreenPage;
        if (isNaviHMIFullScreenPage) {
            return;
        }
        Log.i(TAG, "mapMaskView color --> transparent");
        _$_findCachedViewById(R.id.mapMaskView).setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void hmiRequestFocus() {
        Log.i(TAG, "[hmiRequestFocus]");
        WebView webView = this.mWebView;
        if (webView != null) {
            webView.requestFocus();
        }
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsBroadcastCallBack
    public void closePresentation() {
        Log.i(TAG, "[closePresentation]");
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
        IMapPresentationComponent mapPresentationComponent = ((MotsApplication) application).getMapPresentationComponent();
        if (mapPresentationComponent != null) {
            mapPresentationComponent.closePresentation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startMotsMainService() {
        Log.i(TAG, "startMotsMainService");
        if (CheckBoardKt.isGreatwallV61()) {
            startService(new Intent(this, (Class<?>) MotsMainService.class));
        } else {
            startForegroundService(new Intent(this, (Class<?>) MotsMainService.class));
        }
        bindService(new Intent(this, (Class<?>) MotsMainService.class), this.serviceConnection, 1);
    }

    public final void xWalkViewShowHmi() {
        Log.i(TAG, "xWalkViewShowHmi");
        if (this.mHmiVisible) {
            return;
        }
        XWalkView xWalkView = MotsMainBinder.INSTANCE.getXWalkView();
        this.mXWalkView = xWalkView;
        if (xWalkView != null) {
            if ((xWalkView != null ? xWalkView.getParent() : null) != null) {
                XWalkView xWalkView2 = this.mXWalkView;
                ViewParent parent = xWalkView2 != null ? xWalkView2.getParent() : null;
                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
                ((ViewGroup) parent).removeView(this.mXWalkView);
            }
            ((FrameLayout) _$_findCachedViewById(R.id.hmiWebViewContainer)).addView(this.mXWalkView);
            showHmi();
        } else {
            Log.i(TAG, "mXWalkView == null");
        }
        this.mHmiVisible = true;
    }

    public final void webViewShowHmi() {
        Log.i(TAG, "webViewShowHmi");
        if (this.mHmiVisible) {
            return;
        }
        WebView webView = MotsMainBinder.INSTANCE.getWebView();
        this.mWebView = webView;
        if (webView != null) {
            if ((webView != null ? webView.getParent() : null) != null) {
                WebView webView2 = this.mWebView;
                ViewParent parent = webView2 != null ? webView2.getParent() : null;
                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
                ((ViewGroup) parent).removeView(this.mWebView);
            }
            ((FrameLayout) _$_findCachedViewById(R.id.hmiWebViewContainer)).removeAllViews();
            ((FrameLayout) _$_findCachedViewById(R.id.hmiWebViewContainer)).addView(this.mWebView);
            showHmi();
        } else {
            Log.i(TAG, "mWebView == null");
        }
        this.mHmiVisible = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendNaviActivityStartBC() {
        Log.i(TAG, "sendNaviActivityStartBC");
        Intent intent = new Intent();
        intent.setAction(START_ACTIVITY);
        sendBroadcast(intent);
    }

    private final void showMapPresentation() {
        Log.i(TAG, "[showMapPresentation]");
        if (CheckBoardKt.isByd8155f()) {
            Application application = getApplication();
            Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
            if (((MotsApplication) application).getMapPresentationComponent() == null) {
                Log.i(TAG, "create new IMapPresentationComponent");
                ClassLoader classLoader = getClass().getClassLoader();
                Intrinsics.checkNotNull(classLoader);
                IMapPresentationComponent iMapPresentationComponent = (IMapPresentationComponent) classLoader.loadClass("com.neusoft.na.byd.presentation.PresentationController").getConstructor(Context.class).newInstance(getApplication());
                Application application2 = getApplication();
                Intrinsics.checkNotNull(application2, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
                Intrinsics.checkNotNull(iMapPresentationComponent);
                ((MotsApplication) application2).setMapPresentationComponent(iMapPresentationComponent);
            } else {
                Log.i(TAG, "use old IMapPresentationComponent");
                Application application3 = getApplication();
                Intrinsics.checkNotNull(application3, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
                IMapPresentationComponent mapPresentationComponent = ((MotsApplication) application3).getMapPresentationComponent();
                SurfaceTexture surfaceTexture = mapPresentationComponent != null ? mapPresentationComponent.getSurfaceTexture() : null;
                this.presentationSurface = surfaceTexture;
                if (surfaceTexture == null) {
                    Log.i(TAG, "cluster surface == null");
                }
                Log.i(TAG, "cluster surface already exists, continue to use.");
            }
            initPresentationSystemInterfaceByByd8155F();
        }
    }

    private final void initPresentationSystemInterfaceByByd8155F() {
        Log.i(TAG, "[initPresentationSystemInterfaceByByd8155F]");
        if (CheckBoardKt.isByd8155f()) {
            if (checkSelfPermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) == 0 && checkSelfPermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON) == 0) {
                Log.i(TAG, "has BYDAUTO_INSTRUMENT_COMMON, BYDAUTO_BODYWORK_COMMON permission,do initSystemInterface in mapPresentationComponent");
                Application application = getApplication();
                Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
                IMapPresentationComponent mapPresentationComponent = ((MotsApplication) application).getMapPresentationComponent();
                if (mapPresentationComponent != null) {
                    mapPresentationComponent.initSystemInterface();
                }
                int i = getResources().getConfiguration().uiMode & 48;
                Application application2 = getApplication();
                Intrinsics.checkNotNull(application2, "null cannot be cast to non-null type com.neusoft.na.mots.MotsApplication");
                IMapPresentationComponent mapPresentationComponent2 = ((MotsApplication) application2).getMapPresentationComponent();
                if (mapPresentationComponent2 != null) {
                    mapPresentationComponent2.setDayNightModeChange(i == 32 ? 2 : 1);
                    return;
                }
                return;
            }
            if (checkSelfPermission(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON) != 0) {
                Log.i(TAG, "has no BYDAUTO_INSTRUMENT_COMMON permission, so not do initSystemInterface in mapPresentationComponent.");
            } else if (checkSelfPermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON) != 0) {
                Log.i(TAG, "has no BYDAUTO_BODYWORK_COMMON permission, so not do initSystemInterface in mapPresentationComponent.");
            }
        }
    }

    private final void showAlertWebView() {
        Log.i(TAG, "[showAlertWebView]");
        if (CheckBoardKt.isSmart()) {
            ClassLoader classLoader = getClass().getClassLoader();
            Intrinsics.checkNotNull(classLoader);
            IWebViewDialogComponent iWebViewDialogComponent = (IWebViewDialogComponent) classLoader.loadClass("com.neusoft.na.smart.WebViewDialog").getConstructor(Context.class).newInstance(this);
            this.webViewDialogComponent = iWebViewDialogComponent;
            if (iWebViewDialogComponent != null) {
                iWebViewDialogComponent.multiWindowModeChanged(isInMultiWindowMode());
            }
            IWebViewDialogComponent iWebViewDialogComponent2 = this.webViewDialogComponent;
            if (iWebViewDialogComponent2 != null) {
                iWebViewDialogComponent2.showDialog();
            }
        }
    }

    private final void setStartImageByByd8155F() {
        Log.i(TAG, "[setStartImageByByd8155F]");
        int i = getResources().getConfiguration().orientation;
        Log.i(TAG, "screenDirectionMode == " + i);
        String str = ("mask".concat(i == 2 ? "_land" : "_port") + (isNightModeByByd() ? "_night" : "_day")) + (isInMultiWindowMode() ? "_multi" : "");
        Log.i(TAG, "resourceName == " + str);
        int identifier = getResources().getIdentifier(str, AttrFactory.DRAWABLE, getPackageName());
        if (identifier > 0) {
            ((ImageView) _$_findCachedViewById(R.id.mask)).setBackgroundResource(identifier);
        } else {
            Log.e(TAG, "resource not found!");
        }
    }
}
