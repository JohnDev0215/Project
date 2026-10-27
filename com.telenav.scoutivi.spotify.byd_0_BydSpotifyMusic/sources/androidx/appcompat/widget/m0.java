package androidx.appcompat.widget;

import android.R;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SuggestionsAdapter.java */
/* JADX INFO: loaded from: classes.dex */
class m0 extends l0.c implements View.OnClickListener {
    private int A;
    private int B;
    private int C;
    private int D;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final SearchView f1136q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private final SearchableInfo f1137r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private final Context f1138s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private final WeakHashMap<String, Drawable.ConstantState> f1139t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private final int f1140u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private boolean f1141v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f1142w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private ColorStateList f1143x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private int f1144y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private int f1145z;

    /* JADX INFO: compiled from: SuggestionsAdapter.java */
    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f1146a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f1147b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ImageView f1148c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ImageView f1149d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final ImageView f1150e;

        public a(View view) {
            this.f1146a = (TextView) view.findViewById(R.id.text1);
            this.f1147b = (TextView) view.findViewById(R.id.text2);
            this.f1148c = (ImageView) view.findViewById(R.id.icon1);
            this.f1149d = (ImageView) view.findViewById(R.id.icon2);
            this.f1150e = (ImageView) view.findViewById(e.f.edit_query);
        }
    }

    public m0(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.f1141v = false;
        this.f1142w = 1;
        this.f1144y = -1;
        this.f1145z = -1;
        this.A = -1;
        this.B = -1;
        this.C = -1;
        this.D = -1;
        this.f1136q = searchView;
        this.f1137r = searchableInfo;
        this.f1140u = searchView.getSuggestionCommitIconResId();
        this.f1138s = context;
        this.f1139t = weakHashMap;
    }

    private void A(String str, Drawable drawable) {
        if (drawable != null) {
            this.f1139t.put(str, drawable.getConstantState());
        }
    }

    private void B(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null) {
            extras.getBoolean("in_progress");
        }
    }

    private Drawable k(String str) {
        Drawable.ConstantState constantState = this.f1139t.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    private CharSequence l(CharSequence charSequence) {
        if (this.f1143x == null) {
            TypedValue typedValue = new TypedValue();
            this.f1138s.getTheme().resolveAttribute(e.a.textColorSearchUrl, typedValue, true);
            this.f1143x = this.f1138s.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.f1143x, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    private Drawable m(ComponentName componentName) {
        PackageManager packageManager = this.f1138s.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            Log.w("SuggestionsAdapter", "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString());
            return null;
        } catch (PackageManager.NameNotFoundException e7) {
            Log.w("SuggestionsAdapter", e7.toString());
            return null;
        }
    }

    private Drawable n(ComponentName componentName) {
        String strFlattenToShortString = componentName.flattenToShortString();
        if (!this.f1139t.containsKey(strFlattenToShortString)) {
            Drawable drawableM = m(componentName);
            this.f1139t.put(strFlattenToShortString, drawableM != null ? drawableM.getConstantState() : null);
            return drawableM;
        }
        Drawable.ConstantState constantState = this.f1139t.get(strFlattenToShortString);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable(this.f1138s.getResources());
    }

    public static String o(Cursor cursor, String str) {
        return w(cursor, cursor.getColumnIndex(str));
    }

    private Drawable p() {
        Drawable drawableN = n(this.f1137r.getSearchActivity());
        return drawableN != null ? drawableN : this.f1138s.getPackageManager().getDefaultActivityIcon();
    }

    private Drawable q(Uri uri) {
        try {
            if ("android.resource".equals(uri.getScheme())) {
                try {
                    return r(uri);
                } catch (Resources.NotFoundException unused) {
                    throw new FileNotFoundException("Resource does not exist: " + uri);
                }
            }
            InputStream inputStreamOpenInputStream = this.f1138s.getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            try {
                return Drawable.createFromStream(inputStreamOpenInputStream, null);
            } finally {
                try {
                    inputStreamOpenInputStream.close();
                } catch (IOException e7) {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e7);
                }
            }
        } catch (FileNotFoundException e8) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e8.getMessage());
            return null;
        }
        Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e8.getMessage());
        return null;
    }

    private Drawable s(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int i7 = Integer.parseInt(str);
            String str2 = "android.resource://" + this.f1138s.getPackageName() + "/" + i7;
            Drawable drawableK = k(str2);
            if (drawableK != null) {
                return drawableK;
            }
            Drawable drawableC = x.a.c(this.f1138s, i7);
            A(str2, drawableC);
            return drawableC;
        } catch (Resources.NotFoundException unused) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + str);
            return null;
        } catch (NumberFormatException unused2) {
            Drawable drawableK2 = k(str);
            if (drawableK2 != null) {
                return drawableK2;
            }
            Drawable drawableQ = q(Uri.parse(str));
            A(str, drawableQ);
            return drawableQ;
        }
    }

    private Drawable t(Cursor cursor) {
        int i7 = this.B;
        if (i7 == -1) {
            return null;
        }
        Drawable drawableS = s(cursor.getString(i7));
        return drawableS != null ? drawableS : p();
    }

    private Drawable u(Cursor cursor) {
        int i7 = this.C;
        if (i7 == -1) {
            return null;
        }
        return s(cursor.getString(i7));
    }

    private static String w(Cursor cursor, int i7) {
        if (i7 == -1) {
            return null;
        }
        try {
            return cursor.getString(i7);
        } catch (Exception e7) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e7);
            return null;
        }
    }

    private void y(ImageView imageView, Drawable drawable, int i7) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i7);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    private void z(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    @Override // l0.a, l0.b.a
    public CharSequence a(Cursor cursor) {
        String strO;
        String strO2;
        if (cursor == null) {
            return null;
        }
        String strO3 = o(cursor, "suggest_intent_query");
        if (strO3 != null) {
            return strO3;
        }
        if (this.f1137r.shouldRewriteQueryFromData() && (strO2 = o(cursor, "suggest_intent_data")) != null) {
            return strO2;
        }
        if (!this.f1137r.shouldRewriteQueryFromText() || (strO = o(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return strO;
    }

    @Override // l0.a, l0.b.a
    public void b(Cursor cursor) {
        if (this.f1141v) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.b(cursor);
            if (cursor != null) {
                this.f1144y = cursor.getColumnIndex("suggest_text_1");
                this.f1145z = cursor.getColumnIndex("suggest_text_2");
                this.A = cursor.getColumnIndex("suggest_text_2_url");
                this.B = cursor.getColumnIndex("suggest_icon_1");
                this.C = cursor.getColumnIndex("suggest_icon_2");
                this.D = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e7) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e7);
        }
    }

    @Override // l0.b.a
    public Cursor d(CharSequence charSequence) {
        String string = charSequence == null ? "" : charSequence.toString();
        if (this.f1136q.getVisibility() == 0 && this.f1136q.getWindowVisibility() == 0) {
            try {
                Cursor cursorV = v(this.f1137r, string, 50);
                if (cursorV != null) {
                    cursorV.getCount();
                    return cursorV;
                }
            } catch (RuntimeException e7) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e7);
            }
        }
        return null;
    }

    @Override // l0.a
    public void e(View view, Context context, Cursor cursor) {
        a aVar = (a) view.getTag();
        int i7 = this.D;
        int i8 = i7 != -1 ? cursor.getInt(i7) : 0;
        if (aVar.f1146a != null) {
            z(aVar.f1146a, w(cursor, this.f1144y));
        }
        if (aVar.f1147b != null) {
            String strW = w(cursor, this.A);
            CharSequence charSequenceL = strW != null ? l(strW) : w(cursor, this.f1145z);
            if (TextUtils.isEmpty(charSequenceL)) {
                TextView textView = aVar.f1146a;
                if (textView != null) {
                    textView.setSingleLine(false);
                    aVar.f1146a.setMaxLines(2);
                }
            } else {
                TextView textView2 = aVar.f1146a;
                if (textView2 != null) {
                    textView2.setSingleLine(true);
                    aVar.f1146a.setMaxLines(1);
                }
            }
            z(aVar.f1147b, charSequenceL);
        }
        ImageView imageView = aVar.f1148c;
        if (imageView != null) {
            y(imageView, t(cursor), 4);
        }
        ImageView imageView2 = aVar.f1149d;
        if (imageView2 != null) {
            y(imageView2, u(cursor), 8);
        }
        int i9 = this.f1142w;
        if (i9 != 2 && (i9 != 1 || (i8 & 1) == 0)) {
            aVar.f1150e.setVisibility(8);
            return;
        }
        aVar.f1150e.setVisibility(0);
        aVar.f1150e.setTag(aVar.f1146a.getText());
        aVar.f1150e.setOnClickListener(this);
    }

    @Override // l0.a, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i7, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i7, view, viewGroup);
        } catch (RuntimeException e7) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e7);
            View viewG = g(this.f1138s, c(), viewGroup);
            if (viewG != null) {
                ((a) viewG.getTag()).f1146a.setText(e7.toString());
            }
            return viewG;
        }
    }

    @Override // l0.a, android.widget.Adapter
    public View getView(int i7, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i7, view, viewGroup);
        } catch (RuntimeException e7) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e7);
            View viewH = h(this.f1138s, c(), viewGroup);
            if (viewH != null) {
                ((a) viewH.getTag()).f1146a.setText(e7.toString());
            }
            return viewH;
        }
    }

    @Override // l0.c, l0.a
    public View h(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewH = super.h(context, cursor, viewGroup);
        viewH.setTag(new a(viewH));
        ((ImageView) viewH.findViewById(e.f.edit_query)).setImageResource(this.f1140u);
        return viewH;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        B(c());
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        B(c());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.f1136q.U((CharSequence) tag);
        }
    }

    Drawable r(Uri uri) throws FileNotFoundException {
        int identifier;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.f1138s.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    identifier = Integer.parseInt(pathSegments.get(0));
                } catch (NumberFormatException unused) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else {
                if (size != 2) {
                    throw new FileNotFoundException("More than two path segments: " + uri);
                }
                identifier = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
            }
            if (identifier != 0) {
                return resourcesForApplication.getDrawable(identifier);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (PackageManager.NameNotFoundException unused2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    Cursor v(SearchableInfo searchableInfo, String str, int i7) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder builderFragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            builderFragment.appendEncodedPath(suggestPath);
        }
        builderFragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            builderFragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        if (i7 > 0) {
            builderFragment.appendQueryParameter("limit", String.valueOf(i7));
        }
        return this.f1138s.getContentResolver().query(builderFragment.build(), null, suggestSelection, strArr2, null);
    }

    public void x(int i7) {
        this.f1142w = i7;
    }
}
