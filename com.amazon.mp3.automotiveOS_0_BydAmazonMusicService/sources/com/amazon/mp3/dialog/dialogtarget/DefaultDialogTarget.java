package com.amazon.mp3.dialog.dialogtarget;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.dialog.dialogtarget.DialogTarget;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultDialogTarget extends DialogTarget {
    private final Map<String, Serializable> bundleArguments;
    private final Class<? extends DialogTargetStorable> dialogFragmentClass;

    public DefaultDialogTarget(@NonNull final Class<? extends DialogTargetStorable> dialogFragmentClass, @NonNull final Map<String, Serializable> bundleArguments) {
        Validate.notNull(dialogFragmentClass, "Dialog fragment class provided cannot be null.", new Object[0]);
        Validate.notNull(bundleArguments, "Bundle arguments cannot be null", new Object[0]);
        if (!dialogFragmentHasDefaultPublicConstructor(dialogFragmentClass)) {
            throw new IllegalArgumentException("Dialog fragment class provided must have a public constructorin order for DefaultDialogTarget to store it.");
        }
        this.dialogFragmentClass = dialogFragmentClass;
        this.bundleArguments = bundleArguments;
    }

    private boolean dialogFragmentHasDefaultPublicConstructor(final Class<? extends DialogTargetStorable> dialogFragmentClass) {
        try {
            return dialogFragmentClass.getConstructor(new Class[0]) != null;
        } catch (NoSuchMethodException unused) {
            return false;
        }
    }

    @Override // com.amazon.mp3.dialog.dialogtarget.DialogTarget
    public DialogFragment getDialogFragment() throws DialogTarget.GetDialogFragmentException {
        return reconstructFragmentFromClassName(this.dialogFragmentClass, reconstructBundleArgumentsFromMap(this.bundleArguments));
    }

    private Bundle reconstructBundleArgumentsFromMap(final Map<String, Serializable> bundleArguments) {
        Bundle bundle = new Bundle();
        for (String str : bundleArguments.keySet()) {
            Serializable serializable = bundleArguments.get(str);
            if (serializable != null) {
                bundle.putSerializable(str, serializable);
            }
        }
        return bundle;
    }

    private DialogFragment reconstructFragmentFromClassName(final Class<? extends DialogTargetStorable> classInstance, final Bundle bundle) throws DialogTarget.GetDialogFragmentException {
        try {
            return classInstance.getConstructor(new Class[0]).newInstance(new Object[0]).getInstance(bundle);
        } catch (IllegalAccessException e) {
            e = e;
            e.printStackTrace();
            throw new DialogTarget.GetDialogFragmentException("Failed to invoke the constructor in the dialog fragment class provided.", e);
        } catch (InstantiationException e2) {
            e = e2;
            e.printStackTrace();
            throw new DialogTarget.GetDialogFragmentException("Failed to invoke the constructor in the dialog fragment class provided.", e);
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            throw new DialogTarget.GetDialogFragmentException("Could not find a constructor in the dialog fragment class provided.", e3);
        } catch (InvocationTargetException e4) {
            e = e4;
            e.printStackTrace();
            throw new DialogTarget.GetDialogFragmentException("Failed to invoke the constructor in the dialog fragment class provided.", e);
        }
    }
}
