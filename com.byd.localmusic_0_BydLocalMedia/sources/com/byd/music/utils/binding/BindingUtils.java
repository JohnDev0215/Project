package com.byd.music.utils.binding;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.motion.widget.Key;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.byd.music.R;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.beans.RadioEpgDBBean;
import com.byd.music.beans.SearchTagBean;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.utils.ImageUtil;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.MusicBgUtil;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.ReflectImageView;
import com.byd.music.view.SearchEditText;
import com.byd.music.view.homeMusic.lrc.LrcView2;
import com.byd.music.view.musicPlayingSlideView.MusicPlayingSlideView;
import com.byd.music.view.musicPlayingSlideView.PlayingSlideAnimView;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import io.reactivex.functions.Consumer;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public class BindingUtils {
    public static final int CLICK_INTERVAL = 500;
    private static ObjectAnimator objectAnimator = new ObjectAnimator();
    private static float animatedFraction = 0.0f;

    public static <T> void onClickCommand(final View view, final BindingCommand bindingCommand, boolean z, final T t, final boolean z2) {
        if (z) {
            RxView.clicks(view).subscribe(new Consumer() { // from class: com.byd.music.utils.binding.-$$Lambda$BindingUtils$BRp4c07i23pBR7T3rVC8TGZlIHc
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) throws Exception {
                    BindingUtils.lambda$onClickCommand$0(bindingCommand, t, z2, view, obj);
                }
            });
        } else {
            RxView.clicks(view).throttleFirst(500L, TimeUnit.MILLISECONDS).subscribe(new Consumer() { // from class: com.byd.music.utils.binding.-$$Lambda$BindingUtils$eTnTSf3xdM7KiypcMhiEtXygCkM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) throws Exception {
                    BindingUtils.lambda$onClickCommand$1(bindingCommand, t, z2, view, obj);
                }
            });
        }
    }

    static /* synthetic */ void lambda$onClickCommand$0(BindingCommand bindingCommand, Object obj, boolean z, View view, Object obj2) throws Exception {
        if (bindingCommand != null) {
            if (obj == null) {
                bindingCommand.execute();
            } else if (z) {
                bindingCommand.execute(view, obj);
            } else {
                bindingCommand.execute(obj);
            }
        }
    }

    static /* synthetic */ void lambda$onClickCommand$1(BindingCommand bindingCommand, Object obj, boolean z, View view, Object obj2) throws Exception {
        if (bindingCommand != null) {
            if (obj == null) {
                bindingCommand.execute();
            } else if (z) {
                bindingCommand.execute(view, obj);
            } else {
                bindingCommand.execute(obj);
            }
        }
    }

    public static <T> void onLongClickCommand(View view, final BindingCommand bindingCommand, final T t) {
        RxView.longClicks(view).subscribe(new Consumer() { // from class: com.byd.music.utils.binding.-$$Lambda$BindingUtils$nugJaVx2L24nNKQ2yWqkdYyAr_M
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                BindingUtils.lambda$onLongClickCommand$2(bindingCommand, t, obj);
            }
        });
    }

    static /* synthetic */ void lambda$onLongClickCommand$2(BindingCommand bindingCommand, Object obj, Object obj2) throws Exception {
        if (bindingCommand != null) {
            if (obj != null) {
                bindingCommand.execute(obj);
            } else {
                bindingCommand.execute();
            }
        }
    }

    public static void onSwitchChangeCommand(SwitchCompat switchCompat, final BindingCommand<Boolean> bindingCommand) {
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.byd.music.utils.binding.-$$Lambda$BindingUtils$j3lmf2oVqIE0sbcmdguTO0TxyO0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                bindingCommand.execute(Boolean.valueOf(z));
            }
        });
    }

    public static void setBgColor(View view, int i) {
        view.setBackgroundResource(i);
    }

    public static void setTextColor(TextView textView, int i, int i2) {
        if (i != 0) {
            textView.setTextColor(textView.getResources().getColor(i));
        }
        if (i2 != 0) {
            textView.setHintTextColor(textView.getResources().getColor(i2));
        }
    }

    public static void setFocusable(View view, boolean z) {
        if (z) {
            view.setFocusable(z);
            view.setFocusableInTouchMode(z);
            view.requestFocus();
        }
    }

    public static void setSelected(View view, boolean z) {
        view.setSelected(z);
    }

    public static void setVisiable(View view, boolean z, boolean z2, int i, int i2) {
        if (z && view.getVisibility() != 0) {
            view.setVisibility(0);
            if (i != 0) {
                view.setAnimation(AnimationUtils.loadAnimation(view.getContext(), i));
            }
        }
        if (z || view.getVisibility() != 0) {
            return;
        }
        if (z2) {
            view.setVisibility(4);
        } else {
            view.setVisibility(8);
        }
        if (i2 != 0) {
            view.setAnimation(AnimationUtils.loadAnimation(view.getContext(), i2));
        }
    }

    public static void setMusicSlidePlaying(MusicPlayingSlideView musicPlayingSlideView, boolean z) {
        LogUtil.e("Alvin", "setMusicSlidePlaying: " + z + " view=" + musicPlayingSlideView.getId());
        if (z) {
            musicPlayingSlideView.applyAnimation();
            musicPlayingSlideView.start();
        } else {
            musicPlayingSlideView.cancel();
        }
    }

    public static void setMusicSlidePlaying(PlayingSlideAnimView playingSlideAnimView, boolean z, boolean z2) {
        MusicBgUtil.traverseViewGroup(playingSlideAnimView, ColorUtils.getColor(R.color.color_text_selected_1));
        if (!z2) {
            playingSlideAnimView.cancel();
        } else if (z) {
            playingSlideAnimView.start();
        } else {
            playingSlideAnimView.pause();
        }
    }

    public static void setInvisible(View view, boolean z) {
        view.setVisibility(z ? 4 : 0);
    }

    public static void setImageRes(ImageView imageView, int i) {
        imageView.setImageResource(i);
    }

    public static void setImageBitmap(ImageView imageView, LocalMusicBean localMusicBean) {
        if (localMusicBean != null) {
            Glide.with(Utils.getApp().getApplicationContext()).load(LocalMusicUtils.getAlbumUri(localMusicBean.getAlbumId())).diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder(Utils.getApp().getDrawable(R.drawable.album_default_248)).override(140).dontAnimate().error(Utils.getApp().getDrawable(R.drawable.album_default_248)).into(imageView);
        } else {
            imageView.setImageDrawable(Utils.getApp().getDrawable(R.drawable.album_default_248));
        }
    }

    public static void setImageDrawable(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    public static void setImageByUrl(ImageView imageView, String str) {
        Glide.with(Utils.getApp()).load(str).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(imageView.getDrawable()).dontAnimate().error(ContextCompat.getDrawable(Utils.getApp(), R.drawable.album_default_248)).into(imageView);
    }

    public static void setImageBitmapFromUrl(ImageView imageView, String str) {
        Bitmap albumPictureFromPath;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        LogUtil.d("setImageBitmapFromUrl: ");
        if (str == null) {
            imageView.setImageDrawable(Utils.getApp().getDrawable(R.drawable.album_default_248));
            LogUtil.d("setImageBitmapFromUrl: X " + imageView.getX() + " Y " + imageView.getY());
            return;
        }
        if (!str.isEmpty() && (albumPictureFromPath = LocalMusicUtils.getAlbumPictureFromPath(imageView.getContext(), str)) != null) {
            imageView.setImageBitmap(albumPictureFromPath);
        } else {
            imageView.setImageDrawable(Utils.getApp().getDrawable(R.drawable.album_default_no));
        }
        imageView.setLayoutParams(layoutParams);
        LogUtil.d("setImageBitmapFromUrl: X " + imageView.getX() + " Y " + imageView.getY());
    }

    public static void setImageBitmapFromId(ImageView imageView, long j) {
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (j != 0) {
            Glide.with(Utils.getApp().getApplicationContext()).load(LocalMusicUtils.getAlbumUri(j)).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(((int) ScreenUtils.getScreenDensity()) * 140, ((int) ScreenUtils.getScreenDensity()) * 140).placeholder(Utils.getApp().getDrawable(R.drawable.album_default_248)).dontAnimate().error(Utils.getApp().getDrawable(R.drawable.album_default_248)).into(imageView);
        } else {
            imageView.setImageDrawable(Utils.getApp().getDrawable(R.drawable.album_default_no));
        }
        imageView.setLayoutParams(layoutParams);
        LogUtil.d("setImageBitmapFromUrl: X " + imageView.getX() + " Y " + imageView.getY());
    }

    public static void reflectImageUrl(final ReflectImageView reflectImageView, String str) {
        if (TextUtils.isEmpty(str)) {
            reflectImageView.setImageBitmap(ImageUtil.createReflectionBitmapForSingle(ImageUtils.drawable2Bitmap(reflectImageView.getContext().getResources().getDrawable(R.drawable.album_default_248)), (int) reflectImageView.getContext().getResources().getDimension(R.dimen.dp_248), (int) reflectImageView.getContext().getResources().getDimension(R.dimen.dp_248)));
        } else {
            Glide.with(Utils.getApp()).asBitmap().load(str).into(new SimpleTarget<Bitmap>() { // from class: com.byd.music.utils.binding.BindingUtils.1
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                    reflectImageView.startReflect(bitmap.copy(bitmap.getConfig(), true));
                }
            });
        }
    }

    private static Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        System.out.println("Drawable转Bitmap");
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static void setItemImageByUrl(ImageView imageView, String str) {
        Glide.with(Utils.getApp()).load(str).override(300).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(imageView.getDrawable()).dontAnimate().error(ContextCompat.getDrawable(Utils.getApp(), R.drawable.album_default_248)).into(imageView);
    }

    public static void setImageByUri(ImageView imageView, String str) {
        LogUtil.d("loadImgByUri", "uriStr = " + str);
        if (str == null) {
            return;
        }
        Glide.with(Utils.getApp()).load(Uri.parse(str)).override(500).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(imageView.getDrawable()).dontAnimate().error(ContextCompat.getDrawable(Utils.getApp(), R.drawable.album_default_248)).fallback(ContextCompat.getDrawable(Utils.getApp(), R.drawable.album_default_248)).into(imageView);
    }

    public static void setTextViewCollect(TextView textView, boolean z) {
        if (z) {
            textView.setText("已收藏");
            Drawable drawable = ContextCompat.getDrawable(Utils.getApp(), R.drawable.icon_dark_media_collect_selected_32);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            textView.setCompoundDrawables(drawable, null, null, null);
            return;
        }
        textView.setText("收藏");
        Drawable drawable2 = ContextCompat.getDrawable(Utils.getApp(), R.drawable.icon_dark_media_collect_32);
        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
        textView.setCompoundDrawables(drawable2, null, null, null);
    }

    public static void setSelectItemTextView(TextView textView, int i) {
        SpannableString spannableStringHighlight;
        if (i <= 0) {
            textView.setTextColor(textView.getResources().getColor(R.color.text_color_tab));
            textView.setText(textView.getResources().getString(R.string.batch_operation));
            return;
        }
        if (i <= 99) {
            textView.setText(String.format(textView.getResources().getString(R.string.local_selected_del_song), Integer.valueOf(i)));
            spannableStringHighlight = highlight(textView.getContext(), String.format(textView.getResources().getString(R.string.local_selected_del_song), Integer.valueOf(i)), String.valueOf(i), textView.getResources().getColor(R.color.text_color_selected), 0, 0);
        } else {
            textView.setText(R.string.local_selected_del_song_plus);
            spannableStringHighlight = highlight(textView.getContext(), textView.getResources().getString(R.string.local_selected_del_song_plus), "99", textView.getResources().getColor(R.color.text_color_selected), 0, 1);
        }
        textView.setText(spannableStringHighlight);
    }

    public static SpannableString highlight(Context context, String str, String str2, int i, int i2, int i3) {
        SpannableString spannableString = new SpannableString(str);
        Matcher matcher = Pattern.compile(str2).matcher(str);
        while (matcher.find()) {
            spannableString.setSpan(new ForegroundColorSpan(i), matcher.start() - i2, matcher.end() + i3, 33);
        }
        return spannableString;
    }

    public static void setLyricTime(LrcView2 lrcView2, int i) {
        lrcView2.updateTime(i);
    }

    public static void rotateView(View view, boolean z) {
        if (z) {
            animatedFraction = objectAnimator.getAnimatedFraction();
            objectAnimator.pause();
            view.clearAnimation();
            return;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, Key.ROTATION, 0.0f, 360.0f);
        objectAnimator = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(20000L);
        objectAnimator.setRepeatMode(1);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setCurrentFraction(animatedFraction);
        objectAnimator.start();
    }

    public static void shakeView(View view, boolean z) {
        if (z) {
            RotateAnimation rotateAnimation = new RotateAnimation(-3.0f, 3.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setDuration(60L);
            rotateAnimation.setRepeatMode(2);
            rotateAnimation.setRepeatCount(6);
            view.startAnimation(rotateAnimation);
        }
    }

    public static void setImageRes(View view, int i) {
        view.setBackground(view.getResources().getDrawable(i));
    }

    public static void setViewBackground(View view, float f, boolean z) {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        Drawable drawable4;
        Drawable drawable5;
        if (f == 0.75f) {
            if (z) {
                drawable5 = ImageUtil.getTintDrawable(view.getContext(), R.drawable.icon_dark_media_playmultiplier_075_48, R.color.text_color_selected);
            } else {
                drawable5 = view.getResources().getDrawable(R.drawable.icon_dark_media_playmultiplier_075_48);
            }
            view.setBackground(drawable5);
            return;
        }
        if (f == 1.25f) {
            if (z) {
                drawable4 = ImageUtil.getTintDrawable(view.getContext(), R.drawable.icon_dark_media_playmultiplier_125_48, R.color.text_color_selected);
            } else {
                drawable4 = view.getResources().getDrawable(R.drawable.icon_dark_media_playmultiplier_125_48);
            }
            view.setBackground(drawable4);
            return;
        }
        if (f == 1.5f) {
            if (z) {
                drawable3 = ImageUtil.getTintDrawable(view.getContext(), R.drawable.icon_dark_media_playmultiplier_15_48, R.color.text_color_selected);
            } else {
                drawable3 = view.getResources().getDrawable(R.drawable.icon_dark_media_playmultiplier_15_48);
            }
            view.setBackground(drawable3);
            return;
        }
        if (f == 2.0f) {
            if (z) {
                drawable2 = ImageUtil.getTintDrawable(view.getContext(), R.drawable.icon_dark_media_playmultiplier_20_48, R.color.text_color_selected);
            } else {
                drawable2 = view.getResources().getDrawable(R.drawable.icon_dark_media_playmultiplier_20_48);
            }
            view.setBackground(drawable2);
            return;
        }
        if (z) {
            drawable = ImageUtil.getTintDrawable(view.getContext(), R.drawable.icon_dark_media_playmultiplier_1_48, R.color.text_color_selected);
        } else {
            drawable = view.getResources().getDrawable(R.drawable.icon_dark_media_playmultiplier_1_48);
        }
        view.setBackground(drawable);
    }

    public static void setSeekBarProgress(SeekBar seekBar, int i, boolean z) {
        LogUtil.d("seekbar: dragging = " + z + " and  progress = " + i);
        if (z) {
            return;
        }
        seekBar.setProgress(i);
    }

    public static void setbgImg(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static void setBg(View view, int i) {
        view.setBackgroundResource(i);
    }

    public static void onTextChanged(TextView textView, final BindingCommand<String> bindingCommand) {
        RxTextView.textChanges(textView).subscribe(new Consumer() { // from class: com.byd.music.utils.binding.-$$Lambda$BindingUtils$d_jivpgDyiaOpVFWCjHNNrC8VZ8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                BindingUtils.lambda$onTextChanged$4(bindingCommand, (CharSequence) obj);
            }
        });
    }

    static /* synthetic */ void lambda$onTextChanged$4(BindingCommand bindingCommand, CharSequence charSequence) throws Exception {
        if (bindingCommand != null) {
            bindingCommand.execute(charSequence.toString());
        }
    }

    public static void onFocusChanged(View view, final BindingCommand<Boolean> bindingCommand) {
        RxView.focusChanges(view).subscribe(new Consumer() { // from class: com.byd.music.utils.binding.-$$Lambda$BindingUtils$5cxssAggKgIQdJJb92yczqTNbyw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                BindingUtils.lambda$onFocusChanged$5(bindingCommand, (Boolean) obj);
            }
        });
    }

    static /* synthetic */ void lambda$onFocusChanged$5(BindingCommand bindingCommand, Boolean bool) throws Exception {
        if (bindingCommand != null) {
            bindingCommand.execute(bool);
        }
    }

    public static void setDabAlbumBitmap(ImageView imageView, Bitmap bitmap, int i, String str) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else if (i != 0) {
            imageView.setImageDrawable(imageView.getContext().getResources().getDrawable(i));
        }
    }

    public static void setViewAlpha(View view, float f) {
        view.setAlpha(f);
    }

    public static void setClickable(View view, boolean z) {
        view.setClickable(z);
    }

    public static void setSearchTipIcon(ImageView imageView, int i) {
        imageView.setImageResource(i == 0 ? R.drawable.icon_dark_media_search_list_shijian_48 : R.drawable.icon_dark_public_search_48);
    }

    public static void setKeyWordColor(TextView textView, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!TextUtils.isEmpty(str2) && str.toLowerCase().contains(str2.toLowerCase())) {
            int iIndexOf = str.toLowerCase().indexOf(str2.toLowerCase());
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new ForegroundColorSpan(ColorUtils.getColor(R.color.text_color_selected)), iIndexOf, str2.length() + iIndexOf, 17);
            textView.setText(spannableString);
            return;
        }
        if (LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(str)) {
            str = textView.getContext().getString(R.string.unknown_artist);
        }
        textView.setText(str);
    }

    public static void setTvContent(TextView textView, String str) {
        if (TextUtils.isEmpty(str)) {
            textView.setText(textView.getContext().getString(R.string.local_player_null_artist));
            return;
        }
        if (LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(str)) {
            str = textView.getContext().getString(R.string.unknown_artist);
        }
        textView.setText(str);
    }

    public static void floatViewIsRotate(ImageView imageView, boolean z) {
        if (z) {
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setDuration(8000L);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setRepeatMode(1);
            rotateAnimation.setRepeatCount(-1);
            if (imageView != null) {
                imageView.startAnimation(rotateAnimation);
                return;
            }
            return;
        }
        imageView.clearAnimation();
    }

    public static void searchRightDrawable(ImageView imageView, SearchTagBean searchTagBean) {
        if ("历史搜索".equals(searchTagBean.getKeyword())) {
            imageView.setImageResource(R.drawable.icon_media_search_delete_32);
        } else {
            imageView.setImageResource(R.drawable.icon_dark_media_search_shuaxin_32);
        }
    }

    public static void shouldShowDuration(TextView textView, String str) {
        if (TextUtils.isEmpty(str) || "00:00".equals(str)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    public static void cateItemBottomMarginVisibility(View view, boolean z, boolean z2, boolean z3) {
        if (view != null) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
            if (z) {
                view.setVisibility(0);
                if (z3) {
                    layoutParams.height = SizeUtils.dp2px(140.0f);
                } else if (z2) {
                    layoutParams.height = SizeUtils.dp2px(334.0f);
                } else {
                    layoutParams.height = SizeUtils.dp2px(120.0f);
                }
            } else {
                view.setVisibility(8);
                layoutParams.height = 0;
            }
            view.setLayoutParams(layoutParams);
        }
    }

    public static void searchItemBottomMarginVisibility(View view, boolean z, boolean z2, boolean z3) {
        if (view != null) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
            if (z) {
                view.setVisibility(0);
                if (z3) {
                    layoutParams.height = SizeUtils.dp2px(140.0f);
                } else if (z2) {
                    layoutParams.height = SizeUtils.dp2px(334.0f);
                } else {
                    layoutParams.height = SizeUtils.dp2px(130.0f);
                }
            } else {
                view.setVisibility(8);
                layoutParams.height = 0;
            }
            view.setLayoutParams(layoutParams);
        }
    }

    public static void songListRightVisibility(View view, boolean z, boolean z2) {
        view.setVisibility((z2 || z) ? 8 : 0);
    }

    public static void setPlayState(ImageButton imageButton, boolean z) {
        imageButton.setImageResource(z ? R.drawable.icon_dark_card_suspend_56 : R.drawable.icon_dark_card_play_56);
    }

    public static void viewVisibility(View view, boolean z) {
        view.setVisibility(z ? 0 : 8);
    }

    public static void layoutVisibility(ViewGroup viewGroup, boolean z) {
        viewGroup.setVisibility(z ? 0 : 8);
    }

    public static void widthInMultiMode(View view, boolean z) {
        if (view == null || !(view instanceof SearchEditText)) {
            return;
        }
        LogUtil.e("MultiModeCheck", "widthInMultiMode: " + z);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = SizeUtils.dp2px(z ? 520.0f : 440.0f);
        view.setLayoutParams(layoutParams);
    }

    public static void setViewClickable(View view, boolean z) {
        if (z) {
            view.setAlpha(Float.parseFloat("1"));
        } else {
            view.setAlpha(Float.parseFloat("0.5"));
        }
        view.setClickable(z);
        view.setSoundEffectsEnabled(z);
    }

    public static void bindTextStyle(TextView textView, boolean z) {
        textView.setTypeface(Typeface.defaultFromStyle(z ? 1 : 0));
    }

    public static void noConnectText(TextView textView, boolean z, boolean z2) {
        LogUtil.e("noConnectText, isA2DPConnected = " + z + "; isHFPConnected = " + z2);
        if (z2 && !z) {
            textView.setText(R.string.go_to_connect_a2dp);
        } else {
            if (z2 || z) {
                return;
            }
            textView.setText(R.string.go_to_connect);
        }
    }

    public static void setImageViewCollectAnimation(CollectLottieView collectLottieView, final ObservableBoolean observableBoolean) {
        collectLottieView.startAnimation(observableBoolean.get(), new CollectLottieView.MYAnimatorListener() { // from class: com.byd.music.utils.binding.BindingUtils.2
            @Override // com.byd.music.view.CollectLottieView.MYAnimatorListener
            public void onAnimationStarted() {
                observableBoolean.set(false);
            }
        });
    }

    public static void setImageViewCollectAnimation(CollectLottieView collectLottieView, boolean z) {
        collectLottieView.startAnimation(z);
    }

    public static void bluetoothTagVisibility(View view, boolean z, boolean z2, boolean z3, boolean z4) {
        if (z && z2 && !z3 && !z4) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    public static void setDabSubscribeVisible(View view, RadioDBBean radioDBBean, ObservableArrayList<RadioEpgDBBean> observableArrayList) {
        Iterator<RadioEpgDBBean> it = observableArrayList.iterator();
        while (it.hasNext()) {
            if (it.next().getDabSid() == radioDBBean.getDAB_SID()) {
                view.setVisibility(0);
                return;
            }
        }
        view.setVisibility(4);
    }

    public static void setConstraintCustom(View view, int i, int i2, int i3, int i4) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        if (i <= 0) {
            layoutParams.leftToLeft = -1;
        } else if (i == 1) {
            layoutParams.leftToLeft = 0;
        } else {
            layoutParams.leftToLeft = i;
        }
        if (i2 <= 0) {
            layoutParams.leftToRight = -1;
        } else if (i2 == 1) {
            layoutParams.leftToRight = 0;
        } else {
            layoutParams.leftToRight = i2;
        }
        if (i3 <= 0) {
            layoutParams.leftToLeft = -1;
        } else if (i3 == 1) {
            layoutParams.rightToLeft = 0;
        } else {
            layoutParams.rightToLeft = i3;
        }
        if (i4 <= 0) {
            layoutParams.rightToRight = -1;
        } else if (i4 == 1) {
            layoutParams.rightToRight = 0;
        } else {
            layoutParams.rightToRight = i4;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void setConstraintCustomRTL(View view, int i, int i2, int i3, int i4) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        if (i <= 0) {
            layoutParams.startToStart = -1;
        } else if (i == 1) {
            layoutParams.startToStart = 0;
        } else {
            layoutParams.startToStart = i;
        }
        if (i2 <= 0) {
            layoutParams.startToEnd = -1;
        } else if (i2 == 1) {
            layoutParams.startToEnd = 0;
        } else {
            layoutParams.startToEnd = i2;
        }
        if (i3 <= 0) {
            layoutParams.startToStart = -1;
        } else if (i3 == 1) {
            layoutParams.endToStart = 0;
        } else {
            layoutParams.endToStart = i3;
        }
        if (i4 <= 0) {
            layoutParams.endToEnd = -1;
        } else if (i4 == 1) {
            layoutParams.endToEnd = 0;
        } else {
            layoutParams.endToEnd = i4;
        }
        layoutParams.horizontalChainStyle = 1;
        view.setLayoutParams(layoutParams);
    }

    public static void setConstraintCustomAndChain(View view, int i, int i2, int i3, int i4, int i5) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        if (i <= 0) {
            layoutParams.leftToLeft = -1;
        } else if (i == 1) {
            layoutParams.leftToLeft = 0;
        } else {
            layoutParams.leftToLeft = i;
        }
        if (i2 <= 0) {
            layoutParams.leftToRight = -1;
        } else if (i2 == 1) {
            layoutParams.leftToRight = 0;
        } else {
            layoutParams.leftToRight = i2;
        }
        if (i3 <= 0) {
            layoutParams.leftToLeft = -1;
        } else if (i3 == 1) {
            layoutParams.rightToLeft = 0;
        } else {
            layoutParams.rightToLeft = i3;
        }
        if (i4 <= 0) {
            layoutParams.rightToRight = -1;
        } else if (i4 == 1) {
            layoutParams.rightToRight = 0;
        } else {
            layoutParams.rightToRight = i4;
        }
        layoutParams.horizontalChainStyle = i5;
        view.setLayoutParams(layoutParams);
    }

    public static void setConstraintCustom(View view, boolean z) {
        if (z) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
            layoutParams.leftMargin = SizeUtils.dp2px(-36.0f);
            view.setLayoutParams(layoutParams);
        }
    }

    public static void setLayoutMarginCustom(View view, int i, int i2) {
        LogUtil.d("setLayoutMarginCustom", "setLayoutMarginCustom mrgStart = " + i + " mrgEnd = " + i2);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        layoutParams.setMarginStart(SizeUtils.dp2px((float) i));
        layoutParams.setMarginEnd(SizeUtils.dp2px((float) i2));
        view.setLayoutParams(layoutParams);
    }

    public static <T> void setRecyclerSelectedPosition(RecyclerView recyclerView, T t, int i) {
        LogUtil.d("selectedPosition", "setRecyclerSelectedPosition, position = " + i);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null || i < 0 || i >= layoutManager.getItemCount()) {
            LogUtil.d("selectedPosition", "can't scroll!");
        } else if (recyclerView.getScrollState() == 0) {
            LogUtil.d("selectedPosition", "start scroll");
            recyclerView.smoothScrollToPosition(i);
        }
    }

    public static void onlySetTextSize(TextView textView, int i) {
        textView.setTextSize(2, i);
    }
}
