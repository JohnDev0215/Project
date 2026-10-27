package com.android.launcher3.shortcuts;

import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.popup.PopupContainerWithArrow;

/* JADX INFO: loaded from: classes4.dex */
public class DeepShortcutView extends FrameLayout {
    private static final Point sTempPoint = new Point();
    private BubbleTextView mBubbleText;
    private ShortcutInfo mDetail;
    private View mDivider;
    private View mIconView;
    private WorkspaceItemInfo mInfo;

    public DeepShortcutView(Context context) {
        this(context, null, 0);
    }

    public DeepShortcutView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DeepShortcutView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mBubbleText = (BubbleTextView) findViewById(R.id.bubble_text);
        this.mIconView = findViewById(R.id.icon);
        this.mDivider = findViewById(R.id.divider);
    }

    public void setDividerVisibility(int visibility) {
        this.mDivider.setVisibility(visibility);
    }

    public BubbleTextView getBubbleText() {
        return this.mBubbleText;
    }

    public void setWillDrawIcon(boolean willDraw) {
        this.mIconView.setVisibility(willDraw ? 0 : 4);
    }

    public boolean willDrawIcon() {
        return this.mIconView.getVisibility() == 0;
    }

    public Point getIconCenter() {
        Point point = sTempPoint;
        int measuredHeight = getMeasuredHeight() / 2;
        point.x = measuredHeight;
        point.y = measuredHeight;
        if (Utilities.isRtl(getResources())) {
            point.x = getMeasuredWidth() - point.x;
        }
        return point;
    }

    public void applyShortcutInfo(WorkspaceItemInfo info, ShortcutInfo detail, PopupContainerWithArrow container) {
        this.mInfo = info;
        this.mDetail = detail;
        this.mBubbleText.applyFromWorkspaceItem(info);
        this.mIconView.setBackground(this.mBubbleText.getIcon());
        CharSequence longLabel = this.mDetail.getLongLabel();
        boolean z = !TextUtils.isEmpty(longLabel) && this.mBubbleText.getPaint().measureText(longLabel.toString()) <= ((float) ((this.mBubbleText.getWidth() - this.mBubbleText.getTotalPaddingLeft()) - this.mBubbleText.getTotalPaddingRight()));
        BubbleTextView bubbleTextView = this.mBubbleText;
        if (!z) {
            longLabel = this.mDetail.getShortLabel();
        }
        bubbleTextView.setText(longLabel);
        this.mBubbleText.setOnClickListener(container.getItemClickListener());
        this.mBubbleText.setOnLongClickListener(container);
        this.mBubbleText.setOnTouchListener(container);
    }

    public WorkspaceItemInfo getFinalInfo() {
        WorkspaceItemInfo workspaceItemInfo = new WorkspaceItemInfo(this.mInfo);
        Launcher.getLauncher(getContext()).getModel().updateAndBindWorkspaceItem(workspaceItemInfo, this.mDetail);
        return workspaceItemInfo;
    }

    public View getIconView() {
        return this.mIconView;
    }

    public ShortcutInfo getDetail() {
        return this.mDetail;
    }
}
