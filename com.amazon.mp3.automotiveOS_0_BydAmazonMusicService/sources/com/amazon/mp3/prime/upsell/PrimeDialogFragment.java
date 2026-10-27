package com.amazon.mp3.prime.upsell;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;
import com.amazon.music.events.UserInteractionAppEvent;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/* JADX INFO: loaded from: classes4.dex */
public abstract class PrimeDialogFragment extends DialogFragment {
    private static final String TAG = PrimeDialogFragment.class.getSimpleName();
    private PrimeDialogListener mDialogListener;
    protected View mRootView;
    private boolean mShouldDismiss = true;

    public interface PrimeDialogListener {
        void onAccept();

        void onDismiss();
    }

    protected abstract String getAcceptButton();

    protected abstract String getDismissButton();

    protected abstract String getMessage();

    protected abstract String getTitle();

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        FragmentActivity activity = getActivity();
        View viewInflate = View.inflate(getActivity(), R.layout.dialog_prime_feature, null);
        this.mRootView = viewInflate;
        ((TextView) viewInflate.findViewById(R.id.dialog_prime_feature_title)).setText(getTitle());
        ((TextView) this.mRootView.findViewById(R.id.dialog_prime_feature_message)).setText(getMessage());
        Picasso.get().load("https://m.media-amazon.com/images/G/01/digital/music/DMUX/PrimeAssets/FireTablet/Dialog/prime_feature_dialog_background_image.png").into(initializePrimeDialogBackgroundImage((ImageView) this.mRootView.findViewById(R.id.dialog_image_background)));
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(this.mRootView);
        builder.setCancelable(false);
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.amazon.mp3.prime.upsell.PrimeDialogFragment.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() != 4) {
                    return false;
                }
                PrimeDialogFragment.this.mDialogListener.onDismiss();
                return true;
            }
        });
        Button button = (Button) this.mRootView.findViewById(R.id.dialog_prime_feature_positive_btn);
        button.setText(getAcceptButton());
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.prime.upsell.PrimeDialogFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UserInteractionAppEvent.builder("PrimeBrowseUpsellFTU").publish();
                PrimeDialogFragment.this.mDialogListener.onAccept();
                PrimeDialogFragment.this.mShouldDismiss = false;
            }
        });
        Button button2 = (Button) this.mRootView.findViewById(R.id.dialog_prime_feature_negative_btn);
        button2.setText(getDismissButton());
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.prime.upsell.PrimeDialogFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UserInteractionAppEvent.builder("noThanksPrimeBrowseUpsellFTU").publish();
                PrimeDialogFragment.this.mDialogListener.onDismiss();
            }
        });
        return builder.create();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.mShouldDismiss) {
            this.mDialogListener.onDismiss();
        }
    }

    private Target initializePrimeDialogBackgroundImage(final ImageView imageView) {
        return new Target() { // from class: com.amazon.mp3.prime.upsell.PrimeDialogFragment.4
            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(Drawable drawable) {
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(final Bitmap bitmap, final Picasso.LoadedFrom loadedFrom) {
                imageView.setVisibility(0);
                imageView.setImageBitmap(bitmap);
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapFailed(Exception ex, Drawable drawable) {
                Log.error(PrimeDialogFragment.TAG, "Failed to load prime background image.", ex);
                imageView.setVisibility(8);
            }
        };
    }

    protected void setDialogListener(PrimeDialogListener dialogListener) {
        this.mDialogListener = dialogListener;
    }
}
