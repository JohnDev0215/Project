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
import androidx.autofill.R$id;
import androidx.autofill.R$layout;
import androidx.autofill.R$style;
import androidx.autofill.inline.common.BundledStyle;
import androidx.autofill.inline.common.ImageViewStyle;
import androidx.autofill.inline.common.SlicedContent;
import androidx.autofill.inline.common.TextViewStyle;
import androidx.autofill.inline.common.ViewStyle;

/* JADX INFO: loaded from: classes.dex */
public final class InlineSuggestionUi {
    public static Content fromSlice(Slice slice) {
        Content content = new Content(slice);
        if (content.isValid()) {
            return content;
        }
        Log.w("InlineSuggestionUi", "Invalid content for androidx.autofill.inline.ui.version:v1");
        return null;
    }

    public static Style fromBundle(Bundle bundle) {
        Style style = new Style(bundle);
        if (style.isValid()) {
            return style;
        }
        Log.w("InlineSuggestionUi", "Invalid style for androidx.autofill.inline.ui.version:v1");
        return null;
    }

    public static View render(Context context, Content content, Style style) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getDefaultContextThemeWrapper(context)).inflate(R$layout.autofill_inline_suggestion, (ViewGroup) null);
        ImageView imageView = (ImageView) viewGroup.findViewById(R$id.autofill_inline_suggestion_start_icon);
        TextView textView = (TextView) viewGroup.findViewById(R$id.autofill_inline_suggestion_title);
        TextView textView2 = (TextView) viewGroup.findViewById(R$id.autofill_inline_suggestion_subtitle);
        ImageView imageView2 = (ImageView) viewGroup.findViewById(R$id.autofill_inline_suggestion_end_icon);
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

    public static PendingIntent getAttributionIntent(Content content) {
        return content.getAttributionIntent();
    }

    private static Context getDefaultContextThemeWrapper(Context context) {
        Resources.Theme themeNewTheme = context.getResources().newTheme();
        themeNewTheme.applyStyle(R$style.Theme_AutofillInlineSuggestion, true);
        return new ContextThemeWrapper(context, themeNewTheme);
    }

    public static final class Style extends BundledStyle {
        @Override // androidx.autofill.inline.common.BundledStyle
        protected String getStyleKey() {
            return "style_v1";
        }

        Style(Bundle bundle) {
            super(bundle);
        }

        public void applyStyle(View view, ImageView imageView) {
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

        public void applyStyle(View view, ImageView imageView, TextView textView, TextView textView2, ImageView imageView2) {
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
            int i = this.mBundle.getInt("layout_direction", 0);
            if (i == 0 || i == 1) {
                return i;
            }
            return 0;
        }

        public ViewStyle getChipStyle() {
            Bundle bundle = this.mBundle.getBundle("chip_style");
            if (bundle == null) {
                return null;
            }
            return new ViewStyle(bundle);
        }

        public TextViewStyle getTitleStyle() {
            Bundle bundle = this.mBundle.getBundle("title_style");
            if (bundle == null) {
                return null;
            }
            return new TextViewStyle(bundle);
        }

        public TextViewStyle getSubtitleStyle() {
            Bundle bundle = this.mBundle.getBundle("subtitle_style");
            if (bundle == null) {
                return null;
            }
            return new TextViewStyle(bundle);
        }

        public ImageViewStyle getStartIconStyle() {
            Bundle bundle = this.mBundle.getBundle("start_icon_style");
            if (bundle == null) {
                return null;
            }
            return new ImageViewStyle(bundle);
        }

        public ImageViewStyle getEndIconStyle() {
            Bundle bundle = this.mBundle.getBundle("end_icon_style");
            if (bundle == null) {
                return null;
            }
            return new ImageViewStyle(bundle);
        }

        public ViewStyle getSingleIconChipStyle() {
            Bundle bundle = this.mBundle.getBundle("single_icon_chip_style");
            if (bundle == null) {
                return null;
            }
            return new ViewStyle(bundle);
        }

        public ImageViewStyle getSingleIconChipIconStyle() {
            Bundle bundle = this.mBundle.getBundle("single_icon_chip_icon_style");
            if (bundle == null) {
                return null;
            }
            return new ImageViewStyle(bundle);
        }
    }

    public static final class Content extends SlicedContent {
        private PendingIntent mAttributionIntent;
        private CharSequence mContentDescription;
        private Icon mEndIcon;
        private Icon mStartIcon;
        private CharSequence mSubtitle;
        private CharSequence mTitle;

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        Content(Slice slice) {
            super(slice);
            for (SliceItem sliceItem : slice.getItems()) {
                String strItemType = itemType(sliceItem);
                if (strItemType != null) {
                    byte b = -1;
                    switch (strItemType.hashCode()) {
                        case -1790855426:
                            if (strItemType.equals("inline_subtitle")) {
                                b = 1;
                            }
                            break;
                        case -1269099888:
                            if (strItemType.equals("inline_content_description")) {
                                b = 5;
                            }
                            break;
                        case -145102948:
                            if (strItemType.equals("inline_start_icon")) {
                                b = 2;
                            }
                            break;
                        case 729157938:
                            if (strItemType.equals("inline_title")) {
                                b = 0;
                            }
                            break;
                        case 1020097497:
                            if (strItemType.equals("inline_attribution")) {
                                b = 4;
                            }
                            break;
                        case 1994860611:
                            if (strItemType.equals("inline_end_icon")) {
                                b = 3;
                            }
                            break;
                    }
                    if (b == 0) {
                        this.mTitle = sliceItem.getText().toString();
                    } else if (b == 1) {
                        this.mSubtitle = sliceItem.getText().toString();
                    } else if (b == 2) {
                        this.mStartIcon = sliceItem.getIcon();
                    } else if (b == 3) {
                        this.mEndIcon = sliceItem.getIcon();
                    } else if (b == 4) {
                        this.mAttributionIntent = sliceItem.getAction();
                    } else if (b == 5) {
                        this.mContentDescription = sliceItem.getText();
                    }
                }
            }
        }

        boolean isSingleIconOnly() {
            return this.mStartIcon != null && this.mTitle == null && this.mSubtitle == null && this.mEndIcon == null;
        }

        public CharSequence getTitle() {
            return this.mTitle;
        }

        public CharSequence getSubtitle() {
            return this.mSubtitle;
        }

        public Icon getStartIcon() {
            return this.mStartIcon;
        }

        public Icon getEndIcon() {
            return this.mEndIcon;
        }

        public CharSequence getContentDescription() {
            return this.mContentDescription;
        }

        public PendingIntent getAttributionIntent() {
            return this.mAttributionIntent;
        }

        public boolean isValid() {
            return "androidx.autofill.inline.ui.version:v1".equals(SlicedContent.getVersion(this.mSlice));
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0038  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private static java.lang.String itemType(android.app.slice.SliceItem r5) {
            /*
                java.lang.String r0 = r5.getFormat()
                int r1 = r0.hashCode()
                r2 = -1422950858(0xffffffffab2f7e36, float:-6.234764E-13)
                r3 = 2
                r4 = 1
                if (r1 == r2) goto L2e
                r2 = 3556653(0x36452d, float:4.983932E-39)
                if (r1 == r2) goto L24
                r2 = 100313435(0x5faa95b, float:2.3572098E-35)
                if (r1 == r2) goto L1a
                goto L38
            L1a:
                java.lang.String r1 = "image"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L38
                r0 = 0
                goto L39
            L24:
                java.lang.String r1 = "text"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L38
                r0 = r4
                goto L39
            L2e:
                java.lang.String r1 = "action"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L38
                r0 = r3
                goto L39
            L38:
                r0 = -1
            L39:
                r1 = 0
                if (r0 == 0) goto L86
                if (r0 == r4) goto L54
                if (r0 == r3) goto L41
                return r1
            L41:
                android.app.PendingIntent r0 = r5.getAction()
                if (r0 == 0) goto La7
                java.util.List r5 = r5.getHints()
                java.lang.String r0 = "inline_attribution"
                boolean r5 = r5.contains(r0)
                if (r5 == 0) goto La7
                return r0
            L54:
                java.lang.CharSequence r0 = r5.getText()
                boolean r0 = android.text.TextUtils.isEmpty(r0)
                if (r0 == 0) goto L5f
                return r1
            L5f:
                java.util.List r0 = r5.getHints()
                java.lang.String r2 = "inline_title"
                boolean r0 = r0.contains(r2)
                if (r0 == 0) goto L6c
                return r2
            L6c:
                java.util.List r0 = r5.getHints()
                java.lang.String r2 = "inline_subtitle"
                boolean r0 = r0.contains(r2)
                if (r0 == 0) goto L79
                return r2
            L79:
                java.util.List r5 = r5.getHints()
                java.lang.String r0 = "inline_content_description"
                boolean r5 = r5.contains(r0)
                if (r5 == 0) goto La7
                return r0
            L86:
                android.graphics.drawable.Icon r0 = r5.getIcon()
                if (r0 != 0) goto L8d
                return r1
            L8d:
                java.util.List r0 = r5.getHints()
                java.lang.String r2 = "inline_start_icon"
                boolean r0 = r0.contains(r2)
                if (r0 == 0) goto L9a
                return r2
            L9a:
                java.util.List r5 = r5.getHints()
                java.lang.String r0 = "inline_end_icon"
                boolean r5 = r5.contains(r0)
                if (r5 == 0) goto La7
                return r0
            La7:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.autofill.inline.v1.InlineSuggestionUi.Content.itemType(android.app.slice.SliceItem):java.lang.String");
        }
    }
}
