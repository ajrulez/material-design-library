package com.blunderer.materialdesignlibrary.sample.viewpagers;

import android.os.Bundle;

import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.sample.MainFragment;
import com.blunderer.materialdesignlibrary.sample.R;
import com.blunderer.materialdesignlibrary.sample.pagetransformers.DepthPageTransformer;
import com.blunderer.materialdesignlibrary.sample.pagetransformers.FadingPageTransformer;
import com.blunderer.materialdesignlibrary.sample.pagetransformers.RotationPageTransformer;
import com.blunderer.materialdesignlibrary.sample.pagetransformers.MagazineReaderPageTransformer;
import com.blunderer.materialdesignlibrary.sample.pagetransformers.ScalingPageTransformer;
import com.blunderer.materialdesignlibrary.sample.pagetransformers.ZoomOutPageTransformer;

public class ViewPagerWithTabsActivity
        extends com.blunderer.materialdesignlibrary.activities.ViewPagerWithTabsActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the ViewPager
        if(mViewPager != null) {
            // Set Page Transformation
            //mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
            //mViewPager.setPageTransformer(true, new FadingPageTransformer());
            //mViewPager.setPageTransformer(true, new RotationPageTransformer());
            //mViewPager.setPageTransformer(true, new ScalingPageTransformer());
            //mViewPager.setPageTransformer(true, new DepthPageTransformer());
            MagazineReaderPageTransformer trasformer =
                    new MagazineReaderPageTransformer(MagazineReaderPageTransformer.TransformType.ZOOM);
            mViewPager.setPageTransformer(true, trasformer);
        }
    }

    @Override
    public ViewPagerHandler getViewPagerHandler() {
        return new ViewPagerHandler(this)
                .addPage(R.string.title_section1,
                        MainFragment.newInstance("Material Design ViewPager with Tabs"))
                .addPage(R.string.title_section2,
                        MainFragment.newInstance("Material Design ViewPager with Tabs"));
    }

    @Override
    public boolean expandTabs() {
        return false;
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }

}
