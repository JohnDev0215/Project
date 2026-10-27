package r;

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
public class y extends s0.c implements View.OnClickListener {
    public int A;
    public int B;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final SearchView f7666o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final SearchableInfo f7667p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Context f7668q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final WeakHashMap<String, Drawable.ConstantState> f7669r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f7670s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f7671t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f7672u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ColorStateList f7673v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f7674w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f7675x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f7676y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f7677z;

    /* JADX INFO: compiled from: SuggestionsAdapter.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f7678a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f7679b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ImageView f7680c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ImageView f7681d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final ImageView f7682e;

        public a(View view) {
            this.f7678a = (TextView) view.findViewById(R.id.text1);
            this.f7679b = (TextView) view.findViewById(R.id.text2);
            this.f7680c = (ImageView) view.findViewById(R.id.icon1);
            this.f7681d = (ImageView) view.findViewById(R.id.icon2);
            this.f7682e = (ImageView) view.findViewById(R$id.edit_query);
        }
    }

    public y(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.f7671t = false;
        this.f7672u = 1;
        this.f7674w = -1;
        this.f7675x = -1;
        this.f7676y = -1;
        this.f7677z = -1;
        this.A = -1;
        this.B = -1;
        this.f7666o = searchView;
        this.f7667p = searchableInfo;
        this.f7670s = searchView.getSuggestionCommitIconResId();
        this.f7668q = context;
        this.f7669r = weakHashMap;
    }

    public static String k(Cursor cursor, String str) {
        return s(cursor, cursor.getColumnIndex(str));
    }

    public static String s(Cursor cursor, int i8) {
        if (i8 == -1) {
            return null;
        }
        try {
            return cursor.getString(i8);
        } catch (Exception e8) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e8);
            return null;
        }
    }

    @Override // s0.a
    public void a(View view, Context context, Cursor cursor) {
        a aVar = (a) view.getTag();
        int i8 = this.B;
        int i9 = i8 != -1 ? cursor.getInt(i8) : 0;
        if (aVar.f7678a != null) {
            v(aVar.f7678a, s(cursor, this.f7674w));
        }
        if (aVar.f7679b != null) {
            String strS = s(cursor, this.f7676y);
            CharSequence charSequenceH = strS != null ? h(strS) : s(cursor, this.f7675x);
            if (TextUtils.isEmpty(charSequenceH)) {
                TextView textView = aVar.f7678a;
                if (textView != null) {
                    textView.setSingleLine(false);
                    aVar.f7678a.setMaxLines(2);
                }
            } else {
                TextView textView2 = aVar.f7678a;
                if (textView2 != null) {
                    textView2.setSingleLine(true);
                    aVar.f7678a.setMaxLines(1);
                }
            }
            v(aVar.f7679b, charSequenceH);
        }
        ImageView imageView = aVar.f7680c;
        if (imageView != null) {
            u(imageView, p(cursor), 4);
        }
        ImageView imageView2 = aVar.f7681d;
        if (imageView2 != null) {
            u(imageView2, q(cursor), 8);
        }
        int i10 = this.f7672u;
        if (i10 != 2 && (i10 != 1 || (i9 & 1) == 0)) {
            aVar.f7682e.setVisibility(8);
            return;
        }
        aVar.f7682e.setVisibility(0);
        aVar.f7682e.setTag(aVar.f7678a.getText());
        aVar.f7682e.setOnClickListener(this);
    }

    @Override // s0.a, s0.b.a
    public void changeCursor(Cursor cursor) {
        if (this.f7671t) {
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
                this.f7674w = cursor.getColumnIndex("suggest_text_1");
                this.f7675x = cursor.getColumnIndex("suggest_text_2");
                this.f7676y = cursor.getColumnIndex("suggest_text_2_url");
                this.f7677z = cursor.getColumnIndex("suggest_icon_1");
                this.A = cursor.getColumnIndex("suggest_icon_2");
                this.B = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e8) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e8);
        }
    }

    @Override // s0.a, s0.b.a
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
        if (this.f7667p.shouldRewriteQueryFromData() && (strK2 = k(cursor, "suggest_intent_data")) != null) {
            return strK2;
        }
        if (!this.f7667p.shouldRewriteQueryFromText() || (strK = k(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return strK;
    }

    @Override // s0.c, s0.a
    public View d(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewD = super.d(context, cursor, viewGroup);
        viewD.setTag(new a(viewD));
        ((ImageView) viewD.findViewById(R$id.edit_query)).setImageResource(this.f7670s);
        return viewD;
    }

    public final Drawable g(String str) {
        Drawable.ConstantState constantState = this.f7669r.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    @Override // s0.a, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i8, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i8, view, viewGroup);
        } catch (RuntimeException e8) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e8);
            View viewC = c(this.f7872g, this.f7871f, viewGroup);
            if (viewC != null) {
                ((a) viewC.getTag()).f7678a.setText(e8.toString());
            }
            return viewC;
        }
    }

    @Override // s0.a, android.widget.Adapter
    public View getView(int i8, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i8, view, viewGroup);
        } catch (RuntimeException e8) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e8);
            View viewD = d(this.f7872g, this.f7871f, viewGroup);
            if (viewD != null) {
                ((a) viewD.getTag()).f7678a.setText(e8.toString());
            }
            return viewD;
        }
    }

    public final CharSequence h(CharSequence charSequence) {
        if (this.f7673v == null) {
            TypedValue typedValue = new TypedValue();
            this.f7872g.getTheme().resolveAttribute(R$attr.textColorSearchUrl, typedValue, true);
            this.f7673v = this.f7872g.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.f7673v, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return false;
    }

    public final Drawable i(ComponentName componentName) {
        PackageManager packageManager = this.f7872g.getPackageManager();
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
        } catch (PackageManager.NameNotFoundException e8) {
            Log.w("SuggestionsAdapter", e8.toString());
            return null;
        }
    }

    public final Drawable j(ComponentName componentName) {
        String strFlattenToShortString = componentName.flattenToShortString();
        if (!this.f7669r.containsKey(strFlattenToShortString)) {
            Drawable drawableI = i(componentName);
            this.f7669r.put(strFlattenToShortString, drawableI != null ? drawableI.getConstantState() : null);
            return drawableI;
        }
        Drawable.ConstantState constantState = this.f7669r.get(strFlattenToShortString);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable(this.f7668q.getResources());
    }

    public final Drawable l() {
        Drawable drawableJ = j(this.f7667p.getSearchActivity());
        return drawableJ != null ? drawableJ : this.f7872g.getPackageManager().getDefaultActivityIcon();
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
            InputStream inputStreamOpenInputStream = this.f7668q.getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            try {
                return Drawable.createFromStream(inputStreamOpenInputStream, null);
            } finally {
                try {
                    inputStreamOpenInputStream.close();
                } catch (IOException e8) {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e8);
                }
            }
        } catch (FileNotFoundException e9) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e9.getMessage());
            return null;
        }
        Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e9.getMessage());
        return null;
    }

    public Drawable n(Uri uri) throws FileNotFoundException {
        int identifier;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.f7872g.getPackageManager().getResourcesForApplication(authority);
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
            int i8 = Integer.parseInt(str);
            String str2 = "android.resource://" + this.f7668q.getPackageName() + "/" + i8;
            Drawable drawableG = g(str2);
            if (drawableG != null) {
                return drawableG;
            }
            Drawable drawableD = e0.a.d(this.f7668q, i8);
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
            this.f7666o.U((CharSequence) tag);
        }
    }

    public final Drawable p(Cursor cursor) {
        int i8 = this.f7677z;
        if (i8 == -1) {
            return null;
        }
        Drawable drawableO = o(cursor.getString(i8));
        return drawableO != null ? drawableO : l();
    }

    public final Drawable q(Cursor cursor) {
        int i8 = this.A;
        if (i8 == -1) {
            return null;
        }
        return o(cursor.getString(i8));
    }

    public Cursor r(SearchableInfo searchableInfo, String str, int i8) {
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
        if (i8 > 0) {
            builderFragment.appendQueryParameter("limit", String.valueOf(i8));
        }
        return this.f7872g.getContentResolver().query(builderFragment.build(), null, suggestSelection, strArr2, null);
    }

    @Override // s0.b.a
    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        String string = charSequence == null ? "" : charSequence.toString();
        if (this.f7666o.getVisibility() == 0 && this.f7666o.getWindowVisibility() == 0) {
            try {
                Cursor cursorR = r(this.f7667p, string, 50);
                if (cursorR != null) {
                    cursorR.getCount();
                    return cursorR;
                }
            } catch (RuntimeException e8) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e8);
            }
        }
        return null;
    }

    public void t(int i8) {
        this.f7672u = i8;
    }

    public final void u(ImageView imageView, Drawable drawable, int i8) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i8);
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
            this.f7669r.put(str, drawable.getConstantState());
        }
    }

    public final void x(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras == null || extras.getBoolean("in_progress")) {
        }
    }
}
