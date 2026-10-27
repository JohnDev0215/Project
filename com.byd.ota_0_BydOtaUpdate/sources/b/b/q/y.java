package b.b.q;

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
import androidx.appcompat.widget.SearchView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SuggestionsAdapter.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public class y extends b.g.a.c implements View.OnClickListener {
    public final SearchView K3;
    public final SearchableInfo L3;
    public final Context M3;
    public final WeakHashMap<String, Drawable.ConstantState> N3;
    public final int O3;
    public boolean P3;
    public int Q3;
    public ColorStateList R3;
    public int S3;
    public int T3;
    public int U3;
    public int V3;
    public int W3;
    public int X3;

    /* JADX INFO: compiled from: SuggestionsAdapter.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f1121a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f1122b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ImageView f1123c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ImageView f1124d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final ImageView f1125e;

        public a(View view) {
            this.f1121a = (TextView) view.findViewById(R.id.text1);
            this.f1122b = (TextView) view.findViewById(R.id.text2);
            this.f1123c = (ImageView) view.findViewById(R.id.icon1);
            this.f1124d = (ImageView) view.findViewById(R.id.icon2);
            this.f1125e = (ImageView) view.findViewById(b.b.f.s);
        }
    }

    public y(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.P3 = false;
        this.Q3 = 1;
        this.S3 = -1;
        this.T3 = -1;
        this.U3 = -1;
        this.V3 = -1;
        this.W3 = -1;
        this.X3 = -1;
        this.K3 = searchView;
        this.L3 = searchableInfo;
        this.O3 = searchView.getSuggestionCommitIconResId();
        this.M3 = context;
        this.N3 = weakHashMap;
    }

    public static String k(Cursor cursor, String str) {
        return s(cursor, cursor.getColumnIndex(str));
    }

    public static String s(Cursor cursor, int i2) {
        if (i2 == -1) {
            return null;
        }
        try {
            return cursor.getString(i2);
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e2);
            return null;
        }
    }

    @Override // b.g.a.a
    public void a(View view, Context context, Cursor cursor) {
        a aVar = (a) view.getTag();
        int i2 = this.X3;
        int i3 = i2 != -1 ? cursor.getInt(i2) : 0;
        if (aVar.f1121a != null) {
            v(aVar.f1121a, s(cursor, this.S3));
        }
        if (aVar.f1122b != null) {
            String strS = s(cursor, this.U3);
            CharSequence charSequenceH = strS != null ? h(strS) : s(cursor, this.T3);
            if (TextUtils.isEmpty(charSequenceH)) {
                TextView textView = aVar.f1121a;
                if (textView != null) {
                    textView.setSingleLine(false);
                    aVar.f1121a.setMaxLines(2);
                }
            } else {
                TextView textView2 = aVar.f1121a;
                if (textView2 != null) {
                    textView2.setSingleLine(true);
                    aVar.f1121a.setMaxLines(1);
                }
            }
            v(aVar.f1122b, charSequenceH);
        }
        ImageView imageView = aVar.f1123c;
        if (imageView != null) {
            u(imageView, p(cursor), 4);
        }
        ImageView imageView2 = aVar.f1124d;
        if (imageView2 != null) {
            u(imageView2, q(cursor), 8);
        }
        int i4 = this.Q3;
        if (i4 != 2 && (i4 != 1 || (i3 & 1) == 0)) {
            aVar.f1125e.setVisibility(8);
            return;
        }
        aVar.f1125e.setVisibility(0);
        aVar.f1125e.setTag(aVar.f1121a.getText());
        aVar.f1125e.setOnClickListener(this);
    }

    @Override // b.g.a.a, b.g.a.b.a
    public void changeCursor(Cursor cursor) {
        if (this.P3) {
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
                this.S3 = cursor.getColumnIndex("suggest_text_1");
                this.T3 = cursor.getColumnIndex("suggest_text_2");
                this.U3 = cursor.getColumnIndex("suggest_text_2_url");
                this.V3 = cursor.getColumnIndex("suggest_icon_1");
                this.W3 = cursor.getColumnIndex("suggest_icon_2");
                this.X3 = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e2);
        }
    }

    @Override // b.g.a.a, b.g.a.b.a
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
        if (this.L3.shouldRewriteQueryFromData() && (strK2 = k(cursor, "suggest_intent_data")) != null) {
            return strK2;
        }
        if (!this.L3.shouldRewriteQueryFromText() || (strK = k(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return strK;
    }

    @Override // b.g.a.c, b.g.a.a
    public View d(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewD = super.d(context, cursor, viewGroup);
        viewD.setTag(new a(viewD));
        ((ImageView) viewD.findViewById(b.b.f.s)).setImageResource(this.O3);
        return viewD;
    }

    public final Drawable g(String str) {
        Drawable.ConstantState constantState = this.N3.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    @Override // b.g.a.a, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i2, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View viewC = c(this.x, this.s, viewGroup);
            if (viewC != null) {
                ((a) viewC.getTag()).f1121a.setText(e2.toString());
            }
            return viewC;
        }
    }

    @Override // b.g.a.a, android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i2, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View viewD = d(this.x, this.s, viewGroup);
            if (viewD != null) {
                ((a) viewD.getTag()).f1121a.setText(e2.toString());
            }
            return viewD;
        }
    }

    public final CharSequence h(CharSequence charSequence) {
        if (this.R3 == null) {
            TypedValue typedValue = new TypedValue();
            this.x.getTheme().resolveAttribute(b.b.a.M, typedValue, true);
            this.R3 = this.x.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.R3, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return false;
    }

    public final Drawable i(ComponentName componentName) {
        PackageManager packageManager = this.x.getPackageManager();
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
        } catch (PackageManager.NameNotFoundException e2) {
            Log.w("SuggestionsAdapter", e2.toString());
            return null;
        }
    }

    public final Drawable j(ComponentName componentName) {
        String strFlattenToShortString = componentName.flattenToShortString();
        if (!this.N3.containsKey(strFlattenToShortString)) {
            Drawable drawableI = i(componentName);
            this.N3.put(strFlattenToShortString, drawableI != null ? drawableI.getConstantState() : null);
            return drawableI;
        }
        Drawable.ConstantState constantState = this.N3.get(strFlattenToShortString);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable(this.M3.getResources());
    }

    public final Drawable l() {
        Drawable drawableJ = j(this.L3.getSearchActivity());
        return drawableJ != null ? drawableJ : this.x.getPackageManager().getDefaultActivityIcon();
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
            InputStream inputStreamOpenInputStream = this.M3.getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            try {
                return Drawable.createFromStream(inputStreamOpenInputStream, null);
            } finally {
                try {
                    inputStreamOpenInputStream.close();
                } catch (IOException e2) {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e2);
                }
            }
        } catch (FileNotFoundException e3) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e3.getMessage());
            return null;
        }
        Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e3.getMessage());
        return null;
    }

    public Drawable n(Uri uri) throws FileNotFoundException {
        int identifier;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.x.getPackageManager().getResourcesForApplication(authority);
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
            int i2 = Integer.parseInt(str);
            String str2 = "android.resource://" + this.M3.getPackageName() + "/" + i2;
            Drawable drawableG = g(str2);
            if (drawableG != null) {
                return drawableG;
            }
            Drawable drawableC = b.f.f.a.c(this.M3, i2);
            w(str2, drawableC);
            return drawableC;
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
            this.K3.S((CharSequence) tag);
        }
    }

    public final Drawable p(Cursor cursor) {
        int i2 = this.V3;
        if (i2 == -1) {
            return null;
        }
        Drawable drawableO = o(cursor.getString(i2));
        return drawableO != null ? drawableO : l();
    }

    public final Drawable q(Cursor cursor) {
        int i2 = this.W3;
        if (i2 == -1) {
            return null;
        }
        return o(cursor.getString(i2));
    }

    public Cursor r(SearchableInfo searchableInfo, String str, int i2) {
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
        if (i2 > 0) {
            builderFragment.appendQueryParameter("limit", String.valueOf(i2));
        }
        return this.x.getContentResolver().query(builderFragment.build(), null, suggestSelection, strArr2, null);
    }

    @Override // b.g.a.b.a
    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        String string = charSequence == null ? "" : charSequence.toString();
        if (this.K3.getVisibility() == 0 && this.K3.getWindowVisibility() == 0) {
            try {
                Cursor cursorR = r(this.L3, string, 50);
                if (cursorR != null) {
                    cursorR.getCount();
                    return cursorR;
                }
            } catch (RuntimeException e2) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e2);
            }
        }
        return null;
    }

    public void t(int i2) {
        this.Q3 = i2;
    }

    public final void u(ImageView imageView, Drawable drawable, int i2) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i2);
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
            this.N3.put(str, drawable.getConstantState());
        }
    }

    public final void x(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras == null || extras.getBoolean("in_progress")) {
        }
    }
}
