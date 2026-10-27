package android.service.dreams;

import android.app.Activity;
import android.os.Bundle;
import android.service.dreams.DreamService;
import android.view.WindowInsets;
import com.android.internal.R;

/* JADX INFO: loaded from: classes3.dex */
public class DreamActivity extends Activity {
    static final String EXTRA_CALLBACK = "binder";

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DreamService.DreamServiceWrapper dreamServiceWrapper = (DreamService.DreamServiceWrapper) getIntent().getIBinderExtra("binder");
        if (dreamServiceWrapper != null) {
            dreamServiceWrapper.onActivityCreated(this);
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        getWindow().getInsetsController().hide(WindowInsets.Type.systemBars());
        overridePendingTransition(R.anim.dream_activity_open_enter, R.anim.dream_activity_open_exit);
    }

    @Override // android.app.Activity
    public void finishAndRemoveTask() {
        super.finishAndRemoveTask();
        overridePendingTransition(0, R.anim.dream_activity_close_exit);
    }
}
