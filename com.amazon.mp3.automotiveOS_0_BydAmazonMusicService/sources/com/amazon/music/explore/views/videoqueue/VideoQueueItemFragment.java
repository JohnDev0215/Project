package com.amazon.music.explore.views.videoqueue;

import ExternalActionInterface.v1_0.VideoStoryContent;
import Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.LabeledIconButtonElement;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import com.amazon.music.explore.ExploreResources;
import com.amazon.music.explore.Mappers;
import com.amazon.music.explore.R;
import com.amazon.music.explore.activity.VideoQueueActivity;
import com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.widgets.views.IconButtonView;
import com.google.android.gms.common.util.CollectionUtils;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class VideoQueueItemFragment extends Fragment implements VideoQueuePlayback.PositionCallback {
    public static String INSET_BOTTOM = "INSET_BOTTOM";
    public static String INSET_LEFT = "INSET_LEFT";
    public static String INSET_RIGHT = "INSET_RIGHT";
    public static String INSET_TOP = "INSET_TOP";
    private static final int MIN_DISTANCE = 200;
    public static String VIDEO_QUEUE_ITEM_ELEMENT = "VIDEO_QUEUE_ITEM_ELEMENT";
    public static String VIDEO_QUEUE_ITEM_POSITION = "VIDEO_QUEUE_ITEM_POSITION";
    private LinearLayout actionsContainer;
    private TextView artistName;
    private CloseCallback closeCallback;
    private RelativeLayout interfaceContainer;
    private MethodsDispatcher methodsDispatcher;
    private float onTouchEndY;
    private float onTouchStartY;
    private String ownerId;
    private ImageView previewImage;
    private int queuePosition;
    private TextView subtitle;
    private TextureView textureView;
    private TextView title;
    private PlaybackTemplate videoQueueItem;
    private VideoQueuePlayback videoQueuePlayback;
    private final Logger logger = LoggerFactory.getLogger(VideoQueueItemFragment.class.getSimpleName());
    private boolean playWhenReady = false;

    public interface CloseCallback {
        void close();
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.PositionCallback
    public void onPlaybackFinished() {
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.PositionCallback
    public void onPlaybackPositionChanged(int i, long j) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.video_queue_view, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint({"ClickableViewAccessibility"})
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        VideoQueueActivity videoQueueActivity = (VideoQueueActivity) getActivity();
        this.methodsDispatcher = videoQueueActivity.getMethodsDispatcher();
        this.ownerId = videoQueueActivity.getOwnerId();
        this.closeCallback = videoQueueActivity;
        this.videoQueuePlayback = videoQueueActivity.videoQueuePlayback;
        this.interfaceContainer = (RelativeLayout) view.findViewById(R.id.video_queue_interface_container);
        this.actionsContainer = (LinearLayout) view.findViewById(R.id.video_queue_actions_container);
        this.textureView = (TextureView) view.findViewById(R.id.video_queue_texture_view);
        this.previewImage = (ImageView) view.findViewById(R.id.video_queue_preview_image);
        this.title = (TextView) view.findViewById(R.id.video_queue_primary_text);
        this.subtitle = (TextView) view.findViewById(R.id.video_queue_secondary_text);
        this.artistName = (TextView) view.findViewById(R.id.video_queue_tertiary_text);
        Bundle arguments = getArguments();
        if (arguments != null) {
            try {
                this.videoQueueItem = (PlaybackTemplate) Mappers.mapper().readValue(arguments.getString(VIDEO_QUEUE_ITEM_ELEMENT), PlaybackTemplate.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.queuePosition = arguments.getInt(VIDEO_QUEUE_ITEM_POSITION);
            setWindowInsets(arguments.getInt(INSET_LEFT), arguments.getInt(INSET_TOP), arguments.getInt(INSET_RIGHT), arguments.getInt(INSET_BOTTOM));
            if (this.playWhenReady) {
                this.playWhenReady = false;
                this.videoQueuePlayback.playQueuePosition(this.queuePosition);
            }
        }
        this.textureView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.music.explore.views.videoqueue.VideoQueueItemFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return this.f$0.lambda$onViewCreated$0(view2, motionEvent);
            }
        });
        bind();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onViewCreated$0(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.onTouchStartY = motionEvent.getY();
            return true;
        }
        if (action != 1) {
            return false;
        }
        float y = motionEvent.getY();
        this.onTouchEndY = y;
        float f = this.onTouchStartY;
        if (f - y > 200.0f) {
            int size = this.videoQueueItem.actionButtons().size();
            if (size > 1 && size % 2 == 1) {
                this.methodsDispatcher.dispatchMethods(this.ownerId, this.videoQueueItem.actionButtons().get(this.videoQueueItem.actionButtons().size() / 2).onItemSelected());
            }
        } else if (y - f > 200.0f) {
            close();
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.videoQueuePlayback.addPositionCallback(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.videoQueuePlayback.removePositionCallback(this);
    }

    public void setPlayWhenReady(boolean z) {
        this.playWhenReady = z;
    }

    public TextureView getTextureView() {
        return this.textureView;
    }

    public void close() {
        ((Activity) getContext()).finish();
    }

    private void bind() {
        bindPreviewImage();
        bindActions();
        bindText();
    }

    private void bindPreviewImage() {
        Picasso.get().load(((VideoStoryContent) this.videoQueueItem.playableContent()).uri()).fit().centerCrop().placeholder(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK)).into(this.previewImage);
    }

    private void bindActions() {
        if (this.actionsContainer.getChildCount() > 0) {
            return;
        }
        List<ButtonElement> listActionButtons = this.videoQueueItem.actionButtons();
        if (CollectionUtils.isEmpty(listActionButtons)) {
            return;
        }
        for (int i = 0; i < listActionButtons.size(); i++) {
            if (listActionButtons.get(i) instanceof LabeledIconButtonElement) {
                IconButtonView iconButtonView = new IconButtonView(getContext(), getResources().getDimensionPixelSize(R.dimen.playback_icon_button_size));
                iconButtonView.bind((LabeledIconButtonElement) listActionButtons.get(i), this.ownerId, this.methodsDispatcher);
                iconButtonView.setButtonContentDescription(ExploreResources.PLAYBACK_ACTION_BUTTON_PREFIX + i);
                setIconButtonLabelStyle(iconButtonView);
                this.actionsContainer.addView(iconButtonView);
                if (i < listActionButtons.size() - 1) {
                    Space space = new Space(getContext());
                    space.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 1.0f));
                    this.actionsContainer.addView(space);
                }
            }
        }
        centerMiddleButtonAndLabel();
    }

    private void setIconButtonLabelStyle(IconButtonView iconButtonView) {
        TextView label = iconButtonView.getLabel();
        label.setTypeface(Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_regular)));
        label.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.playback_icon_button_label_size));
        label.setLineSpacing(getResources().getDimensionPixelSize(R.dimen.playback_icon_button_line_spacing), 1.0f);
        if (getResources().getConfiguration().orientation == 2) {
            ((LinearLayout.LayoutParams) label.getLayoutParams()).topMargin = getResources().getDimensionPixelSize(R.dimen.spacer_mini);
        }
    }

    private void centerMiddleButtonAndLabel() {
        this.actionsContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.explore.views.videoqueue.VideoQueueItemFragment.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                IconButtonView iconButtonView = (IconButtonView) VideoQueueItemFragment.this.actionsContainer.getChildAt(0);
                IconButtonView iconButtonView2 = (IconButtonView) VideoQueueItemFragment.this.actionsContainer.getChildAt(VideoQueueItemFragment.this.actionsContainer.getChildCount() - 1);
                if (iconButtonView != iconButtonView2) {
                    if (iconButtonView.getWidth() > iconButtonView2.getWidth()) {
                        iconButtonView2.setMinimumWidth(iconButtonView.getWidth());
                    } else {
                        iconButtonView.setMinimumWidth(iconButtonView2.getWidth());
                    }
                }
                VideoQueueItemFragment.this.actionsContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void bindText() {
        this.title.setText(this.videoQueueItem.tertiaryText());
        this.subtitle.setText(this.videoQueueItem.primaryText());
        this.artistName.setText(this.videoQueueItem.secondaryText());
        setFontFamily();
    }

    private void setFontFamily() {
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_bold));
        Typeface typefaceCreateFromAsset2 = Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_display_condensed));
        this.title.setTypeface(typefaceCreateFromAsset);
        this.subtitle.setTypeface(typefaceCreateFromAsset2);
        this.artistName.setTypeface(typefaceCreateFromAsset);
    }

    private void setWindowInsets(int i, int i2, int i3, int i4) {
        ((ViewGroup.MarginLayoutParams) this.interfaceContainer.getLayoutParams()).setMargins(i, i2, i3, i4);
    }
}
