package com.neusoft.na.ehp;

import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.neusoft.na.ehp.InputCfgActivity;
import java.util.HashMap;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes.dex */
public class InputCfgActivity extends AppCompatActivity {
    private static final String TAG = "InputCfgActivity";
    private FrameLayout.LayoutParams frameLayoutParams;
    private int usableHeightPrevious;

    public static class InputItemValue {
        public String defaultValue;
        public String name;

        InputItemValue(String str, String str2) {
            this.name = str;
            this.defaultValue = str2;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_input_cfg);
        final SharedPreferences sharedPreferences = getSharedPreferences("InputCfgValue", 0);
        final SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        findViewById(R.id.InputCfgclose).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.InputCfgActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m40lambda$onCreate$0$comneusoftnaehpInputCfgActivity(view);
            }
        });
        final View childAt = ((FrameLayout) findViewById(android.R.id.content)).getChildAt(0);
        final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        childAt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.neusoft.na.ehp.InputCfgActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Rect rect = new Rect();
                childAt.getWindowVisibleDisplayFrame(rect);
                int i = rect.bottom - rect.top;
                if (i != InputCfgActivity.this.usableHeightPrevious) {
                    int height = childAt.getRootView().getHeight();
                    if (height - i > height / 4) {
                        FrameLayout.LayoutParams layoutParams2 = layoutParams;
                        layoutParams2.height = 530;
                        layoutParams2.height = 530;
                    } else {
                        layoutParams.height = childAt.getRootView().getHeight();
                    }
                    childAt.requestLayout();
                    InputCfgActivity.this.usableHeightPrevious = i;
                    Log.i(InputCfgActivity.TAG, " usableHeightNow = " + i);
                }
            }
        });
        final HashMap map = new HashMap();
        map.put((TextInputEditText) findViewById(R.id.JamApproachDistance), new InputItemValue("JamApproachDistance", "100"));
        map.put((TextInputEditText) findViewById(R.id.JamDisableTime), new InputItemValue("JamDisableTime", "5"));
        map.put((TextInputEditText) findViewById(R.id.ConstructionApproachDistance), new InputItemValue("ConstructionApproachDistance", "100"));
        map.put((TextInputEditText) findViewById(R.id.ConstructionDisableTime), new InputItemValue("ConstructionDisableTime", "5"));
        map.put((TextInputEditText) findViewById(R.id.AccidentApproachDistance), new InputItemValue("AccidentApproachDistance", "200"));
        map.put((TextInputEditText) findViewById(R.id.AccidentDisableTime), new InputItemValue("AccidentDisableTime", "5"));
        map.put((TextInputEditText) findViewById(R.id.RoundaboutApproachDistance), new InputItemValue("RoundaboutApproachDistance", "100"));
        map.put((TextInputEditText) findViewById(R.id.RoundaboutApproachTime), new InputItemValue("RoundaboutApproachTime", "5"));
        map.put((TextInputEditText) findViewById(R.id.RoundaboutDisableTime), new InputItemValue("RoundaboutDisableTime", "3"));
        map.put((TextInputEditText) findViewById(R.id.JunctionApproachDistance), new InputItemValue("JunctionApproachDistance", "40"));
        map.put((TextInputEditText) findViewById(R.id.JunctionApproachTime), new InputItemValue("JunctionApproachTime", "5"));
        map.put((TextInputEditText) findViewById(R.id.JunctionDisableTime), new InputItemValue("JunctionDisableTime", "3"));
        map.put((TextInputEditText) findViewById(R.id.TrafficLightApproachDistance), new InputItemValue("TrafficLightApproachDistance", "90"));
        map.put((TextInputEditText) findViewById(R.id.TrafficLightApproachTime), new InputItemValue("TrafficLightApproachTime", "0"));
        map.put((TextInputEditText) findViewById(R.id.TrafficLightDisableTime), new InputItemValue("TrafficLightDisableTime", "3"));
        map.put((TextInputEditText) findViewById(R.id.StopApproachDistance), new InputItemValue("StopApproachDistance", "50"));
        map.put((TextInputEditText) findViewById(R.id.StopApproachTime), new InputItemValue("StopApproachTime", "0"));
        map.put((TextInputEditText) findViewById(R.id.StopDisableTime), new InputItemValue("StopDisableTime", "3"));
        map.put((TextInputEditText) findViewById(R.id.YieldApproachDistance), new InputItemValue("YieldApproachDistance", "50"));
        map.put((TextInputEditText) findViewById(R.id.YieldApproachTime), new InputItemValue("YieldApproachTime", "0"));
        map.put((TextInputEditText) findViewById(R.id.YieldDisableTime), new InputItemValue("YieldDisableTime", "3"));
        map.forEach(new BiConsumer() { // from class: com.neusoft.na.ehp.InputCfgActivity$$ExternalSyntheticLambda3
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.m41lambda$onCreate$1$comneusoftnaehpInputCfgActivity(sharedPreferences, editorEdit, (TextInputEditText) obj, (InputCfgActivity.InputItemValue) obj2);
            }
        });
        findViewById(R.id.Reset).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.InputCfgActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InputCfgActivity.lambda$onCreate$3(map, editorEdit, view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onCreate$0$com-neusoft-na-ehp-InputCfgActivity, reason: not valid java name */
    /* synthetic */ void m40lambda$onCreate$0$comneusoftnaehpInputCfgActivity(View view) {
        finish();
    }

    /* JADX INFO: renamed from: lambda$onCreate$1$com-neusoft-na-ehp-InputCfgActivity, reason: not valid java name */
    /* synthetic */ void m41lambda$onCreate$1$comneusoftnaehpInputCfgActivity(SharedPreferences sharedPreferences, final SharedPreferences.Editor editor, TextInputEditText textInputEditText, final InputItemValue inputItemValue) {
        textInputEditText.setText(sharedPreferences.getString(inputItemValue.name, inputItemValue.defaultValue));
        textInputEditText.addTextChangedListener(new TextWatcher() { // from class: com.neusoft.na.ehp.InputCfgActivity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Log.i(InputCfgActivity.TAG, inputItemValue.name + " afterTextChanged:" + editable.toString());
                editor.putString(inputItemValue.name, editable.toString());
                editor.apply();
            }
        });
    }

    static /* synthetic */ void lambda$onCreate$3(HashMap map, final SharedPreferences.Editor editor, View view) {
        map.forEach(new BiConsumer() { // from class: com.neusoft.na.ehp.InputCfgActivity$$ExternalSyntheticLambda2
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                InputCfgActivity.lambda$onCreate$2(editor, (TextInputEditText) obj, (InputCfgActivity.InputItemValue) obj2);
            }
        });
        editor.apply();
    }

    static /* synthetic */ void lambda$onCreate$2(SharedPreferences.Editor editor, TextInputEditText textInputEditText, InputItemValue inputItemValue) {
        textInputEditText.setText(inputItemValue.defaultValue);
        editor.putString(inputItemValue.name, inputItemValue.defaultValue);
    }
}
