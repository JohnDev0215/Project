package androidx.autofill.inline.v1;

import android.app.PendingIntent;
import android.app.slice.Slice;
import android.app.slice.SliceItem;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.autofill.R;
import androidx.autofill.inline.UiVersions;
import androidx.autofill.inline.common.BundledStyle;
import androidx.autofill.inline.common.ImageViewStyle;
import androidx.autofill.inline.common.SlicedContent;
import androidx.autofill.inline.common.TextViewStyle;
import androidx.autofill.inline.common.ViewStyle;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 30)
public final class InlineSuggestionUi {
    private static final String TAG = "InlineSuggestionUi";

    @NonNull
    public static Content.Builder newContentBuilder(@NonNull PendingIntent pendingIntent) {
        return new Content.Builder(pendingIntent);
    }

    @NonNull
    public static Style.Builder newStyleBuilder() {
        return new Style.Builder();
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static Content fromSlice(@NonNull Slice slice) {
        Content content = new Content(slice);
        if (content.isValid()) {
            return content;
        }
        Log.w(TAG, "Invalid content for androidx.autofill.inline.ui.version:v1");
        return null;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static Style fromBundle(@NonNull Bundle bundle) {
        Style style = new Style(bundle);
        if (style.isValid()) {
            return style;
        }
        Log.w(TAG, "Invalid style for androidx.autofill.inline.ui.version:v1");
        return null;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static View render(@NonNull Context context, @NonNull Content content, @NonNull Style style) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getDefaultContextThemeWrapper(context)).inflate(R.layout.autofill_inline_suggestion, (ViewGroup) null);
        ImageView imageView = (ImageView) viewGroup.findViewById(R.id.autofill_inline_suggestion_start_icon);
        TextView textView = (TextView) viewGroup.findViewById(R.id.autofill_inline_suggestion_title);
        TextView textView2 = (TextView) viewGroup.findViewById(R.id.autofill_inline_suggestion_subtitle);
        ImageView imageView2 = (ImageView) viewGroup.findViewById(R.id.autofill_inline_suggestion_end_icon);
        CharSequence title = content.getTitle();
        if (title != null) {
            textView.setText(title);
            textView.setVisibility(0);
        }
        CharSequence subtitle = content.getSubtitle();
        if (subtitle != null) {
            textView2.setText(subtitle);
            textView2.setVisibility(0);
        }
        Icon startIcon = content.getStartIcon();
        if (startIcon != null) {
            imageView.setImageIcon(startIcon);
            imageView.setVisibility(0);
        }
        Icon endIcon = content.getEndIcon();
        if (endIcon != null) {
            imageView2.setImageIcon(endIcon);
            imageView2.setVisibility(0);
        }
        CharSequence contentDescription = content.getContentDescription();
        if (!TextUtils.isEmpty(contentDescription)) {
            viewGroup.setContentDescription(contentDescription);
        }
        if (style.isValid()) {
            if (content.isSingleIconOnly()) {
                style.applyStyle(viewGroup, imageView);
            } else {
                style.applyStyle(viewGroup, imageView, textView, textView2, imageView2);
            }
        }
        return viewGroup;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static PendingIntent getAttributionIntent(@NonNull Content content) {
        return content.getAttributionIntent();
    }

    private static Context getDefaultContextThemeWrapper(@NonNull Context context) {
        Resources.Theme themeNewTheme = context.getResources().newTheme();
        themeNewTheme.applyStyle(R.style.Theme_AutofillInlineSuggestion, true);
        return new ContextThemeWrapper(context, themeNewTheme);
    }

    private InlineSuggestionUi() {
    }

    public static final class Style extends BundledStyle implements UiVersions.Style {
        private static final String KEY_CHIP_STYLE = "chip_style";
        private static final String KEY_END_ICON_STYLE = "end_icon_style";
        private static final String KEY_LAYOUT_DIRECTION = "layout_direction";
        private static final String KEY_SINGLE_ICON_CHIP_ICON_STYLE = "single_icon_chip_icon_style";
        private static final String KEY_SINGLE_ICON_CHIP_STYLE = "single_icon_chip_style";
        private static final String KEY_START_ICON_STYLE = "start_icon_style";
        private static final String KEY_STYLE_V1 = "style_v1";
        private static final String KEY_SUBTITLE_STYLE = "subtitle_style";
        private static final String KEY_TITLE_STYLE = "title_style";

        @Override // androidx.autofill.inline.common.BundledStyle
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        protected String getStyleKey() {
            return KEY_STYLE_V1;
        }

        @Override // androidx.autofill.inline.UiVersions.Style
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public String getVersion() {
            return UiVersions.INLINE_UI_VERSION_1;
        }

        Style(@NonNull Bundle bundle) {
            super(bundle);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public void applyStyle(@NonNull View view, @NonNull ImageView imageView) {
            if (isValid()) {
                view.setLayoutDirection(getLayoutDirection());
                if (imageView.getVisibility() != 8) {
                    ImageViewStyle singleIconChipIconStyle = getSingleIconChipIconStyle();
                    if (singleIconChipIconStyle == null) {
                        singleIconChipIconStyle = getStartIconStyle();
                    }
                    if (singleIconChipIconStyle != null) {
                        singleIconChipIconStyle.applyStyleOnImageViewIfValid(imageView);
                    }
                }
                ViewStyle singleIconChipStyle = getSingleIconChipStyle();
                if (singleIconChipStyle == null) {
                    singleIconChipStyle = getChipStyle();
                }
                if (singleIconChipStyle != null) {
                    singleIconChipStyle.applyStyleOnViewIfValid(view);
                }
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public void applyStyle(@NonNull View view, @NonNull ImageView imageView, @NonNull TextView textView, @NonNull TextView textView2, @NonNull ImageView imageView2) {
            ImageViewStyle endIconStyle;
            TextViewStyle subtitleStyle;
            TextViewStyle titleStyle;
            ImageViewStyle startIconStyle;
            if (isValid()) {
                view.setLayoutDirection(getLayoutDirection());
                if (imageView.getVisibility() != 8 && (startIconStyle = getStartIconStyle()) != null) {
                    startIconStyle.applyStyleOnImageViewIfValid(imageView);
                }
                if (textView.getVisibility() != 8 && (titleStyle = getTitleStyle()) != null) {
                    titleStyle.applyStyleOnTextViewIfValid(textView);
                }
                if (textView2.getVisibility() != 8 && (subtitleStyle = getSubtitleStyle()) != null) {
                    subtitleStyle.applyStyleOnTextViewIfValid(textView2);
                }
                if (imageView2.getVisibility() != 8 && (endIconStyle = getEndIconStyle()) != null) {
                    endIconStyle.applyStyleOnImageViewIfValid(imageView2);
                }
                ViewStyle chipStyle = getChipStyle();
                if (chipStyle != null) {
                    chipStyle.applyStyleOnViewIfValid(view);
                }
            }
        }

        public int getLayoutDirection() {
            int i = this.mBundle.getInt(KEY_LAYOUT_DIRECTION, 0);
            if (i == 0 || i == 1) {
                return i;
            }
            return 0;
        }

        @Nullable
        public ViewStyle getChipStyle() {
            Bundle bundle = this.mBundle.getBundle(KEY_CHIP_STYLE);
            if (bundle == null) {
                return null;
            }
            return new ViewStyle(bundle);
        }

        @Nullable
        public TextViewStyle getTitleStyle() {
            Bundle bundle = this.mBundle.getBundle(KEY_TITLE_STYLE);
            if (bundle == null) {
                return null;
            }
            return new TextViewStyle(bundle);
        }

        @Nullable
        public TextViewStyle getSubtitleStyle() {
            Bundle bundle = this.mBundle.getBundle(KEY_SUBTITLE_STYLE);
            if (bundle == null) {
                return null;
            }
            return new TextViewStyle(bundle);
        }

        @Nullable
        public ImageViewStyle getStartIconStyle() {
            Bundle bundle = this.mBundle.getBundle(KEY_START_ICON_STYLE);
            if (bundle == null) {
                return null;
            }
            return new ImageViewStyle(bundle);
        }

        @Nullable
        public ImageViewStyle getEndIconStyle() {
            Bundle bundle = this.mBundle.getBundle(KEY_END_ICON_STYLE);
            if (bundle == null) {
                return null;
            }
            return new ImageViewStyle(bundle);
        }

        @Nullable
        public ViewStyle getSingleIconChipStyle() {
            Bundle bundle = this.mBundle.getBundle(KEY_SINGLE_ICON_CHIP_STYLE);
            if (bundle == null) {
                return null;
            }
            return new ViewStyle(bundle);
        }

        @Nullable
        public ImageViewStyle getSingleIconChipIconStyle() {
            Bundle bundle = this.mBundle.getBundle(KEY_SINGLE_ICON_CHIP_ICON_STYLE);
            if (bundle == null) {
                return null;
            }
            return new ImageViewStyle(bundle);
        }

        public static final class Builder extends BundledStyle.Builder<Style> {
            Builder() {
                super(Style.KEY_STYLE_V1);
            }

            @NonNull
            public Builder setLayoutDirection(int i) {
                this.mBundle.putInt(Style.KEY_LAYOUT_DIRECTION, i);
                return this;
            }

            @NonNull
            public Builder setChipStyle(@NonNull ViewStyle viewStyle) {
                viewStyle.assertIsValid();
                this.mBundle.putBundle(Style.KEY_CHIP_STYLE, viewStyle.getBundle());
                return this;
            }

            @NonNull
            public Builder setTitleStyle(@NonNull TextViewStyle textViewStyle) {
                textViewStyle.assertIsValid();
                this.mBundle.putBundle(Style.KEY_TITLE_STYLE, textViewStyle.getBundle());
                return this;
            }

            @NonNull
            public Builder setSubtitleStyle(@NonNull TextViewStyle textViewStyle) {
                textViewStyle.assertIsValid();
                this.mBundle.putBundle(Style.KEY_SUBTITLE_STYLE, textViewStyle.getBundle());
                return this;
            }

            @NonNull
            public Builder setStartIconStyle(@NonNull ImageViewStyle imageViewStyle) {
                imageViewStyle.assertIsValid();
                this.mBundle.putBundle(Style.KEY_START_ICON_STYLE, imageViewStyle.getBundle());
                return this;
            }

            @NonNull
            public Builder setEndIconStyle(@NonNull ImageViewStyle imageViewStyle) {
                imageViewStyle.assertIsValid();
                this.mBundle.putBundle(Style.KEY_END_ICON_STYLE, imageViewStyle.getBundle());
                return this;
            }

            @NonNull
            public Builder setSingleIconChipStyle(@NonNull ViewStyle viewStyle) {
                viewStyle.assertIsValid();
                this.mBundle.putBundle(Style.KEY_SINGLE_ICON_CHIP_STYLE, viewStyle.getBundle());
                return this;
            }

            @NonNull
            public Builder setSingleIconChipIconStyle(@NonNull ImageViewStyle imageViewStyle) {
                imageViewStyle.assertIsValid();
                this.mBundle.putBundle(Style.KEY_SINGLE_ICON_CHIP_ICON_STYLE, imageViewStyle.getBundle());
                return this;
            }

            @Override // androidx.autofill.inline.common.BundledStyle.Builder
            @NonNull
            public Style build() {
                return new Style(this.mBundle);
            }
        }
    }

    public static final class Content extends SlicedContent {
        static final String HINT_INLINE_ATTRIBUTION_INTENT = "inline_attribution";
        static final String HINT_INLINE_CONTENT_DESCRIPTION = "inline_content_description";
        static final String HINT_INLINE_END_ICON = "inline_end_icon";
        static final String HINT_INLINE_START_ICON = "inline_start_icon";
        static final String HINT_INLINE_SUBTITLE = "inline_subtitle";
        static final String HINT_INLINE_TITLE = "inline_title";

        @Nullable
        private PendingIntent mAttributionIntent;

        @Nullable
        private CharSequence mContentDescription;

        @Nullable
        private Icon mEndIcon;

        @Nullable
        private Icon mStartIcon;

        @Nullable
        private CharSequence mSubtitle;

        @Nullable
        private CharSequence mTitle;

        Content(@NonNull Slice slice) {
            super(slice);
            for (SliceItem sliceItem : slice.getItems()) {
                String strItemType = itemType(sliceItem);
                if (strItemType != null) {
                    switch (strItemType) {
                        case "inline_subtitle":
                            this.mSubtitle = sliceItem.getText().toString();
                            break;
                        case "inline_content_description":
                            this.mContentDescription = sliceItem.getText();
                            break;
                        case "inline_start_icon":
                            this.mStartIcon = sliceItem.getIcon();
                            break;
                        case "inline_title":
                            this.mTitle = sliceItem.getText().toString();
                            break;
                        case "inline_attribution":
                            this.mAttributionIntent = sliceItem.getAction();
                            break;
                        case "inline_end_icon":
                            this.mEndIcon = sliceItem.getIcon();
                            break;
                    }
                }
            }
        }

        boolean isSingleIconOnly() {
            return this.mStartIcon != null && this.mTitle == null && this.mSubtitle == null && this.mEndIcon == null;
        }

        @Nullable
        public CharSequence getTitle() {
            return this.mTitle;
        }

        @Nullable
        public CharSequence getSubtitle() {
            return this.mSubtitle;
        }

        @Nullable
        public Icon getStartIcon() {
            return this.mStartIcon;
        }

        @Nullable
        public Icon getEndIcon() {
            return this.mEndIcon;
        }

        @Nullable
        public CharSequence getContentDescription() {
            return this.mContentDescription;
        }

        @Override // androidx.autofill.inline.common.SlicedContent
        @Nullable
        public PendingIntent getAttributionIntent() {
            return this.mAttributionIntent;
        }

        @Override // androidx.autofill.inline.common.SlicedContent
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public boolean isValid() {
            return UiVersions.INLINE_UI_VERSION_1.equals(SlicedContent.getVersion(this.mSlice));
        }

        /* JADX WARN: Removed duplicated region for block: B:46:0x009b A[RETURN] */
        @androidx.annotation.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private static java.lang.String itemType(android.app.slice.SliceItem r3) {
            /*
                java.lang.String r0 = r3.getFormat()
                r0.hashCode()
                int r1 = r0.hashCode()
                r2 = -1
                switch(r1) {
                    case -1422950858: goto L26;
                    case 3556653: goto L1b;
                    case 100313435: goto L10;
                    default: goto Lf;
                }
            Lf:
                goto L30
            L10:
                java.lang.String r1 = "image"
                boolean r0 = r0.equals(r1)
                if (r0 != 0) goto L19
                goto L30
            L19:
                r2 = 2
                goto L30
            L1b:
                java.lang.String r1 = "text"
                boolean r0 = r0.equals(r1)
                if (r0 != 0) goto L24
                goto L30
            L24:
                r2 = 1
                goto L30
            L26:
                java.lang.String r1 = "action"
                boolean r0 = r0.equals(r1)
                if (r0 != 0) goto L2f
                goto L30
            L2f:
                r2 = 0
            L30:
                r0 = 0
                switch(r2) {
                    case 0: goto L88;
                    case 1: goto L56;
                    case 2: goto L35;
                    default: goto L34;
                }
            L34:
                return r0
            L35:
                android.graphics.drawable.Icon r1 = r3.getIcon()
                if (r1 != 0) goto L3c
                return r0
            L3c:
                java.util.List r1 = r3.getHints()
                java.lang.String r2 = "inline_start_icon"
                boolean r1 = r1.contains(r2)
                if (r1 == 0) goto L49
                return r2
            L49:
                java.util.List r3 = r3.getHints()
                java.lang.String r1 = "inline_end_icon"
                boolean r3 = r3.contains(r1)
                if (r3 == 0) goto L9b
                return r1
            L56:
                java.lang.CharSequence r1 = r3.getText()
                boolean r1 = android.text.TextUtils.isEmpty(r1)
                if (r1 == 0) goto L61
                return r0
            L61:
                java.util.List r1 = r3.getHints()
                java.lang.String r2 = "inline_title"
                boolean r1 = r1.contains(r2)
                if (r1 == 0) goto L6e
                return r2
            L6e:
                java.util.List r1 = r3.getHints()
                java.lang.String r2 = "inline_subtitle"
                boolean r1 = r1.contains(r2)
                if (r1 == 0) goto L7b
                return r2
            L7b:
                java.util.List r3 = r3.getHints()
                java.lang.String r1 = "inline_content_description"
                boolean r3 = r3.contains(r1)
                if (r3 == 0) goto L9b
                return r1
            L88:
                android.app.PendingIntent r1 = r3.getAction()
                if (r1 == 0) goto L9b
                java.util.List r3 = r3.getHints()
                java.lang.String r1 = "inline_attribution"
                boolean r3 = r3.contains(r1)
                if (r3 == 0) goto L9b
                return r1
            L9b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.autofill.inline.v1.InlineSuggestionUi.Content.itemType(android.app.slice.SliceItem):java.lang.String");
        }

        public static final class Builder extends SlicedContent.Builder<Content> {

            @NonNull
            private final PendingIntent mAttributionIntent;

            @Nullable
            private CharSequence mContentDescription;

            @Nullable
            private Icon mEndIcon;

            @Nullable
            private List<String> mHints;

            @Nullable
            private Icon mStartIcon;

            @Nullable
            private CharSequence mSubtitle;

            @Nullable
            private CharSequence mTitle;

            Builder(@NonNull PendingIntent pendingIntent) {
                super(UiVersions.INLINE_UI_VERSION_1);
                this.mAttributionIntent = pendingIntent;
            }

            @NonNull
            public Builder setTitle(@NonNull CharSequence charSequence) {
                this.mTitle = charSequence;
                return this;
            }

            @NonNull
            public Builder setSubtitle(@NonNull CharSequence charSequence) {
                this.mSubtitle = charSequence;
                return this;
            }

            @NonNull
            public Builder setStartIcon(@NonNull Icon icon) {
                this.mStartIcon = icon;
                return this;
            }

            @NonNull
            public Builder setEndIcon(@NonNull Icon icon) {
                this.mEndIcon = icon;
                return this;
            }

            @NonNull
            public Builder setContentDescription(@NonNull CharSequence charSequence) {
                this.mContentDescription = charSequence;
                return this;
            }

            @NonNull
            public Builder setHints(@NonNull List<String> list) {
                this.mHints = list;
                return this;
            }

            @Override // androidx.autofill.inline.common.SlicedContent.Builder
            @NonNull
            public Content build() {
                CharSequence charSequence = this.mTitle;
                if (charSequence == null && this.mStartIcon == null && this.mEndIcon == null && this.mSubtitle == null) {
                    throw new IllegalStateException("Title, subtitle, start icon, end icon are all null. Please set value for at least one of them");
                }
                if (charSequence == null && this.mSubtitle != null) {
                    throw new IllegalStateException("Cannot set the subtitle without setting the title.");
                }
                if (this.mAttributionIntent == null) {
                    throw new IllegalStateException("Attribution intent cannot be null.");
                }
                Icon icon = this.mStartIcon;
                if (icon != null) {
                    this.mSliceBuilder.addIcon(icon, null, Collections.singletonList(Content.HINT_INLINE_START_ICON));
                }
                CharSequence charSequence2 = this.mTitle;
                if (charSequence2 != null) {
                    this.mSliceBuilder.addText(charSequence2, null, Collections.singletonList(Content.HINT_INLINE_TITLE));
                }
                CharSequence charSequence3 = this.mSubtitle;
                if (charSequence3 != null) {
                    this.mSliceBuilder.addText(charSequence3, null, Collections.singletonList(Content.HINT_INLINE_SUBTITLE));
                }
                Icon icon2 = this.mEndIcon;
                if (icon2 != null) {
                    this.mSliceBuilder.addIcon(icon2, null, Collections.singletonList(Content.HINT_INLINE_END_ICON));
                }
                PendingIntent pendingIntent = this.mAttributionIntent;
                if (pendingIntent != null) {
                    this.mSliceBuilder.addAction(pendingIntent, new Slice.Builder(this.mSliceBuilder).addHints(Collections.singletonList(Content.HINT_INLINE_ATTRIBUTION_INTENT)).build(), null);
                }
                CharSequence charSequence4 = this.mContentDescription;
                if (charSequence4 != null) {
                    this.mSliceBuilder.addText(charSequence4, null, Collections.singletonList(Content.HINT_INLINE_CONTENT_DESCRIPTION));
                }
                List<String> list = this.mHints;
                if (list != null) {
                    this.mSliceBuilder.addHints(list);
                }
                return new Content(this.mSliceBuilder.build());
            }
        }
    }
}
