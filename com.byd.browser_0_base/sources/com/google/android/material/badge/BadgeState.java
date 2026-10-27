package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import com.google.android.material.R$dimen;
import com.google.android.material.R$plurals;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.r83;
import defpackage.rc1;
import java.io.IOException;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class BadgeState {
    public final State a;
    public final State b;
    public final float c;
    public final float d;
    public final float e;

    public BadgeState(Context context, State state) {
        AttributeSet attributeSetAsAttributeSet;
        int styleAttribute;
        int next;
        int i = a.s;
        int i2 = a.r;
        this.b = new State();
        state = state == null ? new State() : state;
        int i3 = state.e;
        if (i3 != 0) {
            try {
                XmlResourceParser xml = context.getResources().getXml(i3);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                if (!TextUtils.equals(xml.getName(), "badge")) {
                    throw new XmlPullParserException("Must have a <" + ((Object) "badge") + "> start tag");
                }
                attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                styleAttribute = attributeSetAsAttributeSet.getStyleAttribute();
            } catch (IOException | XmlPullParserException e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load badge resource ID #0x" + Integer.toHexString(i3));
                notFoundException.initCause(e);
                throw notFoundException;
            }
        } else {
            attributeSetAsAttributeSet = null;
            styleAttribute = 0;
        }
        TypedArray typedArrayD = r83.d(context, attributeSetAsAttributeSet, R$styleable.Badge, i, styleAttribute == 0 ? i2 : styleAttribute, new int[0]);
        Resources resources = context.getResources();
        this.c = typedArrayD.getDimensionPixelSize(R$styleable.Badge_badgeRadius, resources.getDimensionPixelSize(R$dimen.mtrl_badge_radius));
        this.e = typedArrayD.getDimensionPixelSize(R$styleable.Badge_badgeWidePadding, resources.getDimensionPixelSize(R$dimen.mtrl_badge_long_text_horizontal_padding));
        this.d = typedArrayD.getDimensionPixelSize(R$styleable.Badge_badgeWithTextRadius, resources.getDimensionPixelSize(R$dimen.mtrl_badge_with_text_radius));
        State state2 = this.b;
        int i4 = state.h;
        state2.h = i4 == -2 ? 255 : i4;
        CharSequence charSequence = state.l;
        state2.l = charSequence == null ? context.getString(R$string.mtrl_badge_numberless_content_description) : charSequence;
        State state3 = this.b;
        int i5 = state.m;
        state3.m = i5 == 0 ? R$plurals.mtrl_badge_content_description : i5;
        int i6 = state.n;
        state3.n = i6 == 0 ? R$string.mtrl_exceed_max_badge_number_content_description : i6;
        Boolean bool = state.p;
        state3.p = Boolean.valueOf(bool == null || bool.booleanValue());
        State state4 = this.b;
        int i7 = state.j;
        state4.j = i7 == -2 ? typedArrayD.getInt(R$styleable.Badge_maxCharacterCount, 4) : i7;
        int i8 = state.i;
        if (i8 != -2) {
            this.b.i = i8;
        } else {
            int i9 = R$styleable.Badge_number;
            if (typedArrayD.hasValue(i9)) {
                this.b.i = typedArrayD.getInt(i9, 0);
            } else {
                this.b.i = -1;
            }
        }
        State state5 = this.b;
        Integer num = state.f;
        state5.f = Integer.valueOf(num == null ? rc1.a(context, typedArrayD, R$styleable.Badge_backgroundColor).getDefaultColor() : num.intValue());
        Integer num2 = state.g;
        if (num2 != null) {
            this.b.g = num2;
        } else {
            int i10 = R$styleable.Badge_badgeTextColor;
            if (typedArrayD.hasValue(i10)) {
                this.b.g = Integer.valueOf(rc1.a(context, typedArrayD, i10).getDefaultColor());
            } else {
                int i11 = R$style.TextAppearance_MaterialComponents_Badge;
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i11, R$styleable.TextAppearance);
                typedArrayObtainStyledAttributes.getDimension(R$styleable.TextAppearance_android_textSize, 0.0f);
                ColorStateList colorStateListA = rc1.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColor);
                rc1.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColorHint);
                rc1.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColorLink);
                typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_textStyle, 0);
                typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_typeface, 1);
                int i12 = R$styleable.TextAppearance_fontFamily;
                i12 = typedArrayObtainStyledAttributes.hasValue(i12) ? i12 : R$styleable.TextAppearance_android_fontFamily;
                typedArrayObtainStyledAttributes.getResourceId(i12, 0);
                typedArrayObtainStyledAttributes.getString(i12);
                typedArrayObtainStyledAttributes.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
                rc1.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_shadowColor);
                typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDx, 0.0f);
                typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDy, 0.0f);
                typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowRadius, 0.0f);
                typedArrayObtainStyledAttributes.recycle();
                TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(i11, R$styleable.MaterialTextAppearance);
                int i13 = R$styleable.MaterialTextAppearance_android_letterSpacing;
                typedArrayObtainStyledAttributes2.hasValue(i13);
                typedArrayObtainStyledAttributes2.getFloat(i13, 0.0f);
                typedArrayObtainStyledAttributes2.recycle();
                this.b.g = Integer.valueOf(colorStateListA.getDefaultColor());
            }
        }
        State state6 = this.b;
        Integer num3 = state.o;
        state6.o = Integer.valueOf(num3 == null ? typedArrayD.getInt(R$styleable.Badge_badgeGravity, 8388661) : num3.intValue());
        State state7 = this.b;
        Integer num4 = state.q;
        state7.q = Integer.valueOf(num4 == null ? typedArrayD.getDimensionPixelOffset(R$styleable.Badge_horizontalOffset, 0) : num4.intValue());
        this.b.r = Integer.valueOf(state.q == null ? typedArrayD.getDimensionPixelOffset(R$styleable.Badge_verticalOffset, 0) : state.r.intValue());
        State state8 = this.b;
        Integer num5 = state.s;
        state8.s = Integer.valueOf(num5 == null ? typedArrayD.getDimensionPixelOffset(R$styleable.Badge_horizontalOffsetWithText, state8.q.intValue()) : num5.intValue());
        State state9 = this.b;
        Integer num6 = state.t;
        state9.t = Integer.valueOf(num6 == null ? typedArrayD.getDimensionPixelOffset(R$styleable.Badge_verticalOffsetWithText, state9.r.intValue()) : num6.intValue());
        State state10 = this.b;
        Integer num7 = state.u;
        state10.u = Integer.valueOf(num7 == null ? 0 : num7.intValue());
        State state11 = this.b;
        Integer num8 = state.v;
        state11.v = Integer.valueOf(num8 != null ? num8.intValue() : 0);
        typedArrayD.recycle();
        Locale locale = state.k;
        if (locale == null) {
            this.b.k = Locale.getDefault(Locale.Category.FORMAT);
        } else {
            this.b.k = locale;
        }
        this.a = state;
    }

    public static final class State implements Parcelable {
        public static final Parcelable.Creator<State> CREATOR = new a();
        public int e;
        public Integer f;
        public Integer g;
        public int h;
        public int i;
        public int j;
        public Locale k;
        public CharSequence l;
        public int m;
        public int n;
        public Integer o;
        public Boolean p;
        public Integer q;
        public Integer r;
        public Integer s;
        public Integer t;
        public Integer u;
        public Integer v;

        public class a implements Parcelable.Creator<State> {
            @Override // android.os.Parcelable.Creator
            public final State createFromParcel(Parcel parcel) {
                return new State(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final State[] newArray(int i) {
                return new State[i];
            }
        }

        public State() {
            this.h = 255;
            this.i = -2;
            this.j = -2;
            this.p = Boolean.TRUE;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.e);
            parcel.writeSerializable(this.f);
            parcel.writeSerializable(this.g);
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
            parcel.writeInt(this.j);
            CharSequence charSequence = this.l;
            parcel.writeString(charSequence == null ? null : charSequence.toString());
            parcel.writeInt(this.m);
            parcel.writeSerializable(this.o);
            parcel.writeSerializable(this.q);
            parcel.writeSerializable(this.r);
            parcel.writeSerializable(this.s);
            parcel.writeSerializable(this.t);
            parcel.writeSerializable(this.u);
            parcel.writeSerializable(this.v);
            parcel.writeSerializable(this.p);
            parcel.writeSerializable(this.k);
        }

        public State(Parcel parcel) {
            this.h = 255;
            this.i = -2;
            this.j = -2;
            this.p = Boolean.TRUE;
            this.e = parcel.readInt();
            this.f = (Integer) parcel.readSerializable();
            this.g = (Integer) parcel.readSerializable();
            this.h = parcel.readInt();
            this.i = parcel.readInt();
            this.j = parcel.readInt();
            this.l = parcel.readString();
            this.m = parcel.readInt();
            this.o = (Integer) parcel.readSerializable();
            this.q = (Integer) parcel.readSerializable();
            this.r = (Integer) parcel.readSerializable();
            this.s = (Integer) parcel.readSerializable();
            this.t = (Integer) parcel.readSerializable();
            this.u = (Integer) parcel.readSerializable();
            this.v = (Integer) parcel.readSerializable();
            this.p = (Boolean) parcel.readSerializable();
            this.k = (Locale) parcel.readSerializable();
        }
    }
}
