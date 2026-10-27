package com.google.android.material.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.view.MarginLayoutParamsCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class MaterialButtonToggleGroup extends RelativeLayout {
    private static final String LOG_TAG = MaterialButtonToggleGroup.class.getSimpleName();
    private int checkedId;
    private final CheckedStateTracker checkedStateTracker;
    private final ArrayList<MaterialButton> childrenInOrder;
    private final LinkedHashSet<OnButtonCheckedListener> onButtonCheckedListeners;
    private final ArrayList<CornerData> originalCornerData;
    private final PressedStateTracker pressedStateTracker;
    private boolean singleSelection;
    private boolean skipCheckedStateTracker;

    public interface OnButtonCheckedListener {
        void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z);
    }

    public MaterialButtonToggleGroup(Context context) {
        this(context, null);
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.materialButtonToggleGroupStyle);
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.childrenInOrder = new ArrayList<>();
        this.originalCornerData = new ArrayList<>();
        this.checkedStateTracker = new CheckedStateTracker();
        this.pressedStateTracker = new PressedStateTracker();
        this.onButtonCheckedListeners = new LinkedHashSet<>();
        this.skipCheckedStateTracker = false;
        TypedArray attributes = ThemeEnforcement.obtainStyledAttributes(context, attrs, R.styleable.MaterialButtonToggleGroup, defStyleAttr, R.style.Widget_MaterialComponents_MaterialButtonToggleGroup, new int[0]);
        setSingleSelection(attributes.getBoolean(R.styleable.MaterialButtonToggleGroup_singleSelection, false));
        this.checkedId = attributes.getResourceId(R.styleable.MaterialButtonToggleGroup_checkedButton, -1);
        attributes.recycle();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i = this.checkedId;
        if (i != -1) {
            checkForced(i);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (!(child instanceof MaterialButton)) {
            Log.e(LOG_TAG, "Child views must be of type MaterialButton.");
            return;
        }
        MaterialButton buttonChild = (MaterialButton) child;
        setGeneratedIdIfNeeded(buttonChild);
        int indexToAdd = index >= 0 ? index : getChildCount();
        super.addView(buttonChild, index, new RelativeLayout.LayoutParams(params.width, params.height));
        this.childrenInOrder.add(indexToAdd, buttonChild);
        setupButtonChild(buttonChild);
        if (buttonChild.isChecked()) {
            updateCheckedStates(buttonChild.getId(), true);
            setCheckedId(buttonChild.getId());
        }
        ShapeAppearanceModel shapeAppearanceModel = buttonChild.getShapeAppearanceModel();
        this.originalCornerData.add(new CornerData(shapeAppearanceModel.getTopLeftCorner().getCornerSize(), shapeAppearanceModel.getTopRightCorner().getCornerSize(), shapeAppearanceModel.getBottomRightCorner().getCornerSize(), shapeAppearanceModel.getBottomLeftCorner().getCornerSize()));
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        if (child instanceof MaterialButton) {
            ((MaterialButton) child).removeOnCheckedChangeListener(this.checkedStateTracker);
            ((MaterialButton) child).setOnPressedChangeListenerInternal(null);
        }
        int indexOfChild = this.childrenInOrder.indexOf(child);
        if (indexOfChild >= 0) {
            this.childrenInOrder.remove(child);
            this.originalCornerData.remove(indexOfChild);
        }
        updateChildShapes();
        adjustChildMarginsAndUpdateLayout();
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        updateChildShapes();
        adjustChildMarginsAndUpdateLayout();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return MaterialButtonToggleGroup.class.getName();
    }

    public void check(int id) {
        if (id == this.checkedId) {
            return;
        }
        checkForced(id);
    }

    public void clearChecked() {
        this.skipCheckedStateTracker = true;
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton child = this.childrenInOrder.get(i);
            child.setChecked(false);
            dispatchOnButtonChecked(child.getId(), false);
        }
        this.skipCheckedStateTracker = false;
        setCheckedId(-1);
    }

    public int getCheckedButtonId() {
        if (this.singleSelection) {
            return this.checkedId;
        }
        return -1;
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList<Integer> checkedIds = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton child = this.childrenInOrder.get(i);
            if (child.isChecked()) {
                checkedIds.add(Integer.valueOf(child.getId()));
            }
        }
        return checkedIds;
    }

    public void addOnButtonCheckedListener(OnButtonCheckedListener listener) {
        this.onButtonCheckedListeners.add(listener);
    }

    public void removeOnButtonCheckedListener(OnButtonCheckedListener listener) {
        this.onButtonCheckedListeners.remove(listener);
    }

    public void clearOnButtonCheckedListeners() {
        this.onButtonCheckedListeners.clear();
    }

    public boolean isSingleSelection() {
        return this.singleSelection;
    }

    public void setSingleSelection(boolean singleSelection) {
        if (this.singleSelection != singleSelection) {
            this.singleSelection = singleSelection;
            clearChecked();
        }
    }

    public void setSingleSelection(int id) {
        setSingleSelection(getResources().getBoolean(id));
    }

    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView instanceof MaterialButton) {
            this.skipCheckedStateTracker = true;
            ((MaterialButton) checkedView).setChecked(checked);
            this.skipCheckedStateTracker = false;
        }
    }

    private void setCheckedId(int checkedId) {
        this.checkedId = checkedId;
        dispatchOnButtonChecked(checkedId, true);
    }

    private void adjustChildMarginsAndUpdateLayout() {
        for (int i = 1; i < getChildCount(); i++) {
            MaterialButton currentButton = this.childrenInOrder.get(i);
            MaterialButton previousButton = this.childrenInOrder.get(i - 1);
            int smallestStrokeWidth = Math.min(currentButton.getStrokeWidth(), previousButton.getStrokeWidth());
            RelativeLayout.LayoutParams params = buildEndAlignLayoutParams(previousButton, currentButton);
            MarginLayoutParamsCompat.setMarginEnd(params, 0);
            if (MarginLayoutParamsCompat.getMarginStart(params) != smallestStrokeWidth * (-1)) {
                MarginLayoutParamsCompat.setMarginStart(params, smallestStrokeWidth * (-1));
            }
            currentButton.setLayoutParams(params);
        }
        resetFirstChildMargin();
    }

    private void resetFirstChildMargin() {
        if (!this.childrenInOrder.isEmpty()) {
            MaterialButton currentButton = this.childrenInOrder.get(0);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) currentButton.getLayoutParams();
            MarginLayoutParamsCompat.setMarginEnd(params, 0);
            MarginLayoutParamsCompat.setMarginStart(params, 0);
            currentButton.setLayoutParams(params);
        }
    }

    private void updateChildShapes() {
        int numChildren = getChildCount();
        if (numChildren >= 1) {
            for (int i = 0; i < numChildren; i++) {
                MaterialButton button = this.childrenInOrder.get(i);
                if (button.getShapeAppearanceModel() != null) {
                    ShapeAppearanceModel shapeAppearanceModel = button.getShapeAppearanceModel();
                    CornerData cornerData = this.originalCornerData.get(i);
                    if (numChildren == 1) {
                        shapeAppearanceModel.setCornerRadii(cornerData.topLeft, cornerData.topRight, cornerData.bottomRight, cornerData.bottomLeft);
                    } else if (i == (ViewUtils.isLayoutRtl(this) ? numChildren - 1 : 0)) {
                        shapeAppearanceModel.setCornerRadii(cornerData.topLeft, 0.0f, 0.0f, cornerData.bottomLeft);
                    } else if (i != 0 && i < numChildren - 1) {
                        shapeAppearanceModel.setCornerRadius(0.0f);
                    } else if (i == (ViewUtils.isLayoutRtl(this) ? 0 : numChildren - 1)) {
                        shapeAppearanceModel.setCornerRadii(0.0f, cornerData.topRight, cornerData.bottomRight, 0.0f);
                    }
                    button.setShapeAppearanceModel(shapeAppearanceModel);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCheckedStates(int childId, boolean childIsChecked) {
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton button = this.childrenInOrder.get(i);
            if (button.isChecked()) {
                if (this.singleSelection && childIsChecked && button.getId() != childId) {
                    setCheckedStateForView(button.getId(), false);
                    dispatchOnButtonChecked(button.getId(), false);
                } else {
                    button.bringToFront();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnButtonChecked(int buttonId, boolean checked) {
        for (OnButtonCheckedListener listener : this.onButtonCheckedListeners) {
            listener.onButtonChecked(this, buttonId, checked);
        }
    }

    private void checkForced(int checkedId) {
        setCheckedStateForView(checkedId, true);
        updateCheckedStates(checkedId, true);
        setCheckedId(checkedId);
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        int id;
        int id2 = materialButton.getId();
        if (id2 == -1) {
            if (Build.VERSION.SDK_INT >= 17) {
                id = View.generateViewId();
            } else {
                id = materialButton.hashCode();
            }
            materialButton.setId(id);
        }
    }

    private void setupButtonChild(MaterialButton buttonChild) {
        buttonChild.setMaxLines(1);
        buttonChild.setEllipsize(TextUtils.TruncateAt.END);
        buttonChild.setCheckable(true);
        buttonChild.addOnCheckedChangeListener(this.checkedStateTracker);
        buttonChild.setOnPressedChangeListenerInternal(this.pressedStateTracker);
        buttonChild.setShouldDrawSurfaceColorStroke(true);
    }

    private RelativeLayout.LayoutParams buildEndAlignLayoutParams(View view, View view2) {
        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height);
        if (view == null) {
            return layoutParams2;
        }
        layoutParams2.addRule(!ViewUtils.isLayoutRtl(this) ? 1 : 0, view.getId());
        return layoutParams2;
    }

    private class CheckedStateTracker implements MaterialButton.OnCheckedChangeListener {
        private CheckedStateTracker() {
        }

        @Override // com.google.android.material.button.MaterialButton.OnCheckedChangeListener
        public void onCheckedChanged(MaterialButton button, boolean isChecked) {
            if (!MaterialButtonToggleGroup.this.skipCheckedStateTracker) {
                MaterialButtonToggleGroup.this.dispatchOnButtonChecked(button.getId(), isChecked);
                MaterialButtonToggleGroup.this.updateCheckedStates(button.getId(), isChecked);
            }
        }
    }

    private class PressedStateTracker implements MaterialButton.OnPressedChangeListener {
        private PressedStateTracker() {
        }

        @Override // com.google.android.material.button.MaterialButton.OnPressedChangeListener
        public void onPressedChanged(MaterialButton button, boolean isPressed) {
            if (!isPressed) {
                MaterialButtonToggleGroup.this.updateCheckedStates(button.getId(), button.isChecked());
            } else {
                button.bringToFront();
            }
        }
    }

    private static class CornerData {
        final float bottomLeft;
        final float bottomRight;
        final float topLeft;
        final float topRight;

        CornerData(float topLeft, float topRight, float bottomRight, float bottomLeft) {
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomRight = bottomRight;
            this.bottomLeft = bottomLeft;
        }
    }
}
