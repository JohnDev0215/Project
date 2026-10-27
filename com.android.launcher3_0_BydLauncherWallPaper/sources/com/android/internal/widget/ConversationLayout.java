package com.android.internal.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.Person;
import android.app.RemoteInputHistoryItem;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.RemotableViewMethod;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.android.internal.R;
import com.android.internal.graphics.ColorUtils;
import com.android.internal.util.ContrastColorUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
@RemoteViews.RemoteView
public class ConversationLayout extends FrameLayout implements ImageMessageConsumer, IMessagingLayout {
    private static final float COLOR_SHIFT_AMOUNT = 60.0f;
    public static final int IMPORTANCE_ANIM_GROW_DURATION = 250;
    public static final int IMPORTANCE_ANIM_SHRINK_DELAY = 25;
    public static final int IMPORTANCE_ANIM_SHRINK_DURATION = 200;
    private ViewGroup mActions;
    private ArrayList<MessagingGroup> mAddedGroups;
    private ObservableTextView mAppName;
    private View mAppNameDivider;
    private boolean mAppNameGone;
    private ViewGroup mAppOps;
    private Rect mAppOpsTouchRect;
    private Icon mAvatarReplacement;
    private int mAvatarSize;
    private int mBadgedSideMargins;
    private View mContentContainer;
    private int mContentMarginEnd;
    private int mConversationAvatarSize;
    private int mConversationAvatarSizeExpanded;
    private int mConversationContentStart;
    private View mConversationFacePile;
    private View mConversationHeader;
    private Icon mConversationIcon;
    private View mConversationIconBadge;
    private CachingIconView mConversationIconBadgeBg;
    private View mConversationIconContainer;
    private int mConversationIconTopPadding;
    private int mConversationIconTopPaddingExpandedGroup;
    private CachingIconView mConversationIconView;
    private TextView mConversationText;
    private CharSequence mConversationTitle;
    private NotificationExpandButton mExpandButton;
    private ViewGroup mExpandButtonAndContentContainer;
    private View mExpandButtonContainer;
    private int mExpandButtonExpandedTopMargin;
    private View mExpandButtonInnerContainer;
    private boolean mExpandable;
    private int mExpandedGroupMessagePadding;
    private int mExpandedGroupSideMargin;
    private int mExpandedGroupSideMarginFacePile;
    private int mFacePileAvatarSize;
    private int mFacePileAvatarSizeExpandedGroup;
    private int mFacePileProtectionWidth;
    private int mFacePileProtectionWidthExpanded;
    private CharSequence mFallbackChatName;
    private CharSequence mFallbackGroupChatName;
    private ArrayList<MessagingGroup> mGroups;
    private List<MessagingMessage> mHistoricMessages;
    private CachingIconView mIcon;
    private MessagingLinearLayout mImageMessageContainer;
    private ImageResolver mImageResolver;
    private CachingIconView mImportanceRingView;
    private boolean mImportantConversation;
    private int mInternalButtonPadding;
    private boolean mIsCollapsed;
    private boolean mIsOneToOne;
    private Icon mLargeIcon;
    private int mLayoutColor;
    private int mMessageTextColor;
    private List<MessagingMessage> mMessages;
    private Rect mMessagingClipRect;
    private MessagingLinearLayout mMessagingLinearLayout;
    private float mMinTouchSize;
    private CharSequence mNameReplacement;
    private int mNotificationBackgroundColor;
    private int mNotificationHeaderExpandedPadding;
    private Paint mPaint;
    private int mSenderTextColor;
    private Icon mShortcutIcon;
    private boolean mShowHistoricMessages;
    private Paint mTextPaint;
    private TextView mUnreadBadge;
    private Person mUser;
    private static final Pattern IGNORABLE_CHAR_PATTERN = Pattern.compile("[\\p{C}\\p{Z}]");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
    private static final Consumer<MessagingMessage> REMOVE_MESSAGE = $$Lambda$V0sK9ebSJ63M4zBVBrIINLA_QR4.INSTANCE;
    public static final Interpolator LINEAR_OUT_SLOW_IN = new PathInterpolator(0.0f, 0.0f, 0.2f, 1.0f);
    public static final Interpolator FAST_OUT_LINEAR_IN = new PathInterpolator(0.4f, 0.0f, 1.0f, 1.0f);
    public static final Interpolator FAST_OUT_SLOW_IN = new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
    public static final Interpolator OVERSHOOT = new PathInterpolator(0.4f, 0.0f, 0.2f, 1.4f);
    public static final View.OnLayoutChangeListener MESSAGING_PROPERTY_ANIMATOR = new MessagingPropertyAnimator();

    public ConversationLayout(Context context) {
        super(context);
        this.mMessages = new ArrayList();
        this.mHistoricMessages = new ArrayList();
        this.mGroups = new ArrayList<>();
        this.mPaint = new Paint(1);
        this.mTextPaint = new Paint();
        this.mAddedGroups = new ArrayList<>();
        this.mExpandable = true;
        this.mAppOpsTouchRect = new Rect();
    }

    public ConversationLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMessages = new ArrayList();
        this.mHistoricMessages = new ArrayList();
        this.mGroups = new ArrayList<>();
        this.mPaint = new Paint(1);
        this.mTextPaint = new Paint();
        this.mAddedGroups = new ArrayList<>();
        this.mExpandable = true;
        this.mAppOpsTouchRect = new Rect();
    }

    public ConversationLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMessages = new ArrayList();
        this.mHistoricMessages = new ArrayList();
        this.mGroups = new ArrayList<>();
        this.mPaint = new Paint(1);
        this.mTextPaint = new Paint();
        this.mAddedGroups = new ArrayList<>();
        this.mExpandable = true;
        this.mAppOpsTouchRect = new Rect();
    }

    public ConversationLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mMessages = new ArrayList();
        this.mHistoricMessages = new ArrayList();
        this.mGroups = new ArrayList<>();
        this.mPaint = new Paint(1);
        this.mTextPaint = new Paint();
        this.mAddedGroups = new ArrayList<>();
        this.mExpandable = true;
        this.mAppOpsTouchRect = new Rect();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mMessagingLinearLayout = (MessagingLinearLayout) findViewById(R.id.notification_messaging);
        this.mActions = (ViewGroup) findViewById(R.id.actions);
        this.mImageMessageContainer = (MessagingLinearLayout) findViewById(R.id.conversation_image_message_container);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int iMax = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.mMessagingClipRect = new Rect(0, 0, iMax, iMax);
        setMessagingClippingDisabled(false);
        this.mAvatarSize = getResources().getDimensionPixelSize(R.dimen.messaging_avatar_size);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mTextPaint.setAntiAlias(true);
        this.mConversationIconView = (CachingIconView) findViewById(R.id.conversation_icon);
        this.mConversationIconContainer = findViewById(R.id.conversation_icon_container);
        this.mIcon = (CachingIconView) findViewById(16908294);
        this.mAppOps = (ViewGroup) findViewById(R.id.app_ops);
        this.mMinTouchSize = getResources().getDisplayMetrics().density * 48.0f;
        this.mImportanceRingView = (CachingIconView) findViewById(R.id.conversation_icon_badge_ring);
        this.mConversationIconBadge = findViewById(R.id.conversation_icon_badge);
        this.mConversationIconBadgeBg = (CachingIconView) findViewById(R.id.conversation_icon_badge_bg);
        this.mIcon.setOnVisibilityChangedListener(new Consumer() { // from class: com.android.internal.widget.-$$Lambda$ConversationLayout$Nx9OCyKAGDgXb1DM_uBrrqwSfvM
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onFinishInflate$0$ConversationLayout((Integer) obj);
            }
        });
        this.mIcon.setOnForceHiddenChangedListener(new Consumer() { // from class: com.android.internal.widget.-$$Lambda$ConversationLayout$HwBz1UV4zp1KDAKfYBaOev6H-f4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onFinishInflate$1$ConversationLayout((Boolean) obj);
            }
        });
        this.mConversationIconView.setOnForceHiddenChangedListener(new Consumer() { // from class: com.android.internal.widget.-$$Lambda$ConversationLayout$yolxq3di_GvwFdfP0kax9KQ9gso
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onFinishInflate$2$ConversationLayout((Boolean) obj);
            }
        });
        this.mConversationText = (TextView) findViewById(R.id.conversation_text);
        this.mExpandButtonContainer = findViewById(R.id.expand_button_container);
        this.mConversationHeader = findViewById(R.id.conversation_header);
        this.mContentContainer = findViewById(R.id.notification_action_list_margin_target);
        this.mExpandButtonAndContentContainer = (ViewGroup) findViewById(R.id.expand_button_and_content_container);
        this.mExpandButtonInnerContainer = findViewById(R.id.expand_button_inner_container);
        this.mExpandButton = (NotificationExpandButton) findViewById(R.id.expand_button);
        this.mExpandButtonExpandedTopMargin = getResources().getDimensionPixelSize(R.dimen.conversation_expand_button_top_margin_expanded);
        this.mNotificationHeaderExpandedPadding = getResources().getDimensionPixelSize(R.dimen.conversation_header_expanded_padding_end);
        this.mContentMarginEnd = getResources().getDimensionPixelSize(R.dimen.notification_content_margin_end);
        this.mBadgedSideMargins = getResources().getDimensionPixelSize(R.dimen.conversation_badge_side_margin);
        this.mConversationAvatarSize = getResources().getDimensionPixelSize(R.dimen.conversation_avatar_size);
        this.mConversationAvatarSizeExpanded = getResources().getDimensionPixelSize(R.dimen.conversation_avatar_size_group_expanded);
        this.mConversationIconTopPaddingExpandedGroup = getResources().getDimensionPixelSize(R.dimen.conversation_icon_container_top_padding_small_avatar);
        this.mConversationIconTopPadding = getResources().getDimensionPixelSize(R.dimen.conversation_icon_container_top_padding);
        this.mExpandedGroupMessagePadding = getResources().getDimensionPixelSize(R.dimen.expanded_group_conversation_message_padding);
        this.mExpandedGroupSideMargin = getResources().getDimensionPixelSize(R.dimen.conversation_badge_side_margin_group_expanded);
        this.mExpandedGroupSideMarginFacePile = getResources().getDimensionPixelSize(R.dimen.conversation_badge_side_margin_group_expanded_face_pile);
        this.mConversationFacePile = findViewById(R.id.conversation_face_pile);
        this.mFacePileAvatarSize = getResources().getDimensionPixelSize(R.dimen.conversation_face_pile_avatar_size);
        this.mFacePileAvatarSizeExpandedGroup = getResources().getDimensionPixelSize(R.dimen.conversation_face_pile_avatar_size_group_expanded);
        this.mFacePileProtectionWidth = getResources().getDimensionPixelSize(R.dimen.conversation_face_pile_protection_width);
        this.mFacePileProtectionWidthExpanded = getResources().getDimensionPixelSize(R.dimen.conversation_face_pile_protection_width_expanded);
        this.mFallbackChatName = getResources().getString(R.string.conversation_title_fallback_one_to_one);
        this.mFallbackGroupChatName = getResources().getString(R.string.conversation_title_fallback_group_chat);
        this.mAppName = (ObservableTextView) findViewById(R.id.app_name_text);
        this.mAppNameDivider = findViewById(R.id.app_name_divider);
        this.mAppNameGone = this.mAppName.getVisibility() == 8;
        this.mAppName.setOnVisibilityChangedListener(new Consumer() { // from class: com.android.internal.widget.-$$Lambda$ConversationLayout$qmMtuXuZ07ZJz2gLLxEQg4zHK8s
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onFinishInflate$3$ConversationLayout((Integer) obj);
            }
        });
        this.mUnreadBadge = (TextView) findViewById(R.id.conversation_unread_count);
        this.mConversationContentStart = getResources().getDimensionPixelSize(R.dimen.conversation_content_start);
        this.mInternalButtonPadding = getResources().getDimensionPixelSize(R.dimen.button_padding_horizontal_material) + getResources().getDimensionPixelSize(R.dimen.button_inset_horizontal_material);
    }

    public /* synthetic */ void lambda$onFinishInflate$0$ConversationLayout(Integer num) {
        boolean z = num.intValue() == 8;
        if ((this.mConversationIconBadgeBg.getVisibility() == 8) != z) {
            this.mConversationIconBadgeBg.animate().cancel();
            this.mConversationIconBadgeBg.setVisibility(num.intValue());
        }
        boolean z2 = this.mImportanceRingView.getVisibility() == 8;
        Integer numValueOf = Integer.valueOf(!this.mImportantConversation ? 8 : num.intValue());
        if (z2 != (numValueOf.intValue() == 8)) {
            this.mImportanceRingView.animate().cancel();
            this.mImportanceRingView.setVisibility(numValueOf.intValue());
        }
        if ((this.mConversationIconBadge.getVisibility() == 8) != z) {
            this.mConversationIconBadge.animate().cancel();
            this.mConversationIconBadge.setVisibility(numValueOf.intValue());
        }
    }

    public /* synthetic */ void lambda$onFinishInflate$1$ConversationLayout(Boolean bool) {
        animateViewForceHidden(this.mConversationIconBadgeBg, bool.booleanValue());
        animateViewForceHidden(this.mImportanceRingView, bool.booleanValue());
    }

    public /* synthetic */ void lambda$onFinishInflate$2$ConversationLayout(Boolean bool) {
        animateViewForceHidden(this.mConversationIconBadgeBg, bool.booleanValue());
        animateViewForceHidden(this.mImportanceRingView, bool.booleanValue());
        animateViewForceHidden(this.mIcon, bool.booleanValue());
    }

    public /* synthetic */ void lambda$onFinishInflate$3$ConversationLayout(Integer num) {
        onAppNameVisibilityChanged();
    }

    private void animateViewForceHidden(final CachingIconView cachingIconView, final boolean z) {
        if (z == (cachingIconView.willBeForceHidden() || cachingIconView.isForceHidden())) {
            return;
        }
        cachingIconView.animate().cancel();
        cachingIconView.setWillBeForceHidden(z);
        cachingIconView.animate().scaleX(z ? 0.5f : 1.0f).scaleY(z ? 0.5f : 1.0f).alpha(z ? 0.0f : 1.0f).setInterpolator(z ? MessagingPropertyAnimator.ALPHA_OUT : MessagingPropertyAnimator.ALPHA_IN).setDuration(160L);
        if (cachingIconView.getVisibility() != 0) {
            cachingIconView.setForceHidden(z);
        } else {
            cachingIconView.animate().withEndAction(new Runnable() { // from class: com.android.internal.widget.-$$Lambda$ConversationLayout$wae5A4WK0R-xPA4i8nVnO2s5bkE
                @Override // java.lang.Runnable
                public final void run() {
                    cachingIconView.setForceHidden(z);
                }
            });
        }
        cachingIconView.animate().start();
    }

    @RemotableViewMethod
    public void setAvatarReplacement(Icon icon) {
        this.mAvatarReplacement = icon;
    }

    @RemotableViewMethod
    public void setNameReplacement(CharSequence charSequence) {
        this.mNameReplacement = charSequence;
    }

    @RemotableViewMethod
    public void setIsImportantConversation(boolean z) {
        setIsImportantConversation(z, false);
    }

    public void setIsImportantConversation(boolean z, boolean z2) {
        this.mImportantConversation = z;
        CachingIconView cachingIconView = this.mImportanceRingView;
        int i = 8;
        if (z && this.mIcon.getVisibility() != 8) {
            i = 0;
        }
        cachingIconView.setVisibility(i);
        if (z2 && z) {
            final GradientDrawable gradientDrawable = (GradientDrawable) this.mImportanceRingView.getDrawable();
            gradientDrawable.mutate();
            final GradientDrawable gradientDrawable2 = (GradientDrawable) this.mConversationIconBadgeBg.getDrawable();
            gradientDrawable2.mutate();
            final int color = getResources().getColor(R.color.conversation_important_highlight);
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.importance_ring_stroke_width);
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.importance_ring_anim_max_stroke_width);
            final int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.importance_ring_size) - (dimensionPixelSize * 2);
            final int dimensionPixelSize4 = getResources().getDimensionPixelSize(R.dimen.conversation_icon_size_badged);
            ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.internal.widget.-$$Lambda$ConversationLayout$dVX152nXs3f2Mw2HNdlUVCKCmjo
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.lambda$setIsImportantConversation$5$ConversationLayout(gradientDrawable, color, dimensionPixelSize3, valueAnimator);
                }
            };
            float f = dimensionPixelSize2;
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, f);
            valueAnimatorOfFloat.setInterpolator(LINEAR_OUT_SLOW_IN);
            valueAnimatorOfFloat.setDuration(250L);
            valueAnimatorOfFloat.addUpdateListener(animatorUpdateListener);
            ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(f, dimensionPixelSize);
            valueAnimatorOfFloat2.setDuration(200L);
            valueAnimatorOfFloat2.setStartDelay(25L);
            valueAnimatorOfFloat2.setInterpolator(OVERSHOOT);
            valueAnimatorOfFloat2.addUpdateListener(animatorUpdateListener);
            valueAnimatorOfFloat2.addListener(new AnimatorListenerAdapter() { // from class: com.android.internal.widget.ConversationLayout.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    GradientDrawable gradientDrawable3 = gradientDrawable2;
                    int i2 = dimensionPixelSize3;
                    gradientDrawable3.setSize(i2, i2);
                    ConversationLayout.this.mConversationIconBadgeBg.invalidate();
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    GradientDrawable gradientDrawable3 = gradientDrawable2;
                    int i2 = dimensionPixelSize4;
                    gradientDrawable3.setSize(i2, i2);
                    ConversationLayout.this.mConversationIconBadgeBg.invalidate();
                }
            });
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(valueAnimatorOfFloat, valueAnimatorOfFloat2);
            animatorSet.start();
        }
    }

    public /* synthetic */ void lambda$setIsImportantConversation$5$ConversationLayout(GradientDrawable gradientDrawable, int i, int i2, ValueAnimator valueAnimator) {
        int iRound = Math.round(((Float) valueAnimator.getAnimatedValue()).floatValue());
        gradientDrawable.setStroke(iRound, i);
        int i3 = i2 + (iRound * 2);
        gradientDrawable.setSize(i3, i3);
        this.mImportanceRingView.invalidate();
    }

    public boolean isImportantConversation() {
        return this.mImportantConversation;
    }

    @RemotableViewMethod
    public void setIsCollapsed(boolean z) {
        this.mIsCollapsed = z;
        this.mMessagingLinearLayout.setMaxDisplayedLines(z ? 1 : Integer.MAX_VALUE);
        updateExpandButton();
        updateContentEndPaddings();
    }

    @RemotableViewMethod
    public void setData(Bundle bundle) {
        List<Notification.MessagingStyle.Message> messagesFromBundleArray = Notification.MessagingStyle.Message.getMessagesFromBundleArray(bundle.getParcelableArray("android.messages"));
        List<Notification.MessagingStyle.Message> messagesFromBundleArray2 = Notification.MessagingStyle.Message.getMessagesFromBundleArray(bundle.getParcelableArray(Notification.EXTRA_HISTORIC_MESSAGES));
        setUser((Person) bundle.getParcelable(Notification.EXTRA_MESSAGING_PERSON));
        addRemoteInputHistoryToMessages(messagesFromBundleArray, (RemoteInputHistoryItem[]) bundle.getParcelableArray(Notification.EXTRA_REMOTE_INPUT_HISTORY_ITEMS));
        bind(messagesFromBundleArray, messagesFromBundleArray2, bundle.getBoolean(Notification.EXTRA_SHOW_REMOTE_INPUT_SPINNER, false));
        setUnreadCount(bundle.getInt(Notification.EXTRA_CONVERSATION_UNREAD_MESSAGE_COUNT));
    }

    @Override // com.android.internal.widget.ImageMessageConsumer
    public void setImageResolver(ImageResolver imageResolver) {
        this.mImageResolver = imageResolver;
    }

    public void setUnreadCount(int i) {
        String string;
        boolean z = this.mIsCollapsed && i > 1;
        this.mUnreadBadge.setVisibility(z ? 0 : 8);
        if (z) {
            if (i >= 100) {
                string = getResources().getString(R.string.unread_convo_overflow, 99);
            } else {
                string = String.format(Locale.getDefault(), "%d", Integer.valueOf(i));
            }
            this.mUnreadBadge.setText(string);
            this.mUnreadBadge.setBackgroundTintList(ColorStateList.valueOf(this.mLayoutColor));
            this.mUnreadBadge.setTextColor(ColorUtils.calculateLuminance(this.mLayoutColor) > 0.5d ? -16777216 : -1);
        }
    }

    private void addRemoteInputHistoryToMessages(List<Notification.MessagingStyle.Message> list, RemoteInputHistoryItem[] remoteInputHistoryItemArr) {
        if (remoteInputHistoryItemArr == null || remoteInputHistoryItemArr.length == 0) {
            return;
        }
        for (int length = remoteInputHistoryItemArr.length - 1; length >= 0; length--) {
            RemoteInputHistoryItem remoteInputHistoryItem = remoteInputHistoryItemArr[length];
            Notification.MessagingStyle.Message message = new Notification.MessagingStyle.Message(remoteInputHistoryItem.getText(), 0L, (Person) null, true);
            if (remoteInputHistoryItem.getUri() != null) {
                message.setData(remoteInputHistoryItem.getMimeType(), remoteInputHistoryItem.getUri());
            }
            list.add(message);
        }
    }

    private void bind(List<Notification.MessagingStyle.Message> list, List<Notification.MessagingStyle.Message> list2, boolean z) {
        List<MessagingMessage> listCreateMessages = createMessages(list2, true);
        List<MessagingMessage> listCreateMessages2 = createMessages(list, false);
        ArrayList<MessagingGroup> arrayList = new ArrayList<>(this.mGroups);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        findGroups(listCreateMessages, listCreateMessages2, arrayList2, arrayList3);
        createGroupViews(arrayList2, arrayList3, z);
        removeGroups(arrayList);
        List<MessagingMessage> list3 = this.mMessages;
        Consumer<MessagingMessage> consumer = REMOVE_MESSAGE;
        list3.forEach(consumer);
        this.mHistoricMessages.forEach(consumer);
        this.mMessages = listCreateMessages2;
        this.mHistoricMessages = listCreateMessages;
        updateHistoricMessageVisibility();
        updateTitleAndNamesDisplay();
        updateConversationLayout();
    }

    private void updateConversationLayout() {
        CharSequence senderName = this.mConversationTitle;
        this.mConversationIcon = this.mShortcutIcon;
        if (this.mIsOneToOne) {
            CharSequence key = getKey(this.mUser);
            for (int size = this.mGroups.size() - 1; size >= 0; size--) {
                MessagingGroup messagingGroup = this.mGroups.get(size);
                Person sender = messagingGroup.getSender();
                if ((sender != null && !TextUtils.equals(key, getKey(sender))) || size == 0) {
                    if (TextUtils.isEmpty(senderName)) {
                        senderName = messagingGroup.getSenderName();
                    }
                    if (this.mConversationIcon == null) {
                        Icon avatarIcon = messagingGroup.getAvatarIcon();
                        if (avatarIcon == null) {
                            avatarIcon = createAvatarSymbol(senderName, "", this.mLayoutColor);
                        }
                        this.mConversationIcon = avatarIcon;
                    }
                }
            }
        }
        if (this.mConversationIcon == null) {
            this.mConversationIcon = this.mLargeIcon;
        }
        if (this.mIsOneToOne || this.mConversationIcon != null) {
            this.mConversationIconView.setVisibility(0);
            this.mConversationFacePile.setVisibility(8);
            this.mConversationIconView.setImageIcon(this.mConversationIcon);
        } else {
            this.mConversationIconView.setVisibility(8);
            this.mConversationFacePile.setVisibility(0);
            this.mConversationFacePile = findViewById(R.id.conversation_face_pile);
            bindFacePile();
        }
        if (TextUtils.isEmpty(senderName)) {
            senderName = this.mIsOneToOne ? this.mFallbackChatName : this.mFallbackGroupChatName;
        }
        this.mConversationText.setText(senderName);
        for (int size2 = this.mGroups.size() - 1; size2 >= 0; size2--) {
            MessagingGroup messagingGroup2 = this.mGroups.get(size2);
            messagingGroup2.setCanHideSenderIfFirst(this.mIsOneToOne && TextUtils.equals(senderName, messagingGroup2.getSenderName()));
        }
        updateAppName();
        updateIconPositionAndSize();
        updateImageMessages();
        updatePaddingsBasedOnContentAvailability();
        updateActionListPadding();
        updateAppNameDividerVisibility();
    }

    private void updateActionListPadding() {
        View childAt;
        ViewGroup viewGroup = this.mActions;
        if (viewGroup == null || (childAt = viewGroup.getChildAt(0)) == null) {
            return;
        }
        int marginStart = (this.mConversationContentStart - ((ViewGroup.MarginLayoutParams) childAt.getLayoutParams()).getMarginStart()) - this.mInternalButtonPadding;
        ViewGroup viewGroup2 = this.mActions;
        viewGroup2.setPaddingRelative(marginStart, viewGroup2.getPaddingTop(), this.mActions.getPaddingEnd(), this.mActions.getPaddingBottom());
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateImageMessages() {
        /*
            r4 = this;
            boolean r0 = r4.mIsCollapsed
            if (r0 == 0) goto L25
            java.util.ArrayList<com.android.internal.widget.MessagingGroup> r0 = r4.mGroups
            int r0 = r0.size()
            if (r0 <= 0) goto L25
            java.util.ArrayList<com.android.internal.widget.MessagingGroup> r0 = r4.mGroups
            int r1 = r0.size()
            int r1 = r1 + (-1)
            java.lang.Object r0 = r0.get(r1)
            com.android.internal.widget.MessagingGroup r0 = (com.android.internal.widget.MessagingGroup) r0
            com.android.internal.widget.MessagingImageMessage r0 = r0.getIsolatedMessage()
            if (r0 == 0) goto L25
            android.view.View r0 = r0.getView()
            goto L26
        L25:
            r0 = 0
        L26:
            com.android.internal.widget.MessagingLinearLayout r1 = r4.mImageMessageContainer
            r2 = 0
            android.view.View r1 = r1.getChildAt(r2)
            if (r1 == r0) goto L3b
            com.android.internal.widget.MessagingLinearLayout r3 = r4.mImageMessageContainer
            r3.removeView(r1)
            if (r0 == 0) goto L3b
            com.android.internal.widget.MessagingLinearLayout r1 = r4.mImageMessageContainer
            r1.addView(r0)
        L3b:
            com.android.internal.widget.MessagingLinearLayout r4 = r4.mImageMessageContainer
            if (r0 == 0) goto L40
            goto L42
        L40:
            r2 = 8
        L42:
            r4.setVisibility(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.widget.ConversationLayout.updateImageMessages():void");
    }

    public void bindFacePile(ImageView imageView, ImageView imageView2, ImageView imageView3) {
        applyNotificationBackgroundColor(imageView);
        CharSequence key = getKey(this.mUser);
        int size = this.mGroups.size() - 1;
        Icon iconCreateAvatarSymbol = null;
        CharSequence key2 = null;
        Icon iconCreateAvatarSymbol2 = null;
        while (true) {
            if (size < 0) {
                break;
            }
            MessagingGroup messagingGroup = this.mGroups.get(size);
            Person sender = messagingGroup.getSender();
            boolean z = false;
            boolean z2 = (sender == null || TextUtils.equals(key, getKey(sender))) ? false : true;
            if (sender != null && !TextUtils.equals(key2, getKey(sender))) {
                z = true;
            }
            if ((z2 && z) || (size == 0 && key2 == null)) {
                if (iconCreateAvatarSymbol2 == null) {
                    iconCreateAvatarSymbol2 = messagingGroup.getAvatarIcon();
                    key2 = getKey(sender);
                } else {
                    iconCreateAvatarSymbol = messagingGroup.getAvatarIcon();
                    break;
                }
            }
            size--;
        }
        if (iconCreateAvatarSymbol2 == null) {
            iconCreateAvatarSymbol2 = createAvatarSymbol(" ", "", this.mLayoutColor);
        }
        imageView2.setImageIcon(iconCreateAvatarSymbol2);
        if (iconCreateAvatarSymbol == null) {
            iconCreateAvatarSymbol = createAvatarSymbol("", "", this.mLayoutColor);
        }
        imageView3.setImageIcon(iconCreateAvatarSymbol);
    }

    private void bindFacePile() {
        int i;
        int i2;
        int i3;
        ImageView imageView = (ImageView) this.mConversationFacePile.findViewById(R.id.conversation_face_pile_bottom_background);
        ImageView imageView2 = (ImageView) this.mConversationFacePile.findViewById(R.id.conversation_face_pile_bottom);
        ImageView imageView3 = (ImageView) this.mConversationFacePile.findViewById(R.id.conversation_face_pile_top);
        bindFacePile(imageView, imageView2, imageView3);
        if (this.mIsCollapsed) {
            i = this.mConversationAvatarSize;
            i2 = this.mFacePileAvatarSize;
            i3 = this.mFacePileProtectionWidth;
        } else {
            i = this.mConversationAvatarSizeExpanded;
            i2 = this.mFacePileAvatarSizeExpandedGroup;
            i3 = this.mFacePileProtectionWidthExpanded;
        }
        int i4 = (i3 * 2) + i2;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mConversationIconView.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        this.mConversationFacePile.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) imageView2.getLayoutParams();
        layoutParams2.width = i2;
        layoutParams2.height = i2;
        imageView2.setLayoutParams(layoutParams2);
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) imageView3.getLayoutParams();
        layoutParams3.width = i2;
        layoutParams3.height = i2;
        imageView3.setLayoutParams(layoutParams3);
        FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams4.width = i4;
        layoutParams4.height = i4;
        imageView.setLayoutParams(layoutParams4);
    }

    private void updateAppName() {
        this.mAppName.setVisibility(this.mIsCollapsed ? 8 : 0);
    }

    public boolean shouldHideAppName() {
        return this.mIsCollapsed;
    }

    private void updateIconPositionAndSize() {
        int i;
        int i2;
        if (this.mIsOneToOne || this.mIsCollapsed) {
            i = this.mBadgedSideMargins;
            i2 = this.mConversationAvatarSize;
        } else {
            if (this.mConversationFacePile.getVisibility() == 0) {
                i = this.mExpandedGroupSideMarginFacePile;
            } else {
                i = this.mExpandedGroupSideMargin;
            }
            i2 = this.mConversationAvatarSizeExpanded;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mConversationIconBadge.getLayoutParams();
        layoutParams.topMargin = i;
        layoutParams.setMarginStart(i);
        this.mConversationIconBadge.setLayoutParams(layoutParams);
        if (this.mConversationIconView.getVisibility() == 0) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mConversationIconView.getLayoutParams();
            layoutParams2.width = i2;
            layoutParams2.height = i2;
            this.mConversationIconView.setLayoutParams(layoutParams2);
        }
    }

    private void updatePaddingsBasedOnContentAvailability() {
        int i;
        boolean z = this.mIsOneToOne;
        int i2 = (z || this.mIsCollapsed) ? 0 : this.mExpandedGroupMessagePadding;
        if (z || this.mIsCollapsed) {
            i = this.mConversationIconTopPadding;
        } else {
            i = this.mConversationIconTopPaddingExpandedGroup;
        }
        View view = this.mConversationIconContainer;
        view.setPaddingRelative(view.getPaddingStart(), i, this.mConversationIconContainer.getPaddingEnd(), this.mConversationIconContainer.getPaddingBottom());
        MessagingLinearLayout messagingLinearLayout = this.mMessagingLinearLayout;
        messagingLinearLayout.setPaddingRelative(messagingLinearLayout.getPaddingStart(), i2, this.mMessagingLinearLayout.getPaddingEnd(), this.mMessagingLinearLayout.getPaddingBottom());
    }

    @RemotableViewMethod
    public void setLargeIcon(Icon icon) {
        this.mLargeIcon = icon;
    }

    @RemotableViewMethod
    public void setShortcutIcon(Icon icon) {
        this.mShortcutIcon = icon;
    }

    @RemotableViewMethod
    public void setConversationTitle(CharSequence charSequence) {
        this.mConversationTitle = charSequence != null ? charSequence.toString() : null;
    }

    public CharSequence getConversationTitle() {
        return this.mConversationText.getText();
    }

    private void removeGroups(ArrayList<MessagingGroup> arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            final MessagingGroup messagingGroup = arrayList.get(i);
            if (!this.mGroups.contains(messagingGroup)) {
                List<MessagingMessage> messages = messagingGroup.getMessages();
                Runnable runnable = new Runnable() { // from class: com.android.internal.widget.-$$Lambda$ConversationLayout$nbrfjHfIsdsERqjBMY5L51jVK0k
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$removeGroups$6$ConversationLayout(messagingGroup);
                    }
                };
                boolean zIsShown = messagingGroup.isShown();
                this.mMessagingLinearLayout.removeView(messagingGroup);
                if (zIsShown && !MessagingLinearLayout.isGone(messagingGroup)) {
                    this.mMessagingLinearLayout.addTransientView(messagingGroup, 0);
                    messagingGroup.removeGroupAnimated(runnable);
                } else {
                    runnable.run();
                }
                this.mMessages.removeAll(messages);
                this.mHistoricMessages.removeAll(messages);
            }
        }
    }

    public /* synthetic */ void lambda$removeGroups$6$ConversationLayout(MessagingGroup messagingGroup) {
        this.mMessagingLinearLayout.removeTransientView(messagingGroup);
        messagingGroup.recycle();
    }

    private void updateTitleAndNamesDisplay() {
        Icon avatarSymbolIfMatching;
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        for (int i = 0; i < this.mGroups.size(); i++) {
            MessagingGroup messagingGroup = this.mGroups.get(i);
            CharSequence senderName = messagingGroup.getSenderName();
            if (messagingGroup.needsGeneratedAvatar() && !TextUtils.isEmpty(senderName) && !arrayMap.containsKey(senderName)) {
                String strReplaceAll = IGNORABLE_CHAR_PATTERN.matcher(senderName).replaceAll("");
                char cCharAt = strReplaceAll.charAt(0);
                if (arrayMap2.containsKey(Character.valueOf(cCharAt))) {
                    CharSequence charSequence = (CharSequence) arrayMap2.get(Character.valueOf(cCharAt));
                    if (charSequence != null) {
                        arrayMap.put(charSequence, findNameSplit((String) charSequence));
                        arrayMap2.put(Character.valueOf(cCharAt), null);
                    }
                    arrayMap.put(senderName, findNameSplit((String) senderName));
                } else {
                    arrayMap.put(senderName, Character.toString(cCharAt));
                    arrayMap2.put(Character.valueOf(cCharAt), strReplaceAll);
                }
            }
        }
        ArrayMap arrayMap3 = new ArrayMap();
        for (int i2 = 0; i2 < this.mGroups.size(); i2++) {
            MessagingGroup messagingGroup2 = this.mGroups.get(i2);
            boolean z = messagingGroup2.getSender() == this.mUser;
            CharSequence senderName2 = messagingGroup2.getSenderName();
            if (messagingGroup2.needsGeneratedAvatar() && !TextUtils.isEmpty(senderName2) && ((!this.mIsOneToOne || this.mAvatarReplacement == null || z) && (avatarSymbolIfMatching = messagingGroup2.getAvatarSymbolIfMatching(senderName2, (String) arrayMap.get(senderName2), this.mLayoutColor)) != null)) {
                arrayMap3.put(senderName2, avatarSymbolIfMatching);
            }
        }
        for (int i3 = 0; i3 < this.mGroups.size(); i3++) {
            MessagingGroup messagingGroup3 = this.mGroups.get(i3);
            CharSequence senderName3 = messagingGroup3.getSenderName();
            if (messagingGroup3.needsGeneratedAvatar() && !TextUtils.isEmpty(senderName3)) {
                if (this.mIsOneToOne && this.mAvatarReplacement != null && messagingGroup3.getSender() != this.mUser) {
                    messagingGroup3.setAvatar(this.mAvatarReplacement);
                } else {
                    Icon iconCreateAvatarSymbol = (Icon) arrayMap3.get(senderName3);
                    if (iconCreateAvatarSymbol == null) {
                        iconCreateAvatarSymbol = createAvatarSymbol(senderName3, (String) arrayMap.get(senderName3), this.mLayoutColor);
                        arrayMap3.put(senderName3, iconCreateAvatarSymbol);
                    }
                    messagingGroup3.setCreatedAvatar(iconCreateAvatarSymbol, senderName3, (String) arrayMap.get(senderName3), this.mLayoutColor);
                }
            }
        }
    }

    private Icon createAvatarSymbol(CharSequence charSequence, String str, int i) {
        float f;
        float f2;
        if (str.isEmpty() || TextUtils.isDigitsOnly(str) || SPECIAL_CHAR_PATTERN.matcher(str).find()) {
            Icon iconCreateWithResource = Icon.createWithResource(getContext(), R.drawable.messaging_user);
            iconCreateWithResource.setTint(findColor(charSequence, i));
            return iconCreateWithResource;
        }
        int i2 = this.mAvatarSize;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        float f3 = this.mAvatarSize / 2.0f;
        int iFindColor = findColor(charSequence, i);
        this.mPaint.setColor(iFindColor);
        canvas.drawCircle(f3, f3, f3, this.mPaint);
        this.mTextPaint.setColor((ColorUtils.calculateLuminance(iFindColor) > 0.5d ? 1 : (ColorUtils.calculateLuminance(iFindColor) == 0.5d ? 0 : -1)) > 0 ? -16777216 : -1);
        Paint paint = this.mTextPaint;
        if (str.length() == 1) {
            f = this.mAvatarSize;
            f2 = 0.5f;
        } else {
            f = this.mAvatarSize;
            f2 = 0.3f;
        }
        paint.setTextSize(f * f2);
        canvas.drawText(str, f3, (int) (f3 - ((this.mTextPaint.descent() + this.mTextPaint.ascent()) / 2.0f)), this.mTextPaint);
        return Icon.createWithBitmap(bitmapCreateBitmap);
    }

    private int findColor(CharSequence charSequence, int i) {
        double dCalculateLuminance = ContrastColorUtil.calculateLuminance(i);
        return ContrastColorUtil.getShiftedColor(i, (int) (((float) (((double) ((float) (((double) (((Math.abs(charSequence.hashCode()) % 5) / 4.0f) - 0.5f)) + Math.max(0.30000001192092896d - dCalculateLuminance, 0.0d)))) - Math.max(0.30000001192092896d - (1.0d - dCalculateLuminance), 0.0d))) * 60.0f));
    }

    private String findNameSplit(String str) {
        String[] strArrSplit = str.split(" ");
        if (strArrSplit.length > 1) {
            return Character.toString(strArrSplit[0].charAt(0)) + Character.toString(strArrSplit[1].charAt(0));
        }
        return str.substring(0, 1);
    }

    @RemotableViewMethod
    public void setLayoutColor(int i) {
        this.mLayoutColor = i;
    }

    @RemotableViewMethod
    public void setIsOneToOne(boolean z) {
        this.mIsOneToOne = z;
    }

    @RemotableViewMethod
    public void setSenderTextColor(int i) {
        this.mSenderTextColor = i;
        this.mConversationText.setTextColor(i);
    }

    @RemotableViewMethod
    public void setNotificationBackgroundColor(int i) {
        this.mNotificationBackgroundColor = i;
        applyNotificationBackgroundColor(this.mConversationIconBadgeBg);
    }

    private void applyNotificationBackgroundColor(ImageView imageView) {
        imageView.setImageTintList(ColorStateList.valueOf(this.mNotificationBackgroundColor));
    }

    @RemotableViewMethod
    public void setMessageTextColor(int i) {
        this.mMessageTextColor = i;
    }

    private void setUser(Person person) {
        this.mUser = person;
        if (person.getIcon() == null) {
            Icon iconCreateWithResource = Icon.createWithResource(getContext(), R.drawable.messaging_user);
            iconCreateWithResource.setTint(this.mLayoutColor);
            this.mUser = this.mUser.toBuilder().setIcon(iconCreateWithResource).build();
        }
    }

    private void createGroupViews(List<List<MessagingMessage>> list, List<Person> list2, boolean z) {
        CharSequence charSequence;
        this.mGroups.clear();
        int i = 0;
        while (i < list.size()) {
            List<MessagingMessage> list3 = list.get(i);
            CharSequence charSequence2 = null;
            MessagingGroup messagingGroupCreateGroup = null;
            for (int size = list3.size() - 1; size >= 0; size--) {
                messagingGroupCreateGroup = list3.get(size).getGroup();
                if (messagingGroupCreateGroup != null) {
                    break;
                }
            }
            if (messagingGroupCreateGroup == null) {
                messagingGroupCreateGroup = MessagingGroup.createGroup(this.mMessagingLinearLayout);
                this.mAddedGroups.add(messagingGroupCreateGroup);
            }
            messagingGroupCreateGroup.setImageDisplayLocation(this.mIsCollapsed ? 2 : 0);
            messagingGroupCreateGroup.setIsInConversation(true);
            messagingGroupCreateGroup.setLayoutColor(this.mLayoutColor);
            messagingGroupCreateGroup.setTextColors(this.mSenderTextColor, this.mMessageTextColor);
            Person person = list2.get(i);
            if (person != this.mUser && (charSequence = this.mNameReplacement) != null) {
                charSequence2 = charSequence;
            }
            messagingGroupCreateGroup.setShowingAvatar((this.mIsOneToOne || this.mIsCollapsed) ? false : true);
            messagingGroupCreateGroup.setSingleLine(this.mIsCollapsed);
            messagingGroupCreateGroup.setSender(person, charSequence2);
            messagingGroupCreateGroup.setSending(i == list.size() - 1 && z);
            this.mGroups.add(messagingGroupCreateGroup);
            if (this.mMessagingLinearLayout.indexOfChild(messagingGroupCreateGroup) != i) {
                this.mMessagingLinearLayout.removeView(messagingGroupCreateGroup);
                this.mMessagingLinearLayout.addView(messagingGroupCreateGroup, i);
            }
            messagingGroupCreateGroup.setMessages(list3);
            i++;
        }
    }

    private void findGroups(List<MessagingMessage> list, List<MessagingMessage> list2, List<List<MessagingMessage>> list3, List<Person> list4) {
        MessagingMessage messagingMessage;
        Person personBuild;
        int size = list.size();
        ArrayList arrayList = null;
        CharSequence charSequence = null;
        for (int i = 0; i < list2.size() + size; i++) {
            if (i < size) {
                messagingMessage = list.get(i);
            } else {
                messagingMessage = list2.get(i - size);
            }
            boolean z = arrayList == null;
            Person senderPerson = messagingMessage.getMessage().getSenderPerson();
            CharSequence key = getKey(senderPerson);
            if ((true ^ TextUtils.equals(key, charSequence)) | z) {
                arrayList = new ArrayList();
                list3.add(arrayList);
                if (senderPerson == null) {
                    personBuild = this.mUser;
                } else {
                    personBuild = senderPerson.toBuilder().setName(Objects.toString(senderPerson.getName())).build();
                }
                list4.add(personBuild);
                charSequence = key;
            }
            arrayList.add(messagingMessage);
        }
    }

    private CharSequence getKey(Person person) {
        if (person == null) {
            return null;
        }
        return person.getKey() == null ? person.getName() : person.getKey();
    }

    private List<MessagingMessage> createMessages(List<Notification.MessagingStyle.Message> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Notification.MessagingStyle.Message message = list.get(i);
            MessagingMessage messagingMessageFindAndRemoveMatchingMessage = findAndRemoveMatchingMessage(message);
            if (messagingMessageFindAndRemoveMatchingMessage == null) {
                messagingMessageFindAndRemoveMatchingMessage = MessagingMessage.createMessage(this, message, this.mImageResolver);
            }
            messagingMessageFindAndRemoveMatchingMessage.setIsHistoric(z);
            arrayList.add(messagingMessageFindAndRemoveMatchingMessage);
        }
        return arrayList;
    }

    private MessagingMessage findAndRemoveMatchingMessage(Notification.MessagingStyle.Message message) {
        for (int i = 0; i < this.mMessages.size(); i++) {
            MessagingMessage messagingMessage = this.mMessages.get(i);
            if (messagingMessage.sameAs(message)) {
                this.mMessages.remove(i);
                return messagingMessage;
            }
        }
        for (int i2 = 0; i2 < this.mHistoricMessages.size(); i2++) {
            MessagingMessage messagingMessage2 = this.mHistoricMessages.get(i2);
            if (messagingMessage2.sameAs(message)) {
                this.mHistoricMessages.remove(i2);
                return messagingMessage2;
            }
        }
        return null;
    }

    public void showHistoricMessages(boolean z) {
        this.mShowHistoricMessages = z;
        updateHistoricMessageVisibility();
    }

    private void updateHistoricMessageVisibility() {
        int size = this.mHistoricMessages.size();
        int i = 0;
        while (true) {
            int i2 = 8;
            if (i >= size) {
                break;
            }
            MessagingMessage messagingMessage = this.mHistoricMessages.get(i);
            if (this.mShowHistoricMessages) {
                i2 = 0;
            }
            messagingMessage.setVisibility(i2);
            i++;
        }
        int size2 = this.mGroups.size();
        for (int i3 = 0; i3 < size2; i3++) {
            MessagingGroup messagingGroup = this.mGroups.get(i3);
            List<MessagingMessage> messages = messagingGroup.getMessages();
            int size3 = messages.size();
            int i4 = 0;
            for (int i5 = 0; i5 < size3; i5++) {
                if (messages.get(i5).getVisibility() != 8) {
                    i4++;
                }
            }
            if (i4 > 0 && messagingGroup.getVisibility() == 8) {
                messagingGroup.setVisibility(0);
            } else if (i4 == 0 && messagingGroup.getVisibility() != 8) {
                messagingGroup.setVisibility(8);
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (!this.mAddedGroups.isEmpty()) {
            getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.internal.widget.ConversationLayout.2
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    for (MessagingGroup messagingGroup : ConversationLayout.this.mAddedGroups) {
                        if (messagingGroup.isShown()) {
                            MessagingPropertyAnimator.fadeIn(messagingGroup.getAvatar());
                            MessagingPropertyAnimator.fadeIn(messagingGroup.getSenderView());
                            MessagingPropertyAnimator.startLocalTranslationFrom(messagingGroup, messagingGroup.getHeight(), ConversationLayout.LINEAR_OUT_SLOW_IN);
                        }
                    }
                    ConversationLayout.this.mAddedGroups.clear();
                    ConversationLayout.this.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
        }
        if (this.mAppOps.getWidth() > 0) {
            this.mAppOpsTouchRect.set(this.mAppOps.getLeft(), this.mAppOps.getTop(), this.mAppOps.getRight(), this.mAppOps.getBottom());
            for (int i5 = 0; i5 < this.mAppOps.getChildCount(); i5++) {
                if (this.mAppOps.getChildAt(i5).getVisibility() != 8) {
                    float left = r5.getLeft() + (r5.getWidth() / 2.0f);
                    float f = this.mMinTouchSize;
                    float f2 = left - (f / 2.0f);
                    this.mAppOpsTouchRect.left = (int) Math.min(r6.left, this.mAppOps.getLeft() + f2);
                    this.mAppOpsTouchRect.right = (int) Math.max(r6.right, this.mAppOps.getLeft() + f + f2);
                }
            }
            this.mAppOpsTouchRect.inset(0, -(this.mAppOpsTouchRect.height() < this.mMinTouchSize ? (int) Math.ceil((r5 - this.mAppOpsTouchRect.height()) / 2.0f) : 0));
            for (ViewGroup viewGroup = (ViewGroup) this.mAppOps.getParent(); viewGroup != this; viewGroup = (ViewGroup) viewGroup.getParent()) {
                this.mAppOpsTouchRect.offset(viewGroup.getLeft(), viewGroup.getTop());
            }
            setTouchDelegate(new TouchDelegate(this.mAppOpsTouchRect, this.mAppOps));
        }
    }

    @Override // com.android.internal.widget.IMessagingLayout
    public MessagingLinearLayout getMessagingLinearLayout() {
        return this.mMessagingLinearLayout;
    }

    public ViewGroup getImageMessageContainer() {
        return this.mImageMessageContainer;
    }

    @Override // com.android.internal.widget.IMessagingLayout
    public ArrayList<MessagingGroup> getMessagingGroups() {
        return this.mGroups;
    }

    private void updateExpandButton() {
        int i;
        int i2;
        int i3;
        int i4;
        ViewGroup viewGroup;
        if (this.mIsCollapsed) {
            i = R.drawable.ic_expand_notification;
            i2 = R.string.expand_button_content_description_collapsed;
            i3 = 17;
            viewGroup = this.mExpandButtonAndContentContainer;
            i4 = 0;
        } else {
            i = R.drawable.ic_collapse_notification;
            i2 = R.string.expand_button_content_description_expanded;
            i3 = 49;
            i4 = this.mExpandButtonExpandedTopMargin;
            viewGroup = this;
        }
        this.mExpandButton.setImageDrawable(getContext().getDrawable(i));
        NotificationExpandButton notificationExpandButton = this.mExpandButton;
        notificationExpandButton.setColorFilter(notificationExpandButton.getOriginalNotificationColor());
        if (viewGroup != this.mExpandButtonContainer.getParent()) {
            ((ViewGroup) this.mExpandButtonContainer.getParent()).removeView(this.mExpandButtonContainer);
            viewGroup.addView(this.mExpandButtonContainer);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mExpandButton.getLayoutParams();
        layoutParams.gravity = i3;
        layoutParams.topMargin = i4;
        this.mExpandButton.setLayoutParams(layoutParams);
        this.mExpandButtonInnerContainer.setContentDescription(this.mContext.getText(i2));
    }

    private void updateContentEndPaddings() {
        int i;
        int i2 = 0;
        if (!this.mExpandable) {
            i = this.mContentMarginEnd;
        } else if (this.mIsCollapsed) {
            i = 0;
        } else {
            i2 = this.mNotificationHeaderExpandedPadding;
            i = this.mContentMarginEnd;
        }
        View view = this.mConversationHeader;
        view.setPaddingRelative(view.getPaddingStart(), this.mConversationHeader.getPaddingTop(), i2, this.mConversationHeader.getPaddingBottom());
        View view2 = this.mContentContainer;
        view2.setPaddingRelative(view2.getPaddingStart(), this.mContentContainer.getPaddingTop(), i, this.mContentContainer.getPaddingBottom());
    }

    private void onAppNameVisibilityChanged() {
        boolean z = this.mAppName.getVisibility() == 8;
        if (z != this.mAppNameGone) {
            this.mAppNameGone = z;
            updateAppNameDividerVisibility();
        }
    }

    private void updateAppNameDividerVisibility() {
        this.mAppNameDivider.setVisibility(this.mAppNameGone ? 8 : 0);
    }

    public void updateExpandability(boolean z, View.OnClickListener onClickListener) {
        this.mExpandable = z;
        if (z) {
            this.mExpandButtonContainer.setVisibility(0);
            this.mExpandButtonInnerContainer.setOnClickListener(onClickListener);
            this.mConversationIconContainer.setOnClickListener(onClickListener);
        } else {
            this.mExpandButtonContainer.setVisibility(8);
            this.mConversationIconContainer.setOnClickListener(null);
        }
        updateContentEndPaddings();
    }

    @Override // com.android.internal.widget.IMessagingLayout
    public void setMessagingClippingDisabled(boolean z) {
        this.mMessagingLinearLayout.setClipBounds(z ? null : this.mMessagingClipRect);
    }

    public CharSequence getConversationSenderName() {
        if (this.mGroups.isEmpty()) {
            return null;
        }
        ArrayList<MessagingGroup> arrayList = this.mGroups;
        return getResources().getString(R.string.conversation_single_line_name_display, arrayList.get(arrayList.size() - 1).getSenderName());
    }

    public boolean isOneToOne() {
        return this.mIsOneToOne;
    }

    public CharSequence getConversationText() {
        if (this.mMessages.isEmpty()) {
            return null;
        }
        MessagingMessage messagingMessage = this.mMessages.get(r0.size() - 1);
        CharSequence text = messagingMessage.getMessage().getText();
        if (text != null || !(messagingMessage instanceof MessagingImageMessage)) {
            return text;
        }
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.conversation_single_line_image_placeholder));
        spannableString.setSpan(new StyleSpan(2), 0, spannableString.length(), 17);
        return spannableString;
    }

    public Icon getConversationIcon() {
        return this.mConversationIcon;
    }
}
