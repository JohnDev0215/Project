package com.neusoft.na.ocservice;

import android.app.PendingIntent;
import android.car.Car;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.Surface;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;
import androidx.core.os.ConfigurationCompat;
import cerence.ark.assistant.controller.domain.system.SystemRequestsEnum;
import cerence.ark.assistant.controller.enumeration.PhoneNumberTypeEnum;
import cerence.ark.assistant.library.JsonKey;
import com.ecarx.aiserviceapi.AIServiceCallback;
import com.ecarx.aiserviceapi.ConnectCallBack;
import com.ecarx.aiserviceapi.sdk.AIServiceAPI;
import com.neusoft.na.byd.Byd8155fSystem;
import com.neusoft.na.common.ApplicationStatus;
import com.neusoft.na.common.BasicLifeCycleController;
import com.neusoft.na.common.BydSystemAdapterCallback;
import com.neusoft.na.common.CommonSystem;
import com.neusoft.na.common.CommonSystemCallBack;
import com.neusoft.na.common.DayNightModeChangedCallback;
import com.neusoft.na.common.FileUtils;
import com.neusoft.na.common.IAppRunningStatusListener;
import com.neusoft.na.common.IBydSystemAdapterComponent;
import com.neusoft.na.common.IExtensionComponent;
import com.neusoft.na.common.ILanOnOffListener;
import com.neusoft.na.common.IOptionalComponent;
import com.neusoft.na.common.IProtonMc2Component;
import com.neusoft.na.common.IProtonX70lComponent;
import com.neusoft.na.common.IProtonlComponent;
import com.neusoft.na.common.ISeresSystemAdapterComponent;
import com.neusoft.na.common.ITTSListener;
import com.neusoft.na.common.ITimeFormatListener;
import com.neusoft.na.common.MapStatus;
import com.neusoft.na.common.NaviStatus;
import com.neusoft.na.common.ProtonCallBack;
import com.neusoft.na.common.SeresSystemAdapterCallback;
import com.neusoft.na.common.ServiceBase;
import com.neusoft.na.common.Tool;
import com.neusoft.na.common.bean.TtsBean;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import com.neusoft.na.ocservice.Broadcast.MotsServiceBroadcastCallBack;
import com.neusoft.na.ocservice.Broadcast.MotsServiceBroadcastReceiver;
import com.neusoft.na.ocservice.ctrl.ActorServer;
import com.neusoft.na.ocservice.ctrl.OnOffMasterController;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import com.neusoft.na.ocservice.util.EHPUtils;
import com.neusoft.na.ocservice.util.FileCopyUtils;
import com.neusoft.na.ocservice.util.NotificationHelper;
import com.neusoft.na.ocservice.util.SystemUtils;
import com.nuance.android.vocalizer.VocalizerAudioOutputListener;
import com.nuance.android.vocalizer.VocalizerAudioSettings;
import com.nuance.android.vocalizer.VocalizerEngine;
import com.nuance.android.vocalizer.VocalizerEngineListener;
import com.nuance.android.vocalizer.VocalizerSpeechMark;
import com.nuance.android.vocalizer.VocalizerSpeechMarkListener;
import com.nuance.android.vocalizer.VocalizerVoice;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import rx.Observable;
import rx.functions.Action1;

/* JADX INFO: compiled from: MotsService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000û\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0014\n\u0002\b\u000e*\u0007 7el\u007f\u0082\u0001\u0018\u0000 ä\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n:\u0002ä\u0001B\u0005¢\u0006\u0002\u0010\u000bJ\n\u0010\u0088\u0001\u001a\u00030\u0089\u0001H\u0002J\n\u0010\u008a\u0001\u001a\u00030\u0089\u0001H\u0002J\n\u0010\u008b\u0001\u001a\u00030\u0089\u0001H\u0002J\n\u0010\u008c\u0001\u001a\u00030\u0089\u0001H\u0016J\n\u0010\u008d\u0001\u001a\u00030\u0089\u0001H\u0002J\u0013\u0010\u008e\u0001\u001a\u00030\u0089\u00012\u0007\u0010\u008f\u0001\u001a\u00020+H\u0016J\t\u0010\u0090\u0001\u001a\u00020%H\u0002J\n\u0010\u0091\u0001\u001a\u00030\u0089\u0001H\u0002J\n\u0010\u0092\u0001\u001a\u00030\u0089\u0001H\u0002J\u0013\u0010\u0093\u0001\u001a\u00030\u0089\u00012\u0007\u0010\u0094\u0001\u001a\u00020\u0017H\u0002J\t\u0010\u0095\u0001\u001a\u00020\u0017H\u0002J\u000b\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0017H\u0002J\n\u0010\u0097\u0001\u001a\u00030\u0089\u0001H\u0016J\t\u0010\u0098\u0001\u001a\u00020%H\u0002J\u0014\u0010\u0099\u0001\u001a\u00030\u009a\u00012\b\u0010\u009b\u0001\u001a\u00030\u009a\u0001H\u0002J\u0013\u0010\u009c\u0001\u001a\u00020%2\b\u0010\u009d\u0001\u001a\u00030\u009e\u0001H\u0014J\n\u0010\u009f\u0001\u001a\u00030\u0089\u0001H\u0002J\n\u0010 \u0001\u001a\u00030\u0089\u0001H\u0003J\n\u0010¡\u0001\u001a\u00030\u0089\u0001H\u0002J\n\u0010¢\u0001\u001a\u00030\u0089\u0001H\u0002J\n\u0010£\u0001\u001a\u00030\u0089\u0001H\u0002J\n\u0010¤\u0001\u001a\u00030\u0089\u0001H\u0002J\n\u0010¥\u0001\u001a\u00030\u0089\u0001H\u0002J\u0013\u0010¦\u0001\u001a\u00030\u0089\u00012\u0007\u0010§\u0001\u001a\u00020\u0017H\u0002J\u0013\u0010¨\u0001\u001a\u00030\u0089\u00012\u0007\u0010©\u0001\u001a\u00020+H\u0002J\n\u0010ª\u0001\u001a\u00030\u009a\u0001H\u0002J\n\u0010«\u0001\u001a\u00030\u0089\u0001H\u0002J\n\u0010¬\u0001\u001a\u00030\u0089\u0001H\u0002J\"\u0010\u00ad\u0001\u001a\u00030\u0089\u00012\n\u0010®\u0001\u001a\u0005\u0018\u00010¯\u00012\n\u0010°\u0001\u001a\u0005\u0018\u00010±\u0001H\u0016J\u0015\u0010²\u0001\u001a\u00030\u0089\u00012\t\u0010³\u0001\u001a\u0004\u0018\u00010CH\u0016J\u0013\u0010´\u0001\u001a\u00030\u0089\u00012\u0007\u0010µ\u0001\u001a\u00020)H\u0016J\n\u0010¶\u0001\u001a\u00030\u0089\u0001H\u0016J\n\u0010·\u0001\u001a\u00030\u0089\u0001H\u0016J&\u0010¸\u0001\u001a\u00020%2\t\u0010³\u0001\u001a\u0004\u0018\u00010C2\u0007\u0010¹\u0001\u001a\u00020+2\u0007\u0010º\u0001\u001a\u00020+H\u0016J\u0013\u0010»\u0001\u001a\u00030\u0089\u00012\u0007\u0010¼\u0001\u001a\u00020+H\u0016J\u0015\u0010½\u0001\u001a\u00030\u0089\u00012\t\u0010®\u0001\u001a\u0004\u0018\u00010\u0017H\u0016J\u0015\u0010¾\u0001\u001a\u00030\u0089\u00012\t\u0010®\u0001\u001a\u0004\u0018\u00010\u0017H\u0016J\u0016\u0010¿\u0001\u001a\u00030\u0089\u00012\n\u0010®\u0001\u001a\u0005\u0018\u00010À\u0001H\u0016J\n\u0010Á\u0001\u001a\u00030\u0089\u0001H\u0014J'\u0010Â\u0001\u001a\u00020+2\n\u0010Ã\u0001\u001a\u0005\u0018\u00010Ä\u00012\u0007\u0010Å\u0001\u001a\u00020+2\u0007\u0010Æ\u0001\u001a\u00020+H\u0017J\u0013\u0010Ç\u0001\u001a\u00030\u0089\u00012\u0007\u0010®\u0001\u001a\u00020+H\u0016J\n\u0010È\u0001\u001a\u00030\u0089\u0001H\u0016J\n\u0010É\u0001\u001a\u00030\u0089\u0001H\u0002J\n\u0010Ê\u0001\u001a\u00030\u0089\u0001H\u0002J\u0013\u0010Ë\u0001\u001a\u00030\u0089\u00012\u0007\u0010Ì\u0001\u001a\u00020\u0017H\u0002J\n\u0010Í\u0001\u001a\u00030\u0089\u0001H\u0002J\t\u0010Î\u0001\u001a\u00020%H\u0002J\t\u0010Ï\u0001\u001a\u00020%H\u0002J\n\u0010Ð\u0001\u001a\u00030\u0089\u0001H\u0002J\b\u0010Ñ\u0001\u001a\u00030\u0089\u0001J\b\u0010Ò\u0001\u001a\u00030\u0089\u0001J\u0013\u0010Ó\u0001\u001a\u00030\u0089\u00012\u0007\u0010Ô\u0001\u001a\u00020+H\u0002J\u0014\u0010Õ\u0001\u001a\u00030\u0089\u00012\b\u0010Ö\u0001\u001a\u00030×\u0001H\u0002J\u0013\u0010Ø\u0001\u001a\u00030\u0089\u00012\u0007\u0010Ù\u0001\u001a\u00020+H\u0002J\b\u0010Ú\u0001\u001a\u00030\u0089\u0001J\u0011\u0010Û\u0001\u001a\u00030\u0089\u00012\u0007\u0010Ü\u0001\u001a\u00020+J\u0011\u0010Ý\u0001\u001a\u00030\u0089\u00012\u0007\u0010Þ\u0001\u001a\u00020\u0017J\n\u0010ß\u0001\u001a\u00030\u0089\u0001H\u0002J\t\u0010à\u0001\u001a\u00020%H\u0002J\n\u0010á\u0001\u001a\u00030\u0089\u0001H\u0002J\n\u0010â\u0001\u001a\u00030\u0089\u0001H\u0002J\n\u0010ã\u0001\u001a\u00030\u0089\u0001H\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u0010\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0004\n\u0002\u00108R\u000e\u00109\u001a\u00020:X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020<X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010=\u001a\u0004\u0018\u00010<X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u0004\u0018\u00010AX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010B\u001a\u0004\u0018\u00010CX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010F\u001a\u0004\u0018\u00010GX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020<X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020JX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020<X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020<X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010M\u001a\u0004\u0018\u00010NX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u0004\u0018\u00010PX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010V\u001a\u0004\u0018\u00010WX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\u000e\u0010\\\u001a\u00020]X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010^\u001a\u0004\u0018\u00010_X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010`\u001a\u0004\u0018\u00010aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010b\u001a\u0004\u0018\u00010cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010d\u001a\u00020eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010fR\u0010\u0010g\u001a\u0004\u0018\u00010hX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010k\u001a\u00020lX\u0082\u0004¢\u0006\u0004\n\u0002\u0010mR\u000e\u0010n\u001a\u00020\u0017X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010o\u001a\u00020p8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bs\u0010t\u001a\u0004\bq\u0010rR\u000e\u0010u\u001a\u00020\u0017X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010v\u001a\u0004\u0018\u00010wX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u00020yX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010z\u001a\u0004\u0018\u00010{X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010|\u001a\b\u0012\u0004\u0012\u00020W0}X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010~\u001a\u00020\u007fX\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u0080\u0001R\u0013\u0010\u0081\u0001\u001a\u00030\u0082\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u0083\u0001R\u000f\u0010\u0084\u0001\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0085\u0001\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0087\u0001\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006å\u0001"}, d2 = {"Lcom/neusoft/na/ocservice/MotsService;", "Lcom/neusoft/na/common/ServiceBase;", "Lcom/neusoft/na/ocservice/Broadcast/MotsServiceBroadcastCallBack;", "Lcom/neusoft/na/common/CommonSystemCallBack;", "Landroid/speech/tts/TextToSpeech$OnInitListener;", "Lcom/nuance/android/vocalizer/VocalizerEngineListener;", "Lcom/nuance/android/vocalizer/VocalizerSpeechMarkListener;", "Lcom/nuance/android/vocalizer/VocalizerAudioOutputListener;", "Lcom/neusoft/na/common/DayNightModeChangedCallback;", "Landroid/media/MediaPlayer$OnCompletionListener;", "Landroid/media/MediaPlayer$OnErrorListener;", "()V", "MapStatus", "Lcom/neusoft/na/common/MapStatus;", "actorServer", "Lcom/neusoft/na/ocservice/ctrl/ActorServer;", "am", "Landroid/media/AudioManager;", "audioAttributes", "Landroid/media/AudioAttributes;", "audioFocusRequest", "Landroid/media/AudioFocusRequest;", "beepWavName", "", "getBeepWavName", "()Ljava/lang/String;", "setBeepWavName", "(Ljava/lang/String;)V", "beepWavPath", "getBeepWavPath", "setBeepWavPath", "broadcastReceiver", "com/neusoft/na/ocservice/MotsService$broadcastReceiver$1", "Lcom/neusoft/na/ocservice/MotsService$broadcastReceiver$1;", "clusterSurface", "Landroid/view/Surface;", "clusterSurfaceStatus", "", "commonSystem", "Lcom/neusoft/na/common/CommonSystem;", Car.CAR_CONFIGURATION_SERVICE, "Landroid/content/res/Configuration;", "durationHint", "", "handler", "Landroid/os/Handler;", "hasAudioFocus", "iBydSystemAdapterComponent", "Lcom/neusoft/na/common/IBydSystemAdapterComponent;", "iSeresSystemAdapterComponent", "Lcom/neusoft/na/common/ISeresSystemAdapterComponent;", "intentFilter", "Landroid/content/IntentFilter;", "isConnectSmartTTS", "lanOnOffListener", "com/neusoft/na/ocservice/MotsService$lanOnOffListener$1", "Lcom/neusoft/na/ocservice/MotsService$lanOnOffListener$1;", "mAudioFocusListener", "Landroid/media/AudioManager$OnAudioFocusChangeListener;", "mDbuLifeCycleController", "Lcom/neusoft/na/common/BasicLifeCycleController;", "mEhpLifeCycleController", "mExtension", "Lcom/neusoft/na/common/IExtensionComponent;", "mMc2Component", "Lcom/neusoft/na/common/IProtonMc2Component;", "mMediaPlayer", "Landroid/media/MediaPlayer;", "mMotsBl", "Lcom/neusoft/na/ocservice/MotsBl;", "mMotsServiceBroadcastReceiver", "Lcom/neusoft/na/ocservice/Broadcast/MotsServiceBroadcastReceiver;", "mOneCloudClientLifeCycleController", "mOneCoreLifeCycleController", "Lcom/neusoft/na/ocservice/OneCoreLifeCycleController;", "mPosLifeCycleController", "mSensLifeCycleController", "mWSSystem", "Lcom/neusoft/na/common/IOptionalComponent;", "mainMessenger", "Landroid/os/Messenger;", OneCoreService.PAYLOAD_MAP_STATUS, "mapSurfaceStatus", "mobileNetworkConnection", "musicType", "netStateCacheValue", "nowTtsBean", "Lcom/neusoft/na/common/bean/TtsBean;", "getNowTtsBean", "()Lcom/neusoft/na/common/bean/TtsBean;", "setNowTtsBean", "(Lcom/neusoft/na/common/bean/TtsBean;)V", "onOffMasterImpl", "Lcom/neusoft/na/ocservice/ctrl/OnOffMasterController;", "protonCallBack", "Lcom/neusoft/na/common/ProtonCallBack;", "protonSystem", "Lcom/neusoft/na/common/IProtonlComponent;", "protonx70System", "Lcom/neusoft/na/common/IProtonX70lComponent;", "runningStatusListener", "com/neusoft/na/ocservice/MotsService$runningStatusListener$1", "Lcom/neusoft/na/ocservice/MotsService$runningStatusListener$1;", "smartTTSVoice", "Lcom/ecarx/aiserviceapi/sdk/AIServiceAPI;", "soundType", "switchLanOnOff", "timeFormatListener", "com/neusoft/na/ocservice/MotsService$timeFormatListener$1", "Lcom/neusoft/na/ocservice/MotsService$timeFormatListener$1;", "trafficPrivateTempPath", "trafficPublicTempPath", "Ljava/io/File;", "getTrafficPublicTempPath", "()Ljava/io/File;", "trafficPublicTempPath$delegate", "Lkotlin/Lazy;", "trafficTempPath", "tts", "Landroid/speech/tts/TextToSpeech;", "ttsBundle", "Landroid/os/Bundle;", "ttsEngine", "Lcom/nuance/android/vocalizer/VocalizerEngine;", "ttsList", "Ljava/util/ArrayList;", "ttsListener", "com/neusoft/na/ocservice/MotsService$ttsListener$1", "Lcom/neusoft/na/ocservice/MotsService$ttsListener$1;", "utteranceProgressListener", "com/neusoft/na/ocservice/MotsService$utteranceProgressListener$1", "Lcom/neusoft/na/ocservice/MotsService$utteranceProgressListener$1;", "widgetSurfaceStatus", "wifiNetworkConnection", "zeekrBeep02MonoAudioFocusRequest", "zeekrTtsSpeaking", "E01TTSInit", "", "X70TTSInit", "abandonAudioFocus", "applyFinish", "changAnTTSInit", "changed", ServiceBase.PAYLOAD_MODE, "checkBeepAudioUseMediaPlayer", "copyBeepWav", "cpBasicDbFiles", "deleteBasicDbAssetsFiles", "sourceBasicDbPath", "getBeepString", "getDbPath", "getMaskStatus", "getNavigationFlowSwitchState", "greatWallV61LanguageLocale", "Ljava/util/Locale;", "locale", "handleIncomingMessage", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "init", "initByd8155fTTS", "initSeresTTS", "initSmartTTS", "initTTSLanguage", "initVocalizerEngineTTS", "initZeekrTTS", "loadEngineTTSVoice", "name", "loadSelectedVoice", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "loadSystemLanguage", "mc2TTSInit", "ocStartError", "onAudioData", "p0", "Lcom/nuance/android/vocalizer/VocalizerAudioSettings;", "p1", "", "onCompletion", "mp", "onConfigurationChanged", "newConfig", "onCreate", "onDestroy", "onError", "what", JsonKey.NOTIFICATION_EXTRA, "onInit", "status", "onSpeakElementCompleted", "onSpeakElementStarted", "onSpeechMarkReceived", "Lcom/nuance/android/vocalizer/VocalizerSpeechMark;", "onStart", "onStartCommand", "intent", "Landroid/content/Intent;", "flags", "startId", "onStateChanged", "onVoiceListChanged", "otherTTSInit", "playBeep02MonoSound", "playSound", "fileName", "registerMotsBroadcastReceiver", SystemRequestsEnum.REQUEST_AUDIO_FOCUS, "requestZeekrBeep02MonoAudioFocus", "sendA01B01Beep", "sendClusterMaskGone", "sendMaskGone", "sendNetState2Common", "netStateValue", "sendSetCurrentLocation", OneCoreService.PAYLOAD_CURRENT_LOCATION, "", "sendTtsPlayStateBroadcast", "playState", "sendWidgetMaskGone", "soundTypeChanged", "type", "speak", "ttsmsg", "startBL", "startMotsBl", "startOc", "stopTTS", "ttsPlayOnceEnd", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MotsService extends ServiceBase implements MotsServiceBroadcastCallBack, CommonSystemCallBack, TextToSpeech.OnInitListener, VocalizerEngineListener, VocalizerSpeechMarkListener, VocalizerAudioOutputListener, DayNightModeChangedCallback, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    public static final String ACTION_BRAKING_CHANGED = "com.neusoft.na.mots.brakingStateChanged";
    public static final String ACTION_CAR_SPEED_CHANGED = "com.neusoft.na.navigation.car.speed.changed";
    private static final String ACTION_KEY_TTS_PLAY_STATE = "ttsPlayState";
    public static final String ACTION_LANGUAGE_CHANGED = "com.neusoft.action.language.changed";
    public static final String ACTION_NETWORK_CHANGED = "com.byd.tcp.cloud.server.status";
    private static final String ACTION_NEUSOFT_NAVIGATION_PLAY_STATE = "com.neusoft.navigation.tts.play.state";
    public static final String ACTION_SET_CLUSTER_SURFACE = "com.neusoft.na.navigation.set.cluster.surface";
    public static final String ACTION_SOUND_TYPE_CHANGED = "com.neusoft.na.mots.soundTypeChanged";
    public static final String ACTION_TEST_LANGUAGE_CHANGED = "com.neusoft.na.navigation.action.test.language.change";
    public static final String APP_SWITCH_TO_BACKGROUND = "com.neusoft.na.mots.appSwitchToBackground";
    public static final String BYD = "byd";
    public static final String BYD8155F = "byd8155f";
    public static final String BYD_8155F_SYSTEM_CLASSNAME = "com.neusoft.na.byd.Byd8155fSystem";
    public static final String BYD_SYSTEM_CLASSNAME = "com.neusoft.na.byd.BydSystem";
    public static final int BYD_TTS_STREAM_TYPE = 14;
    public static final String CHANGAN = "changan";
    public static final String CHANGANSYSTEM_CLASSNAME = "com.neusoft.na.changan.ChanganSystem";
    public static final String CLUSTER_GONE_MASK = "com.neusoft.na.mots.cluster.mask.gone";
    public static final String ECARXSYSTEM_CLASSNAME = "com.neusoft.na.ecarx.EcarxSystem";
    public static final String EXIT_COMPLETE_ACTIVITY = "com.neusoft.na.mots.closeAndKillMainActivity";
    public static final String EXIT_MAIN_ACTIVITY = "com.neusoft.na.mots.closeMainActivity";
    public static final String EXIT_WITHOUT_RESTARTING = "com.neusoft.na.mots.exitWithoutRestarting";
    public static final String GONE_MASK = "com.neusoft.na.mots.mask.gone";
    public static final String GREATWALLA01 = "greatwalla01";
    public static final String GREATWALLA01_CLASSNAME = "com.neusoft.na.greatwalla01.GreatWallA01System";
    public static final String GREATWALLB01 = "greatwallb01";
    public static final String GREATWALLB01_CLASSNAME = "com.neusoft.na.greatwallb01.GreatWallB01System";
    public static final String GREATWALLP3 = "greatwallp3";
    public static final String GREATWALLP3_CLASSNAME = "com.neusoft.na.greatwallp3.GreatWallP3System";
    public static final String GREATWALLV61 = "greatwallv61";
    public static final String GREATWALLV61_CLASSNAME = "com.neusoft.na.greatwallv61.GreatWallV61System";
    public static final String MOBILE = "mobile";
    public static final int NAVI_TTS = 10;
    public static final String PROTON = "proton";
    public static final String PROTONMC2 = "protonmc2";
    public static final String PROTONMC2SYSTEM_CLASSNAME = "com.neusoft.na.protonmc2.ProtonMC2System";
    public static final String PROTONX70 = "protonx70";
    public static final String PROTONX70_THAI = "protonx70Thai";
    public static final String PROTON_X11_MOBILE_SYSTEM_CLASSNAME = "com.neusoft.protonx11mobile.ProtonX11MobileSystem";
    public static final String SERES = "seres";
    public static final String SERES_SYSTEM_CLASSNAME = "com.neusoft.na.seres.SeresSystem";
    public static final String SET_CURRENT_LOCATION = "com.neusoft.na.mots.setlocation";
    public static final String SHOW_BACK_DOOR = "com.neusoft.na.mots.showBackDoor";
    public static final String SMART = "smart";
    public static final String SMART_SYSTEM_CLASSNAME = "com.neusoft.na.smart.SmartSystem";
    public static final String STANDARD_CLASSNAME = "com.neusoft.na.standard.StandardSystem";
    public static final String TAG = "XJMotsService";
    public static final String WEYFORPRODUCTV3 = "weyforproductv3";
    public static final String WEYFORPRODUCTV3_CLASSNAME = "com.neusoft.weyforproductv3.Weyforproductv3System";
    public static final String WIDGET_GONE_MASK = "com.neusoft.na.mots.widget.mask.gone";
    public static final int X70NAVI_TTS = 12;
    public static final String X70SYSTEM_CLASSNAME = "com.neusoft.na.protonx70.X70System";
    public static final String ZEEKRBX1E = "zeekrbx1e";
    public static final String ZEEKRBX1E_SYSTEM_CLASSNAME = "com.neusoft.na.zeekrbx1e.ZeekrSystem";
    public static final String ZEEKRDC1E = "zeekrdc1e";
    public static final String ZEEKRDC1E_SYSTEM_CLASSNAME = "com.neusoft.na.zeekrdc1e.ZeekrSystem";
    private static boolean isIVI;
    private static int utteranceId;
    private MapStatus MapStatus;
    private final ActorServer actorServer;
    private AudioManager am;
    private AudioAttributes audioAttributes;
    private AudioFocusRequest audioFocusRequest;
    private String beepWavName;
    private String beepWavPath;
    private final MotsService$broadcastReceiver$1 broadcastReceiver;
    private Surface clusterSurface;
    private boolean clusterSurfaceStatus;
    private CommonSystem commonSystem;
    private Configuration configuration;
    private int durationHint;
    private final Handler handler;
    private boolean hasAudioFocus;
    private IBydSystemAdapterComponent iBydSystemAdapterComponent;
    private ISeresSystemAdapterComponent iSeresSystemAdapterComponent;
    private IntentFilter intentFilter;
    private boolean isConnectSmartTTS;
    private final MotsService$lanOnOffListener$1 lanOnOffListener;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusListener;
    private final BasicLifeCycleController mDbuLifeCycleController;
    private BasicLifeCycleController mEhpLifeCycleController;
    private IExtensionComponent mExtension;
    private IProtonMc2Component mMc2Component;
    private MediaPlayer mMediaPlayer;
    private MotsBl mMotsBl;
    private MotsServiceBroadcastReceiver mMotsServiceBroadcastReceiver;
    private final BasicLifeCycleController mOneCloudClientLifeCycleController;
    private final OneCoreLifeCycleController mOneCoreLifeCycleController;
    private BasicLifeCycleController mPosLifeCycleController;
    private final BasicLifeCycleController mSensLifeCycleController;
    private IOptionalComponent mWSSystem;
    private Messenger mainMessenger;
    private int mapStatus;
    private boolean mapSurfaceStatus;
    private boolean mobileNetworkConnection;
    private int musicType;
    private int netStateCacheValue;
    private TtsBean nowTtsBean;
    private final OnOffMasterController onOffMasterImpl;
    private ProtonCallBack protonCallBack;
    private IProtonlComponent protonSystem;
    private IProtonX70lComponent protonx70System;
    private MotsService$runningStatusListener$1 runningStatusListener;
    private AIServiceAPI smartTTSVoice;
    private int soundType;
    private int switchLanOnOff;
    private final MotsService$timeFormatListener$1 timeFormatListener;
    private final String trafficPrivateTempPath;

    /* JADX INFO: renamed from: trafficPublicTempPath$delegate, reason: from kotlin metadata */
    private final Lazy trafficPublicTempPath;
    private final String trafficTempPath;
    private TextToSpeech tts;
    private Bundle ttsBundle;
    private VocalizerEngine ttsEngine;
    private ArrayList<TtsBean> ttsList;
    private final MotsService$ttsListener$1 ttsListener;
    private final MotsService$utteranceProgressListener$1 utteranceProgressListener;
    private boolean widgetSurfaceStatus;
    private boolean wifiNetworkConnection;
    private AudioFocusRequest zeekrBeep02MonoAudioFocusRequest;
    private boolean zeekrTtsSpeaking;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static String IVIName = "";

    /* JADX INFO: renamed from: com.neusoft.na.ocservice.MotsService$onCreate$25, reason: invalid class name */
    /* JADX INFO: compiled from: MotsService.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass25 extends Lambda implements Function0<Object> {
        public static final AnonymousClass25 INSTANCE = new AnonymousClass25();

        AnonymousClass25() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return "instance does not implement IOptionalComponent";
        }
    }

    /* JADX INFO: renamed from: com.neusoft.na.ocservice.MotsService$onCreate$26, reason: invalid class name */
    /* JADX INFO: compiled from: MotsService.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass26 extends Lambda implements Function0<Object> {
        public static final AnonymousClass26 INSTANCE = new AnonymousClass26();

        AnonymousClass26() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return "instance does not implement IProtonlComponent";
        }
    }

    /* JADX INFO: renamed from: com.neusoft.na.ocservice.MotsService$onCreate$27, reason: invalid class name */
    /* JADX INFO: compiled from: MotsService.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass27 extends Lambda implements Function0<Object> {
        public static final AnonymousClass27 INSTANCE = new AnonymousClass27();

        AnonymousClass27() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return "failed to create instance";
        }
    }

    /* JADX INFO: renamed from: com.neusoft.na.ocservice.MotsService$onCreate$28, reason: invalid class name */
    /* JADX INFO: compiled from: MotsService.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass28 extends Lambda implements Function0<Object> {
        public static final AnonymousClass28 INSTANCE = new AnonymousClass28();

        AnonymousClass28() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return "failed to find class 'com.neusoft.na.protonx70.X70System'";
        }
    }

    /* JADX INFO: renamed from: com.neusoft.na.ocservice.MotsService$onCreate$29, reason: invalid class name */
    /* JADX INFO: compiled from: MotsService.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    static final class AnonymousClass29 extends Lambda implements Function0<Object> {
        public static final AnonymousClass29 INSTANCE = new AnonymousClass29();

        AnonymousClass29() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return "failed to load class 'com.neusoft.na.protonx70.X70System'";
        }
    }

    @Override // com.nuance.android.vocalizer.VocalizerAudioOutputListener
    public void onAudioData(VocalizerAudioSettings p0, short[] p1) {
    }

    @Override // com.nuance.android.vocalizer.VocalizerSpeechMarkListener
    public void onSpeechMarkReceived(VocalizerSpeechMark p0) {
    }

    @Override // com.nuance.android.vocalizer.VocalizerEngineListener
    public void onVoiceListChanged() {
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [com.neusoft.na.ocservice.MotsService$broadcastReceiver$1] */
    /* JADX WARN: Type inference failed for: r2v16, types: [com.neusoft.na.ocservice.MotsService$ttsListener$1] */
    /* JADX WARN: Type inference failed for: r2v17, types: [com.neusoft.na.ocservice.MotsService$timeFormatListener$1] */
    /* JADX WARN: Type inference failed for: r2v18, types: [com.neusoft.na.ocservice.MotsService$lanOnOffListener$1] */
    /* JADX WARN: Type inference failed for: r2v19, types: [com.neusoft.na.ocservice.MotsService$utteranceProgressListener$1] */
    /* JADX WARN: Type inference failed for: r2v8, types: [com.neusoft.na.ocservice.MotsService$runningStatusListener$1] */
    public MotsService() {
        super("MotsService");
        this.beepWavPath = "/mapdata/neusoft/";
        this.beepWavName = "PON_3-50.wav";
        MotsService motsService = this;
        this.mDbuLifeCycleController = new BasicLifeCycleController(motsService, DbuService.class);
        this.mPosLifeCycleController = new BasicLifeCycleController(motsService, PosService.class);
        this.mSensLifeCycleController = new BasicLifeCycleController(motsService, SensService.class);
        OneCoreLifeCycleController oneCoreLifeCycleController = new OneCoreLifeCycleController(motsService, null, 2, 0 == true ? 1 : 0);
        this.mOneCoreLifeCycleController = oneCoreLifeCycleController;
        this.mOneCloudClientLifeCycleController = new BasicLifeCycleController(motsService, TelematicsService.class);
        this.musicType = 3;
        this.durationHint = 3;
        this.handler = new Handler();
        this.mapStatus = 11;
        this.runningStatusListener = new IAppRunningStatusListener() { // from class: com.neusoft.na.ocservice.MotsService$runningStatusListener$1
            @Override // com.neusoft.na.common.IAppRunningStatusListener
            public void navAppOperable() {
                Log.i(MotsService.TAG, "navAppOperable");
                ServiceBase.INSTANCE.getLogger().debug("navAppOperable");
                this.this$0.sendMaskGone();
                this.this$0.mOneCoreLifeCycleController.addNmeaListener();
            }

            @Override // com.neusoft.na.common.IAppRunningStatusListener
            public void slaveAppOperable() {
                Log.i(MotsService.TAG, "slaveAppOperable");
                ServiceBase.INSTANCE.getLogger().debug("slaveAppOperable");
                this.this$0.sendWidgetMaskGone();
            }

            @Override // com.neusoft.na.common.IAppRunningStatusListener
            public void clusterOperable() {
                Log.i(MotsService.TAG, "[clusterOperable]");
                ServiceBase.INSTANCE.getLogger().debug("clusterOperable");
                this.this$0.sendClusterMaskGone();
            }

            @Override // com.neusoft.na.common.IAppRunningStatusListener
            public void other() {
                Log.i(MotsService.TAG, "other");
                ServiceBase.INSTANCE.getLogger().debug("other");
            }
        };
        this.ttsBundle = new Bundle();
        this.trafficTempPath = "/local/traffic";
        this.trafficPrivateTempPath = "/home/local/traffic";
        this.trafficPublicTempPath = LazyKt.lazy(new Function0<File>() { // from class: com.neusoft.na.ocservice.MotsService$trafficPublicTempPath$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return new File(this.this$0.getFilesDir(), "__org.liquidplayer.node__/_bl" + this.this$0.trafficTempPath);
            }
        });
        this.switchLanOnOff = 2;
        this.ttsListener = new ITTSListener() { // from class: com.neusoft.na.ocservice.MotsService$ttsListener$1
            @Override // com.neusoft.na.common.ITTSListener
            public void onDone() {
                Log.i(MotsService.TAG, "ttsonDone");
                this.this$0.setNowTtsBean(new TtsBean());
                if (this.this$0.ttsList.size() > 0) {
                    this.this$0.ttsList.remove(0);
                }
                if (this.this$0.ttsList.size() <= 0) {
                    this.this$0.abandonAudioFocus();
                    return;
                }
                MotsService motsService2 = this.this$0;
                motsService2.setNowTtsBean((TtsBean) motsService2.ttsList.get(0));
                MotsService motsService3 = this.this$0;
                TtsBean nowTtsBean = motsService3.getNowTtsBean();
                Intrinsics.checkNotNull(nowTtsBean);
                motsService3.speak(nowTtsBean.getMsg());
            }
        };
        this.timeFormatListener = new ITimeFormatListener() { // from class: com.neusoft.na.ocservice.MotsService$timeFormatListener$1
            @Override // com.neusoft.na.common.ITimeFormatListener
            public void onChange(boolean is24Hour) {
                Log.i(MotsService.TAG, "TimeFormatChanged [" + is24Hour + ']');
                CommonSystem commonSystem = this.this$0.commonSystem;
                if (commonSystem != null) {
                    commonSystem.setSystemTimeFormatChanged(is24Hour);
                }
            }
        };
        this.lanOnOffListener = new ILanOnOffListener() { // from class: com.neusoft.na.ocservice.MotsService$lanOnOffListener$1
            @Override // com.neusoft.na.common.ILanOnOffListener
            public void onChange(int type) throws ClassNotFoundException {
                Locale locale;
                Log.i(MotsService.TAG, "LanguageOnOffChanged [" + type + ']');
                this.this$0.switchLanOnOff = type;
                Locale locale2 = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
                if (this.this$0.switchLanOnOff != 1) {
                    if (this.this$0.switchLanOnOff == 3) {
                        locale = new Locale("zh", "CN", locale2.getVariant());
                        locale2 = locale;
                    } else {
                        Class<?> cls = Class.forName("android.os.SystemProperties");
                        locale2 = new Locale(Intrinsics.areEqual(cls.getDeclaredMethod("get", String.class).invoke(cls, "persist.bean.country.code").toString(), "13") ? "th" : "en", locale2.getCountry(), locale2.getVariant());
                    }
                } else if (!locale2.getLanguage().equals("th")) {
                    locale = new Locale("en", "US", locale2.getVariant());
                    locale2 = locale;
                }
                CommonSystem commonSystem = this.this$0.commonSystem;
                if (commonSystem != null) {
                    Intrinsics.checkNotNullExpressionValue(locale2, "locale");
                    commonSystem.setSystemLocale(locale2);
                }
                Log.i(MotsService.TAG, "LanguageOnOffChanged setLanguage [" + locale2 + ']');
            }
        };
        this.utteranceProgressListener = new UtteranceProgressListener() { // from class: com.neusoft.na.ocservice.MotsService$utteranceProgressListener$1
            @Override // android.speech.tts.UtteranceProgressListener
            public void onDone(String utteranceId2) {
                Log.i(MotsService.TAG, "ttsonDone");
                this.this$0.zeekrTtsSpeaking = false;
                this.this$0.setNowTtsBean(new TtsBean());
                if (this.this$0.ttsList.size() > 0) {
                    this.this$0.ttsList.remove(0);
                }
                if (this.this$0.ttsList.size() <= 0) {
                    this.this$0.abandonAudioFocus();
                    return;
                }
                MotsService motsService2 = this.this$0;
                motsService2.setNowTtsBean((TtsBean) motsService2.ttsList.get(0));
                MotsService motsService3 = this.this$0;
                TtsBean nowTtsBean = motsService3.getNowTtsBean();
                Intrinsics.checkNotNull(nowTtsBean);
                motsService3.speak(nowTtsBean.getMsg());
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onError(String utteranceId2) {
                Log.i(MotsService.TAG, "ttsonError");
                this.this$0.zeekrTtsSpeaking = false;
                this.this$0.setNowTtsBean(new TtsBean());
                if (this.this$0.ttsList.size() > 0) {
                    this.this$0.ttsList.remove(0);
                }
                if (this.this$0.ttsList.size() > 0) {
                    MotsService motsService2 = this.this$0;
                    motsService2.setNowTtsBean((TtsBean) motsService2.ttsList.get(0));
                    MotsService motsService3 = this.this$0;
                    TtsBean nowTtsBean = motsService3.getNowTtsBean();
                    Intrinsics.checkNotNull(nowTtsBean);
                    motsService3.speak(nowTtsBean.getMsg());
                }
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onStart(String utteranceId2) {
                Log.i(MotsService.TAG, "ttsonStart");
                this.this$0.zeekrTtsSpeaking = true;
            }
        };
        this.MapStatus = MapStatus.INSTANCE;
        this.ttsList = new ArrayList<>();
        this.nowTtsBean = new TtsBean();
        ActorServer actorServer = new ActorServer(motsService);
        this.actorServer = actorServer;
        this.onOffMasterImpl = new OnOffMasterController(actorServer, MapsKt.mapOf(TuplesKt.to("na_onecore", oneCoreLifeCycleController)));
        this.netStateCacheValue = -1;
        this.broadcastReceiver = new BroadcastReceiver() { // from class: com.neusoft.na.ocservice.MotsService$broadcastReceiver$1
            /* JADX WARN: Removed duplicated region for block: B:223:0x03cb A[PHI: r9
  0x03cb: PHI (r9v4 java.lang.String) = 
  (r9v3 java.lang.String)
  (r9v5 java.lang.String)
  (r9v6 java.lang.String)
  (r9v7 java.lang.String)
  (r9v8 java.lang.String)
  (r9v9 java.lang.String)
  (r9v10 java.lang.String)
  (r9v11 java.lang.String)
  (r9v12 java.lang.String)
 binds: [B:221:0x03c8, B:218:0x03bf, B:212:0x03af, B:209:0x03a6, B:206:0x039d, B:203:0x0394, B:200:0x038b, B:197:0x0382, B:194:0x0379] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:224:0x03cd  */
            /* JADX WARN: Removed duplicated region for block: B:227:0x03f7  */
            /* JADX WARN: Removed duplicated region for block: B:230:0x0408  */
            /* JADX WARN: Removed duplicated region for block: B:231:0x0419  */
            @Override // android.content.BroadcastReceiver
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onReceive(android.content.Context r17, android.content.Intent r18) {
                /*
                    Method dump skipped, instruction units count: 1116
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.MotsService$broadcastReceiver$1.onReceive(android.content.Context, android.content.Intent):void");
            }
        };
        this.mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda19
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i) {
                MotsService.mAudioFocusListener$lambda$39(this.f$0, i);
            }
        };
    }

    /* JADX INFO: compiled from: MotsService.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b/\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0013X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0013X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010B\u001a\u00020CX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010D\"\u0004\bE\u0010FR\u001a\u0010G\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010K¨\u0006L"}, d2 = {"Lcom/neusoft/na/ocservice/MotsService$Companion;", "", "()V", "ACTION_BRAKING_CHANGED", "", "ACTION_CAR_SPEED_CHANGED", "ACTION_KEY_TTS_PLAY_STATE", "ACTION_LANGUAGE_CHANGED", "ACTION_NETWORK_CHANGED", "ACTION_NEUSOFT_NAVIGATION_PLAY_STATE", "ACTION_SET_CLUSTER_SURFACE", "ACTION_SOUND_TYPE_CHANGED", "ACTION_TEST_LANGUAGE_CHANGED", "APP_SWITCH_TO_BACKGROUND", "BYD", "BYD8155F", "BYD_8155F_SYSTEM_CLASSNAME", "BYD_SYSTEM_CLASSNAME", "BYD_TTS_STREAM_TYPE", "", "CHANGAN", "CHANGANSYSTEM_CLASSNAME", "CLUSTER_GONE_MASK", "ECARXSYSTEM_CLASSNAME", "EXIT_COMPLETE_ACTIVITY", "EXIT_MAIN_ACTIVITY", "EXIT_WITHOUT_RESTARTING", "GONE_MASK", "GREATWALLA01", "GREATWALLA01_CLASSNAME", "GREATWALLB01", "GREATWALLB01_CLASSNAME", "GREATWALLP3", "GREATWALLP3_CLASSNAME", "GREATWALLV61", "GREATWALLV61_CLASSNAME", "IVIName", "getIVIName", "()Ljava/lang/String;", "setIVIName", "(Ljava/lang/String;)V", PhoneNumberTypeEnum.MOBILE, "NAVI_TTS", "PROTON", "PROTONMC2", "PROTONMC2SYSTEM_CLASSNAME", "PROTONX70", "PROTONX70_THAI", "PROTON_X11_MOBILE_SYSTEM_CLASSNAME", "SERES", "SERES_SYSTEM_CLASSNAME", "SET_CURRENT_LOCATION", "SHOW_BACK_DOOR", "SMART", "SMART_SYSTEM_CLASSNAME", "STANDARD_CLASSNAME", "TAG", "WEYFORPRODUCTV3", "WEYFORPRODUCTV3_CLASSNAME", "WIDGET_GONE_MASK", "X70NAVI_TTS", "X70SYSTEM_CLASSNAME", "ZEEKRBX1E", "ZEEKRBX1E_SYSTEM_CLASSNAME", "ZEEKRDC1E", "ZEEKRDC1E_SYSTEM_CLASSNAME", "isIVI", "", "()Z", "setIVI", "(Z)V", "utteranceId", "getUtteranceId", "()I", "setUtteranceId", "(I)V", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getUtteranceId() {
            return MotsService.utteranceId;
        }

        public final void setUtteranceId(int i) {
            MotsService.utteranceId = i;
        }

        public final boolean isIVI() {
            return MotsService.isIVI;
        }

        public final void setIVI(boolean z) {
            MotsService.isIVI = z;
        }

        public final String getIVIName() {
            return MotsService.IVIName;
        }

        public final void setIVIName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            MotsService.IVIName = str;
        }
    }

    public final String getBeepWavPath() {
        return this.beepWavPath;
    }

    public final void setBeepWavPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.beepWavPath = str;
    }

    public final String getBeepWavName() {
        return this.beepWavName;
    }

    public final void setBeepWavName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.beepWavName = str;
    }

    private final File getTrafficPublicTempPath() {
        return (File) this.trafficPublicTempPath.getValue();
    }

    public final TtsBean getNowTtsBean() {
        return this.nowTtsBean;
    }

    public final void setNowTtsBean(TtsBean ttsBean) {
        this.nowTtsBean = ttsBean;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12, types: [T, java.util.Locale] */
    /* JADX WARN: Type inference failed for: r1v3, types: [T, java.util.Locale] */
    public final Locale loadSystemLanguage() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
        Log.i(TAG, "onReceive localeLanguage [" + objectRef.element + ']');
        Log.i(TAG, "onReceive BuildConfig.PROJECT_TYPE [byd8155f]");
        if (CheckBoardKt.isGreatwallV61() || CheckBoardKt.isWeyforproductv3()) {
            T locale = objectRef.element;
            Intrinsics.checkNotNullExpressionValue(locale, "locale");
            objectRef.element = greatWallV61LanguageLocale((Locale) locale);
            CommonSystem commonSystem = this.commonSystem;
            if (commonSystem != null) {
                T locale2 = objectRef.element;
                Intrinsics.checkNotNullExpressionValue(locale2, "locale");
                commonSystem.setSystemLocale((Locale) locale2);
            }
        } else if (CheckBoardKt.isByd()) {
            Log.i(TAG, "byd6125F Locale changed");
            CommonSystem commonSystem2 = this.commonSystem;
            if (commonSystem2 != null) {
                SystemUtils.Companion companion = SystemUtils.INSTANCE;
                T locale3 = objectRef.element;
                Intrinsics.checkNotNullExpressionValue(locale3, "locale");
                commonSystem2.setSystemLocale(companion.getLanguage((Locale) locale3));
            }
            SystemUtils.Companion companion2 = SystemUtils.INSTANCE;
            String language = ((Locale) objectRef.element).getLanguage();
            Intrinsics.checkNotNullExpressionValue(language, "locale.language");
            loadEngineTTSVoice(companion2.getTTSLanguageResourceKey(language));
        } else if (CheckBoardKt.isByd8155f()) {
            Log.i(TAG, "byd8155F Locale changed");
            stopTTS();
            CommonSystem commonSystem3 = this.commonSystem;
            if (commonSystem3 != null) {
                SystemUtils.Companion companion3 = SystemUtils.INSTANCE;
                T locale4 = objectRef.element;
                Intrinsics.checkNotNullExpressionValue(locale4, "locale");
                commonSystem3.setSystemLocale(companion3.getLanguage((Locale) locale4));
            }
            this.handler.post(new Runnable() { // from class: com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda27
                @Override // java.lang.Runnable
                public final void run() {
                    MotsService.loadSystemLanguage$lambda$0(this.f$0, objectRef);
                }
            });
        }
        Log.i(TAG, "language == " + objectRef.element);
        T locale5 = objectRef.element;
        Intrinsics.checkNotNullExpressionValue(locale5, "locale");
        return (Locale) locale5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void loadSystemLanguage$lambda$0(MotsService this$0, Ref.ObjectRef locale) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(locale, "$locale");
        IBydSystemAdapterComponent iBydSystemAdapterComponent = this$0.iBydSystemAdapterComponent;
        if (iBydSystemAdapterComponent != null) {
            SystemUtils.Companion companion = SystemUtils.INSTANCE;
            String language = ((Locale) locale.element).getLanguage();
            Intrinsics.checkNotNullExpressionValue(language, "locale.language");
            iBydSystemAdapterComponent.switchTtsLanguage(companion.getTTSLanguageResourceKey(language));
        }
    }

    private final void E01TTSInit() {
        this.musicType = 10;
        this.ttsBundle.putInt("streamType", 10);
        this.am = (AudioManager) getSystemService(Car.AUDIO_SERVICE);
        TextToSpeech textToSpeech = new TextToSpeech(this, this);
        this.tts = textToSpeech;
        textToSpeech.setOnUtteranceProgressListener(this.utteranceProgressListener);
    }

    private final void initSmartTTS() {
        Log.i(TAG, "initSmartTTS");
        this.musicType = 15;
        this.am = (AudioManager) getSystemService(Car.AUDIO_SERVICE);
        this.smartTTSVoice = AIServiceAPI.get(getApplicationContext());
        Log.i(TAG, "AIServiceImpl instance.");
        AIServiceAPI aIServiceAPI = this.smartTTSVoice;
        if (aIServiceAPI != null) {
            aIServiceAPI.init(getApplicationContext(), new ConnectCallBack() { // from class: com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda29
                @Override // com.ecarx.aiserviceapi.ConnectCallBack
                public final void onAPIReady(boolean z) {
                    MotsService.initSmartTTS$lambda$1(this.f$0, z);
                }
            });
        }
        Log.i(TAG, "initSmartTTS end.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initSmartTTS$lambda$1(final MotsService this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(TAG, "aiServiceImpl.init state == " + z);
        if (z) {
            this$0.isConnectSmartTTS = true;
            AIServiceAPI aIServiceAPI = this$0.smartTTSVoice;
            Intrinsics.checkNotNull(aIServiceAPI);
            aIServiceAPI.registerAIServiceObserver(new AIServiceCallback() { // from class: com.neusoft.na.ocservice.MotsService$initSmartTTS$1$1
                @Override // com.ecarx.aiserviceapi.AIServiceCallback
                public int readMessage(int type, String contactName, String phoneNumber) {
                    return 0;
                }

                @Override // com.ecarx.aiserviceapi.AIServiceCallback
                public int sendMessage(int type, String contactName, String phoneNumber, String messageContent) {
                    return 0;
                }

                @Override // com.ecarx.aiserviceapi.AIServiceCallback
                public int updateMessageContent(String content, boolean isFinal) {
                    return 0;
                }

                @Override // com.ecarx.aiserviceapi.AIServiceCallback
                public void onAIStateChangeObserver(int state) {
                    Log.i(MotsService.TAG, "onAIStateChangeObserver state == " + state);
                }

                @Override // com.ecarx.aiserviceapi.AIServiceCallback
                public void onTTSStateChangeObserver(int state) {
                    Log.i(MotsService.TAG, "onTTSStateChangeObserver state == " + state);
                }

                @Override // com.ecarx.aiserviceapi.AIServiceCallback
                public void onTTSStateChangeByTypeObserver(int instanceType, int state) {
                    Log.i(MotsService.TAG, "onTTSStateChangeByTypeObserver state == " + state + " instanceType == " + instanceType);
                    if (instanceType == 2 && state == 4) {
                        Log.i(MotsService.TAG, "onTTSStateChangeObserver --> ttsPlayOnceEnd");
                        this.this$0.ttsPlayOnceEnd();
                    }
                }
            });
            return;
        }
        Log.e(TAG, "AIServiceAPI init error.");
    }

    private final void initZeekrTTS() {
        Log.i(TAG, "[initZeekrTTS]");
        this.musicType = 15;
        this.am = (AudioManager) getSystemService(Car.AUDIO_SERVICE);
        this.ttsBundle.putInt("streamType", this.musicType);
        this.tts = new TextToSpeech(this, this, "com.ecarx.xiaokagui");
        this.audioAttributes = new AudioAttributes.Builder().setLegacyStreamType(this.musicType).setUsage(12).build();
        AudioFocusRequest.Builder builder = new AudioFocusRequest.Builder(1);
        AudioAttributes audioAttributes = this.audioAttributes;
        Intrinsics.checkNotNull(audioAttributes);
        this.audioFocusRequest = builder.setAudioAttributes(audioAttributes).setOnAudioFocusChangeListener(this.mAudioFocusListener).build();
        TextToSpeech textToSpeech = this.tts;
        if (textToSpeech != null) {
            textToSpeech.setAudioAttributes(this.audioAttributes);
        }
        TextToSpeech textToSpeech2 = this.tts;
        if (textToSpeech2 != null) {
            textToSpeech2.setOnUtteranceProgressListener(this.utteranceProgressListener);
        }
    }

    private final void initSeresTTS() {
        Log.i(TAG, "initSeresTTS");
        this.musicType = 11;
        ISeresSystemAdapterComponent iSeresSystemAdapterComponent = this.iSeresSystemAdapterComponent;
        if (iSeresSystemAdapterComponent != null) {
            iSeresSystemAdapterComponent.initTTS(getApplicationContext(), this.musicType);
            iSeresSystemAdapterComponent.setTtsCallback(new SeresSystemAdapterCallback() { // from class: com.neusoft.na.ocservice.MotsService$initSeresTTS$1$1
                @Override // com.neusoft.na.common.SeresSystemAdapterCallback
                public void ttsPlayEnd() {
                    Log.i(MotsService.TAG, "[ttsPlayEnd]");
                    this.this$0.ttsPlayOnceEnd();
                }

                @Override // com.neusoft.na.common.SeresSystemAdapterCallback
                public void ttsPlayInterrupted() {
                    Log.i(MotsService.TAG, "[ttsPlayInterrupted]");
                    this.this$0.ttsPlayOnceEnd();
                }
            });
        }
    }

    private final void otherTTSInit() {
        Log.i(TAG, "otherTTSInit");
        this.ttsBundle.putInt("streamType", this.musicType);
        this.am = (AudioManager) getSystemService(Car.AUDIO_SERVICE);
        TextToSpeech textToSpeech = new TextToSpeech(this, this);
        this.tts = textToSpeech;
        textToSpeech.setOnUtteranceProgressListener(this.utteranceProgressListener);
    }

    private final void mc2TTSInit() {
        Log.i(TAG, "mc2TTSInit");
        ServiceBase.INSTANCE.getLogger().debug("mc2TTSInit");
        IProtonMc2Component iProtonMc2Component = this.mMc2Component;
        Integer numValueOf = iProtonMc2Component != null ? Integer.valueOf(iProtonMc2Component.getAudioType()) : null;
        Intrinsics.checkNotNull(numValueOf);
        int iIntValue = numValueOf.intValue();
        this.musicType = iIntValue;
        this.ttsBundle.putInt("streamType", iIntValue);
        this.am = (AudioManager) getSystemService(Car.AUDIO_SERVICE);
        TextToSpeech textToSpeech = new TextToSpeech(this, this);
        this.tts = textToSpeech;
        textToSpeech.setOnUtteranceProgressListener(this.utteranceProgressListener);
    }

    private final void X70TTSInit() {
        Log.i(TAG, "X70TTSInit");
        ServiceBase.INSTANCE.getLogger().debug("X70TTSInit");
        this.musicType = 12;
        this.ttsBundle.putInt("streamType", 12);
        this.durationHint = 2;
        this.am = (AudioManager) getSystemService(Car.AUDIO_SERVICE);
        TextToSpeech textToSpeech = new TextToSpeech(this, this);
        this.tts = textToSpeech;
        textToSpeech.setOnUtteranceProgressListener(this.utteranceProgressListener);
    }

    private final void changAnTTSInit() {
        Log.i(TAG, "changAnTTSInit");
        ServiceBase.INSTANCE.getLogger().debug("changAnTTSInit");
        VocalizerEngine vocalizerEngine = this.ttsEngine;
        if (vocalizerEngine == null) {
            VocalizerEngine vocalizerEngine2 = new VocalizerEngine(getApplicationContext());
            this.ttsEngine = vocalizerEngine2;
            Intrinsics.checkNotNull(vocalizerEngine2);
            vocalizerEngine2.setListener(this);
            VocalizerEngine vocalizerEngine3 = this.ttsEngine;
            Intrinsics.checkNotNull(vocalizerEngine3);
            vocalizerEngine3.setSpeechMarkListener(this);
            VocalizerEngine vocalizerEngine4 = this.ttsEngine;
            Intrinsics.checkNotNull(vocalizerEngine4);
            vocalizerEngine4.setAudioOutputListener(this, false);
            VocalizerEngine vocalizerEngine5 = this.ttsEngine;
            Intrinsics.checkNotNull(vocalizerEngine5);
            vocalizerEngine5.initialize();
            return;
        }
        Intrinsics.checkNotNull(vocalizerEngine);
        vocalizerEngine.setListener(this);
        VocalizerEngine vocalizerEngine6 = this.ttsEngine;
        Intrinsics.checkNotNull(vocalizerEngine6);
        vocalizerEngine6.setSpeechMarkListener(this);
        VocalizerEngine vocalizerEngine7 = this.ttsEngine;
        Intrinsics.checkNotNull(vocalizerEngine7);
        vocalizerEngine7.setAudioOutputListener(this, false);
        VocalizerEngine vocalizerEngine8 = this.ttsEngine;
        Intrinsics.checkNotNull(vocalizerEngine8);
        onStateChanged(vocalizerEngine8.getState());
    }

    private final void loadSelectedVoice(int value) {
        Log.i(TAG, "loadSelectedVoice --> " + value);
        ServiceBase.INSTANCE.getLogger().debug("loadSelectedVoice --> " + value);
        if (CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            Log.i(TAG, "byd is not has man/women tts switch.");
        } else {
            loadEngineTTSVoice(value != 0 ? value != 1 ? "" : "Ava-Ml" : "Tom");
        }
    }

    private final void initVocalizerEngineTTS() {
        Log.i(TAG, "initVocalizerEngineTTS");
        VocalizerEngine vocalizerEngine = this.ttsEngine;
        if (vocalizerEngine == null) {
            Log.i(TAG, "initVocalizerEngineTTS init tts engine");
            VocalizerEngine vocalizerEngine2 = new VocalizerEngine(getApplicationContext());
            this.ttsEngine = vocalizerEngine2;
            Intrinsics.checkNotNull(vocalizerEngine2);
            vocalizerEngine2.setListener(this);
            VocalizerEngine vocalizerEngine3 = this.ttsEngine;
            Intrinsics.checkNotNull(vocalizerEngine3);
            vocalizerEngine3.setSpeechMarkListener(this);
            VocalizerEngine vocalizerEngine4 = this.ttsEngine;
            Intrinsics.checkNotNull(vocalizerEngine4);
            vocalizerEngine4.setAudioOutputListener(this, false);
            if (CheckBoardKt.isByd()) {
                this.musicType = 14;
                VocalizerEngine vocalizerEngine5 = this.ttsEngine;
                Intrinsics.checkNotNull(vocalizerEngine5);
                vocalizerEngine5.setAudioStream(this.musicType);
            }
            VocalizerEngine vocalizerEngine6 = this.ttsEngine;
            Intrinsics.checkNotNull(vocalizerEngine6);
            vocalizerEngine6.initialize();
            return;
        }
        Intrinsics.checkNotNull(vocalizerEngine);
        vocalizerEngine.setListener(this);
        VocalizerEngine vocalizerEngine7 = this.ttsEngine;
        Intrinsics.checkNotNull(vocalizerEngine7);
        vocalizerEngine7.setSpeechMarkListener(this);
        VocalizerEngine vocalizerEngine8 = this.ttsEngine;
        Intrinsics.checkNotNull(vocalizerEngine8);
        vocalizerEngine8.setAudioOutputListener(this, false);
        VocalizerEngine vocalizerEngine9 = this.ttsEngine;
        Intrinsics.checkNotNull(vocalizerEngine9);
        onStateChanged(vocalizerEngine9.getState());
    }

    private final void initByd8155fTTS() {
        Log.i(TAG, "[initByd8155fTTS]");
        this.am = (AudioManager) getSystemService(Car.AUDIO_SERVICE);
        IBydSystemAdapterComponent iBydSystemAdapterComponent = this.iBydSystemAdapterComponent;
        Integer numValueOf = iBydSystemAdapterComponent != null ? Integer.valueOf(iBydSystemAdapterComponent.getTtsAudioUsage()) : null;
        Log.i(TAG, "usageAssistanceNavigationGuidance == " + numValueOf);
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        Intrinsics.checkNotNull(numValueOf);
        this.audioAttributes = builder.setUsage(numValueOf.intValue()).build();
        AudioFocusRequest.Builder builder2 = new AudioFocusRequest.Builder(1);
        AudioAttributes audioAttributes = this.audioAttributes;
        Intrinsics.checkNotNull(audioAttributes);
        this.audioFocusRequest = builder2.setAudioAttributes(audioAttributes).setOnAudioFocusChangeListener(this.mAudioFocusListener).build();
        IBydSystemAdapterComponent iBydSystemAdapterComponent2 = this.iBydSystemAdapterComponent;
        if (iBydSystemAdapterComponent2 != null) {
            Log.i(TAG, "iBydSystemAdapterComponent instance, do initTTS");
            iBydSystemAdapterComponent2.initTTS(getApplicationContext());
            iBydSystemAdapterComponent2.setTtsCallback(new BydSystemAdapterCallback() { // from class: com.neusoft.na.ocservice.MotsService$initByd8155fTTS$1$1
                @Override // com.neusoft.na.common.BydSystemAdapterCallback
                public void ttsPlayEnd(String endText) {
                    Log.i(MotsService.TAG, "[ttsPlayEnd] endText == " + endText);
                    this.this$0.setNowTtsBean(new TtsBean());
                    if (this.this$0.ttsList.size() > 0) {
                        this.this$0.ttsList.remove(0);
                    }
                    if (this.this$0.ttsList.size() <= 0) {
                        this.this$0.abandonAudioFocus();
                        return;
                    }
                    MotsService motsService = this.this$0;
                    motsService.setNowTtsBean((TtsBean) motsService.ttsList.get(0));
                    MotsService motsService2 = this.this$0;
                    TtsBean nowTtsBean = motsService2.getNowTtsBean();
                    Intrinsics.checkNotNull(nowTtsBean);
                    motsService2.speak(nowTtsBean.getMsg());
                }

                @Override // com.neusoft.na.common.BydSystemAdapterCallback
                public void ttsPlayError(String errorText) {
                    Log.i(MotsService.TAG, "[ttsPlayError] errorText == " + errorText);
                    this.this$0.setNowTtsBean(new TtsBean());
                    if (this.this$0.ttsList.size() > 0) {
                        this.this$0.ttsList.remove(0);
                    }
                    if (this.this$0.ttsList.size() > 0) {
                        MotsService motsService = this.this$0;
                        motsService.setNowTtsBean((TtsBean) motsService.ttsList.get(0));
                        MotsService motsService2 = this.this$0;
                        TtsBean nowTtsBean = motsService2.getNowTtsBean();
                        Intrinsics.checkNotNull(nowTtsBean);
                        motsService2.speak(nowTtsBean.getMsg());
                    }
                }

                @Override // com.neusoft.na.common.BydSystemAdapterCallback
                public void onLanguageSwitchFail() {
                    Log.d(MotsService.TAG, "onLanguageSwitchFail, set default en language");
                    Locale locale = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
                    Locale locale2 = new Locale("en", locale.getCountry(), locale.getVariant());
                    Log.i(MotsService.TAG, "set default en language --> " + locale2);
                    CommonSystem commonSystem = this.this$0.commonSystem;
                    if (commonSystem != null) {
                        commonSystem.setSystemLocale(SystemUtils.INSTANCE.getLanguage(locale2));
                    }
                    IBydSystemAdapterComponent iBydSystemAdapterComponent3 = this.this$0.iBydSystemAdapterComponent;
                    if (iBydSystemAdapterComponent3 != null) {
                        SystemUtils.Companion companion = SystemUtils.INSTANCE;
                        String language = locale2.getLanguage();
                        Intrinsics.checkNotNullExpressionValue(language, "locale.language");
                        iBydSystemAdapterComponent3.switchTtsLanguage(companion.getTTSLanguageResourceKey(language));
                    }
                }
            });
        }
        initTTSLanguage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadEngineTTSVoice(String name) {
        Log.i(TAG, "loadEngineTTSVoice name == " + name);
        VocalizerEngine vocalizerEngine = this.ttsEngine;
        Integer numValueOf = null;
        VocalizerVoice[] voiceList = vocalizerEngine != null ? vocalizerEngine.getVoiceList() : null;
        Log.i(TAG, "list.size == " + (voiceList != null ? Integer.valueOf(voiceList.length) : null));
        if (voiceList != null) {
            try {
                numValueOf = Integer.valueOf(voiceList.length);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Intrinsics.checkNotNull(numValueOf);
        int iIntValue = numValueOf.intValue();
        for (int i = 0; i < iIntValue; i++) {
            if (Intrinsics.areEqual(voiceList[i].getVoiceName(), name)) {
                stopTTS();
                Log.i(TAG, "found " + name + " tts resource.");
                VocalizerEngine vocalizerEngine2 = this.ttsEngine;
                Intrinsics.checkNotNull(vocalizerEngine2);
                vocalizerEngine2.loadVoice(voiceList[i]);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ttsPlayOnceEnd() {
        Log.i(TAG, "[ttsPlayOnceEnd]");
        this.nowTtsBean = new TtsBean();
        if (this.ttsList.size() > 0) {
            Log.i(TAG, "[ttsPlayOnceEnd] --> remove the first audio text record");
            this.ttsList.remove(0);
        }
        if (this.ttsList.size() > 0) {
            Log.i(TAG, "[ttsPlayOnceEnd] --> load the first audio text record");
            TtsBean ttsBean = this.ttsList.get(0);
            this.nowTtsBean = ttsBean;
            Intrinsics.checkNotNull(ttsBean);
            speak(ttsBean.getMsg());
        } else {
            abandonAudioFocus();
        }
        Log.i(TAG, "ttsPlayOnceEnd end.");
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged");
        CommonSystem commonSystem = this.commonSystem;
        if (commonSystem != null) {
            commonSystem.onConfigurationChanged(newConfig);
        }
        Intent intent = new Intent(Byd8155fSystem.ACTION_WIDGET_SCREEN_CHANGED);
        intent.setComponent(new ComponentName(this, "com.neusoft.na.byd.BydAppWidget"));
        sendBroadcast(intent);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0779  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x07b8  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x07e3  */
    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onCreate() throws java.lang.IllegalAccessException, java.lang.InstantiationException, java.lang.ClassNotFoundException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 2148
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.MotsService.onCreate():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$7(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$8(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$10(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$11(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$12(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$13(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$14(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$15(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$16(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$17(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: renamed from: com.neusoft.na.ocservice.MotsService$onCreate$15, reason: invalid class name */
    /* JADX INFO: compiled from: MotsService.kt */
    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/neusoft/na/ocservice/MotsService$onCreate$15", "Lcom/neusoft/na/common/ProtonCallBack;", "callPhone", "", "num", "", "getBtConnect", "", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class AnonymousClass15 implements ProtonCallBack {
        AnonymousClass15() {
        }

        @Override // com.neusoft.na.common.ProtonCallBack
        public boolean getBtConnect() {
            IProtonlComponent iProtonlComponent = MotsService.this.protonSystem;
            Boolean boolValueOf = iProtonlComponent != null ? Boolean.valueOf(iProtonlComponent.btIsConnect()) : null;
            Intrinsics.checkNotNull(boolValueOf);
            return boolValueOf.booleanValue();
        }

        @Override // com.neusoft.na.common.ProtonCallBack
        public void callPhone(String num) {
            Intrinsics.checkNotNullParameter(num, "num");
            IProtonX70lComponent iProtonX70lComponent = MotsService.this.protonx70System;
            if (iProtonX70lComponent != null) {
                iProtonX70lComponent.callPhone(num);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getNavigationFlowSwitchState() {
        Log.i(TAG, "[getNavigationFlowSwitchState]");
        Log.i(TAG, "navigationSwitchState == " + Settings.Global.getString(getContentResolver(), "persist.mobile.navigation.switch"));
        return !Intrinsics.areEqual(r0, "1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendNetState2Common(int netStateValue) {
        Log.i(TAG, "[sendNetState2Common] --> netStateValue == " + netStateValue + ", netStateCacheValue == " + this.netStateCacheValue);
        if (netStateValue != this.netStateCacheValue) {
            this.netStateCacheValue = netStateValue;
            CommonSystem commonSystem = this.commonSystem;
            if (commonSystem != null) {
                commonSystem.setNetworkState(netStateValue == 1);
                return;
            }
            return;
        }
        Log.i(TAG, "netStateCacheValue == netStateCacheValue, so do not send.");
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        ServiceBase.INSTANCE.getLogger().debug("MotsService.onStartCommand()");
        int iOnStartCommand = super.onStartCommand(intent, flags, startId);
        if (getMApplicationStatus().getMStatus() == ApplicationStatus.Status.UNINITIALIZED) {
            new RemoteInput.Builder("Close").setLabel("Close").build();
            if (CheckBoardKt.isGreatwallV61()) {
                init();
                return iOnStartCommand;
            }
            MotsService motsService = this;
            NotificationCompat.Builder ongoing = new NotificationHelper(motsService).create(NotificationHelper.Channel.OneCore).getBuilder().setSmallIcon(R.drawable.ic_navigation_white_24dp).setContentTitle(getString(R.string.app_name)).setContentText("Navigation running").setPriority(-1).setOngoing(true);
            Intent intent2 = new Intent();
            intent2.setClassName(getPackageName(), "com.neusoft.na.mots.MainActivity");
            intent2.setFlags(536870912);
            startForeground(1338, ongoing.setContentIntent(PendingIntent.getActivity(motsService, 0, intent2, 67108864)).build());
            init();
        }
        return iOnStartCommand;
    }

    @Override // android.app.Service
    public void onDestroy() {
        IBydSystemAdapterComponent iBydSystemAdapterComponent;
        ServiceBase.INSTANCE.getLogger().debug("MotsService.onDestroy()");
        unregisterReceiver(this.broadcastReceiver);
        MotsBl motsBl = this.mMotsBl;
        if (motsBl != null) {
            motsBl.stop();
        }
        TextToSpeech textToSpeech = this.tts;
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        if (CheckBoardKt.isByd8155f() && (iBydSystemAdapterComponent = this.iBydSystemAdapterComponent) != null) {
            iBydSystemAdapterComponent.destroyTts();
        }
        this.MapStatus.unSetDayNightModeChanged();
        unregisterReceiver(this.mMotsServiceBroadcastReceiver);
        super.onDestroy();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.neusoft.na.common.ServiceBase
    protected boolean handleIncomingMessage(Message msg) {
        CommonSystem commonSystem;
        Intrinsics.checkNotNullParameter(msg, "msg");
        switch (msg.what) {
            case OneCoreService.MSG_REQ_SET_SURFACE /* 131073 */:
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_SURFACE");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_SURFACE");
                Bundle data = msg.getData();
                z = (data != null ? (Surface) data.getParcelable(OneCoreService.PAYLOAD_SURFACE) : null) != null;
                this.mapSurfaceStatus = z;
                CommonSystem commonSystem2 = this.commonSystem;
                if (commonSystem2 != null) {
                    commonSystem2.setMapSurfaceStatus(z);
                }
                Log.i(TAG, "handleIncomingMessage::setMapSurfaceStatus");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::setMapSurfaceStatus");
                OneCoreLifeCycleController oneCoreLifeCycleController = this.mOneCoreLifeCycleController;
                Bundle data2 = msg.getData();
                oneCoreLifeCycleController.setSurface(data2 != null ? (Surface) data2.getParcelable(OneCoreService.PAYLOAD_SURFACE) : null);
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_SURFACE end");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_SURFACE end");
                z = true;
                break;
            case OneCoreService.MSG_REQ_SET_WIDGET_SURFACE /* 131074 */:
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_WIDGET_SURFACE");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_WIDGET_SURFACE");
                Bundle data3 = msg.getData();
                z = (data3 != null ? (Surface) data3.getParcelable(OneCoreService.PAYLOAD_SURFACE) : null) != null;
                this.widgetSurfaceStatus = z;
                CommonSystem commonSystem3 = this.commonSystem;
                if (commonSystem3 != null) {
                    commonSystem3.setWidgetSurfaceStatus(z);
                }
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_WIDGET_SURFACE");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_WIDGET_SURFACE");
                OneCoreLifeCycleController oneCoreLifeCycleController2 = this.mOneCoreLifeCycleController;
                Bundle data4 = msg.getData();
                oneCoreLifeCycleController2.setWidgetSurface(data4 != null ? (Surface) data4.getParcelable(OneCoreService.PAYLOAD_SURFACE) : null);
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_WIDGET_SURFACE end");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_WIDGET_SURFACE end");
                z = true;
                break;
            case OneCoreService.MSG_REQ_SET_CLUSTER_SURFACE /* 131075 */:
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_CLUSTER_SURFACE");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_CLUSTER_SURFACE");
                Bundle data5 = msg.getData();
                z = (data5 != null ? (Surface) data5.getParcelable(OneCoreService.PAYLOAD_SURFACE) : null) != null;
                this.clusterSurfaceStatus = z;
                CommonSystem commonSystem4 = this.commonSystem;
                if (commonSystem4 != null) {
                    commonSystem4.setClusterSurfaceStatus(z);
                }
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_CLUSTER_SURFACE");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_CLUSTER_SURFACE");
                OneCoreLifeCycleController oneCoreLifeCycleController3 = this.mOneCoreLifeCycleController;
                Bundle data6 = msg.getData();
                oneCoreLifeCycleController3.setClusterSurface(data6 != null ? (Surface) data6.getParcelable(OneCoreService.PAYLOAD_SURFACE) : null);
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_CLUSTER_SURFACE end");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_CLUSTER_SURFACE end");
                z = true;
                break;
            case OneCoreService.MSG_NOTIFY_SURFACE_SIZE_CHANGED /* 131076 */:
                Log.i(TAG, "handleIncomingMessage::MSG_NOTIFY_SURFACE_SIZE_CHANGED");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_NOTIFY_SURFACE_SIZE_CHANGED");
                this.mOneCoreLifeCycleController.notifySurfaceSizeChanged();
                Log.i(TAG, "handleIncomingMessage::MSG_NOTIFY_SURFACE_SIZE_CHANGED end");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_NOTIFY_SURFACE_SIZE_CHANGED end");
                z = true;
                break;
            case OneCoreService.MSG_REQ_SET_MAP_STATUS /* 196609 */:
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_MAP_STATUS");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_MAP_STATUS");
                Bundle data7 = msg.getData();
                Integer numValueOf = data7 != null ? Integer.valueOf(data7.getInt(OneCoreService.PAYLOAD_MAP_STATUS)) : null;
                Intrinsics.checkNotNull(numValueOf);
                if (numValueOf.intValue() > 0) {
                    Log.i(TAG, "handleIncomingMessage::setMapStatus");
                    ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::setMapStatus");
                    int i = msg.getData().getInt(OneCoreService.PAYLOAD_MAP_STATUS);
                    this.mapStatus = i;
                    CommonSystem commonSystem5 = this.commonSystem;
                    if (commonSystem5 != null) {
                        commonSystem5.setMapScreenStatus(i);
                    }
                }
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_MAP_STATUS:end");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_MAP_STATUS:end");
                z = true;
                break;
            case OneCoreService.MSG_REQ_SET_ON_KEYDOWNVALUE /* 262145 */:
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_ON_KEYDOWNVALUE");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_ON_KEYDOWNVALUE");
                Bundle data8 = msg.getData();
                if ((data8 != null ? Integer.valueOf(data8.getInt(OneCoreService.PAYLOAD_KEY_VALUE)) : null) != null) {
                    CommonSystem commonSystem6 = this.commonSystem;
                    if (commonSystem6 != null) {
                        commonSystem6.setOnKeyDownValue(msg.getData().getInt(OneCoreService.PAYLOAD_KEY_VALUE));
                    }
                    Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_ON_KEYDOWNVALUE:end");
                    ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_ON_KEYDOWNVALUE:end");
                }
                z = true;
                break;
            case OneCoreService.MSG_REQ_TIMEZONE_CHANGED /* 327681 */:
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_TIMEZONE_CHANGED");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_TIMEZONE_CHANGED");
                CommonSystem commonSystem7 = this.commonSystem;
                if (commonSystem7 != null) {
                    commonSystem7.setSystemTimeZoneOffset();
                }
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_TIMEZONE_CHANGED:end");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_TIMEZONE_CHANGED:end");
                z = true;
                break;
            case OneCoreService.MSG_REQ_SET_CALLBACK_MSG /* 393217 */:
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_CALLBACK_MSG");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_CALLBACK_MSG");
                if (msg.replyTo != null) {
                    this.mainMessenger = msg.replyTo;
                    Log.i(TAG, "handleIncomingMessage::MSG_REQ_SET_CALLBACK_MSG:end");
                    ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_SET_CALLBACK_MSG:end");
                }
                z = true;
                break;
            case OneCoreService.MSG_REQ_USB_MOUNT /* 458753 */:
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_USB_MOUNT");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_USB_MOUNT");
                if (this.commonSystem != null) {
                    Bundle data9 = msg.getData();
                    if ((data9 != null ? data9.getString(OneCoreService.PAYLOAD_KEY_PATH) : null) != null && (commonSystem = this.commonSystem) != null) {
                        String string = msg.getData().getString(OneCoreService.PAYLOAD_KEY_PATH);
                        if (string == null) {
                            string = "";
                        }
                        commonSystem.usbMount(string);
                    }
                }
                z = true;
                break;
            case OneCoreService.MSG_REQ_USB_UNMOUNT /* 458754 */:
                Log.i(TAG, "handleIncomingMessage::MSG_REQ_USB_UNMOUNT");
                ServiceBase.INSTANCE.getLogger().debug("handleIncomingMessage::MSG_REQ_USB_UNMOUNT");
                CommonSystem commonSystem8 = this.commonSystem;
                if (commonSystem8 != null && commonSystem8 != null) {
                    commonSystem8.usbUnMount();
                }
                z = true;
                break;
            case OneCoreService.MSG_SET_IN_MULTI_WINDOW_MODE /* 589825 */:
                Log.i(TAG, "receive MSG_SET_IN_MULTI_WINDOW_MODE");
                Bundle data10 = msg.getData();
                if (data10 != null) {
                    boolean z = data10.getBoolean(OneCoreService.PAYLOAD_IN_MULTI_WINDOW_MODE, false);
                    CommonSystem commonSystem9 = this.commonSystem;
                    if (commonSystem9 != null) {
                        commonSystem9.setInMultiWindowMode(z);
                    }
                }
                z = true;
                break;
            default:
                ServiceBase.INSTANCE.getLogger().warn("unexpected message type: " + msg.what);
                break;
        }
        if (z) {
            return true;
        }
        return super.handleIncomingMessage(msg);
    }

    private final synchronized void init() {
        Log.i("MostService", "MostService init");
        updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.INITIALIZING, null, 2, null));
        if (CheckBoardKt.isGreatwallV61() || CheckBoardKt.isWeyforproductv3() || CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            this.beepWavPath = "/mapdata/neusoft/";
        }
        if (CheckBoardKt.isGreatwallV61() || CheckBoardKt.isWeyforproductv3() || CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            copyBeepWav();
        }
        getPrivateTempDir().mkdirs();
        ThreadsKt.thread$default(false, false, null, null, 0, new Function0<Unit>() { // from class: com.neusoft.na.ocservice.MotsService.init.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0070  */
            /* JADX WARN: Removed duplicated region for block: B:24:0x00d3  */
            /* JADX WARN: Removed duplicated region for block: B:26:0x00f4  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x010d  */
            /* JADX WARN: Removed duplicated region for block: B:33:0x011c  */
            /* JADX WARN: Removed duplicated region for block: B:40:0x0163  */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void invoke2() {
                /*
                    Method dump skipped, instruction units count: 368
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.MotsService.AnonymousClass1.invoke2():void");
            }
        }, 31, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cpBasicDbFiles() throws Throwable {
        String dbPath = getDbPath();
        Log.i("MostService", "cp2DbPath：" + dbPath);
        String str = getAssetsInstallDir().getAbsolutePath() + File.separator + "basicDb";
        if (new File(dbPath, "ROOT.NDS").exists()) {
            Log.i("MostService", "已经存在db文件");
            deleteBasicDbAssetsFiles(str);
        } else {
            Log.i("MostService", "不存在db文件");
            FileCopyUtils.copyDir(str, dbPath);
            Log.i("MostService", "复制basicDb文件完成");
            deleteBasicDbAssetsFiles(str);
        }
    }

    private final void deleteBasicDbAssetsFiles(String sourceBasicDbPath) {
        File file = new File(sourceBasicDbPath);
        if (file.exists()) {
            FileCopyUtils.deleteDir(file);
        }
    }

    private final String getDbPath() throws Throwable {
        InputStream inputStreamOpen = getAssets().open(FileUtils.getInstance(this).getDbuIniPath());
        Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "assets.open(FileUtils.ge…nstance(this).dbuIniPath)");
        Map<String, Object> ini = Tool.readIni(inputStreamOpen);
        Intrinsics.checkNotNullExpressionValue(ini, "readIni(inputStream)");
        Map map = (Map) ini.get("nds");
        Intrinsics.checkNotNull(map);
        return Tool.getFileDir((String) map.get("database"));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:5|(1:7)(1:8)|9|(1:14)(1:13)|(1:16)(1:17)|18|(10:20|(6:22|SW:23|80|(1:82)(2:83|(1:85)(1:86))|(2:88|(2:90|(2:92|(2:94|(2:96|(1:SW:98)(2:112|(1:240)(1:115)))(2:116|(1:241)(1:119)))(2:120|(1:242)(1:123)))(2:124|(1:243)(1:127)))(2:128|(1:244)(1:131)))(1:132)|133)(0)|166|233|167|(1:186)(6:171|(1:173)(1:174)|175|231|176|177)|190|(6:192|(1:206)(2:197|(3:201|(1:203)(1:204)|205))|207|(1:209)|210|(8:212|(1:214)|215|(1:217)|218|(4:220|(1:222)|223|(1:225))|226|246)(1:227))(1:228)|229|230)(2:134|(2:136|(5:138|(1:140)|141|(1:143)(1:144)|145))(2:147|(1:149)(10:150|(2:154|(3:156|(1:158)(2:159|(1:161)(2:163|164))|162)(1:165))|166|233|167|(2:169|186)(0)|190|(0)(0)|229|230)))|146|166|233|167|(0)(0)|190|(0)(0)|229|230) */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x0530, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x0531, code lost:
    
        r0.printStackTrace();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x0147. Please report as an issue. */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:169:0x04bc A[Catch: Exception -> 0x0530, TryCatch #1 {Exception -> 0x0530, blocks: (B:167:0x04b2, B:169:0x04bc, B:171:0x04c2, B:173:0x04d6, B:175:0x04e1, B:177:0x04ec, B:184:0x0515, B:185:0x0518, B:174:0x04d9, B:186:0x0519, B:176:0x04e3, B:181:0x0512), top: B:233:0x04b2, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0519 A[Catch: Exception -> 0x0530, TRY_LEAVE, TryCatch #1 {Exception -> 0x0530, blocks: (B:167:0x04b2, B:169:0x04bc, B:171:0x04c2, B:173:0x04d6, B:175:0x04e1, B:177:0x04ec, B:184:0x0515, B:185:0x0518, B:174:0x04d9, B:186:0x0519, B:176:0x04e3, B:181:0x0512), top: B:233:0x04b2, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0562  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x06e4  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0211  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean startMotsBl() throws java.lang.IllegalAccessException, java.lang.NoSuchMethodException, java.lang.ClassNotFoundException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 1832
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.MotsService.startMotsBl():boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.Locale greatWallV61LanguageLocale(java.util.Locale r5) throws java.lang.ClassNotFoundException {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "[greatWallV61LanguageLocale] --> locale == "
            r0.<init>(r1)
            java.lang.String r1 = r5.getLanguage()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "XJMotsService"
            android.util.Log.i(r1, r0)
            com.neusoft.na.ocservice.util.SystemUtils r0 = new com.neusoft.na.ocservice.util.SystemUtils
            r0.<init>()
            java.lang.String r2 = "persist.vendor.gwm.cfg.project.code"
            java.lang.String r0 = r0.getSystemPropertiesValue(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "[greatWallV61LanguageLocale] carModel == "
            r2.<init>(r3)
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r1, r2)
            r1 = 1
            if (r0 == 0) goto L65
            int r2 = r0.hashCode()
            switch(r2) {
                case 1727: goto L5b;
                case 48662: goto L52;
                case 48664: goto L49;
                case 48665: goto L40;
                default: goto L3f;
            }
        L3f:
            goto L65
        L40:
            java.lang.String r2 = "119"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L63
            goto L65
        L49:
            java.lang.String r2 = "118"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L63
            goto L65
        L52:
            java.lang.String r2 = "116"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L63
            goto L65
        L5b:
            java.lang.String r2 = "65"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L65
        L63:
            r0 = r1
            goto L66
        L65:
            r0 = 0
        L66:
            if (r0 == 0) goto L96
            int r0 = r4.switchLanOnOff
            java.lang.String r2 = "th"
            if (r0 != r1) goto L88
            java.lang.String r0 = r5.getLanguage()
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L96
            java.util.Locale r0 = new java.util.Locale
            java.lang.String r1 = r5.getCountry()
            java.lang.String r5 = r5.getVariant()
            java.lang.String r2 = "en"
            r0.<init>(r2, r1, r5)
            return r0
        L88:
            java.util.Locale r0 = new java.util.Locale
            java.lang.String r1 = r5.getCountry()
            java.lang.String r5 = r5.getVariant()
            r0.<init>(r2, r1, r5)
            return r0
        L96:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.MotsService.greatWallV61LanguageLocale(java.util.Locale):java.util.Locale");
    }

    @Override // com.neusoft.na.common.ServiceBase
    protected synchronized void onStart() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startBL() {
        Observable<ApplicationStatus> applicationStatusObservable;
        if (this.mMotsBl == null) {
            this.mMotsBl = new MotsBl(this);
        }
        if (getMApplicationStatus().getMStatus() == ApplicationStatus.Status.INITIALIZED) {
            updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.STARTING, null, 2, null));
            initPrivateTempDir();
            File privateCacheDir = getPrivateCacheDir();
            if (!privateCacheDir.isDirectory()) {
                privateCacheDir.mkdirs();
            }
            MotsService motsService = this;
            NaviStatus companion = NaviStatus.INSTANCE.getInstance(motsService);
            if (companion != null) {
                if (!companion.checkDbApply()) {
                    Log.i(TAG, "!naviStatus.checkDbApply");
                    ServiceBase.INSTANCE.getLogger().debug("!naviStatus.checkDbApply");
                    startOc();
                } else {
                    ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService.startBL.2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "DbApply";
                        }
                    });
                }
            }
            if (EHPUtils.startFilter()) {
                if (this.mEhpLifeCycleController == null) {
                    this.mEhpLifeCycleController = new BasicLifeCycleController(motsService, EHPService.class);
                }
                Log.i(EHPService.TAG, "mEhpLifeCycleController");
                BasicLifeCycleController basicLifeCycleController = this.mEhpLifeCycleController;
                if (basicLifeCycleController != null && (applicationStatusObservable = basicLifeCycleController.getApplicationStatusObservable()) != null) {
                    final Function1<ApplicationStatus, Unit> function1 = new Function1<ApplicationStatus, Unit>() { // from class: com.neusoft.na.ocservice.MotsService.startBL.3

                        /* JADX INFO: renamed from: com.neusoft.na.ocservice.MotsService$startBL$3$WhenMappings */
                        /* JADX INFO: compiled from: MotsService.kt */
                        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
                        public /* synthetic */ class WhenMappings {
                            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                            static {
                                int[] iArr = new int[ApplicationStatus.Status.values().length];
                                try {
                                    iArr[ApplicationStatus.Status.INITIALIZED.ordinal()] = 1;
                                } catch (NoSuchFieldError unused) {
                                }
                                try {
                                    iArr[ApplicationStatus.Status.RUNNING.ordinal()] = 2;
                                } catch (NoSuchFieldError unused2) {
                                }
                                $EnumSwitchMapping$0 = iArr;
                            }
                        }

                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(ApplicationStatus applicationStatus) {
                            invoke2(applicationStatus);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ApplicationStatus applicationStatus) {
                            Log.i(EHPService.TAG, "car service --> new application status: " + applicationStatus);
                            int i = WhenMappings.$EnumSwitchMapping$0[applicationStatus.getMStatus().ordinal()];
                            if (i != 1) {
                                if (i == 2) {
                                    Log.i(EHPService.TAG, "car service --> mEhpLifeCycleController run");
                                    return;
                                } else {
                                    Log.i(EHPService.TAG, "car service --> handle error!?");
                                    return;
                                }
                            }
                            Log.i(EHPService.TAG, "mEhpLifeCycleController.startApplication()");
                            BasicLifeCycleController basicLifeCycleController2 = MotsService.this.mEhpLifeCycleController;
                            if (basicLifeCycleController2 != null) {
                                basicLifeCycleController2.startApplication();
                            }
                            ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService.startBL.3.1
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return "car service -->  starting na_sens:INITIALIZED";
                                }
                            });
                        }
                    };
                    applicationStatusObservable.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda25
                        @Override // rx.functions.Action1
                        public final void call(Object obj) {
                            MotsService.startBL$lambda$25(function1, obj);
                        }
                    });
                }
                Log.i(EHPService.TAG, "mEhpLifeCycleController.init()");
                BasicLifeCycleController basicLifeCycleController2 = this.mEhpLifeCycleController;
                if (basicLifeCycleController2 != null) {
                    basicLifeCycleController2.init();
                }
            }
            new Thread(new Runnable() { // from class: com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda26
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    MotsService.startBL$lambda$32(this.f$0);
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startBL$lambda$25(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startBL$lambda$32(final MotsService this$0) throws Throwable {
        CommonSystem commonSystem;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.startMotsBl()) {
            this$0.updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.RUNNING, null, 2, null));
            Tool.getSelfMaps("MOTSBL");
            this$0.actorServer.start();
            CommonSystem commonSystem2 = this$0.commonSystem;
            if (commonSystem2 != null) {
                commonSystem2.start();
            }
            CommonSystem commonSystem3 = this$0.commonSystem;
            if (commonSystem3 != null) {
                commonSystem3.setAppRunningStatusListener(this$0.runningStatusListener);
            }
            Log.i(TAG, "commonSystem == null? == " + (this$0.commonSystem == null));
            int i = this$0.getResources().getConfiguration().orientation;
            Log.i(TAG, "init resources.configuration == " + i);
            CommonSystem commonSystem4 = this$0.commonSystem;
            if (commonSystem4 != null) {
                commonSystem4.screenDirection2Bl(i == 2 ? 0 : 1);
            }
            IOptionalComponent iOptionalComponent = this$0.mWSSystem;
            if (iOptionalComponent != null) {
                iOptionalComponent.start();
            }
            Observable<ApplicationStatus> applicationStatusObservable = this$0.mOneCloudClientLifeCycleController.getApplicationStatusObservable();
            final Function1<ApplicationStatus, Unit> function1 = new Function1<ApplicationStatus, Unit>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$2$1

                /* JADX INFO: compiled from: MotsService.kt */
                @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[ApplicationStatus.Status.values().length];
                        try {
                            iArr[ApplicationStatus.Status.INITIALIZED.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ApplicationStatus applicationStatus) {
                    invoke2(applicationStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final ApplicationStatus applicationStatus) {
                    ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$2$1.1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "OneCloudClient: new application status: " + applicationStatus;
                        }
                    });
                    if (WhenMappings.$EnumSwitchMapping$0[applicationStatus.getMStatus().ordinal()] == 1) {
                        this.$this_run.mOneCloudClientLifeCycleController.startApplication();
                    }
                }
            };
            applicationStatusObservable.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda20
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MotsService.startBL$lambda$32$lambda$28$lambda$27(function1, obj);
                }
            });
            ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$2$2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return "starting OneCloudClient";
                }
            });
            this$0.mOneCloudClientLifeCycleController.init();
            Boolean POS_ENABLED = BuildConfig.POS_ENABLED;
            Intrinsics.checkNotNullExpressionValue(POS_ENABLED, "POS_ENABLED");
            if (POS_ENABLED.booleanValue()) {
                Observable<ApplicationStatus> applicationStatusObservable2 = this$0.mPosLifeCycleController.getApplicationStatusObservable();
                final Function1<ApplicationStatus, Unit> function12 = new Function1<ApplicationStatus, Unit>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$3

                    /* JADX INFO: compiled from: MotsService.kt */
                    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[ApplicationStatus.Status.values().length];
                            try {
                                iArr[ApplicationStatus.Status.INITIALIZED.ordinal()] = 1;
                            } catch (NoSuchFieldError unused) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(ApplicationStatus applicationStatus) {
                        invoke2(applicationStatus);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(final ApplicationStatus applicationStatus) {
                        ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$3.1
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return "na_pos: new application status: " + applicationStatus;
                            }
                        });
                        if (WhenMappings.$EnumSwitchMapping$0[applicationStatus.getMStatus().ordinal()] == 1) {
                            this.this$0.mPosLifeCycleController.startApplication();
                        }
                    }
                };
                applicationStatusObservable2.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda21
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        MotsService.startBL$lambda$32$lambda$29(function12, obj);
                    }
                });
            } else {
                ServiceBase.INSTANCE.getLogger().debug(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "skip starting na_pos (disabled in configuration)";
                    }
                });
            }
            Boolean DEPLOY_NA_SENS = BuildConfig.DEPLOY_NA_SENS;
            Intrinsics.checkNotNullExpressionValue(DEPLOY_NA_SENS, "DEPLOY_NA_SENS");
            if (DEPLOY_NA_SENS.booleanValue()) {
                Observable<ApplicationStatus> applicationStatusObservable3 = this$0.mSensLifeCycleController.getApplicationStatusObservable();
                final Function1<ApplicationStatus, Unit> function13 = new Function1<ApplicationStatus, Unit>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$5

                    /* JADX INFO: compiled from: MotsService.kt */
                    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[ApplicationStatus.Status.values().length];
                            try {
                                iArr[ApplicationStatus.Status.INITIALIZED.ordinal()] = 1;
                            } catch (NoSuchFieldError unused) {
                            }
                            try {
                                iArr[ApplicationStatus.Status.RUNNING.ordinal()] = 2;
                            } catch (NoSuchFieldError unused2) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(ApplicationStatus applicationStatus) {
                        invoke2(applicationStatus);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(final ApplicationStatus applicationStatus) {
                        ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$5.1
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return "na_sens: new application status: " + applicationStatus;
                            }
                        });
                        Log.i(MotsService.TAG, "na_sens: new application status:" + applicationStatus);
                        int i2 = WhenMappings.$EnumSwitchMapping$0[applicationStatus.getMStatus().ordinal()];
                        if (i2 == 1) {
                            this.this$0.mSensLifeCycleController.startApplication();
                            ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$5.2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return "starting na_sens:INITIALIZED";
                                }
                            });
                        } else {
                            if (i2 != 2) {
                                return;
                            }
                            Boolean POS_ENABLED2 = BuildConfig.POS_ENABLED;
                            Intrinsics.checkNotNullExpressionValue(POS_ENABLED2, "POS_ENABLED");
                            if (POS_ENABLED2.booleanValue()) {
                                ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$5.3
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return "starting na_sens:RUNNING";
                                    }
                                });
                                Log.i(MotsService.TAG, "starting na_pos");
                                this.this$0.mPosLifeCycleController.init();
                            }
                        }
                    }
                };
                applicationStatusObservable3.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda23
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        MotsService.startBL$lambda$32$lambda$30(function13, obj);
                    }
                });
                ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "starting na_sens";
                    }
                });
                this$0.mSensLifeCycleController.init();
            } else {
                Boolean POS_ENABLED2 = BuildConfig.POS_ENABLED;
                Intrinsics.checkNotNullExpressionValue(POS_ENABLED2, "POS_ENABLED");
                if (POS_ENABLED2.booleanValue()) {
                    ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$7
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "starting na_sens:RUNNING";
                        }
                    });
                    Log.i(TAG, "starting na_pos");
                    this$0.mPosLifeCycleController.init();
                }
            }
            Boolean DBU_ENABLED = BuildConfig.DBU_ENABLED;
            Intrinsics.checkNotNullExpressionValue(DBU_ENABLED, "DBU_ENABLED");
            if (DBU_ENABLED.booleanValue()) {
                Observable<ApplicationStatus> applicationStatusObservable4 = this$0.mDbuLifeCycleController.getApplicationStatusObservable();
                final Function1<ApplicationStatus, Unit> function14 = new Function1<ApplicationStatus, Unit>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$8

                    /* JADX INFO: compiled from: MotsService.kt */
                    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
                    public /* synthetic */ class WhenMappings {
                        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                        static {
                            int[] iArr = new int[ApplicationStatus.Status.values().length];
                            try {
                                iArr[ApplicationStatus.Status.INITIALIZED.ordinal()] = 1;
                            } catch (NoSuchFieldError unused) {
                            }
                            $EnumSwitchMapping$0 = iArr;
                        }
                    }

                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(ApplicationStatus applicationStatus) {
                        invoke2(applicationStatus);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(final ApplicationStatus applicationStatus) {
                        ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$8.1
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return "na_dbu: new application status: " + applicationStatus;
                            }
                        });
                        if (WhenMappings.$EnumSwitchMapping$0[applicationStatus.getMStatus().ordinal()] == 1) {
                            this.this$0.mDbuLifeCycleController.startApplication();
                        }
                    }
                };
                applicationStatusObservable4.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda24
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        MotsService.startBL$lambda$32$lambda$31(function14, obj);
                    }
                });
                ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$9
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "starting na_dbu";
                    }
                });
                this$0.mDbuLifeCycleController.init();
            } else {
                ServiceBase.INSTANCE.getLogger().debug(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startBL$4$10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "skip starting na_dbu (disabled in configuration)";
                    }
                });
            }
            int i2 = this$0.netStateCacheValue;
            if (i2 == -1 || (commonSystem = this$0.commonSystem) == null) {
                return;
            }
            commonSystem.setNetworkState(i2 == 1);
            return;
        }
        ServiceBase.INSTANCE.getLogger().error("failed to start native applications; now we're stuck in undefined state :(");
        this$0.updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.ERROR, null, 2, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startBL$lambda$32$lambda$28$lambda$27(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startBL$lambda$32$lambda$29(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startBL$lambda$32$lambda$30(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startBL$lambda$32$lambda$31(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // android.speech.tts.TextToSpeech.OnInitListener
    public void onInit(int status) {
        if (status == 0) {
            Log.i(TAG, "onInit --> tts init success.");
        } else {
            Log.e(TAG, "onInit --> tts init failed.");
        }
    }

    private final boolean requestAudioFocus() {
        Log.i(TAG, "requestAudioFocus hasAudioFocus == " + this.hasAudioFocus);
        if (!this.hasAudioFocus) {
            if (CheckBoardKt.isByd8155f() || CheckBoardKt.isZeekr()) {
                Log.i(TAG, "has no focus, need request");
                AudioManager audioManager = this.am;
                Intrinsics.checkNotNull(audioManager);
                AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
                Intrinsics.checkNotNull(audioFocusRequest);
                int iRequestAudioFocus = audioManager.requestAudioFocus(audioFocusRequest);
                Log.i(TAG, "result == " + iRequestAudioFocus);
                if (iRequestAudioFocus == 0) {
                    Log.i(TAG, "requestAudioFocus false (AUDIOFOCUS_REQUEST_FAILED)");
                    this.hasAudioFocus = false;
                } else if (iRequestAudioFocus == 1) {
                    Log.i(TAG, "requestAudioFocus true (AUDIOFOCUS_REQUEST_GRANTED)");
                    this.hasAudioFocus = true;
                } else if (iRequestAudioFocus == 2) {
                    Log.i(TAG, "requestAudioFocus true (AUDIOFOCUS_REQUEST_DELAYED)");
                    this.hasAudioFocus = true;
                }
                return this.hasAudioFocus;
            }
            AudioManager audioManager2 = this.am;
            if (audioManager2 != null && audioManager2.requestAudioFocus(this.mAudioFocusListener, this.musicType, this.durationHint) == 1) {
                Log.i(TAG, "requestAudioFocus true");
                this.hasAudioFocus = true;
                return true;
            }
            Log.i(TAG, "requestAudioFocus false");
            this.hasAudioFocus = false;
            return false;
        }
        Log.i(TAG, "requestAudioFocus hasAudioFocus = true");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void abandonAudioFocus() {
        Log.i(TAG, "abandonAudioFocus");
        this.nowTtsBean = new TtsBean();
        if (this.hasAudioFocus) {
            this.hasAudioFocus = false;
            if (CheckBoardKt.isByd8155f()) {
                AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
                if (audioFocusRequest != null) {
                    Log.i(TAG, "audioFocusRequest (byd8155f)");
                    AudioManager audioManager = this.am;
                    if (audioManager != null) {
                        audioManager.abandonAudioFocusRequest(audioFocusRequest);
                    }
                }
            } else {
                AudioManager audioManager2 = this.am;
                if (audioManager2 != null) {
                    audioManager2.abandonAudioFocus(this.mAudioFocusListener);
                }
            }
        }
        Log.i(TAG, "abandonAudioFocus end.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void stopTTS() {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.MotsService.stopTTS():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void stopTTS$lambda$34(MotsService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VocalizerEngine vocalizerEngine = this$0.ttsEngine;
        if (vocalizerEngine != null) {
            vocalizerEngine.stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void stopTTS$lambda$35(MotsService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(TAG, "Byd8155F stop tts");
        IBydSystemAdapterComponent iBydSystemAdapterComponent = this$0.iBydSystemAdapterComponent;
        if (iBydSystemAdapterComponent != null) {
            iBydSystemAdapterComponent.stopTts();
        }
        Log.i(TAG, "Byd8155F stop tts end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void stopTTS$lambda$37(MotsService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        IExtensionComponent iExtensionComponent = this$0.mExtension;
        if (iExtensionComponent != null) {
            if (this$0.switchLanOnOff == 3) {
                Log.i(TAG, "[TTSTAGZH] stopTTS");
                Intent intent = new Intent();
                intent.setAction("com.neusoft.ttszh.request");
                intent.putExtra("type", "stopTTSzh");
                this$0.sendBroadcast(intent);
                return;
            }
            iExtensionComponent.stopTTS();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void stopTTS$lambda$38(MotsService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(TAG, "zeekr stop tts");
        TextToSpeech textToSpeech = this$0.tts;
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
        this$0.zeekrTtsSpeaking = false;
        Log.i(TAG, "zeekr stop tts end.");
    }

    private final boolean checkBeepAudioUseMediaPlayer() {
        boolean z = (CheckBoardKt.isGreatwallA01() || CheckBoardKt.isGreatwallB01() || CheckBoardKt.isGreatwallV61() || CheckBoardKt.isWeyforproductv3() || CheckBoardKt.isByd()) ? false : true;
        Log.i(TAG, "supportPlayBeepAudio == " + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mAudioFocusListener$lambda$39(MotsService this$0, int i) {
        MediaPlayer mediaPlayer;
        MediaPlayer mediaPlayer2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == -3) {
            Log.i(TAG, "mAudioFocusListener AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
            ServiceBase.INSTANCE.getLogger().debug("mAudioFocusListener AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
            return;
        }
        if (i == -2) {
            Log.i(TAG, "mAudioFocusListener AUDIOFOCUS_LOSS_TRANSIENT");
            ServiceBase.INSTANCE.getLogger().debug("mAudioFocusListener AUDIOFOCUS_LOSS_TRANSIENT");
            this$0.stopTTS();
            if (this$0.checkBeepAudioUseMediaPlayer() && (mediaPlayer = this$0.mMediaPlayer) != null) {
                mediaPlayer.stop();
            }
            if (CheckBoardKt.isZeekr() && this$0.zeekrTtsSpeaking) {
                this$0.playBeep02MonoSound();
                return;
            }
            return;
        }
        if (i != -1) {
            if (i != 1) {
                return;
            }
            Log.i(TAG, "mAudioFocusListener AUDIOFOCUS_GAIN");
            ServiceBase.INSTANCE.getLogger().debug("mAudioFocusListener AUDIOFOCUS_GAIN");
            return;
        }
        Log.i(TAG, "mAudioFocusListener AUDIOFOCUS_LOSS");
        ServiceBase.INSTANCE.getLogger().debug("mAudioFocusListener AUDIOFOCUS_LOSS");
        this$0.stopTTS();
        if (this$0.checkBeepAudioUseMediaPlayer() && (mediaPlayer2 = this$0.mMediaPlayer) != null) {
            mediaPlayer2.stop();
        }
        if (CheckBoardKt.isZeekr() && this$0.zeekrTtsSpeaking) {
            this$0.playBeep02MonoSound();
        }
    }

    @Override // com.nuance.android.vocalizer.VocalizerEngineListener
    public void onSpeakElementStarted(String p0) {
        Log.i(TAG, "[onSpeakElementStarted] --> " + p0);
        sendTtsPlayStateBroadcast(0);
    }

    @Override // com.nuance.android.vocalizer.VocalizerEngineListener
    public void onSpeakElementCompleted(String p0) {
        Log.i(TAG, "onSpeakElementCompleted:" + p0);
        sendTtsPlayStateBroadcast(2);
        this.nowTtsBean = new TtsBean();
        if (this.ttsList.size() > 0) {
            this.ttsList.remove(0);
        }
        if (this.ttsList.size() > 0) {
            TtsBean ttsBean = this.ttsList.get(0);
            this.nowTtsBean = ttsBean;
            Intrinsics.checkNotNull(ttsBean);
            speak(ttsBean.getMsg());
            return;
        }
        abandonAudioFocus();
    }

    private final void sendTtsPlayStateBroadcast(int playState) {
        Log.i(TAG, "[sendTtsPlayStateBroadcast] --> playState == " + playState);
        boolean z = playState == 0;
        Intent intent = new Intent(ACTION_NEUSOFT_NAVIGATION_PLAY_STATE);
        intent.putExtra(ACTION_KEY_TTS_PLAY_STATE, z);
        sendBroadcast(intent);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x005b, code lost:
    
        if (r0.equals(com.neusoft.na.ocservice.MotsService.GREATWALLV61) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0065, code lost:
    
        if (r0.equals(com.neusoft.na.ocservice.MotsService.GREATWALLB01) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006f, code lost:
    
        if (r0.equals(com.neusoft.na.ocservice.MotsService.GREATWALLA01) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0079, code lost:
    
        if (r0.equals(com.neusoft.na.ocservice.MotsService.ZEEKRDC1E) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0083, code lost:
    
        if (r0.equals(com.neusoft.na.ocservice.MotsService.ZEEKRBX1E) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0087, code lost:
    
        r3.handler.post(new com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda14(r3, r4));
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0099, code lost:
    
        if (r0.equals("changan") == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x010e, code lost:
    
        if (r0.equals(com.neusoft.na.ocservice.MotsService.BYD) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0111, code lost:
    
        r3.handler.post(new com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda9(r3, r4));
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0122, code lost:
    
        if (r0.equals(com.neusoft.na.ocservice.MotsService.WEYFORPRODUCTV3) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0125, code lost:
    
        android.util.Log.i(com.neusoft.na.ocservice.MotsService.TAG, "GREATWALLB01||GREATWALLA01||GREATWALLV61||WEYFORPRODUCTV3");
        r3.handler.post(new com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda13(r3, r4));
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void speak(final java.lang.String r4) {
        /*
            Method dump skipped, instruction units count: 370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.MotsService.speak(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void speak$lambda$40(MotsService this$0, String ttsmsg) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(ttsmsg, "$ttsmsg");
        if (this$0.ttsEngine != null) {
            Log.i(TAG, "speak tts" + ttsmsg);
            VocalizerEngine vocalizerEngine = this$0.ttsEngine;
            if (vocalizerEngine != null) {
                vocalizerEngine.speak(ttsmsg, false, ttsmsg);
            }
        } else {
            this$0.nowTtsBean = new TtsBean();
            if (this$0.ttsList.size() > 0) {
                this$0.ttsList.remove(0);
            }
            Log.e(TAG, "tts speak ttsEngine is null");
        }
        Log.i(TAG, "speak tts::end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void speak$lambda$41(MotsService this$0, String ttsmsg) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(ttsmsg, "$ttsmsg");
        if (this$0.requestAudioFocus()) {
            if (this$0.iBydSystemAdapterComponent != null) {
                Log.i(TAG, "iBydSystemAdapterComponent playTTS --> " + ttsmsg);
                IBydSystemAdapterComponent iBydSystemAdapterComponent = this$0.iBydSystemAdapterComponent;
                if (iBydSystemAdapterComponent != null) {
                    iBydSystemAdapterComponent.playTts(ttsmsg);
                }
            } else {
                Log.e(TAG, "tts speak iBydSystemAdapterComponent is null!");
            }
        } else {
            this$0.nowTtsBean = new TtsBean();
            if (this$0.ttsList.size() > 0) {
                this$0.ttsList.remove(0);
            }
            Log.e(TAG, "request audio focus fail!");
        }
        Log.i(TAG, "speak playTTS end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void speak$lambda$43(MotsService this$0, String ttsmsg) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(ttsmsg, "$ttsmsg");
        IExtensionComponent iExtensionComponent = this$0.mExtension;
        if (iExtensionComponent != null) {
            iExtensionComponent.speak(ttsmsg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void speak$lambda$45(MotsService this$0, String ttsmsg) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(ttsmsg, "$ttsmsg");
        IExtensionComponent iExtensionComponent = this$0.mExtension;
        if (iExtensionComponent != null) {
            if (this$0.switchLanOnOff == 3) {
                Log.i(TAG, "[TTSTAGZH] speak [" + ttsmsg + ']');
                Intent intent = new Intent();
                intent.setAction("com.neusoft.ttszh.request");
                intent.putExtra("type", "startTTSzh");
                intent.putExtra(NotificationCompat.CATEGORY_MESSAGE, ttsmsg);
                this$0.sendBroadcast(intent);
                return;
            }
            iExtensionComponent.speak(ttsmsg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void speak$lambda$46(MotsService this$0, String ttsmsg) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(ttsmsg, "$ttsmsg");
        Log.i(TAG, "zeekr post speak");
        if (this$0.requestAudioFocus()) {
            Log.i(TAG, "has audio focus");
            TextToSpeech textToSpeech = this$0.tts;
            if (textToSpeech != null) {
                textToSpeech.speak(ttsmsg, 0, this$0.ttsBundle, ttsmsg);
            }
        } else {
            Log.i(TAG, "has not audio focus");
            this$0.playBeep02MonoSound();
            this$0.nowTtsBean = new TtsBean();
            if (this$0.ttsList.size() > 0) {
                this$0.ttsList.remove(0);
            }
        }
        Log.i(TAG, "zeekr post speak end.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void speak$lambda$47(MotsService this$0, String ttsmsg) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(ttsmsg, "$ttsmsg");
        Log.i(TAG, "seres post speak");
        ISeresSystemAdapterComponent iSeresSystemAdapterComponent = this$0.iSeresSystemAdapterComponent;
        if (iSeresSystemAdapterComponent != null) {
            iSeresSystemAdapterComponent.playTts(ttsmsg);
        }
        Log.i(TAG, "seres post speak end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void speak$lambda$48(MotsService this$0, String ttsmsg) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(ttsmsg, "$ttsmsg");
        Log.i(TAG, "speak tts");
        if (this$0.requestAudioFocus()) {
            Log.i(TAG, "speak current tts --> " + ttsmsg);
            TextToSpeech textToSpeech = this$0.tts;
            if (textToSpeech != null) {
                textToSpeech.speak(ttsmsg, 1, this$0.ttsBundle, ttsmsg);
                return;
            }
            return;
        }
        Log.e(TAG, "speak tts requestAudioFocus false");
        this$0.nowTtsBean = new TtsBean();
        if (this$0.ttsList.size() > 0) {
            this$0.ttsList.remove(0);
        }
    }

    @Override // com.nuance.android.vocalizer.VocalizerEngineListener
    public void onStateChanged(int p0) {
        Log.i(TAG, "onStateChanged --> p0 == " + p0);
        if (p0 == 2) {
            Log.i(TAG, "onStateChanged STATE_INITIALIZED");
            ServiceBase.INSTANCE.getLogger().debug("onStateChanged STATE_INITIALIZED");
            VocalizerEngine vocalizerEngine = this.ttsEngine;
            Intrinsics.checkNotNull(vocalizerEngine);
            vocalizerEngine.setSpeechVolume(80);
            VocalizerEngine vocalizerEngine2 = this.ttsEngine;
            Intrinsics.checkNotNull(vocalizerEngine2);
            vocalizerEngine2.setSpeechRate(100);
            VocalizerEngine vocalizerEngine3 = this.ttsEngine;
            Intrinsics.checkNotNull(vocalizerEngine3);
            vocalizerEngine3.setSpeechPitch(100);
            if (CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
                initTTSLanguage();
            } else {
                soundTypeChanged(this.soundType);
            }
        }
    }

    @Override // com.neusoft.na.common.DayNightModeChangedCallback
    public void changed(int mode) {
        Log.i(TAG, "ondayNightModeChanged:" + mode);
        ServiceBase.INSTANCE.getLogger().debug("ondayNightModeChanged:" + mode);
        ondayNightModeChanged(mode);
    }

    public final void soundTypeChanged(int type) {
        Log.i(TAG, "soundTypeChanged:" + type);
        ServiceBase.INSTANCE.getLogger().debug("soundTypeChanged:" + type);
        loadSelectedVoice(type);
    }

    private final void initTTSLanguage() {
        IBydSystemAdapterComponent iBydSystemAdapterComponent;
        Log.i(TAG, "initTTSLanguage");
        Locale locale = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
        if (CheckBoardKt.isByd()) {
            SystemUtils.Companion companion = SystemUtils.INSTANCE;
            SystemUtils.Companion companion2 = SystemUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(locale, "locale");
            String language = companion2.getLanguage(locale).getLanguage();
            Intrinsics.checkNotNullExpressionValue(language, "SystemUtils.getLanguage(locale).language");
            loadEngineTTSVoice(companion.getTTSLanguageResourceKey(language));
            return;
        }
        if (!CheckBoardKt.isByd8155f() || (iBydSystemAdapterComponent = this.iBydSystemAdapterComponent) == null) {
            return;
        }
        SystemUtils.Companion companion3 = SystemUtils.INSTANCE;
        String language2 = locale.getLanguage();
        Intrinsics.checkNotNullExpressionValue(language2, "locale.language");
        iBydSystemAdapterComponent.switchTtsLanguage(companion3.getTTSLanguageResourceKey(language2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playSound(String fileName) throws IOException {
        if (checkBeepAudioUseMediaPlayer()) {
            Log.i(TAG, "[playSound] --> fileName == " + fileName);
            AssetFileDescriptor assetFileDescriptorOpenFd = getAssets().openFd(fileName);
            Intrinsics.checkNotNullExpressionValue(assetFileDescriptorOpenFd, "assets.openFd(fileName)");
            MediaPlayer mediaPlayer = this.mMediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.reset();
            }
            MediaPlayer mediaPlayer2 = this.mMediaPlayer;
            if (mediaPlayer2 != null) {
                mediaPlayer2.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
            }
            MediaPlayer mediaPlayer3 = this.mMediaPlayer;
            if (mediaPlayer3 != null) {
                mediaPlayer3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda18
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public final void onPrepared(MediaPlayer mediaPlayer4) {
                        MotsService.playSound$lambda$49(this.f$0, mediaPlayer4);
                    }
                });
            }
            Log.i(TAG, "[playSound] --> prepareAsync");
            MediaPlayer mediaPlayer4 = this.mMediaPlayer;
            if (mediaPlayer4 != null) {
                mediaPlayer4.prepareAsync();
                return;
            }
            return;
        }
        Log.i(TAG, "not support play beep audio.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void playSound$lambda$49(MotsService this$0, MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(TAG, "[playSound] --> onPrepared");
        if (this$0.hasAudioFocus) {
            Log.i(TAG, "hasAudioFocus mediaPlayer.start");
            MediaPlayer mediaPlayer2 = this$0.mMediaPlayer;
            if (mediaPlayer2 != null) {
                mediaPlayer2.start();
                return;
            }
            return;
        }
        if (this$0.requestAudioFocus()) {
            Log.i(TAG, "requestAudioFocus mediaPlayer.start");
            MediaPlayer mediaPlayer3 = this$0.mMediaPlayer;
            if (mediaPlayer3 != null) {
                mediaPlayer3.start();
            }
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mp) {
        Log.i(TAG, "MediaPlayer:onCompletion");
        abandonAudioFocus();
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.i(TAG, "MediaPlayer:onError");
        abandonAudioFocus();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startOc() {
        Log.i(TAG, "[NeuMapLCM]startOc");
        ServiceBase.INSTANCE.getLogger().debug("startOc");
        Observable<ApplicationStatus> applicationStatusObservable = this.mOneCoreLifeCycleController.getApplicationStatusObservable();
        final Function1<ApplicationStatus, Unit> function1 = new Function1<ApplicationStatus, Unit>() { // from class: com.neusoft.na.ocservice.MotsService$startOc$1$1

            /* JADX INFO: compiled from: MotsService.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[ApplicationStatus.Status.values().length];
                    try {
                        iArr[ApplicationStatus.Status.INITIALIZED.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[ApplicationStatus.Status.ERROR.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ApplicationStatus applicationStatus) {
                invoke2(applicationStatus);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final ApplicationStatus applicationStatus) {
                ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startOc$1$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "na_onecore: new application status: " + applicationStatus;
                    }
                });
                int i = WhenMappings.$EnumSwitchMapping$0[applicationStatus.getMStatus().ordinal()];
                if (i == 1) {
                    this.$this_run.mOneCoreLifeCycleController.startApplication();
                } else {
                    if (i != 2) {
                        return;
                    }
                    this.$this_run.ocStartError();
                }
            }
        };
        applicationStatusObservable.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda30
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MotsService.startOc$lambda$51$lambda$50(function1, obj);
            }
        });
        ServiceBase.INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.ocservice.MotsService$startOc$1$2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return "starting na_onecore";
            }
        });
        this.mOneCoreLifeCycleController.init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startOc$lambda$51$lambda$50(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void ocStartError() {
        Log.i(TAG, "ocStartError");
        ServiceBase.INSTANCE.getLogger().debug("ocStartError");
        CommonSystem commonSystem = this.commonSystem;
        if (commonSystem != null) {
            commonSystem.ocStartError();
        }
    }

    @Override // com.neusoft.na.common.CommonSystemCallBack
    public void applyFinish() {
        Log.i(TAG, "applyFinish");
        ServiceBase.INSTANCE.getLogger().debug("applyFinish");
        startOc();
    }

    private final void registerMotsBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        this.intentFilter = intentFilter;
        intentFilter.addAction(MotsServiceBroadcastReceiver.INSTANCE.getSTART_ACTIVITY());
        MotsServiceBroadcastReceiver motsServiceBroadcastReceiver = new MotsServiceBroadcastReceiver(this);
        this.mMotsServiceBroadcastReceiver = motsServiceBroadcastReceiver;
        registerReceiver(motsServiceBroadcastReceiver, this.intentFilter);
    }

    @Override // com.neusoft.na.ocservice.Broadcast.MotsServiceBroadcastCallBack
    public void getMaskStatus() {
        Log.i(TAG, "getMaskStatus");
        ServiceBase.INSTANCE.getLogger().debug("getMaskStatus");
        CommonSystem commonSystem = this.commonSystem;
        Boolean boolValueOf = commonSystem != null ? Boolean.valueOf(commonSystem.getRunningStatus()) : null;
        Intrinsics.checkNotNull(boolValueOf);
        if (boolValueOf.booleanValue()) {
            sendMaskGone();
        }
    }

    public final void sendMaskGone() {
        Log.i(TAG, "sendMaskGone");
        ServiceBase.INSTANCE.getLogger().debug("sendMaskGone");
        Intent intent = new Intent();
        intent.setAction("com.neusoft.na.mots.mask.gone");
        sendBroadcast(intent);
    }

    public final void sendWidgetMaskGone() {
        Log.i(TAG, "sendWidgetMaskGone");
        ServiceBase.INSTANCE.getLogger().debug("sendWidgetMaskGone");
        Intent intent = new Intent();
        intent.setAction("com.neusoft.na.mots.widget.mask.gone");
        sendBroadcast(intent);
    }

    public final void sendClusterMaskGone() {
        Log.i(TAG, "[sendClusterMaskGone]");
        Intent intent = new Intent();
        intent.setAction(CLUSTER_GONE_MASK);
        sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSetCurrentLocation(float[] currentLocation) {
        OneCoreLifeCycleController oneCoreLifeCycleController = this.mOneCoreLifeCycleController;
        Intrinsics.checkNotNull(oneCoreLifeCycleController);
        oneCoreLifeCycleController.setCurrentLocation(currentLocation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void copyBeepWav() {
        Log.i(TAG, "copyBeepWav");
        if (!Tool.fileIsExists(this.beepWavPath + this.beepWavName)) {
            Log.i(TAG, "no BeepWav");
            FileUtils.getInstance(this).copyAssetsToPath(this.beepWavName, this.beepWavPath + this.beepWavName);
        } else {
            Log.i(TAG, "has BeepWav");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendA01B01Beep() {
        Log.i(TAG, "sendA01B01Beep:");
        this.handler.post(new Runnable() { // from class: com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda28
            @Override // java.lang.Runnable
            public final void run() {
                MotsService.sendA01B01Beep$lambda$53(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendA01B01Beep$lambda$53(MotsService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        IExtensionComponent iExtensionComponent = this$0.mExtension;
        if (iExtensionComponent != null) {
            iExtensionComponent.speak(this$0.getBeepString());
        }
    }

    private final String getBeepString() {
        String str = "\u001b\\audio=\"" + (this.beepWavPath + this.beepWavName) + "\"\\";
        Log.i(TAG, "getBeepString:" + str);
        return str;
    }

    private final boolean requestZeekrBeep02MonoAudioFocus() {
        Log.i(TAG, "[requestZeekrBeep02MonoAudioFocus]");
        boolean z = false;
        this.zeekrBeep02MonoAudioFocusRequest = new AudioFocusRequest.Builder(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(5).setContentType(0).build()).build();
        AudioManager audioManager = this.am;
        Intrinsics.checkNotNull(audioManager);
        AudioFocusRequest audioFocusRequest = this.zeekrBeep02MonoAudioFocusRequest;
        Intrinsics.checkNotNull(audioFocusRequest);
        int iRequestAudioFocus = audioManager.requestAudioFocus(audioFocusRequest);
        if (iRequestAudioFocus != 0 && (iRequestAudioFocus == 1 || iRequestAudioFocus == 2)) {
            z = true;
        }
        Log.i(TAG, "requestZeekrBeep02MonoAudioFocus --> hasFocus == " + z);
        return z;
    }

    private final void playBeep02MonoSound() {
        Log.i(TAG, "[playBeep02MonoSound]");
        if (!requestZeekrBeep02MonoAudioFocus()) {
            Log.i(TAG, "requestZeekrBeep02MonoAudioFocus --> request focus failed, so do not play beep_02_mono.wav");
        } else {
            final AudioTrack audioTrack = new AudioTrack(new AudioAttributes.Builder().setUsage(13).build(), new AudioFormat.Builder().setEncoding(2).setSampleRate(48000).setChannelMask(4).build(), AudioTrack.getMinBufferSize(48000, 4, 2), 1, 0);
            new Thread(new Runnable() { // from class: com.neusoft.na.ocservice.MotsService$$ExternalSyntheticLambda17
                @Override // java.lang.Runnable
                public final void run() {
                    MotsService.playBeep02MonoSound$lambda$55(audioTrack, this);
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void playBeep02MonoSound$lambda$55(AudioTrack audioTrack, MotsService this$0) {
        Intrinsics.checkNotNullParameter(audioTrack, "$audioTrack");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        audioTrack.play();
        try {
            InputStream inputStreamOpen = this$0.getAssets().open("beep_02_mono.wav");
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "assets.open(\"beep_02_mono.wav\")");
            byte[] bArr = new byte[1024000];
            boolean z = true;
            while (z) {
                int i = inputStreamOpen.read(bArr);
                Log.i(TAG, "read == " + i);
                if (i > 0) {
                    audioTrack.write(bArr, 44, i);
                    audioTrack.flush();
                    Log.i(TAG, "audioTrack.write");
                } else {
                    Log.i(TAG, "audioTrack.finish");
                    inputStreamOpen.close();
                    audioTrack.stop();
                    audioTrack.release();
                    AudioFocusRequest audioFocusRequest = this$0.zeekrBeep02MonoAudioFocusRequest;
                    if (audioFocusRequest != null) {
                        Log.i(TAG, "abandonAudioFocusRequest (zeekrBeep02MonoAudioFocusRequest)");
                        AudioManager audioManager = this$0.am;
                        if (audioManager != null) {
                            audioManager.abandonAudioFocusRequest(audioFocusRequest);
                        }
                    }
                    z = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, String.valueOf(e.getMessage()));
        }
    }
}
