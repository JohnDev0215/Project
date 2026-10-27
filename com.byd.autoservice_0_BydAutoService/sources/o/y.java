package o;

import android.R;
import android.annotation.SuppressLint;
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
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.widget.SearchView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SuggestionsAdapter.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public class y extends o0.c implements View.OnClickListener {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final SearchView f4407m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final SearchableInfo f4408n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Context f4409o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final WeakHashMap<String, Drawable.ConstantState> f4410p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f4411q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f4412r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f4413s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ColorStateList f4414t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f4415u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f4416v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f4417w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f4418x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f4419y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f4420z;

    /* JADX INFO: compiled from: SuggestionsAdapter.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f4421a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f4422b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ImageView f4423c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ImageView f4424d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final ImageView f4425e;

        public a(View view) {
            this.f4421a = (TextView) view.findViewById(R.id.text1);
            this.f4422b = (TextView) view.findViewById(R.id.text2);
            this.f4423c = (ImageView) view.findViewById(R.id.icon1);
            this.f4424d = (ImageView) view.findViewById(R.id.icon2);
            this.f4425e = (ImageView) view.findViewById(R$id.edit_query);
        }
    }

    public y(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.f4412r = false;
        this.f4413s = 1;
        this.f4415u = -1;
        this.f4416v = -1;
        this.f4417w = -1;
        this.f4418x = -1;
        this.f4419y = -1;
        this.f4420z = -1;
        this.f4407m = searchView;
        this.f4408n = searchableInfo;
        this.f4411q = searchView.getSuggestionCommitIconResId();
        this.f4409o = context;
        this.f4410p = weakHashMap;
    }

    public static String k(Cursor cursor, String str) {
        return s(cursor, cursor.getColumnIndex(str));
    }

    public static String s(Cursor cursor, int i3) {
        if (i3 == -1) {
            return null;
        }
        try {
            return cursor.getString(i3);
        } catch (Exception e3) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e3);
            return null;
        }
    }

    @Override // o0.a
    public void a(View view, Context context, Cursor cursor) {
        a aVar = (a) view.getTag();
        int i3 = this.f4420z;
        int i4 = i3 != -1 ? cursor.getInt(i3) : 0;
        if (aVar.f4421a != null) {
            v(aVar.f4421a, s(cursor, this.f4415u));
        }
        if (aVar.f4422b != null) {
            String strS = s(cursor, this.f4417w);
            CharSequence charSequenceH = strS != null ? h(strS) : s(cursor, this.f4416v);
            if (TextUtils.isEmpty(charSequenceH)) {
                TextView textView = aVar.f4421a;
                if (textView != null) {
                    textView.setSingleLine(false);
                    aVar.f4421a.setMaxLines(2);
                }
            } else {
                TextView textView2 = aVar.f4421a;
                if (textView2 != null) {
                    textView2.setSingleLine(true);
                    aVar.f4421a.setMaxLines(1);
                }
            }
            v(aVar.f4422b, charSequenceH);
        }
        ImageView imageView = aVar.f4423c;
        if (imageView != null) {
            u(imageView, p(cursor), 4);
        }
        ImageView imageView2 = aVar.f4424d;
        if (imageView2 != null) {
            u(imageView2, q(cursor), 8);
        }
        int i5 = this.f4413s;
        if (i5 != 2 && (i5 != 1 || (i4 & 1) == 0)) {
            aVar.f4425e.setVisibility(8);
            return;
        }
        aVar.f4425e.setVisibility(0);
        aVar.f4425e.setTag(aVar.f4421a.getText());
        aVar.f4425e.setOnClickListener(this);
    }

    @Override // o0.a, o0.b.a
    public void changeCursor(Cursor cursor) {
        if (this.f4412r) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.changeCursor(cursor);
            if (cursor != null) {
                this.f4415u = cursor.getColumnIndex("suggest_text_1");
                this.f4416v = cursor.getColumnIndex("suggest_text_2");
                this.f4417w = cursor.getColumnIndex("suggest_text_2_url");
                this.f4418x = cursor.getColumnIndex("suggest_icon_1");
                this.f4419y = cursor.getColumnIndex("suggest_icon_2");
                this.f4420z = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e3) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e3);
        }
    }

    @Override // o0.a, o0.b.a
    public CharSequence convertToString(Cursor cursor) {
        String strK;
        String strK2;
        if (cursor == null) {
            return null;
        }
        String strK3 = k(cursor, "suggest_intent_query");
        if (strK3 != null) {
            return strK3;
        }
        if (this.f4408n.shouldRewriteQueryFromData() && (strK2 = k(cursor, "suggest_intent_data")) != null) {
            return strK2;
        }
        if (!this.f4408n.shouldRewriteQueryFromText() || (strK = k(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return strK;
    }

    @Override // o0.c, o0.a
    public View d(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewD = super.d(context, cursor, viewGroup);
        viewD.setTag(new a(viewD));
        ((ImageView) viewD.findViewById(R$id.edit_query)).setImageResource(this.f4411q);
        return viewD;
    }

    public final Drawable g(String str) {
        Drawable.ConstantState constantState = this.f4410p.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    @Override // o0.a, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i3, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i3, view, viewGroup);
        } catch (RuntimeException e3) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e3);
            View viewC = c(this.f4439e, this.f4438d, viewGroup);
            if (viewC != null) {
                ((a) viewC.getTag()).f4421a.setText(e3.toString());
            }
            return viewC;
        }
    }

    @Override // o0.a, android.widget.Adapter
    public View getView(int i3, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i3, view, viewGroup);
        } catch (RuntimeException e3) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e3);
            View viewD = d(this.f4439e, this.f4438d, viewGroup);
            if (viewD != null) {
                ((a) viewD.getTag()).f4421a.setText(e3.toString());
            }
            return viewD;
        }
    }

    public final CharSequence h(CharSequence charSequence) {
        if (this.f4414t == null) {
            TypedValue typedValue = new TypedValue();
            this.f4439e.getTheme().resolveAttribute(R$attr.textColorSearchUrl, typedValue, true);
            this.f4414t = this.f4439e.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.f4414t, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return false;
    }

    public final Drawable i(ComponentName componentName) {
        PackageManager packageManager = this.f4439e.getPackageManager();
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
        } catch (PackageManager.NameNotFoundException e3) {
            Log.w("SuggestionsAdapter", e3.toString());
            return null;
        }
    }

    public final Drawable j(ComponentName componentName) {
        String strFlattenToShortString = componentName.flattenToShortString();
        if (!this.f4410p.containsKey(strFlattenToShortString)) {
            Drawable drawableI = i(componentName);
            this.f4410p.put(strFlattenToShortString, drawableI != null ? drawableI.getConstantState() : null);
            return drawableI;
        }
        Drawable.ConstantState constantState = this.f4410p.get(strFlattenToShortString);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable(this.f4409o.getResources());
    }

    public final Drawable l() {
        Drawable drawableJ = j(this.f4408n.getSearchActivity());
        return drawableJ != null ? drawableJ : this.f4439e.getPackageManager().getDefaultActivityIcon();
    }

    public final Drawable m(Uri uri) {
        try {
            if ("android.resource".equals(uri.getScheme())) {
                try {
                    return n(uri);
                } catch (Resources.NotFoundException unused) {
                    throw new FileNotFoundException("Resource does not exist: " + uri);
                }
            }
            InputStream inputStreamOpenInputStream = this.f4409o.getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            try {
                return Drawable.createFromStream(inputStreamOpenInputStream, null);
            } finally {
                try {
                    inputStreamOpenInputStream.close();
                } catch (IOException e3) {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e3);
                }
            }
        } catch (FileNotFoundException e4) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e4.getMessage());
            return null;
        }
        Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e4.getMessage());
        return null;
    }

    public Drawable n(Uri uri) throws FileNotFoundException {
        int identifier;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.f4439e.getPackageManager().getResourcesForApplication(authority);
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

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        x(getCursor());
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        x(getCursor());
    }

    public final Drawable o(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int i3 = Integer.parseInt(str);
            String str2 = "android.resource://" + this.f4409o.getPackageName() + "/" + i3;
            Drawable drawableG = g(str2);
            if (drawableG != null) {
                return drawableG;
            }
            Drawable drawableD = b0.a.d(this.f4409o, i3);
            w(str2, drawableD);
            return drawableD;
        } catch (Resources.NotFoundException unused) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + str);
            return null;
        } catch (NumberFormatException unused2) {
            Drawable drawableG2 = g(str);
            if (drawableG2 != null) {
                return drawableG2;
            }
            Drawable drawableM = m(Uri.parse(str));
            w(str, drawableM);
            return drawableM;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.f4407m.U((CharSequence) tag);
        }
    }

    public final Drawable p(Cursor cursor) {
        int i3 = this.f4418x;
        if (i3 == -1) {
            return null;
        }
        Drawable drawableO = o(cursor.getString(i3));
        return drawableO != null ? drawableO : l();
    }

    public final Drawable q(Cursor cursor) {
        int i3 = this.f4419y;
        if (i3 == -1) {
            return null;
        }
        return o(cursor.getString(i3));
    }

    public Cursor r(SearchableInfo searchableInfo, String str, int i3) {
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
        if (i3 > 0) {
            builderFragment.appendQueryParameter("limit", String.valueOf(i3));
        }
        return this.f4439e.getContentResolver().query(builderFragment.build(), null, suggestSelection, strArr2, null);
    }

    @Override // o0.b.a
    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        String string = charSequence == null ? "" : charSequence.toString();
        if (this.f4407m.getVisibility() == 0 && this.f4407m.getWindowVisibility() == 0) {
            try {
                Cursor cursorR = r(this.f4408n, string, 50);
                if (cursorR != null) {
                    cursorR.getCount();
                    return cursorR;
                }
            } catch (RuntimeException e3) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e3);
            }
        }
        return null;
    }

    public void t(int i3) {
        this.f4413s = i3;
    }

    public final void u(ImageView imageView, Drawable drawable, int i3) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i3);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    public final void v(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    public final void w(String str, Drawable drawable) {
        if (drawable != null) {
            this.f4410p.put(str, drawable.getConstantState());
        }
    }

    public final void x(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras == null || extras.getBoolean("in_progress")) {
        }
    }
}
