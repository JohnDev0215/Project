package com.android.car.developeroptions.password;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LifecycleOwner;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.google.android.setupcompat.util.WizardManagerHelper;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ChooseLockTypeDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
    private ScreenLockAdapter mAdapter;
    private ChooseLockGenericController mController;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 990;
    }

    public static ChooseLockTypeDialogFragment newInstance(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("userId", i);
        ChooseLockTypeDialogFragment chooseLockTypeDialogFragment = new ChooseLockTypeDialogFragment();
        chooseLockTypeDialogFragment.setArguments(bundle);
        return chooseLockTypeDialogFragment;
    }

    public interface OnLockTypeSelectedListener {
        void onLockTypeSelected(ScreenLockType screenLockType);

        default void startChooseLockActivity(ScreenLockType screenLockType, Activity activity) {
            Intent intent = activity.getIntent();
            Intent intent2 = new Intent(activity, (Class<?>) SetupChooseLockGeneric.class);
            intent2.addFlags(33554432);
            ChooseLockTypeDialogFragment.copyBooleanExtra(intent, intent2, "has_challenge", false);
            ChooseLockTypeDialogFragment.copyBooleanExtra(intent, intent2, "show_options_button", false);
            if (intent.hasExtra("choose_lock_generic_extras")) {
                intent2.putExtras(intent.getBundleExtra("choose_lock_generic_extras"));
            }
            intent2.putExtra("lockscreen.password_type", screenLockType.defaultQuality);
            intent2.putExtra("challenge", intent.getLongExtra("challenge", 0L));
            WizardManagerHelper.copyWizardManagerExtras(intent, intent2);
            activity.startActivity(intent2);
            activity.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void copyBooleanExtra(Intent intent, Intent intent2, String str, boolean z) {
        intent2.putExtra(str, intent.getBooleanExtra(str, z));
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mController = new ChooseLockGenericController(getContext(), getArguments().getInt("userId"));
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        OnLockTypeSelectedListener onLockTypeSelectedListener;
        LifecycleOwner parentFragment = getParentFragment();
        if (parentFragment instanceof OnLockTypeSelectedListener) {
            onLockTypeSelectedListener = (OnLockTypeSelectedListener) parentFragment;
        } else {
            Object context = getContext();
            onLockTypeSelectedListener = context instanceof OnLockTypeSelectedListener ? (OnLockTypeSelectedListener) context : null;
        }
        if (onLockTypeSelectedListener != null) {
            onLockTypeSelectedListener.onLockTypeSelected(this.mAdapter.getItem(i));
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Context context = getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ScreenLockAdapter screenLockAdapter = new ScreenLockAdapter(context, this.mController.getVisibleScreenLockTypes(65536, false), this.mController);
        this.mAdapter = screenLockAdapter;
        builder.setAdapter(screenLockAdapter, this);
        builder.setTitle(R.string.setup_lock_settings_options_dialog_title);
        return builder.create();
    }

    private static class ScreenLockAdapter extends ArrayAdapter<ScreenLockType> {
        private final ChooseLockGenericController mController;

        ScreenLockAdapter(Context context, List<ScreenLockType> list, ChooseLockGenericController chooseLockGenericController) {
            super(context, R.layout.choose_lock_dialog_item, list);
            this.mController = chooseLockGenericController;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            Context context = viewGroup.getContext();
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.choose_lock_dialog_item, viewGroup, false);
            }
            ScreenLockType item = getItem(i);
            TextView textView = (TextView) view;
            textView.setText(this.mController.getTitle(item));
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(getIconForScreenLock(context, item), (Drawable) null, (Drawable) null, (Drawable) null);
            return view;
        }

        private static Drawable getIconForScreenLock(Context context, ScreenLockType screenLockType) {
            int i = AnonymousClass1.$SwitchMap$com$android$car$developeroptions$password$ScreenLockType[screenLockType.ordinal()];
            if (i == 1) {
                return context.getDrawable(R.drawable.ic_pattern);
            }
            if (i == 2) {
                return context.getDrawable(R.drawable.ic_pin);
            }
            if (i != 3) {
                return null;
            }
            return context.getDrawable(R.drawable.ic_password);
        }
    }

    /* JADX INFO: renamed from: com.android.car.developeroptions.password.ChooseLockTypeDialogFragment$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$car$developeroptions$password$ScreenLockType;

        static {
            int[] iArr = new int[ScreenLockType.values().length];
            $SwitchMap$com$android$car$developeroptions$password$ScreenLockType = iArr;
            try {
                iArr[ScreenLockType.PATTERN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.PIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.PASSWORD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.SWIPE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.MANAGED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
