package com.waze.sdk;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.waze.sdk.WazeSdk;
import com.waze.sdk.WazeSdkUtils;
import com.waze.sdk.ui.R$drawable;
import com.waze.sdk.ui.R$id;
import com.waze.sdk.ui.R$layout;
import com.waze.sdk.ui.R$string;
import com.waze.sdk.ui.R$style;
import com.waze.sdk.ui.R$styleable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public class WazeNavigationBar extends FrameLayout implements WazeSdk.SdkUiWidget {
    private static final int DEFAULT_STYLE_RES;
    private static final int[] INSTRUCTION_ICONS;
    private static final int[] INSTRUCTION_ICONS_LEFT_HAND;
    private boolean mBluetoothCheckEnabled;
    private BroadcastReceiver mBluetoothStateReceiver;
    private boolean mIsLeftHandTraffic;
    private boolean mIsNavigating;
    private WazeNavigationBarListener mListener;
    private TextView mNavigationDistance;
    private ImageView mNavigationInstruction;
    private View mNavigationLayout;
    private TextView mNavigationStreetName;
    private View mNormalLayout;
    private final View.OnClickListener mOnCloseButtonClickListener;
    private boolean mPendingOpenWaze;
    private final List<WazeSdkUtils.WazeStats> mPendingStats;
    private TextView mRoundaboutExitNumber;

    public interface WazeNavigationBarListener {
        boolean onCloseNavigationBar();

        void onStartSdk();
    }

    static {
        int i = R$drawable.big_direction_left;
        int i2 = R$drawable.big_direction_right;
        int i3 = R$drawable.big_direction_exit_left;
        int i4 = R$drawable.big_direction_exit_right;
        int i5 = R$drawable.big_direction_forward;
        int i6 = R$drawable.big_directions_roundabout;
        int i7 = R$drawable.big_directions_roundabout_l;
        int i8 = R$drawable.big_directions_roundabout_s;
        int i9 = R$drawable.big_directions_roundabout_r;
        int i10 = R$drawable.big_directions_roundabout_u;
        int i11 = R$drawable.big_direction_end;
        INSTRUCTION_ICONS = new int[]{0, i, i2, i3, i4, i5, i6, i6, i7, i7, i8, i8, i9, i9, i10, i10, i11, i3, i4, 0, R$drawable.big_directions_uturn};
        int i12 = R$drawable.big_directions_roundabout_lhs;
        int i13 = R$drawable.big_directions_roundabout_l_lhs;
        int i14 = R$drawable.big_directions_roundabout_s_lhs;
        int i15 = R$drawable.big_directions_roundabout_r_lhs;
        int i16 = R$drawable.big_directions_roundabout_u_lhs;
        INSTRUCTION_ICONS_LEFT_HAND = new int[]{0, i, i2, i3, i4, i5, i12, i12, i13, i13, i14, i14, i15, i15, i16, i16, i11, i3, i4, 0, R$drawable.big_directions_uturn_lhs};
        DEFAULT_STYLE_RES = R$style.WazeNavBarBlue;
    }

    public WazeNavigationBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WazeNavigationBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOnCloseButtonClickListener = new View.OnClickListener() { // from class: com.waze.sdk.WazeNavigationBar.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WazeNavigationBar.this.mListener == null || !WazeNavigationBar.this.mListener.onCloseNavigationBar()) {
                    WazeNavigationBar.this.setVisibility(8);
                }
            }
        };
        this.mPendingStats = new ArrayList();
        init(attributeSet, i, DEFAULT_STYLE_RES);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R$layout.waze_navigation_bar, this);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.WazeNavigationBar, i, i2);
        int color = typedArrayObtainStyledAttributes.getColor(R$styleable.WazeNavigationBar_navBarTextColor, -1);
        int color2 = typedArrayObtainStyledAttributes.getColor(R$styleable.WazeNavigationBar_navBarBackgroundColor, -1);
        this.mBluetoothCheckEnabled = typedArrayObtainStyledAttributes.getBoolean(R$styleable.WazeNavigationBar_enableBluetoothCheck, false);
        typedArrayObtainStyledAttributes.recycle();
        setOnClickListener(new View.OnClickListener() { // from class: com.waze.sdk.WazeNavigationBar.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WazeNavigationBar wazeNavigationBar = WazeNavigationBar.this;
                wazeNavigationBar.sendClickStatsIfPossible(wazeNavigationBar.mIsNavigating ? "AUDIOKIT_BANNER_NAV_CLICKED" : "AUDIOKIT_BANNER_START_CLICKED");
                WazeNavigationBar.this.openOrConnectToWaze();
            }
        });
        View viewFindViewById = findViewById(R$id.wazeNavBarGeneralLayout);
        this.mNormalLayout = viewFindViewById;
        viewFindViewById.setBackgroundColor(color2);
        this.mNavigationLayout = findViewById(R$id.wazeNavBarNavigationLayout);
        ImageView imageView = (ImageView) findViewById(R$id.wazeNavBarInstruction);
        this.mNavigationInstruction = imageView;
        imageView.setColorFilter(getDrawableColorFilter(color));
        TextView textView = (TextView) findViewById(R$id.wazeNavBarRoundaboutExitNumber);
        this.mRoundaboutExitNumber = textView;
        textView.setTextColor(color);
        TextView textView2 = (TextView) findViewById(R$id.wazeNavBarStreetName);
        this.mNavigationStreetName = textView2;
        textView2.setTextColor(color);
        TextView textView3 = (TextView) findViewById(R$id.wazeNavBarDistance);
        this.mNavigationDistance = textView3;
        textView3.setTextColor(color);
        this.mNavigationLayout.setBackgroundColor(color2);
        setupCloseButton(color, this.mNormalLayout);
        ((TextView) findViewById(R$id.wazeNavBarStartWazeText)).setTextColor(color);
        int i3 = R$id.wazeNavBarStartWazeDescription;
        ((TextView) findViewById(i3)).setTextColor(color);
        ((TextView) findViewById(i3)).setText(getResources().getString(R$string.waze_nav_bar_see_waze_direction, getContext().getApplicationInfo().loadLabel(getContext().getPackageManager())));
        WazeSdk.registerUiWidget(this);
        updateLayout();
    }

    public void setListener(WazeNavigationBarListener wazeNavigationBarListener) {
        this.mListener = wazeNavigationBarListener;
    }

    public void enableBluetoothDetection(boolean z) {
        boolean z2 = this.mBluetoothCheckEnabled;
        if (z2 && !z) {
            this.mBluetoothCheckEnabled = false;
            stopBluetoothDetection();
        } else {
            if (z2 || !z) {
                return;
            }
            this.mBluetoothCheckEnabled = true;
            startBluetoothDetection();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        if (this.mBluetoothCheckEnabled) {
            startBluetoothDetection();
        }
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopBluetoothDetection();
    }

    @Override // com.waze.sdk.WazeSdkCallback
    public final void onConnected() {
        updateLayout();
        Iterator<WazeSdkUtils.WazeStats> it = this.mPendingStats.iterator();
        while (it.hasNext()) {
            WazeSdk.sInstance.get().sendStats(it.next());
        }
        if (this.mPendingOpenWaze) {
            openOrConnectToWaze();
        }
    }

    @Override // com.waze.sdk.WazeSdkCallback
    public final void onDisconnected(int i) {
        this.mIsNavigating = false;
        this.mPendingOpenWaze = false;
        updateLayout();
    }

    protected boolean onBluetoothDeviceConnected(BluetoothDevice bluetoothDevice, boolean z) {
        if (!z) {
            return false;
        }
        Log.d("WazeNavBar", "Found connected in-car device: " + bluetoothDevice.getName());
        setVisibility(0);
        return true;
    }

    protected void onBluetoothDeviceDisconnected(BluetoothDevice bluetoothDevice, boolean z) {
        if (z) {
            setVisibility(8);
        }
    }

    protected void onBluetoothDetectionStarted() {
        setVisibility(8);
    }

    protected void onBluetoothDetectionFailed(int i) {
        if (i == 1) {
            Log.d("WazeNavBar", "Cannot detect bluetooth device, no permission...");
        } else if (i == 2) {
            Log.d("WazeNavBar", "Cannot detect bluetooth device, no bluetooth support...");
        }
        setVisibility(8);
    }

    protected boolean isInCarDevice(BluetoothDevice bluetoothDevice) {
        try {
            int deviceClass = bluetoothDevice.getBluetoothClass().getDeviceClass();
            return deviceClass == 1056 || deviceClass == 1032;
        } catch (Exception unused) {
            return false;
        }
    }

    private void startBluetoothDetection() {
        if (!hasBluetoothPermission()) {
            onBluetoothDetectionFailed(1);
            return;
        }
        onBluetoothDetectionStarted();
        detectBluetoothDevice();
        if (this.mBluetoothStateReceiver == null) {
            Log.d("WazeNavBar", "Register listener for bluetooth connection/disconnection...");
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
            intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
            this.mBluetoothStateReceiver = new BroadcastReceiver() { // from class: com.waze.sdk.WazeNavigationBar.3
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    BluetoothDevice bluetoothDevice;
                    if (intent.getAction() == null) {
                    }
                    if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                        if (intExtra == 10 || intExtra == 13) {
                            WazeNavigationBar.this.onBluetoothDetectionFailed(2);
                            return;
                        }
                        return;
                    }
                    bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    Log.d("WazeNavBar", String.format("Bluetooth connection state changed: %s, %s", bluetoothDevice.getName(), intent.getAction()));
                    String action = intent.getAction();
                    action.hashCode();
                    switch (action) {
                        case "android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED":
                        case "android.bluetooth.device.action.ACL_DISCONNECTED":
                            WazeNavigationBar wazeNavigationBar = WazeNavigationBar.this;
                            wazeNavigationBar.onBluetoothDeviceDisconnected(bluetoothDevice, wazeNavigationBar.isInCarDevice(bluetoothDevice));
                            WazeNavigationBar.this.detectBluetoothDevice();
                            break;
                        case "android.bluetooth.device.action.ACL_CONNECTED":
                            WazeNavigationBar wazeNavigationBar2 = WazeNavigationBar.this;
                            wazeNavigationBar2.onBluetoothDeviceConnected(bluetoothDevice, wazeNavigationBar2.isInCarDevice(bluetoothDevice));
                            break;
                    }
                }
            };
            getContext().registerReceiver(this.mBluetoothStateReceiver, intentFilter);
        }
    }

    private void stopBluetoothDetection() {
        if (this.mBluetoothStateReceiver != null) {
            Log.d("WazeNavBar", "Unregister bluetooth listener...");
            getContext().unregisterReceiver(this.mBluetoothStateReceiver);
            this.mBluetoothStateReceiver = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void detectBluetoothDevice() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || !defaultAdapter.isEnabled()) {
            onBluetoothDetectionFailed(2);
            return;
        }
        int profileConnectionState = defaultAdapter.getProfileConnectionState(1);
        Log.d("WazeNavBar", "Bluetooth state: " + profileConnectionState);
        if (profileConnectionState == 2) {
            defaultAdapter.getProfileProxy(getContext(), new BluetoothProfile.ServiceListener() { // from class: com.waze.sdk.WazeNavigationBar.4
                @Override // android.bluetooth.BluetoothProfile.ServiceListener
                public void onServiceDisconnected(int i) {
                }

                @Override // android.bluetooth.BluetoothProfile.ServiceListener
                public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                    for (BluetoothDevice bluetoothDevice : bluetoothProfile.getConnectedDevices()) {
                        WazeNavigationBar wazeNavigationBar = WazeNavigationBar.this;
                        if (wazeNavigationBar.onBluetoothDeviceConnected(bluetoothDevice, wazeNavigationBar.isInCarDevice(bluetoothDevice))) {
                            break;
                        }
                    }
                    BluetoothAdapter.getDefaultAdapter().closeProfileProxy(i, bluetoothProfile);
                }
            }, 1);
        }
    }

    private boolean hasBluetoothPermission() {
        return ContextCompat.checkSelfPermission(getContext(), "android.permission.BLUETOOTH") == 0;
    }

    private void setupCloseButton(int i, View... viewArr) {
        for (View view : viewArr) {
            ImageView imageView = (ImageView) view.findViewById(R$id.wazeNavBarCloseButton);
            if (imageView != null) {
                imageView.setOnClickListener(this.mOnCloseButtonClickListener);
                imageView.setColorFilter(i);
            }
        }
    }

    private void updateLayout() {
        if (this.mIsNavigating) {
            this.mNormalLayout.setVisibility(8);
            this.mNavigationLayout.setVisibility(0);
        } else {
            this.mNormalLayout.setVisibility(0);
            closeNavigationLayout();
        }
    }

    private void closeNavigationLayout() {
        this.mNavigationLayout.setVisibility(8);
        this.mNavigationInstruction.setImageResource(0);
        this.mNavigationDistance.setText((CharSequence) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openOrConnectToWaze() {
        if (isSdkConnected()) {
            this.mPendingOpenWaze = false;
            WazeSdk.sInstance.get().openWaze();
        } else {
            if (this.mPendingOpenWaze) {
                return;
            }
            if (WazeSdkUtils.getWazeBuildnumber(getContext()) == null) {
                WazeSdkUtils.openWazeInPlayStore(getContext());
                return;
            }
            WazeNavigationBarListener wazeNavigationBarListener = this.mListener;
            if (wazeNavigationBarListener != null) {
                wazeNavigationBarListener.onStartSdk();
            }
            this.mPendingOpenWaze = true;
        }
    }

    @Override // com.waze.sdk.WazeSdk.NavigationListener
    public void onInstructionUpdated(WazeSdkConstants$WazeInstructions wazeSdkConstants$WazeInstructions) {
        int iOrdinal = wazeSdkConstants$WazeInstructions.ordinal();
        this.mNavigationInstruction.setImageResource(this.mIsLeftHandTraffic ? INSTRUCTION_ICONS_LEFT_HAND[iOrdinal] : INSTRUCTION_ICONS[iOrdinal]);
        this.mRoundaboutExitNumber.setText((CharSequence) null);
    }

    @Override // com.waze.sdk.WazeSdk.NavigationListener
    public void onRoundaboutExitUpdated(int i) {
        if (i > 0) {
            this.mRoundaboutExitNumber.setText(Integer.toString(i));
        } else {
            this.mRoundaboutExitNumber.setText((CharSequence) null);
        }
    }

    @Override // com.waze.sdk.WazeSdk.NavigationListener
    public void onInstructionDistanceUpdated(String str, int i) {
        this.mNavigationDistance.setText(str);
    }

    @Override // com.waze.sdk.WazeSdk.NavigationListener
    public void onStreetNameChanged(String str) {
        this.mNavigationStreetName.setText(str);
    }

    @Override // com.waze.sdk.WazeSdk.NavigationListener
    public void onTrafficSideUpdated(boolean z) {
        this.mIsLeftHandTraffic = z;
    }

    @Override // com.waze.sdk.WazeSdk.NavigationListener
    public void onNavigationStatusChanged(boolean z) {
        this.mIsNavigating = z;
        updateLayout();
    }

    private ColorFilter getDrawableColorFilter(int i) {
        float f = (i >> 16) & 255;
        float f2 = (i >> 8) & 255;
        float f3 = i & 255;
        if ((0.2126f * f) + (0.7152f * f2) + (0.0722f * f3) > 127.0f) {
            return new PorterDuffColorFilter(i, PorterDuff.Mode.MULTIPLY);
        }
        return new ColorMatrixColorFilter(new ColorMatrix(new float[]{(f / 255.0f) - 1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, (f2 / 255.0f) - 1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, (f3 / 255.0f) - 1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendClickStatsIfPossible(String str) {
        WazeSdkUtils.WazeStats wazeStatsAddParam = new WazeSdkUtils.WazeStats(str).addParam("PARTNER_NAME", getContext().getPackageName());
        if (isSdkConnected()) {
            WazeSdk.sInstance.get().sendStats(wazeStatsAddParam);
        } else {
            this.mPendingStats.add(wazeStatsAddParam);
        }
    }

    private boolean isSdkConnected() {
        WeakReference<WazeSdk> weakReference = WazeSdk.sInstance;
        return (weakReference == null || weakReference.get() == null || !WazeSdk.sInstance.get().isConnected()) ? false : true;
    }
}
