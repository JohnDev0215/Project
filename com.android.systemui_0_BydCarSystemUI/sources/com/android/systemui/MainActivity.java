package com.android.systemui;

import android.os.Bundle;
import android.view.MotionEvent;
import androidx.appcompat.app.AppCompatActivity;
import com.android.systemui.statusbar.wm.GestureControl;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    private GestureControl gestureControl;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.gestureControl = (GestureControl) Dependency.get(GestureControl.class);
        finish();
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        if (this.gestureControl.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }
}
