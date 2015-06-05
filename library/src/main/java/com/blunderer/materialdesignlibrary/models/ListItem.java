package com.blunderer.materialdesignlibrary.models;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.blunderer.materialdesignlibrary.listeners.OnAccountOptionClickListener;

public abstract class ListItem {

    protected String mTitle;
    private Drawable mIcon;
    protected boolean mUseTitle = false;
    private boolean mUseIconResource = false;
    private boolean mShowAccountPicture = true; // Default is true;
    private OnAccountOptionClickListener accountOptionClickListener = null;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(Context context, int titleResource) {
        mUseTitle = true;
        mTitle = context.getString(titleResource);
    }

    public void setTitle(String title) {
        mUseTitle = true;
        mTitle = title;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Context context, int iconResource) {
        mUseIconResource = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mIcon = context.getDrawable(iconResource);
        } else mIcon = context.getResources().getDrawable(iconResource);
    }

    public void setIcon(Drawable icon) {
        mUseIconResource = true;
        mIcon = icon;
    }

    public boolean useTitleResource() {
        return mUseTitle;
    }

    public boolean useIconResource() {
        return mUseIconResource;
    }

    public void setShowAccountPicture(boolean show) {
        mShowAccountPicture = show;
    }

    public boolean getShowAccountPicture() {
        return mShowAccountPicture;
    }

    public void setAccountOptionClickListener(OnAccountOptionClickListener listener) {
        accountOptionClickListener = listener;
    }

    public OnAccountOptionClickListener getAccountOptionClickListener() {
        return accountOptionClickListener;
    }

}
