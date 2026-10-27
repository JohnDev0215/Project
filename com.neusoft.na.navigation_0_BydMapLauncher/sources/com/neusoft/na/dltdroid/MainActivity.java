package com.neusoft.na.dltdroid;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0089\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0007*\u0001\t\u0018\u0000 <2\u00020\u00012\u00020\u0002:\u0001<B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0015H\u0016J\u0012\u0010&\u001a\u00020\u00182\b\u0010'\u001a\u0004\u0018\u00010(H\u0014J\u0010\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020\u0018H\u0014J\u0010\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020\u0018H\u0014J-\u00101\u001a\u00020\u00182\u0006\u00102\u001a\u00020\u00052\u000e\u00103\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001e042\u0006\u00105\u001a\u000206H\u0016¢\u0006\u0002\u00107J\b\u00108\u001a\u00020\u0018H\u0014J\b\u00109\u001a\u00020\u0018H\u0002J\b\u0010:\u001a\u00020\u0015H\u0002J\b\u0010;\u001a\u00020\u0015H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/neusoft/na/dltdroid/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "()V", "ACTION_PERMISSIONS_CHANGED", "", "getACTION_PERMISSIONS_CHANGED", "()I", "mConnection", "com/neusoft/na/dltdroid/MainActivity$mConnection$1", "Lcom/neusoft/na/dltdroid/MainActivity$mConnection$1;", "mGraphViewSeries", "Lcom/jjoe64/graphview/series/LineGraphSeries;", "Lcom/jjoe64/graphview/series/DataPoint;", "mIncomingMessenger", "Landroid/os/Messenger;", "getMIncomingMessenger", "()Landroid/os/Messenger;", "mIncomingMessenger$delegate", "Lkotlin/Lazy;", "mIsRunning", "", "mServiceMessenger", "addSample", "", "timestamp", "", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "checkPermissions", "", "", "handleIncomingMessage", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "onCheckedChanged", "compoundButton", "Landroid/widget/CompoundButton;", "b", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onDestroy", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onSettingsClick", "startLogger", "stopLogger", "Companion", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private boolean mIsRunning;
    private Messenger mServiceMessenger;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String LOGTAG = "MainActivity";
    private static final int HISTORY_IN_S = 600;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final int ACTION_PERMISSIONS_CHANGED = 1222;
    private final LineGraphSeries<DataPoint> mGraphViewSeries = new LineGraphSeries<>();
    private final MainActivity$mConnection$1 mConnection = new ServiceConnection() { // from class: com.neusoft.na.dltdroid.MainActivity$mConnection$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName className, IBinder service) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(service, "service");
            this.this$0.mServiceMessenger = new Messenger(service);
            try {
                Message messageObtain = Message.obtain((Handler) null, LogService.INSTANCE.getMSG_Reg_LogState());
                messageObtain.replyTo = this.this$0.getMIncomingMessenger();
                Messenger messenger = this.this$0.mServiceMessenger;
                Intrinsics.checkNotNull(messenger);
                messenger.send(messageObtain);
                Message messageObtain2 = Message.obtain((Handler) null, LogService.INSTANCE.getMSG_Req_ServiceState());
                messageObtain2.replyTo = this.this$0.getMIncomingMessenger();
                Messenger messenger2 = this.this$0.mServiceMessenger;
                Intrinsics.checkNotNull(messenger2);
                messenger2.send(messageObtain2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName className) {
            Intrinsics.checkNotNullParameter(className, "className");
            this.this$0.mServiceMessenger = null;
            ((Switch) this.this$0._$_findCachedViewById(R.id.power_switch)).setChecked(false);
        }
    };

    /* JADX INFO: renamed from: mIncomingMessenger$delegate, reason: from kotlin metadata */
    private final Lazy mIncomingMessenger = LazyKt.lazy(new Function0<Messenger>() { // from class: com.neusoft.na.dltdroid.MainActivity$mIncomingMessenger$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Messenger invoke() {
            Looper mainLooper = this.this$0.getMainLooper();
            final MainActivity mainActivity = this.this$0;
            return new Messenger(new Handler(mainLooper) { // from class: com.neusoft.na.dltdroid.MainActivity$mIncomingMessenger$2.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    Intrinsics.checkNotNullParameter(msg, "msg");
                    if (mainActivity.handleIncomingMessage(msg)) {
                        return;
                    }
                    super.handleMessage(msg);
                }
            });
        }
    });

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

    public final int getACTION_PERMISSIONS_CHANGED() {
        return this.ACTION_PERMISSIONS_CHANGED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Messenger getMIncomingMessenger() {
        return (Messenger) this.mIncomingMessenger.getValue();
    }

    private final boolean startLogger() {
        if (this.mServiceMessenger == null) {
            startForegroundService(new Intent(this, (Class<?>) LogService.class));
            return true;
        }
        Log.w(LOGTAG, "already connected to service!");
        return true;
    }

    private final boolean stopLogger() {
        if (this.mServiceMessenger == null) {
            Log.w(LOGTAG, "server not running!");
            return false;
        }
        MainActivity mainActivity = this;
        stopService(new Intent(mainActivity, (Class<?>) LogService.class));
        bindService(new Intent(mainActivity, (Class<?>) LogService.class), this.mConnection, 8);
        return true;
    }

    private final void addSample(long timestamp, long value) {
        this.mGraphViewSeries.appendData(new DataPoint(timestamp, value), true, HISTORY_IN_S);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        List<String> listCheckPermissions = checkPermissions();
        if (!listCheckPermissions.isEmpty()) {
            Object[] array = listCheckPermissions.toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            ActivityCompat.requestPermissions(this, (String[]) array, this.ACTION_PERMISSIONS_CHANGED);
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        ((Switch) _$_findCachedViewById(R.id.power_switch)).setEnabled(true);
        ((Switch) _$_findCachedViewById(R.id.power_switch)).setOnCheckedChangeListener(this);
        ((GraphView) _$_findCachedViewById(R.id.iograph)).getViewport().setScalable(true);
        ((GraphView) _$_findCachedViewById(R.id.iograph)).getViewport().setScrollable(true);
        ((GraphView) _$_findCachedViewById(R.id.iograph)).getViewport().setXAxisBoundsManual(true);
        ((GraphView) _$_findCachedViewById(R.id.iograph)).getViewport().setMinX(jCurrentTimeMillis - ((long) (HISTORY_IN_S * 1000)));
        ((GraphView) _$_findCachedViewById(R.id.iograph)).getViewport().setMaxX(jCurrentTimeMillis);
        ((GraphView) _$_findCachedViewById(R.id.iograph)).getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() { // from class: com.neusoft.na.dltdroid.MainActivity.onCreate.1
            private final SimpleDateFormat mDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);

            @Override // com.jjoe64.graphview.DefaultLabelFormatter, com.jjoe64.graphview.LabelFormatter
            public String formatLabel(double value, boolean isValueX) {
                int i = (int) value;
                if (isValueX) {
                    String str = this.mDateFormat.format(new Date((long) value));
                    Intrinsics.checkNotNullExpressionValue(str, "{\n               mDateFo….toLong()))\n            }");
                    return str;
                }
                if (Math.abs(i) < 1024) {
                    return i + "B/s";
                }
                if (Math.abs(i) < 1048576) {
                    return (i / 1024) + "kB/s";
                }
                return (i / 1048576) + "MB/s";
            }
        });
        ((GraphView) _$_findCachedViewById(R.id.iograph)).addSeries(this.mGraphViewSeries);
        ((GraphView) _$_findCachedViewById(R.id.iograph)).getViewport().calcCompleteRange();
        bindService(new Intent(this, (Class<?>) LogService.class), this.mConnection, 8);
        String str = LOGTAG;
        Log.i(str, "Model: " + Build.MODEL);
        Log.i(str, "Product: " + Build.PRODUCT);
        Log.i(str, "Manufacturer: " + Build.MANUFACTURER);
        Log.i(str, "User: " + Build.USER);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() throws RemoteException {
        super.onResume();
        this.mIsRunning = true;
        Messenger messenger = this.mServiceMessenger;
        if (messenger != null) {
            Message messageObtain = Message.obtain((Handler) null, LogService.INSTANCE.getMSG_Req_ServiceState());
            messageObtain.replyTo = getMIncomingMessenger();
            messenger.send(messageObtain);
        }
        String ipAsString = UtilKt.getIpAsString();
        if (ipAsString != null) {
            ((TextView) _$_findCachedViewById(R.id.iplabel)).setText(getString(R.string.ip_label, new Object[]{ipAsString}));
        } else {
            ((TextView) _$_findCachedViewById(R.id.iplabel)).setText(getString(R.string.ip_label_undefined));
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mIsRunning = false;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (this.mServiceMessenger != null) {
            Message messageObtain = Message.obtain((Handler) null, LogService.INSTANCE.getMSG_Clr_LogState());
            messageObtain.replyTo = getMIncomingMessenger();
            try {
                Messenger messenger = this.mServiceMessenger;
                if (messenger != null) {
                    messenger.send(messageObtain);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            unbindService(this.mConnection);
            this.mServiceMessenger = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        getMenuInflater().inflate(R.menu.main_view, menu);
        return true;
    }

    private final void onSettingsClick() {
        startActivity(new Intent(this, (Class<?>) SettingsActivity.class));
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == R.id.action_settings) {
            onSettingsClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Intrinsics.checkNotNullParameter(compoundButton, "compoundButton");
        if (compoundButton.isEnabled() && compoundButton == ((Switch) _$_findCachedViewById(R.id.power_switch))) {
            if (b) {
                Log.i(LOGTAG, "Power On!");
                MainActivity mainActivity = this;
                Error errorCheck = new Configuration(mainActivity).check();
                if (errorCheck == Error.OK) {
                    startLogger();
                    ((Switch) compoundButton).setChecked(true);
                    return;
                } else {
                    Toast.makeText(mainActivity, getString(errorCheck.getMsgId()), 1).show();
                    stopLogger();
                    ((Switch) compoundButton).setChecked(false);
                    return;
                }
            }
            Log.i(LOGTAG, "Power Off!");
            try {
                stopLogger();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean handleIncomingMessage(Message msg) {
        int i = msg.what;
        if (i == LogService.INSTANCE.getMSG_Inf_LogState()) {
            Bundle data = msg.getData();
            int i2 = data.getInt(LogService.INSTANCE.getMSGKEY_Int_ClientCount());
            long j = data.getLong(LogService.INSTANCE.getMSGKEY_Lng_DataLogged());
            addSample(data.getLong(LogService.INSTANCE.getMSGKEY_Lng_Timestamp()), data.getLong(LogService.INSTANCE.getMSGKEY_Lng_Bandwidth()));
            Log.i(LOGTAG, "status: clients=" + i2 + ", data=" + j);
        } else if (i == LogService.INSTANCE.getMSG_Res_ServiceState()) {
            int i3 = msg.getData().getInt(LogService.INSTANCE.getMSGKEY_Int_LoggerPort());
            if (this.mIsRunning) {
                ((Switch) _$_findCachedViewById(R.id.power_switch)).setChecked(i3 > 0);
                if (i3 == 0) {
                    stopLogger();
                }
                Log.i(LOGTAG, "handshake with LogService successful!");
            }
        }
        return true;
    }

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/neusoft/na/dltdroid/MainActivity$Companion;", "", "()V", "HISTORY_IN_S", "", "LOGTAG", "", "getLOGTAG", "()Ljava/lang/String;", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getLOGTAG() {
            return MainActivity.LOGTAG;
        }
    }

    private final List<String> checkPermissions() {
        ArrayList arrayList = new ArrayList();
        if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        return arrayList;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
