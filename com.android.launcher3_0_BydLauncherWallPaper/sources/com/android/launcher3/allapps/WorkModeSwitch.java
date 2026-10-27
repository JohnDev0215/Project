package com.android.launcher3.allapps;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Process;
import android.os.UserHandle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Switch;
import com.android.launcher3.compat.UserManagerCompat;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class WorkModeSwitch extends Switch {
    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean checked) {
    }

    public WorkModeSwitch(Context context) {
        super(context);
    }

    public WorkModeSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WorkModeSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        trySetQuietModeEnabledToAllProfilesAsync(isChecked());
    }

    private void setCheckedInternal(boolean checked) {
        super.setChecked(checked);
    }

    public void refresh() {
        setCheckedInternal(!UserManagerCompat.getInstance(getContext()).isAnyProfileQuietModeEnabled());
        setEnabled(true);
    }

    @Override // android.widget.Switch, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        return ev.getActionMasked() == 2 || super.onTouchEvent(ev);
    }

    private void trySetQuietModeEnabledToAllProfilesAsync(boolean enabled) {
        new SetQuietModeEnabledAsyncTask(enabled, new WeakReference(this)).execute(new Void[0]);
    }

    private static final class SetQuietModeEnabledAsyncTask extends AsyncTask<Void, Void, Boolean> {
        private final boolean enabled;
        private final WeakReference<WorkModeSwitch> switchWeakReference;

        SetQuietModeEnabledAsyncTask(boolean enabled, WeakReference<WorkModeSwitch> switchWeakReference) {
            this.enabled = enabled;
            this.switchWeakReference = switchWeakReference;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            WorkModeSwitch workModeSwitch = this.switchWeakReference.get();
            if (workModeSwitch != null) {
                workModeSwitch.setEnabled(false);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voids) {
            WorkModeSwitch workModeSwitch = this.switchWeakReference.get();
            boolean z = false;
            if (workModeSwitch == null) {
                return false;
            }
            Iterator<UserHandle> it = UserManagerCompat.getInstance(workModeSwitch.getContext()).getUserProfiles().iterator();
            while (it.hasNext()) {
                if (!Process.myUserHandle().equals(it.next())) {
                    z |= !r5.requestQuietModeEnabled(this.enabled, r2);
                }
            }
            return Boolean.valueOf(z);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean showConfirm) {
            WorkModeSwitch workModeSwitch;
            if (!showConfirm.booleanValue() || (workModeSwitch = this.switchWeakReference.get()) == null) {
                return;
            }
            workModeSwitch.setEnabled(true);
        }
    }
}
