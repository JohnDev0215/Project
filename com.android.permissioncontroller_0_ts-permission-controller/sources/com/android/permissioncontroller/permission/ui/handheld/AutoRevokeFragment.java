package com.android.permissioncontroller.permission.ui.handheld;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.UserHandle;
import android.view.MenuItem;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.ui.model.AutoRevokeViewModel;
import com.android.permissioncontroller.permission.ui.model.AutoRevokeViewModelFactory;
import com.android.permissioncontroller.permission.utils.AndroidUtilsKt;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import java.text.Collator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: AutoRevokeFragment.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 &2\u00020\u0001:\u0002&'B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0016\u0010\u0017\u001a\u00020\u00132\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u0019H\u0002J\u0012\u0010\u001a\u001a\u00020\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u0011H\u0016J\"\u0010!\u001a\u00020\u00112\u0018\u0010\"\u001a\u0014\u0012\u0004\u0012\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u00190#H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/android/permissioncontroller/permission/ui/handheld/AutoRevokeFragment;", "Lcom/android/permissioncontroller/permission/ui/handheld/PermissionsFrameFragment;", "()V", "collator", "Ljava/text/Collator;", "isFirstLoad", "", "sessionId", "", "viewModel", "Lcom/android/permissioncontroller/permission/ui/model/AutoRevokeViewModel;", "comparePreference", "", "lhs", "Landroidx/preference/Preference;", "rhs", "createDisableDialog", "", "packageName", "", "user", "Landroid/os/UserHandle;", "createKey", "getMostImportantGroup", "groupNames", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onStart", "updatePackages", "categorizedPackages", "", "Lcom/android/permissioncontroller/permission/ui/model/AutoRevokeViewModel$Months;", "Lcom/android/permissioncontroller/permission/ui/model/AutoRevokeViewModel$RevokedPackageInfo;", "Companion", "DisableDialog", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AutoRevokeFragment extends PermissionsFrameFragment {
    private static final float ELEVATION_HIGH = 8.0f;
    private static final String INFO_MSG_KEY = "info_msg";
    private static final long SHOW_LOAD_DELAY_MS = 200;
    private Collator collator;
    private boolean isFirstLoad;
    private long sessionId;
    private AutoRevokeViewModel viewModel;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String LOG_TAG = AutoRevokeFragment.class.getSimpleName();

    @JvmStatic
    public static final Bundle createArgs(long j) {
        return INSTANCE.createArgs(j);
    }

    @JvmStatic
    public static final AutoRevokeFragment newInstance() {
        return INSTANCE.newInstance();
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.ui.handheld.AutoRevokeFragment$updatePackages$4, reason: invalid class name */
    /* JADX INFO: compiled from: AutoRevokeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00062\u0015\u0010\u0007\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "p1", "Landroidx/preference/Preference;", "Lkotlin/ParameterName;", "name", "lhs", "p2", "rhs", "invoke"}, k = 3, mv = {1, 1, 16})
    static final /* synthetic */ class AnonymousClass4 extends FunctionReference implements Function2<Preference, Preference, Integer> {
        AnonymousClass4(AutoRevokeFragment autoRevokeFragment) {
            super(2, autoRevokeFragment);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "comparePreference";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(AutoRevokeFragment.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "comparePreference(Landroidx/preference/Preference;Landroidx/preference/Preference;)I";
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Integer invoke(Preference preference, Preference preference2) {
            return Integer.valueOf(invoke2(preference, preference2));
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final int invoke2(Preference p1, Preference p2) {
            Intrinsics.checkParameterIsNotNull(p1, "p1");
            Intrinsics.checkParameterIsNotNull(p2, "p2");
            return ((AutoRevokeFragment) this.receiver).comparePreference(p1, p2);
        }
    }

    public static final /* synthetic */ AutoRevokeViewModel access$getViewModel$p(AutoRevokeFragment $this) {
        AutoRevokeViewModel autoRevokeViewModel = $this.viewModel;
        if (autoRevokeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return autoRevokeViewModel;
    }

    /* JADX INFO: compiled from: AutoRevokeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/handheld/AutoRevokeFragment$Companion;", "", "()V", "ELEVATION_HIGH", "", "INFO_MSG_KEY", "", "LOG_TAG", "kotlin.jvm.PlatformType", "SHOW_LOAD_DELAY_MS", "", "createArgs", "Landroid/os/Bundle;", "sessionId", "newInstance", "Lcom/android/permissioncontroller/permission/ui/handheld/AutoRevokeFragment;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @JvmStatic
        public final AutoRevokeFragment newInstance() {
            return new AutoRevokeFragment();
        }

        @JvmStatic
        public final Bundle createArgs(long sessionId) {
            Bundle bundle = new Bundle();
            bundle.putLong(Constants.EXTRA_SESSION_ID, sessionId);
            return bundle;
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar;
        this.mUseShadowController = false;
        super.onCreate(savedInstanceState);
        this.isFirstLoad = true;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Collator collator = Collator.getInstance(context.getResources().getConfiguration().getLocales().get(0));
        Intrinsics.checkExpressionValueIsNotNull(collator, "Collator.getInstance(\n  …on().getLocales().get(0))");
        this.collator = collator;
        Bundle arguments = getArguments();
        if (arguments == null) {
            Intrinsics.throwNpe();
        }
        this.sessionId = arguments.getLong(Constants.EXTRA_SESSION_ID, 0L);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
        Application application = activity.getApplication();
        Intrinsics.checkExpressionValueIsNotNull(application, "activity!!.application");
        AutoRevokeViewModelFactory factory = new AutoRevokeViewModelFactory(application, this.sessionId);
        ViewModel viewModel = new ViewModelProvider(this, factory).get(AutoRevokeViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProvider(this, …okeViewModel::class.java)");
        AutoRevokeViewModel autoRevokeViewModel = (AutoRevokeViewModel) viewModel;
        this.viewModel = autoRevokeViewModel;
        if (autoRevokeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        autoRevokeViewModel.getAutoRevokedPackageCategoriesLiveData().observe(this, new Observer<Map<AutoRevokeViewModel.Months, ? extends List<? extends AutoRevokeViewModel.RevokedPackageInfo>>>() { // from class: com.android.permissioncontroller.permission.ui.handheld.AutoRevokeFragment.onCreate.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Map<AutoRevokeViewModel.Months, ? extends List<? extends AutoRevokeViewModel.RevokedPackageInfo>> map) {
                onChanged2((Map<AutoRevokeViewModel.Months, ? extends List<AutoRevokeViewModel.RevokedPackageInfo>>) map);
            }

            /* JADX INFO: renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(Map<AutoRevokeViewModel.Months, ? extends List<AutoRevokeViewModel.RevokedPackageInfo>> map) {
                if (map != null) {
                    AutoRevokeFragment.this.updatePackages(map);
                    AutoRevokeFragment.this.setLoading(false, true);
                }
            }
        });
        setHasOptionsMenu(true);
        FragmentActivity activity2 = getActivity();
        if (activity2 != null && (actionBar = activity2.getActionBar()) != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        AutoRevokeViewModel autoRevokeViewModel2 = this.viewModel;
        if (autoRevokeViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (!autoRevokeViewModel2.areAutoRevokedPackagesLoaded()) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, AndroidUtilsKt.getIPC(), null, new AnonymousClass2(null), 2, null);
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.ui.handheld.AutoRevokeFragment$onCreate$2, reason: invalid class name */
    /* JADX INFO: compiled from: AutoRevokeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.ui.handheld.AutoRevokeFragment$onCreate$2", f = "AutoRevokeFragment.kt", i = {0}, l = {106}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        private CoroutineScope p$;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = AutoRevokeFragment.this.new AnonymousClass2(completion);
            anonymousClass2.p$ = (CoroutineScope) obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$launch = this.p$;
                this.L$0 = $this$launch;
                this.label = 1;
                if (DelayKt.delay(AutoRevokeFragment.SHOW_LOAD_DELAY_MS, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure($result);
            }
            if (!AutoRevokeFragment.access$getViewModel$p(AutoRevokeFragment.this).areAutoRevokedPackagesLoaded()) {
                AutoRevokeFragment.this.setLoading(true, false);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionsFrameFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        FragmentActivity activity = getActivity();
        ActionBar ab = activity != null ? activity.getActionBar() : null;
        if (ab != null) {
            ab.setElevation(ELEVATION_HIGH);
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity2, "activity!!");
        activity2.setTitle(getString(R.string.permission_removed_page_title));
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        if (item.getItemId() == 16908332) {
            UtilsKt.pressBack(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0391  */
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updatePackages(java.util.Map<com.android.permissioncontroller.permission.ui.model.AutoRevokeViewModel.Months, ? extends java.util.List<com.android.permissioncontroller.permission.ui.model.AutoRevokeViewModel.RevokedPackageInfo>> r27) {
        /*
            Method dump skipped, instruction units count: 1044
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.ui.handheld.AutoRevokeFragment.updatePackages(java.util.Map):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int comparePreference(Preference lhs, Preference rhs) {
        Collator collator = this.collator;
        if (collator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("collator");
        }
        int result = collator.compare(lhs.getTitle().toString(), rhs.getTitle().toString());
        if (result == 0) {
            String key = lhs.getKey();
            String key2 = rhs.getKey();
            Intrinsics.checkExpressionValueIsNotNull(key2, "rhs.key");
            return key.compareTo(key2);
        }
        return result;
    }

    private final String createKey(String packageName, UserHandle user) {
        return packageName + ':' + user.getIdentifier();
    }

    private final String getMostImportantGroup(List<String> groupNames) {
        if (groupNames.contains(LocationUtils.LOCATION_PERMISSION)) {
            return LocationUtils.LOCATION_PERMISSION;
        }
        return groupNames.contains("android.permission-group.MICROPHONE") ? "android.permission-group.MICROPHONE" : groupNames.contains("android.permission-group.CAMERA") ? "android.permission-group.CAMERA" : groupNames.contains("android.permission-group.CONTACTS") ? "android.permission-group.CONTACTS" : groupNames.contains("android.permission-group.STORAGE") ? "android.permission-group.STORAGE" : groupNames.contains("android.permission-group.CALENDAR") ? "android.permission-group.CALENDAR" : groupNames.isEmpty() ^ true ? groupNames.get(0) : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createDisableDialog(String packageName, UserHandle user) {
        DisableDialog dialog = new DisableDialog();
        Bundle args = new Bundle();
        args.putString("android.intent.extra.PACKAGE_NAME", packageName);
        args.putParcelable("android.intent.extra.USER", user);
        dialog.setArguments(args);
        dialog.setCancelable(true);
        dialog.show(getChildFragmentManager().beginTransaction(), DisableDialog.class.getName());
    }

    /* JADX INFO: compiled from: AutoRevokeFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/handheld/AutoRevokeFragment$DisableDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
    public static final class DisableDialog extends DialogFragment {
        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Fragment parentFragment = getParentFragment();
            if (parentFragment == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.android.permissioncontroller.permission.ui.handheld.AutoRevokeFragment");
            }
            final AutoRevokeFragment fragment = (AutoRevokeFragment) parentFragment;
            Bundle arguments = getArguments();
            if (arguments == null) {
                Intrinsics.throwNpe();
            }
            final String packageName = arguments.getString("android.intent.extra.PACKAGE_NAME");
            if (packageName == null) {
                Intrinsics.throwNpe();
            }
            Bundle arguments2 = getArguments();
            if (arguments2 == null) {
                Intrinsics.throwNpe();
            }
            Parcelable parcelable = arguments2.getParcelable("android.intent.extra.USER");
            if (parcelable == null) {
                Intrinsics.throwNpe();
            }
            final UserHandle user = (UserHandle) parcelable;
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            AlertDialog.Builder b = new AlertDialog.Builder(context).setMessage(R.string.app_disable_dlg_text).setPositiveButton(R.string.app_disable_dlg_positive, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.AutoRevokeFragment$DisableDialog$onCreateDialog$b$1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface $noName_0, int $noName_1) throws PackageManager.NameNotFoundException {
                    AutoRevokeFragment.access$getViewModel$p(fragment).disableApp(packageName, user);
                }
            }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
            Dialog dialogCreate = b.create();
            Intrinsics.checkExpressionValueIsNotNull(dialogCreate, "b.create()");
            Dialog d = dialogCreate;
            d.setCanceledOnTouchOutside(true);
            return d;
        }
    }
}
