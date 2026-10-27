package com.android.car.developeroptions.applications;

import android.app.ActivityManager;
import android.app.ApplicationErrorReport;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.applications.RunningProcessesView;
import com.android.car.developeroptions.applications.RunningState;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class RunningServiceDetails extends InstrumentedFragment implements RunningState.OnRefreshUiListener {
    ViewGroup mAllDetails;
    ActivityManager mAm;
    boolean mHaveData;
    LayoutInflater mInflater;
    RunningState.MergedItem mMergedItem;
    int mNumProcesses;
    int mNumServices;
    String mProcessName;
    TextView mProcessesHeader;
    TextView mServicesHeader;
    boolean mShowBackground;
    ViewGroup mSnippet;
    RunningProcessesView.ActiveItem mSnippetActiveItem;
    RunningProcessesView.ViewHolder mSnippetViewHolder;
    RunningState mState;
    int mUid;
    int mUserId;
    final ArrayList<ActiveDetail> mActiveDetails = new ArrayList<>();
    StringBuilder mBuilder = new StringBuilder(128);

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 85;
    }

    class ActiveDetail implements View.OnClickListener {
        RunningProcessesView.ActiveItem mActiveItem;
        ComponentName mInstaller;
        PendingIntent mManageIntent;
        Button mReportButton;
        View mRootView;
        RunningState.ServiceItem mServiceItem;
        Button mStopButton;
        RunningProcessesView.ViewHolder mViewHolder;

        ActiveDetail() {
        }

        void stopActiveService(boolean z) {
            RunningState.ServiceItem serviceItem = this.mServiceItem;
            if (!z && (serviceItem.mServiceInfo.applicationInfo.flags & 1) != 0) {
                RunningServiceDetails.this.showConfirmStopDialog(serviceItem.mRunningService.service);
                return;
            }
            RunningServiceDetails.this.getActivity().stopService(new Intent().setComponent(serviceItem.mRunningService.service));
            RunningServiceDetails runningServiceDetails = RunningServiceDetails.this;
            RunningState.MergedItem mergedItem = runningServiceDetails.mMergedItem;
            if (mergedItem == null) {
                runningServiceDetails.mState.updateNow();
                RunningServiceDetails.this.finish();
            } else if (!runningServiceDetails.mShowBackground && mergedItem.mServices.size() <= 1) {
                RunningServiceDetails.this.mState.updateNow();
                RunningServiceDetails.this.finish();
            } else {
                RunningServiceDetails.this.mState.updateNow();
            }
        }

        /* JADX WARN: Can't wrap try/catch for region: R(13:4|(1:6)(1:7)|8|(1:10)(1:11)|12|88|13|(2:78|14)|81|15|(6:73|28|86|29|30|72)|42|43) */
        /* JADX WARN: Removed duplicated region for block: B:84:0x0138 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // android.view.View.OnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onClick(android.view.View r13) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 407
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.applications.RunningServiceDetails.ActiveDetail.onClick(android.view.View):void");
        }
    }

    boolean findMergedItem() {
        RunningState.MergedItem mergedItem;
        int i;
        String str;
        RunningState.ProcessItem processItem;
        RunningState.ProcessItem processItem2;
        ArrayList<RunningState.MergedItem> currentBackgroundItems = this.mShowBackground ? this.mState.getCurrentBackgroundItems() : this.mState.getCurrentMergedItems();
        if (currentBackgroundItems != null) {
            for (int i2 = 0; i2 < currentBackgroundItems.size(); i2++) {
                mergedItem = currentBackgroundItems.get(i2);
                if (mergedItem.mUserId == this.mUserId && (((i = this.mUid) < 0 || (processItem2 = mergedItem.mProcess) == null || processItem2.mUid == i) && ((str = this.mProcessName) == null || ((processItem = mergedItem.mProcess) != null && str.equals(processItem.mProcessName))))) {
                    break;
                }
            }
            mergedItem = null;
        } else {
            mergedItem = null;
        }
        if (this.mMergedItem == mergedItem) {
            return false;
        }
        this.mMergedItem = mergedItem;
        return true;
    }

    void addServicesHeader() {
        if (this.mNumServices == 0) {
            TextView textView = (TextView) this.mInflater.inflate(R.layout.separator_label, this.mAllDetails, false);
            this.mServicesHeader = textView;
            textView.setText(R.string.runningservicedetails_services_title);
            this.mAllDetails.addView(this.mServicesHeader);
        }
        this.mNumServices++;
    }

    void addProcessesHeader() {
        if (this.mNumProcesses == 0) {
            TextView textView = (TextView) this.mInflater.inflate(R.layout.separator_label, this.mAllDetails, false);
            this.mProcessesHeader = textView;
            textView.setText(R.string.runningservicedetails_processes_title);
            this.mAllDetails.addView(this.mProcessesHeader);
        }
        this.mNumProcesses++;
    }

    void addServiceDetailsView(RunningState.ServiceItem serviceItem, RunningState.MergedItem mergedItem, boolean z, boolean z2) {
        if (z) {
            addServicesHeader();
        } else if (mergedItem.mUserId != UserHandle.myUserId()) {
            addProcessesHeader();
        }
        RunningState.BaseItem baseItem = serviceItem != null ? serviceItem : mergedItem;
        ActiveDetail activeDetail = new ActiveDetail();
        View viewInflate = this.mInflater.inflate(R.layout.running_service_details_service, this.mAllDetails, false);
        this.mAllDetails.addView(viewInflate);
        activeDetail.mRootView = viewInflate;
        activeDetail.mServiceItem = serviceItem;
        RunningProcessesView.ViewHolder viewHolder = new RunningProcessesView.ViewHolder(viewInflate);
        activeDetail.mViewHolder = viewHolder;
        activeDetail.mActiveItem = viewHolder.bind(this.mState, baseItem, this.mBuilder);
        if (!z2) {
            viewInflate.findViewById(R.id.service).setVisibility(8);
        }
        if (serviceItem != null) {
            ActivityManager.RunningServiceInfo runningServiceInfo = serviceItem.mRunningService;
            if (runningServiceInfo.clientLabel != 0) {
                activeDetail.mManageIntent = this.mAm.getRunningServiceControlPanel(runningServiceInfo.service);
            }
        }
        TextView textView = (TextView) viewInflate.findViewById(R.id.comp_description);
        activeDetail.mStopButton = (Button) viewInflate.findViewById(R.id.left_button);
        activeDetail.mReportButton = (Button) viewInflate.findViewById(R.id.right_button);
        if (z && mergedItem.mUserId != UserHandle.myUserId()) {
            textView.setVisibility(8);
            viewInflate.findViewById(R.id.control_buttons_panel).setVisibility(8);
        } else {
            if (serviceItem != null && serviceItem.mServiceInfo.descriptionRes != 0) {
                PackageManager packageManager = getActivity().getPackageManager();
                ServiceInfo serviceInfo = serviceItem.mServiceInfo;
                textView.setText(packageManager.getText(serviceInfo.packageName, serviceInfo.descriptionRes, serviceInfo.applicationInfo));
            } else if (mergedItem.mBackground) {
                textView.setText(R.string.background_process_stop_description);
            } else if (activeDetail.mManageIntent != null) {
                try {
                    textView.setText(getActivity().getString(R.string.service_manage_description, new Object[]{getActivity().getPackageManager().getResourcesForApplication(serviceItem.mRunningService.clientPackage).getString(serviceItem.mRunningService.clientLabel)}));
                } catch (PackageManager.NameNotFoundException unused) {
                }
            } else {
                textView.setText(getActivity().getText(serviceItem != null ? R.string.service_stop_description : R.string.heavy_weight_stop_description));
            }
            activeDetail.mStopButton.setOnClickListener(activeDetail);
            activeDetail.mStopButton.setText(getActivity().getText(activeDetail.mManageIntent != null ? R.string.service_manage : R.string.service_stop));
            activeDetail.mReportButton.setOnClickListener(activeDetail);
            activeDetail.mReportButton.setText(android.R.string.orgTypeCustom);
            if (Settings.Global.getInt(getActivity().getContentResolver(), "send_action_app_error", 0) != 0 && serviceItem != null) {
                FragmentActivity activity = getActivity();
                ServiceInfo serviceInfo2 = serviceItem.mServiceInfo;
                ComponentName errorReportReceiver = ApplicationErrorReport.getErrorReportReceiver(activity, serviceInfo2.packageName, serviceInfo2.applicationInfo.flags);
                activeDetail.mInstaller = errorReportReceiver;
                activeDetail.mReportButton.setEnabled(errorReportReceiver != null);
            } else {
                activeDetail.mReportButton.setEnabled(false);
            }
        }
        this.mActiveDetails.add(activeDetail);
    }

    void addProcessDetailsView(RunningState.ProcessItem processItem, boolean z) {
        addProcessesHeader();
        ActiveDetail activeDetail = new ActiveDetail();
        View viewInflate = this.mInflater.inflate(R.layout.running_service_details_process, this.mAllDetails, false);
        this.mAllDetails.addView(viewInflate);
        activeDetail.mRootView = viewInflate;
        RunningProcessesView.ViewHolder viewHolder = new RunningProcessesView.ViewHolder(viewInflate);
        activeDetail.mViewHolder = viewHolder;
        activeDetail.mActiveItem = viewHolder.bind(this.mState, processItem, this.mBuilder);
        TextView textView = (TextView) viewInflate.findViewById(R.id.comp_description);
        if (processItem.mUserId != UserHandle.myUserId()) {
            textView.setVisibility(8);
        } else if (z) {
            textView.setText(R.string.main_running_process_description);
        } else {
            CharSequence charSequenceMakeLabel = null;
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = processItem.mRunningProcessInfo;
            ComponentName componentName = runningAppProcessInfo.importanceReasonComponent;
            int i = runningAppProcessInfo.importanceReasonCode;
            if (i != 1) {
                if (i != 2) {
                    i = 0;
                } else {
                    i = R.string.process_service_in_use_description;
                    if (componentName != null) {
                        ServiceInfo serviceInfo = getActivity().getPackageManager().getServiceInfo(runningAppProcessInfo.importanceReasonComponent, 0);
                        charSequenceMakeLabel = RunningState.makeLabel(getActivity().getPackageManager(), serviceInfo.name, serviceInfo);
                    }
                }
                if (i != 0 && charSequenceMakeLabel != null) {
                    textView.setText(getActivity().getString(i, new Object[]{charSequenceMakeLabel}));
                }
            } else {
                i = R.string.process_provider_in_use_description;
                if (componentName != null) {
                    ProviderInfo providerInfo = getActivity().getPackageManager().getProviderInfo(runningAppProcessInfo.importanceReasonComponent, 0);
                    charSequenceMakeLabel = RunningState.makeLabel(getActivity().getPackageManager(), providerInfo.name, providerInfo);
                }
                if (i != 0) {
                    textView.setText(getActivity().getString(i, new Object[]{charSequenceMakeLabel}));
                }
            }
        }
        this.mActiveDetails.add(activeDetail);
    }

    void addDetailsViews(RunningState.MergedItem mergedItem, boolean z, boolean z2) {
        RunningState.ProcessItem processItem;
        if (mergedItem != null) {
            if (z) {
                for (int i = 0; i < mergedItem.mServices.size(); i++) {
                    addServiceDetailsView(mergedItem.mServices.get(i), mergedItem, true, true);
                }
            }
            if (z2) {
                if (mergedItem.mServices.size() <= 0) {
                    addServiceDetailsView(null, mergedItem, false, mergedItem.mUserId != UserHandle.myUserId());
                    return;
                }
                int i2 = -1;
                while (i2 < mergedItem.mOtherProcesses.size()) {
                    if (i2 < 0) {
                        processItem = mergedItem.mProcess;
                    } else {
                        processItem = mergedItem.mOtherProcesses.get(i2);
                    }
                    if (processItem == null || processItem.mPid > 0) {
                        addProcessDetailsView(processItem, i2 < 0);
                    }
                    i2++;
                }
            }
        }
    }

    void addDetailViews() {
        ArrayList<RunningState.MergedItem> arrayList;
        for (int size = this.mActiveDetails.size() - 1; size >= 0; size--) {
            this.mAllDetails.removeView(this.mActiveDetails.get(size).mRootView);
        }
        this.mActiveDetails.clear();
        TextView textView = this.mServicesHeader;
        if (textView != null) {
            this.mAllDetails.removeView(textView);
            this.mServicesHeader = null;
        }
        TextView textView2 = this.mProcessesHeader;
        if (textView2 != null) {
            this.mAllDetails.removeView(textView2);
            this.mProcessesHeader = null;
        }
        this.mNumProcesses = 0;
        this.mNumServices = 0;
        RunningState.MergedItem mergedItem = this.mMergedItem;
        if (mergedItem != null) {
            if (mergedItem.mUser != null) {
                if (this.mShowBackground) {
                    arrayList = new ArrayList<>(this.mMergedItem.mChildren);
                    Collections.sort(arrayList, this.mState.mBackgroundComparator);
                } else {
                    arrayList = mergedItem.mChildren;
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    addDetailsViews(arrayList.get(i), true, false);
                }
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    addDetailsViews(arrayList.get(i2), false, true);
                }
                return;
            }
            addDetailsViews(mergedItem, true, true);
        }
    }

    void refreshUi(boolean z) {
        if (findMergedItem()) {
            z = true;
        }
        if (z) {
            RunningState.MergedItem mergedItem = this.mMergedItem;
            if (mergedItem != null) {
                this.mSnippetActiveItem = this.mSnippetViewHolder.bind(this.mState, mergedItem, this.mBuilder);
            } else {
                RunningProcessesView.ActiveItem activeItem = this.mSnippetActiveItem;
                if (activeItem != null) {
                    activeItem.mHolder.size.setText("");
                    this.mSnippetActiveItem.mHolder.uptime.setText("");
                    this.mSnippetActiveItem.mHolder.description.setText(R.string.no_services);
                } else {
                    finish();
                    return;
                }
            }
            addDetailViews();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish() {
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.applications.-$$Lambda$RunningServiceDetails$Cm9-6wxkKZHMm9WaeSL2dVtCK-Y
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$finish$0$RunningServiceDetails();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$finish$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$finish$0$RunningServiceDetails() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        this.mUid = getArguments().getInt("uid", -1);
        this.mUserId = getArguments().getInt("user_id", 0);
        this.mProcessName = getArguments().getString("process", null);
        this.mShowBackground = getArguments().getBoolean("background", false);
        this.mAm = (ActivityManager) getActivity().getSystemService("activity");
        this.mInflater = (LayoutInflater) getActivity().getSystemService("layout_inflater");
        this.mState = RunningState.getInstance(getActivity());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.running_service_details, viewGroup, false);
        Utils.prepareCustomPreferencesList(viewGroup, viewInflate, viewInflate, false);
        this.mAllDetails = (ViewGroup) viewInflate.findViewById(R.id.all_details);
        ViewGroup viewGroup2 = (ViewGroup) viewInflate.findViewById(R.id.snippet);
        this.mSnippet = viewGroup2;
        this.mSnippetViewHolder = new RunningProcessesView.ViewHolder(viewGroup2);
        ensureData();
        return viewInflate;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mHaveData = false;
        this.mState.pause();
    }

    @Override // com.android.car.developeroptions.core.InstrumentedFragment, com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ensureData();
    }

    ActiveDetail activeDetailForService(ComponentName componentName) {
        ActivityManager.RunningServiceInfo runningServiceInfo;
        for (int i = 0; i < this.mActiveDetails.size(); i++) {
            ActiveDetail activeDetail = this.mActiveDetails.get(i);
            RunningState.ServiceItem serviceItem = activeDetail.mServiceItem;
            if (serviceItem != null && (runningServiceInfo = serviceItem.mRunningService) != null && componentName.equals(runningServiceInfo.service)) {
                return activeDetail;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showConfirmStopDialog(ComponentName componentName) {
        MyAlertDialogFragment myAlertDialogFragmentNewConfirmStop = MyAlertDialogFragment.newConfirmStop(1, componentName);
        myAlertDialogFragmentNewConfirmStop.setTargetFragment(this, 0);
        myAlertDialogFragmentNewConfirmStop.show(getFragmentManager(), "confirmstop");
    }

    public static class MyAlertDialogFragment extends InstrumentedDialogFragment {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 536;
        }

        public static MyAlertDialogFragment newConfirmStop(int i, ComponentName componentName) {
            MyAlertDialogFragment myAlertDialogFragment = new MyAlertDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", i);
            bundle.putParcelable("comp", componentName);
            myAlertDialogFragment.setArguments(bundle);
            return myAlertDialogFragment;
        }

        RunningServiceDetails getOwner() {
            return (RunningServiceDetails) getTargetFragment();
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            int i = getArguments().getInt("id");
            if (i == 1) {
                final ComponentName componentName = (ComponentName) getArguments().getParcelable("comp");
                if (getOwner().activeDetailForService(componentName) == null) {
                    return null;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getActivity().getString(R.string.runningservicedetails_stop_dlg_title));
                builder.setMessage(getActivity().getString(R.string.runningservicedetails_stop_dlg_text));
                builder.setPositiveButton(R.string.dlg_ok, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.applications.RunningServiceDetails.MyAlertDialogFragment.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ActiveDetail activeDetailActiveDetailForService = MyAlertDialogFragment.this.getOwner().activeDetailForService(componentName);
                        if (activeDetailActiveDetailForService != null) {
                            activeDetailActiveDetailForService.stopActiveService(true);
                        }
                    }
                });
                builder.setNegativeButton(R.string.dlg_cancel, (DialogInterface.OnClickListener) null);
                return builder.create();
            }
            throw new IllegalArgumentException("unknown id " + i);
        }
    }

    void ensureData() {
        if (this.mHaveData) {
            return;
        }
        this.mHaveData = true;
        this.mState.resume(this);
        this.mState.waitForData();
        refreshUi(true);
    }

    void updateTimes() {
        RunningProcessesView.ActiveItem activeItem = this.mSnippetActiveItem;
        if (activeItem != null) {
            activeItem.updateTime(getActivity(), this.mBuilder);
        }
        for (int i = 0; i < this.mActiveDetails.size(); i++) {
            this.mActiveDetails.get(i).mActiveItem.updateTime(getActivity(), this.mBuilder);
        }
    }

    @Override // com.android.car.developeroptions.applications.RunningState.OnRefreshUiListener
    public void onRefreshUi(int i) {
        if (getActivity() == null) {
            return;
        }
        if (i == 0) {
            updateTimes();
            return;
        }
        if (i == 1) {
            refreshUi(false);
            updateTimes();
        } else {
            if (i != 2) {
                return;
            }
            refreshUi(true);
            updateTimes();
        }
    }
}
