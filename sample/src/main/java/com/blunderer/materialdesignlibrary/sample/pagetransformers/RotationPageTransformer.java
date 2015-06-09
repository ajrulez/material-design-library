package com.blunderer.materialdesignlibrary.sample.pagetransformers;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by anujsaluja on 6/9/15.
 */
public class RotationPageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            page.setRotationY(position * -30);
        }
    }
}
